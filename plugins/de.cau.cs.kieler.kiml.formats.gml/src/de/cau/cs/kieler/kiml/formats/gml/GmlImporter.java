/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.formats.gml;

import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.formats.gml.gml.Element;
import de.cau.cs.kieler.kiml.formats.gml.gml.GmlFactory;
import de.cau.cs.kieler.kiml.formats.gml.gml.GmlModel;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Graph importer for the GML format.
 * 
 * @author msp
 */
public class GmlImporter implements IGraphTransformer<GmlModel, KNode> {

    /** map of GML node identifiers to KNodes. */
    private static final IProperty<Map<String, KNode>> NODE_ID_MAP
            = new Property<Map<String, KNode>>("gmlImporter.nodeIdMap");
    /** GML element attached to each new KGraphElement. */
    private static final IProperty<Element> PROP_ELEM
            = new Property<Element>("gmlImporter.element");
    
    /**
     * {@inheritDoc}
     */
    public void transform(final TransformationData<GmlModel, KNode> data) {
        GmlModel gmlModel = data.getSourceGraph();
        for (Element element : gmlModel.getElements()) {
            if ("graph".equalsIgnoreCase(element.getKey())) {
                KNode parent = KimlUtil.createInitializedNode();
                Map<String, KNode> nodeIdMap = Maps.newHashMap();
                data.setProperty(NODE_ID_MAP, nodeIdMap);
                transformGraph(element, parent, data);
                data.getTargetGraphs().add(parent);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(final TransformationData<GmlModel, KNode> data) {
        for (KNode layoutNode : data.getTargetGraphs()) {
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
    private void transformGraph(final Element graph, final KNode parent,
            final TransformationData<GmlModel, KNode> transData) {
        for (Element element : graph.getElements()) {
            if ("node".equalsIgnoreCase(element.getKey())) {
                // transform a node
                String id = null;
                for (Element e : element.getElements()) {
                    if ("id".equalsIgnoreCase(e.getKey())) {
                        id = e.getValue();
                        break;
                    }
                }
                KNode knode = transformNode(id, parent, transData);
                KShapeLayout nodeLayout = knode.getData(KShapeLayout.class);
                nodeLayout.setProperty(PROP_ELEM, element);
                for (Element e : element.getElements()) {
                    if ("graph".equalsIgnoreCase(e.getKey())) {
                        transformGraph(e, knode, transData);
                    } else if ("label".equalsIgnoreCase(e.getKey())) {
                        KLabel label = KimlUtil.createInitializedLabel(knode);
                        label.setText(e.getValue());
                    } else if ("graphics".equalsIgnoreCase(e.getKey())) {
                        for (Element f : e.getElements()) {
                            try {
                                if ("x".equals(f.getKey())) {
                                    nodeLayout.setXpos(Float.parseFloat(f.getValue()));
                                } else if ("y".equals(f.getKey())) {
                                    nodeLayout.setYpos(Float.parseFloat(f.getValue()));
                                } else if ("w".equals(f.getKey())) {
                                    nodeLayout.setWidth(Float.parseFloat(f.getValue()));
                                } else if ("h".equals(f.getKey())) {
                                    nodeLayout.setHeight(Float.parseFloat(f.getValue()));
                                }
                            } catch (NumberFormatException exception) {
                                // ignore exception
                            }
                        }
                    } else {
                        KimlUtil.setOption(nodeLayout, e.getKey(), e.getValue());
                    }
                }
            } else if ("edge".equalsIgnoreCase(element.getKey())) {
                // transform an edge
                String sourceid = null, targetid = null;
                for (Element e : element.getElements()) {
                    if ("source".equalsIgnoreCase(e.getKey())) {
                        sourceid = e.getValue();
                    } else if ("target".equalsIgnoreCase(e.getKey())) {
                        targetid = e.getValue();
                    }
                }
                if (sourceid != null && targetid != null) {
                    KNode source = transformNode(sourceid, parent, transData);
                    KNode target = transformNode(targetid, parent, transData);
                    KEdge kedge = KimlUtil.createInitializedEdge();
                    kedge.setSource(source);
                    kedge.setTarget(target);
                    KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
                    edgeLayout.setProperty(PROP_ELEM, element);
                    for (Element e : element.getElements()) {
                        if ("label".equalsIgnoreCase(e.getKey())) {
                            KLabel label = KimlUtil.createInitializedLabel(kedge);
                            label.setText(e.getValue());
                        } else {
                            KimlUtil.setOption(edgeLayout, e.getKey(), e.getValue());
                        }
                    }
                }
            } else if ("label".equalsIgnoreCase(element.getKey())) {
                KLabel klabel = KimlUtil.createInitializedLabel(parent);
                klabel.setText(element.getValue());
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
    private KNode transformNode(final String nodeId, final KNode parent,
            final TransformationData<GmlModel, KNode> transData) {
        Map<String, KNode> nodeIdMap = transData.getProperty(NODE_ID_MAP);
        KNode knode = nodeIdMap.get(nodeId);
        if (knode == null) {
            knode = KimlUtil.createInitializedNode();
            KShapeLayout nodeLayout = knode.getData(KShapeLayout.class);
            nodeLayout.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FREE);
            knode.setParent(parent);
            if (nodeId != null) {
                nodeIdMap.put(nodeId, knode);
            }
        }
        return knode;
    }
    
    
    /*---------- Layout Transfer KGraph to GML ----------*/
    
    /**
     * Apply layout for the given parent node and all contained subgraphs.
     * 
     * @param parentNode a parent node
     */
    private void applyLayout(final KNode parentNode) {
        for (KNode knode : parentNode.getChildren()) {
            KShapeLayout knodeLayout = knode.getData(KShapeLayout.class);
            Element nodeElement = knodeLayout.getProperty(PROP_ELEM);
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
                    graphics = GmlFactory.eINSTANCE.createElement();
                    graphics.setKey("graphics");
                    nodeElement.getElements().add(graphics);
                }
                Element x = null, y = null, w = null, h = null;
                for (Element e : graphics.getElements()) {
                    if ("x".equals(e.getKey())) {
                        x = e;
                    } else if ("y".equals(e.getKey())) {
                        y = e;
                    } else if ("w".equals(e.getKey())) {
                        w = e;
                    } else if ("h".equals(e.getKey())) {
                        h = e;
                    }
                }
                // set x coordinate position
                if (x == null) {
                    x = GmlFactory.eINSTANCE.createElement();
                    x.setKey("x");
                    graphics.getElements().add(x);
                }
                x.setValue(Float.toString(knodeLayout.getXpos()));
                // set y coordinate position
                if (y == null) {
                    y = GmlFactory.eINSTANCE.createElement();
                    y.setKey("y");
                    graphics.getElements().add(y);
                }
                y.setValue(Float.toString(knodeLayout.getYpos()));
                // set width
                if (w == null) {
                    w = GmlFactory.eINSTANCE.createElement();
                    w.setKey("w");
                    graphics.getElements().add(w);
                }
                w.setValue(Float.toString(knodeLayout.getWidth()));
                // set height
                if (h == null) {
                    h = GmlFactory.eINSTANCE.createElement();
                    h.setKey("h");
                    graphics.getElements().add(h);
                }
                h.setValue(Float.toString(knodeLayout.getHeight()));
            }
            
            for (KEdge kedge : knode.getOutgoingEdges()) {
                KEdgeLayout kedgeLayout = kedge.getData(KEdgeLayout.class);
                Element edgeElement = kedgeLayout.getProperty(PROP_ELEM);
                if (edgeElement != null) {
                    // apply edge layout
                    Element graphics = null;
                    for (Element e : edgeElement.getElements()) {
                        if ("graphics".equalsIgnoreCase(e.getKey())) {
                            graphics = e;
                            break;
                        }
                    }
                    if (graphics == null) {
                        graphics = GmlFactory.eINSTANCE.createElement();
                        graphics.setKey("graphics");
                        edgeElement.getElements().add(graphics);
                    }
                    // remove old points
                    Iterator<Element> elementIter = graphics.getElements().iterator();
                    while (elementIter.hasNext()) {
                        Element e = elementIter.next();
                        if ("point".equalsIgnoreCase(e.getKey())) {
                            elementIter.remove();
                        }
                    }
                    // create new points
                    graphics.getElements().add(GmlHandler.createPoint(kedgeLayout.getSourcePoint()));
                    for (KPoint point : kedgeLayout.getBendPoints()) {
                        graphics.getElements().add(GmlHandler.createPoint(point));
                    }
                    graphics.getElements().add(GmlHandler.createPoint(kedgeLayout.getTargetPoint()));
                }
            }
            
            // apply subgraph layout
            applyLayout(knode);
        }
    }

}
