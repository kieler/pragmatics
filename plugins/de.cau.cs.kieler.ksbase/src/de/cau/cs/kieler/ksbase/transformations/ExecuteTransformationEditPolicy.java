/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.transformations;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.View;

/**
 * Edit policy used to execute a specific transformation. This edit policy
 * creates a {@link ExecuteTransformationCommand} to execute the transformation.
 * 
 * @author Michael Matzen
 */
public class ExecuteTransformationEditPolicy extends AbstractEditPolicy {

    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#understandsRequest(org.eclipse.gef.Request)
     */
    @Override
    public boolean understandsRequest(Request req) {
        return (ExecuteTransformationRequest.REQ_EXEC_TRANS).equals(req
                .getType());
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(Request req) {
        if (ExecuteTransformationRequest.REQ_EXEC_TRANS.equals(req.getType())) {
            if (req instanceof ExecuteTransformationRequest) {
                ExecuteTransformationRequest transformationRequest = (ExecuteTransformationRequest) req;
                IGraphicalEditPart hostEPart = (IGraphicalEditPart) getHost();
                ExecuteTransformationCommand command = new ExecuteTransformationCommand(
                        hostEPart.getEditingDomain(), Messages.ExecuteTransformationEditPolicy_Transformation_Command_Name,
                        new EObjectAdapter((View) hostEPart.getModel()));
                command.initalize(transformationRequest.getEditPart(),
                        transformationRequest.getSelection(),
                        transformationRequest.getCommand(),
                        transformationRequest.getSelectionCount(),
                        transformationRequest.getFileName(),
                        transformationRequest.getModelPackage());
                return new ICommandProxy(command);
            } else
                return null;
        } else
            return super.getCommand(req);
    }
}
