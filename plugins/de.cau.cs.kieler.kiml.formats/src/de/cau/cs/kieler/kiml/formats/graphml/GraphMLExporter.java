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
package de.cau.cs.kieler.kiml.formats.graphml;

import java.util.LinkedList;

import org.graphdrawing.graphml.DocumentRoot;
import org.graphdrawing.graphml.EdgeType;
import org.graphdrawing.graphml.GraphMLFactory;
import org.graphdrawing.graphml.GraphType;
import org.graphdrawing.graphml.GraphmlType;
import org.graphdrawing.graphml.NodeType;
import org.graphdrawing.graphml.PortType;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;

/**
 * Graph exporter for GraphML.
 *
 * @author msp
 */
public class GraphMLExporter implements IGraphTransformer<KNode, DocumentRoot> {

    /** property for node, edge, and port identifiers. */
    private static final IProperty<String> PROP_ID = new Property<String>("graphmlExporter.id");
    
    /**
     * {@inheritDoc}
     */
    public void transform(final TransformationData<KNode, DocumentRoot> data) {
        // create identifiers for all nodes, edges, and ports
        LinkedList<KNode> nodes = new LinkedList<KNode>();
        nodes.add(data.getSourceGraph());
        int nodeId = 0, edgeId = 0;
        do {
            KNode node = nodes.remove();
            node.getData(KShapeLayout.class).setProperty(PROP_ID,
                    "n" + Integer.toString(nodeId++));
            int portId = 0;
            for (KPort port : node.getPorts()) {
                port.getData(KShapeLayout.class).setProperty(PROP_ID,
                        "p" + Integer.toString(portId++));
            }
            for (KEdge edge : node.getOutgoingEdges()) {
                edge.getData(KEdgeLayout.class).setProperty(PROP_ID,
                        "e" + Integer.toString(edgeId++));
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
    public void transform(final KNode parentNode, final GraphType graph) {
        for (KNode knode : parentNode.getChildren()) {
            // transform node
            KShapeLayout knodeLayout = knode.getData(KShapeLayout.class);
            String nodeId = knodeLayout.getProperty(PROP_ID);
            NodeType graphmlNode = GraphMLFactory.eINSTANCE.createNodeType();
            graphmlNode.setId(nodeId);
            graph.getNode().add(graphmlNode);
            
            for (KPort kport : knode.getPorts()) {
                // transform port
                KShapeLayout kportLayout = kport.getData(KShapeLayout.class);
                String portId = kportLayout.getProperty(PROP_ID);
                PortType graphmlPort = GraphMLFactory.eINSTANCE.createPortType();
                graphmlPort.setName(portId);
                graphmlNode.getPort().add(graphmlPort);
            }
            
            for (KEdge kedge : knode.getOutgoingEdges()) {
                // transform edge
                KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
                String edgeId = edgeLayout.getProperty(PROP_ID);
                String sourceId = kedge.getSource().getData(KShapeLayout.class).getProperty(PROP_ID);
                String targetId = kedge.getTarget().getData(KShapeLayout.class).getProperty(PROP_ID);
                EdgeType graphmlEdge = GraphMLFactory.eINSTANCE.createEdgeType();
                graphmlEdge.setId(edgeId);
                graphmlEdge.setSource(sourceId);
                graphmlEdge.setTarget(targetId);
                if (kedge.getSourcePort() != null) {
                    String sourcePortId = kedge.getSourcePort().getData(KShapeLayout.class)
                            .getProperty(PROP_ID);
                    graphmlEdge.setSourceport(sourcePortId);
                }
                if (kedge.getTargetPort() != null) {
                    String targetPortId = kedge.getTargetPort().getData(KShapeLayout.class)
                            .getProperty(PROP_ID);
                    graphmlEdge.setTargetport(targetPortId);
                }
                graph.getEdge().add(graphmlEdge);
            }
            
            if (!knode.getChildren().isEmpty()) {
                // transform subgraph
                GraphType subgraph = GraphMLFactory.eINSTANCE.createGraphType();
                transform(knode, subgraph);
                graphmlNode.setGraph(subgraph);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(final TransformationData<KNode, DocumentRoot> data) {
        throw new UnsupportedOperationException("Layout transfer is not supported for GraphML export.");
    }

}
