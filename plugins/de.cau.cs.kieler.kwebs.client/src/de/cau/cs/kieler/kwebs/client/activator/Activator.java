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

import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.BundleContext;

import de.cau.cs.kieler.kwebs.client.providers.EclipseProvidersStorageManager;
import de.cau.cs.kieler.kwebs.client.providers.Providers;
import de.cau.cs.kieler.kwebs.logging.DisplayLogging;
import de.cau.cs.kieler.kwebs.logging.Logger;
import de.cau.cs.kieler.kwebs.logging.Logger.Mode;
import de.cau.cs.kieler.kwebs.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.util.Arguments;

/**
 * Activator for the client plug-in. It provides storage management for the user defined provider list.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class Activator extends Plugin {

    /** The preference store associated with this plug-in. */
    private static IPreferenceStore preferenceStore;

    /** The instance of this activator. */
    private static Activator instance;

    /** The bundle context of this activator. */
    private static BundleContext bundleContext;
    
    /** Display logger used when in debug mode. */
    private DisplayLogging displayLogging;

    /** Common prefix for property identifiers. */
    private static final String PREFIX
        = "de.cau.cs.kieler.kwebs.";

    /** Whether to set the application in debugging mode. */
    public static final String KWEBS_LOGDEBUGMODE
        = PREFIX + "log.debugMode";
    
    /**
     * {@inheritDoc}
     */
    @Override
    public final void start(final BundleContext context) throws Exception {
        super.start(context);
        instance = this;
        bundleContext = context;
        // Register eclipse storage manager
        Providers.registerStorageManager(new EclipseProvidersStorageManager());
        // Initialize providers from preference store
        Providers.read();
        // Initialize debug mode if command line argument is given
        Map<String, String> arguments = Arguments.parseArgs(Platform.getApplicationArgs());
        if (Boolean.parseBoolean(arguments.get(KWEBS_LOGDEBUGMODE))) {
            displayLogging = new DisplayLogging();
            Logger.addLoggerListener(displayLogging);
            Logger.setRunMode(Mode.DEBUG);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        if (Logger.getRunMode() == Mode.DEBUG) {
            Logger.removeLoggerListener(displayLogging);
        }
        super.stop(context);
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
     * Returns the preference store associated with this plug-in.
     * 
     * @return the preference store associated with this plug-in
     */
    public final synchronized IPreferenceStore getPreferenceStore() {
        if (preferenceStore == null) {
            preferenceStore = new ScopedPreferenceStore(
                InstanceScope.INSTANCE, getBundle().getSymbolicName()
            );
        }
        return preferenceStore;
    }

    /**
     * Returns an image descriptor to an image contained in this plug-in.
     * 
     * @param path
     *            path to the image
     * @return the image descriptor or {@code null} if the specified path is not
     *         contained in this bundle
     */
    public static ImageDescriptor getImageDescriptor(final String path) {
        ImageDescriptor descriptor = null;
        if (path != null) {
            try {        
                descriptor = ImageDescriptor.createFromURL(
                    FileLocator.find(bundleContext.getBundle(), new Path(path), null)
                );
            } catch (Exception e) {
                Logger.log(Severity.WARNING, "Could not find image: " + path, e);
            }
        }
        return descriptor;        
    }
    
}
