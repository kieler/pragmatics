/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.cau.cs.kieler.ksbase.core.TransformationManager;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author mim
 * 
 * @kieler.rating 2009-11-20 proposed yellow -review by msp,skn: class ok
 */
public class KSBasEPlugin extends AbstractUIPlugin {

    /** The plug-in ID. **/
    public static final String PLUGIN_ID = "de.cau.cs.kieler.ksbase";

    /** The shared instance. **/
    private static KSBasEPlugin plugin;

    /** Logging instance. **/
    private ILog logger;

    /**
     * The constructor.
     */
    public KSBasEPlugin() {
        logger = null;
    }

    /**
     * Creates a warning message for the KSBasE plug-in.
     * 
     * @param message
     *            The warning to log
     */
    public void logWarning(final String message) {
        if (logger != null) {
            logger.log(new Status(IStatus.WARNING, KSBasEPlugin.PLUGIN_ID, message));
        }
    }

    /**
     * Creates an error message for the KSBasE plug-in.
     * 
     * @param message
     *            The error message
     */
    public void logError(final String message) {
        if (logger != null) {
            logger.log(new Status(IStatus.ERROR, KSBasEPlugin.PLUGIN_ID, message));
        }
    }

    /**
     * Creates an info message for the KSBasE plug-in.
     * 
     * @param message
     *            The info message
     */
    public void logInfo(final String message) {
        if (logger != null) {
            logger.log(new Status(IStatus.INFO, KSBasEPlugin.PLUGIN_ID, message));
        }
    }

    /**
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     * @param context
     *            The bundle context for this plug-in
     * @throws Exception
     *             When starting this plug-in failes
     */
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        logger = getLog();
        TransformationManager.INSTANCE.initializeTransformations();
    }

    /**
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     * @param context
     *            The bundle context for this plug-in
     * @throws Exception
     *             When stopping this plug-in failes
     */
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        logger = null;
        super.stop(context);
    }

    /**
     * Gets the shared instance.
     * 
     * @return the shared instance.
     */
    public static KSBasEPlugin getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path.
     * 
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(final String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }
}
