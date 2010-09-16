package de.cau.cs.kieler.karma;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;

/**
 * 
 * This condition is true if the annotation identified by the key exists in a given EObject.
 * @author ckru
 * 
 */
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
