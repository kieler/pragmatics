package de.cau.cs.kieler.kaom.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

import de.cau.cs.kieler.kaom.diagram.edit.commands.ActorCreateCommand;
import de.cau.cs.kieler.kaom.diagram.edit.commands.RelationCreateCommand;
import de.cau.cs.kieler.kaom.diagram.edit.commands.StateCreateCommand;
import de.cau.cs.kieler.kaom.diagram.providers.KaomElementTypes;

/**
 * @generated
 */
public class StateItemSemanticEditPolicy extends KaomBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public StateItemSemanticEditPolicy() {
		super(KaomElementTypes.State_1000);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (KaomElementTypes.Actor_2001 == req.getElementType()) {
			return getGEFWrapper(new ActorCreateCommand(req));
		}
		if (KaomElementTypes.State_2002 == req.getElementType()) {
			return getGEFWrapper(new StateCreateCommand(req));
		}
		if (KaomElementTypes.Relation_2003 == req.getElementType()) {
			return getGEFWrapper(new RelationCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
				.getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends
			DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(
				TransactionalEditingDomain editingDomain,
				DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req
					.getElementsToBeDuplicated(), req
					.getAllDuplicatedElementsMap());
		}

	}

}
