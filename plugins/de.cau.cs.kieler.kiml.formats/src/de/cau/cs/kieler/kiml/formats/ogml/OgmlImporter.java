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
package de.cau.cs.kieler.kiml.formats.ogml;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import net.ogdf.ogml.DataType;
import net.ogdf.ogml.DocumentRoot;
import net.ogdf.ogml.EdgeLayoutType;
import net.ogdf.ogml.EdgeType;
import net.ogdf.ogml.GraphType;
import net.ogdf.ogml.LabelLayoutType;
import net.ogdf.ogml.LabelType;
import net.ogdf.ogml.LayoutType;
import net.ogdf.ogml.LocationType;
import net.ogdf.ogml.NodeLayoutType;
import net.ogdf.ogml.NodeType;
import net.ogdf.ogml.OgmlFactory;
import net.ogdf.ogml.ShapeType1;
import net.ogdf.ogml.SourceTargetType;
import net.ogdf.ogml.StylesType;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Transformer for OGML.
 *
 * @author msp
 */
public class OgmlImporter implements IGraphTransformer<DocumentRoot, KNode> {

    /** map of OGML node identifiers to KNodes. */
    private static final IProperty<Map<String, KNode>> NODE_ID_MAP
            = new Property<Map<String, KNode>>("ogmlImporter.nodeIdMap");
    /** map of OGML edge identifiers to KEdges. */
    private static final IProperty<Map<String, KEdge>> EDGE_ID_MAP
            = new Property<Map<String, KEdge>>("ogmlImporter.edgeIdMap");
    /** map of OGML label identifiers to KLabels. */
    private static final IProperty<Map<String, KLabel>> LABEL_ID_MAP
            = new Property<Map<String, KLabel>>("ogmlImporter.labelIdMap");
    /** OGML node attached to each new KNode. */
    private static final IProperty<NodeType> PROP_NODE
            = new Property<NodeType>("ogmlImporter.node");
    /** node layout attached to each new KNode, if present. */
    private static final IProperty<NodeLayoutType> PROP_NODE_LAYOUT
            = new Property<NodeLayoutType>("ogmlImporter.nodeLayout");
    /** OGML edge attached to each new KEdge. */
    private static final IProperty<EdgeType> PROP_EDGE
            = new Property<EdgeType>("ogmlImporter.edge");
    /** edge layout attached to each new KEdge, if present. */
    private static final IProperty<EdgeLayoutType> PROP_EDGE_LAYOUT
            = new Property<EdgeLayoutType>("ogmlImporter.edgeLayout");
    /** OGML label attached to each new KLabel. */
    private static final IProperty<LabelType> PROP_LABEL
            = new Property<LabelType>("ogmlImporter.label");
    /** label layout attached to each new KLabel, if present. */
    private static final IProperty<LabelLayoutType> PROP_LABEL_LAYOUT
            = new Property<LabelLayoutType>("ogmlImporter.labelLayout");
    /** original OGML identifiers attached to graph elements. */
    private static final IProperty<String> PROP_ID = new Property<String>("ogmlImporter.id");

    /**
     * {@inheritDoc}
     */
    public void transform(final TransformationData<DocumentRoot, KNode> transData) {
        GraphType graph = transData.getSourceGraph().getOgml().getGraph();
        if (graph.getStructure() != null) {
            Map<String, KNode> nodeIdMap = Maps.newHashMap();
            transData.setProperty(NODE_ID_MAP, nodeIdMap);
            Map<String, KEdge> edgeIdMap = Maps.newHashMap();
            transData.setProperty(EDGE_ID_MAP, edgeIdMap);
            Map<String, KLabel> labelIdMap = Maps.newHashMap();
            transData.setProperty(LABEL_ID_MAP, labelIdMap);
            transformGraph(graph, transData);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(final TransformationData<DocumentRoot, KNode> transData) {
        for (KNode layoutNode : transData.getTargetGraphs()) {
            applyLayout(layoutNode, new KVector(), transData.getSourceGraph().getOgml().getGraph());
        }
    }

    //---------- Transformation OGML to KGraph ----------//
    
    /**
     * Transform the given list of nodes.
     * 
     * @param nodes a list of OGDF nodes
     * @param parent the parent KNode to which new nodes are added
     * @param transData transformation data
     */
    private void transform(final List<NodeType> nodes, final KNode parent,
            final TransformationData<DocumentRoot, KNode> transData) {
        for (NodeType node : nodes) {
            KNode knode = transformNode(node.getId(), parent, transData);
            KShapeLayout nodeLayout = knode.getData(KShapeLayout.class);
            nodeLayout.setProperty(PROP_NODE, node);
            if (!node.getLabel().isEmpty()) {
                knode.getLabel().setText(node.getLabel().get(0).getContent());
            }
            // transform layout options
            for (DataType data : node.getData()) {
                KimlUtil.setOption(nodeLayout, data.getIdRef(), data.getValue());
            }
            // transform subgraph
            transform(node.getNode(), knode, transData);
        }
    }
    
    /**
     * Transform the contents of an OGDF graph into a KNode.
     * 
     * @param graph an OGDF graph
     * @param transData transformation data
     * @return a new top-level KNode
     */
    private void transformGraph(final GraphType graph,
            final TransformationData<DocumentRoot, KNode> transData) {
        KNode parent = KimlUtil.createInitializedNode();
        // transform layout options
        for (DataType data : graph.getData()) {
            KimlUtil.setOption(parent.getData(KShapeLayout.class), data.getIdRef(), data.getValue());
        }

        // transform nodes
        transform(graph.getStructure().getNode(), parent, transData);
        
        Map<String, KEdge> edgeIdMap = transData.getProperty(EDGE_ID_MAP);
        Map<String, KLabel> labelIdMap = transData.getProperty(LABEL_ID_MAP);
        for (EdgeType edge : graph.getStructure().getEdge()) {
            // transform edges
            if (edge.getSource().size() == 1 && edge.getTarget().size() == 1) {
                KNode source = transformNode(edge.getSource().get(0).getIdRef(), parent, transData);
                KNode target = transformNode(edge.getTarget().get(0).getIdRef(), parent, transData);
                KEdge kedge = KimlUtil.createInitializedEdge();
                KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
                edgeIdMap.put(edge.getId(), kedge);
                edgeLayout.setProperty(PROP_EDGE, edge);
                kedge.setSource(source);
                kedge.setTarget(target);
                // transform layout options
                for (DataType data : edge.getData()) {
                    KimlUtil.setOption(edgeLayout, data.getIdRef(), data.getValue());
                }
                // transform edge labels
                for (LabelType label : edge.getLabel()) {
                    KLabel klabel = KimlUtil.createInitializedLabel();
                    KShapeLayout labelLayout = klabel.getData(KShapeLayout.class);
                    labelIdMap.put(label.getId(), klabel);
                    labelLayout.setProperty(PROP_LABEL, label);
                    klabel.setText(label.getContent());
                    // transform layout options
                    for (DataType data : label.getData()) {
                        KimlUtil.setOption(labelLayout, data.getIdRef(), data.getValue());
                    }
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
        
        transData.getTargetGraphs().add(parent);
        
        if (graph.getLayout() != null && graph.getLayout().getStyles() != null) {
            // transform layout options
            for (DataType data : graph.getLayout().getData()) {
                KimlUtil.setOption(parent.getData(KShapeLayout.class), data.getIdRef(),
                        data.getValue());
            }
            transformLayout(graph.getLayout(), transData);
        }
    }
    
    /** default width and height value for nodes. */
    private static final float DEF_WIDTH = 10.0f;
    
    /**
     * Transforms a single node, if not already done before.
     * 
     * @param nodeId a node identifier
     * @param parent the parent where the new KNode is stored
     * @param transData transformation data
     * @return a KNode instance
     */
    private KNode transformNode(final String nodeId, final KNode parent,
            final TransformationData<DocumentRoot, KNode> transData) {
        Map<String, KNode> nodeIdMap = transData.getProperty(NODE_ID_MAP);
        KNode knode = nodeIdMap.get(nodeId);
        if (knode == null) {
            knode = KimlUtil.createInitializedNode();
            KShapeLayout nodeLayout = knode.getData(KShapeLayout.class);
            nodeLayout.setWidth(DEF_WIDTH);
            nodeLayout.setHeight(DEF_WIDTH);
            knode.setParent(parent);
            if (nodeId != null) {
                nodeIdMap.put(nodeId, knode);
                nodeLayout.setProperty(PROP_ID, nodeId);
            }
        }
        return knode;
    }
    
    /**
     * Transform the layout of the graph as given in the OGML file.
     * 
     * @param layout the OGML layout
     * @param transData transformation data 
     */
    private void transformLayout(final LayoutType layout,
            final TransformationData<DocumentRoot, KNode> transData) {
        // transform node layouts
        Map<String, KNode> nodeIdMap = transData.getProperty(NODE_ID_MAP);
        for (NodeLayoutType ogmlNodeLayout : layout.getStyles().getNodeStyle()) {
            KNode knode = nodeIdMap.get(ogmlNodeLayout.getIdRef());
            if (knode != null) {
                KShapeLayout knodeLayout = knode.getData(KShapeLayout.class);
                knodeLayout.setProperty(PROP_NODE_LAYOUT, ogmlNodeLayout);
                ShapeType1 shape = ogmlNodeLayout.getShape();
                if (shape != null) {
                    knodeLayout.setWidth(shape.getWidth().floatValue());
                    knodeLayout.setHeight(shape.getHeight().floatValue());
                }
                LocationType location = ogmlNodeLayout.getLocation();
                if (location != null) {
                    knodeLayout.setXpos((float) location.getX() - knodeLayout.getWidth() / 2);
                    knodeLayout.setYpos((float) location.getY() - knodeLayout.getHeight() / 2);
                }
                // transform layout options
                for (DataType data : ogmlNodeLayout.getData()) {
                    KimlUtil.setOption(knodeLayout, data.getIdRef(), data.getValue());
                }
            }
        }
        
        // transform edge layouts
        Map<String, KEdge> edgeIdMap = transData.getProperty(EDGE_ID_MAP);
        for (EdgeLayoutType ogmlEdgeLayout : layout.getStyles().getEdgeStyle()) {
            KEdge kedge = edgeIdMap.get(ogmlEdgeLayout.getIdRef());
            if (kedge != null) {
                KEdgeLayout kedgeLayout = kedge.getData(KEdgeLayout.class);
                kedgeLayout.setProperty(PROP_EDGE_LAYOUT, ogmlEdgeLayout);
                // transform layout options
                for (DataType data : ogmlEdgeLayout.getData()) {
                    KimlUtil.setOption(kedgeLayout, data.getIdRef(), data.getValue());
                }
            }
        }
        
        // transform edge label layouts
        Map<String, KLabel> labelIdMap = transData.getProperty(LABEL_ID_MAP);
        for (LabelLayoutType ogmlLabelLayout : layout.getStyles().getLabelStyle()) {
            KLabel klabel = labelIdMap.get(ogmlLabelLayout.getIdRef());
            if (klabel != null) {
                KShapeLayout klabelLayout = klabel.getData(KShapeLayout.class);
                klabelLayout.setProperty(PROP_LABEL_LAYOUT, ogmlLabelLayout);
                // transform layout options
                for (DataType data : ogmlLabelLayout.getData()) {
                    KimlUtil.setOption(klabelLayout, data.getIdRef(), data.getValue());
                }
            }
        }
    }

    
    /*---------- Layout Transfer KGraph to OGML ----------*/
    
    /**
     * Apply layout to the elements of the given graph.
     * 
     * @param parent the parent node of the KGraph
     * @param offset the offset of the parent node
     * @param graph the corresponding OGML graph
     */
    private void applyLayout(final KNode parent, final KVector offset, final GraphType graph) {
        if (graph.getLayout() == null) {
            graph.setLayout(OgmlFactory.eINSTANCE.createLayoutType());
        }
        StylesType layoutStyles = graph.getLayout().getStyles();
        if (layoutStyles == null) {
            layoutStyles = OgmlFactory.eINSTANCE.createStylesType();
            graph.getLayout().setStyles(layoutStyles);
        }
        for (KNode knode : parent.getChildren()) {
            KShapeLayout knodeLayout = knode.getData(KShapeLayout.class);
            // create node if it is not present yet
            NodeType ogmlNode = knodeLayout.getProperty(PROP_NODE);
            if (ogmlNode == null) {
                String id = knodeLayout.getProperty(PROP_ID);
                if (id != null) {
                    ogmlNode = OgmlFactory.eINSTANCE.createNodeType();
                    ogmlNode.setId(id);
                    graph.getStructure().getNode().add(ogmlNode);
                }
            }
            if (ogmlNode != null) {
                // apply node layout
                NodeLayoutType ogmlNodeLayout = knodeLayout.getProperty(PROP_NODE_LAYOUT);
                if (ogmlNodeLayout == null) {
                    ogmlNodeLayout = OgmlFactory.eINSTANCE.createNodeLayoutType();
                    ogmlNodeLayout.setIdRef(ogmlNode.getId());
                    layoutStyles.getNodeStyle().add(ogmlNodeLayout);
                }
                LocationType location = ogmlNodeLayout.getLocation();
                if (location == null) {
                    location = OgmlFactory.eINSTANCE.createLocationType();
                    ogmlNodeLayout.setLocation(location);
                }
                location.setX(knodeLayout.getXpos() + knodeLayout.getWidth() / 2 + offset.x);
                location.setY(knodeLayout.getYpos() + knodeLayout.getHeight() / 2 + offset.y);
                ShapeType1 shape = ogmlNodeLayout.getShape();
                if (shape == null) {
                    shape = OgmlFactory.eINSTANCE.createShapeType1();
                    ogmlNodeLayout.setShape(shape);
                }
                shape.setWidth(BigInteger.valueOf(Math.round(knodeLayout.getWidth())));
                shape.setHeight(BigInteger.valueOf(Math.round(knodeLayout.getHeight())));
            }
            
            for (KEdge kedge : knode.getOutgoingEdges()) {
                KVector edgeOffset = offset;
                if (KimlUtil.isDescendant(kedge.getTarget(), knode)) {
                    edgeOffset = new KVector(offset).translate(knodeLayout.getXpos(),
                            knodeLayout.getYpos());
                }
                KEdgeLayout kedgeLayout = kedge.getData(KEdgeLayout.class);
                // create edge if it is not present yet
                EdgeType ogmlEdge = kedgeLayout.getProperty(PROP_EDGE);
                if (ogmlEdge == null) {
                    ogmlEdge = OgmlFactory.eINSTANCE.createEdgeType();
                    ogmlEdge.setId("edge" + kedge.hashCode());
                    graph.getStructure().getEdge().add(ogmlEdge);
                }
                // apply edge layout
                EdgeLayoutType ogmlEdgeLayout = kedgeLayout.getProperty(PROP_EDGE_LAYOUT);
                if (ogmlEdgeLayout == null) {
                    ogmlEdgeLayout = OgmlFactory.eINSTANCE.createEdgeLayoutType();
                    ogmlEdgeLayout.setIdRef(ogmlEdge.getId());
                    layoutStyles.getEdgeStyle().add(ogmlEdgeLayout);
                }
                ogmlEdgeLayout.getPoint().clear();
                ogmlEdgeLayout.getPoint().add(OgmlHandler.createPoint(kedgeLayout.getSourcePoint(),
                        edgeOffset));
                for (KPoint bendPoint : kedgeLayout.getBendPoints()) {
                    ogmlEdgeLayout.getPoint().add(OgmlHandler.createPoint(bendPoint, edgeOffset));
                }
                ogmlEdgeLayout.getPoint().add(OgmlHandler.createPoint(kedgeLayout.getTargetPoint(),
                        edgeOffset));
                
                for (KLabel klabel : kedge.getLabels()) {
                    KShapeLayout klabelLayout = klabel.getData(KShapeLayout.class);
                    LabelType ogmlLabel = klabelLayout.getProperty(PROP_LABEL);
                    // apply edge label layout
                    LabelLayoutType ogmlLabelLayout = klabelLayout.getProperty(PROP_LABEL_LAYOUT);
                    if (ogmlLabelLayout == null) {
                        ogmlLabelLayout = OgmlFactory.eINSTANCE.createLabelLayoutType();
                        ogmlLabelLayout.setIdRef(ogmlLabel.getId());
                        layoutStyles.getLabelStyle().add(ogmlLabelLayout);
                    }
                    LocationType location = ogmlLabelLayout.getLocation();
                    if (location == null) {
                        location = OgmlFactory.eINSTANCE.createLocationType();
                        ogmlLabelLayout.setLocation(location);
                    }
                    location.setX(klabelLayout.getXpos() + klabelLayout.getWidth() / 2
                            + edgeOffset.x);
                    location.setY(klabelLayout.getYpos() + klabelLayout.getHeight() / 2
                            + edgeOffset.y);
                }
            }
            
            // apply layout for child nodes
            if (!knode.getChildren().isEmpty()) {
                KVector childOffset = new KVector(offset);
                childOffset.translate(knodeLayout.getXpos(), knodeLayout.getYpos());
                applyLayout(knode, childOffset, graph);
            }
        }
    }

}
