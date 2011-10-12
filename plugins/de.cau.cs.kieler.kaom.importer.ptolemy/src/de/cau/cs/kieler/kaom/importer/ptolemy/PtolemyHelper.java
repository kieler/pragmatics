/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kaom.importer.ptolemy;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend.expression.ExecutionContext;
import org.eclipse.xtend.expression.IExecutionContextAware;
import org.ptolemy.moml.ClassType;
import org.ptolemy.moml.EntityType;

import ptolemy.actor.IOPort;
import ptolemy.actor.TypedCompositeActor;
import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.Entity;
import ptolemy.kernel.util.Attribute;
import ptolemy.kernel.util.NamedObj;
import ptolemy.kernel.util.StringAttribute;
import ptolemy.moml.MoMLParser;
import ptolemy.moml.filter.BackwardCompatibility;

import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.AnnotationsFactory;
import de.cau.cs.kieler.core.annotations.TypedStringAnnotation;
import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.Port;

/**
 * Helper methods to inspect Ptolemy models. Could be used from Xtend transformations.
 * 
 * @author haf
 * @author cds
 */
public class PtolemyHelper implements IExecutionContextAware {
    
    /**
     * Constructs a new instance.
     */
    public PtolemyHelper() {
    }
    
    
    /**
     * Returns a list of all the ports of the given entity types or class types.
     * 
     * @param entities the entity or class types whose ports to return.
     * @return list of ports.
     * @throws ClassNotFoundException if the actor could not be resolved.
     */
    public List<Port> getPorts(final List<EObject> entities) throws ClassNotFoundException {
        List<Port> ports = new BasicEList<Port>();
        
        for (EObject o : entities) {
            NamedObj actor = null;
            
            // Check what kind of object this is
            if (o instanceof EntityType) {
                try {
                    actor = instantiatePtolemyEntity((EntityType) o);
                } catch (Exception e) {
                    // Unable to instantiate the actor
                    return null;
                }
            } else if (o instanceof ClassType) {
                try {
                    actor = instantiatePtolemyEntity((ClassType) o);
                } catch (Exception e) {
                    // Unable to instantiate the actor
                    return null;
                }
            }
            
            // If the actor could be instantiated, add its ports to the list
            if (actor != null) {
                ports.addAll(getPorts(actor));
            }
        }
        
        return ports;
    }

    /**
     * Returns a list of ports of the given actor. To obtain an actor instance
     * from an entity type, class type or a simple class name, use one of the
     * {@code instantiatePtolemyEntity(...)} methods.
     * 
     * @param actor the actor whose ports to return.
     * @return list of ports.
     * @throws ClassNotFoundException if the actor could not be resolved.
     */
    public List<Port> getPorts(final NamedObj actor) throws ClassNotFoundException {
        List<Port> kaomPorts = new LinkedList<Port>();
        
        try {
            for (Object obj : ((Entity) actor).portList()) {
                if (obj instanceof IOPort) {
                    IOPort ptolemyPort = (IOPort) obj;
                    Port kaomPort = KaomFactory.eINSTANCE.createPort();
                    
                    // Find out whether it is an input or output (or both)
                    if (ptolemyPort.isInput()) {
                        Annotation isInput = AnnotationsFactory.eINSTANCE.createAnnotation();
                        isInput.setName("input"); //$NON-NLS-1$
                        kaomPort.getAnnotations().add(isInput);
                    }
                    
                    if (ptolemyPort.isOutput()) {
                        Annotation isOutput = AnnotationsFactory.eINSTANCE.createAnnotation();
                        isOutput.setName("output"); //$NON-NLS-1$
                        kaomPort.getAnnotations().add(isOutput);
                    }
                    
                    // Set the name
                    kaomPort.setName(ptolemyPort.getName());

                    for (Object attribute : ptolemyPort.attributeList()) {
                        if (attribute instanceof Attribute) {
                            kaomPort.getAnnotations().add(getAnnotation((Attribute) attribute));
                        }
                    }

                    kaomPorts.add(kaomPort);
                }
            }
        } catch (Exception e) {
            // Unable to instantiate the actor
            return null;
        }
        
        return kaomPorts;
    }

    /**
     * Instanciate a Ptolemy Entity for a given EntityType model object.
     * 
     * @param entity given EMF EntityType model object
     * @return corresponding Ptolemy object
     * @throws Exception may throw different Exceptions during parsing
     */
    public NamedObj instantiatePtolemyEntity(final EntityType entity) throws Exception {
        String classname = entity.getClass1();
        
        if (classname.equals("ptolemy.domains.modal.kernel.State")) { //$NON-NLS-1$
            return instantiatePtolemyState(entity.getClass1(), entity.getName());
        }
        
        return instantiatePtolemyActor(entity.getClass1(), entity.getName());
    }

    /**
     * Instanciate a Ptolemy Entity for a given ClassType model object.
     * 
     * @param classType given EMF ClassType model object
     * @return corresponding Ptolemy object
     * @throws Exception may throw different Exceptions during parsing
     */
    public NamedObj instantiatePtolemyEntity(final ClassType classType) throws Exception {
        String classname = classType.getExtends();
        
        if (classname.equals("ptolemy.domains.modal.kernel.State")) { //$NON-NLS-1$
            return instantiatePtolemyState(classType.getExtends(), classType.getName());
        }
        
        return instantiatePtolemyActor(classType.getExtends(), classType.getName());
    }
    
    /**
     * Instantiate a Ptolemy entity with the given class name.
     * 
     * @param className the class name of the actor to instantiate.
     * @return corresponding Ptolemy object.
     * @throws Exception may throw different exceptions during parsing.
     */
    public NamedObj instantiatePtolemyEntity(final String className) throws Exception {
        return instantiatePtolemyActor(className, "actorInstance"); //$NON-NLS-1$
    }

    /**
     * Instantiate a Ptolemy Actor Entity for a given EntityType model object.
     * 
     * @param className the class name of the actor to instantiate.
     * @param entityName a non-empty string that identifies the actor instance. This is mainly
     *                   interesting for debug messages and can be chosen more or less at
     *                   random.
     * @return corresponding Ptolemy object.
     * @throws Exception may throw different exceptions during parsing.
     */
    private NamedObj instantiatePtolemyActor(final String className, final String entityName)
            throws Exception {
        
        // use the Ptolemy internal Model ML (MoML) parser parsing XML and
        // creates Ptolemy models
        MoMLParser parser = new MoMLParser();
        MoMLParser.setMoMLFilters(BackwardCompatibility.allFilters());
        
        // atomic actors require a valid parent so create a dummy parent
        String parent =
            "<entity name=\"TopLevel\" class=\"ptolemy.actor.TypedCompositeActor\">"; //$NON-NLS-1$
        
        // embed the real entity description in the parent
        String child = parent + "<entity name=\"" + entityName //$NON-NLS-1$
                + "\"class=\"" + className //$NON-NLS-1$
                + "\"/> </entity>"; //$NON-NLS-1$
        
        // let the parser do the job
        NamedObj actor = parser.parse(child);
        actor = (Entity) ((TypedCompositeActor) actor).entityList().get(0);
        return actor;
    }

    /**
     * Instantiate a Ptolemy State Entity for a given EntityType model object.
     * 
     * @param className the class name of the actor to instantiate.
     * @param entityName a non-empty string that identifies the actor instance. This is mainly
     *                   interesting for debug messages and can be chosen more or less at
     *                   random.
     * @return corresponding Ptolemy object.
     * @throws Exception may throw different exceptions during parsing.
     */
    private NamedObj instantiatePtolemyState(final String className, final String entityName)
            throws Exception {
        
        // use the Ptolemy internal Model ML (MoML) parser parsing XML and
        // creates Ptolemy models
        MoMLParser parser = new MoMLParser();
        MoMLParser.setMoMLFilters(BackwardCompatibility.allFilters());
        
        // atomic actors require a valid parent so create a dummy parent
        // states may only be in a ModalController and not in a normal
        // CompositeActor
        String parent =
            "<entity name=\"TopLevel\" " //$NON-NLS-1$
            + "class=\"ptolemy.domains.modal.modal.ModalController\">"; //$NON-NLS-1$
        
        // embed the real entity description in the parent
        String child = parent + "<entity name=\"" + entityName //$NON-NLS-1$
                + "\"class=\"" + className //$NON-NLS-1$
                + "\"/> </entity>"; //$NON-NLS-1$
        
        // let the parser do the job
        NamedObj actor = parser.parse(child);
        actor = (Entity) ((CompositeEntity) actor).entityList().get(0);
        return actor;
    }

    /**
     * Transform Ptolemy Attribute to a KAOM Annotation including all nested
     * Attributes.
     * 
     * @param ptolemyAttribute
     * @return the created KAOM Annotation
     */
    private static Annotation getAnnotation(final Attribute ptolemyAttribute) {
        TypedStringAnnotation kaomAnnotation = AnnotationsFactory.eINSTANCE
                .createTypedStringAnnotation();

        kaomAnnotation.setName(ptolemyAttribute.getName());
        kaomAnnotation.setType(ptolemyAttribute.getClassName());
        if (ptolemyAttribute instanceof StringAttribute) {
            kaomAnnotation.setValue(((StringAttribute) ptolemyAttribute).getValueAsString());
        }

        for (Object childAttribute : ptolemyAttribute.attributeList()) {
            if (childAttribute instanceof Attribute) {
                kaomAnnotation.getAnnotations().add(getAnnotation((Attribute) childAttribute));
            }
        }
        
        return kaomAnnotation;
    }

    /**
     * Execution context is set by Xtend component from outside. It can be used
     * to obtain detailed information about the transformation.
     * 
     * <p>This implementation currently does nothing since the execution context is
     * not used at the moment.</p>
     * 
     * @param context the execution context.
     */
    public void setExecutionContext(final ExecutionContext context) {
        // We don't currently make use of the execution context.
    }

}
