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
package de.cau.cs.kieler.ptolemy.attachmenteval.editors.attachment.analyses;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.core.comments.CommentAttacher;
import org.eclipse.elk.core.comments.DistanceHeuristic;
import org.eclipse.elk.core.comments.IBoundsProvider;
import org.eclipse.elk.core.comments.NodeReferenceHeuristic;
import org.eclipse.elk.core.klayoutdata.KLayoutData;
import org.eclipse.elk.graph.KNode;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;

import de.cau.cs.kieler.ptolemy.attachmenteval.editors.attachment.CommentAttachmentEditor;
import de.cau.cs.kieler.ptolemy.klighd.PtolemyProperties;
import de.cau.cs.kieler.ptolemy.klighd.transformation.comments.PtolemyBoundsProvider;

/**
 * Displays the results of the node reference heuristic.
 * 
 * @author cds
 */
public class NodeReferenceCommentAnalysis implements IAttachmentAnalysis {
    
    private static String NO_REFERENCE = "_ _";
    private static String REFERENCE_HEURISTIC_ONLY = "H  ";
    private static String REFERENCE_BOTH = "H R";
    private static String REFERENCE_DIFFERENT_ATTACHMENT = "H X";

    /** The node reference heuristic we'll be putting to the test here. */
    private NodeReferenceHeuristic referenceHeuristic = null;
    /** Bounds provider used to find the node nearest to a comment. */
    private IBoundsProvider boundsProvider = null;
    /** Distances between a comment and its referenced node if the attachment is correct. */
    private List<Double> correctAttachmentNodeDistances = Lists.newArrayList();
    /** Distances between a comment and its referenced node if the attachment is wrong. */
    private List<Double> wrongAttachmentNodeDistances = Lists.newArrayList();
    /**
     * Map from model file names to comment texts recognized by the heuristic and whether they are
     * attached in the reference function or not.
     */
    private Map<String, Map<String, String>> referenceComments = Maps.newTreeMap();
    /** The number of comments we've encountered. */
    private int comments = 0;
    /** The number of comments that feature a single node reference. */
    private int commentsWithSingleNodeReference = 0;
    /**
     * The number of comments that feature a single node reference AND are attached to the
     * referenced node in the reference attachment function.
     */
    private int commentsAttachedToReferencedNode = 0;
    /**
     * The number of comments that feature a single node reference AND are attached to another
     * node than the referenced one in the reference attachment function.
     */
    private int commentsAttachedToDifferentNode = 0;
    /**
     * The number of comments that feature a single node reference AND are attached to the
     * referenced node in the reference attachment function AND whose reference node is the nearest.
     */
    private int commentsAttachedToReferenceNodeThatAreNearest = 0;

    /**
     * Creates a new instance.
     */
    public NodeReferenceCommentAnalysis() {
        referenceHeuristic = new NodeReferenceHeuristic()
            .withCommentTextProvider((comment) ->
                comment.getData(KLayoutData.class).getProperty(PtolemyProperties.COMMENT_TEXT))
            .withNodeNameProvider((node) ->
                node.getLabels().isEmpty() ? null : node.getLabels().get(0).getText());
//            .withFuzzyMatching();
        
        Injector injector = Guice.createInjector();
        boundsProvider = injector.getInstance(PtolemyBoundsProvider.class);
        boundsProvider = boundsProvider.cached();
    }

    // ///////////////////////////////////////////////////////////////////////////////////////////
    // IAttachmentAnalysis

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(KNode model, String modelFilePath, CommentAttachmentEditor editor) {
        referenceHeuristic.preprocess(model, true);

        Map<String, String> references = Maps.newTreeMap();
        referenceComments.put(modelFilePath, references);
        recursivelyProcess(model, editor, references);

        referenceHeuristic.cleanup();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void finish() {
        System.out.println("=====> Start Node Reference Analysis Results");

        // Simply print the results to the console for each model
        for (String modelFile : referenceComments.keySet()) {
            System.out.println(modelFile);

            Map<String, String> comments = referenceComments.get(modelFile);
            for (String text : comments.keySet()) {
                if (!comments.get(text).equals(NO_REFERENCE)) {
                    System.out.println("    " + comments.get(text) + " " + text);
                }
            }
        }
        
        System.out.println("------ Statistics");
        
        System.out.println("Comments: " + comments);
        System.out.println("Comments that reference a single node: " + commentsWithSingleNodeReference);
        System.out.println("  --> No reference attachment: "
                + (commentsWithSingleNodeReference
                        - commentsAttachedToDifferentNode
                        - commentsAttachedToReferencedNode));
        System.out.println("  --> Same reference attachment: " + commentsAttachedToReferencedNode);
        System.out.println("      --> Nearest: " + commentsAttachedToReferenceNodeThatAreNearest);
        System.out.println("  --> Different reference attachment: " + commentsAttachedToDifferentNode);
        
        System.out.println("------ Distances between comments and correctly attached nodes");
        correctAttachmentNodeDistances.stream().forEach((dist) -> System.out.println(dist));
        System.out.println("------ Distances between comments and incorrectly attached nodes");
        wrongAttachmentNodeDistances.stream().forEach((dist) -> System.out.println(dist));
        
        System.out.println("<===== End Node Reference Analysis Results");
    }

    // ///////////////////////////////////////////////////////////////////////////////////////////
    // Analysis

    private void recursivelyProcess(final KNode graph, CommentAttachmentEditor editor,
            final Map<String, String> references) {

        for (KNode child : graph.getChildren()) {
            if (CommentAttacher.isComment(child)) {
                comments++;
                
                // Find the heuristically attached node and the node attached in the reference
                // attachment
                KNode attachedNode = editor.getAttachmentTarget(child);
                KNode heuristicAttachment = referenceHeuristic.getAttachments().get(child);
                
                if (heuristicAttachment == null) {
                    references.put(getCommentText(child), NO_REFERENCE);
                } else {
                    commentsWithSingleNodeReference++;
                    
                    // Find the distance between comment and node
                    double referencedNodeDistance = DistanceHeuristic.distance(
                            boundsProvider.boundsFor(child),
                            boundsProvider.boundsFor(heuristicAttachment));
                    
                    if (attachedNode == heuristicAttachment) {
                        correctAttachmentNodeDistances.add(referencedNodeDistance);
                    } else {
                        wrongAttachmentNodeDistances.add(referencedNodeDistance);
                    }
                    
                    // Find out if this is also the attachment in the reference attachment function
                    KNode referenceAttachment = editor.getAttachmentTarget(child);
                    
                    if (referenceAttachment == null) {
                        references.put(getCommentText(child), REFERENCE_HEURISTIC_ONLY);
                    } else if (referenceAttachment == heuristicAttachment) {
                        commentsAttachedToReferencedNode++;
                        references.put(getCommentText(child), REFERENCE_BOTH);
                        
                        if (findNearestNode(child) == referenceAttachment) {
                            commentsAttachedToReferenceNodeThatAreNearest++;
                        }
                    } else {
                        commentsAttachedToDifferentNode++;
                        references.put(getCommentText(child), REFERENCE_DIFFERENT_ATTACHMENT);
                    }
                }
            }

            if (!child.getChildren().isEmpty()) {
                recursivelyProcess(child, editor, references);
            }
        }
    }

    private KNode findNearestNode(KNode comment) {
        Rectangle2D.Double commentBounds = boundsProvider.boundsFor(comment);
        double nearestDistance = Double.MAX_VALUE;
        KNode nearestSibling = null;
        
        for (KNode sibling : comment.getParent().getChildren()) {
            if (!CommentAttacher.isComment(sibling)) {
                double distance = DistanceHeuristic.distance(
                        commentBounds, boundsProvider.boundsFor(sibling));
                
                if (distance >= 0 && distance < nearestDistance) {
                    nearestDistance = distance;
                    nearestSibling = sibling;
                }
            }
        }
        
        return nearestSibling;
    }

    // ///////////////////////////////////////////////////////////////////////////////////////////
    // Utilities

    /**
     * Returns the text of the given comment, shortened to at most 50 characters.
     * 
     * @param comment
     *            the comment.
     * @return possibly shortened comment text.
     */
    private String getCommentText(final KNode comment) {
        KLayoutData layoutData = comment.getData(KLayoutData.class);
        String text = layoutData.getProperty(PtolemyProperties.COMMENT_TEXT);
        return text.replace('\n', ' ');

        // // We shorten the text to 50 characters if it's longer
        // int cutoffLength = Math.min(50, text.length());
        //
        // // If there's a line break within the first 50 characters, shorten to that
        // int newlineIndex = text.indexOf('\n');
        // if (newlineIndex >= 0 && newlineIndex < cutoffLength) {
        // cutoffLength = newlineIndex;
        // }
        //
        // return text.substring(0, cutoffLength) + (cutoffLength < text.length() ? "..." : "");
    }

}
