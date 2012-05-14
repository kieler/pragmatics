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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * 
 * @author jjc
 */
public class BKNodePlacer extends AbstractAlgorithm implements ILayoutPhase {

    /** List of edges involved in type 1 conflicts (see above). */
    private List<LEdge> markedEdges;

    /** Basic spacing between nodes, determined by layout options. */
    private float normalSpacing;

    /** Spacing between dummy nodes, determined by layout options. */
    private float smallSpacing;

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("BK node placement", 1);
        markedEdges = new LinkedList<LEdge>();

        // Determine overall node count for an optimal initialization of maps.
        int nodeCount = 0;
        for (Layer layer : layeredGraph) {
            nodeCount += layer.getNodes().size();
        }

        // Initialize four layouts which result from the two possible directions respectively.
        BKAlignedLayout lefttop = new BKAlignedLayout(nodeCount, VDirection.LEFT, HDirection.TOP);
        BKAlignedLayout righttop = new BKAlignedLayout(nodeCount, VDirection.RIGHT, HDirection.TOP);
        BKAlignedLayout leftbottom = new BKAlignedLayout(nodeCount, VDirection.LEFT,
                HDirection.BOTTOM);
        BKAlignedLayout rightbottom = new BKAlignedLayout(nodeCount, VDirection.RIGHT,
                HDirection.BOTTOM);

        // Initialize spacing value from layout options.
        normalSpacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        smallSpacing = normalSpacing * layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);

        // Phase which marks type 1 conflicts, no difference between the directions so only
        // one run is required.
        markConflicts(layeredGraph);

        // Phase which determines the nodes' memberships in blocks. This happens in four different
        // ways, either from processing the nodes from the first layer to the last or vice versa.
        // TODO more doc!!!
        verticalAlignment(layeredGraph, lefttop);
        verticalAlignment(layeredGraph, righttop);
        verticalAlignment(layeredGraph, leftbottom);
        verticalAlignment(layeredGraph, rightbottom);

        // Additional phase which is not included in the original Brandes-Koepf Algorithm.
        // It makes sure that the connected ports within a block are aligned to avoid unnecessary
        // bend points.
        insideBlockShift(layeredGraph, lefttop);
        insideBlockShift(layeredGraph, righttop);
        insideBlockShift(layeredGraph, leftbottom);
        insideBlockShift(layeredGraph, rightbottom);

        horizontalCompaction(layeredGraph, lefttop);
        horizontalCompaction(layeredGraph, righttop);
        horizontalCompaction(layeredGraph, leftbottom);
        horizontalCompaction(layeredGraph, rightbottom);

        BKAlignedLayout chosenLayout = lefttop;
        System.out.println("lefttop size is " + lefttop.layoutSize());
        System.out.println("righttop size is " + righttop.layoutSize());
        System.out.println("leftbottom size is " + leftbottom.layoutSize());
        System.out.println("rightbottom size is " + rightbottom.layoutSize());

        LinkedList<BKAlignedLayout> layouts = new LinkedList<BKAlignedLayout>();
        layouts.add(righttop);
        layouts.add(leftbottom);
        layouts.add(rightbottom);
        for (BKAlignedLayout bal : layouts) {
            if (bal.layoutSize() < chosenLayout.layoutSize()) {
                chosenLayout = bal;
            }
        }
        
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
//                 System.out.println("Set position of " + node.toString() + " to "
//                 + chosenLayout.getY().get(node));
                node.getPosition().y = chosenLayout.getY().get(node);

                if (!node.getProperty(LayoutOptions.HYPERNODE)) {
                    layer.getSize().x = Math.max(layer.getSize().x,
                            node.getSize().x + node.getMargin().left + node.getMargin().right);
                }
            }
        }

        // Set the proper offset and height for the whole graph.
        double minY = 0, maxY = 0;
        for (Layer layer : layeredGraph) {
            KVector layerSize = layer.getSize();
            LNode firstNode = layer.getNodes().get(0);
            double top = firstNode.getPosition().y - firstNode.getMargin().top;
            LNode lastNode = layer.getNodes().get(layer.getNodes().size() - 1);
            double bottom = lastNode.getPosition().y + lastNode.getSize().y
                    + lastNode.getMargin().bottom;
            layerSize.y = bottom - top;
            minY = Math.min(minY, top);
            maxY = Math.max(maxY, bottom);
        }
        layeredGraph.getSize().y = maxY - minY;
        layeredGraph.getOffset().y -= minY;

        System.out.println(getBlocks(chosenLayout));

        // Phase for postprocessing steps:
        // - move dummy nodes to the height of respective ports
        // postProcess(chosenLayout);

        getMonitor().done();
    }

    private void markConflicts(final LayeredGraph layeredGraph) {
        for (int i = 1; i < layeredGraph.getLayers().size() - 2; i++) {
            int k0 = -1;
            int l = 0;
            for (int l1 = 0; l1 < layerSize(layeredGraph, i + 1); l1++) {
                LNode vli = nodeByPosition(layeredGraph, i + 1, l1);
                if (l1 == (layerSize(layeredGraph, i + 1) - 1)
                        || incidentToInnerSegment(vli, -1)) {
                    int k1 = layerSize(layeredGraph, i);
                    if (incidentToInnerSegment(vli, -1)) {
                        k1 =
                           allUpperNeighbors(vli).get(0).getIndex();
                    }
                    while (l <= l1) {
                        LNode vl = nodeByPosition(layeredGraph, i + 1, l);
                        for (LNode un : allUpperNeighbors(vl)) {
                            int k = un.getIndex();
                            if (k < k0 || k > k1) {
                                markedEdges.add(getEdge(un, vl));
                            }
                        }
                        l++;
                    }
                    k0 = k1;
                }
            }
        }
        
        
        
//        for (int i = 1; i < (layeredGraph.getLayers().size() - 1); i++) {
//            int k0 = 0;
//            int l = 1;
//            for (int l1 = 1; l1 < layerSize(layeredGraph, i + 1); l1++) {
//                if (l1 == layerSize(layeredGraph, i + 1)
//                        || incidentToInnerSegment(nodeByPosition(layeredGraph, i + 1, l1), -1)) {
//                    int k1 = layerSize(layeredGraph, i);
//                    if (incidentToInnerSegment(nodeByPosition(layeredGraph, i + 1, l1), -1)) {
//                        k1 = allUpperNeighbors(nodeByPosition(layeredGraph, i + 1, l1)).get(0)
//                                .getIndex();
//                    }
//                    while (l <= l1) {
//                        for (LNode node : allUpperNeighbors(nodeByPosition(layeredGraph, i + 1, l))) {
//                            int k = node.getIndex();
//                            if (k < k0 || k > k1) {
//                                markedEdges.add(getEdge(node,
//                                        nodeByPosition(layeredGraph, i + 1, l)));
//                            }
//                        }
//                        l++;
//                    }
//                    k0 = k1;
//                }
//            }
//        }
    }

    private void verticalAlignment(final LayeredGraph layeredGraph, final BKAlignedLayout bal) {
        // Initialize root and align maps
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode v : layer.getNodes()) {
                bal.getRoot().put(v, v);
                bal.getAlign().put(v, v);
                bal.getInnerShift().put(v, 0.0);
            }
        }

        List<Layer> layers = layeredGraph.getLayers();
        // If the horizontal direction is bottom, the layers are traversed from
        // right to left, thus a reverse iterator is needed
        if (bal.getHDir() == HDirection.BOTTOM) {
            layers = Arrays.asList(new Layer[layeredGraph.getLayers().size()]);
            // Create a copy of the layer list to prevent modifying the original list.
            Collections.copy(layers, layeredGraph.getLayers());
            Collections.reverse(layers);
        }

        for (Layer layer : layers) {
            // r denotes the position in layer order where the last block was found
            // It is initialized with -1, since nothing is found and the ordering starts with 0
            int r = -1;
            List<LNode> nodes = layer.getNodes();
            if (bal.getVDir() == VDirection.RIGHT) {
                // If the alignment direction is RIGHT, the nodes in a layer are traversed
                // reversely, thus we start at INT_MAX and with the reversed list of nodes.
                r = Integer.MAX_VALUE;
                nodes = Arrays.asList(new LNode[layer.getNodes().size()]);
                // Create a copy of the node list to prevent modifying the original list.
                Collections.copy(nodes, layer.getNodes());
                Collections.reverse(nodes);
            }
            for (LNode vik : nodes) {
                List<LNode> neighbors = null;
                if (bal.getHDir() == HDirection.BOTTOM) {
                    neighbors = allLowerNeighbors(vik);
                } else {
                    neighbors = allUpperNeighbors(vik);
                }

                if (neighbors.size() > 0) {

                    // When a node has many upper neighbors, consider only the (two) nodes in the
                    // middle.
                    int d = neighbors.size();
                    int low = ((int) Math.floor(((d + 1.0) / 2.0))) - 1;
                    int high = ((int) Math.ceil(((d + 1.0) / 2.0))) - 1;
                    // System.out.println("d " + d + " High " + high + " Low " + low);

                    if (bal.getVDir() == VDirection.RIGHT) {
                        // Check, whether vik can be added to a block of its upper/lower neighbor(s)
                        for (int m = high; m >= low; m--) {
                            // System.out.println("m " + m);
                            if (bal.getAlign().get(vik).equals(vik)) {
                                LNode um = neighbors.get(m);
                                if (!markedEdges.contains(getEdge(um, vik)) && r > um.getIndex()) {
                                    bal.getAlign().put(um, vik);
                                    bal.getRoot().put(vik, bal.getRoot().get(um));
                                    bal.getAlign().put(vik, bal.getRoot().get(vik));
                                    r = um.getIndex();
                                }
                            }
                        }
                    } else {
                        // Check, whether vik can be added to a block of its upper/lower neighbor(s)
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
    }

    private void insideBlockShift(final LayeredGraph layeredGraph, final BKAlignedLayout bal) {
        HashMap<LNode, List<LNode>> blocks = getBlocks(bal);

        for (LNode root : blocks.keySet()) {
//            double maximumNodeSize = Math.max(root.getSize().y, smallSpacing);
            double maximumNodeSize = root.getSize().y;

            LNode current = root;
            LNode next = bal.getAlign().get(root);

            while (next != root) {
                LEdge edge = getEdge(current, next);
                double difference = 0.0;
                if (bal.getHDir() == HDirection.BOTTOM) {
                    difference = edge.getTarget().getPosition().y
                            - edge.getSource().getPosition().y + bal.getInnerShift().get(current);
                } else {
                    difference = edge.getSource().getPosition().y
                            - edge.getTarget().getPosition().y + bal.getInnerShift().get(current);
                }
//                System.out.println(current + " " + next + " " + difference);
                bal.getInnerShift().put(next, difference);

                double width = next.getSize().y + Math.abs(difference);
                if (width > maximumNodeSize) {
                    maximumNodeSize = width;
                }

                current = next;
                next = bal.getAlign().get(next);
            }

            bal.getBlockSize().put(root, maximumNodeSize);
            System.out.println("block size " + root + " " + maximumNodeSize);
        }
    }

    private void horizontalCompaction(final LayeredGraph layeredGraph, final BKAlignedLayout bal) {
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                bal.getSink().put(node, node);
                bal.getShift().put(node, Double.POSITIVE_INFINITY);
            }
        }

        for (Layer layer : layeredGraph.getLayers()) {

            List<LNode> nodes = layer.getNodes();
            for (LNode v : nodes) {
                if (bal.getRoot().get(v).equals(v)) {
                    placeBlock(v, bal);
                }
            }
        }

        for (Layer layer : layeredGraph.getLayers()) {

            // Switching layer or node order here seems to have no effect
            List<LNode> nodes = layer.getNodes();
            if (bal.getVDir() == VDirection.RIGHT) {
                nodes = Arrays.asList(new LNode[layer.getNodes().size()]);
                Collections.copy(nodes, layer.getNodes());
                Collections.reverse(nodes);
            }

            for (LNode v : layer.getNodes()) {
                bal.getY()
                        .put(v, bal.getY().get(bal.getRoot().get(v)) + bal.getInnerShift().get(v));
                if (bal.getShift().get(bal.getSink().get(bal.getRoot().get(v))) < Double.POSITIVE_INFINITY) {
                    bal.getY().put(
                            v,
                            bal.getY().get(v)
                                    + bal.getShift().get(bal.getSink().get(bal.getRoot().get(v)))
                                    + bal.getBlockSize().get(bal.getSink().get(bal.getRoot().get(v))));
                }
            }
        }

    }

    private void placeBlock(final LNode v, final BKAlignedLayout bal) {
        if (!bal.getY().containsKey(v)) {
            bal.getY().put(v, 0.0);
            LNode w = v;
            do {
                if ((bal.getVDir() == VDirection.LEFT && w.getIndex() > 0)
                        || (bal.getVDir() == VDirection.RIGHT && w.getIndex() < (w.getLayer()
                                .getNodes().size() - 1))) {

                    LNode u = null;
                    if (bal.getVDir() == VDirection.RIGHT) {
                        u = bal.getRoot().get(w.getLayer().getNodes().get(w.getIndex() + 1));
                    } else {
                        u = bal.getRoot().get(w.getLayer().getNodes().get(w.getIndex() - 1));
                    }

                    placeBlock(u, bal);
                    if (bal.getSink().get(v).equals(v)) {
                        bal.getSink().put(v, bal.getSink().get(u));
                    }
                    if (!bal.getSink().get(v).equals(bal.getSink().get(u))) {
                        double spacing = normalSpacing;
                        if (bal.getBlockSize().get(v) == 0.0 || bal.getBlockSize().get(u) == 0.0) {
                            spacing = smallSpacing;
                        }
                        bal.getShift().put(
                                bal.getSink().get(u),
                                Math.min(bal.getShift().get(bal.getSink().get(u)), bal.getY()
                                        .get(u) - bal.getY().get(v) - spacing
                                        - bal.getBlockSize().get(u)));
                    } else {
                        double spacing = normalSpacing;
                        if (bal.getBlockSize().get(v) == 0.0 || bal.getBlockSize().get(u) == 0.0) {
                            spacing = smallSpacing;
                        }
                        if (bal.getVDir() == VDirection.RIGHT) {
                            bal.getY().put(
                                    v,
                                    Math.min(bal.getY().get(v), bal.getY().get(u) - spacing
                                            - bal.getBlockSize().get(v)));
                        } else {
                            bal.getY().put(
                                    v,
                                    Math.max(bal.getY().get(v), bal.getY().get(u) + spacing
                                            + bal.getBlockSize().get(u)));
                        }
                    }
                }
                w = bal.getAlign().get(w);
            } while (w != v);
        }
    }

    private void postProcess(final BKAlignedLayout bal) {
        HashMap<LNode, List<LNode>> blocks = getBlocks(bal);

        for (List<LNode> block : blocks.values()) {
            int layer = Integer.MAX_VALUE;
            double portOffset = 0.0;

            // TODO only use nodes which are directly connected to the long edge!!

            // Check block for none dummy nodes and determine port height
            for (LNode node : block) {
                if (node.getProperty(Properties.NODE_TYPE) != NodeType.LONG_EDGE) {
                    if (node.getLayer().getIndex() < layer) {
                        // TODO consider treating left and right nodes differently
                        // to align them correctly if left side and right side ports
                        // have different positions
                        for (LEdge edge : node.getIncomingEdges()) {
                            if (block.contains(edge.getSource().getNode())) {
                                portOffset = edge.getTarget().getPosition().y;
                                layer = node.getLayer().getIndex();
                            }
                        }
                        // To favor alignment to source nodes, their port offset is checked and
                        // applied second
                        for (LEdge edge : node.getOutgoingEdges()) {
                            if (block.contains(edge.getTarget().getNode())) {
                                portOffset = edge.getSource().getPosition().y;
                                layer = node.getLayer().getIndex();
                            }
                        }
                    }
                }
            }

            // Move dummy nodes to previously determined port height
            for (LNode node : block) {
                if (node.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
                    node.getPosition().y += portOffset;
                    System.out.println("Offsetting " + node.toString() + " by " + portOffset);
                }
            }
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
        int highestPriority = Integer.MIN_VALUE;
        for (LEdge edge : node.getIncomingEdges()) {
            int prio = edge.getProperty(Properties.PRIORITY);
            if (prio > highestPriority) {
                highestPriority = prio;
            }
        }
        System.out.println(highestPriority);
        for (LEdge edge : node.getIncomingEdges()) {
            if (edge.getProperty(Properties.PRIORITY) == highestPriority
                    && node.getLayer() != edge.getSource().getNode().getLayer()) {
                result.add(edge.getSource().getNode());
                System.out.println(edge.getTarget().getNode());
            }
            System.out.println(node + " " + edge.getTarget().getNode() + " " + (node.getLayer() != edge.getTarget().getNode().getLayer()));
        }
        Collections.sort(result, new NeighborComparator());
        return result;
    }

    private List<LNode> allLowerNeighbors(final LNode node) {
        List<LNode> result = new LinkedList<LNode>();
        int highestPriority = Integer.MIN_VALUE;
        for (LEdge edge : node.getOutgoingEdges()) {
            int prio = edge.getProperty(Properties.PRIORITY);
            if (prio > highestPriority) {
                highestPriority = prio;
            }
        }
        System.out.println(highestPriority);
        for (LEdge edge : node.getOutgoingEdges()) {
            if (edge.getProperty(Properties.PRIORITY) == highestPriority
                    && node.getLayer() != edge.getTarget().getNode().getLayer()) {
                result.add(edge.getTarget().getNode());
            }
            System.out.println(node + " " + edge.getTarget().getNode() + " " + (node.getLayer() != edge.getTarget().getNode().getLayer()));
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

        private HashMap<LNode, Double> blockSize;

        private HashMap<LNode, LNode> align;

        private HashMap<LNode, Double> innerShift;

        private HashMap<LNode, LNode> sink;

        private HashMap<LNode, Double> shift;

        private HashMap<LNode, Double> y;

        private VDirection vdir;

        private HDirection hdir;

        /**
         * 
         */
        public BKAlignedLayout(final int nodeCount, final VDirection vdir, final HDirection hdir) {
            root = Maps.newHashMapWithExpectedSize(nodeCount);
            blockSize = Maps.newHashMapWithExpectedSize(nodeCount);
            align = Maps.newHashMapWithExpectedSize(nodeCount);
            innerShift = Maps.newHashMapWithExpectedSize(nodeCount);
            sink = Maps.newHashMapWithExpectedSize(nodeCount);
            shift = Maps.newHashMapWithExpectedSize(nodeCount);
            y = Maps.newHashMapWithExpectedSize(nodeCount);
            this.vdir = vdir;
            this.hdir = hdir;
        }

        /**
         * @return the root
         */
        public HashMap<LNode, LNode> getRoot() {
            return root;
        }

        /**
         * @return the block size
         */
        public HashMap<LNode, Double> getBlockSize() {
            return blockSize;
        }

        /**
         * @return the align
         */
        public HashMap<LNode, LNode> getAlign() {
            return align;
        }

        /**
         * @return the inner shift
         */
        public HashMap<LNode, Double> getInnerShift() {
            return innerShift;
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

        /**
         * @return the vdir
         */
        public VDirection getVDir() {
            return vdir;
        }

        /**
         * @return the hdir
         */
        public HDirection getHDir() {
            return hdir;
        }

        public double layoutSize() {
            double min = Double.POSITIVE_INFINITY;
            double max = Double.NEGATIVE_INFINITY;
            for (double i : y.values()) {
                if (i < min) {
                    min = i;
                }
                if (i > max) {
                    max = i;
                }
            }
            min = Math.abs(min);
            return max + min;
        }
        
        @Override
        public String toString() {
            String result = "";
            if (vdir == VDirection.LEFT) {
                result += "LEFT";
            } else {
                result += "RIGHT";
            }
            if (hdir == HDirection.TOP) {
                result += "TOP";
            } else {
                result += "BOTTOM";
            }
            return result;
        }

    }

    private enum VDirection {

        LEFT, RIGHT;

    }

    private enum HDirection {

        TOP, BOTTOM;

    }

}
