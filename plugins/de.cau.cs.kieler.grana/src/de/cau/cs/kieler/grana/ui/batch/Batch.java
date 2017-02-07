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
package de.cau.cs.kieler.grana.ui.batch;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.properties.MapPropertyHolder;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.grana.AnalysisData;

/**
 * The class which represents an analysis batch.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public abstract class Batch {

    /** textually specified batches may have a name. */
    private String name = null;
    /** the batch jobs. */
    private List<IBatchJob<?>> batchJobs = new LinkedList<IBatchJob<?>>();
    /** whether to execute the contained jobs in parallel. */
    private boolean parallel = false;
    
    /** the basic analyses, performed by each form of batch analysis. */
    protected List<AnalysisData> analyses; // SUPPRESS CHECKSTYLE VisibilityModifier
    
    /**
     * Constructs a Batch.
     * @param name
     *            a name that will be displayed during execution
     */
    public Batch(final String name) {
        this.name = name;
    }

    /**
     * @param parallel the parallel to set
     */
    public void setParallel(final boolean parallel) {
        this.parallel = parallel;
    }
    
    /**
     * Appends a batch job to the batch.
     * 
     * @param batchJob
     *            the batch job
     */
    public void appendJob(final IBatchJob<?> batchJob) {
        batchJob.setBatch(this);
        batchJobs.add(batchJob);
    }
    
    /**
     * @return the analyses
     */
    public List<AnalysisData> getAnalyses() {
        return analyses;
    }
    
    /**
     * Executes the batch which consists of the execution of all batch jobs.
     * 
     * @param monitor
     *            the monitor
     * @return the batch result
     */
    public BatchResult execute(final IElkProgressMonitor monitor) {
        int jobCnt = batchJobs.size();
        String message = "Executing analysis batch " + (name != null ? name + " " : "") + "("
                + jobCnt + " jobs).";
        monitor.begin(message, jobCnt);

        Stream<IBatchJob<?>> jobStream = batchJobs.stream();
        if (parallel) {
            jobStream = jobStream.parallel();
        }
        List<BatchJobResult> results = jobStream.map(job -> {
            if (monitor.isCanceled()) {
                return  new BatchJobResult.Canceled(job);
            }
            
            try {
                // TODO pass a progress monitor ... if it's synchronized ...
                BatchJobResult result = job.execute(analyses, new BasicProgressMonitor());
                synchronized (Batch.this) {
                    monitor.worked(1);
                }
                return result;
            } catch (Throwable t) {
                return new BatchJobResult.Failed(job, t);
            }
            
        }).collect(Collectors.toList()); 
        
        BatchResult batchResult = new BatchResult(this, results);
        
        monitor.done();
        return batchResult;
    }
    
    // -------------------------------------------------------------------------------
    // - - - - - - - - - - - - - - - - - - SIMPLE - - - - - - - - - - - - - - - - - -
    // -------------------------------------------------------------------------------
    
    /**
     * Represents a batch execution in its simplest form. A set of analyses is executed for a set of
     * graphs.
     */
    public static class Simple extends Batch {
        
        /**
         * Constructs a Batch.
         * 
         * @param theAnalyses
         *            the analyses which will be exectuted in the batch run
         */
        public Simple(final List<AnalysisData> theAnalyses) {
            this(null, theAnalyses);
        }
        
        /**
         * Constructs a Batch.
         * 
         * @param name
         *            a name representing the batch execution
         * @param theAnalyses
         *            the analyses which will be exectuted in the batch run
         */
        public Simple(final String name, final List<AnalysisData> theAnalyses) {
            super(name);
            analyses = theAnalyses;
        }
        
    }
    
    // ------------------------------------------------------------------------------
    // - - - - - - - - - - - - - - - - - - RANGE - - - - - - - - - - - - - - - - - -
    // ------------------------------------------------------------------------------

    /**
     * As opposed to {@link Batch.Simple}, the range batch execution 
     * analyses each graph to be processed multiple times with different layout configurations. 
     * For each layout configuration a set of analyses can be performed.
     * 
     * Note that an initial (regular) analysis is performed (equally to {@link Batch.Simple}). 
     */
    public static class Range extends Batch {
        
        // either a single analysis (can be written to csv)        
        private AnalysisData rangeAnalysis;
        private Integer rangeAnalysisComponent;
        
        // or multiple analyses, but requires json export
        private List<AnalysisData> rangeAnalyses;

        private LayoutOptionData rangeOption;
        private SortedSet<? extends Number> rangeValues;
        
        private List<MapPropertyHolder> rangeConfigurations;
        
        // SUPPRESS CHECKSTYLE NEXT 10 Javadoc
        public Range() {
            this(null);
        }
        
        public Range(final String name) {
            this(name, Lists.newArrayList());
        }

        public Range(final String name, final List<AnalysisData> basicAnalyses) {
            super(name);
            this.analyses = basicAnalyses;
        }
        
        /**
         * @return the rangeAnalysis
         */
        public AnalysisData getRangeAnalysis() {
            return rangeAnalysis;
        }

        /**
         * @param rangeAnalysis the rangeAnalysis to set
         */
        public void setRangeAnalysis(final AnalysisData rangeAnalysis) {
            this.rangeAnalysis = rangeAnalysis;
        }

        /**
         * @return the rangeAnalysisComponent
         */
        public Integer getRangeAnalysisComponent() {
            return rangeAnalysisComponent;
        }

        /**
         * @param rangeAnalysisComponent the rangeAnalysisComponent to set
         */
        public void setRangeAnalysisComponent(final Integer rangeAnalysisComponent) {
            this.rangeAnalysisComponent = rangeAnalysisComponent;
        }
        
        /**
         * @return the rangeAnalyses
         */
        public List<AnalysisData> getRangeAnalyses() {
            return rangeAnalyses;
        }

        /**
         * @return the rangeOption
         */
        public LayoutOptionData getRangeOption() {
            return rangeOption;
        }

        /**
         * @param rangeOption the rangeOption to set
         */
        public void setRangeOption(final LayoutOptionData rangeOption) {
            this.rangeOption = rangeOption;
        }

        /**
         * @return the rangeValues
         */
        public SortedSet<? extends Number> getRangeValues() {
            return rangeValues;
        }

        /**
         * @param rangeValues the rangeValues to set
         */
        public void setRangeValues(final SortedSet<? extends Number> rangeValues) {
            this.rangeValues = rangeValues;
        }
        
        /**
         * @param rangeAnalyses the rangeAnalyses to set
         */
        public void setRangeAnalyses(final List<AnalysisData> rangeAnalyses) {
            this.rangeAnalyses = rangeAnalyses;
        }
        
        /**
         * @return the rangeConfigurations
         */
        public List<MapPropertyHolder> getRangeConfigurations() {
            return rangeConfigurations; 
        }
        
        /**
         * @param rangeConfigurations the rangeConfigurations to set
         */
        public void setRangeConfigurations(final List<MapPropertyHolder> rangeConfigurations) {
            this.rangeConfigurations = rangeConfigurations;
        }
    }
    
}
