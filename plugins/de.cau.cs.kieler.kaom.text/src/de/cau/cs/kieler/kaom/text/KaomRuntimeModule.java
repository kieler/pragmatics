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

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 * 
 * @author chsch
 * @kieler.ignore (excluded from review process)
 */
public class KaomRuntimeModule extends de.cau.cs.kieler.kaom.text.AbstractKaomRuntimeModule {
    
    public Class<? extends org.eclipse.xtext.linking.ILinker> bindILinker() {
        return KaomLinker.class;
    }
    
    public Class<? extends org.eclipse.xtext.parsetree.reconstr.ITransientValueService> bindITransientValueService() {
        return KaomTransientValueService.class;
    }

    public Class<? extends org.eclipse.xtext.conversion.IValueConverterService> bindIValueConverterService() {
        return de.cau.cs.kieler.kaom.text.formatting.KaomValueConverter.class;
    }

}
