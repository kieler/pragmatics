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
package de.cau.cs.kieler.klay.test.runner;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.GraphTestUtil;

/**
 * 
 * @author Wahbi
 */
@RunWith(KlayTestRunner.class)
public abstract class KlayAutomatedJUnitTest {

    /** The graph files. */
    private static List<GraphTestObject> graphsList = new LinkedList<GraphTestObject>();

    /**
     * @param file
     */
    public KlayAutomatedJUnitTest(File file) {
    }

    @Parameters
    public static Collection<Object[]> getGraphs() {
        LinkedList<Object[]> graphFilesList = new LinkedList<Object[]>();

        for (GraphTestObject file : graphsList) {
            Object[] objectArray = new Object[1];
            objectArray[0] = file.getFile();
            graphFilesList.add(objectArray);
        }
        return graphFilesList;
    }

    /**
     * 
     */
    @Before
    public void GraphAutomatedTestInitialization() {
        graphsList.clear();
        graphsList = GraphTestUtil.loadGraphs(getBundleTestPath());

    }

    /**
     * Wraps up klay pointer that is stored statically.
     */
    @AfterClass
    public static void kiemAutomatedJUnitTestWrapup() {
        // Clear all static fields
        graphsList.clear();
    }

    /**
     * Important for scanning files is the Plugin ID.
     * 
     * @return the plugin id
     */
    protected String getPluginId() {
        return "de.cau.cs.kieler.klay.test";
    }

    // -------------------------------------------------------------------------

    /**
     * Defines the directory where the graph files are located.
     * 
     * @return the Path
     */
    protected abstract TestPath[] getBundleTestPath();

    // ------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------

    /**
     * The test folder properties
     * 
     * @author Wahbi
     */
    public class TestPath {

        /** the folder containing the graph files */
        private String folder;
        /** if subfolder = true load graphs in subfolder */
        private boolean loadSubfolder;
        /** if doLayout = true apply layout on graphs */
        private boolean doLayout;

        /**
         * The constructor
         * 
         * @param folder
         * @param subfolder
         * @param doLayout
         */
        public TestPath(String folder, boolean subfolder, boolean doLayout) {
            this.folder = folder;
            this.loadSubfolder = subfolder;
            this.doLayout = doLayout;
        }

        /**
         * Return the folder
         * 
         * @return the folder
         */
        public String getFolder() {
            return folder;
        }

        /**
         * Set the folder
         * 
         * @param folder
         *            the folder to set
         */
        public void setFolder(String folder) {
            this.folder = folder;
        }

        /**
         * Return true if load the subfolder and else otherwise
         * 
         * @return the subfolder
         */
        public boolean isLoadSubfolder() {
            return loadSubfolder;
        }

        /**
         * Set the loadSubFolder
         * 
         * @param subfolder
         *            the subfolder to set
         */
        public void setLoadSubfolder(boolean subfolder) {
            this.loadSubfolder = subfolder;
        }

        /**
         * Return true if apply the layout and else otherwise
         * 
         * @return the doLayout
         */
        public boolean isDoLayout() {
            return doLayout;
        }

        /**
         * @param doLayout
         *            the doLayout to set
         */
        public void setDoLayout(boolean doLayout) {
            this.doLayout = doLayout;
        }
    }
}
