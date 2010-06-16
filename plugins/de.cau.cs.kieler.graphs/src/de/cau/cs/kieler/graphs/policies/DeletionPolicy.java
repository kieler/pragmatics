/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.graphs.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;

import de.cau.cs.kieler.core.ui.policies.DeletionPolicyProvider;

/**
 * This policy prevents the deletion of elements that should not be deleted.
 * 
 * @author mri
 */
public class DeletionPolicy extends DeletionPolicyProvider {

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean isUnremovableEditPart(final EditPart editPart) {
        return !(editPart instanceof ShapeCompartmentEditPart);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void createEditPolicies(final EditPart editPart) {
        // only care about one specific edit part for now
        if (editPart instanceof ShapeCompartmentEditPart) {
            editPart.installEditPolicy(EditPolicy.COMPONENT_ROLE,
                    new CompartmentEditPolicy(editPart));
        } else {
            super.createEditPolicies(editPart);
        }
    }

    /**
     * The edit policy for compartments.
     * 
     * @author mri
     */
    protected class CompartmentEditPolicy extends KielerComponentEditPolicy {

        /**
         * Create a new policy.
         * 
         * @param editPartParam
         *            the edit part
         */
        public CompartmentEditPolicy(final EditPart editPartParam) {
            super(editPartParam);
        }

        /**
         * 
         * {@inheritDoc}
         */
        @Override
        public Command getCommand(final Request request) {
            Command result = super.getCommand(request);
            EditPart editPart = super.getEditPart();

            // when a compartment is about to be deleted delete the parent node
            // instead but only if the that parent has a parent as well
            if (request instanceof EditCommandRequestWrapper
                    && editPart instanceof ShapeCompartmentEditPart) {
                EditCommandRequestWrapper req = (EditCommandRequestWrapper) request;
                IEditCommandRequest cmdReq = req.getEditCommandRequest();

                // the element is destroyed
                if (cmdReq instanceof DestroyElementRequest) {
                    // if the compartment is not the top level compartment
                    if(editPart.getParent().getParent() != null) {
                        // get the same request for the parent
                        result = editPart.getParent().getCommand(request);
                    }
                }
            }
            return result;
        }
    }
}
