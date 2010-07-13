package de.cau.cs.kieler.graphs.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

import de.cau.cs.kieler.graphs.diagram.edit.commands.Node2CreateCommand;
import de.cau.cs.kieler.graphs.diagram.edit.commands.NodeCreateCommand;
import de.cau.cs.kieler.graphs.diagram.providers.GraphsElementTypes;

/**
 * @generated
 */
public class NodeItemSemanticEditPolicy extends
		GraphsBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public NodeItemSemanticEditPolicy() {
		super(GraphsElementTypes.Node_1000);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (GraphsElementTypes.Node_2001 == req.getElementType()) {
			return getGEFWrapper(new NodeCreateCommand(req));
		}
		if (GraphsElementTypes.Node_2002 == req.getElementType()) {
			return getGEFWrapper(new Node2CreateCommand(req));
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
