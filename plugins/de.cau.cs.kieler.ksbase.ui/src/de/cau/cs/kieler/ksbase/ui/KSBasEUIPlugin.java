/**
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
package de.cau.cs.kieler.ksbase.ui;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.cau.cs.kieler.ksbase.ui.menus.DynamicBundleLoader;
import de.cau.cs.kieler.ksbase.ui.menus.DynamicMenuContributions;
import de.cau.cs.kieler.viewmanagement.RunLogic;

/**
 * The activator class controls the plug-in life cycle.
 *  * 
 * @author mim
 * 
 * @kieler.rating 2009-12-15 proposed yellow 
 */

public class KSBasEUIPlugin extends AbstractUIPlugin {

    /** The plug-in ID. **/
    public static final String PLUGIN_ID = "de.cau.cs.kieler.ksbase.ui";

    /** The shared instance. **/
    private static KSBasEUIPlugin plugin;

    /** Logging instance. **/
    private ILog logger;

    /**
     * The constructor.
     */
    public KSBasEUIPlugin() {
        logger = null;
    }

    /**
     * Creates a warning message for the KSBasE-UI plug-in.
     * 
     * @param message
     *            The warning to log
     */
    public void logWarning(final String message) {
        if (logger != null) {
            logger.log(new Status(IStatus.WARNING, KSBasEUIPlugin.PLUGIN_ID, message));
        }
    }

    /**
     * Creates an error message for the KSBasE-UI plug-in.
     * 
     * @param message
     *            The error message
     */
    public void logError(final String message) {
        if (logger != null) {
            logger.log(new Status(IStatus.ERROR, KSBasEUIPlugin.PLUGIN_ID, message));
        }
    }

    /**
     * Creates an info message for the KSBasE-UI plug-in.
     * 
     * @param message
     *            The error message
     */
    public void logInfo(final String message) {
        if (logger != null) {
            logger.log(new Status(IStatus.INFO, KSBasEUIPlugin.PLUGIN_ID, message));
        }
    }

    /**
     * Starts the plug-in.
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     * @param context
     *            The bundle context for this plug-in
     * @throws Exception
     *             When starting this plug-in fails.
     * 
     */
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        KSBasEUIPlugin.plugin = this;
        logger = KSBasEUIPlugin.plugin.getLog();

        // Creating bundles
        DynamicMenuContributions.INSTANCE.createAllMenuContributions();
        // Temporary fix for "not loading features"-bug
        DynamicBundleLoader.INSTANCE.activateAllEditors();
        if (!RunLogic.getInstance().getState()) {
            RunLogic.getInstance().registerListeners();
        }
        // Dirty hack, activating view management bundle:
        /*
         * IConfigurationElement[] configurations =
         * Platform.getExtensionRegistry().getConfigurationElementsFor(
         * "de.cau.cs.kieler.ksbase.ui.classLoader"); for (IConfigurationElement loader :
         * configurations) { Object o = loader.createExecutableExtension("class");
         * System.out.println(o); }
         */
        //
        /*
         * // Adding a part listener to check when to activate a bundle
         * System.out.println("activated");
         * 
         * if (PlatformUI.getWorkbench() != null) { System.out.println("workbench found");
         * System.out.println("work windows: " +
         * PlatformUI.getWorkbench().getWorkbenchWindows().length); System.out.println("active: " +
         * PlatformUI.getWorkbench().getActiveWorkbenchWindow ().getClass().getCanonicalName());
         * 
         * 
         * 
         * PlatformUI.getWorkbench().addWindowListener(DynamicBundleLoader.INSTANCE ); }#
         */
    }

    /**
     * Stops the plug-in.
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     * @param context
     *            The bundle context for this plug-in
     * @throws Exception
     *             When stopping this plug-in fails.
     */
    public void stop(final BundleContext context) throws Exception {
        KSBasEUIPlugin.plugin = null;
        logger = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static KSBasEUIPlugin getDefault() {
        return plugin;
    }

}
