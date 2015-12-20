/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.ptolemy.klighd.transformation.comments

import com.google.inject.Inject
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.kiml.comments.IEligibilityFilter
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.ptolemy.klighd.transformation.extensions.AnnotationExtensions
import de.cau.cs.kieler.ptolemy.klighd.transformation.extensions.MarkerExtensions

import static de.cau.cs.kieler.ptolemy.klighd.PtolemyProperties.*

/**
 * Passes judgement on a comment's eligibility for attachment based on whether it is considered a title
 * comment or not. There are two ways to be considered a title comment. First, the comment is a type
 * of title comment in Ptolemy. And second, it is the single comment with the largest font size in the
 * whole graph. We only expect the latter to appear on the graph's top level.
 */
final class PtolemyTitleCommentFilter implements IEligibilityFilter {
    
    @Inject extension AnnotationExtensions
    @Inject extension MarkerExtensions
    
    /** The comment with the largest font size in the current graph. */
    var KNode largestFontSizeComment = null;
    /** Whether to decide only based on the font size, even if a comment is marked as title. */
    var decideBasedOnFontSizeOnly = false;
    
    
    /**
     * Once this method is called, the filter will disregard whether a comment is marked as title and
     * only regard comments as title based on their font size. This should really only be necessary
     * for evaluations.
     */
    def void decideBasedOnFontSizeOnly() {
        decideBasedOnFontSizeOnly = true;
    }
    
    /**
     * Returns the comment determined by the filter to be the title comment. Can only be non-null
     * between calls to {@code preprocess(...)} and {@code cleanup()}.
     */
    def KNode getChosenComment() {
        return largestFontSizeComment;
    }
    
    
    /**
     * {@inheritDoc}
     */
    override void preprocess(KNode graph, boolean includeHierarchy) {
        var int largestFontSize = 0;
        
        // Iterate over all the comments
        for (node : graph.children) {
            // Make sure the node is a comment
            if (node.layout.getProperty(LayoutOptions.COMMENT_BOX)) {
                val fontSize = node.layout.getProperty(COMMENT_FONT_SIZE);
                
                if (fontSize > largestFontSize) {
                    // We have a new biggest font size!
                    largestFontSize = fontSize;
                    largestFontSizeComment = node;
                    
                } else if (fontSize == largestFontSize) {
                    // We don't have a single comment with the biggest font size anymore
                    largestFontSizeComment = null;
                }
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    override eligibleForAttachment(KNode comment) {
        return comment != largestFontSizeComment
            && (decideBasedOnFontSizeOnly || !comment.markedAsTitleNode);
    }
    
    /**
     * {@inheritDoc}
     */
    override void cleanup() {
        largestFontSizeComment = null;
    }
    
}