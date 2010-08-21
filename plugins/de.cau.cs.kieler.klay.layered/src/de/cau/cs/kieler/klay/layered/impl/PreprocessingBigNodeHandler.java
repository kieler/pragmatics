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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.IBigNodeHandler;

/**
 * @author pdo
 */
public class PreprocessingBigNodeHandler extends AbstractAlgorithm implements IBigNodeHandler {

    // ===================================== Enums ================================================

    /** An {@code Enum} defining the traversal direction in the graph. */
    private enum Direction {

        /** The direction from source to target node of an each. */
        NATURAL,

        /** The direction from target to source node of an edge. */
        REVERSE;
    }

    // ================================== Attributes ==============================================

    /**
     * The layered graph, all methods in this class operate on.
     * 
     * @see de.cau.cs.kieler.klay.layered.graph.LayeredGraph LayeredGraph
     */
    private LayeredGraph layeredGraph;

    /**
     * A {@code Collection} containing all nodes in the graph to layer.
     * 
     * @see de.cau.cs.kieler.klay.layered.graph.LNode LNode
     */
    private Collection<LNode> nodes;

    /**
     * A {@code LinkedList} containing all wide nodes of the graph, i.e. all nodes, whose width in
     * pixel is greater then the width of the narrowest node in the graph plus a certain threshold
     * defined as 2 times the width of the narrowest node plus the minimal spacing between two
     * elements in the drawn graph ({@code minSpacing}).
     * 
     * @see de.cau.cs.kieler.klay.layered.impl.PreprocessingBigNodeHandler#minSpacing minSpacing
     * @see de.cau.cs.kieler.klay.layered.impl.PreprocessingBigNodeHandler#width width
     * @see de.cau.cs.kieler.klay.layered.graph.LNode LNode
     */
    private LinkedList<LNode> wideNodes;

    /**
     * The width of each node in layers before wide nodes have been split. Note that all wide nodes
     * in the graph can be easily identified by this attribute, since their {@code width} is always
     * greater than {@code 1}, whereas the {@code width} of all other nodes remains {@code 1}.
     * 
     * @see de.cau.cs.kieler.klay.layered.impl.PreprocessingBigNodeHandler#wideNodes wideNodes
     */
    private int[] width;

    /**
     * The height of each node in the DFS-tree. This attribute is required for the longest path
     * determination only. It will not be reused in any other part of the algorithm.
     * 
     * @see de.cau.cs.kieler.klay.layered.impl.PreprocessingBigNodeHandler#longestPath()
     *      longestPath()
     */
    private int[] height;

    /**
     * Note. that these attributes do not consider any layers that have to be added after the fixing
     * edges have been inserted.(Determined layering might be wider (more layers) due to adding
     * fixing edges).
     */
    private int[] leftLayer;

    private int[] rightLayer;

    /** The minimal spacing between two objects in the drawn graph. */
    private float minSpacing;

    /**
     * The position relative to the largest node in a layer segment each wide node is assigned to.
     * 
     * @see de.cau.cs.kieler.klay.layered.graph.LNode LNode
     */
    private ArrayList<LNode> nodeAssignment;

    /**
     * The ID of each wide node. Each dummy node, a wide node is split into, will share the same ID.
     */
    private int[] wideNodeID;

    /**
     * The length of the longest path in the graph from a source to sink node, which is defined as
     * the number of nodes in the path minus one.
     */
    private int longestPath;

    // ================================== Constructor =============================================

    /**
     * Default constructor for {@link PreprocessingBigNodeHandler}. It creates a new instance of
     * this class.
     * 
     * @see de.cau.cs.kieler.klay.layered.modules.IBigNodeHandler IBigNodeSplitter
     */
    public PreprocessingBigNodeHandler() {
    }

    // =============================== Initialization Methods =====================================

    /**
     * Helper method for the network simplex layerer. It instantiates all necessary attributes for
     * the execution of the network simplex layerer and initializes them with their default values.
     * If the attributes already exist (i.e. they were created by a previous function call) and if
     * their size fits for the nodes of the current graph, then the old instances will be reused as
     * far as possible. TODO
     * 
     * @param theNodes
     *            a {@code Collection} containing all nodes of the graph
     */
    private void initialize(final Collection<LNode> theNodes, final LayeredGraph theLayeredGraph) {

        // reindex nodes and layers
        int counter = 0;
        for (LNode node : nodes) {
            node.id = counter++;
        }
        counter = 0;
        for (Layer theLayer : layeredGraph.getLayers()) {
            theLayer.id = counter++;
        }
        layeredGraph = theLayeredGraph;

        // the number of nodes including all dummy nodes still to add
        int numNodes = nodes.size() + computeNodeSizes();
        // initialize node attributes
        if (width == null || width.length < nodes.size()) {
            width = new int[nodes.size()];
        }
        if (height == null || height.length < numNodes) {
            height = new int[numNodes];
            wideNodeID = new int[numNodes];
            leftLayer = new int[numNodes];
            rightLayer = new int[numNodes];
        } else {
            Arrays.fill(height, -1);
            Arrays.fill(leftLayer, 0);
        }
        wideNodes = new LinkedList<LNode>();
        nodes = theNodes;

        // the object spacing in the drawn graph
        minSpacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
    }

    // ============================== Big-Node-Splitting Algorithm ================================

    /**
     * .
     * 
     * @param theNodes
     *            a {@codeCollection} containing all nodes of the graph
     * @param theLayeredGraph
     *            the layered Graph
     * 
     * @see de.cau.cs.kieler.klay.layered.graph.LNode LNode
     * @see de.cau.cs.kieler.klay.layered.graph.LayeredGraph LayeredGraph
     */
    public void handleBigNodes(final Collection<LNode> theNodes, final LayeredGraph theLayeredGraph) {

        if (theNodes == null) {
            throw new NullPointerException("Input collection of nodes is null.");
        }
        if (layeredGraph == null) {
            throw new NullPointerException("Input graph is null.");
        }

        getMonitor().begin("Big Node Splitting", 1);
        if (theNodes.size() < 1) {
            getMonitor().done();
            return;
        }
        // instantiate attributes
        initialize(theNodes, theLayeredGraph);
        // split wide nodes
        splitWideNodes();
        // determine longest path
        longestPath = longestPath();
        // determine each node's leftmost and rightmost position in a layering
        shiftability();
        // sort wide nodes according to a non decreasing width
        sortWideNodes();
        // fixate wide nodes
        fixateWideNodes();

        getMonitor().done();
    }

    /**
     * Helper method for the Proprocessing Big-Node-Splitter. It fixates all wide nodes among
     * themselves to prevent the layering from becoming improper. Two wide nodes are either disjoint
     * regarding the layers, they are assigned to, or the smaller of the two nodes is assigned to
     * all layers, the larger node is also assigned to.
     * 
     * TODO
     */
    private void fixateWideNodes() {

        // the list of positions each wide is assigned to in the graph
        nodeAssignment = new ArrayList<LNode>(longestPath << 1);

        int addedLayers = 0;
        // the leftmost layer the left edge of the wide node is assigned to on average
        int avgLeft, avgRight;
        // counting variables to determine, with which other wide nodes this nodes shares the most
        // layers on average
        int segment1, segment2, segment3;
        LNode nodeSeg1, nodeSeg3, chosen;
        for (LNode node : wideNodes) {
            // determine layer, the node will be assigned to on average
            avgLeft = Math
                    .round((rightLayer[node.id] + addedLayers - leftLayer[node.id] - width[node.id]) / 2);
            avgRight = avgLeft + width[node.id];
            // determine other wide nodes, also assigned to this layer segment
            segment1 = 1;
            segment2 = 0;
            segment3 = 1;
            nodeSeg1 = nodeAssignment.get(avgLeft);
            nodeSeg3 = nodeAssignment.get(avgRight);
            chosen = null;

            // determine the wide node, the node shares the most layers with (on average)
            if (wideNodeID[nodeSeg1.id] == wideNodeID[nodeSeg3.id]) {
                chosen = nodeSeg1;
            } else {
                for (int layer = avgLeft + 1; layer < avgRight; layer++) {
                    if (wideNodeID[nodeAssignment.get(layer).id] == wideNodeID[nodeSeg1.id]) {
                        segment1++;
                    } else if (wideNodeID[nodeAssignment.get(layer).id] == wideNodeID[nodeSeg3.id]) {
                        segment3++;
                    } else {
                        segment2++;
                    }
                }
            }
            if (segment1 > segment3 && segment1 > segment2) {
                chosen = nodeSeg1;
            } else if (segment3 > segment2) {
                chosen = nodeSeg3;
            }
            
            // fixate node to the wide node, it shares the most layers with
            // TODO
        }
    }

    /**
     * Helper method for the Preprocessing Big Node Handler. It computes the width of each node
     * (i.e. the number of layers required to layer the node, which is the floor of the its width in
     * pixel devided by the width in pixel of the narrowest node in the graph). This value will be
     * stored in {@code width}. Furthermore, it fills the list of wide nodes in the graph
     * {@code wideNodes} with all nodes, whose width is greater than {@code 1} and returns the
     * number of dummy nodes, that have to be added, when the wide nodes are being split.
     * 
     * @return the number of dummy nodes to be added to the graph
     * 
     * @see de.cau.cs.kieler.klay.layered.impl.PreprocessingBigNodeHandler#wideNodes wideNodes
     * @see de.cau.cs.kieler.klay.layered.impl.PreprocessingBigNodeHandler#width width
     */
    private int computeNodeSizes() {

        // determine width of the smallest node
        double minWidth = Float.MAX_VALUE;
        for (LNode node : nodes) {
            if (node.getSize().x < minWidth) {
                minWidth = node.getSize().x;
            }
        }
        int numDummies = 0;
        int numLayers = 0;
        // determine number of layers necessary to layer the node
        double threshold = (2 * minWidth) + minSpacing;
        for (LNode node : nodes) {
            numLayers = 1;
            while (threshold <= node.getSize().x) {
                numLayers++;
                numDummies++;
                threshold += minWidth + minSpacing;
            }
            width[node.id] = numLayers;
            if (numLayers > 1) {
                // node is a wide node
                wideNodes.add(node);
            }
        }
        return numDummies;
    }

    /**
     * Helper method for the Preprocessing Big-Nodes-Splitter. It splits all wide nodes in the graph
     * into smaller, equal sized nodes by the insertion of dummy nodes. All incoming edges incident
     * to the original wide node will remain on the node, but outgoing back edges will be moved to
     * the rightmost inserted dummy node. A unique and consistent ID will be assigned to each
     * created dummy node. The wide node ID {@code wideNodeID} of each dummy node will be equal to
     * the ID of the wide node, they originate from. Note that after a wide node has been split, its
     * width in layers stored in {@code width} will not be updated.
     * 
     * @see de.cau.cs.kieler.klay.layered.impl.PreprocessingBigNodeHandler#wideNodes wideNodes
     * @see de.cau.cs.kieler.klay.layered.impl.PreprocessingBigNodeHandler#width width
     * 
     * 
     *      TODO
     */
    public void splitWideNodes() {

        // List of dummy nodes to be inserted in the graph
        LinkedList<LNode> addNodes = new LinkedList<LNode>();
        // the ID of the next dummy node
        int addID = nodes.size();

        // determine wide nodes
        for (LNode node : wideNodes) {
            wideNodeID[node.id] = node.id;
            // All nodes in the segment get the same width (temporarily)
            double newWidth = (node.getSize().x - ((width[node.id] - 1) * minSpacing))
                    / width[node.id];
            node.getSize().x = newWidth;
            // expand node by one dummy node, if node is wide
            for (int d = 1; d < width[node.id]; d++) {
                LNode addNode = new LNode();
                // assign IDs
                addNode.id = addID++;
                wideNodeID[addNode.id] = node.id;
                // set new size
                addNode.getSize().y = node.getSize().y;
                addNode.getSize().x = newWidth;
                // reassign ports
                LinkedList<LPort> ports = new LinkedList<LPort>();
                for (LPort port : node.getPorts(PortType.OUTPUT)) {
                    ports.add(port);
                }
                for (LPort port : ports) {
                    port.setNode(addNode);
                }
                // add edge to link the nodes
                LPort outPort = new LPort(PortType.OUTPUT);
                LPort inPort = new LPort(PortType.INPUT);
                outPort.setNode(node);
                inPort.setNode(addNode);
                LEdge edge = new LEdge();
                edge.setSource(outPort);
                edge.setTarget(inPort);
                addNodes.add(addNode);
            }
        }
        // add all new nodes to the graph
        nodes.addAll(addNodes);
    }

    /**
     * Helper method for the Preprocessing Big Node Handler. It determines the maximal shiftability
     * of each node in the graph, i.e. the leftmost and rightmost layer each node might be assigned
     * to in the layering phase indicated by {@code leftLayer}, resp. {@code rightLayer}.
     * 
     * @see de.cau.cs.kieler.klay.layered.impl.PreprocessingBigNodeHandler#leftLayer leftLayer
     * @see de.cau.cs.kieler.klay.layered.impl.PreprocessingBigNodeHandler#rightLayer rightLayer
     */
    private void shiftability() {

        // determine sink and source nodes
        LinkedList<LNode> sinks = new LinkedList<LNode>();
        LinkedList<LNode> sources = new LinkedList<LNode>();

        boolean isSource, isSink;
        for (LNode node : nodes) {
            isSource = true;
            isSink = true;
            for (LPort port : node.getPorts()) {
                if (port.getType() == PortType.OUTPUT) {
                    isSink = false;
                } else if (port.getType() == PortType.INPUT) {
                    isSource = false;
                }
            }
            if (isSink) {
                sinks.add(node);
            }
            if (isSource) {
                sources.add(node);
            }
        }

        // determine shiftability of each node
        Arrays.fill(rightLayer, longestPath);
        for (LNode node : sources) {
            shiftabilityDFS(node, Direction.NATURAL);
        }
        for (LNode node : sinks) {
            shiftabilityDFS(node, Direction.REVERSE);
        }
    }

    /**
     * 
     * @param node
     *            the root of the DFS-subtree
     * @param direction
     *            the traversal direction to traverse the DFS-subtree. If
     *            {@code direction = Direction.NATURAL}, only outgoing edges will be traversed (i.e.
     *            the DFS will follow the natural orientation of the edges from source to sink
     *            nodes). Otherwise, if {@code direction = Direction.REVERSE}, only incoming edges
     *            will be traversed (i.e. the DFS will pass each node against the edge orietation
     *            from sink to source nodes).
     * 
     * @see de.cau.cs.kieler.klay.layered.impl.PreprocessingBigNodeHandler.Direction Direction
     */
    private void shiftabilityDFS(final LNode node, final Direction direction) {

        LNode target = null;
        if (direction == Direction.NATURAL) {
            // traverse paths from sink to source node
            for (LPort port : node.getPorts(PortType.INPUT)) {
                for (LEdge edge : port.getEdges()) {
                    target = edge.getSource().getNode();
                    rightLayer[target.id] = Math
                            .min(rightLayer[target.id], rightLayer[node.id] - 1);
                    shiftabilityDFS(target, Direction.NATURAL);
                }
            }
        } else {
            // traverse paths from source to sink node
            for (LPort port : node.getPorts(PortType.OUTPUT)) {
                for (LEdge edge : port.getEdges()) {
                    target = edge.getTarget().getNode();
                    leftLayer[target.id] = Math.max(leftLayer[target.id], leftLayer[node.id] + 1);
                    shiftabilityDFS(target, Direction.REVERSE);
                }
            }
        }
    }

    /**
     * Helper method for the preprocessing Big-Node-Splitter. It sorts the list of wide nodes
     * {@code wideNodes} according to a non-decreasing width persueing the bucket sort strategy,
     * which guarantees a linear execution time. During sorting, the list {@code wideNodes} will be
     * completely cleared and rebuilt.
     * 
     * @see de.cau.cs.kieler.klay.layered.impl.PreprocessingBigNodeHandler#wideNodes wideNodes
     */
    private void sortWideNodes() {

        // determine width of widest node
        int maxWidth = 1;
        for (LNode node : wideNodes) {
            if (width[node.id] > maxWidth) {
                maxWidth = width[node.id];
            }
        }
        // create buckets
        ArrayList<LinkedList<LNode>> buckets = new ArrayList<LinkedList<LNode>>(maxWidth - 1);
        for (int i = 1; i < maxWidth; i++) {
            buckets.add(new LinkedList<LNode>());
        }
        // sort
        for (LNode node : wideNodes) {
            buckets.get(width[node.id] - 2).add(node);
        }
        // rebuild wideNodes
        wideNodes.clear();
        for (LinkedList<LNode> bucket : buckets) {
            wideNodes.addAll(bucket);
        }
    }

    /**
     * Helper method for the Big-Node-Splitter. It determines the length of the longest path in the
     * graph after splitting all wide nodes into smaller, equal sized ones, which is defined as the
     * number of nodes in the path minus one.
     * 
     * @return the length of the longest path in the graph
     * 
     * @see de.cau.cs.kieler.klay.layered.impl.PreprocessingBigNodeHandler#longestPathDFS(LNode)
     *      longestPathDFS()
     */
    private int longestPath() {

        int result = 0;
        for (LNode node : nodes) {
            if (height[node.id] == -1) {
                result = Math.max(result, longestPathDFS(node));
            }
        }
        return result;
    }

    /**
     * Helper method for the longest path determination. Sets height also.
     * 
     * @param node
     *            the root of the current DFS-subtree
     * 
     * @see de.cau.cs.kieler.klay.layered.impl.PreprocessingBigNodeHandler#longestPath()
     *      longestPath()
     * @see de.cau.cs.kieler.klay.layered.impl.PreprocessingBigNodeHandler#height height
     */
    private int longestPathDFS(final LNode node) {

        if (height[node.id] != -1) {
            // node has already been visited
            return height[node.id];
        }
        int maxHeight = 0;
        for (LPort port : node.getPorts(PortType.OUTPUT)) {
            for (LEdge edge : port.getEdges()) {
                maxHeight = Math.max(maxHeight, 1 + longestPathDFS(edge.getTarget().getNode()));
            }
        }
        height[node.id] = maxHeight;
        return maxHeight;
    }

}
