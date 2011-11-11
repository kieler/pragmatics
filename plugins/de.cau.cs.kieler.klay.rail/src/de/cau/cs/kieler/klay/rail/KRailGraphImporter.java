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
package de.cau.cs.kieler.klay.rail;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.IGraphImporter;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Manages the transformation of KGraphs for railway to LayeredGraphs. Custom class adapted from
 * KGraphImporter, since some features aren't supported there. If this changes, the original may be
 * used again.
 * 
 * @author msp, jjc
 */
public class KRailGraphImporter implements IGraphImporter<KNode> {

    /**
     * {@inheritDoc}
     */
    public LayeredGraph importGraph(final KNode graph) {
        LayeredGraph layeredGraph = new LayeredGraph();
        
        transformGraph(graph, layeredGraph);
        layeredGraph.setProperty(Properties.ORIGIN, graph);
        
        KShapeLayout lay = graph.getData(KShapeLayout.class);
        layeredGraph.setProperty(Properties.BEND_ANGLE, lay.getProperty(Properties.BEND_ANGLE));
        layeredGraph.setProperty(Properties.LAYER_DISTANCE,
                lay.getProperty(Properties.LAYER_DISTANCE));
        
        return layeredGraph;
    }

    /**
     * Transform the given KGraph to a layered graph.
     * 
     * @param graph
     *            the graph to transform.
     * @param layeredGraph
     *            the layered graph to transform into.
     */
    private void transformGraph(final KNode graph, final LayeredGraph layeredGraph) {
        List<LNode> layeredNodes = layeredGraph.getLayerlessNodes();

        // transform nodes and ports
        Map<KGraphElement, LGraphElement> elemMap = new HashMap<KGraphElement, LGraphElement>();
        for (KNode child : graph.getChildren()) {
            KShapeLayout nodeLayout = child.getData(KShapeLayout.class);
            PortConstraints portConstraints = nodeLayout
                    .getProperty(LayoutOptions.PORT_CONSTRAINTS);
            LNode newNode = new LNode();
            newNode.setProperty(Properties.ORIGIN, child);
            newNode.getSize().x = nodeLayout.getWidth();
            newNode.getSize().y = nodeLayout.getHeight();
            newNode.setProperty(Properties.NODE_TYPE, nodeLayout.getProperty(Properties.NODE_TYPE));
            newNode.setProperty(Properties.ENTRY_POINT,
                    nodeLayout.getProperty(Properties.ENTRY_POINT));
            newNode.setProperty(Properties.SWITCH_ROTATION,
                    nodeLayout.getProperty(Properties.SWITCH_ROTATION));
            layeredNodes.add(newNode);
            elemMap.put(child, newNode);
            KPort[] sortedPorts = KimlUtil.getSortedPorts(child);
            for (KPort kport : sortedPorts) {
                KShapeLayout portLayout = kport.getData(KShapeLayout.class);
                
                LPort newPort = new LPort();
                newPort.setProperty(Properties.ORIGIN, kport);
                newPort.setProperty(Properties.PORT_TYPE,
                        portLayout.getProperty(Properties.PORT_TYPE));
                newPort.getPosition().x = portLayout.getXpos();
                newPort.getPosition().y = portLayout.getYpos();
                newPort.setNode(newNode);
                elemMap.put(kport, newPort);
                if (portConstraints != PortConstraints.UNDEFINED) {
                    newPort.setSide(KimlUtil.calcPortSide(kport,
                            layeredGraph.getProperty(LayoutOptions.DIRECTION)));
                }
            }
            if (portConstraints != PortConstraints.UNDEFINED) {
                newNode.setProperty(Properties.PORT_CONS, portConstraints);
            }
        }

        // transform edges
        for (KNode child : graph.getChildren()) {
            for (KEdge kedge : child.getOutgoingEdges()) {
                KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
                // exclude edges that pass hierarchy bounds and self-loops
                if (kedge.getTarget().getParent() == child.getParent()
                        && kedge.getSource() != kedge.getTarget()) {
                    // TODO handle fixed port constraints for edges without ports
                    LNode sourceNode = (LNode) elemMap.get(child);
                    LPort sourcePort = (LPort) elemMap.get(kedge.getSourcePort());
                    LNode targetNode = (LNode) elemMap.get(kedge.getTarget());
                    LPort targetPort = (LPort) elemMap.get(kedge.getTargetPort());
                    
                    if (sourcePort == null) {
                        sourcePort = new LPort();
                        sourcePort.setNode(sourceNode);
                    }
                    // TODO: Check if the following case can safely be ignored.
                    /*else if (sourcePort.getType() != PortType.OUTPUT) {
                        // ignore ports with incoming as well as outgoing edges
                        edgeLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
                        continue;
                    }*/
                    
                    if (targetPort == null) {
                        targetPort = new LPort();
                        targetPort.setNode(targetNode);
                    }
                    // TODO: Check if the following case can safely be ignored.
                    /*else if (targetPort.getType() != PortType.INPUT) {
                        // ignore ports with incoming as well as outgoing edges
                        edgeLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
                        continue;
                    }*/
                    
                    LEdge newEdge = new LEdge();
                    newEdge.setProperty(Properties.ORIGIN, kedge);
                    newEdge.setSource(sourcePort);
                    newEdge.setTarget(targetPort);
                    for (KLabel klabel : kedge.getLabels()) {
                        KShapeLayout labelLayout = klabel.getData(KShapeLayout.class);
                        LLabel newLabel = new LLabel(klabel.getText());
                        newLabel.getSize().x = labelLayout.getWidth();
                        newLabel.getSize().y = labelLayout.getHeight();
                        newLabel.setProperty(Properties.ORIGIN, klabel);
                        newEdge.getLabels().add(newLabel);
                    }
                    // set properties of the new edge
                    newEdge.setProperty(Properties.PRIORITY,
                            edgeLayout.getProperty(LayoutOptions.PRIORITY));
                } else {
                    edgeLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void applyLayout(final LayeredGraph layeredGraph) {
        KNode parentNode = (KNode) layeredGraph.getProperty(Properties.ORIGIN);
        KShapeLayout parentLayout = parentNode.getData(KShapeLayout.class);
        float borderSpacing = parentLayout.getProperty(LayoutOptions.BORDER_SPACING);
        if (borderSpacing < 0) {
            borderSpacing = Properties.DEF_SPACING;
        }
        KVector offset = new KVector(borderSpacing + layeredGraph.getOffset().x, borderSpacing
                + layeredGraph.getOffset().y);

        // process the nodes
        for (Layer layer : layeredGraph) {
            for (LNode lnode : layer) {
                Object origin = lnode.getProperty(Properties.ORIGIN);
                if (origin instanceof KNode) {
                    KNode knode = (KNode) origin;
                    KShapeLayout nodeLayout = knode.getData(KShapeLayout.class);
                    nodeLayout.setXpos((float) (lnode.getPosition().x + offset.x));
                    nodeLayout.setYpos((float) (lnode.getPosition().y + offset.y));
                }
                for (LPort port : lnode.getPorts()) {
                    for (LEdge edge : port.getOutgoingEdges()) {
                        edge.id = -1;
                    }
                }

                //yields problems in GMF diagrams
                for (LPort port : lnode.getPorts()) {
                    Object original = port.getProperty(Properties.ORIGIN);
                    if (original instanceof KPort) {
                        KPort kport = (KPort) original;
                        KShapeLayout portLayout = kport.getData(KShapeLayout.class);
                        portLayout.setXpos((float) (port.getPosition().x));
                        portLayout.setYpos((float) (port.getPosition().y));
                    }
                }

            }
        }

        // collect all parts of each long edge
        Map<KEdge, List<LEdge>> edgeMap = new HashMap<KEdge, List<LEdge>>();
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode lnode : layer.getNodes()) {
                for (LPort port : lnode.getPorts()) {
                    for (LEdge ledge : port.getOutgoingEdges()) {
                        Object origin = ledge.getProperty(Properties.ORIGIN);
                        if (ledge.id < 0 && origin instanceof KEdge) {
                            KEdge kedge = (KEdge) origin;
                            List<LEdge> edgeList = edgeMap.get(kedge);
                            if (edgeList == null) {
                                edgeList = new LinkedList<LEdge>();
                                edgeMap.put(kedge, edgeList);
                            }
                            edgeList.add(ledge);
                            // apply layout to labels
                            for (LLabel label : ledge.getLabels()) {
                                KLabel klabel = (KLabel) label.getProperty(Properties.ORIGIN);
                                KShapeLayout klabelLayout = klabel.getData(KShapeLayout.class);
                                KVector labelPos = new KVector(ledge.getSource().getPosition().x, ledge
                                        .getSource().getPosition().y);
                                labelPos.add(ledge.getSource().getNode().getPosition());
                                labelPos.add(label.getPosition());
                                klabelLayout.setXpos((float) (labelPos.x + offset.x));
                                klabelLayout.setYpos((float) (labelPos.y + offset.y));
                            }
                        }
                    }
                }
            }
        }

        // process the edges
        for (Map.Entry<KEdge, List<LEdge>> edgeEntry : edgeMap.entrySet()) {
            KEdge kedge = edgeEntry.getKey();
            KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
            List<LEdge> edgeList = edgeEntry.getValue();
            // set source and target points, considering direction of the edge
            LEdge firstEdge = edgeList.get(0);
            LPort sourcePort = firstEdge.getSource();
            sourcePort.getPosition().add(sourcePort.getNode().getPosition());
            LEdge lastEdge = edgeList.get(edgeList.size() - 1);
            LPort targetPort = lastEdge.getTarget();
            targetPort.getPosition().add(targetPort.getNode().getPosition());
            KShapeLayout standardPort = kedge.getSourcePort().getData(KShapeLayout.class);
            KVector sourcePortOffset = new KVector(standardPort.getWidth(),
                    standardPort.getHeight() / 2);
            KVector targetPortOffset = new KVector(0, standardPort.getHeight() / 2);
            if (firstEdge.getProperty(Properties.REVERSED)) {
                applyLayout(edgeLayout.getSourcePoint(), targetPort.getPosition().add(targetPortOffset),
                        offset);
                applyLayout(edgeLayout.getTargetPoint(), sourcePort.getPosition().add(sourcePortOffset),
                        offset);
            } else {
                applyLayout(edgeLayout.getSourcePoint(), sourcePort.getPosition().add(sourcePortOffset),
                        offset);
                applyLayout(edgeLayout.getTargetPoint(), targetPort.getPosition().add(targetPortOffset),
                        offset);
            }
            // set bend points, considering direction of the edge
            List<KPoint> bendPoints = edgeLayout.getBendPoints();
            bendPoints.clear();
            for (LEdge ledge : edgeList) {
                for (KVector lpoint : ledge.getBendPoints()) {
                    KPoint newPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                    applyLayout(newPoint, lpoint.add(sourcePortOffset), offset);
                    if (ledge.getProperty(Properties.REVERSED)) {
                        bendPoints.add(0, newPoint);
                    } else {
                        bendPoints.add(newPoint);
                    }
                }
            }
        }

        // set up the parent node
        KInsets insets = parentLayout.getInsets();
        parentLayout.setWidth((float) layeredGraph.getSize().x + 2 * borderSpacing
                + insets.getLeft() + insets.getRight());
        parentLayout.setHeight((float) layeredGraph.getSize().y + 2 * borderSpacing
                + insets.getTop() + insets.getBottom());
        parentLayout.setProperty(LayoutOptions.FIXED_SIZE, true);
    }

    /**
     * Apply the given point coordinates to the {@code KPoint}.
     * 
     * @param kpoint
     *            point where coordinates are written
     * @param lpoint
     *            point from which coordinates are read
     */
    private void applyLayout(final KPoint kpoint, final KVector lpoint, final KVector offset) {
        kpoint.setX((float) (lpoint.x + offset.x));
        kpoint.setY((float) (lpoint.y + offset.y));
    }

}
