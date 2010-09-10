/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/

package de.cau.cs.kieler.karma;

import java.util.HashMap;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author ckru
 */
public abstract class AdvancedRenderingBorderedShapeEditPart extends AbstractBorderedShapeEditPart
        implements IAdvancedRenderingEditPart {

    /**
     * Figure that that represents the model element.
     */
    protected IFigure primaryShape;

    /**
     * Utility class containing the actual methods. Used to eliminate redundant code.
     */
    private AdvancedRenderingEditPartUtil util;

    /**
     * The constructor. Just calls super.
     * 
     * @param view
     *            to be given to super
     */
    public AdvancedRenderingBorderedShapeEditPart(final View view) {
        super(view);
        String className = this.getClass().getName();
        ConditionProvider conditionProvider = ConditionProvider.getInstance();
        List<HashMap<String, Object>> conditions = conditionProvider.getPairs(className);
        IRenderingProvider figureProvider = conditionProvider.getFigureProvider(className);
        util = new AdvancedRenderingEditPartUtil(conditions, figureProvider);
    }

    @Override
    public void handleNotificationEvent(final Notification notification) {
        super.handleNotificationEvent(notification);
        util.handleNotificationEvent(notification, primaryShape, this.getModelElement(), this);
    }

    /**
     * {@inheritDoc}
     */
    public boolean updateFigure(final IFigure figure) {
        return util.updateFigure(figure, this.getModelElement(), this);
    }

    /**
     * Getter of the model element for conveniences sake.
     * 
     * @return the modelElement of this editPart
     */
    public EObject getModelElement() {
        return this.getNotationView().getElement();
    }

    @Override
    public IFigure getFigure() {
        IFigure figure = super.getFigure();
        updateFigure(primaryShape);
        return figure;
    }
    
}
