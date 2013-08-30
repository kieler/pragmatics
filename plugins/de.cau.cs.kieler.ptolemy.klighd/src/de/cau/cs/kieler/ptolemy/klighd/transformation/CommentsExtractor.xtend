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
import de.cau.cs.kieler.core.util.Pair
import de.cau.cs.kieler.kiml.util.KimlUtil
import de.cau.cs.kieler.ptolemy.klighd.transformation.extensions.AnnotationExtensions
import de.cau.cs.kieler.ptolemy.klighd.transformation.extensions.LabelExtensions
import de.cau.cs.kieler.ptolemy.klighd.transformation.extensions.MarkerExtensions
import de.cau.cs.kieler.ptolemy.klighd.transformation.extensions.MiscellaneousExtensions
import java.awt.geom.Rectangle2D
import java.util.List
import org.eclipse.emf.ecore.util.FeatureMap
import org.eclipse.emf.ecore.xmi.XMLResource
import org.eclipse.emf.ecore.xml.type.AnyType
import org.ptolemy.moml.PropertyType

import static de.cau.cs.kieler.ptolemy.klighd.PtolemyProperties.*
import static de.cau.cs.kieler.ptolemy.klighd.transformation.util.TransformationConstants.*
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout

/**
 * Extracts comments from the model and turns them into special comment nodes. Also tries to find the
 * entities the comments probably refer to and attaches them to these entities.
 * 
 * <p>In Ptolemy, certain annotations in a model are like comments in source code.
 * There are two ways how they can be represented in MOML:</p>
 * 
 * <ol>
 *   <li>Using a property of type "ptolemy.vergil.kernel.attributes.TextAttribute"
 *       with another property named "text", as follows:
 *     <pre>
 *        &lt;property
 *              name="Annotation2"
 *              class="ptolemy.vergil.kernel.attributes.TextAttribute"&gt;
 *              
 *              &lt;property
 *                      name="text"
 *                      class="ptolemy.kernel.util.StringAttribute"
 *                      value="This is my annotation's text."&gt;
 *              &lt;/property&gt;
 *              &lt;property
 *                      name="_location"
 *                      class="ptolemy.kernel.util.Location"
 *                      value="[140.0, 440.0]"&gt;
 *              &lt;/property&gt;
 *        &lt;/property&gt;
 *     </pre>
 *   </li>
 *   
 *   <li>Using a property of type "ptolemy.kernel.util.Attribute" with an SVG
 *       as its "_iconDescription" property, as follows:
 *     <pre>
 *        &lt;property
 *              name="annotation3"
 *              class="ptolemy.kernel.util.Attribute"&gt;
 *              
 *              &lt;property
 *                      name="_iconDescription"
 *                      class="ptolemy.kernel.util.SingletonConfigurableAttribute"&gt;
 *                      
 *                      &lt;configure&gt;&lt;svg&gt;
 *                              &lt;text x="20" y="20" style="..."&gt;
 *                                      This is my annotation's text.
 *                              &lt;/text&gt;
 *                      &lt;/svg&gt;&lt;/configure&gt;
 *              &lt;/property&gt;
 *              &lt;property
 *                      name="_location"
 *                      class="ptolemy.kernel.util.Location"
 *                      value="[325.0, 10.0]"&gt;
 *              &lt;/property&gt;
 *        &lt;/property&gt;
 *     </pre>
 *   </li>
 * </ol>
 *      
 * <p>while the first version is straightforward, the latter version causes a whole
 * lot of problems. The {@code configure} element is a mixed
 * element, which means that it can contain anything, not just XML. However, it
 * does contain XML (usually an {@code svg} element and its children), which
 * disturbs the quiet peace of the parser. (which, in turn, disturbs my quiet peace.)
 * The {@code configure} element and its children are then dropped by the parser
 * during the transformation and are added to a list of unknown features. Recovering
 * such comments is one of the two tasks of this handler.</p>
 * 
 * <p>The second task is recognizing links between comments and model elements. Such
 * links indicate that a comment is providing additional information for a model
 * element, and should be preserved. In the MoML file, such a link is represented by
 * the following property:</p>
 * 
 * <pre>
 *   &lt;property
 *         name="_location"
 *         class="ptolemy.kernel.util.RelativeLocation"
 *         value="[-195.0, -80.0]"&gt;
 *         
 *         &lt;property
 *               name="relativeTo"
 *               class="ptolemy.kernel.util.StringAttribute"
 *               value="Const"/&gt;
 *         &lt;property
 *               name="relativeToElementName"
 *               class="ptolemy.kernel.util.StringAttribute"
 *               value="entity"/&gt;
 *   &lt;/property&gt;
 * </pre>
 * 
 * <p>That is, the annotation's {@code _location} property is extended by two additional
 * properties that define that the location is to be interpreted relative to a given
 * model element. So far, we only support linking comments to entities, but that could
 * well be changed.</p>
 * 
 * <p>We start by extracting comments and turning them into nodes. After that's done,
 * the model is traversed in an attempt to link comments to the model elements they are
 * most likely referring to. There are two cases here:</p>
 * 
 * <ol>
 *   <li>The model contains explicit links between at least one comment and model
 *   element. In this case, we assume that the model developer added all the links he
 *   wants explicitly and only transform such links into the our way of representing
 *   them.</li>
 *   
 *   <li>The model does not contain any such explicit link. In this case, a heuristic
 *   is applied to each comment, trying to find the entity it is probably referring to.
 *   The heuristic simply looks for the nearest entity and checks if the distance is
 *   within a certain threshold value.
 * </ol>
 * 
 * <p>This is still kind of experimental. It does work, but the heuristic is quite
 * simpllistic and doesn't always give correct results.</p>
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
    /**
     * The maximum distance between a comment node and a regular node for them to be considered to be
     * attached by the comment attachment heuristic.
     */
    val double maxAttachmentDistance = 10000.0
    
    /** List of comment nodes created in the process. */
    var List<KNode> createdCommentNodes = newLinkedList()
    /** List of explicit attachments from comment nodes to other nodes. */
    var List<Pair<KNode, KNode>> explicitAttachments = newLinkedList()
    /** List of heuristically found attachments from comment nodes to other nodes. */
    var List<Pair<KNode, KNode>> heuristicAttachments = newLinkedList()
    
    
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
    def void extractComments(KNode root) {
        // Iterate through the node's annotations looking for comments
        for (annotation : root.annotations) {
            if ((annotation.class_ ?: "").equals(ANNOTATION_TYPE_TEXT_ATTRIBUTE)) {
                // Create comment node and keep annotations of the original comment
                val commentNode = addCommentNode(root,
                    annotation.getAnnotationValue(ANNOTATION_COMMENT_TEXT) ?: "")
                commentNode.annotations += annotation.annotations
            } else if ((annotation.class_ ?: "").equals(ANNOTATION_TYPE_ATTRIBUTE)) {
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
    def void attachComments() {
        // Iterate over the created comment nodes and try attaching them
        for (commentNode : createdCommentNodes) {
            // Check if the comment was explicitly attached to a node
            val explicitAttachment = findExplicitAttachment(commentNode)
            
            if (explicitAttachment != null) {
                explicitAttachments += new Pair(commentNode, explicitAttachment)
            } else if (explicitAttachments.empty || !heuristicsOverride) {
                // Run our heuristic to find an implicit attachment
                val heuristicAttachment = findNearestNonCommentSibling(commentNode)
                
                if (heuristicAttachment != null) {
                    heuristicAttachments += new Pair(commentNode, heuristicAttachment)
                }
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
            && relativeToElementName.value != null
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
    
    /**
     * Finds the node whose position is nearest to the given comment node's position that is not a
     * comment node and not further away than the maximum attachment distance.
     * 
     * @param commentNode the node whose nearest sibling to find.
     * @return the nearest sibling or {@code null} if none could be found within the maximum distance.
     */
    def private KNode findNearestNonCommentSibling(KNode commentNode) {
        // Find the comment node's position in the original diagram
        val commentBounds = getPtolemyBounds(commentNode)
        
        // TODO: Update the comment size
        commentBounds.width = 10
        commentBounds.height = 10
        
        var currentNearestDistance = maxAttachmentDistance + 1
        var KNode currentNearestSibling = null
        
        for (sibling : commentNode.parent.children) {
            if (!sibling.markedAsComment) {
                val distance = computeDistance(commentBounds, getPtolemyBounds(sibling))
                
                if (distance < currentNearestDistance && distance <= maxAttachmentDistance) {
                    currentNearestDistance = distance
                    currentNearestSibling = sibling
                }
            }
        }
        
        return currentNearestSibling
    }
    
    /**
     * Compute a measure of distance between the two shapes defined by the given bounds. If at least
     * one of the bounds has a width or height of zero, a value bigger than the maximum attachment
     * distance is returned. If the two shapes intersect, a distance of zero is returned.
     * 
     * <p>The current implementation simply returns the square of the euclidean distance, which is
     * perfectly fine.</p>
     * 
     * @param bounds1 the first shape.
     * @param bounds2 the second shape.
     * @return a measure of distance between the two shapes.
     */
    def private double computeDistance(Rectangle2D$Double bounds1, Rectangle2D$Double bounds2) {
        // Check if one of the shapes has zero height or width
        if (bounds1.width == 0 || bounds1.height == 0 || bounds2.width == 0 || bounds2.height == 0) {
            return maxAttachmentDistance + 1
        }
        
        // Check if the two shapes intersect
        if (bounds1.intersects(bounds2)) {
            return 0
        }
        
        // TODO: Compute a proper distance
        
        val deltaX = bounds2.x - bounds1.x
        val deltaY = bounds2.y - bounds1.y
        
        return deltaX * deltaX + deltaY * deltaY
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
    
    /**
     * Retrieves the position of the given node in the original Ptolemy diagram, if any, as well as
     * its size as given by the node's shape layout. The shape layout's size fields are expected to
     * have been correctly set during the visualization step of the diagram synthesis. This method
     * is a create method because the bounds don't change and we like Xtend to cache already
     * calculated positions for us.
     * 
     * @param node the node whose location to retrieve.
     * @return the bounds or some bounds way out if there was a problem.
     */
    def private create bounds : new Rectangle2D$Double() getPtolemyBounds(KNode node) {
        // Initialize point with ridiculous values
        bounds.x = 2e20
        bounds.y = 2e20
        
        // Get location annotation
        val locationAnnotation = node.getAnnotation(ANNOTATION_LOCATION)
        if (locationAnnotation == null) {
            return
        }
        
        // Try parsing the location information by splitting the string into an array of components.
        // Locations have one of the following three representations:
        //   "[140.0, 20.0]"     "{140.0, 20.0}"     "140.0, 20.0"
        val locationString = locationAnnotation.value.replaceAll("[\\s\\[\\]{}]+", "")
        val locationArray = locationString.split(",")
        
        if (locationArray.size == 2) {
            try {
                bounds.x = Double::valueOf(locationArray.get(0))
                bounds.y = Double::valueOf(locationArray.get(1))
                
                // Save the node's size in the bounds as well
                bounds.width = (node.layout as KShapeLayout).width
                bounds.height = (node.layout as KShapeLayout).height
            } catch (NumberFormatException e) {
                
            }
        }
    }
    
}