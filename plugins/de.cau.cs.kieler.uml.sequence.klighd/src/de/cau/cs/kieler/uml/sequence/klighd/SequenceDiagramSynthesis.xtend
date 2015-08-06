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
import de.cau.cs.kieler.papyrus.sequence.properties.SequenceExecution
import de.cau.cs.kieler.papyrus.sequence.p4sorting.LifelineSortingStrategy
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
import de.cau.cs.kieler.klighd.SynthesisOption
import com.google.common.collect.ImmutableList

class SequenceDiagramSynthesis extends AbstractDiagramSynthesis<SequenceDiagram> {

    // ///////////////////////////////////////////////////////////////////////////////////////////////////
    // Synthesis Options
    private static final val SynthesisOption STYLE = SynthesisOption.createChoiceOption("Style",
        ImmutableList::of("Boring", "Stylish", "Hello Kitty"), "Boring")

    private static final val SynthesisOption LIFELINESORTING = SynthesisOption.createChoiceOption("Lifeline Sorting",
        ImmutableList::of("Interactive", "Layer Based", "Short Messages"), "Interactive")
    
    // TODO fix initial value not taken at startup
    private static final val SynthesisOption TEXTSIZE = SynthesisOption.createRangeOption("Text Size", 5.0, 30.0, 1.0,
        13.0)

    override getDisplayedSynthesisOptions() {
        return ImmutableList::of(STYLE, LIFELINESORTING, TEXTSIZE);
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
    private val blocks = new HashMap<String, SequenceExecution>
    private val numberOfBlocksOnLifeline = new HashMap<String, Integer>
    private var edgeCounter = 0;

    // ///////////////////////////////////////////////////////////////////////////////////////////////////
    // Synthesis
    // Main Transformation
    override KNode transform(SequenceDiagram model) {
        val root = model.createNode()
        root.addLayoutParam(LayoutOptions.ALGORITHM, FixedLayoutProvider.ID)

        System.out.println(TEXTSIZE.floatValue)

        val surrInteraction = root.createNode().associateWith(model)
        root.children.add(surrInteraction)
        surrInteraction.addLayoutParam(LayoutOptions.ALGORITHM, SequenceDiagramLayoutProvider.ID)
//        surrInteraction.addLayoutParam(SequenceDiagramProperties.COORDINATE_SYSTEM, CoordinateSystem.KGRAPH)
        surrInteraction.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.SURROUNDING_INTERACTION)
        surrInteraction.addLayoutParam(LayoutOptions.BORDER_SPACING, 10f)
        surrInteraction.addLayoutParam(SequenceDiagramProperties.MESSAGE_SPACING, 5 * TEXTSIZE.floatValue)
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

        switch STYLE.objectValue {
            case "Stylish": {
                val surrInteractionRect = surrInteraction.addRoundedRectangle(10, 10, 2)
                surrInteractionRect.setShadow(Colors.BLACK, 10)

                val captionRect = surrInteractionRect.addRectangle.foregroundInvisible = true
//                captionRect.addText("sd " + model.diagramName).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
                captionRect.addText("sd " + model.diagramName).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = TEXTSIZE.
                    intValue
                captionRect.addPolyline(2, lineCoordinates)
                captionRect.setPointPlacementData(LEFT, 0, 0, TOP, 0, 0, H_LEFT, V_TOP, 0, 0, 0, 0)
            }
            case "Hello Kitty": {
                val surrInteractionRect = surrInteraction.addRoundedRectangle(10, 10, 2)
                surrInteractionRect.setShadow(Colors.PURPLE, 10)

                val captionRect = surrInteractionRect.addRectangle.foregroundInvisible = true
//                captionRect.addText("sd " + model.diagramName).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
                captionRect.addText("sd " + model.diagramName).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = TEXTSIZE.
                    intValue
                captionRect.addPolyline(2, lineCoordinates)
                captionRect.setPointPlacementData(LEFT, 0, 0, TOP, 0, 0, H_LEFT, V_TOP, 0, 0, 0, 0)
            }
            default: {
                val surrInteractionRect = surrInteraction.addRectangle

                val captionRect = surrInteractionRect.addRectangle.foregroundInvisible = true
//                captionRect.addText("sd " + model.diagramName).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
                captionRect.addText("sd " + model.diagramName).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = TEXTSIZE.
                    intValue
                captionRect.addPolyline(2, lineCoordinates)
                captionRect.setPointPlacementData(LEFT, 0, 0, TOP, 0, 0, H_LEFT, V_TOP, 0, 0, 0, 0)
            }
        }

        surroundingInteraction = surrInteraction

        model.lifelines.forEach[s|surrInteraction.children += transformLifeline(s)]
        model.interactions.forEach[s|transformInteraction(s)]

        // TODO close all blocks if not closed yet
        lifelineNodes.clear()
        surroundingInteraction = null
        blocks.clear()
        numberOfBlocksOnLifeline.clear()
        edgeCounter = 0

        return root
    }

    // Transformation for Lifelines
    private def KNode transformLifeline(Lifeline lifeline) {
        val lifelineNode = lifeline.createNode().associateWith(lifeline)

        lifelineNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.LIFELINE)

        val lifelineRect = lifelineNode.addRectangle.foregroundInvisible = true

        switch STYLE.objectValue {
            case "Stylish": {
                val captionRect = lifelineRect.addRoundedRectangle(15, 15, 1)
                captionRect.setBackgroundGradient(Colors.WHITE, Colors.CORNFLOWER_BLUE, 90)
//                captionRect.setAreaPlacementData().from(LEFT, 0, 0, TOP, 0, 0).to(RIGHT, 0, 0, TOP, 40, 0)
//                captionRect.addText(lifeline.caption).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
                captionRect.setAreaPlacementData().from(LEFT, 0, 0, TOP, 0, 0).to(RIGHT, 0, 0, TOP,
                    2 * TEXTSIZE.intValue + 10, 0)
                captionRect.addText(lifeline.caption).setSurroundingSpaceGrid(TEXTSIZE.intValue, 0, 8, 0).fontSize = TEXTSIZE.
                    intValue
                captionRect.setShadow(Colors.BLACK, 5)
            }
            case "Hello Kitty": {
                val captionRect = lifelineRect.addRoundedRectangle(15, 15, 1)
                captionRect.setBackgroundGradient("#FFEEEE".color, "#FFBBBB".color, 90)
//                captionRect.setAreaPlacementData().from(LEFT, 0, 0, TOP, 0, 0).to(RIGHT, 0, 0, TOP, 40, 0)
//                captionRect.addText(lifeline.caption).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
                captionRect.setAreaPlacementData().from(LEFT, 0, 0, TOP, 0, 0).to(RIGHT, 0, 0, TOP,
                    2 * TEXTSIZE.intValue + 10, 0)
                captionRect.addText(lifeline.caption).setSurroundingSpaceGrid(TEXTSIZE.intValue, 0, 8, 0).fontSize = TEXTSIZE.
                    intValue
                captionRect.setShadow(Colors.PURPLE, 5)
            }
            default: {
                val captionRect = lifelineRect.addRectangle
//                captionRect.setAreaPlacementData().from(LEFT, 0, 0, TOP, 0, 0).to(RIGHT, 0, 0, TOP, 40, 0)
//                captionRect.addText(lifeline.caption).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
                captionRect.setAreaPlacementData().from(LEFT, 0, 0, TOP, 0, 0).to(RIGHT, 0, 0, TOP,
                    2 * TEXTSIZE.intValue + 10, 0)
                captionRect.addText(lifeline.caption).setSurroundingSpaceGrid(TEXTSIZE.intValue, 0, 8, 0).fontSize = TEXTSIZE.
                    intValue
            }
        }
        
        //Coordinates for the dashed line of the Lifeline
        val lineCoordinates = new ArrayList<KPosition>
        lineCoordinates.add(createKPosition(LEFT, 0, 0.5f, TOP, 0, 0))
        lineCoordinates.add(createKPosition(LEFT, 0, 0.5f, BOTTOM, 0, 0))

        val dashedLine = lifelineRect.addRectangle.foregroundInvisible = true
        dashedLine.addPolyline(2, lineCoordinates).setLineStyle(LineStyle.DASH)
//        dashedLine.setAreaPlacementData().from(LEFT, 0, 0.5f, TOP, 40, 0).to(LEFT, 0, 0.5f, BOTTOM, 0, 0)
        dashedLine.setAreaPlacementData().from(LEFT, 0, 0.5f, TOP, 2 * TEXTSIZE.intValue + 10, 0).to(LEFT, 0, 0.5f, BOTTOM, 0,
            0)

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

        if (msg.sourceStartBlock) {
            // TODO
            // createBlock(source)
        } else if (msg.sourceEndBlock) {
            // TODO?
//            blocks.remove(source.name.concat(numberOfBlocksOnLifeline.get(source.name).toString()))
//            if (numberOfBlocksOnLifeline.get(source.name) < 1 || msg.sourceEndBlockCount < 0) {
//                numberOfBlocksOnLifeline.remove(source.name)
//            } else {
//                if (msg.sourceEndBlockCount > 0) {
//                    numberOfBlocksOnLifeline.put(source.name,
//                        numberOfBlocksOnLifeline.get(source.name) - msg.sourceEndBlockCount)
//                } else {
//                  //Standard value of sourceEndBlockCount if not used is 0
//                    numberOfBlocksOnLifeline.put(source.name, numberOfBlocksOnLifeline.get(source.name) - 1)
//                }
//            }
        }

        if (msg.targetStartBlock) {
            // TODO
            // createBlock(target)
        } else if (msg.targetEndBlock) {
            // TODO?
            // blocks.remove(target.name.concat(numberOfBlocksOnLifeline.get(target.name).toString()))
//            if (numberOfBlocksOnLifeline.get(target.name) < 1 || msg.targetEndBlockCount < 0) {
//                numberOfBlocksOnLifeline.remove(target.name)
//            } else {
//                if (msg.targetEndBlockCount > 0) {
//                    numberOfBlocksOnLifeline.put(target.name,
//                        numberOfBlocksOnLifeline.get(target.name) - msg.targetEndBlockCount)
//                } else {
//                  //Standard value of sourceEndBlockCount if not used is 0
//                    numberOfBlocksOnLifeline.put(target.name, numberOfBlocksOnLifeline.get(target.name) - 1)
//                }
//            }
        }

        if (blocks.containsKey(source.name)) {
            blocks.get(source.name.concat(numberOfBlocksOnLifeline.get(source.name).toString())).addMessage(transEdge)
        } else if (blocks.containsKey(target.name)) {
            blocks.get(target.name.concat(numberOfBlocksOnLifeline.get(target.name).toString())).addMessage(transEdge)
        }

        return transEdge
    }

    private def dispatch KEdge transformInteraction(OneLifelineMessage msg) {
        val transEdge = msg.createEdge().associateWith(msg)
//        transEdge.setMessageRendering(msg.messageType.toString)
        transEdge.setMessageRendering(msg.messageTypeLostAndFound.toString)

        edgeCount(transEdge)

        val label = KimlUtil.createInitializedLabel(transEdge)

        val labelText = msg.caption
        label.configureCenterEdgeLabel(labelText, KlighdConstants.DEFAULT_FONT_SIZE, KlighdConstants.DEFAULT_FONT_NAME)
        transEdge.addPolyline(2).addHeadArrowDecorator()

        val dummyNode = surroundingInteraction.createNode()
        // val circle = 
        dummyNode.addRectangle.setForeground(Colors.AQUAMARINE)

        // TODO source und target setzen und kreis am ende
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

        // TODO
        return endBlockNode
    }

    private def dispatch KNode transformInteraction(OneLifelineNote note) {
        val noteNode = note.createNode().associateWith(note)
        noteNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.COMMENT)

        // TODO 
        return noteNode
    }

    private def dispatch KNode transformInteraction(DestroyLifelineEvent destroy) {
        // TODO close blocks before if not closed yet
        val destroyNode = destroy.createNode().associateWith(destroy)
        destroyNode.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.DESTRUCTION_EVENT)

        // TODO 2 polylines point placement
        //Coordinates for the Symbol to destroy a lifeline
        val lineCoordinates = new ArrayList<KPosition>
        lineCoordinates.add(createKPosition(LEFT, 0, 0, TOP, 0, 0))
        lineCoordinates.add(createKPosition(LEFT, 0, 0.5f, TOP, 5, 0))
        lineCoordinates.add(createKPosition(RIGHT, 0, 0, TOP, 0, 0))
        lineCoordinates.add(createKPosition(LEFT, 0, 0.5f, TOP, 5, 0))
        lineCoordinates.add(createKPosition(RIGHT, 0, 0, BOTTOM, 0, 0))
        lineCoordinates.add(createKPosition(LEFT, 0, 0.5f, TOP, 5, 0))
        lineCoordinates.add(createKPosition(LEFT, 0, 0, BOTTOM, 0, 0))

        val destroyRect = destroyNode.addRectangle().foregroundInvisible = true
        destroyRect.addPolyline(2, lineCoordinates)

//        destroyRect.addPolyline(2).setPointPlacementData(LEFT, 0, 0, TOP, 0, 0, H_LEFT, V_TOP, 0, 0, 0, 0).setForeground(Colors.RED)
//        destroyRect.addPolyline(2).setGridPlacementData(0, 0, (createKPosition(LEFT,0,0,TOP,0,0)), createKPosition(RIGHT,0,0,BOTTOM,0,0)).
//            setForeground(Colors.RED)
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

    // TODO time constraint between messages
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

    /*  private void createExecution(final LayoutMapping<IGraphicalEditPart> mapping,
     *         final ShapeNodeEditPart lifelineEditPart, final List<SequenceExecution> executions,
     *         final ShapeNodeEditPart childEditPart, final NodeType nodeType, final KNode executionNode) {
     * 
     *     KShapeLayout executionLayout = executionNode.getData(KShapeLayout.class);
     *     IFigure executionFigure = childEditPart.getFigure();
     *     Rectangle executionBounds = getAbsoluteBounds(executionFigure);
     * 
     *     SequenceExecution execution = new SequenceExecution();
     * 
     *     if (nodeType == NodeType.BEHAVIOUR_EXEC_SPECIFICATION
     *             || nodeType == NodeType.ACTION_EXEC_SPECIFICATION) {
     * 
     *         executionNode.getData(KShapeLayout.class).setProperty(SequenceDiagramProperties.NODE_TYPE,
     *                 nodeType);
     *         execution.setType(SequenceExecutionType.EXECUTION);
     *     } else if (nodeType == NodeType.DURATION_CONSTRAINT) {
     *         executionNode.getData(KShapeLayout.class).setProperty(SequenceDiagramProperties.NODE_TYPE,
     *                 nodeType);
     *         execution.setType(SequenceExecutionType.DURATION);
     *     } else if (nodeType == NodeType.TIME_CONSTRAINT) {
     *         executionNode.getData(KShapeLayout.class).setProperty(SequenceDiagramProperties.NODE_TYPE,
     *                 nodeType);
     *         execution.setType(SequenceExecutionType.TIME_CONSTRAINT);
     *     }
     * 
     *     // Walk through the connected messages
     *     for (Object targetConn : childEditPart.getTargetConnections()) {
     *         if (targetConn instanceof ConnectionEditPart) {
     *             ConnectionEditPart connectionEditPart = (ConnectionEditPart) targetConn;
     *             mapping.getProperty(CONNECTIONS).add(connectionEditPart);
     * 
     *             execution.addMessage(connectionEditPart);
     *         }
     *     }
     *     for (Object sourceConn : childEditPart.getSourceConnections()) {
     *         if (sourceConn instanceof ConnectionEditPart) {
     *             ConnectionEditPart connectionEditPart = (ConnectionEditPart) sourceConn;
     * 
     *             execution.addMessage(connectionEditPart);
     *         }
     *     }
     *     execution.setOrigin(executionNode);
     *     executions.add(execution);
     executionLayout.setProperty(PapyrusProperties.EXECUTION, execution); */
    // Creates an execution specification to display the "blocks" on the lifelines
    private def KNode createBlock(Lifeline l) {
        val block = l.createNode().associateWith(l)
        block.addLayoutParam(SequenceDiagramProperties.NODE_TYPE, NodeType.ACTION_EXEC_SPECIFICATION)

        // TODO Fix SequenceExecution?!
        // val execution = new SequenceExecution()
        if (numberOfBlocksOnLifeline.containsKey(l.name)) {
            numberOfBlocksOnLifeline.put(l.name, numberOfBlocksOnLifeline.get(l.name) + 1)
        } else {
            numberOfBlocksOnLifeline.put(l.name, 1)
        }
        // blocks.put(l.name.concat(numberOfBlocksOnLifeline.get(l.name).toString()), execution)
        // execution.setType(SequenceExecutionType.EXECUTION)
        // execution.setOrigin(lifelineNodes.get(l.name))
        lifelineNodes.get(l.name).children.add(block)

        block.addRectangle

        // TODO do stuff
        return block
    }

    // Gives the edges an increasing coordinate, so that the Algorithm knows the order of the messages
    private def edgeCount(KEdge e) {
        edgeCounter += 1
        val edgeLayout = e.getData(typeof(KEdgeLayout));
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
            it.setDecoratorPlacementData(12, 12, -6, 1.0f, true)
        // it.foreground = modelOptions.getProperty(OPTION_EDGE_COLOR).color
        ]
    }

}
