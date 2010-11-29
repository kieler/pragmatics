package de.cau.cs.kieler.kaom.karma.ptolemy.conditions;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.karma.ICustomCondition;

public class ConnectionSourceCondition extends ICustomCondition<EObject> {

    public boolean evaluate(EObject object) {
        if (object instanceof Link) {
            Link link = (Link) object;
            EObject source = link.getSource();
            if (source instanceof Annotatable) {
                Annotatable annotatable = (Annotatable) source;
                Annotation sourceAnn = annotatable.getAnnotation(key);
                if (sourceAnn instanceof StringAnnotation) {
                    if (((StringAnnotation)sourceAnn).getValue().equals(value)){
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

}
