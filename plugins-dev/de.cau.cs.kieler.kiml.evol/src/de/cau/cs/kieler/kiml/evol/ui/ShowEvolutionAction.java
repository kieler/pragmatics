/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.evol.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.kiml.evol.EvolLayoutConfig;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.service.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.service.LayoutManagersService;
import de.cau.cs.kieler.kiml.service.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.service.ExtensionLayoutConfigService;
import de.cau.cs.kieler.kiml.service.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.LayoutHandler;
import de.cau.cs.kieler.kiml.ui.views.LayoutViewPart;

/**
 * Handler for opening the evolution dialog.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class ShowEvolutionAction extends Action {
    
    /**
     * The activator triggered when evolutionary layout is enabled or disabled.
     */
    public static class Activator implements Runnable {
        /**
         * {@inheritDoc}
         */
        public void run() {
            LayoutViewPart layoutView = LayoutViewPart.findView();
            if (layoutView != null) {
                IToolBarManager toolbarManager = layoutView.getViewSite().getActionBars()
                        .getToolBarManager();
                boolean active = ExtensionLayoutConfigService.getInstance().getConfigProperties()
                        .getProperty(EvolLayoutConfig.ACTIVATION);
                if (active) {
                    toolbarManager.add(new ShowEvolutionAction());
                } else {
                    toolbarManager.remove(ACTION_ID);
                }
                toolbarManager.update(false);
            }
        }
    }
    
    /** the ICON used for this action. */
    private static final ImageDescriptor ICON = EvolPlugin.getImageDescriptor("icons/bacterium16.gif");
    /** the identifier for this action. */
    private static final String ACTION_ID = "de.cau.cs.kieler.kiml.evol.showEvolution";
    
    /**
     * Creates an action for opening the evolution dialog.
     */
    public ShowEvolutionAction() {
        super("Evolve Layout", ICON);
        setId(ACTION_ID);
        setToolTipText(
                "Open a dialog for finding a layout configuration using evolutionary algorithms");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (window != null) {
            IWorkbenchPage activePage = window.getActivePage();
            if (activePage != null) {
                IEditorPart editorPart = activePage.getActiveEditor();
                IDiagramLayoutManager<?> layoutManager = LayoutManagersService.getInstance()
                        .getManager(editorPart, null);
                if (layoutManager != null) {
                    LayoutMapping<?> layoutMapping = layoutManager.buildLayoutGraph(editorPart, null);
                    if (layoutMapping != null) {
                        
                        // display the evolution dialog
                        doEvolution(window.getShell(), layoutMapping, editorPart);
                        
                    } else {
                        MessageDialog.openError(window.getShell(), "No Active Diagram",
                                "The evolutionary configurator is unable to acquire a diagram.");
                    }
                }
            }
        }
    }
    
    /**
     * Perform evolution.
     * 
     * @param shell the shell
     * @param layoutMapping the layout mapping
     * @param editorPart the editor part
     */
    private void doEvolution(final Shell shell, final LayoutMapping<?> layoutMapping,
            final IEditorPart editorPart) {
        EvolutionDialog dialog = new EvolutionDialog(shell, layoutMapping);
        int result;
        try {
            result = dialog.open();
        } catch (Throwable throwable) {
            IStatus status = new Status(IStatus.ERROR, EvolPlugin.PLUGIN_ID,
                    "Error while initializing evolution dialog.", throwable);
            StatusManager.getManager().handle(status,
                    StatusManager.SHOW | StatusManager.LOG);
            result = EvolutionDialog.CANCEL;
        }
        
        if (result == EvolutionDialog.OK) {
            // perform layout with the new configuration
            IPreferenceStore preferenceStore = KimlUiPlugin.getDefault()
                    .getPreferenceStore();
            boolean animation = preferenceStore.getBoolean(
                    LayoutHandler.PREF_ANIMATION);
            boolean zoomToFit = preferenceStore.getBoolean(
                    LayoutHandler.PREF_ZOOM);
            boolean progressDialog = preferenceStore.getBoolean(
                    LayoutHandler.PREF_PROGRESS);
            DiagramLayoutEngine.INSTANCE.layout(editorPart, null, animation,
                    progressDialog, false, zoomToFit);
            
            // refresh the layout view to update the configuration
            LayoutViewPart layoutView = LayoutViewPart.findView();
            if (layoutView != null) {
                layoutView.refresh();
            }
        }
    }

}
