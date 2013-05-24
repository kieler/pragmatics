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
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.google.common.collect.Lists;
import com.google.inject.Injector;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.text.KGraphStandaloneSetup;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;
import de.cau.cs.kieler.kiml.ui.service.LayoutOptionManager;

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
            "../../../models/graphs/klay_layered_tests/";

    /** the GMF file formats to load. */
    private static final ArrayList<String> GMF_GRAPHS_FORMATS = Lists.newArrayList("kegdi", "kaod",
            "kids");
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
            case GMF:
                graphTestObject.addAll(loadGMFGraphs(testPath.getFolder(),
                        testPath.isLoadSubfolder(), testPath.isDoLayout()));
                break;
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

                // apply layout when applyLayout = true
                if (doLayout) {
                    EclipseLayoutInfoService.getInstance().getLayoutEngine()
                            .layout(graph, new BasicProgressMonitor());
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
     * Load all GMF graphs under the given folder and sub-folder(optional) and apply the layout
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
    public static List<GraphTestObject> loadGMFGraphs(final String folder, final boolean subfolder,
            final boolean doLayout) {

        File rootFolder = new File(SOURCE_GRAPHS_DIRECTORY, folder);

        // test if the root folder is readable by the application
        if (rootFolder.canRead()) {

            // load files from the directory
            List<File> graphFiles =
                    loadFilesFromDirectory(rootFolder, subfolder, GMF_GRAPHS_FORMATS);
            Collections.sort(graphFiles);
            List<GraphTestObject> graphObjects = new ArrayList<GraphTestObject>();
            LayoutOptionManager mng = new LayoutOptionManager();
            
            for (File gfile : graphFiles) {
                LayoutMapping<?> mapping = getLayoutMappingForGraphFile(gfile);

                // load possible {@link ILayoutConfig}s.
                mng.configure(mapping, new BasicProgressMonitor());
                
                // apply layout when applyLayout = true
                if (doLayout) {
                    DiagramLayoutEngine.INSTANCE.layout(mapping, new BasicProgressMonitor());
                }

                // add the KNode to the list
                graphObjects.add(new GraphTestObject(gfile, mapping.getLayoutGraph()));
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

    /**
     * Method to return the KNode graph from a given File.
     * 
     * @param File
     *            the file to convert into KNode
     * @return the KNode file
     */
    private static LayoutMapping<?> getLayoutMappingForGraphFile(final File file) {
        // load the notation diagram element
        URI uri = URI.createFileURI(file.toString());
        ResourceSet resourceSet = new ResourceSetImpl();
        final Resource resource = resourceSet.createResource(uri);

        try {
            resource.load(Collections.emptyMap());
        } catch (IOException e) {
            throw new WrappedException(e);
        }
        if (resource.getContents().isEmpty() || !(resource.getContents().get(0) instanceof Diagram)) {
            throw new IllegalArgumentException("The selected file does not contain a diagram: "
                    + file);
        }

        // create a diagram edit part
        TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(resourceSet);
        final Maybe<LayoutMapping<?>> mapping = new Maybe<LayoutMapping<?>>();
        final Maybe<Throwable> wrappedException = new Maybe<Throwable>();
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                try {
                    Diagram diagram = (Diagram) resource.getContents().get(0);
                    OffscreenEditPartFactory offscreenFactory =
                            OffscreenEditPartFactory.getInstance();
                    Shell shell = Display.getDefault().getActiveShell();
                    if (shell == null) {
                        shell = new Shell();
                    }
                    DiagramEditPart editPart =
                            offscreenFactory.createDiagramEditPart(diagram, shell);

                    // retrieve a kgraph representation of the diagram
                    mapping.set(EclipseLayoutInfoService.getInstance().getManager(null, editPart)
                            .buildLayoutGraph(null, editPart));
                } catch (Throwable throwable) {
                    wrappedException.set(throwable);
                }
            }
        });
        if (wrappedException.get() instanceof RuntimeException) {
            throw (RuntimeException) wrappedException.get();
        } else if (wrappedException.get() != null) {
            throw new WrappedException(wrappedException.get());
        }

        return mapping.get();
    }

}
