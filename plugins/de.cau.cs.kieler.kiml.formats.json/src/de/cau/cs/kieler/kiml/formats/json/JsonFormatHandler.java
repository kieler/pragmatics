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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.formats.IGraphFormatHandler;
import de.cau.cs.kieler.kiml.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.formats.TransformationData;
import de.cau.cs.kieler.kiml.formats.TransformationException;

/**
 * Handler for a JSON graph format.
 * 
 * There are 3 elements to this graph format. <em>Nodes</em> which have a dimension and position
 * and can contain nodes themselves. <em>Ports</em> that are attachment points of nodes where
 * possibly multiple edges can be connected to. <em>Edges</em> connect nodes or ports of nodes.
 * 
 * The following fields are supported for each element, a trailing * marks required fields.
 * 
 * <h2>Common properties of nodes and ports.
 * 
 * <pre>
 * {
 *   id*: "ID", 
 *   x: xpos,
 *   y: ypos,
 *   width: 10,
 *   height: 10,
 *   labels: [ ..array with labels .. ],
 *   properties: { ..object with key value pairs.. }
 * }
 * </pre>
 * 
 * 
 * <h2>Node</h2>
 * 
 * <pre>
 * {
 *   ... 
 *   ports: [ ..array with ports.. ],
 *   children: [ ..array with child nodes.. ],
 *   edges: [ ..array with edges between this nodes children.. ]
 * }
 * </pre>
 * 
 * 
 * <h2>Port</h2>
 * 
 * <pre>
 * {
 *   ...
 * }
 * </pre>
 * 
 * <h2>Edge</h2>
 * 
 * <pre>
 * {
 *   ..
 *   source*: nodeId,
 *   sourcePort: sourcePort,
 *   target*: nodeId,
 *   targetPort: targetPort,
 *   sourcePoint: {x,y},
 *   targetPoint: {x,y},
 *   bendPoints: [ .. {x,y} pairs .. ]
 * }
 * </pre>
 * 
 * TODO
 *  - I guess the labels should be objects as they can hold properties
 * 
 * @author uru
 */
public class JsonFormatHandler implements IGraphFormatHandler<JSONObject> {

    /**
     * {@inheritDoc}
     */
    public void deserialize(final String serializedGraph,
            final TransformationData<JSONObject, KNode> transData) {
        try {
            JSONObject obj = new JSONObject(serializedGraph);
            transData.setSourceGraph(obj);
        } catch (JSONException e) {
            throw new TransformationException("Cannot parse the passed " + "json. ("
                    + e.getMessage() + ")", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String serialize(final TransformationData<KNode, JSONObject> transData) {
        JSONArray jsonArray = new JSONArray();
        for (JSONObject graph : transData.getTargetGraphs()) {
            jsonArray.put(graph);
        }

        // try to return pretty printed json
        try {
            return jsonArray.toString(2);
        } catch (JSONException e) {
            return jsonArray.toString();
        }
    }

    private JsonImporter importer = new JsonImporter();

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<JSONObject, KNode> getImporter() {
        return importer;
    }

    private JsonExporter exporter = new JsonExporter();
    
    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, JSONObject> getExporter() {
        return exporter;
    }

}
