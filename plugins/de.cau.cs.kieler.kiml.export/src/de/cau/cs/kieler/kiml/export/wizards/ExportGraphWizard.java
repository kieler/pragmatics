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
package de.cau.cs.kieler.kiml.export.wizards;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.export.ExportPlugin;
import de.cau.cs.kieler.kiml.export.handlers.GraphFileHandler;

/**
 * A wizard for exporting graphs from workspace.
 * 
 * @author wah
 * @kieler.ignore (excluded from review process)
 */
public class ExportGraphWizard extends Wizard implements IExportWizard {

    /** the page from which to select the workspace source files and the target folder. */
    private ExportGraphWorkspaceSourcesPage workspaceSourcesPage;
    /** the selection the import wizard was called on. */
    private IStructuredSelection selection;

    /**
     * Constructs a graph Export wizard.
     */
    public ExportGraphWizard() {
        super();
        
        setDialogSettings(ExportPlugin.getDefault().getDialogSettings());
        setWindowTitle(Messages.ExportGraphWizard_title);
        setNeedsProgressMonitor(true);
    }

    /**
     * {@inheritDoc}
     */
    public void addPages() {
        workspaceSourcesPage = new ExportGraphWorkspaceSourcesPage(selection);
        workspaceSourcesPage.restoreDialogSettings();
        addPage(workspaceSourcesPage);
    }

    /**
     * {@inheritDoc}
     */
    public IWizardPage getStartingPage() {
        // determine the starting page
        return workspaceSourcesPage;
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench, final IStructuredSelection select) {
        this.selection = select;
    }

    /**
     * {@inheritDoc}
     */
    public boolean performFinish() {

        if (!checkTargetDirectory()) {
            return false;
        }

        if (!exportSelectedGraphs()) {
            return false;
        }
        
        // Save dialog settings
        workspaceSourcesPage.saveDialogSettings();

        return true;
    }

    /**
     * Export the selected graphs. If the target file exists for a graph, then ask for replace,
     * ignore, or cancel.
     * 
     * @return true if the operation was finished, false if it was canceled by the user
     */
    private boolean exportSelectedGraphs() {
        // get the target format selected from the user
        final String targetFormat = workspaceSourcesPage.getTargetFormat();
        final IPath targetDirectory = workspaceSourcesPage.getTargetWorkspaceDirectory();
        final Maybe<Boolean> result = new Maybe<Boolean>(true);
        try {
            getContainer().run(true, true, new IRunnableWithProgress() {
                public void run(final IProgressMonitor monitor)
                    throws InvocationTargetException, InterruptedException {
                    List<IPath> files = workspaceSourcesPage.getSourceFiles(null);
                    monitor.beginTask("Export graphs", files.size());
                    
                    // for all selected files
                    for (IPath sourceFile : files) {

                        final GraphFileHandler graphFileHandler = new GraphFileHandler(sourceFile,
                                targetFormat, targetDirectory);

                        if (graphFileHandler.getAbsoluteTargetFile().exists()) {

                            // display a confirmation dialog
                            final Maybe<Integer> dialogSelection = new Maybe<Integer>();
                            ExportGraphWizard.this.getShell().getDisplay().syncExec(new Runnable() {
                                public void run() {
                                    String[] dialogButtonLabels = { "Ignore", "Replace", "Cancel" };
                                    MessageDialog msgd = new MessageDialog(
                                            ExportGraphWizard.this.getShell(),
                                            "Confirm", null,
                                            "A file named '"
                                            + graphFileHandler.getWorkspaceTargetFile().getName()
                                            + "' already exists in '"
                                            + targetDirectory
                                            + "'. Do you want to replace it?", MessageDialog.NONE,
                                            dialogButtonLabels, 0);
                                    dialogSelection.set(msgd.open());
                                }
                            });
                            
                            switch (dialogSelection.get()) {
                            case 2:// Cancel
                                result.set(false);
                                monitor.done();
                                return;
                            case 1:// Replace
                                exportGraph(graphFileHandler);
                                break;
                            }
                        } else {
                            exportGraph(graphFileHandler);
                        }
                        monitor.worked(1);
                    }
                    
                    monitor.done();
                }
            });
        } catch (Throwable exception) {
            if (exception instanceof InvocationTargetException) {
                exception = exception.getCause();
            }
            IStatus status = new Status(Status.ERROR, ExportPlugin.PLUGIN_ID,
                    "An error occurred while executing graph export.", exception);
            StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.BLOCK);
            result.set(false);
        }
        return result.get();
    }

    /**
     * Export the graph with the new format in the target directory.
     * 
     * @param sourceFile
     * @param targetFile
     * @param targetFormat
     */
    private void exportGraph(final GraphFileHandler graphFileHandler) {
        try {
            Writer writer = new FileWriter(graphFileHandler.getAbsoluteTargetFile());
            writer.write(graphFileHandler.graphToString());
            writer.close();
        } catch (Throwable exception) {
            exception.printStackTrace();
        }
    }

    /**
     * If target directory exists then return true. If not then ask to create it.
     * 
     * 
     * @return return true if target directory is ready, false otherwise
     */
    private boolean checkTargetDirectory() {
        IPath targetPath = ResourcesPlugin.getWorkspace().getRoot().getLocation()
                .append(workspaceSourcesPage.getTargetWorkspaceDirectory());
        if (new File(targetPath.toString()).exists()) {
            return true;
        } else {
            if (MessageDialog.openConfirm(null,
                    Messages.ExportGraphWizard_title_createTargetFolder, workspaceSourcesPage
                            .getTargetWorkspaceDirectory().toString()
                            + " "
                            + Messages.ExportGraphWizard_question_createTargetFolder)) {
                return createTargetDirectory();
            } else {
                return false;
            }

        }
    }

    /**
     * create the target directory if not exists.
     * 
     * @return true if success false otherwise
     */
    private boolean createTargetDirectory() {
        IPath targetPath = ResourcesPlugin.getWorkspace().getRoot().getLocation()
                .append(workspaceSourcesPage.getTargetWorkspaceDirectory());
        return new File(targetPath.toString()).mkdirs();
    }

}
