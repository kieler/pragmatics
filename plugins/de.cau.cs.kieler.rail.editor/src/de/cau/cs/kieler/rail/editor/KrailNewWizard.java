package de.cau.cs.kieler.rail.editor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.editor.DiagramEditorFactory;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.rail.Topologie.Model;
import de.cau.cs.kieler.rail.Topologie.TopologieFactory;

public class KrailNewWizard extends Wizard implements INewWizard {

    /** the grid size for KAOM diagrams (0 means no grid). */
    public static final int GRID_SIZE = 0;
    /** the default setting for snapping to grid. */
    public static final boolean SNAP_TO_GRID = false;
    
    /** the workbench. */
    private IWorkbench workbench;
    /** the current selection. */
    private IStructuredSelection selection;
    /** the diagram model wizard page. */
    private KrailCreationWizardPage diagramModelFilePage;
    /** the domain model wizard page. */
    private KrailCreationWizardPage domainModelFilePage;

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench theworkbench, final IStructuredSelection theselection) {
        this.workbench = theworkbench;
        this.selection = theselection;
        setWindowTitle("New rail Diagram");
        setNeedsProgressMonitor(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPages() {
        diagramModelFilePage = new KrailCreationWizardPage("DiagramModelFile", selection,
                KrailDiagramEditor.DIAGRAM_FILE_EXTENSION);
        diagramModelFilePage.setTitle("Create rail Diagram");
        diagramModelFilePage.setDescription("Select file that will contain diagram model.");
        addPage(diagramModelFilePage);

        domainModelFilePage = new KrailCreationWizardPage("DomainModelFile", selection,
                KrailDiagramEditor.MODEL_FILE_EXTENSION);
        domainModelFilePage.setTitle("Create MENGES Domain Model");
        domainModelFilePage.setDescription("Select file that will contain domain model.");
        addPage(domainModelFilePage);
    }

    /**
     * {@inheritDoc}
     */
    public boolean performFinish() {
        final Maybe<Resource> diagramResource = new Maybe<Resource>();
        IRunnableWithProgress op = new WorkspaceModifyOperation(null) {
            protected void execute(final IProgressMonitor monitor) throws CoreException,
                    InterruptedException {
                diagramResource.set(createDiagram(diagramModelFilePage.getURI(),
                        domainModelFilePage.getURI(), monitor));
                if (diagramResource.get() != null) {
                    try {
                        openDiagram(diagramResource.get());
                    } catch (PartInitException exception) {
                        ErrorDialog.openError(getContainer().getShell(),
                                "Error opening diagram editor", null, exception.getStatus());
                    }
                }
            }
        };
        try {
            getContainer().run(false, true, op);
        } catch (InterruptedException exception) {
            return false;
        } catch (InvocationTargetException exception) {
            if (exception.getTargetException() instanceof CoreException) {
                ErrorDialog.openError(getContainer().getShell(), "Creation Problems", null,
                        ((CoreException) exception.getTargetException()).getStatus());
            } else {
                IStatus status = new Status(IStatus.ERROR, KrailGraphitiPlugin.PLUGIN_ID,
                        "Error creating diagram", exception.getTargetException());
                StatusManager.getManager().handle(status, StatusManager.LOG);
            }
            return false;
        }
        return diagramResource.get() != null;
    }

    /**
     * Create a diagram with given URIs.
     * 
     * @param diagramURI
     *            URI for the diagram file
     * @param modelURI
     *            URI for the model file
     * @param progressMonitor
     *            progress monitor
     * @return a resource for the new diagram file
     */
    private static Resource createDiagram(final URI diagramURI, final URI modelURI,
            final IProgressMonitor progressMonitor) {
        progressMonitor.beginTask("Creating diagram and model files", 2);
        // create a resource set and editing domain
        TransactionalEditingDomain editingDomain = DiagramEditorFactory
                .createResourceSetAndEditingDomain();
        ResourceSet resourceSet = editingDomain.getResourceSet();
        CommandStack commandStack = editingDomain.getCommandStack();
        // create resources for the diagram and domain model files
        final Resource diagramResource = resourceSet.createResource(diagramURI);
        final Resource modelResource = resourceSet.createResource(modelURI);
        if (diagramResource != null && modelResource != null) {
            commandStack.execute(new RecordingCommand(editingDomain) {
                protected void doExecute() {
                    createModel(diagramResource, diagramURI.lastSegment(),
                            modelResource, modelURI.lastSegment());
                }
            });
            progressMonitor.worked(1);

            try {
                modelResource.save(createSaveOptions());
                diagramResource.save(createSaveOptions());
            } catch (IOException exception) {
                 IStatus status = new Status(IStatus.ERROR, KrailGraphitiPlugin.PLUGIN_ID,
                         "Unable to store model and diagram resources", exception);
                 StatusManager.getManager().handle(status);
            }
            setCharset(WorkspaceSynchronizer.getFile(modelResource));
            setCharset(WorkspaceSynchronizer.getFile(diagramResource));
        }
        progressMonitor.done();
        return diagramResource;
    }

    /**
     * Creates save options for the resources.
     * 
     * @return new save options
     */
    private static Map<?, ?> createSaveOptions() {
        HashMap<String, Object> saveOptions = new HashMap<String, Object>();
        saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
        saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED,
                Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
        return saveOptions;
    }

    /**
     * Set the character set for the given file to UTF-8.
     * 
     * @param file a file
     */
    private static void setCharset(final IFile file) {
        try {
            if (file != null) {
                file.setCharset("UTF-8", new NullProgressMonitor());
            }
        } catch (CoreException e) {
            StatusManager.getManager().handle(e, KrailGraphitiPlugin.PLUGIN_ID);
        }
    }

    /**
     * Open the diagram from the given resource.
     * 
     * @param diagramResource a resource for a diagram file
     * @throws PartInitException if the diagram could not be opened
     */
    private void openDiagram(final Resource diagramResource) throws PartInitException {
        String path = diagramResource.getURI().toPlatformString(true);
        IResource workspaceResource = ResourcesPlugin.getWorkspace().getRoot()
                .findMember(new Path(path));
        if (workspaceResource instanceof IFile) {
            IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
            page.openEditor(new FileEditorInput((IFile) workspaceResource),
                    KrailDiagramEditor.EDITOR_ID);
        }
    }
    
    /**
     * Create a model in the given resources.
     * 
     * @param diagramResource resource for the diagram model
     * @param diagramName name of the diagram model
     * @param modelResource resource for the domain model
     * @param modelName name of the domain model
     */
    private static void createModel(final Resource diagramResource, final String diagramName,
            final Resource modelResource, final String modelName) {
        modelResource.setTrackingModification(true);
        Model model = TopologieFactory.eINSTANCE.createModel();
        modelResource.getContents().add(model);
        diagramResource.setTrackingModification(true);
        Diagram diagram = Graphiti.getPeCreateService().createDiagram(
                KrailDiagramEditor.DIAGRAM_TYPE, diagramName,
                GRID_SIZE, SNAP_TO_GRID);
        PictogramLink link = PictogramsFactory.eINSTANCE.createPictogramLink();
        link.setPictogramElement(diagram);
        link.getBusinessObjects().add(model);
        diagramResource.getContents().add(diagram);
    }

}
