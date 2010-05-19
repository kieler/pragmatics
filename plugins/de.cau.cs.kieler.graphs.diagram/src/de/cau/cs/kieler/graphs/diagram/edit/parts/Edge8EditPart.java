package de.cau.cs.kieler.graphs.diagram.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.ui.figures.SplineConnection;
import de.cau.cs.kieler.graphs.diagram.edit.policies.Edge8ItemSemanticEditPolicy;
import de.cau.cs.kieler.graphs.diagram.part.GraphsDiagramEditorPlugin;

/**
 * @generated
 */
public class Edge8EditPart extends ConnectionNodeEditPart implements
        ITreeBranchEditPart {

    /**
     * @generated
     */
    public static final int VISUAL_ID = 4008;

    /**
     * @generated
     */
    public Edge8EditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
                new Edge8ItemSemanticEditPolicy());
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
        Connection figure = new UndirectedEdgeFigure();

        if (figure instanceof SplineConnection) {
            ((SplineConnection) figure).setSplineMode(GraphsDiagramEditorPlugin
                    .getInstance().getPreferenceStore().getInt(
                            SplineConnection.PREF_SPLINE_MODE));
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
