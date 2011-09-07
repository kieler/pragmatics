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

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

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
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.kwebs.formats.Formats;
import de.cau.cs.kieler.kwebs.transformation.AbstractEmfTransformer;
import de.cau.cs.kieler.kwebs.transformation.TransformationData;

/**
 * Transformer for OGML.
 *
 * @author msp
 */
public class OgmlTransformer extends AbstractEmfTransformer<DocumentRoot> {

    /** map of GraphML node identifiers to KNodes. */
    private static final IProperty<Map<String, KNode>> NODE_ID_MAP
            = new Property<Map<String, KNode>>("nodeIdMap");
    
    /**
     * {@inheritDoc}
     */
    public String getSupportedFormat() {
        return Formats.FORMAT_OGML;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFileExtension() {
        return "ogml";
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
    public void deriveLayout(final TransformationData<DocumentRoot> transData) {
        Map<String, KNode> nodeIdMap = Maps.newHashMap();
        transData.setProperty(NODE_ID_MAP, nodeIdMap);
        transformGraph(transData.getSourceGraph().getOgml().getGraph(), transData);
    }

    /**
     * {@inheritDoc}
     */
    public void applyLayout(final TransformationData<DocumentRoot> transData) {
        // TODO Auto-generated method stub
        
    }

    //---------- Transformation OGML to KGraph ----------//  
    
    /**
     * Transform the given list of nodes.
     * 
     * @param nodes a list of OGDF nodes
     * @param parent the parent KNode to which new nodes are added
     */
    private void transform(final List<NodeType> nodes, final KNode parent,
            final TransformationData<DocumentRoot> transData) {
        for (NodeType node : nodes) {
            KNode knode = transformNode(node.getId(), parent, transData);
            if (!node.getLabel().isEmpty()) {
                knode.getLabel().setText(node.getLabel().get(0).getContent());
            }
            // transform subgraph
            transform(node.getNode(), knode, transData);
        }
    }
    
    /**
     * Transform the contents of an OGDF graph into a KNode.
     * 
     * @param graph an OGDF graph
     * @return a new top-level KNode
     */
    private void transformGraph(final GraphType graph,
            final TransformationData<DocumentRoot> transData) {
        KNode parent = KimlUtil.createInitializedNode();

        // transform nodes
        transform(graph.getStructure().getNode(), parent, transData);
        
        for (EdgeType edge : graph.getStructure().getEdge()) {
            // transform edges
            if (edge.getSource().size() == 1 && edge.getTarget().size() == 1) {
                KNode source = transformNode(edge.getSource().get(0).getIdRef(), parent, transData);
                KNode target = transformNode(edge.getTarget().get(0).getIdRef(), parent, transData);
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
                    KNode source = transformNode(sourceref.getIdRef(), parent, transData);
                    KEdge kedge = KimlUtil.createInitializedEdge();
                    kedge.setSource(source);
                    kedge.setTarget(hypernode);
                }
                for (SourceTargetType targetref : edge.getTarget()) {
                    KNode target = transformNode(targetref.getIdRef(), parent, transData);
                    KEdge kedge = KimlUtil.createInitializedEdge();
                    kedge.setSource(hypernode);
                    kedge.setTarget(target);
                }
                if (!edge.getLabel().isEmpty()) {
                    hypernode.getLabel().setText(edge.getLabel().get(0).getContent());
                }
            }
        }
        
        transData.getLayoutGraphs().add(parent);
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
    
}
