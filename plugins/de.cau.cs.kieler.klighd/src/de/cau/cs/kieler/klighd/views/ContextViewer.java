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

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import de.cau.cs.kieler.klighd.AbstractViewer;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.IViewerEvent;
import de.cau.cs.kieler.klighd.IViewerEventListener;
import de.cau.cs.kieler.klighd.IViewerProvider;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.events.SelectionEvent;
import de.cau.cs.kieler.klighd.triggers.SelectionTrigger;
import de.cau.cs.kieler.klighd.triggers.SelectionTrigger.SelectionObject;
import de.cau.cs.kieler.klighd.triggers.SelectionTrigger.SelectionState;

/**
 * A viewer for instances of type {@code ViewContext}. This viewer acts as a wrapper for the viewer
 * supplied by the current view context. The method {@code getControl} returns the control for that
 * viewer and all other methods are delegated to the wrapped viewer.<br>
 * <br>
 * If a model is set that is not of type {@code ViewContext} but is supported by the currently
 * wrapped viewer it is set as model for that viewer instead.<br>
 * <br>
 * In addition it is possible to set a message to be shown instead of a view context, the wrapped
 * viewer is then of type {@code StringViewer}.
 * 
 * @author mri
 */
public class ContextViewer extends AbstractViewer<Object> implements IViewerEventListener {

    /** the parent composite. */
    private Composite parent;
    /** the id of the view this viewer belongs to. */
    private String viewId;
    /** the current viewer. */
    private IViewer<Object> currentViewer;
    /** the current view context. */
    private ViewContext currentViewContext = null;

    /**
     * Constructs a view context viewer.
     * 
     * @param parent
     *            the parent composite
     * @param viewId
     *            the id of the view this viewer belongs to
     */
    public ContextViewer(final Composite parent, final String viewId) {
        this.parent = parent;
        this.viewId = viewId;
    }

    /**
     * {@inheritDoc}
     */
    public Control getControl() {
        return currentViewer.getControl();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public synchronized void setModel(final Object model) {
        // if the model is a view context adapt the viewer to the given context, else set it as
        // input model for the current viewer if possible or show it if it is a string
        if (model instanceof ViewContext) {
            ViewContext viewContext = (ViewContext) model;
            if (currentViewContext == null
                    || currentViewContext.getViewerProvider() != viewContext.getViewerProvider()) {
                // remove the old viewer
                removeViewer();
                // create the new viewer from the view context
                IViewerProvider viewerProvider = viewContext.getViewerProvider();
                IViewer<Object> viewer = (IViewer<Object>) viewerProvider.createViewer(parent);
                // add the new viewer
                addViewer(viewer);
            }
            // set the new view context
            currentViewContext = viewContext;
            // set the model
            currentViewer.setModel(viewContext.getModel());
        } else if (currentViewContext != null
                && currentViewContext.getViewerProvider().supports(model)) {
            // if the current viewer supports the given model set it as new input model
            currentViewer.setModel(model);
        } else if (model instanceof String) {
            // if the model is a string show it
            showMessage((String) model);
        }
    }

    /**
     * Shows the given message.
     * 
     * @param message
     *            the message
     */
    public synchronized void showMessage(final String message) {
        if (!((IViewer<?>) currentViewer instanceof StringViewer)) {
            removeViewer();
            addViewer(new StringViewer(parent));
        }
        currentViewer.setModel(message);
    }

    @SuppressWarnings("unchecked")
    private synchronized void addViewer(final IViewer<?> viewer) {
        currentViewer = (IViewer<Object>) viewer;
        parent.layout();
        currentViewer.addEventListener(this);
    }

    private synchronized void removeViewer() {
        if (currentViewer != null) {
            currentViewer.removeEventListener(this);
            currentViewer.getControl().dispose();
            currentViewer = null;
            currentViewContext = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void handleEvent(final IViewerEvent event) {
        if (event instanceof SelectionEvent) {
            handleSelectionEvent((SelectionEvent) event);
        }
    }

    private void handleSelectionEvent(final SelectionEvent selectionEvent) {
        SelectionTrigger trigger = SelectionTrigger.getInstance();
        if (trigger != null) {
            // create the selection objects
            List<SelectionObject> selections = new LinkedList<SelectionObject>();
            for (Object diagramObject : selectionEvent.getDiagramObjects()) {
                // get the model object represented by the selected object
                Object modelObject = currentViewContext.getSourceObject(diagramObject);
                selections.add(new SelectionObject(diagramObject, modelObject));
            }
            // trigger the selection trigger
            SelectionState state = new SelectionState(viewId, currentViewContext, currentViewer,
                    selections, selectionEvent.isSelection());
            trigger.trigger(state);
        }
    }

    /**
     * Returns the currently active viewer.
     * 
     * @return the viewer
     */
    public IViewer<?> getActiveViewer() {
        return currentViewer;
    }

}
