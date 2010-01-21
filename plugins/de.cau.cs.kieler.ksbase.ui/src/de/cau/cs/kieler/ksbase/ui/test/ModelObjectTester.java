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
package de.cau.cs.kieler.ksbase.ui.test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.model.transformation.ITransformationFramework;
import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.KSBasETransformation;
import de.cau.cs.kieler.ksbase.core.TransformationManager;

/**
 * A property tester which checks if a selected diagram object matches a given model element.
 * 
 * @author mim
 * 
 * @kieler.rating 2009-12-15 proposed yellow
 */
public class ModelObjectTester extends PropertyTester {

    /**
     * Test method called by the eclipse menu framework when checking for menu visibility. This is
     * kind of a hack, because we are ignoring the given items and using the PlatformUI class to get
     * the current selection object. This results in multiple calls for all selected object but is
     * the only good way to do this.
     * 
     * @param receiver
     *            The receiver object
     * @param property
     *            The property to check
     * @param args
     *            The arguments, in this case this has to be the array[Editor,Transformation]
     * @param expectedValue
     *            The expected value
     * @return True if all selected objects are matching to the current selection.
     */
    public boolean test(final Object receiver, final String property, final Object[] args,
            final Object expectedValue) {
        assert (args.length == 2);
        assert (args[0] instanceof String);
        assert (args[1] instanceof String);
        EditorTransformationSettings editor = TransformationManager.INSTANCE
                .getEditorById((String) args[0]);
        if (editor != null) {
            KSBasETransformation t = editor.getTransformationById((String) args[1]);
            if (t != null) {
                // Convert selection to model elements:
                List<EObject> modelElements = getModelElementsFromSelection();
                // First we will evaluate the validation transformation
                // This is a fast operation, test took less than 30ms, so it can be assumed as 'fast
                // enough'.
                // But the actual time depends on the transformation to be executed here, so better
                // use simple & fast ones :)
                String validation = t.getValidation();
                if (validation != null && validation.length() > 0 && modelElements.size() > 0) {
                    if (!evaluateValidation(editor, t, modelElements
                            .toArray(new Object[modelElements.size()]))) {
                        return false;
                    }
                }
                List<String> match = t.getParameterList();
                if (match != null && modelElements.size() > 0) {
                    ISelection sel = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                            .getSelectionService().getSelection();
                    if (sel instanceof StructuredSelection) {
                        Iterator<?> it = ((StructuredSelection) sel).iterator();
                        // Ok we wan't to have to possible options here:
                        // 1. having exactly one list parameter, list<State>
                        // 2. having parameters like State,State,Region

                        // TODO: What about collections and sets?
                        // Maybe we could support multiple lists with different
                        // types too.

                        if (match.size() == 1 && match.get(0).contains("list")) {
                            String listType = match.get(0);
                            int bStart = listType.indexOf('[');
                            int bEnd = listType.indexOf(']');
                            if (bStart == -1 || bEnd == -1) {
                                return false;
                            }
                            listType = listType.substring(bStart + 1, bEnd);
                            // Ok now we need to check if all selected elements
                            // are
                            // of the given type.
                            while (it.hasNext()) {
                                Object testingObejct = it.next();
                                if (testingObejct instanceof EditPart) {
                                    Object model = ((EditPart) testingObejct).getModel();
                                    if (model instanceof View) {
                                        if (!((View) model).getElement().eClass().getName().equals(
                                                listType)) {
                                            return false;
                                        }
                                    }
                                }
                            }
                            // When we reached this part, all selected objects
                            // are
                            // of the given type, so we will return 'true' here
                            return true;
                        }

                        while (it.hasNext()) {
                            Object testingObject = it.next();
                            if (testingObject instanceof EditPart) {
                                Object model = ((EditPart) testingObject).getModel();
                                if (model instanceof View) {
                                    View vep = (View) model;
                                    if (vep.getElement() != null
                                            && vep.getElement().eClass() != null) {
                                        int idx = match
                                                .indexOf(vep.getElement().eClass().getName());
                                        if (idx > -1) {
                                            match.remove(idx);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return match.isEmpty();
                }
            }
        }
        return false;
    }

    private boolean evaluateValidation(final EditorTransformationSettings editor,
            final KSBasETransformation t, final Object... param) {
        Boolean result = false;

        ITransformationFramework framework = editor.getFramework();
        if (!framework.initializeTransformation(editor.getTransformationFile(), t.getValidation(),
                editor.getModelPackageClass(), param)) {
            return false;
        }
        Object res = framework.executeTransformation();
        if (result instanceof Boolean) {
            result = (Boolean) res;
        } else {
            result = false;
        }

        return result;
    }

    private LinkedList<EObject> getModelElementsFromSelection() {
        if (PlatformUI.getWorkbench() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService() != null) {
            ISelection sel = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                    .getSelectionService().getSelection();
            LinkedList<EObject> eo = new LinkedList<EObject>();
            if (sel instanceof StructuredSelection) {
                Iterator<?> it = ((StructuredSelection) sel).iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next instanceof EditPart) {
                        Object model = ((EditPart) next).getModel();
                        if (model instanceof View) {
                            eo.add(((View) model).getElement());
                        }
                    }
                }
            }
            return eo;
        } else {
            return null;
        }
    }
}
