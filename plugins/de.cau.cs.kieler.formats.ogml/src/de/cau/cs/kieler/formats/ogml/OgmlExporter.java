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
package de.cau.cs.kieler.formats.ogml;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.graph.ElkBendPoint;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkLabel;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.formats.IGraphTransformer;
import de.cau.cs.kieler.formats.TransformationData;
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
import net.ogdf.ogml.OgmlType;
import net.ogdf.ogml.ShapeType1;
import net.ogdf.ogml.SourceTargetType;
import net.ogdf.ogml.StructureType;
import net.ogdf.ogml.StylesType;

/**
 * Graph exporter for OGML.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class OgmlExporter implements IGraphTransformer<ElkNode, DocumentRoot> {

    /** property for node and edge identifiers. */
    private static final IProperty<String> PROP_ID = new Property<String>("ogmlExporter.id");
    
    /**
     * {@inheritDoc}
     */
    public void transform(final TransformationData<ElkNode, DocumentRoot> data) {
        // create identifiers for all nodes and edges
        LinkedList<ElkNode> nodes = new LinkedList<>();
        nodes.add(data.getSourceGraph());
        int nodeId = 0, edgeId = 0, labelId = 0;
        do {
            ElkNode node = nodes.remove();
            node.setProperty(PROP_ID, "n" + Integer.toString(nodeId++));
            for (ElkEdge edge : node.getOutgoingEdges()) {
                edge.setProperty(PROP_ID, "e" + Integer.toString(edgeId++));
                for (ElkLabel label : edge.getLabels()) {
                    label.setProperty(PROP_ID, "l" + Integer.toString(labelId++));
                }
            }
            nodes.addAll(node.getChildren());
        } while (!nodes.isEmpty());
        
        // transform into OGML model
        DocumentRoot documentRoot = OgmlFactory.eINSTANCE.createDocumentRoot();
        OgmlType ogml = OgmlFactory.eINSTANCE.createOgmlType();
        GraphType graph = OgmlFactory.eINSTANCE.createGraphType();
        StructureType structure = OgmlFactory.eINSTANCE.createStructureType();
        graph.setStructure(structure);
        LayoutType layout = OgmlFactory.eINSTANCE.createLayoutType();
        StylesType styles = OgmlFactory.eINSTANCE.createStylesType();
        layout.setStyles(styles);
        graph.setLayout(layout);
        
        // transform the nodes
        transformNodes(data.getSourceGraph(), structure.getNode(), styles, new KVector());
        // transform the edges
        transformEdges(data.getSourceGraph(), structure.getEdge(), styles, new KVector());
        
        ogml.setGraph(graph);
        documentRoot.setOgml(ogml);
        data.getTargetGraphs().add(documentRoot);
    }
    
    /**
     * Transform the nodes of the given graph.
     * 
     * @param parentNode parent node of the graph
     * @param nodeList node list to which nodes are added
     * @param styles styles object for node layout
     * @param offset offset for node coordinates
     */
    private void transformNodes(final ElkNode parentNode, final List<NodeType> nodeList,
            final StylesType styles, final KVector offset) {
        
        for (ElkNode elknode : parentNode.getChildren()) {
            // transform node
            String nodeId = elknode.getProperty(PROP_ID);
            NodeType ogmlNode = OgmlFactory.eINSTANCE.createNodeType();
            ogmlNode.setId(nodeId);
            for (ElkLabel label : elknode.getLabels()) {
                LabelType nodeLabel = OgmlFactory.eINSTANCE.createLabelType();
                nodeLabel.setContent(label.getText());
                ogmlNode.getLabel().add(nodeLabel);
            }
            nodeList.add(ogmlNode);
            
            // transform node layout
            if (!elknode.getProperty(CoreOptions.NO_LAYOUT) && (elknode.getX() != 0
                    || elknode.getY() != 0 || elknode.getWidth() != 0
                    || elknode.getHeight() != 0)) {
                
                NodeLayoutType ogmlNodeLayout = OgmlFactory.eINSTANCE.createNodeLayoutType();
                ogmlNodeLayout.setIdRef(nodeId);
                if (elknode.getX() != 0 || elknode.getY() != 0) {
                    LocationType location = OgmlFactory.eINSTANCE.createLocationType();
                    location.setX(elknode.getX() + elknode.getWidth() / 2 + offset.x);
                    location.setY(elknode.getY() + elknode.getHeight() / 2 + offset.y);
                    ogmlNodeLayout.setLocation(location);
                }
                if (elknode.getWidth() != 0 || elknode.getHeight() != 0) {
                    ShapeType1 shape = OgmlFactory.eINSTANCE.createShapeType1();
                    shape.setWidth(BigInteger.valueOf(Math.round(elknode.getWidth())));
                    shape.setHeight(BigInteger.valueOf(Math.round(elknode.getHeight())));
                    ogmlNodeLayout.setShape(shape);
                }
                styles.getNodeStyle().add(ogmlNodeLayout);
            }
            
            if (!elknode.getChildren().isEmpty()) {
                // transform subgraph
                KVector suboffset = new KVector(offset);
                suboffset.add(elknode.getX(), elknode.getY());
                transformNodes(elknode, ogmlNode.getNode(), styles, suboffset);
            }
        }
    }
    
    /**
     * Transform the edges of the given graph.
     * 
     * @param parentNode parent node of the graph
     * @param edgeList edge list to which edges are added
     * @param styles styles object for edge layout
     * @param offset offset for edge coordinates
     */
    private void transformEdges(final ElkNode parentNode, final List<EdgeType> edgeList,
            final StylesType styles, final KVector offset) {
        
        for (ElkNode elknode : parentNode.getChildren()) {
            for (ElkEdge elkedge : elknode.getOutgoingEdges()) {
                // we only transform simple edges
                if (!elkedge.isConnected() || elkedge.isHyperedge()) {
                    continue;
                }
                
                // all edge coordinates need to be relative to our current parent node
                KVector edgeOffset = new KVector();
                ElkUtil.toAbsolute(edgeOffset, elkedge.getContainingNode());
                ElkUtil.toRelative(edgeOffset, parentNode);
                edgeOffset.add(offset);
                
                // find the edge's source and target nodes
                ElkNode sourceNode =
                        ElkGraphUtil.connectableShapeToNode(elkedge.getSources().get(0));
                ElkNode targetNode =
                        ElkGraphUtil.connectableShapeToNode(elkedge.getTargets().get(0));
                
                String edgeId = elkedge.getProperty(PROP_ID);
                EdgeType ogmlEdge = OgmlFactory.eINSTANCE.createEdgeType();
                ogmlEdge.setId(edgeId);
                edgeList.add(ogmlEdge);
                
                String sourceId = sourceNode.getProperty(PROP_ID);
                SourceTargetType source = OgmlFactory.eINSTANCE.createSourceTargetType();
                source.setIdRef(sourceId);
                ogmlEdge.getSource().add(source);
                
                String targetId = targetNode.getProperty(PROP_ID);
                SourceTargetType target = OgmlFactory.eINSTANCE.createSourceTargetType();
                target.setIdRef(targetId);
                ogmlEdge.getTarget().add(target);
                
                // transform edge layout, if there is one
                if (!elkedge.getProperty(CoreOptions.NO_LAYOUT)
                        && !elkedge.getSections().isEmpty()) {
                    
                    ElkEdgeSection section = elkedge.getSections().get(0);
                    if (section.getBendPoints().size() > 0 && section.getStartX() != 0
                            && section.getStartY() != 0 && section.getEndX() != 0
                            && section.getEndY() != 0) {
                        
                        EdgeLayoutType ogmlEdgeLayout =
                                OgmlFactory.eINSTANCE.createEdgeLayoutType();
                        ogmlEdgeLayout.setIdRef(edgeId);
                        ogmlEdgeLayout.getPoint().add(OgmlFormatHandler.createPoint(
                                section.getStartX(), section.getStartY(), edgeOffset));
                        for (ElkBendPoint bendPoint : section.getBendPoints()) {
                            ogmlEdgeLayout.getPoint().add(OgmlFormatHandler.createPoint(
                                    bendPoint.getX(), bendPoint.getY(), edgeOffset));
                        }
                        ogmlEdgeLayout.getPoint().add(OgmlFormatHandler.createPoint(
                                section.getEndX(), section.getEndY(), edgeOffset));
                        styles.getEdgeStyle().add(ogmlEdgeLayout);
                    }
                }
                
                // transform labels
                for (ElkLabel elklabel : elkedge.getLabels()) {
                    if (elklabel.getText().length() > 0) {
                        String labelId = elklabel.getProperty(PROP_ID);
                        LabelType ogmlLabel = OgmlFactory.eINSTANCE.createLabelType();
                        ogmlLabel.setContent(elklabel.getText());
                        ogmlLabel.setId(labelId);
                        if (elklabel.getX() != 0 || elklabel.getY() != 0) {
                            LabelLayoutType ogmlLabelLayout =
                                    OgmlFactory.eINSTANCE.createLabelLayoutType();
                            ogmlLabelLayout.setIdRef(labelId);
                            LocationType location = OgmlFactory.eINSTANCE.createLocationType();
                            location.setX(
                                    elklabel.getX() + elklabel.getWidth() / 2 + edgeOffset.x);
                            location.setY(
                                    elklabel.getY() + elklabel.getHeight() / 2 + edgeOffset.y);
                            ogmlLabelLayout.setLocation(location);
                            styles.getLabelStyle().add(ogmlLabelLayout);
                        }
                        ogmlEdge.getLabel().add(ogmlLabel);
                    }
                }
            }
            
            if (!elknode.getChildren().isEmpty()) {
                KVector suboffset = new KVector(offset);
                suboffset.add(elknode.getX(), elknode.getY());
                transformEdges(elknode, edgeList, styles, suboffset);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(final TransformationData<ElkNode, DocumentRoot> data) {
        throw new UnsupportedOperationException("Layout transfer is not supported for OGML export.");
    }

}
