package de.cau.cs.kieler.kaom.diagram.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.model.gmf.figures.SplineConnection;
import de.cau.cs.kieler.kaom.diagram.edit.policies.LinkItemSemanticEditPolicy;
import de.cau.cs.kieler.karma.AdvancedRenderingConnectionEditPart;

/**
 * @generated
 */
public class LinkEditPart extends AdvancedRenderingConnectionEditPart implements
        ITreeBranchEditPart {

    /**
     * @generated
     */
    public static final int VISUAL_ID = 4001;

    /**
     * @generated
     */
    public LinkEditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new LinkItemSemanticEditPolicy());
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
        return new LinkFigure();
    }

    /**
     * @generated
     */
    public LinkFigure getPrimaryShape() {
        return (LinkFigure) getFigure();
    }

    /**
     * @generated
     */
    public class LinkFigure extends SplineConnection {

        /**
         * @generated
         */
        private WrappingLabel fFigureLinkNameLabel;

        /**
         * @generated
         */
        public LinkFigure() {
            this.setForegroundColor(ColorConstants.black);
            this.setBackgroundColor(ColorConstants.black);

            setTargetDecoration(createTargetDecoration());
        }

        /**
         * @generated
         */
        private RotatableDecoration createTargetDecoration() {
            PolygonDecoration df = new PolygonDecoration();
            df.setFill(true);
            df.setForegroundColor(ColorConstants.black);
            df.setBackgroundColor(ColorConstants.black);
            return df;
        }

        /**
         * @generated
         */
        public WrappingLabel getFigureLinkNameLabel() {
            return fFigureLinkNameLabel;
        }

    }

}
