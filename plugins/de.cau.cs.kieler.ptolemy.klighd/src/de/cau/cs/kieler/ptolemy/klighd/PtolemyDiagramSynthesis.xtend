/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.ptolemy.klighd

import com.google.common.collect.ImmutableList
import com.google.inject.Inject
import de.cau.cs.kieler.kiml.labels.LabelManagementOptions
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.klay.layered.p4nodes.NodePlacementStrategy
import de.cau.cs.kieler.klay.layered.properties.Properties
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.labels.ConditionLabelManager
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses
import de.cau.cs.kieler.ptolemy.klighd.transformation.CommentsExtractor
import de.cau.cs.kieler.ptolemy.klighd.transformation.Ptolemy2KGraphOptimization
import de.cau.cs.kieler.ptolemy.klighd.transformation.Ptolemy2KGraphTransformation
import de.cau.cs.kieler.ptolemy.klighd.transformation.Ptolemy2KGraphVisualization
import org.ptolemy.moml.DocumentRoot

import static de.cau.cs.kieler.ptolemy.klighd.PtolemyDiagramSynthesis.*
import com.google.common.base.Predicates
import de.cau.cs.kieler.klighd.labels.EmfContainerCondition
import de.cau.cs.kieler.core.kgraph.KPort

/**
 * Synthesis for turning Ptolemy models into KGraphs.
 * 
 * @author cds
 */
public class PtolemyDiagramSynthesis extends AbstractDiagramSynthesis<DocumentRoot> {
    
    public static val ID = "de.cau.cs.kieler.ptolemy.klighd.PtolemyDiagramSynthesis"
    
    // Our transformation options
    public static val SynthesisOption SHOW_COMMENTS = SynthesisOption::createCheckOption(
        "Comments", true)
    public static val SynthesisOption SHOW_RELATIONS = SynthesisOption::createCheckOption(
        "Relations", false)
    public static val SynthesisOption SHOW_PORT_LABELS = SynthesisOption::createChoiceOption(
        "Show Port Labels", ImmutableList::of(
            PortLabelDisplayStyle.ALL.toString(),
            PortLabelDisplayStyle.SELECTED_NODE.toString(),
            PortLabelDisplayStyle.NONE.toString()),
        PortLabelDisplayStyle.NONE.toString())
    public static val SynthesisOption SHOW_PROPERTIES = SynthesisOption::createCheckOption(
        "Parameters", true)
    public static val SynthesisOption SHOW_DIRECTORS = SynthesisOption::createCheckOption(
        "Directors", true)
    public static val SynthesisOption COMMENT_ATTACHMENT_HEURISTIC =
        SynthesisOption::createCheckOption("Comment attachment heuristic", true) 
    public static val SynthesisOption FLATTEN = SynthesisOption::createCheckOption(
        "Flatten Composite Actors", false)
    public static val SynthesisOption COMPOUND_NODE_ALPHA = SynthesisOption::createRangeOption(
        "Nested model darkness", 0f, 255f, 30f)
    
    /**
     * Container class for synthesis options. At the beginning of the 
     * diagram synthesis's #transform() method, the #capture() method should be 
     * called in order to cache the current values of the synthesis options.
     * This class can then be used to retrieve the option values.
     */
    public static class Options {
        public var boolean comments
        public var boolean relations
        public var PortLabelDisplayStyle portLabels
        public var boolean properties
        public var boolean directors
        public var boolean attachComments
        public var boolean flatten
        public var int compoundNodeAlpha
        
        def capture(PtolemyDiagramSynthesis s) {
            comments = s.getBooleanValue(SHOW_COMMENTS)
            relations = s.getBooleanValue(SHOW_RELATIONS)
            portLabels = PortLabelDisplayStyle.fromDisplayString(
                s.getObjectValue(SHOW_PORT_LABELS).toString())
            properties = s.getBooleanValue(SHOW_PROPERTIES)
            directors = s.getBooleanValue(SHOW_DIRECTORS)
            attachComments = s.getBooleanValue(COMMENT_ATTACHMENT_HEURISTIC)
            flatten = s.getBooleanValue(FLATTEN)
            compoundNodeAlpha = s.getIntValue(COMPOUND_NODE_ALPHA)
        }
    }
    
    private val options = new Options
    
    // The parts of our transformation
    @Inject Ptolemy2KGraphTransformation transformation
    @Inject Ptolemy2KGraphOptimization optimization
    @Inject Ptolemy2KGraphVisualization visualization
    @Inject CommentsExtractor commentsExtractor
    
   
    override transform(DocumentRoot model) {
        // Capture options
        options.capture(this)
        
        // Transform, optimize, and visualize
        val kgraph = transformation.transform(model, this)
        optimization.optimize(kgraph,
            options,
            if (options.comments) commentsExtractor else null,
            this
        )
        visualization.visualize(kgraph, options)
        
        // If comments should be shown, we want them to be attached properly. Do that now, because we
        // know the node sizes only after the visualization
        if (options.comments) {
            commentsExtractor.attachComments(options.attachComments)
        }
        if(SHOW_PORT_LABELS.objectValue.equals("Selected Node")){
            val labelManager = new ConditionLabelManager(
                null, Predicates.not(new EmfContainerCondition(typeof(KPort))), true)
            kgraph.setLayoutOption(LabelManagementOptions.LABEL_MANAGER, labelManager)
        }
        
        return kgraph
    }
    
    /**
     * Diagram options.
     */
    override getDisplayedSynthesisOptions() {
        return ImmutableList.of(
            SHOW_RELATIONS,
            SHOW_PORT_LABELS,
            SHOW_DIRECTORS,
            SHOW_PROPERTIES,
            SynthesisOption.createSeparator("Comments"),
            SHOW_COMMENTS,
            COMMENT_ATTACHMENT_HEURISTIC,
            SynthesisOption.createSeparator("Hierarchy"),
            FLATTEN, 
            COMPOUND_NODE_ALPHA)
    }
    
    /**
     * Layout options.
     */
    override getDisplayedLayoutOptions() {
        return ImmutableList::of(
            DiagramSyntheses.specifyLayoutOption(Properties::NODE_PLACER,
                ImmutableList::copyOf(NodePlacementStrategy::values)),
            DiagramSyntheses.specifyLayoutOption(LayoutOptions::SPACING,
                ImmutableList::of(0, 255))
        )
    }
    
}