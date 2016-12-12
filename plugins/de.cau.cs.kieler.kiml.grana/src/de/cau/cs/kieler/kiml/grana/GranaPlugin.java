/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana;

import java.util.List;

import org.eclipse.elk.core.service.ILayoutListener;
import org.eclipse.elk.core.service.LayoutConnectorsService;
import org.eclipse.elk.core.service.LayoutMapping;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.cau.cs.kieler.kiml.grana.ui.AnalysisEffect;
import de.cau.cs.kieler.kiml.grana.ui.visualization.VisualizationService;
import de.cau.cs.kieler.kiml.grana.util.GranaUtil;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class GranaPlugin extends AbstractUIPlugin {

    /** the plug-in ID. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.kiml.grana";

    /** the shared instance. */
    private static GranaPlugin plugin;

    /**
     * The constructor.
     */
    public GranaPlugin() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        
        // Register a listener for analysis after layout
        LayoutConnectorsService.getInstance().addLayoutListener(new ILayoutListener() {
            @Override
            public void layoutDone(final LayoutMapping mapping, final IElkProgressMonitor progressMonitor) {
                ElkNode layoutGraph = mapping.getLayoutGraph();

                if (VisualizationService.getInstance().findActiveMethod(true)) {
                    List<AnalysisData> analyses = GranaUtil.getLastAnalysesSelection();
                    new AnalysisEffect(layoutGraph, analyses).schedule();
                }
            }

            @Override
            public void layoutAboutToStart(final LayoutMapping mapping, final IElkProgressMonitor progressMonitor) {
                // nothing to do here
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
    public static GranaPlugin getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path.
     * 
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(final String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

}
