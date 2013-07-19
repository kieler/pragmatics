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
import java.util.List
import de.cau.cs.kieler.core.util.Pair

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
    
    /** If true, the attachment heuristics are disabled once explicitly attached comments are found. */
    val boolean heuristicsOverride = false
    
    /** List of comment nodes created in the process. */
    var List<KNode> createdCommentNodes
    /** List of explicit attachments from comment nodes to other nodes. */
    var List<Pair<KNode, KNode>> explicitAttachments
    /** List of heuristically found attachments from comment nodes to other nodes. */
    var List<Pair<KNode, KNode>> heuristicAttachments
    
    
    /**
     * Finds comments and comment attachments in the tree rooted at the given node.
     */
    def void extractAndAttachComments(KNode root) {
        // Initialize lists
        createdCommentNodes = newLinkedList()
        explicitAttachments = newLinkedList()
        heuristicAttachments = newLinkedList()
        
        extractComments(root)
        attachComments()
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Comment Extraction
    
    /**
     * Extracts the comments attached as annotations to the given node and turns them into proper
     * nodes that are children of the given node.
     * 
     * @param root the root node.
     */
    def private void extractComments(KNode root) {
        // Iterate through the node's annotations looking for comments
        for (annotation : root.annotations) {
            if ((annotation.class_ ?: "").equals(TYPE_TEXT_ATTRIBUTE)) {
                // Create comment node and keep annotations of the original comment
                val commentNode = addCommentNode(root,
                    annotation.getAnnotationValue(ANNOTATION_COMMENT_TEXT) ?: "")
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
    def private String restoreCommentTextFromIconDescription(PropertyType iconDescription) {
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
    
    /**
     * Iterates through the generated comment nodes and tries to attach them to the elements they were
     * initially attached to. The attachment can have been either explicit (by the model designer) or
     * implicit, by a distance-based heuristic.
     */
    def private void attachComments() {
        // Iterate over the created comment nodes and try attaching them
        for (commentNode : createdCommentNodes) {
            // Check if the comment was explicitly attached to a node
            val explicitAttachment = findExplicitAttachment(commentNode)
            
            if (explicitAttachment != null) {
                explicitAttachments += new Pair(commentNode, explicitAttachment)
            } else if (explicitAttachments.empty || !heuristicsOverride) {
                // TODO Run our heuristic to find an implicit attachment
            }
        }
        
        // Apply the explicit attachments without question
        for (attachment : explicitAttachments) {
            attachCommentNode(attachment.first, attachment.second)
        }
        
        // Attach the heuristic attachments only if there are no explicit attachments or if the
        // heuristics override is turned off
        if (!heuristicsOverride || explicitAttachments.empty) {
            for (attachment : heuristicAttachments) {
                attachCommentNode(attachment.first, attachment.second)
            }
        }
    }
    
    /**
     * Tries to find the node the given node is explicitly attached to, if any.
     * 
     * @param commentNode the node whose explicit attachment to find.
     * @return the node the comment was explicitly attached to, or {@code null} if none could be found.
     */
    def private KNode findExplicitAttachment(KNode commentNode) {
        // Retrieve some annotations and check if we have the required information
        val location = commentNode.getAnnotation(ANNOTATION_LOCATION)
        val relativeTo = location?.getAnnotation(ANNOTATION_RELATIVE_TO)
        val relativeToElementName = location?.getAnnotation(ANNOTATION_RELATIVE_TO_ELEMENT_NAME)
        
        if (relativeTo != null
            && relativeToElementName != null
            && relativeToElementName.value.equals("entity")) {
            
            // Look for siblings of the comment node that have the correct name
            for (sibling : commentNode.parent.children) {
                if (sibling.name.equals(relativeTo.value)) {
                    return sibling
                }
            }
        }
        
        return null
    }
    
    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Utility Methods
    
    /**
     * Creates a comment node and adds it to the given parent node. Also adds it to the list of
     * created comment nodes.
     * 
     * @param parent parent of the new comment node.
     * @param text the comment text to be displayed by the comment node.
     * @return the created comment node.
     */
    def private KNode addCommentNode(KNode parent, String text) {
        val commentNode = KimlUtil::createInitializedNode()
        commentNode.layout.setProperty(COMMENT_TEXT, text)
        commentNode.markAsComment()
        
        parent.children += commentNode
        createdCommentNodes += commentNode
        
        return commentNode
    }
    
    /**
     * Attaches the given comment node to the given other node.
     * 
     * @param commentNode comment node to be attached.
     * @param attachedNode other node to attach the comment node to.
     */
    def private void attachCommentNode(KNode commentNode, KNode attachedNode) {
        val edge = KimlUtil::createInitializedEdge()
        edge.source = commentNode
        edge.target = attachedNode
    }
    
    /**
     * Checks if the feature map contains a feature of the given name and returns that.
     * 
     * @param features the feature map to search through.
     * @param name name of the feature to look for.
     * @return the feature or {@code null} if none could be found with that name.
     */
    def private AnyType findUnknownFeature(FeatureMap features, String name) {
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