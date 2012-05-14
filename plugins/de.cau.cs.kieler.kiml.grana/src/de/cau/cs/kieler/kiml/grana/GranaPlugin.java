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
package de.cau.cs.kieler.kiml.grana;

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kivi.KiVi;
import de.cau.cs.kieler.kiml.grana.handlers.AnalysisEffect;
import de.cau.cs.kieler.kiml.grana.util.GranaUtil;
import de.cau.cs.kieler.kiml.grana.visualization.VisualizationService;
import de.cau.cs.kieler.kiml.service.grana.AnalysisData;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author mri
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
        // register a listener for analysis after layout
        DiagramLayoutEngine.INSTANCE.addListener(new DiagramLayoutEngine.IListener() {
            public void layoutDone(final KNode layoutGraph, final IKielerProgressMonitor monitor) {
                if (VisualizationService.getInstance().findActiveMethod(true)) {
                    List<AnalysisData> analyses = GranaUtil.getLastAnalysesSelection();
                    KiVi.getInstance().executeEffect(new AnalysisEffect(layoutGraph, analyses));
                }
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
