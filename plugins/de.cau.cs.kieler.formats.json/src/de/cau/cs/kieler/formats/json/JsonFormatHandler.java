/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.formats.json;

import org.eclipse.elk.core.util.Maybe;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.json.ElkGraphJson;
import org.eclipse.elk.graph.json.JsonImportException;

import de.cau.cs.kieler.formats.IGraphFormatHandler;
import de.cau.cs.kieler.formats.IGraphTransformer;
import de.cau.cs.kieler.formats.TransformationData;
import de.cau.cs.kieler.formats.TransformationException;

/**
 * Format handler for JSON. For further information see the importer/exporter class in ELK and ELK's
 * documentation.
 * 
 * @see org.eclipse.elk.graph.json.JsonImporter
 * @see org.eclipse.elk.graph.json.JsonExporter
 */
public class JsonFormatHandler implements IGraphFormatHandler<String> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void deserialize(final String serializedGraph,
            final TransformationData<String, ElkNode> transData) {
        transData.setSourceGraph(serializedGraph);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String serialize(final TransformationData<ElkNode, String> transData) {
        return transData.getTargetGraphs().get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IGraphTransformer<String, ElkNode> getImporter() {
        return IMPORTER;
    }
    
    private static final IGraphTransformer<String, ElkNode> IMPORTER =
            new IGraphTransformer<String, ElkNode>() {
                private final Maybe<org.eclipse.elk.graph.json.JsonImporter> importerMaybe =
                        new Maybe<>();

                @Override
                public void transform(final TransformationData<String, ElkNode> data) {
                    try {
                        ElkNode root = ElkGraphJson.forGraph(data.getSourceGraph())
                                                   .rememberImporter(importerMaybe)
                                                   .toElk();
                        data.getTargetGraphs().add(root);
                    } catch (JsonImportException jie) {
                        throw new TransformationException(jie);
                    }
                }

                @Override
                public void transferLayout(final TransformationData<String, ElkNode> data) {
                    ElkNode root = data.getTargetGraphs().get(0);
                    importerMaybe.get().transferLayout(root);
                    importerMaybe.clear();
                }
            };

    /**
     * {@inheritDoc}
     */
    @Override
    public IGraphTransformer<ElkNode, String> getExporter() {
        return EXPORTER;
    }

    private static final IGraphTransformer<ElkNode, String> EXPORTER = 
            new IGraphTransformer<ElkNode, String>() {
        
        @Override
        public void transform(final TransformationData<ElkNode, String> data) {
            String json = ElkGraphJson.forGraph(data.getSourceGraph())
                                      .omitLayout(true)
                                      .omitZeroDimension(true)
                                      .omitZeroPositions(true)
                                      .shortLayoutOptionKeys(true)
                                      .prettyPrint(true)
                                      .toJson();
            data.getTargetGraphs().add(json);
        }
        
        @Override
        public void transferLayout(final TransformationData<ElkNode, String> data) {
            throw new UnsupportedOperationException();
        }
    }; 
    
}
