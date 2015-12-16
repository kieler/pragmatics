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
import de.cau.cs.kieler.ptolemy.klighd.transformation.extensions.AnnotationExtensions

import static de.cau.cs.kieler.ptolemy.klighd.PtolemyProperties.*

/**
 * Passes judgement on a comment's eligibility for attachment based on whether it is considered a title
 * comment or not. There are two ways to be considered a title comment. First, the comment is a type
 * of title comment in Ptolemy. And second, it is the single comment with the largest font size in the
 * whole graph. We only expect the latter to appear on the graph's top level.
 */
final class PtolemyAuthorCommentFilter implements IEligibilityFilter {
    
    @Inject extension AnnotationExtensions
    
    /** The text author comments begin with. All lower-case. */
    private val AUTHOR_COMMENT_PREFIX = "author";
    
    
    /**
     * {@inheritDoc}
     */
    override eligibleForAttachment(KNode comment) {
        val commentContents = comment.layout.getProperty(COMMENT_TEXT).trim
        
        if (commentContents.length < AUTHOR_COMMENT_PREFIX.length) {
            return true;
        } else {
            return !commentContents
                .substring(0, AUTHOR_COMMENT_PREFIX.length)
                .toLowerCase
                .equals(AUTHOR_COMMENT_PREFIX);
        }
    }
    
}