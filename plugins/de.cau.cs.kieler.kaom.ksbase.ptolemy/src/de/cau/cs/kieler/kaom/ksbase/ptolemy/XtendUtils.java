/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kaom.ksbase.ptolemy;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.ReferenceAnnotation;
import de.cau.cs.kieler.core.annotations.impl.AnnotationsFactoryImpl;

/**
 * Class holding some utilities to be used as java escapes from the xtend transformation.
 *  
 * @author ckru
 *
 */
public class XtendUtils {
    
    /**
     * Adds a ReferenceAnnotation to a given Annotatable cause xtend ain't able to do this.
     * @param annotatable the annotatable to get the new annotation.
     * @param key the name of the new annotation
     * @param obj the object to be referenced
     */
    public static void addReferenceAnnotation(Annotatable annotatable, String key, Object obj) {
       if (obj instanceof EObject) {
           ReferenceAnnotation ann = AnnotationsFactoryImpl.eINSTANCE.createReferenceAnnotation();
           ann.setName(key);
           EObject eobj = (EObject) obj;
           ann.setObject(eobj);
           annotatable.getAnnotations().add(ann);
       }
    }
    
    public static void debug(Object obj) {
        int x = 12;
        x++;
    }

}
