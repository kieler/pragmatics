/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.ptolemy.attachmenteval.editors.attachment;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.elk.core.klayoutdata.KLayoutData;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.service.util.ProgressMonitorAdapter;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.core.util.Maybe;
import org.eclipse.elk.core.util.WrappedException;
import org.eclipse.elk.graph.KNode;
import org.eclipse.elk.graph.properties.MapPropertyHolder;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.ui.views.navigator.ResourceComparator;
import org.ptolemy.moml.util.MomlResourceFactoryImpl;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.grana.AnalysisData;
import de.cau.cs.kieler.grana.AnalysisService;
import de.cau.cs.kieler.grana.ui.AnalysisSelectionDialog;
import de.cau.cs.kieler.grana.ui.batch.Batch;
import de.cau.cs.kieler.grana.ui.batch.BatchJob;
import de.cau.cs.kieler.grana.ui.batch.BatchResult;
import de.cau.cs.kieler.grana.ui.batch.CSVResultSerializer;
import de.cau.cs.kieler.grana.ui.batch.IBatchResultSerializer;
import de.cau.cs.kieler.grana.ui.batch.IKGraphProvider;
import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.krendering.KText;
import de.cau.cs.kieler.klighd.krendering.SimpleUpdateStrategy;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
import de.cau.cs.kieler.ptolemy.attachmenteval.AttachmentData;
import de.cau.cs.kieler.ptolemy.attachmenteval.DataEvaluator;
import de.cau.cs.kieler.ptolemy.attachmenteval.PtolemyAttachmentEvalPlugin;
import de.cau.cs.kieler.ptolemy.attachmenteval.editors.attachment.analyses.IAttachmentAnalysis;
import de.cau.cs.kieler.ptolemy.klighd.PtolemyDiagramSynthesis;

/**
 * An editor that allows defining correct comment attachments for each Ptolemy model in a given
 * folder of the workspace. The file is structured as follows:
 * <pre>
 *   models folder as portable string
 *   selected model file 1
 *   selected model file 2
 *   ...
 *   selected model file n
 *   -----
 *   model file 1
 *   commentUriFragment1,actorUriFragment1
 *   commentUriFragment2,actorUriFragment2
 *   ...
 *   commentUriFragmentn,actorUriFragmentn
 *   
 *   model file 2
 *   commentUriFragment1,actorUriFragment1
 *   commentUriFragment2,actorUriFragment2
 *   ...
 *   commentUriFragmentn,actorUriFragmentn
 *   
 *   ...
 *   
 *   model file n
 *   commentUriFragment1,actorUriFragment1
 *   commentUriFragment2,actorUriFragment2
 *   ...
 *   commentUriFragmentn,actorUriFragmentn
 * </pre>
 * 
 * @author cds
 */
public final class CommentAttachmentEditor extends EditorPart implements IDiagramWorkbenchPart {
    
    /** The current model file. */
    private IFile currentModelFile = null;
    
    /** The model currently displayed in the KLighD viewer. */
    private EObject currentModel = null;
    
    /** EMF resource the current model was loaded into. */
    private Resource currentResource = null;
    
    /** The current view context for the KLighD viewer. */
    private ViewContext klighdViewContext = null;
    
    /** Whether the editor has unsaved changes. */
    private boolean dirty = false;
    
    /** Attachment data. */
    private AttachmentData attachmentData = null;
    
    /**
     * The raw associations for the currently displayed file. These come directly from the attachment
     * data. Thus, changes here are immediately reflect in the attachment data.
     */
    private Map<String, String> currentRawAssociations = null;
    
    /**
     * Associations from comment KNodes to actor KNodes in the currently opened model, if any.
     * 
     * This map is always updated when an association is changed.
     */
    private Map<KNode, KNode> currentAssociations = null;
    
    /* UI Controls */
    LocalResourceManager resources = null;
    private FormToolkit formToolkit = null;
    private Form form = null;
    private ContainerCheckedTreeViewer modelTreeViewer = null;
    private ContextViewer klighdViewer = null;
    private TreeViewer attachmentsTreeViewer = null;
    
    
    /**
     * Creates a new instance.
     */
    public CommentAttachmentEditor() {
        super();
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // GETTERS / SETTERS
    
    /**
     * @return the currentAssociations
     */
    public Map<KNode, KNode> getCurrentAssociations() {
        return currentAssociations;
    }


    /**
     * @return the klighdViewContext
     */
    public ViewContext getKlighdViewContext() {
        return klighdViewContext;
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // USER INTERFACE

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
        if (!(input instanceof IFileEditorInput)) {
            throw new PartInitException("Editor must be opened on a file!");
        }
        
        setSite(site);
        setInput(input);
        registerResourceChangeListener();
        
        try {
            doLoad();
        } catch (Exception e) {
            throw new PartInitException("Unable to load input file.", e);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        unregisterResourceChangeListener();
        formToolkit.dispose();
        
        super.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {
        resources = new LocalResourceManager(JFaceResources.getResources(), parent);
        
        // Set the part name
        setPartName(getEditorInput().getName());
        
        // Create the part contents
        formToolkit = new FormToolkit(parent.getDisplay());
        form = formToolkit.createForm(parent);
        form.setText("Ptolemy Comment Attachment");
        formToolkit.decorateFormHeading(form);
        
        GridLayoutFactory.fillDefaults().extendedMargins(5, 5, 10, 5).applyTo(form.getBody());
        
        // Create the main sash form
        SashForm sashForm = new SashForm(form.getBody(), SWT.HORIZONTAL);
        sashForm.setSashWidth(10);
        
        GridDataFactory.fillDefaults().grab(true, true).applyTo(sashForm);
        
        // Create the different form sections
        createModelsSection(formToolkit, sashForm);
        createKlighdSection(formToolkit, sashForm);
        createAttachmentsSection(formToolkit, sashForm);
        
        sashForm.setWeights(new int[] {1, 2, 1});
        
        // Add toolbar buttons to the form
        IToolBarManager tbm = form.getToolBarManager();
        
        Action setFolderAction = new Action() {
            @Override
            public void run() {
                setModelsFolder();
            }
        };
        setFolderAction.setImageDescriptor(PtolemyAttachmentEvalPlugin.getImageDescriptor(
                "icons/setfolder.gif"));
        setFolderAction.setToolTipText("Set Models Folder");
        tbm.add(setFolderAction);
        
        Action runAlgorithmAction = new Action() {
            @Override
            public void run() {
                runAlgorithm();
            }
        };
        runAlgorithmAction.setImageDescriptor(PtolemyAttachmentEvalPlugin.getImageDescriptor(
                "icons/ant.gif"));
        runAlgorithmAction.setToolTipText("Run Attachment Heuristic");
        tbm.add(runAlgorithmAction);
        
        Action runStatisticsAction = new Action() {
            @Override
            public void run() {
                runStatistics();
            }
        };
        runStatisticsAction.setImageDescriptor(PtolemyAttachmentEvalPlugin.getImageDescriptor(
                "icons/count.gif"));
        runStatisticsAction.setToolTipText("Run Statistics");
        tbm.add(runStatisticsAction);
        
        Action runGraphAnalysesAction = new Action() {
            @Override
            public void run() {
                runGraphAnalysisOnDiagrams();
            }
        };
        runGraphAnalysesAction.setImageDescriptor(PtolemyAttachmentEvalPlugin.getImageDescriptor(
                "icons/analyzediagrams.gif"));
        runGraphAnalysesAction.setToolTipText("Analyze Selected Diagrams");
        tbm.add(runGraphAnalysesAction);
        
        Action compareAttachmentsAction = new Action() {
            @Override
            public void run() {
                runAttachmentComparison();
            }
        };
        compareAttachmentsAction.setImageDescriptor(PtolemyAttachmentEvalPlugin.getImageDescriptor(
                "icons/compare.gif"));
        compareAttachmentsAction.setToolTipText("Compare Attachment Data With Reference Attachment...");
        tbm.add(compareAttachmentsAction);
        
        tbm.update(true);
    }

    /**
     * Creates the models selection section.
     * 
     * @param toolkit the forms toolkit to use.
     * @param parent the new section's parent control.
     * @return the created section.
     */
    private Section createModelsSection(final FormToolkit toolkit, final Composite parent) {
        Section section = toolkit.createSection(parent, Section.TITLE_BAR | Section.DESCRIPTION);
        section.setText("Models");
        section.setDescription("Select the models to be included in the evaluation.");
        
        // Section tool bar
        ToolBar toolBar = new ToolBar(section, SWT.FLAT | SWT.HORIZONTAL);
        section.setTextClient(toolBar);
        
        ToolItem selectAllItem = new ToolItem(toolBar, SWT.PUSH);
        selectAllItem.setImage(resources.createImage(
                PtolemyAttachmentEvalPlugin.getImageDescriptor("icons/selectall.gif")));
        selectAllItem.setToolTipText("Select All");
        selectAllItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                selectAllModels();
            }
        });
        
        ToolItem unselectAllItem = new ToolItem(toolBar, SWT.PUSH);
        unselectAllItem.setImage(resources.createImage(
                PtolemyAttachmentEvalPlugin.getImageDescriptor("icons/unselectall.gif")));
        unselectAllItem.setToolTipText("Unselect All");
        unselectAllItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                unselectAllModels();
            }
        });
        
        new ToolItem(toolBar, SWT.SEPARATOR);
        
        ToolItem expandAllItem = new ToolItem(toolBar, SWT.PUSH);
        expandAllItem.setImage(resources.createImage(
                PtolemyAttachmentEvalPlugin.getImageDescriptor("icons/expandall.gif")));
        expandAllItem.setToolTipText("Expand All");
        expandAllItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                expandAllModels();
            }
        });
        
        ToolItem collapseAllItem = new ToolItem(toolBar, SWT.PUSH);
        collapseAllItem.setImage(resources.createImage(
                PtolemyAttachmentEvalPlugin.getImageDescriptor("icons/collapseall.png")));
        collapseAllItem.setToolTipText("Collapse All");
        collapseAllItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                collapseAllModels();
            }
        });
        
        // Create client composite
        Composite composite = toolkit.createComposite(section);
        GridLayoutFactory.fillDefaults().extendedMargins(1, 1, 5, 1).applyTo(composite);
        section.setClient(composite);
        
        // Create the tree the section is to display
        Tree modelTree = toolkit.createTree(composite, SWT.SINGLE | SWT.CHECK | SWT.FULL_SELECTION);
        GridDataFactory.fillDefaults().grab(true,  true).applyTo(modelTree);
        
        modelTreeViewer = new ContainerCheckedTreeViewer(modelTree);
        modelTreeViewer.setContentProvider(new BaseWorkbenchContentProvider());
        modelTreeViewer.setLabelProvider(WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider());
        modelTreeViewer.setComparator(new ResourceComparator(ResourceComparator.NAME));
        modelTreeViewer.setFilters(new ViewerFilter[] {new ViewerFilter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                if (element instanceof IFile) {
                    String extension =  ((IFile) element).getFileExtension();
                    return extension.equalsIgnoreCase("xml") || extension.equalsIgnoreCase("moml");
                } else {
                    return true;
                }
            }
        }});
        modelTreeViewer.setInput(attachmentData.getModelsFolder());
        modelTreeViewer.expandAll();
        
        toolkit.paintBordersFor(composite);
        
        // Restore check state of files
        for (String fileName : attachmentData.getSelectedFiles()) {
            // Check the file in the model tree viewer
            IResource file = attachmentData.getModelsFolder().findMember(fileName);
            if (file != null) {
                modelTreeViewer.setChecked(file, true);
            }
        }
        
        // Event Listeners
        modelTreeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
                Object element = ((ITreeSelection) event.getSelection()).getFirstElement();
                
                // We can only display files
                if (element instanceof IFile) {
                    showModel((IFile) element, false);
                } else {
                    showModel(null, false);
                }
            }
        });
        modelTreeViewer.addCheckStateListener(new ICheckStateListener() {
            public void checkStateChanged(CheckStateChangedEvent event) {
                setDirty(true);
            }
        });
        
        return section;
    }
    
    /**
     * Creates the KLighD view section.
     * 
     * @param toolkit the forms toolkit to use.
     * @param parent the new section's parent control.
     * @return the created section.
     */
    private Section createKlighdSection(final FormToolkit toolkit, final Composite parent) {
        Section section = toolkit.createSection(parent, Section.TITLE_BAR | Section.DESCRIPTION);
        section.setText("View");
        section.setDescription("Here's a beautiful graphical view of the model.");
        
        // Create client composite
        Composite composite = toolkit.createComposite(section);
        GridLayoutFactory.fillDefaults().extendedMargins(1, 1, 5, 1).applyTo(composite);
        section.setClient(composite);
        
        // Create another composite that encapsulates the KLighD viewer (the KLighD viewer will happily
        // set another layout manager on this one, which is why we cannot use our main composite
        // directly)
        Composite klighdCapsule = toolkit.createComposite(composite, SWT.BORDER);
        GridDataFactory.fillDefaults().grab(true,  true).applyTo(klighdCapsule);
        klighdCapsule.setLayout(new FillLayout());
        
        // Create the KLighD viewer the section is to display
        klighdViewer = new ContextViewer(klighdCapsule);
        klighdViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                if (!selection.isEmpty()) {
                    Object selectedElement = selection.getFirstElement();
                    
                    // We only want to select comment nodes in the tree; if the selection is a KText,
                    // it might be contained in a comment node, so first extract that
                    if (selectedElement instanceof KText) {
                        selectedElement = ((KText) selectedElement).eContainer().eContainer();
                    }
                    
                    if (selectedElement instanceof KNode) {
                        if (((KNode) selectedElement).getData(KLayoutData.class).getProperty(
                                CoreOptions.COMMENT_BOX)) {

                            // Try selecting the object in the attachment tree
                            attachmentsTreeViewer.setSelection(
                                    new StructuredSelection(selectedElement), true);
                        }
                    }
                }
            }
        });
        
        toolkit.paintBordersFor(composite);
        return section;
    }
    
    /**
     * Creates the attachment editing section.
     * 
     * @param toolkit the forms toolkit to use.
     * @param parent the new section's parent control.
     * @return the created section.
     */
    private Section createAttachmentsSection(final FormToolkit toolkit, final Composite parent) {
        Section section = toolkit.createSection(parent, Section.TITLE_BAR | Section.DESCRIPTION);
        section.setText("Comment Attachments");
        section.setDescription("Define the correct set of attachments to evaluate against.");
        
        // Create client composite
        Composite composite = toolkit.createComposite(section);
        GridLayoutFactory.fillDefaults().extendedMargins(1, 1, 5, 1).applyTo(composite);
        section.setClient(composite);
        
        // Section tool bar
        ToolBar toolBar = new ToolBar(section, SWT.FLAT | SWT.HORIZONTAL);
        section.setTextClient(toolBar);
        
        ToolItem setAttachmentItem = new ToolItem(toolBar, SWT.PUSH);
        setAttachmentItem.setImage(resources.createImage(
                PtolemyAttachmentEvalPlugin.getImageDescriptor("icons/setassociation.gif")));
        setAttachmentItem.setToolTipText("Set Attachment");
        setAttachmentItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                setAttachment();
            }
        });
        
        ToolItem removeAttachmentItem = new ToolItem(toolBar, SWT.PUSH);
        removeAttachmentItem.setImage(resources.createImage(
                PtolemyAttachmentEvalPlugin.getImageDescriptor("icons/remove.gif")));
        removeAttachmentItem.setToolTipText("Clear Attachment");
        removeAttachmentItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                clearAttachment();
            }
        });
        
        // Create the tree the section is to display
        Tree tree = toolkit.createTree(composite, SWT.SINGLE | SWT.FULL_SELECTION);
        tree.setLinesVisible(true);
        tree.setHeaderVisible(true);
        GridDataFactory.fillDefaults().grab(true,  true).applyTo(tree);
        
        attachmentsTreeViewer = new TreeViewer(tree);
        attachmentsTreeViewer.setContentProvider(new AttachmentContentProvider());
        attachmentsTreeViewer.setLabelProvider(new AttachmentLabelProvider(this));
        
        TreeColumn commentsColumn = new TreeColumn(tree, SWT.LEFT);
        commentsColumn.setText("Comment");
        commentsColumn.setWidth(150);
        
        TreeColumn attachmentColumn = new TreeColumn(tree, SWT.LEFT);
        attachmentColumn.setText("Attachment");
        attachmentColumn.setWidth(150);
        
        toolkit.paintBordersFor(composite);
        return section;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        form.setFocus();
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // LOAD, SAVE, AND DIRTY STATE
    
    /**
     * Loads the contents of the given file.
     * 
     * @throws Exception if something goes wrong reading the input file.
     */
    private void doLoad() throws Exception {
        IFileEditorInput editorInput = (IFileEditorInput) getEditorInput();
        IFile editorInputFile = editorInput.getFile();
        attachmentData = AttachmentData.fromFile(editorInputFile);
        setDirty(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDirty() {
        return dirty;
    }
    
    /**
     * Set the dirty status of the editor.
     * 
     * @param dirty the new dirty status
     */
    private void setDirty(final boolean dirty) {
        this.dirty = dirty;
        firePropertyChange(IEditorPart.PROP_DIRTY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doSave(final IProgressMonitor monitor) {
        // Update selected files set
        attachmentData.getSelectedFiles().clear();
        for (Object checkedElement : modelTreeViewer.getCheckedElements()) {
            if (checkedElement instanceof IFile) {
                attachmentData.getSelectedFiles().add(getRelativeModelPath((IFile) checkedElement));
            }
        }
        
        IFileEditorInput editorInput = (IFileEditorInput) getEditorInput();
        IFile editorInputFile = editorInput.getFile();
        
        try {
            // Write the stuff out
            attachmentData.save(editorInputFile, monitor);
            
            // We succeeded!
            setDirty(false);
        } catch (Exception e) {
            
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSaveAsAllowed() {
        // I don't care to write the code necessary to support "Save As".
        // On a side note, would a body design editor provide a "Save Ass" feature?
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doSaveAs() {
        throw new UnsupportedOperationException();
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // MODEL AND STUFF HANDLING (especially stuff)
    
    /**
     * Returns the path of the given model file relative to the models folder. This path is used as
     * an index into the raw association maps.
     * 
     * @param modelFile the model file.
     * @return the relative path.
     */
    private String getRelativeModelPath(final IFile modelFile) {
        return modelFile.getFullPath().makeRelativeTo(
                attachmentData.getModelsFolder().getFullPath()).toPortableString();
    }
    
    /**
     * Sets the Ptolemy models folder.
     * 
     * @param newFolder the new Ptolemy models folder.
     */
    private void setModelsFolder(final IContainer newFolder) {
        if (newFolder == null) {
            if (attachmentData.getModelsFolder() != null) {
                attachmentData.setModelsFolder(newFolder);
                modelTreeViewer.setInput(null);
                setDirty(true);
            }
        } else if (!newFolder.equals(attachmentData.getModelsFolder())) {
            attachmentData.setModelsFolder(newFolder);
            modelTreeViewer.setInput(newFolder);
            setDirty(true);
        }
    }
    
    private void loadModel(final IFile modelFile, final boolean enableHeuristic) {
        // Try loading the model
        if (modelFile != null) {
            URI uri = URI.createPlatformResourceURI(modelFile.getFullPath().toString(), false);
            Resource resource = null;
            try {
                Map<String, Boolean> parserFeatures = Maps.newHashMap();
                parserFeatures.put(
                        "http://xml.org/sax/features/validation", Boolean.FALSE);
                parserFeatures.put(
                        "http://apache.org/xml/features/nonvalidating/load-dtd-grammar", Boolean.FALSE);
                parserFeatures.put(
                        "http://apache.org/xml/features/nonvalidating/load-external-dtd", Boolean.FALSE);
                
                ResourceSet resourceSet = new ResourceSetImpl();
                resourceSet.getLoadOptions().put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, true);
                resourceSet.getLoadOptions().put(XMLResource.OPTION_PARSER_FEATURES, parserFeatures);
                resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                        .put("xml", new MomlResourceFactoryImpl());
                
                resource = resourceSet.getResource(uri, true);
            } catch (WrappedException exception) {
                currentModelFile = null;
                currentModel = null;
                currentResource = null;
            }
            
            if (resource.getContents().isEmpty()) {
                currentModelFile = null;
                currentModel = null;
                currentResource = null;
            }
            // default behavior: get the first element in the resource
            currentModelFile = modelFile;
            currentModel = resource.getContents().get(0);
            currentResource = resource;
        } else {
            currentModelFile = null;
            currentModel = null;
            currentResource = null;
        }
        
        if (currentModel != null) {
            // Create a new view context for the model
            MapPropertyHolder klighdProperties = new MapPropertyHolder();
            klighdProperties.setProperty(KlighdSynthesisProperties.REQUESTED_UPDATE_STRATEGY,
                    SimpleUpdateStrategy.ID);
            
            klighdViewContext = new ViewContext(this, currentModel).configure(klighdProperties);
            
            // Disable or enable the comment attachment heuristic
            klighdViewContext.configureOption(
                    PtolemyDiagramSynthesis.COMMENT_ATTACHMENT_HEURISTIC,
                    enableHeuristic);
            
            klighdViewContext.update(currentModel);
        } else {
            klighdViewContext = null;
        }
        
        // Update the attachment maps
        updateAttachmentMaps();
        if (klighdViewContext != null) {
            extractExplicitAttachments(klighdViewContext.getViewModel());
        }
    }
    
    /**
     * Loads and displays the given model in the KLighD viewer.
     * 
     * @param modelFile file containing the model to be shown, or {@code null} to show nothing.
     * @param enableHeuristic {@code true} if the heuristic should be enabled when displaying the model,
     *                        {@code false} otherwise. Should only really be {@code true} if the model
     *                        is displayed during an algorithm run.
     */
    private void showModel(final IFile modelFile, final boolean enableHeuristic) {
        // Don't load anything if the new model file is identical to the current one
        if (modelFile == currentModelFile) {
            return;
        }
        
        loadModel(modelFile, enableHeuristic);
        
        if (klighdViewContext != null) {
            klighdViewer.setModel(klighdViewContext);
            LightDiagramServices.layoutDiagram(klighdViewContext, false);
        } else {
            klighdViewer.setModel("No valid model selected.", false);
        }

        // Update the input of the attachment viewer
        attachmentsTreeViewer.setInput(klighdViewContext);
        attachmentsTreeViewer.expandAll();
    }
    
    /**
     * Updates the current association maps for the given model that is assumed to have just been
     * successfully loaded.
     */
    private void updateAttachmentMaps() {
        // We might need to null everything
        if (currentModelFile == null) {
            currentAssociations = null;
            currentRawAssociations = null;
            return;
        }
        
        // Retrieve the model file path relative to the models folder
        String relativePath = getRelativeModelPath(currentModelFile);
        
        currentRawAssociations = attachmentData.getAssociations().get(relativePath);
        if (currentRawAssociations == null) {
            currentRawAssociations = Maps.newHashMap();
            attachmentData.getAssociations().put(relativePath, currentRawAssociations);
        }
        
        // Build the concrete associations
        currentAssociations = Maps.newHashMap();
        
        for (Entry<String, String> rawAssociation : currentRawAssociations.entrySet()) {
            // Find the Ptolemy source objects
            EObject commentSource = currentResource.getEObject(rawAssociation.getKey());
            EObject actorSource = currentResource.getEObject(rawAssociation.getValue());
            if (commentSource == null || actorSource == null) {
                continue;
            }
            
            // Find the KNodes
            KNode commentNode = klighdViewContext.getTargetElement(commentSource, KNode.class);
            KNode actorNode = klighdViewContext.getTargetElement(actorSource, KNode.class);
            if (commentNode == null || actorNode == null) {
                continue;
            }
            
            currentAssociations.put(commentNode, actorNode);
        }
    }
    
    /**
     * Finds all the explicit attachments between the given node's children and makes sure they appear
     * in the attachment maps. The attachments either are already in the Ptolemy model or were
     * determined by the attachment heuristic, depending on whether the latter was active when
     * displaying the model.
     */
    private void extractExplicitAttachments(final KNode graph) {
        for (KNode child : graph.getChildren()) {
            // Recurse into children
            if (!child.getChildren().isEmpty()) {
                extractExplicitAttachments(child);
            }
            
            boolean isComment = child.getData(KLayoutData.class).getProperty(CoreOptions.COMMENT_BOX);
            if (isComment) {
                // Check if the comment is attached to something (this assumes that comment nodes only
                // have outgoing edges)
                if (!child.getOutgoingEdges().isEmpty()) {
                    setAttachment(child, child.getOutgoingEdges().get(0).getTarget());
                }
            }
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // TOOL BAR BUTTON EVENT HANDLERS
    
    /**
     * Opens a dialog for setting the folder that contains the relevant Ptolemy models.
     */
    private void setModelsFolder() {
        ContainerSelectionDialog dialog = new ContainerSelectionDialog(
                this.getSite().getShell(),
                attachmentData.getModelsFolder(),
                false,
                "Select the Ptolemy models container, yo!");
        dialog.showClosedProjects(false);
        
        if (dialog.open() == Window.OK) {
            Object result[] = dialog.getResult();
            if (result != null && result.length == 1) {
                IPath newContainerPath = (IPath) result[0];
                IResource newContainerResource =
                        ResourcesPlugin.getWorkspace().getRoot().findMember(newContainerPath);
                
                if (newContainerResource != null && newContainerResource instanceof IContainer) {
                    setModelsFolder((IContainer) newContainerResource);
                }
            }
        }
    }
    
    /**
     * Selects everything in the models tree.
     */
    private void selectAllModels() {
        modelTreeViewer.setAllChecked(true);
        setDirty(true);
    }
    
    /**
     * Unselects everything in the models tree.
     */
    private void unselectAllModels() {
        modelTreeViewer.setAllChecked(false);
        setDirty(true);
    }
    
    /**
     * Expands everything in the models tree.
     */
    private void expandAllModels() {
        modelTreeViewer.expandAll();
    }
    
    /**
     * Collapses everything in the models tree.
     */
    private void collapseAllModels() {
        modelTreeViewer.collapseAll();
    }
    
    /**
     * Returns the comment node currently selected in the attachment editor, if any.
     * 
     * @return the currently selected comment node or {@code null} if there is none.
     */
    private KNode getCurrentlySelectedCommentNode() {
        if (klighdViewContext == null || currentAssociations == null
                || currentRawAssociations == null || currentResource == null) {
            
            return null;
        }
        
        // Retrieve the currently selected comment
        IStructuredSelection commentSelection =
                (IStructuredSelection) attachmentsTreeViewer.getSelection();
        if (commentSelection.isEmpty()) {
            // Nothing selected
            return null;
        }
        
        // Check if the current selection is a comment node
        KNode commentNode = (KNode) commentSelection.getFirstElement();
        if (!commentNode.getData(KLayoutData.class).getProperty(CoreOptions.COMMENT_BOX)) {
            // Selection is not a comment
            return null;
        }
        
        return commentNode;
    }
    
    /**
     * Creates an attachment between the comment currently selected in the attachments tree view and the
     * actor currently selected in the KLighD view, provided they have the same parent node.
     */
    private void setAttachment() {
        // Get the current comment node
        KNode commentNode = getCurrentlySelectedCommentNode();
        if (commentNode == null) {
            MessageDialog.openError(
                    this.getEditorSite().getShell(),
                    "No Comment Selected",
                    "No comment node is currently selected.");
            return;
        }
        
        // Get the currently selected actor in the KLighD view
        IStructuredSelection klighdSelection = (IStructuredSelection) klighdViewer.getSelection();
        if (klighdSelection.isEmpty() || !(klighdSelection.getFirstElement() instanceof KNode)) {
            MessageDialog.openError(
                    this.getEditorSite().getShell(),
                    "No Actor Selected",
                    "No actor node is currently selected in the model.");
            return;
        }
        KNode actorNode = (KNode) klighdSelection.getFirstElement();
        
        setAttachment(commentNode, actorNode);
    }


    /**
     * Adds an attachment between the given comment and actor node if that attachment doesn't already
     * exist and if the two nodes are siblings.
     * 
     * @param commentNode the comment node to attach.
     * @param actorNode the actor node to attach the comment node to.
     */
    private void setAttachment(KNode commentNode, KNode actorNode) {
        // Check if the attachment already exists
        if (currentAssociations.get(commentNode) == actorNode) {
            return;
        }
        
        // Ensure that the two nodes are siblings
        if (commentNode.getParent() != actorNode.getParent()) {
            MessageDialog.openError(
                    this.getEditorSite().getShell(),
                    "Different Parents",
                    "The comment node and the actor must have the same parent node.");
            return;
        }
        
        // Retrieve the URI fragments of the two nodes in the source model
        EObject commentElement = (EObject) klighdViewContext.getSourceElement(commentNode);
        EObject actorElement = (EObject) klighdViewContext.getSourceElement(actorNode);
        
        String commentURIFragment = currentResource.getURIFragment(commentElement);
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        String actorURIFragment = currentResource.getURIFragment(actorElement);
        
        // Add the association
        currentAssociations.put(commentNode, actorNode);
        currentRawAssociations.put(commentURIFragment, actorURIFragment);
        
        // Update the tree viewer
        attachmentsTreeViewer.update(commentNode, null);
        
        this.setDirty(true);
    }
    
    /**
     * Clears the attachment of the comment currently selected in the attachments tree view.
     */
    private void clearAttachment() {
        // Get the current comment node
        KNode commentNode = getCurrentlySelectedCommentNode();
        if (commentNode == null) {
            MessageDialog.openError(
                    this.getEditorSite().getShell(),
                    "No Comment Selected",
                    "No comment node is currently selected in the attachment tree.");
            return;
        }
        
        // Retrieve the Ptolemy model element of the comment and its fragment URI
        EObject commentSource = (EObject) klighdViewContext.getSourceElement(commentNode);
        String commentSourceFragmentURI = currentResource.getURIFragment(commentSource);
        
        // Remove the comment from the attachment maps
        currentAssociations.remove(commentNode);
        currentRawAssociations.remove(commentSourceFragmentURI);
        
        // Update the tree viewer
        attachmentsTreeViewer.update(commentNode, null);
        
        this.setDirty(true);
    }
    
    /**
     * Reset all comment attachments. Then, run through the list of selected model files, load each one
     * of them with the comment attachment heuristic enabled and then extract the attachments.
     */
    private void runAlgorithm() {
        // Reset attachments and retrieve selected elements
        attachmentData.getAssociations().clear();
        setDirty(true);
        
        final Object[] checkedElements = modelTreeViewer.getCheckedElements();
        
        // A control for accessing the display
        final Control control = modelTreeViewer.getControl();
        
        IRunnableWithProgress algorithmJob = new IRunnableWithProgress() {
            public void run(final IProgressMonitor monitor) {
                monitor.beginTask("Running attachment heuristic...", checkedElements.length);
                
                // Iterate over each selected model file
                for (Object selectedObject : checkedElements) {
                    if (selectedObject instanceof IFile) {
                        final IFile selectedFile = (IFile) selectedObject;
                        monitor.subTask(selectedFile.getName());
                        
                        // Load the model
                        control.getDisplay().syncExec(new Runnable() {
                            public void run() {
                                loadModel(selectedFile, true);
                            }
                        });
                    }
                    
                    monitor.worked(1);
                }
                
                monitor.done();
            }
        };
        
        IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
        try {
            progressService.busyCursorWhile(algorithmJob);
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * Updates the annotations count on each model.
     */
    private void runStatistics() {
        // Reset attachments and retrieve selected elements
        attachmentData.getAnnotationCounts().clear();
        final Object[] checkedElements = modelTreeViewer.getCheckedElements();
        
        // The analysis to run on the model files, in addition to the annotation counting which is
        // always done
        final IAttachmentAnalysis analysis = null;
        
        // A control for accessing the display
        final Control control = modelTreeViewer.getControl();
        
        IRunnableWithProgress algorithmJob = new IRunnableWithProgress() {
            public void run(final IProgressMonitor monitor) {
                monitor.beginTask("Counting annotations...", checkedElements.length);
                
                // Iterate over each selected model file
                for (Object selectedObject : checkedElements) {
                    if (monitor.isCanceled()) {
                        break;
                    }
                    
                    if (selectedObject instanceof IFile) {
                        final IFile selectedFile = (IFile) selectedObject;
                        monitor.subTask(selectedFile.getName());
                        
                        // Load the model
                        control.getDisplay().syncExec(new Runnable() {
                            public void run() {
                                loadModel(selectedFile, false);
                            }
                        });
                        
                        // Run statistics on the model in non-UI thread
                        if (klighdViewContext != null) {
                            // Run analysis for this model
                            if (analysis != null) {
                                analysis.process(klighdViewContext.getViewModel(),
                                        getRelativeModelPath(selectedFile),
                                        CommentAttachmentEditor.this);
                            }
                            
                            // Count annotations
                            int count = countAnnotations(klighdViewContext.getViewModel());
                            attachmentData.getAnnotationCounts().put(
                                    getRelativeModelPath(selectedFile),
                                    count);
                        }
                    }
                    
                    monitor.worked(1);
                }
                
                // Tell the analysis to finish
                if (analysis != null) {
                    analysis.finish();
                }
                
                monitor.done();
            }
        };
        
        IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
        try {
            progressService.busyCursorWhile(algorithmJob);
            
            System.out.println("Selected models: " + attachmentData.getSelectedFiles().size());
            System.out.println("Annotations: " + attachmentData.getAnnotationCountsSum());
            System.out.println("Associations: " + attachmentData.getAssociationCountsSum());
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        this.setDirty(true);
    }
    
    /**
     * Counts the annotations in the view model rooted at the given node.
     * 
     * @param node the model.
     * @return the number of annotation nodes found.
     */
    private int countAnnotations(final KNode node) {
        int result = 0;
        for (KNode child : node.getChildren()) {
            KLayoutData layoutData = child.getData(KLayoutData.class);
            
            if (child.getChildren().size() > 0) {
                result += countAnnotations(child);
            } else if (layoutData.getProperty(CoreOptions.COMMENT_BOX)) {
                result++;
            }
        }
        return result;
    }
    
    /**
     * Invokes GrAna to analyze the selected diagrams. The results are saved in a CSV file named just
     * like the file opened in the editor.
     */
    private void runGraphAnalysisOnDiagrams() {
        // Show the analysis selection dialog so that the user can choose which analyses he wants
        AnalysisSelectionDialog dialog = new AnalysisSelectionDialog(
                this.getSite().getShell(),
                AnalysisService.getInstance().getCategories(),
                new ArrayList<AnalysisData>());
        if (dialog.open() != Dialog.OK) {
            // Cancel the whole thing
            return;
        }
        
        // A control for accessing the display
        final Control control = modelTreeViewer.getControl();

        final Object[] checkedElements = modelTreeViewer.getCheckedElements();
        final Batch batch = new Batch(dialog.getAnalyses());
        
        IRunnableWithProgress algorithmJob = new IRunnableWithProgress() {
            public void run(final IProgressMonitor uiMonitor) {
                IElkProgressMonitor monitor = new ProgressMonitorAdapter(uiMonitor);
                monitor.begin("Analyzing diagrams...", 100);
                
                // Iterate over each selected model file
                for (Object selectedObject : checkedElements) {
                    if (selectedObject instanceof IFile) {
                        final IFile selectedFile = (IFile) selectedObject;
                        
                        // We need to tell the batch analysis how to load our model
                        IKGraphProvider<IFile> kGraphProvider = new IKGraphProvider<IFile>() {
                            public KNode getKGraph(IFile parameter, IElkProgressMonitor monitor)
                                    throws Exception {
                                
                                final Maybe<KNode> result = Maybe.create();
                                control.getDisplay().syncExec(new Runnable() {
                                    public void run() {
                                        loadModel(selectedFile, false);
                                        if (klighdViewContext != null) {
                                            result.set(klighdViewContext.getViewModel());
                                        }
                                    }
                                });
                                return result.get();
                            }
                            
                        };
                        
                        BatchJob<IFile> batchJob = new BatchJob<IFile>(selectedFile, kGraphProvider);
                        batch.appendJob(batchJob);
                    }
                }
                monitor.worked(10);
                
                // Execute the batch
                BatchResult result = batch.execute(monitor.subTask(80));
                
                // Print results to console
                IBatchResultSerializer serializer = new CSVResultSerializer();
                try {
                    serializer.serialize(System.out, result, monitor.subTask(10));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                monitor.done();
            }
        };
        
        IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
        try {
            progressService.busyCursorWhile(algorithmJob);
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * Allows the user to compare a selectable attachment data file to this editor's attachment data.
     */
    private void runAttachmentComparison() {
        // Ask the user what to compare the current evaluation data with
        ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(
                this.getSite().getShell(),
                WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(),
                new BaseWorkbenchContentProvider());
        dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
        dialog.addFilter(new ViewerFilter() {
            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                if (element instanceof IFile) {
                    String extension =  ((IFile) element).getFileExtension();
                    return extension.equalsIgnoreCase("krass");
                } else {
                    return true;
                }
            }
        });
        dialog.setTitle("Select File");
        dialog.setMessage("Select the attachment evaluation that contains the reference attachment "
                + "this file will be compared to.");
        if (dialog.open() != Dialog.OK) {
            return;
        }
        
        // Retrieve the selected file
        Object[] result = dialog.getResult();
        if (result == null || result.length != 1 || !(result[0] instanceof IFile)) {
            return;
        }
        IFile otherFile = (IFile) result[0];
        
        // Load the file's data
        AttachmentData otherData = null;
        try {
            otherData = AttachmentData.fromFile(otherFile);
        } catch (Exception e) {
            MessageDialog.openError(
                    this.getEditorSite().getShell(),
                    "Error",
                    "There was an error opening the selected file."
            );
            e.printStackTrace();
            return;
        }
        
        // Evaluate the data
        DataEvaluator evaluator = DataEvaluator.createFor(otherData, attachmentData, true);
        evaluator.printCSV(System.out);
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // RESOURCE CHANGED LISTENER STUFF
    
    /**
     * Register the resource change listener.
     */
    private void registerResourceChangeListener() {
        ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceChangeListener,
                IResourceChangeEvent.POST_CHANGE);
    }
    
    /**
     * Unregister the resource change listener.
     */
    private void unregisterResourceChangeListener() {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceChangeListener);
    }
    
    /**
     * A resource change listener that updates the editor when resources are removed or changed.
     */
    private IResourceChangeListener resourceChangeListener = new IResourceChangeListener() {
        public void resourceChanged(final IResourceChangeEvent event) {
//            try {
//                event.getDelta().accept(new IResourceDeltaVisitor() {
//                    public boolean visit(final IResourceDelta delta) {
//                        
//                        return true;
//                    }
//                });
//            } catch (CoreException exception) {
//                StatusManager.getManager().handle(exception, PtolemyAttachmentEvalPlugin.PLUGIN_ID);
//            }
        }
    };
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // DIAGRAM WORKBENCH PART STUFF

    public String getPartId() {
        return null;
    }

    public IViewer getViewer() {
        return klighdViewer;
    }

    public ContextViewer getContextViewer() {
        return klighdViewer;
    }

    public ViewContext getViewContext() {
        return klighdViewContext;
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // UTILITY METHODS FOR ANALYSES
    
    /**
     * Returns the node the given comment node is attached to in the currently loaded model. This is
     * a utility function to be used by analyses.
     * 
     * @param viewModelComment
     *            comment node from the view model.
     * @return the node the comment is attached to, or {@code null} if it is unattached.
     */
    public KNode getAttachmentTarget(final KNode viewModelComment) {
        return currentAssociations.get(viewModelComment);
    }
    
}
