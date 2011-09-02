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
package de.cau.cs.kieler.kwebs.server.formats;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.ogdf.ogml.DocumentRoot;
import net.ogdf.ogml.EdgeType;
import net.ogdf.ogml.GraphType;
import net.ogdf.ogml.LabelType;
import net.ogdf.ogml.NodeType;
import net.ogdf.ogml.OgmlPackage;
import net.ogdf.ogml.SourceTargetType;
import net.ogdf.ogml.util.OgmlResourceFactoryImpl;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.kwebs.formats.Formats;
import de.cau.cs.kieler.kwebs.transformation.IGraphTransformer;
import de.cau.cs.kieler.kwebs.transformation.TransformationException;

/**
 * Transformer for OGML.
 *
 * @author msp
 */
public class OgmlTransformer implements IGraphTransformer<DocumentRoot> {

    /**
     * {@inheritDoc}
     */
    public String getSupportedFormat() {
        return Formats.FORMAT_OGML;
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
            URI uri = URI.createURI("inputstream://temp.ogml");
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
            URI uri = URI.createURI("outputstream://temp.ogml");
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
            new OgmlResourceFactoryImpl()
        );
        resourceset.getPackageRegistry().put(
            OgmlPackage.eNS_URI,
            OgmlPackage.eINSTANCE
        );
        return resourceset;
    }

    /**
     * {@inheritDoc}
     */
    public List<KNode> deriveLayout(final DocumentRoot graph) {
        KNode knode = transformGraph(graph.getOgml().getGraph());
        return Lists.newArrayList(knode);
    }

    /**
     * {@inheritDoc}
     */
    public void applyLayout(final DocumentRoot graph, final List<KNode> layout) {
        // TODO Auto-generated method stub
        
    }

    //---------- Transformation OGML to KGraph ----------//  
    
    /** map of GraphML node identifiers to KNodes. */
    private Map<String, KNode> nodeIdMap = Maps.newHashMap();
    
    /**
     * Transform the given list of nodes.
     * 
     * @param nodes a list of OGDF nodes
     * @param parent the parent KNode to which new nodes are added
     */
    private void transform(final List<NodeType> nodes, final KNode parent) {
        for (NodeType node : nodes) {
            KNode knode = transformNode(node.getId(), parent);
            if (!node.getLabel().isEmpty()) {
                knode.getLabel().setText(node.getLabel().get(0).getContent());
            }
            // transform subgraph
            transform(node.getNode(), knode);
        }
    }
    
    /**
     * Transform the contents of an OGDF graph into a KNode.
     * 
     * @param graph an OGDF graph
     * @return a new top-level KNode
     */
    private KNode transformGraph(final GraphType graph) {
        KNode parent = KimlUtil.createInitializedNode();

        // transform nodes
        transform(graph.getStructure().getNode(), parent);
        
        for (EdgeType edge : graph.getStructure().getEdge()) {
            // transform edges
            if (edge.getSource().size() == 1 && edge.getTarget().size() == 1) {
                KNode source = transformNode(edge.getSource().get(0).getIdRef(), parent);
                KNode target = transformNode(edge.getTarget().get(0).getIdRef(), parent);
                KEdge kedge = KimlUtil.createInitializedEdge();
                kedge.setSource(source);
                kedge.setTarget(target);
                for (LabelType label : edge.getLabel()) {
                    KLabel klabel = KimlUtil.createInitializedLabel(kedge);
                    klabel.setText(label.getContent());
                }
                
            // transform hyperedges
            } else if (edge.getSource().size() > 1 || edge.getTarget().size() > 1) {
                KNode hypernode = KimlUtil.createInitializedNode();
                hypernode.setParent(parent);
                hypernode.getData(KShapeLayout.class).setProperty(LayoutOptions.HYPERNODE, true);
                for (SourceTargetType sourceref : edge.getSource()) {
                    KNode source = transformNode(sourceref.getIdRef(), parent);
                    KEdge kedge = KimlUtil.createInitializedEdge();
                    kedge.setSource(source);
                    kedge.setTarget(hypernode);
                }
                for (SourceTargetType targetref : edge.getTarget()) {
                    KNode target = transformNode(targetref.getIdRef(), parent);
                    KEdge kedge = KimlUtil.createInitializedEdge();
                    kedge.setSource(hypernode);
                    kedge.setTarget(target);
                }
                if (!edge.getLabel().isEmpty()) {
                    hypernode.getLabel().setText(edge.getLabel().get(0).getContent());
                }
            }
        }
        
        return parent;
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
    
}
