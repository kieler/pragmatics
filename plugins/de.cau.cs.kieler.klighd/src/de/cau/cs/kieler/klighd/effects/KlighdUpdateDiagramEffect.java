/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
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

import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.klighd.views.DiagramViewManager;
import de.cau.cs.kieler.klighd.views.DiagramViewPart;

/**
 * A view management effect for updating a KLighD view. When performing a model update, the new
 * model has to be of the same type as the previous model or the update fails.<br>
 * If the targeted view does not exist the effect opens it.
 * 
 * @author mri
 */
public class KlighdUpdateDiagramEffect extends KlighdDiagramEffect {

    /**
     * Constructs an effect that updates the diagram view for the given identifier with the given
     * name.
     * 
     * @param id
     *            the identifier
     * @param name
     *            the name
     */
    public KlighdUpdateDiagramEffect(final String id, final String name) {
        super(id, name);
    }

    /**
     * Constructs an effect that updates the default diagram view with the given input model and
     * identifier or changes the input model of the default view.
     * 
     * @param model
     *            the input model
     */
    public KlighdUpdateDiagramEffect(final Object model) {
        super(model);
    }

    /**
     * Constructs an effect that updates the diagram view for the given identifier with the input
     * model.
     * 
     * @param id
     *            the identifier
     * @param model
     *            the input model
     */
    public KlighdUpdateDiagramEffect(final String id, final Object model) {
        super(id, model);
    }

    /**
     * Constructs an effect that updates the diagram view for the given identifier with the input
     * model and name.
     * 
     * @param id
     *            the identifier
     * @param name
     *            the name
     * @param model
     *            the input model
     */
    public KlighdUpdateDiagramEffect(final String id, final String name, final Object model) {
        super(id, name, model);
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        final IPropertyHolder propertyHolder = this;
        MonitoredOperation.runInUI(new Runnable() {
            public void run() {
                DiagramViewPart view;
                if (DiagramViewManager.getInstance().getView(getId()) == null) {
                    view = DiagramViewManager.getInstance().createView(getId(), getName(),
                            getModel(), propertyHolder);
                } else {
                    view = DiagramViewManager.getInstance().updateView(getId(), getName(),
                            getModel(), propertyHolder);
                }

                setView(view);
                if (view != null) {
                    setViewer(view.getContextViewer().getActiveViewer());
                }
            }
        }, true);
    }

}
