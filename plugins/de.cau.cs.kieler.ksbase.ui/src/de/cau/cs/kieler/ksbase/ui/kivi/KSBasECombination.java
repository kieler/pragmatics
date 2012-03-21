/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.ksbase.ui.kivi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.core.kivi.menu.ButtonTrigger.ButtonState;
import de.cau.cs.kieler.core.model.m2m.ITransformationListener;
import de.cau.cs.kieler.core.model.m2m.TransformationDescriptor;
import de.cau.cs.kieler.core.model.m2m.TransformationObserver;
import de.cau.cs.kieler.core.model.triggers.SelectionTrigger.EObjectSelectionState;
import de.cau.cs.kieler.core.model.xtend.m2m.XtendTransformationContext;
import de.cau.cs.kieler.core.model.xtend.m2m.XtendTransformationEffect;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutEffect;
import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.KSBasETransformation;
import de.cau.cs.kieler.ksbase.core.TransformationFrameworkFactory;

/**
 * A Combination triggering the KSBasE transformations from kivi menu contributions.
 * 
 * @author ckru
 * 
 */
public class KSBasECombination extends AbstractCombination implements ITransformationListener {

    private EditorTransformationSettings editorSettings;

    private HashMap<String, KSBasETransformation> transformations = new HashMap<String, KSBasETransformation>();

    private static DiagramDocumentEditor lastEditor = null;
    
    private static EObject select = null;

    /**
     * @param editorSettings
     *            the KSBasE editor settings used as a context for the transformation.
     */
    public KSBasECombination(final EditorTransformationSettings editorSettings) {
        this.editorSettings = editorSettings;
        this.setActive(true);
        TransformationObserver.getInstance().register(this);
    }

    /**
     * Add a transformation to the combination.
     * 
     * @param buttonID
     *            the id to identify the transformation
     * @param transformation
     *            the transformation to add
     */
    public void addTransformation(final String buttonID, final KSBasETransformation transformation) {
        transformations.put(buttonID, transformation);
    }

    /**
     * {@inheritDoc}
     */
    public void execute(final ButtonState button, final EObjectSelectionState selection) {
        // don't perform transformation if only selection changed.
        if (button.getSequenceNumber() > selection.getSequenceNumber()) {
            KSBasETransformation transformation = transformations.get(button.getButtonId());
            if (transformation != null) {
                IEditorPart editor = button.getEditor();
                List<EObject> selectionList = new ArrayList<EObject>();

                if (editor instanceof DiagramDocumentEditor) {
                    final DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor) editor;
                    lastEditor = (DiagramDocumentEditor) editor;
                    List<EditPart> selectedParts = diagramEditor.getDiagramGraphicalViewer()
                            .getSelectedEditParts();
                    EditPart root = diagramEditor.getDiagramGraphicalViewer().getRootEditPart();
                    IGraphicalEditPart groot = (IGraphicalEditPart) root.getChildren().get(0);
                    EObject rootObject = groot.getNotationView().getElement();
                    // get the current selection
                    for (EditPart part : selectedParts) {
                        if (part instanceof IGraphicalEditPart) {
                            IGraphicalEditPart gpart = (IGraphicalEditPart) part;
                            selectionList.add(gpart.getNotationView().getElement());
                        }
                    }
                    // if the selection is empty assume the root object as selected
                    if (selectionList.isEmpty()) {
                        selectionList.add(rootObject);
                    }
                    // do xtend2 stuff
                    if (transformation.getTransformationClass() != null) {
                        evokeXtend2(transformation, selectionList, diagramEditor);
                        refreshEditPolicy(diagramEditor);
                        evokeLayout(selectionList, rootObject, button);
                        // setSelection(TransformationUtils.getPostTransformationSelection(),
                        // editor);
                        // do xtend1 stuff
                    } else {
                        // map the selection to the parameters of this transformation
                        List<Object> selectionMapping = null;
                        for (List<String> parameters : transformation.getParameterList()) {
                            selectionMapping = TransformationFrameworkFactory
                                    .getDefaultTransformationFramework().createParameterMapping(
                                            selectionList,
                                            parameters.toArray(new String[parameters.size()]));
                        }
                        // execute xtend transformation
                        if (selectionMapping != null) {
                            EditPart selectedPart = diagramEditor.getDiagramEditPart()
                                    .findEditPart(null, selectionList.get(0));
                            evokeXtend(transformation, selectionMapping, diagramEditor);
                            // refreshEditPolicy(diagramEditor);
                            evokeLayout(selectionList, rootObject, button);

                            // if (!selectionList.isEmpty()) {
                            // EditPart selectPart = diagramEditor.getDiagramEditPart()
                            // .findEditPart(null, selectionList.get(0));
                            // if (selectPart != null) {
                            // setSelection(selectionList.get(0), editor, selectPart);
                            // } else {
                            // selectPart = diagramEditor.getDiagramEditPart().findEditPart(
                            // null, selectionList.get(0).eContainer());
                            // if (selectPart != null) {
                            // setSelection(selectionList.get(0).eContainer(), editor,
                            // selectPart);
                            // }
                            // }
                            // }

                        }
                    }

                } else { // editor is no Diagram Editor
                         // do xtend2 stuff
                    if (transformation.getTransformationClass() != null) {
                        evokeXtend2(transformation, selection.getSelectedObjects(), null);
                    }
                }
            }
        }
    }

    public void setSelection(final EObject obj, final IEditorPart editor, final EditPart part) {

        MonitoredOperation.runInUI(new Runnable() {

            public void run() {
                // if (obj != KSBasECombination.this.lastSelection) {
                try {
                    editor.getEditorSite().getSelectionProvider()
                            .setSelection(new StructuredSelection(part));
                } catch (Exception e) {

                }
                // }

            }
        }, false);
    }

    /**
     * Helper method for xtend2 to bring the current selection to a form we can easier pass as
     * parameters.
     * 
     * @param selection
     *            the current selection
     * @return the current selection of a hashmap with type as key and proposed parameter as value
     */
    private HashMap<Object, Object> getSelectionHash(final List<EObject> selection) {
        HashMap<Object, Object> selectionCache = new HashMap<Object, Object>();
        for (EObject obj : selection) {
            Object cache = selectionCache.get(obj.getClass());
            List<Object> listCache;
            if (cache == null) {
                listCache = new LinkedList<Object>();
                selectionCache.put(obj.getClass(), listCache);
                listCache.add(obj);
            } else if (cache instanceof List<?>) {
                listCache = (List<Object>) cache;
                listCache.add(obj);

            }
        }
        /*
         * // a cache to eliminate concurrent modification error List<Object> cache = new
         * LinkedList<Object>(); cache.addAll(selectionCache.values()); // Also put the element of a
         * list of length = 1 in there for non list single object // parameters. for (Object obj :
         * cache) { if (obj instanceof List) { if (((List<?>) obj).size() == 1) {
         * selectionCache.put(((List<?>) obj).get(0), ((List<?>) obj).get(0)); } } }
         */
        return selectionCache;
    }

    /**
     * Method to execute the given xtend2 transformation.
     * 
     * @param transformation
     *            the xtend2 transformation to execute
     * @param selection
     *            the current selection
     */
    private void evokeXtend2(final KSBasETransformation transformation,
            final List<EObject> selection, DiagramDocumentEditor editor) {
        Method method = null;
        List<Object> params = new LinkedList<Object>();
        // find the right method to execute in the xtend2 transformation class
        for (Method m : transformation.getTransformationClass().getClass().getMethods()) {
            if (m.getName().equals(transformation.getTransformation())) {
                HashMap<Object, Object> selectionCache = this.getSelectionHash(selection);
                params = new LinkedList<Object>();
                method = m;
                int index = 0;
                int parameterindex = 0;
                for (Type t : m.getGenericParameterTypes()) {
                    Object param = null;
                    for (Object p : selectionCache.values()) {
                        if (this.match(t, p) && !params.contains(p)) {
                            param = p;
                            break;
                        } else if ((p instanceof List) && (((List<?>) p).size() >= index + 1)
                                && match(t, ((List<?>) p).get(index))) {
                            param = ((List<?>) p).get(index);
                            index++;
                            break;
                        }
                    }
                    if (param != null) {
                        params.add(param);
                    } else {
                        method = null;
                    }
                    parameterindex++;

                }

                if (method != null) {
                    break;
                }

            }
        }
        // if you found a fitting method execute it
        if (method != null) {
            try {
                if (editor != null) {

                    final Method fmethod = method;
                    final List<Object> fparams = params;
                    AbstractEMFOperation emfOp = new AbstractEMFOperation(
                            editor.getEditingDomain(), "xtend2 transformation",
                            Collections.singletonMap(Transaction.OPTION_UNPROTECTED, true)) {

                        @Override
                        protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info)
                                throws ExecutionException {
                            try {
                                fmethod.invoke(transformation.getTransformationClass(),
                                        fparams.toArray());
                            } catch (IllegalArgumentException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            return Status.OK_STATUS;
                        }

                    };
                    try {
                        // execute above operation
                        OperationHistoryFactory.getOperationHistory().execute(emfOp, null, null);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                } else {
                    method.invoke(transformation.getTransformationClass(), params.toArray());
                }
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    /**
     * A helper method for xtend2 to determine whether an object matches a certain type.
     * 
     * @param a
     *            the type. Likely the type of a parameter.
     * @param b
     *            the objects whose matchability to test
     * @return true if a matches the type of b else false
     */
    private boolean match(final Type a, final Object b) {
        if (a instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) a).getRawType();
            if (rawType instanceof Class) {
                Class<?> c1 = (Class<?>) rawType;
                Class<?> c2 = b.getClass();
                if (c1.isAssignableFrom(c2)) {
                    // if its a list also check generics
                    if (c1.isAssignableFrom(List.class) && b instanceof List) {
                        for (Type actualType : ((ParameterizedType) a).getActualTypeArguments()) {
                            if (actualType instanceof Class) {
                                Class<?> c3 = (Class<?>) actualType;
                                Class<?> c4 = ((List<?>) b).get(0).getClass();
                                if (c3.isAssignableFrom(c4)) {
                                    return true;
                                }
                            }
                        }

                    } else {
                        return true;
                    }
                }
            }
        } else if (a instanceof Class) {
            Class<?> c1 = (Class<?>) a;
            Class<?> c2 = b.getClass();
            if (c1.isAssignableFrom(c2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to execute a given Xtend1 transformation.
     * 
     * @param transformation
     *            the transformation to execute
     * @param selectionMapping
     *            the current selection
     * @param diagramEditor
     *            the current diagram editor
     */
    private void evokeXtend(final KSBasETransformation transformation,
            final List<Object> selectionMapping, final DiagramDocumentEditor diagramEditor) {
        TransformationDescriptor descriptor = new TransformationDescriptor(
                transformation.getTransformation(), selectionMapping.toArray());
        XtendTransformationContext context = new XtendTransformationContext(
                editorSettings.getTransformationFile(), editorSettings.getModelPackages().toArray(
                        new String[editorSettings.getModelPackages().size()]), null,
                diagramEditor.getEditingDomain());
        XtendTransformationEffect effect = new XtendTransformationEffect(context, descriptor);
        effect.schedule();
    }

    /**
     * Method to refresh the CanonicalEditPolicy to show the changes done by a transformation.
     * 
     * @param diagramEditor
     *            the current diagram editor.
     */
    private void refreshEditPolicy(final DiagramDocumentEditor diagramEditor) {
        AbstractEffect refresh = new AbstractEffect() {
            public void execute() {
                CanonicalEditPolicy policy = (CanonicalEditPolicy) diagramEditor
                        .getDiagramEditPart().getEditPolicy("Canonical");
                if (policy != null) {
                    policy.refresh();
                }
            }
        };
        refresh.schedule();
    }

    /**
     * Method to execute the layout so that it adapts to recent changes done by a transformation.
     * 
     * @param selectionList
     *            the current selection
     * @param rootObject
     *            the root element to do the layout on if nothing is selected.
     * @param button
     *            the button triggering this combination to get the editor from
     */
    private void evokeLayout(final List<EObject> selectionList, final EObject rootObject,
            final ButtonState button) {
        LayoutEffect layout = null;
        if (selectionList.get(0) == rootObject) {
            layout = new LayoutEffect(button.getEditor(), rootObject, false);
        } else {
            layout = new LayoutEffect(button.getEditor(), rootObject, false);
        }
        layout.schedule();
    }

    public void transformationExecuted(String transformationName, Object[] parameters, Object result) {
        // TODO Auto-generated method stub
        //this.refreshEditPolicy(lastEditor);
        CanonicalEditPolicy policy = (CanonicalEditPolicy) lastEditor
                .getDiagramEditPart().getEditPolicy("Canonical");
        if (policy != null) {
            policy.refresh();
        }
        if (!((parameters == null) || (parameters.length == 0))) {
            EObject object = null;
            if (parameters[0] instanceof List) {
                List<?> firstParameter = (List<?>) parameters[0];
                if (!firstParameter.isEmpty() && firstParameter.get(0) instanceof EObject) {
                    object = (EObject) firstParameter.get(0);
                    object = select;
                    
                }
            } else if (parameters[0] instanceof EObject) {
                object = (EObject) parameters[0];
            }
            if (object != null) {
                object = select;
                EditPart selectPart = lastEditor.getDiagramEditPart().findEditPart(null, object);
                if (selectPart != null) {
                    setSelection(object, lastEditor, selectPart);
                }
            }
        }

    }
    
    public static void selectObject(final Object obj) {
        if (obj instanceof EObject) {
            select = (EObject) obj;
        }
    }

}
