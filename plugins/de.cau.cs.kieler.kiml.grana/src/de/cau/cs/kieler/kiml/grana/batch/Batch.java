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

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.service.grana.AnalysisData;

/**
 * The class which represents an analysis batch.
 * 
 * @author mri
 */
public class Batch {

    /** the analyses. */
    private List<AnalysisData> analyses;
    /** the batch jobs. */
    private List<BatchJob<?>> batchJobs = new LinkedList<BatchJob<?>>();

    /**
     * Constructs a Batch.
     * 
     * @param theAnalyses
     *            the analyses which will be exectuted in the batch run
     */
    public Batch(final List<AnalysisData> theAnalyses) {
        analyses = theAnalyses;
    }

    /**
     * Appends a batch job to the batch.
     * 
     * @param batchJob
     *            the batch job
     */
    public void appendJob(final BatchJob<?> batchJob) {
        batchJobs.add(batchJob);
    }

    /**
     * Executes the batch which consists of the execution of all batch jobs.
     * 
     * @param monitor
     *            the monitor
     * @return the batch result
     */
    public BatchResult execute(final IKielerProgressMonitor monitor) {
        monitor.begin("Executing analysis batch", batchJobs.size());
        BatchResult batchResult = new BatchResult(analyses);
        for (BatchJob<?> batchJob : batchJobs) {
            if (monitor.isCanceled()) {
                return null;
            }
            try {
                BatchJobResult<?> batchJobResult =
                        batchJob.execute(analyses, monitor.subTask(1));
                batchResult.appendJobResult(batchJobResult);
            } catch (Exception e) {
                batchResult.appendFailedJob(batchJob, e);
            }
        }
        monitor.done();
        return batchResult;
    }
}
