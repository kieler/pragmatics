package de.cau.cs.kieler.uml.sequence.klighd

import de.cau.cs.kieler.core.kgraph.KEdge
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.Colors
import de.cau.cs.kieler.core.krendering.KPosition
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
import de.cau.cs.kieler.papyrus.sequence.properties.MessageType

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

    override KNode transform(SequenceDiagram model) {
        val root = model.createNode();
        root.addLayoutParam(LayoutOptions.ALGORITHM, FixedLayoutProvider.ID)

        val surrInteraction = root.createNode().associateWith(model)
        root.children.add(surrInteraction)
        surrInteraction.addLayoutParam(LayoutOptions.ALGORITHM, SequenceDiagramLayoutProvider.ID)
        surrInteraction.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.SURROUNDING_INTERACTION)

        val surrInteractionRect = surrInteraction.addRoundedRectangle(2, 2, 1).setForeground(Colors.GRAY)
        val captionRect = surrInteractionRect.addRoundedRectangle(2, 2, 1)
        captionRect.addText(model.diagramName).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
        captionRect.setShadow(Colors.BLACK, 5)
        // surrInteractionRect.gridPlacement = 5
        surrInteractionRect.children.add(captionRect)

        model.lifelines.forEach[s|surrInteraction.children += transformLifeline(s)]
        model.interactions.forEach[s|transformInteraction(s)]

        return root
    }

    private def KNode transformLifeline(Lifeline lifeline) {
        val lifelineNode = lifeline.createNode().associateWith(lifeline)

        lifelineNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.LIFELINE)

        val rect = lifelineNode.addRectangle.setForeground(Colors.ORANGE).foregroundInvisible = true
        val rect2 = rect.addRoundedRectangle(2, 2, 1).setBackgroundGradient(Colors.WHITE, Colors.CORNFLOWER_BLUE, 90)
        rect2.addText(lifeline.caption).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
        rect2.setShadow(Colors.BLACK, 5)
        val list = new ArrayList<KPosition>
        list.add(createKPosition(LEFT, 0, 0.5f, BOTTOM, 0, 0))
        list.add(createKPosition(LEFT, 0, 0.5f, BOTTOM, -100, 0))
        val line = rect2.addRectangle.addPolyline(2, list).setLineStyle(LineStyle.DASH)
        rect.setGridPlacement = 1
        rect.children.add(rect2)
        rect.children.add(line)

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
        if (type.equals("sync")) {
            edge.addLayoutParam(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.SYNCHRONOUS)
            edge.addPolyline(2).addHeadArrowDecorator()
        } else if (type.equals("async")) {
            edge.addLayoutParam(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.ASYNCHRONOUS)
            //TODO nicht ausgefüllte spitze
            edge.addPolyline(2).addHeadArrowDecorator()
        } else if (type.equals("response")) {
            //TODO nicht ausgefüllt?
            edge.addLayoutParam(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.REPLY)
            edge.addPolyline(2).setLineStyle(LineStyle.DASH).addHeadArrowDecorator()
        } else if (type.equals("create")) {
            //TODO auf die caption zeigen
            edge.addLayoutParam(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.CREATE)
            edge.addPolyline(2).setLineStyle(LineStyle.DASH).addHeadArrowDecorator()
        } else if (type.equals("lost")) {
            edge.addLayoutParam(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.LOST)
            edge.addPolyline(2).addHeadArrowDecorator()
        } else if (type.equals("found")) {
            edge.addLayoutParam(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.FOUND)
            edge.addPolyline(2).addHeadArrowDecorator()
        }

        return edge
    }

    private def dispatch KEdge transformInteraction(TwoLifelineMessage msg) {
        val transEdge = msg.createEdge().associateWith(msg)
        transEdge.setMessageRendering(msg.messageType.toString)

        val label = KimlUtil.createInitializedLabel(transEdge);
        val labelText = msg.message
        label.configureCenterEdgeLabel(labelText, KlighdConstants.DEFAULT_FONT_SIZE, KlighdConstants.DEFAULT_FONT_NAME);

        transEdge.source = lifelineNodes.get(msg.sourceLifeline.name)
        transEdge.target = lifelineNodes.get(msg.targetLifeline.name)

        return transEdge
    }

    private def dispatch KEdge transformInteraction(OneLifelineMessage msg) {
        val transEdge = msg.createEdge().associateWith(msg);
        // TODO lost and found als create oder response message?
        transEdge.setMessageRendering(msg.messageType)

        val label = KimlUtil.createInitializedLabel(transEdge);
        val labelText = msg.caption;
        label.configureCenterEdgeLabel(labelText, KlighdConstants.DEFAULT_FONT_SIZE, KlighdConstants.DEFAULT_FONT_NAME);

        // TODO source und target setzen und kreis am ende
        if (msg.messageType.equals("lost")) {
            transEdge.source = lifelineNodes.get(msg.lifeline.name)
        } else {
            transEdge.target = lifelineNodes.get(msg.lifeline.name)
        }

        return transEdge
    }

    private def dispatch KNode transformInteraction(OneLifelineEndBlock end) {
        val endNode = end.createNode().associateWith(end);
        //TODO action und behavior unterschied?
        endNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.ACTION_EXEC_SPECIFICATION)
        
        return endNode
    }

    private def dispatch KNode transformInteraction(OneLifelineNote note) {
        val noteNode = note.createNode().associateWith(note);
        noteNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.COMMENT)

        return noteNode
    }

    private def dispatch KNode transformInteraction(DestroyLifelineEvent destroy) {
        val destroyNode = destroy.createNode().associateWith(destroy);
        destroyNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.DESTRUCTION_EVENT)
        
        val list = new ArrayList<KPosition>
        list.add(createKPosition(LEFT, 0, 0, BOTTOM, 0, 0))
        list.add(createKPosition(LEFT, 0, 0.5f, BOTTOM, -5, 0))
        list.add(createKPosition(RIGHT, 0, 0, BOTTOM, 0, 0))        
        list.add(createKPosition(LEFT, 0, 0.5f, BOTTOM, -5, 0))
        list.add(createKPosition(RIGHT, 0, 0, BOTTOM, -10, 0))
        list.add(createKPosition(LEFT, 0, 0.5f, BOTTOM, -5, 0))
        list.add(createKPosition(LEFT, 0, 0, BOTTOM, -10, 0))
        val destroyRect = destroyNode.addRectangle().addPolyline(2, list)
        lifelineNodes.get(destroy.lifeline.name).children.add(destroyNode)

        return destroyNode
    }

    private def dispatch KNode transformInteraction(Fragment frag) {
        val fragNode = frag.createNode().associateWith(frag);
        //TODO if one area interaction_use else if divided into more areas combined
        fragNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.COMBINED_FRAGMENT)
        
        val label = frag.name

        return fragNode
    }

    private def dispatch KNode transformInteraction(Refinement ref) {
        val refNode = ref.createNode().associateWith(ref);
        refNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.INTERACTION_USE)
        
        val label = ref.label

        return refNode
    }

}
