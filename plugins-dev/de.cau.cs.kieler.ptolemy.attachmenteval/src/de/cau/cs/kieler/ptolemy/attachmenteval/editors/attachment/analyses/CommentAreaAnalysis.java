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
import de.cau.cs.kieler.kiml.comments.IBoundsProvider;
import de.cau.cs.kieler.kiml.comments.TextPrefixFilter;
import de.cau.cs.kieler.ptolemy.attachmenteval.editors.attachment.CommentAttachmentEditor;
import de.cau.cs.kieler.ptolemy.klighd.transformation.comments.PtolemyBoundsProvider;
import de.cau.cs.kieler.ptolemy.klighd.transformation.comments.PtolemyTitleCommentFilter;

/**
 * Displays statistics on the size of comments.
 * 
 * @author cds
 */
public class CommentAreaAnalysis implements IAttachmentAnalysis {
    
    /** Bounds provider used to find the node nearest to a comment. */
    private IBoundsProvider boundsProvider = null;
    /** The author comment filter. */
    private TextPrefixFilter textPrefixFilter = null;
    /** The title comment filter. */
    private PtolemyTitleCommentFilter titleCommentFilter = null;
    /** Sizes of all comments. */
    private List<Long> allCommentSizes = Lists.newArrayList();
    /** Sizes of comments that are neither author comments nor title comments (attached or not). */
    private List<Long> filteredCommentSizes = Lists.newArrayList();
    /** Sizes of comments that are neither author comments nor title comments and not attached. */
    private List<Long> filteredUnattachedCommentSizes = Lists.newArrayList();
    /** Sizes of comments that are attached in the reference function. */
    private List<Long> attachedCommentSizes = Lists.newArrayList();
    

    /**
     * Creates a new instance.
     */
    public CommentAreaAnalysis() {
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
        System.out.println("=====> Start Node Size Analysis Results");

        System.out.println("------ All Comments");
        allCommentSizes.stream().forEach((area) -> System.out.println(area));
        
        System.out.println("------ Filtered Comments");
        filteredCommentSizes.stream().forEach((area) -> System.out.println(area));
        
        System.out.println("------ Filtered Unattached Comments");
        filteredUnattachedCommentSizes.stream().forEach((area) -> System.out.println(area));
        
        System.out.println("------ Attached Comments");
        attachedCommentSizes.stream().forEach((area) -> System.out.println(area));
        
        System.out.println("<===== End Node Size Analysis Results");
    }
    

    // ///////////////////////////////////////////////////////////////////////////////////////////
    // Analysis

    private void recursivelyProcess(final KNode graph, CommentAttachmentEditor editor) {
        for (KNode child : graph.getChildren()) {
            if (CommentAttacher.isComment(child)) {
                Rectangle2D.Double bounds = boundsProvider.boundsFor(child);
                long area = (long) (bounds.height * bounds.width);
                
                // Record size in list of all comment sizes
                allCommentSizes.add(area);
                
                // Record size if it's not filtered out by the other filters
                if (textPrefixFilter.eligibleForAttachment(child)
                        && titleCommentFilter.eligibleForAttachment(child)) {
                    
                    filteredCommentSizes.add(area);
                    
                    // Record size if it is unattached in the reference function
                    if (editor.getAttachmentTarget(child) == null) {
                        filteredUnattachedCommentSizes.add(area);
                    }
                }
                
                // Record size if it is attached in the reference function
                if (editor.getAttachmentTarget(child) != null) {
                    attachedCommentSizes.add(area);
                }
            }
            
            if (!child.getChildren().isEmpty()) {
                recursivelyProcess(child, editor);
            }
        }
        
    }

}
