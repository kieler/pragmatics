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
 * 
 */
public class SequenceDiagramLayoutProvider extends AbstractLayoutProvider {
    /** Lifeline ordering algorithm */
    private ILifelineSorter lifelineSorter;
    /** Graph importer */
    private SGraphImporter importer;
    /** Border spacing */
    private float borderSpacing;
    /** Horizontal spacing between two neighbored lifelines */
    private float lifelineSpacing;
    /** Vertical spacing between two neighbored layers of messages */
    private float messageSpacing;
    /** Vertical size of lifeline header */
    private float lifelineHeader = 30;
    /** Vertical space above lifelines */
    private float lifelineYPos = 10;
    /** Height of the header of areas */
    private float areaHeaderHeight = 25;

    @Override
    public void doLayout(KNode parentNode, IKielerProgressMonitor progressMonitor) {
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
    public void applyLayout(SGraph graph, List<SLifeline> lifelineOrder, KNode parentNode) {
        float xPos = borderSpacing;

        // Empiric factor that is applied to message y coordinates in order to fix post-processing
        // by the papyrus framework
        float edgeYCoordFactor = 1 + (28 / (graph.getSizeY() + messageSpacing));

        // Check containments of areas
        List<SequenceArea> areas = graph.getProperty(PapyrusProperties.AREAS);
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

        // Arrange comments
        for (SComment comment : graph.getComments()) {
            SMessage message = null;
            SLifeline lifeline = null;
            // Get random connected message and lifeline if existing
            // This may be optimized if there are more than one connection
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

        List<KPoint> interactionTargetPoints = new LinkedList<KPoint>();

        // Set position for lifelines/nodes
        for (SLifeline lifeline : lifelineOrder) {
            KNode node = (KNode) lifeline.getProperty(Properties.ORIGIN);
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);

            if (nodeLayout.getProperty(PapyrusProperties.NODE_TYPE).equals(
                    "PapyrusUMLSequenceDiagram")) {
                // Set position of the surrounding node
                nodeLayout.setYpos(borderSpacing);
                nodeLayout.setXpos(borderSpacing);
                break;
            }

            // Check maximum size of comments attached to the lifeline
            float maxCommentWidth = lifelineSpacing;
            List<SComment> comments = lifeline.getProperty(SeqProperties.COMMENTS);
            for (SComment comment : comments) {
                if (comment.getWidth() > maxCommentWidth) {
                    maxCommentWidth = comment.getWidth();
                }
            }

            // Set position and height for the lifeline. This may be overridden if there are create-
            // or delete-messages involved.
            nodeLayout.setYpos(lifelineYPos);
            nodeLayout.setXpos(xPos);
            nodeLayout.setHeight(lifelineHeader + graph.getSizeY() + messageSpacing);

            // Check if there are any comments that have to be placed near the lifeline
            int numComments = lifeline.getProperty(SeqProperties.COMMENTS).size();
            if (numComments > 0) {
                // HashMap that organizes which comment belongs to which message. This is important
                // if there are more than one comments at a message.
                HashMap<SMessage, SComment> hash = new HashMap<SMessage, SComment>(numComments);
                for (SComment comment : lifeline.getProperty(SeqProperties.COMMENTS)) {
                    if (comment.getLifeline() == lifeline) {
                        SMessage message = comment.getMessage();

                        // Place comment in the center of the message if it is smaller than
                        // lifelineSpacing
                        float commentXPos = xPos + nodeLayout.getWidth();
                        if (comment.getWidth() < maxCommentWidth) {
                            commentXPos += (maxCommentWidth - comment.getWidth()) / 2;
                        }

                        // Place comment above the message
                        float commentYPos = message.getSourceYPos() + lifelineHeader + lifelineYPos
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
                            commentYPos = message.getSourceYPos() + lifelineHeader + lifelineYPos
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
            }

            // Apply maximum comment width to new xPos
            xPos += nodeLayout.getWidth() + maxCommentWidth;
            // Reset the graph's horizontal size
            if (graph.getSizeX() < xPos) {
                graph.setSizeX(xPos);
            }

            List<SequenceExecution> executions = lifeline.getProperty(PapyrusProperties.EXECUTIONS);

            // Handle outgoing messages of the lifeline
            for (SMessage message : lifeline.getOutgoingMessages()) {
                KEdge edge = (KEdge) message.getProperty(Properties.ORIGIN);
                KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                KPoint sourcePoint = edgeLayout.getSourcePoint();
                sourcePoint.setY(message.getSourceYPos() * edgeYCoordFactor - 1);
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
                    targetPoint.setY(message.getTargetYPos() - borderSpacing);
                    interactionTargetPoints.add(targetPoint);
                }
            }

            // Handle incoming messages of the lifeline
            for (SMessage message : lifeline.getIncomingMessages()) {
                KEdge edge = (KEdge) message.getProperty(Properties.ORIGIN);
                KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                KPoint targetPoint = edgeLayout.getTargetPoint();

                targetPoint.setY(message.getTargetYPos() * edgeYCoordFactor - 1);
                targetPoint.setX(nodeLayout.getXpos() + nodeLayout.getWidth() / 2);

                // Resize lifeline if incoming message is a create or delete message
                if (message.getProperty(SeqProperties.MESSAGE_TYPE) == MessageType.CREATE) {
                    targetPoint.setX(nodeLayout.getXpos());
                    // Set lifeline's yPos to the yPos of the create-message
                    nodeLayout.setYpos(message.getTargetYPos() + lifelineYPos + lifelineHeader / 2);
                    // Modify height of lifeline in order to compensate yPos changes
                    nodeLayout.setHeight(nodeLayout.getHeight() - message.getTargetYPos()
                            - lifelineHeader / 2);
                } else if (message.getProperty(SeqProperties.MESSAGE_TYPE) == MessageType.DELETE) {
                    // Modify height of lifeline in order to end at the yPos of the delete-message
                    nodeLayout
                            .setHeight(nodeLayout.getHeight()
                                    - (graph.getSizeY() + messageSpacing - (message.getTargetYPos() + lifelineHeader)));
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
                    sourcePoint.setY(message.getSourceYPos() - borderSpacing);
                    sourcePoint.setX(borderSpacing);
                }
            }

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
                // Set xPos, maxXPos and height / maxYPos
                arrangeExecutions(executions, nodeLayout.getWidth());

                nodeLayout.setProperty(PapyrusProperties.EXECUTIONS, executions);
                for (SequenceExecution execution : executions) {
                    Object executionObj = execution.getOrigin();
                    if (executionObj instanceof KNode) {
                        KNode executionNode = (KNode) executionObj;
                        KShapeLayout shapelayout = executionNode.getData(KShapeLayout.class);
                        if (execution.getType().equals("Duration")
                                || execution.getType().equals("TimeConstraint")) {
                            execution.setyPos(execution.getyPos() + 20); // FIXME constants? why?
                            execution.setMaxYPos(execution.getMaxYPos() + 20);
                        }
                        shapelayout.setXpos(execution.getxPos());
                        shapelayout.setYpos(execution.getyPos() - 10); // TODO why??
                        shapelayout.setWidth(execution.getMaxXPos() - execution.getxPos());
                        shapelayout.setHeight(execution.getMaxYPos() - execution.getyPos());

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

                                    edgeLayout.getTargetPoint().setX(
                                            newXPos + shapelayout.getWidth());
                                } else if (mess.getSource() == lifeline) {
                                    if (!toLeft) {
                                        newXPos += shapelayout.getWidth();
                                    }
                                    edgeLayout.getSourcePoint().setX(newXPos);
                                } else {
                                    if (toLeft) {
                                        newXPos += shapelayout.getWidth();
                                    }
                                    edgeLayout.getTargetPoint().setX(newXPos);
                                }
                            }
                        }
                    }
                }
            }
        }

        // Handle areas (interactions / combined fragments / interaction operands)
        KShapeLayout parentLayout = parentNode.getData(KShapeLayout.class);
        if (areas != null) {
            // The offset of an area that contains another area (spacing between them)
            int containmentOffset = 5;

            // Set size and position of area
            for (SequenceArea area : areas) {
                if (area.getMessages().size() > 0) {
                    setAreaPositionByMessages(area, edgeYCoordFactor);
                } else {
                    setAreaPositionByLifelinesAndMessage(area, edgeYCoordFactor);
                }
                KNode areaNode = (KNode) area.getOrigin();
                KShapeLayout areaLayout = areaNode.getData(KShapeLayout.class);

                // Check if there are contained areas
                int containmentDepth = checkContainment(area);
                // If so, an offset has to be calculated in order not to have overlapping borders
                int containmentSpacing = containmentDepth * containmentOffset;

                areaLayout.setXpos(area.getxPos() - lifelineSpacing / 2 - containmentSpacing);
                areaLayout.setWidth(area.getWidth() + lifelineSpacing + containmentSpacing * 2);

                areaLayout.setYpos(area.getyPos() + lifelineHeader - messageSpacing / 2
                        - containmentSpacing);
                areaLayout.setHeight(area.getHeight() + messageSpacing + containmentSpacing * 2);

                // Handle interaction operands
                if (area.getSubAreas().size() > 0) {
                    // Reset area yPos and height if subAreas exists (to have a "header" that isn't
                    // occupied by any subArea)
                    areaLayout.setYpos(area.getyPos() - messageSpacing / 2);
                    areaLayout.setHeight(area.getHeight() + messageSpacing + lifelineHeader);

                    float lastPos = 0;
                    KShapeLayout lastLayout = null;
                    for (SequenceArea subArea : area.getSubAreas()) {

                        KNode subAreaNode = (KNode) subArea.getOrigin();
                        KShapeLayout subAreaLayout = subAreaNode.getData(KShapeLayout.class);

                        subAreaLayout.setXpos(0);
                        subAreaLayout.setWidth(area.getWidth() + lifelineSpacing - 2);
                        if (subArea.getMessages().size() > 0) {
                            // Calculate and set y-position by the area's messages
                            setAreaPositionByMessages(subArea, edgeYCoordFactor);
                            subAreaLayout.setYpos(subArea.getyPos() - area.getyPos()
                                    + lifelineHeader - messageSpacing / 2);
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
                                - areaHeaderHeight);
                    }
                }
            }
        }

        // Place comments
        float commentMaxExtraWidth = 0;
        float commentNextYPos = lifelineHeader + lifelineYPos;
        for (SComment comment : graph.getComments()) {
            if (comment.getMessage() != null) {
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

        // Set position and size of surrounding interaction
        parentLayout.setWidth(graph.getSizeX());
        parentLayout.setHeight(graph.getSizeY() + messageSpacing + lifelineHeader + lifelineYPos);
        parentLayout.setPos(borderSpacing, borderSpacing);

        // Set target x position of messages to surrounding interaction
        for (KPoint targetPoint : interactionTargetPoints) {
            targetPoint.setX(graph.getSizeX() + borderSpacing);
        }
    }

    /**
     * Add dummy nodes to the layered graph in order to allocate space for empty areas
     * 
     * @param graph
     *            the SGraph
     * @param lgraph
     *            the layered graph
     */
    private void placeEmptyAreas(SGraph graph, LGraph lgraph) {
        List<SequenceArea> areas = graph.getProperty(PapyrusProperties.AREAS);
        if (areas != null) {
            for (SequenceArea area : areas) {
                if (area.getMessages().size() == 0) {
                    if (area.getNextMessage() != null) {
                        LNode node = importer.layeredMap.get(area.getNextMessage());
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
    private void placeCreatedLifelines(SGraph sgraph, LGraph lgraph) {
        for (SLifeline lifeline : sgraph.getLifelines()) {
            for (SMessage message : lifeline.getIncomingMessages()) {
                if (message.getProperty(SeqProperties.MESSAGE_TYPE) == MessageType.CREATE) {
                    // Add dummy below this messages node
                    LNode node = importer.layeredMap.get(message);
                    createDummyNode(lgraph, node, false);
                }
            }
        }
    }

    /**
     * Place the comments near the element(s) that they are attached to if attached to any element
     * 
     * @param graph
     *            the SGraph
     * @param lgraph
     *            the layered graph
     */
    private void placeComments(SGraph graph, LGraph lgraph) {
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
                LNode node = importer.layeredMap.get(attachedMess);
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
    private void createDummyNode(LGraph lgraph, LNode node, boolean beforeNode) {
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
    private int checkContainment(SequenceArea area) {
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
    private void setAreaPositionByMessages(SequenceArea area, float factor) {
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
                float sourceYPos = 1 + layout.getSourcePoint().getY() / factor;
                if (sourceYPos < minY) {
                    minY = sourceYPos;
                }
                if (sourceYPos > maxY) {
                    maxY = sourceYPos;
                }
                float targetYPos = 1 + layout.getTargetPoint().getY() / factor;
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
     * direction and above the next message
     * 
     * @param area
     *            the SequenceArea
     * @param factor
     *            the edgeYpos factor, that is necessary to compute the real position of the
     *            SMessage
     */
    private void setAreaPositionByLifelinesAndMessage(SequenceArea area, float factor) {
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
                messageYPos = 1 + layout.getSourcePoint().getY() / factor;
            } else {
                messageYPos = 1 + layout.getTargetPoint().getY() / factor;
            }
            area.setHeight(messageSpacing);
            area.setyPos(messageYPos - area.getHeight() - messageSpacing);
        }
    }

    /**
     * Set x position and width of an execution and check for minimum height
     * 
     * @param executions
     *            List of {@link SequenceExecution} at the given {@link SLifeline}
     * @param parentWidth
     *            Width of the {@link SLifeline}
     */
    private void arrangeExecutions(List<SequenceExecution> executions, float parentWidth) {
        float minHeight = 20;
        float executionWidth = 16;

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
     * Apply the layering to the SGraph and check for message overlappings
     * 
     * @param layeredGraph
     *            the layered graph
     */
    private void applyMessageYCoords(LGraph layeredGraph) {
        // Position of first layer of messages
        float layerpos = messageSpacing;

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
     * Check, if two messages are overlapping
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
    private boolean isOverlapping(int mess1source, int mess1target, int mess2source, int mess2target) {
        if (isBetween(mess1source, mess2source, mess2target)) {
            return true;
        } else if (isBetween(mess1target, mess2source, mess2target)) {
            return true;
        } else if (isBetween(mess2source, mess1source, mess1target)) {
            return true;
        } else if (isBetween(mess2target, mess1source, mess1target)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks, if x is between bound1 and bound2
     * 
     * @param x
     * @param bound1
     * @param bound2
     * @return true if x is between bound1 and bound2
     */
    private boolean isBetween(int x, int bound1, int bound2) {
        if (x <= bound1 && x <= bound2) {
            return false;
        } else if (x >= bound1 && x >= bound2) {
            return false;
        } else {
            return true;
        }
    }
}
