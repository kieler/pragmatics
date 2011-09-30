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
package de.cau.cs.kieler.klighd.views;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;

/**
 * A singleton manager for creating, updating and closing diagram views. All methods in this class
 * have to be called in the UI thread.
 * 
 * @author mri
 */
public final class DiagramViewManager implements IPartListener {

    /** the primary identifier for the diagram view as specified in the view extension. */
    private static final String PRIMARY_VIEW_ID = "de.cau.cs.kieler.klighd.lightDiagramView";

    /** the singleton instance. */
    private static DiagramViewManager instance = new DiagramViewManager();

    /**
     * Returns the singleton instance.
     * 
     * @return the singleton instance
     */
    public static DiagramViewManager getInstance() {
        return instance;
    }

    /**
     * A private constructor to prevent instantiation.
     */
    private DiagramViewManager() {
        // do nothing
    }

    /**
     * Returns the diagram view with the given identifier if available. Does not create any views.
     * 
     * @param id
     *            the diagram view identifier (can be null for the default view)
     * @return the diagram view or null if no view with the given identifier exists
     */
    public DiagramViewPart getView(final String id) {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        IViewReference viewRef = window.getActivePage().findViewReference(PRIMARY_VIEW_ID, id);
        if (viewRef != null) {
            IViewPart view = viewRef.getView(false);
            if (view instanceof DiagramViewPart) {
                return (DiagramViewPart) view;
            }
        }
        return null;
    }

    /**
     * Updates the diagram view with the given identifier with a given name and model.
     * 
     * @param id
     *            the diagram view identifier (can be null for the default view)
     * @param name
     *            the name (can be null if the name of the view should remain unchanged)
     * @param model
     *            the model (can be null if the displayed model should remain unchanged)
     * @return whether a view with the given identifier has been updated
     */
    public boolean updateView(final String id, final String name, final Object model) {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        IWorkbenchPage page = window.getActivePage();
        IViewReference viewRef = page.findViewReference(PRIMARY_VIEW_ID, id);
        if (viewRef != null) {
            IViewPart view = viewRef.getView(false);
            if (view instanceof DiagramViewPart) {
                DiagramViewPart diagramView = (DiagramViewPart) view;
                if (name != null) {
                    diagramView.setName(name);
                }
                if (model != null) {
                    ViewContext viewContext =
                            LightDiagramServices.getInstance().createViewContext(model);
                    diagramView.getViewer().setModel(viewContext);
                }
                // chsch:
                page.bringToTop(diagramView); // activate(diagramView);
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a diagram view with the given name and model under the specified identifier.
     * 
     * @param id
     *            the diagram view identifier (can be null for the default view)
     * @param name
     *            the name (can be null if the view should be created with the default name)
     * @param model
     *            the model (can be null if the view should be created without an initial model)
     * @return the created view or null if a view with the given identifier exists already or if
     *         creating the view failed
     */
    public DiagramViewPart createView(final String id, final String name, final Object model) {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        IWorkbenchPage page = window.getActivePage();
        IViewReference viewRef = page.findViewReference(PRIMARY_VIEW_ID, id);
        if (viewRef == null) {
            try {
                // chsch:
                IViewPart view = page.showView(PRIMARY_VIEW_ID, id, IWorkbenchPage.VIEW_VISIBLE);
                // IWorkbenchPage.VIEW_ACTIVATE);
                if (view instanceof DiagramViewPart) {
                    DiagramViewPart diagramView = (DiagramViewPart) view;
                    if (name != null) {
                        diagramView.setName(name);
                    }
                    if (model != null) {
                        ViewContext viewContext =
                                LightDiagramServices.getInstance().createViewContext(model);
                        if (viewContext != null) {
                            diagramView.getViewer().setModel(viewContext);

                        } else {
                            // if the newly created view could not be initialized with a diagram,
                            // hide it and return nothing.
                            page.hideView(diagramView);
                            return null;

                        }
                    }
                    return diagramView;
                }
            } catch (PartInitException e) {
                StatusManager.getManager().handle(
                        new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, e.getMessage(), e));
            } catch (IllegalArgumentException e) {
                StatusManager
                        .getManager()
                        .handle(new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID,
                                "Invalid KLighD view id: must not be empty or contain any colons."));
                return null;
            }
        }
        return null;
    }

    /**
     * Closes the diagram view associated with the given id.
     * 
     * @param id
     *            the identifier of the view to be closed.
     * @return <code>true</code> if a view could be closed successfully, and <code>false</code>
     *         otherwise.
     * 
     * @author chsch
     */
    public boolean closeView(final String id) {
        if (id.equals("")) {
            return false;
        }
        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IViewReference viewRef = page.findViewReference(PRIMARY_VIEW_ID, id);

        if (viewRef != null) {
            page.hideView(viewRef);
            return true;
        }
        return false;
    }

    private void registerPartListener() {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        window.getActivePage().addPartListener(this);
    }
    
    private void unregisterPartListener() {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        window.getActivePage().removePartListener(this);        
    }

    /**
     * {@inheritDoc}
     */
    public void partClosed(final IWorkbenchPart part) {
        // TODO implement this
    }

    /**
     * {@inheritDoc}
     */
    public void partOpened(final IWorkbenchPart part) {
        // do nothing
    }
    
    /**
     * {@inheritDoc}
     */
    public void partActivated(final IWorkbenchPart part) {
        // do nothing
    }
    
    /**
     * {@inheritDoc}
     */
    public void partDeactivated(final IWorkbenchPart part) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void partBroughtToTop(final IWorkbenchPart part) {
        // do nothing
    }

}
