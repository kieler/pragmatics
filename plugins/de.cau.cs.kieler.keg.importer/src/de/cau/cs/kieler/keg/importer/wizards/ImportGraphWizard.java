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
package de.cau.cs.kieler.keg.importer.wizards;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.model.m2m.TransformException;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.ui.ProgressMonitorAdapter;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.keg.diagram.edit.parts.NodeEditPart;
import de.cau.cs.kieler.keg.diagram.part.GraphsDiagramEditorPlugin;
import de.cau.cs.kieler.keg.diagram.part.GraphsDiagramEditorUtil;
import de.cau.cs.kieler.keg.importer.AbstractImporter;
import de.cau.cs.kieler.keg.importer.IImporter;
import de.cau.cs.kieler.keg.importer.ImportManager;
import de.cau.cs.kieler.keg.importer.KEGImporterPlugin;

/**
 * A wizard for importing graphs from various file formats into the KEG format.
 * 
 * @author mri
 */
public class ImportGraphWizard extends Wizard implements IImportWizard {

    /** the page from which to select the import source (file system or workspace). */
    private ImportGraphSourcesPage sourcesPage;
    /** the page from which to select the workspace source files and the target folder. */
    private ImportGraphWorkspaceSourcesPage workspaceSourcesPage;
    /** the page from which to select the file system source files and the target folder. */
    private ImportGraphFileSystemSourcesPage fileSystemSourcesPage;
    /** the page from which to select the import graph type and options. */
    private ImportGraphOptionsPage optionsPage;

    /** the selection the import wizard was called on. */
    private IStructuredSelection selection;

    /**
     * Constructs a graph import wizard.
     */
    public ImportGraphWizard() {
        super();
        setWindowTitle(Messages.ImportGraphWizard_title);
        setDialogSettings(KEGImporterPlugin.getDefault().getDialogSettings());
        setNeedsProgressMonitor(true);
    }

    /**
     * {@inheritDoc}
     */
    public void addPages() {
        sourcesPage = new ImportGraphSourcesPage();
        workspaceSourcesPage = new ImportGraphWorkspaceSourcesPage(selection);
        fileSystemSourcesPage = new ImportGraphFileSystemSourcesPage(selection);
        optionsPage = new ImportGraphOptionsPage();
        addPage(sourcesPage);
        addPage(workspaceSourcesPage);
        addPage(fileSystemSourcesPage);
        addPage(optionsPage);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public IWizardPage getStartingPage() {
        // determine the starting page depending on the selection
        @SuppressWarnings("unchecked")
        Iterator<Object> iterator = selection.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object instanceof IFile) {
                return workspaceSourcesPage;
            }
        }
        return sourcesPage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IWizardPage getNextPage(final IWizardPage page) {
        if (page == sourcesPage) {
            if (sourcesPage.getImportFromWorkspace()) {
                return workspaceSourcesPage;
            } else {
                return fileSystemSourcesPage;
            }
        }
        if (page == workspaceSourcesPage || page == fileSystemSourcesPage) {
            return optionsPage;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canFinish() {
        if (!optionsPage.isPageComplete()) {
            return false;
        }
        if (sourcesPage.getImportFromWorkspace()) {
            return workspaceSourcesPage.isPageComplete();
        } else {
            return fileSystemSourcesPage.isPageComplete();
        }
    }

    private List<IStatus> statuses;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performFinish() {
        workspaceSourcesPage.saveDialogSettings();
        fileSystemSourcesPage.saveDialogSettings();
        optionsPage.savePreferences();
        // run the generation in the wizard container
        IRunnableWithProgress runnable = new IRunnableWithProgress() {
            public void run(final IProgressMonitor monitor) throws InterruptedException,
                    InvocationTargetException {
                try {
                    doFinish(new ProgressMonitorAdapter(monitor));
                } catch (InterruptedException e) {
                    throw e;
                } catch (Throwable e) {
                    throw new InvocationTargetException(e);
                }
            }
        };
        try {
            getContainer().run(true, true, runnable);
        } catch (InterruptedException exception) {
            return false;
        } catch (InvocationTargetException exception) {
            IStatus status =
                    new Status(IStatus.ERROR, KEGImporterPlugin.PLUGIN_ID,
                            Messages.ImportGraphWizard_import_failed_error, exception.getCause());
            StatusManager.getManager().handle(status, StatusManager.BLOCK | StatusManager.SHOW);
        }
        // show any errors which occured during import
        if (statuses.size() > 0) {
            MultiStatus multiStatus =
                    new MultiStatus(KEGImporterPlugin.PLUGIN_ID, IStatus.WARNING,
                            statuses.toArray(new Status[0]),
                            Messages.ImportGraphWizard_errors_occured_error, null);
            StatusManager.getManager()
                    .handle(multiStatus, StatusManager.BLOCK | StatusManager.SHOW);
        }
        return true;
    }

    private void doFinish(final IKielerProgressMonitor monitor) throws InterruptedException,
            ExecutionException {
        // collect all statuses
        statuses = new LinkedList<IStatus>();
        // get options
        final Maybe<AbstractImporter> importer = new Maybe<AbstractImporter>();
        final Maybe<IPropertyHolder> options = new Maybe<IPropertyHolder>();
        final Maybe<Boolean> createDiagram = new Maybe<Boolean>();
        final Maybe<Boolean> openDiagram = new Maybe<Boolean>();
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            public void run() {
                importer.set(optionsPage.getImporter());
                options.set(optionsPage.getOptions());
                createDiagram.set(optionsPage.getCreateDiagramFiles());
                openDiagram.set(optionsPage.getOpenDiagramFiles());
            }
        });
        // import from workspace or file system
        if (sourcesPage.getImportFromWorkspace()) {
            // fetch the files and target folder from the workspace sources page
            final Maybe<List<IFile>> files = new Maybe<List<IFile>>();
            final Maybe<IPath> targetPath = new Maybe<IPath>();
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    files.set(workspaceSourcesPage.getFiles(null));
                    targetPath.set(workspaceSourcesPage.getTargetContainerPath());
                }
            });
            monitor.begin(Messages.ImportGraphWizard_importing_workspace_task, files.get().size());
            // try to import all selected files
            for (IFile file : files.get()) {
                if (monitor.isCanceled()) {
                    throw new InterruptedException();
                }
                try {
                    InputStream inputStream = file.getContents(true);
                    IPath modelFile =
                            targetPath.get().append(file.getName()).removeFileExtension()
                                    .addFileExtension("keg"); //$NON-NLS-1$
                    importAndSerialize(inputStream, modelFile, importer.get(), createDiagram.get(),
                            openDiagram.get(), options.get(), monitor.subTask(1));
                } catch (Throwable throwable) {
                    IStatus status =
                            new Status(IStatus.ERROR, KEGImporterPlugin.PLUGIN_ID,
                                    Messages.ImportGraphWizard_import_failed_workspace_error
                                            + file.getFullPath(), throwable);
                    StatusManager.getManager().handle(status, StatusManager.LOG);
                    statuses.add(status);
                }
            }
        } else {
            // fetch the files and target folder from the file system sources page
            final Maybe<List<File>> files = new Maybe<List<File>>();
            final Maybe<IPath> targetPath = new Maybe<IPath>();
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    files.set(fileSystemSourcesPage.getFiles(null));
                    targetPath.set(fileSystemSourcesPage.getTargetContainerPath());
                }
            });
            monitor.begin(Messages.ImportGraphWizard_importing_file_system_task, files.get().size());
            // try to import all selected files
            for (File file : files.get()) {
                if (monitor.isCanceled()) {
                    throw new InterruptedException();
                }
                try {
                    FileInputStream inputStream = new FileInputStream(file);
                    // build the KEG model file path
                    IPath modelFile =
                            targetPath.get().append(file.getName()).removeFileExtension()
                                    .addFileExtension("keg"); //$NON-NLS-1$
                    // import and serialize the graph
                    importAndSerialize(inputStream, modelFile, importer.get(), createDiagram.get(),
                            openDiagram.get(), options.get(), monitor.subTask(1));
                } catch (Throwable throwable) {
                    IStatus status =
                            new Status(IStatus.ERROR, KEGImporterPlugin.PLUGIN_ID,
                                    Messages.ImportGraphWizard_import_failed_file_system_error
                                            + file.getPath(), throwable);
                    StatusManager.getManager().handle(status, StatusManager.LOG);
                    statuses.add(status);
                }
            }
        }
    }

    private void importAndSerialize(final InputStream inputStream, final IPath modelPath,
            final IImporter importer, final boolean createDiagram, final boolean openDiagram,
            final IPropertyHolder options, final IKielerProgressMonitor monitor)
            throws IOException, TransformException, InterruptedException, ExecutionException {
        // perform the import
        Node graph = importer.doImport(inputStream, options, monitor);
        // serialize KEG graph
        serializeKEGGraph(modelPath, graph);
        // post process the model file
        importer.doModelPostProcess(modelPath, options);
        // create diagram file if selected
        if (createDiagram) {
            IPath diagramFile = modelPath.removeFileExtension().addFileExtension("kegdi"); //$NON-NLS-1$
            createDiagram(modelPath, diagramFile);
            // post process the diagram file
            options.setProperty(ImportManager.OPTION_OPEN_DIAGRAM, openDiagram);
            importer.doDiagramPostProcess(diagramFile, options);
            // open the diagram in an editor if selected and not already opened by the post process
            if (openDiagram) {
                openDiagram(diagramFile);
            }
        }
    }

    private void serializeKEGGraph(final IPath modelPath, final Node graph) throws IOException {
        URI fileURI = URI.createPlatformResourceURI(modelPath.toOSString(), true);
        URIConverter uriConverter = new ExtensibleURIConverterImpl();
        OutputStream outputStream = uriConverter.createOutputStream(fileURI);
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.createResource(URI.createURI("http:///My.keg")); //$NON-NLS-1$
        resource.getContents().add(graph);
        Map<String, Object> resourceOptions = new HashMap<String, Object>();
        resourceOptions.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
        resourceOptions.put(XMLResource.OPTION_FORMATTED, true);
        // write to the stream
        resource.save(outputStream, resourceOptions);
        outputStream.close();
    }

    private void createDiagram(final IPath modelPath, final IPath diagramFile) throws IOException {
        closeDiagram(diagramFile);
        // load the model
        ResourceSet modelResourceSet = new ResourceSetImpl();
        URI modelFileURI = URI.createPlatformResourceURI(modelPath.toOSString(), true);
        Resource modelResource = modelResourceSet.getResource(modelFileURI, true);
        // create the diagram resource
        ResourceSet diagramResourceSet = new ResourceSetImpl();
        URI diagramFileURI = URI.createPlatformResourceURI(diagramFile.toOSString(), true);
        Resource diagramResource = diagramResourceSet.createResource(diagramFileURI);
        // create the diagram model and serialize it to the resource
        EObject model = (EObject) modelResource.getContents().get(0);
        Diagram diagram =
                ViewService.createDiagram(model, NodeEditPart.MODEL_ID,
                        GraphsDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
        diagramResource.getContents().add(diagram);
        diagramResource.save(GraphsDiagramEditorUtil.getSaveOptions());
    }

    private void openDiagram(final IPath diagramPath) {
        final IFile diagramFile = ResourcesPlugin.getWorkspace().getRoot().getFile(diagramPath);
        if (diagramFile.exists()) {
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    IWorkbenchPage page =
                            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                    IEditorInput input = new FileEditorInput(diagramFile);
                    while (page.findEditor(input) == null) {
                        // open the diagram file in an editor
                        IEditorDescriptor editorDescriptor = IDE.getDefaultEditor(diagramFile);
                        if (editorDescriptor == null || editorDescriptor.isOpenExternal()) {
                            // if no editor to open the diagram is present ignore it
                            return;
                        }
                        try {
                            IDE.openEditor(page, diagramFile, editorDescriptor.getId(), true);
                        } catch (PartInitException e) {
                            // opening the diagram failed but as it is optional it can be safely
                            // ignored
                        }
                    }
                }
            });
        }
    }

    private void closeDiagram(final IPath diagramPath) {
        final IFile diagramFile = ResourcesPlugin.getWorkspace().getRoot().getFile(diagramPath);
        if (diagramFile.exists()) {
            // close all editors which have the diagram file opened
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    IWorkbenchPage page =
                            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                    IEditorInput input = new FileEditorInput(diagramFile);
                    IEditorPart editorPart;
                    while ((editorPart = page.findEditor(input)) != null) {
                        page.closeEditor(editorPart, false);
                    }
                }
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench, final IStructuredSelection theselection) {
        selection = theselection;
    }

    /**
     * Returns the most common extension among all selected files.
     * 
     * @return the extension
     */
    public String getCommonSelectedExtension() {
        // map all extensions on the number of their occurence
        Map<String, Integer> extensionNumbers = new HashMap<String, Integer>();
        if (sourcesPage.getImportFromWorkspace()) {
            for (IResource resource : workspaceSourcesPage.getResources(null)) {
                String extension = resource.getFileExtension();
                if (extension != null) {
                    Integer number = extensionNumbers.get(extension);
                    if (number == null) {
                        number = 0;
                    }
                    number++;
                    extensionNumbers.put(extension, number);
                }
            }
        } else {
            for (File file : fileSystemSourcesPage.getFiles(null)) {
                String extension = getExtension(file);
                if (extension != null) {
                    Integer number = extensionNumbers.get(extension);
                    if (number == null) {
                        number = 0;
                    }
                    number++;
                    extensionNumbers.put(extension, number);
                }
            }
        }
        // find the most common extension among all selected files
        String topExt = ""; //$NON-NLS-1$
        int topExtNumber = -1;
        for (Map.Entry<String, Integer> entry : extensionNumbers.entrySet()) {
            if (entry.getValue() > topExtNumber) {
                topExt = entry.getKey();
                topExtNumber = entry.getValue();
            }
        }
        return topExt;
    }

    private String getExtension(final File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex + 1 == fileName.length()) {
            return null;
        }
        String extension = fileName.substring(dotIndex + 1);
        return extension;
    }
}
