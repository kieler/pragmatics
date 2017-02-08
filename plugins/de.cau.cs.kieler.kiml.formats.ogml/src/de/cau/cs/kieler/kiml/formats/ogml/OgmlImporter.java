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
package de.cau.cs.kieler.kiml.formats.ogml;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.graph.ElkBendPoint;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkGraphElement;
import org.eclipse.elk.graph.ElkLabel;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.kiml.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.formats.TransformationData;
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

/**
 * Transformer for OGML.
 * 
 * <ul>
 * <li><b>Layout Options:</b> By convention any layout option related to a graph element is specified by
 * using OGML's {@link DataType} element and connected to this explicit element. The layout option
 * is stored in the {@link String} value of the {@link DataType} element as a key-value pair of the
 * form "option-id=value".</li>
 * </ul>
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class OgmlImporter implements IGraphTransformer<DocumentRoot, ElkNode> {

    /** map of OGML node identifiers to KNodes. */
    private static final IProperty<Map<String, ElkNode>> NODE_ID_MAP = new Property<>(
            "ogmlImporter.nodeIdMap");
    /** map of OGML edge identifiers to KEdges. */
    private static final IProperty<Map<String, ElkEdge>> EDGE_ID_MAP = new Property<>(
            "ogmlImporter.edgeIdMap");
    /** map of OGML label identifiers to KLabels. */
    private static final IProperty<Map<String, ElkLabel>> LABEL_ID_MAP = new Property<>(
            "ogmlImporter.labelIdMap");
    /** OGML node attached to each new KNode. */
    private static final IProperty<NodeType> PROP_NODE = new Property<NodeType>("ogmlImporter.node");
    /** node layout attached to each new KNode, if present. */
    private static final IProperty<NodeLayoutType> PROP_NODE_LAYOUT = new Property<>(
            "ogmlImporter.nodeLayout");
    /** OGML edge attached to each new KEdge. */
    private static final IProperty<EdgeType> PROP_EDGE = new Property<EdgeType>("ogmlImporter.edge");
    /** edge layout attached to each new KEdge, if present. */
    private static final IProperty<EdgeLayoutType> PROP_EDGE_LAYOUT = new Property<>(
            "ogmlImporter.edgeLayout");
    /** OGML label attached to each new KLabel. */
    private static final IProperty<LabelType> PROP_LABEL = new Property<>(
            "ogmlImporter.label");
    /** label layout attached to each new KLabel, if present. */
    private static final IProperty<LabelLayoutType> PROP_LABEL_LAYOUT = new Property<>(
            "ogmlImporter.labelLayout");
    /** original OGML identifiers attached to graph elements. */
    private static final IProperty<String> PROP_ID = new Property<>("ogmlImporter.id");

    /**
     * {@inheritDoc}
     */
    public void transform(final TransformationData<DocumentRoot, ElkNode> transData) {
        GraphType graph = transData.getSourceGraph().getOgml().getGraph();
        if (graph.getStructure() != null) {
            Map<String, ElkNode> nodeIdMap = Maps.newHashMap();
            transData.setProperty(NODE_ID_MAP, nodeIdMap);
            Map<String, ElkEdge> edgeIdMap = Maps.newHashMap();
            transData.setProperty(EDGE_ID_MAP, edgeIdMap);
            Map<String, ElkLabel> labelIdMap = Maps.newHashMap();
            transData.setProperty(LABEL_ID_MAP, labelIdMap);
            transformGraph(graph, transData);
        }
        graph.toString();
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(final TransformationData<DocumentRoot, ElkNode> transData) {
        for (ElkNode layoutNode : transData.getTargetGraphs()) {
            applyLayout(layoutNode, new KVector(), transData.getSourceGraph().getOgml().getGraph());
        }
    }

    // ---------- Transformation OGML to KGraph ----------//

    /**
     * Transform the given list of nodes.
     * 
     * @param nodes
     *            a list of OGDF nodes
     * @param parent
     *            the parent KNode to which new nodes are added
     * @param transData
     *            transformation data
     */
    private void transform(final List<NodeType> nodes, final ElkNode parent,
            final TransformationData<DocumentRoot, ElkNode> transData) {
        
        for (NodeType node : nodes) {
            ElkNode elknode = transformNode(node.getId(), parent, transData);
            elknode.setProperty(PROP_NODE, node);
            for (LabelType label : node.getLabel()) {
                ElkGraphUtil.createLabel(label.getContent(), elknode);
            }
            // transform layout options
            for (DataType data : node.getData()) {
                convertLayoutOption(elknode, data);
            }
            // transform subgraph
            transform(node.getNode(), elknode, transData);
        }
    }

    /**
     * Transform the contents of an OGDF graph into a KNode.
     * 
     * @param graph
     *            an OGDF graph
     * @param transData
     *            transformation data
     * @return a new top-level KNode
     */
    private void transformGraph(final GraphType graph,
            final TransformationData<DocumentRoot, ElkNode> transData) {
        
        ElkNode parent = ElkGraphUtil.createGraph();
        
        // transform layout options
        for (DataType data : graph.getData()) {
            convertLayoutOption(parent, data);
        }

        // transform nodes
        transform(graph.getStructure().getNode(), parent, transData);

        Map<String, ElkEdge> edgeIdMap = transData.getProperty(EDGE_ID_MAP);
        Map<String, ElkLabel> labelIdMap = transData.getProperty(LABEL_ID_MAP);
        for (EdgeType edge : graph.getStructure().getEdge()) {
            // transform edges
            if (edge.getSource().size() == 1 && edge.getTarget().size() == 1) {
                ElkNode source = transformNode(
                        edge.getSource().get(0).getIdRef(), parent, transData);
                ElkNode target = transformNode(
                        edge.getTarget().get(0).getIdRef(), parent, transData);
                ElkEdge elkedge = ElkGraphUtil.createSimpleEdge(source, target);
                edgeIdMap.put(edge.getId(), elkedge);
                elkedge.setProperty(PROP_EDGE, edge);
                // transform layout options
                for (DataType data : edge.getData()) {
                    convertLayoutOption(elkedge, data);
                }
                // transform edge labels
                for (LabelType label : edge.getLabel()) {
                    ElkLabel elklabel = ElkGraphUtil.createLabel(label.getContent(), elkedge);
                    labelIdMap.put(label.getId(), elklabel);
                    elklabel.setProperty(PROP_LABEL, label);
                    // transform layout options
                    for (DataType data : label.getData()) {
                        convertLayoutOption(elklabel, data);
                    }
                }

                // transform hyperedges
            } else if (edge.getSource().size() > 1 || edge.getTarget().size() > 1) {
                // TODO We could properly support hyperedges here at some point
                ElkNode hypernode = ElkGraphUtil.createNode(parent);
                hypernode.setProperty(CoreOptions.HYPERNODE, true);
                
                for (SourceTargetType sourceref : edge.getSource()) {
                    ElkNode source = transformNode(sourceref.getIdRef(), parent, transData);
                    ElkGraphUtil.createSimpleEdge(source, hypernode);
                }
                
                for (SourceTargetType targetref : edge.getTarget()) {
                    ElkNode target = transformNode(targetref.getIdRef(), parent, transData);
                    ElkGraphUtil.createSimpleEdge(hypernode, target);
                }
                
                for (LabelType label : edge.getLabel()) {
                    ElkGraphUtil.createLabel(label.getContent(), hypernode);
                }
            }
        }

        transData.getTargetGraphs().add(parent);

        if (graph.getLayout() != null && graph.getLayout().getStyles() != null) {
            // transform layout options
            for (DataType data : graph.getLayout().getData()) {
                convertLayoutOption(parent, data);
            }
            transformLayout(graph.getLayout(), transData);
        }
    }

    /** default width and height value for nodes. */
    private static final float DEF_WIDTH = 10.0f;

    /**
     * Transforms a single node, if not already done before.
     * 
     * @param nodeId
     *            a node identifier
     * @param parent
     *            the parent where the new KNode is stored
     * @param transData
     *            transformation data
     * @return a KNode instance
     */
    private ElkNode transformNode(final String nodeId, final ElkNode parent,
            final TransformationData<DocumentRoot, ElkNode> transData) {
        
        Map<String, ElkNode> nodeIdMap = transData.getProperty(NODE_ID_MAP);
        ElkNode elknode = nodeIdMap.get(nodeId);
        if (elknode == null) {
            elknode = ElkGraphUtil.createNode(parent);
            elknode.setWidth(DEF_WIDTH);
            elknode.setHeight(DEF_WIDTH);
            
            if (nodeId != null) {
                nodeIdMap.put(nodeId, elknode);
                elknode.setProperty(PROP_ID, nodeId);
            }
        }
        return elknode;
    }

    /**
     * Transform the layout of the graph as given in the OGML file.
     * 
     * @param layout
     *            the OGML layout
     * @param transData
     *            transformation data
     */
    private void transformLayout(final LayoutType layout,
            final TransformationData<DocumentRoot, ElkNode> transData) {
        
        // transform node layouts
        Map<String, ElkNode> nodeIdMap = transData.getProperty(NODE_ID_MAP);
        for (NodeLayoutType ogmlNodeLayout : layout.getStyles().getNodeStyle()) {
            ElkNode elknode = nodeIdMap.get(ogmlNodeLayout.getIdRef());
            if (elknode != null) {
                elknode.setProperty(PROP_NODE_LAYOUT, ogmlNodeLayout);
                ShapeType1 shape = ogmlNodeLayout.getShape();
                if (shape != null) {
                    elknode.setWidth(shape.getWidth().doubleValue());
                    elknode.setHeight(shape.getHeight().doubleValue());
                }
                LocationType location = ogmlNodeLayout.getLocation();
                if (location != null) {
                    elknode.setX(location.getX() - elknode.getWidth() / 2);
                    elknode.setY(location.getY() - elknode.getHeight() / 2);
                }
                
                // transform layout options
                for (DataType data : ogmlNodeLayout.getData()) {
                    convertLayoutOption(elknode, data);
                }
            }
        }

        // transform edge layouts
        Map<String, ElkEdge> edgeIdMap = transData.getProperty(EDGE_ID_MAP);
        for (EdgeLayoutType ogmlEdgeLayout : layout.getStyles().getEdgeStyle()) {
            ElkEdge elkedge = edgeIdMap.get(ogmlEdgeLayout.getIdRef());
            if (elkedge != null) {
                elkedge.setProperty(PROP_EDGE_LAYOUT, ogmlEdgeLayout);
                
                // transform layout options
                for (DataType data : ogmlEdgeLayout.getData()) {
                    convertLayoutOption(elkedge, data);
                }
            }
        }

        // transform edge label layouts
        Map<String, ElkLabel> labelIdMap = transData.getProperty(LABEL_ID_MAP);
        for (LabelLayoutType ogmlLabelLayout : layout.getStyles().getLabelStyle()) {
            ElkLabel elklabel = labelIdMap.get(ogmlLabelLayout.getIdRef());
            if (elklabel != null) {
                elklabel.setProperty(PROP_LABEL_LAYOUT, ogmlLabelLayout);
                
                // transform layout options
                for (DataType data : ogmlLabelLayout.getData()) {
                    convertLayoutOption(elklabel, data);
                }
            }
        }
    }

    /*---------- Layout Transfer KGraph to OGML ----------*/

    /**
     * Apply layout to the elements of the given graph.
     * 
     * @param parent
     *            the parent node of the KGraph
     * @param offset
     *            the offset of the parent node
     * @param graph
     *            the corresponding OGML graph
     */
    private void applyLayout(final ElkNode parent, final KVector offset, final GraphType graph) {
        if (graph.getLayout() == null) {
            graph.setLayout(OgmlFactory.eINSTANCE.createLayoutType());
        }
        StylesType layoutStyles = graph.getLayout().getStyles();
        if (layoutStyles == null) {
            layoutStyles = OgmlFactory.eINSTANCE.createStylesType();
            graph.getLayout().setStyles(layoutStyles);
        }
        for (ElkNode elknode : parent.getChildren()) {
            // create node if it is not present yet
            NodeType ogmlNode = elknode.getProperty(PROP_NODE);
            if (ogmlNode == null) {
                String id = elknode.getProperty(PROP_ID);
                if (id != null) {
                    ogmlNode = OgmlFactory.eINSTANCE.createNodeType();
                    ogmlNode.setId(id);
                    graph.getStructure().getNode().add(ogmlNode);
                }
            }
            
            if (ogmlNode != null) {
                // apply node layout
                NodeLayoutType ogmlNodeLayout = elknode.getProperty(PROP_NODE_LAYOUT);
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
                location.setX(elknode.getX() + elknode.getWidth() / 2 + offset.x);
                location.setY(elknode.getY() + elknode.getHeight() / 2 + offset.y);
                ShapeType1 shape = ogmlNodeLayout.getShape();
                if (shape == null) {
                    shape = OgmlFactory.eINSTANCE.createShapeType1();
                    ogmlNodeLayout.setShape(shape);
                }
                shape.setWidth(BigInteger.valueOf(Math.round(elknode.getWidth())));
                shape.setHeight(BigInteger.valueOf(Math.round(elknode.getHeight())));
            }

            for (ElkEdge elkedge : elknode.getOutgoingEdges()) {
                // make sure we have an edge section to work with
                if (elkedge.getSections().isEmpty()) {
                    continue;
                }
                
                // calculate proper coordinate offset
                KVector edgeOffset = new KVector(offset);
                ElkUtil.toAbsolute(edgeOffset, elkedge.getContainingNode());
                ElkUtil.toRelative(edgeOffset, parent);
                
                // create edge if it is not present yet
                EdgeType ogmlEdge = elkedge.getProperty(PROP_EDGE);
                if (ogmlEdge == null) {
                    ogmlEdge = OgmlFactory.eINSTANCE.createEdgeType();
                    ogmlEdge.setId("edge" + elkedge.hashCode());
                    graph.getStructure().getEdge().add(ogmlEdge);
                }
                
                // apply edge layout
                ElkEdgeSection edgeSection = elkedge.getSections().get(0);
                EdgeLayoutType ogmlEdgeLayout = elkedge.getProperty(PROP_EDGE_LAYOUT);
                if (ogmlEdgeLayout == null) {
                    ogmlEdgeLayout = OgmlFactory.eINSTANCE.createEdgeLayoutType();
                    ogmlEdgeLayout.setIdRef(ogmlEdge.getId());
                    layoutStyles.getEdgeStyle().add(ogmlEdgeLayout);
                }
                ogmlEdgeLayout.getPoint().clear();
                ogmlEdgeLayout.getPoint().add(OgmlFormatHandler.createPoint(
                        edgeSection.getStartX(), edgeSection.getStartY(), edgeOffset));
                for (ElkBendPoint bendPoint : edgeSection.getBendPoints()) {
                    ogmlEdgeLayout.getPoint().add(OgmlFormatHandler.createPoint(
                            bendPoint.getX(), bendPoint.getY(), edgeOffset));
                }
                ogmlEdgeLayout.getPoint().add(OgmlFormatHandler.createPoint(
                        edgeSection.getEndX(), edgeSection.getEndY(), edgeOffset));

                for (ElkLabel elklabel : elkedge.getLabels()) {
                    LabelType ogmlLabel = elklabel.getProperty(PROP_LABEL);
                    // apply edge label layout
                    LabelLayoutType ogmlLabelLayout = elklabel.getProperty(PROP_LABEL_LAYOUT);
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
                    location.setX(elklabel.getX() + elklabel.getWidth() / 2 + edgeOffset.x);
                    location.setY(elklabel.getY() + elklabel.getHeight() / 2 + edgeOffset.y);
                }
            }

            // apply layout for child nodes
            if (!elknode.getChildren().isEmpty()) {
                KVector childOffset = new KVector(offset);
                childOffset.add(elknode.getX(), elknode.getY());
                applyLayout(elknode, childOffset, graph);
            }
        }
    }

    private void convertLayoutOption(final ElkGraphElement layout, final DataType option) {
        String[] splittedOption = option.getValue().split("=");
        if (splittedOption.length == 2) {
            setOption(layout, splittedOption[0], splittedOption[1]);
        }
    }
    
    /**
     * Set a layout option using a serialized key / value pair.
     * 
     * @param graphElement the graph data instance to modify
     * @param id the layout option identifier
     * @param value the value for the layout option
     */
    private static void setOption(final ElkGraphElement graphElement, final String id, final String value) {
        LayoutOptionData optionData = LayoutMetaDataService.getInstance().getOptionData(id);
        if (optionData != null) {
            Object obj = optionData.parseValue(value);
            if (obj != null) {
                graphElement.setProperty(optionData, obj);
            }
        }
    }

}
