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
package de.cau.cs.kieler.kiml.grana.ui.batch;

import java.util.Map;

/**
 * The class that contains the results of a batch job.
 * 
 * @author mri
 * @author uru 
 * @kieler.ignore (excluded from review process)
 * @param <T> the parameter type
 */
public class BatchJobResult<T> {

    /** the analysis job. */
    private IBatchJob<T> job;
    /** the analyses results. */
    private Map<String, Object> results;
    /** results of the execution time analysis. */
    private Map<String, Double> execTimeResults;

    /**
     * Constructs an AnalysisBatchJobResult using the AnalysisBatchJob and the
     * results the job computed.
     * 
     * @param theJob
     *            the job
     * @param theResults
     *            the results
     */
    public BatchJobResult(final IBatchJob<T> theJob,
            final Map<String, Object> theResults) {
        job = theJob;
        results = theResults;
    }

    /**
     * @param theJob
     *            the job
     * @param theResults
     *            the results
     * @param execResults
     *            execution time results
     */
    public BatchJobResult(final IBatchJob<T> theJob,
            final Map<String, Object> theResults, final Map<String, Double> execResults) {
        this(theJob, theResults);
        this.execTimeResults = execResults;
    }
    
    /**
     * Returns the job.
     * 
     * @return the job
     */
    public IBatchJob<T> getJob() {
        return job;
    }

    /**
     * Returns the analyses results performed by the job.
     * 
     * @return the results
     */
    public Map<String, Object> getResults() {
        return results;
    }
    
    /**
     * @return the execTimeResults
     */
    public Map<String, Double> getExecTimeResults() {
        return execTimeResults;
    }
}
