package de.cau.cs.kieler.graphs.diagram.edit.parts;

import org.eclipse.draw2d.Connection;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

import de.cau.cs.kieler.graphs.diagram.edit.policies.Edge6ItemSemanticEditPolicy;

/**
 * @generated
 */
public class Edge6EditPart extends ConnectionNodeEditPart implements
        ITreeBranchEditPart {

    /**
     * @generated
     */
    public static final int VISUAL_ID = 4006;

    /**
     * @generated
     */
    public Edge6EditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
                new Edge6ItemSemanticEditPolicy());
    }

    /**
     * @generated
     */
    protected boolean addFixedChild(EditPart childEditPart) {
        return false;
    }

    /**
     * @generated
     */
    protected void addChildVisual(EditPart childEditPart, int index) {
        if (addFixedChild(childEditPart)) {
            return;
        }
        super.addChildVisual(childEditPart, -1);
    }

    /**
     * @generated
     */
    protected boolean removeFixedChild(EditPart childEditPart) {
        return false;
    }

    /**
     * @generated
     */
    protected void removeChildVisual(EditPart childEditPart) {
        if (removeFixedChild(childEditPart)) {
            return;
        }
        super.removeChildVisual(childEditPart);
    }

    /**
     * Creates figure for this edit part.
     * 
     * Body of this method does not depend on settings in generation model
     * so you may safely remove <i>generated</i> tag and modify it.
     * 
     * @generated
     */

    protected Connection createConnectionFigure() {
        return new UndirectedEdgeFigure();
    }

    /**
     * @generated
     */
    public UndirectedEdgeFigure getPrimaryShape() {
        return (UndirectedEdgeFigure) getFigure();
    }

    /**
     * @generated
     */
    public class UndirectedEdgeFigure extends PolylineConnectionEx {

        /**
         * @generated
         */
        public UndirectedEdgeFigure() {
            this.setLineWidth(1);
            this.setForegroundColor(THIS_FORE);

        }

    }

    /**
     * @generated
     */
    static final Color THIS_FORE = new Color(null, 0, 0, 0);

}
