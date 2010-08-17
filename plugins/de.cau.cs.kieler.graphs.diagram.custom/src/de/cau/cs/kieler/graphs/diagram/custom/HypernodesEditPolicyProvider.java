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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.graphs.diagram.custom;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.CreateEditPoliciesOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;

import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeEditPart;

/**
 * The edit policy provider for the hypernodes edit policy.
 * 
 * @author msp
 */
public class HypernodesEditPolicyProvider extends AbstractProvider implements
        IEditPolicyProvider {

    /** the key used to install a hypernodes edit policy. */
    public static final String HYPERNODES_ROLE = "HypernodesEditPolicy";
    
    /**
     * {@inheritDoc}
     */
    public void createEditPolicies(final EditPart editPart) {
        if (editPart instanceof NodeEditPart) {
            editPart.installEditPolicy(HYPERNODES_ROLE,
                    new HypernodesEditPolicy());
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean provides(final IOperation operation) {
        return operation instanceof CreateEditPoliciesOperation;
    }

}
