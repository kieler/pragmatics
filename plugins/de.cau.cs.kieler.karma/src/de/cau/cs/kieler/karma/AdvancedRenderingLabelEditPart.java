package de.cau.cs.kieler.karma;

import java.util.HashMap;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.model.gmf.IAdvancedRenderingEditPart;
import de.cau.cs.kieler.karma.util.AdvancedRenderingEditPartUtil;

public class AdvancedRenderingLabelEditPart extends LabelEditPart implements IAdvancedRenderingEditPart {

    /**
     * Figure that that represents the model element.
     */
    // SUPPRESS CHECKSTYLE NEXT VisibilityModifier
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
    public AdvancedRenderingLabelEditPart(final View view) {
        super(view);
        String className = this.getClass().getName();
        ConditionProvider conditionProvider = ConditionProvider.getInstance();
        List<HashMap<String, Object>> conditions = conditionProvider.getPairs(className);
        util = new AdvancedRenderingEditPartUtil(conditions);
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
        return util.updateFigure(figure, this.getModelElement(), this, false);
    }

    /**
     * Getter of the model element for conveniences sake.
     * 
     * @return the modelElement of this editPart
     */
    public EObject getModelElement() {
        return this.getNotationView().getElement();
    }

    /**
     * Flag for calling the updateFigure method only once while initializing the diagram.
     */
    private boolean updateTriggerFigure = true;

    @Override
    public void refresh() {
        super.refresh();
        if (updateTriggerFigure) {
            updateTriggerFigure = false;
            util.updateFigure(primaryShape, this.getModelElement(), this, true);
            IFigure label = this.getFigure();
            if (label instanceof WrappingLabel) {
               ((WrappingLabel)label).setTextWrap(true);
            }
        }
        
    }
    
    /**
     * @return the primaryShape
     */
    public IFigure getPrimaryShape() {
        return primaryShape;
    }
}
