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

import de.cau.cs.kieler.core.properties.MapPropertyHolder;

/**
 * A class for holding the configuration for a graph export. This includes the
 * selected exporter, selected options and the file to export to.
 * 
 * @author mri
 */
public class ExporterConfiguration extends MapPropertyHolder {

    /** the exporter. */
    private AbstractExporter exporter = null;
    /** the export file path. */
    private String exportFile = null;
    /** is the file path relative to the workspace? */
    private boolean isWorkspacePath;

    /**
     * Constructs an exporter configuration.
     * 
     * @param theExporter
     *            the exporter
     * @param theExportFile
     *            the export file path
     * @param workspacePath
     *            true if the path is relative to the workspace
     */
    public ExporterConfiguration(final AbstractExporter theExporter,
            final String theExportFile, final boolean workspacePath) {
        exporter = theExporter;
        exportFile = theExportFile;
        isWorkspacePath = workspacePath;
    }

    /**
     * Returns the exporter.
     * 
     * @return the exporter
     */
    public AbstractExporter getExporter() {
        return exporter;
    }

    /**
     * Returns the export file path.
     * 
     * @return the export file path
     */
    public String getExportFilePath() {
        return exportFile;
    }

    /**
     * Returns whether the export file path is relative to the workspace.
     * 
     * @return true if the export file path is relative to the workspace
     */
    public boolean isWorkspacePath() {
        return isWorkspacePath;
    }
}
