/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.test.utils;

import java.util.Collections;
import java.util.Set;

/**
 * The test folder properties. Defines in which folder test framework to load the files and to set
 * the load option (load sub-folders, apply the layout algorithm).
 * 
 * @author wah
 */
public class TestPath {
    
    /** Enumeration of graph types. */
    public static enum Type {
        /** KGraph files. */
        KGRAPH
        /** GMF diagram files. */
        // uru: we do not allow GMF models anymore as they only cause headaches
        // GMF;
    }

    /** the folder containing the graph files. */
    private String folder;
    /** subfolders to be ignored when recursing. */
    private Set<String> excludeFolders;
    /** if subfolder = true load graphs in subfolder. */
    private boolean loadSubfolder;
    /** if doLayout = true apply layout on graphs. */
    private boolean doLayout;
    /** the type of graphs to load. */
    private Type type;

    /**
     * The constructor of the Class This object is used to define in which folder test framework to
     * load the files and to set the load option (load sub-folders, apply the layout algorithm).
     * 
     * @param folder the folder containing the graph files
     * @param subfolder if true load graphs in subfolder
     * @param doLayout if true apply layout on graphs
     * @param type the type of graphs to fetch
     */
    public TestPath(final String folder, final boolean subfolder, final boolean doLayout,
            final Type type) {
        this.folder = folder;
        this.loadSubfolder = subfolder;
        this.doLayout = doLayout;
        this.type = type;
        this.excludeFolders = Collections.emptySet();
    }
    
    /**
     * The constructor of the Class This object is used to define in which folder test framework to
     * load the files and to set the load option (load sub-folders, apply the layout algorithm).
     * 
     * @param folder the folder containing the graph files
     * @param subfolder if true load graphs in subfolder
     * @param doLayout if true apply layout on graphs
     * @param type the type of graphs to fetch
     * @param excludeSubfolder a set of directory names that will 
     *                  be ignored if {@code subfolder} is {@code true}.
     */
    public TestPath(final String folder, final boolean subfolder, final boolean doLayout,
            final Type type, final Set<String> excludeSubfolder) {
        this.folder = folder;
        this.loadSubfolder = subfolder;
        this.doLayout = doLayout;
        this.type = type;
        this.excludeFolders = excludeSubfolder;
    }

    /**
     * Return the folder.
     * 
     * @return the folder
     */
    public String getFolder() {
        return folder;
    }

    /**
     * Return true if load the subfolder and else otherwise.
     * 
     * @return the subfolder
     */
    public boolean isLoadSubfolder() {
        return loadSubfolder;
    }

    /**
     * Return true if apply the layout and else otherwise.
     * 
     * @return the doLayout
     */
    public boolean isDoLayout() {
        return doLayout;
    }
    
    /**
     * Returns the type of graphs that shall be fetched.
     * 
     * @return the graph type
     */
    public Type getType() {
        return type;
    }
    
    /**
     * @return the excludeFolders
     */
    public Set<String> getExcludeFolders() {
        return excludeFolders;
    }
    
}
