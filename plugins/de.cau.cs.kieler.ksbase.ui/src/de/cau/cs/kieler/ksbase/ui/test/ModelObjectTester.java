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
import java.util.Locale;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.ksbase.core.Transformation;
import de.cau.cs.kieler.ksbase.core.TransformationManager;

/**
 * A property tester which checks if a selected diagram object matches a given
 * model element.
 * 
 * @author Michael Matzen - mim AT informatik.uni-kiel.de
 * 
 */
public class ModelObjectTester extends PropertyTester {

    /**
     * Test method called by the eclipse menu framework when checking for menu
     * visibility. This is kind of a hack, because we are ignoring the given
     * items and using the PlatformUI class to get the current selection object.
     * This results in multiple calls for all selected object but is the only
     * good way to do this.
     * 
     * @param receiver
     *            The receiver object
     * @param property
     *            The property to check
     * @param args
     *            The arguments, in this case this has to be the
     *            array[Editor,Transformation]
     * @param expectedValue
     *            The expected value
     * @return True if all selected objects are matching to the current
     *         selection.
     */
    public boolean test(final Object receiver, final String property,
            final Object[] args, final Object expectedValue) {
        assert (args.length == 2);
        assert (args[0] instanceof String);
        assert (args[1] instanceof String);

        Transformation t = TransformationManager.INSTANCE.getEditorById(
                (String) args[0]).getTransformationById((String) args[1]);
        if (t != null) {
            List<String> match = t.getParameterList();
            if (match != null) {
                ISelection sel = PlatformUI.getWorkbench()
                        .getActiveWorkbenchWindow().getSelectionService()
                        .getSelection();
                if (sel instanceof StructuredSelection) {
                    Iterator<?> it = ((StructuredSelection) sel).iterator();
                    while (it.hasNext()) {
                        Object testingObject = it.next();
                        if (testingObject instanceof EditPart) {
                            Object model = ((EditPart) testingObject)
                                    .getModel();
                            if (model instanceof View) {
                                View vep = (View) model;
                                int idx = match.indexOf(vep.getElement()
                                        .eClass().getName().toLowerCase(
                                                Locale.getDefault()));
                                if (idx > -1) {
                                    match.remove(idx);
                                }
                            }
                        }
                    }
                }
                return match.isEmpty();
            }
        }
        return false;
    }

}
