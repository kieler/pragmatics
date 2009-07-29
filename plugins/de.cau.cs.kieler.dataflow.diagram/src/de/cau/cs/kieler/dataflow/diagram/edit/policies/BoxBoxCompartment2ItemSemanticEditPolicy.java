package de.cau.cs.kieler.dataflow.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import de.cau.cs.kieler.dataflow.diagram.edit.commands.Box2CreateCommand;
import de.cau.cs.kieler.dataflow.diagram.providers.DataflowElementTypes;

/**
 * @generated
 */
public class BoxBoxCompartment2ItemSemanticEditPolicy extends
		DataflowBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public BoxBoxCompartment2ItemSemanticEditPolicy() {
		super(DataflowElementTypes.Box_3003);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (DataflowElementTypes.Box_3003 == req.getElementType()) {
			return getGEFWrapper(new Box2CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
