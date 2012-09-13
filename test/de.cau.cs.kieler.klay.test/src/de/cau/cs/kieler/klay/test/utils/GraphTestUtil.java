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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * Class to load graphs from a given folder and its subfolders (optional).
 * 
 * @author wah
 */

public final class GraphTestUtil {

    /** the test graph root directory */
    private static final String SOURCE_GRAPHS_DIRECTORY = "D:\\Uni-Kiel\\kieler\\Examples\\";
    /** the source file format to load */
    private static final ArrayList<String> SOURCE_GRAPHS_FORMAT = new ArrayList<String>(
            Arrays.asList("kegdi", "kaod", "kids"));

    /**
     * A private constructor to prevent instantiation
     */
    private GraphTestUtil() {
    }

    /**
     * Load all graphs under the given List of TestPaths.
     * 
     * @param bundleTestPaths
     *            a list of the test folder + properties
     * @return a list of KNodes
     */
    public static List<GraphTestObject> loadGraphs(TestPath[] bundleTestPaths) {
        // Initialize a List of GraphTestObject to be filled in with GraphTestObjects from each
        // bundleTestPath
        List<GraphTestObject> graphTestObject = new ArrayList<GraphTestObject>();
        for (TestPath testPath : bundleTestPaths) {
            // For each TestPath load the graph files contained in its appropriate folder
            graphTestObject.addAll(loadGMFGraphs(testPath.getFolder(), testPath.isLoadSubfolder(),
                    testPath.isDoLayout()));
        }
        return graphTestObject;
    }

    /**
     * Load all graphs under the given folder. Graphs under sub-folders are excluded.
     * 
     * @param folder
     *            the folder where the graphs are located
     * @return a list of KNode
     */
    public static List<GraphTestObject> loadGraphs(String folder) {
        return loadGMFGraphs(folder, false, false);
    }

    /**
     * Load all graphs under the given folder and sub-folder(optional).
     * 
     * @param folder
     *            the folder where the graphs are located
     * @param subforlder
     *            if true, load all graphs under sub-folders
     * @return a list of KNode
     */
    public static List<GraphTestObject> loadGraphs(String folder, boolean subfolder) {
        return loadGMFGraphs(folder, subfolder, false);
    }

    /**
     * Load all graphs under the given folder and sub-folder(optional) and apply the layout
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
    public static List<GraphTestObject> loadGMFGraphs(String folder, boolean subfolder,
            boolean doLayout) {
        // the root folder where graphs are located
        File rootFolder = new File(SOURCE_GRAPHS_DIRECTORY, folder);

        // test if the root folder is readable by the application
        if (rootFolder.canRead()) {

            // load files from the directory
            List<File> graphFiles = loadFilesFromDirectory(rootFolder,
                    subfolder);

            // Test if there is files
            if (graphFiles.size() > 0) {

                List<GraphTestObject> graphObjects = new ArrayList<GraphTestObject>();
                for (File gfile : graphFiles) {

                    LayoutMapping<?> mapping = getLayoutMappingForGraphFile(gfile);
                    // apply layout when applyLayout = true
                    if (doLayout)
                        DiagramLayoutEngine.INSTANCE.layout(mapping, new BasicProgressMonitor());
                    // add the KNode to the list
                    graphObjects.add(new GraphTestObject(gfile, mapping.getLayoutGraph()));
                }

                return graphObjects;

            } else {
                throw new IllegalArgumentException("The given directory (" + folder
                        + ") doesn't contain graph files!");
            }
        } else {
            throw new IllegalArgumentException("The source graph directory (" + folder
                    + ") cannot be read!");
        }
    }

    /**
     * Method to return the file extension from a file name.
     * 
     * @param file
     *            the file name.
     * @return the file extension, in lower case.
     */
    private static String getFileExtension(String file) {
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
    private static List<File> loadFilesFromDirectory(File folder,
            boolean subfolder) {
        List<File> files = new ArrayList<File>();
        // filter to select only files with SOURCE_GRAPHS_FORMAT extension or
        // sub directories
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    return true;
                }
                if (getFileExtension(pathname.toString()) != null) {
                    return SOURCE_GRAPHS_FORMAT.contains(getFileExtension(pathname.toString()));
                }
                return false;
            }
        };
        // load files from directory
        File[] listOfFiles = folder.listFiles(filter);
        for (int i = 0; i < listOfFiles.length; i++) {
            // load only folders and files with SOURCE_GRAPHS_FORMAT extension
            if (listOfFiles[i].isFile()) {
                files.add(listOfFiles[i]);
            } else if (listOfFiles[i].isDirectory() && subfolder) {
                files.addAll(loadFilesFromDirectory(listOfFiles[i], subfolder));
                // loadFilesFromDirectory(listOfFiles[i], files, subfolder);
            }
        }
        return files;
    }

    /**
     * Method to return the KNode graph from a given File.
     * 
     * @param File
     *            the file to convert into KNode
     * @return the KNode file
     */
    private static LayoutMapping<?> getLayoutMappingForGraphFile(File file) {
        // load the notation diagram element
        URI uri = URI.createFileURI(file.toString());
        ResourceSet resourceSet = new ResourceSetImpl();
        final Resource resource = resourceSet.createResource(uri);

        try {
            resource.load(Collections.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (resource.getContents().isEmpty() || !(resource.getContents().get(0) instanceof Diagram)) {
            throw new IllegalArgumentException("The selected file does not contain a diagram: "
                    + file);
        }

        // create a diagram edit part
        TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(resourceSet);
        final Maybe<DiagramEditPart> editPart = new Maybe<DiagramEditPart>();
        final Maybe<RuntimeException> wrappedException = new Maybe<RuntimeException>();
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                try {
                    Diagram diagram = (Diagram) resource.getContents().get(0);
                    OffscreenEditPartFactory offscreenFactory = OffscreenEditPartFactory
                            .getInstance();
                    editPart.set(offscreenFactory.createDiagramEditPart(diagram, new Shell()));
                } catch (RuntimeException re) {
                    wrappedException.set(re);
                }
            }
        });
        if (wrappedException.get() != null) {
            throw wrappedException.get();
        }

        // retrieve a kgraph representation of the diagram
        LayoutMapping<?> mapping = EclipseLayoutInfoService.getInstance()
                .getManager(null, editPart.get()).buildLayoutGraph(null, editPart.get());

        return mapping;
    }
}
