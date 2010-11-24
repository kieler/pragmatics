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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.statushandlers.StatusManager;
import org.ptolemy.moml.MomlPackage;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.annotations.AnnotationsPackage;
import de.cau.cs.kieler.core.model.util.XtendTransformationUtil;
import de.cau.cs.kieler.kaom.KaomPackage;
import de.cau.cs.kieler.kaom.importer.ptolemy.KaomImporterPtolemyPlugin;

/**
 * A wizard to import diagrams exported from Ptolemy2.
 * 
 * @author cds
 */
public class ImportDiagramWizard extends Wizard implements IImportWizard {
    
    // LOCALIZATION
    private static final String LOC_WIZARD_TITLE = "Import Ptolemy2 Diagram";
    private static final String LOC_TASK = "Importing diagram...";
    
    // WIZARD PAGES
    /** The wizard page to select the source file. */
    private ImportDiagramSourceFilePage sourceFilePage;
    /** The wizard page to select the target file. */
    private ImportDiagramTargetFilePage targetFilePage;
    
    // MISCELLANEOUS
    /** The selection the import wizard was called on. */
    private IStructuredSelection selection;
    /**
     * Whether the last import was successful. This is a variable because the actual
     * import is run inside a job that has no way of returning a value.
     */
    private boolean success;
    
    
    /**
     * Constructs a Ptolemy2 diagram import wizard.
     */
    public ImportDiagramWizard() {
        super();
        setWindowTitle(LOC_WIZARD_TITLE);
        setDialogSettings(KaomImporterPtolemyPlugin.getDefault().getDialogSettings());
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addPages() {
        sourceFilePage = new ImportDiagramSourceFilePage(selection);
        targetFilePage = new ImportDiagramTargetFilePage(selection);
        
        // Target file page
        this.addPage(sourceFilePage);
        this.addPage(targetFilePage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performFinish() {

        // Save the wizard's current settings and reset success flag
        sourceFilePage.storeSettings();
        success = false;
        
        // Get the source file
        boolean externalFile = sourceFilePage.isExternalFileSelected();
        final URI sourceFileURI;
        
        if (externalFile) {
            File file = sourceFilePage.getExternalFile();
            if (file != null) {
                sourceFileURI = URI.createFileURI(file.getPath());
            } else {
                sourceFileURI = null;
            }
        } else {
            IFile file = sourceFilePage.getWorkspaceFile();
            if (file != null) {
                sourceFileURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
            } else {
                sourceFileURI = null;
            }
        }
        
        if (sourceFileURI == null) {
            Status status = new Status(
                    IStatus.ERROR,
                    KaomImporterPtolemyPlugin.PLUGIN_ID,
                    "The source file could not be found.");
            StatusManager.getManager().handle(status, StatusManager.SHOW);
            success = false;
            return success;
        }
        
        // Get the target file
        String targetFileContainer = targetFilePage.getContainerFullPath().toString();
        String targetFileName = targetFilePage.getFileName();
        final URI targetFileURI;
        
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IResource resource = root.findMember(new Path(targetFileContainer));
        if (!resource.exists() || !(resource instanceof IContainer)) {
            Status status = new Status(
                    IStatus.ERROR,
                    KaomImporterPtolemyPlugin.PLUGIN_ID,
                    "The target file could not be created.");
            StatusManager.getManager().handle(status, StatusManager.SHOW);
            success = false;
            return success;
        }
        IContainer container = (IContainer) resource;
        targetFileURI = URI.createPlatformResourceURI(
                container.getFile(new Path(targetFileName)).getFullPath().toString(), true);
        
        // The job that will run the import task
        IRunnableWithProgress job = new IRunnableWithProgress() {
            public void run(final IProgressMonitor monitor) throws InvocationTargetException,
                    InterruptedException {
                
                try {
                    doFinish(sourceFileURI, targetFileURI, monitor);
                } catch (KielerException e) {
                    throw new InvocationTargetException(e);
                }
            }
        };
        
        // Run the job!
        try {
            getContainer().run(true, false, job);
            success = true;
        } catch (InterruptedException e) {
            success = false;
        } catch (InvocationTargetException e) {
            Throwable realException = e.getTargetException();
            Status status = new Status(
                    IStatus.ERROR,
                    KaomImporterPtolemyPlugin.PLUGIN_ID,
                    0,
                    "Ptolemy2 diagram import failed.",
                    realException);
            StatusManager.getManager().handle(status, StatusManager.SHOW);
            success = false;
        }

        return success;
    }
    
    /**
     * Imports the given Ptolemy2 diagram into the given target file.
     * 
     * @param sourceFile the source file.
     * @param targetFile the target file.
     * @param monitor the progress monitor.
     * @throws KielerException if anything goes wrong during the transformation.
     */
    private void doFinish(final URI sourceFile, final URI targetFile,
            final IProgressMonitor monitor) throws KielerException {
        
        monitor.beginTask(LOC_TASK, IProgressMonitor.UNKNOWN);
        
        // Setup the transformation
        final String transformation = "models/ptolemy2kaom";
        final String fun = "transform";

        EPackage p1 = MomlPackage.eINSTANCE;
        EPackage p2 = KaomPackage.eINSTANCE;
        EPackage p3 = AnnotationsPackage.eINSTANCE;
        
        // Do my bidding, little ones!
        XtendTransformationUtil
            .model2ModelTransform(transformation, fun, sourceFile, targetFile, p1, p2, p3);
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench, final IStructuredSelection theSelection) {
        this.selection = theSelection;
    }
}
