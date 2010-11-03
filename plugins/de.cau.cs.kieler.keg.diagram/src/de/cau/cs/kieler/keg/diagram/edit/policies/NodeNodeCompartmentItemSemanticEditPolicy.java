package de.cau.cs.kieler.keg.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import de.cau.cs.kieler.keg.diagram.edit.commands.Node3CreateCommand;
import de.cau.cs.kieler.keg.diagram.edit.commands.Node4CreateCommand;
import de.cau.cs.kieler.keg.diagram.providers.GraphsElementTypes;

/**
 * @generated
 */
public class NodeNodeCompartmentItemSemanticEditPolicy extends
        GraphsBaseItemSemanticEditPolicy {

    /**
     * @generated
     */
    public NodeNodeCompartmentItemSemanticEditPolicy() {
        super(GraphsElementTypes.Node_2001);
    }

    /**
     * @generated
     */
    protected Command getCreateCommand(CreateElementRequest req) {
        if (GraphsElementTypes.Node_3001 == req.getElementType()) {
            return getGEFWrapper(new Node3CreateCommand(req));
        }
        if (GraphsElementTypes.Node_3003 == req.getElementType()) {
            return getGEFWrapper(new Node4CreateCommand(req));
        }
        return super.getCreateCommand(req);
    }

}
