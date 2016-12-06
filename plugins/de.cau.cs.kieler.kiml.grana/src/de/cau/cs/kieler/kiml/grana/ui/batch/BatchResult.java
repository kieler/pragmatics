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

import java.util.List;

import com.google.common.collect.Lists;

/**
 * The class which contains the information about the results of an analysis
 * batch run.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class BatchResult {

    private Batch batch;
    
    /** the results of the individual jobs. */
    private List<BatchJobResult> results = Lists.newLinkedList();

    /**
     * Constructs an AnalysisBatchResult.
     * 
     * @param batch
     *            the batch execution this result originates from
     * @param results
     *            the results of the batch execution
     */
    public BatchResult(final Batch batch,
            final List<BatchJobResult> results) {
        this.batch = batch;
        this.results = results;
    }

    /**
     * @return the batch execution this result originates from
     */
    public Batch getBatch() {
        return batch;
    }
    
    /**
     * Returns the job results.
     * 
     * @return the job results
     */
    public List<BatchJobResult> getJobResults() {
        return results;
    }
    
    /**
     * Appends a job result.
     * 
     * @param result
     *            the job result
     */
    public void appendJobResult(final BatchJobResult result) {
        results.add(result);
    }
}
