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
package de.cau.cs.kieler.kiml.formats.json

import com.google.common.collect.Maps
import de.cau.cs.kieler.core.kgraph.KEdge
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData
import java.util.Map
import org.json.JSONArray
import org.json.JSONObject
import de.cau.cs.kieler.kiml.formats.IGraphTransformer
import de.cau.cs.kieler.kiml.formats.TransformationData

/**
 * Exporter from KNode to json.
 * 
 * @author uru
 */
class JsonExporter implements IGraphTransformer<KNode, JSONObject> {

    extension JsonExtensions = new JsonExtensions

    private val Map<KNode, String> nodeIdMap = Maps.newHashMap
    private val Map<KPort, String> portIdMap = Maps.newHashMap
    private val Map<KEdge, String> edgeIdMap = Maps.newHashMap
        
    private val Map<KNode, JSONObject> nodeJsonMap = Maps.newHashMap
    private val Map<KPort, JSONObject> portJsonMap = Maps.newHashMap
    private val Map<KEdge, JSONObject> edgeJsonMap = Maps.newHashMap
    
    private var nodeIdCounter = 0
    private var portIdCounter = 0
    private var edgeIdCounter = 0

    override transform(TransformationData<KNode, JSONObject> data) {
        
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
        nodeJsonMap.clear
        portJsonMap.clear
        edgeJsonMap.clear
        nodeIdCounter = 0
        portIdCounter = 0
        edgeIdCounter = 0
    }
    

    private def void transformNode(KNode node, JSONArray array) {
        
        // create the node and add it to the parent
        val jsonObj = node.createAndRegister
        array.put(jsonObj)

        // labels
        val labels = new JSONArray
        jsonObj.put("labels", labels)
        node.labels.forEach [ labels.put(it.text) ]

        // transfer positions and dimension
        jsonObj.put("x", node.layout.xpos)
        jsonObj.put("y", node.layout.ypos)
        jsonObj.put("width", node.layout.width)
        jsonObj.put("height", node.layout.height)
        
        // ports
        val ports = new JSONArray
        jsonObj.put("ports", ports)
        node.ports.forEach [ it.transformPort(ports) ]
        
        // children
        val children = new JSONArray
        jsonObj.put("children", children)
        node.children.forEach [ it.transformNode(children) ]
        
        // properties
        node.layout.transformProperties(jsonObj)
        
    }
    
    
    private def void transformPort(KPort port, JSONArray array) {
        val jsonObj = port.createAndRegister
        array.put(jsonObj)

        // labels
        val labels = new JSONArray
        jsonObj.put("labels", labels)
        port.labels.forEach [ labels.put(it.text) ]
        
         // properties
        port.layout.transformProperties(jsonObj)

        // transfer positions and dimension
        jsonObj.put("x", port.layout.xpos)
        jsonObj.put("y", port.layout.ypos)
        jsonObj.put("width", port.layout.width)
        jsonObj.put("height", port.layout.height)
    }

    private def void transformEdges(KNode node) {
        val jsonObj = nodeJsonMap.get(node)        
        val edges = new JSONArray
        jsonObj.put("edges", edges)
        node.outgoingEdges.forEach [ it.transformEdge(edges)]
        
        // children
        node.children.forEach [ it.transformEdges ]
    }

    private def void transformEdge(KEdge edge, JSONArray array) {
        val jsonObj = edge.createAndRegister
        array.put(jsonObj)

        // labels
        val labels = new JSONArray
        jsonObj.put("labels", labels)
        edge.labels.forEach [ labels.put(it.text) ]

        // properties        
        edge.layout.transformProperties(jsonObj)

        // connection points
        nodeIdMap.get(edge.source) => [ jsonObj.put("source", it)]
        nodeIdMap.get(edge.target) => [ jsonObj.put("target", it)]
        
        portIdMap.get(edge.sourcePort) => [ if (it != null) jsonObj.put("sourcePort", it) ]
        portIdMap.get(edge.targetPort) => [ if (it != null) jsonObj.put("targetPort", it) ]
        
        // source
        val sourcePoint = new JSONObject
        sourcePoint.put("x", edge.layout.sourcePoint.x)
        sourcePoint.put("y", edge.layout.sourcePoint.y)
        jsonObj.put("sourcePoint", sourcePoint)

        // target        
        val targetPoint = new JSONObject
        targetPoint.put("x", edge.layout.targetPoint.x)
        targetPoint.put("y", edge.layout.targetPoint.y)
        jsonObj.put("targetPoint", targetPoint)

        // bend points
        val bends = new JSONArray
        edge.layout.bendPoints.forEach [ pnt |
            val jsonPnt = new JSONObject
            jsonPnt.put("x", pnt.x)
            jsonPnt.put("y", pnt.y)
            bends.put(jsonPnt)
        ]
        jsonObj?.put("bendPoints", bends)
    }

    private def void transformProperties(KLayoutData holder, JSONObject parent) {
        
        val jsonProps = new JSONObject
        parent.put("properties", jsonProps)
        
        holder.properties.entrySet.forEach [ p |
            jsonProps.put(p.key.id, "")    
            // TODO
        ]
    } 
    

    override transferLayout(TransformationData<KNode, JSONObject> data) {
        throw new UnsupportedOperationException("TODO: auto-generated method stub")
    }
    
    /* ---------------------------------------------------------------------------
     *   Convenience methods
     */
    def JSONObject createAndRegister(KNode node) {
        
        val obj = new JSONObject
        val id = "n" + nodeIdCounter
        obj.put("id", id)
        nodeIdCounter = nodeIdCounter + 1
        
        nodeIdMap.put(node, id)
        nodeJsonMap.put(node, obj)

        return obj
    }

    def JSONObject createAndRegister(KPort port) {
        
        val obj = new JSONObject
        val id = "p" + portIdCounter
        obj.put("id", id)
        portIdCounter = portIdCounter + 1
    
        portIdMap.put(port, id)
        portJsonMap.put(port, obj)

        return obj
    }

    def JSONObject createAndRegister(KEdge edge) {
        
        val obj = new JSONObject
        val id = "e" + edgeIdCounter
        obj.put("id", id)
        edgeIdCounter = edgeIdCounter + 1

        edgeIdMap.put(edge, id)
        edgeJsonMap.put(edge, obj)


        return obj
    }
}
