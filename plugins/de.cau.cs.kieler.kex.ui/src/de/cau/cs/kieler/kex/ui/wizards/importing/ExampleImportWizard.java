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
 */
package de.cau.cs.kieler.kex.ui.wizards.importing;

import java.util.List;

import org.eclipse.core.filesystem.URIUtil;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.kex.controller.ErrorMessage;
import de.cau.cs.kieler.kex.controller.ExampleManager;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.ui.KEXUIPlugin;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;

/**
 * This wizard contains all elements for an kex import wizard.
 * 
 * @author pkl
 * 
 */
public class ExampleImportWizard extends Wizard implements IImportWizard {

    private static final int WARNING_BORDER = 5;

    private static final String ERROR_TITLE = "Could not complete Import";

    private ImportExamplePage mainPage;
    private ImportDestPage destinationPage;
    private boolean checkDuplicate;

    /**
     * Constructor for {@link ExampleImportWizard}.
     */
    public ExampleImportWizard() {
        super();
    }

    /**
     * initializes the Wizard and adds the mainpage of Type {@link WizardPage} to it.
     * 
     * @param workbench
     *            , {@link IWorkbench}
     * @param selection
     *            , {@link IStructuredSelection}
     */
    public void init(final IWorkbench workbench, final IStructuredSelection selection) {
        setWindowTitle("KIELER Examples Import");
        setNeedsProgressMonitor(true);

        this.checkDuplicate = false;
        try {
            ExampleManager.get().load(true);
        } catch (RuntimeException e) {
            IStatus status = new Status(IStatus.ERROR, KEXUIPlugin.PLUGIN_ID,
                    Messages.getString("loadError"), e);
            StatusManager.getManager().handle(status, StatusManager.SHOW);
        }
        mainPage = new ImportExamplePage("Import Examples", selection);
        destinationPage = new ImportDestPage("Location", selection);
    }

    @Override
    public final void addPages() {
        super.addPages();
        addPage(mainPage);
        addPage(destinationPage);
    }

    @Override
    public final boolean performFinish() {
        try {
            List<Example> checkedExamples = mainPage.getCheckedExamples();

            if (checkedExamples.isEmpty()) {
                // FIXME throw a more specific exception
                throw new RuntimeException(ErrorMessage.NO_EXAMPLE_SELECTED);
            }

            // warning if more examples selected than warning_border.
            if (checkedExamples.size() > WARNING_BORDER
                    && !MessageDialog.openQuestion(this.getShell(),
                            "More than 5 examples selected",
                            "Importing may take a while. Do you really want to continue?")) {
                return false;
            }

            IPath destinationLocation = destinationPage.getResourcePath();
            if (destinationLocation == null || destinationLocation.isEmpty()) {
                // FIXME throw a more specific exception
                throw new RuntimeException("No import location has been set.");
            }
            ExampleManager.get().generateProject(destinationLocation);
            final List<String> directOpens = ExampleManager.get().importExamples(
                    destinationLocation, checkedExamples, checkDuplicate);

            this.getShell().setVisible(false);
            // refresh workspace
            IContainer element = ResourcesPlugin.getWorkspace().getRoot();
            try {
                if (element != null) {
                    element.refreshLocal(IContainer.DEPTH_INFINITE, new NullProgressMonitor());
                }
            } catch (CoreException e1) {
                // do nothing
            }
            // open direct opens
            if (directOpens != null && destinationPage.openImports()) {
                IWorkbenchWindow win = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                IWorkbenchPage page = win.getActivePage();
                for (String path : directOpens) {
                    IFile[] files = ResourcesPlugin.getWorkspace().getRoot()
                            .findFilesForLocationURI(URIUtil.toURI(path), IResource.FILE);
                    if (files.length == 1) {
                        IEditorDescriptor defaultEditor = PlatformUI.getWorkbench()
                                .getEditorRegistry().getDefaultEditor(files[0].getName());
                        if (defaultEditor == null) {
                            defaultEditor = PlatformUI.getWorkbench().getEditorRegistry()
                                    .findEditor(IEditorRegistry.SYSTEM_EXTERNAL_EDITOR_ID);
                        }
                        try {
                            page.openEditor(new FileEditorInput(files[0]), defaultEditor.getId());
                            if (destinationPage.autoLayout()) {
                                DiagramLayoutEngine.INSTANCE.layout(PlatformUI.getWorkbench()
                                        .getActiveWorkbenchWindow().getPartService()
                                        .getActivePart(), null, false, true, false, true);
                            }
                        } catch (PartInitException e) {
                            IStatus status = new Status(IStatus.WARNING, KEXUIPlugin.PLUGIN_ID,
                                    "Could not open editor.", e);
                            StatusManager.getManager().handle(status, StatusManager.SHOW);
                            continue;
                        }
                    }
                }
            }
        } catch (RuntimeException e) {
            if (e.getLocalizedMessage().equals(ErrorMessage.DUPLICATE_EXAMPLE)) {
                checkDuplicate = !MessageDialog.openQuestion(getShell(), ERROR_TITLE,
                        e.getLocalizedMessage() + " Do you want to override it?");
            } else if (e instanceof UnsupportedOperationException) {
                IStatus status = new Status(IStatus.WARNING, KEXUIPlugin.PLUGIN_ID, ERROR_TITLE, e);
                StatusManager.getManager().handle(status, StatusManager.SHOW);
                return true;
            } else {
                IStatus status = new Status(IStatus.WARNING, KEXUIPlugin.PLUGIN_ID, ERROR_TITLE, e);
                StatusManager.getManager().handle(status, StatusManager.SHOW);
            }
            this.getShell().setVisible(true);
            return false;
        }
        return true;
    }
}
