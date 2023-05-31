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
package de.cau.cs.kieler.formats.graphml;

import java.util.LinkedList;

import org.eclipse.elk.core.UnsupportedGraphException;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;
import org.eclipse.elk.graph.util.ElkGraphUtil;
import org.graphdrawing.graphml.DocumentRoot;
import org.graphdrawing.graphml.EdgeType;
import org.graphdrawing.graphml.GraphMLFactory;
import org.graphdrawing.graphml.GraphType;
import org.graphdrawing.graphml.GraphmlType;
import org.graphdrawing.graphml.NodeType;
import org.graphdrawing.graphml.PortType;

import de.cau.cs.kieler.formats.IGraphTransformer;
import de.cau.cs.kieler.formats.TransformationData;

/**
 * Graph exporter for GraphML.
 *
 * @author msp
 */
public class GraphMLExporter implements IGraphTransformer<ElkNode, DocumentRoot> {

    /** property for node, edge, and port identifiers. */
    private static final IProperty<String> PROP_ID = new Property<String>("graphmlExporter.id");
    
    /**
     * {@inheritDoc}
     */
    public void transform(final TransformationData<ElkNode, DocumentRoot> data) {
        // create identifiers for all nodes, edges, and ports
        LinkedList<ElkNode> nodes = new LinkedList<>();
        nodes.add(data.getSourceGraph());
        int nodeId = 0, edgeId = 0;
        do {
            ElkNode node = nodes.remove();
            node.setProperty(PROP_ID, "n" + Integer.toString(nodeId++));
            int portId = 0;
            for (ElkPort port : node.getPorts()) {
                port.setProperty(PROP_ID, "p" + Integer.toString(portId++));
            }
            for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
                edge.setProperty(PROP_ID, "e" + Integer.toString(edgeId++));
            }
            nodes.addAll(node.getChildren());
        } while (!nodes.isEmpty());
        
        // transform into GraphML model
        DocumentRoot documentRoot = GraphMLFactory.eINSTANCE.createDocumentRoot();
        GraphmlType graphml = GraphMLFactory.eINSTANCE.createGraphmlType();
        GraphType graph = GraphMLFactory.eINSTANCE.createGraphType();
        transform(data.getSourceGraph(), graph);
        graphml.getGraph().add(graph);
        documentRoot.setGraphml(graphml);
        data.getTargetGraphs().add(documentRoot);
    }
    
    /**
     * Transform the content of the given parent node.
     * 
     * @param parentNode a parent node
     * @param graph the corresponding GraphML graph
     */
    public void transform(final ElkNode parentNode, final GraphType graph) {
        for (ElkNode elknode : parentNode.getChildren()) {
            // transform node
            String nodeId = elknode.getProperty(PROP_ID);
            NodeType graphmlNode = GraphMLFactory.eINSTANCE.createNodeType();
            graphmlNode.setId(nodeId);
            graph.getNode().add(graphmlNode);
            
            for (ElkPort elkport : elknode.getPorts()) {
                // transform port
                String portId = elkport.getProperty(PROP_ID);
                PortType graphmlPort = GraphMLFactory.eINSTANCE.createPortType();
                graphmlPort.setName(portId);
                graphmlNode.getPort().add(graphmlPort);
            }
            
            for (ElkEdge elkedge : ElkGraphUtil.allOutgoingEdges(elknode)) {
                // make sure it's a proper edge that we support
                if (!elkedge.isConnected() || elkedge.isHyperedge()) {
                    throw new UnsupportedGraphException("Hyperedges are not supported.");
                }
                
                ElkNode sourceNode =
                        ElkGraphUtil.connectableShapeToNode(elkedge.getSources().get(0));
                ElkNode targetNode =
                        ElkGraphUtil.connectableShapeToNode(elkedge.getTargets().get(0));
                ElkPort sourcePort =
                        ElkGraphUtil.connectableShapeToPort(elkedge.getSources().get(0));
                ElkPort targetPort =
                        ElkGraphUtil.connectableShapeToPort(elkedge.getTargets().get(0));
                
                // transform edge
                String edgeId = elkedge.getProperty(PROP_ID);
                String sourceId = sourceNode.getProperty(PROP_ID);
                String targetId = targetNode.getProperty(PROP_ID);
                EdgeType graphmlEdge = GraphMLFactory.eINSTANCE.createEdgeType();
                graphmlEdge.setId(edgeId);
                graphmlEdge.setSource(sourceId);
                graphmlEdge.setTarget(targetId);
                if (sourcePort != null) {
                    String sourcePortId = sourcePort.getProperty(PROP_ID);
                    graphmlEdge.setSourceport(sourcePortId);
                }
                if (targetPort != null) {
                    String targetPortId = targetPort.getProperty(PROP_ID);
                    graphmlEdge.setTargetport(targetPortId);
                }
                graph.getEdge().add(graphmlEdge);
            }
            
            if (!elknode.getChildren().isEmpty()) {
                // transform subgraph
                GraphType subgraph = GraphMLFactory.eINSTANCE.createGraphType();
                transform(elknode, subgraph);
                graphmlNode.setGraph(subgraph);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(final TransformationData<ElkNode, DocumentRoot> data) {
        throw new UnsupportedOperationException("Layout transfer is not supported for GraphML export.");
    }

}
