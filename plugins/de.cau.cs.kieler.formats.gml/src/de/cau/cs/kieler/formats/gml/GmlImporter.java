/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.formats.gml;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.PortConstraints;
import org.eclipse.elk.graph.ElkBendPoint;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkGraphElement;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.formats.IGraphTransformer;
import de.cau.cs.kieler.formats.TransformationData;

/**
 * Graph importer for the GML format.
 * 
 * @author msp
 * @author mkr
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class GmlImporter implements IGraphTransformer<GMLModel, ElkNode> {

    /** map of GML node identifiers to KNodes. */
    private static final IProperty<Map<String, ElkNode>> NODE_ID_MAP
            = new Property<>("gmlImporter.nodeIdMap");
    /** GML element attached to each new KGraphElement. */
    private static final IProperty<Element> PROP_ELEM
            = new Property<>("gmlImporter.element");
    /** GML collection element attached to each new KGraphElement. */
    private static final IProperty<CollectionElement> PROP_COLLECT_ELEM
            = new Property<>("gmlImporter.collectionElement");
    
    /**
     * {@inheritDoc}
     */
    public void transform(final TransformationData<GMLModel, ElkNode> data) {
        GMLModel gmlModel = data.getSourceGraph();
        for (Element element : gmlModel.getElements()) {
            if ("graph".equalsIgnoreCase(element.getKey())) {
                ElkNode parent = ElkGraphUtil.createGraph();
                Map<String, ElkNode> nodeIdMap = Maps.newHashMap();
                data.setProperty(NODE_ID_MAP, nodeIdMap);
                transformGraph(element, parent, data);
                data.getTargetGraphs().add(parent);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(final TransformationData<GMLModel, ElkNode> data) {
        for (ElkNode layoutNode : data.getTargetGraphs()) {
            applyLayout(layoutNode);
        }
    }
    
    //---------- Transformation GML to KGraph ----------//      
    /**
     * Transform the contents of a GML graph or subgraph into a KNode.
     * 
     * @param graph a GML graph
     * @param parent the corresponding KNode
     */
    private void transformGraph(final Element graph, final ElkNode parent,
            final TransformationData<GMLModel, ElkNode> transData) {
        
        for (Element element : GmlFormatHandler.getElements(graph)) {
            if ("node".equalsIgnoreCase(element.getKey())) {
                // transform a node
                String id = null;
                for (Element e : GmlFormatHandler.getElements(element)) {
                    if ("id".equalsIgnoreCase(e.getKey())) {
                        id = e.getValue();
                        break;
                    }
                }
                ElkNode elknode = transformNode(id, parent, transData);
                elknode.setProperty(PROP_ELEM, element);
                for (Element e : GmlFormatHandler.getElements(element)) {
                    if ("graph".equalsIgnoreCase(e.getKey())) {
                        transformGraph(e, elknode, transData);
                    } else if ("label".equalsIgnoreCase(e.getKey())) {
                        ElkGraphUtil.createLabel(e.getValue(), elknode);
                    } else if ("graphics".equalsIgnoreCase(e.getKey())) {
                        for (Element f : GmlFormatHandler.getElements(element)) {
                            try {
                                if ("x".equals(f.getKey())) {
                                    elknode.setX(Double.parseDouble(f.getValue()));
                                } else if ("y".equals(f.getKey())) {
                                    elknode.setY(Double.parseDouble(f.getValue()));
                                } else if ("w".equals(f.getKey())) {
                                    elknode.setWidth(Double.parseDouble(f.getValue()));
                                } else if ("h".equals(f.getKey())) {
                                    elknode.setHeight(Double.parseDouble(f.getValue()));
                                }
                            } catch (NumberFormatException exception) {
                                // ignore exception
                            }
                        }
                    } else {
                        setOption(elknode, e.getKey(), e.getValue());
                    }
                }
            } else if ("edge".equalsIgnoreCase(element.getKey())) {
                // transform an edge
                String sourceid = null, targetid = null;
                for (Element e : GmlFormatHandler.getElements(element)) {
                    if ("source".equalsIgnoreCase(e.getKey())) {
                        sourceid = e.getValue();
                    } else if ("target".equalsIgnoreCase(e.getKey())) {
                        targetid = e.getValue();
                    }
                }
                if (sourceid != null && targetid != null) {
                    ElkNode source = transformNode(sourceid, parent, transData);
                    ElkNode target = transformNode(targetid, parent, transData);
                    ElkEdge elkedge = ElkGraphUtil.createSimpleEdge(source, target);
                    elkedge.setProperty(PROP_ELEM, element);
                    for (Element e : GmlFormatHandler.getElements(element)) {
                        if ("label".equalsIgnoreCase(e.getKey())) {
                            ElkGraphUtil.createLabel(e.getValue(), elkedge);
                        } else {
                            setOption(elkedge, e.getKey(), e.getValue());
                        }
                    }
                }
            } else if ("label".equalsIgnoreCase(element.getKey())) {
                ElkGraphUtil.createLabel(element.getValue(), parent);
            }
        }
    }
    
    /**
     * Transforms a single node, if not already done before.
     * 
     * @param nodeId a node identifier
     * @param parent the parent where the new KNode is stored
     * @return a KNode instance
     */
    private ElkNode transformNode(final String nodeId, final ElkNode parent,
            final TransformationData<GMLModel, ElkNode> transData) {
        
        Map<String, ElkNode> nodeIdMap = transData.getProperty(NODE_ID_MAP);
        ElkNode elknode = nodeIdMap.get(nodeId);
        if (elknode == null) {
            elknode = ElkGraphUtil.createNode(parent);
            elknode.setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FREE);
            if (nodeId != null) {
                nodeIdMap.put(nodeId, elknode);
            }
        }
        return elknode;
    }
    
    
    /*---------- Layout Transfer KGraph to GML ----------*/    
    /**
     * Apply layout for the given parent node and all contained subgraphs.
     * 
     * @param parentNode a parent node
     */
    private void applyLayout(final ElkNode parentNode) {
        for (ElkNode elknode : parentNode.getChildren()) {
            CollectionElement nodeElement = elknode.getProperty(PROP_COLLECT_ELEM);
            if (nodeElement != null) {
                // apply node layout
                Element graphics = null;
                for (Element e : nodeElement.getElements()) {
                    if ("graphics".equalsIgnoreCase(e.getKey())) {
                        graphics = e;
                        break;
                    }
                }
                if (graphics == null) {
                    graphics = new CollectionElement(nodeElement, "graphics");
                    nodeElement.getElements().add(graphics);
                } 
                NumberElement x = null, y = null, w = null, h = null;
                List<Element> liElem = GmlFormatHandler.getElements(graphics);
                ListIterator<Element> graphicsIter = liElem.listIterator();
                while (graphicsIter.hasNext()) {
                    Element e = graphicsIter.next();
                    if ("x".equals(e.getKey())) {
                        x = new NumberElement(graphics, "x", elknode.getX());
                    } else if ("y".equals(e.getKey())) {
                        y = new NumberElement(graphics, "y", elknode.getY());
                    } else if ("w".equals(e.getKey())) {
                        w = new NumberElement(graphics, "w", elknode.getWidth());
                    } else if ("h".equals(e.getKey())) {
                        h = new NumberElement(graphics, "h", elknode.getHeight());
                    }
                    graphicsIter.remove();
                }
                
                // set x coordinate position
                if (x == null) {
                    x = new NumberElement(graphics, "x", elknode.getX());
                }
                GmlFormatHandler.getElements(graphics).add(x);
                
                // set y coordinate position
                if (y == null) {
                    y = new NumberElement(graphics, "y", elknode.getY());
                }
                GmlFormatHandler.getElements(graphics).add(x);
                
                // set width
                if (w == null) {
                    w = new NumberElement(graphics, "w", elknode.getWidth());
                }
                GmlFormatHandler.getElements(graphics).add(x);
                
                // set height  
                if (h == null) {
                    h = new NumberElement(graphics, "h", elknode.getHeight());
                }
                GmlFormatHandler.getElements(graphics).add(x);
            }
            
            for (ElkEdge elkedge : ElkGraphUtil.allOutgoingEdges(elknode)) {
                CollectionElement edgeElement = elkedge.getProperty(PROP_COLLECT_ELEM);
                if (edgeElement != null) {
                    // apply edge layout
                    CollectionElement graphics = null;
                    for (Element e : GmlFormatHandler.getElements(edgeElement)) {
                        if ("graphics".equalsIgnoreCase(e.getKey())) {
                            graphics = (CollectionElement) e;
                            break;
                        }
                    }
                    if (graphics == null) {
                        graphics = new CollectionElement(edgeElement, "graphics");
                        edgeElement.getElements().add(graphics);
                    }
                    
                    // remove old points
                    Iterator<Element> elementIter = GmlFormatHandler.getElements(graphics).iterator();
                    while (elementIter.hasNext()) {
                        Element e = elementIter.next();
                        if ("point".equalsIgnoreCase(e.getKey())) {
                            elementIter.remove();
                        }
                    }
                    
                    // create new points
                    if (!elkedge.getSections().isEmpty()) {
                        ElkEdgeSection edgeSection = elkedge.getSections().get(0);
                        
                        GmlFormatHandler.getElements(graphics).add(GmlFormatHandler.createPoint(
                                graphics, edgeSection.getStartX(), edgeSection.getStartY()));
                        
                        for (ElkBendPoint point : edgeSection.getBendPoints()) {
                            GmlFormatHandler.getElements(graphics).add(
                                    GmlFormatHandler.createPoint(
                                            graphics, point.getX(), point.getY()));
                        }
                        
                        GmlFormatHandler.getElements(graphics).add(GmlFormatHandler.createPoint(
                                graphics, edgeSection.getEndX(), edgeSection.getEndY()));
                    }
                }
            }
            
            // apply subgraph layout
            applyLayout(elknode);
        }
    }

    /**
     * Set a layout option using a serialized key / value pair.
     * 
     * @param graphElement the graph data instance to modify
     * @param id the layout option identifier
     * @param value the value for the layout option
     */
    private static void setOption(final ElkGraphElement graphElement, final String id, final String value) {
        LayoutOptionData optionData = LayoutMetaDataService.getInstance().getOptionData(id);
        if (optionData != null) {
            Object obj = optionData.parseValue(value);
            if (obj != null) {
                graphElement.setProperty(optionData, obj);
            }
        }
    }
}
