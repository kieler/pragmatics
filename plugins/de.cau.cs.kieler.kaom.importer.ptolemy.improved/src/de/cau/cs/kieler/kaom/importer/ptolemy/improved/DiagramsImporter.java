/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kaom.importer.ptolemy.improved;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.statushandlers.StatusManager;
import org.ptolemy.moml.DocumentRoot;

import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.diagram.custom.commands.InitKaomDiagramHandler;
import de.cau.cs.kieler.kaom.importer.ptolemy.improved.utils.Utils;
import de.cau.cs.kieler.kaom.importer.ptolemy.improved.wizards.ImportDiagramsWizard;
import de.cau.cs.kieler.kaom.importer.ptolemy.improved.xtend.Ptolemy2KaomOptimization;
import de.cau.cs.kieler.kaom.importer.ptolemy.improved.xtend.Ptolemy2KaomTransformation;


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
     * Whether advanced annotations handling is to be turned on.
     */
    private boolean advancedAnnotationsHandling;
    
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
     * Map containing a standard list of parser features used for loading EMF resources.
     */
    private Map<String, Boolean> parserFeatures = null;
    
    
    /**
     * Constructs a new instance with the given configuration.
     * 
     * @param wizard the wizard using this importer.
     * @param sourceFiles the list of source files to import.
     * @param targetContainerPath the possibly non-existent container to import them to.
     * @param advancedAnnotationsHandling whether advanced annotations handling is to be
     *                                    turned on. If so, additional heuristics are performed
     *                                    to try an import more annotations, and annotations
     *                                    are connected to elements they are most likely
     *                                    annotating.
     * @param initializeDiagramFiles whether to initialize KAOD diagram files.
     * @param overwriteWithoutWarning whether existing files should be overwritten without
     *                                 warning.
     */
    public DiagramsImporter(final ImportDiagramsWizard wizard, final List<File> sourceFiles,
            final IPath targetContainerPath, final boolean advancedAnnotationsHandling,
            final boolean initializeDiagramFiles, final boolean overwriteWithoutWarning) {
        
        this.wizard = wizard;
        this.sourceFiles = sourceFiles;
        this.targetContainerPath = targetContainerPath;
        this.advancedAnnotationsHandling = advancedAnnotationsHandling;
        this.initializeDiagramFiles = initializeDiagramFiles;
        this.overwriteWithoutWarning = overwriteWithoutWarning;
        
        // Prepare parser feature map. These options avoid searching for DTDs online, which would
        // require an internet connection to load models
        parserFeatures = Maps.newHashMap();
        parserFeatures.put(
                "http://xml.org/sax/features/validation", //$NON-NLS-1$
                Boolean.FALSE);
        parserFeatures.put(
                "http://apache.org/xml/features/nonvalidating/load-dtd-grammar", //$NON-NLS-1$
                Boolean.FALSE);
        parserFeatures.put(
                "http://apache.org/xml/features/nonvalidating/load-external-dtd", //$NON-NLS-1$
                Boolean.FALSE);
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
                    PtolemyImportPlugin.PLUGIN_ID,
                    Messages.DiagramsImporter_status_importCanceled);
        } else if (statuses.size() >= 1) {
            return new MultiStatus(
                    PtolemyImportPlugin.PLUGIN_ID,
                    maxSeverity,
                    statuses.toArray(new IStatus[statuses.size()]),
                    Messages.DiagramsImporter_status_exceptions,
                    null);
        } else {
            return new Status(
                    IStatus.OK,
                    PtolemyImportPlugin.PLUGIN_ID,
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
                    PtolemyImportPlugin.PLUGIN_ID,
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
        // Calculate the total work to be done (we allocate two slots of work for each file
        // to be imported: model import, and diagram initialization)
        int totalWork = sourceFiles.size() * 2;
        
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
                            + PtolemyImportConstants.TARGET_MODEL_FILE_EXTENSION);
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
                                + PtolemyImportConstants.TARGET_MODEL_FILE_EXTENSION,
                            targetFilesBaseName + "." //$NON-NLS-1$
                                + PtolemyImportConstants.TARGET_DIAGRAM_FILE_EXTENSION);
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
                    + PtolemyImportConstants.TARGET_MODEL_FILE_EXTENSION));
        if (file.exists()) {
            modelFileNameClash = true;
        }
        
        if (initializeDiagramFiles) {
            file = targetContainer.getFile(new Path(
                    sourceFileBaseName + "." //$NON-NLS-1$
                        + PtolemyImportConstants.TARGET_DIAGRAM_FILE_EXTENSION));
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
                        + PtolemyImportConstants.TARGET_MODEL_FILE_EXTENSION
                        + "\n"); //$NON-NLS-1$
            }
            
            if (diagramFileNameClash) {
                sb.append("    " //$NON-NLS-1$
                        + sourceFileBaseName
                        + "." //$NON-NLS-1$
                        + PtolemyImportConstants.TARGET_DIAGRAM_FILE_EXTENSION
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
                            + PtolemyImportConstants.TARGET_MODEL_FILE_EXTENSION);
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
     * @throws CoreException if the transformation fails.
     */
    private void doImportModelFile(final File sourceFile, final String targetFileName)
        throws CoreException {
        
        ///////////////////////////////////////////////
        // STEP 1: Preparations
        
        // Copy the source file to a temporary file with a .moml extension
        File realSourceFile;
        try {
            realSourceFile = ensureMomlFileExtension(sourceFile);
        } catch (IOException e) {
            throw new CoreException(new Status(
                    IStatus.ERROR,
                    PtolemyImportPlugin.PLUGIN_ID,
                    Messages.DiagramsImporter_exception_temporaryFileCreationFailed,
                    e));
        }
        
        // Prepare target file and delete it if it already exists
        IFile targetFile = targetContainer.getFile(new Path(targetFileName));
        if (targetFile.exists()) {
            targetFile.delete(true, null);
        }
        
        // Prepare URIs
        URI sourceFileURI = URI.createFileURI(realSourceFile.getAbsolutePath());
        URI targetFileURI = URI.createPlatformResourceURI(targetFile.getFullPath().toString(), true);
        
        
        ///////////////////////////////////////////////
        // STEP 2: Loading the Source Model
        
        // Obtain a new resource set and tell it to save unknown XML elements that can easily appear
        // in MoML files
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getLoadOptions().put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, true);
        resourceSet.getLoadOptions().put(XMLResource.OPTION_PARSER_FEATURES, parserFeatures);
        
        // Load the source model
        Resource srcResource = null;
        DocumentRoot ptModel = null;
        
        try {
            srcResource = resourceSet.getResource(sourceFileURI, true);
            ptModel = (DocumentRoot) srcResource.getContents().get(0);
        } catch (Exception e) {
            throw new CoreException(new Status(
                    IStatus.ERROR,
                    PtolemyImportPlugin.PLUGIN_ID,
                    Messages.DiagramsImporter_exception_sourceModelNotLoaded + sourceFile.toString(),
                    e));
        }
        
        
        ///////////////////////////////////////////////
        // STEP 3: Import Process
        
        // Two steps: transformation, and optimization
        Injector injector = Guice.createInjector();
        
        Ptolemy2KaomTransformation transformation =
                injector.getInstance(Ptolemy2KaomTransformation.class);
        Ptolemy2KaomOptimization optimization =
                injector.getInstance(Ptolemy2KaomOptimization.class);
        
        // Transform and optimize
        Entity kaomModel = null;
        
        try {
            kaomModel = transformation.transform(ptModel);
            optimization.optimize(kaomModel);
        } catch (Exception e) {
            throw new CoreException(new Status(
                    IStatus.ERROR,
                    PtolemyImportPlugin.PLUGIN_ID,
                    "Model transformation failed: " + e.getMessage(),
                    e));
        }
        
        // Advanced annotation handling, if requested
        if (advancedAnnotationsHandling) {
            PtolemyAnnotationHandler annotationHandler = new PtolemyAnnotationHandler(
                    (XMLResource) srcResource, ptModel, kaomModel);
            
            annotationHandler.handleAnnotations();
        }
        
        
        ///////////////////////////////////////////////
        // STEP 4: Saving the Transformed Model
        
        // Create a new resource and add our KAOM model to it
        Resource dstResource = resourceSet.createResource(targetFileURI);
        dstResource.getContents().add(kaomModel);
        
        // Save the resource
        try {
            dstResource.save(Collections.EMPTY_MAP);
        } catch (Exception e) {
            throw new CoreException(new Status(
                    IStatus.ERROR,
                    PtolemyImportPlugin.PLUGIN_ID,
                    Messages.DiagramsImporter_exception_destModelNotSaved + targetFile.toString(),
                    e));
        }
        
        
        // Check if the transformation produced any warnings
        if (!transformation.getWarnings().isEmpty()) {
            throw new CoreException(new MultiStatus(
                    PtolemyImportPlugin.PLUGIN_ID,
                    IStatus.WARNING,
                    transformation.getWarnings().toArray(new IStatus[0]),
                    Messages.DiagramsImporter_exception_possibleErrors + sourceFile.getName(),
                    null));
        }
    }
    
    /**
     * Returns a version of the source file with moml file extension, if is not already a
     * moml file.
     * 
     * <p>This is necessary because the import can only cope with moml files, not with the
     * more common xml files. To work around this, xml files are copied to the temp
     * folder, using a file name with a moml extension. If the file already is a moml file,
     * nothing is copied and the original file is returned.</p>
     *  
     * @param sourceFile the source file to possibly be copied to a temporary moml file.
     * @return moml file to be imported.
     * @throws IOException if the creation of the temporary file failed.
     */
    private File ensureMomlFileExtension(final File sourceFile) throws IOException {
        // Check if the source file is already a moml file
        if (Utils.getFileExtension(sourceFile.getName()).toLowerCase().equals(
                PtolemyImportConstants.PTOLEMY_INTERNAL_FILE_EXTENSION)) {
            
            return sourceFile;
        }
        
        // Get the file's base name
        String baseName = Utils.getFileBaseName(sourceFile.getName());
        
        // Create a temporary file that automatically gets deleted when the VM ends
        File realSourceFile = File.createTempFile(baseName,
                "." + PtolemyImportConstants.PTOLEMY_INTERNAL_FILE_EXTENSION); //$NON-NLS-1$
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
