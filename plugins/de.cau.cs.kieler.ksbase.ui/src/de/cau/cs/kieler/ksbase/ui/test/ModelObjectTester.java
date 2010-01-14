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
import java.util.List;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;

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
                List<String> match = t.getParameterList();
                if (match != null) {
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

}
