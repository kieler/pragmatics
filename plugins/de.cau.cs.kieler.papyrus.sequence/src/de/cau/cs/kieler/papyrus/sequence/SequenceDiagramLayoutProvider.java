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

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.p2layers.NetworkSimplexLayerer;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.papyrus.PapyrusProperties;
import de.cau.cs.kieler.papyrus.SequenceArea;
import de.cau.cs.kieler.papyrus.SequenceExecution;
import de.cau.cs.kieler.papyrus.sequence.graph.SComment;
import de.cau.cs.kieler.papyrus.sequence.graph.SGraph;
import de.cau.cs.kieler.papyrus.sequence.graph.SGraphElement;
import de.cau.cs.kieler.papyrus.sequence.graph.SLifeline;
import de.cau.cs.kieler.papyrus.sequence.graph.SMessage;
import de.cau.cs.kieler.papyrus.sequence.sorter.InteractiveLifelineSorter;
import de.cau.cs.kieler.papyrus.sequence.sorter.LayerbasedLifelineSorter;
import de.cau.cs.kieler.papyrus.sequence.sorter.LifelineSortingStrategy;

/**
 * Layout algorithm for Papyrus sequence diagrams.
 * 
 * @author grh
 * @kieler.design proposed grh
 * @kieler.rating proposed yellow grh
 * 
 */
public class SequenceDiagramLayoutProvider extends AbstractLayoutProvider {
    /** Vertical size of lifeline header. */
    private static final float LIFELINE_HEADER = 30;
    /** Vertical space above lifelines. */
    private static final float LIFELINE_Y_POS = 10;
    /** Height of the header of areas. */
    private static final float AREA_HEADER_HEIGHT = 25;
    /** The offset of an area that contains another area (spacing between them). */
    private static final int CONTAINMENT_OFFSET = 5;
    /** Constant that is needed to calculate some offsets. */
    private static final int TWENTY = 20;
    /** Constant that is needed to calculate some offsets. */
    private static final int FOURTY = 40;

    /** Lifeline ordering algorithm. */
    private ILifelineSorter lifelineSorter;
    /** Graph importer. */
    private SGraphImporter importer;
    /** Border spacing. */
    private float borderSpacing;
    /** Horizontal spacing between two neighbored lifelines. */
    private float lifelineSpacing;
    /** Vertical spacing between two neighbored layers of messages. */
    private float messageSpacing;

    @Override
    public void doLayout(final KNode parentNode, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Sequence Diagrem Layouter", 1);

        // Get layout properties
        KShapeLayout sourceShapeLayout = parentNode.getData(KShapeLayout.class);
        borderSpacing = sourceShapeLayout.getProperty(Properties.BORDER_SPACING);
        lifelineSpacing = sourceShapeLayout.getProperty(SeqProperties.LIFELINE_SPACING);
        messageSpacing = sourceShapeLayout.getProperty(SeqProperties.MESSAGE_SPACING);
        LifelineSortingStrategy strategy = sourceShapeLayout
                .getProperty(SeqProperties.LIFELINE_SORTING);
        if (strategy == LifelineSortingStrategy.LAYER_BASED) {
            lifelineSorter = new LayerbasedLifelineSorter();
        } else {
            lifelineSorter = new InteractiveLifelineSorter();
        }

        // Convert the KGraph into a SGraph
        importer = new SGraphImporter();
        SGraph sgraph = importer.importGraph(parentNode);

        // Build layeredGraph of the edges of the SGraph
        LGraph layeredGraph = importer.createLayeredGraph(sgraph);

        // Allocate space for comments by introducing dummy nodes
        placeComments(sgraph, layeredGraph);

        // Allocate space for empty areas
        placeEmptyAreas(sgraph, layeredGraph);

        // Allocate space for lifeline header of "created" lifelines
        placeCreatedLifelines(sgraph, layeredGraph);

        // Break the cycles in the layeredGraph
        SCycleBreaker cycleBreaker = new SCycleBreaker();
        cycleBreaker.breakCycles(layeredGraph);

        // Find a layering for the layeredGraph
        NetworkSimplexLayerer layerer = new NetworkSimplexLayerer(); // new LongestPathLayerer();
        layerer.process(layeredGraph);

        // Order lifelines by the chosen lifeline sorting algorithm
        List<SLifeline> lifelineOrder = lifelineSorter.sortLifelines(sgraph, layeredGraph);

        // Assign vertical position to SMessages
        applyMessageYCoords(layeredGraph);

        // Apply the calculated layout to the KGraph
        applyLayout(sgraph, lifelineOrder, parentNode);

        progressMonitor.done();
    }

    /**
     * Apply the calculated layout the KGraph.
     * 
     * @param graph
     *            the SGraph
     * @param lifelineOrder
     *            a list of the lifelines in the desired order
     * @param parentNode
     *            the parent layout node
     */
    public void applyLayout(final SGraph graph, final List<SLifeline> lifelineOrder,
            final KNode parentNode) {
        float xPos = borderSpacing;

        // Arrange comments
        arrangeComments(graph);

        // The height of all "normal-sized" (not affected by create or delete messages) lifelines
        float lifelinesHeight = LIFELINE_HEADER + graph.getSizeY() + messageSpacing;

        // The height of the diagram (the surrounding interaction)
        float diagramHeight = graph.getSizeY() + messageSpacing + LIFELINE_HEADER + LIFELINE_Y_POS;

        // Set position for lifelines/nodes
        for (SLifeline lifeline : lifelineOrder) {

            // Dummy lifelines don't need any layout
            if (lifeline.getName().equals("DummyLifeline")) {
                continue;
            }

            KNode node = (KNode) lifeline.getProperty(Properties.ORIGIN);
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);

            if (nodeLayout.getProperty(PapyrusProperties.NODE_TYPE).equals(
                    "PapyrusUMLSequenceDiagram")) {
                // Set position of the surrounding node
                nodeLayout.setYpos(borderSpacing);
                nodeLayout.setXpos(borderSpacing);
                break;
            }

            // Check if there are any comments that have to be placed near the lifeline
            List<SComment> comments = lifeline.getProperty(SeqProperties.COMMENTS);
            float thisLifelinesSpacing = lifelineSpacing;
            if (comments.size() > 0) {
                // Place comments and calculate the maximum width of the comments
                thisLifelinesSpacing = arrangeComments(xPos, lifeline);
            }

            // Set position and height for the lifeline. This may be overridden if there are create-
            // or delete-messages involved.
            nodeLayout.setYpos(LIFELINE_Y_POS);
            nodeLayout.setXpos(xPos);
            nodeLayout.setHeight(lifelinesHeight);

            // Apply maximum comment width to new xPos
            xPos += nodeLayout.getWidth() + thisLifelinesSpacing;
            // Reset the graph's horizontal size
            if (graph.getSizeX() < xPos) {
                graph.setSizeX(xPos);
            }

            List<SequenceExecution> executions = lifeline.getProperty(PapyrusProperties.EXECUTIONS);

            // Handle messages of the lifeline
            handleMessages(diagramHeight, graph, lifeline, executions);

            // Handle destruction if existing
            KNode destruction = lifeline.getDestruction();
            if (destruction != null) {
                KShapeLayout destructLayout = destruction.getData(KShapeLayout.class);
                float destructionXPos = nodeLayout.getWidth() / 2 - destructLayout.getWidth() / 2;
                float destructionYPos = nodeLayout.getHeight() - destructLayout.getHeight();
                destructLayout.setPos(destructionXPos, destructionYPos);
            }

            // Handle executions and messages that are attached to that executions
            if (executions != null) {
                handleExecutions(lifeline, executions);
            }
        }

        List<SequenceArea> areas = graph.getProperty(PapyrusProperties.AREAS);

        // Handle areas (interactions / combined fragments / interaction operands)
        if (areas != null) {
            // Check containments (hierarchy) of areas
            checkAreaContainment(areas);

            handleAreas(areas);
        }

        // Place all comments
        placeComments(graph, xPos);

        // Set position and size of surrounding interaction
        KShapeLayout parentLayout = parentNode.getData(KShapeLayout.class);
        parentLayout.setWidth(graph.getSizeX());
        parentLayout.setHeight(diagramHeight);
        parentLayout.setPos(borderSpacing, borderSpacing);
    }

    /**
     * Place the comment objects (comments, constraints) according to their calculated coordinates.
     * 
     * @param graph
     *            the Sequence Graph
     * @param xPos
     *            the last lifeline's x-coordinate
     */
    private void placeComments(final SGraph graph, final float xPos) {
        float commentMaxExtraWidth = 0;
        float commentNextYPos = LIFELINE_HEADER + LIFELINE_Y_POS;
        for (SComment comment : graph.getComments()) {
            if (comment.getMessage() != null) {
                // Connected comments
                comment.getOrigin().getData(KShapeLayout.class)
                        .setPos(comment.getxPos(), comment.getyPos());

                // Set coordinates for the connection of the comment
                float edgeSourceXPos, edgeSourceYPos, edgeTargetXPos, edgeTargetYPos;
                String attachedElement = comment.getProperty(PapyrusProperties.ATTACHED_ELEMENT);
                if (attachedElement.toLowerCase().startsWith("lifeline")
                        || attachedElement.toLowerCase().contains("execution")) {
                    // Connections to lifelines or executions are drawn horizontally
                    KNode node = (KNode) comment.getLifeline().getProperty(Properties.ORIGIN);
                    KShapeLayout nodelayout = node.getData(KShapeLayout.class);
                    edgeSourceXPos = comment.getxPos();
                    edgeSourceYPos = comment.getyPos() + comment.getHeight() / 2;
                    edgeTargetXPos = nodelayout.getXpos() + nodelayout.getWidth() / 2;
                    edgeTargetYPos = edgeSourceYPos;
                } else {
                    // Connections to messages are drawn vertically
                    edgeSourceXPos = comment.getxPos() + comment.getWidth() / 2;
                    edgeTargetXPos = edgeSourceXPos;
                    KEdge edge = (KEdge) comment.getMessage().getProperty(Properties.ORIGIN);
                    KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                    KPoint targetPoint = edgeLayout.getTargetPoint();
                    KPoint sourcePoint = edgeLayout.getSourcePoint();
                    edgeSourceYPos = comment.getyPos() + comment.getHeight();
                    edgeTargetYPos = (targetPoint.getY() + sourcePoint.getY()) / 2;
                }

                // Apply comment coordinates to layout
                KEdgeLayout edgelayout = comment.getConnection().getData(KEdgeLayout.class);
                edgelayout.getSourcePoint().setPos(edgeSourceXPos, edgeSourceYPos);
                edgelayout.getTargetPoint().setPos(edgeTargetXPos, edgeTargetYPos);
            } else {
                // Unconnected comments
                if (comment.getWidth() > commentMaxExtraWidth) {
                    commentMaxExtraWidth = comment.getWidth();
                }
                // Set position of unconnected comments next to the last lifeline
                comment.getOrigin().getData(KShapeLayout.class).setPos(xPos, commentNextYPos);
                commentNextYPos += comment.getHeight() + messageSpacing;
            }
        }

        if (commentMaxExtraWidth > 0) {
            graph.setSizeX(graph.getSizeX() + lifelineSpacing + commentMaxExtraWidth);
        }
    }

    /**
     * Calculate the position of the areas (interactionUse, combined fragment).
     * 
     * @param areas
     *            the list of areas in the graph
     */
    private void handleAreas(final List<SequenceArea> areas) {

        // Set size and position of area
        for (SequenceArea area : areas) {
            if (area.getMessages().size() > 0) {
                setAreaPositionByMessages(area);
            } else {
                setAreaPositionByLifelinesAndMessage(area);
            }
            KNode areaNode = (KNode) area.getOrigin();
            KShapeLayout areaLayout = areaNode.getData(KShapeLayout.class);

            // Check if there are contained areas
            int containmentDepth = checkContainment(area);
            // If so, an offset has to be calculated in order not to have overlapping borders
            int containmentSpacing = containmentDepth * CONTAINMENT_OFFSET;

            areaLayout.setXpos(area.getxPos() - lifelineSpacing / 2 - containmentSpacing);
            areaLayout.setWidth(area.getWidth() + lifelineSpacing + 2 * containmentSpacing);

            areaLayout.setYpos(area.getyPos() + LIFELINE_HEADER - messageSpacing / 2
                    - containmentSpacing);
            areaLayout.setHeight(area.getHeight() + messageSpacing + 2 * containmentSpacing);

            // Handle interaction operands
            if (area.getSubAreas().size() > 0) {
                // Reset area yPos and height if subAreas exists (to have a "header" that isn't
                // occupied by any subArea)
                areaLayout.setYpos(area.getyPos() - messageSpacing / 2);
                areaLayout.setHeight(area.getHeight() + messageSpacing + LIFELINE_HEADER);

                float lastPos = 0;
                KShapeLayout lastLayout = null;
                for (SequenceArea subArea : area.getSubAreas()) {

                    KNode subAreaNode = (KNode) subArea.getOrigin();
                    KShapeLayout subAreaLayout = subAreaNode.getData(KShapeLayout.class);

                    subAreaLayout.setXpos(0);
                    subAreaLayout.setWidth(area.getWidth() + lifelineSpacing - 2);
                    if (subArea.getMessages().size() > 0) {
                        // Calculate and set y-position by the area's messages
                        setAreaPositionByMessages(subArea);
                        subAreaLayout.setYpos(subArea.getyPos() - area.getyPos() + LIFELINE_HEADER
                                - messageSpacing / 2);
                    } else {
                        // Calculate and set y-position by the available space
                        subAreaLayout.setYpos(lastPos);
                        // FIXME if subarea is empty, it appears first in the list
                    }

                    // Reset last subArea's height to fit
                    if (lastLayout != null) {
                        lastLayout.setHeight(subAreaLayout.getYpos() - lastLayout.getYpos());
                    }
                    lastPos = subAreaLayout.getYpos() + subAreaLayout.getHeight();
                    lastLayout = subAreaLayout;
                }
                // Reset last subArea's height to fit
                if (lastLayout != null) {
                    lastLayout.setHeight(areaLayout.getHeight() - lastLayout.getYpos()
                            - AREA_HEADER_HEIGHT);
                }
            }
        }
    }

    /**
     * Calculate and set the coordinates of the executions of the given lifeline.
     * 
     * @param lifeline
     *            the lifeline, whose executions are placed
     * @param executions
     *            the list of executions
     */
    private void handleExecutions(final SLifeline lifeline, final List<SequenceExecution> executions) {
        KNode node = (KNode) lifeline.getProperty(Properties.ORIGIN);
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);

        // Set xPos, maxXPos and height / maxYPos
        arrangeExecutions(executions, nodeLayout.getWidth());

        // Walk through the lifeline's executions
        nodeLayout.setProperty(PapyrusProperties.EXECUTIONS, executions);
        for (SequenceExecution execution : executions) {
            Object executionObj = execution.getOrigin();
            if (executionObj instanceof KNode) {
                KNode executionNode = (KNode) executionObj;
                KShapeLayout shapelayout = executionNode.getData(KShapeLayout.class);
                if (execution.getType().equals("Duration")
                        || execution.getType().equals("TimeConstraint")) {
                    execution.setyPos(execution.getyPos() + TWENTY);
                    execution.setMaxYPos(execution.getMaxYPos() + TWENTY);
                }
                shapelayout.setXpos(execution.getxPos());
                shapelayout.setYpos(execution.getyPos() - LIFELINE_Y_POS);
                shapelayout.setWidth(execution.getMaxXPos() - execution.getxPos());
                shapelayout.setHeight(execution.getMaxYPos() - execution.getyPos());

                // Determine max and min y-pos of messages
                float lifelineHeight = nodeLayout.getHeight();
                float minYPos = lifelineHeight;
                float maxYPos = 0;
                for (Object messObj : execution.getMessages()) {
                    if (messObj instanceof SMessage) {
                        SMessage message = (SMessage) messObj;
                        float messageYPos;
                        if (message.getSource() == lifeline) {
                            messageYPos = message.getSourceYPos();
                        } else {
                            messageYPos = message.getTargetYPos();
                        }
                        if (messageYPos < minYPos) {
                            minYPos = messageYPos;
                        }
                        if (messageYPos > maxYPos) {
                            maxYPos = messageYPos;
                        }
                    }
                }

                /*
                 * TODO set executionFactor to one if the Papyrus team fixes the bug. Calculate
                 * conversion factor. Conversion is necessary because Papyrus stores the
                 * y-coordinates in a very strange way. When the message starts or ends at an
                 * execution, y-coordinates must be given relative to the execution. However, these
                 * relative coordinates must be scaled as if the execution was having the height of
                 * its lifeline.
                 */
                float effectiveHeight = lifelineHeight - TWENTY;
                float executionHeight = maxYPos - minYPos;
                float executionFactor = effectiveHeight / executionHeight;

                // Walk through execution's messages and adjust their position
                for (Object messObj : execution.getMessages()) {
                    if (messObj instanceof SMessage) {
                        SMessage mess = (SMessage) messObj;
                        boolean toLeft = false;
                        if (mess.getSource().getPosition() > mess.getTarget().getPosition()) {
                            // Message leads leftwards
                            toLeft = true;
                        }

                        KEdge edge = (KEdge) mess.getProperty(Properties.ORIGIN);
                        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                        float newXPos = nodeLayout.getXpos() + shapelayout.getXpos();
                        if (mess.getSource() == mess.getTarget()) {
                            // Selfloop: insert bend points
                            edgeLayout.getBendPoints().get(0)
                                    .setY(edgeLayout.getSourcePoint().getY());
                            edgeLayout.getBendPoints().get(1)
                                    .setY(edgeLayout.getTargetPoint().getY());
                            edgeLayout.getTargetPoint().setX(newXPos + shapelayout.getWidth());
                            edgeLayout.getTargetPoint().setY(0);
                        } else if (mess.getSource() == lifeline) {
                            if (!toLeft) {
                                newXPos += shapelayout.getWidth();
                            }
                            edgeLayout.getSourcePoint().setX(newXPos);

                            // Calculate the message's height relative to the execution
                            float relHeight = mess.getSourceYPos() - minYPos;
                            if (relHeight == 0) {
                                edgeLayout.getSourcePoint().setY(0);
                            } else {
                                edgeLayout.getSourcePoint().setY(
                                        LIFELINE_HEADER + relHeight * executionFactor);
                            }
                        } else {
                            if (toLeft) {
                                newXPos += shapelayout.getWidth();
                            }
                            edgeLayout.getTargetPoint().setX(newXPos);

                            // Calculate the message's height relative to the execution
                            float relHeight = mess.getTargetYPos() - minYPos;
                            if (relHeight == 0) {
                                edgeLayout.getTargetPoint().setY(0);
                            } else {
                                edgeLayout.getTargetPoint().setY(
                                        LIFELINE_HEADER + relHeight * executionFactor);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Handle the messages that are connected to the given lifeline.
     * 
     * @param diagramHeight
     *            the height of the whole diagram
     * @param graph
     *            the Sequence Graph
     * @param lifeline
     *            the lifeline whose messages are handled
     * @param executions
     *            the list of executions
     */
    private void handleMessages(final float diagramHeight, final SGraph graph,
            final SLifeline lifeline, final List<SequenceExecution> executions) {

        KNode node = (KNode) lifeline.getProperty(Properties.ORIGIN);
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);

        /*
         * TODO Set this to one if Papyrus team fixes its bug. Workaround for Papyrus bug.
         * Y-coordinates are stored in a strange way by Papyrus. When the message starts or ends at
         * a lifeline, y-coordinates must be given relative to the lifeline. However, these relative
         * coordinates must be scaled as if the lifeline was having the height of its surrounding
         * interaction.
         */
        float factor = (diagramHeight + TWENTY) / nodeLayout.getHeight();

        // Resize node if there are any create or delete messages involved
        for (SMessage message : lifeline.getIncomingMessages()) {
            if (message.getProperty(SeqProperties.MESSAGE_TYPE) == MessageType.CREATE) {
                // Set lifeline's yPos to the yPos of the create-message
                nodeLayout.setYpos(message.getTargetYPos() + LIFELINE_HEADER / 2);
                // Modify height of lifeline in order to compensate yPos changes
                nodeLayout.setHeight(nodeLayout.getHeight() + LIFELINE_Y_POS
                        - message.getTargetYPos() - LIFELINE_HEADER / 2);
            } else if (message.getProperty(SeqProperties.MESSAGE_TYPE) == MessageType.DELETE) {
                // Modify height of lifeline in order to end at the yPos of the delete-message
                nodeLayout.setHeight(nodeLayout.getHeight()
                        - (graph.getSizeY() + messageSpacing - message.getTargetYPos()));
            }
        }

        // Handle outgoing messages
        for (SMessage message : lifeline.getOutgoingMessages()) {
            KEdge edge = (KEdge) message.getProperty(Properties.ORIGIN);
            KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
            KPoint sourcePoint = edgeLayout.getSourcePoint();
            sourcePoint.setY(message.getSourceYPos() * factor);
            sourcePoint.setX(nodeLayout.getXpos() + nodeLayout.getWidth() / 2);

            // Set execution coordinates according to connected messages coordinates
            if (executions != null) {
                for (SequenceExecution execution : executions) {
                    if (execution.getMessages().contains(message)) {
                        float sourceYPos = message.getSourceYPos();
                        if (sourceYPos < execution.getyPos()) {
                            if (message.getSource() != message.getTarget()) {
                                execution.setyPos(sourceYPos);
                            }
                        }
                        if (sourceYPos > execution.getMaxYPos()) {
                            execution.setMaxYPos(sourceYPos);
                        }
                    }
                }
            }

            // Handle messages that lead to something else than a lifeline
            if (message.getTarget().getName().equals("DummyLifeline")) {
                KPoint targetPoint = edgeLayout.getTargetPoint();
                float reverseFactor = nodeLayout.getHeight() / (diagramHeight + FOURTY);
                targetPoint.setY(TWENTY + message.getTargetYPos() * reverseFactor);

                // Lost-messages end between its source and the next lifeline
                if (message.getProperty(SeqProperties.MESSAGE_TYPE) == MessageType.LOST) {
                    targetPoint.setX(nodeLayout.getXpos() + nodeLayout.getWidth() + lifelineSpacing
                            / 2);
                }
            }
        }

        // Handle incoming messages
        for (SMessage message : lifeline.getIncomingMessages()) {
            KEdge edge = (KEdge) message.getProperty(Properties.ORIGIN);
            KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
            KPoint targetPoint = edgeLayout.getTargetPoint();
            targetPoint.setX(nodeLayout.getXpos() + nodeLayout.getWidth() / 2);
            targetPoint.setY(message.getTargetYPos() * factor);

            if (message.getProperty(SeqProperties.MESSAGE_TYPE) == MessageType.CREATE) {
                // Reset x-position of create message because it leads to the header and not the line
                targetPoint.setX(nodeLayout.getXpos());
            } else if (message.getProperty(SeqProperties.MESSAGE_TYPE) == MessageType.DELETE) {
                // Reset y-position of delete message to end at the end of the lifeline
                targetPoint.setY((nodeLayout.getHeight() + nodeLayout.getYpos() - LIFELINE_HEADER)
                        * factor);
            }

            // Reset execution coordinates if the message is contained in an execution
            if (executions != null) {
                for (SequenceExecution execution : executions) {
                    if (execution.getMessages().contains(message)) {
                        float targetYPos = message.getTargetYPos();
                        if (targetYPos < execution.getyPos()) {
                            execution.setyPos(targetYPos);
                        }
                        if (targetYPos > execution.getMaxYPos()) {
                            execution.setMaxYPos(targetYPos);
                        }
                    }
                }
            }

            // Handle messages that come from something else than a lifeline
            if (message.getSource().getName().equals("DummyLifeline")) {
                KPoint sourcePoint = edgeLayout.getSourcePoint();
                float reverseFactor = nodeLayout.getHeight() / (diagramHeight + FOURTY);
                sourcePoint.setY(TWENTY + message.getSourceYPos() * reverseFactor);

                // Found-messages start between its source and the previous lifeline
                if (message.getProperty(SeqProperties.MESSAGE_TYPE) == MessageType.FOUND) {
                    sourcePoint.setX(nodeLayout.getXpos() - lifelineSpacing / 2);
                }
            }
        }
    }

    /**
     * Places the comments that have to be placed near the lifeline and calculates the width of the
     * widest of them.
     * 
     * @param xPos
     *            the horizontal position where the last lifeline was placed
     * @param lifeline
     *            the current lifeline
     * @return the width of the widest comment
     */
    private float arrangeComments(final float xPos, final SLifeline lifeline) {
        // Get the list of comments attached to the current lifeline
        List<SComment> comments = lifeline.getProperty(SeqProperties.COMMENTS);

        // Check maximum size of comments attached to the lifeline
        float maxCommentWidth = lifelineSpacing;
        for (SComment comment : comments) {
            if (comment.getWidth() > maxCommentWidth) {
                maxCommentWidth = comment.getWidth();
            }
        }

        // Get layout information of the lifeline
        KNode node = (KNode) lifeline.getProperty(Properties.ORIGIN);
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);

        // HashMap that organizes which comment belongs to which message. This is important
        // if there are more than one comments at a message.
        HashMap<SMessage, SComment> hash = new HashMap<SMessage, SComment>(comments.size());
        for (SComment comment : comments) {
            if (comment.getLifeline() == lifeline) {
                SMessage message = comment.getMessage();

                // Place comment in the center of the message if it is smaller than
                // lifelineSpacing
                float commentXPos = xPos + nodeLayout.getWidth();
                if (comment.getWidth() < maxCommentWidth) {
                    commentXPos += (maxCommentWidth - comment.getWidth()) / 2;
                }

                // Place comment above the message
                float commentYPos = message.getSourceYPos() + LIFELINE_HEADER + LIFELINE_Y_POS
                        - (comment.getHeight() + messageSpacing);

                comment.setxPos(commentXPos);
                comment.setyPos(commentYPos);

                if (hash.containsKey(message)) {
                    // Handle conflicts (reset yPos if necessary)
                    SComment upper = comment;
                    SComment lower = hash.get(message);
                    String nodeType = comment.getProperty(PapyrusProperties.NODE_TYPE);

                    // If comment is Observation, place it nearer to the message
                    if (nodeType.equals("3024") || nodeType.equals("3020")) {
                        upper = lower;
                        lower = comment;
                    }

                    // Place lower comment first
                    commentYPos = message.getSourceYPos() + LIFELINE_HEADER + LIFELINE_Y_POS
                            - (lower.getHeight() + messageSpacing);
                    lower.setyPos(commentYPos);

                    // Place upper comment near to lower one
                    float uYpos = lower.getyPos() - upper.getHeight() - messageSpacing / 2;
                    upper.setyPos(uYpos);
                } else {
                    hash.put(message, comment);
                }
            }
        }
        return maxCommentWidth;
    }

    /**
     * Preprocessor that does some organizing stuff for comment objects that are connected to
     * lifelines or messages.
     * 
     * @param graph
     *            the SequenceGraph
     */
    private void arrangeComments(final SGraph graph) {
        for (SComment comment : graph.getComments()) {
            SMessage message = null;
            SLifeline lifeline = null;
            // Get random connected message and lifeline if existing
            // This may be optimized if there is more than one connection
            for (SGraphElement element : comment.getAttachedTo()) {
                if (element instanceof SMessage) {
                    message = (SMessage) element;
                } else if (element instanceof SLifeline) {
                    lifeline = (SLifeline) element;
                }
            }

            comment.setMessage(message);
            comment.setLifeline(lifeline);

            /*
             * If the comment is attached to a message, determine if it should be drawn near the
             * beginning or near the end of the message. If the message is attached to a message and
             * one of the message's lifelines, it should be drawn near that lifeline (this is the
             * case for time observations for example).
             */
            if (message != null) {
                SLifeline right, left;
                if (message.getSource().getPosition() < message.getTarget().getPosition()) {
                    // Message leads rightwards
                    right = message.getTarget();
                    left = message.getSource();
                } else {
                    // Message leads leftwards or is self-loop
                    right = message.getSource();
                    left = message.getTarget();
                }
                if (lifeline == right) {
                    // Find lifeline left to "right" and attach comment to that lifeline because
                    // comments are drawn right of the connected lifeline.
                    int position = right.getPosition();
                    for (SLifeline ll : graph.getLifelines()) {
                        if (ll.getPosition() == position - 1) {
                            comment.setLifeline(ll);
                            break;
                        }
                    }
                } else {
                    comment.setLifeline(left);
                }
            }
        }
    }

    /**
     * Check the hierarchy of areas. This is necessary to avoid overlapping borders.
     * 
     * @param areas
     *            the list of areas
     */
    private void checkAreaContainment(final List<SequenceArea> areas) {
        if (areas != null) {
            for (SequenceArea area : areas) {
                for (SequenceArea otherArea : areas) {
                    if (area != otherArea) {
                        if (area.getMessages().containsAll(otherArea.getMessages())) {
                            // Check if upper left corner is more upper and left than the other
                            // area's corner
                            if (area.getyPos() < otherArea.getyPos()
                                    && area.getxPos() < otherArea.getxPos()) {
                                area.getContainedAreas().add(otherArea);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Add dummy nodes to the layered graph in order to allocate space for empty areas.
     * 
     * @param graph
     *            the SGraph
     * @param lgraph
     *            the layered graph
     */
    private void placeEmptyAreas(final SGraph graph, final LGraph lgraph) {
        List<SequenceArea> areas = graph.getProperty(PapyrusProperties.AREAS);
        if (areas != null) {
            for (SequenceArea area : areas) {
                if (area.getMessages().size() == 0) {
                    if (area.getNextMessage() != null) {
                        LNode node = importer.getLayeredMap().get(area.getNextMessage());
                        if (node != null) {
                            // Create two dummy nodes before node to have enough space for the empty
                            // area
                            createDummyNode(lgraph, node, true);
                            createDummyNode(lgraph, node, true);
                        }
                    }
                }
            }
        }
    }

    /**
     * Creates a dummy node below every node representing a create message. This allocates the space
     * needed for the lifeline header.
     * 
     * @param sgraph
     *            the sequence graph
     * @param lgraph
     *            the layered graph
     */
    private void placeCreatedLifelines(final SGraph sgraph, final LGraph lgraph) {
        for (SLifeline lifeline : sgraph.getLifelines()) {
            for (SMessage message : lifeline.getIncomingMessages()) {
                if (message.getProperty(SeqProperties.MESSAGE_TYPE) == MessageType.CREATE) {
                    // Add dummy below this messages node
                    LNode node = importer.getLayeredMap().get(message);
                    createDummyNode(lgraph, node, false);
                }
            }
        }
    }

    /**
     * Place the comments near the element(s) that they are attached to if attached to any element.
     * 
     * @param graph
     *            the SGraph
     * @param lgraph
     *            the layered graph
     */
    private void placeComments(final SGraph graph, final LGraph lgraph) {
        for (SComment comment : graph.getComments()) {
            // Check to which kind of object the comment is attached
            SMessage attachedMess = null;
            for (SGraphElement element : comment.getAttachedTo()) {
                if (element instanceof SMessage) {
                    attachedMess = (SMessage) element;
                }
            }
            if (attachedMess != null) {
                // Get height of the comment and calculate number of dummy nodes needed
                float height = comment.getHeight();
                int dummys = (int) Math.ceil(height / messageSpacing);
                // Add dummy nodes in the layered graph
                LNode node = importer.getLayeredMap().get(attachedMess);
                if (node != null) {
                    for (int i = 0; i < dummys; i++) {
                        createDummyNode(lgraph, node, true);
                    }
                    comment.setMessage(attachedMess);
                    attachedMess.getProperty(SeqProperties.COMMENTS).add(comment);
                }
            }
        }
    }

    /**
     * Creates a dummy node in the layered graph, that is placed near the given node. Every
     * connected edge of the original node is redirected to the dummy node.
     * 
     * @param lgraph
     *            the layered graph
     * @param node
     *            the node, that gets a predecessor
     * @param beforeNode
     *            if true, the dummy will be inserted before the node, behind the node otherwise
     */
    private void createDummyNode(final LGraph lgraph, final LNode node, final boolean beforeNode) {
        LNode dummy = new LNode(lgraph);
        LPort dummyIn = new LPort(lgraph);
        LPort dummyOut = new LPort(lgraph);
        dummyIn.setNode(dummy);
        dummyOut.setNode(dummy);
        LPort newPort = new LPort(lgraph);
        newPort.setNode(node);

        LEdge dummyEdge = new LEdge(lgraph);

        // To avoid concurrent modification, two lists are needed
        if (beforeNode) {
            List<LEdge> incomingEdges = new LinkedList<LEdge>();
            for (LEdge edge : node.getIncomingEdges()) {
                incomingEdges.add(edge);
            }
            for (LEdge edge : incomingEdges) {
                edge.setTarget(dummyIn);
            }
            dummyEdge.setSource(dummyOut);
            dummyEdge.setTarget(newPort);
        } else {
            List<LEdge> outgoingEdges = new LinkedList<LEdge>();
            for (LEdge edge : node.getOutgoingEdges()) {
                outgoingEdges.add(edge);
            }
            for (LEdge edge : outgoingEdges) {
                edge.setSource(dummyOut);
            }
            dummyEdge.setTarget(dummyIn);
            dummyEdge.setSource(newPort);
        }
        lgraph.getLayerlessNodes().add(dummy);
    }

    /**
     * Check recursively if an area has contained areas and return the maximum depth.
     * 
     * @param area
     *            the {@link SequenceArea}
     * @return the maximum depth of containment
     */
    private int checkContainment(final SequenceArea area) {
        if (area.getContainedAreas().size() > 0) {
            int maxLevel = 0;
            for (SequenceArea subArea : area.getContainedAreas()) {
                int level = checkContainment(subArea);
                if (level > maxLevel) {
                    maxLevel = level;
                }
            }
            return maxLevel + 1;
        } else {
            return 0;
        }
    }

    /**
     * Searches all the contained edges and sets the area's position and size such that it is a
     * bounding box for the contained messages.
     * 
     * @param area
     *            the SequenceArea
     * @param factor
     *            the edgeYpos factor, that is necessary to compute the real position of the
     *            SMessage
     */
    private void setAreaPositionByMessages(final SequenceArea area) {
        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxX = 0;
        float maxY = 0;
        // Compute the bounding box of all contained messages
        for (Object messObj : area.getMessages()) {
            if (messObj instanceof SMessage) {
                // Compute new y coordinates
                SMessage message = (SMessage) messObj;
                KEdge edge = (KEdge) message.getProperty(Properties.ORIGIN);
                KEdgeLayout layout = edge.getData(KEdgeLayout.class);
                float sourceYPos = message.getSourceYPos();
                if (sourceYPos < minY) {
                    minY = sourceYPos;
                }
                if (sourceYPos > maxY) {
                    maxY = sourceYPos;
                }
                float targetYPos = message.getTargetYPos();
                if (targetYPos < minY) {
                    minY = targetYPos;
                }
                if (targetYPos > maxY) {
                    maxY = targetYPos;
                }
                // Compute new x coordinates
                float sourceXPos = layout.getSourcePoint().getX();
                if (sourceXPos < minX) {
                    minX = sourceXPos;
                }
                if (sourceXPos > maxX) {
                    maxX = sourceXPos;
                }
                float targetXPos = layout.getTargetPoint().getX();
                if (targetXPos < minX) {
                    minX = targetXPos;
                }
                if (targetXPos > maxX) {
                    maxX = targetXPos;
                }
            }
        }
        area.setxPos(minX);
        area.setyPos(minY);
        area.setWidth(maxX - minX);
        area.setHeight(maxY - minY);
    }

    /**
     * Sets the areas position such that it is a bounding box for the involved lifelines in x
     * direction and above the next message.
     * 
     * @param area
     *            the SequenceArea
     */
    private void setAreaPositionByLifelinesAndMessage(final SequenceArea area) {
        // Set xPos and width according to the involved lifelines
        float minXPos = Float.MAX_VALUE;
        float maxXPos = 0;
        for (Object lifelineObj : area.getLifelines()) {
            SLifeline lifeline = (SLifeline) lifelineObj;
            KNode node = (KNode) lifeline.getProperty(Properties.ORIGIN);
            KShapeLayout layout = node.getData(KShapeLayout.class);
            float lifelineCenter = layout.getXpos() + layout.getWidth() / 2;
            if (lifelineCenter < minXPos) {
                minXPos = lifelineCenter;
            }
            if (lifelineCenter > maxXPos) {
                maxXPos = lifelineCenter;
            }
        }
        area.setxPos(minXPos);
        area.setWidth(maxXPos - minXPos);

        // Set yPos and height according to the next message's yPos
        if (area.getNextMessage() != null) {
            Object messageObj = area.getNextMessage();
            SMessage message = (SMessage) messageObj;
            KEdge edge = (KEdge) message.getProperty(Properties.ORIGIN);
            KEdgeLayout layout = edge.getData(KEdgeLayout.class);
            float messageYPos;
            if (layout.getSourcePoint().getY() < layout.getTargetPoint().getY()) {
                messageYPos = message.getSourceYPos();
            } else {
                messageYPos = message.getTargetYPos();
            }
            area.setHeight(messageSpacing);
            area.setyPos(messageYPos - area.getHeight() - messageSpacing);
        }
    }

    /**
     * Set x position and width of an execution and check for minimum height.
     * 
     * @param executions
     *            List of {@link SequenceExecution} at the given {@link SLifeline}
     * @param parentWidth
     *            Width of the {@link SLifeline}
     */
    private void arrangeExecutions(final List<SequenceExecution> executions, final float parentWidth) {
        final float minHeight = 20;
        final float executionWidth = 16;

        // Initially set xPosition of execution
        for (SequenceExecution execution : executions) {
            execution.setxPos((parentWidth - executionWidth) / 2);
            // Give executions without messages their original height and yPos
            if (execution.getMessages().size() == 0) {
                KShapeLayout shapelayout = execution.getOrigin().getData(KShapeLayout.class);
                execution.setyPos(shapelayout.getYpos());
                execution.setMaxYPos(shapelayout.getYpos() + shapelayout.getHeight());
            }
        }

        if (executions.size() > 1) {
            // reset xPos if execution is attached to another execution
            for (SequenceExecution execution : executions) {
                if (execution.getType().equals("Duration")
                        || execution.getType().equals("TimeConstraint")) {
                    continue;
                }
                int pos = 0;
                for (SequenceExecution otherExecution : executions) {
                    if (execution != otherExecution) {
                        if (execution.getyPos() > otherExecution.getyPos()
                                && execution.getMaxYPos() < otherExecution.getMaxYPos()) {
                            pos++;
                        }
                    }
                }
                if (pos > 0) {
                    execution.setxPos(execution.getxPos() + pos * executionWidth / 2);
                }
            }
        }

        // Check minimum height of executions and set width
        for (SequenceExecution execution : executions) {
            float height = execution.getMaxYPos() - execution.getyPos();
            if (height < minHeight) {
                execution.setMaxYPos(execution.getyPos() + minHeight);
            }
            if (execution.getType().equals("Duration")
                    || execution.getType().equals("TimeConstraint")) {
                continue;
            }
            execution.setMaxXPos(execution.getxPos() + executionWidth);
        }
    }

    /**
     * Apply the layering to the SGraph and check for message overlappings.
     * 
     * @param layeredGraph
     *            the layered graph
     */
    private void applyMessageYCoords(final LGraph layeredGraph) {
        // Position of first layer of messages
        float layerpos = LIFELINE_Y_POS + messageSpacing;

        // Iterate the layers of nodes that represent messages
        for (int i = 0; i < layeredGraph.getLayers().size(); i++) {
            // Iterate the nodes of the layer
            for (LNode node : layeredGraph.getLayers().get(i).getNodes()) {
                // Get the corresponding message
                SMessage message = (SMessage) node.getProperty(Properties.ORIGIN);

                // Skip dummyNodes
                if (message == null) {
                    continue;
                }

                SLifeline lifeline = node.getProperty(SeqProperties.BELONGS_TO_LIFELINE);
                // This property is set only for splitted messages
                if (lifeline != null) {
                    // consider messages that have different layers for source and target
                    // nodes/heights
                    if (message.getTarget() == lifeline) {
                        message.setTargetYPos(layerpos);
                    } else {
                        message.setSourceYPos(layerpos);
                    }
                    continue;
                }

                if (message.getYPos() != -1.0f) {
                    // Skip iteration message if it was already set
                    continue;
                }

                int sourceXPos = message.getSource().getPosition();
                int targetXPos = message.getTarget().getPosition();

                // If the message crosses at least one lifeline, check for overlappings
                if (Math.abs(sourceXPos - targetXPos) > 1) {
                    // Check overlappings with any other node in the layer
                    for (LNode otherNode : layeredGraph.getLayers().get(i).getNodes()) {
                        // Get the corresponding message
                        SMessage otherMessage = (SMessage) otherNode.getProperty(Properties.ORIGIN);
                        try {
                            int otherSourcePos = otherMessage.getSource().getPosition();
                            int otherTargetPos = otherMessage.getTarget().getPosition();

                            // If the other message starts or ends between the start and the end
                            // of the tested message, there is an overlapping
                            if (isOverlapping(sourceXPos, targetXPos, otherSourcePos,
                                    otherTargetPos)) {
                                if (otherMessage.getYPos() != -1.0f) {
                                    // If the other message was already set, the message has to
                                    // be placed in another layer
                                    layerpos += messageSpacing;
                                    break;
                                } else if (Math.abs(otherSourcePos - otherTargetPos) <= 1) {
                                    // If the other message was not set and it is a short one,
                                    // the other message has to be set here
                                    otherMessage.setYPos(layerpos);
                                    layerpos += messageSpacing;
                                    break;
                                }
                            }
                        } catch (NullPointerException n) {
                            // Ignore
                        }

                    }
                }
                // Set the vertical position of the message
                message.setYPos(layerpos);

                // Handle selfloops
                if (message.getSource() == message.getTarget()) {
                    message.setSourceYPos(layerpos - messageSpacing / 2);
                }

            }
            layerpos += messageSpacing;
        }
    }

    /**
     * Check, if two messages are overlapping.
     * 
     * @param mess1source
     *            the source position of the first message
     * @param mess1target
     *            the target position of the first message
     * @param mess2source
     *            the source position of the second message
     * @param mess2target
     *            the target position of the second message
     * @return true if the messages are overlapping
     */
    private boolean isOverlapping(final int mess1source, final int mess1target,
            final int mess2source, final int mess2target) {

        return isBetween(mess1source, mess2source, mess2target)
                || isBetween(mess1target, mess2source, mess2target)
                || isBetween(mess2source, mess1source, mess1target)
                || isBetween(mess2target, mess1source, mess1target);
    }

    /**
     * Checks, if x is between bound1 and bound2.
     * 
     * @param x
     * @param bound1
     * @param bound2
     * @return true if x is between bound1 and bound2
     */
    private boolean isBetween(final int x, final int bound1, final int bound2) {
        // x is between 1 and 2 if it is not smaller than both or greater than both
        return !((x <= bound1 && x <= bound2) || (x >= bound1 && x >= bound2));
    }
}
