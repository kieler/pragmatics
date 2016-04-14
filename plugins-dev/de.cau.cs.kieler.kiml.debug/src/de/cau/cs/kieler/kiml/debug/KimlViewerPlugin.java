/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.debug;

import org.eclipse.elk.core.service.ILayoutListener;
import org.eclipse.elk.core.service.LayoutConnectorsService;
import org.eclipse.elk.core.service.LayoutMapping;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

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
        LayoutConnectorsService.getInstance().addLayoutListener(new ILayoutListener() {
            @Override
            public void layoutAboutToStart(final LayoutMapping mapping) {
            }
            @Override
            public void layoutDone(final LayoutMapping mapping) {
                new UpdateViewerEffect(mapping.getLayoutGraph(), new BasicProgressMonitor())
                        .execute();
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
