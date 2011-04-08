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
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.p5edges.EdgeRoutingStrategy;

/**
 * Manages the transformation of KGraphs to LayeredGraphs.
 *
 * @author msp
 */
public class KGraphImporter implements IGraphImporter {

    /** the created layered graph. */
    private LayeredGraph layeredGraph;
    
    
    /**
     * Imports a KGraph to a layered graph.
     * 
     * @param knode the top level node of the KGraph
     */
    public KGraphImporter(final KNode knode) {
        layeredGraph = new LayeredGraph();
        layeredGraph.setProperty(Properties.ORIGIN, knode);
        transformGraph(knode);
    }
    

    /**
     * {@inheritDoc}
     */
    public LayeredGraph getGraph() {
        return layeredGraph;
    }

    
    ///////////////////////////////////////////////////////////////////////////////
    // Transformation KGraph -> LGraph
    
    /**
     * Transform the given KGraph to a layered graph.
     * 
     * @param layoutNode parent node of the KGraph
     */
    private void transformGraph(final KNode layoutNode) {
        // copy the properties of the KGraph to the layered graph
        layeredGraph.copyProperties(layoutNode.getData(KShapeLayout.class));
        layeredGraph.checkProperties(Properties.OBJ_SPACING, Properties.THOROUGHNESS,
                LayoutOptions.BORDER_SPACING);

        // keep a list of created nodes in the layered graph, as well as a map between KGraph
        // nodes / ports and LGraph nodes / ports
        List<LNode> layeredNodes = layeredGraph.getLayerlessNodes();
        Map<KGraphElement, LGraphElement> elemMap = new HashMap<KGraphElement, LGraphElement>();
        
        // transform everything
        transformNodesAndPorts(layoutNode, layeredNodes, elemMap);
        transformEdges(layoutNode, elemMap);
    }


    /**
     * Transforms the nodes and ports defined by the given layout node.
     * 
     * @param layoutNode the layout node whose edges to transform.
     * @param layeredNodes list of nodes created.
     * @param elemMap the element map that maps the original {@code KGraph} elements to the
     *                transformed {@code LGraph} elements.
     */
    private void transformNodesAndPorts(final KNode layoutNode, final List<LNode> layeredNodes,
            final Map<KGraphElement, LGraphElement> elemMap) {
        
        for (KNode child : layoutNode.getChildren()) {
            // add a new node to the layered graph, copying its size
            KShapeLayout nodeLayout = child.getData(KShapeLayout.class);
            
            LNode newNode = new LNode(child.getLabel().getText());
            newNode.setProperty(Properties.ORIGIN, child);
            newNode.getPosition().x = nodeLayout.getXpos();
            newNode.getPosition().y = nodeLayout.getYpos();
            newNode.getSize().x = nodeLayout.getWidth();
            newNode.getSize().y = nodeLayout.getHeight();
            layeredNodes.add(newNode);
            
            elemMap.put(child, newNode);
            
            // add the node's ports
            PortConstraints portConstraints = nodeLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS);
            if (portConstraints == PortConstraints.UNDEFINED) {
                portConstraints = PortConstraints.FREE;
            }
            KPort[] sortedPorts = KimlUtil.getSortedPorts(child);
            for (KPort kport : sortedPorts) {
                KShapeLayout portLayout = kport.getData(KShapeLayout.class);
                
                // determine the port type
                int inEdges = 0, outEdges = 0;
                for (KEdge edge : kport.getEdges()) {
                    if (edge.getSourcePort() == kport) {
                        outEdges++;
                    }
                    if (edge.getTargetPort() == kport) {
                        inEdges++;
                    }
                }
                
                PortType type = PortType.UNDEFINED;
                if (outEdges > 0 && inEdges == 0) {
                    type = PortType.OUTPUT;
                } else if (inEdges > 0 && outEdges == 0) {
                    type = PortType.INPUT;
                }
                
                // create layered port, copying its position
                LPort newPort = new LPort(type, kport.getLabel().getText());
                newPort.setProperty(Properties.ORIGIN, kport);
                newPort.getSize().x = portLayout.getWidth();
                newPort.getSize().y = portLayout.getHeight();
                newPort.getPosition().x = portLayout.getXpos() + portLayout.getWidth() / 2;
                newPort.getPosition().y = portLayout.getYpos() + portLayout.getHeight() / 2;
                newPort.setNode(newNode);
                
                elemMap.put(kport, newPort);
                
                // create layered label, if any
                KLabel klabel = kport.getLabel();
                if (klabel != null) {
                    KShapeLayout labelLayout = klabel.getData(KShapeLayout.class);
                    
                    LLabel llabel = new LLabel(klabel.getText());
                    llabel.setProperty(Properties.ORIGIN, klabel);
                    llabel.getSize().x = labelLayout.getWidth();
                    llabel.getSize().y = labelLayout.getHeight();
                    llabel.getPosition().x = labelLayout.getXpos() - portLayout.getWidth() / 2;
                    llabel.getPosition().y = labelLayout.getYpos() - portLayout.getHeight() / 2;
                }
                
                // calculate port side
                newPort.setSide(KimlUtil.calcPortSide(kport));
            }
            
            // add the node's label, if any
            KLabel klabel = child.getLabel();
            if (klabel != null) {
                KShapeLayout labelLayout = klabel.getData(KShapeLayout.class);
                
                LLabel llabel = new LLabel(klabel.getText());
                llabel.setProperty(Properties.ORIGIN, child);
                llabel.getSize().x = labelLayout.getWidth();
                llabel.getSize().y = labelLayout.getHeight();
                llabel.getPosition().x = labelLayout.getXpos();
                llabel.getPosition().y = labelLayout.getYpos();
            }
            
            // set properties of the new node
            newNode.copyProperties(nodeLayout);
            
            // if we have a hypernode without ports, create a default input and output port
            if (newNode.getProperty(LayoutOptions.HYPERNODE) && newNode.getPorts().isEmpty()) {
                LPort inputPort = new LPort(PortType.INPUT);
                inputPort.setSide(PortSide.WEST);
                inputPort.setNode(newNode);
                
                LPort outputPort = new LPort(PortType.OUTPUT);
                outputPort.setSide(PortSide.EAST);
                outputPort.setNode(newNode);
            }
        }
    }


    /**
     * Transforms the edges defined by the given layout node.
     * 
     * @param layoutNode the layout node whose edges to transform.
     * @param elemMap the element map that maps the original {@code KGraph} elements to the
     *                transformed {@code LGraph} elements.
     */
    private void transformEdges(final KNode layoutNode,
            final Map<KGraphElement, LGraphElement> elemMap) {
        
        for (KNode child : layoutNode.getChildren()) {
            for (KEdge kedge : child.getOutgoingEdges()) {
                KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
                
                // exclude edges that pass hierarchy bounds and self-loops
                if (kedge.getTarget().getParent() == child.getParent()) {
                    // retrieve source and target
                    LNode sourceNode = (LNode) elemMap.get(child);
                    LPort sourcePort = (LPort) elemMap.get(kedge.getSourcePort());
                    LNode targetNode = (LNode) elemMap.get(kedge.getTarget());
                    LPort targetPort = (LPort) elemMap.get(kedge.getTargetPort());
                    
                    // create source port
                    if (sourcePort == null) {
                        if (sourceNode.getProperty(LayoutOptions.HYPERNODE)) {
                            // Hypernodes have an input port
                            sourcePort = sourceNode.getPorts(PortType.OUTPUT).iterator().next();
                        } else {
                            sourcePort = new LPort(PortType.OUTPUT);
                            sourcePort.setNode(sourceNode);
                            KPoint sourcePoint = edgeLayout.getSourcePoint();
                            sourcePort.getPosition().x = sourcePoint.getX() - sourceNode.getPosition().x;
                            sourcePort.getPosition().y = sourcePoint.getY() - sourceNode.getPosition().y;
                            sourcePort.setSide(calcPortSide(sourceNode, sourcePort));
                        }
                    }
                    
                    // create target port
                    if (targetPort == null) {
                        if (targetNode.getProperty(LayoutOptions.HYPERNODE)) {
                            // Hypernodes have an input port
                            targetPort = targetNode.getPorts(PortType.INPUT).iterator().next();
                        } else {
                            targetPort = new LPort(PortType.INPUT);
                            targetPort.setNode(targetNode);
                            KPoint targetPoint = edgeLayout.getTargetPoint();
                            targetPort.getPosition().x = targetPoint.getX() - targetNode.getPosition().x;
                            targetPort.getPosition().y = targetPoint.getY() - targetNode.getPosition().y;
                            targetPort.setSide(calcPortSide(targetNode, targetPort));
                        }
                    }
                    
                    // create a layered edge
                    LEdge newEdge = new LEdge();
                    newEdge.setProperty(Properties.ORIGIN, kedge);
                    newEdge.setSource(sourcePort);
                    newEdge.setTarget(targetPort);
                    
                    // transform the edge's labels
                    for (KLabel klabel : kedge.getLabels()) {
                        KShapeLayout labelLayout = klabel.getData(KShapeLayout.class);
                        LLabel newLabel = new LLabel(klabel.getText());
                        newLabel.getPosition().x = labelLayout.getXpos();
                        newLabel.getPosition().y = labelLayout.getYpos();
                        newLabel.getSize().x = labelLayout.getWidth();
                        newLabel.getSize().y = labelLayout.getHeight();
                        newLabel.setProperty(Properties.ORIGIN, klabel);
                        newEdge.getLabels().add(newLabel);
                    }
                    
                    // set properties of the new edge
                    newEdge.copyProperties(edgeLayout);
                } else {
                    // the edge is excluded from layout
                    edgeLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
                }
            }
        }
    }
    
    /**
     * Calculate the port side from the relative position.
     * 
     * @param node a node
     * @param port a port of that node
     * @return the side of the node on which the port is situated
     */
    private static PortSide calcPortSide(final LNode node, final LPort port) {
        double widthPercent = port.getPosition().x / node.getSize().x;
        double heightPercent = port.getPosition().y / node.getSize().y;
        if (widthPercent + heightPercent <= 1
                && widthPercent - heightPercent <= 0) {
            // port is on the left
            return PortSide.WEST;
        } else if (widthPercent + heightPercent >= 1
                && widthPercent - heightPercent >= 0) {
            // port is on the right
            return PortSide.EAST;
        } else if (heightPercent < 1.0f / 2) {
            // port is on the top
            return PortSide.NORTH;
        } else {
            // port is on the bottom
            return PortSide.SOUTH;
        }
    }

    
    ///////////////////////////////////////////////////////////////////////////////
    // Apply Layout Results

    /**
     * {@inheritDoc}
     */
    public void applyLayout() {
        // determine the border spacing, which influences the offset
        KNode parentNode = (KNode) layeredGraph.getProperty(Properties.ORIGIN);
        KShapeLayout parentLayout = parentNode.getData(KShapeLayout.class);
        float borderSpacing = parentLayout.getProperty(LayoutOptions.BORDER_SPACING);
        if (borderSpacing < 0) {
            borderSpacing = Properties.DEF_SPACING;
        }
        
        // calculate the offset
        KVector offset = new KVector(borderSpacing + layeredGraph.getOffset().x,
                borderSpacing + layeredGraph.getOffset().y);
        
        // along the way, we collect the list of edges to be processed later
        List<LEdge> edgeList = new LinkedList<LEdge>();

        // process the nodes
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode lnode : layer.getNodes()) {
                Object origin = lnode.getProperty(Properties.ORIGIN);
                
                if (origin instanceof KNode) {
                    // set the node position
                    KShapeLayout nodeLayout = ((KNode) origin).getData(KShapeLayout.class);
                    
                    nodeLayout.setXpos((float) (lnode.getPosition().x + offset.x));
                    nodeLayout.setYpos((float) (lnode.getPosition().y + offset.y));
                    
                    // set port positions
                    if (!nodeLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS).isPosFixed()) {
                        for (LPort lport : lnode.getPorts()) {
                            origin = lport.getProperty(Properties.ORIGIN);
                            if (origin instanceof KPort) {
                                KShapeLayout portLayout = ((KPort) origin).getData(KShapeLayout.class);
                                portLayout.setXpos(
                                        (float) (lport.getPosition().x - lport.getSize().x / 2.0));
                                portLayout.setYpos(
                                        (float) (lport.getPosition().y - lport.getSize().y / 2.0));
                            }
                        }
                    }
                }
                
                // collect edges
                for (LPort port : lnode.getPorts(PortType.OUTPUT)) {
                    edgeList.addAll(port.getEdges());
                }
            }
        }
        
        // check if the edge routing uses splines
        EdgeRoutingStrategy routing = parentLayout.getProperty(Properties.EDGE_ROUTING);
        boolean splinesActive = routing == EdgeRoutingStrategy.SIMPLE_SPLINES
                || routing == EdgeRoutingStrategy.COMPLEX_SPLINES;
        
        // iterate through all edges
        for (LEdge ledge : edgeList) {
            KEdge kedge = (KEdge) ledge.getProperty(Properties.ORIGIN);
            KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
            KVectorChain bendPoints = ledge.getBendPoints();
            
            // add the source port and target port positions to the vector chain
            LPort sourcePort = ledge.getSource();
            bendPoints.addFirst(KVector.add(sourcePort.getPosition(),
                    sourcePort.getNode().getPosition()));
            LPort targetPort = ledge.getTarget();
            bendPoints.addLast(KVector.add(targetPort.getPosition(),
                    targetPort.getNode().getPosition()));
            
            // translate the bend points by the offset and apply the bend points
            bendPoints.translate(offset);
            KimlUtil.applyVectorChain(edgeLayout, bendPoints);
            
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
            
            // set spline option
            if (splinesActive) {
                edgeLayout.setProperty(LayoutOptions.EDGE_ROUTING, EdgeRouting.SPLINES);
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
    
}
