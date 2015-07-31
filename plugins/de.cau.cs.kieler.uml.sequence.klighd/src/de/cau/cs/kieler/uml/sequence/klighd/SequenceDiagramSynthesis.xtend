package de.cau.cs.kieler.uml.sequence.klighd

import de.cau.cs.kieler.core.kgraph.KEdge
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.Colors
import de.cau.cs.kieler.core.krendering.KPolyline
import de.cau.cs.kieler.core.krendering.KPosition
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.LineStyle
import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.core.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.core.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KLabelExtensions
import de.cau.cs.kieler.core.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.core.krendering.extensions.KPortExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.util.FixedLayoutProvider
import de.cau.cs.kieler.kiml.util.KimlUtil
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.cau.cs.kieler.papyrus.sequence.SequenceDiagramLayoutProvider
import de.cau.cs.kieler.papyrus.sequence.properties.MessageType
import de.cau.cs.kieler.papyrus.sequence.properties.NodeType
import de.cau.cs.kieler.papyrus.sequence.properties.SequenceDiagramProperties
import de.cau.cs.kieler.uml.sequence.text.sequence.DestroyLifelineEvent
import de.cau.cs.kieler.uml.sequence.text.sequence.Fragment
import de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline
import de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineEndBlock
import de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage
import de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineNote
import de.cau.cs.kieler.uml.sequence.text.sequence.Refinement
import de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram
import de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage
import java.util.ArrayList
import java.util.HashMap
import javax.inject.Inject
import de.cau.cs.kieler.uml.sequence.text.sequence.Section

class SequenceDiagramSynthesis extends AbstractDiagramSynthesis<SequenceDiagram> {

    @Inject extension KNodeExtensions
    @Inject extension KEdgeExtensions
    @Inject extension KPortExtensions
    @Inject extension KLabelExtensions
    @Inject extension KRenderingExtensions
    @Inject extension KContainerRenderingExtensions
    @Inject extension KPolylineExtensions
    @Inject extension KColorExtensions
    extension KRenderingFactory = KRenderingFactory.eINSTANCE

    val lifelineNodes = new HashMap<String, KNode>
    var KNode surroundingInteraction
    
    //TODO lifeline sorting property

    override KNode transform(SequenceDiagram model) {
        val root = model.createNode()
        root.addLayoutParam(LayoutOptions.ALGORITHM, FixedLayoutProvider.ID)

        val surrInteraction = root.createNode().associateWith(model)
        root.children.add(surrInteraction)
        surrInteraction.addLayoutParam(LayoutOptions.ALGORITHM, SequenceDiagramLayoutProvider.ID)
        surrInteraction.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.SURROUNDING_INTERACTION)
        surrInteraction.addLayoutParam(SequenceDiagramProperties.LIFELINE_Y_POS, 50)
        surrInteraction.addLayoutParam(SequenceDiagramProperties.LIFELINE_HEADER, 500)
        surrInteraction.addLayoutParam(LayoutOptions.BORDER_SPACING, 10f);

        val surrInteractionRect = surrInteraction.addRoundedRectangle(2, 2, 2)

//        val captionRect = surrInteractionRect.addRectangle.foregroundInvisible = true
        
        val captionRect2 = surrInteractionRect.addRectangle.foregroundInvisible = true
        captionRect2.addText("sd " + model.diagramName).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
        
        val list = new ArrayList<KPosition>
        list.add(createKPosition(LEFT, 0, 0, BOTTOM, 0, 0))
        list.add(createKPosition(RIGHT, 10, 0, BOTTOM, 0, 0))
        list.add(createKPosition(RIGHT, 0, 0, BOTTOM, 10, 0))
        list.add(createKPosition(RIGHT, 0, 0, TOP, 0, 0))
        
        //val line = 
        captionRect2.addPolyline(2, list)
        captionRect2.setPointPlacementData(
                    LEFT, 0, 0,
                    TOP, 0, 0,
                    H_LEFT, V_TOP,
                    0, 0, 0, 0)
        //TODO better length?....
        
        // val float length = model.diagramName.length * 13
        // captionRect2.setGridPlacementData.from(LEFT, 0, 0, TOP, 0, 0).to(LEFT, length, 0, BOTTOM, 0, 0)
        //val lifelinesRect = 
//        surrInteractionRect.addChildArea // .setForeground(Colors.RED)
//        surrInteractionRect.children.add(captionRect)
//        surrInteractionRect.children.add(lifelinesRect)
        surroundingInteraction = surrInteraction

        model.lifelines.forEach[s|surrInteraction.children += transformLifeline(s)]
        //TODO Message Sorting!!!
        //System.out.println("interactions: " + model.interactions)
        model.interactions.forEach[s|transformInteraction(s)]
        
        //TODO close all blocks if not closed yet

        lifelineNodes.clear
        surroundingInteraction = null

        return root
    }

    private def KNode transformLifeline(Lifeline lifeline) {
        val lifelineNode = lifeline.createNode().associateWith(lifeline)

        lifelineNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.LIFELINE)

        val rect = lifelineNode.addRectangle.foregroundInvisible = true
        rect.setGridPlacement(1).from(LEFT, 0, 0, TOP, 0, 0).to(RIGHT, 0, 0, BOTTOM, 0, 0)
        

        //TODO placement....
        val rect2 = rect.addRoundedRectangle(2, 2, 1).setBackgroundGradient(Colors.WHITE, Colors.CORNFLOWER_BLUE, 90)
//        rect2.setGridPlacementData.from(LEFT, 0, 0, TOP, 0, 0).to(RIGHT, 0, 0, BOTTOM, 0, 0)
        rect2.addText(lifeline.caption).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
        rect2.setShadow(Colors.BLACK, 5)
//        rect2.setAreaPlacementData(createKPosition(LEFT, 0, 0, TOP, 0, 0), createKPosition(RIGHT, 0, 0, TOP, 40, 0))
        val list = new ArrayList<KPosition>
        list.add(createKPosition(LEFT, 0, 0.5f, TOP, 0, 0))
        list.add(createKPosition(LEFT, 0, 0.5f, BOTTOM, 0, 0))
        val line = rect.addRectangle.foregroundInvisible = true
        line.addPolyline(2, list).setLineStyle(LineStyle.DASH)
        line.setGridPlacementData.from(LEFT, 0, 0, TOP, 0, 0).to(RIGHT, 0, 0, BOTTOM, 0, 0)

//        rect.children.get(0).setBackground(Colors.GREEN)
//        rect.children.get(1).setBackground(Colors.RED)
//        rect.children.get(0).setGridPlacementData.from(LEFT, 0, 0, TOP, 0, 0).to(RIGHT, 0, 0, TOP, 40, 0)
//        rect.children.get(1).setGridPlacementData.from(LEFT, 0, 0, TOP, 40, 0).to(RIGHT, 0, 0, BOTTOM, 0, 0)
//        rect.children.add(rect2)
//        rect.children.add(line)
//        val rect = lifelineNode.setNodeSize(50, 200).addRoundedRectangle(1, 1, 2)
//        rect.addRoundedRectangle(2, 2, 1).setForeground(Colors.LIGHT_GRAY).setBackground(Colors.WHITE).setSurroundingSpaceGrid(5, 0)
//        rect.addRectangle.addVerticalLine(LEFT, 0.75f).setForeground(Colors.RED)
//        rect.setBackgroundGradient(Colors.WHITE, Colors.CORNFLOWER_BLUE, 90)
//        rect.addText(lifeline.caption).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
//        rect.setShadow(Colors.BLACK, 5)
//        val rect = lifelineNode.setNodeSize(50, 20).addRoundedRectangle(2, 2, 1)
//        rect.setBackgroundGradient(Colors.WHITE, Colors.CORNFLOWER_BLUE, 90)
//        rect.addText(lifeline.caption).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
//        rect.setShadow(Colors.BLACK, 5)
//        rect.setGridPlacement(1)
//        rect.addHorizontalLine(2, 0.75f)
//        rect.addDoubleClickAction(CollapseExpandAction.ID)
//        rect.setBackgroundGradient(Colors.WHITE, Colors.ORANGE, 90)
//
        lifelineNodes.put(lifeline.name, lifelineNode)

        return lifelineNode
    }

    private def KEdge setMessageRendering(KEdge edge, String type) {
        //TODO lifeline header weiter runter
        if (type.equals("sync")) {
            edge.addLayoutParam(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.SYNCHRONOUS)
            edge.addPolyline(2).addHeadArrowDecorator()
        } else if (type.equals("async")) {
            edge.addLayoutParam(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.ASYNCHRONOUS)
          edge.addPolyline(2).addAssociationArrowDecorator()
        } else if (type.equals("response")) {
            edge.addLayoutParam(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.REPLY)
            edge.addPolyline(2).setLineStyle(LineStyle.DASH).addAssociationArrowDecorator()
        } else if (type.equals("create")) {
            //TODO auf die caption zeigen
            edge.addLayoutParam(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.CREATE)
            edge.addPolyline(2).setLineStyle(LineStyle.DASH).addAssociationArrowDecorator()
        } else if (type.equals("lost")) {
            edge.addLayoutParam(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.LOST)
        } else if (type.equals("found")) {
            edge.addLayoutParam(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.FOUND)
        }

        return edge
    }

    private def dispatch KEdge transformInteraction(TwoLifelineMessage msg) {
        val transEdge = msg.createEdge().associateWith(msg)
        transEdge.setMessageRendering(msg.messageType.toString)

        val label = KimlUtil.createInitializedLabel(transEdge)
        
        val labelText = msg.message
        label.configureCenterEdgeLabel(labelText, KlighdConstants.DEFAULT_FONT_SIZE, KlighdConstants.DEFAULT_FONT_NAME)

        //TODO Self Message?!!!
        transEdge.source = lifelineNodes.get(msg.sourceLifeline.name)
        transEdge.target = lifelineNodes.get(msg.targetLifeline.name)

        if (msg.sourceStartBlock) {
            // createBlock(msg.sourceLifeline)
        } else if (msg.sourceEndBlock) {
            //TODO
        }

        if (msg.targetStartBlock) {
            // createBlock(msg.sourceLifeline)
        } else if (msg.targetEndBlock) {
            //TODO
        }

        return transEdge
    }
    
    
    /*  private void createExecution(final LayoutMapping<IGraphicalEditPart> mapping,
            final ShapeNodeEditPart lifelineEditPart, final List<SequenceExecution> executions,
            final ShapeNodeEditPart childEditPart, final NodeType nodeType, final KNode executionNode) {

        KShapeLayout executionLayout = executionNode.getData(KShapeLayout.class);
        IFigure executionFigure = childEditPart.getFigure();
        Rectangle executionBounds = getAbsoluteBounds(executionFigure);

        SequenceExecution execution = new SequenceExecution();

        if (nodeType == NodeType.BEHAVIOUR_EXEC_SPECIFICATION
                || nodeType == NodeType.ACTION_EXEC_SPECIFICATION) {

            executionNode.getData(KShapeLayout.class).setProperty(SequenceDiagramProperties.NODE_TYPE,
                    nodeType);
            execution.setType(SequenceExecutionType.EXECUTION);
        } else if (nodeType == NodeType.DURATION_CONSTRAINT) {
            executionNode.getData(KShapeLayout.class).setProperty(SequenceDiagramProperties.NODE_TYPE,
                    nodeType);
            execution.setType(SequenceExecutionType.DURATION);
        } else if (nodeType == NodeType.TIME_CONSTRAINT) {
            executionNode.getData(KShapeLayout.class).setProperty(SequenceDiagramProperties.NODE_TYPE,
                    nodeType);
            execution.setType(SequenceExecutionType.TIME_CONSTRAINT);
        }

        // Walk through the connected messages
        for (Object targetConn : childEditPart.getTargetConnections()) {
            if (targetConn instanceof ConnectionEditPart) {
                ConnectionEditPart connectionEditPart = (ConnectionEditPart) targetConn;
                mapping.getProperty(CONNECTIONS).add(connectionEditPart);

                execution.addMessage(connectionEditPart);
            }
        }
        for (Object sourceConn : childEditPart.getSourceConnections()) {
            if (sourceConn instanceof ConnectionEditPart) {
                ConnectionEditPart connectionEditPart = (ConnectionEditPart) sourceConn;

                execution.addMessage(connectionEditPart);
            }
        }
        execution.setOrigin(executionNode);
        executions.add(execution);
        executionLayout.setProperty(PapyrusProperties.EXECUTION, execution); */
    private def KNode createBlock(Lifeline l) {
        val block = l.createNode().associateWith(l)
        block.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.ACTION_EXEC_SPECIFICATION)
        
        lifelineNodes.get(l.name).children.add(block)

        //TODO do stuff
        return block
    }

    private def dispatch KEdge transformInteraction(OneLifelineMessage msg) {
        val transEdge = msg.createEdge().associateWith(msg)
//        transEdge.setMessageRendering(msg.messageType.toString)
        transEdge.setMessageRendering(msg.messageTypeLostAndFound.toString)

        val label = KimlUtil.createInitializedLabel(transEdge)

        val labelText = msg.caption
        label.configureCenterEdgeLabel(labelText, KlighdConstants.DEFAULT_FONT_SIZE, KlighdConstants.DEFAULT_FONT_NAME)
        transEdge.addPolyline(2).addHeadArrowDecorator()

        val dummyNode = surroundingInteraction.createNode()
        //val circle = 
        dummyNode.addRectangle.setForeground(Colors.AQUAMARINE)


        //TODO source und target setzen und kreis am ende
        if (msg.messageTypeLostAndFound.equals("lost")) {
            transEdge.source = lifelineNodes.get(msg.lifeline.name)
            transEdge.target = dummyNode
        } else {
            transEdge.source = dummyNode
            transEdge.target = lifelineNodes.get(msg.lifeline.name)
        }

        return transEdge
    }

    private def dispatch KNode transformInteraction(OneLifelineEndBlock end) {
        val endBlockNode = end.createNode().associateWith(end)
        
        //TODO
        
        return endBlockNode
    }

    private def dispatch KNode transformInteraction(OneLifelineNote note) {
        val noteNode = note.createNode().associateWith(note)
        noteNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.COMMENT)
        
        //TODO 
        
        return noteNode
    }

    private def dispatch KNode transformInteraction(DestroyLifelineEvent destroy) {
        //TODO close blocks before if not closed yet
        //TODO am Ende anzeigen
//        val destroyNode = lifelineNodes.get(destroy.lifeline.name).createNode().associateWith(destroy)
        val destroyNode = destroy.createNode().associateWith(destroy)
        destroyNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.DESTRUCTION_EVENT)
        
        //TODO 2 polylines point placement
        val list = new ArrayList<KPosition>
        list.add(createKPosition(LEFT, 0, 0, TOP, 0, 0))
        list.add(createKPosition(LEFT, 0, 0.5f, TOP, 5, 0))
        list.add(createKPosition(RIGHT, 0, 0, TOP, 0, 0))
        list.add(createKPosition(LEFT, 0, 0.5f, TOP, 5, 0))
        list.add(createKPosition(RIGHT, 0, 0, BOTTOM, 0, 0))
        list.add(createKPosition(LEFT, 0, 0.5f, TOP, 5, 0))
        list.add(createKPosition(LEFT, 0, 0, BOTTOM, 0, 0))
        
        val destroyRect = destroyNode.addRectangle().foregroundInvisible = true
        destroyRect.addPolyline(2, list)

        lifelineNodes.get(destroy.lifeline.name).children.add(destroyNode)

        return destroyNode
    }

    private def dispatch KNode transformInteraction(Fragment frag) {
        val fragNode = frag.createNode().associateWith(frag)
        
        if (frag.sections.size > 1) {
            fragNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.COMBINED_FRAGMENT)
        } else {
            fragNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.INTERACTION_USE)
        }

        surroundingInteraction.children.add(fragNode)

        val fragNodeRect = fragNode.addRoundedRectangle(2, 2, 2)

        val captionRect = fragNodeRect.addRectangle.foregroundInvisible = true
        //TODO better length?....
        captionRect.setGridPlacement(2000)
        
        val captionRect2 = captionRect.addRectangle.foregroundInvisible = true
        captionRect2.addText(frag.name).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
        
        val list = new ArrayList<KPosition>
        list.add(createKPosition(LEFT, 0, 0, BOTTOM, 0, 0))
        list.add(createKPosition(RIGHT, 10, 0, BOTTOM, 0, 0))
        list.add(createKPosition(RIGHT, 0, 0, BOTTOM, 10, 0))
        list.add(createKPosition(RIGHT, 0, 0, TOP, 0, 0))
        
        //val line = 
        captionRect2.addPolyline(2, list)
        
        //val messagesRect = 
        fragNodeRect.addChildArea
        fragNodeRect.setGridPlacement = 1

        //TODO sections im messagerect
        for (sect : frag.sections) {
            if (sect.label != null) {
                val captionRect3 = captionRect.addRectangle.foregroundInvisible = true
                captionRect3.addText(sect.label).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
            }
            sect.interactions.forEach[s|transformInteraction(s)]
        }

        return fragNode
    }

    private def dispatch KNode transformInteraction(Refinement ref) {
        val refNode = ref.createNode().associateWith(ref)
        refNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.INTERACTION_USE)

        val label = ref.label

        return refNode
    }

    //TODO time constraint between messages
    /**
     * Adds an arrow decorator suitable for associations to the given polyline.
     * 
     * @param pl the polyline to add the decorator to.
     * @return the given polyline.
     */
    def private KRendering addAssociationArrowDecorator(KPolyline pl) {
        pl.addPolyline() => [
            it.points += createKPosition(LEFT, 0, 0, TOP, 0, 0)
            it.points += createKPosition(RIGHT, 0, 0, TOP, 0, 0.5f)
            it.points += createKPosition(LEFT, 0, 0, BOTTOM, 0, 0)
            it.setDecoratorPlacementData(12, 12, -6, 1.0f, true)
        // it.foreground = modelOptions.getProperty(OPTION_EDGE_COLOR).color
        ]
    }

}
