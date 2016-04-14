/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.kiml.formats.kgtee;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class KGTeeFormatActivator extends AbstractUIPlugin {
    
    private static KGTeeFormatActivator INSTANCE = null;
    
    public static KGTeeFormatActivator getInstance() {
            return INSTANCE;
    }
    
    @Override
    public void start(BundleContext context) throws Exception {
            super.start(context);
            INSTANCE = this;
            
            INSTANCE.initResourceRegistrations();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
            INSTANCE = null;
            super.stop(context);
    }
    
    private void initResourceRegistrations() {
        Injector injector = Guice.createInjector(new de.cau.cs.kieler.kgraph.text.KGraphRuntimeModule());
        org.eclipse.xtext.resource.IResourceFactory resourceFactory = injector.getInstance(org.eclipse.xtext.resource.IResourceFactory.class);
        org.eclipse.xtext.resource.IResourceServiceProvider serviceProvider = injector.getInstance(org.eclipse.xtext.resource.IResourceServiceProvider.class);
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("kgtee", resourceFactory);
        org.eclipse.xtext.resource.IResourceServiceProvider.Registry.INSTANCE.getExtensionToFactoryMap().put("kgtee", serviceProvider);
    }

}
