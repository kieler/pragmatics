/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.keg.diagram.custom.wizards;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.core.ui.KielerProgressMonitor;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.keg.diagram.custom.KEGDiagramPlugin;
import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.keg.diagram.custom.random.IRandomGraphGenerator;
import de.cau.cs.kieler.keg.diagram.custom.random.RandomGraphGenerator;
import de.cau.cs.kieler.keg.diagram.edit.parts.NodeEditPart;
import de.cau.cs.kieler.keg.diagram.part.GraphsDiagramEditorPlugin;
import de.cau.cs.kieler.keg.diagram.part.GraphsDiagramEditorUtil;

/**
 * The new-wizard for creating random KEG graphs.
 * 
 * @author mri
 */
public class RandomGraphWizard extends Wizard implements INewWizard {

    /** the wizard title. */
    private static final String TITLE = "New Random Graph";

    /** the title for the dialog asking about revising settings. */
    private static final String QUESTION_TITLE = "Revise settings";
    /** the message for asking the user to revise settings when surpassing the graphs soft-limit. */
    private static final String MESSAGE_HIGH_NUMBER_OF_GRAPHS =
            "You entered a very high number of generated graphs."
                    + " This can cause significant delay. Do you want to continue?";
    /** the message for asking the user to revise settings when surpassing the diagrams soft-limit. */
    private static final String MESSAGE_HIGH_NUMBER_OF_GRAPHS_AND_DIAGRAMS =
            "You entered a high number of generated graphs and selected to generate diagram files."
                    + " This can cause significant delay. Do you want to continue?";
    /** the message for asking the user to revise settings when surpassing the open soft-limit. */
    private static final String MESSAGE_HIGH_NUMBER_OF_GRAPHS_AND_OPEN_DIAGRAMS =
            "You selected to open all generated diagram files."
                    + " This can cause significant delay for the entered number of generated graphs."
                    + " Do you want to continue?";

    /** the soft-limit for the number of generated graphs. */
    private static final int SOFT_LIMIT_GRAPHS = 10000;
    /** the soft-limit for the number of generated diagrams. */
    private static final int SOFT_LIMIT_DIAGRAMS = 1000;
    /** the soft-limit for the number of opened diagrams. */
    private static final int SOFT_LIMIT_OPEN_DIAGRAMS = 10;

    /** the selection this wizard is invoked on. */
    private IStructuredSelection selection;

    /** the new file page. */
    private RandomGraphNewFilePage newFilePage;
    /** the graph type page. */
    private RandomGraphTypePage typePage;
    /** the page for the ANY graph type. */
    private RandomGraphAnyPage anyPage;
    /** the page for the TREE graph type. */
    private RandomGraphTreePage treePage;
    /** the page for the BICONNECTED graph type. */
    private RandomGraphBiconnectedPage biconnectedPage;
    /** the page for the TRICONNECTED graph type. */
    private RandomGraphTriconnectedPage triconnectedPage;
    /** the page for the ACYCLIC_NO_TRANSITIV_EDGES graph type. */
    private RandomGraphANTEPage antePage;
    /** the utility page. */
    private RandomGraphUtilityPage utilityPage;

    /**
     * Creates a RandomGraphWizard.
     */
    public RandomGraphWizard() {
        super();
        setNeedsProgressMonitor(true);
        setWindowTitle(TITLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPages() {
        newFilePage = new RandomGraphNewFilePage(selection);
        typePage = new RandomGraphTypePage();
        anyPage = new RandomGraphAnyPage();
        treePage = new RandomGraphTreePage();
        biconnectedPage = new RandomGraphBiconnectedPage();
        triconnectedPage = new RandomGraphTriconnectedPage();
        antePage = new RandomGraphANTEPage();
        utilityPage = new RandomGraphUtilityPage();
        addPage(newFilePage);
        addPage(typePage);
        addPage(anyPage);
        addPage(treePage);
        addPage(biconnectedPage);
        addPage(triconnectedPage);
        addPage(antePage);
        addPage(utilityPage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IWizardPage getNextPage(final IWizardPage page) {
        if (page == newFilePage) {
            return typePage;
        }
        if (page == typePage) {
            switch (typePage.getGraphType()) {
            case TREE:
                return treePage;
            case BICONNECTED:
                return biconnectedPage;
            case TRICONNECTED:
                return triconnectedPage;
            case ACYCLIC_NO_TRANSITIV_EDGES:
                return antePage;
            case ANY:
            default:
                return anyPage;
            }
        }
        if (page == treePage || page == biconnectedPage || page == triconnectedPage
                || page == antePage || page == anyPage) {
            return utilityPage;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performFinish() {
        savePreferences();
        // if necessary ask the user to verify his decisions on the number of generated graphs
        if (newFilePage.getNumberOfGraphs() > SOFT_LIMIT_GRAPHS) {
            if (!askUser(MESSAGE_HIGH_NUMBER_OF_GRAPHS)) {
                getContainer().showPage(newFilePage);
                return false;
            }
        } else if (newFilePage.getCreateDiagramFiles()
                && newFilePage.getNumberOfGraphs() > SOFT_LIMIT_DIAGRAMS) {
            if (!askUser(MESSAGE_HIGH_NUMBER_OF_GRAPHS_AND_DIAGRAMS)) {
                getContainer().showPage(newFilePage);
                return false;
            }
        } else if (newFilePage.getOpenDiagramFiles()
                && newFilePage.getNumberOfGraphs() > SOFT_LIMIT_OPEN_DIAGRAMS) {
            if (!askUser(MESSAGE_HIGH_NUMBER_OF_GRAPHS_AND_OPEN_DIAGRAMS)) {
                getContainer().showPage(newFilePage);
                return false;
            }
        }
        // run the generation in the wizard container
        IRunnableWithProgress runnable = new IRunnableWithProgress() {
            public void run(final IProgressMonitor monitor) throws InterruptedException,
                    InvocationTargetException {
                try {
                    doFinish(new KielerProgressMonitor(monitor));
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
                    new Status(IStatus.ERROR, KEGDiagramPlugin.PLUGIN_ID,
                            "Failed generating graphs", exception.getCause());
            StatusManager.getManager().handle(status, StatusManager.BLOCK | StatusManager.SHOW);
        }

        return true;
    }

    private boolean askUser(final String question) {
        MessageBox messageBox =
                new MessageBox(getContainer().getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
        messageBox.setMessage(question);
        messageBox.setText(QUESTION_TITLE);
        int response = messageBox.open();
        return response == SWT.YES;
    }

    private String nameWithoutExt;
    private String ext;
    private int i;

    /**
     * Performs the actual generation and serialization.
     * 
     * @param monitor
     *            the progress monitor
     * @throws IOException
     *             when serializing a graph failed
     * @throws CoreException
     *             when refreshing the resource hierarchy failed
     * @throws InterruptedException
     *             when the user cancels the operation
     */
    private void doFinish(final IKielerProgressMonitor monitor) throws IOException, CoreException,
            InterruptedException {
        int numberOfGraphs = newFilePage.getNumberOfGraphs();
        monitor.begin("Generating KEG graphs", numberOfGraphs);
        try {
            // generate and serialize
            if (numberOfGraphs == 1) {
                Display.getDefault().syncExec(new Runnable() {
                    public void run() {
                        // collect the options from the pages
                        IPropertyHolder options = getOptions();
                        IFile file = newFilePage.createNewFile();
                        try {
                            generateAndSerialize(file, options, monitor.subTask(1));
                        } catch (IOException e) {
                            // ignore
                        } catch (CoreException e) {
                            // ignore
                        }
                    }
                });
            } else {
                // prepare to build filenames
                Display.getDefault().syncExec(new Runnable() {
                    public void run() {
                        String name = newFilePage.getFileName();
                        nameWithoutExt = name.substring(0, name.lastIndexOf("."));
                        ext = newFilePage.getFileExtension();
                    }
                });
                // generate the desired number of graphs
                for (i = 0; i < numberOfGraphs; ++i) {
                    if (monitor.isCanceled()) {
                        throw new InterruptedException();
                    }
                    Display.getDefault().syncExec(new Runnable() {
                        public void run() {
                            IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
                            IPath path =
                                    newFilePage.getContainerFullPath().append(
                                            new Path(nameWithoutExt + i + "." + ext));
                            IFile file = workspaceRoot.getFile(path);
                            // collect the options from the pages
                            IPropertyHolder options = getOptions();
                            // generate and serialize the graph
                            try {
                                generateAndSerialize(file, options, monitor.subTask(1));
                            } catch (IOException e) {
                                // ignore
                            } catch (CoreException e) {
                                // ingore
                            }
                        }
                    });
                }
            }
        } finally {
            monitor.done();
        }
    }

    private void generateAndSerialize(final IFile file, final IPropertyHolder options,
            final IKielerProgressMonitor monitor) throws IOException, CoreException {
        monitor.begin("Generating and serializing: " + file.getName(), 1);
        try {
            // generate
            IRandomGraphGenerator generator = new RandomGraphGenerator();
            Node graph = generator.generate(options);
            // serialize
            ResourceSet resourceSet = new ResourceSetImpl();
            Resource resource =
                    resourceSet.createResource(URI.createURI(file.getLocationURI().toString()));
            resource.getContents().add(graph);
            resource.save(Collections.EMPTY_MAP);
            file.refreshLocal(1, null);
            // create diagram file if requested
            if (newFilePage.getCreateDiagramFiles()) {
                IPath path = file.getFullPath();
                IPath diagramPath = path.removeFileExtension().addFileExtension("kegdi");
                createKEGDiagram(path, diagramPath);
                // open diagram file if requested
                if (newFilePage.getOpenDiagramFiles()) {
                    openDiagram(diagramPath);
                }
            }
        } finally {
            monitor.done();
        }
    }

    private void createKEGDiagram(final IPath modelFile, final IPath diagramFile)
            throws IOException {
        closeDiagramEditor(diagramFile);
        // load the model
        ResourceSet modelResourceSet = new ResourceSetImpl();
        URI modelFileURI = URI.createPlatformResourceURI(modelFile.toOSString(), true);
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

    private void closeDiagramEditor(final IPath diagramPath) {
        final IFile diagramFile = ResourcesPlugin.getWorkspace().getRoot().getFile(diagramPath);
        if (diagramFile.exists()) {
            // close all editors which have the diagram file opened
            MonitoredOperation.runInUI(new Runnable() {
                public void run() {
                    IWorkbenchPage page =
                            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                    IEditorInput input = new FileEditorInput(diagramFile);
                    IEditorPart editorPart;
                    while ((editorPart = page.findEditor(input)) != null) {
                        page.closeEditor(editorPart, false);
                    }
                }
            }, true);
        }
    }

    private void openDiagram(final IPath diagramPath) {
        final IFile diagramFile = ResourcesPlugin.getWorkspace().getRoot().getFile(diagramPath);
        MonitoredOperation.runInUI(new Runnable() {
            public void run() {
                IWorkbenchPage page =
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                // open the diagram file in an editor
                IEditorDescriptor editorDescriptor = IDE.getDefaultEditor(diagramFile);
                if (editorDescriptor == null || editorDescriptor.isOpenExternal()) {
                    // if not editor to open the diagram is present ignore it
                    return;
                }
                try {
                    IDE.openEditor(page, diagramFile, editorDescriptor.getId(), true);
                } catch (PartInitException e) {
                    // opening the diagram failed but as it is optional it can be safely ignored
                }
            }
        }, true);
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench, final IStructuredSelection theselection) {
        this.selection = theselection;
    }

    private IPropertyHolder getOptions() {
        IPropertyHolder options = new MapPropertyHolder();
        options.setProperty(RandomGraphGenerator.GRAPH_TYPE, typePage.getGraphType());
        switch (typePage.getGraphType()) {
        case ANY:
            options.setProperty(RandomGraphGenerator.NUMBER_OF_NODES, anyPage.getNumberOfNodes());
            options.setProperty(RandomGraphGenerator.EDGE_DETERMINATION,
                    anyPage.getEdgeDetermination());
            options.setProperty(RandomGraphGenerator.NUMBER_OF_EDGES, anyPage.getNumberOfEdges());
            options.setProperty(RandomGraphGenerator.MIN_OUTGOING_EDGES,
                    anyPage.getMinOutgoingEdges());
            options.setProperty(RandomGraphGenerator.MAX_OUTGOING_EDGES,
                    anyPage.getMaxOutgoingEdges());
            options.setProperty(RandomGraphGenerator.SELF_LOOPS, anyPage.getSelfLoops());
            options.setProperty(RandomGraphGenerator.MULTI_EDGES, anyPage.getMultiEdges());
            options.setProperty(RandomGraphGenerator.CYCLES, anyPage.getCycles());
            break;
        case TREE:
            options.setProperty(RandomGraphGenerator.NUMBER_OF_NODES, treePage.getNumberOfNodes());
            options.setProperty(RandomGraphGenerator.MAX_DEGREE, treePage.getMaxDegree());
            options.setProperty(RandomGraphGenerator.MAX_WIDTH, treePage.getMaxWidth());
            break;
        case BICONNECTED:
            options.setProperty(RandomGraphGenerator.NUMBER_OF_NODES,
                    biconnectedPage.getNumberOfNodes());
            options.setProperty(RandomGraphGenerator.NUMBER_OF_EDGES,
                    biconnectedPage.getNumberOfEdges());
            break;
        case TRICONNECTED:
            options.setProperty(RandomGraphGenerator.NUMBER_OF_NODES,
                    triconnectedPage.getNumberOfNodes());
            break;
        case ACYCLIC_NO_TRANSITIV_EDGES:
            options.setProperty(RandomGraphGenerator.NUMBER_OF_NODES, antePage.getNumberOfNodes());
            options.setProperty(RandomGraphGenerator.NUMBER_OF_EDGES, antePage.getNumberOfEdges());
            options.setProperty(RandomGraphGenerator.PLANAR, antePage.getPlanar());
            break;
        }
        options.setProperty(RandomGraphGenerator.HIERARCHY_CHANCE, utilityPage.getHierarchyChance());
        options.setProperty(RandomGraphGenerator.MAX_HIERARCHY_LEVEL,
                utilityPage.getMaximumHierarchyLevel());
        options.setProperty(RandomGraphGenerator.HIERARCHY_NODES_FACTOR,
                utilityPage.getHierarchyNodesFactor());
        options.setProperty(RandomGraphGenerator.HYPERNODE_CHANCE, utilityPage.getHypernodeChance());
        options.setProperty(RandomGraphGenerator.EDGE_DIRECTED_CHANCE,
                utilityPage.getEdgeDirectedChance());
        options.setProperty(RandomGraphGenerator.PORTS, utilityPage.getPorts());
        return options;
    }

    private void savePreferences() {
        newFilePage.savePreferences();
        typePage.savePreferences();
        switch (typePage.getGraphType()) {
        case ANY:
            anyPage.savePreferences();
            break;
        case TREE:
            treePage.savePreferences();
            break;
        case BICONNECTED:
            biconnectedPage.savePreferences();
            break;
        case TRICONNECTED:
            triconnectedPage.savePreferences();
            break;
        case ACYCLIC_NO_TRANSITIV_EDGES:
            antePage.savePreferences();
            break;
        }
        utilityPage.savePreferences();
    }
}
