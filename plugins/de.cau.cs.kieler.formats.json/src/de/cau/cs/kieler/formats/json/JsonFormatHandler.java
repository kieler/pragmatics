/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.formats.json;

import org.eclipse.elk.graph.ElkNode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.cau.cs.kieler.formats.IGraphFormatHandler;
import de.cau.cs.kieler.formats.IGraphTransformer;
import de.cau.cs.kieler.formats.TransformationData;
import de.cau.cs.kieler.formats.TransformationException;

/**
 * Handler for a JSON graph format. The JSON graph format has five basic elements: nodes, ports,
 * labels, edges, and edge sections. Details about each element can be found below, with some
 * sections describing features common to multiple elements. Note that in the JSON code, mandatory
 * fields are marked with an asterisk.
 *  
 * 
 * <h2>Nodes, Ports, Labels, Edges, and Edge Sections</h2>
 * 
 * <p>All elements have an {@code id} that uniquely identifies them. All elements furthermore can
 * have properties. Properties are basically a list of key-value pairs that are usually used to
 * assign layout option values to the element.</p> 
 * 
 * <pre>
 * {
 *   id*: "ID",
 *   properties: { ..object with key value pairs.. }
 * }
 * </pre>
 * 
 * 
 * <h2>Nodes, Ports, and Labels</h2>
 * 
 * <p>Nodes, ports, and labels have a two-dimensional location and size. Each of these elements
 * can also have an arbitrary number of labels to describe them properly. Yes, even labels can
 * have labels, although it depends on the layout algorithm whether or not it supports labeled
 * labels.</p>
 * 
 * <pre>
 * {
 *   x: ...,
 *   y: ...,
 *   width: ...,
 *   height: ...,
 *   labels: [ ..array of labels.. ],
 * }
 * </pre>
 * 
 * 
 * <h2>Nodes</h2>
 * 
 * <p>Nodes can have an arbitrary number of ports. Edges can connect to a node either directly or
 * through one of its ports. A node can also contain an arbitrary number of child nodes. A graph
 * is actually nothing more than a simple node whose children are the top-level nodes of the graph.
 * Finally, a node can contain edges. These edges do not necessarily connect to the node, but will
 * usually connect its children. The edge coordinates will be interpreted relative to the upper
 * left corner of the node which contains it.</p>
 * 
 * <pre>
 * {
 *   ports: [ ..array of ports.. ],
 *   children: [ ..array of child nodes.. ],
 *   edges: [ ..array of edges.. ]
 * }
 * </pre>
 * 
 * 
 * <h2>Ports</h2>
 * 
 * <p>Ports do not have any more interesting properties. Ports are boring.</p>
 * 
 * 
 * <h2>Labels</h2>
 * 
 * <p>Labels can additionally contain text.</p>
 * 
 * <pre>
 * {
 *   text: "TEXT"
 * }
 * </pre>
 * 
 * 
 * <h2>Edges</h2>
 * 
 * <p>There are two types of edges: primitive edges and extended edges.
 * Primitive edges are solely supported for legacy models to work. 
 * Exported graphs will always be made up of extended edges</p>
 * 
 * <h3>Primitive Edges</h3>
 * 
 * <p>Primitive edges have a source and target node and can optionally connect 
 * to a source port and target port.</p> 
 * <pre>
 * {
 *   source*: "node identifier",
 *   sourcePort: "port identifier",
 *   target*: "node identifier",
 *   targetPort: "port identifier",
 *   sourcePoint: {x,y},
 *   targetPoint: {x,y},
 *   bendPoints: [ .. {x,y} pairs .. ]
 * }
 * </pre>
 * 
 * <h3>Extended Edges</h3>
 * <p>
 * Extended edges have two mandatory arrays consisting of the identifiers of nodes and ports. One
 * array defines the edge's source elements, the other defines its target elements. Edges may well
 * connect more than one source to more than one target, making them hyperedges. If an edge has
 * a layout, it can specify an arbitrary number of edge sections that define said layout. A simple
 * edge with one source and one target only needs a single section.</p>
 * 
 * <pre>
 * {
 *   sources*: [ ..array of node and / or port identifiers.. ],
 *   targets*: [ ..array of node and / or port identifiers.. ],
 *   sections: [ ..array of edge sections.. ]
 * }
 * </pre>
 * 
 * 
 * <h2>Edge Sections</h2>
 * 
 * <p>Edge sections are only used in conjunction with extended edges and 
 * capture the routing of an edge through a drawing. Each section connects two
 * end points. An end point can be one of the end points of the section's edge (a node or a port),
 * or one or more other edge sections. The points where edge sections meet are <em>junction
 * points</em> where one part of the edge branches off. An edge section can only have either an
 * incoming shape or incoming edge sections (the same is true of course for outgoing shapes and
 * outgoing edge sections). In the simplest case, an edge only has a single edge section which
 * runs from the edge's single source to its single target. In this case, it is enough to define
 * the section's start and end point. Incoming and outgoing shapes are then filled in automatically
 * by the importer.</p>
 * 
 * <pre>
 * {
 *   startPoint*: {x, y},
 *   endPoint*: {x, y},
 *   bendPoints: [ ..array of {x, y} pairs.. ],
 *   incomingShape: "node and / or port identifier",
 *   outgoingShape: "node and / or port identifier",
 *   incomingSections: [ ..array of edge section identifiers.. ],
 *   outgoingSections: [ ..array of edge section identifiers.. ]
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
            final TransformationData<JSONObject, ElkNode> transData) {
        
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
    public String serialize(final TransformationData<ElkNode, JSONObject> transData) {
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
    public IGraphTransformer<JSONObject, ElkNode> getImporter() {
        return importer;
    }

    private JsonExporter exporter = new JsonExporter();
    
    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<ElkNode, JSONObject> getExporter() {
        return exporter;
    }

}
