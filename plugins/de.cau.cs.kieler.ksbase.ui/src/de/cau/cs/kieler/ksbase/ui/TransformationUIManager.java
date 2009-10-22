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
package de.cau.cs.kieler.ksbase.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.Transformation;
import de.cau.cs.kieler.ksbase.ui.handler.ExecuteTransformationRequest;
import de.cau.cs.kieler.ksbase.ui.listener.ITransformationEventListener;

public class TransformationUIManager {

	public static final TransformationUIManager instance = new TransformationUIManager();
	private LinkedList<ITransformationEventListener> postTransformationEventListeners;

	private TransformationUIManager() {
		postTransformationEventListeners = new LinkedList<ITransformationEventListener>();
	}

	public final void addPostTransformationListener(
			ITransformationEventListener listener) {
		System.out.println("listener add");
		postTransformationEventListeners.add(listener);
	}

	public final void removePostTransformationListener(
			ITransformationEventListener listener) {
		postTransformationEventListeners.remove(listener);
	}

	/**
	 * Creates and executes a transformation command by creating a request and
	 * execute the resulting command on the diagram command stack.
	 * 
	 * @param event
	 *            Execution event for which this command should be created
	 * @param editor
	 *            The editor for which this transformation is
	 * @param transformation
	 *            The transformation that should be executed
	 */
	public final void createAndExecuteTransformationCommand(
			final ExecutionEvent event,
			final EditorTransformationSettings editor,
			final Transformation transformation) {

		IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);
		// System.out.println("Diag childs (pre): " +
		// ((DiagramEditor)activeEditor).getDiagram().getVisibleChildren().size());
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
				.getSelectionService().getSelection();

		if (selection instanceof StructuredSelection && !selection.isEmpty()) {

			// First, we have to store the transformation file because Xtend
			// doesn't execute strings
			// We have to do this every time, because we can't be sure that the
			// file we wrote last time is still valid. We will write it
			// to the meta-inf folder:

			EditPart selectedElement = (EditPart) ((StructuredSelection) selection)
					.getFirstElement();

			File file = null;
			try {
				IPath path = ResourcesPlugin.getPlugin().getStateLocation();
				file = File.createTempFile("extension", ".ext", new File(path
						.toOSString()));
				FileOutputStream out = new FileOutputStream(file);
				if (!file.exists()) {
					if (!file.createNewFile()) {
						// FIXME: We were unable to create the file !
						return;
					}
				}
				out.write(editor.getExtFile().getBytes());
				out.flush();
				out.close();

				// Create request
				ExecuteTransformationRequest request = new ExecuteTransformationRequest(
						activeEditor, transformation.getTransformationName(),
						file.getAbsolutePath(), selection, editor
								.getModelPackageClass(), transformation
								.getParameter());

				Command transformationCommand = selectedElement
						.getCommand(request);

				// gets a command stack to execute the command
				DiagramCommandStack commandStack = null;
				Object adapter = activeEditor.getAdapter(CommandStack.class);
				if (adapter instanceof DiagramCommandStack) {
					commandStack = (DiagramCommandStack) adapter;
				}
				if (commandStack == null) {
					commandStack = new DiagramCommandStack(
							((DiagramEditor) activeEditor)
									.getDiagramEditDomain());
				}
				commandStack.execute(transformationCommand);
			} catch (FileNotFoundException e) {

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

				// Remove temporary Xtend file

				if (file != null) {
					if (!file.delete()) {
						System.out
								.println("Warning: Unable to delete temporary xtend file");
					}
				}

				// update edit policies, so GMF will generate diagram elements
				// for model elements which have been generated during the
				// transformation but

				if (activeEditor instanceof DiagramEditor) {
					EObject obj = ((View) ((DiagramEditor) activeEditor)
							.getDiagramEditPart().getModel()).getElement();

					List<?> editPolicies = CanonicalEditPolicy
							.getRegisteredEditPolicies(obj);
					for (Iterator<?> it = editPolicies.iterator(); it.hasNext();) {

						CanonicalEditPolicy nextEditPolicy = (CanonicalEditPolicy) it
								.next();

						nextEditPolicy.refresh();
					}

					IDiagramGraphicalViewer graphViewer = ((DiagramEditor) activeEditor)
							.getDiagramGraphicalViewer();
					graphViewer.flush();
				}
				// not translated to gmf now:
				if (activeEditor instanceof DiagramEditor) {

					// If auto-layout is activated, execute now:
					// FIXME: Changing to 'execute all selected vm tools'
					{
						// Get last parent which is a shapeEditPart
						EditPart par = selectedElement.getParent();
						EditPart layoutTarget = selectedElement;
						if (par instanceof RootEditPart) { // root element has
							// been selected, so
							// layout active
							// element
							// Until Viewmanagement works for root elements
							DiagramLayoutManager.layout(activeEditor,
									selectedElement, true, false);
						}
						if (par != null) { // if a transition is selected, the
							// parent is null
							while (!(par instanceof RootEditPart)) {
								if (par instanceof ShapeEditPart) {
									layoutTarget = par;
								}
								if (par.getParent() == null) {
									break;
								}
								par = par.getParent();
							}
						}
						// Notify event listeners:
						for (ITransformationEventListener transformationEvent : postTransformationEventListeners) {
							transformationEvent
									.transformationExecuted(new Object[] {
											layoutTarget, activeEditor });
						}
					}
				}

			}
		}
	}
}
