package de.cau.cs.kieler.graphs.diagram.edit.parts;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.graphs.diagram.edit.policies.NodeCanonicalEditPolicy;
import de.cau.cs.kieler.graphs.diagram.edit.policies.NodeItemSemanticEditPolicy;

/**
 * @generated
 */
public class NodeEditPart extends DiagramEditPart {

    /**
     * @generated
     */
    public final static String MODEL_ID = "Graphs"; //$NON-NLS-1$

    /**
     * @generated
     */
    public static final int VISUAL_ID = 1000;

    /**
     * @generated
     */
    public NodeEditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
                new NodeItemSemanticEditPolicy());
        installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
                new NodeCanonicalEditPolicy());
        // removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.POPUPBAR_ROLE);
    }

}
