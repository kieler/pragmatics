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

import java.util.LinkedList;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.formats.TransformationData;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * Graph exporter for the GML format.
 *
 * @author msp
 * @author mkr
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class GmlExporter implements IGraphTransformer<KNode, GMLModel> {

    /** property for node and port identifiers. */
    private static final IProperty<String> PROP_ID = new Property<String>("gmlExporter.id");
    
    /**
     * {@inheritDoc}
     */
    public void transform(final TransformationData<KNode, GMLModel> data) {
        // create identifiers for all nodes and ports
        LinkedList<KNode> nodes = new LinkedList<KNode>();
        nodes.add(data.getSourceGraph());
        int nodeId = 0;
        do {
            KNode node = nodes.remove();
            node.getData(KShapeLayout.class).setProperty(PROP_ID, Integer.toString(nodeId++));
            int portId = 0;
            for (KPort port : node.getPorts()) {
                port.getData(KShapeLayout.class).setProperty(PROP_ID, Integer.toString(portId++));
            }
            nodes.addAll(node.getChildren());
        } while (!nodes.isEmpty());
        
        // transform into GML model
        GMLModel gmlModel = new GMLModel();
        Element graphElement = new CollectionElement(null, "graph");
        transform(data.getSourceGraph(), graphElement);
        gmlModel.getElements().add(graphElement);
        data.getTargetGraphs().add(gmlModel);
    }
    
    /**
     * Transform the content of the given parent node.
     * 
     * @param parentNode a parent node
     * @param graphElement the element for the corresponding graph
     */
    private void transform(final KNode parentNode, final Element graphElement) {
        for (KNode knode : parentNode.getChildren()) {
            // transform node
            KShapeLayout nodeLayout = knode.getData(KShapeLayout.class);
            String nodeId = nodeLayout.getProperty(PROP_ID);
            CollectionElement nodeElement = new CollectionElement(graphElement, "node");
            Element id = new StringElement(nodeElement, "id", nodeId);
            nodeElement.getElements().add(id);
            for (KLabel klabel : knode.getLabels()) {
                nodeElement.getElements().add(GmlFormatHandler.createLabel(nodeElement, klabel));
            }
            transform(nodeLayout, nodeElement);
            GmlFormatHandler.getElements(graphElement).add(nodeElement);
            
            for (KPort kport : knode.getPorts()) {
                // transform port
                KShapeLayout portLayout = kport.getData(KShapeLayout.class);
                String portId = portLayout.getProperty(PROP_ID);
                CollectionElement portElement = new CollectionElement(nodeElement, "port");
                id = new StringElement(portElement, "id", portId);
                portElement.getElements().add(id);
                for (KLabel klabel : kport.getLabels()) {
                    portElement.getElements().add(GmlFormatHandler.createLabel(portElement, klabel));
                }
                transform(portLayout, portElement);
                nodeElement.getElements().add(portElement);
            }
            
            if (!knode.getChildren().isEmpty()) {
                // transform subgraph
                Element subgraphElement = new CollectionElement(nodeElement, "graph");
                transform(knode, subgraphElement);
                nodeElement.getElements().add(subgraphElement);
            }
            
            for (KEdge kedge : knode.getOutgoingEdges()) {
                // transform edge
                String sourceId = kedge.getSource().getData(KShapeLayout.class).getProperty(PROP_ID);
                String targetId = kedge.getTarget().getData(KShapeLayout.class).getProperty(PROP_ID);
                CollectionElement edgeElement = new CollectionElement(graphElement, "edge");
                Element source = new StringElement(edgeElement, "source", sourceId);
                edgeElement.getElements().add(source);
                if (kedge.getSourcePort() != null) {
                    String sourcePortId = kedge.getSourcePort().getData(KShapeLayout.class)
                            .getProperty(PROP_ID);
                    Element sourcePort = new StringElement(edgeElement, "sourcePort", sourcePortId);
                    edgeElement.getElements().add(sourcePort);
                }
                Element target = new StringElement(edgeElement, "target", targetId);
                edgeElement.getElements().add(target);
                if (kedge.getTargetPort() != null) {
                    String targetPortId = kedge.getTargetPort().getData(KShapeLayout.class)
                            .getProperty(PROP_ID);
                    Element targetPort = new StringElement(edgeElement, "targetPort", targetPortId);
                    edgeElement.getElements().add(targetPort);
                }
                for (KLabel klabel : kedge.getLabels()) {
                    if (klabel.getText().length() > 0) {
                        edgeElement.getElements().add(GmlFormatHandler.createLabel(edgeElement, klabel));
                    }
                }
                KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
                transform(edgeLayout, edgeElement);
                GmlFormatHandler.getElements(graphElement).add(edgeElement);
            }
        }
    }
    
    /**
     * Transform a shape layout.
     * 
     * @param shapeLayout a shape layout
     * @param parentElement the parent element to which the layout is added
     */
    private void transform(final KShapeLayout shapeLayout, final Element parentElement) {
        if (!shapeLayout.getProperty(LayoutOptions.NO_LAYOUT) && (shapeLayout.getXpos() != 0
                || shapeLayout.getYpos() != 0 || shapeLayout.getWidth() != 0
                || shapeLayout.getHeight() != 0)) {
            CollectionElement graphics = new CollectionElement(parentElement, "graphics");
            if (shapeLayout.getXpos() != 0 || shapeLayout.getYpos() != 0) {
                Element x = new NumberElement(graphics, "x", shapeLayout.getXpos());
                graphics.getElements().add(x);
                Element y = new NumberElement(graphics, "y", shapeLayout.getYpos());
                graphics.getElements().add(y);
            }
            if (shapeLayout.getWidth() != 0 || shapeLayout.getHeight() != 0) {
                Element w = new NumberElement(graphics, "w", shapeLayout.getWidth());
                graphics.getElements().add(w);
                Element h = new NumberElement(graphics, "h", shapeLayout.getHeight());
                graphics.getElements().add(h);
            }
            GmlFormatHandler.getElements(parentElement).add(graphics);
        }
    }
    
    /**
     * Transform an edge layout.
     * 
     * @param edgeLayout an edge layout
     * @param parentElement the parent element to which the layout is added
     */
    private void transform(final KEdgeLayout edgeLayout, final Element parentElement) {
        KPoint sourcePoint = edgeLayout.getSourcePoint();
        KPoint targetPoint = edgeLayout.getTargetPoint();
        if (!edgeLayout.getProperty(LayoutOptions.NO_LAYOUT)
                && (edgeLayout.getBendPoints().size() > 0
                || sourcePoint.getX() != 0 || sourcePoint.getY() != 0
                || targetPoint.getX() != 0 || targetPoint.getY() != 0)) {
            CollectionElement graphics = new CollectionElement(parentElement, "graphics");
            graphics.getElements().add(GmlFormatHandler.createPoint(
                                                        graphics, edgeLayout.getSourcePoint()));
            for (KPoint point : edgeLayout.getBendPoints()) {
                graphics.getElements().add(GmlFormatHandler.createPoint(graphics, point));
            }
            graphics.getElements().add(GmlFormatHandler.createPoint(
                                                        graphics, edgeLayout.getTargetPoint()));
            GmlFormatHandler.getElements(parentElement).add(graphics);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(final TransformationData<KNode, GMLModel> data) {
        throw new UnsupportedOperationException("Layout transfer is not supported for GML export.");
    }

}
