package de.cau.cs.kieler.keg.diagram.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.ui.figures.SplineConnection;
import de.cau.cs.kieler.keg.custom.KEGConnection;
import de.cau.cs.kieler.keg.diagram.edit.policies.Edge2ItemSemanticEditPolicy;
import de.cau.cs.kieler.keg.diagram.part.GraphsDiagramEditorPlugin;

/**
 * @generated
 */
public class Edge2EditPart extends ConnectionNodeEditPart implements
        KEGConnection, ITreeBranchEditPart {

    /**
     * @generated
     */
    public static final int VISUAL_ID = 4002;

    /**
     * @generated
     */
    public Edge2EditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
                new Edge2ItemSemanticEditPolicy());
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
        Connection figure = new UndirectedEdgeFigure();

        if (figure instanceof SplineConnection) {
            ((SplineConnection) figure).setSplineMode(GraphsDiagramEditorPlugin
                    .getInstance().getPreferenceStore()
                    .getInt(SplineConnection.PREF_SPLINE_MODE));
        }
        return figure;
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
    public class UndirectedEdgeFigure extends SplineConnection {

        /**
         * @generated
         */
        public UndirectedEdgeFigure() {

            this.setForegroundColor(ColorConstants.black);
        }

    }

}
