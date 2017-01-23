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
package de.cau.cs.kieler.kiml.config.text.ui;

import java.util.List;
import java.util.UUID;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.elk.core.LayoutConfigurator;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.service.DiagramLayoutEngine;
import org.eclipse.elk.core.service.DiagramLayoutEngine.Parameters;
import org.eclipse.elk.core.service.IDiagramLayoutConnector;
import org.eclipse.elk.core.service.LayoutConnectorsService;
import org.eclipse.elk.core.ui.ElkUiPlugin;
import org.eclipse.elk.core.ui.LayoutHandler;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditor;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorFactory;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorModelAccess;
import org.eclipse.xtext.ui.editor.embedded.IEditedResourceProvider;

import com.google.common.base.Predicate;
import com.google.inject.Injector;

import de.cau.cs.kieler.kiml.config.text.LayoutConfigTransformer;
import de.cau.cs.kieler.kiml.config.text.ui.internal.LayoutConfigActivator;

/**
 * A view that allows to specify (top level or global) layout options textually.
 * This can for instance be used to chain layout runs.
 * 
 * @author uru
 */
@SuppressWarnings("restriction")
public class LayoutConfigViewPart extends ViewPart {
    
    public static final String PLUGIN_ID = "de.cau.ca.kieler.kiml.config.text.ui";
    
    public static final String VIEW_ID = "de.cau.cs.kieler.kiml.config.text.ui.LayoutConfigEditor";
    
    /** Id to store the config text in the preference store. */
    private static final String CURRENT_CONFIG_TEXT = "de.cau.cs.kieler.kiml.config.text.currentConfigText";
    
    // Embedded Xtext Editor
    private Resource resource;
    private EmbeddedEditorModelAccess partialEditor;
    private EmbeddedEditor handle;

    /** Last active editor that is layoutable. */
    private IWorkbenchPart lastActiveEditor;
    
    /** Predicate that tests if the contents of the passed {@link IWorkbenchPart} can be layouted. */
    private Predicate<IWorkbenchPart> isLayoutableEditor = new Predicate<IWorkbenchPart>() {

        public boolean apply(IWorkbenchPart part) {
            IDiagramLayoutConnector layoutManager =
                    LayoutConnectorsService.getInstance().getConnector(part, null);
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
                ResourceSet resourceSet = new XtextResourceSet();
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

        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(1, true);
        composite.setLayout(layout);
        
        // create the xtext editor within this view
        setupXtextEditor(composite);

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
        if (text != null) {
            partialEditor.updateModel(text);
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
        partialEditor = handle.createPartialEditor(false); // false -> don't insert line breaks

    }

    private void createButtons() {
        final IToolBarManager toolBar = getViewSite().getActionBars().getToolBarManager();

        // add a button that allows to perform the specified layout
        final IAction layoutAction = new Action("Layout", ElkUiPlugin.getImageDescriptor(
        		"icons/menu16/arrange.gif")) {
            public void run() {
                if (lastActiveEditor != null) {
                    performLayout();
                } else {
                    StatusManager.getManager().handle(
                            new Status(IStatus.INFO, "de.cau.cs.kieler.kiml.config.text.ui",
                                    "No editor available to apply layout to.\n"
                                    + "Assure that you focused an editor before "
                                    + "pressing the layout button."), StatusManager.SHOW);
                }
            }
        };
        toolBar.add(layoutAction);
        
        // add a menu for storing/loading templates
        // reset the layout options set over the side pane
        final IMenuManager menu = getViewSite().getActionBars().getMenuManager();
        
        final IAction newViewAction = new Action("Open Another View") {
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
    
    private void performLayout() {

        if (lastActiveEditor == null) {
            return;
        }

        // fetch general settings from preferences
        IPreferenceStore preferenceStore = ElkUiPlugin.getInstance().getPreferenceStore();
        boolean animation = preferenceStore.getBoolean(LayoutHandler.PREF_ANIMATION);
        boolean zoomToFit = preferenceStore.getBoolean(LayoutHandler.PREF_ZOOM);
        boolean progressDialog = preferenceStore.getBoolean(LayoutHandler.PREF_PROGRESS);
 
        @SuppressWarnings("unchecked")
        List<LayoutConfigurator> cfgs = LayoutConfigTransformer.from(resource);
        LayoutConfigurator[] layoutConfigs = cfgs.toArray(new LayoutConfigurator[cfgs.size()]);

        if (layoutConfigs.length == 0) {
            return;
        }

        Parameters params = new Parameters();
        params.getGlobalSettings()
            .setProperty(CoreOptions.ANIMATE, animation)
            .setProperty(CoreOptions.PROGRESS_BAR, progressDialog)
            .setProperty(CoreOptions.ZOOM_TO_FIT, zoomToFit);
        
        for (LayoutConfigurator lc : layoutConfigs) {
        	params.addLayoutRun(lc);
        }
        
        // get the active editor, which is expected to contain the diagram for applying layout
        // perform layout on the whole diagram
        DiagramLayoutEngine.invokeLayout(lastActiveEditor, null, params);
    }
}
