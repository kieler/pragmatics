/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.kdiagram.jvmmodel

import org.eclipse.xtext.common.types.JvmType
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder
import javax.inject.Inject
import org.eclipse.emf.ecore.EObject
import javax.inject.Singleton
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable

/**
 * A collection of convenient functions for simplifying the formulation of code generation templates.
 *  
 * @author chsch
 */
 @Singleton
class CodeGenHelper {
    
    @Inject
    extension JvmTypesBuilder
    
    private EObject context = null;
    
    def void setContext(EObject theContext) {
        this.context = theContext;
    }
    
    def JvmType create type: context.newTypeRef(clazz).type getType(Class<?> clazz) {
    }

    def JvmType create type: context.newTypeRef(clazz).type getType(EObject context, Class<?> clazz) {
    }
    
    /**
     * The return type 'CodeGenHelper' of this method is just any uniquely identifiable type.
     * I need such a type in {@link #operator_plus(ITreeAppendable, CodeGenHelper)} below,
     * the concrete value doesn't matter.
     */
    public def CodeGenHelper newline() {
        return this;
    }

    /**
     * A little trick to obtain the ability of writing ' + newline'.
     * I could have also used 'ITreeAppendable' and use the original 'newLine()' method
     * in the occurrences like '+ newLine' but that would be very error-prone ...
     */
    public def ITreeAppendable operator_plus(ITreeAppendable a, CodeGenHelper b) {
        a.newLine;
    }

    public def ITreeAppendable operator_plus(ITreeAppendable a, CharSequence s) {
        a.append(s);
    }
    
    public def ITreeAppendable operator_plus(ITreeAppendable a, JvmType s) {
        a.append(s);
    }
}