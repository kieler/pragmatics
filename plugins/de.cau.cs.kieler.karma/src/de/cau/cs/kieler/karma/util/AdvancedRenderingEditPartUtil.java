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

package de.cau.cs.kieler.karma.util;

import java.util.HashMap;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;

import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.karma.AdvancedRenderingLabelEditPart;
import de.cau.cs.kieler.karma.IRenderingProvider;
import de.cau.cs.kieler.karma.SwitchableFigure;

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
        Object notifier = notification.getNotifier();
        if (!(notification.isTouch()) && !(notifier instanceof Bounds)
                && !(notifier instanceof RelativeBendpoints)
                && !(notifier instanceof IdentityAnchor)) {
            IFigure figure = primaryShape;
            if (figure != null) {
                boolean changed = this.updateFigure(figure, modelElement, editPart, false);
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
     * @param forceUpdate
     *            if true the update will be done even if the same conditions have been in place
     *            before.
     * @return true if the figure actually changed, false else.
     */
    public boolean updateFigure(final IFigure figure, final EObject modelElement,
            final AbstractGraphicalEditPart editPart, final Boolean forceUpdate) {
        if (conditions != null) {
            IFigure oldFigure;
            SwitchableFigure switchableFigure = null;
            if ((figure instanceof SwitchableFigure)) {
                switchableFigure = (SwitchableFigure) figure;
                oldFigure = switchableFigure.getCurrentFigure();
            } else {
                oldFigure = figure;
            }
            for (HashMap<String, Object> conditionElement : conditions) {
                @SuppressWarnings("unchecked")
                ICondition<EObject> condition = (ICondition<EObject>) conditionElement
                        .get("condition");

                if (condition.evaluate(modelElement)) {
                    if (lastCondition == condition && !forceUpdate) {
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

                        this.setFigure(renderingProvider, figureParam, oldFigure, modelElement,
                                switchableFigure, editPart);
                        this.setLayoutManager(figure, renderingProvider, layoutParam, modelElement);
                        this.setBorderItemLocator(editPart, renderingProvider, borderItemParam,
                                modelElement, figure);

                        // setting a fixed node size
                        if (((figureSize.getFirst() >= 0) && (figureSize.getSecond() >= 0))
                                && switchableFigure != null) {
                            setFixedNodeSize(switchableFigure, figure, figureSize);
                        }

                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * method that gets a figure from the renderingProvider and sets it to the SwitchableFigure for
     * display.
     * 
     * @param renderingProvider
     *            the renderingProvider to get the new figure from.
     * @param figureParam
     *            the string representation of the new figure to be given to the renderingProvider.
     * @param oldFigure
     *            the old figure.
     * @param modelElement
     *            the modelElement whose graphical representation should be changed
     * @param switchableFigure
     *            the enclosing SwitchableFigure
     */
    private void setFigure(final IRenderingProvider renderingProvider, final String figureParam,
            final IFigure oldFigure, final EObject modelElement,
            final SwitchableFigure switchableFigure, final EditPart part) {
        // setting the new figure
        IFigure newFigure = renderingProvider.getFigureByString(figureParam, oldFigure,
                modelElement, part);
        if (newFigure != null) {
            if (switchableFigure != null) {
                switchableFigure.setCurrentFigure(newFigure);
            }
        }
    }

    /**
     * Method to get a new LayoutManager from the RenderingProvider and set it.
     * 
     * @param figure
     *            the figure whose LayoutManager should be changed.
     * @param renderingProvider
     *            the RenderingProvider.
     * @param layoutParam
     *            the string representation of the layout to be given to the RenderingProvider.
     * @param modelElement
     *            the ModelElement whose figures LayoutManager should be changed.
     */
    private void setLayoutManager(final IFigure figure, final IRenderingProvider renderingProvider,
            final String layoutParam, final EObject modelElement) {
        // setting the LayoutManager
        if (figure != null) {
            LayoutManager newLayoutManager = renderingProvider.getLayoutManagerByString(
                    layoutParam, figure.getLayoutManager(), modelElement);
            if (newLayoutManager != null) {
                figure.setLayoutManager(newLayoutManager);
            }
        }
    }

    /**
     * Method to get a new BorderItemLocator from the RenderingProvider and setting it to the
     * BorderItem.
     * 
     * @param editPart
     *            the EditPart of the BorderItem.
     * @param renderingProvider
     *            the RenderingProvider.
     * @param borderItemParam
     *            the string representation of the new BorderItemLocator.
     * @param modelElement
     *            the ModelElement of the BorderItem
     * @param figure
     *            the Figure of the BorderItem
     */
    private void setBorderItemLocator(final AbstractGraphicalEditPart editPart,
            final IRenderingProvider renderingProvider, final String borderItemParam,
            final EObject modelElement, final IFigure figure) {
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
                                .getParent().getLayoutManager().getConstraint(contentPane);
                        IBorderItemLocator newLocator = renderingProvider
                                .getBorderItemLocatorByString(borderItemParam, mainFigure,
                                        oldLocator, modelElement);
                        parent.setLayoutConstraint(editPart, contentPane, newLocator);
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
                                    .getBorderItemLocatorByString(borderItemParam, mainFigure,
                                            oldLocator, modelElement);
                            if (oldLocator != newLocator) {
                                parent.setLayoutConstraint(editPart, borderedNodeFigure, newLocator);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Method to set a fixed size of a SwitchableFigure.
     * 
     * @param switchableFigure
     *            enclosing figure of the figure whose size should be set.
     * @param figure
     *            the figure whose size should be set.
     * @param figureSize
     *            the new fixed size
     */
    private void setFixedNodeSize(final SwitchableFigure switchableFigure, final IFigure figure,
            final Pair<Integer, Integer> figureSize) {
        Dimension dim = new Dimension(figureSize.getFirst(), figureSize.getSecond());
        figure.getBounds().setSize(dim);
        figure.setMaximumSize(dim.getCopy());
        figure.setMinimumSize(dim.getCopy());
        figure.setPreferredSize(dim.getCopy());
        if (figure.getParent() instanceof DefaultSizeNodeFigure) {
            ((DefaultSizeNodeFigure) figure.getParent()).setDefaultSize(figure.getSize().getCopy());
        } else if (figure.getParent() instanceof NodeFigure) {
            ((NodeFigure) figure.getParent()).setSize(figure.getSize().getCopy());
        }
        switchableFigure.setResizeable(false);
    }

}
