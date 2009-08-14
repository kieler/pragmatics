package de.cau.cs.kieler.ksbase.transformations;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.CreateEditPoliciesOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;

public class TransformationEditPolicyProvider extends AbstractProvider implements
IEditPolicyProvider {

    /** the key used to install an <i>execute transformation</i> edit policy  */
    public static final String EXECUTE_TRANSFORMATION_ROLE = "ExecuteTransformationEditPolicy";
    
    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider#createEditPolicies(org.eclipse.gef.EditPart)
     */
    public void createEditPolicies(EditPart editPart) {
            editPart.installEditPolicy(EXECUTE_TRANSFORMATION_ROLE,
                    new ExecuteTransformationEditPolicy());
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
     */
	public boolean provides(IOperation operation) {
		return operation instanceof CreateEditPoliciesOperation;
	}

}
