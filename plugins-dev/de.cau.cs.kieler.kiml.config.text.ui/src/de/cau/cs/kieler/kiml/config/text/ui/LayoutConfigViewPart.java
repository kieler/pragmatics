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

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditor;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorFactory;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorModelAccess;
import org.eclipse.xtext.ui.editor.embedded.IEditedResourceProvider;

import com.google.common.base.Predicate;
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
 * @author uru
 */
@SuppressWarnings("restriction")
public class LayoutConfigViewPart extends ViewPart {
    
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
            store.setValue(CURRENT_CONFIG_TEXT, text);
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

        // create the xtext editor within this view
        setupXtextEditor(parent);

        // create the buttons
        createButtons();

        // upon initialization use the first editor we can find
        lastActiveEditor = scanForOpenEditor();

        // also register a listener that does this
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .addPartListener(partListener);
        
        // if there was text stored, load it again
        IPreferenceStore store = LayoutConfigActivator.getInstance().getPreferenceStore();
        String text = store.getString(CURRENT_CONFIG_TEXT);
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

    private void createButtons() {
        final IToolBarManager toolBar = getViewSite().getActionBars().getToolBarManager();

        toolBar.add(new Action("Layout", KimlUiPlugin
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
        });
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
    
    /** preference identifier for animation of layout. */
    private static final String PREF_ANIMATION = "de.cau.cs.kieler.kiml.animation";
    /** preference identifier for zoom-to-fit after layout. */
    private static final String PREF_ZOOM = "de.cau.cs.kieler.kiml.zoomToFit";
    /** preference identifier for progress dialog. */
    private static final String PREF_PROGRESS = "de.cau.cs.kieler.kiml.progressDialog";

    private void performLayout() {

        if (lastActiveEditor == null) {
            return;
        }

        // fetch general settings from preferences
        IPreferenceStore preferenceStore = KimlUiPlugin.getDefault().getPreferenceStore();
        boolean animation = preferenceStore.getBoolean(PREF_ANIMATION);
        boolean zoomToFit = preferenceStore.getBoolean(PREF_ZOOM);
        boolean progressDialog = preferenceStore.getBoolean(PREF_PROGRESS);

        List<VolatileLayoutConfig> cfgs = LayoutConfigTransformer.from(resource);
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
