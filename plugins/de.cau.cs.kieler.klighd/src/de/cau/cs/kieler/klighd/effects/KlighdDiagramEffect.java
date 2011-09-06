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
package de.cau.cs.kieler.klighd.effects;

import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.views.DiagramViewUtil;

/**
 * A view management effect for showing models in a KLighD view.
 * 
 * @author mri
 */
public class KlighdDiagramEffect extends AbstractEffect {

    /** the identifier for the diagram view. */
    private String id = null;
    /** the new name for the diagram view. */
    private String name = null;
    /** the new input model for the diagram view. */
    private Object model = null;

    // the following fields are valid AFTER the effect executed

    /** the created view context. */
    private ViewContext viewContext = null;
    /** the created viewer. */
    private IViewer<?> viewer = null;

    /**
     * Constructs an effect that opens the default diagram view if it is not already open.
     */
    public KlighdDiagramEffect() {
    }

    /**
     * Constructs an effect that opens a diagram view with the given identifier if it is not already
     * open.
     * 
     * @param id
     *            the identifier
     */
    public KlighdDiagramEffect(final String id) {
        this.id = id;
    }

    /**
     * Constructs an effect that opens a diagram view with the given name and identifier or changes
     * the name of an exisiting view with that identifier.
     * 
     * @param id
     *            the identifier
     * @param name
     *            the name
     */
    public KlighdDiagramEffect(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructs an effect that opens the default diagram view with the given input model and
     * identifier or changes the input model of the default view.
     * 
     * @param model
     *            the input model
     */
    public KlighdDiagramEffect(final Object model) {
        this.model = model;
    }

    /**
     * Constructs an effect that opens a diagram view with the given input model and identifier or
     * changes the input model of an existing view with that identifier.
     * 
     * @param id
     *            the identifier
     * @param model
     *            the input model
     */
    public KlighdDiagramEffect(final String id, final Object model) {
        this.id = id;
        this.model = model;
    }

    /**
     * Constructs an effect that opens a diagram view with the given input model, identifier and
     * name or changes the input model and name of an existing view with that identifier.
     * 
     * @param id
     *            the identifier
     * @param name
     *            the name
     * @param model
     *            the input model
     */
    public KlighdDiagramEffect(final String id, final String name, final Object model) {
        this.id = id;
        this.name = name;
        this.model = model;
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        if (LightDiagramServices.getInstance().maybeSupports(model)) {
            MonitoredOperation.runInUI(new Runnable() {
                public void run() {
                    if (!DiagramViewUtil.updateView(id, name, model)) {
                        DiagramViewUtil.createView(id, name, model);
                    }
                }
            }, true);
        }
    }

    /**
     * Returns the view identifier associated with this effect.
     * 
     * @return the view identifier or null for the default view
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name for the view associated with this effect.
     * 
     * @return the name for the view or null for no name modification
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the model for the view associated with this effect.
     * 
     * @return the model for the view or null for no new model
     */
    public Object getModel() {
        return model;
    }

    /**
     * Returns the view context created as part of the effect.
     * 
     * @return the view context or null when called before the effect executed, the execute failed
     *         or was invalid
     */
    public ViewContext getViewContext() {
        return viewContext;
    }

    /**
     * Returns the viewer created as part of the effect.
     * 
     * @return the viewer or null when called before the effect executed, the execute failed or was
     *         invalid
     */
    public IViewer<?> getViewer() {
        return viewer;
    }

    /**
     * A special {@link KlighdDiagramEffect} allowing to close a KLighD view. TODO is this a
     * reasonable way to realize the requirement?
     * 
     * @author chsch
     */
    public static class KLighDCloseDiagramEffect extends KlighdDiagramEffect {

        /**
         * Constructs an effect that closes the diagram view associated with the given id.
         * 
         * @param id
         *            identifier of the view to be closed.
         */
        public KLighDCloseDiagramEffect(final String id) {
            super(id);
        }

        /**
         * {@inheritDoc}
         */
        public void execute() {
            MonitoredOperation.runInUI(new Runnable() {
                public void run() {
                    DiagramViewUtil.closeView(KLighDCloseDiagramEffect.super.id);
                }
            }, true);
        }
    }

}
