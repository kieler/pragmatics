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
package de.cau.cs.kieler.dataflow.diagram.part;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;

/**
 * @generated
 */
public class DataflowDiagramUpdateCommand implements IHandler {

    /**
     * @generated
     */
    public void addHandlerListener(IHandlerListener handlerListener) {
    }

    /**
     * @generated
     */
    public void dispose() {
    }

    /**
     * @generated
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getSelectionService().getSelection();
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            if (structuredSelection.size() != 1) {
                return null;
            }
            if (structuredSelection.getFirstElement() instanceof EditPart
                    && ((EditPart) structuredSelection.getFirstElement()).getModel() instanceof View) {
                EObject modelElement = ((View) ((EditPart) structuredSelection.getFirstElement())
                        .getModel()).getElement();
                List editPolicies = CanonicalEditPolicy.getRegisteredEditPolicies(modelElement);
                for (Iterator it = editPolicies.iterator(); it.hasNext();) {
                    CanonicalEditPolicy nextEditPolicy = (CanonicalEditPolicy) it.next();
                    nextEditPolicy.refresh();
                }

            }
        }
        return null;
    }

    /**
     * @generated
     */
    public boolean isEnabled() {
        return true;
    }

    /**
     * @generated
     */
    public boolean isHandled() {
        return true;
    }

    /**
     * @generated
     */
    public void removeHandlerListener(IHandlerListener handlerListener) {
    }

}
