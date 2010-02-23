package de.cau.cs.kieler.graphs.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import de.cau.cs.kieler.graphs.diagram.edit.commands.CompoundNode2CreateCommand;
import de.cau.cs.kieler.graphs.diagram.edit.commands.Node2CreateCommand;
import de.cau.cs.kieler.graphs.diagram.providers.GraphsElementTypes;

/**
 * @generated
 */
public class CompoundNodeNodeCompartmentItemSemanticEditPolicy extends
		GraphsBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public CompoundNodeNodeCompartmentItemSemanticEditPolicy() {
		super(GraphsElementTypes.CompoundNode_2002);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (GraphsElementTypes.Node_3001 == req.getElementType()) {
			return getGEFWrapper(new Node2CreateCommand(req));
		}
		if (GraphsElementTypes.CompoundNode_3002 == req.getElementType()) {
			return getGEFWrapper(new CompoundNode2CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
