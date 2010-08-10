package de.cau.cs.kieler.kaom.importer.ptolemy;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.xtend.expression.ExecutionContext;
import org.eclipse.xtend.expression.IExecutionContextAware;
import org.ptolemy.moml.EntityType;

import ptolemy.actor.IOPort;
import ptolemy.actor.TypedCompositeActor;
import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.Entity;
import ptolemy.kernel.util.Attribute;
import ptolemy.kernel.util.NamedObj;
import ptolemy.moml.MoMLParser;

import com.microstar.xml.XmlException;

import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.AnnotationsFactory;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.Port;

/**
 * Helper methods to inspect Ptolemy models. Could be used from Xtend transformations.
 * @author haf
 */
public class PtolemyHelper implements IExecutionContextAware {

    ExecutionContext ctx;

    public PtolemyHelper() {
    }

    public List<Port> getPorts(List<EntityType> entities) {
        List<Port> ports = new BasicEList<Port>();
        for (EntityType entity : entities) {
            ports.addAll(getPorts(entity));
        }
        return ports;
    }

    public List<Port> getPorts(EntityType ptolemyEntity) {
        List<Port> kaomPorts = new LinkedList<Port>();
        try {
            NamedObj actor = instanciatePtolemyEntity(ptolemyEntity);

            for (Object obj : ((Entity) actor).portList()) {
                if (obj instanceof IOPort) {
                    IOPort ptolemyPort = (IOPort) obj;
                    Port kaomPort = KaomFactory.eINSTANCE.createPort();
                    // find out whether it is an input or output (or both)
                    if (ptolemyPort.isInput()) {
                        Annotation isInput = AnnotationsFactory.eINSTANCE.createAnnotation();
                        isInput.setName("input");
                        kaomPort.getAnnotations().add(isInput);
                    }
                    if (ptolemyPort.isOutput()) {
                        Annotation isOutput = AnnotationsFactory.eINSTANCE.createAnnotation();
                        isOutput.setName("output");
                        kaomPort.getAnnotations().add(isOutput);
                    }
                    // set the name
                    kaomPort.setName(ptolemyPort.getName());

                    for (Object attribute : ptolemyPort.attributeList()) {
                        if (attribute instanceof Attribute) {
                            kaomPort.getAnnotations().add(getAnnotation((Attribute) attribute));
                        }
                    }

                    kaomPorts.add(kaomPort);
                }
            }
        } catch (XmlException xe) {
            System.out.println("Could not instanciate entity: " + xe.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kaomPorts;
    }

    /**
     * Instanciate a Ptolemy Entity for a given EntityType model object.
     * 
     * @param entity
     *            given EMF EntityType model object
     * @return corresponding Ptolemy object
     * @throws Exception
     *             may throw different Exceptions during parsing
     */
    private NamedObj instanciatePtolemyEntity(EntityType entity) throws Exception {
        String classname = entity.getClass1();
        if (classname.equals("ptolemy.domains.modal.kernel.State")) {
            return instanciatePtolemyState(entity);
        }
        return instanciatePtolemyActor(entity);
    }

    private NamedObj instanciatePtolemyActor(EntityType entity) throws Exception {
        String classname = entity.getClass1();
        // use the Ptolemy internal Model ML (MoML) parser parsing XML and
        // creates Ptolemy models
        MoMLParser parser = new MoMLParser();
        // atomic actors require a valid parent so create a dummy parent
        String parent = "<entity name=\"TopLevel\" class=\"ptolemy.actor.TypedCompositeActor\">";
        // embed the real entity description in the parent
        String child = parent + "<entity name=\"" + entity.getName() + "\"class=\"" + classname
                + "\"/> </entity>";
        // let the parser do the job
        NamedObj actor = parser.parse(child);
        actor = (Entity) ((TypedCompositeActor) actor).entityList().get(0);
        return actor;
    }

    private NamedObj instanciatePtolemyState(EntityType entity) throws Exception {
        String classname = entity.getClass1();
        // use the Ptolemy internal Model ML (MoML) parser parsing XML and
        // creates Ptolemy models
        MoMLParser parser = new MoMLParser();
        // atomic actors require a valid parent so create a dummy parent
        // states may only be in a ModalController and not in a normal
        // CompositeActor
        String parent = "<entity name=\"TopLevel\" class=\"ptolemy.domains.modal.modal.ModalController\">";
        // embed the real entity description in the parent
        String child = parent + "<entity name=\"" + entity.getName() + "\"class=\"" + classname
                + "\"/> </entity>";
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
    static private Annotation getAnnotation(Attribute ptolemyAttribute) {
        StringAnnotation kaomAnnotation = AnnotationsFactory.eINSTANCE.createStringAnnotation();
        StringAnnotation classAnnotation = AnnotationsFactory.eINSTANCE.createStringAnnotation();

        kaomAnnotation.setName(ptolemyAttribute.getName());
        classAnnotation.setName("ptolemyClass");
        classAnnotation.setValue(ptolemyAttribute.getClassName());
        kaomAnnotation.getAnnotations().add(classAnnotation);

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
     */
    public void setExecutionContext(ExecutionContext ctx) {
        this.ctx = ctx;
    }

}
