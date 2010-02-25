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
import net.ogdf.lib.UMLLayoutModule;
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

    /** layout option identifier for label edge distance. */
    public static final String OPT_LABEL_EDGE_DISTANCE =
        "de.cau.cs.kieler.kiml.ogdf.option.labelEdgeDistance";
    
    /** layout option identifier for label margin distance. */
    public static final String OPT_LABEL_MARGIN_DISTANCE =
        "de.cau.cs.kieler.kiml.ogdf.option.labelMarginDistance";
    
    /**
     * Layouts the given graph.
     * 
     * @param layoutNode the node representing the graph
     * @param progressMonitor the progress monitor
     */
    public void doLayout(final KNode layoutNode,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("OGDF Layout", 1);

        // prepare the algorithm for use and pre-process the graph
        LayoutModule layoutModule = prepareLayouter(layoutNode);
        // transform the graph
        GraphAttributes graphAttributes = transformGraph(layoutNode);
        // call the algorithm to determine node and edge positions
        if (layoutModule instanceof UMLLayoutModule && graphAttributes instanceof UMLGraph) {
            ((UMLLayoutModule) layoutModule).call((UMLGraph) graphAttributes);
        } else {
            layoutModule.call(graphAttributes);
        }
        // calculate new label positions and assign them to the mapped labels
        if (labelInterface != null) {
            layoutLabels(layoutNode, graphAttributes, labelInterface);
        }
        // perform post-processing
        postProcess(layoutNode);
        // apply the layout back to the original graph
        applyLayout(layoutNode, graphAttributes);

        progressMonitor.done();
    }
    
    /**
     * Sets the layout specific options and modules depending on the options
     * defined in the node.
     * 
     * @param layoutNode the parent node
     * @return the layout module
     */
    protected abstract LayoutModule prepareLayouter(final KNode layoutNode);
    
    /**
     * Performs post-processing on the given node. The default implementation does nothing.
     * 
     * @param layoutNode the parent node
     */
    protected void postProcess(final KNode layoutNode) {
    }

    /**
     * Layout the edge labels.
     * 
     * @param layoutNode the parent layout node
     * @param graphAttributes graph attributes for the graph
     * @param thelabelInterface the label interface
     */
    protected void layoutLabels(final KNode layoutNode, final GraphAttributes graphAttributes,
            final ELabelInterfaceDouble thelabelInterface) {
        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(layoutNode);
        // TODO as soon as ogdf provides a better label layouter this should be reworked
        ELabelPosSimple labelLayouter = new ELabelPosSimple();
        labelLayouter.setMidOnEdge(false);
        // get the edge distance
        float edgeDistance = LayoutOptions.getFloat(parentLayout, OPT_LABEL_EDGE_DISTANCE);
        if (Float.isNaN(edgeDistance)) {
            Object edgeDistanceObj = getDefault(OPT_LABEL_EDGE_DISTANCE);
            if (edgeDistanceObj instanceof Float) {
                edgeDistance = (Float) edgeDistanceObj;
            } else {
                edgeDistance = 0;
            }
        }
        labelLayouter.setEdgeDistance(edgeDistance);

        // get the margin distance
        float marginDistance = LayoutOptions.getFloat(parentLayout, OPT_LABEL_MARGIN_DISTANCE);
        if (Float.isNaN(marginDistance)) {
            Object marginDistanceObj = getDefault(OPT_LABEL_MARGIN_DISTANCE);
            if (marginDistanceObj instanceof Float) {
                marginDistance = (Float) marginDistanceObj;
            } else {
                marginDistance = 0;
            }
        }
        labelLayouter.setMarginDistance(marginDistance);
        
        // call the label layouter
        labelLayouter.call(graphAttributes, thelabelInterface);
    }
    
    /**
     * Returns the default value for the given layout option.
     * 
     * @param optionId a layout option identifier
     * @return the corresponding default value, or {@code null} if the option is not known
     */
    public abstract Object getDefault(final String optionId);

    /** map of KIELER nodes to OGDF nodes. */
    private Map<KNode, NodeElement> knode2ogdfNodeMap = new LinkedHashMap<KNode, NodeElement>();
    /** map of KIELER edges to OGDF edges. */
    private Map<KEdge, EdgeElement> kedge2ogdfEdgeMap = new LinkedHashMap<KEdge, EdgeElement>();
    /** holds the OGDF edge labels. */
    private ELabelInterfaceDouble labelInterface;

    /**
     * Transforms a DPoint to a KPoint.
     * 
     * @param point the point
     * @return the point as KPoint
     */
    private KPoint toKPoint(final DPoint point, final float offsetX, final float offsetY) {
        KPoint kpoint = KLayoutDataFactory.eINSTANCE.createKPoint();
        kpoint.setX((float) point.getX() + offsetX);
        kpoint.setY((float) point.getY() + offsetY);
        return kpoint;
    }

    /**
     * Assigns OGDF geometry to a KShape layout.
     * 
     * @param layout the shape layout
     * @param x the x coordinate
     * @param y the y coordinate
     * @param width the width
     * @param height the height
     */
    private void toKShape(final KShapeLayout layout, final double x, final double y,
            final double width, final double height) {
        layout.setXpos((float) (x - width / 2));
        layout.setYpos((float) (y - height / 2));
        layout.setWidth((float) width);
        layout.setHeight((float) height);
    }

    /**
     * Assigns KShape geometry to an OGDF node.
     * 
     * @param graphAttributes the graph attributes
     * @param ogdfNode the node
     * @param shapeLayout the shape layout
     */
    private void toOgdfNode(final GraphAttributes graphAttributes,
            final NodeElement ogdfNode, final KShapeLayout shapeLayout) {
        graphAttributes.setNodeWidth(ogdfNode, shapeLayout.getWidth());
        graphAttributes.setNodeHeight(ogdfNode, shapeLayout.getHeight());
    }

    /**
     * Assigns KShape geometry to an OGDF edge label.
     * 
     * @param edgeLabel the edge label
     * @param type the edge label type
     * @param shapeLayout the shape layout
     */
    private void toOgdfEdgeLabel(final EdgeLabelDouble edgeLabel, final eLabelTyp type,
            final KShapeLayout shapeLayout) {
        edgeLabel.addType(type);
        edgeLabel.setWidth(type, shapeLayout.getWidth());
        edgeLabel.setHeight(type, shapeLayout.getHeight());
    }
    
    /**
     * Determines whether the given graph should be layouted as UML graph.
     * 
     * @param layoutNode a parent graph
     * @return true if UML layout should be performed
     */
    protected boolean isUmlGraph(final KNode layoutNode) {
        for (KNode child : layoutNode.getChildren()) {
            for (KEdge kedge : child.getOutgoingEdges()) {
                if (child.getParent() == kedge.getTarget().getParent()) {
                    EdgeType edgeType = LayoutOptions.getEnum(KimlLayoutUtil.getEdgeLayout(kedge),
                            EdgeType.class);
                    if (edgeType == EdgeType.GENERALIZATION
                            || edgeType == EdgeType.ASSOCIATION
                            || edgeType == EdgeType.DEPENDENCY) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Transforms the given layout graph into an OGDF graph.
     * 
     * @param layoutNode the parent node of the layout graph
     * @return an OGDF graph with attached layout attributes
     */
    private GraphAttributes transformGraph(final KNode layoutNode) {
        // reset the mapping
        knode2ogdfNodeMap.clear();
        kedge2ogdfEdgeMap.clear();
        
        // create the graph
        Graph graph = new Graph();
        GraphAttributes graphAttributes;
        final boolean isUmlLayout = isUmlGraph(layoutNode);
        if (isUmlLayout) {
            graphAttributes = new UMLGraph(graph, GraphAttributes.nodeGraphics
                    | GraphAttributes.edgeGraphics | GraphAttributes.edgeLabel
                    | GraphAttributes.edgeType);
        } else {
            graphAttributes = new GraphAttributes(graph, GraphAttributes.nodeGraphics
                    | GraphAttributes.edgeGraphics | GraphAttributes.edgeLabel);
        }
        // process nodes
        for (KNode knode : layoutNode.getChildren()) {
            NodeElement ogdfNode = graph.newNode();
            KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(knode);
            toOgdfNode(graphAttributes, ogdfNode, shapeLayout);
            knode2ogdfNodeMap.put(knode, ogdfNode);
        }
        
        // process edges
        labelInterface = null;
        for (KNode knode1 : layoutNode.getChildren()) {
            for (KEdge kedge : knode1.getOutgoingEdges()) {
                KNode knode2 = kedge.getTarget();
                if (knode2.getParent() == knode1.getParent()) {
                    NodeElement ogdfNode1 = knode2ogdfNodeMap.get(knode1);
                    NodeElement ogdfNode2 = knode2ogdfNodeMap.get(knode2);
                    EdgeElement ogdfEdge = graph.newEdge(ogdfNode1, ogdfNode2);
                    kedge2ogdfEdgeMap.put(kedge, ogdfEdge);
                    if (isUmlLayout) {
                        // set the hierarchy type of the edge
                        EdgeType edgeType = LayoutOptions.getEnum(KimlLayoutUtil.getEdgeLayout(kedge),
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
                            graphAttributes.setEdgeType(ogdfEdge,
                                    Graph.EdgeType.dependency);
                            break;
                        }
                    }

                    if (!kedge.getLabels().isEmpty()) {
                        // create an ogdf label and attach it to the edge
                        EdgeLabelDouble edgeLabel = new EdgeLabelDouble();
                        edgeLabel.setEdge(ogdfEdge);
                        boolean makeMult1 = false, makeMult2 = false;
                        for (KLabel label : kedge.getLabels()) {
                            KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(label);
                            EdgeLabelPlacement placement = LayoutOptions.getEnum(labelLayout,
                                    EdgeLabelPlacement.class);
                            eLabelTyp labelTyp = eLabelTyp.elName;
                            switch (placement) {
                            case HEAD:
                                if (makeMult2) {
                                    labelTyp = eLabelTyp.elMult2;
                                } else {
                                    labelTyp = eLabelTyp.elEnd2;
                                }
                                makeMult2 = !makeMult2;
                                break;
                            case TAIL:
                                if (makeMult1) {
                                    labelTyp = eLabelTyp.elMult1;
                                } else {
                                    labelTyp = eLabelTyp.elEnd1;
                                }
                                makeMult1 = !makeMult1;
                                break;
                            }
                            toOgdfEdgeLabel(edgeLabel, labelTyp, labelLayout);
                        }
                        // add the edge label to the ogdf label interface
                        if (labelInterface == null) {
                            labelInterface = new ELabelInterfaceDouble(graphAttributes);
                        }
                        labelInterface.setLabel(ogdfEdge, edgeLabel);
                    }
                }
            }
        }

        return graphAttributes;
    }

    /**
     * Applies the layout result to the original graph.
     * 
     * @param parentNode the parent node of the layout graph
     * @param graphAttributes ogdf graph with attached layout attributes
     */
    protected void applyLayout(final KNode parentNode,
            final GraphAttributes graphAttributes) {
        // include intersections with the nodes bounding boxes in the bends
        graphAttributes.addNodeCenter2Bends(1);
        // get the parent node layout
        KShapeLayout parentNodeLayout = KimlLayoutUtil.getShapeLayout(parentNode);
        DRect boundingBox = graphAttributes.boundingBox();
        // get the border spacing
        float borderSpacing = LayoutOptions.getFloat(parentNodeLayout,
                LayoutOptions.BORDER_SPACING);
        if (Float.isNaN(borderSpacing)) {
            Object borderSpacingObj = getDefault(LayoutOptions.BORDER_SPACING);
            if (borderSpacingObj instanceof Float) {
                borderSpacing = (Float) borderSpacingObj;
            } else {
                borderSpacing = 0.0f;
            }
        }
        // calculate offsets
        float offsetX = (float) -boundingBox.p1().getX() + borderSpacing;
        float offsetY = (float) -boundingBox.p1().getY() + borderSpacing;

        // apply node layout
        for (Map.Entry<KNode, NodeElement> entry : knode2ogdfNodeMap.entrySet()) {
            KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(entry.getKey());
            NodeElement ogdfNode = entry.getValue();
            toKShape(nodeLayout, graphAttributes.getNodeX(ogdfNode) + offsetX,
                    graphAttributes.getNodeY(ogdfNode) + offsetY,
                    graphAttributes.getNodeWidth(ogdfNode),
                    graphAttributes.getNodeHeight(ogdfNode));
        }

        // apply edge layout
        for (KEdge kedge : kedge2ogdfEdgeMap.keySet()) {
            EdgeElement ogdfEdge = kedge2ogdfEdgeMap.get(kedge);
            KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(kedge);
            DPolyline bends = graphAttributes.bends(ogdfEdge);
            // are source and target point present?
            if (bends.size() >= 2) {
                DPointListConstIterator bendsIter = bends.iterator();
                EList<KPoint> kbends = edgeLayout.getBendPoints();
                kbends.clear();
                // set the source point
                DPoint first = bendsIter.next();
                edgeLayout.setSourcePoint(toKPoint(first, offsetX,
                        offsetY));
                // set the bend points
                while (bendsIter.hasNext()) {
                    DPoint point = bendsIter.next();
                    if (!bendsIter.hasNext()) {
                        // set the target point
                        edgeLayout.setTargetPoint(toKPoint(point, offsetX, offsetY));
                    } else {
                        kbends.add(toKPoint(point, offsetX, offsetY));
                    }
                }
            }
            
            if (labelInterface != null) {
                // set the edge labels
                EdgeLabelDouble edgeLabel = labelInterface.getLabel(ogdfEdge);
                boolean makeMult1 = false, makeMult2 = false;
                for (KLabel label : kedge.getLabels()) {
                    KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(label);
                    EdgeLabelPlacement placement = LayoutOptions.getEnum(labelLayout,
                            EdgeLabelPlacement.class);
                    eLabelTyp labelTyp = eLabelTyp.elName;
                    switch (placement) {
                    case HEAD:
                        if (makeMult2) {
                            labelTyp = eLabelTyp.elMult2;
                        } else {
                            labelTyp = eLabelTyp.elEnd2;
                        }
                        makeMult2 = !makeMult2;
                        break;
                    case TAIL:
                        if (makeMult1) {
                            labelTyp = eLabelTyp.elMult1;
                        } else {
                            labelTyp = eLabelTyp.elEnd1;
                        }
                        makeMult1 = !makeMult1;
                        break;
                    }
                    toKShape(labelLayout, edgeLabel.getX(labelTyp) + offsetX,
                            edgeLabel.getY(labelTyp) + offsetY,
                            edgeLabel.getWidth(labelTyp), edgeLabel.getHeight(labelTyp));
                }
            }
        }
        
        // get the insets
        KInsets insets = LayoutOptions.getObject(parentNodeLayout, KInsets.class);
        // set the width/height of the graph
        parentNodeLayout.setWidth((float) boundingBox.width() + 2 * borderSpacing
                + insets.getLeft() + insets.getRight());
        parentNodeLayout.setHeight((float) boundingBox.height() + 2 * borderSpacing
                + insets.getTop() + insets.getBottom());
    }
    
}
