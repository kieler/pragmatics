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

import org.eclipse.elk.core.comments.AlignmentMatcher;
import org.eclipse.elk.core.comments.CommentAttacher;
import org.eclipse.elk.core.comments.DistanceMatcher;
import org.eclipse.elk.core.comments.IBoundsProvider;
import org.eclipse.elk.core.comments.TextPrefixFilter;
import org.eclipse.elk.graph.KNode;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;

import de.cau.cs.kieler.ptolemy.attachmenteval.editors.attachment.CommentAttachmentEditor;
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
    private TextPrefixFilter textPrefixFilter = null;
    /** The title comment filter. */
    private PtolemyTitleCommentFilter titleCommentFilter = null;
    /** Alignments between a comments and the nodes they are attached to in the reference function. */
    private List<Double> attachedNodeAlignments = Lists.newArrayList();
    /** Alignments between a comment that's attached to something and its best aligned node. */
    private List<Double> bestAlignedToAttachedAlignments = Lists.newArrayList();
    /** Alignments between a comment that's not filtered out and its best aligned node. */
    private List<Double> bestAlignedToFilteredAllAlignments = Lists.newArrayList();
    /** Alignments between an unattached comment that's not filtered out and its best aligned node. */
    private List<Double> bestAlignedToFilteredUnattachedAlignments = Lists.newArrayList();
    /** Number of attached comments. */
    private int attachedComments = 0;
    /** Number of attached comments that are attached to the node they are best aligned to. */
    private int commentsAttachedToBestAlignedNode = 0;
    

    /**
     * Creates a new instance.
     */
    public CommentAlignmentAnalysis() {
        textPrefixFilter = CommentPrefixAnalysis.createTextPrefixFilter();
        titleCommentFilter = CommentFontSizeAnalysis.createTitleCommentFilter();
        
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
        textPrefixFilter.preprocess(model, true);
        titleCommentFilter.preprocess(model, true);
        
        recursivelyProcess(model, editor);
        
        textPrefixFilter.cleanup();
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
        System.out.println("------ Best alignment to attached comment");
        bestAlignedToAttachedAlignments.stream().forEach((dist) -> System.out.println(dist));
        System.out.println("------ Best alignment to filtered comment");
        bestAlignedToFilteredAllAlignments.stream().forEach((dist) -> System.out.println(dist));
        System.out.println("------ Best alignment to filtered unattached comment");
        bestAlignedToFilteredUnattachedAlignments.stream()
            .forEach((dist) -> System.out.println(dist));
        
        System.out.println("<===== End Comment Alignment Analysis Results");
    }

    // ///////////////////////////////////////////////////////////////////////////////////////////
    // Analysis

    private void recursivelyProcess(final KNode graph, CommentAttachmentEditor editor) {
        for (KNode child : graph.getChildren()) {
            if (CommentAttacher.isComment(child)) {
                KNode attachedNode = editor.getAttachmentTarget(child);
                
                if (attachedNode == null) {
                    // The comment is not attached to anything
                    KNode bestAlignedNode = findBestAlignedNode(child, false);
                    if  (bestAlignedNode != null) {
                        double bestAlignment = AlignmentMatcher.alignment(
                                boundsProvider.boundsFor(child),
                                boundsProvider.boundsFor(bestAlignedNode));
                        
                        if (textPrefixFilter.eligibleForAttachment(child)
                                && titleCommentFilter.eligibleForAttachment(child)) {
                            
                            bestAlignedToFilteredAllAlignments.add(bestAlignment);
                            bestAlignedToFilteredUnattachedAlignments.add(bestAlignment);
                        }
                    }
                } else {
                    // The comment is attached to a node
                    attachedComments++;
                    
                    double attachedNodeAlignment = AlignmentMatcher.alignment(
                            boundsProvider.boundsFor(child),
                            boundsProvider.boundsFor(attachedNode));
                    attachedNodeAlignments.add(attachedNodeAlignment);
                    
                    KNode bestAlignedNode = findBestAlignedNode(child, false);
                    if (bestAlignedNode == attachedNode) {
                        // The attached node is the node best aligned to the comment
                        commentsAttachedToBestAlignedNode++;
                        bestAlignedToAttachedAlignments.add(attachedNodeAlignment);
                        bestAlignedToFilteredAllAlignments.add(attachedNodeAlignment);
                    } else {
                        // The attached node is not the node best aligned to the comment
                        if (bestAlignedNode != null) {
                            double bestNodeAlignment = AlignmentMatcher.alignment(
                                    boundsProvider.boundsFor(child),
                                    boundsProvider.boundsFor(bestAlignedNode));
                            bestAlignedToAttachedAlignments.add(bestNodeAlignment);
                            bestAlignedToFilteredAllAlignments.add(bestNodeAlignment);
                        }
                    }
                }
            }

            if (!child.getChildren().isEmpty()) {
                recursivelyProcess(child, editor);
            }
        }
    }

    private KNode findBestAlignedNode(final KNode comment, final boolean upToMaxDistance) {
        Rectangle2D.Double commentBounds = boundsProvider.boundsFor(comment);
        double bestAlignment = Double.MAX_VALUE;
        KNode bestAlignedSibling = null;
        
        for (KNode sibling : comment.getParent().getChildren()) {
            if (!CommentAttacher.isComment(sibling)) {
                Rectangle2D.Double siblingBounds = boundsProvider.boundsFor(sibling);
                
                // Make sure the node is in the comment's vicinity
                if (!upToMaxDistance
                        || DistanceMatcher.distance(commentBounds, siblingBounds) <= 20) {
                    
                    double alignment = AlignmentMatcher.alignment(commentBounds, siblingBounds);
                    
                    if (alignment >= 0 && alignment < bestAlignment) {
                        bestAlignment = alignment;
                        bestAlignedSibling = sibling;
                    }
                }
            }
        }
        
        return bestAlignedSibling;
    }

}
