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

/**
 * A singleton class for accessing graph export functionality.
 * 
 * @author mri
 */
public final class ExportManager {

    /** the singleton instance. */
    private static ExportManager instance = new ExportManager();
    
    /** the registered exporters. */
    private List<AbstractExporter> exporters =
            new LinkedList<AbstractExporter>();

    /**
     * A private constructor to make this class a singleton.
     */
    private ExportManager() {
        // do nothing
    }

    /**
     * Returns the singleton instance.
     * 
     * @return the singleton instance
     */
    public static ExportManager getInstance() {
        return instance;
    }

    /**
     * Registers a graph exporter.
     * 
     * @param exp
     *            the exporter
     */
    public void addExporter(final AbstractExporter exp) {
        exporters.add(exp);
    }

    /**
     * Returns all registered exporters.
     * 
     * @return a list of registered exporter
     */
    public List<AbstractExporter> getExporter() {
        return exporters;
    }

    /**
     * Returns an array containing the names of all registered exporters.
     * 
     * @return an array containing the names of registered exporters
     */
    public String[] getExporterNames() {
        String[] expNames = new String[exporters.size()];
        int i = 0;
        for (AbstractExporter exporter : exporters) {
            expNames[i++] = exporter.getName();
        }
        return expNames;
    }

    /**
     * Returns the exporter with the given name or null if no such exporter
     * exists.
     * 
     * @param name
     *            the exporters name
     * @return the exporter or null if no exporter with the given name exists
     */
    public AbstractExporter getExporterByName(final String name) {
        for (AbstractExporter exporter : exporters) {
            if (exporter.getName().equals(name)) {
                return exporter;
            }
        }
        return null;
    }
}
