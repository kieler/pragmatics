/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.batch;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.service.grana.AnalysisData;

/**
 * The class which contains the information about the results of an analysis
 * batch run.
 * 
 * @author mri
 */
public class BatchResult {

    /** the analysis which have been performed in the batch run. */
    private List<AnalysisData> analyses =
            new LinkedList<AnalysisData>();
    /** the results of the individual jobs. */
    private List<BatchJobResult<?>> results =
            new LinkedList<BatchJobResult<?>>();
    /** the failed jobs. */
    private List<Pair<BatchJob<?>, Exception>> failedJobs =
            new LinkedList<Pair<BatchJob<?>, Exception>>();

    /**
     * Constructs an AnalysisBatchResult.
     * 
     * @param theAnalyses
     *            the analyses performed in the batch run
     */
    public BatchResult(final List<AnalysisData> theAnalyses) {
        analyses = theAnalyses;
    }

    /**
     * Returns the analyses which have been performed in the batch run.
     * 
     * @return the analyses
     */
    public List<AnalysisData> getAnalyses() {
        return analyses;
    }

    /**
     * Returns the job results.
     * 
     * @return the job results
     */
    public List<BatchJobResult<?>> getJobResults() {
        return results;
    }

    /**
     * Appends a job result.
     * 
     * @param result
     *            the job result
     */
    public void appendJobResult(final BatchJobResult<?> result) {
        results.add(result);
    }

    /**
     * Returns pairs of the failed jobs and the exceptions that caused the
     * failure.
     * 
     * @return a list of pairs of failed jobs and exceptions
     */
    public List<Pair<BatchJob<?>, Exception>> getFailedJobs() {
        return failedJobs;
    }

    /**
     * Appends a failed job with the exception that caused the failure.
     * 
     * @param batchJob
     *            the job
     * @param e
     *            the exception
     */
    public void appendFailedJob(final BatchJob<?> batchJob, final Exception e) {
        failedJobs.add(new Pair<BatchJob<?>, Exception>(batchJob, e));
    }
}
