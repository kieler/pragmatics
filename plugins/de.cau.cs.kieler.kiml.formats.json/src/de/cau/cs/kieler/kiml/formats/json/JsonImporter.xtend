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
package de.cau.cs.kieler.kiml.formats.json

import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import com.google.common.collect.HashMultimap
import com.google.common.collect.Maps
import com.google.common.collect.Multimap
import de.cau.cs.kieler.kiml.formats.IGraphTransformer
import de.cau.cs.kieler.kiml.formats.TransformationData
import de.cau.cs.kieler.kiml.formats.TransformationException
import java.util.Map
import org.eclipse.elk.core.data.LayoutMetaDataService
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.EdgeLabelPlacement
import org.eclipse.elk.graph.EMapPropertyHolder
import org.eclipse.elk.graph.ElkEdge
import org.eclipse.elk.graph.ElkEdgeSection
import org.eclipse.elk.graph.ElkGraphElement
import org.eclipse.elk.graph.ElkLabel
import org.eclipse.elk.graph.ElkNode
import org.eclipse.elk.graph.ElkPort
import org.eclipse.elk.graph.ElkShape
import org.eclipse.elk.graph.util.ElkGraphUtil
import org.json.JSONArray
import org.json.JSONObject

/**
 * Importer for graphs in the JSON format.
 * 
 * @see {@link JsonHandler} for more information. 
 *  
 * @author uru
 */
class JsonImporter implements IGraphTransformer<JSONObject, ElkNode> {

    extension JsonExtensions = new JsonExtensions

    private val BiMap<String, ElkNode> nodeIdMap = HashBiMap.create()
    private val BiMap<String, ElkPort> portIdMap = HashBiMap.create()
    private val Map<String, ElkEdge> edgeIdMap = Maps.newHashMap
    private val BiMap<String, ElkEdgeSection> edgeSectionIdMap = HashBiMap.create()

    private val BiMap<ElkNode, JSONObject> nodeJsonMap = HashBiMap.create()
    private val Map<ElkPort, JSONObject> portJsonMap = Maps.newHashMap
    private val Map<ElkEdge, JSONObject> edgeJsonMap = Maps.newHashMap
    private val Map<ElkEdgeSection, JSONObject> edgeSectionJsonMap = Maps.newHashMap
    private val Map<ElkLabel, JSONObject> labelJsonMap = Maps.newHashMap

    /* ---------------------------------------------------------------------------
     *   JSON --> KGraph 
     */
    /**
      * Main entry point for the json to ELK graph transformation. Runs through all elements
      * of the graph (nodes, ports, edges, edge sections) and creates correlating ELK graph elements.
      */
    override transform(TransformationData<JSONObject, ElkNode> data) {
        clearMaps

        // transform the root node along with its children
        val graph = data.sourceGraph
        val root = graph.transformNode(null)
       
        // transform all edges
        graph.transformEdges

        // return the transformed ELK graph
        data.targetGraphs += root
    }

    private def clearMaps() {
        nodeIdMap.clear
        portIdMap.clear
        edgeIdMap.clear
        edgeSectionIdMap.clear
        nodeJsonMap.clear
        portJsonMap.clear
        edgeJsonMap.clear
        edgeSectionJsonMap.clear
    }

    private def transformChildNodes(JSONObject jsonNode, ElkNode parent) {
        jsonNode.optJSONArray("children") => [ children |
            if (children != null) {
                for (i : 0 ..< children.length) {
                    children.optJSONObject(i)?.transformNode(parent)
                }
            }
        ]
    }

    private def ElkNode transformNode(JSONObject jsonNode, ElkNode parent) {
        // create an ElkNode and add it to the parent
        val node = ElkGraphUtil.createNode(parent).register(jsonNode)

        jsonNode.transformProperties(node)
        jsonNode.transformShapeLayout(node)
        jsonNode.transformPorts(node)
        jsonNode.transformLabels(node)
        jsonNode.transformChildNodes(node)

        return node
    }

    private def void transformEdges(JSONObject jsonObj) {
        // the json object represents a node
        val node = nodeJsonMap.inverse.get(jsonObj);
        if (node == null) {
            throw new TransformationException("Unable to find node for json object. This should not happen. Panic!")
        }
        
        // transform edges of the current hierarchy level
        jsonObj.optJSONArray("edges") => [ edges |
            if (edges != null) {
                for (i : 0 ..< edges.length) {
                    edges.optJSONObject(i)?.transformEdge(node)
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

    private def transformEdge(JSONObject jsonObj, ElkNode parent) {
        // Create KEdge
        val edge = ElkGraphUtil.createEdge(parent).register(jsonObj)

        // sources
        jsonObj.optJSONArray("sources") => [ sources |
            if (sources != null) {
                for (i : 0 ..< sources.length) {
                    val sourceElement = shapeById(sources.optString(i));
                    if (sourceElement != null) {
                        edge.sources += sourceElement;
                    }
                }
            }
        ]

        // targets
        jsonObj.optJSONArray("targets") => [ targets |
            if (targets != null) {
                for (i : 0 ..< targets.length) {
                    val targetElement = shapeById(targets.optString(i));
                    if (targetElement != null) {
                        edge.targets += targetElement;
                    }
                }
            }
        ]
        
        // check if ok
        if(edge.sources.empty || edge.targets.empty) {
            throw new TransformationException("Invalid JSON graph format, the targets" 
                + " and sources of a node may not be empty (edge " + jsonObj.optString("id") + ").")
        }
        
        // transform things
        jsonObj.transformEdgeSections(edge)
        jsonObj.transformProperties(edge)
        jsonObj.transformEdgeSections(edge)
        jsonObj.transformLabels(edge)
    }

    private def transformEdgeSections(JSONObject jsonObj, ElkEdge edge) {
        // While iterating over the edge's edge sections, we remember identifiers of the section's incoming and
        // outgoing edge sections. Those references, along with one special case for incoming and outgoing shapes,
        // are resolved later, after all sections have been transformed
        val Multimap<ElkEdgeSection, String> incomingSectionIdentifiers = HashMultimap.create();
        val Multimap<ElkEdgeSection, String> outgoingSectionIdentifiers = HashMultimap.create();
        
        jsonObj.optJSONArray("sections") => [ sections |
            if (sections != null) {
                for (i : 0 ..< sections.length) {
                    sections.optJSONObject(i) => [ jsonSection |
                        val elkSection = ElkGraphUtil.createEdgeSection(edge);
                        
                        fillEdgeSectionCoordinates(jsonSection, elkSection);
                        
                        // Incoming and Outgoing shapes
                        jsonSection.optString("incomingShape") => [ jsonShapeId |
                            if (jsonShapeId != null) {
                                elkSection.incomingShape = shapeById(jsonShapeId);
                            }
                        ]
                        
                        jsonSection.optString("outgoingShape") => [ jsonShapeId |
                            if (jsonShapeId != null) {
                                elkSection.outgoingShape = shapeById(jsonShapeId);
                            }
                        ]
                        
                        // References to incoming and outgoing sections
                        jsonSection.optJSONArray("incomingSections") => [ jsonSectionIds |
                            for (j : 0 ..< jsonSectionIds.length) {
                                incomingSectionIdentifiers.put(elkSection, jsonSectionIds.getString(j))
                            }
                        ]
                        
                        jsonSection.optJSONArray("outgoingSections") => [ jsonSectionIds |
                            for (j : 0 ..< jsonSectionIds.length) {
                                outgoingSectionIdentifiers.put(elkSection, jsonSectionIds.getString(j))
                            }
                        ]
                    ]
                }
            }
        ]
        
        // Fill in references to incoming and outgoing sections
        for (section : incomingSectionIdentifiers.keySet) {
            for (id : incomingSectionIdentifiers.get(section)) {
                val referencedSection = edgeSectionIdMap.get(id);
                if (referencedSection != null) {
                    section.incomingSections += referencedSection;
                } else {
                    throw new TransformationException("Referenced edge section does not exist: " + id);
                }
            }
        }
        
        for (section : outgoingSectionIdentifiers.keySet) {
            for (id : outgoingSectionIdentifiers.get(section)) {
                val referencedSection = edgeSectionIdMap.get(id);
                if (referencedSection != null) {
                    section.outgoingSections += referencedSection;
                } else {
                    throw new TransformationException("Referenced edge section does not exist: " + id);
                }
            }
        }
        
        // Special case: if the edge has only a single source, a single target, and a single edge section which has
        // no incoming and outgoing shapes, set the incoming and outgoing shape to the source and target of the edge,
        // respectively
        if (edge.isConnected && !edge.isHyperedge && edge.sections.size == 1) {
            val section = edge.sections.get(0);
            if (section.incomingShape == null && section.outgoingShape == null) {
                section.incomingShape = edge.sources.get(0);
                section.outgoingShape = edge.targets.get(0);
            }
        }
    }
    
    def fillEdgeSectionCoordinates(JSONObject object, ElkEdgeSection section) {
        object.optJSONObject("startPoint") => [ startPoint |
            if (startPoint != null) {
                startPoint.optDouble("x") => [section.startX = it]
                startPoint.optDouble("y") => [section.startY = it]
            } else {
                throw new TransformationException("All edge sections need a start point.")
            }
        ]
        
        object.optJSONObject("endPoint") => [ endPoint |
            if (endPoint != null) {
                endPoint.optDouble("x") => [section.endX = it]
                endPoint.optDouble("y") => [section.endY = it]
            } else {
                throw new TransformationException("All edge sections need an end point.")
            }
        ]
        
        object.optJSONArray("bendPoints") => [ bendPoints |
            if (bendPoints != null) {
                for (i : 0 ..< bendPoints.length) {
                    bendPoints.optJSONObject(i) => [ bendPoint |
                        ElkGraphUtil.createBendPoint(section, bendPoint.optDouble("x"), bendPoint.optDouble("y"));
                    ]
                }
            }
        ]
    }

    private def transformProperties(JSONObject jsonObject, EMapPropertyHolder layoutData) {
        jsonObject.optJSONObject("properties") => [ props |
            props?.keys.emptyIfNull.forEach [ key |
                val value = props.optString(key as String)
                layoutData.setOption(key as String, value)
            ]
        ]
    }
       
    private def setOption(EMapPropertyHolder e, String id, String value) {
        val optionData = LayoutMetaDataService.instance.getOptionDataBySuffix(id)
        if (optionData != null) {
            val parsed = optionData.parseValue(value)
            if (parsed != null) {
                e.setProperty(optionData, parsed)
            }
        }
    } 

    private def transformLabels(JSONObject jsonObj, ElkGraphElement element) {
        jsonObj.optJSONArray("labels") => [ labels |
            if (labels != null) {
                for (i : 0 ..< labels.length) {
                    val jsonLabel = labels.optJSONObject(i)
                    if (jsonLabel != null) {
                        val label = ElkGraphUtil.createLabel(jsonLabel.optString("text"), element)
                        labelJsonMap.put(label, jsonLabel) 
                        
                        jsonLabel.transformProperties(label)
                        jsonLabel.transformShapeLayout(label)
                        
                        // by default center the label
                        if (label.getProperty(CoreOptions.EDGE_LABELS_PLACEMENT)  == EdgeLabelPlacement.UNDEFINED) {
                            label.setProperty(CoreOptions.EDGE_LABELS_PLACEMENT, EdgeLabelPlacement.CENTER)
                        }
                    }
                }
            }
        ]
    }

    private def transformPorts(JSONObject jsonObj, ElkNode parent) {
        jsonObj.optJSONArray("ports") => [ ports |
            if (ports != null) {
                for (i : 0 ..< ports.length) {
                    ports.optJSONObject(i)?.transformPort(parent)
                }
            }
        ]
    }

    private def transformPort(JSONObject jsonPort, ElkNode parent) {
        // create ElkPort
        val port = ElkGraphUtil.createPort(parent).register(jsonPort)

        // transform things
        jsonPort.transformProperties(port)
        jsonPort.transformShapeLayout(port)
        jsonPort.transformLabels(port)
    }
    
    private def transformShapeLayout(JSONObject jsonObj, ElkShape shape) {
        jsonObj.optJSONObject("location") => [ location |
            if (location != null) {
                location.optDouble("x") => [shape.x = it]
                location.optDouble("y") => [shape.y = it]
            }
        ]
        
        jsonObj.optJSONObject("dimensions") => [ dimensions |
            if (dimensions != null) {
                dimensions.optDouble("width") => [shape.width = it]
                dimensions.optDouble("height") => [shape.height = it]
            }
        ]
    }
    
    private def shapeById(String id) {
        val node = nodeIdMap.get(id);
        val port = portIdMap.get(id);
        
        if (node != null) {
            return node;
        } else if (port != null) {
            return port;
        } else {
            throw new TransformationException("Referenced shape does not exist: " + id);
        }
    }

    /* ---------------------------------------------------------------------------
     *   JSON --> KGraph 
     */
    /**
      * Main entry point if the passed input format has been json and the requested output format is json as well.
      */
    override transferLayout(TransformationData<JSONObject, ElkNode> data) {
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

    private def dispatch transferLayoutInt(ElkNode node) {
        val jsonObj = nodeJsonMap.get(node)

        // transfer positions and dimension
        node.transferShapeLayout(jsonObj)
    }

    private def dispatch transferLayoutInt(ElkPort port) {
        val jsonObj = portJsonMap.get(port)

        // transfer positions and dimension
        port.transferShapeLayout(jsonObj)
    }

    private def dispatch transferLayoutInt(ElkEdge edge) {
        val jsonObj = edgeJsonMap.get(edge)
        
        // what we need to transfer are the edge sections
        val sections = new JSONArray;
        edge.sections.forEach [ elkSection |
            val jsonSection = new JSONObject;
            sections.put(jsonSection);
            
            // Start Point
            val startPoint = new JSONObject;
            startPoint.put("x", elkSection.startX);
            startPoint.put("y", elkSection.startY);
            jsonSection.put("startPoint", startPoint);
            
            // End Point
            val endPoint = new JSONObject;
            endPoint.put("x", elkSection.endX);
            endPoint.put("y", elkSection.endY);
            jsonSection.put("endPoint", endPoint);
            
            // Bend Points
            val bendPoints = new JSONArray;
            elkSection.bendPoints.forEach [ pnt |
                val jsonPnt = new JSONObject
                jsonPnt.put("x", pnt.x)
                jsonPnt.put("y", pnt.y)
                bendPoints.put(jsonPnt)
            ]
            
            // Incoming shape
            if (elkSection.incomingShape != null) {
                jsonSection.put("incomingShape", idByElement(elkSection.incomingShape))
            }
            
            // Outgoing shape
            if (elkSection.outgoingShape != null) {
                jsonSection.put("outgoingShape", idByElement(elkSection.outgoingShape))
            }
            
            // Incoming sections
            if (!elkSection.incomingSections.empty) {
                val incomingSections = new JSONArray;
                elkSection.incomingSections.forEach [ sec |
                    incomingSections.put(idByElement(sec));
                ];
            }
            
            // Incoming sections
            if (!elkSection.incomingSections.empty) {
                val incomingSections = new JSONArray;
                elkSection.incomingSections.forEach [ sec |
                    incomingSections.put(idByElement(sec));
                ];
                jsonSection.put("incomingSections", incomingSections);
            }
            
            // Outgoing sections
            if (!elkSection.outgoingSections.empty) {
                val outgoingSections = new JSONArray;
                elkSection.outgoingSections.forEach [ sec |
                    outgoingSections.put(idByElement(sec));
                ];
                jsonSection.put("outgoingSections", outgoingSections);
            }
            
            jsonSection.transformProperties(elkSection);
        ];
        
        jsonObj?.put("sections", sections);
    }
    
    private def dispatch transferLayoutInt(ElkLabel label) {
        val jsonObj = labelJsonMap.get(label)

        // transfer positions and dimension
        label.transferShapeLayout(jsonObj)
    }
    
    private def dispatch transferLayoutInt(Object obj) {
        // don't care about the rest
    }

    private def transferShapeLayout(ElkShape shape, JSONObject jsonObj) {
        val location = new JSONObject;
        location.put("x", shape.x);
        location.put("y", shape.y);
        jsonObj?.put("location", location);
        
        val dimensions = new JSONObject;
        dimensions.put("width", shape.width);
        dimensions.put("height", shape.height);
        jsonObj?.put("dimensions", dimensions);
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

    /* ---------------------------------------------------------------------------
     *   Convenience methods
     */
    def ElkNode register(ElkNode node, JSONObject obj) {
        val id = obj.optString("id")
        if (id == null) {
            throw new TransformationException("Invalid json graph format. Node is missing 'id' key.")
        }

        nodeIdMap.put(id, node)
        nodeJsonMap.put(node, obj)

        return node
    }

    def ElkPort register(ElkPort port, JSONObject obj) {
        val id = obj.optString("id")
        if (id == null) {
            throw new TransformationException("Invalid json graph format. Port is missing 'id' key.")
        }

        portIdMap.put(id, port)
        portJsonMap.put(port, obj)

        return port
    }

    def ElkEdge register(ElkEdge edge, JSONObject obj) {
        val id = obj.optString("id")
        if (id == null) {
            throw new TransformationException("Invalid json graph format. Edge is missing 'id' key.")
        }

        edgeIdMap.put(id, edge)
        edgeJsonMap.put(edge, obj)

        return edge
    }

    def ElkEdgeSection register(ElkEdgeSection edgeSection, JSONObject obj) {
        val id = obj.optString("id")
        if (id == null) {
            throw new TransformationException("Invalid json graph format. Edge section is missing 'id' key.")
        }

        edgeSectionIdMap.put(id, edgeSection)
        edgeSectionJsonMap.put(edgeSection, obj)

        return edgeSection
    }
}
