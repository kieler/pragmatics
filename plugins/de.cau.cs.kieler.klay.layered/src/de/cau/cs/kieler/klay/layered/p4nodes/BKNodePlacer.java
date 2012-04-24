/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p4nodes;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * 
 * @author jjc
 */
public class BKNodePlacer extends AbstractAlgorithm implements ILayoutPhase {

    private List<LEdge> markedEdges;

    private float normalSpacing;

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("BK node placement", 1);
        markedEdges = new LinkedList<LEdge>();

        int nodeCount = 0;
        for (Layer layer : layeredGraph) {
            nodeCount += layer.getNodes().size();
        }

        BKAlignedLayout lefttop = new BKAlignedLayout(nodeCount);
        BKAlignedLayout righttop = new BKAlignedLayout(nodeCount);
        BKAlignedLayout leftbottom = new BKAlignedLayout(nodeCount);
        BKAlignedLayout rightbottom = new BKAlignedLayout(nodeCount);

        normalSpacing = layeredGraph.getProperty(Properties.OBJ_SPACING);

        // directions?!?
        markConflicts(layeredGraph);

        verticalAlignmentLeftTop(layeredGraph, lefttop);

        horizontalCompactionLeftTop(layeredGraph, lefttop);

        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                System.out.println("Set position of " + node.toString()
                        + " to " + lefttop.getY().get(node));
                node.getPosition().y = lefttop.getY().get(node);
            }
        }

        getMonitor().done();
    }

    private void markConflicts(final LayeredGraph layeredGraph) {
        for (int i = 1; i < (layeredGraph.getLayers().size() - 1); i++) {
            int k0 = 0;
            int l = 1;
            for (int l1 = 1; l1 < layerSize(layeredGraph, i + 1); l1++) {
                if (l1 == layerSize(layeredGraph, i + 1)
                        || incidentToInnerSegment(nodeByPosition(layeredGraph, i + 1, l1), -1)) {
                    int k1 = layerSize(layeredGraph, i);
                    if (incidentToInnerSegment(nodeByPosition(layeredGraph, i + 1, l1), -1)) {
                        k1 = firstUpperNeighbor(nodeByPosition(layeredGraph, i + 1, l1)).getIndex();
                    }
                    while (l <= l1) {
                        for (LNode node : allUpperNeighbors(nodeByPosition(layeredGraph, i + 1, l))) {
                            int k = node.getIndex();
                            if (k < k0 || k > k1) {
                                markedEdges.add(getEdge(node,
                                        nodeByPosition(layeredGraph, i + 1, l)));
                            }
                        }
                        l++;
                    }
                    k0 = k1;
                }
            }
        }
    }

    private void verticalAlignmentLeftTop(final LayeredGraph layeredGraph, final BKAlignedLayout bal) {
        // Initialize root and align maps
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode v : layer.getNodes()) {
                bal.getRoot().put(v, v);
                bal.getAlign().put(v, v);
            }
        }

        for (Layer layer : layeredGraph.getLayers()) {
            // r denotes the position in layer order where the last block was found
            // It is initialized with -1, since nothing is found and the ordering starts with 0
            int r = -1;
            for (LNode vik : layer.getNodes()) {
                List<LNode> neighbors = allUpperNeighbors(vik);
                if (neighbors.size() > 0) {

                    // When a node has many upper neighbors, consider only the (two) nodes in the
                    // middle
                    int d = neighbors.size();
                    int low = ((int) Math.floor(((d + 1.0) / 2.0))) - 1;
                    int high = ((int) Math.ceil(((d + 1.0) / 2.0))) - 1;

                    // Check, whether vik can be added to a block of its upper neighbor(s)
                    for (int m = low; m <= high; m++) {
                        if (bal.getAlign().get(vik).equals(vik)) {
                            LNode um = neighbors.get(m);
                            if (!markedEdges.contains(getEdge(um, vik)) && r < um.getIndex()) {
                                bal.getAlign().put(um, vik);
                                bal.getRoot().put(vik, bal.getRoot().get(um));
                                bal.getAlign().put(vik, bal.getRoot().get(vik));
                                r = um.getIndex();
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void verticalAlignmentRightTop(final LayeredGraph layeredGraph, final BKAlignedLayout bal) {
        // Initialize root and align maps
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode v : layer.getNodes()) {
                bal.getRoot().put(v, v);
                bal.getAlign().put(v, v);
            }
        }

        for (Layer layer : layeredGraph.getLayers()) {
            // r denotes the position in layer order where the last block was found
            // It is initialized with MAX, since nothing is found and the ordering starts with
            // the highest number. This is, because its from right to left this time
            
            //TODO continue here ... should be better to use numbers instead of iterators here!
            int r = Integer.MAX_VALUE;
            for (LNode vik : layer.getNodes()) {
                List<LNode> neighbors = allUpperNeighbors(vik);
                if (neighbors.size() > 0) {

                    // When a node has many upper neighbors, consider only the (two) nodes in the
                    // middle
                    int d = neighbors.size();
                    int low = ((int) Math.floor(((d + 1.0) / 2.0))) - 1;
                    int high = ((int) Math.ceil(((d + 1.0) / 2.0))) - 1;

                    // Check, whether vik can be added to a block of its upper neighbor(s)
                    for (int m = low; m <= high; m++) {
                        if (bal.getAlign().get(vik).equals(vik)) {
                            LNode um = neighbors.get(m);
                            if (!markedEdges.contains(getEdge(um, vik)) && r < um.getIndex()) {
                                bal.getAlign().put(um, vik);
                                bal.getRoot().put(vik, bal.getRoot().get(um));
                                bal.getAlign().put(vik, bal.getRoot().get(vik));
                                r = um.getIndex();
                            }
                        }
                    }
                }
            }
        }
    }

    private void horizontalCompactionLeftTop(final LayeredGraph layeredGraph,
            final BKAlignedLayout bal) {
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                bal.getSink().put(node, node);
                bal.getShift().put(node, Double.POSITIVE_INFINITY);
            }
        }

        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode v : layer.getNodes()) {
                if (bal.getRoot().get(v).equals(v)) {
                    placeBlock(v, bal);
                }
            }
        }

        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode v : layer.getNodes()) {
                bal.getY().put(v, bal.getY().get(bal.getRoot().get(v)));
                if (bal.getShift().get(bal.getSink().get(bal.getRoot().get(v)))
                        < Double.POSITIVE_INFINITY) {
                    bal.getY().put(
                            v,
                            bal.getY().get(v)
                                    + bal.getShift().get(bal.getSink().get(bal.getRoot().get(v))));
                }
            }
        }

    }

    private void placeBlock(final LNode v, final BKAlignedLayout bal) {
        if (!bal.getY().containsKey(v)) {
            bal.getY().put(v, 0.0);
            LNode w = v;
            do {
                if (w.getIndex() > 0) {
                    LNode u = bal.getRoot().get(w.getLayer().getNodes().get(w.getIndex() - 1));
                    placeBlock(u, bal);
                    if (bal.getSink().get(v).equals(v)) {
                        bal.getSink().put(v, bal.getSink().get(u));
                    }
                    if (!bal.getSink().get(v).equals(bal.getSink().get(u))) {
                        bal.getShift().put(
                                bal.getSink().get(u),
                                Math.min(bal.getShift().get(bal.getSink().get(u)), bal.getY()
                                        .get(u) - bal.getY().get(v) + normalSpacing));
                    } else {
                        bal.getY().put(v,
                                Math.max(bal.getY().get(v), bal.getY().get(u) + normalSpacing));
                    }
                }
                w = bal.getAlign().get(w);
            } while (w != v);
        }
    }

    private int layerSize(final LayeredGraph layeredGraph, final int layer) {
        return layeredGraph.getLayers().get(layer).getNodes().size();
    }

    private LNode nodeByPosition(final LayeredGraph layeredGraph, final int layer,
            final int position) {
        return layeredGraph.getLayers().get(layer).getNodes().get(position);
    }

    private boolean incidentToInnerSegment(final LNode node, final int layerOffset) {
        if (node.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
            for (LEdge edge : node.getIncomingEdges()) {
                if (edge.getSource().getNode().getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE
                        && edge.getSource().getNode().getLayer().getIndex() == node.getLayer()
                                .getIndex() + layerOffset) {
                    return true;
                }
            }
            for (LEdge edge : node.getOutgoingEdges()) {
                if (edge.getTarget().getNode().getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE
                        && edge.getTarget().getNode().getLayer().getIndex() == node.getLayer()
                                .getIndex() + layerOffset) {
                    return true;
                }
            }
        }
        return false;
    }

    private LNode firstUpperNeighbor(final LNode node) {
        return node.getIncomingEdges().iterator().next().getSource().getNode();
    }

    /**
     * Gives all upper neighbors of a given node.
     * 
     * An upper neighbor is a node in a previous layer which has an edge pointing to the given node.
     * 
     * @param node
     *            The node of which upper neighbors shall be found
     * @return A list containing all upper neighbors
     */
    private List<LNode> allUpperNeighbors(final LNode node) {
        List<LNode> result = new LinkedList<LNode>();
        for (LEdge edge : node.getIncomingEdges()) {
            result.add(edge.getSource().getNode());
        }
        Collections.sort(result, new NeighborComparator());
        return result;
    }

    private LEdge getEdge(final LNode source, final LNode target) {
        for (LEdge edge : source.getConnectedEdges()) {
            if (edge.getTarget().getNode().equals(target)
                    || edge.getSource().getNode().equals(target)) {
                return edge;
            }
        }
        return null;
    }

    private HashMap<LNode, List<LNode>> getBlocks(final BKAlignedLayout bal) {
        // TODO useless now!?!?!?!?
        HashMap<LNode, List<LNode>> blocks = new HashMap<LNode, List<LNode>>();
        for (LNode key : bal.getRoot().keySet()) {
            if (!blocks.containsKey(bal.getRoot().get(key))) {
                blocks.put(bal.getRoot().get(key), new LinkedList<LNode>());
            }
            blocks.get(bal.getRoot().get(key)).add(key);
        }
        return blocks;
    }

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingStrategy getIntermediateProcessingStrategy(final LayeredGraph graph) {
        // TODO Auto-generated method stub
        return null;
    }

    class NeighborComparator implements Comparator<LNode> {

        /**
         * {@inheritDoc}
         */
        public int compare(final LNode o1, final LNode o2) {
            int result = 0;
            if (o1.getIndex() < o2.getIndex()) {
                result = -1;
            } else if (o1.getIndex() > o2.getIndex()) {
                result = 1;
            }
            return result;
        }

    }

    private class BKAlignedLayout {

        private HashMap<LNode, LNode> root;

        private HashMap<LNode, LNode> align;

        private HashMap<LNode, LNode> sink;

        private HashMap<LNode, Double> shift;

        private HashMap<LNode, Double> y;

        /**
         * 
         */
        public BKAlignedLayout(int nodeCount) {
            root = Maps.newHashMapWithExpectedSize(nodeCount);
            align = Maps.newHashMapWithExpectedSize(nodeCount);
            sink = Maps.newHashMapWithExpectedSize(nodeCount);
            shift = Maps.newHashMapWithExpectedSize(nodeCount);
            y = Maps.newHashMapWithExpectedSize(nodeCount);
        }

        /**
         * @return the root
         */
        public HashMap<LNode, LNode> getRoot() {
            return root;
        }

        /**
         * @return the align
         */
        public HashMap<LNode, LNode> getAlign() {
            return align;
        }

        /**
         * @return the sink
         */
        public HashMap<LNode, LNode> getSink() {
            return sink;
        }

        /**
         * @return the shift
         */
        public HashMap<LNode, Double> getShift() {
            return shift;
        }

        /**
         * @return the y
         */
        public HashMap<LNode, Double> getY() {
            return y;
        }

    }

}
