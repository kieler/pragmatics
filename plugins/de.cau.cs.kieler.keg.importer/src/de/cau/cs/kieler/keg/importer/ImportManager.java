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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * A singleton class for accessing KEG graph import functionality.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public final class ImportManager {

    /** the option for the automatic opening of the diagram file. */
    public static final ImporterOption<Boolean> OPTION_OPEN_DIAGRAM =
            new ImporterOption<Boolean>("importer.openDiagram", //$NON-NLS-1$
                    Messages.ImportManager_open_diagrams_description, true);

    /** the singleton instance. */
    private static ImportManager instance = new ImportManager();

    /** the registered importers. */
    private List<AbstractImporter> importers =
            new LinkedList<AbstractImporter>();
    /** the registered file extensions. */
    private Set<String> extensions = new HashSet<String>();

    /**
     * A private constructor to make this class a singleton.
     */
    private ImportManager() {
        // do nothing
    }

    /**
     * Returns the singleton instance.
     * 
     * @return the singleton instance
     */
    public static ImportManager getInstance() {
        return instance;
    }

    /**
     * Registers a graph importer.
     * 
     * @param imp
     *            the importer
     */
    public void addImporter(final AbstractImporter imp) {
        importers.add(imp);
        for (String extension : imp.getExtensions()) {
            extensions.add(extension);
        }
    }

    /**
     * Returns all registered importers.
     * 
     * @return a list of registered importer
     */
    public List<AbstractImporter> getImporter() {
        return importers;
    }

    /**
     * Returns an array containing the names of all registered importers.
     * 
     * @return an array containing the names of registered importers
     */
    public String[] getImporterNames() {
        String[] expNames = new String[importers.size()];
        int i = 0;
        for (AbstractImporter importer : importers) {
            expNames[i++] = importer.getName();
        }
        return expNames;
    }

    /**
     * Returns the importer with the given name or null if no such importer
     * exists.
     * 
     * @param name
     *            the importers name
     * @return the importer or null if no importer with the given name exists
     */
    public AbstractImporter getImporterByName(final String name) {
        for (AbstractImporter importer : importers) {
            if (importer.getName().equals(name)) {
                return importer;
            }
        }
        return null;
    }

    /**
     * Returns all registered extensions.
     * 
     * @return the extensions
     */
    public String[] getExtensions() {
        return extensions.toArray(new String[0]);
    }

    /**
     * Returns an importer which supports the given file extension or null if no
     * such importer is registered.
     * 
     * @param extension
     *            the extension
     * @return the importer or null if no matching importer is registered
     */
    public AbstractImporter getImporterByExtension(final String extension) {
        for (AbstractImporter importer : importers) {
            for (String importerExtension : importer.getExtensions()) {
                if (extension.equals(importerExtension)) {
                    return importer;
                }
            }
        }
        return null;
    }
}
