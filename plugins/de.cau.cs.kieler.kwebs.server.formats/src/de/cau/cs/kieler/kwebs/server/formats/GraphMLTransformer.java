/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kwebs.server.formats;

import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.graphdrawing.graphml.DocumentRoot;
import org.graphdrawing.graphml.EdgeType;
import org.graphdrawing.graphml.EndpointType;
import org.graphdrawing.graphml.GraphMLPackage;
import org.graphdrawing.graphml.GraphType;
import org.graphdrawing.graphml.GraphmlType;
import org.graphdrawing.graphml.HyperedgeType;
import org.graphdrawing.graphml.NodeType;
import org.graphdrawing.graphml.PortType;
import org.graphdrawing.graphml.util.GraphMLResourceFactoryImpl;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.kwebs.formats.Formats;
import de.cau.cs.kieler.kwebs.transformation.AbstractEmfTransformer;
import de.cau.cs.kieler.kwebs.transformation.TransformationData;

/**
 * A transformer for GraphML.
 *
 * @author msp
 */
public class GraphMLTransformer extends AbstractEmfTransformer<DocumentRoot> {
    
    /** map of GraphML node identifiers to KNodes. */
    private static final IProperty<Map<String, KNode>> NODE_ID_MAP
            = new Property<Map<String, KNode>>("nodeIdMap");
    /** map of GraphML port identifiers to KPorts. */
    private static final IProperty<Map<Pair<KNode, String>, KPort>> PORT_ID_MAP
            = new Property<Map<Pair<KNode, String>, KPort>>("portIdMap");


    /**
     * {@inheritDoc}
     */
    public String getSupportedFormat() {
        return Formats.FORMAT_GRAPHML;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFileExtension() {
        return "graphml";
    }

    /**
     * Creates a resource set ready to be used with the GraphML meta model.
     *
     * @return a resource set
     */
    protected ResourceSet createResourceSet() {
        ResourceSet resourceset = new ResourceSetImpl();
        resourceset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
            Resource.Factory.Registry.DEFAULT_EXTENSION,
            new GraphMLResourceFactoryImpl()
        );
        resourceset.getPackageRegistry().put(
            GraphMLPackage.eNS_URI,
            GraphMLPackage.eINSTANCE
        );
        return resourceset;
    }

    /**
     * {@inheritDoc}
     */
    public void deriveLayout(final TransformationData<DocumentRoot> transData) {
        GraphmlType graphml = transData.getSourceGraph().getGraphml();
        for (GraphType graph : graphml.getGraph()) {
            KNode parent = KimlUtil.createInitializedNode();
            Map<String, KNode> nodeIdMap = Maps.newHashMap();
            transData.setProperty(NODE_ID_MAP, nodeIdMap);
            Map<Pair<KNode, String>, KPort> portIdMap = Maps.newHashMap();
            transData.setProperty(PORT_ID_MAP, portIdMap);
            transformGraph(graph, parent, transData);
            transData.getLayoutGraphs().add(parent);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void applyLayout(final TransformationData<DocumentRoot> transData) {
        // TODO Auto-generated method stub
        
    }
    
    //---------- Transformation GraphML to KGraph ----------//  
        
    /**
     * Transform the contents of a GraphML graph or subgraph into a KNode.
     * 
     * @param graph a GraphML graph
     * @param parent the corresponding KNode
     */
    private void transformGraph(final GraphType graph, final KNode parent,
            final TransformationData<DocumentRoot> transData) {
        // transform nodes
        for (NodeType node : graph.getNode()) {
            KNode knode = transformNode(node.getId(), parent, transData);
            // transform ports
            for (PortType port : node.getPort()) {
                transformPort(port.getName(), knode, transData);
            }
            // transform subgraph
            if (node.getGraph() != null) {
                transformGraph(node.getGraph(), knode, transData);
            }
        }
        
        // transform edges
        for (EdgeType edge : graph.getEdge()) {
            KNode source = transformNode(edge.getSource(), parent, transData);
            KNode target = transformNode(edge.getTarget(), parent, transData);
            KEdge kedge = KimlUtil.createInitializedEdge();
            kedge.setSource(source);
            kedge.setTarget(target);
            if (edge.getSourceport() != null) {
                KPort port = transformPort(edge.getSourceport(), source, transData);
                kedge.setSourcePort(port);
            }
            if (edge.getTargetport() != null) {
                KPort port = transformPort(edge.getTargetport(), target, transData);
                kedge.setTargetPort(port);
            }
        }
        
        // transform hyperedges
        for (HyperedgeType hyperedge : graph.getHyperedge()) {
            KNode hypernode = KimlUtil.createInitializedNode();
            hypernode.setParent(parent);
            hypernode.getData(KShapeLayout.class).setProperty(LayoutOptions.HYPERNODE, true);
            for (EndpointType endpoint : hyperedge.getEndpoint()) {
                KNode epnode = transformNode(endpoint.getNode(), parent, transData);
                KEdge kedge = KimlUtil.createInitializedEdge();
                kedge.setSource(epnode);
                kedge.setTarget(hypernode);
                if (endpoint.getPort() != null) {
                    KPort port = transformPort(endpoint.getPort(), epnode, transData);
                    kedge.setSourcePort(port);
                }
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
            final TransformationData<DocumentRoot> transData) {
        Map<String, KNode> nodeIdMap = transData.getProperty(NODE_ID_MAP);
        KNode knode = nodeIdMap.get(nodeId);
        if (knode == null) {
            knode = KimlUtil.createInitializedNode();
            knode.setParent(parent);
            if (nodeId != null) {
                nodeIdMap.put(nodeId, knode);
            }
        }
        return knode;
    }
    
    /**
     * Transforms a single port, if not already done before.
     * 
     * @param portId a port identifier
     * @param node the node to which the new KPort belongs
     * @return a KPort instance
     */
    private KPort transformPort(final String portId, final KNode node,
            final TransformationData<DocumentRoot> transData) {
        Map<Pair<KNode, String>, KPort> portIdMap = transData.getProperty(PORT_ID_MAP);
        Pair<KNode, String> key = new Pair<KNode, String>(node, portId);
        KPort kport = portIdMap.get(key);
        if (kport == null) {
            kport = KimlUtil.createInitializedPort();
            kport.setNode(node);
            if (portId != null) {
                portIdMap.put(key, kport);
            }
        }
        return kport;
    }

}
