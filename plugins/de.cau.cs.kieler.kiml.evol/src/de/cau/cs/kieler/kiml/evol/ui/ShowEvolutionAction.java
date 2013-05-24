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
package de.cau.cs.kieler.kiml.evol.ui;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.evol.EvolLayoutConfig;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.LayoutEvolutionModel;
import de.cau.cs.kieler.kiml.service.LayoutInfoService;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutHandler;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;
import de.cau.cs.kieler.kiml.ui.util.ProgressMonitorAdapter;
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
                boolean active = LayoutInfoService.getInstance().getConfigProperties()
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
                IDiagramLayoutManager<?> layoutManager = EclipseLayoutInfoService.getInstance()
                        .getManager(editorPart, null);
                if (layoutManager != null) {
                    LayoutMapping<?> layoutMapping = layoutManager.buildLayoutGraph(editorPart, null);
                    if (layoutMapping != null) {
                        
                        // initialize the population if necessary
                        if (initializePopulation(window.getShell(), layoutMapping)) {
                            
                            // display the evolution dialog
                            doEvolution(window.getShell(), layoutMapping, editorPart);
                        }
                        
                    } else {
                        MessageDialog.openError(window.getShell(), "No Active Diagram",
                                "The evolutionary configurator is unable to acquire a diagram.");
                    }
                }
            }
        }
    }
    
    /**
     * Initialize the population of the layout evolution model. A dialog may be opened asking the
     * user to select layout options.
     * 
     * @param shell the shell
     * @param layoutMapping the layout mapping
     * @return true if the process can continue, or false if the user aborted the option
     *          selection dialog
     */
    private boolean initializePopulation(final Shell shell, final LayoutMapping<?> layoutMapping) {
        final LayoutEvolutionModel evolutionModel = LayoutEvolutionModel.getInstance();
        if (evolutionModel.getPopulation() == null) {
            // make a new selection of layout options
            OptionSelectionDialog optionSelectionDialog = new OptionSelectionDialog(shell,
                    evolutionModel.getLayoutOptions());
            if (optionSelectionDialog.open() != OptionSelectionDialog.OK) {
                return false;
            }
            
            final List<LayoutOptionData<?>> selectedOptions = optionSelectionDialog.getSelection();
            if (selectedOptions.isEmpty()) {
                MessageDialog.openError(shell, "No Selected Options",
                        "The evolutionary process requires at least one selected option.");
                return false;
            }
            
            try {
                PlatformUI.getWorkbench().getProgressService().busyCursorWhile(
                        new IRunnableWithProgress() {
                    public void run(final IProgressMonitor monitor) throws InvocationTargetException,
                            InterruptedException {
                        synchronized (evolutionModel) {
                            evolutionModel.initializePopulation(layoutMapping, selectedOptions,
                                    new ProgressMonitorAdapter(monitor));
                        }
                    }
                });
            } catch (Exception exception) {
                throw new WrappedException(exception);
            }
        }
        return true;
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
