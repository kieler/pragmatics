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
package de.cau.cs.kieler.kiml.grana.xtend;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author mri
 */
public class GranaXtendPlugin extends AbstractUIPlugin {

    /** the plugin id. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.kiml.grana.xtend"; //$NON-NLS-1$

    /** the shared instance. */
    private static GranaXtendPlugin plugin;

    /**
     * The constructor.
     */
    public GranaXtendPlugin() {
    }

    /**
     * {@inheritDoc}
     */
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        // initializes the xtend analyses
        XtendAnalysesManager.getInstance();
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
     * @return the shared instance
     */
    public static GranaXtendPlugin getDefault() {
        return plugin;
    }

}
