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

import java.util.LinkedList;

import org.eclipse.elk.core.UnsupportedGraphException;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.graph.ElkBendPoint;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkLabel;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;
import org.eclipse.elk.graph.ElkShape;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.formats.IGraphTransformer;
import de.cau.cs.kieler.formats.TransformationData;

/**
 * Graph exporter for the GML format.
 *
 * @author msp
 * @author mkr
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class GmlExporter implements IGraphTransformer<ElkNode, GMLModel> {

    /** property for node and port identifiers. */
    private static final IProperty<String> PROP_ID = new Property<String>("gmlExporter.id");
    
    /**
     * {@inheritDoc}
     */
    public void transform(final TransformationData<ElkNode, GMLModel> data) {
        // create identifiers for all nodes and ports
        LinkedList<ElkNode> nodes = new LinkedList<>();
        nodes.add(data.getSourceGraph());
        int nodeId = 0;
        do {
            ElkNode node = nodes.remove();
            node.setProperty(PROP_ID, Integer.toString(nodeId++));
            int portId = 0;
            for (ElkPort port : node.getPorts()) {
                port.setProperty(PROP_ID, Integer.toString(portId++));
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
    private void transform(final ElkNode parentNode, final Element graphElement) {
        for (ElkNode elknode : parentNode.getChildren()) {
            // transform node
            String nodeId = elknode.getProperty(PROP_ID);
            CollectionElement nodeElement = new CollectionElement(graphElement, "node");
            Element id = new StringElement(nodeElement, "id", nodeId);
            nodeElement.getElements().add(id);
            for (ElkLabel elklabel : elknode.getLabels()) {
                nodeElement.getElements().add(GmlFormatHandler.createLabel(nodeElement, elklabel));
            }
            transform(elknode, nodeElement);
            GmlFormatHandler.getElements(graphElement).add(nodeElement);
            
            for (ElkPort elkport : elknode.getPorts()) {
                // transform port
                String portId = elkport.getProperty(PROP_ID);
                CollectionElement portElement = new CollectionElement(nodeElement, "port");
                id = new StringElement(portElement, "id", portId);
                portElement.getElements().add(id);
                for (ElkLabel elklabel : elkport.getLabels()) {
                    portElement.getElements().add(
                            GmlFormatHandler.createLabel(portElement, elklabel));
                }
                transform(elkport, portElement);
                nodeElement.getElements().add(portElement);
            }
            
            if (!elknode.getChildren().isEmpty()) {
                // transform subgraph
                Element subgraphElement = new CollectionElement(nodeElement, "graph");
                transform(elknode, subgraphElement);
                nodeElement.getElements().add(subgraphElement);
            }
            
            for (ElkEdge elkedge : ElkGraphUtil.allOutgoingEdges(elknode)) {
                // We don't support hyperedges
                if (!(elkedge.isConnected() && elkedge.isHyperedge())) {
                    throw new UnsupportedGraphException("Hyperedges are not supported");
                }
                
                ElkNode sourceNode = ElkGraphUtil.connectableShapeToNode(
                        elkedge.getSources().get(0));
                ElkNode targetNode = ElkGraphUtil.connectableShapeToNode(
                        elkedge.getTargets().get(0));
                ElkPort sourcePort = ElkGraphUtil.connectableShapeToPort(
                        elkedge.getSources().get(0));
                ElkPort targetPort = ElkGraphUtil.connectableShapeToPort(
                        elkedge.getTargets().get(0));
                
                // transform edge
                String sourceId = sourceNode.getProperty(PROP_ID);
                String targetId = targetNode.getProperty(PROP_ID);
                CollectionElement edgeElement = new CollectionElement(graphElement, "edge");
                
                Element source = new StringElement(edgeElement, "source", sourceId);
                edgeElement.getElements().add(source);
                if (sourcePort != null) {
                    String sourcePortId = sourcePort.getProperty(PROP_ID);
                    Element sourcePortElement =
                            new StringElement(edgeElement, "sourcePort", sourcePortId);
                    edgeElement.getElements().add(sourcePortElement);
                }
                
                Element target = new StringElement(edgeElement, "target", targetId);
                edgeElement.getElements().add(target);
                if (targetPort != null) {
                    String targetPortId = targetPort.getProperty(PROP_ID);
                    Element targetPortElement =
                            new StringElement(edgeElement, "targetPort", targetPortId);
                    edgeElement.getElements().add(targetPortElement);
                }
                
                for (ElkLabel elklabel : elkedge.getLabels()) {
                    if (elklabel.getText().length() > 0) {
                        edgeElement.getElements().add(
                                GmlFormatHandler.createLabel(edgeElement, elklabel));
                    }
                }
                transform(elkedge, edgeElement);
                GmlFormatHandler.getElements(graphElement).add(edgeElement);
            }
        }
    }
    
    /**
     * Transform a shape layout.
     * 
     * @param shape a shape
     * @param parentElement the parent element to which the layout is added
     */
    private void transform(final ElkShape shape, final Element parentElement) {
        if (!shape.getProperty(CoreOptions.NO_LAYOUT) && (shape.getX() != 0
                || shape.getY() != 0 || shape.getWidth() != 0 || shape.getHeight() != 0)) {
            
            CollectionElement graphics = new CollectionElement(parentElement, "graphics");
            
            if (shape.getX() != 0 || shape.getY() != 0) {
                Element x = new NumberElement(graphics, "x", shape.getX());
                graphics.getElements().add(x);
                Element y = new NumberElement(graphics, "y", shape.getY());
                graphics.getElements().add(y);
            }
            
            if (shape.getWidth() != 0 || shape.getHeight() != 0) {
                Element w = new NumberElement(graphics, "w", shape.getWidth());
                graphics.getElements().add(w);
                Element h = new NumberElement(graphics, "h", shape.getHeight());
                graphics.getElements().add(h);
            }
            
            GmlFormatHandler.getElements(parentElement).add(graphics);
        }
    }
    
    /**
     * Transform an edge layout.
     * 
     * @param edge an edge
     * @param parentElement the parent element to which the layout is added
     */
    private void transform(final ElkEdge edge, final Element parentElement) {
        // If there is no edge section, there is no layout
        if (edge.getProperty(CoreOptions.NO_LAYOUT) || edge.getSections().isEmpty()) {
            return;
        }
        
        ElkEdgeSection edgeSection = edge.getSections().get(0);
        
        if (edgeSection.getBendPoints().size() > 0
                || edgeSection.getStartX() != 0 || edgeSection.getStartY() != 0
                || edgeSection.getEndX() != 0 || edgeSection.getEndY() != 0) {
            
            CollectionElement graphics = new CollectionElement(parentElement, "graphics");
            graphics.getElements().add(GmlFormatHandler.createPoint(
                    graphics, edgeSection.getStartX(), edgeSection.getStartY()));
            
            for (ElkBendPoint point : edgeSection.getBendPoints()) {
                graphics.getElements().add(GmlFormatHandler.createPoint(
                        graphics, point.getX(), point.getY()));
            }
            
            graphics.getElements().add(GmlFormatHandler.createPoint(
                    graphics, edgeSection.getEndX(), edgeSection.getEndY()));
            
            GmlFormatHandler.getElements(parentElement).add(graphics);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(final TransformationData<ElkNode, GMLModel> data) {
        throw new UnsupportedOperationException("Layout transfer is not supported for GML export.");
    }

}
