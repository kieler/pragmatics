package de.cau.cs.kieler.graphs.diagram.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.ui.figures.SplineConnection;
import de.cau.cs.kieler.graphs.custom.GraphsConnection;
import de.cau.cs.kieler.graphs.diagram.edit.policies.Edge6ItemSemanticEditPolicy;
import de.cau.cs.kieler.graphs.diagram.part.GraphsDiagramEditorPlugin;

/**
 * @generated
 */
public class Edge6EditPart extends ConnectionNodeEditPart implements GraphsConnection,
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
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new Edge6ItemSemanticEditPolicy());
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
            ((SplineConnection) figure).setSplineMode(GraphsDiagramEditorPlugin.getInstance()
                    .getPreferenceStore().getInt(SplineConnection.PREF_SPLINE_MODE));
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
