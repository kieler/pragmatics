/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ogdf;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;

import net.ogdf.lib.DPoint;
import net.ogdf.lib.DPointListConstIterator;
import net.ogdf.lib.DPolyline;
import net.ogdf.lib.DRect;
import net.ogdf.lib.ELabelInterfaceDouble;
import net.ogdf.lib.ELabelPosSimple;
import net.ogdf.lib.EdgeElement;
import net.ogdf.lib.EdgeLabelDouble;
import net.ogdf.lib.Graph;
import net.ogdf.lib.GraphAttributes;
import net.ogdf.lib.LayoutModule;
import net.ogdf.lib.NodeElement;
import net.ogdf.lib.UMLGraph;
import net.ogdf.lib.eLabelTyp;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.layout.options.EdgeType;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

/**
 * The base wrapper class for all OGDF layouters.
 * 
 * @author mri
 */
public abstract class OgdfLayouter {

    /**
     * Sets the layout specific options and modules depending on the options
     * defined in the node.
     * 
     * @param node
     *            the node
     * @param progressMonitor
     *            the progress monitor
     * @return the layout module
     */
    protected LayoutModule prepareLayouter(final KNode node,
            final IKielerProgressMonitor progressMonitor) {
        return null;
    }

    /**
     * Layouts the given graph.
     * 
     * @param layoutNode
     *            the node representing the graph
     * @param progressMonitor
     *            the progress monitor
     */
    public void doLayout(final KNode layoutNode,
            final IKielerProgressMonitor progressMonitor) {
        // transform the graph
        GraphAttributes graphAttributes = transformGraph(layoutNode);

        // prepares the algorithm for use
        LayoutModule layout = prepareLayouter(layoutNode, progressMonitor);

        if (layout != null) {
            // call the algorithm
            layout.call(graphAttributes);

            // apply the layout back to the original graph
            applyLayout(layoutNode, graphAttributes);
        }
    }

    /**
     * {@inheritDoc}
     */
    public abstract Object getDefault(final String optionId);

    /** map of KIELER nodes to OGDF nodes. */
    private Map<KNode, NodeElement> knode2ogdfNodeMap = new LinkedHashMap<KNode, NodeElement>();

    /** map of KIELER edges to OGDF edges. */
    private Map<KEdge, EdgeElement> kedge2ogdfEdgeMap = new LinkedHashMap<KEdge, EdgeElement>();

    /** holds the OGDF edge labels. */
    private ELabelInterfaceDouble labelInterface = null;

    /**
     * Transforms a DPoint to a KPoint.
     * 
     * @param point
     *            the point
     * @return the point as KPoint
     */
    private KPoint transformPoint(final DPoint point, final float offsetX,
            final float offsetY) {
        KPoint kpoint = KLayoutDataFactory.eINSTANCE.createKPoint();
        kpoint.setX((float) point.getX() + offsetX);
        kpoint.setY((float) point.getY() + offsetY);
        return kpoint;
    }

    /**
     * Assigns OGDF geometry to a KShape layout.
     * 
     * @param layout
     *            the shape layout
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     * @param width
     *            the width
     * @param height
     *            the height
     */
    private void assignOgdfGeometryToKShape(final KShapeLayout layout,
            final double x, final double y, final double width,
            final double height) {
        layout.setXpos((float) (x - width / 2));
        layout.setYpos((float) (y - height / 2));
        layout.setWidth((float) width);
        layout.setHeight((float) height);
    }

    /**
     * Assigns KShape geometry to an OGDF node.
     * 
     * @param graphAttributes
     *            the graph attributes
     * @param ogdfNode
     *            the node
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     * @param width
     *            the width
     * @param height
     *            the height
     */
    private void assignKGeometryToOgdfNode(
            final GraphAttributes graphAttributes, final NodeElement ogdfNode,
            final double x, final double y, final double width,
            final double height) {
        graphAttributes.setNodeX(ogdfNode, x + width / 2);
        graphAttributes.setNodeY(ogdfNode, y + height / 2);
        graphAttributes.setNodeWidth(ogdfNode, width);
        graphAttributes.setNodeHeight(ogdfNode, height);
    }

    /**
     * Assigns KShape geometry to an OGDF edge label.
     * 
     * @param graphAttributes
     *            the graph attributes
     * @param edgeLabel
     *            the edge label
     * @param type
     *            the edge label type
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     * @param width
     *            the width
     * @param height
     *            the height
     */
    private void assignKGeometryToOgdfEdgeLabel(
            final GraphAttributes graphAttributes,
            final EdgeLabelDouble edgeLabel, final eLabelTyp type,
            final double x, final double y, final double width,
            final double height) {
        edgeLabel.addType(type);
        edgeLabel.setX(type, x + width / 2);
        edgeLabel.setY(type, y + height / 2);
        edgeLabel.setWidth(type, width);
        edgeLabel.setHeight(type, height);
    }

    /**
     * Transforms the given layout graph into an OGDF graph.
     * 
     * @param layoutNode
     *            the parent node of the layout graph
     * @return an OGDF graph with attached layout attributes
     */
    protected GraphAttributes transformGraph(final KNode layoutNode) {
        knode2ogdfNodeMap.clear();
        kedge2ogdfEdgeMap.clear();
        Graph graph = new Graph();
        GraphAttributes graphAttributes = new GraphAttributes(graph,
                GraphAttributes.nodeGraphics | GraphAttributes.edgeGraphics
                        | GraphAttributes.edgeLabel);
        // process nodes
        for (KNode knode : layoutNode.getChildren()) {
            NodeElement ogdfNode = graph.newNode();
            KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(knode);
            assignKGeometryToOgdfNode(graphAttributes, ogdfNode, shapeLayout
                    .getXpos(), shapeLayout.getYpos(), shapeLayout.getWidth(),
                    shapeLayout.getHeight());
            knode2ogdfNodeMap.put(knode, ogdfNode);
        }
        // create an interface for label layout
        labelInterface = new ELabelInterfaceDouble(graphAttributes);
        // process edges
        for (KNode knode1 : layoutNode.getChildren()) {
            for (KEdge kedge : knode1.getOutgoingEdges()) {
                KNode knode2 = kedge.getTarget();
                if (knode2.getParent() == knode1.getParent()) {
                    NodeElement ogdfNode1 = knode2ogdfNodeMap.get(knode1);
                    NodeElement ogdfNode2 = knode2ogdfNodeMap.get(knode2);
                    EdgeElement ogdfEdge = graph.newEdge(ogdfNode1, ogdfNode2);
                    kedge2ogdfEdgeMap.put(kedge, ogdfEdge);

                    // create an ogdf label and attach it to the edge
                    EdgeLabelDouble edgeLabel = new EdgeLabelDouble();
                    edgeLabel.setEdge(ogdfEdge);
                    // set the labels
                    EList<KLabel> labels = kedge.getLabels();
                    for (KLabel label : labels) {
                        KShapeLayout labelLayout = KimlLayoutUtil
                                .getShapeLayout(label);
                        EdgeLabelPlacement placement = LayoutOptions.getEnum(
                                labelLayout, EdgeLabelPlacement.class);
                        switch (placement) {
                        case CENTER:
                            assignKGeometryToOgdfEdgeLabel(graphAttributes,
                                    edgeLabel, eLabelTyp.elName, labelLayout
                                            .getXpos(), labelLayout.getYpos(),
                                    labelLayout.getWidth(), labelLayout
                                            .getHeight());
                            break;
                        case HEAD:
                            assignKGeometryToOgdfEdgeLabel(graphAttributes,
                                    edgeLabel, eLabelTyp.elEnd1, labelLayout
                                            .getXpos(), labelLayout.getYpos(),
                                    labelLayout.getWidth(), labelLayout
                                            .getHeight());
                            break;
                        case TAIL:
                            assignKGeometryToOgdfEdgeLabel(graphAttributes,
                                    edgeLabel, eLabelTyp.elEnd2, labelLayout
                                            .getXpos(), labelLayout.getYpos(),
                                    labelLayout.getWidth(), labelLayout
                                            .getHeight());
                            break;
                        case UNDEFINED:
                            // TODO handle this case
                            break;
                        }
                    }
                    // add the edge label to the ogdf label interface
                    labelInterface.setLabel(ogdfEdge, edgeLabel);
                }
            }
        }

        return graphAttributes;
    }

    /**
     * Transforms the given layout graph into an OGDF UMLGraph.
     * 
     * @param layoutNode
     *            the parent node of the layout graph
     * @return an OGDF UMLGraph with attached layout attributes
     */
    protected UMLGraph transformUMLGraph(final KNode layoutNode) {
        knode2ogdfNodeMap.clear();
        kedge2ogdfEdgeMap.clear();
        Graph graph = new Graph();
        UMLGraph graphAttributes = new UMLGraph(graph,
                GraphAttributes.nodeGraphics | GraphAttributes.edgeGraphics
                        | GraphAttributes.edgeLabel | GraphAttributes.edgeType);
        // process nodes
        for (KNode knode : layoutNode.getChildren()) {
            NodeElement ogdfNode = graph.newNode();
            KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(knode);
            assignKGeometryToOgdfNode(graphAttributes, ogdfNode, shapeLayout
                    .getXpos(), shapeLayout.getYpos(), shapeLayout.getWidth(),
                    shapeLayout.getHeight());
            // remember the node mapping
            knode2ogdfNodeMap.put(knode, ogdfNode);
        }
        // create an interface for label layout
        labelInterface = new ELabelInterfaceDouble(graphAttributes);
        // process edges
        for (KNode knode1 : layoutNode.getChildren()) {
            for (KEdge kedge : knode1.getOutgoingEdges()) {
                KNode knode2 = kedge.getTarget();
                if (knode2.getParent() == knode1.getParent()) {
                    // no self loops allowed in ogdf uml layouts
                    // TODO find a better way to solve this problem
                    if (knode2 == knode1) {
                        continue;
                    }
                    NodeElement ogdfNode1 = knode2ogdfNodeMap.get(knode1);
                    NodeElement ogdfNode2 = knode2ogdfNodeMap.get(knode2);
                    EdgeElement ogdfEdge = graph.newEdge(ogdfNode1, ogdfNode2);
                    // set the hierarchy type of the edge
                    KEdgeLayout edgeLayout = KimlLayoutUtil
                            .getEdgeLayout(kedge);
                    EdgeType edgeType = LayoutOptions.getEnum(edgeLayout,
                            EdgeType.class);
                    switch (edgeType) {
                    case ASSOCIATION:
                        graphAttributes.setEdgeType(ogdfEdge,
                                Graph.EdgeType.association);
                        break;
                    case GENERALIZATION:
                        graphAttributes.setEdgeType(ogdfEdge,
                                Graph.EdgeType.generalization);
                        break;
                    case DEPENDENCY:
                    case NONE:
                    default:
                    }
                    // remember the edge mapping
                    kedge2ogdfEdgeMap.put(kedge, ogdfEdge);

                    // create an ogdf label and attach it to the edge
                    EdgeLabelDouble edgeLabel = new EdgeLabelDouble();
                    edgeLabel.setEdge(ogdfEdge);
                    // set the labels
                    EList<KLabel> labels = kedge.getLabels();
                    for (KLabel label : labels) {
                        KShapeLayout labelLayout = KimlLayoutUtil
                                .getShapeLayout(label);
                        EdgeLabelPlacement placement = LayoutOptions.getEnum(
                                labelLayout, EdgeLabelPlacement.class);
                        switch (placement) {
                        case CENTER:
                            assignKGeometryToOgdfEdgeLabel(graphAttributes,
                                    edgeLabel, eLabelTyp.elName, labelLayout
                                            .getXpos(), labelLayout.getYpos(),
                                    labelLayout.getWidth(), labelLayout
                                            .getHeight());
                            break;
                        case HEAD:
                            assignKGeometryToOgdfEdgeLabel(graphAttributes,
                                    edgeLabel, eLabelTyp.elEnd1, labelLayout
                                            .getXpos(), labelLayout.getYpos(),
                                    labelLayout.getWidth(), labelLayout
                                            .getHeight());
                            break;
                        case TAIL:
                            assignKGeometryToOgdfEdgeLabel(graphAttributes,
                                    edgeLabel, eLabelTyp.elEnd2, labelLayout
                                            .getXpos(), labelLayout.getYpos(),
                                    labelLayout.getWidth(), labelLayout
                                            .getHeight());
                            break;
                        case UNDEFINED:
                            // TODO handle this case
                            break;
                        }
                    }
                    // add the edge label to the ogdf label interface
                    labelInterface.setLabel(ogdfEdge, edgeLabel);
                }
            }
        }

        return graphAttributes;
    }

    /**
     * Applies the layout result to the original graph.
     * 
     * @param parentNode
     *            the parent node of the layout graph
     * @param graphAttributes
     *            OGDF graph with attached layout attributes
     */
    protected void applyLayout(final KNode parentNode,
            final GraphAttributes graphAttributes) {
        // include intersections with the nodes bounding boxes in the bends
        graphAttributes.addNodeCenter2Bends(1);
        // get the parent node layout
        KShapeLayout parentNodeLayout = KimlLayoutUtil
                .getShapeLayout(parentNode);
        DRect boundingBox = graphAttributes.boundingBox();
        // get the border spacing
        float borderSpacing = LayoutOptions.getFloat(parentNodeLayout,
                LayoutOptions.BORDER_SPACING);
        if (Float.isNaN(borderSpacing)) {
            Object borderSpacingObj = getDefault(LayoutOptions.BORDER_SPACING);
            if (borderSpacingObj instanceof Float) {
                borderSpacing = (Float) borderSpacingObj;
            } else {
                borderSpacing = 0;
            }
        }
        // calculate offsets
        float offsetX = (float) -boundingBox.p1().getX() + borderSpacing;
        float offsetY = (float) -boundingBox.p1().getY() + borderSpacing;
        // apply node layout
        for (KNode knode : knode2ogdfNodeMap.keySet()) {
            NodeElement ogdfNode = knode2ogdfNodeMap.get(knode);
            KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(knode);
            assignOgdfGeometryToKShape(nodeLayout, graphAttributes
                    .getNodeX(ogdfNode)
                    + offsetX, graphAttributes.getNodeY(ogdfNode) + offsetY,
                    graphAttributes.getNodeWidth(ogdfNode), graphAttributes
                            .getNodeHeight(ogdfNode));
        }

        // calculate new label positions and assign them to the mapped labels
        // TODO use the customizable label layouter instead of this simple one
        ELabelPosSimple labelLayouter = new ELabelPosSimple();
        labelLayouter.call(graphAttributes, labelInterface);

        // apply edge layout
        for (KEdge kedge : kedge2ogdfEdgeMap.keySet()) {
            EdgeElement ogdfEdge = kedge2ogdfEdgeMap.get(kedge);
            KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(kedge);
            DPolyline bends = graphAttributes.bends(ogdfEdge);
            // are source and target point present?
            if (bends.size() >= 2) {
                DPointListConstIterator it = bends.iterator();
                EList<KPoint> kbends = edgeLayout.getBendPoints();
                kbends.clear();
                // set the source point
                DPoint first = it.next();
                edgeLayout.setSourcePoint(transformPoint(first, offsetX,
                        offsetY));
                // set the bend points
                while (it.hasNext()) {
                    DPoint point = it.next();
                    if (!it.hasNext()) {
                        edgeLayout.setTargetPoint(transformPoint(point,
                                offsetX, offsetY));
                    } else {
                        // set the target point
                        kbends.add(transformPoint(point, offsetX, offsetY));
                    }
                }
            }
            // set the edge labels
            EdgeLabelDouble edgeLabel = labelInterface.getLabel(ogdfEdge);
            EList<KLabel> labels = kedge.getLabels();
            for (KLabel label : labels) {
                KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(label);
                EdgeLabelPlacement placement = LayoutOptions.getEnum(
                        labelLayout, EdgeLabelPlacement.class);
                switch (placement) {
                case CENTER:
                    // should never happen
                    if (!edgeLabel.usedLabel(eLabelTyp.elName)) {
                        continue;
                    }
                    assignOgdfGeometryToKShape(labelLayout, edgeLabel
                            .getX(eLabelTyp.elName)
                            + offsetX, edgeLabel.getY(eLabelTyp.elName)
                            + offsetY, edgeLabel.getWidth(eLabelTyp.elName),
                            edgeLabel.getHeight(eLabelTyp.elName));
                    break;
                case HEAD:
                    // should never happen
                    if (!edgeLabel.usedLabel(eLabelTyp.elEnd1)) {
                        continue;
                    }
                    assignOgdfGeometryToKShape(labelLayout, edgeLabel
                            .getX(eLabelTyp.elEnd1)
                            + offsetX, edgeLabel.getY(eLabelTyp.elEnd1)
                            + offsetY, edgeLabel.getWidth(eLabelTyp.elEnd1),
                            edgeLabel.getHeight(eLabelTyp.elEnd1));
                    break;
                case TAIL:
                    // should never happen
                    if (!edgeLabel.usedLabel(eLabelTyp.elEnd2)) {
                        continue;
                    }
                    assignOgdfGeometryToKShape(labelLayout, edgeLabel
                            .getX(eLabelTyp.elEnd2)
                            + offsetX, edgeLabel.getY(eLabelTyp.elEnd2)
                            + offsetY, edgeLabel.getWidth(eLabelTyp.elEnd2),
                            edgeLabel.getHeight(eLabelTyp.elEnd2));
                    break;
                case UNDEFINED:
                    // TODO handle this case
                    break;
                }
            }
        }
        // get the insets
        KInsets insets = LayoutOptions.getObject(parentNodeLayout,
                KInsets.class);
        // set the width/height of the graph
        parentNodeLayout.setWidth((float) boundingBox.width() + 2
                * borderSpacing + insets.getLeft() + insets.getRight());
        parentNodeLayout.setHeight((float) boundingBox.height() + 2
                * borderSpacing + insets.getTop() + insets.getBottom());
    }
}
