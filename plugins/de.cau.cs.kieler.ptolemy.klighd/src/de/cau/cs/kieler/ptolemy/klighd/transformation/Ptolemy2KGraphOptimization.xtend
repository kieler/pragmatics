/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.ptolemy.klighd.transformation

import com.google.inject.Inject
import de.cau.cs.kieler.core.kgraph.KEdge
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.kiml.util.KimlUtil
import java.util.List

/**
 * Optimizes a KGraph model freshly transformed from a Ptolemy2 model. This is step two of the Ptolemy
 * model import process.
 * 
 * <p>In this part, edge directions are computed, relations that only connect two ports are replaced
 * by a single edge, special annotations are replaced by nodes (the Ptolemy director, ...), and
 * edges that connect states are changed.</p>
 * 
 * @author cds
 * @author haf
 * @kieler.rating yellow 2012-06-14 KI-12 cmot, grh
 */
class Ptolemy2KGraphOptimization {
    
    /** Marking nodes. */
    @Inject extension AnnotationExtensions
    /** Marking nodes. */
    @Inject extension LabelExtensions
    /** Marking nodes. */
    @Inject extension MarkerExtensions
    /** Miscellaneous stuff to make my life easier. */
    @Inject extension MiscellaneousExtensions
    /** Marking nodes. */
    @Inject extension PortExtensions
    
    @Inject CommentsExtractor commentsExtractor
    
    
    /**
     * Optimizes the given KGraph model.
     * 
     * <p>The order in which we do that is partly important. We can remove a relation if it has only
     * one incoming and one outgoing edge. In order to determine this, we need to have inferred the
     * edge directions. When we remove ports from states or convert annotations to nodes is less
     * important.</p>
     * 
     * @param kGraph the model to optimize.
     */
    def void optimize(KNode kGraph) {
        // Infer edge directions
        inferEdgeDirections(kGraph)
        
        // Remove ports from nodes that represent states
        makeStatesPortless(kGraph)
        
        // Remove either unnecessary or all relations
//        removeUnnecessaryRelations(kGraph)
        removeAllRelations(kGraph)
        
        // Convert special annotations into nodes
        convertAnnotationsToNodes(kGraph)
        
        // Convert comments into nodes
        commentsExtractor.extractAndAttachComments(kGraph)
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inference of Edge Directions
    
    /**
     * Infers the direction of the edges in the model tree rooted at the given node.
     * 
     * <p>First, a list of all ports of known and unknown type, all edge of unknown direction, and all
     * relations are collected. In that process, the direction of edges connected to ports of known type
     * is inferred.</p>
     * 
     * <p>Second, an attempt is made to infer the type of ports of yet unknown type. This succeeds if
     * the port is connected to an edge whose direction is known. The direction of all edges connected
     * to such a port is inferred in that process.</p>
     * 
     * <p>Third, all relations are traversed, looking for a relation with only incoming or only outgoing
     * edges and just one edge of unknown direction. If such a relation is found, the direction of that
     * one edge is inferred. If no such relation is found, an undirected edge's direction is randomly
     * fixed.</p>
     * 
     * <p>Repeat at step two.</p>
     * 
     * <p>By this time, either all edge directions have been inferred or there are some edges left whose
     * direction cannot be determined with the information available. These edges are left untouched,
     * since we might as well leave them in the direction they currently have.</p>
     * 
     * @param kGraph root of the graph.
     */
    def private void inferEdgeDirections(KNode kGraph) {
        val List<KPort> knownPorts = newArrayList()
        val List<KPort> unknownPorts = newArrayList()
        val List<KEdge> unknownEdges = newArrayList()
        val List<KNode> unknownRelations = newArrayList()
        
        // STEP 1: Fill the lists
        gatherModelElements(kGraph, knownPorts, unknownPorts, unknownEdges, unknownRelations)
        
        // For each known port, set the direction of its incident edges
        for (knownPort : knownPorts) {
            propagatePortTypeToIncidentEdges(knownPort, unknownEdges)
        }
        
        // STEPS 2 AND 3: Infer port types and traverse relations
        var portTypesChanged = false
        var relationsChanged = false
        var randomEdgeFixed = false
        
        /* This loop runs until no port types have changed, no relations have changed, and no random
         * edge has been fixed. It always terminates since the one factor that determines if one of
         * these changes is if there are any undirected edges remaining in the model. The way the loop
         * works is that with each iteration, at least one edge's direction is fixed. Assuming that the
         * model only contains a finite number of edges, this loop thus has to terminate.
         */
        do {
            portTypesChanged = inferPortTypes(unknownPorts, unknownEdges)
            relationsChanged = traverseRelations(unknownRelations, unknownEdges, true)
            
            if (!(portTypesChanged || relationsChanged)) {
                // See if an edge can be randomly fixed
                randomEdgeFixed = traverseRelations(unknownRelations, unknownEdges, false)
            }
        } while (portTypesChanged || relationsChanged || randomEdgeFixed)
    }
    
    /**
     * Traverses the model, filling the given lists with interesting elements: ports of known and
     * unknown type, edges of unknown direction, and relations with incident edges of unknown
     * direction. The lists can then be iterated over instead of always having to iterate over the
     * whole model again and again.
     * 
     * @param root the root of the model tree.
     * @param knownPorts list to which ports of known type are added.
     * @param unknownPorts list to which ports of unknown type are added.
     * @param unknownLinks list to which edges of unknown direction are added.
     * @param unknownRelations list to which relations with incident edges of unknown direction
     *                         are added.
     */
    def private void gatherModelElements(KNode root, List<KPort> knownPorts, List<KPort> unknownPorts,
        List<KEdge> unknownEdges, List<KNode> unknownRelations) {
        
        // Chceck if this node is a relation node
        if (root.markedAsHypernode) {
            unknownRelations.add(root)
        }
        
        // Iterate over the entity's ports
        for (port : root.ports) {
            if (port.markedAsInputPort || port.markedAsOutputPort) {
                knownPorts.add(port)
            } else {
                unknownPorts.add(port)
            }
        }
        
        // Iterate over the entity's outgoing edges
        for (edge : root.outgoingEdges) {
            if (edge.markedAsUndirected) {
                unknownEdges.add(edge)
            }
        }
        
        // Recurse into child entities
        for (child : root.children) {
            gatherModelElements(child, knownPorts, unknownPorts, unknownEdges, unknownRelations)
        }
    }
    
    /**
     * For a port of known type, sets the directions of its incident unknown edges accordingly. This
     * succeeds if the port is marked as being either an input port or an output port, not both.
     * 
     * <p>Note that for hierarchical ports, the port type is only valid for connections to its node's
     * environment. Children of the node must treat a hierarchical input port as being an output port,
     * and the other way round for hierarchical output ports.</p>
     * 
     * @param port the port.
     * @param unknownEdges list of edges with unknown direction. Edges whose direction is set are
     *                     removed from this list.
     * @return {@code true} if at least one edge's direction is fixed or reversed.
     */
    def private boolean propagatePortTypeToIncidentEdges(KPort port, List<KEdge> unknownEdges) {
        val List<KEdge> edgesToBeReversed = newArrayList()
        val List<KEdge> edgesToBeKept = newArrayList()
        var result = false
        
        if (port.markedAsInputPort && !port.markedAsOutputPort) {
            // Edges connected to the node's inside must be outgoing, edges connected to the node's
            // outside must be incoming
            for (edge : port.edges) {
                if (port.node.children.contains(edge.source)) {
                    edgesToBeReversed += edge
                } else if (edge.sourcePort == port && port.node.parent.children.contains(edge.target)) {
                    edgesToBeReversed += edge
                } else {
                    edgesToBeKept += edge
                }
            }
        } else if (port.markedAsOutputPort && !port.markedAsInputPort) {
            // Edges connected to the node's inside must be incoming, edges connected to the node's
            // outside must be outgoing
            for (edge : port.edges) {
                if (port.node.children.contains(edge.target)) {
                    edgesToBeReversed += edge
                } else if (edge.targetPort == port && port.node.parent.children.contains(edge.source)) {
                    edgesToBeReversed += edge
                } else {
                    edgesToBeKept += edge
                }
            }
        }
        
        // Reverse edges and mark as directed
        for (edge : edgesToBeReversed) {
            edge.reverseEdge()
            result = true
            
            if (edge.markedAsUndirected) {
                edge.markAsUndirected(false)
                unknownEdges.remove(edge)
            }
        }
        
        // Mark remaining edges as directed
        for (edge : edgesToBeKept) {
            if (edge.markedAsUndirected) {
                edge.markAsUndirected(false)
                unknownEdges.remove(edge)
                result = true
            }
        }
        
        return result
    }
    
    /**
     * Iterates over the given list of ports of unknown type and tries to infer the type of as many
     * ports as possible. For ports whose type is inferred, the incident edges are fixed accordingly.
     * 
     * @param unknownPorts list of unknown ports. Ports whose type is set are removed from this list.
     * @param unknownEdges list of edges with unknown direction. Edges whose direction is set are
     *                     removed from this list.
     * @return {@code true} if at least one edge's direction is fixed.
     */
    def private boolean inferPortTypes(List<KPort> unknownPorts, List<KEdge> unknownEdges) {
        var result = false
        
        // Iterate over all the ports of unknown type using a list iterator, since we want to be able
        // to remove ports from the list in the process
        val unknownPortsIterator = unknownPorts.listIterator
        while (unknownPortsIterator.hasNext()) {
            val unknownPort = unknownPortsIterator.next()
            val directedIncomingEdge = getFirstDirectedEdge(unknownPort.edges.filter(
                [e | e.targetPort == unknownPort]))
            val directedOutgoingEdge = getFirstDirectedEdge(unknownPort.edges.filter(
                [e | e.sourcePort == unknownPort]))
            
            if (directedIncomingEdge != null) {
                // The port has an incoming edge of known direction!
                if (unknownPort.node.children.contains(directedIncomingEdge.source)) {
                    // Connection from the inside -> the port is an output port
                    unknownPort.markAsOutputPort()
                } else {
                    // Connection from the outside -> the port is an input port
                    unknownPort.markAsInputPort()
                }
            } else if (directedOutgoingEdge != null) {
                // The port has an outgoing edge of known direction!
                if (unknownPort.node.children.contains(directedIncomingEdge.target)) {
                    // Connection to the inside -> the port is an input port
                    unknownPort.markAsInputPort()
                } else {
                    // Connection to the outside -> the port is an output port
                    unknownPort.markAsOutputPort()
                }
            } else if (isInputPortName(unknownPort.name)) {
                // The port is named like an input port -> mark as input port
                unknownPort.markAsInputPort()
            } else if (isOutputPortName(unknownPort.name)) {
                // The port is named like an output port -> mark as output port
                unknownPort.markAsOutputPort()
            }
            
            // If the port's type is now known, fix incident edge directions accordingly and remove
            // from the list of unknown ports
            if (unknownPort.markedAsInputPort ||unknownPort.markedAsOutputPort) {
                result = propagatePortTypeToIncidentEdges(unknownPort, unknownEdges) || result
                unknownPortsIterator.remove()
            }
        }
        
        return result
    }
    
    /**
     * Iterates over the list of relations with unknown edges and tries to infer edge directions. This is
     * done by looking for relations with only incoming or only outgoing edges whose direction is known,
     * and edges whose direction is unknown. This method can operate in two different modes regarding
     * the number of edges with unknown direction.
     * 
     * <p>The first mode is conservative. For each undirected edge it only fixes its direction if it
     * is the only undirected edge incident to a relation. This is the safe mode of operation.</p>
     * 
     * <p>The second mode also accepts relations with more than one incident undirected edge. It takes
     * the first of them, sets its direction and returns, thereby only fixing the direction of at most
     * one edge in the whole model. This mode is used to fix an edge's direction if the other methods
     * cannot infer any more edge directions.</p>
     * 
     * @param unknownRelation list of relations with unknown incident edges. Relations whose incident
     *                        edges are all fixed are removed from this list.
     * @param unknownEdges list of edges with unknown direction. Edges whose direction is set are
     *                     removed from this list.
     * @param conservative if {@code false}, operates in a mode that fixes a single edge more or less
     *                     at random, if possible.
     * @return {@code true} if at least one edge's direction is fixed or reversed.
     */
    def private boolean traverseRelations(List<KNode> unknownRelations, List<KEdge> unknownEdges,
        boolean conservative) {
        
        var result = false
        
        // Iterate over all the relations with incident edges of unknown direction using a list
        // iterator, since we want to be able to remove relations from the list in the process
        val unknownRelationsIterator = unknownRelations.listIterator
        while (unknownRelationsIterator.hasNext()) {
            val unknownRelation = unknownRelationsIterator.next()
            var fixedEdgeInThisIteration = false
            
            // Find all incident edges of known direction
            val fixedIncomingEdges = unknownRelation.incomingEdges.filter(l | !l.markedAsUndirected)
            val fixedOutgoingEdges = unknownRelation.outgoingEdges.filter(l | !l.markedAsUndirected)
            val undirectedIncidentEdges =
                unknownRelation.incidentEdges.filter(l | l.markedAsUndirected)
            
            // If there is only one undirected incident edge...
            val undirectedIncidentEdgesSize = undirectedIncidentEdges.size
            
            if ((conservative && undirectedIncidentEdgesSize == 1)
                || (!conservative && undirectedIncidentEdgesSize > 0)) {
                
                val undirectedEdge = undirectedIncidentEdges.iterator().next()
                
                // ...and only incoming or outgoing directed edges...
                if (fixedIncomingEdges.size > 0 && fixedOutgoingEdges.size == 0) {
                    // ...the undirected edge must be outgoing
                    if (undirectedEdge.source != unknownRelation) {
                        undirectedEdge.reverseEdge()
                    }
                    
                    undirectedEdge.markAsUndirected(false)
                    unknownEdges.remove(undirectedEdge)
                    fixedEdgeInThisIteration = true
                } else if (fixedOutgoingEdges.size > 0 && fixedIncomingEdges.size == 0) {
                    // ...the undirected edge must be incoming
                    if (undirectedEdge.target != unknownRelation) {
                        undirectedEdge.reverseEdge()
                    }
                    
                    undirectedEdge.markAsUndirected(false)
                    unknownEdges.remove(undirectedEdge)
                    fixedEdgeInThisIteration = true
                }
            }
            
            // Check if we fixed an edge
            if (fixedEdgeInThisIteration) {
                result = true
                
                // Remove relation from the list of unknown relations if all edge directions are
                // now fixed
                if (undirectedIncidentEdgesSize == 1) {
                    unknownRelationsIterator.remove()
                }
                
                // If we're not in conservative mode, return without touching further edges
                if (!conservative) {
                    return result
                }
            }
        }
        
        return result
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Removal of State Ports
    
    /**
     * Removes ports from nodes representing modal model states in the model rooted at the given root
     * entity. Also, nodes containing such states are annotated for the layout algorithm to know to
     * use a layout algorithm for state machines.
     * 
     * @param root the model tree's root entity.
     */
    def private void makeStatesPortless(KNode root) {
        // Check if we have a Ptolemy state
        if (root.markedAsState) {
            // Iterate over the state's ports
            for (port : root.ports) {
                // Remove edges from the port while it still has any
                while (!port.edges.empty) {
                    // In the former version of the transformation, we would make sure the edges would
                    // face into the correct direction. However, this should in theory have already been
                    // inferred, so we skip this and hope for the best. Amen.
                    
                    // Connect the edge to the state instead of the port
                    val edge = port.edges.get(0)
                    if (edge.sourcePort == port) {
                        edge.sourcePort = null
                    } else {
                        edge.targetPort = null
                    }
                }
            }
            
            // Get rid of the state's ports
            root.ports.clear()
            
            // Annotate the state's parent to use a proper layout algorithm
            (root.eContainer as KNode).markAsStateMachineContainer()
        }
        
        // Recurse into child nodes
        for (child : root.children) {
            makeStatesPortless(child)
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Removal of Unnecessary Relations
    
    /**
     * Recursively removes unnecessary relations in the model rooted at the given node. Unnecessary
     * relations are those that have one incoming and one outgoing edge.
     * 
     * @param root the model's root node. 
     */
    def private void removeUnnecessaryRelations(KNode root) {
        val relationsIterator = root.children.filter([c | c.markedAsHypernode]).iterator
        val List<KNode> nodesToBeRemoved = newArrayList()
        
        while (relationsIterator.hasNext()) {
            val relation = relationsIterator.next()
            
            // Check if the relation has only one incoming and one outgoing edge
            if (relation.incomingEdges.size() == 1 && relation.outgoingEdges.size() == 1) {
                val inEdge = relation.incomingEdges.get(0)
                val outEdge = relation.outgoingEdges.get(0)
                
                inEdge.target = outEdge.target
                inEdge.targetPort = outEdge.targetPort
                
                // Remove the outgoing edge
                outEdge.source = null
                outEdge.target = null
                outEdge.sourcePort = null
                outEdge.targetPort = null
                
                // Remove the relation
                nodesToBeRemoved += relation
            }
        }
        
        // Remove relations
        for (node : nodesToBeRemoved) {
            root.children.remove(node)
        }
        
        // Recurse into child nodes
        for (child : root.children) {
            removeUnnecessaryRelations(child)
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Removal of All Relations
    
    /**
     * Recursively removes unnecessary relations in the model rooted at the given node. Unnecessary
     * relations are those that have one incoming and one outgoing edge.
     * 
     * @param root the model's root node. 
     */
    def private void removeAllRelations(KNode root) {
        /* Several Steps:
         * 1. Divide relations into groups. All relations of a particular group belong to
         *    one hyperedge. Also gather all connection points to non-relation nodes. Note
         *    that this step may result in relations that cannot be removed because they
         *    have only incoming or only outgoing connections. This would be due to problems
         *    during edge direction inference.
         *    This step is outsourced to HyperedgeGatherer.
         * 2. Iterate over hyperedges. Remove all relations and edges and add new edges that
         *    connect all source nodes and ports to all target nodes and ports.
         */
        
        // 1. Gather hyperedges
        val magic = new HyperedgeGatherer(root)
        magic.gatherHyperedges()
        

        // 2. Remove all relations and edges and add new edges
        for (hyperedge : magic.hyperedges) {
            // Remove relations and edges
            for (relation : hyperedge.relations) {
                root.children.remove(relation)
                
                // Remove edges
                while (!relation.incomingEdges.empty) {
                    val edge = relation.incomingEdges.get(0)
                    edge.sourcePort = null
                    edge.source = null
                    edge.targetPort = null
                    edge.target = null
                }
                
                while (!relation.outgoingEdges.empty) {
                    val edge = relation.outgoingEdges.get(0)
                    edge.sourcePort = null
                    edge.source = null
                    edge.targetPort = null
                    edge.target = null
                }
            }
            
            // Add new edges
            for (sourceNode : hyperedge.sourceNodes) {
                for (targetNode : hyperedge.targetNodes) {
                    val newEdge = KimlUtil::createInitializedEdge()
                    newEdge.source = sourceNode
                    
                    newEdge.target = targetNode
                }
                
                for (targetPort : hyperedge.targetPorts) {
                    val newEdge = KimlUtil::createInitializedEdge()
                    newEdge.source = sourceNode
                    
                    newEdge.target = targetPort.node
                    newEdge.targetPort = targetPort
                }
            }
            
            for (sourcePort : hyperedge.sourcePorts) {
                for (targetNode : hyperedge.targetNodes) {
                    val newEdge = KimlUtil::createInitializedEdge()
                    newEdge.source = sourcePort.node
                    newEdge.sourcePort = sourcePort
                    
                    newEdge.target = targetNode
                }
                
                for (targetPort : hyperedge.targetPorts) {
                    val newEdge = KimlUtil::createInitializedEdge()
                    newEdge.source = sourcePort.node
                    newEdge.sourcePort = sourcePort
                    
                    newEdge.target = targetPort.node
                    newEdge.targetPort = targetPort
                }
            }
        }
        
        // Recurse into sub-levels
        for (node : root.children) {
            if (!node.children.empty) {
                removeAllRelations(node)
            }
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Transformation of Certain Annotations into Nodes
    
    /**
     * Converts certain annotations into nodes of their own right. In particular, Ptolemy directors
     * are persisted as annotations in Ptolemy models, but need to be nodes in KGraph models to be
     * correctly displayed.
     * 
     * @param root root element of the model to look for convertible annotations in.
     */
    def private void convertAnnotationsToNodes(KNode root) {
        // Only consider nodes that were not themselves created from annotations
        if (root.markedAsFormerAnnotationNode) {
            return
        }
        
        // Look at the node's annotations
        val annotationsIterator = root.annotations.listIterator
        while (annotationsIterator.hasNext()) {
            val annotation = annotationsIterator.next()
            
            // Check if the annotation denotes a Ptolemy director
            if (annotation.class_ != null && annotation.class_.endsWith("Director")) {
                // Create a new node for it
                val directorNode = KimlUtil::createInitializedNode()
                
                // Set the name, add language annotation and mark it as having been created from
                // an annotation
                directorNode.name = annotation.name
                directorNode.markAsPtolemyElement()
                directorNode.markAsDirector()
                directorNode.markAsFormerAnnotationNode()
                
                // Add the new node to the root element
                root.children += directorNode
            }
        }
        
        // Recurse into child nodes
        for (child : root.children) {
            convertAnnotationsToNodes(child)
        }
    }
}