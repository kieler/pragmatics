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
package de.cau.cs.kieler.keg.importer;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.cau.cs.kieler.keg.importer.importer.DotImporter;
import de.cau.cs.kieler.keg.importer.importer.GraphMLImporter;
import de.cau.cs.kieler.keg.importer.importer.KGraphImporter;
import de.cau.cs.kieler.keg.importer.importer.OGMLImporter;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class KEGImporterPlugin extends AbstractUIPlugin {

    /** the plug-in id. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.keg.importer"; //$NON-NLS-1$
    /** the shared instance. */
    private static KEGImporterPlugin plugin;

    /**
     * The constructor.
     */
    public KEGImporterPlugin() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;

        ImportManager.getInstance().addImporter(new GraphMLImporter());
        ImportManager.getInstance().addImporter(new KGraphImporter());
        ImportManager.getInstance().addImporter(new OGMLImporter());
        ImportManager.getInstance().addImporter(new DotImporter());
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
    public static KEGImporterPlugin getDefault() {
        return plugin;
    }
}
