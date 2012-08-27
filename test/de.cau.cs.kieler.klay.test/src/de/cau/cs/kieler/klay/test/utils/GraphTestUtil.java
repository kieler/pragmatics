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
import de.cau.cs.kieler.klay.test.runner.KlayAutomatedJUnitTest.TestPath;

/**
 * class to load graphs from a given folder and its subfolders (optional)
 * 
 * @author wah
 * 
 */

public class GraphTestUtil {

    /** the test graph root directory */
    private static final String SOURCE_GRAPHS_DIRECTORY = "D:\\Uni-Kiel\\kieler\\Examples\\";
    /** the source file format to load */
    private static final ArrayList<String> SOURCE_GRAPHS_FORMAT = new ArrayList<String>(
            Arrays.asList("kegdi", "kaod", "kids"));

    /**
     * Load all graphs under the given List of TestPaths.
     * 
     * @param TestPaths
     * @return a list of KNodes
     */
    public static List<GraphTestObject> loadGraphs(TestPath[] bundleTestPaths) {
        // Initialise a List of GraphTestObject
        List<GraphTestObject> graphTestObject = new ArrayList<GraphTestObject>();
        for (TestPath testPath : bundleTestPaths) {
            // For each TestPath load the graph files contained in its appropriate folder
            graphTestObject.addAll(loadGraphsHandler(testPath.getFolder(),
                    testPath.isLoadSubfolder(), testPath.isDoLayout()));
        }
        return graphTestObject;
    }

    /**
     * Load all graphs under the given folder.
     * 
     * @param folder
     *            the folder where the graphs are located
     * @return a list of KNode
     */
    public static List<GraphTestObject> loadGraphs(String folder) {
        return loadGraphsHandler(folder, false, false);
    }

    /**
     * Load all graphs under the given folder and subfolder(optional).
     * 
     * @param folder
     *            the folder where the graphs are located
     * @param subforlder
     * @return a list of KNode
     */
    public static List<GraphTestObject> loadGraphs(String folder, boolean subfolder) {
        return loadGraphsHandler(folder, subfolder, false);
    }

    /**
     * Load all graphs under the given folder and subfolder(optional) and apply the layout
     * algorithm(optional).
     * 
     * @param folder
     *            the folder where the graphs are located
     * @param subfolder
     *            if true then load subfolder graphs else only the given directory
     * @param doLayout
     *            apply the layout algorithm
     * @return a list of KNode
     */
    public static List<GraphTestObject> loadGraphs(String folder, boolean subfolder,
            boolean doLayout) {
        return loadGraphsHandler(folder, subfolder, doLayout);
    }

    /**
     * 
     * load all graphs under the given folder and subfolder(optional) and apply the layout
     * algorithm(optional).
     * 
     * @param folder
     *            the folder where the graphs are located
     * @param subfolder
     *            if true then load subfolder graphs else only the given directory
     * @param doLayout
     *            apply the layout algorithm
     * @return a list of KNode
     */
    private static List<GraphTestObject> loadGraphsHandler(String folder, boolean subfolder,
            boolean doLayout) {

        // the root folder where graphs are located
        File rootFolder = new File(SOURCE_GRAPHS_DIRECTORY.concat(folder));

        // test if the root folder exists
        if (rootFolder.exists()) {

            // load files from the directory
            List<File> graphFiles = loadFilesFromDirectory(rootFolder, new ArrayList<File>(),
                    subfolder);

            // Test if there is files
            if (graphFiles.size() > 0) {

                List<GraphTestObject> graphObjects = new ArrayList<GraphTestObject>();
                for (File gfile : graphFiles) {

                    LayoutMapping<?> mapping = getGraph(gfile);
                    // apply layout when applyLayout = true
                    if (doLayout)
                        applyLayout(mapping);
                    // add the KNode to the list
                    graphObjects.add(new GraphTestObject(gfile, mapping.getLayoutGraph()));
                }

                return graphObjects;

            } else {
                throw new IllegalArgumentException(
                        "The given directory doesn't contain graph files!");
            }
        } else {
            throw new IllegalArgumentException("The source graph directory doesn't exists!");
        }
    }

    /**
     * Method to return the file extension from a file name.
     * 
     * @param file
     *            the file name
     * @return the file extension
     */
    private static String getFileExtension(String file) {
        // get the last dot position
        int dotPos = file.lastIndexOf(".");
        if (dotPos >= 0) {
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
    private static List<File> loadFilesFromDirectory(File folder, List<File> files,
            boolean subfolder) {
        // filter to select only files with SOURCE_GRAPHS_FORMAT extension or
        // sub directories
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    return true;
                }
                if (getFileExtension(pathname.getName()) != null) {

                    return SOURCE_GRAPHS_FORMAT.contains(getFileExtension(pathname.getName()));
                }
                return false;
            }
        };
        // load files from directory
        File[] listOfFiles = folder.listFiles(filter);
        if (listOfFiles.length > 0) {
            for (int i = 0; i < listOfFiles.length; i++) {
                // load only files and files with SOURCE_GRAPHS_FORMAT extension
                if (listOfFiles[i].isFile()) {
                    files.add(listOfFiles[i]);
                } else if (listOfFiles[i].isDirectory() && subfolder) {
                    // files.addAll(loadFilesFromDirectory(listOfFiles[i],
                    // files, subfolder));
                    loadFilesFromDirectory(listOfFiles[i], files, subfolder);
                }
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
    private static LayoutMapping<?> getGraph(File file) {
        // load the notation diagram element
        // URI uri = URI.createPlatformResourceURI(file.toString(), true);
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

        // KNode inputGraph = mapping.getLayoutGraph();
        // return inputGraph;
        return mapping;
    }

    /**
     * Apply the layout algorithm
     * 
     * @param LayoutMapping
     *            <?> graph
     */
    private static void applyLayout(LayoutMapping<?> mapping) {
        DiagramLayoutEngine.INSTANCE.layout(mapping, new BasicProgressMonitor());
    }

}
