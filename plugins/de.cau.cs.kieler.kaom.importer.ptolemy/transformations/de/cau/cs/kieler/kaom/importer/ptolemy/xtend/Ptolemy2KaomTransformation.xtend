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

import de.cau.cs.kieler.core.annotations.Annotatable
import de.cau.cs.kieler.core.annotations.AnnotationsFactory
import de.cau.cs.kieler.core.annotations.TypedStringAnnotation
import de.cau.cs.kieler.kaom.Entity
import de.cau.cs.kieler.kaom.KaomFactory
import de.cau.cs.kieler.kaom.Linkable
import de.cau.cs.kieler.kaom.Port
import de.cau.cs.kieler.kaom.importer.ptolemy.Messages
import de.cau.cs.kieler.kaom.importer.ptolemy.PtolemyImportPlugin
import de.cau.cs.kieler.kaom.importer.ptolemy.xtend.utils.TransformationUtils

import static de.cau.cs.kieler.kaom.importer.ptolemy.PtolemyImportConstants.*


/**
 * Transforms a Ptolemy2 model to a KAOM model. This is part one of the Ptolemy model import process,
 * with part two consisting of the optimization of the transformed model as defined in
 * {@code Ptolemy2KaomOptimization}.
 * 
 * <p>In this part, all Ptolemy entities, relations and links are transformed to their KAOM
 * counterparts. The transformed objects are annotated to originate from a Ptolemy2 model. This
 * is fairly straightforward, except for the ports of actors. During the transformation, actors
 * must be instantiated using the KIELER Ptolemy library to find their ports. The problem is that
 * if an actor is not part of the official Ptolemy actor library, the instantiation often fails,
 * leaving us without properly defined ports. In particular, we then cannot determine if a port
 * is an input port or an output port, which leads to problems when the directions of links are
 * inferred in the second part of the import process. (contrary to KAOM links, Ptolemy links are
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
class Ptolemy2KaomTransformation {
    
    /**
     * Extensions used during the transformation. To make things easier. And stuff.
     */
    @Inject extension TransformationUtils
    
    /**
     * Interface to the Ptolemy library.
     */
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
     * root element and returns a KAOM entity representing the converted model.
     * 
     * @param ptDocumentRoot the Ptolemy MOML document's root element.
     * @return the transformed KAOM entity.
     * @throws IllegalStateException if this class's instance has already been used.
     */
    def create result : KaomFactory::eINSTANCE.createEntity() transform(DocumentRoot ptDocumentRoot) {
        if (alreadyUsed) {
            throw new IllegalStateException("Transformations cannot be reused.");
        }
        
        // A Ptolemy document can contain an entity or a class, so transform those and add the
        // transformed objects as the KAOM entity's children
        val kaomEntity = ptDocumentRoot.entity?.transform()
        if (kaomEntity != null) {
            result.childEntities.add(kaomEntity)
        }
        
        val kaomClass = ptDocumentRoot.class_?.transform()
        if (kaomClass != null) {
            result.childEntities.add(kaomClass)
        }
    }
    
    /**
     * Transforms the given Ptolemy entity and its children into a KAOM entity.
     * 
     * @param ptEntity the Ptolemy entity to transform.
     * @return the KAOM entity.
     */
    def private create result : KaomFactory::eINSTANCE.createEntity() transform(EntityType ptEntity) {
        result.name = ptEntity.name
        
        // Add annotations identifying this entity as having been created from a Ptolemy entity
        result.markAsPtolemyElement()
        result.addStringAnnotation(ANNOTATION_PTOLEMY_CLASS, ptEntity.class1)
        
        // Add the entity's ports, relations, links, and child entities
        result.addProperties(ptEntity.property)
        result.addChildPorts(ptEntity)
        result.addChildEntities(ptEntity.entity)
        result.addChildRelations(ptEntity.relation)
        result.addChildLinks(ptEntity.link)
    }
    
    /**
     * Transforms the given Ptolemy class and its children into a KAOM entity.
     * 
     * <p>Assumption: a ClassType node can only occur at the top level. If this assumption turns out
     * not to be true, we need to overload quite a bunch of methods. Which we don't want to do. So
     * it better be true.</p>
     * 
     * @param ptClass the Ptolemy class to transform.
     * @return the KAOM entity.
     */
    def private create result : KaomFactory::eINSTANCE.createEntity() transform(ClassType ptClass) {
        result.name = ptClass.name
        
        // Add annotations identifying this entity as having been created from a Ptolemy entity
        result.markAsPtolemyElement()
        result.addStringAnnotation(ANNOTATION_PTOLEMY_CLASS, ptClass.^extends)
        
        // Add the entity's ports, relations, links, and child entities
        result.addProperties(ptClass.property)
        result.addChildPorts(ptClass)
        result.addChildEntities(ptClass.entity)
        result.addChildRelations(ptClass.relation)
        result.addChildLinks(ptClass.link)
    }
    
    /**
     * Transforms the given Ptolemy relation into a KAOM relation.
     * 
     * @param ptRelation the Ptolemy relation to transform.
     * @return the KAOM relation.
     */
    def private create result : KaomFactory::eINSTANCE.createRelation() transform(
        RelationType ptRelation) {
        
        result.name = ptRelation.name
        
        // Add annotation identifying this relation as having been created from a Ptolemy relation
        result.markAsPtolemyElement()
        
        // Add the relation's properties
        result.addProperties(ptRelation.property)
    }
    
    /**
     * Transforms the given Ptolemy link into a KAOM link. The problem here is that a Ptolemy link
     * is (1) undirected and (2) has multiple possible attributes for head and tail.
     * 
     * <p>The former is ignored at this point: the link is simply annotated as not having had its
     * direction determined yet, which is one of the responsibilities of the second part of the
     * transformation.</p>
     * 
     * <p>The latter comes from the fact that a link can connect either a port and a relation, or two
     * relations. We solve that by collecting all the elements incident to this link, hoping that there
     * are only two (if that is not the case, we leave the link unconnected). The two are then
     * connected to the transformed link element. Their order is not relevant, since the link's
     * direction is currently unknown anyway.</p>
     * 
     * <p>Note: This method expects relations of the {@code kaomParent} to already have been
     * transformed.</p>
     * 
     * @param ptLink the Ptolemy link to transform.
     * @param kaomParent the link's parent entity, with relations already transformed.
     * @return the transformed KAOM link.
     */
    def private create result : KaomFactory::eINSTANCE.createLink() transform(
        LinkType ptLink, Entity kaomParent) {
        
        // Fetch the relations and ports this link connects (since we cannot always get reliable port
        // information from all actors, the port might not exist yet and would then be created)
        val relation = kaomParent.childRelations.findFirst(r | r.name.equals(ptLink.relation))
        val relation1 = kaomParent.childRelations.findFirst(r | r.name.equals(ptLink.relation1))
        val relation2 = kaomParent.childRelations.findFirst(r | r.name.equals(ptLink.relation2))
        val port =
            if (ptLink.port == null) {
                null
            } else {
                kaomParent.getOrCreatePortByName(ptLink.port)
            }
        val endpoints = new ArrayList<Linkable>()
        
        // Add annotation identifying this link as having been created from a Ptolemy link
        result.markAsPtolemyElement()
        
        // Add the ports and relations that are != null and hope that there's only two of them, leaving
        // the link unconnected if that is not the case (which should never happen, at least not for
        // valid Ptolemy models)
        if (relation != null) {
            endpoints.add(relation)
        }
        
        if (relation1 != null) {
            endpoints.add(relation1)
        }
        
        if (relation2 != null) {
            endpoints.add(relation2)
        }
        
        if (port != null) {
            endpoints.add(port)
        }
        
        // If there are exactly two endpoints, let the link connect them. We're not interested in the
        // link's direction yet; link directions are inferred later, during the optimization phase of
        // the transformation
        if (endpoints.size() == 2) {
            result.source = endpoints.get(0)
            result.target = endpoints.get(1)
        }
        result.markAsUndirected(true)
    }
    
    /**
     * Transforms the given Ptolemy port into a KAOM port.
     * 
     * @param ptPort the Ptolemy port to transform.
     * @return the KAOM port.
     */
    def private create result : KaomFactory::eINSTANCE.createPort() transform(PortType ptPort) {
        result.name = ptPort.name
        
        // Add annotation identifying this port as having been created from a Ptolemy port
        result.markAsPtolemyElement()
        
        // Add the port's properties, which might add "input" / "output" annotations
        result.addProperties(ptPort.property)
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Port Fetching and Creation
    
    /**
     * Looks for a port with the given name under the given parent entity. The port name may be of the
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
     * @param kaomParent the entity to look for ports and actors in.
     * @param name the name of the port to find or create.
     * @return the port.
     * @throws CoreException if the port name does not follow the expected format, or if the referenced
     *                       actor could not be found.
     */
    def private Port getOrCreatePortByName(Entity kaomParent, String name) throws CoreException {
        // Split the name into its two parts
        val nameParts = newArrayList(name.split(PORT_NAME_SEPARATOR_REGEX))
        
        // Check if nameParts has the correct size (1 or 2)
        if (nameParts.size() < 1 || nameParts.size() > 2) {
            throw new CoreException(new Status(
                IStatus::ERROR,
                PtolemyImportPlugin::PLUGIN_ID,
                Messages::PtolemyTransformation_exception_malformedPortName.replace("%1", name),
                null
            ))
        }
        
        // Find the actor
        val actor = switch nameParts.size() {
            case 1:
                // We only have a port name; use the parent entity as the actor
                kaomParent
            
            case 2:
                // We have an actor name; try to find it
                kaomParent.childEntities.findFirst(a | a.name.equals(nameParts.get(0)))
        }
        
        // If the actor is null, raise an error!
        if (actor == null) {
            throw new CoreException(new Status(
                IStatus::ERROR,
                PtolemyImportPlugin::PLUGIN_ID,
                Messages::PtolemyTransformation_exception_portReferencesUnknownActor.replace("%1", name),
                null
            ))
        }
        
        // Find the port
        val portName = nameParts.get(nameParts.size() - 1)
        val port = actor.childPorts.findFirst(p | p.name.equals(portName))
        
        // If the port is null, create it
        if (port == null) {
            createPort(actor, portName)
        } else {
            port
        }
    }
    
    /**
     * Creates a port with the given name and adds it to the entity's list of ports.
     * 
     * @param kaomEntity the entity to create the port for.
     * @param name the name of the port to create.
     * @return the created port.
     */
    def private Port createPort(Entity kaomEntity, String name) {
        // Create a new port
        val result = KaomFactory::eINSTANCE.createPort()
        
        // Assign name and language annotation
        result.name = name;
        result.markAsPtolemyElement()
        kaomEntity.childPorts.add(result)
        
        result
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
    def private addProperties(Annotatable annotatable, List<PropertyType> ptProperties) {
        // Transform all properties
        for (ptProperty : ptProperties) {
            val TypedStringAnnotation propertyAnnotation =
                AnnotationsFactory::eINSTANCE.createTypedStringAnnotation()
            
            // Save the name, value, and class of the annotation
            propertyAnnotation.name = ptProperty.name
            propertyAnnotation.value = ptProperty.value
            propertyAnnotation.type = ptProperty.class_
            
            // Recursively add the property's properties
            propertyAnnotation.addProperties(ptProperty.property)
            
            // Add the property annotation
            annotatable.annotations.add(propertyAnnotation)
        }
    }
    
    /**
     * Transforms the given list of entities and adds them to the given parent entity.
     * 
     * @param kaomParent the new parent entity of the transformed entities.
     * @param ptEntities list of entities to transform
     */
    def private void addChildEntities(Entity kaomParent, EntityType[] ptEntities) {
        for (ptEntity : ptEntities) {
            // Transform the entity and add the result to the new parent
            kaomParent.childEntities.add(transform(ptEntity))
        }
    }
    
    /**
     * Transforms the given list of relations and adds them to the given parent entity.
     * 
     * @param kaomParent the new parent entity of the transformed relations.
     * @param ptRelations list of relations to transform
     */
    def private void addChildRelations(Entity kaomParent, RelationType[] ptRelations) {
        for (ptRelation : ptRelations) {
            // Transform the relation and add the result to the new parent
            kaomParent.childRelations.add(transform(ptRelation))
        }
    }
    
    /**
     * Transforms the given list of links and adds them to the given parent entity.
     * 
     * @param kaomParent the new parent entity of the transformed links.
     * @param ptLinks list of links to transform
     */
    def private void addChildLinks(Entity kaomParent, LinkType[] ptLinks) {
        for (ptLink : ptLinks) {
            // Transform the link and add the result to the new parent
            kaomParent.childLinks.add(transform(ptLink, kaomParent))
        }
    }
    
    /**
     * Adds the entity's ports to it.
     * 
     * <p>This is no trivial task. Ports are not necessarily explicitly defined objects in a Ptolemy
     * model. Usually, they are created implicitly in an actor's Java implementation. Hence, to see
     * what ports an entity has, we need to instantiate it in Ptolemy.</p>
     * 
     * <p>If the actor is not available in KIELER's Ptolemy library, instantiating an actor to check
     * for its ports will of course fail, leaving the entity without ports. To handle these cases, such
     * ports are created once they are referenced later on. However, their specific attributes (e.g.,
     * whether they are input ports or output ports) will then be unavailable, which in turn may cause
     * link directions to be incorrectly inferred.</p>
     * 
     * @param kaomEntity the entity to add the ports to.
     * @param entityOrClass the entity or class that was transformed into the KAOM entity.
     */
    def private void addChildPorts(Entity kaomEntity, EObject entityOrClass) {
        // Get the list of ports defined by the entity's Java implementation, if any
        val ports = new ArrayList<Port>()
        
        try {
            ports.addAll(ptolemy.getPortsFromImplementation(entityOrClass))
        } catch (Exception e) {
            warnings.add(new Status(
                IStatus::WARNING,
                PtolemyImportPlugin::PLUGIN_ID,
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
        for (modelPort : modelPorts) {
            // Check if a port of the same name already exists
            val existingPort = ports.findFirst(p | p.name.equals(modelPort.name))
            
            if (existingPort != null) {
                // A port exists; merge the model port's attributes into the existing port's attributes
                existingPort.annotations.addAll(modelPort.annotations)
            } else {
                // No port of that name exists, so add it
                ports.add(modelPort)
            }
        }
        
        // Add all of these ports to the kaom entity
        kaomEntity.childPorts.addAll(ports)
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