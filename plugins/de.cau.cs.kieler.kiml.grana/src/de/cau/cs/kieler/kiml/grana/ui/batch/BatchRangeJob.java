/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.ui.batch;

import java.util.List;

import org.eclipse.elk.core.LayoutConfigurator;
import org.eclipse.elk.core.service.DiagramLayoutEngine;
import org.eclipse.elk.core.service.DiagramLayoutEngine.Parameters;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.KGraphElement;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisData;
import de.cau.cs.kieler.kiml.grana.AnalysisService;

/**
 * The class which represents an analysis batch job.
 * 
 * @author mri
 * @author uru
 * @kieler.ignore (excluded from review process)
 * @param <T> the type of the parameter
 */
public class BatchRangeJob<T> implements IBatchJob<T> {

    /** the parameter for this batch job. */
    private T parameter;
    /** the KGraph provider for this batch run. */
    private IKGraphProvider<T> kgraphProvider;
    /** the containing batch for reference. */
    private Batch batch;
    
    /**
     * Constructs an AnalysisBatchJob.
     * 
     * @param batch
     *            the surrounding batch instance
     * @param param
     *            the parameter
     * @param provider
     *            the KGraph provider
     */
    public BatchRangeJob(final Batch batch, final T param, final IKGraphProvider<T> provider) {
        this.batch = batch;
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
            final IElkProgressMonitor monitor) throws Exception {
        monitor.begin("Executing analysis batch job: " + parameter, WORK);

        // execute regular analyses as requested ...
        KNode graph = kgraphProvider.getKGraph(parameter, monitor.subTask(WORK_KGRAPH));
        AnalysisContext context = AnalysisService.getInstance().analyze(graph, analyses,
                monitor.subTask(WORK_ANALYSIS));
        
        List<Object> rangeResults = Lists.newArrayList();
        // now layout with the range or whatever it is layout option
        LayoutConfigurator lc = new LayoutConfigurator();
        for (Number n : batch.getRangeValues()) {
            Object value = batch.getRangeOption().parseValue(n.toString());
            if (value == null) {
                throw new IllegalArgumentException("Value " + n
                        + " is not valid for layout option " + batch.getRangeOption());
            }
            lc.configure(KGraphElement.class).setProperty(batch.getRangeOption(), value);
            Parameters params = new Parameters();
            params.addLayoutRun(lc);
            DiagramLayoutEngine.invokeLayout(null, graph, params);

            AnalysisContext tmp = new AnalysisContext();
            AnalysisService.getInstance().analyze(graph, batch.getRangeAnalysis(),
                    monitor.subTask(1), tmp);

            Object result = tmp.getResult(batch.getRangeAnalysis().getId());
            
            rangeResults.add(result);
        }

        // exectime not supported for range jobs

        BatchJobResult<T> batchJobResult = new BatchJobResult<T>(this, context.getResults(), null);
        batchJobResult.setRangeResults(rangeResults);
        monitor.done();

        return batchJobResult;
    }

}
