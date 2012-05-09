/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.debug;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kivi.KiVi;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author msp
 */
public class KimlViewerPlugin extends AbstractUIPlugin {

    /** the plug-in ID. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.kiml.debug";

    /** the shared instance. */
    private static KimlViewerPlugin plugin;

    /**
     * The constructor.
     */
    public KimlViewerPlugin() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        // register a listener for layout
        DiagramLayoutEngine.INSTANCE.addListener(new DiagramLayoutEngine.IListener() {
            public void layoutDone(final KNode layoutGraph, final IKielerProgressMonitor monitor) {
                KiVi.getInstance().executeEffect(new UpdateViewerEffect(layoutGraph, monitor));
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static KimlViewerPlugin getDefault() {
        return plugin;
    }

}
