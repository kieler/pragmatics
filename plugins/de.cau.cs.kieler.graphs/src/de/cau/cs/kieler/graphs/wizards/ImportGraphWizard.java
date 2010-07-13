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
package de.cau.cs.kieler.graphs.wizards;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;
import org.graphdrawing.graphml.xmlns.XmlnsPackage;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.model.util.XtendTransformationUtil;
import de.cau.cs.kieler.graphs.GraphsPackage;
import de.cau.cs.kieler.graphs.GraphsPlugin;

/**
 * A wizard for importing graphs from various file formats into a graphs file.
 * 
 * @author mri
 */
public class ImportGraphWizard extends Wizard implements IImportWizard {

    /** the message for telling the user that the specified file was not valid. */
    private static final String MESSAGE_INVALID_FILE =
            "The specified file isn't a valid graph file.";

    /** the selection the import wizard was called on. */
    private IStructuredSelection selection;
    /** the wizard page from which to select the new file. */
    private ImportGraphNewFilePage newFilePage;
    /** the wizard page from which to select the import file. */
    private ImportGraphWizardPage importPage;
    /** was the last import a success? */
    private boolean success;

    /**
     * Constructs a graph import wizard.
     */
    public ImportGraphWizard() {
        super();
        setWindowTitle("Import");
    }

    /**
     * {@inheritDoc}
     */
    public void addPages() {
        newFilePage = new ImportGraphNewFilePage(selection);
        importPage = new ImportGraphWizardPage();
        addPage(newFilePage);
        addPage(importPage);
    }

    /**
     * {@inheritDoc}
     */
    public boolean performFinish() {
        final String containerName =
                newFilePage.getContainerFullPath().toString();
        final String fileName = newFilePage.getFileName();
        final String importFileName = importPage.getFileName();
        success = false;
        IRunnableWithProgress op = new IRunnableWithProgress() {
            public void run(final IProgressMonitor monitor)
                    throws InvocationTargetException {
                try {
                    success =
                            doFinish(importFileName, containerName, fileName,
                                    monitor);
                } catch (CoreException e) {
                    throw new InvocationTargetException(e);
                } catch (IOException e) {
                    throw new InvocationTargetException(e);
                } finally {
                    monitor.done();
                }
            }
        };
        try {
            getContainer().run(true, false, op);
        } catch (InterruptedException exception) {
            return false;
        } catch (InvocationTargetException exception) {
            Throwable realException = exception.getTargetException();
            Status status =
                    new Status(IStatus.ERROR, GraphsPlugin.PLUGIN_ID, 0,
                            "Error while creating file.", realException);
            StatusManager.getManager().handle(status, StatusManager.SHOW);
            return false;
        }

        return success;
    }

    /**
     * Find the container and create the file if missing or just replace its contents.
     * 
     * @param importFileName
     *            name of the import file
     * @param containerName
     *            name of the container project or folder
     * @param fileName
     *            file name
     * @param monitor
     *            progress monitor
     * @return true if the import succeeded
     * @throws IOException
     *             if writing the file fails
     * @throws CoreException
     *             if container does not exist or related things
     */
    private boolean doFinish(final String importFileName,
            final String containerName, final String fileName,
            final IProgressMonitor monitor) throws IOException, CoreException {
        // create a sample file
        monitor.beginTask("Creating File" + fileName, 2);
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IResource resource = root.findMember(new Path(containerName));
        if (!resource.exists() || !(resource instanceof IContainer)) {
            IStatus status =
                    new Status(IStatus.ERROR, GraphsPlugin.PLUGIN_ID, 0,
                            "Container \"" + containerName
                                    + "\" does not exist.", null);
            throw new CoreException(status);
        }
        IContainer container = (IContainer) resource;

        final String transformation = "transformations/graphml2graphs";
        final String fun = "transform";
        URI input = URI.createURI("");
        URI output = URI.createURI("");

        EPackage p1 = GraphsPackage.eINSTANCE;
        EPackage p2 = XmlnsPackage.eINSTANCE;

        Status myStatus = null;
        try {
            input = URI.createURI(importFileName, true);
            output =
                    URI.createPlatformResourceURI(
                            container.getFile(new Path(fileName)).getFullPath()
                                    .toString(), true);
            XtendTransformationUtil.model2ModelTransform(transformation, fun,
                    input, output, p1, p2);
        } catch (KielerException e) {
            myStatus =
                    new Status(
                            IStatus.ERROR,
                            GraphsPlugin.PLUGIN_ID,
                            "Failed to transform GraphML model into Graphs model.",
                            e);
        } catch (NullPointerException npe) {
            myStatus =
                    new Status(
                            IStatus.ERROR,
                            GraphsPlugin.PLUGIN_ID,
                            "Failed to transform GraphML model into Graphs model. Could not determine input file.",
                            npe);
        } catch (ClassCastException cce) {
            myStatus =
                    new Status(
                            IStatus.WARNING,
                            GraphsPlugin.PLUGIN_ID,
                            "Failed to transform GraphML model into Graphs model. Could not determine input file.");
        } finally {
            if (myStatus != null) {
                StatusManager.getManager().handle(myStatus, StatusManager.SHOW);
            }
        }

        // file.refreshLocal(1, null);

        monitor.done();
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench,
            final IStructuredSelection theselection) {
        selection = theselection;
    }
}
