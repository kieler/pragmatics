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
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.importexport.ImportUtil;
import de.cau.cs.kieler.klay.layered.intermediate.PortListSorter;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.PortType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Preprocess a compound graph by splitting cross-hierarchy edges. The result is stored in
 * {@link Properties#CROSS_HIERARCHY_MAP}, which is attached to the top-level graph.
 * 
 * <dl>
 *   <dt>Precondition:</dt>
 *     <dd>a compound graph with no layers.</dd>
 *   <dt>Postcondition:</dt>
 *     <dd>a compound graph with no layers and no cross-hierarchy edges, but with external ports.</dd>
 * </dl>
 *
 * @author msp
 */
public class CompoundGraphPreprocessor implements ILayoutProcessor {
    
    /** map of generated cross-hierarchy edges. */
    private Multimap<LEdge, CrossHierarchyEdge> crossHierarchyMap;

    /**
     * An internal representation for external ports. This class is used to pass information
     * gathered on one hierarchy level to the containing hierarchy level. Instances are created
     * whenever a cross-hierarchy edge crosses the hierarchy bounds of a parent node; the instance
     * represents the split point of the edge.
     */
    private static class ExternalPort {
        /** the original edge for which the port is created. */
        private LEdge origEdge;
        /** the new edge by which the original edge is replaced. */
        private LEdge newEdge;
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
         * @param origEdge the original edge for which the port is created
         * @param newEdge the new edge by which the original edge is replaced
         * @param parentNode the node whose outer bounds are crossed by the edge
         * @param lgraph the layered graph corresponding to the content of the compound node
         * @param dummyNode the dummy node used by the algorithm as representative for the external port
         * @param portType the flow direction: input or output
         */
        ExternalPort(final LEdge origEdge, final LEdge newEdge, final LNode parentNode,
                final LGraph lgraph, final LNode dummyNode, final PortType portType) {
            this.origEdge = origEdge;
            this.newEdge = newEdge;
            this.parentNode = parentNode;
            this.lgraph = lgraph;
            this.dummyNode = dummyNode;
            this.type = portType;
        }

    }
    
    /**
     * {@inheritDoc}
     */
    public void process(final LGraph graph, final IKielerProgressMonitor monitor) {
        monitor.begin("Compound graph preprocessor", 1);
        crossHierarchyMap = HashMultimap.create();
        
        // create new dummy edges at hierarchy bounds
        transformHierarchyEdges(null, graph);
        
        // remove the original edges from the graph
        for (LEdge origEdge : crossHierarchyMap.keySet()) {
            origEdge.setSource(null);
            origEdge.setTarget(null);
        }
        
        graph.setProperty(Properties.CROSS_HIERARCHY_MAP, crossHierarchyMap);
        crossHierarchyMap = null;
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
        processInsideEdges(parentNode, graph, exportedExternalPorts, containedExternalPorts);
        
        // process the cross-hierarchy edges connected to the outside of the parent node
        processOutsideEdges(parentNode, graph, exportedExternalPorts);
        
        return exportedExternalPorts;
    }
    
    /**
     * Process edges connected to the inside of compound nodes contained in the given graph.
     * 
     * @param parentNode the parent node of the nested graph
     * @param graph the processed graph
     * @param exportedExternalPorts list into which new external ports are put
     * @param containedExternalPorts external ports gathered during the recursive layout of children
     */
    private void processInsideEdges(final LNode parentNode, final LGraph graph,
            final List<ExternalPort> exportedExternalPorts,
            final List<ExternalPort> containedExternalPorts) {
        Direction layoutDirection = graph.getProperty(LayoutOptions.DIRECTION);
        for (ExternalPort externalPort : containedExternalPorts) {
            LNode sourceNode = externalPort.origEdge.getSource().getNode();
            LNode targetNode = externalPort.origEdge.getTarget().getNode();
            if (externalPort.type == PortType.INPUT && ImportUtil.isDescendant(sourceNode, parentNode)
                    && sourceNode.getGraph() != graph) {
                // the edge comes from the inside of another sibling node,
                // hence we want to process it only once, namely as outgoing edge
                continue;
            }
            
            KVector newPortPos = ImportUtil.getExternalPortPosition(
                    externalPort.lgraph, externalPort.dummyNode, 0, 0);
            LPort newPort = createFixedPort(externalPort.parentNode, newPortPos,
                    externalPort.dummyNode.getProperty(Properties.EXT_PORT_SIDE), graph);
            externalPort.dummyNode.setProperty(Properties.ORIGIN, newPort);
            LEdge newEdge = new LEdge(graph);
            newEdge.copyProperties(externalPort.origEdge);
            newEdge.setProperty(LayoutOptions.JUNCTION_POINTS, null);
            crossHierarchyMap.put(externalPort.origEdge, new CrossHierarchyEdge(newEdge,
                    graph, parentNode, externalPort.type));
            
            if (externalPort.type == PortType.OUTPUT) {
                newEdge.setSource(newPort);
                LPort targetPort = null;
                if (targetNode.getGraph() == graph) {
                    // the edge goes to a direct child of the parent node
                    targetPort = externalPort.origEdge.getTarget();
                } else if (ImportUtil.isDescendant(targetNode, parentNode)) {
                    // the edge goes to the inside of another sibling node
                    ExternalPort targetExtenalPort = null;
                    for (ExternalPort externalPort2 : containedExternalPorts) {
                        if (externalPort2 != externalPort
                                && externalPort2.origEdge == externalPort.origEdge) {
                            targetExtenalPort = externalPort2;
                            break;
                        }
                    }
                    KVector targetPortPos = ImportUtil.getExternalPortPosition(
                            targetExtenalPort.lgraph, targetExtenalPort.dummyNode, 0, 0);
                    targetPort = createFixedPort(targetExtenalPort.parentNode, targetPortPos,
                            targetExtenalPort.dummyNode.getProperty(Properties.EXT_PORT_SIDE),
                            graph);
                    targetExtenalPort.dummyNode.setProperty(Properties.ORIGIN, targetPort);
                } else if (targetNode == parentNode) {
                    // the edge goes to a port of the parent node
                    LPort edgeTarget = externalPort.origEdge.getTarget();
                    LNode dummyNode = ImportUtil.createExternalPortDummy(edgeTarget,
                            PortConstraints.FIXED_SIDE, PortSide.fromDirection(layoutDirection),
                            1, null, null, edgeTarget.getSize(), layoutDirection, graph);
                    dummyNode.setProperty(Properties.ORIGIN, edgeTarget);
                    graph.getLayerlessNodes().add(dummyNode);
                    graph.getProperty(Properties.GRAPH_PROPERTIES).add(GraphProperties.EXTERNAL_PORTS);
                    targetPort = dummyNode.getPorts().get(0);
                } else {
                    // the edge goes to the outside of the parent node
                    LNode dummyNode = ImportUtil.createExternalPortDummy(
                            getExternalPortProperties(graph, externalPort.origEdge),
                            PortConstraints.FIXED_SIDE, PortSide.fromDirection(layoutDirection),
                            1, null, null, new KVector(), layoutDirection, graph);
                    graph.getLayerlessNodes().add(dummyNode);
                    graph.getProperty(Properties.GRAPH_PROPERTIES).add(GraphProperties.EXTERNAL_PORTS);
                    targetPort = dummyNode.getPorts().get(0);
                    exportedExternalPorts.add(new ExternalPort(externalPort.origEdge, newEdge,
                            parentNode, graph, dummyNode, PortType.OUTPUT));
                }
                newEdge.setTarget(targetPort);
                
            } else if (externalPort.type == PortType.INPUT) {
                newEdge.setTarget(newPort);
                LPort sourcePort = null;
                if (sourceNode.getGraph() == graph) {
                    // the edge comes from a direct child of the parent node
                    sourcePort = externalPort.origEdge.getSource();
                } else if (sourceNode == parentNode) {
                    // the edge comes from a port of the parent node
                    LPort edgeSource = externalPort.origEdge.getSource();
                    LNode dummyNode = ImportUtil.createExternalPortDummy(edgeSource,
                            PortConstraints.FIXED_SIDE,
                            PortSide.fromDirection(layoutDirection).opposed(), -1, null, null,
                            edgeSource.getSize(), layoutDirection, graph);
                    dummyNode.setProperty(Properties.ORIGIN, edgeSource);
                    graph.getLayerlessNodes().add(dummyNode);
                    graph.getProperty(Properties.GRAPH_PROPERTIES).add(GraphProperties.EXTERNAL_PORTS);
                    sourcePort = dummyNode.getPorts().get(0);
                } else {
                    // the edge comes from the outside of the parent node
                    LNode dummyNode = ImportUtil.createExternalPortDummy(
                            getExternalPortProperties(graph, externalPort.origEdge),
                            PortConstraints.FIXED_SIDE,
                            PortSide.fromDirection(layoutDirection).opposed(), -1, null, null,
                            new KVector(), layoutDirection, graph);
                    graph.getLayerlessNodes().add(dummyNode);
                    graph.getProperty(Properties.GRAPH_PROPERTIES).add(GraphProperties.EXTERNAL_PORTS);
                    sourcePort = dummyNode.getPorts().get(0);
                    exportedExternalPorts.add(new ExternalPort(externalPort.origEdge, newEdge,
                            parentNode, graph, dummyNode, PortType.INPUT));
                }
                newEdge.setSource(sourcePort);
            }
        }
    }
    
    /**
     * Process edges incident to a node in the given graph and crossing its boundary. These
     * edges are split with instances of {@link ExternalPort}. The resulting edge segments are
     * stored as instances of {@link CrossHierarchyEdge} in the {@link #crossHierarchyMap}.
     * 
     * @param parentNode the parent node of the nested graph
     * @param graph the processed graph
     * @param exportedExternalPorts list into which new external ports are put
     */
    private void processOutsideEdges(final LNode parentNode, final LGraph graph,
            final List<ExternalPort> exportedExternalPorts) {
        Direction layoutDirection = graph.getProperty(LayoutOptions.DIRECTION);
        List<ExternalPort> externalOutputPorts = new LinkedList<ExternalPort>();
        List<ExternalPort> externalInputPorts = new LinkedList<ExternalPort>();
        for (LNode childNode : graph.getLayerlessNodes()) {
            for (LEdge origEdge : childNode.getOutgoingEdges()) {
                if (!ImportUtil.isDescendant(origEdge.getTarget().getNode(), parentNode)) {
                    // the edge goes to the outside of the parent node
                    LEdge newEdge = new LEdge(graph);
                    newEdge.copyProperties(origEdge);
                    newEdge.setProperty(LayoutOptions.JUNCTION_POINTS, null);
                    crossHierarchyMap.put(origEdge, new CrossHierarchyEdge(newEdge, graph, parentNode,
                            PortType.OUTPUT));
                    LNode dummyNode = ImportUtil.createExternalPortDummy(
                            getExternalPortProperties(graph, origEdge),
                            PortConstraints.FREE, PortSide.fromDirection(layoutDirection), 1,
                            null, null, new KVector(), layoutDirection, graph);
                    newEdge.setTarget(dummyNode.getPorts().get(0));
                    externalOutputPorts.add(new ExternalPort(origEdge, newEdge, parentNode, graph,
                            dummyNode, PortType.OUTPUT));
                }
            }
            
            for (LEdge origEdge : childNode.getIncomingEdges()) {
                if (!ImportUtil.isDescendant(origEdge.getSource().getNode(), parentNode)) {
                    // the edge comes from the outside of the parent node
                    LEdge newEdge = new LEdge(graph);
                    newEdge.copyProperties(origEdge);
                    newEdge.setProperty(LayoutOptions.JUNCTION_POINTS, null);
                    crossHierarchyMap.put(origEdge, new CrossHierarchyEdge(newEdge,  graph, parentNode,
                            PortType.INPUT));
                    LNode dummyNode = ImportUtil.createExternalPortDummy(
                            getExternalPortProperties(graph, origEdge),
                            PortConstraints.FREE, PortSide.fromDirection(layoutDirection).opposed(),
                            -1, null, null, new KVector(), layoutDirection, graph);
                    newEdge.setSource(dummyNode.getPorts().get(0));
                    externalInputPorts.add(new ExternalPort(origEdge, newEdge, parentNode, graph,
                            dummyNode, PortType.INPUT));
                }
            }
        }
        
        for (ExternalPort externalPort : externalOutputPorts) {
            graph.getLayerlessNodes().add(externalPort.dummyNode);
            externalPort.newEdge.setSource(externalPort.origEdge.getSource());
            if (externalPort.origEdge.getTarget().getNode() == parentNode) {
                externalPort.dummyNode.setProperty(Properties.ORIGIN,
                        externalPort.origEdge.getTarget());
            } else {
                exportedExternalPorts.add(externalPort);
            }
        }
        for (ExternalPort externalPort : externalInputPorts) {
            graph.getLayerlessNodes().add(externalPort.dummyNode);
            externalPort.newEdge.setTarget(externalPort.origEdge.getTarget());
            if (externalPort.origEdge.getSource().getNode() == parentNode) {
                externalPort.dummyNode.setProperty(Properties.ORIGIN,
                        externalPort.origEdge.getSource());
            } else {
                exportedExternalPorts.add(externalPort);
            }
        }
        if (externalOutputPorts.size() + externalInputPorts.size() > 0) {
            graph.getProperty(Properties.GRAPH_PROPERTIES).add(GraphProperties.EXTERNAL_PORTS);
        }
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

}
