/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.ptolemy.attachmenteval;

import java.io.PrintStream;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

/**
 * Knows how to compare to {@link AttachmentData} instances. Can output the evaluation data onto
 * the console as CSV-formatted lines.
 * 
 * @author cds
 */
public final class DataEvaluator {
    
    private static final class EvaluationData {
        /** The number of annotations in the model. */
        private int annotations;
        /** The number of correct attachments. */
        private int correctAttachments;
        /** The number of annotations that should be attached to something, but are not. */
        private int lostAttachments;
        /** The number of annotations that should not be attached to something, but are. */
        private int foundAttachments;
        /**
         * The number of annotations that are attached to something, but should be attached
         * to something else.
         */
        private int changedAttachments;
    }
    
    
    /** The base attachment data the other will be compared against. */
    private AttachmentData baseData = null;
    /** The attachment data to compare to the base data. */
    private AttachmentData otherData = null;
    /** Whether annotations unattached in the base data are included in the evaluation. */
    private boolean includeUnattached = false;
    /** Evaluation results. */
    private Map<String, EvaluationData> results = Maps.newTreeMap();
    
    
    /////////////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS AND FACTORY METHODS
    
    /**
     * Creates a new instance.
     */
    private DataEvaluator() {
        
    }
    
    
    /**
     * Creates a new instance for the given attachment data and immediately triggers the evaluation. The
     * results will only be meaningful if the base attachment data have valid annotation counts.
     * 
     * @param base base data the other will be evaluated against.
     * @param other data to be compared to the base data.
     * @param includeUnattached if {@code true}, annotations that are not attached in the base data are
     *                          included in the evaluation. Otherwise, the statistics will only include
     *                          annotations attached in the base data.
     * @return evaluator instance that has already finished evaluating the two data instances.
     * @throws IllegalArgumentException if the two instances don't point to the same models folder.
     */
    public static DataEvaluator createFor(final AttachmentData base, final AttachmentData other,
            final boolean includeUnattached) {
        
        // Check that the two instances use models from the same folder
        if (!base.getModelsFolder().equals(other.getModelsFolder())) {
            throw new IllegalArgumentException(
                    "The two instances must be based on the same model folder.");
        }
        
        DataEvaluator evaluator = new DataEvaluator();
        evaluator.baseData = base;
        evaluator.otherData = other;
        evaluator.includeUnattached = includeUnattached;
        evaluator.evaluate();
        
        return evaluator;
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC INTERFACE
    
    /**
     * Prints the evaluation results in CSV format to the given stream.
     * 
     * @param stream output stream to print the data to.
     */
    public void printCSV(final PrintStream stream) {
        // Heading
        stream.printf("%s,%s,%s,%s,%s,%s%n",
                "File name",
                "Annotations",
                "Correct",
                "Changed",
                "Lost",
                "Found"
        );
        
        // Each file entry
        for (Entry<String, EvaluationData> entry : results.entrySet()) {
            EvaluationData data = entry.getValue();
            
            // Skip entries with zero annotations
            if (!includeUnattached && data.annotations == 0) {
                continue;
            }
            
            stream.printf("%s,%d,%d,%d,%d,%d%n",
                    entry.getKey(),
                    data.annotations,
                    data.correctAttachments,
                    data.changedAttachments,
                    data.lostAttachments,
                    data.foundAttachments);
        }
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////
    // EVALUATION
    
    /**
     * Starts the evaluation of the attachment data instances.
     */
    private void evaluate() {
        // First, find the set of files included in both attachment data
        SetView<String> evaluationFiles = Sets.intersection(
                baseData.getSelectedFiles(), otherData.getSelectedFiles());
        
        // For each selected file, compare the results
        for (String file : evaluationFiles) {
            doEvaluate(file);
        }
    }
    
    /**
     * Does the evaluation for the given model file.
     * 
     * @param file the file.
     */
    private void doEvaluate(final String file) {
        EvaluationData data = new EvaluationData();
        results.put(file, data);
        
        Map<String, String> baseAttachments = baseData.getAssociations().get(file);
        if (baseAttachments == null) {
            baseAttachments = Maps.newHashMap();
        }
        Map<String, String> otherAttachments = otherData.getAssociations().get(file);
        if (otherAttachments == null) {
            otherAttachments = Maps.newHashMap();
        }
        
        // Find out how many annotations are unattached
//        int unattached = baseData.getAnnotationCounts().get(file) - baseAttachments.size();
        data.annotations = baseAttachments.size();
        
        // Iterate over the attachments found in the base attachment data; outcome is the number of
        // lost, changed, and correct attachments, not including annotations that are not attached
        // to anything
        for (Entry<String, String> baseAttachmentEntry : baseAttachments.entrySet()) {
            String otherAttachment = otherAttachments.get(baseAttachmentEntry.getKey());
            
            if (otherAttachment == null) {
                data.lostAttachments++;
            } else if (otherAttachment.equals(baseAttachmentEntry.getValue())) {
                data.correctAttachments++;
            } else {
                data.changedAttachments++;
            }
        }
        
        // Include annotations unattached in the base data?
        if (includeUnattached) {
            // Annotations count must be reset
            data.annotations = baseData.getAnnotationCounts().get(file);            
            
            // Found attachments
            for (Entry<String, String> otherAttachmentEntry : otherAttachments.entrySet()) {
                String baseAttachment = baseAttachments.get(otherAttachmentEntry.getKey());
                
                if (baseAttachment == null) {
                    data.foundAttachments++;
                }
            }
            
            // Correct attachments need to be corrected for the annotations unattached in both data
            data.correctAttachments = data.annotations - data.foundAttachments - data.lostAttachments
                    - data.changedAttachments;
        }
    }
    
}
