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

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * The base class for graph exporter.
 * 
 * @author mri
 */
public abstract class AbstractExporter implements IExporter {

    /** the message for a failed export. */
    protected static final String ERROR_MESSAGE_EXPORT_FAILED =
            "The export failed.";

    /** the available options. */
    private List<ExporterOption<?>> options =
            new LinkedList<ExporterOption<?>>();

    /**
     * Returns the name of the file format the exporter is exporting to.
     * 
     * @return the name of the file format
     */
    public abstract String getName();

    /**
     * Returns the default file extension for this exporter.
     * 
     * @return the default file extension
     */
    public abstract String getDefaultExtension();

    /**
     * Returns the exporters supported file extensions.
     * 
     * @return the supported file extensions
     */
    public abstract String[] getExtensions();

    /**
     * {@inheritDoc}
     */
    public abstract void doExport(final IKielerProgressMonitor monitor,
            final ExporterConfiguration configuration, final KNode graph)
            throws KielerException;

    /**
     * Returns the options that are available for this exporter.
     * 
     * @return the exporters options
     */
    public List<ExporterOption<?>> getOptions() {
        return options;
    }

    /**
     * Adds an option to the exporter. This option with the chosen values can be
     * received from the configuration that is passed to the export method.
     * 
     * @param <T>
     *            the option value type
     * @param option
     *            the option
     */
    public <T> void addOption(final ExporterOption<T> option) {
        options.add(option);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getName();
    }
}
