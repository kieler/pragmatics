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
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.util.KimlUtil
import java.util.Map
import org.json.JSONArray
import org.json.JSONObject
import de.cau.cs.kieler.core.kgraph.KLabel
import de.cau.cs.kieler.kiml.formats.IGraphTransformer
import de.cau.cs.kieler.kiml.formats.TransformationException
import de.cau.cs.kieler.kiml.formats.TransformationData
import de.cau.cs.kieler.kiml.LayoutMetaDataService

/**
 * Importer for graphs in the json format.
 * 
 * @see {@link JsonHandler} for more information. 
 *  
 * @author uru
 */
class JsonImporter implements IGraphTransformer<JSONObject, KNode> {

    extension JsonExtensions = new JsonExtensions

    private val Map<String, KNode> nodeIdMap = Maps.newHashMap
    private val Map<String, KPort> portIdMap = Maps.newHashMap
    private val Map<String, KEdge> edgeIdMap = Maps.newHashMap

    private val Map<KNode, JSONObject> nodeJsonMap = Maps.newHashMap
    private val Map<KPort, JSONObject> portJsonMap = Maps.newHashMap
    private val Map<KEdge, JSONObject> edgeJsonMap = Maps.newHashMap
    private val Map<KLabel, JSONObject> labelJsonMap = Maps.newHashMap

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

        // transform the root node along with its children
        val root = graph.transformNode(null)
       
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
                for (i : 0 ..< children.length) {
                    children.optJSONObject(i)?.transformNode(parent)
                }
            }
        ]
    }

    private def KNode transformNode(JSONObject jsonNode, KNode parent) {

        // create a KNode and add it to the parent
        val node = KimlUtil.createInitializedNode.register(jsonNode)
        
        if (parent != null) {
            parent.children += node
        }

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

    private def void transformEdges(JSONObject jsonObj) {

        // transform edges of the current hierarchy level
        jsonObj.optJSONArray("edges") => [ edges |
            if (edges != null) {
                for (i : 0 ..< edges.length) {
                    edges.optJSONObject(i)?.transformEdge
                }
            }
        ]

        // transform the edges of all child nodes
        jsonObj.optJSONArray("children") => [ children |
            if (children != null) {
                for (i : 0 ..< children.length) {
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
        
        // check if ok
        if(edge.source == null || edge.target == null) {
            throw new TransformationException("Invalid JSON graph format, the target" 
                + " and source of a node may not be null (edge " + jsonObj.optString("id") + ").")
        }
        
        // labels
        jsonObj.transformLabels(edge)

        // bend points
        jsonObj.transformEdgeLayout(edge.layout)
    }

    private def transformEdgeLayout(JSONObject jsonObj, KEdgeLayout edgeLayout) {

        // src
        jsonObj.optJSONObject("sourcePoint") => [ srcPnt |
            if (srcPnt != null) {
                srcPnt.optDouble("x") => [edgeLayout.sourcePoint.x = it.floatValueValid]
                srcPnt.optDouble("y") => [edgeLayout.sourcePoint.y = it.floatValueValid]
            }
        ]

        // tgt
        jsonObj.optJSONObject("targetPoint") => [ tgtPnt |
            if (tgtPnt != null) {
                tgtPnt.optDouble("x") => [edgeLayout.targetPoint.x = it.floatValueValid]
                tgtPnt.optDouble("y") => [edgeLayout.targetPoint.y = it.floatValueValid]
            }
        ]

        // bend points
        jsonObj.optJSONArray("bendPoints") => [ bends |
            if (bends != null) {
                for (i : 0 ..< bends.length) {
                    val jsonBend = bends.optJSONObject(i)
                    val bend = KLayoutDataFactory.eINSTANCE.createKPoint
                    jsonBend.optDouble("x") => [bend.x = it.floatValueValid]
                    jsonBend.optDouble("y") => [bend.y = it.floatValueValid]
                    edgeLayout.bendPoints += bend
                }
            }
        ]
    }

    private def transformProperties(JSONObject jsonObject, KLayoutData layoutData) {
        val metaService = LayoutMetaDataService.getInstance()
      
        jsonObject.optJSONObject("properties") => [ props |
            props?.keys.emptyIfNull.forEach [ key |
                val value = props.optString(key as String)
                KimlUtil.loadDataElement(metaService, layoutData, key as String, value)
            ]
        ]
    }

    private def transformLabels(JSONObject jsonObj, KLabeledGraphElement element) {
        jsonObj.optJSONArray("labels") => [ labels |
            if (labels != null) {
                for (i : 0 ..< labels.length) {
                    val jsonLabel = labels.optJSONObject(i)
                    if (jsonLabel != null) {
                        val label = KimlUtil.createInitializedLabel(element)
                        labelJsonMap.put(label, jsonLabel) 
                        
                        label.text = jsonLabel.optString("text")
                        
                        jsonLabel.transformProperties(label.layout)
                        
                        // by default center the label
                        if (label.layout.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT) 
                                == EdgeLabelPlacement.UNDEFINED) {
                            label.layout.setProperty(LayoutOptions.EDGE_LABEL_PLACEMENT,
                                    EdgeLabelPlacement.CENTER)
                        }
                    }
                }
            }
        ]
    }

    private def transformPorts(JSONObject jsonObj, KNode parent) {
        jsonObj.optJSONArray("ports") => [ ports |
            if (ports != null) {
                for (i : 0 ..< ports.length) {
                    ports.optJSONObject(i)?.transformPort(parent)
                }
            }
        ]
    }

    private def transformPort(JSONObject jsonPort, KNode parent) {

        // create KPort
        val port = KimlUtil.createInitializedPort.register(jsonPort)
        parent.ports += port

        // position and dimension
        jsonPort.transformShapeLayout(port.layout)

        // labels 
        jsonPort.transformLabels(port)

        // properties
        jsonPort.transformProperties(port.layout)
    }
    
    private def transformShapeLayout(JSONObject jsonObj, KShapeLayout shapeLayout) {
        jsonObj.optDouble("x") => [shapeLayout.xpos = it.floatValueValid]
        jsonObj.optDouble("y") => [shapeLayout.ypos = it.floatValueValid]
        jsonObj.optDouble("width") => [shapeLayout.width = it.floatValueValid]
        jsonObj.optDouble("height") => [shapeLayout.height = it.floatValueValid]
        
        // transfer padding
        jsonObj.optJSONObject("padding") => [ padding |
            if (padding != null) {
                val insets = shapeLayout.insets
                padding.optDouble("left") => [insets.left = it.floatValueValid]
                padding.optDouble("right") => [insets.right = it.floatValueValid]
                padding.optDouble("top") => [insets.top = it.floatValueValid]
                padding.optDouble("bottom") => [insets.bottom = it.floatValueValid]
                println(insets)
            }
        ]
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
            // transfer layout of root
            graph.transferLayoutInt
            // and of any child of any type
            graph.eAllContents.forEach [ element |
                element.transferLayoutInt
            ]
        ]
    }

    private def dispatch transferLayoutInt(KNode node) {
        val jsonObj = nodeJsonMap.get(node)

        // transfer positions and dimension
        node.layout.transferShapeLayout(jsonObj)

    }

    private def dispatch transferLayoutInt(KPort port) {
        val jsonObj = portJsonMap.get(port)

        // transfer positions and dimension
        port.layout.transferShapeLayout(jsonObj)
    }

    private def dispatch transferLayoutInt(KEdge edge) {
        val jsonObj = edgeJsonMap.get(edge)

        // source
        val sourcePoint = new JSONObject
        sourcePoint.put("x", edge.layout.sourcePoint.x)
        sourcePoint.put("y", edge.layout.sourcePoint.y)
        jsonObj?.put("sourcePoint", sourcePoint)

        // target        
        val targetPoint = new JSONObject
        targetPoint.put("x", edge.layout.targetPoint.x)
        targetPoint.put("y", edge.layout.targetPoint.y)
        jsonObj?.put("targetPoint", targetPoint)

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

    private def dispatch transferLayoutInt(KLabel label) {
        val jsonObj = labelJsonMap.get(label)

        // transfer positions and dimension
        label.layout.transferShapeLayout(jsonObj)
    }
    
    private def dispatch transferLayoutInt(Object obj) {
        // don't care about the rest
    }

    private def transferShapeLayout(KShapeLayout layout, JSONObject jsonObj) {
        // pos and dimension
        jsonObj?.put("x", layout.xpos)
        jsonObj?.put("y", layout.ypos)
        jsonObj?.put("width", layout.width)
        jsonObj?.put("height", layout.height)
        
        // padding
        val insets = layout.insets
        if (insets != null) {
          var padding = jsonObj?.optJSONObject("padding")
          if (padding == null) {
              padding = new JSONObject()
              jsonObj?.put("padding", padding)  
          }
          padding?.put("left", insets.left)
          padding?.put("top", insets.top)
          padding?.put("right", insets.right)
          padding?.put("bottom", insets.bottom)
        }
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
}
