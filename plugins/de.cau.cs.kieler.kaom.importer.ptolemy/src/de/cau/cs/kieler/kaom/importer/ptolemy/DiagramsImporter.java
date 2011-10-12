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

package de.cau.cs.kieler.kaom.importer.ptolemy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.statushandlers.StatusManager;
import org.ptolemy.moml.MomlPackage;

import de.cau.cs.kieler.core.annotations.AnnotationsPackage;
import de.cau.cs.kieler.core.model.xtend.util.XtendStatus;
import de.cau.cs.kieler.core.model.xtend.util.XtendTransformationUtil;
import de.cau.cs.kieler.core.ui.KielerProgressMonitor;
import de.cau.cs.kieler.kaom.KaomPackage;
import de.cau.cs.kieler.kaom.diagram.custom.commands.InitKaomDiagramHandler;
import de.cau.cs.kieler.kaom.importer.ptolemy.utils.Utils;
import de.cau.cs.kieler.kaom.importer.ptolemy.wizards.ImportDiagramsWizard;


/**
 * An importer for Ptolemy2 diagrams. Usually invoked by the import wizard. Designed to
 * only run once and be destroyed.
 * 
 * @author cds
 * @kieler.rating yellow 2010-03-14
 *      reviewed by haf, msp, pkl
 */
public class DiagramsImporter implements IRunnableWithProgress {
    
    /**
     * Size of the file copy buffer in bytes.
     */
    private static final int FILE_BUFFER_SIZE = 1024;
    
    /**
     * The wizard that uses this importer.
     */
    private ImportDiagramsWizard wizard;
    
    /**
     * The list of Ptolemy2 diagram files to import.
     */
    private List<File> sourceFiles;
    
    /**
     * Where to import the diagrams to.
     */
    private IPath targetContainerPath;
    
    /**
     * Where to import the diagrams to.
     */
    private IContainer targetContainer;
    
    /**
     * Whether for the imported diagrams KAOD diagram files should be created.
     */
    private boolean initializeDiagramFiles;
    
    /**
     * Whether existing files should be overwritten without warning.
     */
    private boolean overwriteWithoutWarning;
    
    /**
     * Indicates whether the user canceled the import.
     */
    private boolean wasImportCanceled = false;
    
    /**
     * The maximum severity that an Xtend status had during the import.
     */
    private int maxSeverity = IStatus.OK;
    
    /**
     * List of exceptions thrown during the import process, wrapped in {@code IStatus}
     * objects.
     */
    private List<IStatus> statuses = new ArrayList<IStatus>();
    
    
    /**
     * Constructs a new instance with the given configuration.
     * 
     * @param wizard the wizard using this importer.
     * @param sourceFiles the list of source files to import.
     * @param targetContainerPath the possibly non-existent container to import them to.
     * @param initializeDiagramFiles whether to initialize KAOD diagram files.
     * @param overwriteWithoutWarning whether existing files should be overwritten without
     *                                 warning.
     */
    public DiagramsImporter(final ImportDiagramsWizard wizard, final List<File> sourceFiles,
            final IPath targetContainerPath, final boolean initializeDiagramFiles,
            final boolean overwriteWithoutWarning) {
        
        this.wizard = wizard;
        this.sourceFiles = sourceFiles;
        this.targetContainerPath = targetContainerPath;
        this.initializeDiagramFiles = initializeDiagramFiles;
        this.overwriteWithoutWarning = overwriteWithoutWarning;
    }
    
    
    /**
     * Checks whether or not the import was successful. The import was successful if
     * it completed and no exceptions occurred. If the import was not successful, use
     * {@link isImportCanceled()} and {@link getException()} to find out whether the
     * user canceled the operation or an exception occurred, respectively.
     * 
     * @return {@code true} if an uninterrupted import was successful.
     */
    public boolean isImportSuccessful() {
        return maxSeverity < IStatus.ERROR && !wasImportCanceled;
    }
    
    /**
     * Checks whether the user canceled the import or not.
     * 
     * @return {@code true} if the user canceled the import.
     */
    public boolean isImportCanceled() {
        return wasImportCanceled;
    }
    
    /**
     * Returns an {@code IStatus} object describing the outcome of the import. This
     * may be either a single status object if everything went fine, or a multi status
     * that contains a list of error statusses.
     * 
     * @return the status.
     */
    public IStatus getStatus() {
        if (wasImportCanceled) {
            return new Status(
                    IStatus.CANCEL,
                    KaomImporterPtolemyPlugin.PLUGIN_ID,
                    Messages.DiagramsImporter_status_importCanceled);
        } else if (statuses.size() >= 1) {
            return new MultiStatus(
                    KaomImporterPtolemyPlugin.PLUGIN_ID,
                    maxSeverity,
                    statuses.toArray(new IStatus[statuses.size()]),
                    Messages.DiagramsImporter_status_exceptions,
                    null);
        } else {
            return new Status(
                    IStatus.OK,
                    KaomImporterPtolemyPlugin.PLUGIN_ID,
                    Messages.DiagramsImporter_status_ok);
        }
    }
    
    
    /**
     * {@inheritDoc}
     */
    public void run(final IProgressMonitor monitor) throws InvocationTargetException,
            InterruptedException {
        
        // If the progress monitor was omitted, use the null monitor
        IProgressMonitor progressMonitor = monitor;
        if (progressMonitor == null) {
            progressMonitor = new NullProgressMonitor();
        }
        
        // Calculate how much work there is to be done (also counting the subtask of having
        // to create the container)
        int workPerSourceFile = initializeDiagramFiles ? 2 : 1;
        int totalWork = sourceFiles.size() * workPerSourceFile + 1;
        
        // Begin the main task
        progressMonitor.beginTask(Messages.DiagramsImporter_task_importingDiagrams, totalWork);
        
        /* The main task is splitted into two subtasks:
         *  1. Ensuring the existence of the target container.
         *  2. Importing all the source files.
         */
        
        // Subtask 1
        try {
            targetContainer = getTargetContainer(new SubProgressMonitor(progressMonitor, 1));
        } catch (CoreException e) {
            StatusManager.getManager().handle(e.getStatus(), StatusManager.LOG);
            statuses.add(e.getStatus());
            progressMonitor.done();
            return;
        }
        
        // Subtask 2
        importFiles(new SubProgressMonitor(progressMonitor, totalWork - 1));
        
        // Everything's done. Yay!
        progressMonitor.done();
    }
    
    /**
     * Returns the target container, which is then guaranteed to exist.
     * 
     * @param monitor monitor to report progress to.
     * @return the target container.
     * @throws CoreException if something goes wrong creating the target container.
     */
    private IContainer getTargetContainer(final IProgressMonitor monitor) throws CoreException {
        monitor.beginTask(Messages.DiagramsImporter_task_ensuringTargetContainerExistence, 1);
        
        // Declare some helpful variables
        IContainer currContainer;
        IFolder currFolder;
        String[] pathSegments = targetContainerPath.segments();
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot workspaceRoot = workspace.getRoot();
        
        // Retrieve the project
        currContainer = workspaceRoot.getProject(pathSegments[0]);
        if (!currContainer.exists()) {
            throw new CoreException(new Status(
                    IStatus.ERROR,
                    KaomImporterPtolemyPlugin.PLUGIN_ID,
                    Messages.DiagramsImporter_exception_projectNotExistant));
        }
        
        // Iterate through the path segments, creating the folder structure along the way
        for (int i = 1; i < pathSegments.length; i++) {
            // Retrieve the new container
            currFolder = currContainer.getFolder(new Path(pathSegments[i]));
            if (!currFolder.exists()) {
                currFolder.create(true, true, null);
            }
            
            currContainer = currFolder;
        }
        
        // We're done
        monitor.worked(1);
        monitor.done();
        
        return currContainer;
    }
    
    /**
     * Loops through the list of source files and calls appropriate methods to import them.
     * Any exceptions thrown in the process are added to the list of statusses.
     * 
     * @param monitor monitor to report progress to.
     */
    private void importFiles(final IProgressMonitor monitor) {
        // Calculate the total work to be done
        int totalWork = sourceFiles.size();
        
        // Begin the main task
        monitor.beginTask(Messages.DiagramsImporter_task_importingModels, totalWork);
        
        // Iterate through the array of source files
        for (File sourceFile : sourceFiles) {
            monitor.subTask(sourceFile.getName());
            
            // Check if the operation should be cancelled
            if (monitor.isCanceled()) {
                this.wasImportCanceled = true;
                monitor.done();
                return;
            }
            
            // Get the base name for the imported files (this takes care of files with
            // the same name)
            String targetFilesBaseName = getBaseFileName(sourceFile.getName());
            if (targetFilesBaseName == null) {
                this.wasImportCanceled = true;
                monitor.done();
                return;
            }
            
            // Import
            try {
                doImportModelFile(
                        sourceFile,
                        targetFilesBaseName + "." //$NON-NLS-1$
                            + PtolemyImporterConstants.TARGET_MODEL_FILE_EXTENSION);
            } catch (CoreException e) {
                StatusManager.getManager().handle(e.getStatus(), StatusManager.LOG);
                statuses.add(e.getStatus());
            }
            
            monitor.worked(1);
            
            try {
                // Possibly initialize diagram file
                if (initializeDiagramFiles) {
                    doImportDiagramFile(
                            targetFilesBaseName + "." //$NON-NLS-1$
                                + PtolemyImporterConstants.TARGET_MODEL_FILE_EXTENSION,
                            targetFilesBaseName + "." //$NON-NLS-1$
                                + PtolemyImporterConstants.TARGET_DIAGRAM_FILE_EXTENSION);
                }
            } catch (CoreException e) {
                StatusManager.getManager().handle(e.getStatus(), StatusManager.LOG);
                statuses.add(e.getStatus());
            }
            
            monitor.worked(1);
        }
        
        // Import done
        monitor.done();
    }
    
    /**
     * Checks if the target container contains files with the names of the files to be
     * imported. If so, the user is asked for a new name.
     * 
     * @param sourceFileName the source file name.
     * @return the new base file name, or {@code null} if the user decided to cancel the
     *         operation.
     */
    private String getBaseFileName(final String sourceFileName) {
        // Get the file's base name
        String sourceFileExtension = Utils.getFileExtension(sourceFileName);
        String sourceFileBaseName = sourceFileName.substring(
                0, sourceFileName.length() - sourceFileExtension.length() - 1);
        
        // If files are to be overwritten without warning anyway, don't bother with this
        if (overwriteWithoutWarning) {
            return sourceFileBaseName;
        }
        
        // Check if there exists a model or diagram file with the same base name
        boolean modelFileNameClash = false;
        boolean diagramFileNameClash = false;
        
        IFile file = targetContainer.getFile(new Path(
                sourceFileBaseName + "." //$NON-NLS-1$
                    + PtolemyImporterConstants.TARGET_MODEL_FILE_EXTENSION));
        if (file.exists()) {
            modelFileNameClash = true;
        }
        
        if (initializeDiagramFiles) {
            file = targetContainer.getFile(new Path(
                    sourceFileBaseName + "." //$NON-NLS-1$
                        + PtolemyImporterConstants.TARGET_DIAGRAM_FILE_EXTENSION));
            if (file.exists() && initializeDiagramFiles) {
                diagramFileNameClash = true;
            }
        }
        
        // If we have a name clash, ask the user for a new name. Politely.
        if (modelFileNameClash || diagramFileNameClash) {
            StringBuilder sb = new StringBuilder();
            sb.append(Messages.DiagramsImporter_message_filesExist_prefix);
            
            if (modelFileNameClash) {
                sb.append("    " //$NON-NLS-1$
                        + sourceFileBaseName
                        + "." //$NON-NLS-1$
                        + PtolemyImporterConstants.TARGET_MODEL_FILE_EXTENSION
                        + "\n"); //$NON-NLS-1$
            }
            
            if (diagramFileNameClash) {
                sb.append("    " //$NON-NLS-1$
                        + sourceFileBaseName
                        + "." //$NON-NLS-1$
                        + PtolemyImporterConstants.TARGET_DIAGRAM_FILE_EXTENSION
                        + "\n"); //$NON-NLS-1$
            }
            
            sb.append("\n"); //$NON-NLS-1$
            sb.append(Messages.DiagramsImporter_message_filesExist_suffix);
            
            // Popup a dialog
            final InputDialog dialog = new InputDialog(
                    wizard.getShell(),
                    Messages.DiagramsImporter_title_filesExist,
                    sb.toString(),
                    sourceFileBaseName,
                    null);
            
            wizard.getShell().getDisplay().syncExec(new Runnable() {
                public void run() {
                    dialog.open();
                }
            });
            
            if (dialog.getReturnCode() == Dialog.CANCEL) {
                // The user wants to cancel the import
                return null;
            } else {
                // We have a new name. Check if it clashes
                return getBaseFileName(
                        dialog.getValue() + "." //$NON-NLS-1$
                            + PtolemyImporterConstants.TARGET_MODEL_FILE_EXTENSION);
            }
        } else {
            return sourceFileBaseName;
        }
    }
    
    /**
     * Returns a version of the source file with moml file extension.
     * 
     * <p>This is necessary because the import can only cope with moml files, not with the
     * more common xml files. To work around this, xml files are copied to the temp
     * folder, using a file name with a moml extension.</p>
     *  
     * @param sourceFile the source file to possibly be copied to a temporary moml file.
     * @return moml file to be imported.
     * @throws IOException if the creation of the temporary file failed.
     */
    private File getTemporarySourceFile(final File sourceFile) throws IOException {
        // Check if the source file is already a moml file
        if (Utils.getFileExtension(sourceFile.getName()).toLowerCase().equals(
                PtolemyImporterConstants.PTOLEMY_INTERNAL_FILE_EXTENSION)) {
            
            return sourceFile;
        }
        
        // Get the file's base name
        String baseName = Utils.getFileBaseName(sourceFile.getName());
        
        // Create a temporary file that automatically gets deleted when the VM ends
        File realSourceFile = File.createTempFile(baseName,
                "." + PtolemyImporterConstants.PTOLEMY_INTERNAL_FILE_EXTENSION); //$NON-NLS-1$
        realSourceFile.deleteOnExit();
        
        // Copy the source file's content to the new file
        InputStream iStream = new FileInputStream(sourceFile);
        OutputStream oStream = new FileOutputStream(realSourceFile);
        
        byte[] buffer = new byte[FILE_BUFFER_SIZE];
        int len;
        
        while ((len = iStream.read(buffer)) > 0) {
            oStream.write(buffer, 0, len);
        }
        
        iStream.close();
        oStream.close();
        
        return realSourceFile;
    }
    
    /**
     * Does the actual work of importing the given file.
     * 
     * @param sourceFile the source file to import.
     * @param targetFileName the name of the target file to import to.
     * @throws CoreException if the transformation fails.
     */
    private void doImportModelFile(final File sourceFile, final String targetFileName)
        throws CoreException {
        
        URI sourceFileURI, targetFileURI;
        
        // Copy the source file to a temporary file with a .moml extension
        File realSourceFile;
        try {
            realSourceFile = getTemporarySourceFile(sourceFile);
        } catch (IOException e) {
            throw new CoreException(new Status(
                    IStatus.ERROR,
                    KaomImporterPtolemyPlugin.PLUGIN_ID,
                    Messages.DiagramsImporter_exception_temporaryFileCreationFailed,
                    e));
        }
        
        // Prepare target file and delete it if it already exists
        IFile targetFile = targetContainer.getFile(new Path(targetFileName));
        if (targetFile.exists()) {
            targetFile.delete(true, null);
        }
        
        // Prepare URIs
        sourceFileURI = URI.createFileURI(realSourceFile.getAbsolutePath());
        targetFileURI = URI.createPlatformResourceURI(targetFile.getFullPath().toString(), true);
        
        // Setup the transformation
        String transformation = "models/ptolemy2kaom"; //$NON-NLS-1$
        String entryFunction = "transform"; //$NON-NLS-1$
        
        EPackage p1 = MomlPackage.eINSTANCE;
        EPackage p2 = KaomPackage.eINSTANCE;
        EPackage p3 = AnnotationsPackage.eINSTANCE;
        
        // Do my bidding, little ones!
        XtendStatus status = XtendTransformationUtil.model2ModelTransform(
                new KielerProgressMonitor(new NullProgressMonitor()),
                transformation,
                entryFunction,
                sourceFileURI,
                targetFileURI,
                new PtolemyAnnotationHandler(),
                p1, p2, p3);
        
        // Check if everything went fine
        int severity = status.getSeverity();
        maxSeverity = Math.max(maxSeverity, severity);
         
        if (severity == XtendStatus.WARNING || severity == XtendStatus.ERROR) {
            throw new CoreException(new MultiStatus(
                    KaomImporterPtolemyPlugin.PLUGIN_ID,
                    severity,
                    new IStatus[] {status},
                    Messages.DiagramsImporter_exception_possibleErrors + sourceFile.getName(),
                    status.getException()));
        }
        
    }
    
    /**
     * Takes the given source file and initializes the corresponding diagram file.
     * 
     * @param sourceFileName the model file name.
     * @param targetFileName the diagram file name.
     * @throws CoreException in case of an error.
     */
    private void doImportDiagramFile(final String sourceFileName, final String targetFileName)
        throws CoreException {
        
        // Get the source file
        IFile sourceFile = targetContainer.getFile(new Path(sourceFileName));
        if (!sourceFile.exists()) {
            // Don't throw an exception. This is usually caused by the model import
            // having failed somehow, which will already have caused an exception.
            return;
        }
        
        // Get the target file and delete it if it already exists
        IFile targetFile = targetContainer.getFile(new Path(targetFileName));
        if (targetFile.exists()) {
            targetFile.delete(true, null);
        }
        
        // Prepare the file URIs
        URI sourceFileURI = URI.createPlatformResourceURI(
                sourceFile.getFullPath().toString(), true);
        
        // Create an editing domain and a resource to save the diagram into
        TransactionalEditingDomain editingDomain =
            GMFEditingDomainFactory.INSTANCE.createEditingDomain();
        
        ResourceSet resourceSet = editingDomain.getResourceSet();
        
        // Get the model's root element
        Resource sourceResource = resourceSet.getResource(sourceFileURI, true);
        EObject diagramRoot = (EObject) sourceResource.getContents().get(0);
        
        // Create and save diagram
        InitKaomDiagramHandler diagramInitializer = new InitKaomDiagramHandler();
        diagramInitializer.createNewDiagram(diagramRoot, editingDomain, targetFile, null);
    }
}
