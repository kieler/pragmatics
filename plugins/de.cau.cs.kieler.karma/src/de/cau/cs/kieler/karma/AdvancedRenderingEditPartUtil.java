package de.cau.cs.kieler.karma;

import java.util.HashMap;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.NodeListener;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
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
     * The figure provider for generating the figures from a string.
     */
    private IRenderingProvider renderingProvider;

    /**
     * 
     * @param theConditions
     *            The list of conditions and the corresponding string for generating the figure.
     * @param theRenderingProvider
     *            The figure provider for generating the figures from a string.
     */
    public AdvancedRenderingEditPartUtil(final List<HashMap<String, Object>> theConditions,
            final IRenderingProvider theRenderingProvider) {
        conditions = theConditions;
        renderingProvider = theRenderingProvider;
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
            if (primaryShape != null) {
                IFigure attrFigure = primaryShape;
                boolean changed = this.updateFigure(attrFigure, modelElement, editPart);
                if (changed) {
                    LayoutManager layoutManager = attrFigure.getLayoutManager();
                    if (layoutManager != null) {
                        layoutManager.layout(attrFigure);
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
                        @SuppressWarnings("unchecked")
                        Pair<Integer, Integer> figureSize = (Pair<Integer, Integer>) conditionElement
                                .get("figureSize");
                        String figureParam = (String) conditionElement.get("figureParam");
                        String layoutParam = (String) conditionElement.get("layoutParam");

                        newFigure = renderingProvider.getFigureByString(figureParam, oldFigure,
                                modelElement);
                        LayoutManager newLayoutManager = renderingProvider
                                .getLayoutManagerByString(layoutParam, figure.getLayoutManager(),
                                        modelElement);
                        if (attrFigure != null) {
                            attrFigure.setCurrentFigure(newFigure);
                        } else {

                        }
                        figure.setLayoutManager(newLayoutManager);

                        if ((figureSize.getFirst() >= 0) && (figureSize.getSecond() >= 0)) {
                            Dimension dim = new Dimension(figureSize.getFirst(),
                                    figureSize.getSecond());
                            figure.getBounds().setSize(dim);
                            figure.setMaximumSize(dim.getCopy());
                            figure.setMinimumSize(dim.getCopy());
                            figure.setPreferredSize(dim.getCopy());
                            if (figure.getParent() instanceof DefaultSizeNodeFigure) {
                                ((DefaultSizeNodeFigure)figure.getParent()).setDefaultSize(figure.getSize().getCopy());
                            }
                            if (attrFigure != null) {
                                attrFigure.setResizeable(false);
                            }
                        } 

                        lastCondition = condition;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public void setSize(IFigure figure, final EObject modelElement, final AbstractGraphicalEditPart editPart) {
        if (figure instanceof SwitchableFigure) {
            if (!((SwitchableFigure)figure).getResizeable()){
                figure.getParent().setSize(figure.getSize().getCopy());
            } 
        }
                /*
                IFigure bnf = editPart.getFigure();
                if (bnf instanceof BorderedNodeFigure) {
                    IFigure dnsf = ((BorderedNodeFigure)bnf).getMainFigure();
                
                if (dnsf instanceof DefaultSizeNodeFigure) {
                    ((DefaultSizeNodeFigure) dnsf).setSize(figure.getSize().getCopy());
                    ((DefaultSizeNodeFigure) dnsf).setMaximumSize(figure.getSize().getCopy());
                }
                }
            }
        }
        */
        
        
    }
}


