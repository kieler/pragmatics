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
package de.cau.cs.kieler.kiml.server;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * KIML Server Plugin.
 * 
 * @author msp
 */
public class KimlServerActivator implements BundleActivator {

    /** the bundle context. */
    private static BundleContext context;
    /** the singleton instance of the remote layout service. */
    private static RemoteLayoutService instance;

    /**
     * Returns the bundle context.
     * 
     * @return the bundle context
     */
    static BundleContext getContext() {
        return context;
    }

    /**
     * {@inheritDoc}
     */
    public void start(final BundleContext bundleContext) throws Exception {
        KimlServerActivator.context = bundleContext;
        if (instance == null) {
            instance = new RemoteLayoutService();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void stop(final BundleContext bundleContext) throws Exception {
        if (instance != null) {
            instance.dispose();
            instance = null;
        }
        KimlServerActivator.context = null;
    }

}
