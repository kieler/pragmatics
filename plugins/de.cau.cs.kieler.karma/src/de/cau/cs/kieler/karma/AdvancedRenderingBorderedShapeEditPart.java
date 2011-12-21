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
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.model.gmf.IAdvancedRenderingEditPart;
import de.cau.cs.kieler.karma.util.AdvancedRenderingEditPartUtil;
import de.cau.cs.kieler.karma.util.expandcollapsebutton.AdvancedRenderingResizableCompartmentEditPolicy;

/**
 * @author ckru
 */
public abstract class AdvancedRenderingBorderedShapeEditPart extends AbstractBorderedShapeEditPart
        implements IAdvancedRenderingEditPart {

    /**
     * Figure that that represents the model element.
     */
    // SUPPRESS CHECKSTYLE NEXT VisibilityModifier
    protected IFigure primaryShape;

    /**
     * Utility class containing the actual methods. Used to eliminate redundant code.
     */
    private AdvancedRenderingEditPartUtil util;

    public NodeFigure MyNodePlate;

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

    /**
     * This Method installs the AdvancedRenderingResizableCompartmentEditPolicy as Primary_drag_role
     * editpolicy for all compartments of this Edit Part. This is necessary to make the
     * expand/collapse button exchangable.
     */
    private void activateExchangableExpandCollapse() {
        List<EditPart> resizeableCompartments = this.getResizableCompartments();
        for (EditPart compartment : resizeableCompartments) {
            if (compartment instanceof IResizableCompartmentEditPart) {
                IResizableCompartmentEditPart resizeComp = (IResizableCompartmentEditPart) compartment;
                AdvancedRenderingResizableCompartmentEditPolicy arcep = 
                    new AdvancedRenderingResizableCompartmentEditPolicy();
                resizeComp.installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, arcep);
            }
        }
    }
    
    /**
     * set custom collapse figure.
     * @param figure the new custom figure
     */
    public void setCollapseFigure(final IFigure figure) {
        List<EditPart> resizeableCompartments = this.getResizableCompartments();
        for (EditPart compartment : resizeableCompartments) {
            if (compartment instanceof IResizableCompartmentEditPart) {
                IResizableCompartmentEditPart resizeComp = (IResizableCompartmentEditPart) compartment;
                EditPolicy epol = resizeComp.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
                if (epol instanceof AdvancedRenderingResizableCompartmentEditPolicy) {
                    ((AdvancedRenderingResizableCompartmentEditPolicy)epol).setCollapseFigure(figure);
                }
            }
        }
    }

    /**
     * set custom expand figure.
     * @param figure the new custom figure
     */
    public void setExpandFigure(final IFigure figure) {
        List<EditPart> resizeableCompartments = this.getResizableCompartments();
        for (EditPart compartment : resizeableCompartments) {
            if (compartment instanceof IResizableCompartmentEditPart) {
                IResizableCompartmentEditPart resizeComp = (IResizableCompartmentEditPart) compartment;
                EditPolicy epol = resizeComp.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
                if (epol instanceof AdvancedRenderingResizableCompartmentEditPolicy) {
                    ((AdvancedRenderingResizableCompartmentEditPolicy)epol).setExpandFigure(figure);
                }
            }
        }
    }

    /**
     * set custom collapse/expand locator.
     * @param locator the new custom figure
     */
    public void setCollapseExpandLocator(final Locator locator) {
        List<EditPart> resizeableCompartments = this.getResizableCompartments();
        for (EditPart compartment : resizeableCompartments) {
            if (compartment instanceof IResizableCompartmentEditPart) {
                IResizableCompartmentEditPart resizeComp = (IResizableCompartmentEditPart) compartment;
                EditPolicy epol = resizeComp.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
                if (epol instanceof AdvancedRenderingResizableCompartmentEditPolicy) {
                    ((AdvancedRenderingResizableCompartmentEditPolicy)epol).setCollapseExpandLocator(locator);
                }
            }
        }
    }
    
    /**
     * set custom size of the clickable area to expand/collapse.
     * @param dim
     */
    public void setCollapseExpandSize(final Dimension dim) {
        List<EditPart> resizeableCompartments = this.getResizableCompartments();
        for (EditPart compartment : resizeableCompartments) {
            if (compartment instanceof IResizableCompartmentEditPart) {
                IResizableCompartmentEditPart resizeComp = (IResizableCompartmentEditPart) compartment;
                EditPolicy epol = resizeComp.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
                if (epol instanceof AdvancedRenderingResizableCompartmentEditPolicy) {
                    ((AdvancedRenderingResizableCompartmentEditPolicy)epol).setCollapseExpandSize(dim);
                }
            }
        }
    }
    

    @Override
    public void refresh() {
        super.refresh();
        if (updateTriggerFigure) {
            this.activateExchangableExpandCollapse();
            updateTriggerFigure = false;
            util.updateFigure(primaryShape, this.getModelElement(), this, true);
        }
    }
}
