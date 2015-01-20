/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p2layers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateProcessorStrategy;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;

/**
 * @author uru
 */
public class HeuristicGeneralizedLayerer implements ILayoutPhase {

    private LGraph graph;
    private Layer[] graphLayers;
    
    
    private Adjancency[] adjencency;

    private LNode[] nodes;
    
    // number of layers a node can be moved to the left
    private int[] move;

    // profit function: what do we win if we move the node
    private double[] profit;
    
    
    private double wLen = 1;
    private double wRev = 1;

    private Random random;
    
    /** intermediate processing configuration. */
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION =
            IntermediateProcessingConfiguration.createEmpty()
                    //.addBeforePhase2(IntermediateProcessorStrategy.GLAY_PREPROCESSOR)
                    .addAfterPhase5(IntermediateProcessorStrategy.REVERSED_EDGE_RESTORER);

    

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {
        graph = layeredGraph;
        random = layeredGraph.getProperty(InternalProperties.RANDOM);
        
        
        // ---------------------
        // #1 Create an initial layering where each layer holds a single node
        // ---------------------
        constructiveLayering();
        
        // ---------------------
        // #2 Reverse edges such that the graph is acyclic with regards to the order
        //    calculated in #1
        // ---------------------
        for (LNode n : layeredGraph.getLayerlessNodes()) {
            for (LEdge e : Lists.newArrayList(n.getOutgoingEdges())) {
                boolean reversed = e.getTarget().getNode().id < n.id;
                if (reversed) {
                    e.reverse(layeredGraph, true);
                    layeredGraph.setProperty(InternalProperties.CYCLIC, true);
                }
            }
        }
        
        // ---------------------
        // #3 run Gansner et al.'s algorithm to compact everything nicely
        // ---------------------
        new NetworkSimplexLayerer().process(layeredGraph, progressMonitor);

        // store the layers we created for quick access
        graphLayers = new Layer[graph.getLayers().size()];
        int index = 0;
        for (Layer l : graph.getLayers()) {
            graphLayers[index++] = l;
        }
        
        // ---------------------
        // #4 Restore original edge directions
        // ---------------------
        for (Layer layer : layeredGraph) {
            for (LNode node : layer) {
                for (LPort port : node.getPorts()) {
                    LEdge[] edgeArray = port.getOutgoingEdges().toArray(
                            new LEdge[port.getOutgoingEdges().size()]);
                    
                    for (LEdge edge : edgeArray) {
                        if (edge.getProperty(InternalProperties.REVERSED)) {
                            edge.reverse(layeredGraph, true);
                        }
                    }
                }
            }
        }
        

        // ---------------------
        // #5 Remove leafs
        // ---------------------
        // collect all nodes to check for leafs
        Queue<LNode> nodeQueue = new LinkedList<LNode>();
        for (Layer layer : layeredGraph) {
            nodeQueue.addAll(layer.getNodes());
        }
        
        Stack<LeafNode> leafNodes = new Stack<LeafNode>();
        while (!nodeQueue.isEmpty()) {
            LNode node = nodeQueue.poll();
            
           if (isLeafNode(node)) {
                LeafNode ln = new LeafNode(node);
                leafNodes.push(ln);
                LNode adj = ln.detach();
                // we might have created a new leaf node
                nodeQueue.add(adj);
            }
        }
        
        // ---------------------
        // #6 Improve result by performing moves
        // ---------------------
        initialize();
        
        determineAdjencency();
        
        determinePossibleMoves();
        
        determineProfits();
        
        performMoves();
        

        // ---------------------
        // #7 Re-attach leaf nodes
        // ---------------------
        while (!leafNodes.isEmpty()) {
            LeafNode ln = leafNodes.pop();
            ln.reattach();
        }
        
        
        // ------------------
        // #8 Set new edge-reversal state
        // ---------------------
        for (Layer l : graph) {
            for (LNode n : l.getNodes()) {
                for (LEdge e : Lists.newArrayList(n.getOutgoingEdges())) {
                    boolean reversed =
                            e.getTarget().getNode().getLayer().getIndex() < l.getIndex();
                    if (reversed) {
                        e.reverse(layeredGraph, true);
                        layeredGraph.setProperty(InternalProperties.CYCLIC, true);
                    }
                }
            }
        }
        
        // ------------------
        // #9 Remove any non-occupied layers
        // ---------------------
        Iterator<Layer> layerIt = graph.getLayers().iterator();
        while (layerIt.hasNext()) {
            Layer l = layerIt.next();
            if (l.getNodes().isEmpty()) {
                layerIt.remove();
            }
        }
        
        cleanup();
    }

    private void initialize() {
        int index = 0;
        for (Layer l : graph) {
            for (LNode n : l) {
                n.id = index++;
            }
        }
        
        adjencency = new Adjancency[index];
        move = new int[index];
        profit = new double[index];
        nodes = new LNode[index];
        
        // only for debugging
        for (Layer l : graph) {
            for (LNode n : l) {
                nodes[n.id] = n;
            }
        }
        
    }
    
    private void cleanup() {
        adjencency = null;
        move = null;
        profit = null;
        nodes = null;
        graphLayers = null;
        graph = null;
    }
    
    private void constructiveLayering() {
        Set<LNode> unassigned = Sets.newHashSet();
        Set<LNode> assigned = Sets.newHashSet();
        Set<LNode> candidates = Sets.newHashSet();
        
        unassigned.addAll(graph.getLayerlessNodes());

        // randomly choose a starting node
        int r = random.nextInt(graph.getLayerlessNodes().size());
        LNode u = graph.getLayerlessNodes().get(r);
        unassigned.remove(u);
        assigned.add(u);

        // add adjacent nodes to the candidate list
        for (LNode v : getAdjacentNodes(u)) {
            candidates.add(v);
        }
        
        int index = 0;
        u.id = index;

        int lIndex = -1;
        int rIndex = 1;

        while (!unassigned.isEmpty()) {
            index++;

            // compute quality of possible solutions
            int minDegree = Integer.MAX_VALUE;
            // if < 0 place the node left, otherwise right
            double side = 0;
            LNode minDegNode = null;
            for (LNode v : candidates) {

                // incoming edges to v that have been assigned
                int incAssigned = 0;
                // outgoing edges of v that have been assigned
                int outAssigned = 0;

                // unassigned and assigned neighbors of v
                int du = 0, da = 0;

                for (LEdge we : v.getIncomingEdges()) {
                    LNode w = we.getSource().getNode();
                    if (unassigned.contains(w)) {
                        du++;
                    } else {
                        da++;
                        incAssigned++;
                    }
                }

                for (LEdge we : v.getOutgoingEdges()) {
                    LNode w = we.getTarget().getNode();
                    if (unassigned.contains(w)) {
                        du++;
                    } else {
                        da++;
                        outAssigned++;
                    }
                }

                int score = du - da;
                if (score < minDegree) {
                    minDegNode = v;
                    
                    // decide which side to place the node to

                    // score if placed on the left 
                    int edgeLengthLeft = 0;
                    for (LNode adj : getAdjacentNodes(minDegNode)) {
                        if (assigned.contains(adj)) {
                           edgeLengthLeft += Math.abs(adj.id - lIndex); 
                        }
                    }
                    int revEdgesLeft = 0;
                    for (LNode pred : getPredecessorNodes(minDegNode)) {
                        if (assigned.contains(pred)) {
                            revEdgesLeft++;
                        }
                    }
                    double scoreLeft = wLen * edgeLengthLeft + wRev * revEdgesLeft;
                    
                    // score if placed to the right
                    int edgeLengthRight = 0;
                    for (LNode adj : getAdjacentNodes(minDegNode)) {
                        if (assigned.contains(adj)) {
                            edgeLengthRight += Math.abs(rIndex - adj.id); 
                         }
                    }
                    int revEdgesRight = 0;
                    for (LNode succ: getSuccessorNodes(minDegNode)) {
                        if (assigned.contains(succ)) {
                            revEdgesRight++;
                        }
                    }
                    double scoreRight = wLen * edgeLengthRight + wRev * revEdgesRight;
                     
//                    System.out.println(revEdgesLeft + " " + revEdgesRight);
//                    System.out.println("Right: " + scoreRight + " Left: " + scoreLeft);

                    // position on left side if sL < sR
                    
                    // ## strategy 1 (thought this is more sophisticated)
//                    side = scoreLeft - scoreRight;
                    
                    // ## strategy 2 (looks more promising atm)
                    side = incAssigned - outAssigned;
                }
                minDegree = Math.min(minDegree, score);
            }

            // label the selected node
            // minDegNode.id = index;

            if (side < 0) {
                minDegNode.id = lIndex--;
            } else {
                minDegNode.id = rIndex++;
            }

            assigned.add(minDegNode);
            unassigned.remove(minDegNode);
            candidates.remove(minDegNode);

            // add adjacent nodes to the candidates list
            for (LNode w : getAdjacentNodes(minDegNode)) {
                // if they have not been assigned so far
                if (unassigned.contains(w)) {
                    candidates.add(w);
                }
            }
            
        }

        // shift all node layers and assign them to layers
        for (LNode n : graph.getLayerlessNodes()) {
            n.id += (-1 * lIndex) - 1;
//            System.out.println(n + " " + n.id);
        }
    }

    

    private void determineAdjencency() {
        for (Layer l : graph) {
            for (LNode n : l) {
                Adjancency a = new Adjancency();
                adjencency[n.id] = a;
                int nLayer = n.getLayer().getIndex();
                for (LEdge e : n.getOutgoingEdges()) {
                    if (isSelfloop(e)) {
                        continue;
                    }
                    if (e.getTarget().getNode().getLayer().getIndex() > nLayer) {
                        // n --> t
                        a.rightSuccessors.add(e.getTarget().getNode());
                    } else {
                        // t <-- n
                        a.leftSuccessors.add(e.getTarget().getNode());
                    }
                }

                for (LEdge e : n.getIncomingEdges()) {
                    if (isSelfloop(e)) {
                        continue;
                    }
                    if (e.getSource().getNode().getLayer().getIndex() > nLayer) {
                        // n <-- s
                        a.rightPredecessors.add(e.getSource().getNode());
                    } else {
                        // s --> n
                        a.leftPredecessors.add(e.getSource().getNode());
                    }
                }
            }
        }
    }
    
    private void determinePossibleMoves() {
        for (Layer l : graph) {
            for (LNode n : l) {
                determinePossibleMove(n);
            }
        }
    }

    private void determinePossibleMove(final LNode n) {

        int layer = n.getLayer().getIndex();
        move[n.id] = 0;
        
        // # there are no edges to change into the 'correct' direction
        if (adjencency[n.id].leftSuccessors.isEmpty()) {
            return;
        }
        
        // # if there are no left predecessors
        if (adjencency[n.id].leftPredecessors.isEmpty()) {
            int minLeftSucc = getMinLayer(adjencency[n.id].leftSuccessors);
            move[n.id] = layer - minLeftSucc + 1;
            return; 
        }
        
        // #

        // max layer of n.leftPred
        int maxLeftPred = getMaxLayer(adjencency[n.id].leftPredecessors);
        // TODO check for further reversed edges

        // a move only pays off if the node is moved at least by 2
        int pays = layer - 2 - maxLeftPred;
        if (pays > 0) {
            move[n.id] = pays + 1;
        }
    }
    
    
    private void determineProfits() {
        for (Layer l : graph) {
            for (LNode n : l) {
                int indexAfterMove = n.getLayer().getIndex() - move[n.id];
                
                double edgeLength = wLen
                        // saved dummies if moved
                        //* (adjencency[n.id].leftPredecessors.size() * move[n.id]
                        * (adjencency[n.id].getLeftAdjencencySizeBefore(indexAfterMove) * move[n.id]
                        // newly introduced dummies
                        - adjencency[n.id].getRightAdjacencySize() * move[n.id]);
                
                // number of edges that will turn from pointing right->left to left->right
                double reversed = wRev * adjencency[n.id].getLeftSuccessorsAfter(indexAfterMove);

                profit[n.id] = edgeLength + reversed;
            }
        }
        
    }
    
    private void performMoves() {
        
        System.out.print("Move: ");
        for (int i = 0; i < move.length; i++) {
            if (move[i] != 0) 
            System.out.print("(" + nodes[i] + ", " + move[i] + ") ");
        }
        System.out.println();
        System.out.print("Profit: ");
        for (int i = 0; i < profit.length; i++) {       
            if (profit[i] != 0.0)
            System.out.print("(" + nodes[i] + ", " + profit[i] + ") ");
        }
        System.out.println();
        
        // stupidly finding best profit
        for (int i = 0; i < move.length; i++) {
            
            if (move[i] > 0 && profit[i] > 0) {
                
                int newLayerIndex = nodes[i].getLayer().getIndex() - move[i];
                // TODO should be possible even for -1
                if (newLayerIndex > 0) {
                    Layer newLayer = graph.getLayers().get(newLayerIndex);
                    nodes[i].setLayer(newLayer);
                }
            }
        }
    }
    
    private boolean isSelfloop(final LEdge e) {
        return e.getTarget().getNode() == e.getSource().getNode();
    }

    private boolean isLeafNode(final LNode n) {
        // is it a leaf?
        int degree = 0;
        for (LPort p : n.getPorts()) {
            for (LEdge e : p.getConnectedEdges()) {
                if (++degree > 1) {
                    break;
                }
            }
            if (degree > 1) {
                break;
            }
        }
     
        return degree == 1;
    }

    private int getMaxLayer(final Iterable<LNode> nodeSet) {
        int max = 0;
        for (LNode n : nodeSet) {
            max = Math.max(max, n.getLayer().getIndex());
        }
        return max;
    }

    private int getMinLayer(final Iterable<LNode> nodeSet) {
        int min = Integer.MAX_VALUE;
        for (LNode n : nodeSet) {
            min = Math.min(min, n.getLayer().getIndex());
        }
        return min;
    }
    
    private Iterable<LNode> getAdjacentNodes(final LNode n) {
        return Iterables.concat(
                Iterables.transform(n.getOutgoingEdges(), new Function<LEdge, LNode>() {
                    public LNode apply(final LEdge input) {
                        return input.getTarget().getNode();
                    }
                }), Iterables.transform(n.getIncomingEdges(), new Function<LEdge, LNode>() {
                    public LNode apply(final LEdge input) {
                        return input.getSource().getNode();
                    }
                }));
    }

    private Iterable<LNode> getPredecessorNodes(final LNode n) {
        return Iterables.transform(n.getIncomingEdges(), new Function<LEdge, LNode>() {
            public LNode apply(final LEdge input) {
                return input.getSource().getNode();
            }
        });
    }

    private Iterable<LNode> getSuccessorNodes(final LNode n) {
        return Iterables.transform(n.getOutgoingEdges(), new Function<LEdge, LNode>() {
            public LNode apply(final LEdge input) {
                return input.getTarget().getNode();
            }
        });
    }
    
    /**
     * Class holds adjacency information of nodes. That is
     * for a node n all adjacent nodes are recorded based
     * on whether they are predecessors or successors and 
     * if they are placed relatively to the left or right 
     * in the current layering. 
     */
    private static final class Adjancency {

        // SUPPRESS CHECKSTYLE NEXT 4 VisibilityModifier
        public List<LNode> leftPredecessors = Lists.newArrayList();
        public List<LNode> leftSuccessors = Lists.newArrayList();
        public List<LNode> rightPredecessors = Lists.newArrayList();
        public List<LNode> rightSuccessors = Lists.newArrayList();
        
        public int getRightAdjacencySize() {
            return rightPredecessors.size() + rightSuccessors.size();
        }
        
        public int getLeftAdjencencySizeBefore(final int index) {
            return leftPredecessors.size() + getLeftSuccessorsBefore(index);
        }
        
        public int getLeftSuccessorsBefore(final int index) {
            int count = 0;
            for (LNode n : leftSuccessors) {
                if (n.getLayer().getIndex() < index) {
                    count++;
                }
            }
            return count;
        }
        
        public int getLeftSuccessorsAfter(final int index) {
            int count = 0;
            for (LNode n : leftSuccessors) {
                if (n.getLayer().getIndex() > index) {
                    count++;
                }
            }
            return count;
        }
    }
    
    /**
     * Represents a leaf node of the graph that 
     * is removed from the graph during post-processing
     * and has to be re-attached later on.   
     */
    private final class LeafNode {
        
        /** original node. */
        private LNode node;
        
        // internal bookkeeping for re-attachment
        private LNode adjacentNode;
        private LEdge edge;
        private boolean isOutgoing; 
        private LPort edgeSrc;
        private LPort edgeTgt;
        
        public LeafNode(final LNode node) {
            this.node = node;
        }
        
        /**
         * Removes this leaf's node from its layer and 
         * sets source and target of its edge.
         *  
         * @return
         *      the adjacent node of this leaf node.
         */
        public LNode detach() {
            
            // remove the node from the layer
            node.setLayer(null);
            
            // we can expect the node to have only 
            // a single edge, otherwise it wouldn't be a leaf
            for (LEdge connected : node.getConnectedEdges()) {
                edge = connected;
            }
            
            isOutgoing = edge.getSource().getNode() == node;
            adjacentNode = isOutgoing ? edge.getTarget().getNode() : edge.getSource().getNode();
           
            // remove the edge from source and target node
            edgeSrc = edge.getSource();
            edgeTgt = edge.getTarget();
            edge.setSource(null);
            edge.setTarget(null);
            
            return adjacentNode;
        }
        
        /**
         * Re-attaches this leaf's node. If possible, the node 
         * is placed in an adjacent layer of the adjacent node
         * coherent with the layout direction. Exceptions are
         * when the adjacent node is in the first or last layer.
         * Then the leaf is possibly placed against the flow.
         */
        public void reattach() {
            // we add the node to an adjacent layer 
            // of adjacentNode such that the edge
            // points from left-to-right
            int newLayerIndex = adjacentNode.getLayer().getIndex() + (isOutgoing ? -1 : 1);
            
            if (newLayerIndex < 0) {
               newLayerIndex = 1; 
            } else if (newLayerIndex >= graphLayers.length) {
                newLayerIndex = graphLayers.length - 2;
            }
            
            node.setLayer(graphLayers[newLayerIndex]);
            
            // reattach the edge
            edge.setSource(edgeSrc);
            edge.setTarget(edgeTgt);
            
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "LeafNode { " + node + " }";
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final LGraph lgraph) {
        return INTERMEDIATE_PROCESSING_CONFIGURATION;
    }
}
