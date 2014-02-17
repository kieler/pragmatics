/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 * + Department of Computer Science
 * + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p4nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Node placement implementation inspired by Buchheim, Jünger und Leipert
 * 
 * @author kpe
 */
/**
 * @author katja
 * 
 */
public final class BJLNodePlacer implements ILayoutPhase {
    /** the original layered Graph. */
    private LGraph layeredGraph;
    /** total number of nodes of the current graph. */
    private int numberOfNodes;
    /** list of all linear segments of the current graph. */
    private List<LinearSegment> linearSegments;
    /** Lookup for siblings, segments and classes by Node id. */
    private static Map<Integer, LNodeExtensions> nodeExtensions;

    /** top classes computed by traversing the graph from left to right and each level downwards. */
    private Map<Integer, List<LinearSegment>> topClasses;
    /** bottom classes computed by traversing the graph left to right and each level upwards. */
    private Map<Integer, List<LinearSegment>> bottomClasses;
    /** top layout, topmost y-coordinates computed by placeVirtual. */
    private double[] nodePositionsTop;
    /** bottom layout, bottommost y-coordinates computed by placeVirtual. */
    private double[] nodePositionsBottom;

    /** original layout y-coordinates of each node computed by placeOriginal. */
    private double[] nodePositions;

    /** distance between original nodes. */
    private float normalSpacing;
    /** distance between virtual nodes. */
    private float smallSpacing;
    /** maximum y-Coordinate of the graph. */
    private double maxY;

    /** additional processor dependencies for graphs with hierarchical ports. */
    private static final IntermediateProcessingConfiguration HIERARCHY_PROCESSING_ADDITIONS =
            new IntermediateProcessingConfiguration(
                    IntermediateProcessingConfiguration.BEFORE_PHASE_5,
                    LayoutProcessorStrategy.HIERARCHICAL_PORT_POSITION_PROCESSOR);

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final LGraph graph) {

        if (graph.getProperty(Properties.GRAPH_PROPERTIES).contains(GraphProperties.EXTERNAL_PORTS)) {
            return HIERARCHY_PROCESSING_ADDITIONS;
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph lGraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Buchheim, Jünger and Leipert Node Placement", 1);

        this.layeredGraph = lGraph;

        normalSpacing =
                layeredGraph.getProperty(Properties.OBJ_SPACING)
                        * layeredGraph.getProperty(Properties.OBJ_SPACING_VERTICAL_FACTOR);
        smallSpacing = normalSpacing * layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);

        // preparation
        initializeNodes();

        initializeLayerId();

        initializeNodeExtensions();

        // start algorithm
        placeVirtual();

        placeOrigninal();

        monitor.done();
    }

    // begin initialization
    /********************************************************/
    /** initialize node id and compute the number of nodes. */
    private void initializeNodes() {
        numberOfNodes = 0;
        for (Layer layer : layeredGraph) {
            for (LNode node : layer.getNodes()) {
                node.id = numberOfNodes;
                numberOfNodes++;
            }
        }
    }

    /** initialize layer id. */
    private void initializeLayerId() {
        int idOfLayer = 0;
        for (Layer layer : layeredGraph) {
            layer.id = idOfLayer;
            idOfLayer++;
        }
    }

    /** initialize node extensions, compute all left and right siblings. */
    private void initializeNodeExtensions() {
        // lookup for all direct siblings
        nodeExtensions = new HashMap<Integer, LNodeExtensions>();
        // initialize top sibling
        LNode topSibling = null;
        // initialize bottom sibling
        LNode bottomSibling = null;

        for (Layer layer : layeredGraph) {
            for (LNode node : layer) {

                int id = node.getIndex();
                // compute top sibling of current node
                if (id == 0) {
                    topSibling = null;
                } else {
                    topSibling = layer.getNodes().get(id - 1);
                }
                // compute RightSibbling of current node
                if (id == layer.getNodes().size() - 1) {
                    bottomSibling = null;
                } else {
                    bottomSibling = layer.getNodes().get(id + 1);
                }
                // create node Extensions and save current node with siblings
                LNodeExtensions aNode = new LNodeExtensions(topSibling, bottomSibling, -1, -1);
                nodeExtensions.put(node.id, aNode);
            }
        }
    }

    // end initialization
    /******************************************************************************/
    /**
     * Node Extension contains additional informations for all nodes.
     */
    private static final class LNodeExtensions {
        /** previous node in the same layer. */
        private LNode topSibling;
        /** successive node in the same layer. */
        private LNode bottomSibling;
        /** identifier value of the current node's linear segment. */
        private int segId;
        /** identifier value of the current node's class. */
        private int classId;
        /** Flag value to mark the node as placed or not. */
        private boolean isPlaced;
        /** computed minimum distance to the next bottom node in the same layer. */
        private double minDistanceToBottomSibling;

        /**
         * @param topSibling
         *            previous node in the same layer.
         * @param bottomSibling
         *            succesive node in the same layer
         * @param segId
         *            identifier value of the current node's linear segment.
         * @param classId
         *            identifier value of the current node's class.
         */
        public LNodeExtensions(final LNode topSibling, final LNode bottomSibling, final int segId,
                final int classId) {
            super();
            this.topSibling = topSibling;
            this.bottomSibling = bottomSibling;
            this.segId = segId;
            this.classId = classId;
            this.isPlaced = false;
            this.minDistanceToBottomSibling = 0;
        }
    }

    /**
     * A linear segment contains a single original node or all virtual nodes of a long edge.
     */
    private static final class LinearSegment {
        /** Identifier value, used as index in the segments array. */
        private int id;
        /** Identifier value of the layer of the first node in a linear segment. */
        private int layerId;
        /** Identifier value of the computed classes. */
        private int computedClassId;
        /** Nodes of the linear segment. */
        private List<LNode> nodes;

        /**
         * @param segId
         *            Identifier value, used as index in the segments array.
         * @param layerId
         *            Identifier value of the layer of the first node in a linear segment.
         */
        private LinearSegment(final int segId, final int layerId) {
            this.id = segId;
            this.layerId = layerId;
            this.computedClassId = -1;
            this.nodes = new LinkedList<LNode>();

        }

        /**
         * Splits this linear segment before the given node. The returned segment contains all nodes
         * from the given node onward, this nodes are removed from this segment.
         * 
         * @param id2
         *            the new segments identifier value
         * @param layer
         *            the layer identifier of the first node of the new segment
         * @param node
         *            node to split the segment at
         * @return seg new computed linear segment
         */
        public LinearSegment split(final int id2, final int layer, final LNode node) {
            int nodeIndex = this.getIndexOf(node);
            LinearSegment seg = new LinearSegment(id2, layer);

            ListIterator<LNode> iterator = nodes.listIterator(nodeIndex);
            while (iterator.hasNext()) {
                LNode movedNode = iterator.next();
                seg.addNode(movedNode);
                iterator.remove();
            }
            return seg;
        }

        // /**
        // * set the node list of this segment.
        // *
        // * @param nodes2
        // */
        // public void setNodes(final List<LNode> nodes2) {
        // nodes2.clear();
        // this.nodes = nodes2;
        // for (LNode lNode : this.nodes) {
        // nodeExtensions.get(lNode.id).segId = this.id;
        // }
        // }

        /**
         * add a node to the end of the node list of this segment and set the segment id in the node
         * extension.
         * 
         * @param node
         */
        public void addNode(final LNode node) {
            this.nodes.add(node);
            nodeExtensions.get(node.id).segId = this.id;
        }

        /**
         * returns the position of the node in this segment.
         * 
         * @param node
         * @return int
         */
        public int getIndexOf(final LNode node) {
            int index = -1;
            for (int i = 0; i < nodes.size(); i++) {
                if (nodes.get(i).id == node.id) {
                    index = i;
                }
            }
            return index;
        }
    }

    // end helper classes
    /************************************** Begin**placeVirtual **************************************/

    /**
     * place the virtual nodes as close to each other as possible respecting the minimal distance
     * between direct siblings, the given order of the nodes and the straightness of all inner
     * segments of long edges.
     */
    private void placeVirtual() {

        computeLinearSegments();

        computePositionTop();

        computePositionBottom();

        for (Layer layer : layeredGraph) {
            for (LNode lNode : layer) {
                lNode.getPosition().y =
                        (nodePositionsTop[lNode.id] + nodePositionsBottom[lNode.id]) / 2;
                System.out.println("Layer I: " + lNode.getLayer().id + " Knoten Name: "
                        + lNode.getName() + " Knoten I: " + lNode.id + " Position: "
                        + lNode.getPosition().y);
            }
        }
    }

    /**
     * computes the bottommost positions of all nodes of the graph.
     */
    private void computePositionBottom() {
        // initialize the bottom positions
        nodePositionsBottom = new double[numberOfNodes];
        for (int i = 0; i < nodePositionsBottom.length; i++) {
            nodePositionsBottom[i] = 0.0;
        }
        maxY = 0;
        for (int i = 0; i < nodePositionsTop.length; i++) {
            if (nodePositionsTop[i] > maxY) {
                maxY = nodePositionsTop[i];
            }
        }

        // mark all nodes as not placed
        for (int extNode : nodeExtensions.keySet()) {
            nodeExtensions.get(extNode).isPlaced = false;
        }
        computeBottomClasses();

        for (int classId : bottomClasses.keySet()) {
            for (LinearSegment seg : bottomClasses.get(classId)) {
                for (LNode node : seg.nodes) {
                    if (!nodeExtensions.get(node.id).isPlaced) {
                        // place all nodes of the current class to the lowest position as possible
                        placeBottom(node, classId);
                    }
                }
            }
            adjustBottomClass(classId);
        }
    }

    /**
     * place all nodes of the current node's segment to the bottommost position.
     * 
     * @param node
     * @param classId
     */
    private void placeBottom(final LNode lNode, final int classId) {

        double p = Double.POSITIVE_INFINITY;

        LinearSegment seg = linearSegments.get(nodeExtensions.get(lNode.id).segId);

        for (LNode node : seg.nodes) {
            // get the bottom sibling of the current node
            LNode bottomSibling = nodeExtensions.get(node.id).bottomSibling;

            if ((bottomSibling != null) && classId == nodeExtensions.get(bottomSibling.id).classId) {

                // if the bottom sibling wasn't placed yet
                if (!nodeExtensions.get(bottomSibling.id).isPlaced) {

                    placeBottom(bottomSibling, classId);
                }
                // compute the minimum distance between the current node and his bottom sibling
                double m = 0;
                if (node.getProperty(Properties.NODE_TYPE) == NodeType.NORMAL) {
                    m =
                            bottomSibling.getMargin().top + normalSpacing + node.getMargin().bottom
                                    + node.getSize().y;
                } else {
                    m =
                            bottomSibling.getMargin().top + smallSpacing + node.getMargin().bottom
                                    + node.getSize().y;
                }
                p = Math.min(p, (nodePositionsBottom[bottomSibling.id] - m));
            }

        }

        if (p == Double.POSITIVE_INFINITY) {
            // p = 0;
            p = maxY;
        }
        // set all nodes of the current linear segment to the same y-coordinate
        for (LNode node2 : seg.nodes) {
            nodePositionsBottom[node2.id] = p;
            nodeExtensions.get(node2.id).isPlaced = true;
        }
    }

    /**
     * align the current class to the previously placed class.
     * 
     * @param classId
     *            identifier value of the current class
     */
    private void adjustBottomClass(final int classId) {
        double d = Double.NEGATIVE_INFINITY;
        double m;

        for (LinearSegment seg : bottomClasses.get(classId)) {
            for (LNode node : seg.nodes) {
                // get top sibling of the current node
                LNode topSibling = nodeExtensions.get(node.id).topSibling;

                if ((topSibling != null) && (nodeExtensions.get(topSibling.id).classId != classId)) {

                    // minimum distance between the current node and his top sibling
                    m = 0;

                    if (node.getProperty(Properties.NODE_TYPE) == NodeType.NORMAL) {
                        m =
                                node.getMargin().top + normalSpacing
                                        + topSibling.getMargin().bottom + topSibling.getSize().y;
                    } else {
                        m =
                                node.getMargin().top + smallSpacing + topSibling.getMargin().bottom
                                        + topSibling.getSize().y;
                    }
                    d =
                            Math.max(d, nodePositionsBottom[node.id]
                                    - (nodePositionsBottom[topSibling.id] + m));
                    // System.out.println("d " + d + " für: " + node.id);
                }
            }
        }
        if (d == Double.NEGATIVE_INFINITY) {
            // list to save the positions of the connected nodes in the next layer for computing the
            // median
            ArrayList<Double> heap = new ArrayList<Double>();

            for (LinearSegment seg : bottomClasses.get(classId)) {
                for (LNode node : seg.nodes) {
                    for (LEdge edge : node.getIncomingEdges()) {

                        LNode neighbour = edge.getSource().getNode();
                        if (nodeExtensions.get(neighbour.id).classId < classId) {
                            heap.add(-nodePositionsBottom[neighbour.id]
                                    + nodePositionsBottom[node.id]);
                        }
                    }
                }
            }
            if (heap.size() == 0) {
                d = 0;
            } else {
                Collections.sort(heap);
                d = heap.get((int) Math.ceil(heap.size() / 2));

            }
        }
        for (LinearSegment seg : bottomClasses.get(classId)) {
            for (LNode node : seg.nodes) {
                nodePositionsBottom[node.id] = nodePositionsBottom[node.id] - d;
                // System.out.println("d " + d + " für: " + node.id + " " + node.getName());
            }
        }
    }

    /**
     * place all nodes of the current class to the topmost position.
     */
    private void computePositionTop() {
        // initialize the top Layout
        nodePositionsTop = new double[numberOfNodes];

        // mark all nodes as not placed
        for (int extNode : nodeExtensions.keySet()) {
            nodeExtensions.get(extNode).isPlaced = false;
        }

        computeTopClasses();

        for (int classId : topClasses.keySet()) {
            for (LinearSegment seg : topClasses.get(classId)) {
                for (LNode node : seg.nodes) {
                    if (!nodeExtensions.get(node.id).isPlaced) {
                        placeTop(node, classId);

                    }
                }
            }
            adjustTopClass(classId);
        }
    }

    /**
     * place all nodes of the current node's segment to the topmost position.
     * 
     * @param node
     * @param classId
     */
    private void placeTop(final LNode lNode, final int classId) {

        double p = Double.NEGATIVE_INFINITY;
        // get the current node's segment
        LinearSegment seg = linearSegments.get(nodeExtensions.get(lNode.id).segId);

        for (LNode node : seg.nodes) {

            LNode topSibling = nodeExtensions.get(node.id).topSibling;

            if ((topSibling != null) && classId == nodeExtensions.get(topSibling.id).classId) {

                if (!nodeExtensions.get(topSibling.id).isPlaced) {

                    placeTop(topSibling, classId);
                }
                // compute the minimum distance between the current node and his top sibling
                double m = 0;
                if (node.getProperty(Properties.NODE_TYPE) == NodeType.NORMAL) {
                    m =
                            topSibling.getSize().y + topSibling.getMargin().bottom + normalSpacing
                                    + node.getMargin().top;
                } else {
                    m =
                            topSibling.getSize().y + topSibling.getMargin().bottom + smallSpacing
                                    + node.getMargin().top;
                }
                p = Math.max(p, (nodePositionsTop[topSibling.id] + m));
            }
        }
        if (p == Double.NEGATIVE_INFINITY) {
            p = 0;
        }
        // set all nodes of the current segment to the same y-coordinate
        for (LNode node2 : seg.nodes) {
            nodePositionsTop[node2.id] = p;
            nodeExtensions.get(node2.id).isPlaced = true;
        }
    }

    /**
     * align the current class to the previously placed class.
     * 
     * @param classID
     */
    private void adjustTopClass(final int classId) {

        double d = Double.POSITIVE_INFINITY;
        double m;

        for (LinearSegment seg : topClasses.get(classId)) {
            for (LNode node : seg.nodes) {

                LNode bottomSibling = nodeExtensions.get(node.id).bottomSibling;

                if ((bottomSibling != null)
                        && (nodeExtensions.get(bottomSibling.id).classId != classId)) {

                    // compute the minimum distance between the current node and his bottom sibling
                    m = 0;
                    if (node.getProperty(Properties.NODE_TYPE) == NodeType.NORMAL) {
                        m =
                                node.getSize().y + node.getMargin().bottom + normalSpacing
                                        + bottomSibling.getMargin().top;
                    } else {
                        m =
                                node.getSize().y + node.getMargin().bottom + smallSpacing
                                        + bottomSibling.getMargin().top;
                    }
                    d =
                            Math.min(d, nodePositionsTop[bottomSibling.id]
                                    - nodePositionsTop[node.id] - m);
                }

            }

        }
        if (d == Double.POSITIVE_INFINITY) {

            // list to save the positions of the connected nodes in the next layer for computing the
            // median
            ArrayList<Double> heap = new ArrayList<Double>();

            for (LinearSegment seg : topClasses.get(classId)) {
                for (LNode node : seg.nodes) {

                    for (LEdge edge : node.getIncomingEdges()) {

                        LNode neighbour = edge.getSource().getNode();
                        if (nodeExtensions.get(neighbour.id).classId < classId) {
                            heap.add(nodePositionsTop[neighbour.id] - nodePositionsTop[node.id]);
                        }
                    }
                }
            }
            if (heap.size() == 0) {
                d = 0;
            } else {
                Collections.sort(heap);
                d = heap.get((int) Math.ceil(heap.size() / 2));
            }
        }
        for (LinearSegment seg : topClasses.get(classId)) {
            for (LNode node2 : seg.nodes) {
                nodePositionsTop[node2.id] = nodePositionsTop[node2.id] + d;
            }
        }
    }

    /**
     * computes the linear segments of the layered graph. splits segments to avoid inner segment
     * crossings.
     */
    private void computeLinearSegments() {

        Map<Integer, Boolean> visited = new HashMap<Integer, Boolean>(numberOfNodes);

        // mark all nodes as unvisited
        for (Layer layer : layeredGraph) {
            for (LNode node : layer.getNodes()) {
                visited.put(node.id, false);
            }
        }

        // initialize list of all linear segments
        linearSegments = new LinkedList<LinearSegment>();

        // helper for inner segment crossings,
        // lists of virtual node's segment identifier which successors are also virtual
        // last layer
        List<Integer> lastLayerSegments = new LinkedList<Integer>();
        // current layer
        List<Integer> currentLayerSegments = new LinkedList<Integer>();

        int segCounter = 0;

        for (Layer layer : layeredGraph) {

            lastLayerSegments = currentLayerSegments;

            for (LNode lNode : layer) {
                // if the current node is not visited yet
                if (!visited.get(lNode.id)) {

                    boolean dummy = false;

                    LinearSegment seg = new LinearSegment(segCounter, layer.id);
                    seg.addNode(lNode);
                    visited.put(lNode.id, true);

                    if (isDummy(lNode) && isDummy(successor(lNode))
                            && (successor(lNode).getLayer().id == lNode.getLayer().id + 1)) {

                        currentLayerSegments.add(segCounter);
                        dummy = true;
                    }
                    LNode succ = successor(lNode);
                    while (dummy) {

                        if (isDummy(succ)) {
                            seg.addNode(succ);
                            visited.put(succ.id, true);
                            succ = successor(succ);
                        } else {
                            dummy = false;
                        }
                    }
                    linearSegments.add(seg);
                    segCounter++;
                    // if the current node is already visited
                } else {
                    if (isDummy(lNode)) {

                        if (nodeExtensions.get(lNode.id).segId != lastLayerSegments.get(0)) {
                            // split segment
                            LinearSegment seg2 =
                                    linearSegments.get(nodeExtensions.get(lNode.id).segId).split(
                                            segCounter, layer.id, lNode);
                            linearSegments.add(seg2);
                            segCounter++;
                        }
                        lastLayerSegments.remove(0);
                        if (isDummy(successor(lNode))) {
                            currentLayerSegments.add(nodeExtensions.get(lNode.id).segId);
                        }
                    }

                }
            }
        }
    }

    private LNode successor(final LNode node) {
        LNode successor = null;
        for (LEdge edge : node.getOutgoingEdges()) {
            successor = edge.getTarget().getNode();
        }
        return successor;
    }

    private boolean isDummy(final LNode node) {
        boolean dummy = false;
        if (node.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
            dummy = true;
        }
        return dummy;
    }

    /**
     * divide the graph into classes by traversing each layer downwards.
     */
    private void computeTopClasses() {

        topClasses = new LinkedHashMap<Integer, List<LinearSegment>>();

        for (Layer layer : layeredGraph) {
            int c = layer.id;
            for (LNode lNode : layer) {
                // is the current node already part of a class?
                if (nodeExtensions.get(lNode.id).classId == -1) {

                    linearSegments.get(nodeExtensions.get(lNode.id).segId).computedClassId = c;

                    for (LNode lNode2 : linearSegments.get(nodeExtensions.get(lNode.id).segId).nodes) {
                        nodeExtensions.get(lNode2.id).classId = c;
                    }

                    // insert segment into the current class c
                    List<LinearSegment> currentClass = new LinkedList<LinearSegment>();
                    if (topClasses.get(c) != null) {
                        currentClass = topClasses.get(c);
                    }
                    currentClass.add(linearSegments.get(nodeExtensions.get(lNode.id).segId));
                    topClasses.put(c, currentClass);

                    // node already part of a class
                } else {
                    c = nodeExtensions.get(lNode.id).classId;
                }
            }
        }
    }

    /**
     * divide the graph into classes by traversing each layer upwards.
     */
    private void computeBottomClasses() {

        bottomClasses = new LinkedHashMap<Integer, List<LinearSegment>>();

        // initialize classId
        for (int i : nodeExtensions.keySet()) {
            nodeExtensions.get(i).classId = -1;
        }
        for (int i = 0; i < linearSegments.size(); i++) {
            linearSegments.get(i).computedClassId = -1;
        }

        for (Layer layer : layeredGraph) {
            int c = layer.id;

            for (int i = layer.getNodes().size() - 1; i >= 0; i--) {

                // is node already part of a class?
                if (nodeExtensions.get(layer.getNodes().get(i).id).classId == -1) {

                    // get segment of the current node
                    int segmentId = nodeExtensions.get(layer.getNodes().get(i).id).segId;
                    linearSegments.get(segmentId).computedClassId = c;

                    for (LNode lNode2 : linearSegments.get(segmentId).nodes) {
                        nodeExtensions.get(lNode2.id).classId = c;
                    }

                    // insert Segment into the current class c
                    List<LinearSegment> currentClass = new LinkedList<LinearSegment>();
                    if (bottomClasses.get(c) != null) {
                        currentClass = bottomClasses.get(c);
                    }
                    currentClass.add(linearSegments.get(segmentId));
                    bottomClasses.put(c, currentClass);

                    // node already part of a class
                } else {
                    c = nodeExtensions.get(layer.getNodes().get(i).id).classId;
                }
            }
        }
    }

    /************************************** End**placeVirtual *****************************************/

    /************************************** Begin**placeOriginal **************************************/
    /**
     * placeOriginal minimizes the total length of all outer segments. the positions of virtual
     * nodes are regarded as fixed. Thus the original sequences can be placed independently, wether
     * a dummy node exists between the sequences.
     */
    private void placeOrigninal() {
        /********* Preparations ************************************************/
        // b+
        LNode bPlus = null;
        // b-
        LNode bMinus = null;
        // d
        int[] direction = { 1, -1 };

        // all virtual nodes of the layered graph
        List<LNode> virtuals = new ArrayList<LNode>();
        // sequence of original nodes
        List<LNode> sequence = new ArrayList<LNode>();
        // array D, saves the current direction
        int[] directions;
        // array P
        boolean[] placed;

        // get y-coordinates of all nodes computed by placeVirtual
        nodePositions = new double[numberOfNodes];
        for (Layer layer : layeredGraph) {
            for (LNode lNode : layer) {
                nodePositions[lNode.id] = lNode.getPosition().y;
            }
        }

        // list of all virtuals nodes of the current graph
        for (Layer layer : layeredGraph) {
            for (LNode node : layer) {
                if (node.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {

                    virtuals.add(node);
                }

                // compute minimumdistance between all nodes and their bottom siblings
                LNode sib = nodeExtensions.get(node.id).bottomSibling;
                if (sib != null) {
                    if (node.getProperty(Properties.NODE_TYPE) == NodeType.NORMAL) {
                        nodeExtensions.get(node.id).minDistanceToBottomSibling =
                                node.getSize().y + node.getMargin().bottom + normalSpacing
                                        + sib.getMargin().top;
                    } else {
                        nodeExtensions.get(node.id).minDistanceToBottomSibling =
                                node.getSize().y + node.getMargin().bottom + smallSpacing
                                        + sib.getMargin().top;
                    }
                }
            }
        }
        // initialize array of the possible directions
        directions = new int[numberOfNodes];
        // initialize array to save for all nodes wether they are already placed or not
        placed = new boolean[numberOfNodes];

        for (int i = 0; i < numberOfNodes; i++) {
            directions[i] = 0;
            placed[i] = false;
        }
        /********* end of preparations ************************************************/
        // algorithm
        // at first the algorithm finds the original sequences that can be regarded as placed.
        // It is the case if the distance between the surrounding virtual nodes is already the
        // minimum distance.
        for (int i = 0; i < virtuals.size(); i++) {
            bMinus = virtuals.get(i);
            bPlus = findNextVirtual(bMinus, sequence);

            if (bPlus != null) {
                directions[bMinus.id] = 0;
                if (Math.abs((nodePositions[bPlus.id] - nodePositions[bMinus.id])
                        - minimumDistance(bMinus, bPlus)) < 1) {
                    placed[bMinus.id] = true;
                } else {
                    placed[bMinus.id] = false;
                }
            }
        }
        // traversing direction: 1 means from left to right and -1 means from right to left
        for (int i = 0; i < direction.length; i++) {
            int d = direction[i];
            if (d == 1) {
                for (Layer layer : layeredGraph) {
                    traverseByDirection(sequence, directions, placed, d, layer);
                    if (layer.id < layeredGraph.getLayers().size() - 1) {
                        adjustDirections(layer, d, directions, placed);
                    }
                }
            }
            if (d == -1) {
                for (int j = layeredGraph.getLayers().size() - 1; j >= 0; j--) {
                    traverseByDirection(sequence, directions, placed, d, layeredGraph.getLayers()
                            .get(j));
                    if (layeredGraph.getLayers().get(j).id > 0) {
                        adjustDirections(layeredGraph.getLayers().get(j), d, directions, placed);
                    }
                }
            }
        }
        // set y-coordinates of the original graph
        for (Layer layer : layeredGraph) {
            for (LNode lNode : layer) {
                lNode.getPosition().y = nodePositions[lNode.id];
            }
        }
    }

    /**
     * places all original sequences by traversing the graph in the direction d.
     * 
     * @param sequence
     *            chain of original nodes
     * @param directions
     *            array of all traversing directions
     * @param placed
     *            mark nodes as placed or not
     * @param d
     *            current direction
     * @param layer
     *            current layer
     */
    private void traverseByDirection(final List<LNode> sequence, final int[] directions,
            final boolean[] placed, final int d, final Layer layer) {
        // empty sequence
        sequence.clear();
        // initialize the next bottom virtual node b+
        LNode bPlus = null;
        // initialize top virtual node b-
        LNode bMinus = null;

        for (LNode node : layer) {

            // find the topmost virtual node b-, and add all original nodes below to the current
            // sequence
            if (node.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
                bMinus = node;
                break;
            } else {
                sequence.add(node);
            }
        }
        placeSequence(null, bMinus, d, sequence);
        // save computed positions as minimum distance
        for (LNode node : sequence) {
            LNode bottomSibling = nodeExtensions.get(node.id).bottomSibling;
            if (bottomSibling != null) {
                nodeExtensions.get(node.id).minDistanceToBottomSibling =
                        nodePositions[bottomSibling.id] - nodePositions[node.id];
            }
        }
//        if (bMinus != null && !sequence.isEmpty()) {
//            nodeExtensions.get(sequence.get(sequence.size() - 1).id).minDistanceToBottomSibling =
//                    nodePositions[bMinus.id] - nodePositions[sequence.get(sequence.size() - 1).id];
//        }
        while (bMinus != null) {
            // find the next virtual node in the current layer
            bPlus = findNextVirtual(bMinus, sequence);
            if (bPlus == null) {
                placeSequence(bMinus, null, d, sequence);
                for (LNode node : sequence) {
                    LNode bottomSibling = nodeExtensions.get(node.id).bottomSibling;
                    if (bottomSibling != null) {
                        nodeExtensions.get(node.id).minDistanceToBottomSibling =
                                nodePositions[bottomSibling.id] - nodePositions[node.id];
                    }
                }
                if (bMinus != null && !sequence.isEmpty()) {
                    nodeExtensions.get(bMinus.id).minDistanceToBottomSibling =
                            nodePositions[sequence.get(0).id] - nodePositions[bMinus.id];
                }
            } else if ((bPlus != null) && (directions[bMinus.id] == d)) {
                placeSequence(bMinus, bPlus, d, sequence);
                placed[bMinus.id] = true;
            }
            bMinus = bPlus;
        }
    }

    /**
     * computes the values of the array directions for the next layer. If it sets directions[b-] to
     * d, this forces the method placeOriginal to pace the original sequence while processing the
     * next layer.
     * 
     * @param layer
     *            current layer
     * @param d
     *            current direction
     * @param directions
     *            array of all traversing directions
     * @param placed
     *            mark nodes as placed or not
     */
    private void adjustDirections(final Layer layer, final int d, final int[] directions,
            final boolean[] placed) {

        LNode vMinus = null;
        LNode wMinus = null;
        LNode vPlus = null;
        LNode wPlus = null;

        boolean p = false;

        Layer nextLayer = layeredGraph.getLayers().get(layer.id + d);

        for (LNode node : nextLayer) {
            if (node.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
                // all virtual nodes in layer l+d
                vPlus = node;
                // find all virtual neighbours w in layer l of virtual nodes v in layer l+d
                for (LEdge edge : vPlus.getIncomingEdges()) {

                    if ((edge.getSource().getNode().getLayer().id == layer.id)
                            && (edge.getSource().getNode().getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE)) {
                        wPlus = edge.getSource().getNode();
                        if (vMinus != null) { // && wMinus != null) {
                            p = placed[wMinus.id];

                            for (int i = wMinus.id; i < wPlus.id - 1; i++) {
                                if (nodeExtensions.get(i).bottomSibling != null) {
                                    if (nodeExtensions.get(i).bottomSibling
                                            .getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
                                        LNode w = nodeExtensions.get(i).bottomSibling;
                                        p = (p && placed[w.id]);
                                    }
                                }
                            }
                            if (p) {
                                directions[vMinus.id] = d;
                                // sequence between v- and v+
                                for (int i = vMinus.id; i < vPlus.id - 1; i++) {
                                    if (nodeExtensions.get(i).bottomSibling != null) {
                                        if (nodeExtensions.get(i).bottomSibling
                                                .getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE) {
                                            LNode v = nodeExtensions.get(i).bottomSibling;
                                            directions[v.id] = d;
                                        }
                                    }
                                }
                            }
                        }
                        vMinus = vPlus;
                        wMinus = wPlus;
                    }
                }
            }
        }
    }

    /**
     * places the sequence of original nodes.
     * 
     * @param bMinus
     *            next virtual node above the original sequence
     * @param bPlus
     *            next virtual node below the original sequence
     * @param d
     *            current direction
     * @param sequence
     *            consecutive chain of original nodes
     */
    private void placeSequence(final LNode bMinus, final LNode bPlus, final int d,
            final List<LNode> sequence) {

        if (sequence.size() == 1) {
            placeSingle(bMinus, bPlus, d, sequence.get(0));
        }
        if (sequence.size() > 1) {
            int t = (int) Math.floor(sequence.size() / 2);
            placeSequence(bMinus, bPlus, d, sequence.subList(0, t));
            placeSequence(bMinus, bPlus, d, sequence.subList(t, sequence.size()));
            combineSequences(bMinus, bPlus, d, sequence);
        }
    }

    /**
     * places a single original node. * @param bMinus next virtual node above the original node
     * 
     * @param bPlus
     *            next virtual node below the original node current direction
     * @param node
     *            current original node
     */
    private void placeSingle(final LNode bMinus, final LNode bPlus, final int d, final LNode node) {

        // all neighbours in the previous layer
        List<Double> neighbourPositions = new ArrayList<Double>();
        LNode neighbour = null;

        for (LEdge edge : node.getIncomingEdges()) {
            neighbour = edge.getSource().getNode();

            if (neighbour.getLayer().id == node.getLayer().id - d) {
                neighbourPositions.add(nodePositions[neighbour.id]);
            }
        }

        if (!neighbourPositions.isEmpty()) {
            // compute the median of all neighbours and set the current node to this position
            Collections.sort(neighbourPositions);
            nodePositions[node.id] =
                    neighbourPositions.get((int) Math.ceil(neighbourPositions.size() / 2));

            // attend to the minimum distances
            if (bMinus != null) {
                nodePositions[node.id] =
                        Math.max(nodePositions[node.id], nodePositions[bMinus.id]
                                + minimumDistance(bMinus, node));
            }
            if (bPlus != null) {
                nodePositions[node.id] =
                        Math.min(nodePositions[node.id],
                                nodePositions[bPlus.id] - minimumDistance(node, bPlus));
            }
        }
    }

    /**
     * combines the original sequences and align their position by attending the minimum distances.
     * 
     * @param bMinus
     *            virtual node above the original sequence
     * @param bPlus
     *            virtual node below the original sequence
     * @param d
     *            current direction
     * @param sequence
     *            consecutive chain of original nodes
     */
    private void combineSequences(final LNode bMinus, final LNode bPlus, final int d,
            final List<LNode> sequence) {

        List<Resistance> minusResistance = new LinkedList<Resistance>();
        List<Resistance> plusResistance = new LinkedList<Resistance>();

        // compute the middle of the sequence
        int t = (int) Math.floor(sequence.size() / 2);

        // divide the sequence
        List<LNode> s1 = sequence.subList(0, t);
        List<LNode> s2 = sequence.subList(t, sequence.size());

        // get the middle elements
        LNode vt1 = s1.get(s1.size() - 1);
        LNode vt2 = s2.get(0);

        collectTopChanges(bMinus, bPlus, minusResistance, s1, d);
        // sorted list of Resistances
        Collections.sort(minusResistance);

        collectBottomChanges(bMinus, bPlus, plusResistance, s2, d);
        Collections.sort(plusResistance);

        int rMinus = 0;
        int rPlus = 0;

        double m = minimumDistance(vt1, vt2);

        while ((nodePositions[vt2.id] - nodePositions[vt1.id]) < m) {
            if (rMinus < rPlus) {
                if (minusResistance.isEmpty()) {
                    nodePositions[vt1.id] = nodePositions[vt2.id] - m;
                } else {
                    Resistance r = minusResistance.get(0);
                    minusResistance.remove(0);
                    rMinus = rMinus + r.c;
                    nodePositions[vt1.id] = Math.max(r.position, nodePositions[vt2.id] - m);
                    // Math.max(nodePositions[vt1.id], nodePositions[vt2.id] - m);
                }
            } else {
                if (plusResistance.isEmpty()) {
                    nodePositions[vt2.id] = nodePositions[vt1.id] + m;
                } else {
                    Resistance r = plusResistance.get(plusResistance.size() - 1);
                    plusResistance.remove(plusResistance.size() - 1);
                    rPlus = rPlus + r.c;
                    nodePositions[vt2.id] = Math.min(r.position, nodePositions[vt1.id] + m);
                    // Math.min(nodePositions[vt2.id], nodePositions[vt1.id] + m);
                }
            }
        }
        for (int i = t - 1; i > 0; i--) {
            LNode vi = s1.get(i);
            nodePositions[vi.id] =
                    Math.min(nodePositions[vi.id], nodePositions[vt1.id] - minimumDistance(vi, vt1));
        }
        for (int i = 1; i < s2.size(); i++) {
            LNode vi = s2.get(i);
            nodePositions[vi.id] =
                    Math.max(nodePositions[vi.id], nodePositions[vt2.id] + minimumDistance(vt2, vi));
        }
    }

    /**
     * computes the salti of all rPlus rPlus is the number of all segments getting longer by
     * increasing the position of the current node minus the number of all segments getting shorter.
     * 
     * @param bMinus
     *            virtual node above the original sequence
     * @param bPlus
     *            virtual node below the original sequence
     * @param plusResistance
     *            saves the possible positions of all nodes of s2 and the respectively resistances.
     * @param d
     *            current direction
     * @param s2
     *            the bottom part of the sequence of original nodes
     */
    private void collectBottomChanges(final LNode bMinus, final LNode bPlus,
            final List<Resistance> plusResistance, final List<LNode> s2, final int d) {

        LNode vt2 = s2.get(0);

        for (LNode node : s2) {
            int c = 0;

            List<LNode> neighbours = new ArrayList<LNode>();
            LNode neighbour = null;
            for (LEdge edge : node.getIncomingEdges()) {
                neighbour = edge.getSource().getNode();

                if (d == 1) {
                    if ((neighbour.getLayer().id == node.getLayer().id - 1)
                            && !neighbour.equals(node)) {
                        neighbours.add(neighbour);
                    }
                }
                if (d == -1) {
                    if ((neighbour.getLayer().id == node.getLayer().id + 1)
                            && !neighbour.equals(node)) {
                        neighbours.add(neighbour);
                    }
                }
            }

            for (LNode nNode : neighbours) {
                if (nodePositions[nNode.id] <= nodePositions[node.id]) {
                    c++;
                } else {
                    c--;

                    plusResistance.add(new Resistance(2, nodePositions[nNode.id]
                            - minimumDistance(vt2, node)));
                }
            }
            plusResistance.add(new Resistance(c, nodePositions[node.id]
                    - minimumDistance(vt2, node)));
        }
        if (bPlus != null) {

            plusResistance.add(new Resistance(Integer.MAX_VALUE, nodePositions[bPlus.id]
                    - minimumDistance(vt2, bPlus)));
        }
    }

    /**
     * computes the salti of all rMinus. rMinus is the number of all segments getting longer by
     * decreasing the position of the current node minus the number of all segments getting shorter.
     * 
     * @param bMinus
     *            virtual node above the original sequence
     * @param bPlus
     *            virtual node below the original sequence
     * @param minusResistance
     *            saves the possible positions of all nodes of s1 and the respectively resistances.
     * @param d
     *            current direction
     * @param s1
     *            the top part of the sequence of original nodes
     */
    private void collectTopChanges(final LNode bMinus, final LNode bPlus,
            final List<Resistance> minusResistance, final List<LNode> s1, final int d) {

        LNode vt1 = s1.get(s1.size() - 1);

        for (LNode node : s1) {
            int c = 0;

            List<LNode> neighbours = new ArrayList<LNode>();
            LNode neighbour = null;
            for (LEdge edge : node.getConnectedEdges()) {
                neighbour = edge.getSource().getNode();

                if (d == 1) {
                    if ((neighbour.getLayer().id == node.getLayer().id - 1)
                            && !neighbour.equals(node)) {
                        neighbours.add(neighbour);
                    }
                }
                if (d == -1) {
                    if ((neighbour.getLayer().id == node.getLayer().id + 1)
                            && !neighbour.equals(node)) {
                        neighbours.add(neighbour);
                    }
                }

            }

            for (LNode nNode : neighbours) {
                if (nodePositions[nNode.id] >= nodePositions[node.id]) {
                    c++;
                } else {
                    c--;

                    minusResistance.add(new Resistance(2, nodePositions[nNode.id]
                            + minimumDistance(node, vt1)));
                }
            }
            minusResistance.add(new Resistance(c, nodePositions[node.id]
                    + minimumDistance(node, vt1)));
        }
        if (bMinus != null) {

            minusResistance.add(new Resistance(Integer.MAX_VALUE, nodePositions[bMinus.id]
                    + minimumDistance(bMinus, vt1)));
        }
    }

    /********************* helper placeOriginal(). ********************************************/

    /**
     * Resistance contains a node position and the corresponding resistance.
     * 
     */
    private static class Resistance implements Comparable<Resistance> {

        private int c;
        private double position;

        Resistance(final int c, final double position) {
            this.c = c;
            this.position = position;
        }

        /**
         * {@inheritDoc}
         */
        public int compareTo(final Resistance o) {
            int ret = 0;

            if (this.position < o.position) {
                ret = -1;
            } else if (this.position > o.position) {
                ret = 1;
            }
            return ret;
        }
    }

    /**
     * finds the next virtual node and saves the above consecutive chain of original nodes.
     * 
     * @param node
     * @param sequence
     * @return LNode
     */
    private LNode findNextVirtual(final LNode node, final List<LNode> sequence) {
        sequence.clear();
        LNode nextNode = nodeExtensions.get(node.id).bottomSibling;
        while ((nextNode != null)
                && (nextNode.getProperty(Properties.NODE_TYPE) != NodeType.LONG_EDGE)) {
            // sequence of original nodes
            sequence.add(nextNode);
            nextNode = nodeExtensions.get(nextNode.id).bottomSibling;
        }
        return nextNode;
    }

    /**
     * computes the minimum distance between two nodes.
     * 
     * @param node1
     * @param node2
     * @return double
     */
    private double minimumDistance(final LNode node1, final LNode node2) {
        double m = 0;
        if (node1 != null && node2 != null) { // && (node1.getLayer().id == node2.getLayer().id)) {
            if (node1.id < node2.id) {
                for (int i = node1.id; i < node2.id; i++) {

                    m += nodeExtensions.get(i).minDistanceToBottomSibling;
                }
            } else {
                for (int i = node2.id; i < node1.id; i++) {

                    m += nodeExtensions.get(i).minDistanceToBottomSibling;
                }
            }
        }
        return m;
    }
    /********************* Ende helper placeOriginal() ********************************************/
    /************************************** End**placeOriginal *************************************/
}
