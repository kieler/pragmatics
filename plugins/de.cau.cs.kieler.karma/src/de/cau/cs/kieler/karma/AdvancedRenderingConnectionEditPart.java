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
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.notation.View;

/**
 * 
 * @author ckru
 * 
 */
public abstract class AdvancedRenderingConnectionEditPart extends ConnectionNodeEditPart implements
        IAdvancedRenderingEditPart {

    /**
     * Figure that that represents the model element.
     */
    // SUPPRESS CHECKSTYLE NEXT VisibilityModifier
    protected IFigure primaryShape;

    private AdvancedRenderingEditPartUtil util;

    /**
     * The constructor. Just calls super.
     * 
     * @param view
     *            to be given to super
     */
    public AdvancedRenderingConnectionEditPart(final View view) {
        super(view);
        String className = this.getClass().getName();
        ConditionProvider conditionProvider = ConditionProvider.getInstance();
        List<HashMap<String, Object>> conditions = conditionProvider.getPairs(className);
        IRenderingProvider renderingProvider = conditionProvider.getFigureProvider(className);
        util = new AdvancedRenderingEditPartUtil(conditions, renderingProvider);
    }

    @Override
    public void handleNotificationEvent(final Notification notification) {
        super.handleNotificationEvent(notification);
        /*
         * if (!(notification.isTouch())) { primaryShape = this.getFigure(); if (primaryShape !=
         * null) { if (primaryShape instanceof PolylineConnection) { PolylineConnection polyFigure =
         * (PolylineConnection) primaryShape; boolean changed = this.updateFigure(polyFigure); if
         * (changed) { LayoutManager layoutManager = polyFigure.getLayoutManager(); if
         * (layoutManager != null) { layoutManager.layout(polyFigure); } } } } }
         */
        util.handleNotificationEvent(notification, primaryShape, this.getModelElement(), this);
    }

    /**
     * {@inheritDoc}
     */
    public boolean updateFigure(final IFigure figure) {
        /*
         * if ((figure instanceof PolylineConnection) && (conditions != null)) { if
         * (!(conditions.isEmpty())) { PolylineConnection polyFigure = (PolylineConnection) figure;
         * IFigure oldFigure = polyFigure; IFigure newFigure = null; for (HashMap<String, Object>
         * conditionElement : conditions) {
         * 
         * @SuppressWarnings("unchecked") ICondition<EObject> condition = (ICondition<EObject>)
         * conditionElement.get("condition");
         * 
         * if (condition.evaluate(this.getModelElement())) { if (condition == lastCondition) {
         * return false; } else {
         * 
         * @SuppressWarnings("unchecked") Pair<Integer, Integer> figureSize = (Pair<Integer,
         * Integer>)conditionElement.get("figureSize"); String figureParam = (String)
         * conditionElement.get("figureParam"); String layoutParam = (String)
         * conditionElement.get("layoutParam");
         * 
         * newFigure = renderingProvider.getFigureByString(figureParam, oldFigure,
         * this.getModelElement()); primaryShape = newFigure; LayoutManager newLayoutManager =
         * renderingProvider .getLayoutManagerByString(layoutParam, polyFigure.getLayoutManager(),
         * this.getModelElement()); polyFigure.setLayoutManager(newLayoutManager); lastCondition =
         * condition; return true; } } } } } return false;
         */
        return util.updateFigure(figure, this.getModelElement(), this);
    }

    /**
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
