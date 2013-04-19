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
import de.cau.cs.kieler.core.kgraph.KLabel;
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
import de.cau.cs.kieler.papyrus.sequence.sorter.EqualDistributionLifelineSorter;
import de.cau.cs.kieler.papyrus.sequence.sorter.InteractiveLifelineSorter;
import de.cau.cs.kieler.papyrus.sequence.sorter.LayerbasedLifelineSorter;
import de.cau.cs.kieler.papyrus.sequence.sorter.LifelineSortingStrategy;

/**
 * Layout algorithm for Papyrus sequence diagrams.
 * 
 * @author grh
 * @kieler.design 2012-11-20 cds, msp
 * @kieler.rating proposed yellow grh
 * 
 */
public class SequenceDiagramLayoutProvider extends AbstractLayoutProvider {
    /** Constant that is needed to calculate some offsets. */
    private static final int TWENTY = 20;
    /** Constant that is needed to calculate some offsets. */
    private static final int FOURTY = 40;
    /** The vertical spacing between message and label. */
    private static final int LABELSPACING = 5;
    /** The horizontal margin for message labels. */
    private static final int LABELMARGIN = 10;

    /** The height of the lifeline's header. */
    private int lifelineHeader;
    /** The vertical position of lifelines. */
    private int lifelineYPos;
    /** The height of the header of combined fragments. */
    private int areaHeader;
    /** The offset between two nested areas. */
    private int containmentOffset;

    /** Border spacing. */
    private double borderSpacing;
    /** Horizontal spacing between two neighbored lifelines. */
    private double lifelineSpacing;
    /** Vertical spacing between two neighbored layers of messages. */
    private double messageSpacing;
    /** The label alignment strategy. */
    private LabelAlignment labelAlignment;

    @Override
    public void doLayout(final KNode parentNode, final IKielerProgressMonitor progressMonitor) {
        // Prevent the surrounding diagram from being laid out
        if (parentNode.getParent() == null) {
            return;
        }

        progressMonitor.begin("Sequence Diagrem Layouter", 1 + 1 + 1 + 1 + 1 + 1);

        // Get layout properties
        KShapeLayout sourceShapeLayout = parentNode.getData(KShapeLayout.class);
        borderSpacing = sourceShapeLayout.getProperty(Properties.BORDER_SPACING);
        lifelineHeader = sourceShapeLayout.getProperty(SequenceDiagramProperties.LIFELINE_HEADER);
        lifelineYPos = sourceShapeLayout.getProperty(SequenceDiagramProperties.LIFELINE_Y_POS);
        areaHeader = sourceShapeLayout.getProperty(SequenceDiagramProperties.AREA_HEADER);
        containmentOffset = sourceShapeLayout
                .getProperty(SequenceDiagramProperties.CONTAINMENT_OFFSET);
        lifelineSpacing = sourceShapeLayout.getProperty(SequenceDiagramProperties.LIFELINE_SPACING);
        messageSpacing = sourceShapeLayout.getProperty(SequenceDiagramProperties.MESSAGE_SPACING);
        labelAlignment = sourceShapeLayout.getProperty(SequenceDiagramProperties.LABEL_ALIGNMENT);
        LifelineSortingStrategy strategy = sourceShapeLayout
                .getProperty(SequenceDiagramProperties.LIFELINE_SORTING);

        // Lifeline ordering algorithm.
        ILifelineSorter lifelineSorter;
        if (strategy == LifelineSortingStrategy.LAYER_BASED) {
            lifelineSorter = new LayerbasedLifelineSorter();
        } else if (strategy == LifelineSortingStrategy.SHORT_MESSAGES) {
            // The short messages lifeline sorter has an additional layout option
            boolean groupAreas = false;
            if (sourceShapeLayout.getProperty(SequenceDiagramProperties.GROUP_AREAS)) {
                groupAreas = true;
            }
            lifelineSorter = new EqualDistributionLifelineSorter(groupAreas);
        } else {
            lifelineSorter = new InteractiveLifelineSorter();
        }

        // Convert the KGraph into a SGraph
        SGraphImporter importer = new SGraphImporter();
        SGraph sgraph = importer.importGraph(parentNode, progressMonitor.subTask(1));

        // Build layeredGraph of the edges of the SGraph
        LGraph layeredGraph = importer.createLayeredGraph(sgraph, progressMonitor.subTask(1));

        // Allocate space for various objects
        allocateSpace(sgraph, layeredGraph);

        // Break the cycles in the layeredGraph
        SCycleBreaker cycleBreaker = new SCycleBreaker();
        cycleBreaker.breakCycles(layeredGraph, progressMonitor.subTask(1));

        // Find a layering for the layeredGraph
        NetworkSimplexLayerer layerer = new NetworkSimplexLayerer();
        layerer.process(layeredGraph, progressMonitor.subTask(1));

        // Order lifelines by the chosen lifeline sorting algorithm
        List<SLifeline> lifelineOrder = lifelineSorter.sortLifelines(sgraph, layeredGraph,
                progressMonitor.subTask(1));

        // Calculate the coordinates for all the objects of the diagram
        calculateCoordinates(sgraph, layeredGraph, lifelineOrder, progressMonitor.subTask(1));

        // Apply the calculated layout to the KGraph
        applyLayout(sgraph, lifelineOrder, parentNode);

        progressMonitor.done();
    }

    /**
     * Allocate vertical space for various objects by introducing dummy nodes.
     * 
     * @param sgraph
     *            the sequence graph
     * @param layeredGraph
     *            the layered graph
     */
    private void allocateSpace(final SGraph sgraph, final LGraph layeredGraph) {
        // Allocate space for the header of combined fragments
        allocateCFSpace(sgraph, layeredGraph);

        // Allocate space for comments by introducing dummy nodes
        allocateCommentSpace(sgraph, layeredGraph);

        // Allocate space for empty areas
        allocateEmptyAreaSpace(sgraph, layeredGraph);
    }

    /**
     * Allocate space for the header of combined fragments.
     * 
     * @param sgraph
     *            the SGraph
     * @param lgraph
     *            the layered graph
     */
    private void allocateCFSpace(final SGraph sgraph, final LGraph lgraph) {
        // Add dummy nodes before the first messages of combined fragments to have enough space
        // above the topmost message of the area
        List<SequenceArea> areas = sgraph.getProperty(PapyrusProperties.AREAS);
        if (areas != null) {
            for (SequenceArea area : areas) {
                if (area.getSubAreas().size() > 0) {
                    // Find the uppermost message contained in the combined fragment
                    double minYPos = Double.MAX_VALUE;
                    SMessage uppermostMessage = null;
                    for (Object messageObj : area.getMessages()) {
                        SMessage message = (SMessage) messageObj;
                        if (message.getSourceYPos() < minYPos) {
                            minYPos = message.getSourceYPos();
                            uppermostMessage = message;
                        }
                    }
                    if (uppermostMessage != null) {
                        LNode node = uppermostMessage
                                .getProperty(SequenceDiagramProperties.LAYERED_NODE);
                        createDummyNode(lgraph, node, true);
                    }
                }
            }
        }
    }

    /**
     * Allocate space for placing the comments near to their attached elements.
     * 
     * @param graph
     *            the SGraph
     * @param lgraph
     *            the layered graph
     */
    private void allocateCommentSpace(final SGraph graph, final LGraph lgraph) {
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
                double height = comment.getSize().y;
                int dummys = (int) Math.ceil(height / messageSpacing);
                // Add dummy nodes in the layered graph
                LNode node = attachedMess.getProperty(SequenceDiagramProperties.LAYERED_NODE);
                if (node != null) {
                    for (int i = 0; i < dummys; i++) {
                        createDummyNode(lgraph, node, true);
                    }
                    comment.setMessage(attachedMess);
                    attachedMess.getComments().add(comment);
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
    private void allocateEmptyAreaSpace(final SGraph graph, final LGraph lgraph) {
        List<SequenceArea> areas = graph.getProperty(PapyrusProperties.AREAS);
        if (areas != null) {
            for (SequenceArea area : areas) {
                if (area.getMessages().size() == 0) {
                    Object nextMess = area.getNextMessage();
                    if (nextMess != null) {
                        LNode node = ((SMessage) nextMess)
                                .getProperty(SequenceDiagramProperties.LAYERED_NODE);
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
    // private void allocateSpaceForCreatedLifelines(final SGraph sgraph, final LGraph lgraph) {
    // for (SLifeline lifeline : sgraph.getLifelines()) {
    // for (SMessage message : lifeline.getIncomingMessages()) {
    // if (message.getProperty(SequenceDiagramProperties.MESSAGE_TYPE) == MessageType.CREATE) {
    // // Add dummy below this messages node
    // LNode node = message.getProperty(SequenceDiagramProperties.LAYERED_NODE);
    // createDummyNode(lgraph, node, false);
    // }
    // }
    // }
    // }

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
     * Calculate the final coordinates for all the objects in the graph.
     * 
     * @param graph
     *            the sequence graph
     * @param lifelineOrder
     *            the order of the lifelines
     */
    private void calculateCoordinates(final SGraph graph, final LGraph layeredGraph,
            final List<SLifeline> lifelineOrder, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Calculate coordinates", 1);
        
        // Initialize graph size
        graph.getSize().x = 0;
        graph.getSize().y = 0;

        // Assign vertical position to SMessages
        calculateMessageYCoords(layeredGraph);

        // Arrange comments that are connected to a message or lifeline
        arrangeConnectedComments(graph);

        // Position of the next lifeline (at first, of the first lifeline)
        double xPos = borderSpacing;

        // The height of all "normal-sized" (not affected by create or delete messages) lifelines
        double lifelinesHeight = lifelineHeader + graph.getSize().y + messageSpacing;

        // Set position for lifelines/nodes
        for (SLifeline lifeline : lifelineOrder) {

            // Dummy lifelines don't need any layout
            if (lifeline.getName().equals("DummyLifeline")) {
                continue;
            }

            // Calculate the spacing between this lifeline and its successor. Place comments.
            double thisLifelinesSpacing = calculateLifelineSpacing(xPos, lifeline);

            // Set position and height for the lifeline. This may be overridden if there are create-
            // or delete-messages involved.
            lifeline.getPosition().y = lifelineYPos;
            lifeline.getPosition().x = xPos;
            lifeline.getSize().y = lifelinesHeight;

            // Apply maximum comment width to new xPos
            xPos += lifeline.getSize().x + thisLifelinesSpacing;
            // Reset the graph's horizontal size
            if (graph.getSize().x < xPos) {
                graph.getSize().x = xPos;
            }
        }

        // Arrange unconnected comments (after the last lifeline)
        arrangeUnconnectedComments(graph);

        // Handle areas (interactions / combined fragments / interaction operands)
        List<SequenceArea> areas = graph.getProperty(PapyrusProperties.AREAS);
        // Check containments (hierarchy) of areas
        checkAreaContainment(areas);
        // Calculate the areas positions
        calculateAreaPosition(areas);

        progressMonitor.done();
    }

    /**
     * Calculate the spacing between the current lifeline and its successor. This is done by
     * considering the width of the comments between them and by the width of the widest label
     * attached to a message between them.
     * 
     * @param xPos
     *            the horizontal position where the last lifeline was placed
     * @param lifeline
     *            the current lifeline
     * @return the width of the widest comment
     */
    private double calculateLifelineSpacing(final double xPos, final SLifeline lifeline) {

        // Initialize spacing with the normal lifeline spacing
        double spacing = lifelineSpacing;

        // Check, if there are labels longer than the available space
        for (SMessage message : lifeline.getIncomingMessages()) {
            if (message.getLabelWidth() > spacing + lifeline.getSize().x) {
                spacing = LABELMARGIN + message.getLabelWidth() - lifeline.getSize().x;
            }
        }
        for (SMessage message : lifeline.getOutgoingMessages()) {
            if (message.getLabelWidth() > spacing + lifeline.getSize().x) {
                spacing = LABELMARGIN + message.getLabelWidth() - lifeline.getSize().x;
            }
            // Labels of create messages should not overlap the target's header
            if (message.getProperty(SequenceDiagramProperties.MESSAGE_TYPE) == MessageType.CREATE) {
                if (message.getLabelWidth() + LABELMARGIN > spacing + lifeline.getSize().x / 2) {
                    spacing = LABELMARGIN + message.getLabelWidth()
                            - message.getTarget().getSize().x / 2;
                }
            } 
            // Selfloops need a little more space
            if (message.getSource() == message.getTarget()) {
                if (message.getLabelWidth() + LABELMARGIN + messageSpacing / 2 
                        > spacing + lifeline.getSize().x / 2) {
                    spacing = LABELMARGIN + message.getLabelWidth() - lifeline.getSize().x / 2;
                }
            }
        }

        // Get the list of comments attached to the current lifeline
        List<SComment> comments = lifeline.getComments();

        // Return if there are no comments attached
        if (comments == null) {
            return spacing;
        }

        // Check maximum size of comments attached to the lifeline
        for (SComment comment : comments) {
            if (comment.getSize().x > spacing) {
                spacing = comment.getSize().x;
            }
        }

        // HashMap that organizes which comment belongs to which message. This is important
        // if there are more than one comments at a message.
        HashMap<SMessage, SComment> hash = new HashMap<SMessage, SComment>(comments.size());
        for (SComment comment : comments) {
            if (comment.getLifeline() == lifeline) {
                SMessage message = comment.getMessage();

                // Place comment in the center of the message if it is smaller than
                // lifelineSpacing
                double commentXPos = xPos + lifeline.getSize().x;
                if (comment.getSize().x < spacing) {
                    commentXPos += (spacing - comment.getSize().x) / 2;
                }

                // Place comment above the message
                double commentYPos = message.getSourceYPos() + lifelineHeader + lifelineYPos
                        - (comment.getSize().y + messageSpacing);

                comment.getPosition().x = commentXPos;
                comment.getPosition().y = commentYPos;

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
                            - (lower.getSize().y + messageSpacing);
                    lower.getPosition().y = commentYPos;

                    // Place upper comment near to lower one
                    double uYpos = lower.getPosition().y - upper.getSize().y - messageSpacing / 2;
                    upper.getPosition().y = uYpos;
                } else {
                    hash.put(message, comment);
                }
            }
        }
        return spacing;
    }

    /**
     * Apply the layering to the SGraph and check for message overlappings.
     * 
     * @param layeredGraph
     *            the layered graph
     */
    private void calculateMessageYCoords(final LGraph layeredGraph) {
        // Position of first layer of messages
        double layerpos = lifelineYPos + messageSpacing;

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

                SLifeline lifeline = node
                        .getProperty(SequenceDiagramProperties.BELONGS_TO_LIFELINE);
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

                if (message.isLayerPositionSet()) {
                    // Skip iteration message if it was already set
                    continue;
                }

                int sourceXPos = message.getSource().getHorizontalSlot();
                int targetXPos = message.getTarget().getHorizontalSlot();

                // If the message crosses at least one lifeline, check for overlappings
                if (Math.abs(sourceXPos - targetXPos) > 1) {
                    // Check overlappings with any other node in the layer
                    for (LNode otherNode : layeredGraph.getLayers().get(i).getNodes()) {
                        // Get the corresponding message
                        SMessage otherMessage = (SMessage) otherNode.getProperty(Properties.ORIGIN);
                        try {
                            int otherSourcePos = otherMessage.getSource().getHorizontalSlot();
                            int otherTargetPos = otherMessage.getTarget().getHorizontalSlot();

                            // If the other message starts or ends between the start and the end
                            // of the tested message, there is an overlapping
                            if (isOverlapping(sourceXPos, targetXPos, otherSourcePos,
                                    otherTargetPos)) {
                                if (otherMessage.isLayerPositionSet()) {
                                    // If the other message was already set, this message has to
                                    // be placed in another layer
                                    layerpos += messageSpacing;
                                    break;
                                } else if (Math.abs(otherSourcePos - otherTargetPos) <= 1) {
                                    // If the other message was not set and it is a short one,
                                    // the other message has to be set here
                                    otherMessage.setLayerYPos(layerpos);
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
                message.setLayerYPos(layerpos);

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

    /**
     * Preprocessor that does some organizing stuff for connected comment objects.
     * 
     * @param graph
     *            the SequenceGraph
     */
    private void arrangeConnectedComments(final SGraph graph) {
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
             * beginning or near the end of the message. If the comment is attached to a message and
             * one of the message's lifelines, it should be drawn near that lifeline (this is the
             * case for time observations for example).
             */
            if (message != null) {
                SLifeline right, left;
                if (message.getSource().getHorizontalSlot() < message.getTarget()
                        .getHorizontalSlot()) {
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
                    int position = right.getHorizontalSlot();
                    for (SLifeline ll : graph.getLifelines()) {
                        if (ll.getHorizontalSlot() == position - 1) {
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
     * Calculate positions for comments that are not connected to any element. They will be drawn
     * after the last lifeline.
     * 
     * @param graph
     *            the sequence graph
     */
    private void arrangeUnconnectedComments(final SGraph graph) {
        // The width of the widest comment that has to be placed after the last lifeline
        double commentMaxExtraWidth = 0;
        // The vertical position of the next comment that is drawn after the last lifeline
        double commentNextYPos = lifelineHeader + lifelineYPos;

        for (SComment comment : graph.getComments()) {
            if (comment.getMessage() == null) {
                // Unconnected comments
                if (comment.getSize().x > commentMaxExtraWidth) {
                    commentMaxExtraWidth = comment.getSize().x;
                }
                // Set position of unconnected comments next to the last lifeline
                comment.getPosition().x = graph.getSize().x;
                comment.getPosition().y = commentNextYPos;
                commentNextYPos += comment.getSize().y + messageSpacing;
            }
        }

        if (commentMaxExtraWidth > 0) {
            graph.getSize().x += lifelineSpacing + commentMaxExtraWidth;
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
                            if (area.getPosition().y < otherArea.getPosition().y
                                    && area.getPosition().x < otherArea.getPosition().x) {
                                area.getContainedAreas().add(otherArea);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Calculate the position of the areas (interactionUse, combined fragment).
     * 
     * @param areas
     *            the list of areas in the graph
     */
    private void calculateAreaPosition(final List<SequenceArea> areas) {
        if (areas != null) {
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
                int containmentDepth = checkHierarchy(area);
                // If so, an offset has to be calculated in order not to have overlapping borders
                int containmentSpacing = containmentDepth * containmentOffset;

                areaLayout.setXpos((float) (area.getPosition().x - TWENTY - lifelineSpacing / 2 
                        - containmentSpacing));
                areaLayout.setWidth((float) (area.getSize().x + FOURTY + lifelineSpacing 
                        + 2 * containmentSpacing));

                areaLayout.setYpos((float) (area.getPosition().y + lifelineHeader - messageSpacing / 2 
                        - containmentSpacing));
                areaLayout.setHeight((float) (area.getSize().y + messageSpacing 
                        + 2 * containmentSpacing));

                // Handle interaction operands
                if (area.getSubAreas().size() > 0) {
                    // Reset area yPos and height if subAreas exists (to have a "header" that isn't
                    // occupied by any subArea)
                    areaLayout.setYpos((float) (area.getPosition().y - messageSpacing / 2));
                    areaLayout.setHeight((float) (area.getSize().y + messageSpacing + lifelineHeader));

                    double lastPos = 0;
                    KShapeLayout lastLayout = null;
                    for (SequenceArea subArea : area.getSubAreas()) {
                        KNode subAreaNode = (KNode) subArea.getOrigin();
                        KShapeLayout subAreaLayout = subAreaNode.getData(KShapeLayout.class);

                        subAreaLayout.setXpos(0);
                        subAreaLayout.setWidth((float) (area.getSize().x + FOURTY + lifelineSpacing 
                                - 2));
                        if (subArea.getMessages().size() > 0) {
                            // Calculate and set y-position by the area's messages
                            setAreaPositionByMessages(subArea);
                            subAreaLayout.setYpos((float) (subArea.getPosition().y
                                    - area.getPosition().y + lifelineHeader - messageSpacing / 2));
                        } else {
                            // Calculate and set y-position by the available space
                            subAreaLayout.setYpos((float) lastPos);
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
                        lastLayout.setHeight((float) (areaLayout.getHeight() - lastLayout.getYpos() 
                                - areaHeader));
                    }
                }
            }
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
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = 0;
        double maxY = 0;
        // Compute the bounding box of all contained messages
        for (Object messObj : area.getMessages()) {
            if (messObj instanceof SMessage) {
                // Compute new y coordinates
                SMessage message = (SMessage) messObj;
                double sourceYPos = message.getSourceYPos();
                if (sourceYPos < minY) {
                    minY = sourceYPos;
                }
                if (sourceYPos > maxY) {
                    maxY = sourceYPos;
                }
                double targetYPos = message.getTargetYPos();
                if (targetYPos < minY) {
                    minY = targetYPos;
                }
                if (targetYPos > maxY) {
                    maxY = targetYPos;
                }
                // Compute new x coordinates
                SLifeline sourceLL = message.getSource();
                double sourceXPos = sourceLL.getPosition().x + sourceLL.getSize().x / 2;
                if (sourceXPos < minX) {
                    minX = sourceXPos;
                }
                if (sourceXPos > maxX) {
                    maxX = sourceXPos;
                }
                SLifeline targetLL = message.getTarget();
                double targetXPos = targetLL.getPosition().x + targetLL.getSize().x / 2;
                if (targetXPos < minX) {
                    minX = targetXPos;
                }
                if (targetXPos > maxX) {
                    maxX = targetXPos;
                }
            }
        }
        area.getPosition().x = minX;
        area.getPosition().y = minY;
        area.getSize().x = maxX - minX;
        area.getSize().y = maxY - minY;
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
        double minXPos = Double.MAX_VALUE;
        double maxXPos = 0;
        for (Object lifelineObj : area.getLifelines()) {
            SLifeline lifeline = (SLifeline) lifelineObj;
            KNode node = (KNode) lifeline.getProperty(Properties.ORIGIN);
            KShapeLayout layout = node.getData(KShapeLayout.class);
            double lifelineCenter = layout.getXpos() + layout.getWidth() / 2;
            if (lifelineCenter < minXPos) {
                minXPos = lifelineCenter;
            }
            if (lifelineCenter > maxXPos) {
                maxXPos = lifelineCenter;
            }
        }
        area.getPosition().x = minXPos;
        area.getSize().x = maxXPos - minXPos;

        // Set yPos and height according to the next message's yPos
        if (area.getNextMessage() != null) {
            Object messageObj = area.getNextMessage();
            SMessage message = (SMessage) messageObj;
            KEdge edge = (KEdge) message.getProperty(Properties.ORIGIN);
            KEdgeLayout layout = edge.getData(KEdgeLayout.class);
            double messageYPos;
            if (layout.getSourcePoint().getY() < layout.getTargetPoint().getY()) {
                messageYPos = message.getSourceYPos();
            } else {
                messageYPos = message.getTargetYPos();
            }
            area.getSize().y = messageSpacing;
            area.getPosition().y = messageYPos - area.getSize().y - messageSpacing;
        }
    }

    /**
     * Check recursively if an area has contained areas and return the maximum depth.
     * 
     * @param area
     *            the {@link SequenceArea}
     * @return the maximum depth of hierarchy
     */
    private int checkHierarchy(final SequenceArea area) {
        if (area.getContainedAreas().size() > 0) {
            int maxLevel = 0;
            for (SequenceArea subArea : area.getContainedAreas()) {
                int level = checkHierarchy(subArea);
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
     * Apply the calculated coordinates to the KGraph.
     * 
     * @param graph
     *            the SGraph
     * @param parentNode
     *            the parent layout node
     */
    private void applyLayout(final SGraph graph, final List<SLifeline> lifelineOrder,
            final KNode parentNode) {
        // The height of the diagram (the surrounding interaction)
        double diagramHeight = graph.getSize().y + messageSpacing + lifelineHeader + lifelineYPos;
        
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
                // This is the surrounding node
                break;
            }

            // Handle messages of the lifeline and their labels
            List<SequenceExecution> executions = lifeline.getProperty(PapyrusProperties.EXECUTIONS);
            applyMessageCoordinates(diagramHeight, graph, lifelineOrder, lifeline, executions);

            // Apply execution coordinates and adjust positions of messages attached to these
            // executions.
            applyExecutionCoordinates(lifeline);

            // Set position and height for the lifeline.
            nodeLayout.setYpos((float) lifeline.getPosition().y);
            nodeLayout.setXpos((float) lifeline.getPosition().x);
            nodeLayout.setHeight((float) lifeline.getSize().y);

            // Place destruction if existing
            KNode destruction = lifeline.getProperty(SequenceDiagramProperties.DESTRUCTION_EVENT);
            if (destruction != null) {
                KShapeLayout destructLayout = destruction.getData(KShapeLayout.class);
                double destructionXPos = nodeLayout.getWidth() / 2 - destructLayout.getWidth() / 2;
                double destructionYPos = nodeLayout.getHeight() - destructLayout.getHeight();
                destructLayout.setPos((float) destructionXPos, (float) destructionYPos);
            }
        }

        // Place all comments
        placeComments(graph);

        // Set position and size of surrounding interaction
        KShapeLayout parentLayout = parentNode.getData(KShapeLayout.class);
        parentLayout.setWidth((float) graph.getSize().x);
        parentLayout.setHeight((float) diagramHeight);
        parentLayout.setPos((float) borderSpacing, (float) borderSpacing);
    }

    /**
     * Apply the calculated coordinates of the messages that are connected to the given lifeline.
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
    private void applyMessageCoordinates(final double diagramHeight, final SGraph graph,
            final List<SLifeline> lifelineOrder, final SLifeline lifeline,
            final List<SequenceExecution> executions) {
        /*
         * TODO Set this to one if Papyrus team fixes its bug. Workaround for Papyrus bug:
         * Y-coordinates are stored in a strange way by Papyrus. When the message starts or ends at
         * a lifeline, y-coordinates must be given relative to the lifeline. However, these relative
         * coordinates must be scaled as if the lifeline was having the height of its surrounding
         * interaction.
         */
        double factor = (diagramHeight + TWENTY) / lifeline.getSize().y;

        // Resize node if there are any create or delete messages involved
        for (SMessage message : lifeline.getIncomingMessages()) {
            if (message.getProperty(SequenceDiagramProperties.MESSAGE_TYPE) == MessageType.CREATE) {
                // Set lifeline's yPos to the yPos of the create-message
                lifeline.getPosition().y = message.getTargetYPos() + lifelineHeader / 2;
                // Modify height of lifeline in order to compensate yPos changes
                lifeline.getSize().y += lifelineYPos - message.getTargetYPos() - lifelineHeader / 2;
            } else if (message.getProperty(SequenceDiagramProperties.MESSAGE_TYPE) 
                    == MessageType.DELETE) {
                // Modify height of lifeline in order to end at the yPos of the delete-message
                lifeline.getSize().y -= graph.getSize().y + messageSpacing
                        - message.getTargetYPos();
            }
        }

        // The horizontal center of the current lifeline
        double llCenter = lifeline.getPosition().x + lifeline.getSize().x / 2;

        // Handle outgoing messages
        for (SMessage message : lifeline.getOutgoingMessages()) {
            KEdge edge = (KEdge) message.getProperty(Properties.ORIGIN);
            KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
            KPoint sourcePoint = edgeLayout.getSourcePoint();
            sourcePoint.setY((float) (message.getSourceYPos() * factor));
            sourcePoint.setX((float) (lifeline.getPosition().x + lifeline.getSize().x / 2));

            // Set execution coordinates according to connected messages coordinates
            if (executions != null) {
                for (SequenceExecution execution : executions) {
                    if (execution.getMessages().contains(message)) {
                        double sourceYPos = message.getSourceYPos();
                        if (execution.getPosition().y == 0) {
                            execution.getPosition().y = sourceYPos;
                            execution.getSize().y = 0;
                        } else {
                            if (sourceYPos < execution.getPosition().y) {
                                if (message.getSource() != message.getTarget()) {
                                    double diff = execution.getPosition().y - sourceYPos;
                                    execution.getPosition().y = sourceYPos;
                                    if (execution.getSize().y >= 0) {
                                        execution.getSize().y += diff;
                                    }
                                }
                            }
                            if (sourceYPos > execution.getPosition().y + execution.getSize().y) {
                                execution.getSize().y = sourceYPos - execution.getPosition().y;
                            }
                        }
                    }
                }
            }

            // Handle messages that lead to something else than a lifeline
            if (message.getTarget().getName().equals("DummyLifeline")) {
                KPoint targetPoint = edgeLayout.getTargetPoint();
                double reverseFactor = lifeline.getSize().y / (diagramHeight + FOURTY);
                targetPoint.setY((float) (TWENTY + message.getTargetYPos() * reverseFactor));

                // Lost-messages end between its source and the next lifeline
                if (message.getProperty(SequenceDiagramProperties.MESSAGE_TYPE) == MessageType.LOST) {
                    targetPoint.setX((float) (lifeline.getPosition().x + lifeline.getSize().x 
                            + lifelineSpacing / 2));
                }
            }

            if (message.getSource() == message.getTarget()) {
                // Specify bendpoints for selfloops
                List<KPoint> bendPoints = edgeLayout.getBendPoints();
                bendPoints.get(0).setX((float) (llCenter + messageSpacing / 2));
                bendPoints.get(0).setY(edgeLayout.getSourcePoint().getY());
            }

            // Walk through the labels and adjust their position
            placeLabels(lifelineOrder, lifeline, factor, llCenter, message, edge);
        }

        // Handle incoming messages
        for (SMessage message : lifeline.getIncomingMessages()) {
            KEdge edge = (KEdge) message.getProperty(Properties.ORIGIN);
            KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
            KPoint targetPoint = edgeLayout.getTargetPoint();
            targetPoint.setX((float) (lifeline.getPosition().x + lifeline.getSize().x / 2));
            targetPoint.setY((float) (message.getTargetYPos() * factor));

            if (message.getProperty(SequenceDiagramProperties.MESSAGE_TYPE) == MessageType.CREATE) {
                // Reset x-position of create message because it leads to the header and not the
                // line
                targetPoint.setX((float) lifeline.getPosition().x);
            } else if (message.getProperty(SequenceDiagramProperties.MESSAGE_TYPE) 
                    == MessageType.DELETE) {
                // Reset y-position of delete message to end at the end of the lifeline
                targetPoint.setY((float) ((lifeline.getPosition().y + lifeline.getSize().y 
                        - lifelineHeader) * factor));
            }

            // Reset execution coordinates if the message is contained in an execution
            if (executions != null) {
                for (SequenceExecution execution : executions) {
                    if (execution.getMessages().contains(message)) {
                        double targetYPos = message.getTargetYPos();
                        if (execution.getPosition().y == 0) {
                            execution.getPosition().y = targetYPos;
                            execution.getSize().y = 0;
                        } else {
                            if (targetYPos < execution.getPosition().y) {
                                double diff = execution.getPosition().y - targetYPos;
                                execution.getPosition().y = targetYPos;
                                if (execution.getSize().y >= 0) {
                                    execution.getSize().y += diff;
                                }
                            }
                            if (targetYPos > execution.getPosition().y + execution.getSize().y) {
                                execution.getSize().y = targetYPos - execution.getPosition().y;
                            }
                        }
                    }
                }
            }

            // Handle messages that come from something else than a lifeline
            if (message.getSource().getName().equals("DummyLifeline")) {
                KPoint sourcePoint = edgeLayout.getSourcePoint();
                double reverseFactor = lifeline.getSize().y / (diagramHeight + FOURTY);
                sourcePoint.setY((float) (TWENTY + message.getSourceYPos() * reverseFactor));

                // Found-messages start between its source and the previous lifeline
                if (message.getProperty(SequenceDiagramProperties.MESSAGE_TYPE) == MessageType.FOUND) {
                    sourcePoint.setX((float) (lifeline.getPosition().x - lifelineSpacing / 2));
                }
            }

            if (message.getSource() == message.getTarget()) {
                // Specify bendpoints for selfloops
                List<KPoint> bendPoints = edgeLayout.getBendPoints();
                bendPoints.get(1).setX((float) (llCenter + messageSpacing / 2));
                bendPoints.get(1).setY(edgeLayout.getTargetPoint().getY());
            }
        }
    }

    /**
     * Place the label(s) of the given message.
     * 
     * @param lifelineOrder
     *            the list of lifelines in the correct order
     * @param lifeline
     *            the current lifeline
     * @param factor
     *            the edge factor
     * @param llCenter
     *            the horizontal center of the current lifeline
     * @param message
     *            the current message
     * @param edge
     *            the edge representation of the message
     */
    private void placeLabels(final List<SLifeline> lifelineOrder, final SLifeline lifeline,
            final double factor, final double llCenter, final SMessage message, final KEdge edge) {
        for (KLabel label : edge.getLabels()) {
            KShapeLayout labelLayout = label.getData(KShapeLayout.class);

            // The index of the current lifeline in the ordered list of lifelines
            int lifelineIndex = lifelineOrder.indexOf(lifeline);

            if (message.getTarget().getHorizontalSlot() > lifeline.getHorizontalSlot()) {
                // Message leads rightwards
                switch (labelAlignment) {
                case FIRST_CENTER:
                    // If the lifeline is the last lifeline (lost message), fall through to SOURCE
                    // placement to avoid ArrayIndexOutOfBoundsException
                    if (lifelineIndex + 1 < lifelineOrder.size()) {
                        // Place labels centered between the source lifeline and its neighbored
                        // lifeline
                        SLifeline nextLL = lifelineOrder.get(lifelineIndex + 1);
                        double center = (llCenter + nextLL.getPosition().x + nextLL.getSize().x / 2) / 2;
                        labelLayout.setXpos((float) (center - labelLayout.getWidth() / 2));
                        break;
                    }
                case SOURCE:
                    // Place labels near the source lifeline
                    labelLayout.setXpos((float) llCenter + LABELSPACING);
                    break;
                case CENTER:
                    // Place labels in the center of the message
                    double targetCenter = message.getTarget().getPosition().x
                            + message.getTarget().getSize().x / 2;
                    labelLayout.setXpos((float) ((llCenter + targetCenter) / 2 - labelLayout
                            .getWidth() / 2));
                }
                // Create messages should not overlap the target's header
                if (message.getProperty(SequenceDiagramProperties.MESSAGE_TYPE) == MessageType.CREATE) {
                    labelLayout.setXpos((float) (llCenter + LABELSPACING));
                }
                labelLayout.setYpos((float) ((message.getSourceYPos() - labelLayout.getHeight() - 2)
                        * factor));
            } else if (message.getTarget().getHorizontalSlot() < lifeline.getHorizontalSlot()) {
                // Message leads leftwards
                switch (labelAlignment) {
                case FIRST_CENTER:
                    // If the lifeline is the first lifeline (found message), fall through to SOURCE
                    // placement to avoid ArrayIndexOutOfBoundsException
                    if (lifelineIndex > 0) {
                        // Place labels centered between the source lifeline and its neighbored
                        // lifeline
                        SLifeline lastLL = lifelineOrder.get(lifelineIndex - 1);
                        double center = (llCenter + lastLL.getPosition().x + lastLL.getSize().x / 2) / 2;
                        labelLayout.setXpos((float) (center - labelLayout.getWidth() / 2));
                        break;
                    }
                case SOURCE:
                    // Place labels near the source lifeline
                    labelLayout.setXpos((float) (llCenter - labelLayout.getWidth() - LABELSPACING));
                    break;
                case CENTER:
                    // Place labels in the center of the message
                    double targetCenter = message.getTarget().getPosition().x
                            + message.getTarget().getSize().x / 2;
                    labelLayout.setXpos((float) ((llCenter + targetCenter) / 2 - labelLayout
                            .getWidth() / 2));
                }
                labelLayout.setYpos((float) ((message.getSourceYPos() + 2) * factor));
            } else {
                // Message is selfloop
                
                // Place labels right of the selfloop
                KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                double xPos;
                if (edgeLayout.getBendPoints().size() > 0) {
                    KPoint firstBend = edgeLayout.getBendPoints().get(0);
                    xPos = firstBend.getX();
                } else {
                    xPos = edgeLayout.getSourcePoint().getX();
                }
                labelLayout.setYpos((float) ((message.getSourceYPos() + LABELSPACING) * factor));
                labelLayout.setXpos((float) (xPos + LABELMARGIN / 2));
            }
        }
    }

    /**
     * Apply execution coordinates and adjust positions of messages attached to these executions.
     * 
     * @param lifeline
     *            the lifeline, whose executions are placed
     */
    private void applyExecutionCoordinates(final SLifeline lifeline) {
        List<SequenceExecution> executions = lifeline.getProperty(PapyrusProperties.EXECUTIONS);
        if (executions == null) {
            return;
        }

        // Set xPos, maxXPos and height / maxYPos
        arrangeExecutions(executions, lifeline.getSize().x);

        // Get the layout data of the execution
        KNode node = (KNode) lifeline.getProperty(Properties.ORIGIN);
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);

        // Walk through the lifeline's executions
        nodeLayout.setProperty(PapyrusProperties.EXECUTIONS, executions);
        for (SequenceExecution execution : executions) {
            Object executionObj = execution.getOrigin();

            if (executionObj instanceof KNode) {
                if (execution.getType().equals("Duration")
                        || execution.getType().equals("TimeConstraint")) {
                    execution.getPosition().y += TWENTY;
                }

                // Apply calculated coordinates to the execution
                KNode executionNode = (KNode) executionObj;
                KShapeLayout shapelayout = executionNode.getData(KShapeLayout.class);
                shapelayout.setXpos((float) execution.getPosition().x);
                shapelayout.setYpos((float) (execution.getPosition().y - lifelineYPos));
                shapelayout.setWidth((float) execution.getSize().x);
                shapelayout.setHeight((float) execution.getSize().y);

                // Determine max and min y-pos of messages
                double minYPos = lifeline.getSize().y;
                double maxYPos = 0;
                for (Object messObj : execution.getMessages()) {
                    if (messObj instanceof SMessage) {
                        SMessage message = (SMessage) messObj;
                        double messageYPos;
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
                double effectiveHeight = lifeline.getSize().y - TWENTY;
                double executionHeight = maxYPos - minYPos;
                double executionFactor = effectiveHeight / executionHeight;

                // Walk through execution's messages and adjust their position
                for (Object messObj : execution.getMessages()) {
                    if (messObj instanceof SMessage) {
                        SMessage mess = (SMessage) messObj;
                        boolean toLeft = false;
                        if (mess.getSource().getHorizontalSlot() > mess.getTarget()
                                .getHorizontalSlot()) {
                            // Message leads leftwards
                            toLeft = true;
                        }

                        KEdge edge = (KEdge) mess.getProperty(Properties.ORIGIN);
                        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                        double newXPos = lifeline.getPosition().x + execution.getPosition().x;
                        if (mess.getSource() == mess.getTarget()) {
                            // Selfloop: insert bend points
                            edgeLayout.getBendPoints().get(0).setY(edgeLayout.getSourcePoint().getY());
                            edgeLayout.getBendPoints().get(1).setY(edgeLayout.getTargetPoint().getY());
                            edgeLayout.getTargetPoint().setX((float) (newXPos + execution.getSize().x));
                            edgeLayout.getTargetPoint().setY(0);
                        } else if (mess.getSource() == lifeline) {
                            if (!toLeft) {
                                newXPos += execution.getSize().x;
                            }
                            edgeLayout.getSourcePoint().setX((float) newXPos);

                            // Calculate the message's height relative to the execution
                            double relHeight = mess.getSourceYPos() - minYPos;
                            if (relHeight == 0) {
                                edgeLayout.getSourcePoint().setY(0);
                            } else {
                                edgeLayout.getSourcePoint().setY(
                                        (float) (lifelineHeader + relHeight * executionFactor));
                            }
                        } else {
                            if (toLeft) {
                                newXPos += execution.getSize().x;
                            }
                            edgeLayout.getTargetPoint().setX((float) newXPos);

                            // Calculate the message's height relative to the execution
                            double relHeight = mess.getTargetYPos() - minYPos;
                            if (relHeight == 0) {
                                edgeLayout.getTargetPoint().setY(0);
                            } else {
                                edgeLayout.getTargetPoint().setY(
                                        (float) (lifelineHeader + relHeight * executionFactor));
                            }
                        }
                    }
                }
            }
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
    private void arrangeExecutions(final List<SequenceExecution> executions,
            final double parentWidth) {
        final double minHeight = 20;
        final double executionWidth = 16;

        // Initially set horizontal position and height of empty executions
        for (SequenceExecution execution : executions) {
            execution.getPosition().x = (parentWidth - executionWidth) / 2;
            // Give executions without messages their original height and yPos
            if (execution.getMessages().size() == 0) {
                KShapeLayout shapelayout = execution.getOrigin().getData(KShapeLayout.class);
                execution.getPosition().y = shapelayout.getYpos();
                execution.getSize().y = shapelayout.getHeight();
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
                        if (execution.getPosition().y > otherExecution.getPosition().y
                                && execution.getPosition().y + execution.getSize().y < otherExecution
                                        .getPosition().y + otherExecution.getSize().y) {
                            pos++;
                        }
                    }
                }
                if (pos > 0) {
                    execution.getPosition().x = execution.getPosition().x + pos * executionWidth
                            / 2;
                }
            }
        }

        // Check minimum height of executions and set width
        for (SequenceExecution execution : executions) {
            if (execution.getSize().y < minHeight) {
                execution.getSize().y = minHeight;
            }
            if (execution.getType().equals("Duration")
                    || execution.getType().equals("TimeConstraint")) {
                continue;
            }
            execution.getSize().x = executionWidth;
        }
    }

    /**
     * Place the comment objects (comments, constraints) according to their calculated coordinates.
     * 
     * @param graph
     *            the Sequence Graph
     */
    private void placeComments(final SGraph graph) {
        for (SComment comment : graph.getComments()) {
            Object origin = comment.getProperty(Properties.ORIGIN);
            KShapeLayout commentLayout = ((KNode) origin).getData(KShapeLayout.class);
            commentLayout.setPos((float) comment.getPosition().x, (float) comment.getPosition().y);
            if (comment.getMessage() != null) {
                // Connected comments

                // Set coordinates for the connection of the comment
                double edgeSourceXPos, edgeSourceYPos, edgeTargetXPos, edgeTargetYPos;
                String attachedElement = comment.getProperty(PapyrusProperties.ATTACHED_ELEMENT);
                if (attachedElement.toLowerCase().startsWith("lifeline")
                        || attachedElement.toLowerCase().contains("execution")) {
                    // Connections to lifelines or executions are drawn horizontally
                    SLifeline lifeline = comment.getLifeline();
                    edgeSourceXPos = comment.getPosition().x;
                    edgeSourceYPos = comment.getPosition().y + comment.getSize().y / 2;
                    edgeTargetXPos = lifeline.getPosition().x + lifeline.getSize().x / 2;
                    edgeTargetYPos = edgeSourceYPos;
                } else {
                    // Connections to messages are drawn vertically
                    edgeSourceXPos = comment.getPosition().x + comment.getSize().x / 2;
                    edgeTargetXPos = edgeSourceXPos;
                    KEdge edge = (KEdge) comment.getMessage().getProperty(Properties.ORIGIN);
                    KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                    KPoint targetPoint = edgeLayout.getTargetPoint();
                    KPoint sourcePoint = edgeLayout.getSourcePoint();
                    edgeSourceYPos = comment.getPosition().y + comment.getSize().y;
                    edgeTargetYPos = (targetPoint.getY() + sourcePoint.getY()) / 2;
                }

                // Apply connection coordinates to layout
                KEdgeLayout edgelayout = comment.getProperty(
                        SequenceDiagramProperties.COMMENT_CONNECTION).getData(KEdgeLayout.class);
                edgelayout.getSourcePoint().setPos((float) edgeSourceXPos, (float) edgeSourceYPos);
                edgelayout.getTargetPoint().setPos((float) edgeTargetXPos, (float) edgeTargetYPos);
            }
        }
    }
}
