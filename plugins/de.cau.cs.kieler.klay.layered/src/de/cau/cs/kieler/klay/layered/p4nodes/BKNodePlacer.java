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

import java.io.Serializable;
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
import de.cau.cs.kieler.klay.layered.IntermediateLayoutProcessor;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * 
 * This algorithm is an implementation for solving the node placement problem
 * which is posed in phase 4 of the KLay Layered algorithm.
 * 
 * It is based on the node placement algorithm by Ulrik Brandes and Boris Koepf
 * @see <a href="http://www.informatik.uni-konstanz.de/algo/publications/bk-fshca-01.pdf">
 * the corresponding paper</a> and was extended to be able to cope with ports, node sizes,
 * node margins and was made more stable in general.
 * 
 * The algorithm is structured in five steps, which include two new steps which were
 * not included in the original algorithm by Brandes and Koepf. The middle three steps
 * are executed four times, traversing the graph in all combinations of TOP or BOTTOM
 * and LEFT or RIGHT.
 * 
 * Although we have, in KLay Layered, the general idea of layouting from left to right
 * and transforming in the desired direction later, we decided to keep the terminology
 * from the original algorithm which thinks of a layout from top to bottom. When placing
 * coordinates, we have to differ from the original algorithm, since node placement in
 * KLay Layered has to assign y-coordinates and not x-coordinates.
 * 
 * The algorithm:
 * 
 * The first step checks the graphs' edges and marks short edges which cross long edges
 * (called type 1 conflict). The algorithm indents to draw long edges as straight
 * as possible, thus trying to solve the marked conflicts in a way which keep the
 * long edge straight.
 * 
 * ============ TOP, BOTTOM x LEFT, RIGHT ============
 * 
 * The second step traverses the graph in the given directions and tries to group
 * connected nodes into (horizontal) blocks. These blocks, respectively the contained
 * nodes, will be drawn straight when the algorithm is finished. Here, type 1 conflicts
 * are resolved, so that the dummy nodes of a long edge share the same block if possible,
 * such that the long edge is drawn straightly.
 * 
 * The third step contains the addition of node size and port positions to the original
 * algorithm. Each block is investigated from TOP to BOTTOM. Nodes are moved inside the
 * blocks, such that the port of the edge going to the next node is on the same level as
 * that next node. Furthermore, the size of the block is calculated, regarding node sizes
 * and new space needed due to node movement.
 * 
 * In the fourth step, actual y-coordinates are determined. The blocks are placed, start
 * block and direction determined by the directions of the current iteration. 
 * It is tried to place the blocks as compact as possible by grouping blocks.
 *  
 * ======================= END =======================
 * 
 * The action of the last step depends on a layout option. If "Less Edge Bends" is set to
 * true, one of the four calculated layouts is selected and applied, choosing the layout 
 * which uses the least space. If it is false, a balanced layout is chosen by calculating
 * a median layout of all four layouts.
 * 
 * In rare cases, it is possible that one or more layouts is not correct, e.g. having nodes
 * which overlap each other or violating the layer ordering constraint. If the algorithm
 * detects that, the respective layout is discarded and another one is chosen.
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
    private static final IntermediateProcessingStrategy HIERARCHY_PROCESSING_ADDITIONS
                            = new IntermediateProcessingStrategy(
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
        getMonitor().begin("Brandes & Koepf node placement", 1);
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

        // Choose a layout from the four calculated layouts. Layouts that contain errors are skipped.
        // The layout with the smallest size is selected. If more than one smallest layout exists,
        // the first one of the competing layouts is selected.
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
     * This phase of the node placer marks all type 1 and type 2 conflicts.
     * 
     * The conflict types base on the distinction of inner segments and non-inner segments of edges.
     * A inner segment is present if an edge is drawn between two dummy nodes and thus is part of
     * a long edge. A non-inner segment is present if one of the connected nodes is not a dummy
     * node.
     * 
     * Type 0 conflicts occur if two non-inner segments cross each other. Type 1 conflicts happen 
     * when a non-inner segment and a inner segment cross. Type 2 conflicts are present if two
     * inner segments cross.
     * 
     * The markers are later used to solve conflicts in favor of long edges. In case of type 2
     * conflicts, the marker favors the earlier node in layout order.
     * 
     * @param layeredGraph
     */
    private void markConflicts(final LayeredGraph layeredGraph) {
        for (int i = 1; i <= layeredGraph.getLayers().size() - 2; i++) {
            // The variable naming here follows the notation of the corresponding paper
            int k_0 = 0;
            int l = 0;
            for (int l_1 = 0; l_1 < layerSize(layeredGraph, i + 1); l_1++) {
                // In the paper, l and i are indices for the layer and the position in the layer
                LNode v_l_i = nodeByPosition(layeredGraph, i + 1, l_1);
                if (l_1 == (layerSize(layeredGraph, i + 1)) || incidentToInnerSegment(v_l_i, i, i + 1)) {
                    int k_1 = layerSize(layeredGraph, i);
                    if (incidentToInnerSegment(v_l_i, i, i + 1)) {
                        k_1 = allUpperNeighbors(v_l_i).get(0).getIndex();
                    }
                    while (l <= l_1) {
                        LNode v_l = nodeByPosition(layeredGraph, i + 1, l);
                        for (LNode upperNeighbor : allUpperNeighbors(v_l)) {
                            int k = upperNeighbor.getIndex();
                            if (k < k_0 || k > k_1) {
                                // Marked edge can't return null here, because the upper neighbor
                                // relationship between v_l and upperNeighbor enforces the existence
                                // of at least one edge between the two nodes
                                markedEdges.add(getEdge(upperNeighbor, v_l));
                            }
                        }
                        l++;
                    }
                    k_0 = k_1;
                }
            }
        }
    }

    /**
     * 
     * The graph is traversed in the given directions and nodes a grouped into blocks.
     * 
     * These blocks, respectively the contained nodes, will be drawn straight when the
     * algorithm is finished.
     * 
     * Type 1 conflicts are resolved, so that the dummy nodes of a long edge share the
     * same block if possible, such that the long edge is drawn straightly.
     * 
     * @param layeredGraph
     * @param bal One of the four layouts which shall be used in this step 
     */
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
            // Variable names here are again taken from the paper mentioned above.
            // i denotes the index of the layer and k the position of the node within the layer.
            // m denotes the position of a neighbor in the neighbor list of a node.
            for (LNode v_i_k : nodes) {
                List<LNode> neighbors = null;
                if (bal.getHDir() == HDirection.BOTTOM) {
                    neighbors = allLowerNeighbors(v_i_k);
                } else {
                    neighbors = allUpperNeighbors(v_i_k);
                }

                if (neighbors.size() > 0) {

                    // When a node has many upper neighbors, consider only the (two) nodes in the
                    // middle.
                    int d = neighbors.size();
                    int low = ((int) Math.floor(((d + 1.0) / 2.0))) - 1;
                    int high = ((int) Math.ceil(((d + 1.0) / 2.0))) - 1;

                    if (bal.getVDir() == VDirection.RIGHT) {
                        // Check, whether v_i_k can be added to a block of its upper/lower neighbor(s)
                        for (int m = high; m >= low; m--) {
                            if (bal.getAlign().get(v_i_k).equals(v_i_k)) {
                                LNode u_m = neighbors.get(m);
                                // Again, getEdge won't return null because the neighbor relationship
                                // ensures that at least one edge exists
                                if (!markedEdges.contains(getEdge(u_m, v_i_k)) && r > u_m.getIndex()) {
                                    bal.getAlign().put(u_m, v_i_k);
                                    bal.getRoot().put(v_i_k, bal.getRoot().get(u_m));
                                    bal.getAlign().put(v_i_k, bal.getRoot().get(v_i_k));
                                    r = u_m.getIndex();
                                }
                            }
                        }
                    } else {
                        // Check, whether vik can be added to a block of its upper/lower neighbor(s)
                        for (int m = low; m <= high; m++) {
                            if (bal.getAlign().get(v_i_k).equals(v_i_k)) {
                                LNode um = neighbors.get(m);
                                if (!markedEdges.contains(getEdge(um, v_i_k)) && r < um.getIndex()) {
                                    bal.getAlign().put(um, v_i_k);
                                    bal.getRoot().put(v_i_k, bal.getRoot().get(um));
                                    bal.getAlign().put(v_i_k, bal.getRoot().get(v_i_k));
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
     * This phase moves the nodes inside a block, ensuring that all edges inside a block
     * can be drawn straightly.
     * 
     * This phase is not included in the original algorithm and adds port and node size
     * handling.
     * 
     * @param layeredGraph
     * @param bal One of the four layouts which shall be used in this step
     */
    private void insideBlockShift(final LayeredGraph layeredGraph, final BKAlignedLayout bal) {
        HashMap<LNode, List<LNode>> blocks = getBlocks(bal);

        for (LNode root : blocks.keySet()) {
            // Determine the basic size of the block
            double maximumNodeSize = root.getMargin().top + root.getSize().y + root.getMargin().bottom;
            
            // lowerBound determines the top (!) border of the block, since the coordinates
            // of the canvas start with (0,0) and grow down. upperBound then determines
            // the bottom border of the block.
            double lowerBound = 0.0;
            double upperBound = root.getMargin().top + root.getSize().y + root.getMargin().bottom;
            double postShift = 0.0;

            // Now, the sizes and shifts between the root node and a possible second node
            // of the block is determined, for cases where there are only one or two nodes
            // inside a block
            LNode current = root;
            LNode next = bal.getAlign().get(root);
            
            double rootPortPos = 0.0;
            double rootUpperBound = 0.0;
            
            LEdge rootEdge = getEdge(current, next);
            
            if (rootEdge != null) {
                if (bal.getHDir() == HDirection.BOTTOM) {
                    rootPortPos = rootEdge.getTarget().getPosition().y
                            + rootEdge.getTarget().getAnchor().y + next.getMargin().top;
                } else {
                    rootPortPos = rootEdge.getSource().getPosition().y
                            + rootEdge.getSource().getAnchor().y + next.getMargin().top;
                }
                rootUpperBound = current.getMargin().top + current.getSize().y
                        + current.getMargin().bottom - rootPortPos;
                
                lowerBound = rootPortPos;
                upperBound = rootUpperBound;
            }

            // Now, the rest of the block is investigated and borders are updated if
            // they exceed the old borders due to shifting or larger node sizes
            while (next != root) {
                LEdge edge = getEdge(current, next);

                double difference = 0.0;
                double portPos = 0.0;
                if (bal.getHDir() == HDirection.BOTTOM) {
                    difference = edge.getTarget().getPosition().y + edge.getTarget().getAnchor().y
                            - edge.getSource().getPosition().y - edge.getSource().getAnchor().y
                            + bal.getInnerShift().get(current);
                    portPos = edge.getSource().getPosition().y + edge.getSource().getAnchor().y
                            + next.getMargin().top;
                } else {
                    difference = edge.getSource().getPosition().y + edge.getSource().getAnchor().y
                            - edge.getTarget().getPosition().y - edge.getTarget().getAnchor().y
                            + bal.getInnerShift().get(current);
                    portPos = edge.getTarget().getPosition().y + edge.getTarget().getAnchor().y
                            + next.getMargin().top;
                }
                double currentLowerBound = portPos;
                double currentUpperBound = next.getMargin().top + next.getSize().y
                        + next.getMargin().bottom - currentLowerBound;
                bal.getInnerShift().put(next, difference);

                if (currentLowerBound > lowerBound) {
                    lowerBound = currentLowerBound;
                }
                if (currentUpperBound > upperBound) {
                    upperBound = currentUpperBound;
                }

                current = next;
                next = bal.getAlign().get(next);
            }

            // Use the values calculated above only if there are more nodes than the
            // root node in the block
            if (bal.getAlign().get(root) != root) {
                maximumNodeSize = upperBound + lowerBound;
            }
            // If the block's top border is higher than the root node, use this, else
            // use the root node
            if (lowerBound > rootPortPos) {
                postShift = lowerBound;
            }

            // Apply a general shift to all nodes of the block, which results from
            // nodes which would be placed higher than the top border of the block
            bal.getInnerShift().put(root, bal.getInnerShift().get(root) + postShift);
            current = root;
            next = bal.getAlign().get(root);
            while (next != root) {
                bal.getInnerShift().put(next, bal.getInnerShift().get(next) + postShift);
                current = next;
                next = bal.getAlign().get(next);
            }
            
            // Note the block size of the investigated block for later use
            bal.getBlockSize().put(root, maximumNodeSize);
        }
    }

    /**
     * 
     * In this step, actual coordinates are calculated for blocks and its nodes.
     * 
     * First, all blocks are placed, trying to avoid any crossing of the blocks.
     * Then, the blocks are shifted towards each other if there is any space for 
     * compaction.
     * 
     * @param layeredGraph
     * @param bal One of the four layouts which shall be used in this step
     */
    private void horizontalCompaction(final LayeredGraph layeredGraph, final BKAlignedLayout bal) {
        // Initialize fields with basic values, partially depending on the direction
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
            // As with layers, we need a reversed iterator for blocks for different directions
            List<LNode> nodes = layer.getNodes();
            if (bal.getVDir() == VDirection.RIGHT) {
                nodes = Arrays.asList(new LNode[layer.getNodes().size()]);
                Collections.copy(nodes, layer.getNodes());
                Collections.reverse(nodes);
            }
            
            // Do a initial placement for all blocks
            for (LNode v : nodes) {
                if (bal.getRoot().get(v).equals(v)) {
                    placeBlock(v, bal);
                }
            }
        }

        // Try to compact blocks by shifting them towards each other if there is space
        // between them. It's important to traverse top-bottom or bottom-top here too
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

    /**
     * 
     * Blocks are placed based on their root node.
     * 
     * This is done by watching all layers which are crossed by this block and
     * moving the whole block up/downwards if there are blocks which already occupy
     * the chosen position.
     * 
     * @param v The root node of the block
     * @param bal One of the four layouts which shall be used in this step
     */
    private void placeBlock(final LNode v, final BKAlignedLayout bal) {
        // Only place block if it does not have a placement already
        if (!bal.getY().containsKey(v)) {
            bal.getY().put(v, 0.0);
            LNode w = v;
            
            // Iterate through block and determine, where the block can be placed
            do {
                // If the node is the top or bottom node of it's layer, it can be, depending
                // on the current direction, placed safely since it is the first to be placed
                if ((bal.getVDir() == VDirection.LEFT && w.getIndex() > 0)
                        || (bal.getVDir() == VDirection.RIGHT && w.getIndex() < (w.getLayer()
                                .getNodes().size() - 1))) {

                    // Get the node which is top/bottom to the node to be placed to check,
                    // whether the current node conflicts with it
                    LNode u = null;
                    if (bal.getVDir() == VDirection.RIGHT) {
                        u = bal.getRoot().get(w.getLayer().getNodes().get(w.getIndex() + 1));
                    } else {
                        u = bal.getRoot().get(w.getLayer().getNodes().get(w.getIndex() - 1));
                    }

                    // Check whether the comparison node is already placed, place it if not
                    placeBlock(u, bal);
                    
                    // Note that the two nodes and their blocks form a unit called class in the
                    // original algorithm. These are combinations of blocks which play a role
                    // in the final compaction
                    if (bal.getSink().get(v).equals(v)) {
                        bal.getSink().put(v, bal.getSink().get(u));
                    }
                    
                    // If two nodes aren't member of the same class, calculate how the two classes
                    // might be compacted later on
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
                        // If they are on the class, calculate a y position for the current block,
                        // using the information from the comparison node
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
                // Get the next node in the block
                w = bal.getAlign().get(w);
            } while (w != v);
        }
    }

    /**
     * 
     * A balanced layout is calculated by determining the median layout of the
     * four layouts.
     * 
     * First, the layout with the smallest height, meaning the difference between the highest and the
     * lowest y-coordinate placement, is used as a starting point.
     * Then, the median position of each of the four layouts is used for determining
     * the final position.
     * 
     * @param layouts The four calculated layouts
     * @param nodeCount The number of nodes in the graph
     * @return A balanced layout, the median of the four layouts
     */
    private BKAlignedLayout createBalancedLayout(final List<BKAlignedLayout> layouts,
            final int nodeCount) {
        final int noOfLayouts = layouts.size();
        BKAlignedLayout balanced = new BKAlignedLayout(nodeCount, null, null);
        double[] width = new double[noOfLayouts];
        double[] min = new double[noOfLayouts];
        double[] max = new double[noOfLayouts];
        int minWidthLayout = 0;

        // Find the smallest layout
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

        // Find the shift between the smallest and the four layouts
        double[] shift = new double[noOfLayouts];
        for (int i = 0; i < noOfLayouts; i++) {
            if (layouts.get(i).vdir == VDirection.LEFT) {
                shift[i] = min[minWidthLayout] - min[i];
            } else {
                shift[i] = max[minWidthLayout] - max[i];
            }
        }

        // Calculated y-coordinates for a balanced placement
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

    /**
     * 
     * Auxiliary method for getting the size of a layer.
     * 
     * @param layeredGraph
     * @param layer
     * @return The size of the given layer
     */
    private int layerSize(final LayeredGraph layeredGraph, final int layer) {
        return layeredGraph.getLayers().get(layer).getNodes().size();
    }

    /**
     * 
     * Auxiliary method for getting the node on a certain position of a layer.
     * 
     * @param layeredGraph
     * @param layer
     * @param position
     * @return The node which is on the given position of the given layer or an exception, if there is
     *         no node on the given position
     */
    private LNode nodeByPosition(final LayeredGraph layeredGraph, final int layer,
            final int position) {
        return layeredGraph.getLayers().get(layer).getNodes().get(position);
    }

    /**
     * 
     * Checks whether the given node is part of a long edge between the two given layers.
     * 
     * @param node
     * @param layer1
     * @param layer2
     * @return True if the node is part of a long edge between the layers, false else
     */
    private boolean incidentToInnerSegment(final LNode node, final int layer1, final int layer2) {
        if (node.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
            for (LEdge edge : node.getIncomingEdges()) {
                if ((edge.getSource().getNode().getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE
                        || edge.getSource().getNode().getProperty(Properties.NODE_TYPE)
                                                             == NodeType.COMPOUND_SIDE)
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
     * @return A list containing all upper neighbors
     */
    private List<LNode> allUpperNeighbors(final LNode node) {
        List<LNode> result = new LinkedList<LNode>();
        for (LEdge edge : node.getIncomingEdges()) {
            if (node.getLayer() != edge.getSource().getNode().getLayer()) {
                result.add(edge.getSource().getNode());
            }
        }
        Collections.sort(result, new NeighborComparator());
        return result;
    }

    /**
     * Give all lower neighbors of a given node.
     * 
     * A lower neighbor is a node in a following layer which has an edge coming from the given node.
     * 
     * @param node
     * @return A list containing all lower neighbors
     */
    private List<LNode> allLowerNeighbors(final LNode node) {
        List<LNode> result = new LinkedList<LNode>();
        for (LEdge edge : node.getOutgoingEdges()) {
            if (node.getLayer() != edge.getTarget().getNode().getLayer()) {
                result.add(edge.getTarget().getNode());
            }
        }
        Collections.sort(result, new NeighborComparator());
        return result;
    }

    /**
     * An auxiliary method to find an edge between two given nodes.
     * 
     * @param source
     * @param target
     * @return The edge between source and target, or null if there is none
     */
    private LEdge getEdge(final LNode source, final LNode target) {
        for (LEdge edge : source.getConnectedEdges()) {
            if (edge.getTarget().getNode().equals(target)
                    || edge.getSource().getNode().equals(target)) {
                return edge;
            }
        }
        return null;
    }

    /**
     * An auxiliary method for finding all blocks of a given layout.
     * 
     * @param bal The layout of which the blocks shall be found
     * @return The blocks of the given layout
     */
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
     * It is checked whether all nodes are placed in the correct order in their layers
     * and do not overlap each other.
     * 
     * @param layeredGraph
     * @param bal The layout which shall be checked
     * @return True if the order is preserved and no nodes overlap, false else
     */
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

    /**
     * Comparator which determines the order of nodes in a layer.
     */
    private static class NeighborComparator implements Comparator<LNode>, Serializable {

        /**
         * 
         */
        private static final long serialVersionUID = 7540379553811800233L;

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

    /**
     * Class which holds all information about a layout in one of the four direction
     * combinations.
     */
    private static class BKAlignedLayout {

        /** The root node of each node in a block. */
        private HashMap<LNode, LNode> root;

        /** The size of a block. */
        private HashMap<LNode, Double> blockSize;

        /** The next node in a block, or the first if the current node is the last,
         * forming a ring. */
        private HashMap<LNode, LNode> align;

        /** The value by which a node must be shifted to stay straight inside a block. */
        private HashMap<LNode, Double> innerShift;

        /** The root node of a class, mapped from block root nodes to class root nodes. */
        private HashMap<LNode, LNode> sink;

        /** The value by which a block must be shifted for a more compact placement. */
        private HashMap<LNode, Double> shift;

        /** The y-coordinate of every node, forming the final layout. */
        private HashMap<LNode, Double> y;

        /** The vertical direction of the current layout. */
        private VDirection vdir;

        /** The horizontal direction of the current layout. */
        private HDirection hdir;

        /**
         * Basic constructor for a layout.
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

        /**
         * Calculate the layout size for comparison.
         * 
         * @return The size of the layout
         */
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

    /**
     * Vertical direction enumeration.
     */
    private enum VDirection {
        LEFT, RIGHT;
    }

    /**
     * Horizontal direction enumeration.
     */
    private enum HDirection {
        TOP, BOTTOM;
    }

}
