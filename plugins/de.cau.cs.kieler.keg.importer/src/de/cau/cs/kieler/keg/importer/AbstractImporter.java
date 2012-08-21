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

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IPath;

import de.cau.cs.kieler.core.properties.IPropertyHolder;

/**
 * The base class for a KEG importer.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public abstract class AbstractImporter implements IImporter {

    /** the available options. */
    private List<ImporterOption<?>> options = new LinkedList<ImporterOption<?>>();

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
     * Returns the importers supported file extensions.
     * 
     * @return the supported file extensions
     */
    public abstract String[] getExtensions();

    /**
     * Returns the options that are available for this importer.
     * 
     * @return the exporters options
     */
    public List<ImporterOption<?>> getOptions() {
        return options;
    }

    /**
     * Adds an option to the importer. This option with the chosen values can be received from the
     * configuration that is passed to the import method.
     * 
     * @param <T>
     *            the option value type
     * @param option
     *            the option
     */
    public <T> void addOption(final ImporterOption<T> option) {
        options.add(option);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    public void doModelPostProcess(final IPath modelPath, final IPropertyHolder theOptions) {
    }

    /**
     * {@inheritDoc}
     */
    public void doDiagramPostProcess(final IPath diagramPath, final IPropertyHolder theOptions) {
    }
}
