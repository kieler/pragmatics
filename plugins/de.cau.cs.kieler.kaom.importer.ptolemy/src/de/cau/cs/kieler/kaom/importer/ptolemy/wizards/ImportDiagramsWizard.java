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

package de.cau.cs.kieler.kaom.importer.ptolemy.wizards;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kaom.importer.ptolemy.DiagramsImporter;
import de.cau.cs.kieler.kaom.importer.ptolemy.KaomImporterPtolemyPlugin;


/**
 * A wizard to import diagrams exported from Ptolemy2.
 * 
 * @author cds
 */
public class ImportDiagramsWizard extends Wizard implements IImportWizard {
    
    // WIZARD PAGES
    /**
     * The wizard page to set the target folder and options.
     */
    private ImportDiagramsOptionsPage optionsPage;
    
    /**
     * The wizard page to select the files to import from the file system.
     */
    private ImportDiagramsFileSystemSourcesPage fileSystemSourcesPage;
    
    /**
     * The wizard page to select the files to import from the workspace.
     */
    private ImportDiagramsWorkspaceSourcesPage workspaceSourcesPage;
    
    // MISCELLANEOUS
    /**
     * The selection the import wizard was called on.
     */
    private IStructuredSelection selection;
    
    
    /**
     * Constructs a Ptolemy2 diagram import wizard.
     */
    public ImportDiagramsWizard() {
        super();
        
        setWindowTitle("Import Ptolemy2 Diagrams");
        setDialogSettings(KaomImporterPtolemyPlugin.getDefault().getDialogSettings());
        
        // Required for the import process
        setNeedsProgressMonitor(true);
    }
    
    
    /**
     * Returns the options page. (the wizard's first page, as it is) This method is
     * used internally by the wizard pages.
     * 
     * @return the wizard's options page.
     */
    ImportDiagramsOptionsPage getOptionsPage() {
        return optionsPage;
    }
    
    /**
     * Returns the file system sources page. (one of the two second pages of the wizard)
     * This method is used internally by the wizard pages.
     * 
     * @return the wizard's file system source page.
     */
    ImportDiagramsFileSystemSourcesPage getFileSystemSourcesPage() {
        return fileSystemSourcesPage;
    }
    
    /**
     * Returns the workspace sources page. (one of the two second pages of the wizard)
     * This method is used internally by the wizard pages.
     * 
     * @return the wizard's workspace source page.
     */
    ImportDiagramsWorkspaceSourcesPage getWorkspaceSourcesPage() {
        return workspaceSourcesPage;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addPages() {
        optionsPage = new ImportDiagramsOptionsPage();
        fileSystemSourcesPage = new ImportDiagramsFileSystemSourcesPage(selection);
        workspaceSourcesPage = new ImportDiagramsWorkspaceSourcesPage(selection);
        
        this.addPage(optionsPage);
        this.addPage(fileSystemSourcesPage);
        this.addPage(workspaceSourcesPage);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canFinish() {
        // Check if the options page and the selected sources page is complete
        boolean complete = optionsPage.isPageComplete();
        
        if (optionsPage.isFileSystemSource()) {
            complete &= fileSystemSourcesPage.isPageComplete();
        } else {
            complete &= workspaceSourcesPage.isPageComplete();
        }
        
        return complete;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performFinish() {
        // Save dialog settings
        optionsPage.saveDialogSettings();
        fileSystemSourcesPage.saveDialogSettings();
        
        // Retrieve the user's settings
        boolean importFromFileSystem = optionsPage.isFileSystemSource();
        boolean initializeDiagramFiles = optionsPage.isInitializeDiagramFiles();
        boolean overwriteWithoutWarning = optionsPage.isOverwriteWithoutWarning();
        List<File> sourceFiles = null;
        final Maybe<List<File>> sourceFilesWrapper = new Maybe<List<File>>();
        IPath targetContainerPath = null;
        
        if (importFromFileSystem) {
            // Finding the source files is a potentially long-running operation that requires
            // a progress monitor
            try {
                this.getContainer().run(true, true, new IRunnableWithProgress() {
                    public void run(final IProgressMonitor monitor)
                        throws InvocationTargetException, InterruptedException {
                        
                        List<File> files = fileSystemSourcesPage.getFiles(monitor);
                        sourceFilesWrapper.set(files);
                    }
                });
            } catch (Exception e) {
                IStatus status = new Status(
                            IStatus.ERROR,
                            KaomImporterPtolemyPlugin.PLUGIN_ID,
                            "Error getting the list of files to import.",
                            e);
                StatusManager.getManager().handle(status, StatusManager.BLOCK);
                return false;
            }
            
            sourceFiles = sourceFilesWrapper.get();
            targetContainerPath = fileSystemSourcesPage.getTargetContainerPath();
        } else {
            // Finding the source files is a potentially long-running operation that requires
            // a progress monitor
            try {
                this.getContainer().run(true, true, new IRunnableWithProgress() {
                    public void run(final IProgressMonitor monitor)
                        throws InvocationTargetException, InterruptedException {
                        
                        List<File> files = workspaceSourcesPage.getSourceFiles(monitor);
                        sourceFilesWrapper.set(files);
                    }
                });
            } catch (Exception e) {
                IStatus status = new Status(
                            IStatus.ERROR,
                            KaomImporterPtolemyPlugin.PLUGIN_ID,
                            "Error getting the list of files to import.",
                            e);
                StatusManager.getManager().handle(status, StatusManager.BLOCK);
                return false;
            }
            
            sourceFiles = sourceFilesWrapper.get();
            targetContainerPath = workspaceSourcesPage.getTargetContainerPath();
        }
        
        // Create the importer and let it do its work
        DiagramsImporter importer = new DiagramsImporter(this, sourceFiles, targetContainerPath,
                initializeDiagramFiles, overwriteWithoutWarning);
        try {
            this.getContainer().run(true, true, importer);
        } catch (Exception e) {
            IStatus status = new Status(
                        IStatus.ERROR,
                        KaomImporterPtolemyPlugin.PLUGIN_ID,
                        "Error importing the models.",
                        e);
            StatusManager.getManager().handle(status, StatusManager.BLOCK);
            return false;
        }
        
        // The import wasn't cancelled, so check if it was otherwise successful
        if (importer.isImportSuccessful()) {
            // Everything went fine
            return true;
        } else if (importer.isImportCanceled()) {
            // No exception, but don't close the wizard
            return false;
        } else {
            // Show the user what went wrong
            StatusManager.getManager().handle(
                    importer.getStatus(), StatusManager.BLOCK);
            return false;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench, final IStructuredSelection theSelection) {
        selection = theSelection;
    }
}
