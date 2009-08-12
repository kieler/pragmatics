package de.cau.cs.kieler.dataflow.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

import de.cau.cs.kieler.dataflow.diagram.providers.DataflowElementTypes;

/**
 * @generated
 */
public class ConnectionItemSemanticEditPolicy extends DataflowBaseItemSemanticEditPolicy {

    /**
     * @generated
     */
    public ConnectionItemSemanticEditPolicy() {
        super(DataflowElementTypes.Connection_4001);
    }

    /**
     * @generated
     */
    protected Command getDestroyElementCommand(DestroyElementRequest req) {
        return getGEFWrapper(new DestroyElementCommand(req));
    }

}
