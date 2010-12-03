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
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
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
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.ptolemy.moml.MomlPackage;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.annotations.AnnotationsPackage;
import de.cau.cs.kieler.core.model.util.XtendStatus;
import de.cau.cs.kieler.core.model.util.XtendTransformationUtil;
import de.cau.cs.kieler.core.ui.KielerProgressMonitor;
import de.cau.cs.kieler.kaom.KaomPackage;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEditPart;
import de.cau.cs.kieler.kaom.diagram.part.KaomDiagramEditorPlugin;
import de.cau.cs.kieler.kaom.diagram.part.KaomDiagramEditorUtil;
import de.cau.cs.kieler.kaom.importer.ptolemy.KaomImporterPtolemyPlugin;
import de.cau.cs.kieler.kaom.importer.ptolemy.PtolemyImporterConstants;
import de.cau.cs.kieler.kaom.importer.ptolemy.utils.Utils;


/**
 * An importer for Ptolemy2 diagrams. Usually invoked by the import wizard.
 * 
 * @author cds
 */
class DiagramsImporter implements IRunnableWithProgress {
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
     * If the import threw an exception and was thus not successful, this is
     * the place where it's stored. As the run method cannot throw a
     * miscellaneous exception, we need to use this way to communicate the
     * status of the import.
     */
    private CoreException exception = null;
    
    
    /**
     * Constructs a new instance with the given configuration.
     * 
     * @param _wizard the wizard using this importer.
     * @param _sourceFiles the list of source files to import.
     * @param _targetContainerPath the possibly non-existent container to import them to.
     * @param _initializeDiagramFiles whether to initialize KAOD diagram files.
     * @param _overwriteWithoutWarning whether existing files should be overwritten without
     *                                 warning.
     */
    public DiagramsImporter(ImportDiagramsWizard _wizard, List<File> _sourceFiles,
            IPath _targetContainerPath, boolean _initializeDiagramFiles,
            boolean _overwriteWithoutWarning) {
        
        wizard = _wizard;
        sourceFiles = _sourceFiles;
        targetContainerPath = _targetContainerPath;
        initializeDiagramFiles = _initializeDiagramFiles;
        overwriteWithoutWarning = _overwriteWithoutWarning;
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
        return exception == null && !wasImportCanceled;
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
     * If the import was not successful, this method returns the exception that
     * occurred.
     * 
     * @return the exception or {@code null}.
     */
    public CoreException getException() {
        return exception;
    }
    
    
    /**
     * {@inheritDoc}
     */
    public void run(IProgressMonitor monitor) throws InvocationTargetException,
            InterruptedException {
        
        // If the progress monitor was omitted, use the null monitor
        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }
        
        // Calculate how much work there is to be done (also counting the subtask of having
        // to create the container)
        int workPerSourceFile = initializeDiagramFiles ? 2 : 1;
        int totalWork = sourceFiles.size() * workPerSourceFile + 1;
        
        // Begin the main task
        monitor.beginTask("Import Ptolemy2 diagrams...", totalWork);
        
        /* The main task is splitted into two subtasks:
         *  1. Ensuring the existence of the target container.
         *  2. Importing all the source files.
         */
        
        // Subtask 1
        try {
            targetContainer = getTargetContainer(new SubProgressMonitor(monitor, 1));
        } catch (CoreException e) {
            exception = e;
            monitor.done();
            return;
        }
        
        // Subtask 2
        try {
            importFiles(new SubProgressMonitor(monitor, totalWork - 1));
        } catch (CoreException e) {
            exception = e;
            monitor.done();
            return;
        }
        
        // Everything's done. Yay!
        monitor.done();
    }
    
    /**
     * Returns the target container, which is then guaranteed to exist.
     * 
     * @param monitor monitor to report progress to.
     * @return the target container.
     * @throws CoreException if something goes wrong creating the target container.
     */
    private IContainer getTargetContainer(IProgressMonitor monitor) throws CoreException {
        monitor.beginTask("Ensure target container existence.", 1);
        
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
                    "Invalid target container path: Project doesn't exist."));
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
     * 
     * @param monitor monitor to report progress to.
     * @throws CoreException if the import fails.
     */
    private void importFiles(IProgressMonitor monitor) throws CoreException {
        // Calculate the total work to be done
        int totalWork = sourceFiles.size();
        if (initializeDiagramFiles) {
            totalWork *= 2;
        }
        
        // Begin the main task
        monitor.beginTask("Importing diagrams.", totalWork);
        
        // Iterate through the array of source files
        for (File sourceFile : sourceFiles) {
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
            
            // Import (automatically advances the monitor)
            doImportModelFile(
                    sourceFile,
                    targetFilesBaseName + "."
                        + PtolemyImporterConstants.TARGET_MODEL_FILE_EXTENSION,
                    new SubProgressMonitor(monitor, 1));
            
            // Possibly initialize diagram file
            if (initializeDiagramFiles) {
                doImportDiagramFile(
                        targetFilesBaseName + "."
                            + PtolemyImporterConstants.TARGET_MODEL_FILE_EXTENSION,
                        targetFilesBaseName + "."
                            + PtolemyImporterConstants.TARGET_DIAGRAM_FILE_EXTENSION);
                monitor.worked(1);
            }
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
    private String getBaseFileName(String sourceFileName) {
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
                sourceFileBaseName + "."
                    + PtolemyImporterConstants.TARGET_MODEL_FILE_EXTENSION));
        if (file.exists()) {
            modelFileNameClash = true;
        }
        
        if (initializeDiagramFiles) {
            file = targetContainer.getFile(new Path(
                    sourceFileBaseName + "."
                        + PtolemyImporterConstants.TARGET_DIAGRAM_FILE_EXTENSION));
            if (file.exists() && initializeDiagramFiles) {
                diagramFileNameClash = true;
            }
        }
        
        // If we have a name clash, ask the user for a new name. Politely.
        if (modelFileNameClash || diagramFileNameClash) {
            StringBuilder sb = new StringBuilder(300);
            sb.append("The target folder already contains the following file(s):\n\n");
            
            if (modelFileNameClash) {
                sb.append("    "
                        + sourceFileBaseName
                        + "."
                        + PtolemyImporterConstants.TARGET_MODEL_FILE_EXTENSION
                        + "\n");
            }
            
            if (diagramFileNameClash) {
                sb.append("    "
                        + sourceFileBaseName
                        + "."
                        + PtolemyImporterConstants.TARGET_DIAGRAM_FILE_EXTENSION
                        + "\n");
            }
            
            sb.append("\n");
            sb.append("Please enter a new name without file extension or cancel the import.");
            
            // Popup a dialog
            final InputDialog dialog = new InputDialog(
                    wizard.getShell(),
                    "File Already Exists",
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
                        dialog.getValue() + "."
                            + PtolemyImporterConstants.TARGET_MODEL_FILE_EXTENSION);
            }
        } else {
            return sourceFileBaseName;
        }
    }
    
    /**
     * Does the actual work of importing the given file.
     * 
     * @param sourceFile the source file to import.
     * @param targetFileName the name of the target file to import to.
     * @param monitor the progress monitor to report progress to.
     * @throws CoreException if the transformation fails.
     */
    private void doImportModelFile(File sourceFile, String targetFileName, IProgressMonitor monitor)
        throws CoreException {
        
        URI sourceFileURI, targetFileURI;
        
        // Prepare target file
        IFile targetFile = targetContainer.getFile(new Path(targetFileName));
        
        // Prepare URIs
        sourceFileURI = URI.createFileURI(sourceFile.getAbsolutePath());
        targetFileURI = URI.createPlatformResourceURI(targetFile.getFullPath().toString(), true);
        
        // Setup the transformation
        String transformation = "models/ptolemy2kaom";
        String entryFunction = "transform";
        
        EPackage p1 = MomlPackage.eINSTANCE;
        EPackage p2 = KaomPackage.eINSTANCE;
        EPackage p3 = AnnotationsPackage.eINSTANCE;
        
        // Do my bidding, little ones!
        XtendStatus status = XtendTransformationUtil.model2ModelTransform(
                new KielerProgressMonitor(monitor),
                transformation,
                entryFunction,
                sourceFileURI,
                targetFileURI,
                p1, p2, p3);
        
        // Check if everything went fine
        if (status.getSeverity() == XtendStatus.ERROR) {
            throw new CoreException(new Status(
                    IStatus.ERROR,
                    KaomImporterPtolemyPlugin.PLUGIN_ID,
                    status.getMessage(),
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
    private void doImportDiagramFile(String sourceFileName, String targetFileName)
        throws CoreException {
        
        // Get the source file
        IFile sourceFile = targetContainer.getFile(new Path(sourceFileName));
        if (!sourceFile.exists()) {
            throw new CoreException(new Status(
                    IStatus.ERROR,
                    KaomImporterPtolemyPlugin.PLUGIN_ID,
                    "Model file not found: " + sourceFileName));
        }
        
        // Get the target file
        IFile targetFile = targetContainer.getFile(new Path(targetFileName));
        if (targetFile.exists()) {
            targetFile.delete(true, null);
        }
        
        // Prepare the file URIs
        URI sourceFileURI = URI.createPlatformResourceURI(
                sourceFile.getFullPath().toString(), true);
        URI targetFileURI = URI.createPlatformResourceURI(
                targetFile.getFullPath().toString(), true);
        
        // Create an editing domain and a resource to save the diagram into
        TransactionalEditingDomain editingDomain =
            GMFEditingDomainFactory.INSTANCE.createEditingDomain();
        
        ResourceSet resourceSet = editingDomain.getResourceSet();
        final Resource targetResource = resourceSet.createResource(targetFileURI);
        
        // Get the model's root element
        Resource sourceResource = resourceSet.getResource(sourceFileURI, true);
        EObject diagramRoot = (EObject) sourceResource.getContents().get(0);
        
        // Create the diagram
        final Diagram diagram = ViewService.createDiagram(
                diagramRoot,
                EntityEditPart.MODEL_ID,
                KaomDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
        
        // Add the diagram to the resource. This requires a proper transaction, so get
        // ready for some verbose transaction code action! (this has nothing to do with
        // music; that would be a TranceAction...) (sorry...)
        List<IFile> affectedFiles = new ArrayList<IFile>();
        affectedFiles.add(targetFile);
        AbstractTransactionalCommand command = new AbstractTransactionalCommand(
                editingDomain,
                "",
                affectedFiles) {
            
            @Override
            protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
                    throws ExecutionException {

                targetResource.getContents().add(diagram);
                return CommandResult.newOKCommandResult();
            }
        };
        
        try {
            OperationHistoryFactory.getOperationHistory().execute(command, null, null);
        } catch (ExecutionException e) {
            throw new CoreException(new Status(
                    IStatus.ERROR,
                    KaomImporterPtolemyPlugin.PLUGIN_ID,
                    "Error initializing diagram " + targetFileName,
                    e));
        }
        
        
        // Try to save the diagram
        try {
            targetResource.save(KaomDiagramEditorUtil.getSaveOptions());
        } catch (IOException e) {
            throw new CoreException(new Status(
                    IStatus.ERROR,
                    KaomImporterPtolemyPlugin.PLUGIN_ID,
                    "Error initializing diagram " + targetFileName,
                    e));
        }
    }
}
