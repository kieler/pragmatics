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
package de.cau.cs.kieler.klay.layered.importexport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.kiml.UnsupportedGraphException;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.KlayLayered;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement.HashCodeCounter;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.intermediate.PortListSorter;
import de.cau.cs.kieler.klay.layered.intermediate.PortSideProcessor;
import de.cau.cs.kieler.klay.layered.p5edges.OrthogonalRoutingGenerator;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.PortType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * A handler for compound graphs that uses the standard layer-based algorithm as module that is
 * executed once on each hierarchy level. Cross-hierarchy edges are pre-processed by splitting
 * them with external ports.
 *
 * @author msp
 */
public class RecursiveCompoundKGraphHandler {
    
    /** the layout algorithm used for regular layout runs. */
    private KlayLayered klayLayered;
    /** the hash code counter used to create graph elements. */
    private HashCodeCounter hashCodeCounter;
    /** map of generated cross-hierarchy edges. */
    private final Multimap<KEdge, CrossHierarchyEdge> crossHierarchyMap = HashMultimap.create();
    /** map of input layered graphs to output layered graphs of the layer-based algorithm. */
    private final Map<LGraph, LGraph> layeredGraphMap = Maps.newHashMap();
    /** set of external ports that have already been positioned. */
    private final Set<KPort> positionedPorts = Sets.newHashSet();

    /**
     * Create a recursive compound KGraph handler.
     * 
     * @param klayLayered the layer-based algorithm executed on each hierarchy level
     * @param hashCodeCounter the hash code counter for creating new graph elements
     */
    public RecursiveCompoundKGraphHandler(final KlayLayered klayLayered,
            final HashCodeCounter hashCodeCounter) {
        this.klayLayered = klayLayered;
        this.hashCodeCounter = hashCodeCounter;
    }

    /**
     * Layout the whole hierarchy of the given graph.
     * 
     * @param kgraph a graph
     * @param monitor a progress monitor
     */
    public void doLayoutHierarchy(final KNode kgraph, final IKielerProgressMonitor monitor) {
        // perform the standard flat layout on each hierarchy level
        recursiveLayout(kgraph, monitor);
        
        // apply layout to the cross-hierarchy edges
        applyCrossHierarchyLayout(kgraph);
    }
    
    /**
     * An internal representation for external ports. This class is used to pass information
     * gathered on one hierarchy level to the containing hierarchy level. Instances are created
     * whenever a cross-hierarchy edge crosses the hierarchy bounds of a parent node; the instance
     * represents the split point of the edge.
     */
    private static class ExternalPort {
        /** the original edge for which the port is created. */
        private KEdge kedge;
        /** the node whose outer bounds are crossed by the edge. */
        private KNode knode;
        /** the layered graph corresponding to the content of the compound node. */
        private LGraph lgraph;
        /** the dummy node used by the algorithm as representative for the external port. */
        private LNode lnode;
        /** the flow direction: input or output. */
        private PortType type = PortType.UNDEFINED;
        
        /**
         * Create an external port.
         * 
         * @param kedge the original edge for which the port is created
         * @param knode the node whose outer bounds are crossed by the edge
         * @param lgraph the layered graph corresponding to the content of the compound node
         * @param lnode the dummy node used by the algorithm as representative for the external port
         * @param portType the flow direction: input or output
         */
        ExternalPort(final KEdge kedge, final KNode knode, final LGraph lgraph, final LNode lnode,
                final PortType portType) {
            this.kedge = kedge;
            this.knode = knode;
            this.lgraph = lgraph;
            this.lnode = lnode;
            this.type = portType;
        }
    }
    
    /**
     * A segment of a cross-hierarchy edge split at the boundary of a compound node.
     */
    private static class CrossHierarchyEdge {
        /** the edge used in the layered graph to compute a layout. */
        private LEdge ledge;
        /** the layered graph in which the layout was computed. */
        private LGraph lgraph;
        /** the corresponding compound node. */
        private KNode parentNode;
        /** the flow direction: input or output. */
        private PortType type;
        
        /**
         * Create a cross-hierarchy edge segment.
         * 
         * @param ledge the edge used in the layered graph to compute a layout
         * @param lgraph the layered graph in which the layout is computed
         * @param parentNode the corresponding compound node
         * @param type the flow direction: input or output
         */
        CrossHierarchyEdge(final LEdge ledge, final LGraph lgraph, final KNode parentNode,
                final PortType type) {
            this.ledge = ledge;
            this.lgraph = lgraph;
            this.parentNode = parentNode;
            this.type = type;
        }
        
        /**
         * {@inheritDoc}
         */
        public String toString() {
            return type.toString() + ":" + ledge.toString();
        }
    }
    
    /**
     * Perform automatic layout recursively in the given graph.
     * 
     * @param parentNode the parent node of the graph
     * @param monitor a progress monitor
     * @return the external ports created to split edges that cross the boundary of the parent node
     */
    private Collection<ExternalPort> recursiveLayout(final KNode parentNode,
            final IKielerProgressMonitor monitor) {
        monitor.begin("Recursive compound graph layout", parentNode.getChildren().size() + 1);
        
        // perform layout in all children and gather their external ports
        List<ExternalPort> containedExternalPorts = new LinkedList<ExternalPort>();
        for (KNode childNode : parentNode.getChildren()) {
            if (childNode.getChildren().isEmpty()) {
                monitor.worked(1);
            } else {
                // layout the content of the compound node recursively
                Collection<ExternalPort> childPorts = recursiveLayout(childNode, monitor.subTask(1));
                containedExternalPorts.addAll(childPorts);
            }
        }
        
        // import the graph
        KGraphImporter graphImporter = new KGraphImporter(hashCodeCounter);
        LGraph layeredGraph = graphImporter.importGraph(parentNode);
        
        // process the cross-hierarchy edges connected to the inside of the child nodes
        List<ExternalPort> exportedExternalPorts = new LinkedList<ExternalPort>();
        processInsideEdges(parentNode, layeredGraph, graphImporter, exportedExternalPorts,
                containedExternalPorts);
        
        // process the cross-hierarchy edges connected to the outside of the parent node
        processOutsideEdges(parentNode, layeredGraph, graphImporter, exportedExternalPorts);
        
        // assign a position to all unpositioned ports of compound nodes
        Map<KGraphElement, LGraphElement> elementMap = layeredGraph.getProperty(
                KGraphImporter.ELEMENT_MAP);
        for (KNode childNode : parentNode.getChildren()) {
            if (!childNode.getChildren().isEmpty()
                    && childNode.getData(KShapeLayout.class).getProperty(LayoutOptions.PORT_CONSTRAINTS)
                    .isPosFixed()) {
                for (KPort kport : childNode.getPorts()) {
                    if (!positionedPorts.contains(kport)) {
                        LPort lport = (LPort) elementMap.get(kport);
                        PortSideProcessor.setPortSide(lport);
                        LNode lnode = lport.getNode();
                        // remove the port temporarily in order to find a suitable position
                        lport.setNode(null);
                        KVector portPos = calcPortPos(lnode, lport.getSide(), kport);
                        lport.getPosition().x = portPos.x;
                        lport.getPosition().y = portPos.y;
                        lport.setNode(lnode);
                        positionedPorts.add(kport);
                    }
                }
            }
        }

        // perform layer-based layout
        LGraph result = klayLayered.doLayout(layeredGraph, monitor.subTask(1));
        layeredGraphMap.put(layeredGraph, result);
        
        // mark external ports as positioned if they have been included in the layered graph
        for (KPort kport : parentNode.getPorts()) {
            if (elementMap.containsKey(kport)) {
                positionedPorts.add(kport);
            }
        }
        
        // mark compound nodes with free port constraints so the graph importer will update their ports
        for (KNode childNode : parentNode.getChildren()) {
            if (!childNode.getChildren().isEmpty()) {
                childNode.getData(KShapeLayout.class).setProperty(LayoutOptions.PORT_CONSTRAINTS,
                        PortConstraints.FREE);
            }
        }

        // apply the layout results to the original graph
        graphImporter.applyLayout(result);
        
        monitor.done();
        return exportedExternalPorts;
    }
    
    /**
     * Process edges incident to a node in the given graph and crossing its boundary. These
     * edges are split with instances of {@link ExternalPort}. The resulting edge segments are
     * stored as instances of {@link CrossHierarchyEdge} in the {@link #crossHierarchyMap}.
     * 
     * @param parentNode the parent node of the graph
     * @param layeredGraph the corresponding layered graph
     * @param graphImporter the importer used to create the layered graph
     * @param exportedExternalPorts list into which new external ports are put
     */
    private void processOutsideEdges(final KNode parentNode, final LGraph layeredGraph,
            final KGraphImporter graphImporter, final List<ExternalPort> exportedExternalPorts) {
        Direction layoutDirection = layeredGraph.getProperty(LayoutOptions.DIRECTION);
        if (layoutDirection == Direction.UNDEFINED) {
            layoutDirection = Direction.RIGHT;
        }
        Map<KGraphElement, LGraphElement> elementMap = layeredGraph.getProperty(
                KGraphImporter.ELEMENT_MAP);
        for (KNode childNode : parentNode.getChildren()) {
            LNode lnode = (LNode) elementMap.get(childNode);
            
            for (KEdge kedge : childNode.getOutgoingEdges()) {
                KNode targetNode = kedge.getTarget();
                if (!KimlUtil.isDescendant(targetNode, parentNode)
                        && !(targetNode == parentNode && kedge.getTargetPort() != null)) {
                    // the edge goes to the outside of the parent node
                    LEdge newEdge = new LEdge(layeredGraph);
                    KEdgeLayout kedgeLayout = kedge.getData(KEdgeLayout.class);
                    newEdge.copyProperties(kedgeLayout);
                    newEdge.setProperty(LayoutOptions.JUNCTION_POINTS, null);
                    crossHierarchyMap.put(kedge, new CrossHierarchyEdge(newEdge, layeredGraph,
                            parentNode, PortType.OUTPUT));
                    LPort sourcePort = (LPort) elementMap.get(kedge.getSourcePort());
                    if (sourcePort == null) {
                        sourcePort = createFreePort(lnode, PortType.OUTPUT,
                                kedgeLayout.getSourcePoint().createVector(), layeredGraph,
                                layoutDirection);
                    } else if (kedge.getSourcePort().getNode() != childNode) {
                        throw new UnsupportedGraphException("Inconsistent source port reference found.");
                    }
                    newEdge.setSource(sourcePort);
                    LNode dummyNode = ImportUtil.createExternalPortDummy(
                            getExternalPortProperties(parentNode, kedge),
                            PortConstraints.FREE, PortSide.fromDirection(layoutDirection), 1,
                            null, null, new KVector(), layoutDirection, layeredGraph);
                    layeredGraph.getLayerlessNodes().add(dummyNode);
                    layeredGraph.getProperty(Properties.GRAPH_PROPERTIES).add(
                            GraphProperties.EXTERNAL_PORTS);
                    LPort targetPort = dummyNode.getPorts().get(0);
                    newEdge.setTarget(targetPort);
                    ExternalPort externalPort = new ExternalPort(kedge, parentNode, layeredGraph,
                            dummyNode, PortType.OUTPUT);
                    exportedExternalPorts.add(externalPort);
                    
                } else if (lnode.getProperty(LayoutOptions.PORT_CONSTRAINTS).isSideFixed()
                        && kedge.getSourcePort() == null) {
                    LEdge ledge = (LEdge) elementMap.get(kedge);
                    if (ledge != null) {
                        // correct the source position of the normal edge
                        LPort sourcePort = ledge.getSource();
                        sourcePort.setSide(PortSide.fromDirection(layoutDirection));
                        KVector portPos = calcPortPos(lnode, sourcePort.getSide(), null);
                        sourcePort.getPosition().x = portPos.x;
                        sourcePort.getPosition().y = portPos.y;
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
    }
    
    /**
     * Process edges connected to the inside of compound nodes contained in the given graph.
     * 
     * @param parentNode the parent node of the graph
     * @param layeredGraph the corresponding layered graph
     * @param graphImporter the importer used to create the layered graph
     * @param exportedExternalPorts list into which new external ports are put
     * @param containedExternalPorts external ports gathered during the recursive layout of children
     */
    private void processInsideEdges(final KNode parentNode, final LGraph layeredGraph,
            final KGraphImporter graphImporter, final List<ExternalPort> exportedExternalPorts,
            final List<ExternalPort> containedExternalPorts) {
        Direction layoutDirection = layeredGraph.getProperty(LayoutOptions.DIRECTION);
        if (layoutDirection == Direction.UNDEFINED) {
            layoutDirection = Direction.RIGHT;
        }
        Map<KGraphElement, LGraphElement> elementMap = layeredGraph.getProperty(
                KGraphImporter.ELEMENT_MAP);
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
        ArrayList<LPort> al = Lists.newArrayList(node.getPorts(side));
        LPort[] ports = al.toArray(new LPort[al.size()]);
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
     * Apply the computed layout to all cross-hierarchy edges.
     * 
     * @param kgraph a graph
     */
    private void applyCrossHierarchyLayout(final KNode kgraph) {
        for (KEdge kedge : crossHierarchyMap.keySet()) {
            List<CrossHierarchyEdge> crossHierarchyEdges = new ArrayList<CrossHierarchyEdge>(
                    crossHierarchyMap.get(kedge));
            // put the cross-hierarchy edges in proper order from source to target
            Collections.sort(crossHierarchyEdges, new Comparator<CrossHierarchyEdge>() {
                public int compare(final CrossHierarchyEdge edge1, final CrossHierarchyEdge edge2) {
                    if (edge1.type == PortType.OUTPUT && edge2.type == PortType.INPUT) {
                        return -1;
                    } else if (edge1.type == PortType.INPUT && edge2.type == PortType.OUTPUT) {
                        return 1;
                    }
                    int level1 = hierarchyLevel(edge1.parentNode, kgraph);
                    int level2 = hierarchyLevel(edge2.parentNode, kgraph);
                    if (edge1.type == PortType.OUTPUT) {
                        // from deeper level to higher level
                        return level2 - level1;
                    } else {
                        // from higher level to deeper level
                        return level1 - level2;
                    }
                }
            });
            
            // determine the reference node for all bend points
            KNode referenceNode = kedge.getSource();
            if (!KimlUtil.isDescendant(kedge.getTarget(), referenceNode)) {
                referenceNode = referenceNode.getParent();
            }

            // check whether there are any junction points
            KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
            edgeLayout.getBendPoints().clear();
            KVectorChain junctionPoints = edgeLayout.getProperty(LayoutOptions.JUNCTION_POINTS);
            if (Iterables.any(crossHierarchyEdges, new Predicate<CrossHierarchyEdge>() {
                public boolean apply(final CrossHierarchyEdge chEdge) {
                    KVectorChain ledgeJPs = chEdge.ledge.getProperty(LayoutOptions.JUNCTION_POINTS);
                    return ledgeJPs != null && !ledgeJPs.isEmpty();
                }
            })) {
                if (junctionPoints == null) {
                    junctionPoints = new KVectorChain();
                    edgeLayout.setProperty(LayoutOptions.JUNCTION_POINTS, junctionPoints);
                } else {
                    junctionPoints.clear();
                }
            } else if (junctionPoints != null) {
                edgeLayout.setProperty(LayoutOptions.JUNCTION_POINTS, null);
            }
            
            // apply the computed layouts to the cross-hierarchy edge
            KVector lastPoint = null;
            for (CrossHierarchyEdge chEdge : crossHierarchyEdges) {
                LGraph layeredGraph = layeredGraphMap.get(chEdge.lgraph);
                KVector offset = new KVector(layeredGraph.getOffset());
                KimlUtil.toAbsolute(offset, chEdge.parentNode);
                KimlUtil.toRelative(offset, referenceNode);
                KVectorChain bendPoints = chEdge.ledge.getBendPoints().translate(offset);
                // Note: if an NPE occurs here, that means KLay Layered has replaced the original edge
                KVector sourcePoint = chEdge.ledge.getSource().getAbsoluteAnchor().add(offset);
                KVector targetPoint = chEdge.ledge.getTarget().getAbsoluteAnchor().add(offset);

                if (chEdge.ledge.getSource().getNode().getProperty(Properties.ORIGIN)
                        == kedge.getSource()
                        || chEdge.parentNode == kedge.getSource()) {
                    edgeLayout.getSourcePoint().applyVector(sourcePoint);
                } else if (lastPoint != null) {
                    KVector nextPoint = targetPoint;
                    if (!bendPoints.isEmpty()) {
                        nextPoint = bendPoints.getFirst();
                    }
                    if (Math.abs(lastPoint.x - nextPoint.x) > OrthogonalRoutingGenerator.TOLERANCE
                        && Math.abs(lastPoint.y - nextPoint.y) > OrthogonalRoutingGenerator.TOLERANCE) {
                        // add the source point as bend point to properly connect the hierarchy levels
                        KPoint bendPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                        bendPoint.applyVector(sourcePoint);
                        edgeLayout.getBendPoints().add(bendPoint);
                    }
                }
                
                for (KVector point : bendPoints) {
                    KPoint bendPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
                    bendPoint.applyVector(point);
                    edgeLayout.getBendPoints().add(bendPoint);
                }
                
                if (chEdge.ledge.getTarget().getNode().getProperty(Properties.ORIGIN)
                        == kedge.getTarget()
                        || chEdge.parentNode == kedge.getTarget()) {
                    edgeLayout.getTargetPoint().applyVector(targetPoint);
                }
                if (bendPoints.isEmpty()) {
                    lastPoint = sourcePoint;
                } else {
                    lastPoint = bendPoints.getLast();
                }
                
                // copy junction points
                KVectorChain ledgeJPs = chEdge.ledge.getProperty(LayoutOptions.JUNCTION_POINTS);
                if (ledgeJPs != null) {
                    junctionPoints.addAll(ledgeJPs.translate(offset));
                }
            }
        }
    }
    
    /**
     * Compute the hierarchy level of the given node.
     * 
     * @param node a node
     * @param kgraph the containing hierarchical graph
     * @return the hierarchy level (higher number means the node is nested deeper)
     */
    private static int hierarchyLevel(final KNode node, final KNode kgraph) {
        KNode current = node;
        int level = 0;
        while (current != null) {
            if (current == kgraph) {
                return level;
            }
            current = current.getParent();
            level++;
        }
        // the given node is not an ancestor of the graph node
        throw new IllegalArgumentException();
    }
    
    /**
     * Create suitable port properties for dummy external ports.
     * 
     * @param node the node for which the dummy external port is created
     * @param edge the edge that is split with the external port
     * @return properties to apply to the dummy port
     */
    private static IPropertyHolder getExternalPortProperties(final KNode node, final KEdge edge) {
        KShapeLayout parentLayout = node.getParent().getData(KShapeLayout.class);
        IPropertyHolder propertyHolder = new MapPropertyHolder();
        float offset = 0;
        if (edge.getSource() != node && edge.getTarget() != node) {
            offset = parentLayout.getProperty(Properties.OBJ_SPACING)
                    * parentLayout.getProperty(Properties.EDGE_SPACING_FACTOR) / 2;
        }
        propertyHolder.setProperty(Properties.OFFSET, offset);
        return propertyHolder;
    }

}
