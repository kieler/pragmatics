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
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.menu.ButtonTrigger.ButtonState;
import de.cau.cs.kieler.core.kivi.triggers.SelectionTrigger.SelectionState;
import de.cau.cs.kieler.kiml.kivi.LayoutEffect;
import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.KSBasETransformation;
import de.cau.cs.kieler.ksbase.core.TransformationFrameworkFactory;
import de.cau.cs.kieler.ksbase.m2m.ITransformationListener;
import de.cau.cs.kieler.ksbase.m2m.TransformationDescriptor;
import de.cau.cs.kieler.ksbase.m2m.TransformationObserver;
import de.cau.cs.kieler.ksbase.ui.KSBasEUIPlugin;
import de.cau.cs.kieler.ksbase.ui.m2m.XtendTransformationContext;
import de.cau.cs.kieler.ksbase.ui.m2m.XtendTransformationEffect;

/**
 * A Combination triggering the KSBasE transformations from kivi menu contributions.
 * 
 * @author ckru
 * 
 */
public class KSBasECombination extends AbstractCombination implements ITransformationListener {

    private EditorTransformationSettings editorSettings;

    private HashMap<String, KSBasETransformation> transformations =
            new HashMap<String, KSBasETransformation>();

    // private static DiagramDocumentEditor lastEditor = null;
    private static IKSBasEHandler activeHandler = null;

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
    public void execute(final ButtonState button, final SelectionState selection) { // final
                                                                                    // EObjectSelectionState
                                                                                    // selection) {
        // don't perform transformation if only selection changed.
        if (button.getSequenceNumber() > selection.getSequenceNumber()) {
            KSBasETransformation transformation = transformations.get(button.getButtonId());

            IEditorPart editor = button.getEditor();
            executeTransformation(transformation, editor, selection);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void executeTransformation(final KSBasETransformation transformation,
            final IEditorPart editor, final SelectionState selection) {
        // don't perform transformation if only selection changed.
        if (transformation != null) {

            // update the corresponding handler
            activeHandler = KSBasEUIPlugin.getDefault().getFittingKSBasEHandler(editor, selection);
            if (activeHandler == null) {
                StatusManager.getManager().handle(
                        new Status(Status.WARNING, KSBasEUIPlugin.PLUGIN_ID,
                                "Could not find a proper KSBasE handler."), StatusManager.SHOW);
                return;
            }

            List<EObject> selectionList = activeHandler.getSelection(editor, selection);

            // do xtend2 stuff
            if (transformation.getTransformationClass() != null) {
                evokeXtend2(transformation, selectionList);

                // do xtend1 stuff
            } else {
                // map the selection to the parameters of this transformation
                List<Object> selectionMapping = null;
                for (List<String> parameters : transformation.getParameterList()) {
                    selectionMapping =
                            TransformationFrameworkFactory.getDefaultTransformationFramework()
                                    .createParameterMapping(selectionList,
                                            parameters.toArray(new String[parameters.size()]));
                }
                // execute xtend transformation
                if (selectionMapping != null) {
                    evokeXtend(transformation, selectionMapping);
                    // refreshEditPolicy(diagramEditor);
                }
            }

            // arbitrary post processing, eg, refreshing editing policies
            activeHandler.performPostProcessing();

            // possibly execute layout
            if (activeHandler.isPerformLayout()) {
                EObject rootObject = activeHandler.getLayoutRoot();
                evokeLayout(selectionList, rootObject, editor);
            }
        }
    }

    /**
     * Helper method for xtend2 to bring the current selection to a form we can easier pass as
     * parameters.
     * 
     * @param selection
     *            the current selection
     * @return the current selection of a hashmap with type as key and proposed parameter as value
     */
    // Can't check for generics.
    @SuppressWarnings("unchecked")
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
            final List<EObject> selection) {
        Method method = null;
        List<Object> params = new LinkedList<Object>();
        // find the right method to execute in the xtend2 transformation class
        for (Method m : transformation.getTransformationClass().getClass().getMethods()) {
            if (m.getName().equals(transformation.getTransformation())) {
                HashMap<Object, Object> selectionCache = this.getSelectionHash(selection);
                params = new LinkedList<Object>();
                method = m;
                int index = 0;
                // int parameterindex = 0;
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
                    // parameterindex++;

                }

                if (method != null) {
                    break;
                }

            }
        }
        // if you found a fitting method execute it
        if (method != null) {
            try {
                if (activeHandler.getEditingDomain() != null) {

                    final Method fmethod = method;
                    final List<Object> fparams = params;
                    AbstractEMFOperation emfOp =
                            new AbstractEMFOperation(activeHandler.getEditingDomain(),
                                    "xtend2 transformation", Collections.singletonMap(
                                            Transaction.OPTION_UNPROTECTED, true)) {

                                @Override
                                protected IStatus doExecute(final IProgressMonitor monitor,
                                        final IAdaptable info) throws ExecutionException {
                                    try {
                                        fmethod.invoke(transformation.getTransformationClass(),
                                                fparams.toArray());
                                    } catch (IllegalArgumentException e) {
                                        e.printStackTrace();
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    } catch (InvocationTargetException e) {
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
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
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
            final List<Object> selectionMapping) {
        TransformationDescriptor descriptor =
                new TransformationDescriptor(transformation.getTransformation(),
                        selectionMapping.toArray());
        XtendTransformationContext context =
                new XtendTransformationContext(editorSettings.getTransformationFile(),
                        editorSettings.getModelPackages().toArray(
                                new String[editorSettings.getModelPackages().size()]), null,
                        activeHandler.getEditingDomain());
        XtendTransformationEffect effect = new XtendTransformationEffect(context, descriptor);
        effect.schedule();
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
            final IEditorPart editor) {
        LayoutEffect layout = null;
        if (selectionList.get(0) == rootObject) {
            layout = new LayoutEffect(editor, rootObject, false);
        } else {
            layout = new LayoutEffect(editor, rootObject, false);
        }
        layout.schedule();
    }

    /**
     * {@inheritDoc}
     */
    public void transformationExecuted(final String transformationName, final Object[] parameters,
            final Object result) {
        activeHandler.transformationExecuted(transformationName, parameters, result, select);
    }
    
    
    /**
     * CARE: this method is called reflectively by xtend transformations.
     * 
     * Set an object that will be selected after the next transformation has finished executing.
     * 
     * @param obj
     *            the object to be selected
     */
    public static void selectObject(final Object obj) {
        if (obj instanceof EObject) {
            select = (EObject) obj;
        }
    }

}
