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
package de.cau.cs.kieler.klay.layered.p3order;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import de.cau.cs.kieler.core.KielerRuntimeException;
import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.LPSolveAborter;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import lpsolve.LpSolveException;
import lpsolve.LpSolve;

/**
 * The main class of the LpSolve crossing minimizer component. It offers an algorithm to determine
 * an optimal ordering of all nodes in the layered graph using the LP-solver of
 * <a href="http://lpsolve.sourceforge.net/">lpsolve.sourceforge.net</a>.
 * 
 * @author msp
 */
public class LPSolveCrossingMinimizer extends AbstractAlgorithm implements ILayoutPhase {

    /** The timeout in milliseconds after which the LP solver is aborted. */
    private static final long LPSOLVE_TIMEOUT = 5000;
    
    /** arrays of nodes for each layer, sorted by node id. */
    private LNode[][] layerNodes;
    /** arrays of edges for each layer, sorted by source node id. */
    private LEdge[][] layerEdges;
    /** map of node identifier hashes to the indices of the corresponding ordering variables. */
    private Map<Integer, Integer> orderingVariables = new HashMap<Integer, Integer>();
    /** map of edge identifier hashes to the indices of the corresponding crossing variables. */
    private Map<Integer, Integer> crossingVariables = new HashMap<Integer, Integer>();

    // =============================== Initialization Methods =====================================

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        super.reset();
        orderingVariables.clear();
        crossingVariables.clear();
    }

    /**
     * Initialize the crossing minimizer for the given layered graph.
     * 
     * @param layeredGraph a layered graph
     */
    private void initialize(final LayeredGraph layeredGraph) {
        // assign unique identifiers to the nodes and collect them
        int index = 0;
        layerNodes = new LNode[layeredGraph.getLayers().size()][];
        ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();
            LNode[] nodes = new LNode[layer.getNodes().size()];
            layerNodes[layerIter.previousIndex()] = nodes;
            ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                LNode node = nodeIter.next();
                node.id = index++;
                nodes[nodeIter.previousIndex()] = node;
            }
        }
        
        // assign ordering variable indices
        index = 1;
        for (int r = 0; r < layerNodes.length; r++) {
            LNode[] nodes = layerNodes[r];
            for (int i = 0; i < nodes.length; i++) {
                for (int j = i + 1; j < nodes.length; j++) {
                    orderingVariables.put(getHash(nodes[i], nodes[j]), index++);
                }
            }
        }
        
        // collect the edges of the layered graph, without multi-edges
        layerEdges = new LEdge[layerNodes.length - 1][];
        Set<Integer> edgeHashes = new HashSet<Integer>();
        for (int r = 0; r < layerNodes.length - 1; r++) {
            LNode[] nodes = layerNodes[r];
            List<LEdge> edges = new LinkedList<LEdge>();
            for (int i = 0; i < nodes.length; i++) {
                for (LPort port : nodes[i].getPorts(PortType.OUTPUT)) {
                    for (LEdge edge : port.getEdges()) {
                        Integer hash = getHash(edge.getSource().getNode(), edge.getTarget().getNode());
                        if (!edgeHashes.contains(hash)) {
                            edges.add(edge);
                        }
                    }
                }
            }
            layerEdges[r] = edges.toArray(new LEdge[edges.size()]);
        }
        
        // assign crossing variable indices
        for (int r = 0; r < layerEdges.length; r++) {
            LEdge[] edges = layerEdges[r];
            for (int i = 0; i < edges.length; i++) {
                for (int j = i + 1; j < edges.length; j++) {
                    crossingVariables.put(getHash(edges[i], edges[j]), index++);
                }
            }
        }
    }

    // ======================== LpSolve Crossing Minimization Algorithm ===========================

    /**
     * {@inheritDoc}
     */
    public void execute(final LayeredGraph layeredGraph) {
        assert layeredGraph != null;
        getMonitor().begin("LpSolve Crossing Minimization", 1);
        if (layeredGraph.getLayers().size() <= 1) {
            getMonitor().done();
            return;
        }
        // initialize the LpSolve library, which may cause an UnsatisfiedLinkError
        try {
            LpSolve.initialize();
        } catch (UnsatisfiedLinkError error) {
            throw new KielerRuntimeException("LpSolve is not available for your platform."
                    + " Please choose another crossing minimization method.", error);
        }
        
        initialize(layeredGraph);
        
        // determine optimal ordering
        LpSolve lp = null;
        try {
            // construct LP
            lp = createLp(layeredGraph);
            LPSolveAborter abortListener = new LPSolveAborter(LPSOLVE_TIMEOUT, getMonitor());
            lp.putAbortfunc(abortListener, null);
            // execute LpSolver with the given LP
            int solution = lp.solve();
            if (solution == LpSolve.OPTIMAL || solution == LpSolve.SUBOPTIMAL) {
                // apply the solution
                applySolution(lp, layeredGraph);
            } else {
                if (solution == LpSolve.USERABORT && abortListener.isTimeoutOccurred()) {
                    solution = LpSolve.TIMEOUT;
                }
                throw new KielerRuntimeException(LPSolveAborter.getErrorMessage(solution));
            }
        } catch (LpSolveException exception) {
            throw new KielerRuntimeException("LpSolve crossing minimization failed.", exception);
        } finally {
            if (lp != null) {
                lp.deleteLp();
            }
            // dispose the created resources
            this.layerNodes = null;
            this.layerEdges = null;
            getMonitor().done();
        }
    }
    
    // CHECKSTYLEOFF MagicNumber
    private static final int HALF_WORD = Integer.SIZE / 2;
    private static final int QUARTER_WORD = Integer.SIZE / 4;
    
    /**
     * Calculate a hash value for the ordered node pair.
     * 
     * @param node1 first node
     * @param node2 second node
     * @return a hash value for the two nodes
     */
    private static int getHash(final LNode node1, final LNode node2) {
        return node1.id ^ (node2.id << HALF_WORD);
    }
    
    /**
     * Calculate a hash value for the ordered edge pair.
     * 
     * @param edge1 first edge
     * @param edge2 second edge
     * @return a hash value for the two edges
     */
    private static int getHash(final LEdge edge1, final LEdge edge2) {
        LNode node1s = edge1.getSource().getNode();
        LNode node1t = edge1.getTarget().getNode();
        LNode node2s = edge2.getSource().getNode();
        LNode node2t = edge2.getTarget().getNode();
        return node1s.id ^ (node2s.id << QUARTER_WORD)
                ^ (node1t.id << HALF_WORD) ^ (node2t.id << (HALF_WORD + QUARTER_WORD));
    }
    
    /**
     * Creates a new LP representing the crossing minimization problem of this graph.
     * 
     * @param layeredGraph the processed layered graph
     * @return the created LP representing the crossing minimization problem
     * @throws LpSolveException if the LP creation failed
     */
    private LpSolve createLp(final LayeredGraph layeredGraph) throws LpSolveException {
        // create LP instance
        int orderingCount = orderingVariables.size();
        int crossingCount = crossingVariables.size();
        int totalVars = orderingCount + crossingCount;
        LpSolve lp = LpSolve.makeLp(0, totalVars);
        lp.setVerbose(LpSolve.SEVERE);
        lp.setLpName("Crossing Minimization");
        lp.setMinim();
        
        // set variable types and bounds
        for (int j = 1; j <= totalVars; j++) {
            lp.setInt(j, true);
            lp.setBounds(j, 0, 1);
        }
        
        // set objective function
        double[] objFct = new double[1 + orderingCount + crossingCount];
        Arrays.fill(objFct, 1 + orderingCount, objFct.length, 1);
        lp.setObjFn(objFct);
        
        lp.setAddRowmode(true);
        // add 3-cycle constraints
        for (int r = 0; r < layerNodes.length; r++) {
            LNode[] nodes = layerNodes[r];
            for (int i = 0; i < nodes.length; i++) {
                for (int j = i + 1; j < nodes.length; j++) {
                    for (int k = j + 1; k < nodes.length; k++) {
                        int[] colno1 = new int[3];
                        colno1[0] = orderingVariables.get(getHash(nodes[i], nodes[j]));
                        colno1[1] = orderingVariables.get(getHash(nodes[j], nodes[k]));
                        colno1[2] = orderingVariables.get(getHash(nodes[i], nodes[k]));
                        int[] colno2 = Arrays.copyOf(colno1, colno1.length);
                        lp.addConstraintex(3, new double[] { 1, 1, -1 },
                                colno1, LpSolve.GE, 0);
                        lp.addConstraintex(3, new double[] { 1, 1, -1 },
                                colno2, LpSolve.LE, 1);
                    }
                }
            }
        }
        
        // add crossing variable constraints
        for (int r = 0; r < layerEdges.length; r++) {
            LEdge[] edges = layerEdges[r];
            for (int i = 0; i < edges.length; i++) {
                LEdge edge1 = edges[i];
                LNode source1 = edge1.getSource().getNode();
                LNode target1 = edge1.getTarget().getNode();
                for (int j = i + 1; j < edges.length; j++) {
                    LEdge edge2 = edges[j];
                    LNode source2 = edge2.getSource().getNode();
                    LNode target2 = edge2.getTarget().getNode();
                    if (source1 != source2 && target1 != target2) {
                        int crossIndex = crossingVariables.get(getHash(edge1, edge2));
                        int[] colno1 = new int[3];
                        if (target1.id < target2.id) {
                            colno1[0] = orderingVariables.get(getHash(target1, target2));
                            colno1[1] = orderingVariables.get(getHash(source1, source2));
                            colno1[2] = crossIndex;
                            int[] colno2 = Arrays.copyOf(colno1, colno1.length);
                            lp.addConstraintex(3, new double[] { 1, -1, 1 },
                                    colno1, LpSolve.GE, 0);
                            lp.addConstraintex(3, new double[] { 1, -1, -1 },
                                    colno2, LpSolve.LE, 0);
                        } else  {
                            colno1[0] = orderingVariables.get(getHash(target2, target1));
                            colno1[1] = orderingVariables.get(getHash(source1, source2));
                            colno1[2] = crossIndex;
                            int[] colno2 = Arrays.copyOf(colno1, colno1.length);
                            lp.addConstraintex(3, new double[] { 1, 1, 1 },
                                    colno1, LpSolve.GE, 1);
                            lp.addConstraintex(3, new double[] { 1, 1, -1 },
                                    colno2, LpSolve.LE, 1);
                        }
                    }
                }
            }
        }
        
        // break symmetry by setting a single ordering variable to 1
        lp.setLowbo(1, 1);
        
        return lp;
        // CHECKSTYLEON MagicNumber
    }

    /**
     * Applies the determined solution of the ILP to the layered graph.
     * 
     * @param lp the ILP to apply the solution
     * @throws LpSolveException if an error occurred during solution retrieving
     */
    private void applySolution(final LpSolve lp, final LayeredGraph layeredGraph)
            throws LpSolveException {
        final int rowCount = lp.getNrows();
        final double[] solution = new double[1 + rowCount + lp.getNcolumns()];
        lp.getPrimalSolution(solution);
        
        // sort each layer by the solution array
        for (Layer layer : layeredGraph.getLayers()) {
            Collections.sort(layer.getNodes(), new Comparator<LNode>() {
                public int compare(final LNode node1, final LNode node2) {
                    if (node1.id <= node2.id) {
                        double result = solution[getHash(node1, node2) + rowCount];
                        return (result > 1.0 / 2) ? -1 : 1;
                    } else {
                        double result = solution[getHash(node2, node1) + rowCount];
                        return (result > 1.0 / 2) ? 1 : -1;
                    }
                }
            });
        }
        
        // distribute the ports of each node
        distributePorts(layeredGraph);
    }
    
    /**
     * Distribute the ports of all nodes of the layered graph.
     * 
     * @param layeredGraph the processed layered graph
     */
    private void distributePorts(final LayeredGraph layeredGraph) {
        // reassign node and port identifiers
        for (Layer layer : layeredGraph.getLayers()) {
            int nodeid = 0;
            for (LNode node : layer.getNodes()) {
                node.id = nodeid++;
                int portid = 0;
                for (LPort port : node.getPorts()) {
                    port.id = portid++;
                    // set input ports left, output ports right
                    switch (port.getType()) {
                    case INPUT:
                        port.setSide(PortSide.WEST);
                        break;
                    case OUTPUT:
                        port.setSide(PortSide.EAST);
                        break;
                    }
                }
            }
        }
        
        // calculate barycenter values for the ports of each node and sort them
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                float[] portBarycenter = new float[node.getPorts().size()];
                for (LPort port : node.getPorts()) {
                    int sum = 0;
                    for (LPort connectedPort : port.getConnectedPorts()) {
                        sum += connectedPort.getNode().id;
                    }
                    if (port.getEdges().isEmpty()) {
                        portBarycenter[port.id] = -1;
                    } else {
                        portBarycenter[port.id] = sum / port.getEdges().size();
                    }
                }
                node.sortPorts(portBarycenter);
            }
        }
    }

}
