/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.formats.json;

import org.json.JSONException;
import org.json.JSONObject;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.ITransformationHandler;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;
import de.cau.cs.kieler.kiml.service.formats.TransformationException;

/**
 * @author uru
 */
public class JsonHandler implements ITransformationHandler<JSONObject> {

    /**
     * {@inheritDoc}
     */
    public void deserialize(final String serializedGraph,
            final TransformationData<JSONObject, KNode> transData) {
        try {
            JSONObject obj = new JSONObject(serializedGraph);
            transData.setSourceGraph(obj);
        } catch (JSONException e) {
            throw new TransformationException("Cannot parse the passed "
                    + "json. (" + e.getMessage() + ")", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String serialize(final TransformationData<KNode, JSONObject> transData) {
        // TODO there might be multiple graphs?
        for (JSONObject graph : transData.getTargetGraphs()) {
            String json = graph.toString();
            return json;
        }
        return "";
    }

    private JsonImporter importer = new JsonImporter();
    
    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<JSONObject, KNode> getImporter() {
        return importer;
    }

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, JSONObject> getExporter() {
        throw new UnsupportedOperationException("Json export not yet supported.");
    }

}
