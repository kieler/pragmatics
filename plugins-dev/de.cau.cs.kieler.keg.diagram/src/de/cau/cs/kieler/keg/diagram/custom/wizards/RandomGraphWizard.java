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
import java.util.Random;

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
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.keg.diagram.custom.random.IRandomGraphGenerator;
import de.cau.cs.kieler.keg.diagram.custom.random.RandomGraphGenerator;
import de.cau.cs.kieler.keg.diagram.edit.parts.NodeEditPart;
import de.cau.cs.kieler.keg.diagram.part.GraphsDiagramEditorPlugin;
import de.cau.cs.kieler.keg.diagram.part.GraphsDiagramEditorUtil;
import de.cau.cs.kieler.kiml.service.util.ProgressMonitorAdapter;

/**
 * The new-wizard for creating random KEG graphs.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class RandomGraphWizard extends Wizard implements INewWizard {

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
    /** the options page. */
    private RandomGraphOptionsPage optionsPage;

    /**
     * Creates a RandomGraphWizard.
     */
    public RandomGraphWizard() {
        super();
        setNeedsProgressMonitor(true);
        setWindowTitle(Messages.RandomGraphWizard_title);
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
        optionsPage = new RandomGraphOptionsPage();
        addPage(newFilePage);
        addPage(typePage);
        addPage(anyPage);
        addPage(treePage);
        addPage(biconnectedPage);
        addPage(triconnectedPage);
        addPage(antePage);
        addPage(optionsPage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IWizardPage getNextPage(final IWizardPage page) {
        if (page == newFilePage) {
            return typePage;
        } else if (page == typePage) {
            switch (typePage.getGraphType()) {
            case TREE:
                return treePage;
            case BICONNECTED:
                return biconnectedPage;
            case TRICONNECTED:
                return triconnectedPage;
            case ACYCLIC_NO_TRANSITIVE_EDGES:
                return antePage;
            case ANY:
            default:
                return anyPage;
            }
        } else if (page == treePage
                || page == biconnectedPage
                || page == triconnectedPage
                || page == antePage
                || page == anyPage) {
            
            return optionsPage;
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performFinish() {
        savePreferences();
        // if necessary ask the user to verify his decisions on the number of generated graphs
        if (newFilePage.getNumberOfGraphs() > SOFT_LIMIT_GRAPHS) {
            if (!askUser(Messages.RandomGraphWizard_soft_limit_graphs_message)) {
                getContainer().showPage(newFilePage);
                return false;
            }
        } else if (newFilePage.getCreateDiagramFiles()
                && newFilePage.getNumberOfGraphs() > SOFT_LIMIT_DIAGRAMS) {
            if (!askUser(Messages.RandomGraphWizard_soft_limit_diagrams_message)) {
                getContainer().showPage(newFilePage);
                return false;
            }
        } else if (newFilePage.getOpenDiagramFiles()
                && newFilePage.getNumberOfGraphs() > SOFT_LIMIT_OPEN_DIAGRAMS) {
            if (!askUser(Messages.RandomGraphWizard_soft_limit_open_diagrams_message)) {
                getContainer().showPage(newFilePage);
                return false;
            }
        }
        
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
                    new Status(IStatus.ERROR, GraphsDiagramEditorPlugin.ID,
                            Messages.RandomGraphWizard_graph_generated_failed_error,
                            exception.getCause());
            StatusManager.getManager().handle(status, StatusManager.BLOCK | StatusManager.SHOW);
        }

        return true;
    }

    private boolean askUser(final String question) {
        MessageBox messageBox =
                new MessageBox(getContainer().getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
        messageBox.setMessage(question);
        messageBox.setText(Messages.RandomGraphWizard_revise_settings_title);
        int response = messageBox.open();
        return response == SWT.YES;
    }

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
        monitor.begin(Messages.RandomGraphWizard_generating_graphs_task, numberOfGraphs);
        // retrieve options
        IPropertyHolder options = getOptions();
        final Maybe<Boolean> createDiagram = new Maybe<Boolean>();
        final Maybe<Boolean> openDiagram = new Maybe<Boolean>();
        final Maybe<Random> random = new Maybe<Random>();
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            public void run() {
                createDiagram.set(newFilePage.getCreateDiagramFiles());
                openDiagram.set(newFilePage.getOpenDiagramFiles());
                if (newFilePage.getTimeBasedSeed()) {
                    random.set(new Random());
                } else {
                    random.set(new Random(newFilePage.getRandomSeedValue()));
                }
            }
        });
        // do the generation
        try {
            if (numberOfGraphs == 1) {
                final Maybe<IFile> file = new Maybe<IFile>();
                PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                    public void run() {
                        file.set(newFilePage.createNewFile());
                    }
                });
                // generate and serialize the graph
                try {
                    generateAndSerialize(file.get(), createDiagram.get(), openDiagram.get(),
                            options, random.get(), monitor.subTask(1));
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }

            } else {
                // prepare to build filenames
                final Maybe<String> name = new Maybe<String>();
                final Maybe<String> ext = new Maybe<String>();
                final Maybe<IPath> containerPath = new Maybe<IPath>();
                PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                    public void run() {
                        name.set(newFilePage.getFileName());
                        ext.set(newFilePage.getFileExtension());
                        containerPath.set(newFilePage.getContainerFullPath());
                    }
                });
                String nameWithoutExt = 
                    name.get().substring(0, name.get().lastIndexOf(".")); //$NON-NLS-1$
                // generate the desired number of graphs
                int graphNumber = 1;
                int decimalPlaces = (int) Math.log10(numberOfGraphs) + 1;
                for (int i = 0; i < numberOfGraphs; i++) {
                    if (monitor.isCanceled()) {
                        throw new InterruptedException();
                    }
                    
                    // construct the file path
                    IFile file;
                    do {
                        int p = (int) Math.log10(graphNumber) + 1;
                        String fileName = nameWithoutExt + generateZeros(decimalPlaces - p)
                                + graphNumber + "." + ext.get();
                        IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
                        IPath path = containerPath.get().append(new Path(fileName));
                        file = workspaceRoot.getFile(path);
                        graphNumber++;
                    } while (file.exists());
                    
                    // generate and serialize the graph
                    try {
                        generateAndSerialize(file, createDiagram.get(), openDiagram.get(), options,
                                random.get(), monitor.subTask(1));
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            }
        } finally {
            monitor.done();
        }
    }
    
    /**
     * Generate a string with n zeros.
     * 
     * @param n the number of zeros
     * @return a string with n zeros
     */
    private static String generateZeros(final int n) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append('0');
        }
        return result.toString();
    }

    /**
     * Generate a random graph and serialize it to the given file.
     * 
     * @param file target file for serialization
     * @param createDiagram whether to create a diagram file
     * @param openDiagram whether to open the diagram file
     * @param options the generation options map
     * @param random the random number generator
     * @param monitor the progress monitor
     * @throws IOException if the file cannot be written
     * @throws CoreException if the file cannot be refreshed
     */
    private void generateAndSerialize(final IFile file, final boolean createDiagram,
            final boolean openDiagram, final IPropertyHolder options, final Random random,
            final IKielerProgressMonitor monitor) throws IOException, CoreException {
        monitor.begin(Messages.RandomGraphWizard_generate_and_serialize_task + file.getName(), 1);
        try {
            // generate
            IRandomGraphGenerator generator = new RandomGraphGenerator(random);
            Node graph = generator.generate(options);
            // serialize
            ResourceSet resourceSet = new ResourceSetImpl();
            Resource resource =
                    resourceSet.createResource(URI.createURI(file.getLocationURI().toString()));
            resource.getContents().add(graph);
            resource.save(Collections.EMPTY_MAP);
            file.refreshLocal(1, null);
            // create diagram file if requested
            if (createDiagram) {
                IPath path = file.getFullPath();
                IPath diagramPath = path.removeFileExtension().addFileExtension("kegdi"); //$NON-NLS-1$
                createDiagram(path, diagramPath);
                // open diagram file if requested
                if (openDiagram) {
                    openDiagram(diagramPath);
                }
            }
        } finally {
            monitor.done();
        }
    }
    
    /**
     * Initializes a diagram from the given model file.
     * 
     * @param modelFile the model file to create a diagram for.
     * @param diagramFile path of the diagram file to create.
     * @throws IOException if anything goes wrong.
     */
    private void createDiagram(final IPath modelFile, final IPath diagramFile) throws IOException {
        closeDiagram(diagramFile);
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
    
    /**
     * Closes the given diagram file if it is open in any editors.
     * 
     * @param diagramPath the diagram file to close.
     */
    private void closeDiagram(final IPath diagramPath) {
        final IFile diagramFile = ResourcesPlugin.getWorkspace().getRoot().getFile(diagramPath);
        if (diagramFile.exists()) {
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    // close all editors which have the diagram file opened
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
     * Opens the given diagram file.
     * 
     * @param diagramPath path to the diagram file that is to be opened.
     */
    private void openDiagram(final IPath diagramPath) {
        final IFile diagramFile = ResourcesPlugin.getWorkspace().getRoot().getFile(diagramPath);
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            public void run() {
                IWorkbenchPage page =
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                // open the diagram file in an editor
                IEditorDescriptor editorDescriptor = IDE.getDefaultEditor(diagramFile);
                if (editorDescriptor == null || editorDescriptor.isOpenExternal()) {
                    // if no editor to open the diagram is present ignore it
                    return;
                }
                try {
                    IDE.openEditor(page, diagramFile, editorDescriptor.getId(), true);
                } catch (PartInitException e) {
                    // opening the diagram failed but as it is optional it can be safely ignored
                }
            }
        });
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
        
        // Graph-specific options
        switch (typePage.getGraphType()) {
        case ANY:
            options.setProperty(RandomGraphGenerator.NUMBER_OF_NODES_MIN,
                    anyPage.getMinNumberOfNodes());
            options.setProperty(RandomGraphGenerator.NUMBER_OF_NODES_MAX,
                    anyPage.getMaxNumberOfNodes());
            options.setProperty(RandomGraphGenerator.EDGE_DETERMINATION,
                    anyPage.getEdgeDetermination());
            options.setProperty(RandomGraphGenerator.NUMBER_OF_EDGES,
                    anyPage.getNumberOfEdges());
            options.setProperty(RandomGraphGenerator.EDGES_VARIANCE,
                    anyPage.getEdgesVariance());
            options.setProperty(RandomGraphGenerator.EDGES_RELATIVE,
                    anyPage.getRelativeNumberOfEdges());
            options.setProperty(RandomGraphGenerator.EDGES_REL_VARIANCE,
                    anyPage.getRelativeEdgesVariance());
            options.setProperty(RandomGraphGenerator.DENSITY,
                    anyPage.getDensity());
            options.setProperty(RandomGraphGenerator.DENSITY_VARIANCE,
                    anyPage.getDensityVariance());
            options.setProperty(RandomGraphGenerator.MIN_OUTGOING_EDGES,
                    anyPage.getMinOutgoingEdges());
            options.setProperty(RandomGraphGenerator.MAX_OUTGOING_EDGES,
                    anyPage.getMaxOutgoingEdges());
            options.setProperty(RandomGraphGenerator.SELF_LOOPS,
                    anyPage.getSelfLoops());
            options.setProperty(RandomGraphGenerator.MULTI_EDGES,
                    anyPage.getMultiEdges());
            options.setProperty(RandomGraphGenerator.CYCLES,
                    anyPage.getCycles());
            options.setProperty(RandomGraphGenerator.ISOLATED_NODES,
                    anyPage.getIsolatedNodes());
            break;
        case TREE:
            options.setProperty(RandomGraphGenerator.NUMBER_OF_NODES,
                    treePage.getNumberOfNodes());
            options.setProperty(RandomGraphGenerator.MAX_DEGREE,
                    treePage.getMaxDegree());
            options.setProperty(RandomGraphGenerator.MAX_WIDTH,
                    treePage.getMaxWidth());
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
        case ACYCLIC_NO_TRANSITIVE_EDGES:
            options.setProperty(RandomGraphGenerator.NUMBER_OF_NODES,
                    antePage.getNumberOfNodes());
            options.setProperty(RandomGraphGenerator.NUMBER_OF_EDGES,
                    antePage.getNumberOfEdges());
            options.setProperty(RandomGraphGenerator.PLANAR,
                    antePage.getPlanar());
            break;
        }
        
        // Common options
        if (optionsPage.isHierarchyEnabled()) {
            options.setProperty(RandomGraphGenerator.HIERARCHY_CHANCE, optionsPage.getHierarchyChance());
        }
        options.setProperty(RandomGraphGenerator.MAX_HIERARCHY_LEVEL,
                optionsPage.getMaximumHierarchyLevel());
        options.setProperty(RandomGraphGenerator.HIERARCHY_NODES_FACTOR,
                optionsPage.getHierarchyNodesFactor());
        options.setProperty(RandomGraphGenerator.HYPERNODE_CHANCE,
                optionsPage.getHypernodeChance());
        options.setProperty(RandomGraphGenerator.EDGE_DIRECTED_CHANCE,
                optionsPage.getEdgeDirectedChance());
        options.setProperty(RandomGraphGenerator.PORTS,
                optionsPage.getPorts());
        options.setProperty(RandomGraphGenerator.USE_EXISTING_PORTS_CHANCE,
                optionsPage.getUseExistingPortsChance());
        options.setProperty(RandomGraphGenerator.CROSS_HIERARCHY_EDGES,
                optionsPage.getCrossHierarchyEdges());
        
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
        case ACYCLIC_NO_TRANSITIVE_EDGES:
            antePage.savePreferences();
            break;
        }
        optionsPage.savePreferences();
    }
}
