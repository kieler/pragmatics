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
import org.eclipse.core.runtime.NullProgressMonitor;
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

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.ErrorMessage;
import de.cau.cs.kieler.kex.controller.ExampleManager;
import de.cau.cs.kieler.kex.model.Example;

/**
 * This wizard contains all elements for an kex import wizard.
 * 
 * @author pkl
 * 
 */
public class ExampleImportWizard extends Wizard implements IImportWizard {

    private static final String ERROR_TITLE = "Could not complete Import";

    private ImportMainPage mainPage;
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
        } catch (KielerException e) {
            MessageDialog.openError(this.getShell(), "Can't initialize existing example pool.",
                    e.getLocalizedMessage());
        }
        mainPage = new ImportMainPage("Import Examples");
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
        List<String> directOpens = null;
        try {
            List<Example> checkedExamples = mainPage.getExamples();
            // TODO warning if more examples selected than 5.
            if (checkedExamples.isEmpty()) {
                throw new KielerException(ErrorMessage.NO_EXAMPLE_SELECTED);
            }
            // TODO check if projectPath is empty when deletes the import location.
            IPath destinationLocation = destinationPage.getResourcePath();
            if (destinationLocation == null || destinationLocation.isEmpty()) {
                throw new KielerException("No import location has been set.");
            }
            ExampleManager.get().generateProject(destinationLocation);
            directOpens = ExampleManager.get().importExamples(destinationLocation, checkedExamples,
                    checkDuplicate);
        } catch (KielerException e) {
            if (e.getLocalizedMessage().equals(ErrorMessage.DUPLICATE_EXAMPLE)) {
                checkDuplicate = !MessageDialog.openQuestion(getShell(), ERROR_TITLE,
                        e.getLocalizedMessage() + " Do you want to override it?");

            } else {
                MessageDialog.openError(getShell(), ERROR_TITLE, e.getLocalizedMessage());
            }

            return false;
        }

        // refresh workspace
        IContainer element = ResourcesPlugin.getWorkspace().getRoot();
        try {
            if (element != null) {
                element.refreshLocal(IContainer.DEPTH_INFINITE, new NullProgressMonitor());
            }
        } catch (CoreException e1) {
            // do nothing
            return true;
        }

        // open direct opens
        if (directOpens != null && destinationPage.openImports()) {
            IWorkbenchWindow win = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            IWorkbenchPage page = win.getActivePage();
            for (String path : directOpens) {
                IFile[] files = ResourcesPlugin.getWorkspace().getRoot()
                        .findFilesForLocationURI(URIUtil.toURI(path), IResource.FILE);
                if (files.length == 1) {
                    IEditorDescriptor defaultEditor = PlatformUI.getWorkbench().getEditorRegistry()
                            .getDefaultEditor(files[0].getName());
                    if (defaultEditor == null) {
                        defaultEditor = PlatformUI.getWorkbench().getEditorRegistry()
                                .findEditor(IEditorRegistry.SYSTEM_EXTERNAL_EDITOR_ID);
                    }
                    try {
                        page.openEditor(new FileEditorInput(files[0]), defaultEditor.getId());
                    } catch (PartInitException e) {
                        MessageDialog.openError(getShell(), "Opening Editor",
                                e.getLocalizedMessage());
                    }
                }
            }
        }
        return true;
    }
}
