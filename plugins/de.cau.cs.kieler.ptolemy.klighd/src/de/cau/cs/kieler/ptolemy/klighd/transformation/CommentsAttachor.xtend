 /*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.ptolemy.klighd.transformation

import com.google.common.collect.Multimap
import com.google.inject.Inject
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.util.Pair
import de.cau.cs.kieler.kiml.util.KimlUtil
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil
import de.cau.cs.kieler.klighd.piccolo.export.SVGGeneratorManager
import de.cau.cs.kieler.ptolemy.klighd.transformation.extensions.AnnotationExtensions
import de.cau.cs.kieler.ptolemy.klighd.transformation.extensions.LabelExtensions
import de.cau.cs.kieler.ptolemy.klighd.transformation.extensions.MarkerExtensions
import java.awt.geom.Rectangle2D
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.List
import java.util.regex.Pattern

import static de.cau.cs.kieler.ptolemy.klighd.PtolemyProperties.*
import static de.cau.cs.kieler.ptolemy.klighd.transformation.util.TransformationConstants.*

/**
 * Tries to infer the attachments between comment nodes and the elements they are supposed to describe.
 * There are two cases here:</p>
 *
 * <ol>
 *   <li>The model contains explicit links between at least one comment and model element. In this case,
 *   we assume that the model developer added all the links he wants explicitly and only transform such
 *   links into the our way of representing them. We recognize explicit links through the fact that the
 *   {@link CommentsExtractor} has already turned them into edges.</li>
 *
 *   <li>The model does not contain any such explicit link. In this case, a heuristic is applied to each
 *   comment, trying to find the entity it is probably referring to.
 * </ol>
 *
 * <p>The heuristic can be applied in the first case as well. The explicit links will not be touched,
 * then; only unlinked comments will be considered for attachment.</p>
 *
 * @see CommentsExtractor
 * @author cds
 */
class CommentsAttachor {

    /** Marking nodes. */
    @Inject extension AnnotationExtensions
    /** Marking nodes. */
    @Inject extension LabelExtensions
    /** Marking nodes. */
    @Inject extension MarkerExtensions
    /** Utility class for attaching the correct rendering to comment edges. */
    @Inject extension KRenderingFigureProvider

    /** If true, the attachment heuristics are disabled once explicitly attached comments are found. */
    val boolean disableHeuristicOnExplicitAttachments = false;
    /** List of explicit attachments from comment nodes to other nodes. */
    val List<Pair<KNode, KNode>> explicitAttachments = newLinkedList();
    /** List of heuristically found attachments from comment nodes to other nodes. */
    val List<Pair<KNode, KNode>> heuristicAttachments = newLinkedList();

    /** Maximum distance for two objects to be considered close enough for attachment. */
    val double maxAttachmentDistance = 500.0;
    /** Maximum distance for two objects to be considered to be aligned. */
    val double maxAlignmentDistance = 2001;
    /** The maximum area of a comment to still be considered attacheable. */
    val double maxCommentArea = 20000;


    /**
     * Iterates through the generated comment nodes and tries to attach them to the elements they were
     * initially attached to. The attachment can have been either explicit (by the model designer) or
     * implicit, by a combined heuristic, if it is enabled through the given parameter.
     *
     * @param enableHeuristic {@code true} if the combined attachment heuristic should be
     *                        enabled.
     */
    def void attachComments(boolean enableHeuristic, Multimap<KNode, KNode> createdCommentNodes) {
        // Find the title comment
        val titleComment = findTitleComment(createdCommentNodes);

        // Iterate over the created comment nodes and try to attachthem
        for (commentNode : createdCommentNodes.values) {
            // Check if the comment was explicitly attached to a node
            val explicitAttachment = findExplicitAttachment(commentNode)

            if (explicitAttachment != null) {
                // CARE xtend's "+=" allows to have iterables on both sides, due to the
                // fact that Pair implements Iterable and no generics are specified during
                // the creation of the pair, xtend thinks of the pair as an iterable and
                // adds both elements of the to the list. Not, as intended, the pair itself
                // heuristicAttachments += new Pair(commentNode, heuristicAttachment)
                explicitAttachments.add(new Pair(commentNode, explicitAttachment))

            } else if (enableHeuristic
                && (explicitAttachments.empty || !disableHeuristicOnExplicitAttachments)) {
                
                // Check if this comment is eligible for attachment in the first place
                val eligibleForAttachment = !isAuthorComment(commentNode)
                    && titleComment != commentNode
                    && !commentNode.markedAsTitleNode
                    && isSmallEnoughToBeAttached(commentNode);
                    
                if (eligibleForAttachment) {
                    // Look for mentioned siblings
//                        if (heuristicAttachment == null) {
//                            heuristicAttachment = findMentionedSibling(commentNode);
//                        }
                    
                    val heuristicAttachment = findNearestSibling(commentNode)
                    if (heuristicAttachment != null) {
                        // CARE see above
                        heuristicAttachments.add(new Pair(commentNode, heuristicAttachment))
                    }
                }
            }
        }

        // Apply the explicit attachments without question
        for (attachment : explicitAttachments) {
            attachCommentNode(attachment.first, attachment.second)
        }

        // Attach the heuristic attachments only if there are no explicit attachments or if the
        // heuristics override is turned off
        if (enableHeuristic && (!disableHeuristicOnExplicitAttachments || explicitAttachments.empty)) {
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
    private def KNode findExplicitAttachment(KNode commentNode) {
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
     * Attaches the given comment node to the given other node.
     *
     * @param commentNode comment node to be attached.
     * @param attachedNode other node to attach the comment node to.
     */
    private def void attachCommentNode(KNode commentNode, KNode attachedNode) {
        val edge = KimlUtil::createInitializedEdge()
        val edgeRendering = createCommentEdgeRendering(edge)
        edge.data += edgeRendering

        edge.source = commentNode
        edge.target = attachedNode
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // ATTACHMENT HEURISTICS
    
    ////////////////////////////////////////////////////
    // Find Author Comment
    
    /** The text author comments begin with. All lower-case. */
    private val AUTHOR_COMMENT_PREFIX = "author";

    /**
     * Checks whether a comment is the author-comment by looking for the word "author".
     *
     * @param commentNode a comment that was transformed into a KNode.
     * @return {@code true} if the comment contains the word "Author" or "author".
     */
    private def boolean isAuthorComment(KNode commentNode) {
        val commentContents = commentNode.layout.getProperty(COMMENT_TEXT).trim
        
        if (commentContents.length < AUTHOR_COMMENT_PREFIX.length) {
            return false;
        } else {
            return commentContents
                .substring(0, AUTHOR_COMMENT_PREFIX.length)
                .toLowerCase
                .equals(AUTHOR_COMMENT_PREFIX);
        }
    }


    ////////////////////////////////////////////////////
    // Title Comment

    /**
     * Finds the comment with the largest font size of them all. The comment is only returned if it is
     * unique, that is, if there is no second comment that has the same font size. The idea here is that
     * the single comment with the largest font size is probably the diagram's title.
     *
     * @param createdCommentNodes comment nodes that were created by the comments extractor.
     * @return the title comment or {@code null} if we were unable to find a unique one.
     */
    private def KNode findTitleComment(Multimap<KNode, KNode> createdCommentNodes) {
        var int biggestFontSize = 0;
        var KNode biggestFontSizeComment = null;
        
        // Iterate over all the comments
        for (commentNode : createdCommentNodes.values) {
            val fontSize = commentNode.layout.getProperty(COMMENT_FONT_SIZE);
            
            if (fontSize > biggestFontSize) {
                // We have a new biggest font size!
                biggestFontSize = fontSize;
                biggestFontSizeComment = commentNode;
                
            } else if (fontSize == biggestFontSize) {
                // We don't have a single comment with the biggest font size anymore
                biggestFontSizeComment = null;
            }
        }
        
        return biggestFontSizeComment;
    }


    ////////////////////////////////////////////////////
    // Distance
    
    /**
     * Finds the node nearest to the given comment node if the distance doesn't exceed the maximum
     * attachment distance.
     * 
     * @param commentNode the comment node whose closest sibling to find.
     * @return the node closest to the comment, or {@code null} if there is none within the maximum
     *         attachment distance.
     */
    private def KNode findNearestSibling(KNode commentNode) {
        val commentBounds = getPtolemyBounds(commentNode);
        
        // Iterate over all non-comment siblings and find the closest one
        var double currentClosestDistance = maxAttachmentDistance;
        var KNode currentClosestSibling = null;
        
        for (sibling : commentNode.parent.children.filter(child | !child.markedAsComment)) {
            val distance = computeSquaredDistance(commentBounds, getPtolemyBounds(sibling));
            
            if (distance <= currentClosestDistance) {
                currentClosestDistance = distance;
                currentClosestSibling = sibling;
            }
        }
        
        return currentClosestSibling;
    }


    ////////////////////////////////////////////////////
    // Alignment
    
    /**
     * Finds the best aligned sibling within the comment attachment distance and within the maximum
     * allowed alignment.
     * 
     * @param commentNode the comment whose best aligned sibling to find.
     * @return the best aligned sibling, or {@code null} if no such sibling could be found within the
     *         allowed bounds.
     */
    private def KNode findBestAlignedSibling(KNode commentNode) {
        val commentBounds = getPtolemyBounds(commentNode);
        
        // Iterate over all non-comment siblings and find the closest one
        var double currentBestAlignment = maxAlignmentDistance;
        var KNode currentBestSibling = null;
        
        for (sibling : commentNode.parent.children.filter(child | !child.markedAsComment)) {
            val alignment = computeAlignment(commentBounds, getPtolemyBounds(sibling));
            val distance = computeSquaredDistance(commentBounds, getPtolemyBounds(sibling));
            
            if (alignment <= currentBestAlignment && distance <= maxAttachmentDistance) {
                currentBestAlignment = alignment;
                currentBestSibling = sibling;
            }
        }
        
        return currentBestSibling;
    }

    /**
     * Compute the alignment between two shapes defined by the given bounds. The alignment is calculated
     * by the smallest distance in the x- or y-direction capped by the maximum attachment distance.
     *
     * @param bounds1 the first shape.
     * @param bounds2 the second shape.
     * @return the alignment between the two shapes as a number between zero and one.
     */
    private def double computeAlignment(Rectangle2D.Double bounds1, Rectangle2D.Double bounds2) {
        // Calculate where the top left and bottom right points of bounds1 lie with regard to bounds2
        val topLeftOutcode = bounds2.outcode(bounds1.x, bounds1.y);
        val bottomRightOutcode = bounds2.outcode(bounds1.x + bounds1.width, bounds1.y + bounds1.height);

        // Do not compute the alignment if the comment is cater-cornered to the node
        if (bottomRightOutcode.bitwiseAnd(OUT_TOP_LEFT) == OUT_TOP_LEFT
            && topLeftOutcode.bitwiseAnd(OUT_TOP_LEFT) == OUT_TOP_LEFT
            || bottomRightOutcode.bitwiseAnd(OUT_TOP_RIGHT) == OUT_TOP_RIGHT
            && topLeftOutcode.bitwiseAnd(OUT_TOP_RIGHT) == OUT_TOP_RIGHT
            || bottomRightOutcode.bitwiseAnd(OUT_BOTTOM_LEFT) == OUT_BOTTOM_LEFT
            && topLeftOutcode.bitwiseAnd(OUT_BOTTOM_LEFT) == OUT_BOTTOM_LEFT
            || bottomRightOutcode.bitwiseAnd(OUT_BOTTOM_RIGHT) == OUT_BOTTOM_RIGHT
            && topLeftOutcode.bitwiseAnd(OUT_BOTTOM_RIGHT) == OUT_BOTTOM_RIGHT) {
            
                 return maxAlignmentDistance + 1.0;
        
        } else {
            // The shapes relate in some way, so we'll compute the horizontal and vertical alignment
            
            // The shapes intersect, so compute the alignment of the left / right and top / bottom
            // boundaries of the shapes
            val horizontalAlignment = Math.min(
                Math.abs(bounds2.x - bounds1.x),
                Math.abs(bounds2.x + bounds2.width - bounds1.x - bounds1.width)
            );
            
            val verticalAlignment = Math.min(
                Math.abs(bounds2.y - bounds1.y),
                Math.abs(bounds2.y + bounds2.height - bounds1.y - bounds1.height)
            );
            
            
            if (bounds2.intersects(bounds1)) {
                // The shapes intersect, so we'll use whatever's smaller of the horizontal and vertical
                // alignment
                return Math.min(horizontalAlignment, verticalAlignment);
                
            } else if (bottomRightOutcode.bitwiseAnd(TOP) != 0
                || topLeftOutcode.bitwiseAnd(BOTTOM) != 0) {
                    
                // The shapes are above / below, so compute the horizontal alignment
                return horizontalAlignment;
            } else if (bottomRightOutcode.bitwiseAnd(LEFT) != 0
                || topLeftOutcode.bitwiseAnd(RIGHT) != 0) {
                
                // The shapes are left / right, so compute the vertical alignment
                return verticalAlignment;
            } else {
                // Actually, this case shouldn't happen, but well... better be safe.
                return maxAlignmentDistance + 1.0;
            }
        }
    }


    ////////////////////////////////////////////////////
    // Find Siblings Mentioned in Comment

    /**
     * Tries to find a sibling node in the graph that is mentioned by name in the comment. If multiple
     * siblings are mentioned in the comment, we don't return any of them since we don't support the
     * attachment of comments to more than one object.
     * 
     * <p>This version of the code looks for the exact label of siblings (ignoring the case).</p>
     *
     * @param commentNode the comment node whose siblings we want to find.
     * @return the node whose label is found in the comment node or {@code null} if no or more than one
     *         node is found.
     */
    private def KNode findMentionedSibling(KNode commentNode) {
        val commentContents = commentNode.layout.getProperty(COMMENT_TEXT).toLowerCase

        var KNode foundSibling = null
        
        // Search through all non-comment siblings
        for (candidate : commentNode.parent.children) {
            if (!candidate.markedAsComment) {
                val siblingName = candidate.name.trim;
                
                if (siblingName.length > 0 && commentContents.contains(siblingName.toLowerCase())) {
                    // If this is the first sibling we found, we're still good; once we find a second
                    // one, simply return null
                    if (foundSibling == null) {
                        foundSibling = candidate
                    } else {
                        return null
                    }
                }
            }
        }
        
        return foundSibling
    }
    
    /**
     * For every comment the method finds out whether it contains the label of a non-comment node in the
     * diagram and returns the non-comment node. If a comment contains more than one node label, we
     * assume that this comment concerns the entire diagram and the node is not returned.
     *
     * @param commentNode the comment node for which we find out whether it contains any labels.
     * @return siblingWithName the node whose label is found in the comment node or {@code null} if no
     *         node's label is matching.
     */
    private def KNode findMentionedSiblingFuzzy(KNode commentNode) {
        val commentContents = commentNode.layout.getProperty(COMMENT_TEXT)

        var KNode foundSibling = null
        
        // Search through all non-comment siblings
        for (candidate : commentNode.parent.children) {
            if (!candidate.markedAsComment) {
                val siblingName = candidate.name;
                
                if (fuzzilyContains(commentContents, siblingName)) {
                    // If this is the first sibling we found, we're still good; once we find a second
                    // one, simply return null
                    if (foundSibling == null) {
                        foundSibling = candidate
                    } else {
                        return null
                    }
                }
            }
        }
        
        return foundSibling
    }
    
    /**
     * Checks if the search string is fuzzily contained in the base string. Fuzzy containation (I so
     * much want that word to exist) is (fuzzily) defined as follows:
     * <ul>
     *   <li>a space character in the search string can be represented by one or more whitespace and
     *       line break characters in the base string.</li>
     *   <li>if the search string is camelCased, each upper-case character preceded by a lower-case
     *       character can be prefixed by one or more whitespace and line break characters in the base
     *       string.</li>
     * </ul>
     * 
     * @param base the string to search in.
     * @param search the string to search for.
     * @return {@code true} if the search string is fuzzily contained in the base string as defined
     *         above.
     */
    private def boolean fuzzilyContains(String base, String search) {
        val regExp = new StringBuffer(search.length * 2);
        
        var char lastC;
        
        val trimmedSearch = search.trim;
        for (var i = 0; i < trimmedSearch.length; i++) {
            val char currC = trimmedSearch.charAt(i);
            
            if (Character.isUpperCase(currC)) {
                // If the previous character was lower-case, insert whitespace placeholders
                if (i > 0 && Character.isLowerCase(trimmedSearch.charAt(i - 1))) {
                    regExp.append("[\\h\\v]*");
                }
                
                regExp.append(currC);
            } else if (Character.isWhitespace(currC)) {
                // The first of a series of whitespace characters must be replaced by whitespace
                // placeholders in the regular expression
                if (i > 0 && !Character.isWhitespace(trimmedSearch.charAt(i - 1))) {
                    regExp.append("[\\h\\v]*");
                }
            } else {
                // It's neither upper-case, nor whitespace, so just add it to the regular expression
                regExp.append(currC);
            }
            
            lastC = currC;
        }
        
        // Check if the regular expression matches something in the base string
        val pattern = Pattern.compile(regExp.toString,
            Pattern.DOTALL.bitwiseOr(Pattern.CASE_INSENSITIVE));
        val matcher = pattern.matcher(base);
        
        return matcher.matches;
    }


    ////////////////////////////////////////////////////
    // Node Area
    
    /**
     * Checks if the comment's area is small enough for it to be considered attachable. The idea is
     * that comments with a very large area usually don't relate to a specific object, but rather
     * describe the diagram as a whole (in which case it won't be attached), or a group of objects
     * (which we don't support).
     * 
     * @param commentNode the node whose area to test.
     * @return {@code true} if the node is small enough to be attached.
     */
    private def boolean isSmallEnoughToBeAttached(KNode commentNode) {
        val commentBounds = getPtolemyBounds(commentNode);
        
        return commentBounds.width * commentBounds.height <= maxCommentArea;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // ATTACHMENT UTILITIES

     /**
     * Retrieves the position of the given node in the original Ptolemy diagram, if any, as well as
     * its size as given by the node's shape layout. The shape layout's size fields are expected to
     * have been correctly set during the visualization step of the diagram synthesis. This method
     * is a create method because the bounds don't change and we like Xtend to cache already
     * calculated positions for us. Makes it run faster.
     *
     * @param node the node whose  to retrieve.
     * @return the bounds or some bounds way out if there was a problem.
     */
    private def create bounds : new Rectangle2D.Double() getPtolemyBounds(KNode node) {
        // Initialize point with ridiculous values
        bounds.x = 2e20
        bounds.y = 2e20

        // Get location annotation
        var locationAnnotation = node.getAnnotation(ANNOTATION_LOCATION)
        var String locationAnnotationValue

        if (locationAnnotation == null) {
            var locationSpecialAnnotation = node.layout.getProperty(PT_LOCATION)
            if (locationSpecialAnnotation == null) {
                return
            } else {
                locationAnnotationValue = locationSpecialAnnotation
            }
        } else {
            locationAnnotationValue = locationAnnotation.value
        }

        // Try parsing the location information by splitting the string into an array of components.
        // Locations have one of the following three representations:
        //   "[140.0, 20.0]"     "{140.0, 20.0}"     "140.0, 20.0"
        // We remove all braces and whitespace and split at the comma that remains.
        val locationString = locationAnnotationValue.replaceAll("[\\s\\[\\]{}]+", "")
        val locationArray = locationString.split(",")

        if (locationArray.size == 2) {
            try {
                bounds.x = Double::valueOf(locationArray.get(0))
                bounds.y = Double::valueOf(locationArray.get(1))

                // Save the node's size in the bounds as well
                val estimatedSize = PlacementUtil::estimateSize(node)

                bounds.width = estimatedSize.width
                bounds.height = estimatedSize.height
            } catch (NumberFormatException e) {

            }
        }

        // The location defines where an actor's anchor point is. Where the anchor point is positioned
        // in the actor is a completely different question and defaults to the actor's center, except
        // for TextAttribute instances, which default to northwest.
        val anchorDefault =
            if (node.markedAsComment) {
                "northwest"
            } else {
                "center"
            }
        val anchorAnnotation = node.getAnnotation(ANNOTATION_ANCHOR)
        val anchorString = anchorAnnotation?.value ?: anchorDefault

        switch (anchorString) {
            case "north": {
                bounds.x = bounds.x - bounds.width / 2
            }
            case "south": {
                bounds.x = bounds.x - bounds.width / 2
                bounds.y = bounds.y - bounds.height
            }
            case "west": {
                bounds.y = bounds.y - bounds.height / 2
            }
            case "east": {
                bounds.x = bounds.x - bounds.width
                bounds.y = bounds.y - bounds.height / 2
            }
            case "northwest": {
                // Nothing to do
            }
            case "northeast": {
                bounds.x = bounds.x - bounds.width
            }
            case "southwest": {
                bounds.y = bounds.y - bounds.height
            }
            case "sountheast": {
                // Ptolemy has a typo here; we support this typo as well as the correct spelling
                bounds.x = bounds.x - bounds.width
                bounds.y = bounds.y - bounds.height
            }
            case "southeast": {
                bounds.x = bounds.x - bounds.width
                bounds.y = bounds.y - bounds.height
            }
            default: {
                bounds.x = bounds.x - bounds.width / 2
                bounds.y = bounds.y - bounds.height / 2
            }
        }
    }

    val TOP = Rectangle2D.OUT_TOP
    val LEFT = Rectangle2D.OUT_LEFT
    val RIGHT = Rectangle2D.OUT_RIGHT
    val BOTTOM = Rectangle2D.OUT_BOTTOM

    val OUT_TOP_LEFT = Rectangle2D::OUT_TOP.bitwiseOr(Rectangle2D::OUT_LEFT)
    val OUT_BOTTOM_LEFT = Rectangle2D::OUT_BOTTOM.bitwiseOr(Rectangle2D::OUT_LEFT)
    val OUT_TOP_RIGHT = Rectangle2D::OUT_TOP.bitwiseOr(Rectangle2D::OUT_RIGHT)
    val OUT_BOTTOM_RIGHT = Rectangle2D::OUT_BOTTOM.bitwiseOr(Rectangle2D::OUT_RIGHT)

    /**
     * Compute the squared distance between the two shapes defined by the given bounds. If the two
     * shapes intersect, a distance of zero is returned.
     *
     * <p>The current implementation simply returns the square of the euclidean distance, which is
     * perfectly fine.</p>
     *
     * @param bounds1 the first shape.
     * @param bounds2 the second shape.
     * @return the squared distance between the two shapes.
     */
    private def double computeSquaredDistance(Rectangle2D.Double bounds1, Rectangle2D.Double bounds2) {
        // Check if the two shapes intersect
        if (bounds1.intersects(bounds2)) {
            return 0
        }

        // Check where the top left and bottom right corners of shape 1 lie with respect to shape 2
        val topLeftOutcode = bounds2.outcode(bounds1.x, bounds1.y)
        val bottomRightOutcode = bounds2.outcode(bounds1.x + bounds1.width, bounds1.y + bounds1.height)

        // What we use to compute the distances depends entirely on where the two corners are
        if (topLeftOutcode.bitwiseAnd(OUT_TOP_LEFT) == OUT_TOP_LEFT
            && bottomRightOutcode.bitwiseAnd(OUT_TOP_LEFT) == OUT_TOP_LEFT) {

            // Return distance between shape1.bottomRight and shape2.topLeft
            return computeSquaredDistance(
                bounds1.x + bounds1.width, bounds1.y + bounds1.height,
                bounds2.x, bounds2.y)
                
        } else if (topLeftOutcode.bitwiseAnd(OUT_BOTTOM_LEFT) == OUT_BOTTOM_LEFT
            && bottomRightOutcode.bitwiseAnd(OUT_BOTTOM_LEFT) == OUT_BOTTOM_LEFT) {

            // Return distance between shape1.topRight and shape2.bottomLeft
            return computeSquaredDistance(
                bounds1.x + bounds1.width, bounds1.y,
                bounds2.x, bounds2.y + bounds2.height)
                
        } else if (topLeftOutcode.bitwiseAnd(OUT_TOP_RIGHT) == OUT_TOP_RIGHT
            && bottomRightOutcode.bitwiseAnd(OUT_TOP_RIGHT) == OUT_TOP_RIGHT) {

            // Return distance between shape1.bottomLeft and shape2.topRight
            return computeSquaredDistance(
                bounds1.x, bounds1.y + bounds1.height,
                bounds2.x + bounds2.width, bounds2.y)
                
        } else if (topLeftOutcode.bitwiseAnd(OUT_BOTTOM_RIGHT) == OUT_BOTTOM_RIGHT
            && bottomRightOutcode.bitwiseAnd(OUT_BOTTOM_RIGHT) == OUT_BOTTOM_RIGHT) {

            // Return distance between shape1.topLeft and shape2.bottomRight
            return computeSquaredDistance(
                bounds1.x, bounds1.y,
                bounds2.x + bounds2.width, bounds2.y + bounds2.height)
                
        } else if (topLeftOutcode.bitwiseAnd(Rectangle2D::OUT_LEFT) != 0
            && bottomRightOutcode.bitwiseAnd(Rectangle2D::OUT_LEFT) != 0) {

            // Return distance between shape1.right and shape2.left
            val distance = bounds1.x + bounds1.width - bounds2.x
            return distance * distance
            
        } else if (topLeftOutcode.bitwiseAnd(Rectangle2D::OUT_RIGHT) != 0
            && bottomRightOutcode.bitwiseAnd(Rectangle2D::OUT_RIGHT) != 0) {

            // Return distance between shape1.left and shape2.right
            val distance = bounds1.x - bounds2.x - bounds2.width
            return distance * distance
            
        } else if (topLeftOutcode.bitwiseAnd(Rectangle2D::OUT_TOP) != 0
            && bottomRightOutcode.bitwiseAnd(Rectangle2D::OUT_TOP) != 0) {

            // Return distance between shape1.bottom and shape2.top
            val distance = bounds1.y + bounds1.height - bounds2.y
            return distance * distance
            
        } else if (topLeftOutcode.bitwiseAnd(Rectangle2D::OUT_BOTTOM) != 0
            && bottomRightOutcode.bitwiseAnd(Rectangle2D::OUT_BOTTOM) != 0) {

            // Return distance between shape1.top and shape2.bottom
            val distance = bounds1.y - bounds2.y - bounds2.height
            return distance * distance
        }

        return maxAttachmentDistance + 1.0
    }
    
    /**
     * Returns the square of the distance between the two points defined by the given coordinates.
     *
     * @param x1 x coordinate of the first point.
     * @param y1 y coordinate of the first point.
     * @param x2 x coordinate of the second point.
     * @param y2 y coordinate of the second point.
     * @return the squared distance between the two points.
     */
    private def double computeSquaredDistance(double x1, double y1, double x2, double y2) {
        val deltaX = x2 - x1
        val deltaY = y2 - y1

        return deltaX * deltaX + deltaY * deltaY
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // DEBUGGING UTILITIES
    
    /**
     * Saves the given KGraph as an SVG in one of our excellent debugging locations.
     * 
     * @param root root node of the KGraph to save.
     */
    private def void saveDebugSvg(KNode root) {
        // Iterate over the children and find the bounds of the diagram
        var Rectangle2D.Double bounds = null;
        for (child : root.children) {
            if (child.ptolemyBounds.x != 2e20) {
                if (bounds == null) {
                    bounds = new Rectangle2D.Double();
                    bounds.setRect(child.ptolemyBounds);
                } else {
                    bounds.add(child.ptolemyBounds);
                }
            }

            if (!child.children.empty) {
                saveDebugSvg(child);
            }
        }

        // It may happen that none of the children have proper positions, in which case we don't have
        // anything to draw
        if (bounds == null) {
            return;
        }

        // Make the bounds start at (0,0)
        bounds.width += bounds.x + 5;
        bounds.height += bounds.y + 5;
        bounds.x = 0;
        bounds.y = 0;

        // Create an SVG graphics object to draw on
        val graphics = SVGGeneratorManager.createGraphics(
            "de.cau.cs.kieler.klighd.piccolo.svggen.freeHEPExtended", bounds, false, false);

        graphics.clip(bounds);
        graphics.fillColor = KlighdConstants.WHITE;
        graphics.fill(bounds);

        // Draw all children on the diagram
        root.children.forEach[ child |
            if (child.ptolemyBounds.x != 2e20) {
                graphics.strokeColor =
                    if (child.markedAsComment)
                        KlighdConstants.BLUE
                    else
                        KlighdConstants.BLACK;

                graphics.draw(child.ptolemyBounds);
            }
        ];

        // Find a proper file name
        try {
            val oStream = createOutputStream(root);
            graphics.stream(oStream);
            oStream.close();
        } catch (IOException e) {
            System.out.println("Unable to save debug output...");
            e.printStackTrace();
        }
    }

    private def OutputStream createOutputStream(KNode root) throws IOException {
        val path = getDebugOutputPath();
        new File(path).mkdirs();

        val debugFileName = getDebugOutputFileName(root);
        return new FileOutputStream(new File(path + File.separator + debugFileName + ".svg"));
    }

    private def String getDebugOutputPath() {
        var path = System.getProperty("user.home");
        if (path.endsWith(File.separator)) {
            path += "tmp" + File.separator + "ptdebug";
        } else {
            path += File.separator + "tmp" + File.separator + "ptdebug";
        }

        return path;
    }

    private def String getDebugOutputFileName(KNode root) {
        return Integer.toString(root.hashCode().bitwiseAnd((1 << (Integer.SIZE / 2)) - 1));
    }

}