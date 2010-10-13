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
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;

import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.core.util.Pair;

/**
 * Class containing generic method to update the figure and handle the notification. Used to
 * eliminate redundant code from AdvancedRenderingEditParts.
 * 
 * @author ckru
 * 
 */
public class AdvancedRenderingEditPartUtil {

    /**
     * Container for the last positive condition. Used for performance optimizations.
     */
    private ICondition<EObject> lastCondition = null;

    /**
     * The list of conditions and the corresponding string for generating the figure.
     */
    private List<HashMap<String, Object>> conditions;

    /**
     * 
     * @param theConditions
     *            The list of conditions and the corresponding string for generating the figure.
     */
    public AdvancedRenderingEditPartUtil(final List<HashMap<String, Object>> theConditions) {
        conditions = theConditions;
    }

    /**
     * 
     * @param notification
     *            the notification given to the handleNotificationEvent of the editpart.
     * @param primaryShape
     *            the primaryShape attribute of the editpart.
     * @param modelElement
     *            the modelelement of the editpart.
     * @param editPart
     *            the editpart himself.
     */
    public void handleNotificationEvent(final Notification notification,
            final IFigure primaryShape, final EObject modelElement,
            final AbstractGraphicalEditPart editPart) {
        if (!(notification.isTouch())) {
            IFigure figure = primaryShape;
            if (figure != null) {
                boolean changed = this.updateFigure(figure, modelElement, editPart);
                if (changed) {
                    LayoutManager layoutManager = figure.getLayoutManager();
                    if (layoutManager != null) {
                        layoutManager.layout(figure);
                    }
                }

            }
        }
    }

    /**
     * Method to update a figure according to the conditions.
     * 
     * @param figure
     *            the figure to be updated.
     * @param modelElement
     *            the modelelement the figure belongs to.
     * @param editPart
     *            the edit part of the model element
     * @return true if the figure actually changed, false else.
     */
    public boolean updateFigure(final IFigure figure, final EObject modelElement,
            final AbstractGraphicalEditPart editPart) {
        if (conditions != null) {
            IFigure oldFigure;
            SwitchableFigure attrFigure = null;
            if ((figure instanceof SwitchableFigure)) {
                attrFigure = (SwitchableFigure) figure;
                oldFigure = attrFigure.getCurrentFigure();
            } else {
                oldFigure = figure;
            }
            IFigure newFigure = null;
            for (HashMap<String, Object> conditionElement : conditions) {
                @SuppressWarnings("unchecked")
                ICondition<EObject> condition = (ICondition<EObject>) conditionElement
                        .get("condition");

                if (condition.evaluate(modelElement)) {
                    if (lastCondition == condition) {
                        return false;
                    } else {
                        lastCondition = condition;
                        @SuppressWarnings("unchecked")
                        Pair<Integer, Integer> figureSize = (Pair<Integer, Integer>) conditionElement
                                .get("figureSize");
                        String figureParam = (String) conditionElement.get("figureParam");
                        String layoutParam = (String) conditionElement.get("layoutParam");
                        String borderItemParam = (String) conditionElement.get("borderItemParam");

                        IRenderingProvider renderingProvider = (IRenderingProvider) conditionElement
                                .get("renderingProvider");

                        // setting the new figure
                        newFigure = renderingProvider.getFigureByString(figureParam, oldFigure,
                                modelElement);
                        if (newFigure != null) {
                            if (attrFigure != null) {
                                attrFigure.setCurrentFigure(newFigure);
                            }
                        }
                        // setting the LayoutManager
                        if (figure != null) {
                            LayoutManager newLayoutManager = renderingProvider
                                    .getLayoutManagerByString(layoutParam,
                                            figure.getLayoutManager(), modelElement);
                            if (newLayoutManager != null) {
                                figure.setLayoutManager(newLayoutManager);
                            }
                        }

                        // sets the new BoderItemLocator
                        if (editPart instanceof IBorderItemEditPart) {
                            if (editPart.getParent() instanceof AbstractBorderedShapeEditPart) {
                                AbstractBorderedShapeEditPart parent = ((AbstractBorderedShapeEditPart) editPart
                                        .getParent());
                                IFigure mainFigure = parent.getMainFigure();
                                if (editPart instanceof AdvancedRenderingLabelEditPart) {
                                    IFigure contentPane = editPart.getContentPane();
                                    if (contentPane != null) {
                                        IBorderItemLocator oldLocator = (IBorderItemLocator) contentPane
                                                .getParent().getLayoutManager()
                                                .getConstraint(contentPane);

                                        IBorderItemLocator newLocator = renderingProvider
                                                .getBorderItemLocatorByString(borderItemParam,
                                                        mainFigure, oldLocator, modelElement);

                                        parent.setLayoutConstraint(editPart, contentPane,
                                                newLocator);
                                    } else {
                                        lastCondition = null;
                                    }
                                } else {
                                    IFigure parentsParent = figure.getParent().getParent();
                                    if (parentsParent instanceof BorderedNodeFigure) {
                                        BorderedNodeFigure borderedNodeFigure = (BorderedNodeFigure) parentsParent;
                                        if (borderedNodeFigure.getParent() != null) {
                                            IBorderItemLocator oldLocator = (IBorderItemLocator) borderedNodeFigure
                                                    .getParent().getLayoutManager()
                                                    .getConstraint(borderedNodeFigure);
                                            IBorderItemLocator newLocator = renderingProvider
                                                    .getBorderItemLocatorByString(borderItemParam,
                                                            mainFigure, oldLocator, modelElement);
                                            parent.setLayoutConstraint(editPart,
                                                    borderedNodeFigure, newLocator);
                                        } 
                                    }
                                }
                            }
                        }
                        // setting a fixed node size
                        if (((figureSize.getFirst() >= 0) && (figureSize.getSecond() >= 0))
                                && attrFigure != null) {
                            setFixedNodeSize(attrFigure, figure, figureSize);
                        }

                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void setFixedNodeSize(final SwitchableFigure attrFigure, final IFigure figure,
            final Pair<Integer, Integer> figureSize) {
        Dimension dim = new Dimension(figureSize.getFirst(), figureSize.getSecond());
        figure.getBounds().setSize(dim);
        figure.setMaximumSize(dim.getCopy());
        figure.setMinimumSize(dim.getCopy());
        figure.setPreferredSize(dim.getCopy());
        if (figure.getParent() instanceof DefaultSizeNodeFigure) {
            ((DefaultSizeNodeFigure) figure.getParent()).setDefaultSize(figure.getSize().getCopy());
        }
        attrFigure.setResizeable(false);
    }

}
