/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.client.activator;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.BundleContext;

import de.cau.cs.kieler.kwebs.client.providers.EclipseProvidersStorageManager;
import de.cau.cs.kieler.kwebs.client.providers.Providers;

/**
 * Activator for the client plugin. It provides storage management for the user defined provider list.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class Activator extends Plugin {

    /** The preference store associated with this plugin. */
    private static IPreferenceStore preferenceStore;

    /** The instance of this activator. */
    private static Activator instance;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void start(final BundleContext context) throws Exception {
        super.start(context);
        instance = this;
        // Register eclipse storage manager
        Providers.registerStorageManager(new EclipseProvidersStorageManager());
        // Initialize providers from preference store
        Providers.read();
    }

    /**
     * Returns the instance of this activator.
     * 
     * @return the instance of this activator
     */
    public static Activator getInstance() {
        return instance;
    }

    /**
     * Returns the preference store associated with this plugin.
     * 
     * @return the preference store associated with this plugin
     */
    public final synchronized IPreferenceStore getPreferenceStore() {
        if (preferenceStore == null) {
            preferenceStore = new ScopedPreferenceStore(
                InstanceScope.INSTANCE, getBundle().getSymbolicName()
            );
        }
        return preferenceStore;
    }

}
