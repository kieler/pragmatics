/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.kivi.effects;

import org.eclipse.elk.graph.properties.IPropertyHolder;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.ui.DiagramViewManager;

/**
 * A view management effect for updating a KLighD view. When performing a model update, the new
 * model has to be of the same type as the previous model or the update fails.<br>
 * If the targeted view does not exist the effect opens it.
 * 
 * @author mri
 */
public class KlighdUpdateDiagramEffect extends KlighdDiagramEffect {

    /** the serial version UID. */
    private static final long serialVersionUID = -5653125647788085065L;

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
     * Constructs an effect that updates the diagram view for the given identifier with the input
     * model and name.
     * 
     * @param id
     *            the identifier
     * @param name
     *            the name
     * @param model
     *            the input model
     * @param theSourceWorkbenchPart
     *            the workbench part the element to be shown has been selected in
     */
    public KlighdUpdateDiagramEffect(final String id, final String name, final Object model,
            final IWorkbenchPart theSourceWorkbenchPart) {
        super(id, name, model, theSourceWorkbenchPart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        final IPropertyHolder propertyHolder = this;
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            public void run() {
                final IDiagramWorkbenchPart view;
                if (DiagramViewManager.getView(getId()) == null) {
                    view = DiagramViewManager.createView(getId(), getName(), getModel(),
                                   propertyHolder);
                } else {
                    view = DiagramViewManager.updateView(getId(), getName(), getModel(),
                                   propertyHolder);
                }

                setView(view);
                if (view != null) {
                    setViewer(view.getViewer().getContextViewer().getActiveViewer());
                    setSourceWorkbenchPart();
                }
            }
        });
    }

}
