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

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Set;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.core.util.Pair;
import org.eclipse.elk.graph.properties.MapPropertyHolder;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.grana.AnalysisData;
import de.cau.cs.kieler.grana.ui.batch.BatchJobResult.Range;
import de.cau.cs.kieler.grana.ui.batch.BatchJobResult.Simple;
import de.cau.cs.kieler.grana.ui.visualization.Visualization;
import de.cau.cs.kieler.grana.ui.visualization.VisualizationService;

/**
 * A batch result serializer which saves the information as CSV.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class CSVResultSerializer implements IBatchResultSerializer {

    /** the visualization type used in the serialization. */
    private static final String VISUALIZATION_TYPE = "csv";

    /**
     * {@inheritDoc}
     */
    public void serialize(final OutputStream outputStream,
            final BatchResult batchResult, final IElkProgressMonitor monitor)
            throws Exception {
        monitor.begin("Serialize batch result as CSV", 1);
        // create a stream writer
        OutputStreamWriter writer =
                new OutputStreamWriter(outputStream, "UTF-8");
        // write the header
        writer.write("File");
        
        Batch batch = batchResult.getBatch();
        if (batch instanceof Batch.Simple) {
            handleSimpleBatch((Batch.Simple) batch, batchResult, writer);
        } else if (batch instanceof Batch.Range) {
            handleRangeBatch((Batch.Range) batch, batchResult, writer);
        }
        
        writer.close();
        monitor.done();
    }

    private void handleSimpleBatch(final Batch.Simple batch, final BatchResult batchResult,
            final OutputStreamWriter writer) throws IOException {

        // some pre-processing in case execution times were measured
        Iterable<BatchJobResult.Simple> simpleSuccesses =
                FluentIterable.from(batchResult.getJobResults()).filter(Simple.class);
        
        // different layout executions can use different processors, thus we have to find the 
        // superset of the processors to be consistent in the csv file
        Set<String> proccessorSuperSet = Sets.newLinkedHashSet();
        Map<BatchJobResult.Simple, Map<String, Double>> executionTimeResults = Maps.newHashMap();
        for (BatchJobResult.Simple r : simpleSuccesses) {
            Map<String, Double> times = Maps.newHashMap();
            executionTimeResults.put(r, times);
            if (r.getFastestExection() != null) {
                IElkProgressMonitor pm = r.getFastestExection();
                proccessorSuperSet.add(pm.getTaskName());
                times.put(pm.getTaskName(), pm.getExecutionTime());
                for (IElkProgressMonitor subPm : pm.getSubMonitors()) {
                    proccessorSuperSet.add(subPm.getTaskName());
                    times.put(subPm.getTaskName(), subPm.getExecutionTime());
                }
            }
        }
        
        // header 
        for (AnalysisData analysis : batch.getAnalyses()) {
            if (analysis.getComponents().size() > 0) {
                for (Pair<String, String> component : analysis.getComponents()) {
                    writer.write(";" + analysis.getName() + " ("
                            + component.getFirst() + ")");
                }
            } else {
                writer.write(";" + analysis.getName());
            }
        }
        
        // exectime header
        for (String processor : proccessorSuperSet) {
            writer.write(";" + processor); 
        }
        
        writer.write("\n");
        
        // write the job results
        for (BatchJobResult.Simple jobResult : simpleSuccesses) {
            writer.write(jobResult.getJob().getParameter().toString());
            Map<String, Object> results = jobResult.getResults();
            // basic analyses
            for (AnalysisData analysis : batch.getAnalyses()) {
                Object result = results.get(analysis.getId());
                Visualization visualization =
                        VisualizationService.getInstance().getVisualization(
                                VISUALIZATION_TYPE, result);
                String s = visualization.get(analysis, result);
                writer.write(";" + s);
            }
            
            Map<String, Double> times = executionTimeResults.get(jobResult);
            for (String processor : proccessorSuperSet) {
                double time = times.getOrDefault(processor, -1d);
                writer.write(";" + time);
            }
            
            writer.write("\n");
        }
    }
    
    private void handleRangeBatch(final Batch.Range batch, final BatchResult batchResult,
            final OutputStreamWriter writer) throws IOException {

        for (AnalysisData analysis : batch.getAnalyses()) {
            if (analysis.getComponents().size() > 0) {
                for (Pair<String, String> component : analysis.getComponents()) {
                    writer.write(";" + analysis.getName() + " ("
                            + component.getFirst() + ")");
                }
            } else {
                writer.write(";" + analysis.getName());
            }
        }
        // possibly write range analysis headers
        if (batch.getRangeAnalysis() != null) {
            for (Object n : batch.getRangeValues()) {
                writer.write(";" + n.toString());
            }
        }

        writer.write("\n");

        Iterable<BatchJobResult.Range> rangeSuccesses =
                FluentIterable.from(batchResult.getJobResults()).filter(Range.class);
        for (BatchJobResult.Range jobResult : rangeSuccesses) {
            writer.write(jobResult.getJob().getParameter().toString());
            Map<String, Object> results = jobResult.getResults();
            
            // basic analyses
            for (AnalysisData analysis : batch.getAnalyses()) {
                Object result = results.get(analysis.getId());
                Visualization visualization = VisualizationService.getInstance()
                        .getVisualization(VISUALIZATION_TYPE, result);
                String s = visualization.get(analysis, result);
                writer.write(";" + s);
            }

            // possibly append range batch results
            LinkedListMultimap<MapPropertyHolder, Map<String, Object>> rangeResults =
                    jobResult.getRangeResults();

            // note that we rely on the proper iteration order of the results here
            // which should be guaranteed by the 'LinkedListMultimap'
            for (Map<String, Object> rangeResult : rangeResults.values()) {
                for (Object result : rangeResult.values()) {
                    Object component = result;
                    if (result instanceof Object[]) {
                        component = ((Object[]) result)[batch.getRangeAnalysisComponent()];
                    }
                    Visualization visualization =
                            VisualizationService.getInstance().getVisualization("text", result);
                    String text = visualization.get(batch.getRangeAnalysis(), component);
                    writer.write(";" + text);
                }
            }
            writer.write("\n");
        }
    }
    
}
