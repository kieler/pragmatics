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

import org.eclipse.elk.core.comments.CommentAttacher;
import org.eclipse.elk.core.comments.DistanceHeuristic;
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
 * Analyses the distances between comments and nodes and stuff.
 * 
 * @author cds
 */
public class CommentDistanceAnalysis implements IAttachmentAnalysis {
    
    /** Bounds provider used to find the node nearest to a comment. */
    private IBoundsProvider boundsProvider = null;
    /** The author comment filter. */
    private TextPrefixFilter textPrefixFilter = null;
    /** The title comment filter. */
    private PtolemyTitleCommentFilter titleCommentFilter = null;
    /** Distances between a comments and the nodes they are attached to in the reference function. */
    private List<Double> attachedNodeDistances = Lists.newArrayList();
    /** Distance between any comment and its nearest node. */
    private List<Double> closestToAnyDistances = Lists.newArrayList();
    /** Distance between a comment that's attached to something and its nearest node. */
    private List<Double> closestToAttachedDistances = Lists.newArrayList();
    /** Distance between a filtered comment its nearest node. */
    private List<Double> closestToFilteredAllDistances = Lists.newArrayList();
    /** Distance between a filtered unattached comment and its nearest node. */
    private List<Double> closestToFilteredUnattachedCommentDistances = Lists.newArrayList();
    /** Number of attached comments. */
    private int attachedComments = 0;
    /** Number of attached comments that are attached to the node nearest to them. */
    private int commentsAttachedToNearestNode = 0;

    /**
     * Creates a new instance.
     */
    public CommentDistanceAnalysis() {
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
        System.out.println("=====> Start Comment Distance Analysis Results");
        
        // Statistics
        System.out.println("    --> Attached comments: " + attachedComments);
        System.out.println("    --> Comments attached to nearest node: "
                + commentsAttachedToNearestNode);
        
        // Distance Histograms
        System.out.println("------ Comments and their attached nodes");
        attachedNodeDistances.stream().forEach((dist) -> System.out.println(dist));
        System.out.println("------ Closest node to any comment");
        closestToAnyDistances.stream().forEach((dist) -> System.out.println(dist));
        System.out.println("------ Closest node to attached comment");
        closestToAttachedDistances.stream().forEach((dist) -> System.out.println(dist));
        System.out.println("------ Closest node to filtered comment");
        closestToFilteredAllDistances.stream().forEach((dist) -> System.out.println(dist));
        System.out.println("------ Closest node to filtered unattached comment");
        closestToFilteredUnattachedCommentDistances.stream()
            .forEach((dist) -> System.out.println(dist));
        
        System.out.println("<===== End Comment Distance Analysis Results");
    }

    // ///////////////////////////////////////////////////////////////////////////////////////////
    // Analysis

    private void recursivelyProcess(final KNode graph, CommentAttachmentEditor editor) {
        for (KNode child : graph.getChildren()) {
            if (CommentAttacher.isComment(child)) {
                KNode attachedNode = editor.getAttachmentTarget(child);
                KNode nearestNode = findNearestNode(child);
                double nearestNodeDistance = DistanceHeuristic.distance(
                        boundsProvider.boundsFor(child),
                        boundsProvider.boundsFor(nearestNode));
                
                closestToAnyDistances.add(nearestNodeDistance);
                
                if (attachedNode == null) {
                    // Check if the comment is filtered out
                    if (textPrefixFilter.eligibleForAttachment(child)
                            && titleCommentFilter.eligibleForAttachment(child)) {
                        
                        closestToFilteredAllDistances.add(nearestNodeDistance);
                        closestToFilteredUnattachedCommentDistances.add(nearestNodeDistance);
                    }
                } else {
                    // The comment is attached to a node
                    attachedComments++;
                    
                    double attachedNodeDistance = DistanceHeuristic.distance(
                            boundsProvider.boundsFor(child),
                            boundsProvider.boundsFor(attachedNode));
                    attachedNodeDistances.add(attachedNodeDistance);
                    
                    if (nearestNode == attachedNode) {
                        // The attached node is the node closest to the comment
                        commentsAttachedToNearestNode++;
                        closestToAttachedDistances.add(attachedNodeDistance);
                    }
                    
                    if (textPrefixFilter.eligibleForAttachment(child)
                            && titleCommentFilter.eligibleForAttachment(child)) {
                        
                        closestToFilteredAllDistances.add(nearestNodeDistance);
                    }
                }
            }

            if (!child.getChildren().isEmpty()) {
                recursivelyProcess(child, editor);
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

}
