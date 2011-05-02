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

import java.util.EnumSet;
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
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.graph.Insets;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.p5edges.EdgeRoutingStrategy;

/**
 * Manages the transformation of KGraphs to LayeredGraphs. Sets the
 * {@link Properties#GRAPH_PROPERTIES} property on imported graphs.
 *
 * @author msp
 * @author cds
 */
public class KGraphImporter extends AbstractGraphImporter<KNode> {

    /**
     * Constructs a new instance that transforms the given node into a layered graph.
     * 
     * @param node the node to import.
     */
    public KGraphImporter(final KNode node) {
        super(node);
    }


    ///////////////////////////////////////////////////////////////////////////////
    // Transformation KGraph -> LGraph

    /**
     * {@inheritDoc}
     */
    protected void transform(final KNode source, final LayeredGraph layeredGraph) {
        KShapeLayout sourceShapeLayout = source.getData(KShapeLayout.class);
        
        // copy the properties of the KGraph to the layered graph
        layeredGraph.copyProperties(sourceShapeLayout);
        layeredGraph.checkProperties(Properties.OBJ_SPACING, Properties.THOROUGHNESS);
        
        float borderSpacing = layeredGraph.getProperty(LayoutOptions.BORDER_SPACING);
        if (borderSpacing < 0) {
            borderSpacing = Properties.DEF_SPACING;
        }
        layeredGraph.setProperty(LayoutOptions.BORDER_SPACING, borderSpacing);
        
        // copy the insets to the layered graph
        KInsets kinsets = sourceShapeLayout.getInsets();
        Insets.Double linsets = layeredGraph.getInsets();
        
        linsets.left = kinsets.getLeft();
        linsets.right = kinsets.getRight();
        linsets.top = kinsets.getTop();
        linsets.bottom = kinsets.getBottom();

        // keep a list of created nodes in the layered graph, as well as a map between KGraph
        // nodes / ports and LGraph nodes / ports
        Map<KGraphElement, LGraphElement> elemMap = new HashMap<KGraphElement, LGraphElement>();
        
        // the graph properties discovered during the transformations
        EnumSet<Properties.GraphProperties> graphProperties =
            EnumSet.noneOf(Properties.GraphProperties.class);
        
        // transform everything
        transformNodesAndPorts(source, layeredGraph, elemMap, graphProperties);
        transformEdges(source, elemMap, graphProperties);
        
        // set the graph properties property
        layeredGraph.setProperty(Properties.GRAPH_PROPERTIES, graphProperties);
    }


    /**
     * Transforms the nodes and ports defined by the given layout node.
     * 
     * @param layoutNode the layout node whose edges to transform.
     * @param layeredGraph the layered graph.
     * @param elemMap the element map that maps the original {@code KGraph} elements to the
     *                transformed {@code LGraph} elements.
     * @param graphProperties graph properties updated during the transformation.
     */
    private void transformNodesAndPorts(final KNode layoutNode, final LayeredGraph layeredGraph,
            final Map<KGraphElement, LGraphElement> elemMap,
            final EnumSet<Properties.GraphProperties> graphProperties) {
        
        List<LNode> layeredNodes = layeredGraph.getLayerlessNodes();
        
        KShapeLayout layoutNodeLayout = layoutNode.getData(KShapeLayout.class);
        KVector layoutNodeSize = new KVector(layoutNodeLayout.getWidth(), layoutNodeLayout.getHeight());
        
        // Find out if there are external ports
        List<KPort> ports = layoutNode.getPorts();
        if (!ports.isEmpty()) {
            graphProperties.add(Properties.GraphProperties.EXTERNAL_PORTS);
        }
        
        // Transform the external ports
        for (KPort kport : ports) {
            KShapeLayout kportLayout = kport.getData(KShapeLayout.class);
            
            // Calculate the position of the port's center
            KVector kportPosition = new KVector(
                    kportLayout.getXpos() + kportLayout.getWidth() / 2.0,
                    kportLayout.getYpos() + kportLayout.getHeight() / 2.0);
            
            // Count the number of incoming and outgoing edges
            int inEdges = 0, outEdges = 0;
            for (KEdge edge : kport.getEdges()) {
                if (edge.getSourcePort() == kport) {
                    outEdges++;
                }
                if (edge.getTargetPort() == kport) {
                    inEdges++;
                }
            }
            
            // Create dummy
            LNode dummy = createExternalPortDummy(kport,
                    kportLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS),
                    KimlUtil.calcPortSide(kport),
                    inEdges - outEdges,
                    layoutNodeSize,
                    kportPosition);
            layeredNodes.add(dummy);
            elemMap.put(kport, dummy);
        }
        
        // Now transform the node's children
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
            
            // port constraints cannot be undefined
            PortConstraints portConstraints = nodeLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS);
            if (portConstraints == PortConstraints.UNDEFINED) {
                portConstraints = PortConstraints.FREE;
            }
            
            // get a sorted list of the node's ports; if there are any with non-free port
            // constraints, set the appropriate graph property
            KPort[] sortedPorts = KimlUtil.getSortedPorts(child);
            if (sortedPorts.length > 0 && portConstraints != PortConstraints.FREE) {
                graphProperties.add(Properties.GraphProperties.NON_FREE_PORTS);
            }
            
            // transform the ports
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
                
                // create layered port, copying its position
                LPort newPort = new LPort(kport.getLabel().getText());
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
                    newPort.setLabel(llabel);
                }
                
                // calculate port side
                PortSide newPortSide = KimlUtil.calcPortSide(kport);
                newPort.setSide(newPortSide);
                
                if (newPortSide == PortSide.NORTH || newPortSide == PortSide.SOUTH) {
                    graphProperties.add(Properties.GraphProperties.NORTH_SOUTH_PORTS);
                }
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
                LPort inputPort = new LPort();
                inputPort.setSide(PortSide.WEST);
                inputPort.setNode(newNode);
                
                LPort outputPort = new LPort();
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
     * @param graphProperties graph properties updated during the transformation.
     */
    private void transformEdges(final KNode layoutNode, final Map<KGraphElement, LGraphElement> elemMap,
            final EnumSet<Properties.GraphProperties> graphProperties) {
        
        // Transform edges originating in the layout node's external ports
        for (KEdge kedge : layoutNode.getOutgoingEdges()) {
            // Only transform edges going into the layout node's direct children
            // (self-loops of the layout node will be processed on level higher)
            if (kedge.getTarget().getParent() == layoutNode) {
                transformEdge(kedge, layoutNode, elemMap, graphProperties);
            } else {
                // the edge is excluded from layout
                KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
                edgeLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
            }
        }
        
        // Transform edges originating in the layout node's children
        for (KNode child : layoutNode.getChildren()) {
            for (KEdge kedge : child.getOutgoingEdges()) {
                // exclude edges that pass hierarchy bounds (except for those going into an
                // external port)
                if (kedge.getTarget() != layoutNode
                        && kedge.getTarget().getParent() == child.getParent()) {
                    
                    transformEdge(kedge, layoutNode, elemMap, graphProperties);
                } else {
                    // the edge is excluded from layout
                    KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
                    edgeLayout.setProperty(LayoutOptions.NO_LAYOUT, true);
                }
            }
        }
    }

    /**
     * Transforms the given edge.
     * 
     * @param kedge the edge to transform.
     * @param layoutNode the node containing everything to lay out.
     * @param elemMap the element map that maps the original {@code KGraph} elements to the
     *                transformed {@code LGraph} elements.
     * @param graphProperties graph properties updated during the transformation.
     */
    private void transformEdge(final KEdge kedge, final KNode layoutNode,
            final Map<KGraphElement, LGraphElement> elemMap,
            final EnumSet<Properties.GraphProperties> graphProperties) {
        
        KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
        
        // retrieve source and target
        LNode sourceNode = null;
        LPort sourcePort = null;
        LNode targetNode = null;
        LPort targetPort = null;
        
        // check if the edge source is an external port
        if (kedge.getSource() == layoutNode) {
            sourceNode = (LNode) elemMap.get(kedge.getSourcePort());
            sourcePort = sourceNode.getPorts().get(0);
        } else {
            sourceNode = (LNode) elemMap.get(kedge.getSource());
            sourcePort = (LPort) elemMap.get(kedge.getSourcePort());
        }
        
        // check if the edge target is an external port
        if (kedge.getTarget() == layoutNode) {
            targetNode = (LNode) elemMap.get(kedge.getTargetPort());
            targetPort = targetNode.getPorts().get(0);
        } else {
            targetNode = (LNode) elemMap.get(kedge.getTarget());
            targetPort = (LPort) elemMap.get(kedge.getTargetPort());
        }
        
        // if we have a self-loop, set the appropriate graph property
        if (sourceNode != null && sourceNode != layoutNode && sourceNode == targetNode) {
            graphProperties.add(Properties.GraphProperties.SELF_LOOPS);
        }
        
        // create source port
        if (sourcePort == null) {
            if (sourceNode.getProperty(LayoutOptions.HYPERNODE)) {
                // Hypernodes have a western input port
                sourcePort = sourceNode.getPorts(PortSide.WEST).iterator().next();
            } else {
                sourcePort = new LPort();
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
                // Hypernodes have a western input port
                targetPort = targetNode.getPorts(PortSide.WEST).iterator().next();
            } else {
                targetPort = new LPort();
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
    @Override
    protected void applyLayout(final LayeredGraph layeredGraph, final KNode target) {
        // determine the border spacing, which influences the offset
        KShapeLayout parentLayout = target.getData(KShapeLayout.class);
        float borderSpacing = layeredGraph.getProperty(LayoutOptions.BORDER_SPACING);
        
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
                for (LPort port : lnode.getPorts()) {
                    edgeList.addAll(port.getOutgoingEdges());
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
