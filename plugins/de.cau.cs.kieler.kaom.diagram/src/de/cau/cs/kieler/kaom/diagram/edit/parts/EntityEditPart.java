package de.cau.cs.kieler.kaom.diagram.edit.parts;

import java.util.Collections;
import java.util.List;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.NonResizableLabelEditPolicy;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.kaom.diagram.edit.policies.EntityCanonicalEditPolicy;
import de.cau.cs.kieler.kaom.diagram.edit.policies.EntityItemSemanticEditPolicy;

/**
 * @generated
 */
public class EntityEditPart extends DiagramEditPart {

    /**
     * @generated
     */
    public final static String MODEL_ID = "Kaom"; //$NON-NLS-1$

    /**
     * @generated
     */
    public static final int VISUAL_ID = 1000;

    /**
     * @generated
     */
    public EntityEditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new EntityItemSemanticEditPolicy());
        installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new EntityCanonicalEditPolicy());
        // removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.POPUPBAR_ROLE);
    }

    /**
     * @generated
     */
    /*package-local*/static class LinkLabelDragPolicy extends NonResizableLabelEditPolicy {

        /**
         * @generated
         */
        @SuppressWarnings("rawtypes")
        protected List createSelectionHandles() {
            MoveHandle mh = new MoveHandle((GraphicalEditPart) getHost());
            mh.setBorder(null);
            return Collections.singletonList(mh);
        }
    }

}
