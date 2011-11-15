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
import java.util.LinkedList;
import java.util.List;

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
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Graph exporter for OGML.
 *
 * @author msp
 */
public class OgmlExporter implements IGraphTransformer<KNode, DocumentRoot> {

    /** property for node and edge identifiers. */
    private static final IProperty<String> PROP_ID = new Property<String>("ogmlExporter.id");
    
    /**
     * {@inheritDoc}
     */
    public void transform(final TransformationData<KNode, DocumentRoot> data) {
        // create identifiers for all nodes and edges
        LinkedList<KNode> nodes = new LinkedList<KNode>();
        nodes.add(data.getSourceGraph());
        int nodeId = 0, edgeId = 0, labelId = 0;
        do {
            KNode node = nodes.remove();
            node.getData(KShapeLayout.class).setProperty(PROP_ID,
                    "n" + Integer.toString(nodeId++));
            for (KEdge edge : node.getOutgoingEdges()) {
                edge.getData(KEdgeLayout.class).setProperty(PROP_ID,
                        "e" + Integer.toString(edgeId++));
                for (KLabel label : edge.getLabels()) {
                    label.getData(KShapeLayout.class).setProperty(PROP_ID,
                            "l" + Integer.toString(labelId++));
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
    private void transformNodes(final KNode parentNode, final List<NodeType> nodeList,
            final StylesType styles, final KVector offset) {
        for (KNode knode : parentNode.getChildren()) {
            // transform node
            KShapeLayout knodeLayout = knode.getData(KShapeLayout.class);
            String nodeId = knodeLayout.getProperty(PROP_ID);
            NodeType ogmlNode = OgmlFactory.eINSTANCE.createNodeType();
            ogmlNode.setId(nodeId);
            if (knode.getLabel().getText().length() > 0) {
                LabelType nodeLabel = OgmlFactory.eINSTANCE.createLabelType();
                nodeLabel.setContent(knode.getLabel().getText());
                ogmlNode.getLabel().add(nodeLabel);
            }
            nodeList.add(ogmlNode);
            
            // transform node layout
            if (knodeLayout.getXpos() != 0 || knodeLayout.getYpos() != 0
                    || knodeLayout.getWidth() != 0 || knodeLayout.getHeight() != 0) {
                NodeLayoutType ogmlNodeLayout = OgmlFactory.eINSTANCE.createNodeLayoutType();
                ogmlNodeLayout.setIdRef(nodeId);
                if (knodeLayout.getXpos() != 0 || knodeLayout.getYpos() != 0) {
                    LocationType location = OgmlFactory.eINSTANCE.createLocationType();
                    location.setX(knodeLayout.getXpos() + knodeLayout.getWidth() / 2 + offset.x);
                    location.setY(knodeLayout.getYpos() + knodeLayout.getHeight() / 2 + offset.y);
                    ogmlNodeLayout.setLocation(location);
                }
                if (knodeLayout.getWidth() != 0 || knodeLayout.getHeight() != 0) {
                    ShapeType1 shape = OgmlFactory.eINSTANCE.createShapeType1();
                    shape.setWidth(BigInteger.valueOf(Math.round(knodeLayout.getWidth())));
                    shape.setHeight(BigInteger.valueOf(Math.round(knodeLayout.getHeight())));
                    ogmlNodeLayout.setShape(shape);
                }
                styles.getNodeStyle().add(ogmlNodeLayout);
            }
            
            if (!knode.getChildren().isEmpty()) {
                // transform subgraph
                KVector suboffset = new KVector(offset);
                suboffset.translate(knodeLayout.getXpos() + knodeLayout.getInsets().getLeft(),
                        knodeLayout.getYpos() + knodeLayout.getInsets().getTop());
                transformNodes(knode, ogmlNode.getNode(), styles, suboffset);
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
    private void transformEdges(final KNode parentNode, final List<EdgeType> edgeList,
            final StylesType styles, final KVector offset) {
        for (KNode knode : parentNode.getChildren()) {
            for (KEdge kedge : knode.getOutgoingEdges()) {
                // transform edge
                KEdgeLayout kedgeLayout = kedge.getData(KEdgeLayout.class);
                KVector edgeOffset = offset;
                if (KimlUtil.isDescendant(kedge.getTarget(), knode)) {
                    KShapeLayout sourceLayout = knode.getData(KShapeLayout.class);
                    edgeOffset = new KVector(offset).translate(sourceLayout.getXpos(),
                            sourceLayout.getYpos());
                }
                String edgeId = kedgeLayout.getProperty(PROP_ID);
                EdgeType ogmlEdge = OgmlFactory.eINSTANCE.createEdgeType();
                ogmlEdge.setId(edgeId);
                String sourceId = kedge.getSource().getData(KShapeLayout.class).getProperty(PROP_ID);
                SourceTargetType source = OgmlFactory.eINSTANCE.createSourceTargetType();
                source.setIdRef(sourceId);
                ogmlEdge.getSource().add(source);
                String targetId = kedge.getTarget().getData(KShapeLayout.class).getProperty(PROP_ID);
                SourceTargetType target = OgmlFactory.eINSTANCE.createSourceTargetType();
                target.setIdRef(targetId);
                ogmlEdge.getTarget().add(target);
                edgeList.add(ogmlEdge);
                
                // transform edge layout
                KPoint sourcePoint = kedgeLayout.getSourcePoint();
                KPoint targetPoint = kedgeLayout.getTargetPoint();
                if (kedgeLayout.getBendPoints().size() > 0
                        || sourcePoint.getX() != 0 || sourcePoint.getY() != 0
                        || targetPoint.getY() != 0 || targetPoint.getY() != 0) {
                    EdgeLayoutType ogmlEdgeLayout = OgmlFactory.eINSTANCE.createEdgeLayoutType();
                    ogmlEdgeLayout.setIdRef(edgeId);
                    ogmlEdgeLayout.getPoint().add(OgmlHandler.createPoint(sourcePoint, edgeOffset));
                    for (KPoint bendPoint : kedgeLayout.getBendPoints()) {
                        ogmlEdgeLayout.getPoint().add(OgmlHandler.createPoint(bendPoint, edgeOffset));
                    }
                    ogmlEdgeLayout.getPoint().add(OgmlHandler.createPoint(targetPoint, edgeOffset));
                    styles.getEdgeStyle().add(ogmlEdgeLayout);
                }
                
                // transform labels
                for (KLabel klabel : kedge.getLabels()) {
                    if (klabel.getText().length() > 0) {
                        KShapeLayout klabelLayout = klabel.getData(KShapeLayout.class);
                        String labelId = klabelLayout.getProperty(PROP_ID);
                        LabelType ogmlLabel = OgmlFactory.eINSTANCE.createLabelType();
                        ogmlLabel.setContent(klabel.getText());
                        ogmlLabel.setId(labelId);
                        if (klabelLayout.getXpos() != 0 || klabelLayout.getYpos() != 0) {
                            LabelLayoutType ogmlLabelLayout = OgmlFactory.eINSTANCE
                                    .createLabelLayoutType();
                            ogmlLabelLayout.setIdRef(labelId);
                            LocationType location = OgmlFactory.eINSTANCE.createLocationType();
                            location.setX(klabelLayout.getXpos() + klabelLayout.getWidth() / 2
                                    + edgeOffset.x);
                            location.setY(klabelLayout.getYpos() + klabelLayout.getHeight() / 2
                                    + edgeOffset.y);
                            ogmlLabelLayout.setLocation(location);
                            styles.getLabelStyle().add(ogmlLabelLayout);
                        }
                        ogmlEdge.getLabel().add(ogmlLabel);
                    }
                }
            }
            
            if (!knode.getChildren().isEmpty()) {
                KShapeLayout nodeLayout = knode.getData(KShapeLayout.class);
                KVector suboffset = new KVector(offset);
                suboffset.translate(nodeLayout.getXpos() + nodeLayout.getInsets().getLeft(),
                        nodeLayout.getYpos() + nodeLayout.getInsets().getTop());
                transformEdges(knode, edgeList, styles, suboffset);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(final TransformationData<KNode, DocumentRoot> data) {
        throw new UnsupportedOperationException("Layout transfer is not supported for OGML export.");
    }

}
