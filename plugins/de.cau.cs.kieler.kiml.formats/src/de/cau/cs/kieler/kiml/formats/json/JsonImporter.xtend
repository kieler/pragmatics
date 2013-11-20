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

import com.google.common.collect.Iterators
import com.google.common.collect.Maps
import de.cau.cs.kieler.core.kgraph.KEdge
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer
import de.cau.cs.kieler.kiml.service.formats.TransformationData
import de.cau.cs.kieler.kiml.service.formats.TransformationException
import de.cau.cs.kieler.kiml.util.KimlUtil
import java.util.Iterator
import java.util.Map
import org.json.JSONObject

/**
 * Importer for graphs in the json format. 
 * 
 * Expects a the json format to be as follows:
 * 
 * <h2>
 * 
 * {
 *   id: "ID",
 *   x: xpos,
 *   y: ypos,
 *   width: 10,
 *   height: 10,
 *   labels: [ ..array with labels .. ],
 *   properties: { ..object with key value pairs.. }
 * }
 * 
 * 
 * <h2>Node</h2>
 * {
 *   ... 
 *   ports: [ ..array with ports.. ],
 *   children: [ ..array with child nodes.. ],
 *   edges: [ ..array with edges between this nodes children.. ]
 * }
 * 
 * 
 * <h2>Port</h2>
 * {
 *   ...
 * }
 * 
 * <h2>Edge</h2>
 * {
 *   ..
 *   source: nodeId,
 *   sourcePort: sourcePort,
 *   target: nodeId,
 *   targetPort: targetPort,
 *   bendpoints: [ ..array of {x:1,y:1} objects (including source and target point.. ]
 * }
 * 
 * 
 * 
 * 
 * @author uru
 */
class JsonImporter implements IGraphTransformer<JSONObject, KNode> {

    private val Map<String, KNode> nodeIdMap = Maps.newHashMap
    private val Map<String, KPort> portIdMap = Maps.newHashMap
    private val Map<String, KEdge> edgeIdMap = Maps.newHashMap

    private val Map<KNode, JSONObject> nodeJsonMap = Maps.newHashMap
    private val Map<KPort, JSONObject> portJsonMap = Maps.newHashMap
    private val Map<KEdge, JSONObject> edgeJsonMap = Maps.newHashMap

    /* ---------------------------------------------------------------------------
     *   JSON --> KGraph 
     */
    /**
      * Main entry point for the json to kgraph transformation. Runs through all elements
      * of the graph (nodes, ports, edges) and creates correlating KGraph elements.
      */
    override transform(TransformationData<JSONObject, KNode> data) {
        val graph = data.sourceGraph

        clearMaps

        // create a root
        val root = KimlUtil.createInitializedNode

        // transform all nodes
        graph.transformChildNodes(root)

        // transform all edges
        graph.transformEdges

        // return the transformed KGraph
        data.targetGraphs += root
    }

    private def clearMaps() {
        nodeIdMap.clear
        portIdMap.clear
        edgeIdMap.clear
        nodeJsonMap.clear
        portJsonMap.clear
        edgeJsonMap.clear
    }

    private def transformChildNodes(JSONObject jsonNode, KNode parent) {
        jsonNode.optJSONArray("children") => [ children |
            if (children != null) {
                for (i : 0 .. children.length) {
                    children.optJSONObject(i)?.transformNode(parent)
                }
            }
        ]
    }

    private def KNode transformNode(JSONObject jsonNode, KNode parent) {

        // create a KNode and add it to the parent
        val node = KimlUtil.createInitializedNode.register(jsonNode)
        parent.children += node

        // position and dimension
        jsonNode.transformShapeLayout(node.layout)

        // ports
        jsonNode.transformPorts(node)

        // labels
        jsonNode.transformLabels(node)

        // properties
        jsonNode.transformProperties(node.layout)

        // recursive call
        jsonNode.transformChildNodes(node)

        return node
    }

    private def transformShapeLayout(JSONObject jsonObj, KShapeLayout shapeLayout) {
        jsonObj.optDouble("x") => [shapeLayout.xpos = it.floatValueValid]
        jsonObj.optDouble("y") => [shapeLayout.ypos = it.floatValueValid]
        jsonObj.optDouble("width") => [shapeLayout.width = it.floatValueValid]
        jsonObj.optDouble("height") => [shapeLayout.height = it.floatValueValid]
    }

    private def void transformEdges(JSONObject jsonObj) {

        // transform edges of the current hierarchy level
        jsonObj.optJSONArray("edges") => [ edges |
            if (edges != null) {
                for (i : 0 .. edges.length) {
                    edges.optJSONObject(i)?.transformEdge
                }
            }
        ]

        // transform the edges of all child nodes
        jsonObj.optJSONArray("children") => [ children |
            if (children != null) {
                for (i : 0 .. children.length) {
                    children.optJSONObject(i)?.transformEdges
                }
            }
        ]
    }

    private def transformEdge(JSONObject jsonObj) {

        // Create KEdge
        val edge = KimlUtil.createInitializedEdge.register(jsonObj)

        // sources
        edge.source = nodeIdMap.get(jsonObj.optString("source"))
        edge.sourcePort = portIdMap.get(jsonObj.optString("sourcePort"))

        // targets
        edge.target = nodeIdMap.get(jsonObj.optString("target"))
        edge.targetPort = portIdMap.get(jsonObj.optString("targetPort"))

        // bend points
        jsonObj.transformEdgeLayout(edge.layout)
    }

    private def transformEdgeLayout(JSONObject jsonObj, KEdgeLayout edgeLayout) {
        // TODO !
    }

    private def transformProperties(JSONObject jsonObject, KLayoutData layoutData) {
        jsonObject.optJSONObject("properties") => [ props |
            props?.keys.emptyIfNull.forEach [ key |
                val value = props.optString(key)
                KimlUtil.setOption(layoutData, key, value)
            ]
        ]
    }

    private def transformLabels(JSONObject jsonObj, KLabeledGraphElement element) {
        jsonObj.optJSONArray("labels") => [ labels |
            if (labels != null) {
                for (i : 0 .. labels.length) {
                    val label = KimlUtil.createInitializedLabel(element)
                    label.text = labels.optString(i)
                }
            }
        ]
    }

    private def transformPorts(JSONObject jsonObj, KNode parent) {
        jsonObj.optJSONArray("ports") => [ ports |
            if (ports != null) {
                for (i : 0 .. ports.length) {
                    ports.optJSONObject(i)?.transformPort(parent)
                }
            }
        ]
    }

    private def transformPort(JSONObject jsonPort, KNode parent) {

        // create KPort
        val port = KimlUtil.createInitializedPort.register(jsonPort)

        // position and dimension
        jsonPort.optDouble("x") => [port.layout.xpos = it.floatValueValid]
        jsonPort.optDouble("y") => [port.layout.ypos = it.floatValueValid]
        jsonPort.optDouble("width") => [port.layout.width = it.floatValueValid]
        jsonPort.optDouble("height") => [port.layout.height = it.floatValueValid]

        // labels 
        jsonPort.transformLabels(port)

        // properties
        jsonPort.transformProperties(port.layout)
    }

    /* ---------------------------------------------------------------------------
     *   JSON --> KGraph 
     */
    /**
      * Main entry point if the passed input format has been json and the requested 
      * output format is json as well.
      */
    override transferLayout(TransformationData<JSONObject, KNode> data) {
        // for each resulting graph run through all elements of the root
        data.targetGraphs.forEach [ graph |
            graph.eAllContents.forEach [ element |
                element.transferLayoutInt
            ]
        ]
    }

    private def dispatch transferLayoutInt(KNode node) {
        val jsonObj = nodeJsonMap.get(node)

        // transfer positions and dimension
        jsonObj?.put("x", node.layout.xpos)
        jsonObj?.put("y", node.layout.ypos)
        jsonObj?.put("width", node.layout.width)
        jsonObj?.put("height", node.layout.height)
    }

    private def dispatch transferLayoutInt(KPort port) {
        val jsonObj = portJsonMap.get(port)

        // transfer positions and dimension
        jsonObj?.put("x", port.layout.xpos)
        jsonObj?.put("y", port.layout.ypos)
        jsonObj?.put("width", port.layout.width)
        jsonObj?.put("height", port.layout.height)
    }

    private def dispatch transferLayoutInt(KEdge edge) {
        val jsonObj = edgeJsonMap.get(edge)

        // TODO
        return ""
    }

    private def dispatch transferLayoutInt(Object obj) {
        // don't care about the rest
    }

    /* ---------------------------------------------------------------------------
     *   Convenience methods
     */
     
    def KNode register(KNode node, JSONObject obj) {
        val id = obj.optString("id")
        if (id == null) {
            throw new TransformationException("Invalid json graph format. Node is missing 'id' key.")
        }

        nodeIdMap.put(id, node)

        nodeJsonMap.put(node, obj)

        return node
    }

    def KPort register(KPort port, JSONObject obj) {
        val id = obj.optString("id")
        if (id == null) {
            throw new TransformationException("Invalid json graph format. Port is missing 'id' key.")
        }

        portIdMap.put(id, port)

        portJsonMap.put(port, obj)

        return port
    }

    def KEdge register(KEdge edge, JSONObject obj) {
        val id = obj.optString("id")
        if (id == null) {
            throw new TransformationException("Invalid json graph format. Edge is missing 'id' key.")
        }

        edgeIdMap.put(id, edge)

        edgeJsonMap.put(edge, obj)

        return edge
    }

    def layout(KNode node) {
        return node.getData(typeof(KShapeLayout))
    }

    def layout(KPort port) {
        return port.getData(typeof(KShapeLayout))
    }

    def layout(KEdge edge) {
        return edge.getData(typeof(KEdgeLayout))
    }

    /**
     * Maps infinite or NaN values to 0f.
     */
    def float floatValueValid(Double d) {
        if (d.infinite || d.naN) {
            return 0f
        } else {
            return d.floatValue
        }
    }

    def <T> Iterator<T> emptyIfNull(Iterator<T> iterator) {
        if (iterator == null) {
            return Iterators.emptyIterator
        } else {
            iterator
        }
    }
}
