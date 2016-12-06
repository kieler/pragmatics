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

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.core.util.Pair;
import org.eclipse.elk.graph.properties.MapPropertyHolder;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.LinkedListMultimap;

import de.cau.cs.kieler.kiml.grana.AnalysisData;
import de.cau.cs.kieler.kiml.grana.ui.batch.BatchJobResult.Range;
import de.cau.cs.kieler.kiml.grana.ui.batch.BatchJobResult.Simple;
import de.cau.cs.kieler.kiml.grana.ui.visualization.Visualization;
import de.cau.cs.kieler.kiml.grana.ui.visualization.VisualizationService;

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
        
        writer.write("\n");
        
        // write the job results
        Iterable<BatchJobResult.Simple> simpleSuccesses =
                FluentIterable.from(batchResult.getJobResults()).filter(Simple.class);
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
            for (Number n : batch.getRangeValues()) {
                writer.write(";" + n);
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
