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
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.ptolemy.attachmenteval.editors.attachment.CommentAttachmentEditor;
import de.cau.cs.kieler.ptolemy.klighd.PtolemyProperties;
import de.cau.cs.kieler.ptolemy.klighd.transformation.comments.PtolemyBoundsProvider;
import de.cau.cs.kieler.ptolemy.klighd.transformation.comments.PtolemyTitleCommentFilter;

/**
 * Displays statistics on the size of comments.
 * 
 * @author cds
 */
public class CommentSizeAnalysis implements IAttachmentAnalysis {
    
    /** Bounds provider used to find the node nearest to a comment. */
    private IBoundsProvider boundsProvider = null;
    /** The author comment filter. */
    private TextPrefixFilter authorCommentFilter = null;
    /** The title comment filter. */
    private PtolemyTitleCommentFilter titleCommentFilter = null;
    /** Sizes of comments that are unattached in the reference function. */
    private List<Long> unattachedCommentSizes = Lists.newArrayList();
    /**
     * Sizes of comments that are unattached in the reference function and that are neither author
     * comments nor title comments.
     */
    private List<Long> unattachedFilteredCommentSizes = Lists.newArrayList();
    /** Sizes of comments that are attached in the reference function. */
    private List<Long> attachedCommentSizes = Lists.newArrayList();
    

    /**
     * Creates a new instance.
     */
    public CommentSizeAnalysis() {
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
        System.out.println("=====> Start Node Size Analysis Results");

        System.out.println("------ Unattached Comments");
        unattachedCommentSizes.stream().forEach((area) -> System.out.println(area));
        
        System.out.println("------ Filtered Unattached Comments");
        unattachedFilteredCommentSizes.stream().forEach((area) -> System.out.println(area));
        
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
                
                if (editor.getAttachmentTarget(child) == null) {
                    // The comment is unattached
                    unattachedCommentSizes.add(area);
                    
                    if (authorCommentFilter.eligibleForAttachment(child)
                            && titleCommentFilter.eligibleForAttachment(child)) {
                        
                        unattachedFilteredCommentSizes.add(area);
                    }
                } else {
                    // The comment is attached
                    attachedCommentSizes.add(area);
                }
            }
            
            if (!child.getChildren().isEmpty()) {
                recursivelyProcess(child, editor);
            }
        }
        
    }

}
