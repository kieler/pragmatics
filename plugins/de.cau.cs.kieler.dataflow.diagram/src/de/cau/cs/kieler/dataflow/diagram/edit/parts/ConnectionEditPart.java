/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.dataflow.diagram.edit.parts;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

import de.cau.cs.kieler.dataflow.diagram.edit.policies.ConnectionItemSemanticEditPolicy;

/**
 * @generated
 */
public class ConnectionEditPart extends ConnectionNodeEditPart implements ITreeBranchEditPart {

    /**
     * @generated
     */
    public static final int VISUAL_ID = 4001;

    /**
     * @generated
     */
    public ConnectionEditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new ConnectionItemSemanticEditPolicy());
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
        return new ConnectionFigure();
    }

    /**
     * @generated
     */
    public ConnectionFigure getPrimaryShape() {
        return (ConnectionFigure) getFigure();
    }

    /**
     * @generated
     */
    public class ConnectionFigure extends PolylineConnectionEx {

        /**
         * @generated
         */
        public ConnectionFigure() {
            this.setLineWidth(1);
            this.setForegroundColor(THIS_FORE);

            setTargetDecoration(createTargetDecoration());
        }

        /**
         * @generated
         */
        private RotatableDecoration createTargetDecoration() {
            PolygonDecoration df = new PolygonDecoration();
            df.setFill(true);
            df.setLineWidth(1);
            return df;
        }

    }

    /**
     * @generated
     */
    static final Color THIS_FORE = new Color(null, 22, 64, 45);

}
