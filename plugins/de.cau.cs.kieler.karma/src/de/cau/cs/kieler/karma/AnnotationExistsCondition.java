package de.cau.cs.kieler.karma;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;

public class AnnotationExistsCondition extends ICustomCondition<EObject> {

    public boolean evaluate(final EObject object) {
        if (object instanceof Annotatable) {
            Annotatable annotatable = ((Annotatable) object);
            if (!annotatable.getAnnotations().isEmpty()) {
                Annotation annotation = annotatable.getAnnotation(key);
                if (annotation != null) {
                    return true;
                } else {
                    for (Annotation ann : annotatable.getAnnotations()){
                        if (evaluate(ann)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
