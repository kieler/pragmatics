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

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import com.google.common.collect.Lists;
import com.google.inject.Injector;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.text.KGraphStandaloneSetup;
import de.cau.cs.kieler.kiml.IGraphLayoutEngine;
import de.cau.cs.kieler.kiml.RecursiveGraphLayoutEngine;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Class to load graphs from a given folder and its subfolders (optional).
 * 
 * @author wah
 */
public final class GraphTestUtil {

    /**
     * The test graph root directory. This is given relative to the working directory, which is the
     * test plugin's root. The path below leads to the repository root and then into the
     * {@code models} subfolder, which contains all test graphs.
     */
    private static final String SOURCE_GRAPHS_DIRECTORY =
            "../../../models/graphs/";

    /** the KGraph file formats to load. */
    private static final ArrayList<String> KGRAPH_FORMATS = Lists.newArrayList("kgraph", "kgx",
            "kgt");

    private static Injector xtextInjector = new KGraphStandaloneSetup()
            .createInjectorAndDoEMFRegistration();

    /**
     * A private constructor to prevent instantiation.
     */
    private GraphTestUtil() {
    }

    /**
     * Load all graphs under the given list of test paths.
     * 
     * @param bundleTestPaths
     *            a list of the test folder + properties
     * @return a list of KNodes
     */
    public static List<GraphTestObject> loadGraphs(final TestPath[] bundleTestPaths) {
        // Initialize a List of GraphTestObject to be filled in with GraphTestObjects from each
        // bundleTestPath
        List<GraphTestObject> graphTestObject = new ArrayList<GraphTestObject>();
        for (TestPath testPath : bundleTestPaths) {
            // For each TestPath load the graph files contained in its appropriate folder
            switch (testPath.getType()) {
            // uru: we do not support GMF models during testing anymore
            // case GMF:
            // graphTestObject.addAll(loadGMFGraphs(testPath.getFolder(),
            // testPath.isLoadSubfolder(), testPath.isDoLayout()));
            // break;
            case KGRAPH:
                graphTestObject.addAll(loadKGraphs(testPath.getFolder(),
                        testPath.isLoadSubfolder(), testPath.isDoLayout()));
                break;
            }
        }
        return graphTestObject;
    }

    /**
     * Load all KGraphs under the given folder and sub-folder(optional) and apply the layout
     * algorithm(optional).
     * 
     * @param folder
     *            the folder where the graphs are located
     * @param subfolder
     *            if true then load sub-folder graphs else only the given directory
     * @param doLayout
     *            if true, apply automatic layout to loaded graphs
     * @return a list of KNode
     */
    public static List<GraphTestObject> loadKGraphs(final String folder, final boolean subfolder,
            final boolean doLayout) {
        IGraphLayoutEngine layoutEngine = new RecursiveGraphLayoutEngine();
        File rootFolder = new File(SOURCE_GRAPHS_DIRECTORY, folder);

        // test if the root folder is readable by the application
        if (rootFolder.canRead()) {

            // load files from the directory
            List<File> graphFiles = loadFilesFromDirectory(rootFolder, subfolder, KGRAPH_FORMATS);
            Collections.sort(graphFiles);
            List<GraphTestObject> graphObjects = new ArrayList<GraphTestObject>();
            for (File gfile : graphFiles) {
                URI uri = URI.createFileURI(gfile.toString());
                ResourceSet resourceSet = xtextInjector.getInstance(ResourceSet.class);
                Resource resource = resourceSet.createResource(uri);

                try {
                    resource.load(Collections.emptyMap());
                } catch (IOException e) {
                    throw new WrappedException(e);
                }
                if (resource.getContents().isEmpty()
                        || !(resource.getContents().get(0) instanceof KNode)) {
                    throw new IllegalArgumentException(
                            "The selected file does not contain a graph: " + gfile);
                }
                KNode graph = (KNode) resource.getContents().get(0);
                // parse persisted key-value pairs using KIML's layout data service
                KimlUtil.loadDataElements(graph);
                
                // apply layout when applyLayout = true
                if (doLayout) {
                    layoutEngine.layout(graph, new BasicProgressMonitor());
                }

                // add the KNode to the list
                graphObjects.add(new GraphTestObject(gfile, graph));
            }

            return graphObjects;

        } else {
            throw new IllegalArgumentException("The source graph directory ("
                    + rootFolder.getAbsolutePath() + ") cannot be read!");
        }
    }

    /**
     * Method to return the file extension from a file name.
     * 
     * @param file
     *            the file name.
     * @return the file extension, in lower case.
     */
    private static String getFileExtension(final String file) {
        // get the last dot position
        int dotPos = file.lastIndexOf(".");
        if (dotPos >= 0) {
            // this is safe because using the string length as an argument to substring returns an
            // empty string
            return file.substring(dotPos + 1).toLowerCase();
        } else {
            return null;
        }
    }

    /**
     * Method to load all graph files under a given directory. This Method is called recursively (if
     * subfolder is set to true) to load all subcategories files.
     * 
     * @param folder
     *            the folder where the graphs are located
     * @param files
     *            List of found files to transfer if the method is called recursively
     * @param subfolder
     *            if true then load subfolder graphs else only the given directory
     * @return return the List of graph files
     */
    private static List<File> loadFilesFromDirectory(final File folder, final boolean subfolder,
            final List<String> extensions) {
        List<File> files = new ArrayList<File>();
        // filter to select only files with SOURCE_GRAPHS_FORMAT extension or sub directories
        FileFilter filter = new FileFilter() {
            public boolean accept(final File pathname) {
                if (pathname.isDirectory()) {
                    return true;
                }
                if (getFileExtension(pathname.toString()) != null) {
                    return extensions.contains(getFileExtension(pathname.toString()));
                }
                return false;
            }
        };
        // load files from directory
        File[] listOfFiles = folder.listFiles(filter);
        if (listOfFiles != null) {
            for (int i = 0; i < listOfFiles.length; i++) {
                // load only folders and files with SOURCE_GRAPHS_FORMAT extension
                if (listOfFiles[i].isFile()) {
                    files.add(listOfFiles[i]);
                } else if (listOfFiles[i].isDirectory() && subfolder) {
                    files.addAll(loadFilesFromDirectory(listOfFiles[i], subfolder, extensions));
                }
            }
        }
        return files;
    }

}
