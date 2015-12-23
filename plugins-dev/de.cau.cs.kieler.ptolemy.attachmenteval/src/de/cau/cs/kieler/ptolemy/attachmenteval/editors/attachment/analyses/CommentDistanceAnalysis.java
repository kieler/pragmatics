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
import de.cau.cs.kieler.kiml.comments.CommentAttacher;
import de.cau.cs.kieler.kiml.comments.DistanceHeuristic;
import de.cau.cs.kieler.kiml.comments.IBoundsProvider;
import de.cau.cs.kieler.ptolemy.attachmenteval.editors.attachment.CommentAttachmentEditor;
import de.cau.cs.kieler.ptolemy.klighd.transformation.comments.PtolemyBoundsProvider;

/**
 * Analyses the distances between comments and nodes and stuff.
 * 
 * @author cds
 */
public class CommentDistanceAnalysis implements IAttachmentAnalysis {
    
    /** Bounds provider used to find the node nearest to a comment. */
    private IBoundsProvider boundsProvider = null;
    /** Distances between a comments and the nodes they are attached to in the reference function. */
    private List<Double> attachedNodeDistances = Lists.newArrayList();
    /** Distance between a comment that's attached to something and its nearest node. */
    private List<Double> nearestNodeToAttachedCommentDistances = Lists.newArrayList();
    /** Distance between a comment that's not attached to anything and its nearest node. */
    private List<Double> nearestNodeToUnattachedCommentDistances = Lists.newArrayList();
    /** Number of attached comments. */
    private int attachedComments = 0;
    /** Number of attached comments that are attached to the node nearest to them. */
    private int commentsAttachedToNearestNode = 0;

    /**
     * Creates a new instance.
     */
    public CommentDistanceAnalysis() {
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
        recursivelyProcess(model, editor);
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
        System.out.println("------ Distances between comments and their attached nodes");
        attachedNodeDistances.stream().forEach((dist) -> System.out.println(dist));
        System.out.println("------ Distances between attached comments and their closest nodes");
        nearestNodeToAttachedCommentDistances.stream().forEach((dist) -> System.out.println(dist));
        System.out.println("------ Distances between unattached comments and their closest nodes");
        nearestNodeToUnattachedCommentDistances.stream().forEach((dist) -> System.out.println(dist));
        
        System.out.println("<===== End Comment Distance Analysis Results");
    }

    // ///////////////////////////////////////////////////////////////////////////////////////////
    // Analysis

    private void recursivelyProcess(final KNode graph, CommentAttachmentEditor editor) {
        for (KNode child : graph.getChildren()) {
            if (CommentAttacher.isComment(child)) {
                KNode nearestNode = findNearestNode(child);
                KNode attachedNode = editor.getAttachmentTarget(child);
                
                if (attachedNode == null) {
                    // The comment is not attached to anything
                    double nearestNodeDistance = Math.sqrt(DistanceHeuristic.squaredDistance(
                            boundsProvider.boundsFor(child),
                            boundsProvider.boundsFor(nearestNode)));
                    nearestNodeToUnattachedCommentDistances.add(nearestNodeDistance);
                } else {
                    // The comment is attached to a node
                    attachedComments++;
                    
                    double attachedNodeDistance = Math.sqrt(DistanceHeuristic.squaredDistance(
                            boundsProvider.boundsFor(child),
                            boundsProvider.boundsFor(attachedNode)));
                    attachedNodeDistances.add(attachedNodeDistance);
                    
                    if (nearestNode == attachedNode) {
                        // The attached node is the node closest to the comment
                        commentsAttachedToNearestNode++;
                        nearestNodeToAttachedCommentDistances.add(attachedNodeDistance);
                    } else {
                        // The attached node is not the node closest to the comment
                        double nearestNodeDistance = Math.sqrt(DistanceHeuristic.squaredDistance(
                                boundsProvider.boundsFor(child),
                                boundsProvider.boundsFor(nearestNode)));
                        nearestNodeToAttachedCommentDistances.add(nearestNodeDistance);
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
                double distance = DistanceHeuristic.squaredDistance(
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
