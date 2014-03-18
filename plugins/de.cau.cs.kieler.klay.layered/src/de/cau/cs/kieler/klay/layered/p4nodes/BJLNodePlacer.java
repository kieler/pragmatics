/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 * + Department of Computer Science
 * + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p4nodes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.DebugGraphics;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Node placement implementation inspired by Buchheim, Jünger und Leipert.
 * 
 * <dl>
 * <dt>Precondition:</dt>
 * <dd>the graph has a proper layering with optimized nodes ordering; ports are properly arranged</dd>
 * <dt>Postcondition:</dt>
 * <dd>each node is assigned a vertical coordinate such that no two nodes overlap; the size of each
 * layer is set according to the area occupied by contained nodes; the height of the graph is set to
 * the maximal layer height</dd>
 * </dl>
 * 
 * @author kpe
 */
public final class BJLNodePlacer implements ILayoutPhase {
    /** the current Graph. */
    private LGraph layeredGraph;
    /** total number of nodes of the current graph. */
    private int numberOfNodes;
    /** list of all linear segments of the current graph. */
    private List<LinearSegment> linearSegments;
    /** Map from node IDs to siblings, segments, and classes. */
    private LNodeExtensions[] nodeExtensions;
    /** top classes computed by traversing the graph from left to right and each level downwards. */
    private List<List<LinearSegment>> topClasses;
    /** bottom classes computed by traversing the graph left to right and each level upwards. */
    private List<List<LinearSegment>> bottomClasses;
    /** top layout, topmost y-coordinates computed by placeVirtual. */
    private double[] nodePositionsTop;
    /** bottom layout, bottommost y-coordinates computed by placeVirtual. */
    private double[] nodePositionsBottom;

    /** original layout y-coordinates of each node computed by placeOriginal. */
    // private double[] nodePositions;

    /** distance between regular nodes. */
    private float normalSpacing;
    /** distance between dummy nodes. */
    private float smallSpacing;
    /** minimum y-Coordinate of the graph. */
    private double minY;

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
        initializeIds();
        initializeNodeExtensions();

        // start algorithm
        placeDummy();
        placeRegular();

        postProcess();

        monitor.done();
    }

    // //////////////////////////////////////////////////////////////////////////////////////////////////
    // begin initialization

    /** initialize node id, layer id and number of nodes. */
    private void initializeIds() {
        numberOfNodes = 0;
        int idOfLayer = 0;

        for (Layer layer : layeredGraph) {
            layer.id = idOfLayer;
            idOfLayer++;

            for (LNode node : layer.getNodes()) {
                node.id = numberOfNodes;
                numberOfNodes++;
            }
        }
    }

    /** initialize node extensions, compute all left and right siblings. */
    private void initializeNodeExtensions() {
        // map for all direct siblings
        nodeExtensions = new LNodeExtensions[numberOfNodes];

        for (Layer layer : layeredGraph) {

            // if there is only a single node in the layer
            if (layer.getNodes().size() == 1) {
                // create node extensions and set siblings to null
                LNodeExtensions extendedNode = new LNodeExtensions(null, null, -1, -1);
                nodeExtensions[layer.getNodes().get(0).id] = extendedNode;
            } else {

                LNode topSibling = null;
                LNode currentNode = null;
                LNode bottomSibling = null;
                // mark the first node of a layer
                boolean firstNode = true;

                // the current node is always used as the bottom sibling
                for (LNode node : layer.getNodes()) {

                    // if the node is the first node of the layer
                    if (firstNode) {
                        currentNode = node;
                        firstNode = false;
                    } else {
                        bottomSibling = node;
                        // create node Extensions and save current node with siblings
                        LNodeExtensions extendedNode =
                                new LNodeExtensions(topSibling, bottomSibling, -1, -1);
                        nodeExtensions[currentNode.id] = extendedNode;
                        topSibling = currentNode;
                        currentNode = bottomSibling;
                    }
                }

                // save node extensions of the last node in the current layer
                LNodeExtensions extendedNode = new LNodeExtensions(topSibling, null, -1, -1);
                nodeExtensions[currentNode.id] = extendedNode;
            }
        }
    }

    // end initialization
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // begin placeDummy
    /**
     * place the dummy nodes as close to each other as possible respecting the minimal distance
     * between direct siblings, the given order of the nodes and the straightness of all inner
     * segments of long edges.
     */
    private void placeDummy() {

        computeLinearSegments();
        computePositionTop();
        computePositionBottom();

        minY = 0;
        for (Layer layer : layeredGraph) {
            for (LNode lNode : layer) {
                if (((nodePositionsTop[lNode.id] + nodePositionsBottom[lNode.id]) / 2) < minY) {
                    minY = (nodePositionsTop[lNode.id] + nodePositionsBottom[lNode.id]) / 2;
                }
            }
        }
        // it takes the topmost layout and the bottommost layout and computes for every node
        // the mean y coordinate from both layouts and adjust it to 0
        for (Layer layer : layeredGraph) {
            for (LNode lNode : layer) {
                lNode.getPosition().y =
                        ((nodePositionsTop[lNode.id] + nodePositionsBottom[lNode.id]) / 2)
                                + Math.abs(minY);
            }
        }
    }

    /**
     * computes the bottommost positions of all nodes of the graph.
     */
    private void computePositionBottom() {
        // initialize the bottom positions
        nodePositionsBottom = new double[numberOfNodes];

        // mark all nodes as not placed
        for (int i = 0; i < nodeExtensions.length - 1; i++) {
            nodeExtensions[i].isPlaced = false;
        }

        computeBottomClasses();

        // place all nodes of each class to the lowest position as possible
        for (int i = 0; i < bottomClasses.size(); i++) {
            if (!bottomClasses.get(i).isEmpty()) {
                int classId = i;
                for (LinearSegment seg : bottomClasses.get(classId)) {
                    for (LNode node : seg.nodes) {
                        if (!nodeExtensions[node.id].isPlaced) {
                            placeBottom(node, classId);
                        }
                    }
                }
                adjustBottomClass(classId);
            }
        }
    }

    /**
     * place all nodes of the current node's segment to the bottommost position.
     * 
     * @param node
     * @param classId
     */
    // TODO Documentation missing
    private void placeBottom(final LNode lNode, final int classId) {

        double p = Double.POSITIVE_INFINITY;

        for (LNode node : linearSegments.get(nodeExtensions[lNode.id].segId).nodes) {
            // get the bottom sibling of the current node
            LNode bottomSibling = nodeExtensions[node.id].bottomSibling;
            // Check if the bottom sibling exists and is in the same class as the current node
            if ((bottomSibling != null) && classId == nodeExtensions[bottomSibling.id].classId) {

                // if the bottom sibling wasn't placed yet
                if (!nodeExtensions[bottomSibling.id].isPlaced) {
                    placeBottom(bottomSibling, classId);
                }
                // compute the minimum distance between the current node and his bottom sibling
                double m = 0;
                if (!isDummy(node)) {
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
        // if no bottom sibling of the same class found, set p to 0
        if (p == Double.POSITIVE_INFINITY) {
            p = 0;

        }

        for (LNode node2 : linearSegments.get(nodeExtensions[lNode.id].segId).nodes) {
            nodePositionsBottom[node2.id] = p;
            nodeExtensions[node2.id].isPlaced = true;
        }
    }

    /**
     * align the current class to the previously placed class.
     * 
     * @param classId
     *            identifier value of the current class
     */
    private void adjustBottomClass(final int classId) {
        double d = Double.POSITIVE_INFINITY;
        double m;

        for (LinearSegment seg : bottomClasses.get(classId)) {
            for (LNode node : seg.nodes) {
                // get top sibling of the current node
                LNode topSibling = nodeExtensions[node.id].topSibling;
                // check if the top sibling exists and belongs to a different class as the current
                // node
                if ((topSibling != null) && (nodeExtensions[topSibling.id].classId != classId)) {

                    // minimum distance between the current node and his top sibling
                    m = 0;

                    if (!isDummy(node)) {
                        m =
                                node.getMargin().top + normalSpacing
                                        + topSibling.getMargin().bottom + topSibling.getSize().y;
                    } else {
                        m =
                                node.getMargin().top + smallSpacing + topSibling.getMargin().bottom
                                        + topSibling.getSize().y;
                    }

                    d =
                            Math.min(
                                    d,
                                    ((nodePositionsBottom[node.id] - nodePositionsBottom[topSibling.id]) - m));
                }
            }
        }

        if (d == Double.POSITIVE_INFINITY) {
            // list to save the difference between the y-coordinate of the current node
            // and the connected nodes in the previous layer for computing the median
            ArrayList<Double> distancesToNeighbors = new ArrayList<Double>();

            for (LinearSegment seg : bottomClasses.get(classId)) {
                for (LNode node : seg.nodes) {
                    for (LEdge edge : node.getIncomingEdges()) {

                        LNode neighbour = edge.getSource().getNode();

                        LPort neighborPort = edge.getSource();
                        LPort currentNodePort = edge.getTarget();

                        // check if the connected neighbor belongs to a lower class,
                        // which are always already placed at this moment
                        if (nodeExtensions[neighbour.id].classId < classId) {
                            distancesToNeighbors
                                    .add((nodePositionsBottom[node.id] - currentNodePort
                                            .getPosition().y)
                                            - (nodePositionsBottom[neighbour.id] - neighborPort
                                                    .getPosition().y));
                        }
                    }
                }
            }

            if (distancesToNeighbors.isEmpty()) {
                d = 0;
            } else {
                Collections.sort(distancesToNeighbors);
                d = distancesToNeighbors.get(distancesToNeighbors.size() / 2);
            }
        }

        for (LinearSegment seg : bottomClasses.get(classId)) {
            for (LNode node : seg.nodes) {
                nodePositionsBottom[node.id] = nodePositionsBottom[node.id] - d;
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
        for (int i = 0; i < nodeExtensions.length; i++) {
            nodeExtensions[i].isPlaced = false;
        }

        computeTopClasses();

        for (int i = 0; i < topClasses.size(); i++) {
            if (!topClasses.get(i).isEmpty()) {
                int classId = i;
                for (LinearSegment seg : topClasses.get(classId)) {
                    for (LNode node : seg.nodes) {
                        if (!nodeExtensions[node.id].isPlaced) {
                            placeTop(node, classId);
                        }
                    }
                }
                adjustTopClass(classId);
            }
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
        LinearSegment seg = linearSegments.get(nodeExtensions[lNode.id].segId);

        for (LNode node : seg.nodes) {

            LNode topSibling = nodeExtensions[node.id].topSibling;
            // check if the top sibling exists and belongs to the same class as the current node
            if ((topSibling != null) && classId == nodeExtensions[topSibling.id].classId) {

                if (!nodeExtensions[topSibling.id].isPlaced) {
                    placeTop(topSibling, classId);
                }
                // compute the minimum distance between the current node and his top sibling
                double m = 0;
                if (!isDummy(node)) {

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
        // if no top sibling of the same class found, set p to the initial value 0
        if (p == Double.NEGATIVE_INFINITY) {
            p = 0;
        }
        // set all nodes of the current segment to the same y-coordinate
        for (LNode node2 : seg.nodes) {
            nodePositionsTop[node2.id] = p;
            nodeExtensions[node2.id].isPlaced = true;
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
                LNode bottomSibling = nodeExtensions[node.id].bottomSibling;
                // check if the bottom sibling exists
                // and belongs to a different class as the current node
                if ((bottomSibling != null)
                        && (nodeExtensions[bottomSibling.id].classId != classId)) {

                    // compute the minimum distance between the current node and his bottom sibling
                    m = 0;

                    if (!isDummy(node)) {

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

        // if there's no bottom sibling that belongs to another class
        if (d == Double.POSITIVE_INFINITY) {

            // list to save the positions of the connected nodes in the previous layer for computing
            // the
            // median
            ArrayList<Double> distancesToNeighbors = new ArrayList<Double>();

            for (LinearSegment seg : topClasses.get(classId)) {
                for (LNode node : seg.nodes) {

                    for (LEdge edge : node.getIncomingEdges()) {

                        LNode neighbour = edge.getSource().getNode();

                        LPort neighborPort = edge.getSource();
                        LPort currentNodePort = edge.getTarget();

                        // check if the connected neighbor belongs to a lower class,
                        // which are always already placed at this moment
                        // TODO > oder < ???
                        if (nodeExtensions[neighbour.id].classId < classId) {
                            distancesToNeighbors
                                    .add((nodePositionsTop[neighbour.id] - neighborPort
                                            .getPosition().y)
                                            - (nodePositionsTop[node.id] - currentNodePort
                                                    .getPosition().y));
                        }

                    }
                }
            }
            if (distancesToNeighbors.isEmpty()) {
                d = 0;
            } else {
                Collections.sort(distancesToNeighbors);
                d = distancesToNeighbors.get((distancesToNeighbors.size() / 2));
            }
        }
        for (LinearSegment seg : topClasses.get(classId)) {

            for (LNode node2 : seg.nodes) {
                nodePositionsTop[node2.id] = nodePositionsTop[node2.id] + d;
            }
        }
    }

    /**
     * computes the linear segments of the layered graph.
     * 
     */
    private void computeLinearSegments() {
        int maxSegId = computeSegmentIds();
        // // initialize list of all linear segments
        linearSegments = new ArrayList<LinearSegment>(maxSegId);
        for (int i = 0; i < maxSegId; i++) {
            linearSegments.add(new LinearSegment(i));
        }
        for (Layer layer : layeredGraph) {
            for (LNode node : layer) {
                linearSegments.get(nodeExtensions[node.id].segId).addNode(node);
            }
        }

    }

    /**
     * computes the segmendId of each node returns the number of linear Segments.
     * @return maximum segment id
     */
    private int computeSegmentIds() {
        int segId = 0;

        // helper for inner segment crossings,
        // list of dummy nodes of the last layer which has dummy successors in the current layer
        List<Integer> lastLayerSegments;
        // list of dummy node's segment identifier which successors are also dummy nodes
        List<Integer> currentLayerSegments = new LinkedList<Integer>();

        for (Layer layer : layeredGraph) {
            lastLayerSegments = currentLayerSegments;
            currentLayerSegments = new LinkedList<Integer>();

            for (LNode node : layer) {
                if (!isDummy(node)) {
                    nodeExtensions[node.id].segId = segId;
                    segId++;
                } else {
                    // predecessor in the last layer
                    LNode pred = getFirstPredeccessor(node);
                    // successor in the next layer
                    LNode succ = getFirstsuccessor(node);

                    // check wether the list of dummy nodes which has successors in the currentlayer
                    // is emppty
                    if (!lastLayerSegments.isEmpty()) {
                        if (isDummy(pred) && (pred.getLayer().id < node.getLayer().id)) {
                            if (nodeExtensions[pred.id].segId == lastLayerSegments.get(0)) {
                                nodeExtensions[node.id].segId = nodeExtensions[pred.id].segId;
                                lastLayerSegments.remove(0);
                            } else {
                                lastLayerSegments.remove(0);
                                nodeExtensions[node.id].segId = segId;
                                segId++;
                            }
                        } else {
                            nodeExtensions[node.id].segId = segId;
                            segId++;
                        }
                    } else {
                        nodeExtensions[node.id].segId = segId;
                        segId++;
                    }
                    if (isDummy(succ) && (succ.getLayer().id > node.getLayer().id)) {
                        currentLayerSegments.add(nodeExtensions[node.id].segId);
                    }
                }
            }
        }
        return segId;
    }

    /**
     * @param node
     * @return the first predecessor in the previous layer
     */
    private LNode getFirstPredeccessor(LNode node) {
        LNode predecessor = null;
         for (LEdge edge : node.getIncomingEdges()) {
         if (edge.getSource().getNode().getLayer().id < node.getLayer().id) {
         predecessor = edge.getSource().getNode();
         }
//        for (LEdge edge : node.getConnectedEdges()) {
//            if (edge.getSource().getNode().getLayer().id < node.getLayer().id) {
//                predecessor = edge.getSource().getNode();
//                break;
//            } else if (edge.getSource().getNode().getLayer().id < node.getLayer().id) {
//                predecessor = edge.getTarget().getNode();
//                break;
//            }
        }
        return predecessor;
    }
    /**
     * 
     * @param node
     * @return returns a successor of the current node
     */
    private LNode getFirstsuccessor(final LNode node) {
        LNode successor = null;
        for (LEdge edge : node.getOutgoingEdges()) {
             if (edge.getTarget().getNode().getLayer().id == (node.getLayer().id + 1)) {
             successor = edge.getTarget().getNode();
             }

//            if (edge.getTarget().getNode().getLayer().id > node.getLayer().id) {
//                successor = edge.getSource().getNode();
//                break;
//            } else if (edge.getSource().getNode().getLayer().id > node.getLayer().id) {
//                successor = edge.getTarget().getNode();
//                break;
//            }
        }
        return successor;
    }

    /**
     * @param node
     * @return true if node is a dummy node
     */
    private boolean isDummy(final LNode node) {
        if (node != null) {
            return node.getProperty(Properties.NODE_TYPE) != NodeType.NORMAL;
        } else {
            return false;
        }
    }

    /**
     * divide the graph into classes by traversing each layer downwards.
     */
    private void computeTopClasses() {
        // save the computed topmost classes in the order they are inserted
        topClasses = new ArrayList<List<LinearSegment>>(layeredGraph.getLayers().size());
        for (int i = 0; i < layeredGraph.getLayers().size(); i++) {
            topClasses.add(new LinkedList<LinearSegment>());
        }

        for (Layer layer : layeredGraph) {
            int c = layer.id;
            for (LNode lNode : layer) {
                // is the current node already part of a class?
                if (nodeExtensions[lNode.id].classId == -1) {

                    linearSegments.get(nodeExtensions[lNode.id].segId).computedClassId = c;

                    for (LNode lNode2 : linearSegments.get(nodeExtensions[lNode.id].segId).nodes) {
                        nodeExtensions[lNode2.id].classId = c;
                    }

                    // insert segment into the current class c
                    topClasses.get(c).add(linearSegments.get(nodeExtensions[lNode.id].segId));

                    // node already part of a class
                } else {
                    c = nodeExtensions[lNode.id].classId;
                }
            }
        }
    }

    /**
     * divide the graph into classes by traversing each layer upwards.
     */
    private void computeBottomClasses() {

        bottomClasses = new ArrayList<List<LinearSegment>>(layeredGraph.getLayers().size());
        for (int i = 0; i < layeredGraph.getLayers().size(); i++) {
            bottomClasses.add(new LinkedList<LinearSegment>());
        }

        // initialize all classesIds
        for (int i = 0; i < nodeExtensions.length; i++) {
            nodeExtensions[i].classId = -1;
        }
        for (LinearSegment seg : linearSegments) {
            seg.computedClassId = -1;
        }

        for (Layer layer : layeredGraph) {
            int c = layer.id;

            // for computing the bottommost classes
            // we have to iterate over the nodes in reverse order.
            for (int i = layer.getNodes().size() - 1; i >= 0; i--) {

                // is node already part of a class?
                if (nodeExtensions[layer.getNodes().get(i).id].classId == -1) {

                    // get segment of the current node
                    int segmentId = nodeExtensions[layer.getNodes().get(i).id].segId;
                    linearSegments.get(segmentId).computedClassId = c;

                    for (LNode lNode2 : linearSegments.get(segmentId).nodes) {
                        nodeExtensions[lNode2.id].classId = c;
                    }

                    // insert Segment into the current class c
                    bottomClasses.get(c).add(linearSegments.get(segmentId));

                    // node already part of a class
                } else {
                    c = nodeExtensions[layer.getNodes().get(i).id].classId;
                }
            }
        }
    }

    // end placeDummy
    // ////////////////////////////////////////////////////////////////////////////////////////
    // begin placeRegular
    /**
     * placeRegular minimizes the total length of all outer segments. the positions of dummy nodes
     * are regarded as fixed. Thus the regular sequences can be placed independently, wether a dummy
     * node exists between the sequences.
     */
    private void placeRegular() {
        // Preparations/////////////////////
        // dummy that border a regular sequence on the right side
        LNode bPlus = null;
        // dummy that border a regular sequence on the left side
        LNode bMinus = null;
        // all dummy nodes of the layered graph
        List<LNode> dummies = new ArrayList<LNode>();
        // sequence of regular nodes
        List<LNode> sequenceOfRegulars = new ArrayList<LNode>();
        // saves the current direction of each node
        int[] directions = new int[numberOfNodes];
        // saves for each node if it's already placed
        boolean[] placed = new boolean[numberOfNodes];
        // current traversing direction, regularDirection true means from left to right and false
        // right to left
        boolean regularDirection = true;

        // list of all dummy nodes of the current graph
        for (Layer layer : layeredGraph) {
            for (LNode node : layer) {
                if (isDummy(node)) {
                    dummies.add(node);
                }

                // compute minimum distance between all nodes and their bottom siblings
                LNode sib = nodeExtensions[node.id].bottomSibling;
                if (sib != null) {
                    if (!isDummy(node)) {
                        nodeExtensions[node.id].minDistanceToBottomSibling =
                                node.getSize().y + node.getMargin().bottom + normalSpacing
                                        + sib.getMargin().top;
                    } else {
                        nodeExtensions[node.id].minDistanceToBottomSibling =
                                node.getSize().y + node.getMargin().bottom + smallSpacing
                                        + sib.getMargin().top;
                    }
                }
            }
        }
        // end of preparation/////////////////
        // at first the algorithm finds the regular sequences that can be regarded as placed.
        // It is the case if the distance between the surrounding virtual nodes is already the
        // minimum distance.
        for (int i = 0; i < dummies.size(); i++) {
            // TODO eigentlich überflüssig
            sequenceOfRegulars.clear();
            //
            bMinus = dummies.get(i);
            bPlus = findNextVirtual(bMinus, sequenceOfRegulars);
            if (bPlus != null) {
                directions[bMinus.id] = 0;
                if (Math.abs((bPlus.getPosition().y - bMinus.getPosition().y)
                        - minimumDistance(bMinus, bPlus)) < 5) {
                    placed[bMinus.id] = true;
                } else {
                    placed[bMinus.id] = false;
                }
            }
        }
        // traversing the graph from left to right
        for (Layer layer : layeredGraph) {
            sequenceOfRegulars.clear();
            traverseByDirection(sequenceOfRegulars, directions, placed, 1, layer);
            if (layer.id < layeredGraph.getLayers().size() - 1) {
                adjustDirections(layer, 1, directions, placed);
            }
        }
        // traversing the graph in the reversed order from right to left
        for (int j = layeredGraph.getLayers().size() - 1; j >= 0; j--) {
            sequenceOfRegulars.clear();
            traverseByDirection(sequenceOfRegulars, directions, placed, -1, layeredGraph
                    .getLayers().get(j));
            if (layeredGraph.getLayers().get(j).id > 0) {
                adjustDirections(layeredGraph.getLayers().get(j), -1, directions, placed);
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
            if (!isDummy(node)) {
                sequence.add(node);
            } else {
                bMinus = node;
                break;
            }
        }

        placeSequence(null, bMinus, d, sequence);

        // save computed minimum distance
        for (LNode node : sequence) {
            LNode bottomSibling = nodeExtensions[node.id].bottomSibling;
            if (bottomSibling != null) {
                nodeExtensions[node.id].minDistanceToBottomSibling =
                        Math.max((bottomSibling.getPosition().y - node.getPosition().y),
                                (bottomSibling.getMargin().top + normalSpacing
                                        + node.getMargin().bottom + node.getSize().y));
            }
        }
        if (bMinus != null && !sequence.isEmpty()) {
            nodeExtensions[(sequence.get(sequence.size() - 1).id)].minDistanceToBottomSibling =
                    // bMinus.getPosition().y - sequence.get(sequence.size() - 1).getPosition().y;
                    Math.max(
                            (bMinus.getPosition().y - sequence.get(sequence.size() - 1)
                                    .getPosition().y),
                            (bMinus.getMargin().top + normalSpacing
                                    + sequence.get(sequence.size() - 1).getMargin().bottom + sequence
                                    .get(sequence.size() - 1).getSize().y));
        }
        while (bMinus != null) {
            // find the next virtual node in the current layer
            // and save regular nodes in sequence
            sequence.clear();
            bPlus = findNextVirtual(bMinus, sequence);
            if (bPlus == null) {
                placeSequence(bMinus, null, d, sequence);
                // set the distances computed by placeSequence as the new minimum distances from
                // each node of the sequence to his bottom sibling
                for (LNode node : sequence) {
                    LNode bottomSibling = nodeExtensions[node.id].bottomSibling;
                    if (bottomSibling != null) {
                        nodeExtensions[node.id].minDistanceToBottomSibling =
                                Math.max(
                                        (bottomSibling.getPosition().y - node.getPosition().y),
                                        (bottomSibling.getMargin().top + normalSpacing
                                                + node.getMargin().bottom + node.getSize().y));
                        // bottomSibling.getPosition().y - node.getPosition().y;
                    }
                }
                // bMinus != null &&
                if (!sequence.isEmpty()) {
                    nodeExtensions[bMinus.id].minDistanceToBottomSibling =
                            Math.max(
                                    (sequence.get(0).getPosition().y - bMinus.getPosition().y),
                                    (sequence.get(0).getMargin().top + normalSpacing
                                            + bMinus.getMargin().bottom + bMinus.getSize().y));
                    // sequence.get(0).getPosition().y - bMinus.getPosition().y;
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
            if (isDummy(node)) {
                // all dummy nodes in layer l+d
                vPlus = node;
                // find all dummy neighbors w in layer l of dummy nodes v in layer l+d
                for (LEdge edge : vPlus.getIncomingEdges()) {

                    if ((edge.getSource().getNode().getLayer().id == layer.id)
                            && (isDummy(edge.getSource().getNode()))) {
                        wPlus = edge.getSource().getNode();
                        if (vMinus != null) { // && wMinus != null) {
                            p = placed[wMinus.id];

                            for (int i = wMinus.id; i < wPlus.id - 1; i++) {
                                if (nodeExtensions[i].bottomSibling != null) {
                                    if (isDummy(nodeExtensions[i].bottomSibling)) {
                                        LNode w = nodeExtensions[i].bottomSibling;
                                        p = (p && placed[w.id]);
                                    }
                                }
                            }
                            if (p) {
                                directions[vMinus.id] = d;
                                // sequence between v- and v+
                                for (int i = vMinus.id; i < vPlus.id - 1; i++) {
                                    if (nodeExtensions[i].bottomSibling != null) {
                                        if (isDummy(nodeExtensions[i].bottomSibling)) {
                                            LNode v = nodeExtensions[i].bottomSibling;
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
        } else if (sequence.size() > 1) {
            int t = sequence.size() / 2;
            placeSequence(bMinus, bPlus, d, sequence.subList(0, t));
            placeSequence(bMinus, bPlus, d, sequence.subList(t, sequence.size()));
            combineSequences(bMinus, bPlus, d, sequence);
        }
    }

    /**
     * places a single original node.
     * 
     * @param bMinus
     *            next virtual node above the original node
     * @param bPlus
     *            next virtual node below the original node current direction
     * @param node
     *            current original node
     */
    private void placeSingle(final LNode bMinus, final LNode bPlus, final int d, final LNode node) {

        // all neighbors in the previous layer
        List<LNode> neighbors = new ArrayList<LNode>();

        LNode neighbor = null;

        for (LEdge edge : node.getIncomingEdges()) {
            neighbor = edge.getSource().getNode();

            if (neighbor.getLayer().id == node.getLayer().id - d) {
                neighbors.add(neighbor);
            }
        }
        // compute the median of all neighbors and set the current node to this position
        if (!neighbors.isEmpty()) {
            // sorted list of neighbors
            Collections.sort(neighbors, new NeighborComparator());
            // median node
            LNode neighbor2 = neighbors.get(neighbors.size() / 2);
            double neighbor2Position;

            for (LPort port : neighbor2.getPorts()) {
                for (LPort port2 : port.getConnectedPorts()) {
                    if (port2.getNode().equals(node)) {
                        if (isDummy(neighbor2)) {
                            node.getPosition().y =
                                    neighbor2.getPosition().y - port2.getPosition().y;
                        } else {
                            neighbor2Position = neighbor2.getPosition().y - port.getPosition().y;
                            node.getPosition().y = neighbor2Position + port2.getPosition().y;
                        }
                    }
                }
            }
            // attend to the minimum distances
            if (bMinus != null) {
                node.getPosition().y =
                        Math.max(node.getPosition().y,
                                bMinus.getPosition().y + minimumDistance(bMinus, node));
            }
            if (bPlus != null) {
                node.getPosition().y =
                        Math.min(node.getPosition().y,
                                bPlus.getPosition().y - minimumDistance(node, bPlus));
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
        int t = sequence.size() / 2;

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

        // double m = minimumDistance(vt1, vt2);

        while ((vt2.getPosition().y - vt1.getPosition().y) < minimumDistance(vt1, vt2)) {
            if (rMinus < rPlus) {
                if (minusResistance.isEmpty()) {
                    vt1.getPosition().y = vt2.getPosition().y - minimumDistance(vt1, vt2);
                } else {
                    Resistance resitance = minusResistance.get(0);
                    rMinus = rMinus + resitance.c;
                    vt1.getPosition().y = resitance.position;
                    vt1.getPosition().y =
                            Math.max(vt1.getPosition().y,
                                    vt2.getPosition().y - minimumDistance(vt1, vt2));
                    minusResistance.remove(0);
                }
            } else {
                if (plusResistance.isEmpty()) {
                    vt2.getPosition().y = vt1.getPosition().y + minimumDistance(vt1, vt2);
                } else {
                    Resistance resistance = plusResistance.get(plusResistance.size() - 1);
                    rPlus = rPlus + resistance.c;
                    vt2.getPosition().y = resistance.position;
                    vt2.getPosition().y =
                            Math.min(vt2.getPosition().y,
                                    vt1.getPosition().y + minimumDistance(vt1, vt2));
                    plusResistance.remove(plusResistance.size() - 1);
                }
            }
        }
        for (int i = t - 1; i > 0; i--) {
            LNode vi = s1.get(i);
            vi.getPosition().y =
                    Math.min(vi.getPosition().y, (vt1.getPosition().y - minimumDistance(vi, vt1)));
        }

        for (int i = 1; i < s2.size(); i++) {
            LNode vi = s2.get(i);
            vi.getPosition().y =
                    Math.max(vi.getPosition().y, (vt2.getPosition().y + minimumDistance(vt2, vi)));
        }
    }

    /**
     * computes the results of all rPlus. rPlus is the number of all segments getting longer by
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
                } else if (d == -1) {
                    if ((neighbour.getLayer().id == node.getLayer().id + 1)
                            && !neighbour.equals(node)) {
                        neighbours.add(neighbour);
                    }
                }
            }

            for (LNode nNode : neighbours) {
                if (nNode.getPosition().y <= node.getPosition().y) {
                    c++;
                } else {
                    c--;
                    plusResistance.add(new Resistance(2, nNode.getPosition().y
                            - minimumDistance(vt2, node)));
                }
            }
            plusResistance
                    .add(new Resistance(c, node.getPosition().y - minimumDistance(vt2, node)));
        }
        if (bPlus != null) {
            plusResistance.add(new Resistance(Integer.MAX_VALUE, bPlus.getPosition().y
                    - minimumDistance(vt2, bPlus)));
        }
    }

    /**
     * computes the results of all rMinus. rMinus is the number of all segments getting longer by
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

            List<LNode> neighbors = new ArrayList<LNode>();
            LNode neighbour = null;
            for (LEdge edge : node.getConnectedEdges()) {
                neighbour = edge.getSource().getNode();

                if (d == 1) {
                    if ((neighbour.getLayer().id == node.getLayer().id - 1)
                            && !neighbour.equals(node)) {
                        neighbors.add(neighbour);
                    }
                } else if (d == -1) {
                    if ((neighbour.getLayer().id == node.getLayer().id + 1)
                            && !neighbour.equals(node)) {
                        neighbors.add(neighbour);
                    }
                }

            }

            for (LNode nNode : neighbors) {
                if (nNode.getPosition().y >= node.getPosition().y) {
                    c++;
                } else {
                    c--;
                    minusResistance.add(new Resistance(2, nNode.getPosition().y
                            + minimumDistance(node, vt1)));
                }
            }
            minusResistance
                    .add(new Resistance(c, node.getPosition().y + minimumDistance(node, vt1)));
        }
        if (bMinus != null) {

            minusResistance.add(new Resistance(Integer.MAX_VALUE, bMinus.getPosition().y
                    + minimumDistance(bMinus, vt1)));
        }
    }

    // //////////////////////////////////////////////////////////////////////////////////////////
    // helper placeRegular
    /**
     * finds the next dummy node and saves the above consecutive chain of original nodes.
     * 
     * @param node
     * @param sequence
     * @return LNode
     */
    private LNode findNextVirtual(final LNode node, final List<LNode> sequence) {
        sequence.clear();
        LNode nextNode = nodeExtensions[node.id].bottomSibling;
        while ((nextNode != null) && (!isDummy(nextNode))) {
            // sequence of regular nodes
            sequence.add(nextNode);
            nextNode = nodeExtensions[nextNode.id].bottomSibling;
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
        assert node1.getLayer().id == node2.getLayer().id;

        double m = 0;
        if (node1 != null && node2 != null) {
            if (node1.id < node2.id) {
                LNode node = node1;
                for (int i = node1.id; i < node2.id; i++) {
                    m +=
                            Math.max(
                                    (nodeExtensions[i].minDistanceToBottomSibling),
                                    (node.getSize().y + node.getMargin().bottom + normalSpacing + nodeExtensions[i].bottomSibling
                                            .getMargin().top));
                    node = nodeExtensions[i].bottomSibling;
                }
            } else {
                LNode node = node2;
                for (int i = node2.id; i < node1.id; i++) {

                    m +=
                            Math.max(
                                    (nodeExtensions[i].minDistanceToBottomSibling),
                                    (node.getSize().y + node.getMargin().bottom + normalSpacing + nodeExtensions[i].bottomSibling
                                            .getMargin().top));
                    node = nodeExtensions[i].bottomSibling;
                }
            }
        }
        return m;
    }

    // end placeRegular
    // //////////////////////////////////////////////////////////////////////////////////////////////
    // helper classes
    /**
     * Node Extension contains additional informations about a node.
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
     * A linear segment contains a single regular node or all dummy nodes of a long edge.
     */
    private final class LinearSegment {
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
        private LinearSegment(final int segId) {// , final int layerId) {
            this.id = segId;
            // this.layerId = layerId;
            this.computedClassId = -1;
            this.nodes = new LinkedList<LNode>();
        }

        /**
         * Splits this linear segment before the given node. All nodes from the given node onward
         * are moved to the new segment.
         * 
         * @param id2
         *            the new segments identifier value
         * @param layer
         *            the layer identifier of the first node of the new segment
         * @param node
         *            node to split the segment at
         * @return new computed linear segment
         */
        public LinearSegment split(final int id2, /* final int layer */final LNode node) {
            int nodeIndex = this.getIndexOf(node);
            LinearSegment newSeg = new LinearSegment(id2);// , layer);

            ListIterator<LNode> iterator = nodes.listIterator(nodeIndex);
            while (iterator.hasNext()) {
                LNode movedNode = iterator.next();
                newSeg.addNode(movedNode);
                iterator.remove();
            }
            return newSeg;
        }

        /**
         * add a node to the end of the node list of this segment and set the segment id in the node
         * extension.
         * 
         * @param node
         *            node to add
         */
        public void addNode(final LNode node) {
            this.nodes.add(node);
            nodeExtensions[node.id].segId = this.id;
        }

        /**
         * returns the position of the node in this segment.
         * 
         * @param node
         *            node of the segment to get the index of
         * @return index of the node
         */
        public int getIndexOf(final LNode node) {
            return nodes.indexOf(node);
        }
    }

    /**
     * Resistance contains a node position and the corresponding resistance.
     * 
     */
    private static final class Resistance implements Comparable<Resistance> {
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
            return (int) (this.position - o.position);
        }
    }

    /**
     * Comparator which determines the order of nodes in a layer.
     */
    private static class NeighborComparator implements Comparator<LNode>, Serializable {

        private static final long serialVersionUID = 8508550732869672955L;

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

    // adjust the linear segments to minimize edge bends
    private void postProcess() {

        // traverse all linear segment independently
        for (LinearSegment segment : linearSegments) {
            double minSpaceAbove = Integer.MAX_VALUE, minSpaceBelow = Integer.MAX_VALUE;

            for (LNode node : segment.nodes) {
                double spaceAbove, spaceBelow;
                int index = node.getIndex();

                // compute the distance between the linear segment and all direct siblings above
                LNode topSibling = nodeExtensions[node.id].topSibling;
                if (topSibling != null) {
                    float spacing =
                            !isDummy(node) && !isDummy(topSibling) ? normalSpacing : smallSpacing;
                    spaceAbove =
                            node.getPosition().y
                                    - node.getMargin().top
                                    - (topSibling.getPosition().y + topSibling.getSize().y
                                            + topSibling.getMargin().bottom + spacing);
                } else {
                    spaceAbove = node.getPosition().y - node.getMargin().top;
                }
                minSpaceAbove = Math.min(spaceAbove, minSpaceAbove);

                // compute the distance between the linear segment and all direct siblings below
                LNode bottomSibling = nodeExtensions[node.id].bottomSibling;
                if (bottomSibling != null) {
                    float spacing =
                            !isDummy(node) && !isDummy(bottomSibling) ? normalSpacing
                                    : smallSpacing;
                    spaceBelow =
                            bottomSibling.getPosition().y
                                    - bottomSibling.getMargin().top
                                    - (node.getPosition().y + node.getSize().y
                                            + node.getMargin().bottom + spacing);
                } else {
                    spaceBelow = 2 * node.getPosition().y;
                }
                minSpaceBelow = Math.min(spaceBelow, minSpaceBelow);
            }

            double minDisplaceSegment = Integer.MAX_VALUE;
            boolean foundPosition = false;

            // determine the minimal displacement that would make one incoming edge straight
            LNode firstNode = segment.nodes.get(0);
            for (LPort target : firstNode.getPorts()) {
                double pos =
                        firstNode.getPosition().y + target.getPosition().y + target.getAnchor().y;
                for (LEdge edge : target.getIncomingEdges()) {
                    LPort source = edge.getSource();
                    double d =
                            source.getNode().getPosition().y + source.getPosition().y
                                    + source.getAnchor().y - pos;
                    if (Math.abs(d) < Math.abs(minDisplaceSegment)
                            && Math.abs(d) < (d < 0 ? minSpaceAbove : minSpaceBelow)) {
                        minDisplaceSegment = d;
                        foundPosition = true;
                    }
                }
            }

            // determine the minimal displacement that would make one outgoing edge straight
            LNode lastNode = segment.nodes.get(segment.nodes.size() - 1);
            for (LPort source : lastNode.getPorts()) {
                double pos =
                        lastNode.getPosition().y + source.getPosition().y + source.getAnchor().y;
                for (LEdge edge : source.getOutgoingEdges()) {
                    LPort target = edge.getTarget();
                    double d =
                            target.getNode().getPosition().y + target.getPosition().y
                                    + target.getAnchor().y - pos;
                    if (Math.abs(d) < Math.abs(minDisplaceSegment)
                            && Math.abs(d) < (d < 0 ? minSpaceAbove : minSpaceBelow)) {
                        minDisplaceSegment = d;
                        foundPosition = true;
                    }
                }
            }

            // if such a displacement could be found, apply it to the whole linear segment
            if (foundPosition && minDisplaceSegment != 0) {
                for (LNode node : segment.nodes) {
                    node.getPosition().y += minDisplaceSegment;
                }
            }
        }
    }
}
