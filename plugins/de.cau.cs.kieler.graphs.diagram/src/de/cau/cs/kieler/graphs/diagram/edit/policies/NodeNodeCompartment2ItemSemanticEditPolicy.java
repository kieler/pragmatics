package de.cau.cs.kieler.graphs.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import de.cau.cs.kieler.graphs.diagram.edit.commands.Node3CreateCommand;
import de.cau.cs.kieler.graphs.diagram.edit.commands.Node4CreateCommand;
import de.cau.cs.kieler.graphs.diagram.providers.GraphsElementTypes;

/**
 * @generated
 */
public class NodeNodeCompartment2ItemSemanticEditPolicy extends
		GraphsBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public NodeNodeCompartment2ItemSemanticEditPolicy() {
		super(GraphsElementTypes.Node_3001);
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
