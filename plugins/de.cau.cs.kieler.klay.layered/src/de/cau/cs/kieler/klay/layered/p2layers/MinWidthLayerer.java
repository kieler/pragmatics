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

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets.SetView;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateProcessorStrategy;

/**
 * TODO: NEW COMMENTS, This is only a copy of the longest path layerer!
 * 
 * The most basic layering algorithm, which assign layers according to the longest path to a sink.
 * 
 * <dl>
 * <dt>Precondition:</dt>
 * <dd>the graph has no cycles</dd>
 * <dt>Postcondition:</dt>
 * <dd>all nodes have been assigned a layer such that edges connect only nodes from layers with
 * increasing indices</dd>
 * </dl>
 *
 * @author mic
 */
public final class MinWidthLayerer implements ILayoutPhase {
    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final LGraph graph) {
        return IntermediateProcessingConfiguration
                .createEmpty()
                // Look at doku
                .addBeforePhase1(
                        IntermediateProcessorStrategy.EDGE_AND_LAYER_CONSTRAINT_EDGE_REVERSER)
                .addBeforePhase3(IntermediateProcessorStrategy.LAYER_CONSTRAINT_PROCESSOR);
    }

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {
        List<Layer> layers = layeredGraph.getLayers();
        List<LNode> notInserted = layeredGraph.getLayerlessNodes();
        // Requieres: DAG G = (V, E) ~ Type LGraph, integers UBW and c ~ hard coded for now, worry
        // worry about config later. In this version selfloops are allowed.
        final int ubw = 4;
        final int c = 1;

        // Guarantee ConditionSelect by ordering the nodes by descending maximum outdegree in
        // advance.
        // We're using the nodes id property to store the outdegree (so it's not an id anymore in
        // the
        // strict sense, as it might not be unique).
        for (LNode node : notInserted) {
            node.id = countElems(node.getOutgoingEdges());
        }
        notInserted.sort(new MaxOutgoingEdgesComparator());
        // LinkesHassets respect ordering
        Set<LNode> v = new LinkedHashSet<LNode>(notInserted);

        // first naive implementation of the minWidth-algorithm
        Set<LNode> u = new HashSet<LNode>();
        Set<LNode> z = new HashSet<LNode>();

        Layer currentLayer = new Layer(layeredGraph);
        layers.add(currentLayer);
        int widthCurrent = 0;
        int widthUp = 0;
        boolean noNodeSelected = false;
        int inDegree = 0;
        // better defaultvalue?
        int outDegree = 0;

        while (!u.equals(v)) {
            // Use some googly magic: TODO: Explain.
            SetView<LNode> vWithoutU = com.google.common.collect.Sets.difference(v, u);

            try {
                LNode currentNode = this.selectNode(vWithoutU, z);
                currentNode.setLayer(currentLayer);
                u.add(currentNode);
                // id==outDegree
                outDegree = currentNode.id;
                widthCurrent += 1 - outDegree;
                inDegree = countElems(currentNode.getIncomingEdges());
                widthUp += inDegree;
                // System.out.println("chosen: " + currentNode.id);
            } catch (NoElementLeftException e) { // In case no node can be found
                noNodeSelected = true;
                // System.out.println("no node chosen");
            }
            if (noNodeSelected || (widthCurrent >= ubw && outDegree < 1) || widthUp >= c * ubw) {
                // System.out.println("new layer");
                noNodeSelected = false;
                currentLayer = new Layer(layeredGraph);
                /* Don't forget to add the layer to your graph! */
                layers.add(currentLayer);
                z.addAll(u);
                widthCurrent = widthUp;
                widthUp = 0;
            }
            
//         // EVIL: Should have used breakpoints instead
//            System.out.println("v: " + v);
//            System.out.println("u: " + u);
//            System.out.println("vOhneU: " + vWithoutU);
//            String od = "vOhneU-grad: ";
//            for (LNode node : vWithoutU) {
//                od += node.id + " ";
//            }
//            System.out.println(od);
//            System.out.println("z: " + z);
//            System.out.println("---------------------------- ");

            // layeredGraph.getProperty(Properties.) … I Need this for configuring ubw and c
        }

        notInserted.clear();
E        java.util.Collections.reverse(layers);
//        System.out.println(layeredGraph.getLayers());
    }

    /**
     * @param nodes
     * @param successors
     * @return
     * @throws NoElementLeftException
     */
    private LNode selectNode(final SetView<LNode> nodes, final Set<LNode> successors)
            throws NoElementLeftException {
        Set<LNode> outNodes = new HashSet<LNode>();

        for (LNode node : nodes) {
            // System.out.println(node.id);
            outNodes.clear();
            Iterable<LEdge> outEdges = node.getOutgoingEdges();

            for (LEdge edge : outEdges) {
                if (!edge.getSource().getNode().equals(edge.getTarget().getNode())) {
                    outNodes.add(edge.getTarget().getNode());
                }
            }

            if (successors.containsAll(outNodes)) {
                return node;
            }
        }
        throw new NoElementLeftException();
    }

    /**
     * TODO: CHANGE DOC, Type not generic anymore. Returns the size of given Iterable. (Iterable
     * doesn't have a method size().)
     * 
     * @param iter
     *            Iterable whose elements are to be counted
     * @return size of given Iterable
     */
    private static int countElems(final Iterable<LEdge> edges) {
        int i = 0;
        for (LEdge edge : edges) {
            if (!edge.getSource().getNode().equals(edge.getTarget().getNode())) {
                i++;
            }
        }
        return i;
    }

    /**
     * Comparator for determining whether a LNode has more outgoing edges than another one.
     *
     * Requires the LNode property "id" to be set to the number of outgoing edges of the node.
     * 
     * @author mic
     */
    private class MaxOutgoingEdgesComparator implements Comparator<LNode> {
        /**
         * {@inheritDoc}
         */
        public int compare(final LNode o1, final LNode o2) {
            int outs1 = o1.id;
            int outs2 = o2.id;

            if (outs1 < outs2) {
                return -1;
            }
            if (outs1 == outs2) {
                return 0;
            }
            return 1;
        }
    }

    /**
     * Exception for indicating that a Collection has unexpectedly ended before an element has been
     * chosen to return.
     * 
     * @author mic
     */
    private class NoElementLeftException extends Exception {
        private static final long serialVersionUID = -7510397180858968135L;
    }

    // /** intermediate processing configuration. */
    // private static final IntermediateProcessingConfiguration BASELINE_PROCESSING_CONFIGURATION =
    // IntermediateProcessingConfiguration.createEmpty()
    // .addBeforePhase1(IntermediateProcessorStrategy.EDGE_AND_LAYER_CONSTRAINT_EDGE_REVERSER)
    // .addBeforePhase3(IntermediateProcessorStrategy.LAYER_CONSTRAINT_PROCESSOR);
    //
    // /** additional processor dependencies for handling big nodes. */
    // private static final IntermediateProcessingConfiguration
    // BIG_NODES_PROCESSING_ADDITIONS_AGGRESSIVE =
    // IntermediateProcessingConfiguration.createEmpty()
    // .addBeforePhase2(IntermediateProcessorStrategy.BIG_NODES_PREPROCESSOR)
    // .addBeforePhase3(IntermediateProcessorStrategy.BIG_NODES_INTERMEDIATEPROCESSOR)
    // .addAfterPhase5(IntermediateProcessorStrategy.BIG_NODES_POSTPROCESSOR);
    //
    // /** additional processor dependencies for handling big nodes after cross min. */
    // private static final IntermediateProcessingConfiguration
    // BIG_NODES_PROCESSING_ADDITIONS_CAREFUL =
    // IntermediateProcessingConfiguration.createEmpty()
    // .addBeforePhase4(IntermediateProcessorStrategy.BIG_NODES_SPLITTER)
    // .addAfterPhase5(IntermediateProcessorStrategy.BIG_NODES_POSTPROCESSOR);
    //
    // /** the layered graph to which layers are added. */
    // private LGraph layeredGraph;
    // /** map of nodes to their height in the layering. */
    // private int[] nodeHeights;
    //
    // /**
    // * {@inheritDoc}
    // */
    // @SuppressWarnings("deprecation")
    // public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
    // final LGraph graph) {
    //
    // // Basic strategy
    // IntermediateProcessingConfiguration strategy =
    // IntermediateProcessingConfiguration.fromExisting(BASELINE_PROCESSING_CONFIGURATION);
    //
    // // Additional dependencies
    // if (graph.getProperty(Properties.DISTRIBUTE_NODES)
    // || graph.getProperty(Properties.WIDE_NODES_ON_MULTIPLE_LAYERS)
    // == WideNodesStrategy.AGGRESSIVE) {
    // strategy.addAll(BIG_NODES_PROCESSING_ADDITIONS_AGGRESSIVE);
    //
    // } else if (graph.getProperty(Properties.WIDE_NODES_ON_MULTIPLE_LAYERS)
    // == WideNodesStrategy.CAREFUL) {
    // strategy.addAll(BIG_NODES_PROCESSING_ADDITIONS_CAREFUL);
    // }
    //
    // if (graph.getProperty(Properties.SAUSAGE_FOLDING)) {
    // strategy.addBeforePhase4(IntermediateProcessorStrategy.SAUSAGE_COMPACTION);
    // }
    //
    // return strategy;
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // public void process(final LGraph thelayeredGraph, final IKielerProgressMonitor monitor) {
    // monitor.begin("Longest path layering", 1);
    //
    // layeredGraph = thelayeredGraph;
    // List<LNode> nodes = layeredGraph.getLayerlessNodes();
    //
    // // initialize values required for the computation
    // nodeHeights = new int[nodes.size()];
    // int index = 0;
    // for (LNode node : nodes) {
    // // the node id is used as index for the nodeHeights array
    // node.id = index;
    // nodeHeights[index] = -1;
    // index++;
    // }
    //
    // // process all nodes
    // for (LNode node : nodes) {
    // visit(node);
    // }
    //
    // // empty the list of unlayered nodes
    // nodes.clear();
    //
    // // release the created resources
    // this.layeredGraph = null;
    // this.nodeHeights = null;
    //
    // monitor.done();
    // }
    //
    //
    // /**
    // * Visit a node: if not already visited, find the longest path to a sink.
    // *
    // * @param node node to visit
    // * @return height of the given node in the layered graph
    // */
    // private int visit(final LNode node) {
    // int height = nodeHeights[node.id];
    // if (height >= 0) {
    // // the node was already visited (the case height == 0 should never occur)
    // return height;
    // } else {
    // int maxHeight = 1;
    // for (LPort port : node.getPorts()) {
    // for (LEdge edge : port.getOutgoingEdges()) {
    // LNode targetNode = edge.getTarget().getNode();
    //
    // // ignore self-loops
    // if (node != targetNode) {
    // int targetHeight = visit(targetNode);
    // maxHeight = Math.max(maxHeight, targetHeight + 1);
    // }
    // }
    // }
    // putNode(node, maxHeight);
    // return maxHeight;
    // }
    // }
    //
    // /**
    // * Puts the given node into the layered graph, adding new layers as necessary.
    // *
    // * @param node a node
    // * @param height height of the layer where the node shall be added
    // * (height = number of layers - layer index)
    // */
    // private void putNode(final LNode node, final int height) {
    // List<Layer> layers = layeredGraph.getLayers();
    //
    // // add layers so as to guarantee that number of layers >= height
    // for (int i = layers.size(); i < height; i++) {
    // layers.add(0, new Layer(layeredGraph));
    // }
    //
    // // layer index = number of layers - height
    // node.setLayer(layers.get(layers.size() - height));
    // nodeHeights[node.id] = height;
    // }

}
