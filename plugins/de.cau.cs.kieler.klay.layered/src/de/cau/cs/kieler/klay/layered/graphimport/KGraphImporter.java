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
package de.cau.cs.kieler.klay.layered.graphimport;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortLabelPlacement;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.SizeConstraint;
import de.cau.cs.kieler.kiml.options.SizeOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement.HashCodeCounter;
import de.cau.cs.kieler.klay.layered.graph.LGraphUtil;
import de.cau.cs.kieler.klay.layered.graph.LInsets;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.p3order.CrossingMinimizationStrategy;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.PortType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Manages the transformation of KGraphs to LayeredGraphs. Sets the
 * {@link Properties#GRAPH_PROPERTIES} property on imported graphs.
 * 
 * @author msp
 * @author cds
 * @kieler.design 2012-08-10 chsch grh
 * @kieler.rating proposed yellow by msp
 */
public class KGraphImporter implements IGraphImporter<KNode> {
    
    /** the hash code counter used to determine hash codes of graph elements. */
    private final HashCodeCounter hashCodeCounter;
    
    /**
     * Creates a graph importer with the given hash code counter.
     * 
     * @param counter the hash code counter used to determine hash codes of graph elements
     */
    public KGraphImporter(final HashCodeCounter counter) {
        this.hashCodeCounter = counter;
    }

    // //////////////////////////////////////////////////////////////////////////////
    // Transformation KGraph -> LGraph

    /**
     * {@inheritDoc}
     */
    public LGraph importGraph(final KNode kgraph) {
        // Keep a map between KGraph nodes / ports and LGraph nodes / ports
        Map<KGraphElement, LGraphElement> elemMap = new HashMap<KGraphElement, LGraphElement>();
        
        // Create the layered graph
        LGraph topLevelGraph = createLGraph(kgraph);
        
        // Transform the external ports
        Set<GraphProperties> graphProperties = topLevelGraph.getProperty(
                InternalProperties.GRAPH_PROPERTIES);
        checkExternalPorts(kgraph, graphProperties);
        if (graphProperties.contains(GraphProperties.EXTERNAL_PORTS)) {
            for (KPort kport : kgraph.getPorts()) {
                transformExternalPort(kport, kgraph, topLevelGraph, elemMap);
            }
        }
        
        boolean hierarchicalLayout = kgraph.getData(KShapeLayout.class).getProperty(
                LayoutOptions.LAYOUT_HIERARCHY);
        if (hierarchicalLayout) {
            LinkedList<KNode> knodeQueue = new LinkedList<KNode>();

            // Transform the node's children
            knodeQueue.addAll(kgraph.getChildren());
            do {
                KNode knode = knodeQueue.removeFirst();
                if (!knode.getData(KShapeLayout.class).getProperty(LayoutOptions.NO_LAYOUT)) {
                    LGraph parentGraph = topLevelGraph;
                    LNode parentLNode = (LNode) elemMap.get(knode.getParent());
                    if (parentLNode != null) {
                        parentGraph = parentLNode.getProperty(InternalProperties.NESTED_LGRAPH);
                    }
                    LNode lnode = transformNode(knode, parentGraph, elemMap);
                    
                    if (!knode.getChildren().isEmpty()) {
                        LGraph nestedGraph = createLGraph(knode);
                        lnode.setProperty(InternalProperties.NESTED_LGRAPH, nestedGraph);
                        nestedGraph.setProperty(InternalProperties.PARENT_LNODE, lnode);
                        knodeQueue.addAll(knode.getChildren());
                    }
                }
            } while (!knodeQueue.isEmpty());

            // Transform the edges
            knodeQueue.add(kgraph);
            do {
                KNode knode = knodeQueue.removeFirst();
                for (KEdge kedge : knode.getOutgoingEdges()) {
                    if (!kedge.getData(KEdgeLayout.class).getProperty(LayoutOptions.NO_LAYOUT)) {
                        KNode parentKNode = knode;
                        if (!KimlUtil.isDescendant(kedge.getTarget(), knode)) {
                            parentKNode = knode.getParent();
                        }
                        LGraph parentGraph = topLevelGraph;
                        LNode parentLNode = (LNode) elemMap.get(parentKNode);
                        if (parentLNode != null) {
                            parentGraph = parentLNode.getProperty(InternalProperties.NESTED_LGRAPH);
                        }
                        transformEdge(kedge, parentGraph, elemMap);
                    }
                }
                knodeQueue.addAll(knode.getChildren());
            } while (!knodeQueue.isEmpty());
        
        } else {

            // Transform the node's children
            for (KNode child : kgraph.getChildren()) {
                if (!child.getData(KShapeLayout.class).getProperty(LayoutOptions.NO_LAYOUT)) {
                    transformNode(child, topLevelGraph, elemMap);
                }
            }
            
            // Transform the edges
            for (KNode child : kgraph.getChildren()) {
                for (KEdge kedge : child.getOutgoingEdges()) {
                    // Exclude edges that pass hierarchy bounds
                    if ((kedge.getTarget().getParent() == kgraph || kedge.getTarget() == kgraph)
                            && !kedge.getData(KEdgeLayout.class).getProperty(LayoutOptions.NO_LAYOUT)) {
                        transformEdge(kedge, topLevelGraph, elemMap);
                    }
                }
            }
            for (KEdge kedge : kgraph.getOutgoingEdges()) {
                if (kedge.getTarget().getParent() == kgraph
                        && !kedge.getData(KEdgeLayout.class).getProperty(LayoutOptions.NO_LAYOUT)) {
                    transformEdge(kedge, topLevelGraph, elemMap);
                }
            }
        }
        
        return topLevelGraph;
    }
    
    /**
     * Create an LGraph from the given KNode.
     * 
     * @param parentKNode the parent KNode from which to create the LGraph
     * @return a new LGraph instance
     */
    private LGraph createLGraph(final KNode parentKNode) {
        LGraph layeredGraph = new LGraph(hashCodeCounter);
        layeredGraph.setProperty(InternalProperties.ORIGIN, parentKNode);
        
        // Copy the properties of the KGraph to the layered graph
        KShapeLayout parentLayout = parentKNode.getData(KShapeLayout.class);
        layeredGraph.copyProperties(parentLayout);
        if (layeredGraph.getProperty(LayoutOptions.DIRECTION) == Direction.UNDEFINED) {
            // The default direction is right
            layeredGraph.setProperty(LayoutOptions.DIRECTION, Direction.RIGHT);
        }

        // Initialize the graph properties discovered during the transformations
        layeredGraph.setProperty(InternalProperties.GRAPH_PROPERTIES,
                EnumSet.noneOf(GraphProperties.class));
        
        // Copy the insets to the layered graph
        KInsets kinsets = parentLayout.getInsets();
        LInsets linsets = layeredGraph.getInsets();
        linsets.left = kinsets.getLeft();
        linsets.right = kinsets.getRight();
        linsets.top = kinsets.getTop();
        linsets.bottom = kinsets.getBottom();
        
        return layeredGraph;
    }
    
    /**
     * Check the external ports of the given parent node and set the corresponding graph properties.
     * 
     * @param parentNode a parent KNode
     * @param graphProperties the set of graph properties to modify
     */
    private void checkExternalPorts(final KNode parentNode,
            final Set<GraphProperties> graphProperties) {
        for (KPort kport : parentNode.getPorts()) {
            int hierarchicalEdges = 0;

            for (KEdge kedge : kport.getEdges()) {
                if (parentNode.equals(kedge.getSource().getParent())
                        || parentNode.equals(kedge.getTarget().getParent())) {
                    hierarchicalEdges++;
                    if (hierarchicalEdges > 1) {
                        break;
                    }
                }
            }

            if (hierarchicalEdges > 0) {
                graphProperties.add(GraphProperties.EXTERNAL_PORTS);
            }

            if (hierarchicalEdges > 1) {
                graphProperties.add(GraphProperties.HYPEREDGES);
                break;
            }
        }
    }

    /**
     * Transforms the given external port into a dummy node.
     * 
     * @param kport the port
     * @param graph the original graph
     * @param layeredGraph the corresponding layered graph
     * @param elemMap the element map that maps the original {@code KGraph} elements to the transformed
     *         {@code LGraph} elements
     */
    private void transformExternalPort(final KPort kport, final KNode graph, final LGraph layeredGraph,
            final Map<KGraphElement, LGraphElement> elemMap) {

        KShapeLayout graphLayout = graph.getData(KShapeLayout.class);
        KShapeLayout knodeLayout = kport.getNode().getData(KShapeLayout.class);
        KShapeLayout kportLayout = kport.getData(KShapeLayout.class);

        // Calculate the position of the port's center
        KVector kportPosition = new KVector(kportLayout.getXpos() + kportLayout.getWidth() / 2.0,
                kportLayout.getYpos() + kportLayout.getHeight() / 2.0);

        // Count the number of incoming and outgoing edges
        int inEdges = 0, outEdges = 0;
        for (KEdge edge : kport.getEdges()) {
            if (edge.getSourcePort() == kport && edge.getTarget().getParent() == graph) {
                outEdges++;
            }
            if (edge.getTargetPort() == kport && edge.getSource().getParent() == graph) {
                inEdges++;
            }
        }

        // Create dummy
        KShapeLayout portLayout = kport.getData(KShapeLayout.class);
        PortSide portSide = portLayout.getProperty(LayoutOptions.PORT_SIDE);
        Float offset = portLayout.getProperty(LayoutOptions.OFFSET);
        PortConstraints portConstraints = graphLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        
        Direction direction = layeredGraph.getProperty(LayoutOptions.DIRECTION);
        if (portSide == PortSide.UNDEFINED) {
            portSide = KimlUtil.calcPortSide(kport, direction);
            portLayout.setProperty(LayoutOptions.PORT_SIDE, portSide);
        }
        
        if (offset == null) {
            // if port coordinates are (0,0), we default to port offset 0 to make the common case
            // frustration-free
            if (portLayout.getXpos() == 0.0f && portLayout.getYpos() == 0.0f) {
                offset = 0.0f;
            } else {
                offset = KimlUtil.calcPortOffset(kport, portSide);
            }
            portLayout.setProperty(LayoutOptions.OFFSET, offset);
        }
        
        KShapeLayout layoutNodeLayout = graph.getData(KShapeLayout.class);
        KVector layoutNodeSize = new KVector(layoutNodeLayout.getWidth(),
                layoutNodeLayout.getHeight());
        LNode dummy = LGraphUtil.createExternalPortDummy(
                kportLayout, portConstraints, portSide, inEdges - outEdges, layoutNodeSize,
                kportPosition, new KVector(kportLayout.getWidth(), kportLayout.getHeight()),
                direction, layeredGraph);
        dummy.setProperty(InternalProperties.ORIGIN, kport);
        
        // If the compound node wants to have its port labels placed on the inside, we need to leave
        // enough space for them by creating an LLabel for the KLabels
        if (knodeLayout.getProperty(LayoutOptions.PORT_LABEL_PLACEMENT) == PortLabelPlacement.INSIDE) {
            LPort dummyPort = dummy.getPorts().get(0);
            dummy.setProperty(LayoutOptions.PORT_LABEL_PLACEMENT, PortLabelPlacement.OUTSIDE);
            for (KLabel klabel : kport.getLabels()) {
                KShapeLayout labelLayout = klabel.getData(KShapeLayout.class);

                LLabel newLabel = new LLabel(layeredGraph, klabel.getText());
                newLabel.setProperty(InternalProperties.ORIGIN, klabel);
                newLabel.getSize().x = labelLayout.getWidth();
                newLabel.getSize().y = labelLayout.getHeight();
                newLabel.getPosition().x = labelLayout.getXpos();
                newLabel.getPosition().y = labelLayout.getYpos();
                dummyPort.getLabels().add(newLabel);
            }
        }
        
        layeredGraph.getLayerlessNodes().add(dummy);
        elemMap.put(kport, dummy);
    }

    /**
     * Transforms the given node and its contained ports.
     * 
     * @param node the node to transform
     * @param layeredGraph the layered graph into which the transformed node is put
     * @param elemMap the element map that maps the original {@code KGraph} elements to the transformed
     *         {@code LGraph} elements.
     * @return the new transformed node
     */
    private LNode transformNode(final KNode node, final LGraph layeredGraph,
            final Map<KGraphElement, LGraphElement> elemMap) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);

        // add a new node to the layered graph, copying its position
        LNode newNode = new LNode(layeredGraph);
        newNode.setProperty(InternalProperties.ORIGIN, node);
        newNode.getSize().x = nodeLayout.getWidth();
        newNode.getSize().y = nodeLayout.getHeight();
        newNode.getPosition().x = nodeLayout.getXpos();
        newNode.getPosition().y = nodeLayout.getYpos();
        
        layeredGraph.getLayerlessNodes().add(newNode);
        elemMap.put(node, newNode);
        
        // check if the node is a compound node in the original graph
        if (!node.getChildren().isEmpty()) {
            newNode.setProperty(InternalProperties.COMPOUND_NODE, true);
        }

        // port constraints and sides cannot be undefined
        Set<GraphProperties> graphProperties = layeredGraph.getProperty(
                InternalProperties.GRAPH_PROPERTIES);
        PortConstraints portConstraints = nodeLayout.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        if (portConstraints == PortConstraints.UNDEFINED) {
            portConstraints = PortConstraints.FREE;
        } else if (portConstraints != PortConstraints.FREE) {
            // if the port constraints are not free, set the appropriate graph property
            graphProperties.add(GraphProperties.NON_FREE_PORTS);
        }

        // transform the ports
        Direction direction = layeredGraph.getProperty(LayoutOptions.DIRECTION);
        for (KPort kport : node.getPorts()) {
            KShapeLayout portLayout = kport.getData(KShapeLayout.class);
            if (portLayout.getProperty(LayoutOptions.NO_LAYOUT)) {
                continue;
            }
            
            // find out if there are hyperedges, that is a set of edges connected to the same port
            if (kport.getEdges().size() > 1) {
                graphProperties.add(GraphProperties.HYPEREDGES);
            }

            // create layered port, copying its position
            LPort newPort = new LPort(layeredGraph);
            newPort.setProperty(InternalProperties.ORIGIN, kport);
            KVector portSize = newPort.getSize();
            portSize.x = portLayout.getWidth();
            portSize.y = portLayout.getHeight();
            KVector portPos = newPort.getPosition();
            portPos.x = portLayout.getXpos();
            portPos.y = portLayout.getYpos();
            newPort.setNode(newNode);
            
            newPort.copyProperties(portLayout);
            newPort.setSide(portLayout.getProperty(LayoutOptions.PORT_SIDE));

            // initialize the port's side, offset, and anchor point
            LGraphUtil.initializePort(newPort, portConstraints, direction,
                    portLayout.getProperty(Properties.PORT_ANCHOR));
            
            elemMap.put(kport, newPort);

            switch (direction) {
            case LEFT:
            case RIGHT:
                if (newPort.getSide() == PortSide.NORTH || newPort.getSide() == PortSide.SOUTH) {
                    graphProperties.add(GraphProperties.NORTH_SOUTH_PORTS);
                }
                break;
            case UP:
            case DOWN:
                if (newPort.getSide() == PortSide.EAST || newPort.getSide() == PortSide.WEST) {
                    graphProperties.add(GraphProperties.NORTH_SOUTH_PORTS);
                }
                break;
            }

            // create the port's labels
            for (KLabel klabel : kport.getLabels()) {
                KShapeLayout labelLayout = klabel.getData(KShapeLayout.class);
                if (!labelLayout.getProperty(LayoutOptions.NO_LAYOUT)) {
                    LLabel newLabel = new LLabel(layeredGraph, klabel.getText());
                    newLabel.setProperty(InternalProperties.ORIGIN, klabel);
                    newLabel.getSize().x = labelLayout.getWidth();
                    newLabel.getSize().y = labelLayout.getHeight();
                    newLabel.getPosition().x = labelLayout.getXpos();
                    newLabel.getPosition().y = labelLayout.getYpos();
                    newPort.getLabels().add(newLabel);
                }
            }
        }

        // add the node's labels
        for (KLabel klabel : node.getLabels()) {
            KShapeLayout labelLayout = klabel.getData(KShapeLayout.class);
            if (!labelLayout.getProperty(LayoutOptions.NO_LAYOUT)) {
                LLabel newLabel = new LLabel(layeredGraph, klabel.getText());
                newLabel.setProperty(InternalProperties.ORIGIN, klabel);
                newLabel.getSize().x = labelLayout.getWidth();
                newLabel.getSize().y = labelLayout.getHeight();
                newLabel.getPosition().x = labelLayout.getXpos();
                newLabel.getPosition().y = labelLayout.getYpos();
                newNode.getLabels().add(newLabel);
            }
        }

        // set properties of the new node
        newNode.copyProperties(nodeLayout);

        if (newNode.getProperty(LayoutOptions.COMMENT_BOX)) {
            graphProperties.add(GraphProperties.COMMENTS);
        }

        // if we have a hypernode without ports, create a default input and output port
        if (newNode.getProperty(LayoutOptions.HYPERNODE)) {
            graphProperties.add(GraphProperties.HYPERNODES);
            graphProperties.add(GraphProperties.HYPEREDGES);
            newNode.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FREE);
        }
        
        return newNode;
    }

    /**
     * Transforms the given edge.
     * 
     * @param kedge the edge to transform
     * @param layeredGraph the layered graph
     * @param elemMap the element map that maps the original {@code KGraph} elements to the transformed
     *         {@code LGraph} elements
     * @return the transformed edge, or {@code null} if it cannot be transformed
     */
    private LEdge transformEdge(final KEdge kedge, final LGraph layeredGraph,
            final Map<KGraphElement, LGraphElement> elemMap) {
        LNode sourceNode = (LNode) elemMap.get(kedge.getSource());
        LPort sourcePort = null;
        if (kedge.getSourcePort() != null) {
            assert kedge.getSource() == kedge.getSourcePort().getNode();
            LGraphElement sourceElem = elemMap.get(kedge.getSourcePort());
            if (sourceElem instanceof LPort) {
                sourcePort = (LPort) sourceElem;
            } else if (sourceElem instanceof LNode) {
                // The source port is an external port of the top-level KGraph
                sourceNode = (LNode) sourceElem;
                sourcePort = sourceNode.getPorts().get(0);
            }
        }

        LNode targetNode = (LNode) elemMap.get(kedge.getTarget());
        LPort targetPort = null;
        if (kedge.getTargetPort() != null) {
            assert kedge.getTarget() == kedge.getTargetPort().getNode();
            LGraphElement targetElem = elemMap.get(kedge.getTargetPort());
            if (targetElem instanceof LPort) {
                targetPort = (LPort) targetElem;
            } else if (targetElem instanceof LNode) {
                // The target port is an external port of the top-level KGraph
                targetNode = (LNode) targetElem;
                targetPort = targetNode.getPorts().get(0);
            }
        }
        
        if (sourceNode != null && targetNode != null) {
            // Create a layered edge
            LEdge newEdge = new LEdge(layeredGraph);
            newEdge.setProperty(InternalProperties.ORIGIN, kedge);
            elemMap.put(kedge, newEdge);
            
            // If we have a self-loop, set the appropriate graph property
            Set<GraphProperties> graphProperties = layeredGraph.getProperty(
                    InternalProperties.GRAPH_PROPERTIES);
            if (sourceNode == targetNode) {
                graphProperties.add(GraphProperties.SELF_LOOPS);
            }

            // Create source and target ports if they do not exist yet
            KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
            if (sourcePort == null) {
                PortType portType = PortType.OUTPUT;
                KVector sourcePoint = null;
                if (sourceNode.getProperty(LayoutOptions.PORT_CONSTRAINTS).isSideFixed()) {
                    sourcePoint = edgeLayout.getSourcePoint().createVector();
                    if (KimlUtil.isDescendant(kedge.getTarget(), kedge.getSource())) {
                        // External source port: put it on the west side
                        portType = PortType.INPUT;
                        sourcePoint.add(sourceNode.getPosition());
                    }
                }
                sourcePort = LGraphUtil.createPort(sourceNode, sourcePoint, portType, layeredGraph);
            }
            if (targetPort == null) {
                PortType portType = PortType.INPUT;
                KVector targetPoint = null;
                if (targetNode.getProperty(LayoutOptions.PORT_CONSTRAINTS).isSideFixed()) {
                    targetPoint = edgeLayout.getTargetPoint().createVector();
                    if (kedge.getSource().getParent() != kedge.getTarget().getParent()) {
                        // Cross-hierarchy edge: correct the target position
                        KNode referenceNode = kedge.getSource();
                        if (!KimlUtil.isDescendant(kedge.getTarget(), kedge.getSource())) {
                            referenceNode = referenceNode.getParent();
                            if (KimlUtil.isDescendant(kedge.getSource(), kedge.getTarget())) {
                                portType = PortType.OUTPUT;
                            }
                        }
                        KimlUtil.toAbsolute(targetPoint, referenceNode);
                        KimlUtil.toRelative(targetPoint, kedge.getTarget().getParent());
                    }
                }
                targetPort = LGraphUtil.createPort(
                        targetNode, targetPoint, portType, targetNode.getGraph());
            }
            
            newEdge.setSource(sourcePort);
            newEdge.setTarget(targetPort);

            // Transform the edge's labels
            for (KLabel klabel : kedge.getLabels()) {
                KShapeLayout labelLayout = klabel.getData(KShapeLayout.class);
                EdgeLabelPlacement labelPlacement = labelLayout.getProperty(
                        LayoutOptions.EDGE_LABEL_PLACEMENT);
                if (!labelLayout.getProperty(LayoutOptions.NO_LAYOUT)) {
                    LLabel newLabel = new LLabel(layeredGraph, klabel.getText());
                    newLabel.getPosition().x = labelLayout.getXpos();
                    newLabel.getPosition().y = labelLayout.getYpos();
                    newLabel.getSize().x = labelLayout.getWidth();
                    newLabel.getSize().y = labelLayout.getHeight();
                    newLabel.setProperty(InternalProperties.ORIGIN, klabel);
                    newEdge.getLabels().add(newLabel);
                    switch (labelPlacement) {
                    case HEAD:
                    case TAIL:
                        // Add annotation if the graph contains labels which shall be placed
                        // at the ends of an edge
                        graphProperties.add(GraphProperties.END_LABELS);
                        newLabel.setProperty(LayoutOptions.EDGE_LABEL_PLACEMENT, labelPlacement);
                        break;
                    default:
                        // Add annotation if the graph contains labels which shall be placed
                        // in the middle of an edge
                        graphProperties.add(GraphProperties.CENTER_LABELS);
                        newLabel.setProperty(LayoutOptions.EDGE_LABEL_PLACEMENT,
                                EdgeLabelPlacement.CENTER);
                    }
                }
            }
            
            // Copy the bend points of the edge if they are needed by anyone
            if (layeredGraph.getProperty(Properties.CROSS_MIN)
                    == CrossingMinimizationStrategy.INTERACTIVE
                    && !edgeLayout.getBendPoints().isEmpty()) {
                KVectorChain bendpoints = new KVectorChain();
                for (KPoint point : edgeLayout.getBendPoints()) {
                    bendpoints.add(point.createVector());
                }
                newEdge.setProperty(InternalProperties.ORIGINAL_BENDPOINTS, bendpoints);
            }
    
            // Set properties of the new edge
            newEdge.copyProperties(edgeLayout);
            // Clear junction points, since they are recomputed from scratch
            newEdge.setProperty(LayoutOptions.JUNCTION_POINTS, null);
            
            return newEdge;
        }
        return null;
    }

    // //////////////////////////////////////////////////////////////////////////////
    // Apply Layout Results

    /**
     * {@inheritDoc}
     */
    public void applyLayout(final LGraph lgraph) {
        Object graphOrigin = lgraph.getProperty(InternalProperties.ORIGIN);
        if (!(graphOrigin instanceof KNode)) {
            return;
        }
        KNode parentNode = (KNode) graphOrigin;
        
        // Get the offset to be added to all coordinates
        KVector offset = lgraph.getOffset();

        // Along the way, we collect the list of edges to be processed later
        List<LEdge> edgeList = new LinkedList<LEdge>();

        // Process the nodes
        for (LNode lnode : lgraph.getLayerlessNodes()) {
            Object origin = lnode.getProperty(InternalProperties.ORIGIN);

            if (origin instanceof KNode) {
                // Set the node position
                KNode knode = (KNode) origin;
                KShapeLayout nodeLayout = knode.getData(KShapeLayout.class);

                nodeLayout.setXpos((float) (lnode.getPosition().x + offset.x));
                nodeLayout.setYpos((float) (lnode.getPosition().y + offset.y));
                
                // Set the node size, if necessary
                if (!nodeLayout.getProperty(LayoutOptions.SIZE_CONSTRAINT).isEmpty()) {
                    nodeLayout.setWidth((float) lnode.getSize().x);
                    nodeLayout.setHeight((float) lnode.getSize().y);
                }

                // Set port positions
                for (LPort lport : lnode.getPorts()) {
                    origin = lport.getProperty(InternalProperties.ORIGIN);
                    if (origin instanceof KPort) {
                        KPort kport = (KPort) origin;
                        KShapeLayout portLayout = kport.getData(KShapeLayout.class);
                        portLayout.applyVector(lport.getPosition());
                        portLayout.setProperty(LayoutOptions.PORT_SIDE, lport.getSide());
                    }
                }
                
                // Set node label positions, if they were not fixed
                if (!lnode.getProperty(LayoutOptions.NODE_LABEL_PLACEMENT).isEmpty()) {
                    for (LLabel llabel : lnode.getLabels()) {
                        KLabel klabel = (KLabel) llabel.getProperty(InternalProperties.ORIGIN);
                        KShapeLayout klabelLayout = klabel.getData(KShapeLayout.class);
                        klabelLayout.applyVector(llabel.getPosition());
                    }
                }
                
                // Set port label positions, if they were not fixed
                if (lnode.getProperty(LayoutOptions.PORT_LABEL_PLACEMENT) != PortLabelPlacement.FIXED) {
                    for (LPort lport : lnode.getPorts()) {
                        for (LLabel label : lport.getLabels()) {
                            KLabel klabel = (KLabel) label.getProperty(InternalProperties.ORIGIN);
                            KShapeLayout klabelLayout = klabel.getData(KShapeLayout.class);
                            klabelLayout.applyVector(label.getPosition());
                        }
                    }
                }
                
                // Set node insets, if requested
                if (nodeLayout.getProperty(LayoutOptions.SIZE_OPTIONS)
                        .contains(SizeOptions.COMPUTE_INSETS)) {
                    
                    // Apply insets
                    LInsets lInsets = lnode.getInsets();
                    KInsets kInsets = nodeLayout.getInsets();
                    kInsets.setBottom((float) lInsets.bottom);
                    kInsets.setTop((float) lInsets.top);
                    kInsets.setLeft((float) lInsets.left);
                    kInsets.setRight((float) lInsets.right);
                }
            } else if (origin instanceof KPort
                    && lgraph.getProperty(InternalProperties.PARENT_LNODE) == null) {
                // It's an external port. Set its position if it hasn't already been done before
                KPort kport = (KPort) origin;
                KShapeLayout portLayout = kport.getData(KShapeLayout.class);
                KVector portPosition = LGraphUtil.getExternalPortPosition(lgraph, lnode,
                        portLayout.getWidth(), portLayout.getHeight());
                portLayout.applyVector(portPosition);
            }

            // Collect edges
            for (LPort port : lnode.getPorts()) {
                edgeList.addAll(port.getOutgoingEdges());
            }
        }

        KShapeLayout parentLayout = parentNode.getData(KShapeLayout.class);
        EdgeRouting routing = parentLayout.getProperty(LayoutOptions.EDGE_ROUTING);
        
        // Iterate through all edges
        for (LEdge ledge : edgeList) {
            KEdge kedge = (KEdge) ledge.getProperty(InternalProperties.ORIGIN);
            // Self-loops are currently left untouched unless the edge router is set to
            // the orthogonal router
            if (kedge == null || ledge.isSelfLoop() && routing != EdgeRouting.ORTHOGONAL) {
                continue;
            }
            
            KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
            KVectorChain bendPoints = ledge.getBendPoints();
            KVector edgeOffset = offset;

            // Adapt the offset value and add the source port position to the vector chain
            KVector sourcePoint;
            if (LGraphUtil.isDescendant(ledge.getTarget().getNode(), ledge.getSource().getNode())) {
                LPort sourcePort = ledge.getSource();
                sourcePoint = KVector.sum(sourcePort.getPosition(), sourcePort.getAnchor());
                LInsets sourceInsets = sourcePort.getNode().getInsets();
                sourcePoint.translate(-sourceInsets.left, -sourceInsets.top);
                LGraph nestedGraph = sourcePort.getNode().getProperty(InternalProperties.NESTED_LGRAPH);
                if (nestedGraph != null) {
                    edgeOffset = nestedGraph.getOffset();
                }
                sourcePoint.sub(edgeOffset);
            } else {
                sourcePoint = ledge.getSource().getAbsoluteAnchor();
            }
            bendPoints.addFirst(sourcePoint);
            
            // Add the target port position to the vector chain, including additional offset
            KVector targetPoint = ledge.getTarget().getAbsoluteAnchor();
            if (ledge.getProperty(InternalProperties.TARGET_OFFSET) != null) {
                targetPoint.add(ledge.getProperty(InternalProperties.TARGET_OFFSET));
            }
            bendPoints.addLast(targetPoint);

            // Translate the bend points by the offset and apply the bend points
            bendPoints.translate(edgeOffset);
            edgeLayout.applyVectorChain(bendPoints);

            // Apply layout to labels
            for (LLabel label : ledge.getLabels()) {
                KLabel klabel = (KLabel) label.getProperty(InternalProperties.ORIGIN);
                KShapeLayout klabelLayout = klabel.getData(KShapeLayout.class);
                klabelLayout.applyVector(label.getPosition().add(edgeOffset));
            }
            
            // Copy junction points
            KVectorChain junctionPoints = ledge.getProperty(LayoutOptions.JUNCTION_POINTS);
            if (junctionPoints != null) {
                junctionPoints.translate(edgeOffset);
                edgeLayout.setProperty(LayoutOptions.JUNCTION_POINTS, junctionPoints);
            } else {
                edgeLayout.setProperty(LayoutOptions.JUNCTION_POINTS, null);
            }

            // Mark the edge with information about its routing
            if (routing == EdgeRouting.SPLINES) {
                // SPLINES means that bend points shall be interpreted as control points for splines
                edgeLayout.setProperty(LayoutOptions.EDGE_ROUTING, EdgeRouting.SPLINES);
            } else {
                // null means that bend points shall be interpreted as bend points
                edgeLayout.setProperty(LayoutOptions.EDGE_ROUTING, null);
            }
        }

        // Setup the parent node
        KVector actualGraphSize = lgraph.getActualSize();
        parentLayout.setProperty(LayoutOptions.SIZE_CONSTRAINT, SizeConstraint.fixed());

        if (lgraph.getProperty(InternalProperties.PARENT_LNODE) == null) {
            if (lgraph.getProperty(InternalProperties.GRAPH_PROPERTIES).contains(
                    GraphProperties.EXTERNAL_PORTS)) {
                // Ports have positions assigned, the graph's size is final
                parentLayout.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
                KimlUtil.resizeNode(
                        parentNode,
                        (float) actualGraphSize.x,
                        (float) actualGraphSize.y,
                        false,
                        true);
            } else {
                // Ports have not been positioned yet - leave this for next layouter
                KimlUtil.resizeNode(
                        parentNode,
                        (float) actualGraphSize.x,
                        (float) actualGraphSize.y,
                        true,
                        true);
            }
        }
        
        // Process nested subgraphs
        for (LNode lnode : lgraph.getLayerlessNodes()) {
            LGraph nestedGraph = lnode.getProperty(InternalProperties.NESTED_LGRAPH);
            if (nestedGraph != null) {
                applyLayout(nestedGraph);
            }
        }
    }

}
