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

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
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
import de.cau.cs.kieler.klay.layered.properties.PortType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 *
 * @author msp
 */
public class RecursiveCompoundKGraphHandler {
    
    /** the layout algorithm used for regular layout runs. */
    private KlayLayered klayLayered;
    /** the hash code counter used to create graph elements. */
    private HashCodeCounter hashCodeCounter;
    /** map of edges that cross hierarchy borders. */
    private final Map<KEdge, ExternalPort> crossHierarchyEdges = new HashMap<KEdge, ExternalPort>();
    /** property holder for external port dummies. */
    private final IPropertyHolder externalPortDummyProperties = new MapPropertyHolder();
    
    public RecursiveCompoundKGraphHandler(final KlayLayered klayLayered,
            final HashCodeCounter hashCodeCounter) {
        this.klayLayered = klayLayered;
        this.hashCodeCounter = hashCodeCounter;
        externalPortDummyProperties.setProperty(LayoutOptions.OFFSET, 0.0f);
    }

    public void doLayoutHierarchy(final KNode kgraph, final IKielerProgressMonitor monitor) {
        // perform the standard flat layout on each hierarchy level
        recursiveLayout(kgraph, monitor);
        
        // TODO handle the cross-hierarchy edges
    }
    
    private static class ExternalPort {
        private KEdge kedge;
        private KNode knode;
        private LGraph lgraph;
        private LNode lnode;
        private PortType type = PortType.UNDEFINED;
        ExternalPort(final KEdge kedge, final KNode knode, final LGraph lgraph, final LNode lnode,
                final PortType portType) {
            this.kedge = kedge;
            this.knode = knode;
            this.lgraph = lgraph;
            this.lnode = lnode;
            this.type = portType;
        }
    }
    
    private Collection<ExternalPort> recursiveLayout(final KNode parentNode,
            final IKielerProgressMonitor monitor) {
        monitor.begin("Recursive compound graph layout", parentNode.getChildren().size() + 1);
        
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
        
        // process the cross-hierarchy edges connected to the outside of the parent node
        List<ExternalPort> exportedExternalPorts = new LinkedList<ExternalPort>();
        processOutsideEdges(parentNode, layeredGraph, graphImporter, exportedExternalPorts);

        // process the cross-hierarchy edges connected to the inside of the child nodes
        processInsideEdges(parentNode, layeredGraph, graphImporter, exportedExternalPorts,
                containedExternalPorts);
        
        // perform layer-based layout
        LGraph result = klayLayered.doLayout(layeredGraph, monitor.subTask(1));

        // apply the layout results to the original graph
        graphImporter.applyLayout(result);
        
        monitor.done();
        return exportedExternalPorts;
    }
    
    private void processOutsideEdges(final KNode parentNode, final LGraph layeredGraph,
            final KGraphImporter graphImporter, final List<ExternalPort> exportedExternalPorts) {
        Direction layoutDirection = layeredGraph.getProperty(LayoutOptions.DIRECTION);
        Map<KGraphElement, LGraphElement> elementMap = layeredGraph.getProperty(Properties.ELEMENT_MAP);
        for (KNode childNode : parentNode.getChildren()) {
            LNode lnode = (LNode) elementMap.get(childNode);
            
            for (KEdge kedge : childNode.getOutgoingEdges()) {
                KNode targetNode = kedge.getTarget();
                if (!KimlUtil.isDescendant(targetNode, parentNode)) {
                    // the edge goes to the outside of the parent node
                    LEdge newEdge = new LEdge(layeredGraph);
                    KEdgeLayout kedgeLayout = kedge.getData(KEdgeLayout.class);
                    newEdge.copyProperties(kedgeLayout);
                    LPort sourcePort;
                    if (kedge.getSourcePort() == null) {
                        sourcePort = graphImporter.createPort(lnode,
                                kedgeLayout.getSourcePoint().createVector(), PortType.OUTPUT);
                    } else {
                        sourcePort = (LPort) elementMap.get(kedge.getSourcePort());
                    }
                    newEdge.setSource(sourcePort);
                    LNode dummyNode = graphImporter.createExternalPortDummy(externalPortDummyProperties,
                            PortConstraints.FREE, PortSide.EAST, 1, null, null, new KVector(),
                            layoutDirection);
                    layeredGraph.getLayerlessNodes().add(dummyNode);
                    LPort targetPort = dummyNode.getPorts().get(0);
                    newEdge.setTarget(targetPort);
                    ExternalPort externalPort = new ExternalPort(kedge, parentNode, layeredGraph,
                            dummyNode, PortType.OUTPUT);
                    exportedExternalPorts.add(externalPort);
                }
            }
            
            for (KEdge kedge : childNode.getIncomingEdges()) {
                KNode sourceNode = kedge.getSource();
                if (!KimlUtil.isDescendant(sourceNode, parentNode)) {
                    // the edge comes from the outside of the parent node
                    LEdge newEdge = new LEdge(layeredGraph);
                    KEdgeLayout kedgeLayout = kedge.getData(KEdgeLayout.class);
                    newEdge.copyProperties(kedgeLayout);
                    LPort targetPort;
                    if (kedge.getTargetPort() == null) {
                        targetPort = graphImporter.createPort(lnode,
                                kedgeLayout.getTargetPoint().createVector(), PortType.INPUT);
                    } else {
                        targetPort = (LPort) elementMap.get(kedge.getTargetPort());
                    }
                    newEdge.setTarget(targetPort);
                    LNode dummyNode = graphImporter.createExternalPortDummy(externalPortDummyProperties,
                            PortConstraints.FREE, PortSide.WEST, -1, null, null, new KVector(),
                            layoutDirection);
                    layeredGraph.getLayerlessNodes().add(dummyNode);
                    LPort sourcePort = dummyNode.getPorts().get(0);
                    newEdge.setSource(sourcePort);
                    ExternalPort externalPort = new ExternalPort(kedge, parentNode, layeredGraph,
                            dummyNode, PortType.INPUT);
                    exportedExternalPorts.add(externalPort);
                }
            }
        }
    }
    
    private void processInsideEdges(final KNode parentNode, final LGraph layeredGraph,
            final KGraphImporter graphImporter, final List<ExternalPort> exportedExternalPorts,
            final List<ExternalPort> containedExternalPorts) {
        Direction layoutDirection = layeredGraph.getProperty(LayoutOptions.DIRECTION);
        Map<KGraphElement, LGraphElement> elementMap = layeredGraph.getProperty(Properties.ELEMENT_MAP);
        for (ExternalPort externalPort : containedExternalPorts) {
            LNode lnode = (LNode) elementMap.get(externalPort.knode);
            LPort lport = graphImporter.createPort(lnode, graphImporter.getExternalPortPosition(
                    externalPort.lgraph, externalPort.lnode, 0, 0),
                    externalPort.type);
            KNode sourceKNode = externalPort.kedge.getSource();
            KNode targetKNode = externalPort.kedge.getTarget();
            if (externalPort.knode != targetKNode && externalPort.knode != sourceKNode) {
                LEdge newEdge = new LEdge(layeredGraph);
                KEdgeLayout kedgeLayout = externalPort.kedge.getData(KEdgeLayout.class);
                newEdge.copyProperties(kedgeLayout);
                
                if (externalPort.type == PortType.OUTPUT) {
                    newEdge.setSource(lport);
                    LPort targetLPort;
                    if (KimlUtil.isDescendant(targetKNode, parentNode)) {
                        if (targetKNode.getParent() == parentNode) {
                            // the edge goes to a direct child of the parent node
                            if (externalPort.kedge.getTargetPort() != null) {
                                targetLPort = (LPort) elementMap.get(
                                        externalPort.kedge.getTargetPort());
                            } else {
                                LNode targetLNode = (LNode) elementMap.get(targetKNode);
                                // TODO determine a good position for the dummy port
                                targetLPort = graphImporter.createPort(targetLNode, new KVector(),
                                        PortType.INPUT);
                            }
                        } else {
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
                            targetLPort = graphImporter.createPort(targetLNode,
                                    graphImporter.getExternalPortPosition(targetExtenalPort.lgraph,
                                            targetExtenalPort.lnode, 0, 0), PortType.INPUT);
                        }
                    } else {
                        // the edge goes to the outside of the parent node
                        LNode dummyNode = graphImporter.createExternalPortDummy(
                                externalPortDummyProperties, PortConstraints.FREE, PortSide.EAST, 1,
                                null, null, new KVector(), layoutDirection);
                        layeredGraph.getLayerlessNodes().add(dummyNode);
                        targetLPort = dummyNode.getPorts().get(0);
                        ExternalPort newExternalPort = new ExternalPort(externalPort.kedge, parentNode,
                                layeredGraph, dummyNode, PortType.OUTPUT);
                        exportedExternalPorts.add(newExternalPort);
                    }
                    newEdge.setTarget(targetLPort);
                    
                } else if (externalPort.type == PortType.INPUT) {
                    newEdge.setTarget(lport);
                    LPort sourceLPort;
                    if (KimlUtil.isDescendant(sourceKNode, parentNode)) {
                        if (sourceKNode.getParent() == parentNode) {
                            // the edge comes from a direct child of the parent node
                            if (externalPort.kedge.getSourcePort() != null) {
                                sourceLPort = (LPort) elementMap.get(
                                        externalPort.kedge.getSourcePort());
                            } else {
                                LNode sourceLNode = (LNode) elementMap.get(sourceKNode);
                                // TODO determine a good position for the dummy port
                                sourceLPort = graphImporter.createPort(sourceLNode, new KVector(),
                                        PortType.OUTPUT);
                            }
                        } else {
                            // the edge comes from the inside of another sibling node
                            ExternalPort sourceExtenalPort = null;
                            for (ExternalPort externalPort2 : containedExternalPorts) {
                                if (externalPort2 != externalPort
                                        && externalPort2.kedge == externalPort.kedge) {
                                    sourceExtenalPort = externalPort2;
                                    break;
                                }
                            }
                            LNode sourceLNode = (LNode) elementMap.get(sourceExtenalPort.knode);
                            sourceLPort = graphImporter.createPort(sourceLNode,
                                    graphImporter.getExternalPortPosition(sourceExtenalPort.lgraph,
                                            sourceExtenalPort.lnode, 0, 0), PortType.OUTPUT);
                        }
                    } else {
                        // the edge comes from the outside of the parent node
                        LNode dummyNode = graphImporter.createExternalPortDummy(
                                externalPortDummyProperties, PortConstraints.FREE, PortSide.WEST, -1,
                                null, null, new KVector(), layoutDirection);
                        layeredGraph.getLayerlessNodes().add(dummyNode);
                        sourceLPort = dummyNode.getPorts().get(0);
                        ExternalPort newExternalPort = new ExternalPort(externalPort.kedge, parentNode,
                                layeredGraph, dummyNode, PortType.INPUT);
                        exportedExternalPorts.add(newExternalPort);
                    }
                    newEdge.setSource(sourceLPort);
                }
            }
        }
    }

}
