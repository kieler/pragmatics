/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.ui.handler;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Locale;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.WorkflowContextDefaultImpl;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.issues.IssuesImpl;
import org.eclipse.emf.mwe.core.issues.MWEDiagnostic;
import org.eclipse.emf.mwe.core.monitor.NullProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.ksbase.core.KielerWorkflow;

/**
 * The command to execute an Xtend transformation. Handles MWE initialization
 * too.
 * 
 * @author Michael Matzen
 * 
 */
public class ExecuteTransformationCommand extends AbstractTransactionalCommand {

	private KielerWorkflow workflow;
	private WorkflowContext context;
	private Issues issues;
	private NullProgressMonitor monitor;

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
	public ExecuteTransformationCommand(
			final TransactionalEditingDomain domain, final String label,
			final IAdaptable adapter) {
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
		// This is a very ugly way to suppress messages from xtend
		PrintStream syse = System.err;
		PrintStream syso = System.out;

		if (workflow == null) {
			return CommandResult
					.newErrorCommandResult(Messages.ExecuteTransformationCommand_Workflow_Initialization_Error);
		}
		try {
			System.setErr(new PrintStream(new ByteArrayOutputStream()));
			System.setOut(new PrintStream(new ByteArrayOutputStream()));
			workflow.invoke(this.context, this.monitor, this.issues);
		} catch (Exception e) {
			return CommandResult
					.newErrorCommandResult(Messages.ExecuteTransformationCommand_Workflow_Invoke_Error);
		} finally {
			System.setErr(syse);
			System.setOut(syso);
		}
		if (issues.hasWarnings()) {
			for (MWEDiagnostic warnings : issues.getWarnings()) {
				System.err.println("Warning: " + warnings.getMessage()); //$NON-NLS-1$
			} // TODO: Check how to write multiple warnings, or write directly
			// to the log
			return CommandResult.newWarningCommandResult(
					"Transformation completed with warnings. " //$NON-NLS-1$
							+ issues.getWarnings()[0], null);
		} else if (issues.hasErrors()) {
			for (MWEDiagnostic errors : issues.getErrors()) {
				System.err.println("Error: " + errors.getMessage()); //$NON-NLS-1$
			} // TODO: Check how to write multiple errors, or write directly to
			// the log
			return CommandResult
					.newErrorCommandResult("Transformation failed. " //$NON-NLS-1$
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
	 * @param fileName
	 *            Name of the .ext transformation file
	 * @param basePackage
	 *            The package of the underlying meta model
	 * @param parameter
	 *            The parameters of the Xtend method
	 * @return False if an error occurred
	 */
	public final boolean initalize(final IEditorPart editPart,
			final ISelection selection, final String command,
			final String fileName, final String basePackage,
			final String[] parameter) {
		StructuredSelection s;

		if (selection instanceof StructuredSelection) {
			s = (StructuredSelection) selection;
		} else {
			return false;
		}

		if (s.size() != parameter.length) {
			return false;
		}
		String file = fileName;
		if (file.contains(".")) { // Remove .ext from fileName //$NON-NLS-1$
			file = fileName.substring(0, fileName.lastIndexOf(".")); //$NON-NLS-1$
		}

		StringBuffer modelSelection = new StringBuffer();

		// We are now going to order the selected diagram elements by
		Iterator<?> it;
		int paramCount = 0;
		for (String param : parameter) {
			it = s.iterator();
			if (modelSelection.length() > 0) {
				modelSelection.append(",");
			}
			while (it.hasNext()) {
				Object next = it.next();
				if (next instanceof EditPart) {
					Object model = ((EditPart) next).getModel();
					if (model instanceof View) {
						if (((View) model).getElement().eClass().getName()
								.toLowerCase(Locale.getDefault()).equals(param)) {
							String modelName = "model"
									+ String.valueOf(paramCount++);
							modelSelection.append(modelName);
							context.set(modelName, ((View) model).getElement());
						}
					}
				}
			}
		}
		// check if all parameters have been set
		if (paramCount != parameter.length) {
			return false;
		}

		workflow = new KielerWorkflow(command, file, basePackage,
				modelSelection.toString());
		return true;
	}

}
