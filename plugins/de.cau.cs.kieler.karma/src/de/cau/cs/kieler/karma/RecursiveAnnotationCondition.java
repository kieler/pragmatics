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

package de.cau.cs.kieler.karma;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.BooleanAnnotation;
import de.cau.cs.kieler.core.annotations.ContainmentAnnotation;
import de.cau.cs.kieler.core.annotations.FloatAnnotation;
import de.cau.cs.kieler.core.annotations.IntAnnotation;
import de.cau.cs.kieler.core.annotations.ReferenceAnnotation;
import de.cau.cs.kieler.core.annotations.StringAnnotation;

/**
 * Condition that works on annotations.
 * 
 * @author ckru
 * 
 */
public class RecursiveAnnotationCondition extends ICustomCondition<EObject> {

    /**
     * {@inheritDoc}
     */
    public boolean evaluate(final EObject object) {
        if (object instanceof Annotatable) {
            Annotatable annotatable = ((Annotatable) object);
            Annotation annotation = getAnnotationByKeyRecursive(annotatable);
            if (annotation == null) {
                return false;
            } else {
            if (annotation instanceof BooleanAnnotation) {
                BooleanAnnotation booleanAnnotation = (BooleanAnnotation) annotation;
                boolean annotationValue = booleanAnnotation.isValue();
                boolean boolValue = Boolean.parseBoolean(value);
                return (annotationValue == boolValue);
            } else if (annotation instanceof ContainmentAnnotation) {
                ContainmentAnnotation containmentAnnotation = (ContainmentAnnotation) annotation;
                EObject eObject = containmentAnnotation.getObject();
                //TODO probably think of a different check that makes more sense.
                return eObject.eClass().getName().equals(value);
            } else if (annotation instanceof FloatAnnotation) {
                FloatAnnotation floatAnnotation = (FloatAnnotation) annotation;
                float annotationValue = floatAnnotation.getValue();
                float floatValue = Float.parseFloat(value);
                return (floatValue == annotationValue);
            } else if (annotation instanceof IntAnnotation) {
                IntAnnotation intAnnotation = (IntAnnotation) annotation;
                int annotationValue = intAnnotation.getValue();
                int intValue = Integer.parseInt(value);
                return (intValue == annotationValue);
            } else if (annotation instanceof ReferenceAnnotation) {
                ReferenceAnnotation referenceAnnotation = (ReferenceAnnotation) annotation;
                EObject eObject = referenceAnnotation.getObject();
              //TODO probably think of a different check that makes more sense.
                return eObject.eClass().getName().equals(value);
            } else if (annotation instanceof StringAnnotation) {
                StringAnnotation stringAnnotation = (StringAnnotation) annotation;
                //return stringAnnotation.getValue().equals(value);
                return stringAnnotation.getValue().equals(value);
            }
        }
        }
        return false;
    }
    
    private Annotation getAnnotationByKeyRecursive(Annotatable annotatable) {
        if (!annotatable.getAnnotations().isEmpty()) {
            Annotation annotation = annotatable.getAnnotation(key);
            if (annotation != null) {
                return annotation;
            } else {
                for (Annotation ann : annotatable.getAnnotations()){
                    return getAnnotationByKeyRecursive(ann);
                }
            }
        }
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    public void initialize(final String thekey, final String thevalue) {
        key = thekey;
        value = thevalue;
    }

}
