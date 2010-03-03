/*
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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.KSBasETransformation;
import de.cau.cs.kieler.ksbase.ui.handler.ExecuteTransformationRequest;
import de.cau.cs.kieler.ksbase.ui.listener.ITransformationEventListener;
import de.cau.cs.kieler.ksbase.ui.utils.TransformationUtils;
import de.cau.cs.kieler.viewmanagement.RunLogic;

/**
 * Transformation-UI manager. Handles creation and execution of commands and notify of
 * transformationEvent listeners
 * 
 * @author mim
 * 
 * @kieler.rating 2009-12-15 proposed yellow
 */
public final class TransformationUIManager {

    /** Transformation-UI instance. **/
    public static final TransformationUIManager INSTANCE = new TransformationUIManager();

    /**
     * The list of listeners to notify before and after transformation has been executed.
     **/
    private LinkedList<ITransformationEventListener> transformationEventListeners;

    private TransformationUtils transformationUtils;

    /**
     * The default constructor.
     */
    private TransformationUIManager() {
        transformationEventListeners = new LinkedList<ITransformationEventListener>();
        transformationUtils = new TransformationUtils();
        transformationEventListeners.add(transformationUtils);
    }

    /**
     * Adds a listener to the post-transformation transformation listener queue.
     * 
     * @param listener
     *            The listener to add
     */
    public void addTransformationListener(final ITransformationEventListener listener) {
        transformationEventListeners.add(listener);
    }

    /**
     * Removes a listener from the post-transformation listener queue.
     * 
     * @param listener
     *            The listener to remove.
     */
    public void removeTransformationListener(final ITransformationEventListener listener) {
        transformationEventListeners.remove(listener);
    }

    /**
     * Creates and executes a transformation command by creating a request and execute the resulting
     * command on the diagram command stack.
     * 
     * @param event
     *            Execution event for which this command should be created
     * @param editor
     *            The editor for which this transformation is
     * @param transformation
     *            The transformation that should be executed
     */
    public void createAndExecuteTransformationCommand(final EditorTransformationSettings editor,
            final KSBasETransformation transformation) {
        // We need the view management
        if (!RunLogic.getInstance().getState()) {
            RunLogic.getInstance().registerListeners();
        }

        // Notify event listeners:
        for (ITransformationEventListener te : transformationEventListeners) {
            te.transformationAboutToExecute(new Object[] {});
        }

        IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getActivePage().getActiveEditor();
        // System.out.println("Diag childs (pre): " +
        // ((DiagramEditor)activeEditor).getDiagram().getVisibleChildren().size());

        ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getSelectionService().getSelection();

        if (selection instanceof StructuredSelection && !selection.isEmpty()) {

            EditPart selectedElement = (EditPart) ((StructuredSelection) selection)
                    .getFirstElement();

            // Create request
            ExecuteTransformationRequest request = new ExecuteTransformationRequest(activeEditor,
                    transformation.getTransformation(), editor.getTransformationFile(), selection,
                    editor.getModelPackageClass(), transformation.getParameters(), editor
                            .getFramework());

            Command transformationCommand = selectedElement.getCommand(request);

            // gets a command stack to execute the command
            DiagramCommandStack commandStack = null;
            Object adapter = activeEditor.getAdapter(CommandStack.class);
            if (adapter instanceof DiagramCommandStack) {
                commandStack = (DiagramCommandStack) adapter;
            }
            if (commandStack == null) {
                commandStack = new DiagramCommandStack(((DiagramEditor) activeEditor)
                        .getDiagramEditDomain());
            }
            commandStack.execute(transformationCommand);

            // update edit policies, so GMF will generate diagram elements
            // for model elements which have been generated during the
            // transformation.

            if (activeEditor instanceof IDiagramWorkbenchPart) {
                EObject obj = ((View) ((IDiagramWorkbenchPart) activeEditor).getDiagramEditPart()
                        .getModel()).getElement();

                List<?> editPolicies = CanonicalEditPolicy.getRegisteredEditPolicies(obj);
                for (Iterator<?> it = editPolicies.iterator(); it.hasNext();) {

                    CanonicalEditPolicy nextEditPolicy = (CanonicalEditPolicy) it.next();

                    nextEditPolicy.refresh();
                }

                IDiagramGraphicalViewer graphViewer = ((IDiagramWorkbenchPart) activeEditor)
                        .getDiagramGraphicalViewer();
                graphViewer.flush();

                // Notify event listeners:
                for (ITransformationEventListener te : transformationEventListeners) {
                    te.transformationExecuted(new Object[] {obj, activeEditor });
                }

            }
        }
    }
}
