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
import de.cau.cs.kieler.kiml.formats.gml.gml.Element;
import de.cau.cs.kieler.kiml.formats.gml.gml.GmlFactory;
import de.cau.cs.kieler.kiml.formats.gml.gml.GmlModel;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;

/**
 * Graph exporter for the GML format.
 *
 * @author msp
 */
public class GmlExporter implements IGraphTransformer<KNode, GmlModel> {

    /** property for node and port identifiers. */
    private static final IProperty<String> PROP_ID = new Property<String>("gmlExporter.id");
    
    /**
     * {@inheritDoc}
     */
    public void transform(final TransformationData<KNode, GmlModel> data) {
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
        GmlModel gmlModel = GmlFactory.eINSTANCE.createGmlModel();
        Element graphElement = GmlFactory.eINSTANCE.createElement();
        graphElement.setKey("graph");
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
            Element nodeElement = GmlFactory.eINSTANCE.createElement();
            nodeElement.setKey("node");
            Element id = GmlFactory.eINSTANCE.createElement();
            id.setKey("id");
            id.setValue(nodeId);
            nodeElement.getElements().add(id);
            for (KLabel klabel : knode.getLabels()) {
                nodeElement.getElements().add(GmlHandler.createLabel(klabel));
            }
            transform(nodeLayout, nodeElement);
            graphElement.getElements().add(nodeElement);
            
            for (KPort kport : knode.getPorts()) {
                // transform port
                KShapeLayout portLayout = kport.getData(KShapeLayout.class);
                String portId = portLayout.getProperty(PROP_ID);
                Element portElement = GmlFactory.eINSTANCE.createElement();
                portElement.setKey("port");
                id = GmlFactory.eINSTANCE.createElement();
                id.setKey("id");
                id.setValue(portId);
                portElement.getElements().add(id);
                for (KLabel klabel : kport.getLabels()) {
                    portElement.getElements().add(GmlHandler.createLabel(klabel));
                }
                transform(portLayout, portElement);
                nodeElement.getElements().add(portElement);
            }
            
            if (!knode.getChildren().isEmpty()) {
                // transform subgraph
                Element subgraphElement = GmlFactory.eINSTANCE.createElement();
                subgraphElement.setKey("graph");
                transform(knode, subgraphElement);
                nodeElement.getElements().add(subgraphElement);
            }
            
            for (KEdge kedge : knode.getOutgoingEdges()) {
                // transform edge
                String sourceId = kedge.getSource().getData(KShapeLayout.class).getProperty(PROP_ID);
                String targetId = kedge.getTarget().getData(KShapeLayout.class).getProperty(PROP_ID);
                Element edgeElement = GmlFactory.eINSTANCE.createElement();
                edgeElement.setKey("edge");
                Element source = GmlFactory.eINSTANCE.createElement();
                source.setKey("source");
                source.setValue(sourceId);
                edgeElement.getElements().add(source);
                if (kedge.getSourcePort() != null) {
                    String sourcePortId = kedge.getSourcePort().getData(KShapeLayout.class)
                            .getProperty(PROP_ID);
                    Element sourcePort = GmlFactory.eINSTANCE.createElement();
                    sourcePort.setKey("sourcePort");
                    sourcePort.setValue(sourcePortId);
                    edgeElement.getElements().add(sourcePort);
                }
                Element target = GmlFactory.eINSTANCE.createElement();
                target.setKey("target");
                target.setValue(targetId);
                edgeElement.getElements().add(target);
                if (kedge.getTargetPort() != null) {
                    String targetPortId = kedge.getTargetPort().getData(KShapeLayout.class)
                            .getProperty(PROP_ID);
                    Element targetPort = GmlFactory.eINSTANCE.createElement();
                    targetPort.setKey("targetPort");
                    targetPort.setValue(targetPortId);
                    edgeElement.getElements().add(targetPort);
                }
                for (KLabel klabel : kedge.getLabels()) {
                    if (klabel.getText().length() > 0) {
                        edgeElement.getElements().add(GmlHandler.createLabel(klabel));
                    }
                }
                KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
                transform(edgeLayout, edgeElement);
                graphElement.getElements().add(edgeElement);
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
            Element graphics = GmlFactory.eINSTANCE.createElement();
            graphics.setKey("graphics");
            if (shapeLayout.getXpos() != 0 || shapeLayout.getYpos() != 0) {
                Element x = GmlFactory.eINSTANCE.createElement();
                x.setKey("x");
                x.setValue(Float.toString(shapeLayout.getXpos()));
                graphics.getElements().add(x);
                Element y = GmlFactory.eINSTANCE.createElement();
                y.setKey("y");
                y.setValue(Float.toString(shapeLayout.getYpos()));
                graphics.getElements().add(y);
            }
            if (shapeLayout.getWidth() != 0 || shapeLayout.getHeight() != 0) {
                Element w = GmlFactory.eINSTANCE.createElement();
                w.setKey("w");
                w.setValue(Float.toString(shapeLayout.getWidth()));
                graphics.getElements().add(w);
                Element h = GmlFactory.eINSTANCE.createElement();
                h.setKey("h");
                h.setValue(Float.toString(shapeLayout.getHeight()));
                graphics.getElements().add(h);
            }
            parentElement.getElements().add(graphics);
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
            Element graphics = GmlFactory.eINSTANCE.createElement();
            graphics.setKey("graphics");
            graphics.getElements().add(GmlHandler.createPoint(edgeLayout.getSourcePoint()));
            for (KPoint point : edgeLayout.getBendPoints()) {
                graphics.getElements().add(GmlHandler.createPoint(point));
            }
            graphics.getElements().add(GmlHandler.createPoint(edgeLayout.getTargetPoint()));
            parentElement.getElements().add(graphics);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(final TransformationData<KNode, GmlModel> data) {
        throw new UnsupportedOperationException("Layout transfer is not supported for GML export.");
    }

}
