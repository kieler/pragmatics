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

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.comments.AlignmentHeuristic;
import de.cau.cs.kieler.kiml.comments.CommentAttacher;
import de.cau.cs.kieler.kiml.comments.IBoundsProvider;
import de.cau.cs.kieler.kiml.comments.TextPrefixFilter;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.ptolemy.attachmenteval.editors.attachment.CommentAttachmentEditor;
import de.cau.cs.kieler.ptolemy.klighd.PtolemyProperties;
import de.cau.cs.kieler.ptolemy.klighd.transformation.comments.PtolemyBoundsProvider;
import de.cau.cs.kieler.ptolemy.klighd.transformation.comments.PtolemyTitleCommentFilter;

/**
 * Analyses the alignment between comments and nodes and stuff.
 * 
 * @author cds
 */
public class CommentAlignmentAnalysis implements IAttachmentAnalysis {
    
    /** Bounds provider used to find the node nearest to a comment. */
    private IBoundsProvider boundsProvider = null;
    /** The author comment filter. */
    private TextPrefixFilter authorCommentFilter = null;
    /** The title comment filter. */
    private PtolemyTitleCommentFilter titleCommentFilter = null;
    /** Alignments between a comments and the nodes they are attached to in the reference function. */
    private List<Double> attachedNodeAlignments = Lists.newArrayList();
    /** Alignments between a comment that's attached to something and its best aligned node. */
    private List<Double> bestAlignedNodeToAttachedCommentAlignments = Lists.newArrayList();
    /** Alignments between a comment that's not attached to anything and its best aligned node. */
    private List<Double> bestAlignedNodeToUnattachedCommentAlignments = Lists.newArrayList();
    /**
     * Alignments between a comment that's not attached, but eligible for attachment and its best
     * aligned node.
     */
    private List<Double> bestAlignedNodeToUnattachedFilteredCommentDistances = Lists.newArrayList();
    /** Number of attached comments. */
    private int attachedComments = 0;
    /** Number of attached comments that are attached to the node they are best aligned to. */
    private int commentsAttachedToBestAlignedNode = 0;
    

    /**
     * Creates a new instance.
     */
    public CommentAlignmentAnalysis() {
        authorCommentFilter = new TextPrefixFilter()
            .withCommentTextProvider((comment) ->
                comment.getData(KLayoutData.class).getProperty(PtolemyProperties.COMMENT_TEXT))
            .addPrefix("Author")  // Also matches "Authors"
            .addPrefix("Demo created by");
        
        Injector injector = Guice.createInjector();
    
        titleCommentFilter = injector.getInstance(PtolemyTitleCommentFilter.class);
        titleCommentFilter.decideBasedOnFontSizeOnly();
        
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
        authorCommentFilter.preprocess(model, true);
        titleCommentFilter.preprocess(model, true);
        
        recursivelyProcess(model, editor);
        
        authorCommentFilter.cleanup();
        titleCommentFilter.cleanup();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void finish() {
        System.out.println("=====> Start Comment Alignment Analysis Results");
        
        // Statistics
        System.out.println("    --> Attached comments: " + attachedComments);
        System.out.println("    --> Comments attached to best aligned node: "
                + commentsAttachedToBestAlignedNode);
        
        // Distance Histograms
        System.out.println("------ Comments and their attached nodes");
        attachedNodeAlignments.stream().forEach((dist) -> System.out.println(dist));
        System.out.println("------ Attached comments and their best aligned nodes");
        bestAlignedNodeToAttachedCommentAlignments.stream().forEach((dist) -> System.out.println(dist));
        System.out.println("------ Unattached comments and their best aligned nodes");
        bestAlignedNodeToUnattachedCommentAlignments.stream().forEach((dist) -> System.out.println(dist));
        System.out.println("------ Filtered unattached comments and their best aligned nodes");
        bestAlignedNodeToUnattachedFilteredCommentDistances.stream()
            .forEach((dist) -> System.out.println(dist));
        
        System.out.println("<===== End Comment Alignment Analysis Results");
    }

    // ///////////////////////////////////////////////////////////////////////////////////////////
    // Analysis

    private void recursivelyProcess(final KNode graph, CommentAttachmentEditor editor) {
        for (KNode child : graph.getChildren()) {
            if (CommentAttacher.isComment(child)) {
                KNode bestAlignedNode = findBestAlignedNode(child);
                KNode attachedNode = editor.getAttachmentTarget(child);
                
                if (attachedNode == null) {
                    // The comment is not attached to anything
                    if  (bestAlignedNode != null) {
                        double bestAlignment = AlignmentHeuristic.alignment(
                                boundsProvider.boundsFor(child),
                                boundsProvider.boundsFor(bestAlignedNode));
                        bestAlignedNodeToUnattachedCommentAlignments.add(bestAlignment);
                        
                        if (authorCommentFilter.eligibleForAttachment(child)
                                && titleCommentFilter.eligibleForAttachment(child)) {
                            
                            bestAlignedNodeToUnattachedFilteredCommentDistances.add(bestAlignment);
                        }
                    }
                } else {
                    // The comment is attached to a node
                    attachedComments++;
                    
                    double attachedNodeAlignment = AlignmentHeuristic.alignment(
                            boundsProvider.boundsFor(child),
                            boundsProvider.boundsFor(attachedNode));
                    attachedNodeAlignments.add(attachedNodeAlignment);
                    
                    if (bestAlignedNode == attachedNode) {
                        // The attached node is the node best aligned to the comment
                        commentsAttachedToBestAlignedNode++;
                        bestAlignedNodeToAttachedCommentAlignments.add(attachedNodeAlignment);
                    } else {
                        // The attached node is not the node best aligned to the comment
                        if (bestAlignedNode != null) {
                            double bestNodeAlignment = AlignmentHeuristic.alignment(
                                    boundsProvider.boundsFor(child),
                                    boundsProvider.boundsFor(bestAlignedNode));
                            bestAlignedNodeToAttachedCommentAlignments.add(bestNodeAlignment);
                        }
                    }
                }
            }

            if (!child.getChildren().isEmpty()) {
                recursivelyProcess(child, editor);
            }
        }
    }

    private KNode findBestAlignedNode(KNode comment) {
        Rectangle2D.Double commentBounds = boundsProvider.boundsFor(comment);
        double bestAlignment = Double.MAX_VALUE;
        KNode bestAlignedSibling = null;
        
        for (KNode sibling : comment.getParent().getChildren()) {
            if (!CommentAttacher.isComment(sibling)) {
                double alignment = AlignmentHeuristic.alignment(
                        commentBounds, boundsProvider.boundsFor(sibling));
                
                if (alignment >= 0 && alignment < bestAlignment) {
                    bestAlignment = alignment;
                    bestAlignedSibling = sibling;
                }
            }
        }
        
        return bestAlignedSibling;
    }

}
