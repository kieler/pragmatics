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
 */
package de.cau.cs.kieler.kaom.examples;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author pkl
 * @kieler.ignore (excluded from review process)
 */
public class KAOMExamplesPlugin extends AbstractUIPlugin {

    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.kaom.examples"; //$NON-NLS-1$

    // The shared instance
    private static KAOMExamplesPlugin plugin;

    /**
     * The constructor of {@link KAOMExamplesPlugin}.
     */
    public KAOMExamplesPlugin() {
    }

    /**
     * starts the bundle.
     * 
     * @param context
     *            , {@link BundleContext}
     * @throws Exception
     *             , if any exception occurs.
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /**
     * stops the bundle.
     * 
     * @param context
     *            , {@link BundleContext}
     * @throws Exception
     *             , if any exception occurs.
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static KAOMExamplesPlugin getDefault() {
        return plugin;
    }

}
