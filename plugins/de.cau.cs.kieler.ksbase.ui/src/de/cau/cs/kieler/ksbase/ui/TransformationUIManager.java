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

import de.cau.cs.kieler.core.model.gmf.util.GmfModelingUtil;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutEffect;
import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.KSBasETransformation;
import de.cau.cs.kieler.ksbase.core.TransformationFrameworkFactory;
import de.cau.cs.kieler.ksbase.ui.handler.ExecuteTransformationRequest;
import de.cau.cs.kieler.ksbase.ui.listener.ITransformationEventListener;
import de.cau.cs.kieler.ksbase.ui.utils.TransformationUtils;

/**
 * Transformation-UI manager. Handles creation and execution of commands and
 * notify of transformationEvent listeners
 * 
 * @author mim
 * 
 * @kieler.rating 2009-12-15 proposed yellow
 */
public final class TransformationUIManager {

    /** Transformation-UI instance. **/
    public static final TransformationUIManager INSTANCE = new TransformationUIManager();

    /**
     * The list of listeners to notify before and after transformation has been
     * executed.
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
    public void addTransformationListener(
            final ITransformationEventListener listener) {
        transformationEventListeners.add(listener);
    }

    /**
     * Removes a listener from the post-transformation listener queue.
     * 
     * @param listener
     *            The listener to remove.
     */
    public void removeTransformationListener(
            final ITransformationEventListener listener) {
        transformationEventListeners.remove(listener);
    }

    /**
     * Getter for the transformationEventListeners.
     * 
     * @return the transformationEventListeners
     */
    public LinkedList<ITransformationEventListener> getTransformationEventListeners() {
        return this.transformationEventListeners;
    }

    /**
     * Creates and executes a transformation command by creating a request and
     * execute the resulting command on the diagram command stack.
     * 
     * @param editorSettings
     *            The editor for which this transformation is
     * @param transformation
     *            The transformation that should be executed
     * @param selection
     *            A selection containing the edit parts that should be used.
     *            This may also be null, which indicates that the elements are
     *            selected automatically
     */
    public void createAndExecuteTransformationCommand(
            final EditorTransformationSettings editorSettings,
            final KSBasETransformation transformation,
            final List<EObject> selection) {
        // Notify event listeners:
        for (ITransformationEventListener te : transformationEventListeners) {
            te.transformationAboutToExecute(new Object[] {});
        }

        IEditorPart activeEditor = PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        EditPart selectedEditPart = null;
        if (selection != null) {
            selectedEditPart = GmfModelingUtil.getEditPart(selection.get(0));
        } else {
            // retrieve selection:
            ISelection sel = PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow().getSelectionService()
                    .getSelection();
            if (sel instanceof StructuredSelection) {
                Object selObj = ((StructuredSelection) sel).getFirstElement();
                if (selObj instanceof EditPart) {
                    selectedEditPart = (EditPart) selObj;
                } else {
                    // we only support edit parts here.
                    return;
                }

            } else {
                // Maybe we should support other selections but not right now.
                return;
            }

        }

        // If the given selection is null, we have to calculate the actual
        // mapping:
        List<Object> selectionMapping = null;
        if (selection == null) {
            if (transformation.getParameterList().size() == 0) {
                KSBasEUIPlugin.getDefault().logError(
                        "The Transformation "
                                + transformation.getTransformation()
                                + " does not have any parameters");
            } else {
                for (List<String> parameters : transformation
                        .getParameterList()) {
                    selectionMapping = TransformationFrameworkFactory
                            .getDefaultTransformationFramework()
                            .createParameterMapping(
                                    null,
                                    parameters.toArray(new String[parameters
                                            .size()]));
                    if (selectionMapping != null) {
                        break;
                    }
                }
                // FIXME what if selectionMapping is still null?
            }
        } else { // We need to convert the list from EObjects to objects
            selectionMapping = new LinkedList<Object>();
            selectionMapping.addAll(selection);
        }

        ExecuteTransformationRequest request = new ExecuteTransformationRequest(
                activeEditor, transformation.getTransformation(),
                editorSettings.getTransformationFile(), selectionMapping,
                editorSettings.getModelPackages(),
                editorSettings.getFramework());
        Command transformationCommand = selectedEditPart.getCommand(request);

        // gets a command stack to execute the command
        DiagramCommandStack commandStack = null;
        Object adapter = activeEditor.getAdapter(CommandStack.class);
        if (adapter instanceof DiagramCommandStack) {
            commandStack = (DiagramCommandStack) adapter;
        }
        if (commandStack == null) {
            commandStack = new DiagramCommandStack(
                    ((DiagramEditor) activeEditor).getDiagramEditDomain());
        }
        commandStack.execute(transformationCommand);

        // update edit policies, so GMF will generate diagram elements
        // for model elements which have been generated during the
        // transformation.

        if (activeEditor instanceof IDiagramWorkbenchPart) {
            EObject obj = ((View) ((IDiagramWorkbenchPart) activeEditor)
                    .getDiagramEditPart().getModel()).getElement();

            List<?> editPolicies = CanonicalEditPolicy
                    .getRegisteredEditPolicies(obj);
            for (Iterator<?> it = editPolicies.iterator(); it.hasNext();) {

                CanonicalEditPolicy nextEditPolicy = (CanonicalEditPolicy) it
                        .next();

                nextEditPolicy.refresh();
            }

            IDiagramGraphicalViewer graphViewer = ((IDiagramWorkbenchPart) activeEditor)
                    .getDiagramGraphicalViewer();
            graphViewer.flush();

            // Notify event listeners:
            for (ITransformationEventListener te : transformationEventListeners) {
                te.transformationExecuted(new Object[] { obj, activeEditor });
            }

            //do a layout in the end
            LayoutEffect effect = new LayoutEffect(activeEditor, (EObject)((IDiagramWorkbenchPart) activeEditor).getDiagramEditPart().getModel(), false);
            effect.schedule();
        }
    }
}
