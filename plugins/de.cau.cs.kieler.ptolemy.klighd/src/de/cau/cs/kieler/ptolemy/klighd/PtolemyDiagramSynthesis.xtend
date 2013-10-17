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

import com.google.common.collect.ImmutableSet
import com.google.inject.Inject
import de.cau.cs.kieler.klighd.TransformationOption
import de.cau.cs.kieler.klighd.transformations.AbstractDiagramSynthesis
import de.cau.cs.kieler.ptolemy.klighd.transformation.CommentsExtractor
import de.cau.cs.kieler.ptolemy.klighd.transformation.Ptolemy2KGraphOptimization
import de.cau.cs.kieler.ptolemy.klighd.transformation.Ptolemy2KGraphTransformation
import de.cau.cs.kieler.ptolemy.klighd.transformation.Ptolemy2KGraphVisualization
import org.ptolemy.moml.DocumentRoot

/**
 * Synthesis for turning Ptolemy models into KGraphs.
 * 
 * @author cds
 */
public class PtolemyDiagramSynthesis extends AbstractDiagramSynthesis<DocumentRoot> {
    
    // Our transformation options
    static val TransformationOption SHOW_COMMENTS = TransformationOption::createCheckOption(
        "Comments", true)
    static val TransformationOption SHOW_RELATIONS = TransformationOption::createCheckOption(
        "Relations", false)
    static val TransformationOption FLATTEN = TransformationOption::createCheckOption(
        "Flatten Composite Actors", false);
    // FIXME changed arguments to floats due to KIPRA-1317
    static val TransformationOption COMPOUND_NODE_ALPHA = TransformationOption::createRangeOption(
        "Nested model darkness", 0, 255, 10)
    
    // The parts of our transformation
    @Inject Ptolemy2KGraphTransformation transformation
    @Inject Ptolemy2KGraphOptimization optimization
    @Inject Ptolemy2KGraphVisualization visualization
    @Inject CommentsExtractor commentsExtractor
    
    
    override transform(DocumentRoot model) {
        // Transform, optimize, and visualize
        val kgraph = transformation.transform(model)
        optimization.optimize(kgraph,
            !SHOW_RELATIONS.optionBooleanValue,
            FLATTEN.optionBooleanValue,
            if (SHOW_COMMENTS.optionBooleanValue) commentsExtractor else null
        )
        visualization.visualize(kgraph, (COMPOUND_NODE_ALPHA.optionIntValue))
        
        // If comments should be shown, we want them to be attached properly. Do that now, because we
        // know the node sizes only after the visualization
        if (SHOW_COMMENTS.optionBooleanValue) {
            commentsExtractor.attachComments()
        }
        
        return kgraph
    }
    
    override getTransformationOptions() {
        return ImmutableSet::of(SHOW_COMMENTS, SHOW_RELATIONS, FLATTEN, COMPOUND_NODE_ALPHA)
    }
    
}