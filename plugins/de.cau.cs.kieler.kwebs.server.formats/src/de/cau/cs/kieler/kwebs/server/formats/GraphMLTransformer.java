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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
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
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.kwebs.formats.Formats;
import de.cau.cs.kieler.kwebs.transformation.IGraphTransformer;
import de.cau.cs.kieler.kwebs.transformation.TransformationException;

/**
 * A transformer for GraphML.
 *
 * @author msp
 */
public class GraphMLTransformer implements IGraphTransformer<DocumentRoot> {

    /**
     * {@inheritDoc}
     */
    public String getSupportedFormat() {
        return Formats.FORMAT_GRAPHML;
    }

    /**
     * {@inheritDoc}
     */
    public DocumentRoot deserialize(final String serializedGraph) {
        DocumentRoot graph = null;
        try {
            ByteArrayInputStream inStream = new ByteArrayInputStream(
                serializedGraph.getBytes("UTF-8")
            );
            URI uri = URI.createURI("inputstream://temp.graphml");
            ResourceSet resourceSet = createResourceSet();
            Resource resource = resourceSet.createResource(uri);
            EObject eObject = null;
            Map<String, String> options = new HashMap<String, String>();
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            resource.load(inStream, options);
            eObject = resource.getContents().get(0);
            if (eObject instanceof DocumentRoot) {
                graph = (DocumentRoot) eObject;
            }
            inStream.close();
        } catch (UnsupportedEncodingException e) {
            throw new TransformationException(e);
        } catch (IOException e) {
            throw new TransformationException(e);
        }
        return graph;
    }

    /**
     * {@inheritDoc}
     */
    public String serialize(final DocumentRoot graph) {
        String xmi = null;
        try {
            EcoreUtil.resolveAll(graph);
            URI uri = URI.createURI("outputstream://temp.graphml");
            ResourceSet resourceSet = createResourceSet();
            Resource resource = resourceSet.createResource(uri);
            resource.unload();
            resource.getContents().add(graph);            
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            Map<String, String> options = new HashMap<String, String>();
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            resource.save(outStream, options);
            outStream.flush();
            xmi = new String(outStream.toByteArray(), "UTF-8");
            outStream.close();
        } catch (IOException e) {
            throw new TransformationException(e);
        }
        return xmi;
    }

    /**
     * Creates a resource set ready to be used with the GraphML meta model.
     *
     * @return a resource set
     */
    private ResourceSet createResourceSet() {
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
    public List<KNode> deriveLayout(final DocumentRoot graph) {
        return transform(graph.getGraphml());
    }

    /**
     * {@inheritDoc}
     */
    public void applyLayout(final DocumentRoot graph, final List<KNode> layout) {
        // TODO Auto-generated method stub
        
    }
    
    //---------- Transformation GraphML to KGraph ----------//  
    
    /** map of GraphML node identifiers to KNodes. */
    private Map<String, KNode> nodeIdMap = Maps.newHashMap();
    /** map of GraphML port identifiers to KPorts. */
    private Map<Pair<KNode, String>, KPort> portIdMap = Maps.newHashMap();
    
    /**
     * Transform a GraphML structure to a KGraph list.
     * 
     * @param graphml a GraphML instance
     * @return a KGraph instance
     */
    private List<KNode> transform(final GraphmlType graphml) {
        List<KNode> result = new LinkedList<KNode>();
        for (GraphType graph : graphml.getGraph()) {
            try {
                KNode parent = KimlUtil.createInitializedNode();
                transformGraph(graph, parent);
                result.add(parent);
            } finally {
                nodeIdMap.clear();
                portIdMap.clear();
            }
        }
        return result;
    }
    
    /**
     * Transform the contents of a GraphML graph or subgraph into a KNode.
     * 
     * @param graph a GraphML graph
     * @param parent the corresponding KNode
     */
    private void transformGraph(final GraphType graph, final KNode parent) {
        // transform nodes
        for (NodeType node : graph.getNode()) {
            KNode knode = transformNode(node.getId(), parent);
            // transform ports
            for (PortType port : node.getPort()) {
                transformPort(port.getName(), knode);
            }
            // transform subgraph
            if (node.getGraph() != null) {
                transformGraph(node.getGraph(), knode);
            }
        }
        
        // transform edges
        for (EdgeType edge : graph.getEdge()) {
            KNode source = transformNode(edge.getSource(), parent);
            KNode target = transformNode(edge.getTarget(), parent);
            KEdge kedge = KimlUtil.createInitializedEdge();
            kedge.setSource(source);
            kedge.setTarget(target);
            if (edge.getSourceport() != null) {
                KPort port = transformPort(edge.getSourceport(), source);
                kedge.setSourcePort(port);
            }
            if (edge.getTargetport() != null) {
                KPort port = transformPort(edge.getTargetport(), target);
                kedge.setTargetPort(port);
            }
        }
        
        // transform hyperedges
        for (HyperedgeType hyperedge : graph.getHyperedge()) {
            KNode hypernode = KimlUtil.createInitializedNode();
            hypernode.setParent(parent);
            hypernode.getData(KShapeLayout.class).setProperty(LayoutOptions.HYPERNODE, true);
            for (EndpointType endpoint : hyperedge.getEndpoint()) {
                KNode epnode = transformNode(endpoint.getNode(), parent);
                KEdge kedge = KimlUtil.createInitializedEdge();
                kedge.setSource(epnode);
                kedge.setTarget(hypernode);
                if (endpoint.getPort() != null) {
                    KPort port = transformPort(endpoint.getPort(), epnode);
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
    private KNode transformNode(final String nodeId, final KNode parent) {
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
    private KPort transformPort(final String portId, final KNode node) {
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
