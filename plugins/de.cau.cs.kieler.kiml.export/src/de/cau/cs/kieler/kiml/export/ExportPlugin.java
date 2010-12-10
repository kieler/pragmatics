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
package de.cau.cs.kieler.kiml.export;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.cau.cs.kieler.kiml.export.exporter.DotExporter;
import de.cau.cs.kieler.kiml.export.exporter.GMLExporter;
import de.cau.cs.kieler.kiml.export.exporter.GraphMLExporter;
import de.cau.cs.kieler.kiml.export.exporter.KGraphExporter;
import de.cau.cs.kieler.kiml.export.exporter.OGMLExporter;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author mri
 */
public class ExportPlugin extends AbstractUIPlugin {

    /** the plug-in ID. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.kiml.export"; //$NON-NLS-1$

    /** the shared instance. */
    private static ExportPlugin plugin;
    
    /**
     * The constructor.
     */
    public ExportPlugin() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        
        ExportManager.getInstance().addExporter(new GraphMLExporter());
        ExportManager.getInstance().addExporter(new OGMLExporter());
        ExportManager.getInstance().addExporter(new GMLExporter());
        ExportManager.getInstance().addExporter(new KGraphExporter());
        ExportManager.getInstance().addExporter(new DotExporter());
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
    public static ExportPlugin getDefault() {
        return plugin;
    }

}
