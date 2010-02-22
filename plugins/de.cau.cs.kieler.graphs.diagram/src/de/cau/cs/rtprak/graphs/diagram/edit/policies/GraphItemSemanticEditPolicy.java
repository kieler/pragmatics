package de.cau.cs.rtprak.graphs.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

import de.cau.cs.rtprak.graphs.diagram.edit.commands.CompoundNodeCreateCommand;
import de.cau.cs.rtprak.graphs.diagram.edit.commands.NodeCreateCommand;
import de.cau.cs.rtprak.graphs.diagram.providers.GraphsElementTypes;

/**
 * @generated
 */
public class GraphItemSemanticEditPolicy extends
		GraphsBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public GraphItemSemanticEditPolicy() {
		super(GraphsElementTypes.Graph_1000);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (GraphsElementTypes.Node_2001 == req.getElementType()) {
			return getGEFWrapper(new NodeCreateCommand(req));
		}
		if (GraphsElementTypes.CompoundNode_2002 == req.getElementType()) {
			return getGEFWrapper(new CompoundNodeCreateCommand(req));
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
