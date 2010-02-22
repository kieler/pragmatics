package de.cau.cs.rtprak.graphs.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

import de.cau.cs.rtprak.graphs.diagram.providers.GraphsElementTypes;

/**
 * @generated
 */
public class EdgeItemSemanticEditPolicy extends
		GraphsBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public EdgeItemSemanticEditPolicy() {
		super(GraphsElementTypes.Edge_4001);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}
