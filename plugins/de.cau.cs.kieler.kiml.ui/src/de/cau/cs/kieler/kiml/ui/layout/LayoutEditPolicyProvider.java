/******************************************************************************
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
package de.cau.cs.kieler.kiml.ui.layout;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.CreateEditPoliciesOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;

/**
 * The edit policy provider for the <i>apply layout</i> edit policy.
 * 
 * @author <a href="mailto:haf@informatik.uni-kiel.de">Hauke Fuhrmann</a>
 */
public class LayoutEditPolicyProvider extends AbstractProvider implements
        IEditPolicyProvider {

    /** the key used to install an <i>apply layout</i> edit policy  */
    public static final String APPLY_LAYOUT_ROLE = "ApplyLayoutEditPolicy";
    
    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider#createEditPolicies(org.eclipse.gef.EditPart)
     */
    public void createEditPolicies(EditPart editPart) {
        if (editPart instanceof DiagramEditPart) {
            editPart.installEditPolicy(APPLY_LAYOUT_ROLE,
                    new GmfLayoutEditPolicy());
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
     */
    public boolean provides(IOperation operation) {
        return operation instanceof CreateEditPoliciesOperation;
    }

}
