/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.ui.batch;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.elk.core.util.Pair;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.kiml.grana.AnalysisData;

/**
 * The class which contains the information about the results of an analysis
 * batch run.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class BatchResult {

    private Batch batch;
    
    /** the analysis which have been performed in the batch run. */
    private List<AnalysisData> analyses =
            new LinkedList<AnalysisData>();
    
    /**
     * List of phases that occurred during the execution time analysis. Note that not the phases of
     * different layout runs for different graphs may differ.
     */
    private Set<String> executionTimePhases = Sets.newHashSet();
    /** the results of the individual jobs. */
    private List<BatchJobResult<?>> results = Lists.newLinkedList();
    /** the failed jobs. */
    private List<Pair<IBatchJob<?>, Throwable>> failedJobs = Lists.newLinkedList();

    
    /**
     * Constructs an AnalysisBatchResult.
     * 
     * @param batch
     *            the batch execution this result originates from
     * @param theAnalyses
     *            the analyses performed in the batch run
     */
    public BatchResult(final Batch batch, final List<AnalysisData> theAnalyses) {
        this.batch = batch;
        this.analyses = theAnalyses;
    }

    /**
     * @return the batch execution this result originates from
     */
    public Batch getBatch() {
        return batch;
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
     * @return the executionTimePhases
     */
    public Set<String> getExecutionTimePhases() {
        return executionTimePhases;
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
    public List<Pair<IBatchJob<?>, Throwable>> getFailedJobs() {
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
    public void appendFailedJob(final IBatchJob<?> batchJob, final Throwable e) {
        failedJobs.add(new Pair<IBatchJob<?>, Throwable>(batchJob, e));
    }
}
