package de.cau.cs.kieler.dataflow.diagram.edit.parts;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.dataflow.diagram.edit.policies.DataflowModelCanonicalEditPolicy;
import de.cau.cs.kieler.dataflow.diagram.edit.policies.DataflowModelItemSemanticEditPolicy;

/**
 * @generated
 */
public class DataflowModelEditPart extends DiagramEditPart {

    /**
     * @generated
     */
    public final static String MODEL_ID = "Dataflow"; //$NON-NLS-1$

    /**
     * @generated
     */
    public static final int VISUAL_ID = 1000;

    /**
     * @generated
     */
    public DataflowModelEditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DataflowModelItemSemanticEditPolicy());
        installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new DataflowModelCanonicalEditPolicy());
        // removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.POPUPBAR_ROLE);
    }

}
