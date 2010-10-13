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
package de.cau.cs.kieler.klay.layered.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.KielerRuntimeException;
import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.IBigNodeHandler;
import de.cau.cs.kieler.klay.layered.modules.ILayerer;
import lpsolve.AbortListener;
import lpsolve.LpSolveException;
import lpsolve.LpSolve;

/**
 * The main class of the LpSolve-layerer component. It offers an algorithm to determine an optimal
 * layering of all nodes in the graph concerning a minimal edge span using the LP-solver of
 * lpsolve.sourceforge.net.
 * 
 * @see de.cau.cs.kieler.klay.layered.modules.ILayerer ILayerer
 * 
 * @author pdo
 */
public class LPSolveLayerer extends AbstractAlgorithm implements ILayerer {

    // ================================== Attributes ==============================================

    /** The timeout in milliseconds after which the LP solver is aborted. */
    private static final long LPSOLVE_TIMEOUT = 5000;

    /** The layered graph all methods in this class operate on. */
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

    // ================================== Constructor =============================================

    /**
     * Default Constructor for {@link LPSolveLayerer}. It creates a new instance of this class.
     */
    public LPSolveLayerer() {
    }

    // ================================= Inner Classes ============================================

    /**
     * A abort listener for the LpSolve-Layerer used to abort the execution when the timeout was
     * reached.
     * 
     * @see de.cau.cs.kieler.klay.layered.impl.LPSolveLayerer#LPSOLVE_TIMEOUT LPSOLVE_TIMEOUT
     */
    private class LpSolveLayererAborter implements AbortListener {

        /** A flag indicating whether a timeout has occurred. */
        private boolean timeout = false;

        /**
         * The start time for the timeout. It is set, when the specific instance of this class is
         * created.
         */
        private long startTime = System.currentTimeMillis();

        /**
         * {@inheritDoc}
         */
        public boolean abortfunc(final LpSolve problem, final Object userhandle)
                throws LpSolveException {

            return System.currentTimeMillis() - startTime > LPSOLVE_TIMEOUT;
        }
    }

    // =============================== Initialization Methods =====================================

    /**
     * Helper method for the LpSolve-layerer. It instantiates all necessary attributes for the
     * execution of the LpSolve-layerer and initializes them with their default values. If the
     * attributes already exist (i.e. they were created by a previous function call) and if their
     * size fits for the nodes of the current graph, then the old instances will be reused as far as
     * possible. Furthermore, all edges in the graph will be determined, as well as the number of
     * incoming and outgoing edges of each node ( {@code inDegree}, respectively {@code outDegree}).
     * All sinks nodes in the graph identified in this step will be added to {@code sinks}.
     * 
     * @param nodes
     *            a {@code Collection} containing all nodes of the graph
     */
    private void initialize(final Collection<LNode> nodes) {

        // initialize node attributes
        int numNodes = nodes.size();
        if (inDegree == null || inDegree.length < nodes.size()) {
            inDegree = new int[nodes.size()];
            outDegree = new int[nodes.size()];
            layer = new int[numNodes];
        } else {
            Arrays.fill(inDegree, 0);
            Arrays.fill(outDegree, 0);
            Arrays.fill(layer, 0);
        }
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
    }

    // ============================== LpSolve-Layering Algorithm ==================================

    /**
     * The main method of the LpSolve-layerer component. It determines an optimal layering of all
     * nodes in the graph concerning a minimal edge span using the LP-solver of
     * lpsolve.sourceforge.net.
     * 
     * @param nodes
     *            a {@code Collection} of all nodes of the graph to layer
     * @param layeredGraph
     *            an initially empty layered graph which is filled with layers
     * @see de.cau.cs.kieler.klay.layered.modules.ILayerer ILayerer
     */
    public void layer(final Collection<LNode> nodes, final LayeredGraph layeredGraph) {
        assert nodes != null;
        assert layeredGraph != null;
        getMonitor().begin("LpSolve layering", 1);
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
            LpSolveLayererAborter abortListener = new LpSolveLayererAborter();
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
                if (solution == LpSolve.USERABORT && abortListener.timeout) {
                    solution = LpSolve.TIMEOUT;
                }
                throw new RuntimeException(getErrorMessage(solution));
            }
        } catch (LpSolveException exception) {
            throw new RuntimeException("LpSolve layering failed.", exception);
        } finally {
            if (lp != null) {
                lp.deleteLp();
            }
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
        lp.setMinim();
        // set objective function
        double[] objFct = new double[layerNodes.size() + 1];
        for (LEdge edge : layerEdges) {
            objFct[edge.getSource().getNode().id + 1]--;
            objFct[edge.getTarget().getNode().id + 1]++;
        }
        lp.setObjFn(objFct);
        // set variable bounds
        int upperBound = layerNodes.size() - 1;
        for (int i = 1; i <= layerNodes.size(); i++) {
            lp.setBounds(i, 0, upperBound);
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
     * Helper method for the LpSolve-layerer. It applies the determined solution of the LP to the
     * graph, i.e. retrieves the solution from the LP-Solver, balances the layering and assigns each
     * node to its determined layer in {@code layeredGraph}.
     * 
     * @param lp
     *            the LP to apply its solution
     * @throws LpSolveException
     *             if an error occurred during solution retrieving
     * 
     * @see de.cau.cs.kieler.klay.layered.impl.LPSolveLayerer#balance(int[]) balance()
     * @see de.cau.cs.kieler.klay.layered.impl.LPSolveLayerer#putNode(LNode) putNode()
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
     * Helper method for the LpSolve-Layerer. It returns a readable error message for an LpSolve
     * error.
     * 
     * @param lpSolveError
     *            the return value of LpSolve indicating an error
     * @return the readable message created for the LpSolve error
     */
    private String getErrorMessage(final int lpSolveError) {

        switch (lpSolveError) {
        case LpSolve.NOMEMORY:
            return "The LpSolver has run out of memory";
        case LpSolve.INFEASIBLE:
            return "The LP is infeasible.";
        case LpSolve.UNBOUNDED:
            return "The LP is unbounded.";
        case LpSolve.DEGENERATE:
            return "The LP is degenerative.";
        case LpSolve.NUMFAILURE:
            return "A numerical failure has been encountered.";
        case LpSolve.USERABORT:
            return "LpSolver aborted by user.";
        case LpSolve.TIMEOUT:
            return "No feasible solution found after " + LPSOLVE_TIMEOUT + "ms.";
        case LpSolve.PROCFAIL:
            return "The branch and bound routine failed.";
        case LpSolve.PROCBREAK:
            return "The branch and bound was stopped because of a break-at-first or a break-at-value.";
        case LpSolve.NOFEASFOUND:
            return "No feasible branch-and-bound solution found.";
        default:
            return "No feasible solution found. LpSolver return value: " + lpSolveError;
        }
    }

    /**
     * Helper method for the LpSolve-layerer. It determines the length of the shortest incoming
     * respectively outgoing edge of the input node.
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
     * Helper method for the LpSolve-layerer. It balances the layering concerning its width, i.e.
     * the number of nodes in each layer. If the graph allows multiple optimal layerings regarding a
     * minimal edge length, this method moves separate nodes to a layer with a minimal amount of
     * contained nodes with respect to the retention of feasibility and optimality of the given
     * layering.
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
                newLayer = layer[node.id];
                range = minimalSpan(node);
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
     * Helper method for the network simplex layerer. It puts the specified node into its assigned
     * layer indicated by {@code layer} in the layered graph. If the layered graph does not contain
     * the specified layer (i.e. the number of layers in {@code layeredGraph} is lesser than the
     * supposed height in the layering), additional layers will be added to match the required
     * amount.
     * 
     * @param node
     *            the node to put into its assigned layer in the layered graph
     * 
     * @see de.cau.cs.kieler.klay.layered.impl.LPSolveLayerer#layeredGraph layeredGraph
     * @see de.cau.cs.kieler.klay.layered.impl.LPSolveLayerer#layer layer
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
