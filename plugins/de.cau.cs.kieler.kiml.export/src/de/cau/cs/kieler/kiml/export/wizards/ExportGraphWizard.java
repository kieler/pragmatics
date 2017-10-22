/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.export.wizards;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.elk.core.util.Maybe;
import org.eclipse.elk.core.util.WrappedException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.kiml.export.ExportPlugin;
import de.cau.cs.kieler.kiml.export.GraphFileHandler;
import de.cau.cs.kieler.formats.GraphFormatData;

/**
 * A wizard for exporting graphs from workspace.
 * 
 * @author wah
 * @author msp
 * @kieler.ignore (excluded from review process)
 */
public class ExportGraphWizard extends Wizard implements IExportWizard {

    /** the page from which to select the workspace source files and the target folder. */
    private ExportGraphWorkspaceSourcesPage workspaceSourcesPage;
    /** the selection the import wizard was called on. */
    private IStructuredSelection selection;
    /** the user may choose to overwrite all files that already exist during export. */
    private Boolean overwriteWithoutAsking = null;

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

        // Export the selected graphs
        exportSelectedGraphs();
        
        // Save dialog settings
        workspaceSourcesPage.saveDialogSettings();

        return true;
    }

    /**
     * Export the selected graphs. If the target file exists for a graph, then ask for replace,
     * ignore, or cancel.
     */
    private void exportSelectedGraphs() {
        // get the target format selected from the user
        final GraphFormatData targetFormat = workspaceSourcesPage.getTargetFormat();
        final IPath targetDirectory = workspaceSourcesPage.getTargetWorkspaceDirectory();
        final boolean exportHierachyLevels = workspaceSourcesPage.getSeparateHierarchyLevels();
        final boolean filterEdgelessLevels = workspaceSourcesPage.getFilterEdgelessLevels();
        final boolean filterSelfLoops = workspaceSourcesPage.getFilterSelfLoops();
        
        try {
            getContainer().run(true, true, new IRunnableWithProgress() {
                public void run(final IProgressMonitor monitor)
                    throws InvocationTargetException, InterruptedException {
                    
                    List<IFile> files = workspaceSourcesPage.getSourceFiles(null);
                    monitor.beginTask("Export graphs", files.size());
                    
                    // be sure that the user can make decisions on whether to overwrite fiels
                    overwriteWithoutAsking = null;
                    
                    // for all selected files
                    for (IFile sourceFile : files) {
                        final GraphFileHandler graphFileHandler = new GraphFileHandler(sourceFile,
                                targetFormat, targetDirectory);
                        
                        // export the graph as a whole or each hierarchy level separately?
                        if (exportHierachyLevels) {
                            String[] graphStrings = graphFileHandler.hierarchyGraphsToStrings(
                                    filterEdgelessLevels, filterSelfLoops);
                            exportGraphs(graphFileHandler, graphStrings);
                        } else {
                            exportGraph(graphFileHandler, graphFileHandler.graphToString());
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
        }
    }

    /**
     * Export the graph with the new format in the target directory.
     */
    private void exportGraph(final GraphFileHandler graphFileHandler, final String graph) {
        IFile targetFile = cobbleTargetFileTogether(graphFileHandler, "");
        doExportSingleGraphThingy(graph, targetFile);
    }
    
    /**
     * Export the graphs with the new format in the target directory.
     */
    private void exportGraphs(final GraphFileHandler graphFileHandler, final String[] graphs) {
        // Find out how many characters we need for numbering the files
        int appendixLength = Integer.toString(graphs.length).length();
        
        // Save each graph
        for (int i = 0; i < graphs.length; i++) {
            IFile targetFile = cobbleTargetFileTogether(graphFileHandler,
                    String.format("_%0" + appendixLength + "d", i));
            doExportSingleGraphThingy(graphs[i], targetFile);
        }
    }
    
    /**
     * Exports a single graph to a single file. If the file exists, the user may be asked whether
     * it should be overwritten.
     */
    private void doExportSingleGraphThingy(final String graph, final IFile targetFile) {
        try {
            if (targetFile.exists()) {
                if (shouldReplaceFile(targetFile)) {
                    targetFile.setContents(
                            new ByteArrayInputStream(graph.getBytes()), IFile.FORCE, null);
                }
            } else {
                targetFile.create(new ByteArrayInputStream(graph.getBytes()), IFile.FORCE, null);
            }
        } catch (Throwable exception) {
            throw new WrappedException(exception);
        }
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // File System Stuff

    /**
     * If target directory exists then return true. If not then ask to create it.
     * 
     * @return return true if target directory is ready, false otherwise
     */
    private boolean checkTargetDirectory() {
        IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
        IPath path = workspaceSourcesPage.getTargetWorkspaceDirectory();
        IContainer container;
        int segmentCount = path.segmentCount();
        if (segmentCount == 0) {
            return false;
        } else if (segmentCount == 1) {
            container = workspaceRoot.getProject(path.lastSegment());
        } else {
            container = workspaceRoot.getFolder(path);
        }
        if (container.exists()) {
            return true;
        } else {
            if (MessageDialog.openConfirm(null,
                    Messages.ExportGraphWizard_title_createTargetFolder, workspaceSourcesPage
                            .getTargetWorkspaceDirectory().toString()
                            + " "
                            + Messages.ExportGraphWizard_question_createTargetFolder)) {
                try {
                    LinkedList<IContainer> containers = new LinkedList<IContainer>();
                    // gather all containers that do not exist
                    do {
                        containers.addFirst(container);
                        container = container.getParent();
                    } while (!container.exists());
                    // create each container starting with the lowest
                    for (IContainer c : containers) {
                        if (c instanceof IFolder) {
                            ((IFolder) c).create(true, false, null);
                        } else if (c instanceof IProject) {
                            ((IProject) c).create(null, null);
                        }
                    }
                } catch (CoreException exception) {
                    StatusManager.getManager().handle(exception.getStatus(),
                            StatusManager.LOG | StatusManager.BLOCK);
                }
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Builds the target file based on the source file name. The target file will be named the same
     * as the source file, plus a (possibly empty) appendix, plus the target extension.
     */
    private IFile cobbleTargetFileTogether(final GraphFileHandler handler, final String appendix) {
        String sourceFileName = handler.getSourceFile().getFullPath().toFile().getName();
        String extension = handler.getTargetFormat().getExtensions()[0];
        
        // get the last dot position
        int dotPos = sourceFileName.lastIndexOf(".");
        if (dotPos < 0) {
            dotPos = sourceFileName.length();
        }
        
        // replace the file extension with the new one
        IPath targetPath = handler.getWorkspaceTargetDirectory().append(
                sourceFileName.substring(0, dotPos) + appendix + "." + extension);
        return ResourcesPlugin.getWorkspace().getRoot().getFile(targetPath);
    }
    
    /**
     * Checks whether the given file (which already exists) should be overwritten or not. If the
     * user hasn't made a permanent decision yet, a message dialog gets thrown onto the screen.
     */
    private boolean shouldReplaceFile(final IFile file) {
        // If the user has already made a permanent choice, use that
        if (this.overwriteWithoutAsking != null) {
            return this.overwriteWithoutAsking.booleanValue();
        }
        
        // display a confirmation dialog
        final Maybe<Integer> dialogSelection = new Maybe<Integer>();
        ExportGraphWizard.this.getShell().getDisplay().syncExec(new Runnable() {
            public void run() {
                String[] dialogButtonLabels = {
                        "Keep", "Keep All", "Replace", "Replace All" };
                MessageDialog msgd = new MessageDialog(
                        ExportGraphWizard.this.getShell(),
                        "Confirm", null,
                        "A file named '"
                        + file.getName()
                        + "' already exists. Do you want to replace it?",
                        MessageDialog.NONE,
                        dialogButtonLabels, 0);
                dialogSelection.set(msgd.open());
            }
        });
        
        boolean overwrite = false;
        // CHECKSTYLEOFF MagicNumber
        switch (dialogSelection.get()) {
        case 1: // Keep all
            this.overwriteWithoutAsking = Boolean.FALSE;
            break;

        case 2: // Replace
            overwrite = true;
            break;
        
        case 3: // Replace all
            overwrite = true;
            this.overwriteWithoutAsking = Boolean.TRUE;
            break;
        }
        
        return overwrite;
    }

}
