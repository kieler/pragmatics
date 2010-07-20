/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.kiml.util.KimlLayoutUtil;
import de.cau.cs.kieler.klay.layered.graph.Coord;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Manages the transformation of KGraphs to LayeredGraphs.
 *
 * @author msp
 */
public class KGraphImporter implements IGraphImporter {

    /** the created layered graph. */
    private LayeredGraph layeredGraph;
    /** the imported nodes. */
    private List<LNode> importedNodes;
    
    /**
     * Imports a KGraph to a layered graph.
     * 
     * @param knode the top level node of the KGraph
     */
    public KGraphImporter(final KNode knode) {
        importedNodes = transformGraph(knode);
        layeredGraph = new LayeredGraph();
        layeredGraph.setProperty(Properties.ORIGIN, knode);
    }

    /**
     * {@inheritDoc}
     */
    public List<LNode> getImportedNodes() {
        return importedNodes;
    }

    /**
     * {@inheritDoc}
     */
    public LayeredGraph getGraph() {
        return layeredGraph;
    }
    
    /**
     * Transform the given KGraph to a layered graph.
     * 
     * @param layoutNode
     *            parent node of the KGraph
     * @return a list of nodes for a layered graph
     */
    private List<LNode> transformGraph(final KNode layoutNode) {
        List<LNode> layeredNodes = new LinkedList<LNode>();

        // transform nodes and ports
        Map<KGraphElement, LGraphElement> elemMap = new HashMap<KGraphElement, LGraphElement>();
        for (KNode child : layoutNode.getChildren()) {
            KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(child);
            PortConstraints portConstraints = LayoutOptions.getEnum(nodeLayout, PortConstraints.class);
            LNode newNode = new LNode(child, child.getLabel().getText());
            newNode.getSize().x = nodeLayout.getWidth();
            newNode.getSize().y = nodeLayout.getHeight();
            layeredNodes.add(newNode);
            elemMap.put(child, newNode);
            for (KPort kport : child.getPorts()) {
                KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(kport);
                PortType type = PortType.UNDEFINED;
                int outBalance = 0;
                for (KEdge edge : kport.getEdges()) {
                    if (edge.getSourcePort() == kport) {
                        outBalance++;
                    }
                    if (edge.getTargetPort() == kport) {
                        outBalance--;
                    }
                }
                if (outBalance > 0) {
                    type = PortType.OUTPUT;
                } else if (outBalance < 0) {
                    type = PortType.INPUT;
                }
                LPort newPort = new LPort(type, kport, kport.getLabel().getText());
                newPort.getPos().x = portLayout.getXpos();
                newPort.getPos().y = portLayout.getYpos();
                newPort.setNode(newNode);
                elemMap.put(kport, newPort);
                if (portConstraints != PortConstraints.UNDEFINED) {
                    newPort.setSide(KimlLayoutUtil.calcPortSide(kport));
                }
            }
            if (portConstraints != PortConstraints.UNDEFINED) {
                newNode.setProperty(Properties.PORT_CONS, portConstraints);
            }
        }

        // transform edges
        for (KNode child : layoutNode.getChildren()) {
            for (KEdge kedge : child.getOutgoingEdges()) {
                KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(kedge);
                // exclude edges that pass hierarchy bounds and self-loops
                if (kedge.getTarget().getParent() == child.getParent()
                        && kedge.getSource() != kedge.getTarget()) {
                    LNode sourceNode = (LNode) elemMap.get(child);
                    LPort sourcePort = (LPort) elemMap.get(kedge.getSourcePort());
                    LNode targetNode = (LNode) elemMap.get(kedge.getTarget());
                    LPort targetPort = (LPort) elemMap.get(kedge.getTargetPort());
                    if (sourcePort == null) {
                        sourcePort = new LPort(PortType.OUTPUT);
                        sourcePort.setNode(sourceNode);
                    } else if (sourcePort.getType() != PortType.OUTPUT) {
                        // ignore ports with incoming as well as outgoing edges
                        LayoutOptions.setBoolean(edgeLayout, LayoutOptions.NO_LAYOUT, true);
                        continue;
                    }
                    if (targetPort == null) {
                        targetPort = new LPort(PortType.INPUT);
                        targetPort.setNode(targetNode);
                    } else if (targetPort.getType() != PortType.INPUT) {
                        // ignore ports with incoming as well as outgoing edges
                        LayoutOptions.setBoolean(edgeLayout, LayoutOptions.NO_LAYOUT, true);
                        continue;
                    }
                    LEdge newEdge = new LEdge(kedge);
                    newEdge.setSource(sourcePort);
                    newEdge.setTarget(targetPort);
                    for (KLabel label : kedge.getLabels()) {
                        KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(label);
                        Coord labelSize = new Coord(labelLayout.getWidth(), labelLayout.getHeight());
                        LLabel newLabel = new LLabel(label, label.getText());
                        newLabel.getSize().add(labelSize);
                        newEdge.getLabels().add(newLabel);
                    }
                    // set properties of the new edge
                    int priority = LayoutOptions.getInt(edgeLayout, LayoutOptions.PRIORITY);
                    if (priority > 0) {
                        newEdge.setProperty(Properties.PRIORITY, Integer.valueOf(priority));
                    }
                } else {
                    LayoutOptions.setBoolean(edgeLayout, LayoutOptions.NO_LAYOUT, true);
                }
            }
        }

        return layeredNodes;
    }


    /**
     * {@inheritDoc}
     */
    public void applyLayout() {
        KNode parentNode = (KNode) layeredGraph.getProperty(Properties.ORIGIN);
        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(parentNode);
        float borderSpacing = LayoutOptions.getFloat(parentLayout, LayoutOptions.BORDER_SPACING);
        if (Float.isNaN(borderSpacing) || borderSpacing < 0) {
            borderSpacing = Properties.DEF_SPACING;
        }
        Coord offset = new Coord(borderSpacing + layeredGraph.getOffset().x, borderSpacing
                + layeredGraph.getOffset().y);

        // process the nodes
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode lnode : layer.getNodes()) {
                if (lnode.getOrigin() instanceof KNode) {
                    KNode knode = (KNode) lnode.getOrigin();
                    KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(knode);
                    nodeLayout.setXpos(lnode.getPos().x + offset.x);
                    nodeLayout.setYpos(lnode.getPos().y + offset.y);
                }
                for (LPort port : lnode.getPorts(PortType.OUTPUT)) {
                    for (LEdge edge : port.getEdges()) {
                        edge.id = -1;
                    }
                }
            }
        }

        // collect all parts of each long edge
        Map<KEdge, List<LEdge>> edgeMap = new HashMap<KEdge, List<LEdge>>();
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode lnode : layer.getNodes()) {
                for (LPort port : lnode.getPorts(PortType.OUTPUT)) {
                    for (LEdge ledge : port.getEdges()) {
                        if (ledge.id < 0 && ledge.getOrigin() instanceof KEdge) {
                            KEdge kedge = (KEdge) ledge.getOrigin();
                            List<LEdge> edgeList = edgeMap.get(kedge);
                            if (edgeList == null) {
                                edgeList = new LinkedList<LEdge>();
                                edgeMap.put(kedge, edgeList);
                            }
                            edgeList.add(ledge);
                            // apply layout to labels
                            for (LLabel label : ledge.getLabels()) {
                                KLabel klabel = (KLabel) label.getOrigin();
                                KShapeLayout klabelLayout = KimlLayoutUtil.getShapeLayout(klabel);
                                Coord labelPos = new Coord(ledge.getSource().getPos().x, ledge
                                        .getSource().getPos().y);
                                labelPos.add(ledge.getSource().getNode().getPos());
                                labelPos.add(label.getPos());
                                klabelLayout.setXpos(labelPos.x + offset.x);
                                klabelLayout.setYpos(labelPos.y + offset.y);
                            }
                        }
                    }
                }
            }
        }
        
        // process the edges
        LayeredEdgeRouting routing = LayoutOptions.getEnum(parentLayout, LayeredEdgeRouting.class);
        boolean splinesActive = routing == LayeredEdgeRouting.SIMPLE_SPLINES
                || routing == LayeredEdgeRouting.COMPLEX_SPLINES;
        for (Map.Entry<KEdge, List<LEdge>> edgeEntry : edgeMap.entrySet()) {
            KEdge kedge = edgeEntry.getKey();
            KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(kedge);
            List<LEdge> edgeList = edgeEntry.getValue();
            // set source and target points, considering direction of the edge
            LEdge firstEdge = edgeList.get(0);
            LPort sourcePort = firstEdge.getSource();
            sourcePort.getPos().add(sourcePort.getNode().getPos());
            LEdge lastEdge = edgeList.get(edgeList.size() - 1);
            LPort targetPort = lastEdge.getTarget();
            targetPort.getPos().add(targetPort.getNode().getPos());
            if (firstEdge.isReversed()) {
                applyLayout(edgeLayout.getSourcePoint(), targetPort.getPos(), offset);
                applyLayout(edgeLayout.getTargetPoint(), sourcePort.getPos(), offset);
            } else {
                applyLayout(edgeLayout.getSourcePoint(), sourcePort.getPos(), offset);
                applyLayout(edgeLayout.getTargetPoint(), targetPort.getPos(), offset);
            }
            // set bend points, considering direction of the edge
            List<KPoint> bendPoints = edgeLayout.getBendPoints();
            bendPoints.clear();
            for (LEdge ledge : edgeList) {
                for (Coord lpoint : ledge.getBendPoints()) {
                    KPoint newPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                    applyLayout(newPoint, lpoint, offset);
                    if (ledge.isReversed()) {
                        bendPoints.add(0, newPoint);
                    } else {
                        bendPoints.add(newPoint);
                    }
                }
            }
            // set spline option
            if (splinesActive) {
                LayoutOptions.setEnum(edgeLayout, EdgeRouting.SPLINES);
            }
        }

        // set up the parent node
        parentLayout.setWidth(layeredGraph.getSize().x + 2 * borderSpacing);
        parentLayout.setHeight(layeredGraph.getSize().y + 2 * borderSpacing);
        LayoutOptions.setBoolean(parentLayout, LayoutOptions.FIXED_SIZE, true);
    }

    /**
     * Apply the given point coordinates to the {@code KPoint}.
     * 
     * @param kpoint
     *            point where coordinates are written
     * @param lpoint
     *            point from which coordinates are read
     */
    private void applyLayout(final KPoint kpoint, final Coord lpoint, final Coord offset) {
        kpoint.setX(lpoint.x + offset.x);
        kpoint.setY(lpoint.y + offset.y);
    }
    
}
