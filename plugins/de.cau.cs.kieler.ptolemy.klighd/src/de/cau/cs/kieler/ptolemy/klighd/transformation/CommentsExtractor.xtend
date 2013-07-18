/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.ptolemy.klighd.transformation

import com.google.inject.Inject
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.kiml.util.KimlUtil
import org.eclipse.emf.ecore.xmi.XMLResource

import static de.cau.cs.kieler.ptolemy.klighd.PtolemyProperties.*
import static de.cau.cs.kieler.ptolemy.klighd.transformation.TransformationConstants.*

/**
 * TODO Document
 * 
 * @author cds
 */
class CommentsExtractor {
    
    /** Marking nodes. */
    @Inject extension AnnotationExtensions
    /** Marking nodes. */
    @Inject extension LabelExtensions
    /** Marking nodes. */
    @Inject extension MarkerExtensions
    /** Miscellaneous stuff to make my life easier. */
    @Inject extension MiscellaneousExtensions
    /** Marking nodes. */
    @Inject extension PortExtensions
    
    
    def void extractComments(KNode root, XMLResource ptModelResource) {
        // Iterate through the node's annotations looking for comments
        for (annotation : root.annotations) {
            if ((annotation.class_ ?: "").equals(TYPE_COMMENT)) {
                // Create a new node for the comment
                val commentNode = KimlUtil::createInitializedNode()
                commentNode.layout.setProperty(COMMENT_TEXT, "")
                commentNode.markAsComment()
                root.children += commentNode
                
                // Extract comment text
                for (subAnnotation : annotation.annotations) {
                    if ((subAnnotation.class_ ?: "").equals(TYPE_COMMENT_TEXT)
                        && (subAnnotation.name ?: "").equals(NAME_COMMENT_TEXT)) {
                        
                        commentNode.layout.setProperty(COMMENT_TEXT, subAnnotation.value)
                    }
                }
                
                // Keep annotations of the original comment
                commentNode.annotations += annotation.annotations
            }
        }
        
        // Recurse into child compound nodes
        for (child : root.children) {
            if (!child.children.empty) {
                extractComments(child, ptModelResource)
            }
        }
    }
    
    
    
    
}