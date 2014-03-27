/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.config.text.ui;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditor;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorFactory;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorModelAccess;
import org.eclipse.xtext.ui.editor.embedded.IEditedResourceProvider;
import org.eclipse.xtext.xbase.lib.Pair;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.io.Files;
import com.google.inject.Injector;

import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;
import de.cau.cs.kieler.kiml.config.text.LayoutConfigStandaloneSetup;
import de.cau.cs.kieler.kiml.config.text.LayoutConfigTransformer;
import de.cau.cs.kieler.kiml.config.text.ui.internal.LayoutConfigActivator;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.service.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.service.LayoutManagersService;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;

/**
 * A view that allows to specify (top level or global) layout options textually.
 * This can for instance be used to chain layout runs, executing klay and libavoid.
 * 
 * Via a dropdown menu several templates are available.
 * 
 * @author uru
 */
@SuppressWarnings("restriction")
public class LayoutConfigViewPart extends ViewPart {
    
    public static final String PLUGIN_ID = "de.cau.ca.kieler.kiml.config.text.ui";
    
    public static final String VIEW_ID = "de.cau.cs.kieler.kiml.config.text.ui.LayoutConfigEditor";
    
    /** Id to store the config text in the preference store. */
    private static final String CURRENT_CONFIG_TEXT = "de.cau.cs.kieler.kiml.config.text.currentConfigText";
    /** Id to store the last used filename to store a layout config. */
    private static final String LAST_FILE_NAME = "de.cau.cs.kieler.kiml.config.text.lastFileName";
    
    private static final String CFGS_SUBFOLDER = ".kiml" + File.separator + "laycfgs";
    
    // Embedded Xtext Editor
    private Resource resource;
    private EmbeddedEditorModelAccess partialEditor;
    private EmbeddedEditor handle;

    /** Last active editor that is layoutable. */
    private IWorkbenchPart lastActiveEditor;
    
    private Shell shell;
    
    /** Predicate that tests if the contents of the passed {@link IWorkbenchPart} can be layouted. */
    private Predicate<IWorkbenchPart> isLayoutableEditor = new Predicate<IWorkbenchPart>() {

        public boolean apply(IWorkbenchPart part) {
            IDiagramLayoutManager<?> layoutManager =
                    LayoutManagersService.getInstance().getManager(part, null);
            return layoutManager != null;
        }
    };

    /** Listen to part changes to remember the last layoutable editor. */
    private IPartListener partListener = new IPartListener() {

        public void partOpened(IWorkbenchPart part) {
            if (part.equals(lastActiveEditor) || isLayoutableEditor.apply(part)) {
                lastActiveEditor = part;
            }
        }

        public void partDeactivated(IWorkbenchPart part) {
            // will happen as soon as the user switches to the layout view
        }

        public void partClosed(IWorkbenchPart part) {
            if (part.equals(lastActiveEditor)) {
                lastActiveEditor = null;
            }
        }

        public void partBroughtToTop(IWorkbenchPart part) {
            if (part.equals(lastActiveEditor) || isLayoutableEditor.apply(part)) {
                lastActiveEditor = part;
            }
        }

        public void partActivated(IWorkbenchPart part) {
            if (part.equals(lastActiveEditor) || isLayoutableEditor.apply(part)) {
                lastActiveEditor = part;
            }
        }
    };
    
    /** Upon a change to the document remember the current config text in the preference store. */
    private IDocumentListener documentListener = new IDocumentListener() {

        public void documentChanged(DocumentEvent event) {
            // store the current text
            String text = partialEditor.getSerializedModel();
            IPreferenceStore store = LayoutConfigActivator.getInstance().getPreferenceStore();
            store.setValue(CURRENT_CONFIG_TEXT + ":" + getViewSite().getSecondaryId(), text);
        }

        public void documentAboutToBeChanged(DocumentEvent event) {
        }
    };
    
    /** Resource Provider for the embedded editor. */
    private IEditedResourceProvider resourceProvider = new IEditedResourceProvider() {

        public XtextResource createResource() {
            try {
                LayoutConfigStandaloneSetup.doSetup();
                ResourceSet resourceSet = new ResourceSetImpl();
                resource = resourceSet.createResource(URI.createURI("dummy.lc"));

                return (XtextResource) resource;
            } catch (Exception e) {
                return null;
            }
        }
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {

        this.shell = parent.getShell();
        
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(1, true);
        composite.setLayout(layout);
        
        // create the xtext editor within this view
        setupXtextEditor(composite);

        // a scaler to increase/decrease the value of an option
        setupScale(composite);

        // create the buttons
        createButtons();

        // upon initialization use the first editor we can find
        lastActiveEditor = scanForOpenEditor();

        // also register a listener that does this
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .addPartListener(partListener);
        
        // if there was text stored, load it again
        IPreferenceStore store = LayoutConfigActivator.getInstance().getPreferenceStore();
        String text = store.getString(CURRENT_CONFIG_TEXT + ":" + getViewSite().getSecondaryId());
        if(text != null) {
            partialEditor.updateModel("", text, "");
        }
        
        handle.getDocument().addDocumentListener(documentListener);        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {

        // remove the listeners
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .removePartListener(partListener);
        handle.getDocument().removeDocumentListener(documentListener);
        
        super.dispose();
    }

    private void setupXtextEditor(final Composite parent) {
        LayoutConfigActivator activator = LayoutConfigActivator.getInstance();
        Injector injector = activator
                        .getInjector(LayoutConfigActivator.DE_CAU_CS_KIELER_KIML_CONFIG_TEXT_LAYOUTCONFIG);

        EmbeddedEditorFactory factory = injector.getInstance(EmbeddedEditorFactory.class);
        
        // create the editor
        handle = factory.newEditor(resourceProvider)
                    .showErrorAndWarningAnnotations()
                    .withParent(parent);
        partialEditor = handle.createPartialEditor(true);

    }
    
    private void setupScale(final Composite parent) {
        
        // new parent for the text field and scale
        Composite scaleParent = new Composite(parent, SWT.NONE);
        GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        scaleParent.setLayoutData(gd);
        GridLayout grid = new GridLayout(2, false);
        scaleParent.setLayout(grid);
        
        // create textfield to specify the desired layout option
        final Text tf = new Text(scaleParent, SWT.SINGLE | SWT.BORDER);
        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        tf.setLayoutData(gd);
        tf.setText("Layout Option Id");
        
        // a scale to specify the desired value 
        final Scale scale = new Scale(scaleParent, SWT.NONE);
        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        scale.setLayoutData(gd);
        scale.setMinimum(0);
        scale.setMaximum(100);
        
        // listen to value changes of the scale
        scale.addSelectionListener(new SelectionListener() {

            public void widgetSelected(SelectionEvent e) {
                String option = tf.getText();
                int value = scale.getSelection();

                scaleAddition = Pair.of(option, (Number) value);

                performLayout();
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                System.out.println(e);
            }
        });
    }

    private void createButtons() {
        final IToolBarManager toolBar = getViewSite().getActionBars().getToolBarManager();

        // add a button that allows to perform the specified layout
        final IAction layoutAction = new Action("Layout", KimlUiPlugin
                .getImageDescriptor("icons/menu16/kieler-arrange.gif")) {
            public void run() {
                if (lastActiveEditor != null) {
                    performLayout();
                } else {
                    StatusManager.getManager().handle(
                            new Status(IStatus.INFO, "de.cau.cs.kieler.kiml.config.text.ui",
                                    "No editor available to apply layout to."), StatusManager.SHOW);
                }
            }
        };
        toolBar.add(layoutAction);
        
        final IPreferenceStore store = LayoutConfigActivator.getInstance().getPreferenceStore();
        // add a menu for storing/loading templates
        // reset the layout options set over the side pane
        final IMenuManager menu = getViewSite().getActionBars().getMenuManager();
        IAction saveTemplate = new Action("Save as ..") {
            @Override
            public void run() {
                
                // query for a filename
                InputDialog inputDialog =
                        new InputDialog(shell, "Layout Configuration Name", "File Name",
                                store.getString(LAST_FILE_NAME), null);

                if (inputDialog.open() == InputDialog.OK) {
                    // retrieve the filename and remember it
                    String fileName = inputDialog.getValue();
                    store.setValue(LAST_FILE_NAME, fileName);

                    // assemble a file
                    File file = new File(getCfgFolder(), fileName);

                    try {
                        // create folders in case they do not yet exist
                        getCfgFolder().mkdirs();
                        // write the current config to the file
                        String currentText = partialEditor.getSerializedModel();
                        Files.write(currentText.getBytes(), file);
                    } catch (IOException e) {
                        StatusManager.getManager().handle(
                                new Status(Status.ERROR, PLUGIN_ID,
                                        "Could not write layout config to file " + file, e),
                                StatusManager.SHOW);
                    }
                }
            }
        };
        menu.add(saveTemplate);
       
        IAction loadTemplate = new Action("Load ..") {
            @Override
            public void run() {
                ElementListSelectionDialog elsd = new ElementListSelectionDialog(shell, new LabelProvider(){
                    @Override
                    public String getText(Object element) {
                        if (element instanceof File) {
                           return ((File) element).getName(); 
                        }
                        return super.getText(element);
                    }
                });
                elsd.setTitle("Select Configuration File");
                elsd.setMessage("Select a Configuration (* = any string, ? = any char):");

                // get possible files
                File cfgFolder = getCfgFolder();
                elsd.setElements(cfgFolder.listFiles());
                
                String lastName = store.getString(LAST_FILE_NAME);
                for(File f : cfgFolder.listFiles()) {
                    if(f.getName().equals(lastName)) {
                        elsd.setInitialSelections(new File[]{f});
                        break;
                    }
                }
                
                // let the user decide!
                if(elsd.open() == Dialog.OK) {
                    
                    // get the selection and remember
                    File selected = (File) elsd.getFirstResult();
                    store.setValue(LAST_FILE_NAME, selected.getName());
                    
                    try {
                        List<String> lines = Files.readLines(selected, Charset.forName("utf8"));
                        String joined = Joiner.on("\n").join(lines);
                        
                        partialEditor.updateModel("", joined, "");
                        
                    } catch (IOException e) {
                        StatusManager.getManager().handle(
                                new Status(Status.ERROR, PLUGIN_ID,
                                        "Could not load layout config from file " + selected, e),
                                StatusManager.SHOW);
                    }
                }
                
            }
        };
        menu.add(loadTemplate);
        
        
        final IAction newViewAction = new Action("Open New View") {
            @Override
            public void run() {
                IWorkbenchPage page = LayoutConfigViewPart.this.getSite().getPage();
                try {
                    // when opening multiple views it has to be guaranteed that they differ in the
                    // secondary id, we use a randomly generated uuid for this
                    page.showView(VIEW_ID, UUID.randomUUID().toString(),
                            IWorkbenchPage.VIEW_ACTIVATE);
                } catch (PartInitException e) {
                    StatusManager.getManager().handle(
                            new Status(Status.ERROR, PLUGIN_ID,
                                    "Could not open another Textual Layout View", e),
                            StatusManager.SHOW);
                }
            }
        };
        menu.add(newViewAction);

        menu.add(new Separator());
       
        // add the known default templates
        for(final Pair<String, String> pair : DefaultTemplates.defaultTemplates) {
            IAction defaultTemp = new Action(pair.getKey()) {
                @Override
                public void run() {
                    partialEditor.updateModel("", pair.getValue(), "");                    
                }
            };
            menu.add(defaultTemp);
        }
    }

    private IWorkbenchPart scanForOpenEditor() {
        IWorkbenchWindow ww = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (ww != null) {
            IWorkbenchPage page = ww.getActivePage();
            if (page != null) {
                for (IEditorReference ref : page.getEditorReferences()) {
                    IWorkbenchPart part = ref.getEditor(false);
                    if (isLayoutableEditor.apply(part)) {
                        // use this one
                        return part;
                    }
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        // nothing?
    }
    
    private File getCfgFolder() {
        String usrPath = System.getProperty("user.home");
        return new File(usrPath + File.separator + CFGS_SUBFOLDER);
    }
    
    /** preference identifier for animation of layout. */
    private static final String PREF_ANIMATION = "de.cau.cs.kieler.kiml.animation";
    /** preference identifier for zoom-to-fit after layout. */
    private static final String PREF_ZOOM = "de.cau.cs.kieler.kiml.zoomToFit";
    /** preference identifier for progress dialog. */
    private static final String PREF_PROGRESS = "de.cau.cs.kieler.kiml.progressDialog";

    /** an additional layout option that can be specified via a scaler below the textual view. */
    private Pair<String, Number> scaleAddition = Pair.of("", (Number) 0);
    
    private void performLayout() {

        if (lastActiveEditor == null) {
            return;
        }

        // fetch general settings from preferences
        IPreferenceStore preferenceStore = KimlUiPlugin.getDefault().getPreferenceStore();
        boolean animation = preferenceStore.getBoolean(PREF_ANIMATION);
        boolean zoomToFit = preferenceStore.getBoolean(PREF_ZOOM);
        boolean progressDialog = preferenceStore.getBoolean(PREF_PROGRESS);
 
        @SuppressWarnings("unchecked")
        List<VolatileLayoutConfig> cfgs = LayoutConfigTransformer.from(resource, scaleAddition);
        VolatileLayoutConfig[] layoutConfigs = cfgs.toArray(new VolatileLayoutConfig[cfgs.size()]);

        if (layoutConfigs.length == 0) {
            return;
        }

        ((VolatileLayoutConfig) layoutConfigs[0]).setValue(LayoutOptions.ANIMATE, animation)
                .setValue(LayoutOptions.PROGRESS_BAR, progressDialog)
                .setValue(LayoutOptions.ZOOM_TO_FIT, zoomToFit);

        // get the active editor, which is expected to contain the diagram for applying layout
        // perform layout on the whole diagram
        DiagramLayoutEngine.INSTANCE.layout(lastActiveEditor, null, layoutConfigs);
    }
}
