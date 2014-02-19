/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.compound;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.kiml.UnsupportedGraphException;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.KlayLayered;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.importexport.ImportUtil;
import de.cau.cs.kieler.klay.layered.intermediate.PortListSorter;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.PortType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * 
 *
 * @author msp
 */
public class CompoundGraphPreprocessor implements ILayoutProcessor {
    
    /** map of generated cross-hierarchy edges. */
    private final Multimap<LEdge, CrossHierarchyEdge> crossHierarchyMap = HashMultimap.create();

    /**
     * An internal representation for external ports. This class is used to pass information
     * gathered on one hierarchy level to the containing hierarchy level. Instances are created
     * whenever a cross-hierarchy edge crosses the hierarchy bounds of a parent node; the instance
     * represents the split point of the edge.
     */
    private static class ExternalPort {
        /** the original edge for which the port is created. */
        private LEdge origEdge;
        /** the node whose outer bounds are crossed by the edge. */
        private LNode parentNode;
        /** the layered graph corresponding to the content of the compound node. */
        private LGraph lgraph;
        /** the dummy node used by the algorithm as representative for the external port. */
        private LNode dummyNode;
        /** the flow direction: input or output. */
        private PortType type = PortType.UNDEFINED;
        
        /**
         * Create an external port.
         * 
         * @param parentNode the original edge for which the port is created
         * @param parentNode the node whose outer bounds are crossed by the edge
         * @param lgraph the layered graph corresponding to the content of the compound node
         * @param dummyNode the dummy node used by the algorithm as representative for the external port
         * @param portType the flow direction: input or output
         */
        ExternalPort(final LEdge origEdge, final LNode parentNode, final LGraph lgraph,
                final LNode dummyNode, final PortType portType) {
            this.origEdge = origEdge;
            this.parentNode = parentNode;
            this.lgraph = lgraph;
            this.dummyNode = dummyNode;
            this.type = portType;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void process(final LGraph lgraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Compound graph preprocessor", 1);
        transformHierarchyEdges(null, lgraph);
        monitor.done();
    }
    
    /**
     * Perform automatic layout recursively in the given graph.
     * 
     * @param parentNode the parent node of the graph
     * @param graph the layered graph
     * @param monitor a progress monitor
     * @return the external ports created to split edges that cross the boundary of the parent node
     */
    private Collection<ExternalPort> transformHierarchyEdges(final LNode parentNode,
            final LGraph graph) {
        // process all children and gather their external ports
        List<ExternalPort> containedExternalPorts = new LinkedList<ExternalPort>();
        for (LNode node : graph.getLayerlessNodes()) {
            LGraph nestedGraph = node.getProperty(Properties.NESTED_LGRAPH);
            if (nestedGraph != null) {
                Collection<ExternalPort> childPorts = transformHierarchyEdges(node, nestedGraph);
                containedExternalPorts.addAll(childPorts);
            }
        }
        
        // process the cross-hierarchy edges connected to the inside of the child nodes
        List<ExternalPort> exportedExternalPorts = new LinkedList<ExternalPort>();
        processInsideEdges(graph, exportedExternalPorts, containedExternalPorts);
        
        // process the cross-hierarchy edges connected to the outside of the parent node
        processOutsideEdges(parentNode, graph, exportedExternalPorts);
        
        return exportedExternalPorts;
    }
    
    /**
     * Process edges connected to the inside of compound nodes contained in the given graph.
     * 
     * @param graph
     * @param exportedExternalPorts list into which new external ports are put
     * @param containedExternalPorts external ports gathered during the recursive layout of children
     */
    private void processInsideEdges(final LGraph graph, final List<ExternalPort> exportedExternalPorts,
            final List<ExternalPort> containedExternalPorts) {
        Direction layoutDirection = layeredGraph.getProperty(LayoutOptions.DIRECTION);
        if (layoutDirection == Direction.UNDEFINED) {
            layoutDirection = Direction.RIGHT;
        }
        Map<KGraphElement, LGraphElement> elementMap = Maps.newHashMap();
        //layeredGraph.getProperty(KGraphImporter.ELEMENT_MAP);
        Map<LEdge, LNode> needSourcePort = new HashMap<LEdge, LNode>();
        Map<LEdge, LNode> needTargetPort = new HashMap<LEdge, LNode>();
        for (ExternalPort externalPort : containedExternalPorts) {
            KNode sourceKNode = externalPort.kedge.getSource();
            KNode targetKNode = externalPort.kedge.getTarget();
            if (externalPort.type == PortType.INPUT && KimlUtil.isDescendant(sourceKNode, parentNode)
                    && sourceKNode.getParent() != parentNode) {
                // the edge comes from the inside of another sibling node,
                // hence we want to process it only once, namely as outgoing edge
                continue;
            }
            
            LNode lnode = (LNode) elementMap.get(externalPort.knode);
            KVector lportPos = ImportUtil.getExternalPortPosition(
                    layeredGraphMap.get(externalPort.lgraph), externalPort.lnode, 0, 0);
            LPort lport = createFixedPort(lnode, lportPos,
                    externalPort.lnode.getProperty(Properties.EXT_PORT_SIDE), layeredGraph);
            if (externalPort.knode != targetKNode && externalPort.knode != sourceKNode) {
                LEdge newEdge = new LEdge(layeredGraph);
                KEdgeLayout kedgeLayout = externalPort.kedge.getData(KEdgeLayout.class);
                newEdge.copyProperties(kedgeLayout);
                newEdge.setProperty(LayoutOptions.JUNCTION_POINTS, null);
                crossHierarchyMap.put(externalPort.kedge, new CrossHierarchyEdge(newEdge,
                        layeredGraph, parentNode, externalPort.type));
                
                if (externalPort.type == PortType.OUTPUT) {
                    newEdge.setSource(lport);
                    LPort targetLPort = null;
                    if (targetKNode.getParent() == parentNode) {
                        // the edge goes to a direct child of the parent node
                        targetLPort = (LPort) elementMap.get(externalPort.kedge.getTargetPort());
                        if (targetLPort == null) {
                            LNode targetLNode = (LNode) elementMap.get(targetKNode);
                            needTargetPort.put(newEdge, targetLNode);
                        } else if (externalPort.kedge.getTargetPort().getNode() != targetKNode) {
                            throw new UnsupportedGraphException(
                                    "Inconsistent target port reference found.");
                        }
                    } else if (KimlUtil.isDescendant(targetKNode, parentNode)) {
                        // the edge goes to the inside of another sibling node
                        ExternalPort targetExtenalPort = null;
                        for (ExternalPort externalPort2 : containedExternalPorts) {
                            if (externalPort2 != externalPort
                                    && externalPort2.kedge == externalPort.kedge) {
                                targetExtenalPort = externalPort2;
                                break;
                            }
                        }
                        LNode targetLNode = (LNode) elementMap.get(targetExtenalPort.knode);
                        KVector targetLPortPos = ImportUtil.getExternalPortPosition(
                                layeredGraphMap.get(targetExtenalPort.lgraph),
                                targetExtenalPort.lnode, 0, 0);
                        targetLPort = createFixedPort(targetLNode, targetLPortPos,
                                targetExtenalPort.lnode.getProperty(Properties.EXT_PORT_SIDE),
                                layeredGraph);
                    } else if (targetKNode == parentNode
                            && externalPort.kedge.getTargetPort() != null) {
                        // the edge goes to an existing port of the parent node
                        KPort targetKPort = externalPort.kedge.getTargetPort();
                        LNode dummyNode = (LNode) elementMap.get(targetKPort);
                        if (dummyNode == null) {
                            KShapeLayout kportLayout = targetKPort.getData(KShapeLayout.class);
                            dummyNode = ImportUtil.createExternalPortDummy(kportLayout,
                                    PortConstraints.FIXED_SIDE, PortSide.fromDirection(layoutDirection),
                                    1, null, null,
                                    new KVector(kportLayout.getWidth(), kportLayout.getHeight()),
                                    layoutDirection, layeredGraph);
                            dummyNode.setProperty(Properties.ORIGIN, targetKPort);
                            layeredGraph.getLayerlessNodes().add(dummyNode);
                            layeredGraph.getProperty(Properties.GRAPH_PROPERTIES).add(
                                    GraphProperties.EXTERNAL_PORTS);
                            elementMap.put(targetKPort, dummyNode);
                        }
                        targetLPort = dummyNode.getPorts().get(0);
                    } else {
                        // the edge goes to the outside of the parent node
                        LNode dummyNode = ImportUtil.createExternalPortDummy(
                                getExternalPortProperties(parentNode, externalPort.kedge),
                                PortConstraints.FIXED_SIDE, PortSide.fromDirection(layoutDirection),
                                1, null, null, new KVector(), layoutDirection, layeredGraph);
                        layeredGraph.getLayerlessNodes().add(dummyNode);
                        layeredGraph.getProperty(Properties.GRAPH_PROPERTIES).add(
                                GraphProperties.EXTERNAL_PORTS);
                        targetLPort = dummyNode.getPorts().get(0);
                        ExternalPort newExternalPort = new ExternalPort(externalPort.kedge, parentNode,
                                layeredGraph, dummyNode, PortType.OUTPUT);
                        exportedExternalPorts.add(newExternalPort);
                    }
                    newEdge.setTarget(targetLPort);
                    
                } else if (externalPort.type == PortType.INPUT) {
                    newEdge.setTarget(lport);
                    LPort sourceLPort = null;
                    if (sourceKNode.getParent() == parentNode) {
                        // the edge comes from a direct child of the parent node
                        sourceLPort = (LPort) elementMap.get(externalPort.kedge.getSourcePort());
                        if (sourceLPort == null) {
                            LNode sourceLNode = (LNode) elementMap.get(sourceKNode);
                            needSourcePort.put(newEdge, sourceLNode);
                        } else if (externalPort.kedge.getSourcePort().getNode() != sourceKNode) {
                            throw new UnsupportedGraphException(
                                    "Inconsistent source port reference found.");
                        }
                    } else if (sourceKNode == parentNode
                            && externalPort.kedge.getSourcePort() != null) {
                        // the edge comes from an existing port of the parent node
                        KPort sourceKPort = externalPort.kedge.getSourcePort();
                        LNode dummyNode = (LNode) elementMap.get(sourceKPort);
                        if (dummyNode == null) {
                            KShapeLayout kportLayout = sourceKPort.getData(KShapeLayout.class);
                            dummyNode = ImportUtil.createExternalPortDummy(kportLayout,
                                    PortConstraints.FIXED_SIDE,
                                    PortSide.fromDirection(layoutDirection).opposed(), -1, null, null,
                                    new KVector(kportLayout.getWidth(), kportLayout.getHeight()),
                                    layoutDirection, layeredGraph);
                            dummyNode.setProperty(Properties.ORIGIN, sourceKPort);
                            layeredGraph.getLayerlessNodes().add(dummyNode);
                            layeredGraph.getProperty(Properties.GRAPH_PROPERTIES).add(
                                    GraphProperties.EXTERNAL_PORTS);
                            elementMap.put(sourceKPort, dummyNode);
                        }
                        sourceLPort = dummyNode.getPorts().get(0);
                    } else {
                        // the edge comes from the outside of the parent node
                        LNode dummyNode = ImportUtil.createExternalPortDummy(
                                getExternalPortProperties(parentNode, externalPort.kedge),
                                PortConstraints.FIXED_SIDE,
                                PortSide.fromDirection(layoutDirection).opposed(), -1, null, null,
                                new KVector(), layoutDirection, layeredGraph);
                        layeredGraph.getLayerlessNodes().add(dummyNode);
                        layeredGraph.getProperty(Properties.GRAPH_PROPERTIES).add(
                                GraphProperties.EXTERNAL_PORTS);
                        sourceLPort = dummyNode.getPorts().get(0);
                        ExternalPort newExternalPort = new ExternalPort(externalPort.kedge, parentNode,
                                layeredGraph, dummyNode, PortType.INPUT);
                        exportedExternalPorts.add(newExternalPort);
                    }
                    newEdge.setSource(sourceLPort);
                }
            }
        }
        
        // create source and target ports where no predefined port position was given
        for (Map.Entry<LEdge, LNode> entry : needTargetPort.entrySet()) {
            LPort port = createFreePort(entry.getValue(), PortType.INPUT, new KVector(), layeredGraph,
                    layoutDirection);
            entry.getKey().setTarget(port);
        }
        for (Map.Entry<LEdge, LNode> entry : needSourcePort.entrySet()) {
            LPort port = createFreePort(entry.getValue(), PortType.OUTPUT, new KVector(), layeredGraph,
                    layoutDirection);
            entry.getKey().setSource(port);
        }
    }
    
    /**
     * Process edges incident to a node in the given graph and crossing its boundary. These
     * edges are split with instances of {@link ExternalPort}. The resulting edge segments are
     * stored as instances of {@link CrossHierarchyEdge} in the {@link #crossHierarchyMap}.
     * 
     * @param graph the processed graph
     * @param exportedExternalPorts list into which new external ports are put
     */
    private void processOutsideEdges(final LNode parentNode, final LGraph graph,
            final List<ExternalPort> exportedExternalPorts) {
        Direction layoutDirection = graph.getProperty(LayoutOptions.DIRECTION);
        List<LNode> newNodes = new LinkedList<LNode>();
        for (LNode childNode : graph.getLayerlessNodes()) {
            for (LEdge origEdge : childNode.getOutgoingEdges()) {
                LNode targetNode = origEdge.getTarget().getNode();
                if (!isDescendant(targetNode, parentNode)) {
                    // the edge goes to the outside of the parent node
                    LEdge newEdge = new LEdge(graph);
                    newEdge.copyProperties(origEdge);
                    newEdge.setProperty(LayoutOptions.JUNCTION_POINTS, null);
                    crossHierarchyMap.put(origEdge, new CrossHierarchyEdge(newEdge, graph,
                            parentNode, PortType.OUTPUT));
                    newEdge.setSource(origEdge.getSource());
                    LNode dummyNode = ImportUtil.createExternalPortDummy(
                            getExternalPortProperties(graph, origEdge),
                            PortConstraints.FREE, PortSide.fromDirection(layoutDirection), 1,
                            null, null, new KVector(), layoutDirection, graph);
                    newNodes.add(dummyNode);
                    graph.getProperty(Properties.GRAPH_PROPERTIES).add(GraphProperties.EXTERNAL_PORTS);
                    LPort targetPort = dummyNode.getPorts().get(0);
                    newEdge.setTarget(targetPort);
                    ExternalPort externalPort = new ExternalPort(origEdge, parentNode, graph,
                            dummyNode, PortType.OUTPUT);
                    exportedExternalPorts.add(externalPort);
                    // disconnect the original edge from the graph
                    origEdge.setSource(null);
                    LPort originalTarget = origEdge.getTarget();
                    if (originalTarget.getProperty(Properties.ORIGIN) == null
                            && originalTarget.getIncomingEdges().size()
                            + originalTarget.getOutgoingEdges().size() == 1) {
                        originalTarget.setNode(null);
                    } else {
                        origEdge.setTarget(null);
                    }
                }
            }
            
            for (KEdge kedge : childNode.getIncomingEdges()) {
                KNode sourceNode = kedge.getSource();
                if (!KimlUtil.isDescendant(sourceNode, parentNode)
                        && !(sourceNode == parentNode && kedge.getSourcePort() != null)) {
                    // the edge comes from the outside of the parent node
                    LEdge newEdge = new LEdge(layeredGraph);
                    KEdgeLayout kedgeLayout = kedge.getData(KEdgeLayout.class);
                    newEdge.copyProperties(kedgeLayout);
                    newEdge.setProperty(LayoutOptions.JUNCTION_POINTS, null);
                    crossHierarchyMap.put(kedge, new CrossHierarchyEdge(newEdge, layeredGraph,
                            parentNode, PortType.INPUT));
                    LPort targetPort = (LPort) elementMap.get(kedge.getTargetPort());
                    if (targetPort == null) {
                        targetPort = createFreePort(lnode, PortType.INPUT,
                                kedgeLayout.getTargetPoint().createVector(), layeredGraph,
                                layoutDirection);
                    } else if (kedge.getTargetPort().getNode() != childNode) {
                        throw new UnsupportedGraphException("Inconsistent target port reference found.");
                    }
                    newEdge.setTarget(targetPort);
                    LNode dummyNode = ImportUtil.createExternalPortDummy(
                            getExternalPortProperties(parentNode, kedge),
                            PortConstraints.FREE, PortSide.fromDirection(layoutDirection).opposed(),
                            -1, null, null, new KVector(), layoutDirection, layeredGraph);
                    layeredGraph.getLayerlessNodes().add(dummyNode);
                    layeredGraph.getProperty(Properties.GRAPH_PROPERTIES).add(
                            GraphProperties.EXTERNAL_PORTS);
                    LPort sourcePort = dummyNode.getPorts().get(0);
                    newEdge.setSource(sourcePort);
                    ExternalPort externalPort = new ExternalPort(kedge, parentNode, layeredGraph,
                            dummyNode, PortType.INPUT);
                    exportedExternalPorts.add(externalPort);
                    
                } else if (lnode.getProperty(LayoutOptions.PORT_CONSTRAINTS).isSideFixed()
                        && kedge.getTargetPort() == null) {
                    LEdge ledge = (LEdge) elementMap.get(kedge);
                    if (ledge != null) {
                        // correct the target position of the normal edge
                        LPort targetPort = ledge.getTarget();
                        targetPort.setSide(PortSide.fromDirection(layoutDirection).opposed());
                        KVector portPos = calcPortPos(lnode, targetPort.getSide(), null);
                        targetPort.getPosition().x = portPos.x;
                        targetPort.getPosition().y = portPos.y;
                    }
                }
            }
        }
        graph.getLayerlessNodes().addAll(newNodes);
    }
    
    /**
     * Create a port with fixed position.
     * 
     * @param node the node to which the port is added
     * @param position the fixed port position
     * @param side the port side
     * @param layeredGraph the layered graph in which the port is created
     * @return the new port
     */
    private LPort createFixedPort(final LNode node, final KVector position, final PortSide side,
            final LGraph layeredGraph) {
        LPort port = new LPort(layeredGraph);
        port.setNode(node);
        
        KVector pos = port.getPosition();
        pos.x = position.x;
        pos.y = position.y;
        
        port.setSide(side);
        
        return port;
    }
    
    /**
     * Create a port with free position.
     * 
     * @param node the node to which the port is added
     * @param type the port type: input or output
     * @param edgePoint the point where the edge touches the node
     *          (used only if port constraints are free)
     * @param layeredGraph the layered graph in which the port is created
     * @param direction the layout direction
     * @return the new port
     */
    private LPort createFreePort(final LNode node, final PortType type, final KVector edgePoint,
            final LGraph layeredGraph, final Direction direction) {
        LPort port = new LPort(layeredGraph);
        port.setNode(node);
        
        switch (type) {
        case INPUT:
            port.setSide(PortSide.fromDirection(direction).opposed());
            break;
        case OUTPUT:
            port.setSide(PortSide.fromDirection(direction));
            break;
        }
        
        KVector pos = port.getPosition();
        KVector newPos;
        if (node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isSideFixed()) {
            newPos = calcPortPos(node, port.getSide(), null);
        } else {
            newPos = edgePoint.sub(node.getPosition()).applyBounds(0, 0, node.getSize().x,
                    node.getSize().y);
        }
        pos.x = newPos.x;
        pos.y = newPos.y;
        
        return port;
    }
    
    /**
     * Determine a suitable position for a new port.
     * 
     * @param node the node for which the port position is requested
     * @param side the side of the node to put the port in
     * @param kport the original port, or {@code null}
     * @return a suitable position
     */
    private KVector calcPortPos(final LNode node, final PortSide side, final KPort kport) {
        LPort[] ports = Iterables.toArray(node.getPorts(side), LPort.class);
        Arrays.sort(ports, new PortListSorter.PortComparator());
        if (side == PortSide.WEST || side == PortSide.SOUTH) {
            // reverse the array due to the clockwise order of ports
            LPort[] reversePorts = ports;
            ports = new LPort[ports.length];
            for (int i = ports.length - 1; i >= 0; i--) {
                ports[i] = reversePorts[ports.length - 1 - i];
            }
        }
        
        boolean vertical = side == PortSide.EAST || side == PortSide.WEST;
        double[] positions = new double[ports.length + 2];
        for (int i = 0; i < ports.length; i++) {
            positions[i + 1] = vertical
                    ? ports[i].getPosition().y
                    : ports[i].getPosition().x;
        }
        positions[positions.length - 1] = vertical
                ? node.getSize().y
                : node.getSize().x;
        
        // find the largest gap between two positions
        int largestPos = 0;
        for (int i = 1; i < positions.length - 1; i++) {
            if (positions[i + 1] - positions[i] > positions[largestPos + 1] - positions[largestPos]) {
                largestPos = i;
            }
        }
        
        float offset = 0, width = 0, height = 0;
        if (kport != null) {
            KShapeLayout portLayout = kport.getData(KShapeLayout.class);
            offset = portLayout.getProperty(Properties.OFFSET);
            width = portLayout.getWidth();
            height = portLayout.getHeight();
        }
        KVector result = new KVector();
        switch (side) {
        case NORTH:
            result.y = -height - offset;
            break;
        case EAST:
            result.x = node.getSize().x + offset;
            break;
        case SOUTH:
            result.y = node.getSize().y + offset;
            break;
        case WEST:
            result.x = -width - offset;
            break;
        default:
            throw new IllegalArgumentException();
        }
        if (vertical) {
            result.y = (positions[largestPos] + positions[largestPos + 1] - height) / 2;
        } else {
            result.x = (positions[largestPos] + positions[largestPos + 1] - width) / 2;
        }
        return result;
    }
    
    /**
     * Create suitable port properties for dummy external ports.
     * 
     * @param graph the graph for which the dummy external port is created
     * @param edge the edge that is split with the external port
     * @return properties to apply to the dummy port
     */
    private static IPropertyHolder getExternalPortProperties(final LGraph graph, final LEdge edge) {
        IPropertyHolder propertyHolder = new MapPropertyHolder();
        float offset = 0;
        LNode parentNode = graph.getProperty(Properties.PARENT_LNODE);
        if (edge.getSource().getNode() != parentNode && edge.getTarget().getNode() != parentNode) {
            offset = graph.getProperty(Properties.OBJ_SPACING)
                    * graph.getProperty(Properties.EDGE_SPACING_FACTOR) / 2;
        }
        propertyHolder.setProperty(Properties.OFFSET, offset);
        return propertyHolder;
    }
    
    /**
     * Determines whether the given child node is a descendant of the parent node.
     * 
     * @param child a child node
     * @param parent a parent node
     * @return true if {@code child} is a direct or indirect child of {@code parent}
     */
    private static boolean isDescendant(final LNode child, final LNode parent) {
        LNode current = child;
        LNode next = current.getGraph().getProperty(Properties.PARENT_LNODE);
        while (next != null) {
            current = next;
            if (current == parent) {
                return true;
            }
            next = current.getGraph().getProperty(Properties.PARENT_LNODE);
        }
        return false;
    }

}
