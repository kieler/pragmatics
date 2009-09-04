package de.itemis.gmf.runtime.combolabel;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;


public class ComboBoxDirectEditPolicy extends DirectEditPolicy {

	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		final EObject newValue = (EObject) request.getCellEditor().getValue();
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
		return new ICommandProxy(new AbstractTransactionalCommand(editingDomain, "Set value from cell editor", null) {
			@Override
			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				((ISemanticRedirectingEditPart) getHost()).setValue(newValue);
				return CommandResult.newOKCommandResult();
			}
		});
	}

	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
		// not necessary
	}

}
