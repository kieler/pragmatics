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
package de.cau.cs.kieler.kaom.importer.ptolemy.improved.xtend


import com.google.inject.Inject
import java.util.ArrayList
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
import de.cau.cs.kieler.kaom.KaomFactory
import de.cau.cs.kieler.kaom.Port


/**
 * Provides an interface to the Ptolemy library to instantiate actors. This is used during the
 * transformation to get our hands at the list of ports defined for an actor.
 * 
 * @author cds
 * @author haf
 */
class PtolemyInterface {
    
    /**
     * Utility methods.
     */
    @Inject extension TransformationUtils
    
    
    /**
     * Tries to instantiate the given entity to return a list of its ports. The entity must either be
     * an {@code EntityType} or a {@code ClassType}. If the entity could not be instantiated, an empty
     * list is returned.
     * 
     * @param entity description of the entity.
     * @return list of ports which will be empty if the entity could not be instantiated.
     */
	def List<Port> getPortsFromImplementation(EObject entity) {
	    // Create an empty list of ports which we'll add to
	    val result = new ArrayList<Port>()
	    
	    // Try to instantiate the actor
	    var Entity actor = null
	    
	    try {
	        actor = instantiatePtolemyEntity(entity)
	    } catch (Exception e) {
	        // Nothing to be done here
	    }
	    
	    // Add its ports
	    if (actor != null) {
            for (o : actor.portList) {
                if (o instanceof IOPort) {
                    val IOPort ptPort = o as IOPort
                    val Port kaomPort = KaomFactory::eINSTANCE.createPort()
                    
                    // Find our whether it is an input or an output port (or even both)
                    if (ptPort.input) {
                        kaomPort.markAsInputPort()
                    }
                    
                    if (ptPort.output) {
                        kaomPort.markAsOutputPort()
                    }
                    
                    // Set the name
                    kaomPort.name = ptPort.name
                    kaomPort.addStringAnnotation("language", "ptolemy")
                    
                    // Copy attributes
                    for (attribute : ptPort.attributeList) {
                        if (attribute instanceof Attribute) {
                            turnAttributeIntoAnnotation(kaomPort, attribute as Attribute)
                        }
                    }
                    
                    // Add the created port to our result list
                    result.add(kaomPort)
                }
            }
	    }
	    
	    // Return the list of ports
	    result
	}
	
	/**
	 * Makes an annotation out of the given attribute and attaches it to the given annotatable object.
	 * Recursively adds attributes of the attributes to the correspondingly created annotations.
	 * 
	 * @param annotatable the object to annotate with the transformed attribute.
	 * @param ptAttribute the attribute to turn into an annotation
	 */
	def private void turnAttributeIntoAnnotation(Annotatable annotatable, Attribute ptAttribute) {
	    // Create an annotation for the attribute
	    val kaomAnnotation = AnnotationsFactory::eINSTANCE.createTypedStringAnnotation()
	    
	    kaomAnnotation.name = ptAttribute.name
	    kaomAnnotation.type = ptAttribute.className
	    
	    // If the attribute is a StringAttribute, assign its value
	    if (ptAttribute instanceof StringAttribute) {
	        kaomAnnotation.value = (ptAttribute as StringAttribute).valueAsString
	    }
	    
	    // Add the annotation
	    annotatable.annotations.add(kaomAnnotation)
	    
	    // Recursively add further attributes
	    for (attribute : ptAttribute.attributeList) {
            if (attribute instanceof Attribute) {
                turnAttributeIntoAnnotation(kaomAnnotation, attribute as Attribute)
            }
	    }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Actor Instantiation
    
    /**
     * Tries to instantiate the entity referenced by the given entity type.
     * 
     * @param ptEntity entity type describing the entity to instantiate.
     */
    def private dispatch Entity instantiatePtolemyEntity(EntityType ptEntity) {
        val className = ptEntity.class1;
        
        if (className.equals("ptolemy.domains.modal.kernel.State")) {
            instantiatePtolemyState(className, ptEntity.name)
        } else {
            instantiatePtolemyActor(className, ptEntity.name)
        }
    }
    
    /**
     * Tries to instantiate the entity referenced by the given class type.
     * 
     * @param ptClass class type describing the entity to instantiate.
     */
    def private dispatch Entity instantiatePtolemyEntity(ClassType ptClass) {
        val className = ptClass.^extends
        
        if (className.equals("ptolemy.domains.modal.kernel.State")) {
            instantiatePtolemyState(className, ptClass.name)
        } else {
            instantiatePtolemyActor(className, ptClass.name)
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
     * @throws Exception may throw different exceptions during parsing.
     */
    def private Entity instantiatePtolemyActor(String className, String entityName) throws Exception {
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
     * @throws Exception may throw different exceptions during parsing.
     */
    def private Entity instantiatePtolemyState(String className, String entityName) throws Exception {
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
        
        // Parse XML
        val NamedObj parentElement = parser.parse(xml.toString())
        (parentElement as CompositeEntity).entityList().get(0) as Entity
    }
}