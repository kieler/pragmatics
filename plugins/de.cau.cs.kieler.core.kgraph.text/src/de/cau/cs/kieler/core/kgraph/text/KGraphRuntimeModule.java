/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kgraph.text;

import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.linking.ILinker;
import org.eclipse.xtext.linking.impl.Linker;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.resource.XtextResource;

import de.cau.cs.kieler.core.kgraph.text.scoping.KGraphQualifiedNameProvider;

/**
 * This class defines some customizations on the textual KGraph editing tooling.
 * 
 * @author chsch
 * @author msp
 */
public class KGraphRuntimeModule extends AbstractKGraphRuntimeModule {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider() {
        return KGraphQualifiedNameProvider.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends IValueConverterService> bindIValueConverterService() {
        return KGraphValueConverters.class;
    }

    /**
     * Method registers a customized {@link org.eclipse.xtext.linking.lazy.LazyLinkingResource} in
     * order to handle the (de-) serialization of {@link de.cau.cs.kieler.core.properties.IProperty}s
     * correctly.
     * 
     * @return the {@link KGraphResource} class
     */
    public Class<? extends XtextResource> bindXtextResource() {
        return KGraphResource.class;
    }
    
    /**
     * Method registers the non-lazy linking Linker since the default
     * {@link org.eclipse.xtext.linking.lazy.LazyLinker} doesn't work properly with EOpposite
     * references. (Produces error markers in editor.)
     * 
     * @return the {@link Linker} class
     */
    @Override
    public Class<? extends ILinker> bindILinker() {
        return Linker.class;
    }
    
}
