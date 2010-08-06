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

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.ui.figures.SplineConnection;
import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.core.util.Pair;

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
    protected IFigure primaryShape;

    /**
     * The list of conditions and the corresponding string for generating the figure.
     */
    private List<Pair<String, ICondition<EObject>>> conditions;

    /**
     * The figure provider for generating the figures from a string.
     */
    private IFigureProvider figureProvider;
    
    private ICondition<EObject> lastCondition = null;

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
        conditions = conditionProvider.getPairs(className);
        figureProvider = conditionProvider.getFigureProvider(className);

    }

    @Override
    public void handleNotificationEvent(final Notification notification) {
        super.handleNotificationEvent(notification);
        if (!(notification.isTouch())) {
            primaryShape = this.getFigure();
            if (primaryShape != null) {
                if (primaryShape instanceof SplineConnection) {
                    SplineConnection splineFigure = (SplineConnection) primaryShape;
                    this.updateFigure(splineFigure);
                    LayoutManager layoutManager = splineFigure.getLayoutManager();
                    if (layoutManager != null) {
                        layoutManager.layout(splineFigure);
                    }
                }
            }
        }
    }

    /**
     * Changes the appearance of a given AttributeAwareFigure according to its circumstances.
     * (checks conditions)
     * 
     * @param figure
     *            the figure to be updated
     */
    public boolean updateFigure(final IFigure figure) {
        if (figure instanceof SplineConnection) {
            SplineConnection splineFigure = (SplineConnection) figure;
            IFigure oldFigure = splineFigure;
            IFigure newFigure = null;
            for (Pair<String, ICondition<EObject>> cf : conditions) {
                if (cf.getSecond().evaluate(this.getModelElement())) {
                    if (cf.getSecond() == lastCondition){
                        return false;
                    } else {
                        newFigure = figureProvider.getFigureByString(cf.getFirst(), oldFigure, null);
                        primaryShape = newFigure;
                        lastCondition = cf.getSecond();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 
     * @return the modelElement of this editPart
     */
    public EObject getModelElement() {
        return this.getNotationView().getElement();
    }

}
