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
package de.cau.cs.kieler.kaom.importer.ptolemy.xtend


import com.google.inject.Inject
import java.util.List

import de.cau.cs.kieler.core.annotations.TypedStringAnnotation
import de.cau.cs.kieler.kaom.Entity
import de.cau.cs.kieler.kaom.KaomFactory
import de.cau.cs.kieler.kaom.Link
import de.cau.cs.kieler.kaom.Port
import de.cau.cs.kieler.kaom.Relation
import de.cau.cs.kieler.kaom.importer.ptolemy.xtend.utils.TransformationUtils


/**
 * Optimizes a KAOM model freshly transformed from a Ptolemy2 model. This is part two of the Ptolemy
 * model import process.
 * 
 * <p>In this part, link directions are computed, relations that only connect two ports are replaced
 * by a single link, special annotations are replaced by entities (the Ptolemy director, ...), and
 * links of states are changed.</p>
 * 
 * @author cds
 * @author haf
 * @kieler.rating yellow 2012-06-14 KI-12 cmot, grh
 */
class Ptolemy2KaomOptimization {
    
    /**
     * Extensions used during the transformation. To make things easier. And stuff.
     */
    @Inject extension TransformationUtils
    
    
    /**
     * Optimizes the given KAOM model.
     * 
     * <p>The order in which we do that is partly important. We can remove a relation if it has only
     * one incoming and one outgoing link. In order to determine this, we need to have inferred the
     * link directions. When we remove ports from states or convert annotations to entities is less
     * important.</p>
     * 
     * @param kaomModel the model to optimize.
     */
    def void optimize(Entity kaomModel) {
        // Infer link directions
        inferLinkDirections(kaomModel)
        
        // Remove ports from entities representing states
        makeStatesPortless(kaomModel)
        
        // Remove unnecessary relations
        removeUnnecessaryRelations(kaomModel)
        
        // Convert special annotations into entities
        convertAnnotationsToEntities(kaomModel)
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Inference of Link Directions
    
    /**
     * Infers the direction of the links in the model tree rooted at the given entity.
     * 
     * <p>First, a list of all ports of known and unknown type, all links of unknown direction, and all
     * relations are collected. In that process, the direction of links connected to ports of known type
     * is inferred.</p>
     * 
     * <p>Second, an attempt is made to infer the type of ports of yet unknown type. This succeeds if
     * the port is connected to a link whose direction is known. The direction of all links connected
     * to such a port is inferred in that process.</p>
     * 
     * <p>Third, all relations are traversed, looking for a relation with only incoming or only outgoing
     * links and just one link of unknown direction. If such a relation is found, the direction of that
     * one link is inferred. If no such relation is found, an undirected link's direction is randomly
     * fixed.</p>
     * 
     * <p>Repeat at step two.</p>
     * 
     * <p>By this time, either all link directions have been inferred or there are some links left whose
     * direction cannot be determined with the information available. These links are left untouched,
     * since we might as well leave them in the direction they currently have.</p>
     * 
     * @param kaomModel root of the model tree.
     */
    def private void inferLinkDirections(Entity kaomModel) {
        val List<Port> knownPorts = newArrayList()
        val List<Port> unknownPorts = newArrayList()
        val List<Link> unknownLinks = newArrayList()
        val List<Relation> unknownRelations = newArrayList()
        
        // STEP 1: Fill the lists
        gatherModelElements(kaomModel, knownPorts, unknownPorts, unknownLinks, unknownRelations)
        
        // For each known port, set the direction of its incident links
        for (knownPort : knownPorts) {
            propagatePortTypeToIncidentLinks(knownPort, unknownLinks)
        }
        
        // STEPS 2 AND 3: Infer port types and traverse relations
        var portTypesChanged = false
        var relationsChanged = false
        var randomLinkFixed = false
        
        /* This loop runs until no port types have changed, no relations have changed, and no random
         * link has been fixed. It always terminates since the one factor that determines if one of
         * these changes is if there are any undirected links remaining in the model. The way the loop
         * works is that with each iteration, at least one link's direction is fixed. Assuming that the
         * model only contains a finite number of links, this loop thus has to terminate.
         */
        do {
            portTypesChanged = inferPortTypes(unknownPorts, unknownLinks)
            relationsChanged = traverseRelations(unknownRelations, unknownLinks, true)
            
            if (!(portTypesChanged || relationsChanged)) {
                // See if a link can be randomly fixed
                randomLinkFixed = traverseRelations(unknownRelations, unknownLinks, false)
            }
        } while (portTypesChanged || relationsChanged || randomLinkFixed)
    }
    
    /**
     * Traverses the model, filling the given lists with interesting elements: ports of known and
     * unknown type, links of unknown direction, and relations with incident links of unknown
     * direction. The lists can then be iterated over instead of always having to iterate over the
     * whole model again and again.
     * 
     * @param root the root of the model tree.
     * @param knownPorts list to which ports of known type are added.
     * @param unknownPorts list to which ports of unknown type are added.
     * @param unknownLinks list to which links of unknown direction are added.
     * @param unknownRelations list to which relations with incident links of unknown direction
     *                         are added.
     */
    def private void gatherModelElements(Entity root, List<Port> knownPorts, List<Port> unknownPorts,
        List<Link> unknownLinks, List<Relation> unknownRelations) {
        
        // Iterate over the entity's ports
        for (port : root.childPorts) {
            if (port.isMarkedAsInputPort() || port.isMarkedAsOutputPort()) {
                knownPorts.add(port)
            } else {
                unknownPorts.add(port)
            }
        }
        
        // Iterate over the entity's links
        for (link : root.childLinks) {
            if (link.isMarkedAsUndirected()) {
                unknownLinks.add(link)
            }
        }
        
        // Iterate over the entity's relations
        for (relation : root.childRelations) {
            if (relation.hasUnknownIncidentLink()) {
                unknownRelations.add(relation)
            }
        }
        
        // Recurse into child entities
        for (childEntity : root.childEntities) {
            gatherModelElements(childEntity, knownPorts, unknownPorts, unknownLinks, unknownRelations)
        }
    }
    
    /**
     * For a port of known type, sets the directions of its incident unknown links accordingly. This
     * succeeds if the port is marked as being either an input port or an output port, not both.
     * 
     * @param port the port.
     * @param unknownLinks list of links with unknown direction. Links whose direction is set are
     *                     removed from this list.
     * @return {@code true} if at least one link's direction is fixed or reversed.
     */
    def private boolean propagatePortTypeToIncidentLinks(Port port, List<Link> unknownLinks) {
        val List<Link> linksToBeReversed = newArrayList()
        val List<Link> linksToBeKept = newArrayList() 
        var result = false
        
        if (port.isMarkedAsInputPort() && !port.isMarkedAsOutputPort()) {
            // Reverse outgoing links, keep incoming links
            linksToBeReversed.addAll(port.outgoingLinks)
            linksToBeKept.addAll(port.incomingLinks)
        } else if (port.isMarkedAsOutputPort() && !port.isMarkedAsInputPort()) {
            // Reverse incoming links, keep outgoing links
            linksToBeReversed.addAll(port.incomingLinks)
            linksToBeKept.addAll(port.outgoingLinks)
        }
        
        // Reverse links and mark as directed
        for (link : linksToBeReversed) {
            link.reverseLink()
            result = true
            
            if (link.isMarkedAsUndirected()) {
                link.markAsUndirected(false)
                unknownLinks.remove(link)
            }
        }
        
        // Mark remaining links as directed
        for (link : linksToBeKept) {
            if (link.isMarkedAsUndirected()) {
                link.markAsUndirected(false)
                unknownLinks.remove(link)
                result = true
            }
        }
        
        result
    }
    
    /**
     * Iterates over the given list of ports of unknown type and tries to infer the type of as many
     * ports as possible. For ports whose type is inferred, the incident links are fixed accordingly.
     * 
     * @param unknownPorts list of unknown ports. Ports whose type is set are removed from this list.
     * @param unknownLinks list of links with unknown direction. Links whose direction is set are
     *                     removed from this list.
     * @return {@code true} if at least one link's direction is fixed.
     */
    def private boolean inferPortTypes(List<Port> unknownPorts, List<Link> unknownLinks) {
        var result = false
        
        // Iterate over all the ports of unknown type using a list iterator, since we want to be able
        // to remove ports from the list in the process
        val unknownPortsIterator = unknownPorts.listIterator
        while (unknownPortsIterator.hasNext()) {
            val unknownPort = unknownPortsIterator.next()
            
            if (containsDirectedLink(unknownPort.incomingLinks)) {
                // The port has an incoming link of known direction -> mark as input port
                unknownPort.markAsInputPort()
            } else if (containsDirectedLink(unknownPort.outgoingLinks)) {
                // The port has an outgoing link of known direction -> mark as output port
                unknownPort.markAsOutputPort()
            } else if (isInputPortName(unknownPort.name)) {
                // The port is named like an input port -> mark as input port
                unknownPort.markAsInputPort()
            } else if (isOutputPortName(unknownPort.name)) {
                
                // The port is named like an input port -> mark as input port
                unknownPort.markAsOutputPort()
            }
            
            // If the port's type is now known, fix incident link directions accordingly and remove
            // from the list of unknown ports
            if (unknownPort.isMarkedAsInputPort() ||unknownPort.isMarkedAsOutputPort()) {
                result = propagatePortTypeToIncidentLinks(unknownPort, unknownLinks) || result
                unknownPortsIterator.remove()
            }
        }
        
        result
    }
    
    /**
     * Iterates over the list of relations with unknown links and tries to infer link directions. This is
     * done by looking for relations with only incoming or only outgoing links whose direction is known,
     * and links whose direction is unknown. This method can operate in two different modes regarding
     * the number of links with unknown direction.
     * 
     * <p>The first mode is conservative. For each undirected link it only fixes its direction if it
     * is the only undirected link incident to a relation. This is the safe mode of operation.</p>
     * 
     * <p>The second mode also accepts relations with more than one incident undirected links. It takes
     * the first of them, sets its direction and returns, thereby only fixing the direction of at most
     * one link in the whole model. This mode is used to fix a link's direction if the other methods
     * cannot infer any more link directions.</p>
     * 
     * @param unknownRelation list of relations with unknown incident links. Relations whose incident
     *                        links are all fixed are removed from this list.
     * @param unknownLinks list of links with unknown direction. Links whose direction is set are
     *                     removed from this list.
     * @param conservative if {@code false}, operates in a mode that fixes a single link more or less
     *                     at random, if possible.
     * @return {@code true} if at least one link's direction is fixed or reversed.
     */
    def private boolean traverseRelations(List<Relation> unknownRelations, List<Link> unknownLinks,
        boolean conservative) {
        
        var result = false
        
        // Iterate over all the relations with incident links of unknown direction using a list
        // iterator, since we want to be able to remove relations from the list in the process
        val unknownRelationsIterator = unknownRelations.listIterator
        while (unknownRelationsIterator.hasNext()) {
            val unknownRelation = unknownRelationsIterator.next()
            var fixedLinkInThisIteration = false
            
            // Find all incident links of known direction
            val fixedIncomingLinks = unknownRelation.incomingLinks.filter(l | !l.isMarkedAsUndirected())
            val fixedOutgoingLinks = unknownRelation.outgoingLinks.filter(l | !l.isMarkedAsUndirected())
            
            val undirectedIncidentLinks =
                unknownRelation.incidentLinks.filter(l | l.isMarkedAsUndirected())
            
            // If there is only one undirected incident link...
            val undirectedIncidentLinksSize = undirectedIncidentLinks.size
            
            if ((conservative && undirectedIncidentLinksSize == 1)
                || (!conservative && undirectedIncidentLinksSize > 0)) {
                
                val undirectedLink = undirectedIncidentLinks.iterator().next()
                
                // ...and only incoming or outgoing directed links...
                if (fixedIncomingLinks.size > 0 && fixedOutgoingLinks.size == 0) {
                    // ...the undirected link must be outgoing
                    if (undirectedLink.source != unknownRelation) {
                        undirectedLink.reverseLink()
                    }
                    
                    undirectedLink.markAsUndirected(false)
                    unknownLinks.remove(undirectedLink)
                    fixedLinkInThisIteration = true
                } else if (fixedOutgoingLinks.size > 0 && fixedIncomingLinks.size == 0) {
                    // ...the undirected link must be incoming
                    if (undirectedLink.target != unknownRelation) {
                        undirectedLink.reverseLink()
                    }
                    
                    undirectedLink.markAsUndirected(false)
                    unknownLinks.remove(undirectedLink)
                    fixedLinkInThisIteration = true
                }
            }
            
            // Check if we fixed a link
            if (fixedLinkInThisIteration) {
                result = true
                
                // Remove relation from the list of unknown relations if all link directions are
                // now fixed
                if (undirectedIncidentLinksSize == 1) {
                    unknownRelationsIterator.remove()
                }
                
                // If we're not in conservative mode, return without touching further links
                if (!conservative) {
                    return result
                }
            }
        }
        
        result
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Removal of State Ports
    
    /**
     * Removes ports from entities representing modal model states in the model rooted at the given root
     * entity. Also, entities containing such states are annotated for the layout algorithm to know to
     * use a layout algorithm for state machines.
     * 
     * @param root the model tree's root entity.
     */
    def private void makeStatesPortless(Entity root) {
        // Check if we have a Ptolemy state
        if (root.getStringAnnotationValue("ptolemyClass").equals("ptolemy.domains.modal.kernel.State")) {
            // Iterate over the state's ports
            for (port : root.childPorts) {
                // Iterate over the port's incident links
                for (link : port.getIncidentLinks()) {
                    // In the former version of the transformation, we would make sure the links would
                    // face into the correct direction. However, this should in theory have already been
                    // inferred, so we skip this and hope for the best.
                    
                    // Connect the link to the state instead of the port
                    if (link.source == port) {
                        link.source = root
                    } else {
                        link.target = root
                    }
                }
            }
            
            // Get rid of the state's ports
            root.childPorts.clear()
            
            // Annotate the entity's parent to use a proper layout algorithm
            (root.eContainer as Entity).addStringAnnotation("DiagramType", "StateMachine")
        }
        
        // Recurse into child entities
        for (childEntity : root.childEntities) {
            makeStatesPortless(childEntity)
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Removal of Unnecessary Relations
    
    /**
     * Recursively removes unnecessary relations in the model rooted at the given entity. Unnecessary
     * relations are those that have one incoming and one outgoing link.
     * 
     * @param root the model's root entity. 
     */
    def private void removeUnnecessaryRelations(Entity root) {
        val relationsIterator = root.childRelations.listIterator
        while (relationsIterator.hasNext()) {
            val relation = relationsIterator.next()
            
            // Check if the relation has only one incoming and one outgoing link
            if (relation.incomingLinks.size() == 1 && relation.outgoingLinks.size() == 1) {
                val inLink = relation.incomingLinks.get(0)
                val outLink = relation.outgoingLinks.get(0)
                
                inLink.target = outLink.target
                
                // Remove the outgoing link
                outLink.source = null
                outLink.target = null
                (outLink.eContainer as Entity).childLinks.remove(outLink)
                
                // Remove the relation
                relationsIterator.remove()
            }
        }
        
        // Recurse into child entities
        for (childEntity : root.childEntities) {
            removeUnnecessaryRelations(childEntity)
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Transformation of Certain Annotations into Entities
    
    /**
     * Converts certain annotations into entities of their own right. In particular, Ptolemy directors
     * are persisted as annotations in Ptolemy models, but need to be entities in KAOM models to be
     * correctly displayed.
     * 
     * @param root root element of the model to look for convertible annotations in.
     */
    def private void convertAnnotationsToEntities(Entity root) {
        // Only consider entities that were not themselves created from annotations
        if (root.isMarkedAsFormerAnnotationEntity()) {
            return
        }
        
        // Look at the entities annotations
        val annotationsIterator = root.annotations.listIterator
        while (annotationsIterator.hasNext()) {
            val annotation = annotationsIterator.next()
            
            // If the annotation is not a TypedStringAnnotation, we're not interested
            if (annotation instanceof TypedStringAnnotation) {
                val tsAnnotation = annotation as TypedStringAnnotation
                
                // Check if the annotation denotes a Ptolemy director
                if (tsAnnotation.type.endsWith("Director")) {
                    // Create a new entity for it
                    val directorEntity = KaomFactory::eINSTANCE.createEntity()
                    
                    // Set the name, add language annotation and mark it as having been created from
                    // an annotation
                    directorEntity.name = tsAnnotation.name
                    directorEntity.addStringAnnotation("language", "ptolemy")
                    directorEntity.addAnnotation("Director")
                    directorEntity.markAsFormerAnnotationEntity(true)
                    
                    // Annotate the new entity with the original annotation and remove that from its
                    // former entity
                    annotationsIterator.remove()
                    directorEntity.annotations.add(tsAnnotation)
                    
                    // Add the new entity to the root element
                    root.childEntities.add(directorEntity)
                }
            }
        }
        
        // Recurse into child entities
        for (childEntity : root.childEntities) {
            convertAnnotationsToEntities(childEntity)
        }
    }
}