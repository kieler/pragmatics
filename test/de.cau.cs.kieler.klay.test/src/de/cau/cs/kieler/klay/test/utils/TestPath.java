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

/**
 * The test folder properties. Define in which folder test framework to load the files and to set
 * the load option (load sub-folders, apply the layout algorithm)
 * 
 * @author Wahbi
 */
public class TestPath {

    /** the folder containing the graph files. */
    private String folder;
    /** if subfolder = true load graphs in subfolder. */
    private boolean loadSubfolder;
    /** if doLayout = true apply layout on graphs. */
    private boolean doLayout;

    /**
     * The constructor of the Class This object is used to define in which folder test framework to
     * load the files and to set the load option (load sub-folders, apply the layout algorithm).
     * 
     * @param folder the folder containing the graph files
     * @param subfolder if true load graphs in subfolder
     * @param doLayout if true apply layout on graphs
     */
    public TestPath(final String folder, final boolean subfolder, final boolean doLayout) {
        this.folder = folder;
        this.loadSubfolder = subfolder;
        this.doLayout = doLayout;
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
     * Set the folder.
     * 
     * @param folder
     *            the folder to set
     */
    public void setFolder(final String folder) {
        this.folder = folder;
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
     * Set the loadSubFolder.
     * 
     * @param subfolder
     *            the subfolder to set
     */
    public void setLoadSubfolder(final boolean subfolder) {
        this.loadSubfolder = subfolder;
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
     * Set the doLayout option. Set to True to apply the layout.
     * 
     * @param doLayout
     *            the doLayout to set
     */
    public void setDoLayout(final boolean doLayout) {
        this.doLayout = doLayout;
    }
    
}
