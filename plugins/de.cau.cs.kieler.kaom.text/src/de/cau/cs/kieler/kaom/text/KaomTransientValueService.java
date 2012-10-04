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
package de.cau.cs.kieler.kaom.text;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.parsetree.reconstr.ITransientValueService;
import org.eclipse.xtext.parsetree.reconstr.impl.DefaultTransientValueService;

import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.AnnotationsPackage;
import de.cau.cs.kieler.core.annotations.ContainmentAnnotation;
import de.cau.cs.kieler.core.annotations.ReferenceAnnotation;
import de.cau.cs.kieler.kaom.KaomPackage;

/**
 * Custom {@link ITransientValueService} contributing to Kits serialization.
 * Besides the usual references {@link KitsValueSerializer} also delegates to this class.
 * 
 * @author chsch
 * @author msp
 * @kieler.ignore (excluded from review process)
 */
public class KaomTransientValueService extends DefaultTransientValueService {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCheckElementsIndividually(EObject owner, EStructuralFeature feature) {
        // don't have grammar rules for all kinds of annotations yet
        if (feature == AnnotationsPackage.eINSTANCE.getAnnotatable_Annotations()) {
            return true;
        }
        
        return super.isCheckElementsIndividually(owner, feature);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTransient(EObject owner, EStructuralFeature feature, int index) {
        // suppress the EOpposites features
        if (feature == KaomPackage.eINSTANCE.getLinkable_IncomingLinks()
                || feature == KaomPackage.eINSTANCE.getLinkable_OutgoingLinks()) {
            return true;
        }
        
        // suppress annotations that have no grammar rules
        if (feature == AnnotationsPackage.eINSTANCE.getAnnotatable_Annotations()
                && owner instanceof Annotatable && index >= 0) {
            Annotation annotation = ((Annotatable) owner).getAnnotations().get(index);
            if (annotation instanceof ReferenceAnnotation
                    || annotation instanceof ContainmentAnnotation) {
                return true;
            }
        }
        
        return super.isTransient(owner, feature, index);
    }

}
