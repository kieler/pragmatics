/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 */
package de.cau.cs.kieler.kex.controller.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.KielerModelException;
import de.cau.cs.kieler.kex.controller.ErrorMessage;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleResource;

/**
 * Contains all Elements for an import of examples.
 * 
 * @author pkl
 * 
 */
public final class ExampleImport {

    private static final String WORKSPACE_LOCATION = Platform.getLocation().toString();

    private static final String STANDARD_PIC_PATH = "files/noPreview.png";
    private static final String KEX_NAMESPACE_ID = "de.cau.cs.kieler.kex";

    private ExampleImport() {
        // should not called
    }

    /**
     * This method contains all functions to import an example.
     * 
     * @param selectedResource
     *            , destination resource of type {@link IPath}.
     * @param selectedExamples
     *            , {@link List} of {@link Example}s.
     * @param, checkDuplicate , flag for checking example duplication in project.
     * @throws KielerException
     *             , will throw if any error occurs.
     * @return directopens, {@link List} of {@link String}.
     */
    public static List<String> importExamples(final IPath selectedResource,
            final List<Example> selectedExamples, final boolean checkDuplicate)
            throws KielerException {

        List<String> directOpens = new ArrayList<String>();
        List<String> finishedResources = new ArrayList<String>();

        StringBuilder destFolder = new StringBuilder();
        destFolder.append(WORKSPACE_LOCATION).append("/")
                .append((selectedResource != null ? selectedResource.toString() : "")).append("/");
        try {

            for (Example example : selectedExamples) {

                List<ExampleResource> resources = example.getResources();

                String rootDirectory = example.getRootDir();
                int exampleBeginIndex = 0;
                if (rootDirectory != null && rootDirectory.length() > 1) {
                    exampleBeginIndex = rootDirectory.length();
                }

                handleResources(directOpens, resources, destFolder.toString(),
                        example.getNamespaceId(), exampleBeginIndex, checkDuplicate,
                        finishedResources);
            }
        } catch (KielerException e) {
            deleteResources(finishedResources);
            throw e;
        }
        return directOpens;
    }

    private static void deleteResources(final List<String> finishedResources) {
        for (String resourcePath : finishedResources) {
            IOHandler.deleteFile(new File(resourcePath));
        }
    }

    private static void handleResources(final List<String> directOpens,
            final List<ExampleResource> resources, String destFolder, final String nameSpaceId,
            final int exampleBeginIndex, final boolean checkDuplicate,
            final List<String> finishedResources) throws KielerException {
        Bundle bundle = Platform.getBundle(nameSpaceId);

        for (ExampleResource resource : resources) {
            try {
                String localPath = resource.getLocalPath();
                String destPath = localPath.substring(exampleBeginIndex);

                switch (resource.getResourceType()) {
                case PROJECT:
                    checkDuplicate(destPath);
                    IProgressMonitor progressMonitor = new NullProgressMonitor();
                    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
                    IProject project = root.getProject(destPath);
                    project.create(progressMonitor);
                    project.open(progressMonitor);
                    destFolder = root.getLocation().toPortableString();
                    break;

                case FOLDER:
                    File destFile = new File(destFolder + "/" + destPath);
                    finishedResources.add(destFile.getPath());
                    if (checkDuplicate && destFile.exists()) {
                        throw new KielerModelException(ErrorMessage.DUPLICATE_EXAMPLE,
                                destFile.getName());
                    }
                    IOHandler.createFolder(destFolder + "/" + destPath);
                    break;

                case FILE:
                    URL entry = bundle.getEntry(localPath);
                    String dest = destFolder + "/" + destPath;
                    finishedResources.add(dest);
                    IOHandler.writeFile(entry, dest, checkDuplicate);
                    if (resource.isDirectOpen()) {
                        directOpens.add(dest);
                    }
                    break;

                }
            } catch (FileNotFoundException e) {
                throw new KielerException(ErrorMessage.NO_Import, e);
            } catch (IOException e1) {
                throw new KielerException(ErrorMessage.NO_Import, e1);
            } catch (CoreException e2) {
                throw new KielerException(ErrorMessage.NO_Import + e2.getMessage());
            }
        }
    }

    private static void checkDuplicate(final String destPath) throws KielerException {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(destPath);
        if (project != null && project.exists()) {
            throw new KielerException("Duplicate Project, you maybe inserted it before.");
        }
    }

    /**
     * Loads the preview pic of an example.
     * 
     * @param example
     *            , {@link Example}
     * @return {@link InputStream}
     * @throws KielerException
     *             , if anything goes wrong.
     */
    public static InputStream loadOverviewPic(final Example example) throws KielerException {
        Bundle bundle = Platform.getBundle(example.getNamespaceId());
        URL entry = bundle.getEntry(example.getOverviewPic());
        try {
            return entry.openStream();
        } catch (IOException e) {
            throw new KielerException(ErrorMessage.PREVIEW_LOAD_ERROR + example.getTitle());
        }
    }

    /**
     * loads the standard picture of an example.
     * 
     * @return {@link InputStream}
     */
    public static InputStream loadStandardPic() {
        Bundle bundle = Platform.getBundle(ExampleImport.KEX_NAMESPACE_ID);
        URL entry = bundle.getEntry(ExampleImport.STANDARD_PIC_PATH);
        try {
            return entry.openStream();
        } catch (IOException e) {
            // should not happen at runtime
            e.printStackTrace();
        }
        return null;
    }

    /**
     * validates the selected examples.
     * 
     * @param selectedResource
     *            , {@link IPath}
     * @param selectedExamples
     *            , {@link List} of {@link Example}
     * @param checkDuplicate
     *            , boolean
     * @throws KielerException
     *             , if any error occurs.
     */
    public static void validate(final IPath selectedResource, final List<Example> selectedExamples,
            final boolean checkDuplicate) throws KielerException {
        if (selectedExamples == null || selectedExamples.size() == 0) {
            throw new KielerException(ErrorMessage.NO_EXAMPLE_SELECTED);
        }
        // could happen that user wants to import a project and a example in a
        // other project
        boolean allProjects = false;
        for (Example example : selectedExamples) {
            if (example.isProject()) {
                allProjects = true;
            } else {
                allProjects = false;
                break;
            }
        }
        // FIXME it is possible to choose a project and additional files at
        // export.
        // that would cause a BANG! :-), that shouldn�t be possible

        // projects do not need a destinatin resource
        if (!allProjects) {
            if (selectedResource == null || selectedResource.segmentCount() == 0) {
                throw new KielerException(ErrorMessage.NO_DEST_SET);
            }
        }
    }
}
