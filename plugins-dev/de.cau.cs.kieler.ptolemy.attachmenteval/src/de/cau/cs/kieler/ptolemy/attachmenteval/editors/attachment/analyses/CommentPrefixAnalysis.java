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

import java.util.Map;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.comments.CommentAttacher;
import de.cau.cs.kieler.kiml.comments.TextPrefixFilter;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.ptolemy.attachmenteval.editors.attachment.CommentAttachmentEditor;
import de.cau.cs.kieler.ptolemy.klighd.PtolemyProperties;

/**
 * Displays the results of the text prefix heuristic.
 * 
 * @author cds
 */
public class CommentPrefixAnalysis implements IAttachmentAnalysis {
    
    /** The text prefix filter put to the test here. */
    private TextPrefixFilter textPrefixFilter = null;
    /**
     * Map from model file names to comment texts and whether they are considerd author comments or
     * not.
     */
    private Map<String, Map<String, Boolean>> modelComments = Maps.newTreeMap();
    /** Number of comments that are filtered out. */
    private int filtered = 0;
    /** Number of comments that are filtered out, but are attached in the reference attachment. */
    private int falsePositives = 0;
    
    
    /**
     * Creates a new instance.
     */
    public CommentPrefixAnalysis() {
        textPrefixFilter = createTextPrefixFilter();
    }
    
    public static TextPrefixFilter createTextPrefixFilter() {
        return new TextPrefixFilter()
            .withCommentTextProvider((comment) ->
                comment.getData(KLayoutData.class).getProperty(PtolemyProperties.COMMENT_TEXT))
            .addPrefix("Author")  // Also matches "Authors"
            .addPrefix("Demo created by")
            .addPrefix("This model ")
            .addPrefix("This submodel ")
            .addPrefix("This example ")
            .addPrefix("This demo ")
            .addPrefix("Model of ");
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // IAttachmentAnalysis
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void process(KNode model, String modelFilePath, CommentAttachmentEditor editor) {
        textPrefixFilter.preprocess(model, true);
        
        Map<String, Boolean> comments = Maps.newTreeMap();
        recursivelyFillCommentTextMap(model, comments, editor);
        modelComments.put(modelFilePath, comments);
        
        textPrefixFilter.cleanup();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void finish() {
        System.out.println("=====> Start Author Comment Analysis Results");
        
        // Simply print the results to the console for each model
        for (String modelFile : modelComments.keySet()) {
            System.out.println(modelFile);
            
            Map<String, Boolean> comments = modelComments.get(modelFile);
            for (String text : comments.keySet()) {
                if (comments.get(text)) {
                    System.out.println("    " + "X " + text);
                } else {
                    System.out.println("    " + "_ " + text);
                }
            }
        }

        System.out.println("------ Statistics");
        System.out.println("  Filtered: " + filtered);
        System.out.println("  False positives: " + falsePositives);
        
        System.out.println("<===== End Author Comment Analysis Results");
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Analysis
    
    private void recursivelyFillCommentTextMap(final KNode model,
            final Map<String, Boolean> modelComments, CommentAttachmentEditor editor) {
        
        for (KNode child : model.getChildren()) {
            if (CommentAttacher.isComment(child)) {
                // A comment is an author comment if the filter wants it to not be attached to anything
                boolean filterOut = !textPrefixFilter.eligibleForAttachment(child);
                modelComments.put(getCommentText(child), filterOut);
                
                if (filterOut) {
                    filtered++;
                    
                    if (editor.getAttachmentTarget(child) != null) {
                        falsePositives++;
                    }
                }
            }
            
            if (!child.getChildren().isEmpty()) {
                recursivelyFillCommentTextMap(child, modelComments, editor);
            }
        }
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
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
        
        // We shorten the text to 50 characters if it's longer
        int cutoffLength = Math.min(50, text.length());
        
        // If there's a line break within the first 50 characters, shorten to that
        int newlineIndex = text.indexOf('\n');
        if (newlineIndex >= 0 && newlineIndex < cutoffLength) {
            cutoffLength = newlineIndex;
        }
        
        return text.substring(0, cutoffLength) + (cutoffLength < text.length() ? "..." : "");
    }

}
