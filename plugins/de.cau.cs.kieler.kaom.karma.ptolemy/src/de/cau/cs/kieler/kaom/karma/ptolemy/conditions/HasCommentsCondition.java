package de.cau.cs.kieler.kaom.karma.ptolemy.conditions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.karma.ICustomCondition;

public class HasCommentsCondition extends ICustomCondition<EObject> {

    public boolean evaluate(EObject object) {
        if (object instanceof Annotatable) {
            List<Annotation> annotationList = ((Annotatable)object).getAnnotations();
            for (Annotation annotation : annotationList) {
                if (annotation.getAnnotation("attachedTo") != null) {
                    return true;
                }
            }
        }
        return false;
    }

}
