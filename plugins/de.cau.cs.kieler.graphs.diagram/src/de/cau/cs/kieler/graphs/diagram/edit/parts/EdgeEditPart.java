package de.cau.cs.kieler.graphs.diagram.edit.parts;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

import de.cau.cs.kieler.graphs.diagram.edit.policies.EdgeItemSemanticEditPolicy;

/**
 * @generated
 */
public class EdgeEditPart extends ConnectionNodeEditPart implements ITreeBranchEditPart {

    /**
     * @generated
     */
    public static final int VISUAL_ID = 4001;

    /**
     * @generated
     */
    public EdgeEditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new EdgeItemSemanticEditPolicy());
    }

    /**
     * @generated
     */
    protected boolean addFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof EdgeLabelEditPart) {
            ((EdgeLabelEditPart) childEditPart).setLabel(getPrimaryShape().getFigureEdgeLabelFigure());
            return true;
        }
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
        if (childEditPart instanceof EdgeLabelEditPart) {
            return true;
        }
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
        return new EdgeFigure();
    }

    /**
     * @generated
     */
    public EdgeFigure getPrimaryShape() {
        return (EdgeFigure) getFigure();
    }

    /**
     * @generated
     */
    public class EdgeFigure extends PolylineConnectionEx {

        /**
         * @generated
         */
        private WrappingLabel fFigureEdgeLabelFigure;

        /**
         * @generated
         */
        public EdgeFigure() {
            this.setLineWidth(1);
            this.setForegroundColor(THIS_FORE);

            createContents();
            setTargetDecoration(createTargetDecoration());
        }

        /**
         * @generated
         */
        private void createContents() {

            fFigureEdgeLabelFigure = new WrappingLabel();
            fFigureEdgeLabelFigure.setText("");

            this.add(fFigureEdgeLabelFigure);

        }

        /**
         * @generated
         */
        private RotatableDecoration createTargetDecoration() {
            PolygonDecoration df = new PolygonDecoration();
            df.setFill(true);
            df.setLineWidth(1);
            PointList pl = new PointList();
            pl.addPoint(getMapMode().DPtoLP(-2), getMapMode().DPtoLP(1));
            pl.addPoint(getMapMode().DPtoLP(0), getMapMode().DPtoLP(0));
            pl.addPoint(getMapMode().DPtoLP(-2), getMapMode().DPtoLP(-1));
            pl.addPoint(getMapMode().DPtoLP(-1), getMapMode().DPtoLP(0));
            df.setTemplate(pl);
            df.setScale(getMapMode().DPtoLP(7), getMapMode().DPtoLP(3));
            return df;
        }

        /**
         * @generated
         */
        public WrappingLabel getFigureEdgeLabelFigure() {
            return fFigureEdgeLabelFigure;
        }

    }

    /**
     * @generated
     */
    static final Color THIS_FORE = new Color(null, 0, 0, 20);

}
