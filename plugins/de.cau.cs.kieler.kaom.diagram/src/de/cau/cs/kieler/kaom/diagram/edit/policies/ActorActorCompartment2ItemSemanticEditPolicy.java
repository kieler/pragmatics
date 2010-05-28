package de.cau.cs.kieler.kaom.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import de.cau.cs.kieler.kaom.diagram.edit.commands.Actor2CreateCommand;
import de.cau.cs.kieler.kaom.diagram.edit.commands.Relation2CreateCommand;
import de.cau.cs.kieler.kaom.diagram.edit.commands.State2CreateCommand;
import de.cau.cs.kieler.kaom.diagram.providers.KaomElementTypes;

/**
 * @generated
 */
public class ActorActorCompartment2ItemSemanticEditPolicy extends
		KaomBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ActorActorCompartment2ItemSemanticEditPolicy() {
		super(KaomElementTypes.Actor_3002);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (KaomElementTypes.Actor_3002 == req.getElementType()) {
			return getGEFWrapper(new Actor2CreateCommand(req));
		}
		if (KaomElementTypes.State_3003 == req.getElementType()) {
			return getGEFWrapper(new State2CreateCommand(req));
		}
		if (KaomElementTypes.Relation_3004 == req.getElementType()) {
			return getGEFWrapper(new Relation2CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
