/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p2layers;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.KielerRuntimeException;
import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.layered.LPSolveAborter;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import lpsolve.LpSolveException;
import lpsolve.LpSolve;

/**
 * The main class of the LpSolve layerer component. It offers an algorithm to determine an optimal
 * layering of all nodes in the graph concerning a minimal edge span using the LP-solver of <a
 * href="http://lpsolve.sourceforge.net/">lpsolve.sourceforge.net</a>.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>the graph has no cycles</dd>
 *   <dt>Postcondition:</dt><dd>all nodes have been assigned a layer such that
 *     edges connect only nodes from layers with increasing indices</dd>
 * </dl>
 * 
 * @see de.cau.cs.kieler.klay.layered.p2layers.ILayerer ILayerer
 * 
 * @author pdo
 */
public class LPSolveLayerer extends AbstractAlgorithm implements ILayoutPhase {

    // ================================== Attributes ==============================================

    /** The timeout in milliseconds after which the LP solver is aborted. */
    private static final long LPSOLVE_TIMEOUT = 5000;

    /** The layered graph which all methods in this class operate on. */
    private LayeredGraph layerGraph;

    /** A {@code Collection} containing all nodes in the graph to layer. */
    private Collection<LNode> layerNodes;

    /** A {@code LinkedList} containing all edges in the graph. */
    private LinkedList<LEdge> layerEdges;

    /**
     * A {@code LinkedList} containing all sink nodes of the graph, i.e. all nodes that have no
     * incident outgoing edges.
     */
    private LinkedList<LNode> sinks;

    /** The number of incoming edges incident to each node. */
    private int[] inDegree;

    /** The number of outgoing edges incident to each node. */
    private int[] outDegree;

    /**
     * The layer each node is currently assigned to. Note that during layerer execution, the lowest
     * layer is not necessary the zeroth layer. To fulfill this characteristic, a final call of
     * {@code normalize()} has to be performed.
     */
    private int[] layer;

    /**
     * The length of the longest path in the graph from a source to sink node defined by the number
     * of nodes in this path minus one.
     */
    private int longestPath;

    // =============================== Initialization Methods =====================================
    
    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingStrategy getIntermediateProcessingStrategy() {
        return null;
    }

    /**
     * Instantiates all necessary attributes for the execution of the LpSolve-layerer and
     * initializes them with their default values. All edges in the graph will be determined, as
     * well as the number of incoming and outgoing edges of each node ( {@code inDegree},
     * respectively {@code outDegree}). All sinks nodes in the graph identified in this step will be
     * added to {@code sinks}.
     * 
     * @param nodes
     *            a {@code Collection} containing all nodes of the graph
     */
    private void initialize(final Collection<LNode> nodes) {
        // initialize node attributes
        int numNodes = nodes.size();
        inDegree = new int[nodes.size()];
        outDegree = new int[nodes.size()];
        layer = new int[numNodes];
        sinks = new LinkedList<LNode>();
        layerNodes = nodes;

        // determine edges and re-index nodes
        int index = 0;
        LinkedList<LEdge> edges = new LinkedList<LEdge>();
        for (LNode node : nodes) {
            node.id = index++;
            for (LPort port : node.getPorts()) {
                if (port.getType() == PortType.OUTPUT) {
                    edges.addAll(port.getEdges());
                    outDegree[node.id] += port.getEdges().size();
                } else if (port.getType() == PortType.INPUT) {
                    inDegree[node.id] += port.getEdges().size();
                }
            }
            if (outDegree[node.id] == 0) {
                sinks.add(node);
            }
        }
        // re-index edges
        int counter = 0;
        for (LEdge edge : edges) {
            edge.id = counter++;
        }
        layerEdges = edges;
        // determine longest path
        longestPath = 0;
        for (LNode node : sinks) {
            longestPath = Math.max(longestPath, longestPathDFS(node));
        }
    }

    /**
     * Helper method for the LpSolve-layerer. It determines the length of the longest path from a
     * source node to the specified node defined by the number of nodes in this path minus one.
     * 
     * @param sink
     *            the node to determine the length of the longest path from a source node to it
     * @return the length of the longest path from a source node to the specified node defined by
     *         the number of nodes in this path minus one.
     */
    private int longestPathDFS(final LNode sink) {
        int path = 0;
        for (LPort port : sink.getPorts(PortType.INPUT)) {
            for (LEdge edge : port.getEdges()) {
                path = Math.max(path, longestPathDFS(edge.getSource().getNode()) + 1);
            }
        }
        return path;
    }

    /**
     * Release all created resources so the garbage collector can reap them.
     */
    private void dispose() {
        this.inDegree = null;
        this.outDegree = null;
        this.layer = null;
        this.layerEdges = null;
        this.layerNodes = null;
        this.layerGraph = null;
        this.sinks = null;
    }

    // ============================== LpSolve-Layering Algorithm ==================================

    /**
     * The main method of the LpSolve-layerer component. It determines an optimal layering of all
     * nodes in the graph concerning a minimal edge span using the LP-solver of
     * lpsolve.sourceforge.net.
     * 
     * @param layeredGraph
     *            a layered graph which initially only contains layerless nodes and is
     *            then filled with layers
     * @see de.cau.cs.kieler.klay.layered.p2layers.ILayerer ILayerer
     */
    public void process(final LayeredGraph layeredGraph) {
        assert layeredGraph != null;
        
        getMonitor().begin("LpSolve layering", 1);
        
        Collection<LNode> nodes = layeredGraph.getLayerlessNodes();
        if (nodes.size() < 1) {
            getMonitor().done();
            return;
        }
        
        layerGraph = layeredGraph;
        
        // initialize the LpSolve library, which may cause an UnsatisfiedLinkError
        try {
            LpSolve.initialize();
        } catch (UnsatisfiedLinkError error) {
            throw new KielerRuntimeException("LpSolve is not available for your platform."
                    + " Please choose another layering method.", error);
        }

        // enhance layering, if requested
        LayeringEnhancer enhancer = null;
        if (layeredGraph.getProperty(Properties.ENHANCE_LAYERING)) {
            enhancer = new LayeringEnhancer();
            enhancer.preProcess(nodes);
        }

        // support wide nodes, if requested
        IBigNodeHandler bigNodeHandler = null;
        if (layeredGraph.getProperty(Properties.DISTRIBUTE_NODES)) {
            bigNodeHandler = new BigNodeHandler();
            bigNodeHandler.splitWideNodes(nodes, layeredGraph);
        }

        initialize(nodes);

        // determine optimal layering
        LpSolve lp = null;
        try {
            // construct LP
            lp = createLp();
            LPSolveAborter abortListener = new LPSolveAborter(LPSOLVE_TIMEOUT, getMonitor());
            lp.putAbortfunc(abortListener, null);
            // execute LpSolver with the given LP
            int solution = lp.solve();
            if (solution == LpSolve.OPTIMAL || solution == LpSolve.SUBOPTIMAL) {
                // apply the solution
                applySolution(lp);
                // segmentate layering, if requested
                if (layeredGraph.getProperty(Properties.DISTRIBUTE_NODES)
                        && layeredGraph.getProperty(Properties.SEGMENTATE_LAYERING)) {
                    bigNodeHandler.segmentateLayering();
                }

                if (enhancer != null) {
                    enhancer.postProcess();
                }
            } else {
                if (solution == LpSolve.USERABORT && abortListener.isTimeoutOccurred()) {
                    solution = LpSolve.TIMEOUT;
                }
                throw new KielerRuntimeException(LPSolveAborter.getErrorMessage(solution));
            }
        } catch (LpSolveException exception) {
            throw new KielerRuntimeException("LpSolve layering failed.", exception);
        } finally {
            if (lp != null) {
                lp.deleteLp();
            }
            
            // empty the list of unlayered nodes
            nodes.clear();
            
            // release the created resources
            dispose();
            getMonitor().done();
        }
    }

    /**
     * Helper method for the LpSolve-layerer. It creates a new LP representing the layering problem
     * of this graph.
     * 
     * @return the created LP representing the layering problem
     * @throws LpSolveException
     *             if the LP creation failed
     */
    private LpSolve createLp() throws LpSolveException {
        // create LP instance
        LpSolve lp = LpSolve.makeLp(layerEdges.size(), layerNodes.size());
        lp.setVerbose(LpSolve.SEVERE);
        lp.setLpName("Layering");
        lp.setMinim();
        // set objective function
        double[] objFct = new double[layerNodes.size() + 1];
        for (LEdge edge : layerEdges) {
            int priority = edge.getProperty(Properties.PRIORITY);
            if (priority >= 0) {
                objFct[edge.getSource().getNode().id + 1]--;
                objFct[edge.getTarget().getNode().id + 1]++;
            }
        }
        lp.setObjFn(objFct);
        // set variable bounds
        for (int i = 1; i <= layerNodes.size(); i++) {
            lp.setBounds(i, 0, longestPath);
        }
        // set variable types
        for (int i = 1; i < objFct.length; i++) {
            lp.setInt(i, true);
        }
        // add constraints
        for (LEdge edge : layerEdges) {
            lp.setMat(edge.id + 1, edge.getSource().getNode().id + 1, -1);
            lp.setMat(edge.id + 1, edge.getTarget().getNode().id + 1, 1);
        }
        for (int i = 1; i <= layerEdges.size(); i++) {
            lp.setRh(i, 1);
            lp.setConstrType(i, LpSolve.GE);
        }
        return lp;
    }

    /**
     * Applies the determined solution of the LP to the graph, i.e. retrieves the solution from the
     * LP-Solver, balances the layering and assigns each node to its determined layer in
     * {@code layeredGraph}.
     * 
     * @param lp
     *            the LP to apply its solution
     * @throws LpSolveException
     *             if an error occurred during solution retrieving
     */
    private void applySolution(final LpSolve lp) throws LpSolveException {
        int rowCount = lp.getNrows();
        double[] solution = new double[1 + rowCount + lp.getNcolumns()];
        lp.getPrimalSolution(solution);
        // assign the layer to each node
        for (LNode node : layerNodes) {
            layer[node.id] = (int) solution[1 + rowCount + node.id];
        }
        // balance the layers
        balance();
        // put nodes into their determined layers
        for (LNode node : layerNodes) {
            putNode(node);
        }
        // remove empty layers
        Iterator<Layer> iterator = layerGraph.getLayers().iterator();
        Layer curLayer = null;
        while (iterator.hasNext()) {
            curLayer = iterator.next();
            if (curLayer.getNodes().isEmpty()) {
                iterator.remove();
            }
        }
    }

    /**
     * Determines the length of the shortest incoming, respectively outgoing edge of the input node.
     * 
     * @param node
     *            the node to determine the length of its shortest incident incoming and outgoing
     *            edge
     * @return a pair containing the length of the shortest incoming (first element) and outgoing
     *         edge (second element) incident to the input node or {@code -1} as the length, if no
     *         such edge is incident
     * 
     * @see de.cau.cs.kieler.core.util.Pair Pair
     */
    private Pair<Integer, Integer> minimalSpan(final LNode node) {
        int minSpanOut = Integer.MAX_VALUE;
        int minSpanIn = Integer.MAX_VALUE;
        int currentSpan;

        for (LPort port : node.getPorts()) {
            for (LEdge edge : port.getEdges()) {
                currentSpan = layer[edge.getTarget().getNode().id]
                        - layer[edge.getSource().getNode().id];
                if (port.getType() == PortType.INPUT && currentSpan < minSpanIn) {
                    minSpanIn = currentSpan;
                } else if (currentSpan < minSpanOut) {
                    minSpanOut = currentSpan;
                }
            }
        }
        if (minSpanIn == Integer.MAX_VALUE) {
            minSpanIn = -1;
        }
        if (minSpanOut == Integer.MAX_VALUE) {
            minSpanOut = -1;
        }

        return new Pair<Integer, Integer>(minSpanIn, minSpanOut);
    }

    /**
     * Balances the layering concerning its width, i.e. the number of nodes in each layer. If the
     * graph allows multiple optimal layerings regarding a minimal edge length, this method moves
     * separate nodes to a layer with a minimal amount of contained nodes with respect to the
     * retention of feasibility and optimality of the given layering.
     */
    private void balance() {
        // determine filling structure of the layers
        int highest = Integer.MIN_VALUE;
        for (LNode node : sinks) {
            if (layer[node.id] > highest) {
                highest = layer[node.id];
            }
        }
        // normalize and determine layer filling
        int[] filling = new int[highest + 1];
        for (LNode node : layerNodes) {
            filling[layer[node.id]]++;
        }
        // determine possible layers
        int newLayer;
        Pair<Integer, Integer> range = null;
        for (LNode node : layerNodes) {
            if (inDegree[node.id] == outDegree[node.id]) {
                if (inDegree[node.id] != 0) {
                    newLayer = layer[node.id];
                    range = minimalSpan(node);
                } else {
                    newLayer = 0;
                    range = new Pair<Integer, Integer>(1, longestPath + 1);
                }
                for (int i = layer[node.id] - range.getFirst() + 1; i < layer[node.id]
                        + range.getSecond(); i++) {
                    if (filling[i] < filling[newLayer]) {
                        newLayer = i;
                    }
                }
                // assign new layer
                if (filling[newLayer] < filling[layer[node.id]]) {
                    filling[layer[node.id]]--;
                    filling[newLayer]++;
                    layer[node.id] = newLayer;
                }

            }
        }
    }

    /**
     * Puts the specified node into its assigned layer indicated by {@code layer} in the layered
     * graph. If the layered graph does not contain the specified layer (i.e. the number of layers
     * in {@code layeredGraph} is lesser than the supposed height in the layering), additional
     * layers will be added to match the required amount.
     * 
     * @param node
     *            the node to put into its assigned layer in the layered graph
     * 
     * @see de.cau.cs.kieler.klay.layered.p2layers.LPSolveLayerer#layeredGraph layeredGraph
     * @see de.cau.cs.kieler.klay.layered.p2layers.LPSolveLayerer#layer layer
     */
    private void putNode(final LNode node) {
        List<Layer> layers = layerGraph.getLayers();
        // add additional layers to match required amount
        while (layers.size() <= layer[node.id]) {
            layers.add(layers.size(), new Layer(layerGraph));
        }
        node.setLayer(layers.get(layer[node.id]));
    }

}
