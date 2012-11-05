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
package de.cau.cs.kieler.papyrus.sequence;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.papyrus.sequence.graph.SComment;
import de.cau.cs.kieler.papyrus.sequence.graph.SGraph;
import de.cau.cs.kieler.papyrus.sequence.graph.SGraphElement;
import de.cau.cs.kieler.papyrus.sequence.graph.SLifeline;
import de.cau.cs.kieler.papyrus.sequence.graph.SMessage;
import de.cau.cs.kieler.papyrus.PapyrusProperties;
import de.cau.cs.kieler.papyrus.SequenceArea;
import de.cau.cs.kieler.papyrus.SequenceExecution;

/**
 * Importer class that converts the KGraph into a SGraph and builds the LayeredGraph out of the
 * SGraph.
 * 
 * @author grh
 * 
 */
public class SGraphImporter {

    /**
     * Just needed for a workaround.
     */
    private static final int TWENTY = 20;

    /**
     * The map that stores which messages belongs to which node in the layered graph.
     */
    private HashMap<SMessage, LNode> layeredMap;

    /**
     * Builds a PGraph out of a given KGraph by associating every KNode to a PLifeline and every
     * KEdge to a PMessage.
     * 
     * @param topNode
     *            the KGraphElement, that holds the nodes
     * @return the built PGraph
     */
    public SGraph importGraph(final KNode topNode) {
        // Create a graph object
        SGraph sgraph = new SGraph();
        // Initialize node-lifeline and edge-message maps
        HashMap<KNode, SLifeline> nodeMap = Maps.newHashMap();
        HashMap<KEdge, SMessage> edgeMap = Maps.newHashMap();
        // Get the list of areas
        List<SequenceArea> areas = topNode.getData(KShapeLayout.class).getProperty(
                PapyrusProperties.AREAS);

        // Create lifeline objects
        for (KNode node : topNode.getChildren()) {
            createLifeline(sgraph, nodeMap, node);
        }

        // Walk through lifelines (create their messages) and comments
        for (KNode node : topNode.getChildren()) {
            String nodeType = node.getData(KShapeLayout.class).getProperty(
                    PapyrusProperties.NODE_TYPE);
            if (nodeType.equals("2001")) {
                // Node is surrounding interaction
                continue;
            } else if (nodeType.equals("3001")) {
                // Node is a lifeline

                // Create SMessages for each of the outgoing edges
                createMessages(sgraph, nodeMap, edgeMap, areas, node);

                // Handle found messages (incoming messages)
                createFoundMessages(sgraph, nodeMap, edgeMap, node);

            } else if (nodeType.equals("3009") || nodeType.equals("3008")
                    || nodeType.equals("3024") || nodeType.equals("3020")) {
                // Node is comment or constraint

                createComment(sgraph, nodeMap, edgeMap, node);
            }
        }

        // Check areas that have no messages in it
        if (areas != null) {
            for (SequenceArea area : areas) {
                if (area.getMessages().size() == 0) {
                    handleEmptyArea(sgraph, area);
                }
            }
        }

        // Reset graph size to zero before layouting
        sgraph.setSizeX(0);
        sgraph.setSizeY(0);
        
        // Copy the areas property to the SGraph
        sgraph.setProperty(PapyrusProperties.AREAS, areas);
        
        return sgraph;
    }

    /**
     * Builds a layered graph that contains every message as a node. Edges are representations of
     * the relative order of the messages.
     * 
     * @param sgraph
     *            the given SGraph
     * @return the layeredGraph
     */
    public LGraph createLayeredGraph(final SGraph sgraph) {
        LGraph lgraph = new LGraph();

        layeredMap = Maps.newHashMap();

        // Build a node for every message.
        int i = 0;
        for (SLifeline lifeline : sgraph.getLifelines()) {
            for (SMessage message : lifeline.getOutgoingMessages()) {
                LNode node = new LNode(lgraph);
                node.getLabels().add(new LLabel(lgraph, "Node" + i++));
                node.setProperty(Properties.ORIGIN, message);
                layeredMap.put(message, node);
                lgraph.getLayerlessNodes().add(node);
            }
            // Handle found messages (they have no source lifeline)
            for (SMessage message : lifeline.getIncomingMessages()) {
                if (message.getProperty(SeqProperties.MESSAGE_TYPE) == MessageType.FOUND) {
                    LNode node = new LNode(lgraph);
                    node.getLabels().add(new LLabel(lgraph, "Node" + i++));
                    node.setProperty(Properties.ORIGIN, message);
                    layeredMap.put(message, node);
                    lgraph.getLayerlessNodes().add(node);
                }
            }
        }

        // Add an edge for every neighbored pair of messages at every lifeline
        // indicating the relative order of the messages.
        for (SLifeline lifeline : sgraph.getLifelines()) {
            List<SMessage> messages = lifeline.getMessagesSorted();
            for (int j = 1; j < messages.size(); j++) {
                // Add an edge from the node belonging to message j-1 to the node belonging to
                // message j
                LNode sourceNode = layeredMap.get(messages.get(j - 1));
                LNode targetNode = layeredMap.get(messages.get(j));
                if (sourceNode != targetNode) {
                    LPort sourcePort = new LPort(lgraph);
                    sourcePort.setNode(sourceNode);
                    LPort targetPort = new LPort(lgraph);
                    targetPort.setNode(targetNode);
                    LEdge edge = new LEdge(lgraph);
                    edge.setSource(sourcePort);
                    edge.setTarget(targetPort);
                    edge.setProperty(SeqProperties.BELONGS_TO_LIFELINE, lifeline);
                }
            }
        }

        // Add dummy nodes before the first messages of combined fragments to have enough space
        // above the topmost message of the area
        List<SequenceArea> areas = sgraph.getProperty(PapyrusProperties.AREAS);
        if (areas != null) {
            for (SequenceArea area : areas) {
                if (area.getSubAreas().size() > 0) {
                    LNode dummyNode = new LNode(lgraph);
                    LPort dummyPortIn = new LPort(lgraph);
                    LPort dummyPortOut = new LPort(lgraph);
                    dummyPortIn.setNode(dummyNode);
                    dummyPortOut.setNode(dummyNode);

                    float minYPos = Float.MAX_VALUE;
                    SMessage uppermostMessage = null;
                    for (Object messageObj : area.getMessages()) {
                        SMessage message = (SMessage) messageObj;
                        if (message.getSourceYPos() < minYPos) {
                            minYPos = message.getSourceYPos();
                            uppermostMessage = message;
                        }
                    }
                    if (uppermostMessage != null) {
                        LNode node = layeredMap.get(uppermostMessage);
                        LPort port = new LPort(lgraph);
                        port.setNode(node);

                        // Copy list in order to avoid concurrent modification
                        List<LEdge> incomingEdges = new LinkedList<LEdge>();
                        for (LEdge edge : node.getIncomingEdges()) {
                            incomingEdges.add(edge);
                        }
                        for (LEdge edge : incomingEdges) {
                            edge.setTarget(dummyPortIn);
                        }
                        LEdge edge = new LEdge(lgraph);
                        edge.setSource(dummyPortOut);
                        edge.setTarget(port);
                        lgraph.getLayerlessNodes().add(dummyNode);
                    }
                }
            }
        }

        return lgraph;
    }

    /**
     * Gets the map of message's and node's relations.
     * 
     * @return the HashMap
     */
    public HashMap<SMessage, LNode> getLayeredMap() {
        return layeredMap;
    }

    /**
     * Create a comment object for comments or constraints.
     * 
     * @param sgraph
     *            the Sequence Graph
     * @param nodeMap
     *            the map of node-lifeline connections
     * @param edgeMap
     *            the map of edge-message connections
     * @param node
     *            the node to create a comment object from
     */
    private void createComment(final SGraph sgraph, final HashMap<KNode, SLifeline> nodeMap,
            final HashMap<KEdge, SMessage> edgeMap, final KNode node) {

        // Get the node's type
        String nodeType = node.getData(KShapeLayout.class).getProperty(PapyrusProperties.NODE_TYPE);

        // Create comment object
        KShapeLayout commentLayout = node.getData(KShapeLayout.class);

        SComment comment = new SComment(node);
        comment.setProperty(PapyrusProperties.NODE_TYPE, nodeType);
        String attachedElement = commentLayout.getProperty(PapyrusProperties.ATTACHED_ELEMENT);
        comment.setProperty(PapyrusProperties.ATTACHED_ELEMENT, attachedElement);
        // Attach connected edge to comment
        if (!node.getOutgoingEdges().isEmpty()) {
            comment.setConnection(node.getOutgoingEdges().get(0));
        }

        List<Object> attachedTo = commentLayout.getProperty(PapyrusProperties.ATTACHED_TO);
        if (attachedTo != null) {
            List<SGraphElement> attTo = new LinkedList<SGraphElement>();
            for (Object att : attachedTo) {
                if (att instanceof KNode) {
                    attTo.add(nodeMap.get(att));
                } else if (att instanceof KEdge) {
                    attTo.add(edgeMap.get(att));
                }
            }
            comment.getAttachedTo().addAll(attTo);
        }

        comment.setProperty(PapyrusProperties.NODE_TYPE, nodeType);

        comment.setxPos(commentLayout.getXpos());
        comment.setyPos(commentLayout.getYpos());
        comment.setWidth(commentLayout.getWidth());
        comment.setHeight(commentLayout.getHeight());
        if (nodeType.equals("3020")) {
            comment.setWidth(TWENTY); // FIXME workaround for time observations to have a width
        }
        sgraph.getComments().add(comment);
    }

    /**
     * Initialize empty area.
     * 
     * @param sgraph
     *            the Sequence Graph
     * @param area
     *            the area
     */
    private void handleEmptyArea(final SGraph sgraph, final SequenceArea area) {
        // Check which lifelines are involved
        for (SLifeline lifeline : sgraph.getLifelines()) {
            if (lifelineIsContained(lifeline, area)) {
                area.addLifeline(lifeline);
            }
        }

        float lowerEnd = area.getyPos() + area.getHeight();
        SMessage nextMessage = null;
        float uppermostPosition = Float.MAX_VALUE;
        // Check which message is the next one below the area
        for (Object lifelineObj : area.getLifelines()) {
            SLifeline lifeline = (SLifeline) lifelineObj;
            for (SMessage message : lifeline.getIncomingMessages()) {
                Object originObj = message.getProperty(Properties.ORIGIN);
                if (originObj instanceof KEdge) {
                    KEdge edge = (KEdge) originObj;
                    KEdgeLayout layout = edge.getData(KEdgeLayout.class);
                    float yPos = layout.getTargetPoint().getY();
                    if (yPos > lowerEnd && yPos < uppermostPosition) {
                        nextMessage = message;
                        uppermostPosition = yPos;
                    }
                }
            }
            for (SMessage message : lifeline.getOutgoingMessages()) {
                Object originObj = message.getProperty(Properties.ORIGIN);
                if (originObj instanceof KEdge) {
                    KEdge edge = (KEdge) originObj;
                    KEdgeLayout layout = edge.getData(KEdgeLayout.class);
                    float yPos = layout.getSourcePoint().getY();
                    if (yPos > lowerEnd && yPos < uppermostPosition) {
                        nextMessage = message;
                        uppermostPosition = yPos;
                    }
                }
            }
            if (nextMessage != null) {
                area.setNextMessage(nextMessage);
            }
        }
    }

    /**
     * Walk through the node's outgoing edges and create SMessages for each of them.
     * 
     * @param sgraph
     *            the Sequence Graph
     * @param nodeMap
     *            the map of node-lifeline connections
     * @param edgeMap
     *            the map of edge-message connections
     * @param areas
     *            the list of areas
     * @param node
     *            the KNode to search its outgoing edges
     */
    private void createMessages(final SGraph sgraph, final HashMap<KNode, SLifeline> nodeMap,
            final HashMap<KEdge, SMessage> edgeMap, final List<SequenceArea> areas, final KNode node) {
        for (KEdge edge : node.getOutgoingEdges()) {
            SLifeline sourceLL = nodeMap.get(edge.getSource());
            if (sourceLL == null) {
                SLifeline dummy = new SLifeline();
                dummy.setName("DummyLifeline");
                dummy.setGraph(sgraph);
                sourceLL = dummy;
            }
            SLifeline targetLL = nodeMap.get(edge.getTarget());
            if (targetLL == null) {
                SLifeline dummy = new SLifeline();
                dummy.setName("DummyLifeline");
                dummy.setGraph(sgraph);
                targetLL = dummy;
            }
            SMessage message = new SMessage(sourceLL, targetLL);
            message.setProperty(Properties.ORIGIN, edge);
            message.setProperty(SeqProperties.COMMENTS, new LinkedList<SComment>());

            // Put edge and message into the edge map
            edgeMap.put(edge, message);

            // replace KEdge by its SMessage if it appears in one of the lifeline's
            // executions
            if (sourceLL.getProperty(PapyrusProperties.EXECUTIONS) != null) {
                for (SequenceExecution execution : sourceLL
                        .getProperty(PapyrusProperties.EXECUTIONS)) {
                    if (execution.getMessages().remove(edge)) {
                        execution.addMessage(message);
                    }
                }
            }
            if (targetLL.getProperty(PapyrusProperties.EXECUTIONS) != null) {
                for (SequenceExecution execution : targetLL
                        .getProperty(PapyrusProperties.EXECUTIONS)) {
                    if (execution.getMessages().remove(edge)) {
                        execution.addMessage(message);
                    }
                }
            }

            KEdgeLayout layout = edge.getData(KEdgeLayout.class);

            // Append the message type of the edge to the message
            String messageType = layout.getProperty(PapyrusProperties.MESSAGE_TYPE);
            if (messageType.equals("4004")) {
                message.setProperty(SeqProperties.MESSAGE_TYPE, MessageType.ASYNCHRONOUS);
            } else if (messageType.equals("4006")) {
                message.setProperty(SeqProperties.MESSAGE_TYPE, MessageType.CREATE);
            } else if (messageType.equals("4007")) {
                message.setProperty(SeqProperties.MESSAGE_TYPE, MessageType.DELETE);
            } else if (messageType.equals("4003")) {
                message.setProperty(SeqProperties.MESSAGE_TYPE, MessageType.SYNCHRONOUS);
            } else if (messageType.equals("4008")) {
                message.setProperty(SeqProperties.MESSAGE_TYPE, MessageType.LOST);
            }

            message.setSourceYPos(layout.getSourcePoint().getY());
            message.setTargetYPos(layout.getTargetPoint().getY());

            // check if message is in any area
            if (areas != null) {
                for (SequenceArea area : areas) {
                    if (isInArea(layout.getSourcePoint(), area)
                            && isInArea(layout.getTargetPoint(), area)) {
                        area.getMessages().add(message);
                        area.addLifeline(message.getSource());
                        area.addLifeline(message.getTarget());
                        for (SequenceArea subArea : area.getSubAreas()) {
                            if (isInArea(layout.getSourcePoint(), subArea)
                                    && isInArea(layout.getTargetPoint(), subArea)) {
                                subArea.getMessages().add(message);
                                subArea.addLifeline(message.getSource());
                                subArea.addLifeline(message.getTarget());
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Walk through incoming edges and check if there are found messages. If so, create the
     * corresponding SMessage.
     * 
     * @param sgraph
     *            the Sequence Graph
     * @param nodeMap
     *            the map of node-lifeline connections
     * @param edgeMap
     *            the map of edge-message connections
     * @param node
     *            the KNode to search its incoming edges.
     */
    private void createFoundMessages(final SGraph sgraph, final HashMap<KNode, SLifeline> nodeMap,
            final HashMap<KEdge, SMessage> edgeMap, final KNode node) {
        for (KEdge edge : node.getIncomingEdges()) {
            KEdgeLayout layout = edge.getData(KEdgeLayout.class);
            // Append the message type of the edge to the message
            String messageType = layout.getProperty(PapyrusProperties.MESSAGE_TYPE);
            if (messageType.equals("4009")) {
                SLifeline sourceLL = nodeMap.get(edge.getSource());
                if (sourceLL == null) {
                    SLifeline dummy = new SLifeline();
                    dummy.setName("DummyLifeline");
                    dummy.setGraph(sgraph);
                    sourceLL = dummy;
                }
                SLifeline targetLL = nodeMap.get(edge.getTarget());
                if (targetLL == null) {
                    SLifeline dummy = new SLifeline();
                    dummy.setName("DummyLifeline");
                    dummy.setGraph(sgraph);
                    targetLL = dummy;
                }
                SMessage message = new SMessage(sourceLL, targetLL);
                message.setProperty(Properties.ORIGIN, edge);
                message.setProperty(SeqProperties.COMMENTS, new LinkedList<SComment>());

                // Put edge and message into the edge map
                edgeMap.put(edge, message);

                message.setProperty(SeqProperties.MESSAGE_TYPE, MessageType.FOUND);

                // replace KEdge by its SMessage if it appears in one of the lifeline's
                // executions
                if (sourceLL.getProperty(PapyrusProperties.EXECUTIONS) != null) {
                    for (SequenceExecution execution : sourceLL
                            .getProperty(PapyrusProperties.EXECUTIONS)) {
                        if (execution.getMessages().remove(edge)) {
                            execution.addMessage(message);
                        }
                    }
                }
                if (targetLL.getProperty(PapyrusProperties.EXECUTIONS) != null) {
                    for (SequenceExecution execution : targetLL
                            .getProperty(PapyrusProperties.EXECUTIONS)) {
                        if (execution.getMessages().remove(edge)) {
                            execution.addMessage(message);
                        }
                    }
                }
                message.setTargetYPos(layout.getTargetPoint().getY());
                message.setTargetYPos(layout.getTargetPoint().getY());
            }
        }
    }

    /**
     * Creates the SLifeline for the KNode and copies its properties.
     * 
     * @param sgraph
     *            the Sequence Graph
     * @param nodeMap
     *            the map of node-lifeline connections
     * @param node
     *            the KNode to create a lifeline for
     */
    private void createLifeline(final SGraph sgraph, final HashMap<KNode, SLifeline> nodeMap,
            final KNode node) {
        String nodeType = node.getData(KShapeLayout.class).getProperty(PapyrusProperties.NODE_TYPE);
        if (nodeType.equals("3001")) {
            // Node is lifeline
            SLifeline lifeline = new SLifeline();
            if (node.getLabels().size() > 0) {
                lifeline.setName(node.getLabels().get(0).getText());
            }
            lifeline.setProperty(Properties.ORIGIN, node);
            nodeMap.put(node, lifeline);
            sgraph.addLifeline(lifeline);

            // Copy executions to lifeline
            List<SequenceExecution> executions = node.getData(KShapeLayout.class).getProperty(
                    PapyrusProperties.EXECUTIONS);
            lifeline.setProperty(PapyrusProperties.EXECUTIONS, executions);

            lifeline.setProperty(SeqProperties.COMMENTS, new LinkedList<SComment>());

            // Copy destruction to lifeline
            KNode destruction = node.getData(KShapeLayout.class).getProperty(
                    PapyrusProperties.DESTRUCTION);
            if (destruction != null) {
                lifeline.setDestruction(destruction);
            }
        }
    }

    /**
     * Checks, if a given lifeline's center is horizontally within an area.
     * 
     * @param lifeline
     *            the lifeline
     * @param area
     *            the area
     * @return true, if the lifeline is inside the area, false otherwise
     */
    private boolean lifelineIsContained(final SLifeline lifeline, final SequenceArea area) {
        KNode node = (KNode) lifeline.getProperty(Properties.ORIGIN);
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        float lifelineCenter = nodeLayout.getXpos() + nodeLayout.getWidth() / 2;
        float leftEnd = area.getxPos();
        float rightEnd = area.getxPos() + area.getWidth();

        return (lifelineCenter >= leftEnd && lifelineCenter <= rightEnd);
    }

    /**
     * Checks if a given KPoint is inside the borders of a given SequenceArea.
     * 
     * @param point
     *            the KPoint
     * @param area
     *            the SequenceArea
     * @return true if the point is inside the area
     */
    private boolean isInArea(final KPoint point, final SequenceArea area) {
        if (point.getX() < area.getxPos()) {
            return false;
        }
        if (point.getX() > area.getxPos() + area.getWidth()) {
            return false;
        }
        if (point.getY() < area.getyPos()) {
            return false;
        }
        if (point.getY() > area.getyPos() + area.getHeight()) {
            return false;
        }
        return true;
    }
}
