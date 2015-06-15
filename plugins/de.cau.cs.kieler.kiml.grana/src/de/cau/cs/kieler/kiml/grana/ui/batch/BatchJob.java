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

import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisData;
import de.cau.cs.kieler.kiml.grana.AnalysisService;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;

/**
 * The class which represents an analysis batch job.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 * @param <T> the type of the parameter
 */
public class BatchJob<T> implements IBatchJob<T> {

    /** the parameter for this batch job. */
    private T parameter;
    /** the KGraph provider for this batch run. */
    private IKGraphProvider<T> kgraphProvider;

    /**
     * Constructs an AnalysisBatchJob.
     * 
     * @param param
     *            the parameter
     * @param provider
     *            the KGraph provider
     */
    public BatchJob(final T param, final IKGraphProvider<T> provider) {
        parameter = param;
        kgraphProvider = provider;
    }

    /**
     * Returns the parameter of the job.
     * 
     * @return the parameter
     */
    public T getParameter() {
        return parameter;
    }

    private static final int WORK = 3;
    private static final int WORK_KGRAPH = 1;
    private static final int WORK_ANALYSIS = 2;

    /**
     * {@inheritDoc}
     */
    public BatchJobResult<T> execute(final List<AnalysisData> analyses,
            final IKielerProgressMonitor monitor) throws Exception {
        monitor.begin("Executing analysis batch job: " + parameter, WORK);
        KNode graph = kgraphProvider.getKGraph(parameter, monitor.subTask(WORK_KGRAPH));
        AnalysisContext context = AnalysisService.getInstance().analyze(graph, analyses,
                monitor.subTask(WORK_ANALYSIS));
        Map<String, Double> execResults =
                graph.getData(KLayoutData.class).getProperty(BatchHandler.EXECUTION_TIME_RESULTS);
        BatchJobResult<T> batchJobResult =
                new BatchJobResult<T>(this, context.getResults(), execResults);
        monitor.done();
        return batchJobResult;
    }
}
