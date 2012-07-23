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

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.karma.ICustomCondition;

/**
 * Condition to check an annotation of the source of a connection.
 * Similar to annotation condition.
 * 
 * @author ckru
 * @kieler.ignore (excluded from review process)
 */
public class ConnectionSourceCondition extends ICustomCondition<EObject> {

    /**
     * {@inheritDoc}
     */
    public boolean evaluate(final EObject object) {
        if (object instanceof Link) {
            Link link = (Link) object;
            EObject source = link.getSource();
            if (source instanceof Annotatable) {
                Annotatable annotatable = (Annotatable) source;
                Annotation sourceAnn = annotatable.getAnnotation(key);
                if (sourceAnn instanceof StringAnnotation) {
                    return ((StringAnnotation) sourceAnn).getValue().equals(value);               
                }
            }
        }
        return false;
    }

}
