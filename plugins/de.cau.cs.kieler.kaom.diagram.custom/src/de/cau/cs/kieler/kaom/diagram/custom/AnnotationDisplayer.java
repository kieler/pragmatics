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
 */

package de.cau.cs.kieler.kaom.diagram.custom;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest.ConnectionViewDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.diagram.ui.type.DiagramNotationType;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.ReferenceAnnotation;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kaom.Entity;

/**
 * Utility class for displaying some annotations of a diagram.
 * 
 * @author ckru
 * 
 */
public final class AnnotationDisplayer {

    /**
     * Its a utility class and should not be instanced.
     */
    private AnnotationDisplayer() {

    }

    /**
     * Displays some annotations of the given editor.
     * 
     * @param editorPart
     *            the EditorPart of the diagram whose annotations to display.
     */
    public static void displayAnnotations(final IEditorPart editorPart) {
        displayTextAsNote(editorPart);

    }

    /**
     * Displays Ptolemy comments from text annotations as eclipse notes.
     * 
     * @param editorPart
     *            the EditorPart of the diagram whose annotations to display.
     */
    private static void displayTextAsNote(final IEditorPart editorPart) {
        if (editorPart instanceof DiagramDocumentEditor) {
            final DiagramDocumentEditor dde = (DiagramDocumentEditor) editorPart;
            EObject rootElement = dde.getDiagramEditPart().getNotationView().getElement();
            if (rootElement instanceof Entity) {
                Entity rootEntity = (Entity) rootElement;
                // do it for all fitting annotations
                List<Pair<Annotation, EObject>> allTextAnnotations = getAllTextAnnotations(rootEntity);
                for (Pair<Annotation, EObject> ann : allTextAnnotations) {

                    final StringAnnotation textAnnotation = (StringAnnotation) ann.getFirst()
                            .getAnnotation("text");
                    final EditPart nodeParentPart = dde.getDiagramEditPart().findEditPart(
                            dde.getDiagramEditPart(), ann.getSecond());
                    // we want to add the note to the compartment
                    CompartmentEditPart compartmentPart = null;
                    for (Object child : nodeParentPart.getChildren()) {
                        if (child instanceof CompartmentEditPart) {
                            compartmentPart = (CompartmentEditPart) child;
                        }
                    }

                    if (compartmentPart != null) {

                        // make a request and a command for creating the note
                        ViewDescriptor viewDescriptor = new ViewDescriptor(null, Node.class,
                                DiagramNotationType.NOTE.getSemanticHint(), dde
                                        .getDiagramEditPart().getDiagramPreferencesHint());
                        final CreateViewRequest noteRequest = new CreateViewRequest(viewDescriptor);
                        final IAdaptable noteViewAdapter = (IAdaptable) ((List<?>) noteRequest
                                .getNewObject()).get(0);
                        Command createNoteCmd = compartmentPart.getCommand(noteRequest);

                        // execute the command
                        dde.getDiagramEditDomain().getDiagramCommandStack().execute(createNoteCmd);

                        // attach the note to something
                        Object adaptable = ((ViewDescriptor) noteViewAdapter)
                                .getAdapter(Shape.class);
                        if (adaptable instanceof Shape) {
                            Shape shape = (Shape) adaptable;
                            EditPartViewer viewer = dde.getDiagramGraphicalViewer();
                            Map<?, ?> epRegistry = viewer.getEditPartRegistry();
                            ReferenceAnnotation targetAnnotation = (ReferenceAnnotation) ann
                                    .getFirst().getAnnotation("attachedTo");
                            EditPart target = null;
                            if (targetAnnotation != null) {
                                EObject targetObject = targetAnnotation.getObject();
                                if (targetObject != null) {
                                    target = dde.getDiagramEditPart().findEditPart(
                                            dde.getDiagramEditPart(), targetObject);
                                    // target = (EditPart) epRegistry.get(targetObject);
                                }
                            }
                            IGraphicalEditPart source = (IGraphicalEditPart) epRegistry.get(shape);
                            if ((target != null) && (source != null)) {
                                ConnectionViewDescriptor connectionViewDescriptor
                                        = new ConnectionViewDescriptor(null,
                                        DiagramNotationType.NOTE_ATTACHMENT.getSemanticHint(), dde
                                                .getDiagramEditPart().getDiagramPreferencesHint());
                                CreateConnectionViewRequest createRequest
                                        = new CreateConnectionViewRequest(connectionViewDescriptor);
                                Command attachCmd = CreateConnectionViewRequest.getCreateCommand(
                                        createRequest, source, target);
                                dde.getDiagramEditDomain().getDiagramCommandStack()
                                        .execute(attachCmd);
                            }
                        }

                        // make an operation to throw the right text into our new note
                        AbstractEMFOperation emfOp = new AbstractEMFOperation(dde
                                .getDiagramEditPart().getEditingDomain(), "set Note text") {

                            @Override
                            protected IStatus doExecute(final IProgressMonitor monitor,
                                    final IAdaptable info) throws ExecutionException {
                                Object obj = noteRequest.getNewObject();
                                if (obj instanceof List<?>) {
                                    Object viewObj = ((List<?>) obj).get(0);
                                    if (viewObj instanceof ViewDescriptor) {
                                        Object adaptable = ((ViewDescriptor) viewObj)
                                                .getAdapter(Shape.class);
                                        if (adaptable instanceof Shape) {
                                            Shape shape = (Shape) adaptable;
                                            shape.setDescription(textAnnotation.getValue());
                                        }
                                    }
                                }
                                return Status.OK_STATUS;
                            }
                        };

                        // execute the operation
                        try {
                            IOperationHistory oh = OperationHistoryFactory.getOperationHistory();
                            oh.execute(emfOp, null, null);
                        } catch (ExecutionException e) { //
                            e.printStackTrace();
                        }

                    }

                }
            }
        }
    }

    /**
     * search for all annotations that represent a ptolemy comment.
     * 
     * @param root
     *            the entity to start looking from
     * @return a pair of the found annotation and the modelelement owning it
     */
    private static List<Pair<Annotation, EObject>> getAllTextAnnotations(final Entity root) {
        List<Pair<Annotation, EObject>> foundAnnotations = new LinkedList<Pair<Annotation, EObject>>();
        List<Annotation> annotationList = root.getAnnotations();
        for (Annotation annotation : annotationList) {
            if (annotation.getAnnotation("attachedTo") != null) {
                foundAnnotations.add(new Pair<Annotation, EObject>(annotation, root));
            }
        }
        for (Entity child : root.getChildEntities()) {
            foundAnnotations.addAll(getAllTextAnnotations(child));
        }
        return foundAnnotations;
    }
}
