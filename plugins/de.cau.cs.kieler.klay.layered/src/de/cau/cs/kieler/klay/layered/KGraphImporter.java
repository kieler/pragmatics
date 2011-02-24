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
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
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
        List<LNode> layeredNodes = layeredGraph.getLayerlessNodes();

        // keep a map between KGraph nodes / ports and LGraph nodes / ports
        Map<KGraphElement, LGraphElement> elemMap = new HashMap<KGraphElement, LGraphElement>();
        
        // transform nodes and ports
        for (KNode child : layoutNode.getChildren()) {
            // add a new node to the layered graph, copying its size
            KShapeLayout nodeLayout = child.getData(KShapeLayout.class);
            
            LNode newNode = new LNode(child.getLabel().getText());
            newNode.setProperty(Properties.ORIGIN, child);
            newNode.getSize().x = nodeLayout.getWidth();
            newNode.getSize().y = nodeLayout.getHeight();
            layeredNodes.add(newNode);
            
            elemMap.put(child, newNode);
            
            // add the node's ports
            KPort[] sortedPorts = KimlUtil.getSortedPorts(child);
            for (KPort kport : sortedPorts) {
                KShapeLayout portLayout = kport.getData(KShapeLayout.class);
                
                // determine the port type
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
                
                // create layered port, copying its position
                LPort newPort = new LPort(type, kport.getLabel().getText());
                newPort.setProperty(Properties.ORIGIN, kport);
                newPort.getPos().x = portLayout.getXpos();
                newPort.getPos().y = portLayout.getYpos();
                newPort.setNode(newNode);
                
                elemMap.put(kport, newPort);
                
                // calculate port side
                PortConstraints portConstraints = nodeLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS);
                if (portConstraints != PortConstraints.UNDEFINED) {
                    newPort.setSide(KimlUtil.calcPortSide(kport));
                }
            }
            newNode.copyProperties(nodeLayout);
        }

        // transform edges
        for (KNode child : layoutNode.getChildren()) {
            for (KEdge kedge : child.getOutgoingEdges()) {
                KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
                
                // exclude edges that pass hierarchy bounds and self-loops
                // TODO self loops shouldn't be excluded once the edge router can handle them
                if (kedge.getTarget().getParent() == child.getParent()
                        && kedge.getSource() != kedge.getTarget()) {
                    
                    // TODO handle fixed port constraints for edges without ports
                    
                    // retrieve source and target
                    LNode sourceNode = (LNode) elemMap.get(child);
                    LPort sourcePort = (LPort) elemMap.get(kedge.getSourcePort());
                    LNode targetNode = (LNode) elemMap.get(kedge.getTarget());
                    LPort targetPort = (LPort) elemMap.get(kedge.getTargetPort());
                    
                    // source port
                    if (sourcePort == null) {
                        sourcePort = new LPort(PortType.OUTPUT);
                        sourcePort.setNode(sourceNode);
                    } else if (sourcePort.getType() != PortType.OUTPUT) {
                        // ignore ports with incoming as well as outgoing edges
                        edgeLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
                        continue;
                    }
                    
                    // target port
                    if (targetPort == null) {
                        targetPort = new LPort(PortType.INPUT);
                        targetPort.setNode(targetNode);
                    } else if (targetPort.getType() != PortType.INPUT) {
                        // ignore ports with incoming as well as outgoing edges
                        edgeLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
                        continue;
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
                        newLabel.getSize().x = labelLayout.getWidth();
                        newLabel.getSize().y = labelLayout.getHeight();
                        newLabel.setProperty(Properties.ORIGIN, klabel);
                        newEdge.getLabels().add(newLabel);
                    }
                    
                    // set properties of the new edge
                    newEdge.setProperty(Properties.PRIORITY,
                            edgeLayout.getProperty(LayoutOptions.PRIORITY));
                } else {
                    // 
                    edgeLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
                }
            }
        }
    }

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
                    KNode knode = (KNode) origin;
                    KShapeLayout nodeLayout = knode.getData(KShapeLayout.class);
                    
                    nodeLayout.setXpos((float) (lnode.getPos().x + offset.x));
                    nodeLayout.setYpos((float) (lnode.getPos().y + offset.y));
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
            KVector sourcePortPos = ledge.getSource().getPos();
            bendPoints.addFirst(sourcePortPos);
            
            KVector targetPortPos = ledge.getTarget().getPos();
            bendPoints.add(targetPortPos);
            
            // offset the bend points by the offset and apply the bend points
            bendPoints.offset(offset);
            KimlUtil.applyVectorChain(edgeLayout, bendPoints);
            
            // apply layout to labels
            for (LLabel label : ledge.getLabels()) {
                KLabel klabel = (KLabel) label.getProperty(Properties.ORIGIN);
                KShapeLayout klabelLayout = klabel.getData(KShapeLayout.class);
                
                KVector labelPos = new KVector(ledge.getSource().getPos().x, ledge
                        .getSource().getPos().y);
                labelPos.add(ledge.getSource().getNode().getPos());
                labelPos.add(label.getPos());
                klabelLayout.setXpos((float) (labelPos.x + offset.x));
                klabelLayout.setYpos((float) (labelPos.y + offset.y));
            }
            
            // set spline option
            if (splinesActive) {
                edgeLayout.setProperty(LayoutOptions.EDGE_ROUTING, EdgeRouting.SPLINES);
            }
        }

        // set up the parent node
        KInsets insets = parentLayout.getProperty(LayoutOptions.INSETS);
        parentLayout.setWidth((float) layeredGraph.getSize().x + 2 * borderSpacing
                + insets.getLeft() + insets.getRight());
        parentLayout.setHeight((float) layeredGraph.getSize().y + 2 * borderSpacing
                + insets.getTop() + insets.getBottom());
        parentLayout.setProperty(LayoutOptions.FIXED_SIZE, true);
    }
    
}
