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
import java.util.SortedSet;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.grana.AnalysisData;

/**
 * The class which represents an analysis batch.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class Batch {

    /** the analyses. */
    private List<AnalysisData> analyses;
    /** the batch jobs. */
    private List<IBatchJob<?>> batchJobs = new LinkedList<IBatchJob<?>>();
    
    
    private AnalysisData rangeAnalysis;
    private Integer rangeAnalysisComponent;
    private LayoutOptionData rangeOption;
    private SortedSet<? extends Number> rangeValues;
    
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
    public void appendJob(final IBatchJob<?> batchJob) {
        batchJobs.add(batchJob);
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
     * Executes the batch which consists of the execution of all batch jobs.
     * 
     * @param monitor
     *            the monitor
     * @return the batch result
     */
    public BatchResult execute(final IKielerProgressMonitor monitor) {
        monitor.begin("Executing analysis batch", batchJobs.size());
        BatchResult batchResult = new BatchResult(this, analyses);
        for (IBatchJob<?> batchJob : batchJobs) {
            if (monitor.isCanceled()) {
                return null;
            }
            try {
                BatchJobResult<?> batchJobResult =
                        batchJob.execute(analyses, monitor.subTask(1));
                batchResult.appendJobResult(batchJobResult);
                if (batchJobResult.getExecTimeResults() != null) {
                    batchResult.getExecutionTimePhases().addAll(
                            batchJobResult.getExecTimeResults().keySet());
                }
            } catch (Throwable e) {
                batchResult.appendFailedJob(batchJob, e);
            }
        }
        monitor.done();
        return batchResult;
    }
}
