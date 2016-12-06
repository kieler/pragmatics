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

import java.util.Collections;
import java.util.Map;

import org.eclipse.elk.graph.properties.MapPropertyHolder;

import com.google.common.collect.LinkedListMultimap;

/**
 * The class that contains the results of a batch job. Each form of {@link BatchJob}s has its own
 * {@link BatchJobResult} class.
 * 
 * @author mri
 * @author uru
 * @kieler.ignore (excluded from review process)
 */
public abstract class BatchJobResult {

    /** the analysis job. */
    private IBatchJob<?> job;
    
    /** results for the basic analyses as returned by {@link Batch#getAnalyses()}. */
    private Map<String, Object> results;

    /**
     * Constructs an BatchJobResult using the IBatchJob and the results the job computed.
     * 
     * @param theJob
     *            the job
     */
    public BatchJobResult(final IBatchJob<?> theJob) {
        this(theJob, Collections.emptyMap());
    }

    /**
     * Constructs an BatchJobResult using the IBatchJob and the results the job computed.
     * 
     * @param theJob
     *            the job
     * @param results
     *            the results for the basic analyses.
     */
    public BatchJobResult(final IBatchJob<?> theJob, final Map<String, Object> results) {
        this.job = theJob;
        this.results = results;
    }
    
    /**
     * Returns the job.
     * 
     * @return the job
     */
    public IBatchJob<?> getJob() {
        return job;
    }
    
    /**
     * @return the results
     */
    public Map<String, Object> getResults() {
        return results;
    }

    // ------------------------------------------------------------------------------
    // - - - - - - - - - - - - - - - - - - RANGE - - - - - - - - - - - - - - - - - -
    // ------------------------------------------------------------------------------
    
    /**
     * Indicates that the job was not executed as the batch run was cancelled (e.g. by the user).
     */
    public static class Canceled extends BatchJobResult {
        public Canceled(final IBatchJob<?> job) { // SUPPRESS CHECKSTYLE Javadoc
            super(job);
        }
    }

    // ------------------------------------------------------------------------------
    // - - - - - - - - - - - - - - - - - - RANGE - - - - - - - - - - - - - - - - - -
    // ------------------------------------------------------------------------------

    /**
     * Indicates that the associated job failed for some reason. 
     */
    public static class Failed extends BatchJobResult {
        private Throwable t;
        public Failed(final IBatchJob<?> job, final Throwable t) { // SUPPRESS CHECKSTYLE Javadoc
            super(job);
            this.t = t;
        }
        /**
         * @return the reason the job failed.
         */
        public Throwable getThrowable() {
            return t;
        }
    }

    // ------------------------------------------------------------------------------
    // - - - - - - - - - - - - - - - - - - RANGE - - - - - - - - - - - - - - - - - -
    // ------------------------------------------------------------------------------
    
    /**
     * Common superclass for successful jobs.
     */
    public abstract static class Success extends BatchJobResult {
        public Success(final IBatchJob<?> job, final Map<String, Object> results) { // SUPPRESS CHECKSTYLE Javadoc
            super(job, results);
        }
    }

    // ------------------------------------------------------------------------------
    // - - - - - - - - - - - - - - - - - - RANGE - - - - - - - - - - - - - - - - - -
    // ------------------------------------------------------------------------------

    /**
     * Holds the results of a successfully executed {@link BatchJob.Simple}.
     */
    public static class Simple extends Success {
        public Simple(final IBatchJob<?> job, final Map<String, Object> results) { // SUPPRESS CHECKSTYLE Javadoc
            super(job, results);
        }
    }

    // ------------------------------------------------------------------------------
    // - - - - - - - - - - - - - - - - - - RANGE - - - - - - - - - - - - - - - - - -
    // ------------------------------------------------------------------------------
    
    /**
     * Holds the results of a successfully executed {@link BatchJob.Range}.
     */
    public static class Range extends Success {
        /** the results of a range analysis, properly ordered according to
         *  they execution sequence. */
        private LinkedListMultimap<MapPropertyHolder, Map<String, Object>> rangeResults;

        // SUPPRESS CHECKSTYLE NEXT 2 Javadoc
        public Range(final IBatchJob<?> job, final Map<String, Object> results,
                final LinkedListMultimap<MapPropertyHolder, Map<String, Object>> rangeResults) { 
            super(job, results);
            this.rangeResults = rangeResults;
        }
       
        /**
         * @return the rangeResults
         */
        public LinkedListMultimap<MapPropertyHolder, Map<String, Object>> getRangeResults() {
            return rangeResults;
        }
    }

}
