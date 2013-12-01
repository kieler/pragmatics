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
 * The json graph format comprises of four basic elements - Nodes, Ports, Labels, and Edges. 
 * - Each element has an 'id' that identifies it uniquely. 
 * - The first three elements can hold a position and dimension. 
 * - Edges on the contrary can hold bend points specifying where the edge changes direction.
 * - Nodes can contain child nodes and hold ports that specify attachment points of edges.
 * - Multiple edges can be attached to the same port, the port can be attached to the node itself.
 * - All elements can hold labels (despite the label itself).
 * - All elements can hold properties which represent additional information to the layout algorithm.
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
 *   labels: [ ..array with labels objects .. ],
 *   properties: { ..object with key value pairs.. }
 * }
 * </pre>
 * 
 * <h2>Label</h2>
 * {
 *   text: "TEXT",
 *   properties: { object with key value pairs}
 * }
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
 * @author uru
 */
public class JsonFormatHandler implements IGraphFormatHandler<JSONObject> {

    /**
     * {@inheritDoc}
     */
    public void deserialize(final String serializedGraph,
            final TransformationData<JSONObject, KNode> transData) {
        try {
            // remove single line comments
            String commentFree = serializedGraph
                    .replaceAll("//.*?\n", "\n") // single line
                    .replaceAll("(?s)/\\*.*?\\*/", ""); // multi line 
            // parse the json
            JSONObject obj = new JSONObject(commentFree);
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
