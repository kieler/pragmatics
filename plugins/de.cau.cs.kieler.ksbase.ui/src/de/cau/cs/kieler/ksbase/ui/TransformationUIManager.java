/**
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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
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
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.Transformation;
import de.cau.cs.kieler.ksbase.ui.handler.ExecuteTransformationRequest;
import de.cau.cs.kieler.ksbase.ui.listener.ITransformationEventListener;

/**
 * Transformation-UI manager. Handles creation and execution of commands and
 * notify of transformationEvent listeners
 * 
 * @author Michael Matzen - mim AT informatik.uni-kiel.de
 * 
 */
public final class TransformationUIManager {

    /** Transformation-UI instance. **/
    public static final TransformationUIManager INSTANCE = new TransformationUIManager();
    /**
     * The list of listeners to notify after a transformation has been executed.
     **/
    private LinkedList<ITransformationEventListener> postTransformationEventListeners;

    /**
     * The default constructor.
     */
    private TransformationUIManager() {
        postTransformationEventListeners = new LinkedList<ITransformationEventListener>();
    }

    /**
     * Adds a listener to the transformation listener queue.
     * 
     * @param listener
     *            The listener to add
     */
    public void addPostTransformationListener(final ITransformationEventListener listener) {
        postTransformationEventListeners.add(listener);
    }

    /**
     * Removes a listener from the listener queue.
     * 
     * @param listener
     *            The listener to remove.
     */
    public void removePostTransformationListener(final ITransformationEventListener listener) {
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
    public void createAndExecuteTransformationCommand(
            final ExecutionEvent event, final EditorTransformationSettings editor,
            final Transformation transformation) {

        IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);
        // System.out.println("Diag childs (pre): " +
        // ((DiagramEditor)activeEditor).getDiagram().getVisibleChildren().size());
        ISelection selection =
                HandlerUtil.getActiveWorkbenchWindow(event).getSelectionService().getSelection();

        if (selection instanceof StructuredSelection && !selection.isEmpty()) {

            // First, we have to store the transformation file because Xtend
            // doesn't execute strings
            // We have to do this every time, because we can't be sure that the
            // file we wrote last time is still valid. We will write it
            // to the meta-inf folder:

            EditPart selectedElement =
                    (EditPart) ((StructuredSelection) selection).getFirstElement();

            File file = null;
            FileOutputStream out = null;
            try {
                IPath path = ResourcesPlugin.getPlugin().getStateLocation();
                file = File.createTempFile("extension", ".ext", new File(path.toOSString()));
                out = new FileOutputStream(file);
                if (!file.exists()) {
                    if (!file.createNewFile()) {
                        KSBasEUIPlugin.getDefault().getLog().log(
                                new Status(IStatus.ERROR, KSBasEUIPlugin.PLUGIN_ID,
                                        "Xtend could not be stored."));
                        return;
                    }
                }

                out.write(editor.getExtFile().getBytes());
                out.flush();
                out.close();

                // Create request
                ExecuteTransformationRequest request =
                        new ExecuteTransformationRequest(activeEditor, transformation
                                .getTransformationName(), file.getAbsolutePath(), selection, editor
                                .getModelPackageClass(), transformation.getParameter());

                Command transformationCommand = selectedElement.getCommand(request);

                // gets a command stack to execute the command
                DiagramCommandStack commandStack = null;
                Object adapter = activeEditor.getAdapter(CommandStack.class);
                if (adapter instanceof DiagramCommandStack) {
                    commandStack = (DiagramCommandStack) adapter;
                }
                if (commandStack == null) {
                    commandStack =
                            new DiagramCommandStack(((DiagramEditor) activeEditor)
                                    .getDiagramEditDomain());
                }
                commandStack.execute(transformationCommand);
            } catch (FileNotFoundException e) {
                KSBasEUIPlugin.getDefault().getLog().log(
                        new Status(IStatus.ERROR, KSBasEUIPlugin.PLUGIN_ID,
                                "Xtend file not found."));
            } catch (IOException e) {
                KSBasEUIPlugin.getDefault().getLog().log(
                        new Status(IStatus.ERROR, KSBasEUIPlugin.PLUGIN_ID,
                                "Xtend file could not be read."));
            } finally {

                // Remove temporary Xtend file
                if (file != null) {
                    if (!file.delete()) {
                        KSBasEUIPlugin.getDefault().getLog().log(
                                new Status(IStatus.WARNING, KSBasEUIPlugin.PLUGIN_ID,
                                        "Could not remove temporary file."));
                    }
                }
                // Close stream
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        //ignoring nested exception
                    }
                }

                // update edit policies, so GMF will generate diagram elements
                // for model elements which have been generated during the
                // transformation but
               
                if (activeEditor instanceof IDiagramWorkbenchPart) {
                    EObject obj =
                            ((View) ((IDiagramWorkbenchPart) activeEditor).getDiagramEditPart().getModel())
                                    .getElement();

                    List<?> editPolicies = CanonicalEditPolicy.getRegisteredEditPolicies(obj);
                    for (Iterator<?> it = editPolicies.iterator(); it.hasNext();) {

                        CanonicalEditPolicy nextEditPolicy = (CanonicalEditPolicy) it.next();

                        nextEditPolicy.refresh();
                    }

                    IDiagramGraphicalViewer graphViewer =
                            ((IDiagramWorkbenchPart) activeEditor).getDiagramGraphicalViewer();
                    graphViewer.flush();
                 
                    // Notify event listeners:
                    for (ITransformationEventListener te : postTransformationEventListeners) {
                        te.transformationExecuted(new Object[] {
                                obj, activeEditor });
                    }
                    
                }

            }
        }
    }
}
