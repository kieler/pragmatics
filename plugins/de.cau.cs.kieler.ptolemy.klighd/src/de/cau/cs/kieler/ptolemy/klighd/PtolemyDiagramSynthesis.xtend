/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.ptolemy.klighd

import com.google.common.collect.ImmutableList
import com.google.inject.Inject
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.klay.layered.p4nodes.NodePlacementStrategy
import de.cau.cs.kieler.klay.layered.properties.Properties

import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.cau.cs.kieler.ptolemy.klighd.transformation.CommentsExtractor
import de.cau.cs.kieler.ptolemy.klighd.transformation.Ptolemy2KGraphOptimization
import de.cau.cs.kieler.ptolemy.klighd.transformation.Ptolemy2KGraphTransformation
import de.cau.cs.kieler.ptolemy.klighd.transformation.Ptolemy2KGraphVisualization
import org.ptolemy.moml.DocumentRoot
import de.cau.cs.kieler.klighd.SynthesisOption

/**
 * Synthesis for turning Ptolemy models into KGraphs.
 * 
 * @author cds
 */
public class PtolemyDiagramSynthesis extends AbstractDiagramSynthesis<DocumentRoot> {
    
    // Our transformation options
    public static val SynthesisOption SHOW_COMMENTS = SynthesisOption::createCheckOption(
        "Comments", true)
    public static val SynthesisOption SHOW_RELATIONS = SynthesisOption::createCheckOption(
        "Relations", false)
    public static val SynthesisOption COMMENT_ATTACHMENT_HEURISTIC =
        SynthesisOption::createCheckOption("Comment attachment heuristic", true)
    public static val SynthesisOption FLATTEN = SynthesisOption::createCheckOption(
        "Flatten Composite Actors", false)
    public static val SynthesisOption COMPOUND_NODE_ALPHA = SynthesisOption::createRangeOption(
        "Nested model darkness", 0f, 255f, 30f)
    
    // The parts of our transformation
    @Inject Ptolemy2KGraphTransformation transformation
    @Inject Ptolemy2KGraphOptimization optimization
    @Inject Ptolemy2KGraphVisualization visualization
    @Inject CommentsExtractor commentsExtractor
    
    
    override transform(DocumentRoot model) {
        // Transform, optimize, and visualize
        val kgraph = transformation.transform(model, this)
        optimization.optimize(kgraph,
            !SHOW_RELATIONS.booleanValue,
            FLATTEN.booleanValue,
            if (SHOW_COMMENTS.booleanValue) commentsExtractor else null,
            this
        )
        visualization.visualize(kgraph, (COMPOUND_NODE_ALPHA.intValue))
        
        // If comments should be shown, we want them to be attached properly. Do that now, because we
        // know the node sizes only after the visualization
        if (SHOW_COMMENTS.booleanValue) {
            commentsExtractor.attachComments(COMMENT_ATTACHMENT_HEURISTIC.booleanValue)
        }
        
        return kgraph
    }
    
    /**
     * Diagram options.
     */
    override getDisplayedSynthesisOptions() {
        return ImmutableList::of(
            SHOW_COMMENTS,
            SHOW_RELATIONS,
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
			specifyLayoutOption(Properties::NODE_PLACER, ImmutableList::copyOf(NodePlacementStrategy::values)),
			specifyLayoutOption(LayoutOptions::SPACING,	ImmutableList::of(0, 255))
		)
	}
    
}
