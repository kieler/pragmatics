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
package de.cau.cs.kieler.kiml.formats.gml;

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

/**
 * The content of this class was mainly copied from
 * {@link org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory}.
 * 
 * @author msp (original code by Sven Efftinge)
 */
public class GmlExecutableExtensionFactory implements IExecutableExtensionFactory,
        IExecutableExtension {
    
    private static final String GUICEKEY = "guicekey";
    
    private String clazzName;
    private IConfigurationElement config;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({ "unchecked" })
    public void setInitializationData(final IConfigurationElement theconfig, final String propertyName,
            final Object data)
            throws CoreException {
        if (data instanceof String) {
            clazzName = (String) data;
        } else if (data instanceof Map<?, ?>) {
            clazzName = ((Map<String, String>) data).get(GUICEKEY);
        }
        if (clazzName == null) {
            throw new IllegalArgumentException("couldn't handle passed data : " + data);
        }
        this.config = theconfig;
    }

    /**
     * {@inheritDoc}
     */
    public Object create() throws CoreException {
        Bundle bundle = GmlActivator.getInstance().getBundle();
        try {
            final Class<?> clazz = bundle.loadClass(clazzName);
            final Injector injector = GmlActivator.getInstance().getInjector(GmlActivator.GML);
            final Object result = injector.getInstance(clazz);
            if (result instanceof IExecutableExtension) {
                ((IExecutableExtension) result).setInitializationData(config, null, null);
            }
            return result;
        } catch (Exception e) {
            throw new CoreException(new Status(IStatus.ERROR, bundle.getSymbolicName(),
                    e.getMessage() + " ExtensionFactory: " + getClass().getName(), e));
        }
    }

}
