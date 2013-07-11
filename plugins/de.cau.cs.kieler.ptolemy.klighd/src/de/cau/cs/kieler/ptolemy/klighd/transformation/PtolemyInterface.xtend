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
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.ptolemy.moml.ClassType
import org.ptolemy.moml.EntityType
import ptolemy.actor.IOPort
import ptolemy.actor.TypedCompositeActor
import ptolemy.kernel.CompositeEntity
import ptolemy.kernel.Entity
import ptolemy.kernel.util.Attribute
import ptolemy.kernel.util.NamedObj
import ptolemy.kernel.util.StringAttribute
import ptolemy.moml.MoMLParser
import ptolemy.moml.filter.BackwardCompatibility
import de.cau.cs.kieler.core.annotations.Annotatable
import de.cau.cs.kieler.core.annotations.AnnotationsFactory
import de.cau.cs.kieler.kiml.util.KimlUtil
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.core.kgraph.KGraphElement
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IStatus
import org.eclipse.core.runtime.Status
import de.cau.cs.kieler.ptolemy.klighd.PluginConstants

/**
 * Provides an interface to the Ptolemy library to instantiate actors. This is used during the
 * transformation to get our hands at the list of ports defined for an actor.
 * 
 * @author cds
 * @author haf
 * @kieler.rating yellow 2012-06-14 KI-12 cmot, grh
 */
class PtolemyInterface {
    
    /** Accessing annotations. */
    @Inject extension AnnotationExtensions
    /** Marking nodes. */
    @Inject extension MarkerExtensions
    /** Labeling nodes and ports. */
    @Inject extension LabelExtensions
    
    /**
     * A cache mapping qualified class names of Ptolemy actors to their actual instances. If an actor
     * was already instantiated, there's no need to instantiate it again since that's quite a bit of
     * work.
     */
    static HashMap<String, Entity> entityCache = new HashMap<String, Entity>()
    
    
    /**
     * Tries to instantiate the given entity to return a list of its ports. The entity must either be
     * an {@code EntityType} or a {@code ClassType}. If the entity could not be instantiated, an empty
     * list is returned.
     * 
     * @param entity description of the entity.
     * @return list of ports which will be empty if the entity could not be instantiated.
     * @throws Exception if the instantiation fails.
     */
    def List<KPort> getPortsFromImplementation(EObject entity) {
        // Create an empty list of ports which we'll add to
        val result = new ArrayList<KPort>()
        
        // Try to instantiate the actor (this is where an exception might be thrown which is propagated
        // up to the caling method)
        var Entity actor = null
        actor = instantiatePtolemyEntity(entity)
        
        // Add its ports
        if (actor != null) {
            for (port : actor.portList) {
                if (port instanceof IOPort) {
                    val IOPort ptPort = port as IOPort
                    val KPort kgraphPort = KimlUtil::createInitializedPort()
                    
                    // Find out whether it is an input or an output port (or even both)
                    if (ptPort.input) {
                        kgraphPort.markAsInputPort()
                    }
                    
                    if (ptPort.output) {
                        kgraphPort.markAsOutputPort()
                    }
                    
                    // TODO: Set the name
                    kgraphPort.name = ptPort.name
                    kgraphPort.markAsPtolemyElement()
                    
                    // Copy attributes
                    for (attribute : ptPort.attributeList) {
                        if (attribute instanceof Attribute) {
                            turnAttributeIntoAnnotation(kgraphPort, attribute as Attribute)
                        }
                    }
                    
                    // Add the created port to our result list
                    result.add(kgraphPort)
                }
            }
        }
        
        // Return the list of ports
        result
    }
    
    /**
     * Makes an annotation out of the given attribute and attaches it to the given KGraph element.
     * Recursively adds attributes of the attributes to the correspondingly created annotations.
     * 
     * @param element the KGraph element to annotate with the transformed attribute.
     * @param ptAttribute the attribute to turn into an annotation
     */
    def private void turnAttributeIntoAnnotation(KGraphElement element, Attribute ptAttribute) {
        // Create an annotation for the attribute
        val kgraphAnnotation = AnnotationsFactory::eINSTANCE.createTypedStringAnnotation()
        
        kgraphAnnotation.name = ptAttribute.name
        kgraphAnnotation.type = ptAttribute.className
        
        // If the attribute is a StringAttribute, assign its value
        if (ptAttribute instanceof StringAttribute) {
            kgraphAnnotation.value = (ptAttribute as StringAttribute).valueAsString
        }
        
        // Add the annotation
        getAnnotations(element).add(kgraphAnnotation)
        
        // Recursively add further attributes
        for (attribute : ptAttribute.attributeList) {
            if (attribute instanceof Attribute) {
                turnAttributeIntoAnnotation(kgraphAnnotation, attribute as Attribute)
            }
        }
    }
    
    /**
     * Makes an annotation out of the given attribute and attaches it to the given annotatable.
     * Recursively adds attributes of the attributes to the correspondingly created annotations.
     * 
     * @param annotatable the annotatable object to annotate with the transformed attribute.
     * @param ptAttribute the attribute to turn into an annotation
     */
    def private void turnAttributeIntoAnnotation(Annotatable annotatable, Attribute ptAttribute) {
        // Create an annotation for the attribute
        val kgraphAnnotation = AnnotationsFactory::eINSTANCE.createTypedStringAnnotation()
        
        kgraphAnnotation.name = ptAttribute.name
        kgraphAnnotation.type = ptAttribute.className
        
        // If the attribute is a StringAttribute, assign its value
        if (ptAttribute instanceof StringAttribute) {
            kgraphAnnotation.value = (ptAttribute as StringAttribute).valueAsString
        }
        
        // Add the annotation
        annotatable.annotations.add(kgraphAnnotation)
        
        // Recursively add further attributes
        for (attribute : ptAttribute.attributeList) {
            if (attribute instanceof Attribute) {
                turnAttributeIntoAnnotation(kgraphAnnotation, attribute as Attribute)
            }
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Actor Instantiation
    
    /**
     * Tries to instantiate the entity referenced by the given entity type.
     * 
     * @param ptEntity entity type describing the entity to instantiate.
     * @return the instantiated entity.
     * @throws Exception if the instantiation fails.
     */
    def dispatch Entity instantiatePtolemyEntity(EntityType ptEntity) {
        instantiatePtolemyEntityWithCache(ptEntity.class1, ptEntity.name)
    }
    
    /**
     * Tries to instantiate the entity referenced by the given class type.
     * 
     * @param ptClass class type describing the entity to instantiate.
     * @return the instantiated entity.
     * @throws Exception if the instantiation fails.
     */
    def dispatch Entity instantiatePtolemyEntity(ClassType ptClass) {
        instantiatePtolemyEntityWithCache(ptClass.^extends, ptClass.name)
    }
    
    /**
     * Tries to instantiate the entity referenced by the given class name.
     * 
     * @param className name of the class of the entity to instantiate.
     * @return the instantiated entity.
     * @throws Exception if the instantiation fails.
     */
    def dispatch Entity instantiatePtolemyEntity(String className) {
        instantiatePtolemyEntityWithCache(className, "anEntity")
    }
    
    /**
     * Instantiates the Ptolemy actor with the given class name. The entity name doesn't matter, but
     * makes errors make more sense.
     * 
     * @param className the fully qualified class name of the actor to instantiate.
     * @param entityName the actor's name in the model. Useful for error messages.
     * @return the instantiated entity.
     * @throws CoreException if the actor couldn't be instantiated.
     */
    def private Entity instantiatePtolemyEntityWithCache(String className, String entityName) {
        val cachedEntity = entityCache.get(className)
        
        if (cachedEntity == null) {
            // The entity is not already in the cache, so try to instantiate it
            try {
                if (className.equals("ptolemy.domains.modal.kernel.State")) {
                    val entity = instantiatePtolemyState(className, entityName)
                    entityCache.put(className, entity)
                    return entity
                } else {
                    val entity = instantiatePtolemyActor(className, entityName)
                    entityCache.put(className, entity)
                    return entity
                }
            } catch (Exception e) {
                // An exception occurred: wrap it
                throw new CoreException(new Status(
                    IStatus::WARNING,
                    PluginConstants::PLUGIN_ID,
                    "Unable to instantiate actor %1 (class '%2')."
                        .replace("%1", entityName)
                        .replace("%2", className),
                    e
                ))
            }
        } else {
            // The entity is already in the cache, so just return that
            cachedEntity
        }
    }
    
    /**
     * Instantiates a Ptolemy actor of the given class with the given name.
     * 
     * @param className the name of the actor's class.
     * @param entityName the name that should be used for the actor when instantiating it. This has no
     *                   influence on the functionality, but results in more readable error messages if
     *                   anything goes wrong.
     * @return the instantiated actor.
     * @throws Exception if the instantiation fails.
     */
    def private Entity instantiatePtolemyActor(String className, String entityName) {
        // Get our hands at Ptolemy's internal MoML parser
        MoMLParser::setMoMLFilters(BackwardCompatibility::allFilters())
        val parser = new MoMLParser()
        
        // We need to generate a basic MoML file with a valid parent entity and the actual actor entity
        // we want to instantiate
        val xml = '''
            <entity name="TopLevel" class="ptolemy.actor.TypedCompositeActor">
                <entity name="«entityName»" class="«className»" />
            </entity>
        '''
        
        // Parse XML
        val NamedObj parentElement = parser.parse(xml.toString())
        (parentElement as TypedCompositeActor).entityList().get(0) as Entity
    }
    
    /**
     * Instantiates a Ptolemy state entity of the given class with the given name.
     * 
     * @param className the name of the actor's class.
     * @param entityName the name that should be used for the actor when instantiating it. This has no
     *                   influence on the functionality, but results in more readable error messages if
     *                   anything goes wrong.
     * @return the instantiated actor.
     * @throws Exception if the instantiation fails.
     */
    def private Entity instantiatePtolemyState(String className, String entityName) {
        // Get our hands at Ptolemy's internal MoML parser
        MoMLParser::setMoMLFilters(BackwardCompatibility::allFilters())
        val parser = new MoMLParser()
        
        // We need to generate a basic MoML file with a valid parent entity and the actual actor entity
        // we want to instantiate
        val xml = '''
            <entity name="TopLevel" class="ptolemy.domains.modal.modal.ModalController">
                <entity name="«entityName»" class="«className»" />
            </entity>
        '''
        
        // Parse XML and return the first entity in the returned list. If the parser has a problem or
        // if the returned list is empty, an exception will be thrown which is then propagated up to
        // the calling method
        val NamedObj parentElement = parser.parse(xml.toString())
        (parentElement as CompositeEntity).entityList().get(0) as Entity
    }
}