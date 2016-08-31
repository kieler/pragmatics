/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.uml.sequence.klighd

import com.google.common.collect.ImmutableList
import com.google.common.collect.Lists
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.krendering.Colors
import de.cau.cs.kieler.klighd.krendering.KContainerRendering
import de.cau.cs.kieler.klighd.krendering.KPolyline
import de.cau.cs.kieler.klighd.krendering.KPosition
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory
import de.cau.cs.kieler.klighd.krendering.LineStyle
import de.cau.cs.kieler.klighd.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KLabelExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPortExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.cau.cs.kieler.papyrus.sequence.SequenceDiagramLayoutProvider
import de.cau.cs.kieler.papyrus.sequence.p4sorting.LifelineSortingStrategy
import de.cau.cs.kieler.papyrus.sequence.properties.MessageType
import de.cau.cs.kieler.papyrus.sequence.properties.NodeType
import de.cau.cs.kieler.papyrus.sequence.properties.SequenceDiagramOptions
import de.cau.cs.kieler.papyrus.sequence.properties.SequenceExecutionType
import de.cau.cs.kieler.uml.sequence.text.sequence.DestroyLifelineEvent
import de.cau.cs.kieler.uml.sequence.text.sequence.Fragment
import de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline
import de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeLostAndFound
import de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeOne
import de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeTwo
import de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage
import de.cau.cs.kieler.uml.sequence.text.sequence.Refinement
import de.cau.cs.kieler.uml.sequence.text.sequence.Section
import de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage
import de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram
import de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Stack
import javax.inject.Inject
import org.eclipse.elk.core.klayoutdata.KEdgeLayout
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.FixedLayouterOptions
import org.eclipse.elk.core.util.ElkUtil
import org.eclipse.elk.graph.KEdge
import org.eclipse.elk.graph.KNode

/**
 * This class is used for the transformation of a Sequence Diagram Model into a KGraph.
 * 
 * @author dja
 */
class SequenceDiagramSynthesis extends AbstractDiagramSynthesis<SequenceDiagram> {


    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // Synthesis Options
    
    /** Add options to edit the style of the diagram. */
    private static final val SynthesisOption STYLE = SynthesisOption.createChoiceOption
        ("Style", ImmutableList::of("Black and White", "Stylish", "Hello Kitty"), "Stylish")

    /** Add options to change the lifelinesorting algorithm. */
    private static final val SynthesisOption LIFELINESORTING = SynthesisOption.createChoiceOption
        (
            "Lifeline Sorting", 
            ImmutableList::of("Interactive", "Layer Based", "Short Messages"), "Interactive"
        )
    
    /** @{inheritDoc} */
    override getDisplayedSynthesisOptions() {
        return ImmutableList::of(STYLE, LIFELINESORTING)
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // Extensions
    @Inject extension KNodeExtensions
    @Inject extension KEdgeExtensions
    @Inject extension KPortExtensions
    @Inject extension KLabelExtensions
    @Inject extension KRenderingExtensions
    @Inject extension KContainerRenderingExtensions
    @Inject extension KPolylineExtensions
    @Inject extension KColorExtensions
    extension KRenderingFactory = KRenderingFactory.eINSTANCE


    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // Variables
    
    /** Map of all KNodes that model lifelines in the KGraph. */
    private val lifelineNodes = new HashMap<Lifeline, KNode>
    /** The KNode of the surrounding interaction. */
    private var KNode surroundingInteraction
    /** Map with every lifeline as key and a Stack as value. The stack consists of the elementIds 
     * currently used for the executions on such that lifeline.
     */
    private val elementIdOnLifeline = new HashMap<Lifeline, Stack<Integer>>
    /** Increasing number, such that every element receives a different Id. */
    private var elementId = 1
    /** Stack of all top-level fragments. */
    private var fragmentList = new Stack<Integer>
    /** The elementId of the last created edge, such that it can't accidently happen to increase the 
     * elementId and use a wrong elementId for an edge. 
     */
    private var edgeId = 1
    /** Map of the elementId for every lifeline. */
    private var lifelineId = new HashMap<String, Integer>
    /**
     * List of all fragments that lie inside of another fragment. 
     * It will be cleared after every top-level fragment.
     */
    private val fragmentDepths = new ArrayList<Integer>


    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // Synthesis
    
    /** @{inheritDoc} */
    override KNode transform(SequenceDiagram model) {
        val root = model.createNode()
        root.addLayoutParam(CoreOptions.ALGORITHM, FixedLayouterOptions.ALGORITHM_ID)

        // Attach different Properties.
        val surrInteraction = root.createNode().associateWith(model)
        root.children.add(surrInteraction)
        surrInteraction.addSurroundingInteractionCoreOptions()

        // Diagramproperties for Lifelinesorting.
        switch LIFELINESORTING.objectValue {
            case "Layer Based":
                surrInteraction.addLayoutParam(SequenceDiagramOptions.LIFELINE_SORTING_STRATEGY,
                    LifelineSortingStrategy.LAYER_BASED)
            case "Short Message":
                surrInteraction.addLayoutParam(SequenceDiagramOptions.LIFELINE_SORTING_STRATEGY,
                    LifelineSortingStrategy.SHORT_MESSAGES)
            default:
                surrInteraction.addLayoutParam(SequenceDiagramOptions.LIFELINE_SORTING_STRATEGY,
                    LifelineSortingStrategy.INTERACTIVE)
        }

        // Diagram style Properties.
        var KContainerRendering surrInteractionRect = null
        switch STYLE.objectValue {
            case "Stylish": {
                surrInteractionRect = surrInteraction.setNodeSize(100, 100)
                .addRoundedRectangle(10, 10, 2)
                surrInteractionRect.setShadow(Colors.BLACK, 10)
            }
            case "Hello Kitty": {
                surrInteractionRect = surrInteraction.setNodeSize(100, 100)
                .addRoundedRectangle(10, 10, 2)
                surrInteractionRect.setShadow(Colors.PURPLE, 10)
            }
            default: {
                surrInteractionRect = surrInteraction.setNodeSize(100, 100).addRectangle
            }
        }

        // Coordinates for the Rectangle where the diagram name is inside.
        val lineCoordinates = new ArrayList<KPosition>(4)
        lineCoordinates.add(createKPosition(LEFT, 0, 0, BOTTOM, 0, 0))
        lineCoordinates.add(createKPosition(RIGHT, 10, 0, BOTTOM, 0, 0))
        lineCoordinates.add(createKPosition(RIGHT, 0, 0, BOTTOM, 10, 0))
        lineCoordinates.add(createKPosition(RIGHT, 0, 0, TOP, 0, 0))

        // Rectangle for the diagram name.
        val captionRect = surrInteractionRect.addRectangle.foregroundInvisible = true
        captionRect.addText("sd " + model.diagramName).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
        captionRect.addPolyline(2, lineCoordinates)
        captionRect.setPointPlacementData(LEFT, 0, 0, TOP, 0, 0, H_LEFT, V_TOP, 20, 0, 0, 0)

        surroundingInteraction = surrInteraction

        // Start to transform the interactions and the other nodes.
        model.lifelines.forEach[s|surrInteraction.children += transformLifeline(s)]
        model.interactions.forEach[s|transformInteraction(s)]

        // Reset class variables.
        lifelineNodes.clear()
        surroundingInteraction = null
        elementIdOnLifeline.clear()
        elementId = 0
        fragmentList.clear()
        edgeId = 0
        lifelineId.clear()
        fragmentDepths.clear()

        return root
    }
    
    /**
     * Adds the specific Layout Options to the given KNode.
     * 
     * @param node The KNode where the layout options shall be applied to.
     */
    private def void addSurroundingInteractionCoreOptions(KNode node) {
        node.addLayoutParam(CoreOptions.ALGORITHM, SequenceDiagramLayoutProvider.ID)
        .addLayoutParam(SequenceDiagramOptions.NODE_TYPE, NodeType.SURROUNDING_INTERACTION)
        .addLayoutParam(SequenceDiagramOptions.SPACING_BORDER, 10f)
        .addLayoutParam(SequenceDiagramOptions.MESSAGE_SPACING, 65f)
        .addLayoutParam(SequenceDiagramOptions.LIFELINE_Y_POS, 50f)
        .addLayoutParam(SequenceDiagramOptions.LIFELINE_HEADER_HEIGHT, 40f)
        .addLayoutParam(SequenceDiagramOptions.AREA_HEADER_HEIGHT, 45f)
    }

    /**
     * The transformation of a lifeline into a KNode. It will receive KRendering-informations as well. 
     * 
     * @param lifeline A lifeline out of the surrounding interaction.
     * @return The KNode of the given lifeline.
     */
    private def KNode transformLifeline(Lifeline lifeline) {
        val lifelineNode = lifeline.createNode().associateWith(lifeline)
        lifelineNode.addLayoutParam(SequenceDiagramOptions.NODE_TYPE, NodeType.LIFELINE)

        elementId += 1

        lifelineId.put(lifeline.name, elementId)

        val lifelineRect = lifelineNode.addRectangle.foregroundInvisible = true

        if (lifeline.caption != null) {
            // Rendering for a lifeline.
            var KContainerRendering captionRect = null
            switch STYLE.objectValue {
                case "Stylish": {
                    captionRect = lifelineRect.addRoundedRectangle(15, 15, 1)
                    captionRect.setBackgroundGradient(Colors.WHITE, Colors.CORNFLOWER_BLUE, 90)
                    captionRect.setShadow(Colors.BLACK, 5)
                }
                case "Hello Kitty": {
                    captionRect = lifelineRect.addRoundedRectangle(15, 15, 1)
                    captionRect.setBackgroundGradient("#FFEEEE".color, "#FFBBBB".color, 90)
                    captionRect.setShadow(Colors.PURPLE, 5)
                }
                default: {
                    captionRect = lifelineRect.addRectangle
                }
            }
            captionRect.setAreaPlacementData().from(LEFT, 0, 0, TOP, 0, 0).to(RIGHT, 0, 0, TOP, 36, 0)
            captionRect.addText(lifeline.caption).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 13
        } else {
            // Rendering for a usecase.
            val usecaseRect = lifelineRect.addRectangle.foregroundInvisible = true
            usecaseRect.setAreaPlacementData().from(LEFT, 0, 0, TOP, 0, 0).to(LEFT, 30, 0, TOP, 36, 0)

            usecaseRect.addEllipse.setAreaPlacementData().from(LEFT, 0, 0.35f, TOP, 0, 0)
            .to(LEFT, 0, 0.65f, TOP, 10, 0)
            usecaseRect.addPolyline(1).from(LEFT, 0, 0.5f, TOP, 10, 0).to(LEFT, 0, 0.5f, TOP, 26, 0)
            usecaseRect.addPolyline(1).from(LEFT, 0, 0.3f, TOP, 15, 0).to(LEFT, 0, 0.7f, TOP, 15, 0)
            usecaseRect.addPolyline(1).from(LEFT, 0, 0.5f, TOP, 26, 0).to(LEFT, 0, 0.3f, TOP, 36, 0)
            usecaseRect.addPolyline(1).from(LEFT, 0, 0.5f, TOP, 26, 0).to(LEFT, 0, 0.7f, TOP, 36, 0)

            val captionRect = lifelineRect.addRectangle.foregroundInvisible = true
            captionRect.setAreaPlacementData().from(LEFT, 30, 0, TOP, 0, 0).to(RIGHT, 0, 0, TOP, 36, 0)
            captionRect.addText(lifeline.usecaseCaption).fontSize = 13
        }

        // Coordinates for the dashed line of the Lifeline.
        val lineCoordinates = new ArrayList<KPosition>(2)
        lineCoordinates.add(createKPosition(LEFT, 0, 0.5f, TOP, 0, 0))
        lineCoordinates.add(createKPosition(LEFT, 0, 0.5f, BOTTOM, 0, 0))

        val dashedLine = lifelineRect.addRectangle.foregroundInvisible = true
        dashedLine.addPolyline(2, lineCoordinates).setLineStyle(LineStyle.DASH)
        dashedLine.setAreaPlacementData().from(LEFT, 0, 0.5f, TOP, 36, 0).to(LEFT, 0, 0.5f, BOTTOM, 0, 0)

        lifelineNodes.put(lifeline, lifelineNode)

        return lifelineNode
    }

    /**
     * The transformation of a message between two lifelines into a KEdge. It will receive KRendering-
     * informations as well.
     * 
     * @param msg A message betweeen two lifelines out of the surrounding interaction.
     * @return The KEdge of the given message.
     */
    private def dispatch KEdge transformInteraction(TwoLifelineMessage msg) {
        val transEdge = msg.createEdge().associateWith(msg)
        transEdge.setMessageRendering(msg.messageType)

        // Create a label.
        val label = ElkUtil.createInitializedLabel(transEdge)
        val labelText = msg.message
        label.configureCenterEdgeLabel(labelText, 13, KlighdConstants.DEFAULT_FONT_NAME)

        // Set the source and target destination of the message.
        val source = msg.sourceLifeline
        val target = msg.targetLifeline
        transEdge.source = lifelineNodes.get(source)
        transEdge.target = lifelineNodes.get(target)

        edgeOrder(transEdge)
        
        // Check if different options are used in the textual description.
        if (msg.sourceNote != null) {
            createNote(msg.sourceNote, edgeId)
        }

        if (msg.targetNote != null) {
            createNote(msg.targetNote, edgeId)
        }

        if (msg.sourceStartExec || msg.sourceStartEndExec) {
            createExecution(source)
        }

        if (msg.targetStartExec || msg.targetStartEndExec) {
            createExecution(target)
        }

        if (elementIdOnLifeline.containsKey(source)) {
            val List<Integer> executionList = Lists.newArrayList(elementIdOnLifeline.get(source))

            transEdge.addLayoutParam(SequenceDiagramOptions.SOURCE_EXECUTION_IDS, executionList)
        }

        if (elementIdOnLifeline.containsKey(target)) {
            val List<Integer> executionList = Lists.newArrayList(elementIdOnLifeline.get(target))

            transEdge.addLayoutParam(SequenceDiagramOptions.TARGET_EXECUTION_IDS, executionList)
        }

        if (msg.sourceEndExec || msg.sourceStartEndExec) {
            endExecution(source, msg.sourceEndExecCount)
        }

        if (msg.targetEndExec || msg.targetStartEndExec) {
            endExecution(target, msg.targetEndExecCount)
        }

        // Check if message is inside of a fragment.
        if (msg.eContainer instanceof Section) {
            // Check if the fragment is inside of another fragment.
            if (msg.eContainer.eContainer.eContainer instanceof Section) {
                val i = fragmentList.pop()
                fragmentDepths.add(i)
                if (!fragmentDepths.contains(fragmentList.last)) {
                    fragmentDepths.add(fragmentList.last)
                }
                var list = new ArrayList(fragmentDepths)
                transEdge.addLayoutParam(SequenceDiagramOptions.AREA_IDS, list)
                transEdge.addLayoutParam(SequenceDiagramOptions.PARENT_AREA_ID, fragmentList.last)
            } else {
                val list = new ArrayList(1)
                list.add(fragmentList.last)
                transEdge.addLayoutParam(SequenceDiagramOptions.AREA_IDS, list)
            }
        }

        return transEdge
    }

    /**
     * The transformation of a lost or found message into a KEdge. It will receive KRendering-
     * informations as well.
     * 
     * @param msg A lost or found message out of the surrounding interaction.
     * @return The KEdge of the given message.
     */
    private def dispatch KEdge transformInteraction(OneLifelineMessage msg) {
        val transEdge = msg.createEdge().associateWith(msg)
        transEdge.setMessageRendering(msg.messageTypeLostAndFound)

        // Create a label.
        val label = ElkUtil.createInitializedLabel(transEdge)
        val labelText = msg.message
        label.configureCenterEdgeLabel(labelText, 13, KlighdConstants.DEFAULT_FONT_NAME)
        transEdge.addPolyline(2).addHeadArrowDecorator()

        // Create a dummy node as source or target destination.
        val dummyNode = ElkUtil.createInitializedNode()
        surroundingInteraction.children.add(dummyNode)
        dummyNode.addEllipse.setBackground(Colors.BLACK)

        if (msg.messageTypeLostAndFound.equals(MessageTypeLostAndFound.LOST)) {
            transEdge.source = lifelineNodes.get(msg.lifeline)
            transEdge.target = dummyNode
            dummyNode.addLayoutParam(SequenceDiagramOptions.NODE_TYPE, NodeType.LOST_MESSAGE_TARGET)
        } else {
            transEdge.source = dummyNode
            transEdge.target = lifelineNodes.get(msg.lifeline)
            dummyNode.addLayoutParam(SequenceDiagramOptions.NODE_TYPE, NodeType.FOUND_MESSAGE_SOURCE)
        }

        edgeOrder(transEdge)

        // Check if different options are used in the textual description.
        if (msg.note != null) {
            createNote(msg.note, edgeId)
        }

        if (msg.startExec || msg.startEndExec) {
            createExecution(msg.lifeline)
        }

        if (elementIdOnLifeline.containsKey(msg.lifeline)) {
            if (msg.messageTypeLostAndFound.equals(MessageTypeLostAndFound.LOST)) {
                val List<Integer> executionList = Lists
                .newArrayList(elementIdOnLifeline.get(msg.lifeline))

                transEdge.addLayoutParam(SequenceDiagramOptions.SOURCE_EXECUTION_IDS, executionList)
            } else {
                val List<Integer> executionList = Lists
                .newArrayList(elementIdOnLifeline.get(msg.lifeline))

                transEdge.addLayoutParam(SequenceDiagramOptions.TARGET_EXECUTION_IDS, executionList)
            }
        }

        if (msg.endExec || msg.startEndExec) {
            endExecution(msg.lifeline, msg.endExecCount)
        }

        // Check if message is inside of a fragment.
        if (msg.eContainer instanceof Section) {
            // Check if the fragment is inside of another fragment.
            if (msg.eContainer.eContainer.eContainer instanceof Section) {
                val i = fragmentList.pop()
                fragmentDepths.add(i)
                if (!fragmentDepths.contains(fragmentList.last)) {
                    fragmentDepths.add(fragmentList.last)
                }
                var list = new ArrayList(fragmentDepths)
                transEdge.addLayoutParam(SequenceDiagramOptions.AREA_IDS, list)
                transEdge.addLayoutParam(SequenceDiagramOptions.PARENT_AREA_ID, fragmentList.last)
            } else {
                val list = new ArrayList(1)
                list.add(fragmentList.last)
                transEdge.addLayoutParam(SequenceDiagramOptions.AREA_IDS, list)
            }
        }
        return transEdge
    }

    /**
     * The transformation of a selfmessage into a KEdge. It will receive KRendering-informations as well.
     * 
     * @param msg A selfmessage out of the surrounding interaction.
     * @return The Kedge of the given message.
     */
    private def dispatch KEdge transformInteraction(SelfMessage msg) {
        val transEdge = msg.createEdge().associateWith(msg)
        
        transEdge.setMessageRendering(msg.messageType)

        // Create a label.
        val label = ElkUtil.createInitializedLabel(transEdge)
        val labelText = msg.message
        label.configureCenterEdgeLabel(labelText, 13, KlighdConstants.DEFAULT_FONT_NAME)

        // Set the source and target destination of the message.
        transEdge.source = lifelineNodes.get(msg.lifeline)
        transEdge.target = lifelineNodes.get(msg.lifeline)

        edgeOrder(transEdge)

        // Check if different options are used in the textual description.
        if (msg.note != null) {
            createNote(msg.note, edgeId)
        }

        if (msg.startExec || msg.startEndExec) {
            createExecution(msg.lifeline)
        }

        if (elementIdOnLifeline.containsKey(msg.lifeline)) {
            val List<Integer> executionList = Lists
            .newArrayList(elementIdOnLifeline.get(msg.lifeline))

            transEdge.addLayoutParam(SequenceDiagramOptions.SOURCE_EXECUTION_IDS, executionList)
        }

        if (msg.endExec || msg.startEndExec) {
            endExecution(msg.lifeline, msg.endExecCount)
        }

        // Check if message is inside of a fragment.
        if (msg.eContainer instanceof Section) {
            // Check if the fragment is inside of another fragment.
            if (msg.eContainer.eContainer.eContainer instanceof Section) {
                val i = fragmentList.pop()
                fragmentDepths.add(i)
                if (!fragmentDepths.contains(fragmentList.last)) {
                    fragmentDepths.add(fragmentList.last)
                }
                var list = new ArrayList(fragmentDepths)
                transEdge.addLayoutParam(SequenceDiagramOptions.AREA_IDS, list)
                transEdge.addLayoutParam(SequenceDiagramOptions.PARENT_AREA_ID, fragmentList.last)
            } else {
                val list = new ArrayList(1)
                list.add(fragmentList.last)
                transEdge.addLayoutParam(SequenceDiagramOptions.AREA_IDS, list)
            }
        }

        return transEdge
    }

    /**
     * The transformation of a destruction-event into a KNode. It will receive KRendering-
     * informations as well.
     * 
     * @param destroy A destruction-event out of the surrounding interaction.
     * @return The KNode of the given event.
     */
    private def dispatch KNode transformInteraction(DestroyLifelineEvent destroy) {
        val destroyNode = destroy.createNode().associateWith(destroy)
        destroyNode.addLayoutParam(SequenceDiagramOptions.NODE_TYPE, NodeType.DESTRUCTION_EVENT)

        val destroyRect = destroyNode.setNodeSize(20, 20).addRectangle().foregroundInvisible = true

        // A rectangle that overpaints the rest of the dashed line of a lifeline white.
        val whiteBackgroundRect = destroyRect.addRectangle.foregroundInvisible = true
        whiteBackgroundRect.setGridPlacementData.from(LEFT, 0, 0, TOP, 0, 0.5f)
        .to(RIGHT, 0, 0, BOTTOM, -2, 0).setBackground(Colors.WHITE)

        // Two polylines with Coordinates for the "X".
        destroyRect.addPolyline(2).from(LEFT, 0, 0, TOP, 0, 0).to(RIGHT, 0, 0, BOTTOM, 0, 0)
        destroyRect.addPolyline(2).from(RIGHT, 0, 0, TOP, 0, 0).to(LEFT, 0, 0, BOTTOM, 0, 0)

        lifelineNodes.get(destroy.lifeline).children.add(destroyNode)

        return destroyNode
    }

    /**
     * The transformation of a fragment into a KNode. It will receive KRendering-informations as well.
     * 
     * @param frag A fragment out of the surrounding interaction.
     * @return The KNode of the given fragment.
     */
    private def dispatch KNode transformInteraction(Fragment frag) {
        val fragNode = frag.createNode().associateWith(frag)
        
        val sectionSize = frag.sections.size
        
        // var sectionCount = 0
        
        // Different node type, if the fragment consists of multiple regions or not.
        if (sectionSize > 1) {
            fragNode.addLayoutParam(SequenceDiagramOptions.NODE_TYPE, NodeType.COMBINED_FRAGMENT)
        } else {
            fragNode.addLayoutParam(SequenceDiagramOptions.NODE_TYPE, NodeType.INTERACTION_USE)
        }

        elementId += 1

        fragmentList.push(elementId)

        fragNode.addLayoutParam(SequenceDiagramOptions.ELEMENT_ID, elementId)

        surroundingInteraction.children.add(fragNode)

        // Coordinates for the Rectangle where the fragment name is inside.
        val lineCoordinates = new ArrayList<KPosition>(4)
        lineCoordinates.add(createKPosition(LEFT, 0, 0, BOTTOM, 0, 0))
        lineCoordinates.add(createKPosition(RIGHT, 10, 0, BOTTOM, 0, 0))
        lineCoordinates.add(createKPosition(RIGHT, 0, 0, BOTTOM, 10, 0))
        lineCoordinates.add(createKPosition(RIGHT, 0, 0, TOP, 0, 0))

        //Set the style options of a fragment.
        var KContainerRendering fragNodeRect = null
        switch STYLE.objectValue {
            case "Stylish": {
                fragNodeRect = fragNode.addRoundedRectangle(10, 10, 2)
            }
            case "Hello Kitty": {
                fragNodeRect = fragNode.addRoundedRectangle(10, 10, 2)
            }
            default: {
                fragNodeRect = fragNode.addRectangle
            }
        }
        fragNodeRect.backgroundInvisible = true

        // Create the Rectangle with the fragment name inside.
        val captionRect = fragNodeRect.addRoundedRectangle(10, 10, 2).foregroundInvisible = true
        captionRect.setBackground(Colors.WHITE)
        captionRect.addText(frag.name).setSurroundingSpaceGrid(15, 0, 0, 0).fontSize = 13
        captionRect.addPolyline(2, lineCoordinates)
        captionRect.setPointPlacementData(LEFT, 1, 0, TOP, 1, 0, H_LEFT, V_TOP, 0, 0, 0, 0)

        // Go through all the contents of a fragment.
        for (sect : frag.sections) {
            // sectionCount += 1
            // val sectRect = fragNodeRect.addRectangle // .foregroundInvisible = true
            if (sect.label != null) {
                val label = ElkUtil.createInitializedLabel(fragNode)

                val labelText = "[" + sect.label + "]"
                label.configureCenterEdgeLabel(labelText, 13, KlighdConstants.DEFAULT_FONT_NAME)
            }
            
            // Start to transform the interactions inside of the fragment.
            sect.interactions.forEach[s|transformInteraction(s)]
//            if (sectionSize - sectionCount > 0) {
//                sectRect.addPolyline(2).from(LEFT, 0, 0, BOTTOM, 0, 0).to(RIGHT, 0, 0, BOTTOM, 0, 0)
//            }

            fragmentDepths.clear()
        }

        return fragNode
    }

    // Not supported so far.
    private def dispatch KNode transformInteraction(Refinement ref) {
        val refNode = ref.createNode().associateWith(ref)
        refNode.addLayoutParam(SequenceDiagramOptions.NODE_TYPE, NodeType.INTERACTION_USE)

        val label = ref.label

        return refNode
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // Rest
        
    /**
     * Specifies the correct Properties for the different message types. 
     * 
     * @param edge The edge to add the message type properties to.
     * @param type The message type of the message.
     * @return The given edge.
     */
    private def dispatch KEdge setMessageRendering(KEdge edge, MessageTypeOne type) {
        switch(type) {
            case MessageTypeOne.SYNC: {
                edge.addLayoutParam(SequenceDiagramOptions.MESSAGE_TYPE, MessageType.SYNCHRONOUS)
                edge.addPolyline(2).addHeadArrowDecorator()
            }
            case MessageTypeOne.ASYNC: {
                edge.addLayoutParam(SequenceDiagramOptions.MESSAGE_TYPE, MessageType.ASYNCHRONOUS)
                edge.addPolyline(2).addAssociationArrowDecorator()
            }
            case MessageTypeOne.RESPONSE: {
                edge.addLayoutParam(SequenceDiagramOptions.MESSAGE_TYPE, MessageType.REPLY)
                edge.addPolyline(2).setLineStyle(LineStyle.DASH).addAssociationArrowDecorator()
            }
        }

        return edge
    }
    
    /**
     * Specifies the correct Properties for the different message types. 
     * 
     * @param edge The edge to add the message type properties to.
     * @param type The message type of the message.
     * @return The given edge.
     */
    private def dispatch KEdge setMessageRendering(KEdge edge, MessageTypeTwo type) {
        switch(type) {
            case MessageTypeTwo.SYNC: {
                edge.addLayoutParam(SequenceDiagramOptions.MESSAGE_TYPE, MessageType.SYNCHRONOUS)
                edge.addPolyline(2).addHeadArrowDecorator()
            }
            case MessageTypeTwo.ASYNC: {
                edge.addLayoutParam(SequenceDiagramOptions.MESSAGE_TYPE, MessageType.ASYNCHRONOUS)
                edge.addPolyline(2).addAssociationArrowDecorator()
            }
            case MessageTypeTwo.RESPONSE: {
                edge.addLayoutParam(SequenceDiagramOptions.MESSAGE_TYPE, MessageType.REPLY)
                edge.addPolyline(2).setLineStyle(LineStyle.DASH).addAssociationArrowDecorator()
            } 
            case MessageTypeTwo.CREATE: {
                edge.addLayoutParam(SequenceDiagramOptions.MESSAGE_TYPE, MessageType.CREATE)
                edge.addPolyline(2).setLineStyle(LineStyle.DASH).addAssociationArrowDecorator()
            }
        }

        return edge
    }
    
    /**
     * Specifies the correct Properties for the different message types. 
     * 
     * @param edge The edge to add the message type properties to.
     * @param type The message type of the message.
     * @return The given edge.
     */
    private def dispatch KEdge setMessageRendering(KEdge edge, MessageTypeLostAndFound type) {
        switch(type) {
            case MessageTypeLostAndFound.LOST: {
                edge.addLayoutParam(SequenceDiagramOptions.MESSAGE_TYPE, MessageType.LOST)
            }
            case MessageTypeLostAndFound.FOUND: {
                edge.addLayoutParam(SequenceDiagramOptions.MESSAGE_TYPE, MessageType.FOUND)
            }
        }

        return edge
    }

    /**
     * Creates an execution specification so it can be displayed on a specific lifelines.
     * 
     * @param l The lifeline where the execution specefication should be started.
     * @return The KNode of the execution specification.
     */
    private def KNode createExecution(Lifeline l) {
        val execution = createNode()
        execution.addLayoutParam(SequenceDiagramOptions.NODE_TYPE, NodeType.ACTION_EXEC_SPECIFICATION)
        
        elementId += 1
        
        /* Check if the execution is already active on the given lifeline, 
        otherwise create a new execution. */ 
        if (elementIdOnLifeline.containsKey(l)) {
            val stack = elementIdOnLifeline.get(l)
            stack.push(elementId)
            elementIdOnLifeline.put(l, stack)
        } else {
            val stack = new Stack()
            stack.push(elementId)
            elementIdOnLifeline.put(l, stack)
        }
        execution.addLayoutParam(SequenceDiagramOptions.ELEMENT_ID, elementIdOnLifeline.get(l).peek())
        execution.addLayoutParam(SequenceDiagramOptions.EXECUTION_TYPE, SequenceExecutionType.EXECUTION)
        
        // Set the style options for the execution.
        switch STYLE.objectValue {
            case "Stylish": {
                execution.addRectangle.setBackgroundGradient(Colors.WHITE, Colors.CORNFLOWER_BLUE, 90)
            }
            case "Hello Kitty": {
                execution.addRectangle.setBackgroundGradient("#FFEEEE".color, "#FFBBBB".color, 90)
            }
            default: {
                execution.addRectangle.setBackground(Colors.LIGHT_GRAY)
            }
        }
        lifelineNodes.get(l).children.add(execution)

        return execution
    }

    /**
     * Ends one or more execution specifications.
     * 
     * @param l The lifeline where the specification should be ended.
     * @param endExecCount The number of executions that should be closed.
     */
    private def endExecution(Lifeline l, Integer endExecCount) {
        /* In the language you don't need to enter an endExecCount or you enter a positive integer 
         * or you can use the keyword 'all'
         * close one execution (no endExecCount used) = 0
         * close a number of executions = positive integer
         * close all executions = -1
         */
        if (endExecCount > 0) {
            val stack = elementIdOnLifeline.get(l)
            for (var i = 0; i < endExecCount; i++) {
                if (!stack.isEmpty) {
                    stack.pop()
                }
            }
            elementIdOnLifeline.put(l, stack)
        } else if (endExecCount < 0) {
            val stack = elementIdOnLifeline.get(l)
            stack.clear()
            elementIdOnLifeline.put(l, stack)
        } else {
            val stack = elementIdOnLifeline.get(l)
            if (!stack.isEmpty) {
                stack.pop()
            }
            elementIdOnLifeline.put(l, stack)
        }
        val stack = elementIdOnLifeline.get(l)
        if (stack.isEmpty) {
            elementIdOnLifeline.remove(l)
        }
    }

    /**
     * Creates a note.
     * 
     * @param note The Message of the note that should be displayed.
     * @param id The Id to which message or lifeline the note belongs to.
     * @return The KNode of a note.
     */
    private def KNode createNote(String note, Integer id) {
        val noteNode = createNode()
        noteNode.addLayoutParam(SequenceDiagramOptions.NODE_TYPE, NodeType.COMMENT)
        noteNode.addLayoutParam(SequenceDiagramOptions.ATTACHED_OBJECT_ID, id)

        surroundingInteraction.children.add(noteNode)
        
        // A Rectangle that holds the text of the note.
        val noteRect = noteNode.addRectangle()
        noteRect.addText(note).setSurroundingSpaceGrid(10, 0, 8, 0).fontSize = 8

        return noteNode
    }

    /**
     * Gives an edge an elementId, so that the Algorithm knows the order of the messages.
     * 
     * @param e The edge to add the elementId to.
     */
    private def edgeOrder(KEdge e) {
        elementId += 1
        edgeId = elementId

        // Set a position for a message, so that the algorithm has an increasing order.
        val edgeLayout = e.getData(typeof(KEdgeLayout))
        edgeLayout.sourcePoint.y = edgeId
        edgeLayout.targetPoint.y = edgeId
    }

    /**
     * Adds an arrow decorator suitable for associations to the given polyline.
     * 
     * @param pl The polyline to add the decorator to.
     * @return The given polyline.
     */
    private def KRendering addAssociationArrowDecorator(KPolyline pl) {
        pl.addPolyline(2) => [
            it.points += createKPosition(LEFT, 0, 0, TOP, 0, 0)
            it.points += createKPosition(RIGHT, 0, 0, TOP, 0, 0.5f)
            it.points += createKPosition(LEFT, 0, 0, BOTTOM, 0, 0)
            it.setDecoratorPlacementData(10, 12, -6, 1.0f, true)
        ]
    }
}
