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
import de.cau.cs.kieler.core.kgraph.KGraphElement
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.util.KimlUtil
import de.cau.cs.kieler.ptolemy.klighd.PluginConstants
import de.cau.cs.kieler.ptolemy.klighd.transformation.extensions.AnnotationExtensions
import de.cau.cs.kieler.ptolemy.klighd.transformation.extensions.LabelExtensions
import de.cau.cs.kieler.ptolemy.klighd.transformation.extensions.MarkerExtensions
import de.cau.cs.kieler.ptolemy.klighd.transformation.extensions.MiscellaneousExtensions
import java.util.ArrayList
import java.util.List
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IStatus
import org.eclipse.core.runtime.Status
import org.eclipse.emf.ecore.EObject
import org.ptolemy.moml.ClassType
import org.ptolemy.moml.DocumentRoot
import org.ptolemy.moml.EntityType
import org.ptolemy.moml.LinkType
import org.ptolemy.moml.PortType
import org.ptolemy.moml.PropertyType
import org.ptolemy.moml.RelationType

import static de.cau.cs.kieler.ptolemy.klighd.transformation.util.TransformationConstants.*

import static extension com.google.common.base.Strings.*

/**
 * Transforms a Ptolemy2 model to a KGraph. This is step one of the Ptolemy model transformation
 * process, with step two consisting of the optimization of the transformed model as defined in
 * {@code Ptolemy2KGraphOptimization}, and step three consisting of providing the KRendering information
 * necessary to display the model as defined in {@code Ptolemy2KGraphVisualization}.
 * 
 * <p>In this part, all Ptolemy entities, relations and links are transformed to their KGraph
 * counterparts. The transformed objects are annotated to originate from a Ptolemy2 model. This
 * is fairly straightforward, except for the ports of actors. During the transformation, actors
 * must be instantiated using the KIELER Ptolemy library to find their ports. The problem is that
 * if an actor is not part of the official Ptolemy actor library, the instantiation often fails,
 * leaving us without properly defined ports. In particular, we then cannot determine if a port
 * is an input port or an output port, which leads to problems when the directions of links are
 * inferred in the second part of the import process. (contrary to KGraph edges, Ptolemy links are
 * undirected) The optimization part following this transformation part tries to solve the port
 * problem by heuristically inferring the direction of links.</p>
 * 
 * <p>The transformation works by recursively iterating over all entities defined in the model. For
 * each entity, we transform its properties to annotations, transform its child ports, its child
 * entities, and finally relations and links defined in the entity.</p>
 * 
 * <p><b>Note:</b> This transformation cannot simply be reused due to the way Xtend handles create
 * methods. To keep things simple, always use a new instance for each model to be transformed.</p>
 * 
 * @author cds
 * @author haf
 * @kieler.rating yellow 2012-06-15 KI-12 cmot, grh
 */
class Ptolemy2KGraphTransformation {
    
    /** Accessing annotations. */
    @Inject extension AnnotationExtensions
    /** Accessing labels for elements. */
    @Inject extension LabelExtensions
    /** Marking elements. */
    @Inject extension MarkerExtensions
    /** Further utility stuff. */
    @Inject extension MiscellaneousExtensions
    /** Interface to the Ptolemy library. */
    @Inject PtolemyInterface ptolemy
    
    /**
     * Flag indicating whether an instance of this transformation has already transformed something.
     * If so, it cannot be reused due to Xtend restrictions.
     */
    boolean alreadyUsed = false
    
    /**
     * List of warnings collected during the transformation. These will usually only be warnings about
     * actors that couldn't be instantiated.
     */
    ArrayList<IStatus> warnings = new ArrayList<IStatus>()
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Transformations
    
    /**
     * Entry point for the whole transformation business. Takes a Ptolemy MOML document's
     * root element and returns a KGraph node representing the converted model.
     * 
     * @param ptDocumentRoot the Ptolemy MOML document's root element.
     * @return the transformed KGraph node.
     * @throws IllegalStateException if this class's instance has already been used.
     */
    def KNode transform(DocumentRoot ptDocumentRoot) {
        if (alreadyUsed) {
            throw new IllegalStateException("Transformations cannot be reused.")
        }
        
        // A Ptolemy document can contain an entity or a class, so transform those and add the
        // transformed objects as the KGraph's children
        val kEntityNode = ptDocumentRoot.entity?.transform()
        if (kEntityNode != null) {
            return kEntityNode
        }
        
        val kClassNode = ptDocumentRoot.class_?.transform()
        if (kClassNode != null) {
            return kClassNode
        }
        
        return null
    }
    
    /**
     * Transforms the given Ptolemy entity and its children into a KNode.
     * 
     * @param ptEntity the Ptolemy entity to transform.
     * @return the KGraph node.
     */
    def private create kNode : KimlUtil::createInitializedNode() transform(EntityType ptEntity) {
        kNode.name = ptEntity.name
        
        // Add annotations identifying this node as having been created from a Ptolemy entity
        kNode.markAsPtolemyElement()
        kNode.addAnnotation(ANNOTATION_PTOLEMY_CLASS, ptEntity.class1)
        
        // Add properties and ports
        kNode.addProperties(ptEntity.property)
        kNode.addChildPorts(ptEntity)
        
        // Check if we have an FSM, a modal model, or a regular node
        if (ptEntity.class1.equals(ENTITY_CLASS_FSM)) {
            // Mark as a state machine and then add the required relations, links, and child
            // entities as usual
            kNode.markAsStateMachineContainer()
            
            kNode.addChildEntities(ptEntity.entity)
            kNode.addChildRelations(ptEntity.relation)
            kNode.addChildLinks(ptEntity.link)
        } else if (ptEntity.class1.equals(ENTITY_CLASS_MODAL_MODEL)
            || ptEntity.class1.equals(ENTITY_CLASS_FSM_MODAL_MODEL)) {
            
            // Mark as a state machine
            kNode.markAsStateMachineContainer()
            
            // The actual states are found in the state machine controller
            for (child : ptEntity.entity) {
                if (child.name.equals(ENTITY_NAME_MODAL_CONTROLLER)) {
                    kNode.addChildEntities(child.entity)
                    kNode.addChildRelations(child.relation)
                    kNode.addChildLinks(child.link)
                }
            }
        } else if (ptEntity.class1.equals(ENTITY_CLASS_STATE)
            || ptEntity.class1.equals(ENTITY_CLASS_FSM_STATE)) {
            
            // A modal model state may have refinements
            val refinement = ptEntity.findRefinement()
            
            if (refinement != null) {
                // We have a refinement; transform it (which has possibly already been done) and
                // copy it; then add its children to our list of children
                // Note: this code assumes that each refinement is only used once in the model. If
                // that assumption turns out to be false, we need to create a copy of the
                // transformed refinement before adding its children.
                val transformedRefinement = transform(refinement)
                kNode.children += transformedRefinement.children
                while (!transformedRefinement.ports.empty) {
                    kNode.children += transformRefinementPort(transformedRefinement.ports.get(0))
                }
                kNode.annotations += transformedRefinement.annotations
                
                // Check if the refinement is itself a state machine
                val refinementClass = transformedRefinement.getAnnotationValue(
                    ANNOTATION_PTOLEMY_CLASS).nullToEmpty()
                
                if (refinementClass.equals(ENTITY_CLASS_MODEL_CONTROLLER)
                    || refinementClass.equals(ENTITY_CLASS_FSM_MODEL_CONTROLLER)) {
                    
                    kNode.markAsStateMachineContainer()
                }
            }
        } else {
            // Add the required relations, links, and child entities
            kNode.addChildEntities(ptEntity.entity)
            kNode.addChildRelations(ptEntity.relation)
            kNode.addChildLinks(ptEntity.link)
        }
    }
    
    /**
     * Transforms the given Ptolemy class and its children into a KNode.
     * 
     * <p>Assumption: a ClassType node can only occur at the top level. If this assumption turns out
     * not to be true, we need to overload quite a bunch of methods. Which we don't want to do. So
     * it better be true.</p>
     * 
     * @param ptClass the Ptolemy class to transform.
     * @return the KGraph node.
     */
    def private create kNode : KimlUtil::createInitializedNode() transform(ClassType ptClass) {
        kNode.name = ptClass.name
        
        // Add annotations identifying this node as having been created from a Ptolemy entity
        kNode.markAsPtolemyElement()
        kNode.addAnnotation(ANNOTATION_PTOLEMY_CLASS, ptClass.^extends)
        
        // Add properties and ports
        kNode.addProperties(ptClass.property)
        kNode.addChildPorts(ptClass)
        
        // Check if we have an FSM, a modal model, or a regular node
        if (ptClass.^extends.equals(ENTITY_CLASS_FSM)) {
            // Mark as a state machine and then add the required relations, links, and child
            // entities as usual
            kNode.markAsStateMachineContainer()
            
            kNode.addChildEntities(ptClass.entity)
            kNode.addChildRelations(ptClass.relation)
            kNode.addChildLinks(ptClass.link)
        } else if (ptClass.^extends.equals(ENTITY_CLASS_MODAL_MODEL)
            || ptClass.^extends.equals(ENTITY_CLASS_FSM_MODAL_MODEL)) {
            
            // Mark as a state machine
            kNode.markAsStateMachineContainer()
            
            // The actual states are found in the state machine controller
            for (child : ptClass.entity) {
                if (child.name.equals(ENTITY_NAME_MODAL_CONTROLLER)) {
                    kNode.addChildEntities(child.entity)
                    kNode.addChildRelations(child.relation)
                    kNode.addChildLinks(child.link)
                }
            }
        } else if (ptClass.^extends.equals(ENTITY_CLASS_MODAL_MODEL)
            || ptClass.^extends.equals(ENTITY_CLASS_FSM_MODAL_MODEL)) {
            
            // A modal model state may have refinements
            val refinement = ptClass.findRefinement()
            
            if (refinement != null) {
                // We have a refinement; transform it (which has possibly already been done) and
                // copy it; then add its children to our list of children
                // Note: this code assumes that each refinement is only used once in the model. If
                // that assumption turns out to be false, we need to create a copy of the
                // transformed refinement before adding its children.
                val transformedRefinement = transform(refinement)
                kNode.children += transformedRefinement.children
                while (!transformedRefinement.ports.empty) {
                    kNode.children += transformRefinementPort(transformedRefinement.ports.get(0))
                }
                kNode.annotations += transformedRefinement.annotations
                
                // Check if the refinement is itself a state machine
                val refinementClass = transformedRefinement.getAnnotationValue(
                    ANNOTATION_PTOLEMY_CLASS).nullToEmpty()
                
                if (refinementClass.equals(ENTITY_CLASS_MODEL_CONTROLLER)
                    || refinementClass.equals(ENTITY_CLASS_FSM_MODEL_CONTROLLER)) {
                    
                    kNode.markAsStateMachineContainer()
                }
            }
        } else {
            // Add the required relations, links, and child entities
            kNode.addChildEntities(ptClass.entity)
            kNode.addChildRelations(ptClass.relation)
            kNode.addChildLinks(ptClass.link)
        }
    }
    
    /**
     * Transforms the given Ptolemy relation into a KNode and marks it as being a hypernode.
     * 
     * @param ptRelation the Ptolemy relation to transform.
     * @return the KGraph node.
     */
    def private create kNode : KimlUtil::createInitializedNode() transform(RelationType ptRelation) {
        kNode.name = ptRelation.name
        
        // Add annotation identifying this relation as having been created from a Ptolemy relation
        kNode.markAsPtolemyElement()
        kNode.markAsHypernode()
        
        // Add the relation's properties
        kNode.addProperties(ptRelation.property)
    }
    
    /**
     * Transforms the given Ptolemy link into a KGraph edge. The problem here is that a Ptolemy link
     * is (1) undirected and (2) has multiple possible attributes for head and tail.
     * 
     * <p>The former is ignored at this point: the edge is simply annotated as not having had its
     * direction determined yet, which is one of the responsibilities of the second part of the
     * transformation.</p>
     * 
     * <p>The latter comes from the fact that an edge can connect either a port and a relation, or two
     * relations. We solve that by collecting all the elements incident to this link, hoping that there
     * are only two (if that is not the case, we leave the edge unconnected). The two are then
     * connected to the transformed edge. Their order is not relevant, since the edge's
     * direction is currently unknown anyway.</p>
     * 
     * <p>Note: This method expects relations of the {@code kParent} to already have been
     * transformed.</p>
     * 
     * @param ptLink the Ptolemy link to transform.
     * @param kaomParent the link's parent entity, with relations already transformed.
     * @return the transformed KAOM link.
     */
    def private create kEdge : KimlUtil::createInitializedEdge() transform(
        LinkType ptLink, KNode kParent) {
        
        // Fetch the relations and ports this link connects (since we cannot always get reliable port
        // information from all actors, the port might not exist yet and would then be created)
        val kRelation = kParent.children.findFirst(r | r.name.equals(ptLink.relation))
        val kRelation1 = kParent.children.findFirst(r | r.name.equals(ptLink.relation1))
        val kRelation2 = kParent.children.findFirst(r | r.name.equals(ptLink.relation2))
        val kPort =
            if (ptLink.port == null) {
                null
            } else {
                kParent.getOrCreatePortByName(ptLink.port)
            }
        val endpoints = new ArrayList<KGraphElement>()
        
        // Add annotation identifying this edge as having been created from a Ptolemy link
        kEdge.markAsPtolemyElement()
        
        // Add the ports and relations that are != null and hope that there's only two of them, leaving
        // the link unconnected if that is not the case (which should never happen, at least not for
        // valid Ptolemy models)
        if (kRelation != null) {
            endpoints.add(kRelation)
        }
        
        if (kRelation1 != null) {
            endpoints.add(kRelation1)
        }
        
        if (kRelation2 != null) {
            endpoints.add(kRelation2)
        }
        
        if (kPort != null) {
            endpoints.add(kPort)
        }
        
        // If there are exactly two endpoints, let the edge connect them. We're not interested in the
        // edge's direction yet; edge directions are inferred later, during the optimization phase of
        // the transformation. If there are more or less than two endpoints, we leave the edge
        // unconnected; it thereby isn't added to the transformed KGraph model
        if (endpoints.size() == 2) {
            // We need to check whether we have nodes (representing the relations) or also a port
            if (endpoints.get(0) instanceof KNode) {
                kEdge.source = endpoints.get(0) as KNode
            } else if (endpoints.get(0) instanceof KPort) {
                kEdge.source = (endpoints.get(0) as KPort).node
                kEdge.sourcePort = endpoints.get(0) as KPort
            }
            
            if (endpoints.get(1) instanceof KNode) {
                kEdge.target = endpoints.get(1) as KNode
            } else if (endpoints.get(1) instanceof KPort) {
                kEdge.target = (endpoints.get(1) as KPort).node
                kEdge.targetPort = endpoints.get(1) as KPort
            }
        }
        kEdge.markAsUndirected(true)
    }
    
    /**
     * Transforms the given Ptolemy port into a KPort.
     * 
     * @param ptPort the Ptolemy port to transform.
     * @return the KPort.
     */
    def private create kPort : KimlUtil::createInitializedPort() transform(PortType ptPort) {
        kPort.name = ptPort.name
        
        // Add annotation identifying this port as having been created from a Ptolemy port
        kPort.markAsPtolemyElement()
        kPort.addAnnotation(ANNOTATION_PTOLEMY_CLASS, ptPort.class_)
        
        // Add the port's properties, which might add "input" / "output" annotations
        kPort.addProperties(ptPort.property)
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Port Fetching and Creation
    
    /**
     * Looks for a port with the given name under the given parent node. The port name may be of the
     * following two forms:
     * 
     * <ol>
     *   <li>{@code portName}<br/>
     *       In this simple form, the port is assumed to belong to the parent entity. If the port does
     *       not exist, it is created and added to the parent entity.
     *   </li>
     *   <li>{@code actorName.portName}<br/>
     *       In this extended form, the port is assumed to belong to an actor of the given name. The
     *       actor must be a direct child of the parent entity. If the actor doesn't have a port of the
     *       given name, it is created and added to the model. If an actor of the given name doesn't
     *       exist, the model is not modified.
     *   </li>
     * </ol>
     * 
     * @param kparent the node to look for ports and actors in.
     * @param name the name of the port to find or create.
     * @return the port.
     * @throws CoreException if the port name does not follow the expected format, or if the referenced
     *                       actor could not be found.
     */
    def private KPort getOrCreatePortByName(KNode kparent, String name) throws CoreException {
        // Split the name into its two parts
        val nameParts = newArrayList(name.split(PORT_NAME_SEPARATOR_REGEX))
        
        // Check if nameParts has the correct size (1 or 2)
        if (nameParts.size() < 1 || nameParts.size() > 2) {
            throw new CoreException(new Status(
                IStatus::ERROR,
                PluginConstants::PLUGIN_ID,
                "Port name format not recognized: %1".replace("%1", name),
                null
            ))
        }
        
        // Find the actor
        val kActor = switch nameParts.size() {
            case 1:
                // We only have a port name; use the parent entity as the actor
                kparent
            
            case 2:
                // We have an actor name; try to find it
                kparent.children.findFirst(a | a.name.equals(nameParts.get(0)))
        }
        
        // If the actor is null, raise an error!
        if (kActor == null) {
            throw new CoreException(new Status(
                IStatus::ERROR,
                PluginConstants::PLUGIN_ID,
                "Port name references unknown actor: %1".replace("%1", name),
                null
            ))
        }
        
        // Find the port
        val portName = nameParts.get(nameParts.size() - 1)
        val port = kActor.ports.findFirst(p | p.name.equals(portName))
        
        // If the port is null, create it
        if (port == null) {
            createPort(kActor, portName, kActor.ports.size)
        } else {
            port
        }
    }
    
    /**
     * Creates a port with the given name and adds it to the given node.
     * 
     * @param kNode the node to create the port for.
     * @param name the name of the port to create.
     * @return the created port.
     */
    def private KPort createPort(KNode kNode, String name, int index) {
        // Create a new port
        val result = KimlUtil::createInitializedPort()
        result.layout.setProperty(LayoutOptions::PORT_INDEX, index)
        
        // Assign name and language annotation
        result.name = name
        result.markAsPtolemyElement()
        result.node = kNode
        
        result
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Handling Ports of State Refinements
    
    /**
     * Takes the given port of a state refinement and creates a node for it that the port is moved to.
     * This is necessary since states don't have hierarchical ports, but state refinements sometimes
     * need them. We thus display such ports as nodes with special rendering, just like in Ptolemy.
     * 
     * @param port the port to turn into a node.
     * @return the node with the port attached.
     */
    def private KNode transformRefinementPort(KPort port) {
        val node = KimlUtil::createInitializedNode()
        node.ports.add(port)
        
        // Mark the node as being a representation of a modal model port
        node.markAsModalModelPort()
        
        // Find the type of the port
        val inputPort = port.hasAnnotation("input")
        val outputPort = port.hasAnnotation("output")
        val multiPort = port.hasAnnotation("multiport")
        
        if (inputPort && !outputPort) {
            node.markAsInputPort(true)
            
            port.markAsInputPort(false)
            port.markAsOutputPort(true)
        } else if (!inputPort && outputPort) {
            node.markAsOutputPort(true)
            
            port.markAsOutputPort(false)
            port.markAsInputPort(true)
        } else if (inputPort && outputPort) {
            // TODO We could well create a second port for this case
            node.markAsInputPort(true)
            node.markAsOutputPort(true)
            
            port.markAsInputPort(true)
            port.markAsOutputPort(true)
        }
        
        if (multiPort) {
            node.addAnnotation("multiport")
        }
        
        // Find all incident edges and reroute them to the port's new node
        for (edge : port.edges) {
            if (edge.sourcePort == port) {
                edge.source = node
            }
            
            if (edge.targetPort == port) {
                edge.target = node
            }
        }
        
        return node
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Transformation of Properties and Children
    
    /**
     * Recursively transforms the given list of properties and adds them to the given annotatable
     * object.
     * 
     * @param annotatable object to add the transformed properties to.
     * @param ptProperties list of properties to transform.
     */
    def private addProperties(KGraphElement element, List<PropertyType> ptProperties) {
        element.annotations.addAll(ptProperties)
    }
    
    /**
     * Transforms the given list of entities and adds them to the given parent entity.
     * 
     * @param parent the new parent node of the transformed entities.
     * @param ptEntities list of entities to transform
     */
    def private void addChildEntities(KNode parent, EntityType[] ptEntities) {
        for (ptEntity : ptEntities) {
            // Transform the entity and add the result to the new parent
            parent.children.add(transform(ptEntity))
        }
    }
    
    /**
     * Transforms the given list of relations and adds them to the given parent entity.
     * 
     * @param parent the new parent node of the transformed relations.
     * @param ptRelations list of relations to transform
     */
    def private void addChildRelations(KNode parent, RelationType[] ptRelations) {
        for (ptRelation : ptRelations) {
            // Transform the relation and add the result to the new parent
            parent.children.add(transform(ptRelation))
        }
    }
    
    /**
     * Transforms the given list of links and adds them to the given parent node.
     * 
     * @param parent the node containing the relations and actors connected by the links once they
     *               are transformed.
     * @param ptLinks list of links to transform
     */
    def private void addChildLinks(KNode kParent, LinkType[] ptLinks) {
        for (ptLink : ptLinks) {
            // Transform the link and add the result to the new parent
            transform(ptLink, kParent)
        }
    }
    
    /**
     * Adds ports to the given node created from the given Ptolemy entity or class.
     * 
     * <p>This is no trivial task. Ports are not necessarily explicitly defined objects in a Ptolemy
     * model. Usually, they are created implicitly in an actor's Java implementation. Hence, to see
     * what ports an entity has, we need to try to instantiate it in Ptolemy.</p>
     * 
     * <p>If the actor is not available in KIELER's Ptolemy library, instantiating an actor to check
     * for its ports will of course fail, leaving the entity without ports. To handle these cases, such
     * ports are created once they are referenced later on. However, their specific attributes (e.g.,
     * whether they are input ports or output ports) will then be unavailable, which in turn may cause
     * link directions to be incorrectly inferred.</p>
     * 
     * @param knode the node to add the ports to.
     * @param entityOrClass the entity or class that was transformed into the knode.
     */
    def private void addChildPorts(KNode knode, EObject entityOrClass) {
        // Get the list of ports defined by the entity's Java implementation, if any
        val ports = new ArrayList<KPort>()
        
        try {
            ports.addAll(ptolemy.getPortsFromImplementation(entityOrClass))
        } catch (Exception e) {
            warnings.add(new Status(
                IStatus::WARNING,
                PluginConstants::PLUGIN_ID,
                e.message,
                e))
        }
        
        // Get the list of ports explicitly defined in the model
        val modelPorts =
            if (entityOrClass instanceof EntityType) {
                (entityOrClass as EntityType).port.map(p | p.transform())
            } else if (entityOrClass instanceof ClassType) {
                (entityOrClass as ClassType).port.map(p | p.transform())
            }
        
        // Merge the model port list into the implementation port list
        var index = ports.size;
        for (modelPort : modelPorts) {
            // Set the index (here we assume the original port order found in the MoML is preserved)
            modelPort.layout.setProperty(LayoutOptions::PORT_INDEX, index);
            
            // Check if a port of the same name already exists
            val existingPort = ports.findFirst(p | p.name.equals(modelPort.name))
            
            if (existingPort != null) {
                // A port exists; merge the model port's attributes into the existing port's attributes
                existingPort.annotations.addAll(modelPort.annotations)
            } else {
                // No port of that name exists, so add it
                ports.add(modelPort)
            }
            index = index + 1
        }
        
        // Add all of these ports to the knode and mark them as input or output port, if necessary
        for (port : ports) {
            port.node = knode
            
            if (!(port.markedAsInputPort || port.markedAsOutputPort)) {
                // Find out whether it is an input or an output port (or even both)
                if (port.hasAnnotation("input") || port.hasAnnotation("inputoutput")) {
                    port.markAsInputPort(true)
                }
                
                if (port.hasAnnotation("output") || port.hasAnnotation("inputoutput")) {
                    port.markAsOutputPort(true)
                }
            }
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Return Warnings
    
    /**
     * Returns the list of warnings produced by the transformation.
     */
    def List<IStatus> getWarnings() {
        warnings
    }
}