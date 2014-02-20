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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
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
         * @param dummyNode the dummy node used by the algorithm as representative for the external port
         * @param portType the flow direction: input or output
         */
        ExternalPort(final LEdge origEdge, final LEdge newEdge, final LNode parentNode,
                final LNode dummyNode, final PortType portType) {
            this.origEdge = origEdge;
            this.newEdge = newEdge;
            this.parentNode = parentNode;
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
        transformHierarchyEdges(graph, null);
        
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
     * @param graph the layered graph
     * @param parentNode the parent node of the graph, or {@code null} if it is on top-level
     * @param monitor a progress monitor
     * @return the external ports created to split edges that cross the boundary of the parent node
     */
    private Collection<ExternalPort> transformHierarchyEdges(final LGraph graph,
            final LNode parentNode) {
        // process all children and gather their external ports
        List<ExternalPort> containedExternalPorts = new LinkedList<ExternalPort>();
        for (LNode node : graph.getLayerlessNodes()) {
            LGraph nestedGraph = node.getProperty(Properties.NESTED_LGRAPH);
            if (nestedGraph != null) {
                Collection<ExternalPort> childPorts = transformHierarchyEdges(nestedGraph, node);
                containedExternalPorts.addAll(childPorts);
            }
        }
        
        // process the cross-hierarchy edges connected to the inside of the child nodes
        List<ExternalPort> exportedExternalPorts = new LinkedList<ExternalPort>();
        processInsideEdges(graph, parentNode, exportedExternalPorts, containedExternalPorts);
        
        // process the cross-hierarchy edges connected to the outside of the parent node
        if (parentNode != null) {
            processOutsideEdges(graph, parentNode, exportedExternalPorts);
        }
        
        return exportedExternalPorts;
    }
    
    /**
     * Process edges connected to the inside of compound nodes contained in the given graph.
     * 
     * @param graph the processed graph
     * @param parentNode the parent node of the nested graph, or {@code null} if it is on top-level
     * @param exportedExternalPorts list into which new external ports are put
     * @param containedExternalPorts external ports gathered during the recursive layout of children
     */
    private void processInsideEdges(final LGraph graph, final LNode parentNode,
            final List<ExternalPort> exportedExternalPorts,
            final List<ExternalPort> containedExternalPorts) {
        Direction layoutDirection = graph.getProperty(LayoutOptions.DIRECTION);
        
        for (ExternalPort externalPort : containedExternalPorts) {
            LNode sourceNode = externalPort.origEdge.getSource().getNode();
            LNode targetNode = externalPort.origEdge.getTarget().getNode();
            if (externalPort.type == PortType.INPUT && sourceNode.getGraph() != graph
                    && (parentNode == null || ImportUtil.isDescendant(sourceNode, parentNode))) {
                // the edge comes from the inside of another sibling node,
                // hence we want to process it only once, namely as outgoing edge
                continue;
            }
            
            // create a dummy port matching the external port dummy node
            LPort newPort = new LPort(graph);
            newPort.setNode(externalPort.parentNode);
            switch (externalPort.type) {
            case INPUT:
                newPort.setSide(PortSide.fromDirection(layoutDirection).opposed());
                break;
            case OUTPUT:
                newPort.setSide(PortSide.fromDirection(layoutDirection));
                break;
            }
            externalPort.dummyNode.setProperty(Properties.ORIGIN, newPort);
            
            // create a new dummy edge for the next segment of the cross-hierarchy edge
            LEdge newEdge = new LEdge(graph);
            newEdge.copyProperties(externalPort.origEdge);
            newEdge.setProperty(LayoutOptions.JUNCTION_POINTS, null);
            crossHierarchyMap.put(externalPort.origEdge, new CrossHierarchyEdge(newEdge,
                    graph, externalPort.type));
            
            if (externalPort.type == PortType.OUTPUT) {
                newEdge.setSource(newPort);
                LPort targetPort = null;
                if (targetNode.getGraph() == graph) {
                    // the edge goes to a direct child of the parent node
                    targetPort = externalPort.origEdge.getTarget();
                } else if (parentNode == null || ImportUtil.isDescendant(targetNode, parentNode)) {
                    // the edge goes to the inside of another sibling node
                    ExternalPort targetExtenalPort = null;
                    for (ExternalPort externalPort2 : containedExternalPorts) {
                        if (externalPort2 != externalPort
                                && externalPort2.origEdge == externalPort.origEdge) {
                            targetExtenalPort = externalPort2;
                            break;
                        }
                    }
                    assert targetExtenalPort.type == PortType.INPUT;
                    targetPort = new LPort(graph);
                    targetPort.setNode(targetExtenalPort.parentNode);
                    targetPort.setSide(PortSide.fromDirection(layoutDirection).opposed());
                    targetExtenalPort.dummyNode.setProperty(Properties.ORIGIN, targetPort);
                } else if (targetNode == parentNode) {
                    // the edge goes to a port of the parent node
                    LPort edgeTarget = externalPort.origEdge.getTarget();
                    LNode dummyNode = ImportUtil.createExternalPortDummy(edgeTarget,
                            PortConstraints.FREE, PortSide.fromDirection(layoutDirection),
                            1, null, null, edgeTarget.getSize(), layoutDirection, graph);
                    dummyNode.setProperty(Properties.ORIGIN, edgeTarget);
                    graph.getLayerlessNodes().add(dummyNode);
                    graph.getProperty(Properties.GRAPH_PROPERTIES).add(GraphProperties.EXTERNAL_PORTS);
                    targetPort = dummyNode.getPorts().get(0);
                } else {
                    // the edge goes to the outside of the parent node
                    LNode dummyNode = ImportUtil.createExternalPortDummy(
                            getExternalPortProperties(graph, externalPort.origEdge),
                            PortConstraints.FREE, PortSide.fromDirection(layoutDirection),
                            1, null, null, new KVector(), layoutDirection, graph);
                    graph.getLayerlessNodes().add(dummyNode);
                    graph.getProperty(Properties.GRAPH_PROPERTIES).add(GraphProperties.EXTERNAL_PORTS);
                    targetPort = dummyNode.getPorts().get(0);
                    exportedExternalPorts.add(new ExternalPort(externalPort.origEdge, newEdge,
                            parentNode, dummyNode, PortType.OUTPUT));
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
                            PortConstraints.FREE, PortSide.fromDirection(layoutDirection).opposed(),
                            -1, null, null, edgeSource.getSize(), layoutDirection, graph);
                    dummyNode.setProperty(Properties.ORIGIN, edgeSource);
                    graph.getLayerlessNodes().add(dummyNode);
                    graph.getProperty(Properties.GRAPH_PROPERTIES).add(GraphProperties.EXTERNAL_PORTS);
                    sourcePort = dummyNode.getPorts().get(0);
                } else {
                    // the edge comes from the outside of the parent node
                    LNode dummyNode = ImportUtil.createExternalPortDummy(
                            getExternalPortProperties(graph, externalPort.origEdge),
                            PortConstraints.FREE, PortSide.fromDirection(layoutDirection).opposed(),
                            -1, null, null, new KVector(), layoutDirection, graph);
                    graph.getLayerlessNodes().add(dummyNode);
                    graph.getProperty(Properties.GRAPH_PROPERTIES).add(GraphProperties.EXTERNAL_PORTS);
                    sourcePort = dummyNode.getPorts().get(0);
                    exportedExternalPorts.add(new ExternalPort(externalPort.origEdge, newEdge,
                            parentNode, dummyNode, PortType.INPUT));
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
     * @param graph the processed graph
     * @param parentNode the parent node of the nested graph
     * @param exportedExternalPorts list into which new external ports are put
     */
    private void processOutsideEdges(final LGraph graph, final LNode parentNode,
            final List<ExternalPort> exportedExternalPorts) {
        Direction layoutDirection = graph.getProperty(LayoutOptions.DIRECTION);
        
        List<ExternalPort> externalOutputPorts = new LinkedList<ExternalPort>();
        List<ExternalPort> externalInputPorts = new LinkedList<ExternalPort>();
        for (LNode childNode : graph.getLayerlessNodes()) {
            for (LEdge origEdge : childNode.getOutgoingEdges()) {
                LPort targetPort = origEdge.getTarget();
                if (!ImportUtil.isDescendant(targetPort.getNode(), parentNode)) {
                    // the edge goes to the outside of the parent node
                    LEdge newEdge = new LEdge(graph);
                    newEdge.copyProperties(origEdge);
                    newEdge.setProperty(LayoutOptions.JUNCTION_POINTS, null);
                    crossHierarchyMap.put(origEdge, new CrossHierarchyEdge(newEdge, graph,
                            PortType.OUTPUT));
                    LNode dummyNode;
                    if (targetPort.getNode() == parentNode) {
                        dummyNode = ImportUtil.createExternalPortDummy(targetPort,
                                PortConstraints.FREE, PortSide.fromDirection(layoutDirection), 1,
                                null, null, targetPort.getSize(), layoutDirection, graph);
                        dummyNode.setProperty(Properties.ORIGIN, targetPort);
                    } else {
                        dummyNode = ImportUtil.createExternalPortDummy(
                                getExternalPortProperties(graph, origEdge),
                                PortConstraints.FREE, PortSide.fromDirection(layoutDirection), 1,
                                null, null, new KVector(), layoutDirection, graph);
                    }
                    newEdge.setTarget(dummyNode.getPorts().get(0));
                    externalOutputPorts.add(new ExternalPort(origEdge, newEdge, parentNode,
                            dummyNode, PortType.OUTPUT));
                }
            }
            
            for (LEdge origEdge : childNode.getIncomingEdges()) {
                LPort sourcePort = origEdge.getSource();
                if (!ImportUtil.isDescendant(sourcePort.getNode(), parentNode)) {
                    // the edge comes from the outside of the parent node
                    LEdge newEdge = new LEdge(graph);
                    newEdge.copyProperties(origEdge);
                    newEdge.setProperty(LayoutOptions.JUNCTION_POINTS, null);
                    crossHierarchyMap.put(origEdge, new CrossHierarchyEdge(newEdge, graph,
                            PortType.INPUT));
                    LNode dummyNode;
                    if (sourcePort.getNode() == parentNode) {
                        dummyNode = ImportUtil.createExternalPortDummy(sourcePort,
                                PortConstraints.FREE, PortSide.fromDirection(layoutDirection).opposed(),
                                -1, null, null, sourcePort.getSize(), layoutDirection, graph);
                        dummyNode.setProperty(Properties.ORIGIN, sourcePort);
                    } else {
                        dummyNode = ImportUtil.createExternalPortDummy(
                                getExternalPortProperties(graph, origEdge),
                                PortConstraints.FREE, PortSide.fromDirection(layoutDirection).opposed(),
                                -1, null, null, new KVector(), layoutDirection, graph);
                    }
                    newEdge.setSource(dummyNode.getPorts().get(0));
                    externalInputPorts.add(new ExternalPort(origEdge, newEdge, parentNode,
                            dummyNode, PortType.INPUT));
                }
            }
        }
        
        // do some further adaptations outside of the above loop to avoid CMEs
        for (ExternalPort externalPort : externalOutputPorts) {
            graph.getLayerlessNodes().add(externalPort.dummyNode);
            externalPort.newEdge.setSource(externalPort.origEdge.getSource());
            if (externalPort.origEdge.getTarget().getNode() != parentNode) {
                exportedExternalPorts.add(externalPort);
            }
        }
        for (ExternalPort externalPort : externalInputPorts) {
            graph.getLayerlessNodes().add(externalPort.dummyNode);
            externalPort.newEdge.setTarget(externalPort.origEdge.getTarget());
            if (externalPort.origEdge.getSource().getNode() != parentNode) {
                exportedExternalPorts.add(externalPort);
            }
        }
        
        // update some properties of the graph
        if (externalOutputPorts.size() + externalInputPorts.size() > 0) {
            graph.getProperty(Properties.GRAPH_PROPERTIES).add(GraphProperties.EXTERNAL_PORTS);
            PortConstraints portConstraints = graph.getProperty(LayoutOptions.PORT_CONSTRAINTS);
            if (portConstraints.isSideFixed()) {
                portConstraints = PortConstraints.FIXED_SIDE;
            } else {
                portConstraints = PortConstraints.FREE;
            }
            graph.setProperty(LayoutOptions.PORT_CONSTRAINTS, portConstraints);
        }
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
