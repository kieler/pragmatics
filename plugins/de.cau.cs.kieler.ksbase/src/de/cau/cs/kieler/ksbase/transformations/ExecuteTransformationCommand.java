package de.cau.cs.kieler.ksbase.transformations;

import java.awt.ActiveEvent;
import java.io.FileOutputStream;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.BasicNotifierImpl;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.WorkflowContextDefaultImpl;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.issues.IssuesImpl;
import org.eclipse.emf.mwe.core.issues.MWEDiagnostic;
import org.eclipse.emf.mwe.core.monitor.NullProgressMonitor;
import org.eclipse.emf.transaction.TransactionChangeDescription;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.impl.WorkspaceCommandStackImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;

public class ExecuteTransformationCommand extends AbstractTransactionalCommand {

	private KielerWorkflow workflow;
	private WorkflowContext context;
	private Issues issues;
	private NullProgressMonitor monitor;
	private List<IFile> affectedFiles;
	
	/**
	 * Creates a command to execute a transformation.
	 * 
	 * @param domain
	 *            the editing domain through which model changes are made
	 * @param label
	 *            the command label
	 * @param adapter
	 *            an adapter to the {@code View} of the base diagram
	 */
	public ExecuteTransformationCommand(TransactionalEditingDomain domain,
			String label, IAdaptable adapter) {
		super(domain, label, null);
		context = new WorkflowContextDefaultImpl();
		issues = new IssuesImpl();
		monitor = new NullProgressMonitor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.gmf.runtime.emf.commands.core.command.
	 * AbstractTransactionalCommand
	 * #doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor,
	 * org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		// TOOD: Suppress messages from xtend/mwe
		if (workflow == null) {
			return CommandResult
					.newErrorCommandResult("Workflow not initalized");
		}
		try {
			workflow.invoke(this.context, this.monitor, this.issues);
		} catch (Exception e) {
			return CommandResult
					.newErrorCommandResult("Failed to invoke workflow");
		}
		if (issues.hasWarnings()) {
			for (MWEDiagnostic warnings : issues.getWarnings()) {
				System.err.println("Warning: " + warnings.getMessage());
			} // TODO: Check how to write multiple warnings, or write directly
			// to the log
			return CommandResult.newWarningCommandResult(
					"Transformation completed with warnings. "
							+ issues.getWarnings()[0], null);
		} else if (issues.hasErrors()) {
			for (MWEDiagnostic errors : issues.getErrors()) {
				System.err.println("Error: " + errors.getMessage());
			} // TODO: Check how to write multiple errors, or write directly to
			// the log
			return CommandResult
					.newErrorCommandResult("Transformation failed. "
							+ issues.getErrors()[0]);
		}

		return CommandResult.newOKCommandResult();
	}
	
	/**
	 * Initializes the transformation
	 * 
	 * @param editPart
	 *            Current edit part
	 * @param selection
	 *            Current selection
	 * @param command
	 *            The command to execute
	 * @param numSelections
	 *            Number of elements that are transformed
	 * @param fileName
	 *            Name of the .ext transformation file
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean initalize(IEditorPart editPart, ISelection selection,
			String command, int numSelections, String fileName) {
		StructuredSelection s;

		if (selection instanceof StructuredSelection)
			s = (StructuredSelection) selection;
		else
			return false; // TODO: Throw unsupported exec

		if (s.size() != numSelections)
			return false;

		Object selectedObject = s.getFirstElement();
		if (selectedObject instanceof EditPart) {
			if (fileName.contains(".")) { //Remove .ext from fileName 
				fileName = fileName.substring(0, fileName.lastIndexOf("."));
			}
			  //TODO: AUSLESEN DES ePACKAGES !!!
			workflow = new KielerWorkflow(command, fileName, null);
			Object model = ((EditPart)selectedObject).getModel();
			if ( model instanceof View) {
				Object element = ((View)model).getElement();
				context.set("model",((View)model).getElement());
			}

		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getAffectedFiles() {
		if (affectedFiles != null)
			return affectedFiles;
		else
			return super.getAffectedFiles();
	}

	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public boolean canRedo() {
		return true;
	}

	protected IStatus doUndo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		/*
		 * TransactionChangeDescription change = getChange();
		 * System.out.println(change.getObjectChanges().size());
		 * System.out.println(change.getObjectsToAttach().size());
		 * System.out.println(change.getObjectsToDetach().size());
		 * System.out.println(change.getResourceChanges().size());
		 * IOperationHistory history =
		 * OperationHistoryFactory.getOperationHistory(); IUndoableOperation[]
		 * ops = history.getUndoHistory(getContexts()[0]);
		 */
		// IUndoContext history = IOperationHistory.GLOBAL_UNDO_CONTEXT;
		// System.out.println(history.matches(getContexts()[0]));
		return super.doUndo(monitor, info);
	}

}
