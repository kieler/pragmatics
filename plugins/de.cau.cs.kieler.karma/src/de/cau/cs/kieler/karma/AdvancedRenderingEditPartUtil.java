package de.cau.cs.kieler.karma;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;

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
    private List<Pair<String, ICondition<EObject>>> conditions;

    /**
     * The figure provider for generating the figures from a string.
     */
    private IFigureProvider figureProvider;

    /**
     * 
     * @param theConditions
     *            The list of conditions and the corresponding string for generating the figure.
     * @param theFigureProvider
     *            The figure provider for generating the figures from a string.
     */
    public AdvancedRenderingEditPartUtil(List<Pair<String, ICondition<EObject>>> theConditions,
            IFigureProvider theFigureProvider) {
        conditions = theConditions;
        figureProvider = theFigureProvider;
    }

    /**
     * Method to update a figure according to the conditions.
     * 
     * @param figure
     *            the figure to be updated.
     * @param modelElement
     *            the modelelement the figure belongs to.
     * @return true if the figure actually changed, false else.
     */
    public boolean updateFigure(IFigure figure, EObject modelElement) {
        if ((figure instanceof SwitchableFigure) && (conditions != null)) {
            if (!(conditions.isEmpty())) {
                SwitchableFigure attrFigure = (SwitchableFigure) figure;
                IFigure oldFigure = attrFigure.getCurrentFigure();
                IFigure newFigure = null;
                for (Pair<String, ICondition<EObject>> cf : conditions) {
                    if (cf.getSecond().evaluate(modelElement)) {
                        if (lastCondition == cf.getSecond()) {
                            return false;
                        } else {

                            newFigure = figureProvider.getFigureByString(cf.getFirst(), oldFigure,
                                    modelElement);
                            attrFigure.setCurrentFigure(newFigure);
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
     * @param notification
     *            the notification given to the handleNotificationEvent of the editpart.
     * @param primaryShape
     *            the primaryShape attribute of the editpart.
     * @param modelElement
     *            the modelelement of the editpart.
     * @param editPart
     *            the editpart himself.
     */
    public void handleNotificationEvent(final Notification notification, IFigure primaryShape,
            EObject modelElement, GraphicalEditPart editPart) {
        if (!(notification.isTouch())) {
            if (primaryShape != null) {
                if (primaryShape instanceof SwitchableFigure) {
                    SwitchableFigure attrFigure = (SwitchableFigure) primaryShape;
                    // attrFigure.setCurrentFigure(figureProvider.getDefaultFigure());
                    boolean changed = this.updateFigure(attrFigure, modelElement);
                    if (changed) {
                        LayoutManager layoutManager = attrFigure.getLayoutManager();
                        if (layoutManager != null) {
                            layoutManager.layout(attrFigure);
                        }
                        editPart.refresh();
                    }
                }
            }
        }
    }

}
