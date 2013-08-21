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
 package de.cau.cs.kieler.ptolemy.klighd.transformation

import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.kiml.util.KimlUtil
import com.google.inject.Inject
import java.util.List
import de.cau.cs.kieler.core.kgraph.KEdge

/**
 * Optional optimization that flattens the graph by eliminating all composite actors.
 * 
 * @author msp
 */
class Flattener {
    
    /** Access to marked nodes. */
    @Inject extension MarkerExtensions
    /** Miscellaneous stuff to make my life easier. */
    @Inject extension MiscellaneousExtensions
    
    /**
     * Flatten all composite actors in the given graph.
     */
    def void flatten(KNode node) {
        val List<KNode> compositeNodes = newArrayList()
        for (child : node.children) {
            if (!child.children.empty && !child.markedAsState && !child.markedAsStateMachineContainer) {
                compositeNodes.add(child)
            }
        }
        // Iterate the composite nodes in an extra loop to avoid concurrent modification
        for (child : compositeNodes) {
            flatten(child)
            eliminateHierarchy(node, child)
        }
    }
    
    /**
     * Eliminate the hierarchy level introduced by the given child node.
     * 
     * @param parent the parent node
     * @param child the child node
     */
    def void eliminateHierarchy(KNode parent, KNode child) {
        for (port : child.ports) {
            // Gather incoming and outgoing edges from the port
            val List<KEdge> incoming = newArrayList()
            val List<KEdge> outgoing = newArrayList()
            for (edge : port.edges) {
                if (edge.sourcePort == port) {
                    outgoing.add(edge)
                }
                if (edge.targetPort == port) {
                    incoming.add(edge)
                }
            }
            
            // Create the new edges to bridge the old ones
            for (edge1 : incoming) {
                for (edge2 : outgoing) {
                    if (edge1.source != child && edge2.target != child) {
                        val newEdge = KimlUtil::createInitializedEdge();
                        newEdge.source = edge1.source
                        newEdge.sourcePort = edge1.sourcePort
                        newEdge.target = edge2.target
                        newEdge.targetPort = edge2.targetPort
                        newEdge.getLayout().copyProperties(edge1.getLayout());
                    }
                }
            }
            
            // Remove the old edges
            for (edge1 : incoming) {
                edge1.source = null
                edge1.sourcePort = null
            }
            for (edge2 : outgoing) {
                edge2.target = null
                edge2.targetPort = null
            }
        }
        
        // Move all contained nodes to the parent
        while (!child.children.empty) {
            val grandChild = child.children.get(0)
            if (grandChild.markedAsDirector || grandChild.markedAsParameterNode) {
                // These kinds of nodes are discarded
                grandChild.setParent(null)
            } else {
                grandChild.setParent(parent)
            }
        }
        
        // Remove the composite node
        child.setParent(null)
        for (edge : child.incomingEdges) {
            edge.source = null
            edge.sourcePort = null
        }
        for (edge : child.outgoingEdges) {
            edge.target = null
            edge.targetPort = null
        }
    }
    
}