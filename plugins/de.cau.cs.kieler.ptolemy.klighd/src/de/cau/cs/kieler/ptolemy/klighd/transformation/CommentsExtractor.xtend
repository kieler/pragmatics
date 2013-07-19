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
import org.ptolemy.moml.PropertyType
import org.eclipse.emf.ecore.util.FeatureMap
import org.eclipse.emf.ecore.xml.type.AnyType

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
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Comment Extraction
    
    /**
     * Extracts the comments attached as annotations to the given node and turns them into proper
     * nodes that are children of the given node.
     * 
     * @param root the root node.
     */
    def void extractComments(KNode root) {
        // Iterate through the node's annotations looking for comments
        for (annotation : root.annotations) {
            if ((annotation.class_ ?: "").equals(TYPE_TEXT_ATTRIBUTE)) {
                // Create comment node and keep annotations of the original comment
                val commentNode = addCommentNode(root,
                    annotation.getAnnotationValue(NAME_COMMENT_TEXT) ?: "")
                commentNode.annotations += annotation.annotations
            } else if ((annotation.class_ ?: "").equals(TYPE_ATTRIBUTE)) {
                // Check if there is an _iconDescription attribute
                val iconDescription = annotation.getAnnotation("_iconDescription")
                
                if (iconDescription != null) {
                    // We may have a shot at retrieving the comment text
                    val text = restoreCommentTextFromIconDescription(iconDescription)
                    
                    if (text != null) {
                        // We were successful; add a comment node
                        val commentNode = addCommentNode(root, text)
                        commentNode.annotations += annotation.annotations
                    }
                }
            }
        }
        
        // Recurse into child compound nodes
        for (child : root.children) {
            if (!child.children.empty) {
                extractComments(child)
            }
        }
    }
    
    /**
     * Tries to restore the text of a comment that was not saved as a regular comment attribute, but
     * as an SVG graphic.
     * 
     * @param iconDescription the {@code _iconDescription} attribute of the attribute that might be a
     *                        comment. This is where we will look for the SVG graphic in.
     * @return the text, if it could be restored, or {@code null} if it couldn't.
     */
    def String restoreCommentTextFromIconDescription(PropertyType iconDescription) {
        // For this stuff to work, we need to get our hands at the XMLResource that loaded the icon
        // description, because that has a map of features that couldn't be parsed
        if (!(iconDescription.eResource instanceof XMLResource)) {
            return null
        }
        val xmlResource = iconDescription.eResource as XMLResource
        
        // Check if the icon description has a <configure> element
        if (iconDescription.configure.empty) {
            return null
        }
        val configureElement = iconDescription.configure.get(0)
        
        // Check if there are unknown features associated with the <configure> element
        val unknownFeature = xmlResource.EObjectToExtensionMap.get(configureElement)
        if (unknownFeature == null) {
            return null
        }
        
        val svgElement = findUnknownFeature(unknownFeature.mixed, "svg")
        if (svgElement == null) {
            return null
        }
        
        val textElement = findUnknownFeature(svgElement.mixed, "text")
        if (textElement == null) {
            return null
        }
        
        // We've found a text element; retrieve its character data
        if (textElement.mixed.empty) {
            return null
        }
        
        val data = textElement.mixed.get(0).value
        if (!(data instanceof String)) {
            return null
        } else {
            return data as String
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Comment Attachment
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Utility Methods
    
    /**
     * Creates a comment node and adds it to the given parent node.
     * 
     * @param parent parent of the new comment node.
     * @param text the comment text to be displayed by the comment node.
     * @return the created comment node.
     */
    def KNode addCommentNode(KNode parent, String text) {
        val commentNode = KimlUtil::createInitializedNode()
        commentNode.layout.setProperty(COMMENT_TEXT, text)
        commentNode.markAsComment()
        parent.children += commentNode
        
        return commentNode
    }
    
    /**
     * Checks if the feature map contains a feature of the given name and returns that.
     * 
     * @param features the feature map to search through.
     * @param name name of the feature to look for.
     * @return the feature or {@code null} if none could be found with that name.
     */
    def AnyType findUnknownFeature(FeatureMap features, String name) {
        for (entry : features) {
            if (entry.EStructuralFeature.name.equals(name)) {
                if (entry.value instanceof AnyType) {
                    return entry.value as AnyType
                }
            }
        }
        
        return null
    }
    
}