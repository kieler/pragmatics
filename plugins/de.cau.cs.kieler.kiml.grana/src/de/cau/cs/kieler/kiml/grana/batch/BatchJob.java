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

import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.AnalysisService;
import de.cau.cs.kieler.kiml.service.grana.AnalysisData;

/**
 * The class which represents an analysis batch job.
 * 
 * @author mri
 * 
 * @param <T>
 *            the type of the parameter
 */
public class BatchJob<T> {

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
     * The alternative constructor for an AnalysisBatchJob using a KGraph instance instead of a
     * KGraph provider.
     * 
     * @param param
     *            the parameter
     * @param graph
     *            the KGraph instance
     */
    public BatchJob(final T param, final KNode graph) {
        parameter = param;
        kgraphProvider = new StaticProvider(graph);
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
     * Executes the job which consists of retrieving a KGraph instance through the KGraph provider
     * and performing the given analyses on it.
     * 
     * @param analyses
     *            the analyses
     * @param monitor
     *            the progress monitor
     * @return the job result
     * @throws Exception
     *             any kind of exception
     */
    public BatchJobResult<T> execute(final List<AnalysisData> analyses,
            final IKielerProgressMonitor monitor) throws Exception {
        monitor.begin("Executing analysis batch job: " + parameter, WORK);
        KNode graph = kgraphProvider.getKGraph(parameter, monitor.subTask(WORK_KGRAPH));
        Map<String, Object> results = AnalysisService.getInstance().analyze(graph, analyses,
                monitor.subTask(WORK_ANALYSIS));
        BatchJobResult<T> batchJobResult = new BatchJobResult<T>(this, results);
        monitor.done();
        return batchJobResult;
    }

    /**
     * A KGraph provider which returns a given KGraph instance.
     * 
     * @author mri
     */
    private class StaticProvider implements IKGraphProvider<T> {

        /** the static kgraph instance. */
        private KNode graph;

        /**
         * The constructor for the StaticProvider.
         * 
         * @param parent
         *            the parent node of the KGraph instance
         */
        public StaticProvider(final KNode parent) {
            graph = parent;
        }

        /**
         * {@inheritDoc}
         */
        public KNode getKGraph(final T param, final IKielerProgressMonitor monitor) {
            return graph;
        }
    }
}
