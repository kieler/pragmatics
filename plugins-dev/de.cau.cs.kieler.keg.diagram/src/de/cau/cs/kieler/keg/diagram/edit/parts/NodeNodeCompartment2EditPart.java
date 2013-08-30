package de.cau.cs.kieler.keg.diagram.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.keg.diagram.edit.policies.NodeNodeCompartment2CanonicalEditPolicy;
import de.cau.cs.kieler.keg.diagram.edit.policies.NodeNodeCompartment2ItemSemanticEditPolicy;
import de.cau.cs.kieler.keg.diagram.part.Messages;

/**
 * @generated
 */
public class NodeNodeCompartment2EditPart extends ShapeCompartmentEditPart {

    /**
     * @generated
     */
    public static final int VISUAL_ID = 7002;

    /**
     * @generated
     */
    public NodeNodeCompartment2EditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    public String getCompartmentName() {
        return Messages.NodeNodeCompartment2EditPart_title;
    }

    /**
     * @generated
     */
    public IFigure createFigure() {
        ResizableCompartmentFigure result = (ResizableCompartmentFigure) super.createFigure();
        result.setTitleVisibility(false);

        result.setBorder(null);
        return result;
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
                new NodeNodeCompartment2ItemSemanticEditPolicy());
        installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicy());
        installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new DragDropEditPolicy());
        installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
                new NodeNodeCompartment2CanonicalEditPolicy());
    }

    /**
     * @generated
     */
    protected void setRatio(Double ratio) {
        if (getFigure().getParent().getLayoutManager() instanceof ConstrainedToolbarLayout) {
            super.setRatio(ratio);
        }
    }

}
