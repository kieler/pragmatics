/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kaom.karma.ptolemy.conditions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.karma.ICustomCondition;

/**
 * Condition to check whether a modelelement has a comment annotation thats supposed to be
 * displayed.
 * 
 * @author ckru
 * @kieler.ignore (excluded from review process)
 */
public class HasCommentsCondition extends ICustomCondition<EObject> {

    /**
     * {@inheritDoc}
     */
    public boolean evaluate(final EObject object) {
        if (object instanceof Annotatable) {
            List<Annotation> annotationList = ((Annotatable) object).getAnnotations();
            for (Annotation annotation : annotationList) {
                if (annotation.getAnnotation("attachedTo") != null) {
                    return true;
                }
            }
        }
        return false;
    }

}
