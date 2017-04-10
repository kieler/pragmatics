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
package de.cau.cs.kieler.formats.json

import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import com.google.common.collect.Maps
import de.cau.cs.kieler.formats.IGraphTransformer
import de.cau.cs.kieler.formats.TransformationData
import java.util.Map
import org.eclipse.elk.graph.EMapPropertyHolder
import org.eclipse.elk.graph.ElkEdge
import org.eclipse.elk.graph.ElkEdgeSection
import org.eclipse.elk.graph.ElkLabel
import org.eclipse.elk.graph.ElkNode
import org.eclipse.elk.graph.ElkPort
import org.eclipse.elk.graph.ElkShape
import org.json.JSONArray
import org.json.JSONObject

import static org.eclipse.elk.graph.util.ElkGraphUtil.allOutgoingEdges

/**
 * Exporter from elk graph to json.
 * 
 * @author uru
 */
class JsonExporter implements IGraphTransformer<ElkNode, JSONObject> {

    private val BiMap<ElkNode, String> nodeIdMap = HashBiMap.create()
    private val BiMap<ElkPort, String> portIdMap = HashBiMap.create()
    private val Map<ElkEdge, String> edgeIdMap = Maps.newHashMap
    private val BiMap<ElkEdgeSection, String> edgeSectionIdMap = HashBiMap.create()

    private val Map<ElkNode, JSONObject> nodeJsonMap = Maps.newHashMap
    private val Map<ElkPort, JSONObject> portJsonMap = Maps.newHashMap
    private val Map<ElkEdge, JSONObject> edgeJsonMap = Maps.newHashMap
    private val Map<ElkEdgeSection, JSONObject> edgeSectionJsonMap = Maps.newHashMap

    private var nodeIdCounter = 0
    private var portIdCounter = 0
    private var edgeIdCounter = 0
    private var edgeSectionIdCounter = 0

    override transform(TransformationData<ElkNode, JSONObject> data) {
        init

        val jsonGraph = new JSONObject
        jsonGraph.put("id", "root")

        // create a tmp array
        val jsonArray = new JSONArray

        // transform the root node
        data.sourceGraph.transformNode(jsonArray)

        // edges (important that all nodes are already transformed!)
        data.sourceGraph.transformEdges

        // retrieve the result from the tmp array
        data.targetGraphs += jsonArray.get(0) as JSONObject
    }

    private def init() {
        nodeIdMap.clear
        portIdMap.clear
        edgeIdMap.clear
        edgeSectionIdMap.clear
        nodeJsonMap.clear
        portJsonMap.clear
        edgeJsonMap.clear
        edgeSectionJsonMap.clear
        nodeIdCounter = 0
        portIdCounter = 0
        edgeIdCounter = 0
        edgeSectionIdCounter = 0
    }

    private def void transformNode(ElkNode node, JSONArray array) {
        // create the node and add it to the parent
        val jsonObj = node.createAndRegister
        array.put(jsonObj)

        // labels
        val labels = new JSONArray
        jsonObj.put("labels", labels)
        node.labels.forEach[it.transformLabel(labels)]

        // ports
        val ports = new JSONArray
        jsonObj.put("ports", ports)
        node.ports.forEach[it.transformPort(ports)]

        // children
        val children = new JSONArray
        jsonObj.put("children", children)
        node.children.forEach[it.transformNode(children)]

        // properties
        node.transformProperties(jsonObj)
        node.transferShapeLayout(jsonObj)
    }

    private def void transformPort(ElkPort port, JSONArray array) {
        val jsonObj = port.createAndRegister
        array.put(jsonObj)

        // labels
        val labels = new JSONArray
        jsonObj.put("labels", labels)
        port.labels.forEach[it.transformLabel(labels)]

        // properties and things
        port.transformProperties(jsonObj)
        port.transferShapeLayout(jsonObj)
    }

    private def void transformEdges(ElkNode node) {
        val jsonObj = nodeJsonMap.get(node)
        val edges = new JSONArray
        jsonObj.put("edges", edges)
        allOutgoingEdges(node).forEach[it.transformEdge(edges)]

        // children
        node.children.forEach[it.transformEdges]
    }

    private def void transformEdge(ElkEdge edge, JSONArray array) {
        val jsonObj = edge.createAndRegister
        array.put(jsonObj)

        // labels
        val labels = new JSONArray
        jsonObj.put("labels", labels)
        edge.labels.forEach[it.transformLabel(labels)]
        
        // sections
        val sections = new JSONArray
        jsonObj.put("sections", sections)
        edge.sections.forEach[it.transformSection(sections)]

        // properties        
        edge.transformProperties(jsonObj)
    }
    
    private def void transformSection(ElkEdgeSection section, JSONArray sections) {
        val jsonObj = section.createAndRegister
        sections.put(jsonObj)
        
        // Start Point
        val startPoint = new JSONObject;
        startPoint.put("x", section.startX);
        startPoint.put("y", section.startY);
        jsonObj.put("startPoint", startPoint);
        
        // End Point
        val endPoint = new JSONObject;
        endPoint.put("x", section.endX);
        endPoint.put("y", section.endY);
        jsonObj.put("endPoint", endPoint);
        
        // Bend Points
        val bendPoints = new JSONArray;
        section.bendPoints.forEach [ pnt |
            val jsonPnt = new JSONObject
            jsonPnt.put("x", pnt.x)
            jsonPnt.put("y", pnt.y)
            bendPoints.put(jsonPnt)
        ]
        
        // Incoming shape
        if (section.incomingShape != null) {
            jsonObj.put("incomingShape", idByElement(section.incomingShape))
        }
        
        // Outgoing shape
        if (section.outgoingShape != null) {
            jsonObj.put("outgoingShape", idByElement(section.outgoingShape))
        }
        
        // Incoming sections
        if (!section.incomingSections.empty) {
            val incomingSections = new JSONArray;
            section.incomingSections.forEach [ sec |
                incomingSections.put(idByElement(sec));
            ];
        }
        
        // Incoming sections
        if (!section.incomingSections.empty) {
            val incomingSections = new JSONArray;
            section.incomingSections.forEach [ sec |
                incomingSections.put(idByElement(sec));
            ];
            jsonObj.put("incomingSections", incomingSections);
        }
        
        // Outgoing sections
        if (!section.outgoingSections.empty) {
            val outgoingSections = new JSONArray;
            section.outgoingSections.forEach [ sec |
                outgoingSections.put(idByElement(sec));
            ];
            jsonObj.put("outgoingSections", outgoingSections);
        }
        
        section.transformProperties(jsonObj);
    }

    private def void transformLabel(ElkLabel label, JSONArray array) {
        val jsonLabel = new JSONObject
        jsonLabel.put("text", label.text)

        // properties and things
        label.transformProperties(jsonLabel)
        label.transferShapeLayout(jsonLabel)
    }

    private def void transformProperties(EMapPropertyHolder holder, JSONObject parent) {
        val jsonProps = new JSONObject
        parent.put("properties", jsonProps)

        holder.properties.entrySet.forEach [ p |
            jsonProps.put(p.key.id, p.value.toString)
        ]
    }

    override transferLayout(TransformationData<ElkNode, JSONObject> data) {
        throw new UnsupportedOperationException("Not yet implemented.")
    }

    private def transferShapeLayout(ElkShape shape, JSONObject jsonObj) {
        // pos and dimension
        jsonObj?.put("x", shape.x)
        jsonObj?.put("y", shape.y)
        jsonObj?.put("width", shape.width)
        jsonObj?.put("height", shape.height)
    }

    /* ---------------------------------------------------------------------------
     *   Convenience methods
     */
    def JSONObject createAndRegister(ElkNode node) {
        val obj = new JSONObject
        val id = "n" + nodeIdCounter
        obj.put("id", id)
        nodeIdCounter = nodeIdCounter + 1

        nodeIdMap.put(node, id)
        nodeJsonMap.put(node, obj)

        return obj
    }

    def JSONObject createAndRegister(ElkPort port) {
        val obj = new JSONObject
        val id = "p" + portIdCounter
        obj.put("id", id)
        portIdCounter = portIdCounter + 1

        portIdMap.put(port, id)
        portJsonMap.put(port, obj)

        return obj
    }

    def JSONObject createAndRegister(ElkEdge edge) {
        val obj = new JSONObject
        val id = "e" + edgeIdCounter
        obj.put("id", id)
        edgeIdCounter = edgeIdCounter + 1

        edgeIdMap.put(edge, id)
        edgeJsonMap.put(edge, obj)

        return obj
    }

    def JSONObject createAndRegister(ElkEdgeSection section) {
        val obj = new JSONObject
        val id = "s" + edgeSectionIdCounter
        obj.put("id", id)
        edgeSectionIdCounter = edgeIdCounter + 1

        edgeSectionIdMap.put(section, id)
        edgeSectionJsonMap.put(section, obj)

        return obj
    }
    
    private def dispatch idByElement(ElkNode node) {
        return nodeIdMap.inverse.get(node);
    }
    
    private def dispatch idByElement(ElkPort port) {
        return portIdMap.inverse.get(port);
    }
    
    private def dispatch idByElement(ElkEdgeSection section) {
        return edgeSectionIdMap.inverse.get(section);
    }
}
