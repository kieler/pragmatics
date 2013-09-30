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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.KlayLayered;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement.HashCodeCounter;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.properties.PortType;

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
    
    public RecursiveCompoundKGraphHandler(final KlayLayered klayLayered,
            final HashCodeCounter hashCodeCounter) {
        this.klayLayered = klayLayered;
        this.hashCodeCounter = hashCodeCounter;
    }

    public void doLayoutHierarchy(final KNode kgraph, final IKielerProgressMonitor monitor) {
        // perform the standard flat layout on each hierarchy level
        recursiveLayout(kgraph, monitor);
        
        // TODO handle the cross-hierarchy edges
    }
    
    private static class ExternalPort {
        private KEdge kedge;
        private KNode knode;
        private LPort lport;
        private PortType type = PortType.UNDEFINED;
    }
    
    private void recursiveLayout(final KNode parentNode, final IKielerProgressMonitor monitor) {
        monitor.begin("Recursive compound graph layout", parentNode.getChildren().size() + 1);
        
        for (KNode childNode : parentNode.getChildren()) {
            if (childNode.getChildren().isEmpty()) {
                monitor.worked(1);
            } else {
                // layout the content of the compound node recursively
                recursiveLayout(childNode, monitor.subTask(1));
            }
            
            // gather the cross-hierarchy edges connected to the child node
            for (KEdge edge : childNode.getOutgoingEdges()) {
                KNode targetNode = edge.getTarget();
                if (targetNode.getParent() != parentNode) {
                    if (KimlUtil.isDescendant(targetNode, childNode)) {
                        if (edge.getSourcePort() == null) {
                            // the edge goes into the source node and has no source port
                        }
                    } else if (KimlUtil.isDescendant(targetNode, parentNode)) {
                        // the edge goes into a sibling of its source node
                    } else {
                        // the edge goes out of the parent node
                    }
                }
            }
            for (KEdge edge : childNode.getIncomingEdges()) {
                KNode sourceNode = edge.getSource();
                if (sourceNode.getParent() != parentNode) {
                    if (KimlUtil.isDescendant(sourceNode, childNode)) {
                        if (edge.getTargetPort() == null) {
                            // the edge comes from the inside of the target node and has no target port
                        }
                    } else if (KimlUtil.isDescendant(sourceNode, parentNode)) {
                        // the edge comes from a sibling if its target node
                    } else {
                        // the edge comes from the outside of the parent node
                    }
                }
            }
        }
        
        // import the graph
        IGraphImporter<KNode> graphImporter = new KGraphImporter(hashCodeCounter);
        LGraph layeredGraph = graphImporter.importGraph(parentNode);

        // perform layer-based layout
        LGraph result = klayLayered.doLayout(layeredGraph, monitor.subTask(1));

        // apply the layout results to the original graph
        graphImporter.applyLayout(result);
        
        monitor.done();
    }

}
