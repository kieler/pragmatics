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

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateLayoutProcessor;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
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

    /** Flag which switches debug output of the algorithm on or off. */
    private boolean debug = false;

    private boolean addBalancedLayout = false;

    /** additional processor dependencies for graphs with hierarchical ports. */
    private static final IntermediateProcessingStrategy HIERARCHY_PROCESSING_ADDITIONS = new IntermediateProcessingStrategy(
            IntermediateProcessingStrategy.BEFORE_PHASE_5,
            IntermediateLayoutProcessor.HIERARCHICAL_PORT_POSITION_PROCESSOR);

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingStrategy getIntermediateProcessingStrategy(final LayeredGraph graph) {
        if (graph.getProperty(Properties.GRAPH_PROPERTIES).contains(GraphProperties.EXTERNAL_PORTS)) {
            return HIERARCHY_PROCESSING_ADDITIONS;
        } else {
            return null;
        }
    }

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

        // Regard possible other layout options.
        debug = layeredGraph.getProperty(Properties.DEBUG_MODE);
        addBalancedLayout = !layeredGraph.getProperty(Properties.EDGE_BENDS);

        // Phase which marks type 1 conflicts, no difference between the directions so only
        // one run is required.
        markConflicts(layeredGraph);

        // Phase which determines the nodes' memberships in blocks. This happens in four different
        // ways, either from processing the nodes from the first layer to the last or vice versa.
        verticalAlignment(layeredGraph, lefttop);
        verticalAlignment(layeredGraph, righttop);
        verticalAlignment(layeredGraph, leftbottom);
        verticalAlignment(layeredGraph, rightbottom);

        // Additional phase which is not included in the original Brandes-Koepf Algorithm.
        // It makes sure that the connected ports within a block are aligned to avoid unnecessary
        // bend points.
        // Also, the required size of each block is determined.
        insideBlockShift(layeredGraph, lefttop);
        insideBlockShift(layeredGraph, righttop);
        insideBlockShift(layeredGraph, leftbottom);
        insideBlockShift(layeredGraph, rightbottom);

        // This phase determines the y coordinates of the blocks and thus the vertical coordinates
        // of all nodes.
        horizontalCompaction(layeredGraph, lefttop);
        horizontalCompaction(layeredGraph, righttop);
        horizontalCompaction(layeredGraph, leftbottom);
        horizontalCompaction(layeredGraph, rightbottom);

        if (debug) {
            System.out.println("lefttop size is " + lefttop.layoutSize());
            System.out.println("righttop size is " + righttop.layoutSize());
            System.out.println("leftbottom size is " + leftbottom.layoutSize());
            System.out.println("rightbottom size is " + rightbottom.layoutSize());
        }

        // Choose a layout from the four calculated layouts. Layouts that contain errors a skipped.
        // The layout with the smallest size is selected.
        BKAlignedLayout chosenLayout = null;
        LinkedList<BKAlignedLayout> layouts = new LinkedList<BKAlignedLayout>();
        layouts.add(lefttop);
        layouts.add(righttop);
        layouts.add(leftbottom);
        layouts.add(rightbottom);

        BKAlignedLayout balanced = new BKAlignedLayout(nodeCount, null, null);

        // If layout options chose to use the balanced layout, it is calculated and added here.
        // If it is broken for any reason, one of the four other layouts is selected by the
        // given criteria.
        if (addBalancedLayout) {
            balanced = createBalancedLayout(layouts, nodeCount);
            chosenLayout = balanced;
        }
        if (!addBalancedLayout || !checkOrderConstraint(layeredGraph, balanced)) {
            chosenLayout = null;
            for (BKAlignedLayout bal : layouts) {
                if (checkOrderConstraint(layeredGraph, bal)) {
                    if (chosenLayout == null) {
                        chosenLayout = bal;
                    } else {
                        if (bal.layoutSize() < chosenLayout.layoutSize()) {
                            chosenLayout = bal;
                        }
                    }
                }
            }
        }
        // If no layout is correct (which should never happen but is not provable to never happen),
        // the lefttop layout is chosen by default.
        if (chosenLayout == null) {
            chosenLayout = lefttop;
        }

        // Apply calculated positions to nodes.
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                node.getPosition().y = chosenLayout.getY().get(node)
                        + chosenLayout.getInnerShift().get(node);
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

        if (debug) {
            System.out.println(getBlocks(chosenLayout));
        }

        getMonitor().done();
    }

    /**
     * This phase of the node placer marks all type 1 conflicts.
     * 
     * Type 0 conflicts refer to two short edges crossing each other. Type 1 conflicts occur when a
     * short edge cross a long edge. Type 2 conflicts happen when two long edges cross each other.
     * 
     * The markers are later used to solve conflicts in favor of long edges.
     * 
     * @param layeredGraph
     */
    private void markConflicts(final LayeredGraph layeredGraph) {
        for (int i = 1; i <= layeredGraph.getLayers().size() - 2; i++) {
            int k0 = 0;
            int l = 0;
            for (int l1 = 0; l1 < layerSize(layeredGraph, i + 1); l1++) {
                LNode vli = nodeByPosition(layeredGraph, i + 1, l1);
                if (l1 == (layerSize(layeredGraph, i + 1)) || incidentToInnerSegment(vli, i, i + 1)) {
                    int k1 = layerSize(layeredGraph, i);
                    if (incidentToInnerSegment(vli, i, i + 1)) {
                        k1 = allUpperNeighbors(vli).get(0).getIndex();
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

                    if (bal.getVDir() == VDirection.RIGHT) {
                        // Check, whether vik can be added to a block of its upper/lower neighbor(s)
                        for (int m = high; m >= low; m--) {
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

    /**
     * 
     * @param layeredGraph
     * @param bal
     */
    private void insideBlockShift(final LayeredGraph layeredGraph, final BKAlignedLayout bal) {
        HashMap<LNode, List<LNode>> blocks = getBlocks(bal);

        for (LNode root : blocks.keySet()) {
            double maximumNodeSize = root.getSize().y;
            double lowerBound = 0.0;
            double upperBound = root.getSize().y;
            double postShift = 0.0;

            LNode current = root;
            LNode next = bal.getAlign().get(root);

            while (next != root) {
                LEdge edge = getEdge(current, next);

                double difference = 0.0;
                if (bal.getHDir() == HDirection.BOTTOM) {
                    difference = edge.getTarget().getPosition().y + edge.getTarget().getAnchor().y
                            - edge.getSource().getPosition().y - edge.getSource().getAnchor().y
                            + bal.getInnerShift().get(current);
                } else {
                    difference = edge.getSource().getPosition().y + edge.getSource().getAnchor().y
                            - edge.getTarget().getPosition().y - edge.getTarget().getAnchor().y
                            + bal.getInnerShift().get(current);
                }
                double portPos = edge.getTarget().getPosition().y + edge.getTarget().getAnchor().y
                        + current.getMargin().top;
                double currentLowerBound = portPos;
                double currentUpperBound = current.getMargin().top + current.getSize().y
                        + current.getMargin().bottom - currentLowerBound;
                bal.getInnerShift().put(next, difference);

                if (bal.getAlign().get(next) == root) {
                    double nextPortPos = edge.getTarget().getPosition().y
                            + edge.getTarget().getAnchor().y + next.getMargin().top;
                    if (nextPortPos > currentLowerBound) {
                        currentLowerBound = nextPortPos;
                    }
                    double nextUpperBound = next.getSize().y + next.getMargin().top
                            + next.getMargin().bottom - nextPortPos;
                    if (nextUpperBound > currentUpperBound) {
                        currentUpperBound = nextUpperBound;
                    }
                }

                if (currentLowerBound > lowerBound) {
                    lowerBound = currentLowerBound;
                }
                if (currentUpperBound > upperBound) {
                    upperBound = currentUpperBound;
                }

                current = next;
                next = bal.getAlign().get(next);
            }

            maximumNodeSize = upperBound + lowerBound;
            postShift = lowerBound;

            bal.getInnerShift().put(root, bal.getInnerShift().get(root) + postShift);

            current = root;
            next = bal.getAlign().get(root);
            while (next != root) {
                bal.getInnerShift().put(next, bal.getInnerShift().get(next) + postShift);
                current = next;
                next = bal.getAlign().get(next);
            }
            bal.getBlockSize().put(root, maximumNodeSize);
        }
    }

    private void horizontalCompaction(final LayeredGraph layeredGraph, final BKAlignedLayout bal) {
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                bal.getSink().put(node, node);
                if (bal.getVDir() == VDirection.RIGHT) {
                    bal.getShift().put(node, Double.NEGATIVE_INFINITY);
                } else {
                    bal.getShift().put(node, Double.POSITIVE_INFINITY);
                }
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

            List<LNode> nodes = layer.getNodes();
            if (bal.getVDir() == VDirection.RIGHT) {
                nodes = Arrays.asList(new LNode[layer.getNodes().size()]);
                Collections.copy(nodes, layer.getNodes());
                Collections.reverse(nodes);
            }
            for (LNode v : nodes) {
                if (bal.getRoot().get(v).equals(v)) {
                    placeBlock(v, bal);
                }
            }
        }

        // It's important to traverse top-bottom or bottom-top here too
        for (Layer layer : layers) {

            for (LNode v : layer.getNodes()) {
                bal.getY().put(v, bal.getY().get(bal.getRoot().get(v)));
                if (bal.getVDir() == VDirection.RIGHT) {
                    if (v.equals(bal.getRoot().get(v))
                            && bal.getShift().get(bal.getSink().get(v)) > Double.NEGATIVE_INFINITY) {
                        bal.getY().put(v,
                                bal.getY().get(v) + bal.getShift().get(bal.getSink().get(v)));
                    }
                } else {
                    if (v.equals(bal.getRoot().get(v))
                            && bal.getShift().get(bal.getSink().get(v)) < Double.POSITIVE_INFINITY) {
                        bal.getY().put(v,
                                bal.getY().get(v) + bal.getShift().get(bal.getSink().get(v)));
                    }
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
                        if (bal.getVDir() == VDirection.RIGHT) {
                            bal.getShift().put(
                                    bal.getSink().get(u),
                                    Math.max(bal.getShift().get(bal.getSink().get(u)), bal.getY()
                                            .get(v)
                                            - bal.getY().get(u)
                                            + bal.getBlockSize().get(v)
                                            + spacing));
                        } else {
                            bal.getShift().put(
                                    bal.getSink().get(u),
                                    Math.min(bal.getShift().get(bal.getSink().get(u)), bal.getY()
                                            .get(v)
                                            - bal.getY().get(u)
                                            - bal.getBlockSize().get(u)
                                            - spacing));
                        }
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

    private BKAlignedLayout createBalancedLayout(final List<BKAlignedLayout> layouts,
            final int nodeCount) {
        final int noOfLayouts = layouts.size();
        BKAlignedLayout balanced = new BKAlignedLayout(nodeCount, null, null);
        double[] width = new double[noOfLayouts];
        double[] min = new double[noOfLayouts];
        double[] max = new double[noOfLayouts];
        int minWidthLayout = 0;

        for (int i = 0; i < noOfLayouts; i++) {
            min[i] = Integer.MAX_VALUE;
            max[i] = Integer.MIN_VALUE;
        }

        for (int i = 0; i < noOfLayouts; i++) {
            BKAlignedLayout current = layouts.get(i);
            for (double y : current.getY().values()) {
                if (min[i] > y) {
                    min[i] = y;
                }
                if (max[i] < y) {
                    max[i] = y;
                }
            }
            width[i] = max[i] - min[i];
            if (width[minWidthLayout] > width[i]) {
                minWidthLayout = i;
            }
        }

        double[] shift = new double[noOfLayouts];
        for (int i = 0; i < noOfLayouts; i++) {
            if (layouts.get(i).vdir == VDirection.LEFT) {
                shift[i] = min[minWidthLayout] - min[i];
            } else {
                shift[i] = max[minWidthLayout] - max[i];
            }
        }

        double[] calculatedYs = new double[noOfLayouts];
        for (LNode node : layouts.get(0).getY().keySet()) {
            for (int i = 0; i < noOfLayouts; i++) {
                calculatedYs[i] = layouts.get(i).getY().get(node) + shift[i];
            }
            Arrays.sort(calculatedYs);
            balanced.getY().put(node, (calculatedYs[1] + calculatedYs[2]) / 2.0);
            balanced.getInnerShift().put(node,
                    layouts.get(minWidthLayout).getInnerShift().get(node));
        }

        return balanced;
    }

    private int layerSize(final LayeredGraph layeredGraph, final int layer) {
        return layeredGraph.getLayers().get(layer).getNodes().size();
    }

    private LNode nodeByPosition(final LayeredGraph layeredGraph, final int layer,
            final int position) {
        return layeredGraph.getLayers().get(layer).getNodes().get(position);
    }

    private boolean incidentToInnerSegment(final LNode node, final int layer1, final int layer2) {
        if (node.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
            for (LEdge edge : node.getIncomingEdges()) {
                if (edge.getSource().getNode().getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE
                        && edge.getSource().getNode().getLayer().getIndex() == layer1
                        && node.getLayer().getIndex() == layer2) {
                    return true;
                }
            }
        }
        return false;
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
            if (
            node.getLayer() != edge.getSource().getNode().getLayer()) {
                result.add(edge.getSource().getNode());
            }
        }
        Collections.sort(result, new NeighborComparator());
        return result;
    }

    private List<LNode> allLowerNeighbors(final LNode node) {
        List<LNode> result = new LinkedList<LNode>();
        for (LEdge edge : node.getOutgoingEdges()) {
            if (
            node.getLayer() != edge.getTarget().getNode().getLayer()) {
                result.add(edge.getTarget().getNode());
            }
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

    private boolean checkOrderConstraint(final LayeredGraph layeredGraph, final BKAlignedLayout bal) {
        if (bal.getY().isEmpty()) {
            return false;
        }
        boolean layoutIsSane = true;
        for (Layer layer : layeredGraph.getLayers()) {
            double pos = Double.NEGATIVE_INFINITY;
            LNode previous = new LNode();
            for (LNode node : layer.getNodes()) {
                if (bal.getY().get(node) + bal.getInnerShift().get(node) + node.getSize().y > pos) {
                    previous = node;
                    pos = bal.getY().get(node) + bal.getInnerShift().get(node) + node.getSize().y;
                } else {
                    layoutIsSane = false;
                    if (debug) {
                        System.out.println("breaks on " + node + " which should have been after "
                                + previous);
                    }
                    break;
                }
            }
            if (!layoutIsSane) {
                break;
            }
        }
        if (debug) {
            System.out.println(bal + " is correct: " + layoutIsSane);
        }
        return layoutIsSane;
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
            } else if (vdir == VDirection.RIGHT) {
                result += "RIGHT";
            } else {
                result += "BALANCED";
            }
            if (hdir == HDirection.TOP) {
                result += "TOP";
            } else if (hdir == HDirection.BOTTOM) {
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
