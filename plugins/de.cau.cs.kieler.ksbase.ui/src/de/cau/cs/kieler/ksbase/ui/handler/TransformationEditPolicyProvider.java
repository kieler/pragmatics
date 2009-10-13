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
package de.cau.cs.kieler.ksbase.ui.handler;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.CreateEditPoliciesOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;

/**
 * Provides an edit policy the create and execute KSBasE commands.
 * 
 * @author Michael Matzen
 * 
 */
public class TransformationEditPolicyProvider extends AbstractProvider
        implements IEditPolicyProvider {

    /** the key used to install an <i>execute transformation</i> edit policy */
    public static final String EXECUTE_TRANSFORMATION_ROLE = "ExecuteTransformationEditPolicy";
    /** the key used to install an <i>KSBasE popup bar</i> edit policy */
    public static final String KSBASE_POPUPBAR = "KSbasEPopupBarEditPolicy";
    
    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider#createEditPolicies(org.eclipse.gef.EditPart)
     */
    public void createEditPolicies(EditPart editPart) {
        editPart.installEditPolicy(EXECUTE_TRANSFORMATION_ROLE,
                new ExecuteTransformationEditPolicy());
        //editPart.installEditPolicy(KSBASE_POPUPBAR, new KSBAsEPopupBarEditPolicy());
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
     */
    public boolean provides(IOperation operation) {
        return operation instanceof CreateEditPoliciesOperation;
    }

}
