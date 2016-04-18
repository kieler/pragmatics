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
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.eclipse.elk.core.comments.CommentAttacher;
import org.eclipse.elk.core.klayoutdata.KLayoutData;
import org.eclipse.elk.graph.KNode;

import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;

import de.cau.cs.kieler.ptolemy.attachmenteval.editors.attachment.CommentAttachmentEditor;
import de.cau.cs.kieler.ptolemy.klighd.PtolemyProperties;
import de.cau.cs.kieler.ptolemy.klighd.transformation.comments.PtolemyTitleCommentFilter;

/**
 * Collects statistics on the font sizes used in comments.
 * 
 * @author cds
 */
public class CommentFontSizeAnalysis implements IAttachmentAnalysis {
    
    /** The title comment filter put to the test here. */
    private PtolemyTitleCommentFilter titleCommentFilter = null;
    /** Map from model file names to statistics on how often a given font size appears in a model. */
    private Map<String, TreeMap<Integer, Integer>> histograms = Maps.newTreeMap();
    /** Font sizes of title comments as determined by the title comment filter. */
    private Map<String, Integer> titleComments = Maps.newTreeMap();
    
    
    /**
     * Creates a new instance.
     */
    public CommentFontSizeAnalysis() {
        titleCommentFilter = createTitleCommentFilter();
    }
    
    public static PtolemyTitleCommentFilter createTitleCommentFilter() {
        Injector injector = Guice.createInjector();
        PtolemyTitleCommentFilter filter = injector.getInstance(PtolemyTitleCommentFilter.class);
        filter.decideBasedOnFontSizeOnly();
        
        return filter;
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // IAttachmentAnalysis
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void process(KNode model, String modelFilePath, CommentAttachmentEditor editor) {
        // Generate a font size histogram for this model
        TreeMap<Integer, Integer> histogram = createHistogram(model);
        histograms.put(modelFilePath, histogram);
        
        // Let the heuristic do its work
        Integer titleCommentFontSize = runFilter(model);
        if (titleCommentFontSize != null) {
            titleComments.put(modelFilePath, titleCommentFontSize);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void finish() {
        System.out.println("=====> Start Font Size Analysis Results");
        
        // Simply print the results to the console for each model
        for (String modelFile : histograms.keySet()) {
            System.out.println(modelFile);
            
            Map<Integer, Integer> histogram = histograms.get(modelFile);
            for (Integer fontSize : histogram.keySet()) {
                System.out.println("    " + fontSize + ": " + histogram.get(fontSize));
            }
        }
        
        
        System.out.println("------ Models with a single comment with the largest font size");
        System.out.println("       (largest font size, number of comments on top level, "
                + "whether the filter found a title comment, model file");
        
        for (String modelFile : histograms.keySet()) {
            Map.Entry<Integer, Integer> largestFontOccurrences = histograms.get(modelFile).lastEntry();
            
            // If there is a single comment with largest font size, print it out
            if (largestFontOccurrences != null && largestFontOccurrences.getValue() == 1) {
                // Count the number of comments in the model
                int comments = (int) histograms.get(modelFile).values().stream()
                        .collect(Collectors.summarizingInt((v) -> v)).getSum();
                
                System.out.println(largestFontOccurrences.getKey()
                        + " " + comments
                        + " " + (titleComments.containsKey(modelFile) ? "Yes" : "No ")
                        + " " + modelFile);
            }
        }
        
        
        System.out.println("------ Title comments found by PtolemyTitleCommentFilter");
        
        for (String modelFile : titleComments.keySet()) {
            System.out.println("    " + titleComments.get(modelFile) + ": " + modelFile);
        }

        System.out.println("<===== End Font Size Analysis Results");
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Analysis Methods
    
    /**
     * Creates a font size histogram for the given model.
     * 
     * @param model
     *            the model.
     * @return map from font sizes to the number of comments with the font size.
     */
    private TreeMap<Integer, Integer> createHistogram(final KNode model) {
        TreeMap<Integer, Integer> histogram = Maps.newTreeMap();
        
        for (KNode child : model.getChildren()) {
            if (CommentAttacher.isComment(child)) {
                // Find out the font size
                int fontSize = getFontSize(child);
                
                // Record the font size in the histogram
                if (histogram.containsKey(fontSize)) {
                    histogram.put(fontSize, histogram.get(fontSize) + 1);
                } else {
                    histogram.put(fontSize, 1);
                }
            }
        }
        
        return histogram;
    }
    
    /**
     * Runs the title comment filter on the given model.
     * 
     * @param model
     *            the model.
     * @return the font size of the comment determined to be the title comment, or {@code null} if
     *         no title comment was found.
     */
    private Integer runFilter(final KNode model) {
        titleCommentFilter.preprocess(model, false);
        KNode titleNode = titleCommentFilter.getChosenComment();
        titleCommentFilter.cleanup();
        
        return titleNode == null ? null : getFontSize(titleNode);
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Utilities
    
    private int getFontSize(final KNode comment) {
        KLayoutData layoutData = comment.getData(KLayoutData.class);
        return layoutData.getProperty(PtolemyProperties.COMMENT_FONT_SIZE);
    }

}
