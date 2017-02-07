/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.grana.ui.batch;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.MapPropertyHolder;

import de.cau.cs.kieler.grana.AnalysisData;
import de.cau.cs.kieler.grana.ui.visualization.Visualization;
import de.cau.cs.kieler.grana.ui.visualization.VisualizationService;

/**
 * A batch result serializer which saves the information as JSON. As opposed to {@link CSVResultSerializer}, 
 * it is capable to export the nested structure of range analyses.
 * 
 * @author uru
 */
public class JsonResultSerializer implements IBatchResultSerializer {

    /** the visualization type used in the serialization. */
    private static final String VISUALIZATION_TYPE = "json";
    
    private static final String QUOTE = "\"";

    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(final OutputStream outputStream, final BatchResult batchResult,
            final IElkProgressMonitor monitor) throws Exception {
        monitor.begin("Serialize batch result as JSON", 1);

        OutputStreamWriter writer = new OutputStreamWriter(outputStream, Charset.forName("UTF-8"));

        writer.write("[\n");

        Batch batch = batchResult.getBatch();
        Iterator<BatchJobResult> results = batchResult.getJobResults().iterator();
        if (results.hasNext()) {
            writeJobResult(batch, results.next(), writer);
        }
        while (results.hasNext()) {
            writer.write(",\n");
            writeJobResult(batch, results.next(), writer);
        }

        writer.write("]\n");

        writer.close();
        monitor.done();
    }

    private void writeJobResult(final Batch batch, final BatchJobResult result,
            final OutputStreamWriter writer) throws Exception {

        writer.write("{\n");

        writer.write(QUOTE + "id" + QUOTE + ": " + QUOTE);
        writer.write(result.getJob().getParameter().toString());
        writer.write(QUOTE + ",\n");

        writer.write(QUOTE + "type" + QUOTE + ": " + QUOTE);
        writer.write(result.getJob().getClass().getSimpleName());
        writer.write(QUOTE + ",\n");

        // basic analyses as executed by every batch type
        writer.write(QUOTE + "analyses" + QUOTE + ": ");
        writer.write("{\n");
        writeResults(batch.getAnalyses(), result.getResults(), writer);
        writer.write("}");

        // give each batch type the opportunity to add its additional results
        writeResults(batch, result, writer);

        writer.write("}");
    }

    private void writeResults(final Batch batch, final BatchJobResult result,
            final OutputStreamWriter writer) throws Exception {

        if (result instanceof BatchJobResult.Range) {
            
            Batch.Range rangeBatch = (Batch.Range) batch;
            BatchJobResult.Range rangeJobResult = (BatchJobResult.Range) result;

            // add the results of each range job
            writer.write(",\n");
            writeRangeResults(rangeBatch, rangeJobResult, writer);
            writer.write("\n");
        }
        
        // the others have do not require special treatment (so far)
    }

    private void writeResults(final List<AnalysisData> analyses, final Map<String, Object> results,
            final OutputStreamWriter writer) throws Exception {
        boolean first = true;
        for (AnalysisData analysis : analyses) {
            if (!first) {
                writer.write(",\n");
            }
            first = false;
            Object result = results.get(analysis.getId());
            Visualization visualization =
                    VisualizationService.getInstance().getVisualization(VISUALIZATION_TYPE, result);
            String s = visualization.get(analysis, result);
            writer.write(s);
        }
        writer.write("\n");
    }

    private void writeRangeResults(final Batch.Range batch, final BatchJobResult.Range rangeJob,
            final OutputStreamWriter writer) throws Exception {
        
        writer.write(QUOTE + "rangeanalyses" + QUOTE + ": [");

        boolean first = true;
        for (Entry<MapPropertyHolder, Map<String, Object>> entry : rangeJob.getRangeResults().entries()) {
            if (!first) {
                writer.write(", ");
            }
            first = false;
            writeRangeResult(batch, entry.getKey(), batch.getRangeAnalyses(), entry.getValue(), writer);
        }

        writer.write(" ]");
    }
                          
    private void writeRangeResult(final Batch.Range batch, final MapPropertyHolder options,
            final List<AnalysisData> analyses, final Map<String, Object> results,
            final OutputStreamWriter writer) throws Exception {

        writer.write("{\n");
        
        writer.write(QUOTE + "options" + QUOTE + ": { ");
        boolean first = true;
        for (Entry<IProperty<?>, Object> option : options.getAllProperties().entrySet()) {
            if (!first) {
                writer.write(", ");
            }
            first = false;
            writer.write(QUOTE + option.getKey().getId() + QUOTE + ": ");
            writer.write(QUOTE + option.getValue() + QUOTE);
        }
        writer.write("},\n");
        
        writer.write(QUOTE + "analyses" + QUOTE + ": {");
        writeResults(analyses, results, writer);
        writer.write("}");
        
        writer.write("}");
    }
}
