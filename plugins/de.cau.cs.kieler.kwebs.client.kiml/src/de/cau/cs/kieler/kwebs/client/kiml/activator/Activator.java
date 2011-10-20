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

package de.cau.cs.kieler.kwebs.client.kiml.activator;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.BundleContext;

import de.cau.cs.kieler.kwebs.client.kiml.EclipseServerConfigsStorageManager;
import de.cau.cs.kieler.kwebs.client.kiml.ServerConfigs;
import de.cau.cs.kieler.kwebs.client.kiml.layout.RemoteLayoutDataService;

/**
 * Activator for the client plug-in. It provides storage management for the user defined provider list.
 *
 * @kieler.rating  2011-08-02 proposed yellow
 *     reviewed by ckru, mri, msp
 *     
 * @author  swe
 */
public class Activator extends Plugin {

    /** The preference store associated with this plug-in. */
    private static IPreferenceStore preferenceStore;

    /** The instance of this activator. */
    private static Activator instance;

    /** The bundle context of this activator. */
    private static BundleContext bundleContext;
    
    /** The ID of this plug-in. */
    public static final String PLUGIN_ID
        = "de.cau.cs.kieler.kwebs.client.kiml";
    
    /**
     * {@inheritDoc}
     */
    @Override
    public final void start(final BundleContext context) throws Exception {
        super.start(context);
        instance = this;
        bundleContext = context;
        // Register eclipse storage manager
        ServerConfigs.getInstance().registerStorageManager(new EclipseServerConfigsStorageManager());
        // Initialize providers from preference store
        ServerConfigs.getInstance().read();
        // Create and register layout data for remote layout.
        // FIXME make sure the right service instance is activated on startup
        RemoteLayoutDataService.create();
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
                StatusManager.getManager().handle(
                    new Status(
                        IStatus.WARNING, PLUGIN_ID, "Could not load image: " + path, 
                        e
                    )
                );
            }
        }
        return descriptor;        
    }
    
}
