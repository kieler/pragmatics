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
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.util.FixedLayoutProvider
import de.cau.cs.kieler.kiml.util.KimlUtil
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.cau.cs.kieler.papyrus.sequence.SequenceDiagramLayoutProvider
import de.cau.cs.kieler.papyrus.sequence.properties.MessageType
import de.cau.cs.kieler.papyrus.sequence.properties.NodeType
import de.cau.cs.kieler.papyrus.sequence.properties.SequenceDiagramProperties
import de.cau.cs.kieler.papyrus.sequence.p4sorting.LifelineSortingStrategy
import de.cau.cs.kieler.uml.sequence.text.sequence.DestroyLifelineEvent
import de.cau.cs.kieler.uml.sequence.text.sequence.Fragment
import de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline
//import de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineEndBlock
import de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage
import de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineNote
import de.cau.cs.kieler.uml.sequence.text.sequence.Refinement
import de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram
import de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage
import java.util.ArrayList
import java.util.HashMap
import javax.inject.Inject
import de.cau.cs.kieler.klighd.SynthesisOption
import com.google.common.collect.ImmutableList
import de.cau.cs.kieler.papyrus.sequence.properties.SequenceExecutionType
import de.cau.cs.kieler.core.krendering.KContainerRendering
import java.util.Stack

class SequenceDiagramSynthesis extends AbstractDiagramSynthesis<SequenceDiagram> {

    // ///////////////////////////////////////////////////////////////////////////////////////////////////
    // Synthesis Options
    private static final val SynthesisOption STYLE = SynthesisOption.createChoiceOption("Style",
        ImmutableList::of("Black and White", "Stylish", "Hello Kitty"), "Stylish")

    private static final val SynthesisOption LIFELINESORTING = SynthesisOption.createChoiceOption("Lifeline Sorting",
        ImmutableList::of("Interactive", "Layer Based", "Short Messages"), "Interactive")

    // TODO fix initial value not taken at startup
    private static final val SynthesisOption TEXTSIZE = SynthesisOption.createRangeOption("Text Size", 5, 30, 1, 13)

    override getDisplayedSynthesisOptions() {
        return ImmutableList::of(STYLE, LIFELINESORTING, TEXTSIZE)
    }

    // ///////////////////////////////////////////////////////////////////////////////////////////////////
    // Variables
    @Inject extension KNodeExtensions
    @Inject extension KEdgeExtensions
    @Inject extension KPortExtensions
    @Inject extension KLabelExtensions
    @Inject extension KRenderingExtensions
    @Inject extension KContainerRenderingExtensions
    @Inject extension KPolylineExtensions
    @Inject extension KColorExtensions
    extension KRenderingFactory = KRenderingFactory.eINSTANCE

    private val lifelineNodes = new HashMap<String, KNode>
    private var KNode surroundingInteraction
    private val elementIdOnLifeline = new HashMap<String, Stack<Integer>>
    private var executionCount = 0
    private var edgeCounter = 0

    // ///////////////////////////////////////////////////////////////////////////////////////////////////
    // Synthesis
    // Main Transformation
    override KNode transform(SequenceDiagram model) {
        val root = model.createNode()
        root.addLayoutParam(LayoutOptions.ALGORITHM, FixedLayoutProvider.ID)

        val surrInteraction = root.createNode().associateWith(model)
        root.children.add(surrInteraction)
        surrInteraction.addLayoutParam(LayoutOptions.ALGORITHM, SequenceDiagramLayoutProvider.ID)
//        surrInteraction.addLayoutParam(SequenceDiagramProperties.COORDINATE_SYSTEM, CoordinateSystem.KGRAPH)
        surrInteraction.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.SURROUNDING_INTERACTION)
        surrInteraction.addLayoutParam(LayoutOptions.BORDER_SPACING, 10f)
        surrInteraction.addLayoutParam(SequenceDiagramProperties.MESSAGE_SPACING, 5.0f * TEXTSIZE.intValue)
//        surrInteraction.addLayoutParam(SequenceDiagramProperties.LIFELINE_Y_POS, 50)
//        surrInteraction.addLayoutParam(SequenceDiagramProperties.LIFELINE_HEADER, 40)
        surrInteraction.addLayoutParam(SequenceDiagramProperties.LIFELINE_Y_POS, 2 * TEXTSIZE.intValue + 30)
        surrInteraction.addLayoutParam(SequenceDiagramProperties.LIFELINE_HEADER, 2 * TEXTSIZE.intValue + 10)

        switch LIFELINESORTING.objectValue {
            case "Layer Based":
                surrInteraction.addLayoutParam(SequenceDiagramProperties.LIFELINE_SORTING,
                    LifelineSortingStrategy.LAYER_BASED)
            case "Short Message":
                surrInteraction.addLayoutParam(SequenceDiagramProperties.LIFELINE_SORTING,
                    LifelineSortingStrategy.SHORT_MESSAGES)
            default:
                surrInteraction.addLayoutParam(SequenceDiagramProperties.LIFELINE_SORTING,
                    LifelineSortingStrategy.INTERACTIVE)
        }

        // Coordinates for the Rectangle where the Diagram Name is inside
        val lineCoordinates = new ArrayList<KPosition>
        lineCoordinates.add(createKPosition(LEFT, 0, 0, BOTTOM, 0, 0))
        lineCoordinates.add(createKPosition(RIGHT, 10, 0, BOTTOM, 0, 0))
        lineCoordinates.add(createKPosition(RIGHT, 0, 0, BOTTOM, 10, 0))
        lineCoordinates.add(createKPosition(RIGHT, 0, 0, TOP, 0, 0))

        var KContainerRendering surrInteractionRect = null
        switch STYLE.objectValue {
            case "Stylish": {
                surrInteractionRect = surrInteraction.addRoundedRectangle(10, 10, 2)
                surrInteractionRect.setShadow(Colors.BLACK, 10)
            }
            case "Hello Kitty": {
                surrInteractionRect = surrInteraction.addRoundedRectangle(10, 10, 2)
                surrInteractionRect.setShadow(Colors.PURPLE, 10)
            }
            default: {
                surrInteractionRect = surrInteraction.addRectangle
            }
        }

        val captionRect = surrInteractionRect.addRectangle.foregroundInvisible = true
//        captionRect.addText("sd " + model.diagramName).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
        captionRect.addText("sd " + model.diagramName).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = TEXTSIZE.intValue
        captionRect.addPolyline(2, lineCoordinates)
        captionRect.setPointPlacementData(LEFT, 0, 0, TOP, 0, 0, H_LEFT, V_TOP, 0, 0, 0, 0)

        surroundingInteraction = surrInteraction

        model.lifelines.forEach[s|surrInteraction.children += transformLifeline(s)]
        model.interactions.forEach[s|transformInteraction(s)]

        // TODO close all blocks if not closed yet
        lifelineNodes.clear()
        surroundingInteraction = null
        elementIdOnLifeline.clear()
        executionCount = 0
        edgeCounter = 0
        return root
    }

    // Transformation for Lifelines
    private def KNode transformLifeline(Lifeline lifeline) {
        val lifelineNode = lifeline.createNode().associateWith(lifeline)

        lifelineNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.LIFELINE)

        val lifelineRect = lifelineNode.addRectangle.foregroundInvisible = true

        if (lifeline.caption != null) {
            var KContainerRendering captionRect = null
            switch STYLE.objectValue {
                case "Stylish": {
                    captionRect = lifelineRect.addRoundedRectangle(15, 15, 1)
                    captionRect.setBackgroundGradient(Colors.WHITE, Colors.CORNFLOWER_BLUE, 90)
//                captionRect.setAreaPlacementData().from(LEFT, 0, 0, TOP, 0, 0).to(RIGHT, 0, 0, TOP, 40, 0)
//                captionRect.addText(lifeline.caption).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
                    captionRect.setShadow(Colors.BLACK, 5)
                }
                case "Hello Kitty": {
                    captionRect = lifelineRect.addRoundedRectangle(15, 15, 1)
                    captionRect.setBackgroundGradient("#FFEEEE".color, "#FFBBBB".color, 90)
//                captionRect.setAreaPlacementData().from(LEFT, 0, 0, TOP, 0, 0).to(RIGHT, 0, 0, TOP, 40, 0)
//                captionRect.addText(lifeline.caption).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
                    captionRect.setShadow(Colors.PURPLE, 5)
                }
                default: {
                    captionRect = lifelineRect.addRectangle
//                captionRect.setAreaPlacementData().from(LEFT, 0, 0, TOP, 0, 0).to(RIGHT, 0, 0, TOP, 40, 0)
//                captionRect.addText(lifeline.caption).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
                }
            }
            captionRect.setAreaPlacementData().from(LEFT, 0, 0, TOP, 0, 0).to(RIGHT, 0, 0, TOP,
                2 * TEXTSIZE.intValue + 10, 0)
            captionRect.addText(lifeline.caption).setSurroundingSpaceGrid(TEXTSIZE.intValue, 0, 8,
                0).fontSize = TEXTSIZE.intValue
        } else {
            val usecaseRect = lifelineRect.addRectangle.foregroundInvisible = true
            usecaseRect.setAreaPlacementData().from(LEFT, 0, 0, TOP, 0, 0).to(LEFT, 30, 0, TOP,
                2 * TEXTSIZE.intValue + 10, 0)

            usecaseRect.addEllipse.setAreaPlacementData().from(LEFT, 0, 0.35f, TOP, 0, 0).to(LEFT, 0, 0.65f, TOP, 10, 0)
            usecaseRect.addPolyline(1).from(LEFT, 0, 0.5f, TOP, 10, 0).to(LEFT, 0, 0.5f, TOP, 2 * TEXTSIZE.intValue, 0)
            usecaseRect.addPolyline(1).from(LEFT, 0, 0.3f, TOP, 15, 0).to(LEFT, 0, 0.7f, TOP, 15, 0)
            usecaseRect.addPolyline(1).from(LEFT, 0, 0.5f, TOP, 2 * TEXTSIZE.intValue, 0).to(LEFT, 0, 0.3f, TOP,
                2 * TEXTSIZE.intValue + 10, 0)
            usecaseRect.addPolyline(1).from(LEFT, 0, 0.5f, TOP, 2 * TEXTSIZE.intValue, 0).to(LEFT, 0, 0.7f, TOP,
                2 * TEXTSIZE.intValue + 10, 0)

            val captionRect = lifelineRect.addRectangle.foregroundInvisible = true
            captionRect.setAreaPlacementData().from(LEFT, 30, 0, TOP, 0, 0).to(RIGHT, 0, 0, TOP,
                2 * TEXTSIZE.intValue + 10, 0)
            captionRect.addText(lifeline.usecaseCaption).fontSize = TEXTSIZE.intValue
        }

        // Coordinates for the dashed line of the Lifeline
        val lineCoordinates = new ArrayList<KPosition>
        lineCoordinates.add(createKPosition(LEFT, 0, 0.5f, TOP, 0, 0))
        lineCoordinates.add(createKPosition(LEFT, 0, 0.5f, BOTTOM, 0, 0))

        val dashedLine = lifelineRect.addRectangle.foregroundInvisible = true
        dashedLine.addPolyline(2, lineCoordinates).setLineStyle(LineStyle.DASH)
//        dashedLine.setAreaPlacementData().from(LEFT, 0, 0.5f, TOP, 40, 0).to(LEFT, 0, 0.5f, BOTTOM, 0, 0)
        dashedLine.setAreaPlacementData().from(LEFT, 0, 0.5f, TOP, 2 * TEXTSIZE.intValue + 10, 0).to(LEFT, 0, 0.5f,
            BOTTOM, 0, 0)

        lifelineNodes.put(lifeline.name, lifelineNode)

        return lifelineNode
    }

    // Dispatch for Transformation of different interactions
    private def dispatch KEdge transformInteraction(TwoLifelineMessage msg) {
        val transEdge = msg.createEdge().associateWith(msg)
        transEdge.setMessageRendering(msg.messageType.toString)

        edgeCount(transEdge)

        val label = KimlUtil.createInitializedLabel(transEdge)

        val labelText = msg.message
        val source = msg.sourceLifeline
        val target = msg.targetLifeline
        label.configureCenterEdgeLabel(labelText, TEXTSIZE.intValue, KlighdConstants.DEFAULT_FONT_NAME)

        transEdge.source = lifelineNodes.get(source.name)
        transEdge.target = lifelineNodes.get(target.name)

// TODO FIX??
//        if (msg.sourceStartBlock) {
//            createExecution(source)
//        }
//
//        if (msg.targetStartBlock) {
//            createExecution(target)
//        }
//
//        if (elementIdOnLifeline.containsKey(source.name)) {
//            transEdge.addLayoutParam(SequenceDiagramProperties.SOURCE_EXECUTION_ID,
//                elementIdOnLifeline.get(source.name).peek())
//        }
//
//        if (elementIdOnLifeline.containsKey(target.name)) {
//            transEdge.addLayoutParam(SequenceDiagramProperties.TARGET_EXECUTION_ID,
//                elementIdOnLifeline.get(target.name).peek())
//        }
//
//        if (msg.sourceEndBlock) {
//            endExecution(source, msg.sourceEndBlockCount)
//        }
//
//        if (msg.targetEndBlock) {
//            endExecution(target, msg.targetEndBlockCount)
//        }
        return transEdge
    }

    private def dispatch KEdge transformInteraction(OneLifelineMessage msg) {
        val transEdge = msg.createEdge().associateWith(msg)
        transEdge.setMessageRendering(msg.messageTypeLostAndFound.toString)

        edgeCount(transEdge)

// TODO FIX??
//        if (msg.startBlock) {
////            if (msg.messageTypeLostAndFound.equals("lost")) {
//            createExecution(msg.lifeline)
////            } else {
////            }
//        }
//
//        if (elementIdOnLifeline.containsKey(msg.lifeline.name)) {
//            if (msg.messageTypeLostAndFound.equals("lost")) {
//                transEdge.addLayoutParam(SequenceDiagramProperties.SOURCE_EXECUTION_ID,
//                    elementIdOnLifeline.get(msg.lifeline.name).peek())
//            } else {
//                transEdge.addLayoutParam(SequenceDiagramProperties.TARGET_EXECUTION_ID,
//                    elementIdOnLifeline.get(msg.lifeline.name).peek())
//            }
//        }
//
//        if (msg.endBlock) {
////            if (msg.messageTypeLostAndFound.equals("lost")) {
//            endExecution(msg.lifeline, msg.endBlockCount)
////            } else {
////            }
//        }
        val label = KimlUtil.createInitializedLabel(transEdge)

        val labelText = msg.caption
        label.configureCenterEdgeLabel(labelText, KlighdConstants.DEFAULT_FONT_SIZE, KlighdConstants.DEFAULT_FONT_NAME)
        transEdge.addPolyline(2).addHeadArrowDecorator()

        val dummyNode = KimlUtil.createInitializedNode()
        surroundingInteraction.children += dummyNode
        dummyNode.addEllipse.setBackground(Colors.BLACK)

        if (msg.messageTypeLostAndFound.equals("lost")) {
            transEdge.source = lifelineNodes.get(msg.lifeline.name)
            transEdge.target = dummyNode
            dummyNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.LOST_MESSAGE_TARGET)
        } else {
            transEdge.source = dummyNode
            transEdge.target = lifelineNodes.get(msg.lifeline.name)
            dummyNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.FOUND_MESSAGE_SOURCE)
        }

        return transEdge
    }

//    private def dispatch KNode transformInteraction(OneLifelineEndBlock end) {
//        val endBlockNode = end.createNode().associateWith(end)
//        return endBlockNode
//    }
    private def dispatch KNode transformInteraction(OneLifelineNote note) {
        val noteNode = note.createNode().associateWith(note)
        noteNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.COMMENT)

        // TODO 
        return noteNode
    }

    private def dispatch KNode transformInteraction(DestroyLifelineEvent destroy) {
        // TODO close blocks before if not closed yet
        val destroyNode = destroy.createNode().associateWith(destroy)
        // TODO Was macht das?
        destroyNode.addLayoutParam(SequenceDiagramProperties.DESTRUCTION, destroyNode)
        destroyNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.DESTRUCTION_EVENT)

        val destroyRect = destroyNode.addRectangle().foregroundInvisible = true

        val whiteBackgroundRect = destroyRect.addRectangle.foregroundInvisible = true
        whiteBackgroundRect.setGridPlacementData.from(LEFT, 0, 0, TOP, 0, 0.5f).to(RIGHT, 0, 0, BOTTOM, -2, 0).
            setBackground(Colors.WHITE)

        // Coordinates for the "X"
        destroyRect.addPolyline(2).from(LEFT, 0, 0, TOP, 0, 0).to(RIGHT, 0, 0, BOTTOM, 0, 0)
        destroyRect.addPolyline(2).from(RIGHT, 0, 0, TOP, 0, 0).to(LEFT, 0, 0, BOTTOM, 0, 0)

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

        // TODO use this?
//        val fragNodeRect = fragNode.addRoundedRectangle(2, 2, 2)
//
//        val captionRect = fragNodeRect.addRectangle.foregroundInvisible = true
//        captionRect.addText(frag.name).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
//
//        val list = new ArrayList<KPosition>
//        list.add(createKPosition(LEFT, 0, 0, BOTTOM, 0, 0))
//        list.add(createKPosition(RIGHT, 10, 0, BOTTOM, 0, 0))
//        list.add(createKPosition(RIGHT, 0, 0, BOTTOM, 10, 0))
//        list.add(createKPosition(RIGHT, 0, 0, TOP, 0, 0))
//
//        captionRect.addPolyline(2, list)
//        captionRect.setPointPlacementData(
//                    LEFT, 0, 0,
//                    TOP, 0, 0,
//                    H_LEFT, V_TOP,
//                    0, 0, 0, 0)
        val fragNodeRect = fragNode.addRoundedRectangle(2, 2, 2)

        val captionRect = fragNodeRect.addRectangle.foregroundInvisible = true
        captionRect.setGridPlacement(2000)

        val captionRect2 = captionRect.addRectangle.foregroundInvisible = true
        captionRect2.addText(frag.name).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13

        val list = new ArrayList<KPosition>
        list.add(createKPosition(LEFT, 0, 0, BOTTOM, 0, 0))
        list.add(createKPosition(RIGHT, 10, 0, BOTTOM, 0, 0))
        list.add(createKPosition(RIGHT, 0, 0, BOTTOM, 10, 0))
        list.add(createKPosition(RIGHT, 0, 0, TOP, 0, 0))

        captionRect2.addPolyline(2, list)

        fragNodeRect.addChildArea
        fragNodeRect.setGridPlacement = 1

        // TODO sections im messagerect
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

    // TODO time constraint between messages, 
    // TODO Duration constraint auch als property aber nur als string anhängen in der Darstellung
    // TODO Strichmann (Actor, Use cases)
    // TODO Zustandsinvariante als Notiz
    // ///////////////////////////////////////////////////////////////////////////////////////////////////
    // Rest
    // Specifies the correct Properties for the different message types
    private def KEdge setMessageRendering(KEdge edge, String type) {
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
            edge.addLayoutParam(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.CREATE)
            edge.addPolyline(2).setLineStyle(LineStyle.DASH).addAssociationArrowDecorator()
        } else if (type.equals("lost")) {
            edge.addLayoutParam(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.LOST)
        } else if (type.equals("found")) {
            edge.addLayoutParam(SequenceDiagramProperties.MESSAGE_TYPE, MessageType.FOUND)
        }

        return edge
    }

    // Creates an execution specification to display the "blocks" on the lifelines
    private def KNode createExecution(Lifeline l) {
        val execution = l.createNode().associateWith(l)
        // execution.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.ACTION_EXEC_SPECIFICATION)
        executionCount += 1
        if (elementIdOnLifeline.containsKey(l.name)) {
            val stack = elementIdOnLifeline.get(l.name)
            stack.push(executionCount)
            elementIdOnLifeline.put(l.name, stack)
        } else {
            val stack = new Stack()
            stack.push(executionCount)
            elementIdOnLifeline.put(l.name, stack)
        }
        execution.addLayoutParam(SequenceDiagramProperties.ELEMENT_ID, elementIdOnLifeline.get(l.name).peek())
        execution.addLayoutParam(SequenceDiagramProperties.EXECUTION_TYPE, SequenceExecutionType.EXECUTION)

        lifelineNodes.get(l.name).children.add(execution)

        execution.addRectangle

        return execution
    }

    // Ends the execution specification
    private def endExecution(Lifeline l, Integer endBlockCount) {
        if (endBlockCount > 0) {
            val stack = elementIdOnLifeline.get(l.name)
            for (var i = 0; i < endBlockCount; i++) {
                if (!stack.isEmpty) {
                    stack.pop()
                }
            }
            elementIdOnLifeline.put(l.name, stack)
        } else if (endBlockCount < 0) {
            val stack = elementIdOnLifeline.get(l.name)
            stack.clear()
            elementIdOnLifeline.put(l.name, stack)
        } else {
            val stack = elementIdOnLifeline.get(l.name)
            if (!stack.isEmpty) {
                stack.pop()
            }
            elementIdOnLifeline.put(l.name, stack)
        }
        val stack = elementIdOnLifeline.get(l.name)
        if (stack.isEmpty) {
            elementIdOnLifeline.remove(l.name)
        }
    }

    // Gives the edges an increasing number, so that the Algorithm knows the order of the messages
    private def edgeCount(KEdge e) {
        edgeCounter += 1
        val edgeLayout = e.getData(typeof(KEdgeLayout))
        edgeLayout.sourcePoint.y = edgeCounter
        edgeLayout.targetPoint.y = edgeCounter
    }

    /**
     * Adds an arrow decorator suitable for associations to the given polyline.
     * 
     * @param pl the polyline to add the decorator to.
     * @return the given polyline.
     */
    def private KRendering addAssociationArrowDecorator(KPolyline pl) {
        pl.addPolyline(2) => [
            it.points += createKPosition(LEFT, 0, 0, TOP, 0, 0)
            it.points += createKPosition(RIGHT, 0, 0, TOP, 0, 0.5f)
            it.points += createKPosition(LEFT, 0, 0, BOTTOM, 0, 0)
            it.setDecoratorPlacementData(10, 12, -6, 1.0f, true)
        // it.foreground = modelOptions.getProperty(OPTION_EDGE_COLOR).color
        ]
    }

}
