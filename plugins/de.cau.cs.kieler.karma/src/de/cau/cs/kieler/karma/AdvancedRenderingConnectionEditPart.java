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
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
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
    // SUPPRESS CHECKSTYLE NEXT VisibilityModifier
    protected IFigure primaryShape;

    /**
     * The list of conditions and the corresponding string for generating the figure.
     */
    private List<Pair<Pair<String, String>, ICondition<EObject>>> conditions;

    /**
     * The figure provider for generating the figures from a string.
     */
    private IRenderingProvider renderingProvider;

    /**
     * Container for the last positive condition. Used for performance optimizations.
     */
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
        renderingProvider = conditionProvider.getFigureProvider(className);
    }

    @Override
    public void handleNotificationEvent(final Notification notification) {
        super.handleNotificationEvent(notification);
        if (!(notification.isTouch())) {
            primaryShape = this.getFigure();
            if (primaryShape != null) {
                if (primaryShape instanceof PolylineConnection) {
                    PolylineConnection polyFigure = (PolylineConnection) primaryShape;
                    boolean changed = this.updateFigure(polyFigure);
                    if (changed) {
                        LayoutManager layoutManager = polyFigure.getLayoutManager();
                        if (layoutManager != null) {
                            layoutManager.layout(polyFigure);
                        }
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean updateFigure(final IFigure figure) {
        if ((figure instanceof PolylineConnection) && (conditions != null)) {
            if (!(conditions.isEmpty())) {
                PolylineConnection polyFigure = (PolylineConnection) figure;
                IFigure oldFigure = polyFigure;
                IFigure newFigure = null;
                for (Pair<Pair<String, String>, ICondition<EObject>> cf : conditions) {
                    if (cf.getSecond().evaluate(this.getModelElement())) {
                        if (cf.getSecond() == lastCondition) {
                            return false;
                        } else {
                            newFigure = renderingProvider.getFigureByString(cf.getFirst()
                                    .getFirst(), oldFigure, this.getModelElement());
                            primaryShape = newFigure;
                            LayoutManager newLayoutManager = renderingProvider
                                    .getLayoutManagerByString(cf.getFirst().getSecond(),
                                            polyFigure.getLayoutManager(), this.getModelElement());
                            polyFigure.setLayoutManager(newLayoutManager);
                            lastCondition = cf.getSecond();
                            return true;
                        }
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
