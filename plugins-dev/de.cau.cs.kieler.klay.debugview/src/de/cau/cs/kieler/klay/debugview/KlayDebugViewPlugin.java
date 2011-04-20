/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.debugview;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author cds
 */
public class KlayDebugViewPlugin extends AbstractUIPlugin {

    /** The plug-in ID. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.klay.debugview"; //$NON-NLS-1$
    
    /** The image folder. */
    private static final String IMAGE_FOLDER = "images/"; //$NON-NLS-1$

    // The shared instance
    private static KlayDebugViewPlugin plugin;
    
    
    /**
     * The constructor.
     */
    public KlayDebugViewPlugin() {
    }
    

    /**
     * {@inheritDoc}
     */
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /**
     * {@inheritDoc}
     */
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     *
     * @return the shared instance.
     */
    public static KlayDebugViewPlugin getDefault() {
        return plugin;
    }
    
    /**
     * Loads the image with the given file name. The image must be explicitly disposed.
     * 
     * @param fileName the image's file name.
     * @return the image or {@code null} if it couldn't be found.
     */
    public static Image loadImage(final String fileName) {
        ImageDescriptor descriptor = imageDescriptorFromPlugin(PLUGIN_ID, IMAGE_FOLDER + fileName);
        if (descriptor != null) {
            return descriptor.createImage();
        } else {
            return null;
        }
    }

}
