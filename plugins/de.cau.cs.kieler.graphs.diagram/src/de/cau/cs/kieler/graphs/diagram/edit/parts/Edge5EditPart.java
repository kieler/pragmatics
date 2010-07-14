package de.cau.cs.kieler.graphs.diagram.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.ui.figures.SplineConnection;
import de.cau.cs.kieler.graphs.custom.DirectedConnection;
import de.cau.cs.kieler.graphs.custom.GraphsConnection;
import de.cau.cs.kieler.graphs.diagram.edit.policies.Edge5ItemSemanticEditPolicy;
import de.cau.cs.kieler.graphs.diagram.part.GraphsDiagramEditorPlugin;

/**
 * @generated
 */
public class Edge5EditPart extends ConnectionNodeEditPart implements
        GraphsConnection, ITreeBranchEditPart {

    /**
     * @generated
     */
    public static final int VISUAL_ID = 4005;

    /**
     * @generated
     */
    public Edge5EditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
                new Edge5ItemSemanticEditPolicy());
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
        Connection figure = new DirectedEdgeFigure();

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
    public DirectedEdgeFigure getPrimaryShape() {
        return (DirectedEdgeFigure) getFigure();
    }

    /**
     * @generated
     */
    public class DirectedEdgeFigure extends DirectedConnection {

        /**
         * @generated
         */
        public DirectedEdgeFigure() {

            this.setForegroundColor(ColorConstants.black);
        }

        /**
         * @generated
         */
        private boolean myUseLocalCoordinates = false;

        /**
         * @generated
         */
        protected boolean useLocalCoordinates() {
            return myUseLocalCoordinates;
        }

        /**
         * @generated
         */
        protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
            myUseLocalCoordinates = useLocalCoordinates;
        }

    }

}
