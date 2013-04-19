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

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
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
 * @kieler.design 2012-11-20 cds, msp
 * @kieler.rating proposed yellow grh
 * 
 */
public class SGraphImporter {
    /**
     * Builds a PGraph out of a given KGraph by associating every KNode to a PLifeline and every
     * KEdge to a PMessage.
     * 
     * @param topNode
     *            the KGraphElement, that holds the nodes
     * @param progressMonitor
     *            the progress monitor
     * @return the built PGraph
     */
    public SGraph importGraph(final KNode topNode, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Import graph", 1);

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
            if (nodeType.equals("3001")) {
                // Node is a lifeline

                // Create SMessages for each of the outgoing edges
                createMessages(sgraph, nodeMap, edgeMap, areas, node);

                // Handle found messages (incoming messages)
                createIncomingMessages(sgraph, nodeMap, edgeMap, node);

            } else if (nodeType.equals("3009") || nodeType.equals("3008")
                    || nodeType.equals("3024") || nodeType.equals("3020")) {
                // Node is comment, constraint or time observation/constraint 

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
        sgraph.getSize().x = 0;
        sgraph.getSize().y = 0;

        // Copy the areas property to the SGraph
        sgraph.setProperty(PapyrusProperties.AREAS, areas);

        progressMonitor.done();

        return sgraph;
    }

    /**
     * Builds a layered graph that contains every message as a node. Edges are representations of
     * the relative order of the messages.
     * 
     * @param sgraph
     *            the given SGraph
     * @param progressMonitor
     *            the progress monitor
     * @return the layeredGraph
     */
    public LGraph createLayeredGraph(final SGraph sgraph,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Create layered graph", 1);

        LGraph lgraph = new LGraph();

        // Build a node for every message.
        int i = 0;
        for (SLifeline lifeline : sgraph.getLifelines()) {
            for (SMessage message : lifeline.getOutgoingMessages()) {
                LNode node = new LNode(lgraph);
                node.getLabels().add(new LLabel(lgraph, "Node" + i++));
                node.setProperty(Properties.ORIGIN, message);
                message.setProperty(SequenceDiagramProperties.LAYERED_NODE, node);
                lgraph.getLayerlessNodes().add(node);
            }
            // Handle found messages (they have no source lifeline)
            for (SMessage message : lifeline.getIncomingMessages()) {
                if (message.getSource().getName().equals("DummyLifeline")) {
                    LNode node = new LNode(lgraph);
                    node.getLabels().add(new LLabel(lgraph, "Node" + i++));
                    node.setProperty(Properties.ORIGIN, message);
                    message.setProperty(SequenceDiagramProperties.LAYERED_NODE, node);
                    lgraph.getLayerlessNodes().add(node);
                }
            }
        }

        // Add an edge for every neighbored pair of messages at every lifeline
        // indicating the relative order of the messages.
        for (SLifeline lifeline : sgraph.getLifelines()) {
            List<SMessage> messages = lifeline.getMessages();
            for (int j = 1; j < messages.size(); j++) {
                // Add an edge from the node belonging to message j-1 to the node belonging to
                // message j
                LNode sourceNode = messages.get(j - 1).getProperty(
                        SequenceDiagramProperties.LAYERED_NODE);
                LNode targetNode = messages.get(j).getProperty(
                        SequenceDiagramProperties.LAYERED_NODE);
                if (sourceNode != targetNode) {
                    LPort sourcePort = new LPort(lgraph);
                    sourcePort.setNode(sourceNode);
                    LPort targetPort = new LPort(lgraph);
                    targetPort.setNode(targetNode);
                    LEdge edge = new LEdge(lgraph);
                    edge.setSource(sourcePort);
                    edge.setTarget(targetPort);
                    edge.setProperty(SequenceDiagramProperties.BELONGS_TO_LIFELINE, lifeline);
                }
            }
        }

        progressMonitor.done();

        return lgraph;
    }

    /**
     * Create a comment object for comments or constraints (which are handled like comments).
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

        KShapeLayout commentLayout = node.getData(KShapeLayout.class);

        // Get the node's type
        String nodeType = commentLayout.getProperty(PapyrusProperties.NODE_TYPE);

        // Create comment object
        SComment comment = new SComment();
        comment.setProperty(Properties.ORIGIN, node);
        comment.setProperty(PapyrusProperties.NODE_TYPE, nodeType);
        String attachedElement = commentLayout.getProperty(PapyrusProperties.ATTACHED_ELEMENT);
        comment.setProperty(PapyrusProperties.ATTACHED_ELEMENT, attachedElement);
        // Attach connected edge to comment
        if (!node.getOutgoingEdges().isEmpty()) {
            comment.setProperty(SequenceDiagramProperties.COMMENT_CONNECTION, node
                    .getOutgoingEdges().get(0));
        }

        // Copy all the entries of the list of attached elements to the comment object
        List<Object> attachedTo = commentLayout.getProperty(PapyrusProperties.ATTACHED_TO);
        if (attachedTo != null) {
            List<SGraphElement> attTo = comment.getAttachedTo();
            for (Object att : attachedTo) {
                if (att instanceof KNode) {
                    attTo.add(nodeMap.get(att));
                } else if (att instanceof KEdge) {
                    attTo.add(edgeMap.get(att));
                }
            }
        }

        // Copy layout information
        comment.getPosition().x = commentLayout.getXpos();
        comment.getPosition().y = commentLayout.getYpos();
        comment.getSize().x = commentLayout.getWidth();
        comment.getSize().y = commentLayout.getHeight();

        // Handle time observations
        if (nodeType.equals("3020")) {
            comment.getSize().x = sgraph
                    .getProperty(SequenceDiagramProperties.TIME_OBSERVATION_WIDTH);

            // Find lifeline that is next to the time observation
            SLifeline nextLifeline = null;
            double smallestDistance = Double.MAX_VALUE;
            for (SLifeline lifeline : sgraph.getLifelines()) {
                double distance = Math.abs((lifeline.getPosition().x + lifeline.getSize().x / 2)
                        - (commentLayout.getXpos() + commentLayout.getWidth() / 2));
                if (distance < smallestDistance) {
                    smallestDistance = distance;
                    nextLifeline = lifeline;
                }
            }

            // Find message on the calculated lifeline that is next to the time observation
            SMessage nextMessage = null;
            smallestDistance = Double.MAX_VALUE;
            for (SMessage message : nextLifeline.getMessages()) {
                KEdge edge = (KEdge) message.getProperty(Properties.ORIGIN);
                KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                double distance;
                if (message.getSource() == nextLifeline) {
                    distance = Math.abs((edgeLayout.getSourcePoint().getY())
                            - (commentLayout.getYpos() + commentLayout.getHeight() / 2));
                } else {
                    distance = Math.abs((edgeLayout.getTargetPoint().getY())
                            - (commentLayout.getYpos() + commentLayout.getHeight() / 2));
                }
                if (distance < smallestDistance) {
                    smallestDistance = distance;
                    nextMessage = message;
                }
            }

            // Set both, lifeline and message of the comment to indicate that it should be drawn
            // near to the event
            comment.setLifeline(nextLifeline);
            comment.setMessage(nextMessage);
        }

        sgraph.getComments().add(comment);
    }

    /**
     * Check, where to place an empty area.
     * 
     * @param sgraph
     *            the Sequence Graph
     * @param area
     *            the area
     */
    private void handleEmptyArea(final SGraph sgraph, final SequenceArea area) {
        // Check which lifelines are involved
        for (SLifeline lifeline : sgraph.getLifelines()) {
            if (isLifelineContained(lifeline, area)) {
                area.addLifeline(lifeline);
            }
        }

        double lowerEnd = area.getPosition().y + area.getSize().y;
        SMessage nextMessage = null;
        double uppermostPosition = Double.MAX_VALUE;
        // Check which message is the next one below the area
        for (Object lifelineObj : area.getLifelines()) {
            SLifeline lifeline = (SLifeline) lifelineObj;
            for (SMessage message : lifeline.getIncomingMessages()) {
                Object originObj = message.getProperty(Properties.ORIGIN);
                if (originObj instanceof KEdge) {
                    KEdge edge = (KEdge) originObj;
                    KEdgeLayout layout = edge.getData(KEdgeLayout.class);
                    double yPos = layout.getTargetPoint().getY();
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
                    double yPos = layout.getSourcePoint().getY();
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
            SLifeline targetLL = nodeMap.get(edge.getTarget());

            // Lost-messages and messages to the surrounding interaction don't have a lifeline, so
            // create dummy lifeline
            if (targetLL == null) {
                SLifeline dummy = new SLifeline();
                dummy.setName("DummyLifeline");
                dummy.setGraph(sgraph);
                targetLL = dummy;
            }

            // Create message object
            SMessage message = new SMessage(sourceLL, targetLL);
            message.setProperty(Properties.ORIGIN, edge);
            message.setComments(new LinkedList<SComment>());

            KEdgeLayout layout = edge.getData(KEdgeLayout.class);
            message.setSourceYPos(layout.getSourcePoint().getY());
            message.setTargetYPos(layout.getTargetPoint().getY());

            // Read size of the attached label
            double maxLabelLength = 0;
            for (KLabel label : edge.getLabels()) {
                KShapeLayout labelLayout = label.getData(KShapeLayout.class);
                if (labelLayout.getWidth() > maxLabelLength) {
                    maxLabelLength = labelLayout.getWidth();
                }
            }
            message.setLabelWidth(maxLabelLength);

            // Add message to the source and the target lifeline's list of messages
            sourceLL.addMessage(message);
            targetLL.addMessage(message);

            // Put edge and message into the edge map
            edgeMap.put(edge, message);

            // Replace KEdge by its SMessage if it appears in one of the lifeline's executions. It
            // is better to do it this way than running through the list of executions since that
            // would lead to concurrent modification exceptions.
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

            // Append the message type of the edge to the message
            String messageType = layout.getProperty(PapyrusProperties.MESSAGE_TYPE);
            if (messageType.equals("4004")) {
                message.setProperty(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.ASYNCHRONOUS);
            } else if (messageType.equals("4006")) {
                message.setProperty(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.CREATE);
            } else if (messageType.equals("4007")) {
                message.setProperty(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.DELETE);
            } else if (messageType.equals("4003")) {
                message.setProperty(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.SYNCHRONOUS);
            } else if (messageType.equals("4008")) {
                message.setProperty(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.LOST);
            }

            // Outgoing messages to the surrounding interaction are drawn to the right and therefore
            // their target lifeline should have highest position
            if (targetLL.getName().equals("DummyLifeline") && !messageType.equals("4008")) {
                targetLL.setHorizontalSlot(sgraph.getLifelines().size() + 1);
            }

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
     * Walk through incoming edges and check if there are found messages or messages that come from
     * the surrounding interaction. If so, create the corresponding SMessage.
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
    private void createIncomingMessages(final SGraph sgraph,
            final HashMap<KNode, SLifeline> nodeMap, final HashMap<KEdge, SMessage> edgeMap,
            final KNode node) {
        for (KEdge edge : node.getIncomingEdges()) {
            KEdgeLayout layout = edge.getData(KEdgeLayout.class);

            SLifeline sourceLL = nodeMap.get(edge.getSource());
            if (sourceLL == null) {
                // TODO consider connections to comments and constraints!
                // Create dummy lifeline as source since the message has no source lifeline
                SLifeline dummy = new SLifeline();
                dummy.setName("DummyLifeline");
                dummy.setGraph(sgraph);
                sourceLL = dummy;
                SLifeline targetLL = nodeMap.get(edge.getTarget());

                // Create message object
                SMessage message = new SMessage(sourceLL, targetLL);
                message.setProperty(Properties.ORIGIN, edge);
                message.setComments(new LinkedList<SComment>());
                message.setTargetYPos(layout.getTargetPoint().getY());

                // Add the message to the source and target lifeline's list of messages
                sourceLL.addMessage(message);
                targetLL.addMessage(message);

                // Put edge and message into the edge map
                edgeMap.put(edge, message);

                // Append the message type of the edge to the message
                String messageType = layout.getProperty(PapyrusProperties.MESSAGE_TYPE);
                if (messageType.equals("4009")) {
                    message.setProperty(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.FOUND);
                } else {
                    // Since incoming messages come from the left side of the surrounding
                    // interaction, give its dummy lifeline position -1
                    sourceLL.setHorizontalSlot(-1);

                    if (messageType.equals("4004")) {
                        message.setProperty(SequenceDiagramProperties.MESSAGE_TYPE,
                                MessageType.ASYNCHRONOUS);
                    } else if (messageType.equals("4006")) {
                        message.setProperty(SequenceDiagramProperties.MESSAGE_TYPE,
                                MessageType.CREATE);
                    } else if (messageType.equals("4007")) {
                        message.setProperty(SequenceDiagramProperties.MESSAGE_TYPE,
                                MessageType.DELETE);
                    } else if (messageType.equals("4003")) {
                        message.setProperty(SequenceDiagramProperties.MESSAGE_TYPE,
                                MessageType.SYNCHRONOUS);
                    }
                }

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
        KShapeLayout layout = node.getData(KShapeLayout.class);
        String nodeType = layout.getProperty(PapyrusProperties.NODE_TYPE);
        if (nodeType.equals("3001")) {
            // Node is lifeline
            SLifeline lifeline = new SLifeline();
            if (node.getLabels().size() > 0) {
                lifeline.setName(node.getLabels().get(0).getText());
            }
            lifeline.setProperty(Properties.ORIGIN, node);
            nodeMap.put(node, lifeline);
            sgraph.addLifeline(lifeline);

            // Copy layout information to lifeline
            lifeline.getPosition().x = layout.getXpos();
            lifeline.getPosition().y = layout.getYpos();
            lifeline.getSize().x = layout.getWidth();
            lifeline.getSize().y = layout.getHeight();

            // Copy executions to lifeline
            List<SequenceExecution> executions = layout.getProperty(PapyrusProperties.EXECUTIONS);
            lifeline.setProperty(PapyrusProperties.EXECUTIONS, executions);

            lifeline.setComments(new LinkedList<SComment>());

            // Copy destruction to lifeline
            KNode destruction = layout.getProperty(PapyrusProperties.DESTRUCTION);
            if (destruction != null) {
                lifeline.setProperty(SequenceDiagramProperties.DESTRUCTION_EVENT, destruction);
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
    private boolean isLifelineContained(final SLifeline lifeline, final SequenceArea area) {
        double lifelineCenter = lifeline.getPosition().x + lifeline.getSize().x / 2;
        double leftEnd = area.getPosition().x;
        double rightEnd = area.getPosition().x + area.getSize().x;

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
        if (point.getX() < area.getPosition().x) {
            return false;
        }
        if (point.getX() > area.getPosition().x + area.getSize().x) {
            return false;
        }
        if (point.getY() < area.getPosition().y) {
            return false;
        }
        if (point.getY() > area.getPosition().y + area.getSize().y) {
            return false;
        }
        return true;
    }
}
