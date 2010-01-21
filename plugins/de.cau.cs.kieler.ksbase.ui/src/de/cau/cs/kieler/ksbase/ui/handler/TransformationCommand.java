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
package de.cau.cs.kieler.ksbase.ui.handler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.model.transformation.ITransformationFramework;

/**
 * The command to execute a transformation.
 * 
 * @author mim
 * 
 * @kieler.rating 2009-12-15 proposed yellow
 */
public class TransformationCommand extends AbstractTransactionalCommand {

    /** The component that handles execution of a transformation. **/
    private ITransformationFramework component;

    /**
     * Creates a command to execute a transformation.
     * 
     * @param domain
     *            the editing domain through which model changes are made
     * @param label
     *            the command label
     * @param adapter
     *            an adapter to the {@code View} of the base diagram
     */
    public TransformationCommand(final TransactionalEditingDomain domain, final String label,
            final IAdaptable adapter) {
        super(domain, label, null);
    }

    /**
     * Executes the command.
     * 
     * @see org.eclipse.gmf.runtime.emf.commands.core.command. AbstractTransactionalCommand
     *      #doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor,
     *      org.eclipse.core.runtime.IAdaptable)
     * 
     * @param monitor
     *            Progress monitor for the execution
     * @param info
     *            Additional informations for the command
     * @return Either an Error/Warning command result if the execution failed, or OK else
     * @throws ExecutionException
     *             if the Execution faild due to a critical error.
     */
    @Override
    protected CommandResult doExecuteWithResult(final IProgressMonitor monitor,
            final IAdaptable info) throws ExecutionException {

        component.executeTransformation();

        IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getActivePage().getActiveEditor();
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
        }
        return CommandResult.newOKCommandResult();
    }

    /**
     * Initializes the transformation.
     * 
     * @param editPart
     *            Current edit part
     * @param selection
     *            Current selection
     * @param command
     *            The command to execute
     * @param fileName
     *            Name of the transformation file
     * @param basePackage
     *            The package of the underlying meta model
     * @param parameter
     *            The parameters of the transformation method
     * @param framework
     *            The transformation framework to use for execution
     * @return False if an error occurred
     */
    public final boolean initalize(final IEditorPart editPart, final ISelection selection,
            final String command, final String fileName, final String basePackage,
            final String[] parameter, final ITransformationFramework framework) {
        component = framework;

        StructuredSelection s;

        if (selection instanceof StructuredSelection) {
            s = (StructuredSelection) selection;
        } else {
            return false;
        }

        StringBuffer modelSelection = new StringBuffer();

        // We need a modifiable list of the selection:
        LinkedList<Object> slist = new LinkedList<Object>();
        slist.addAll((List<?>) s.toList());
        Object[] parameters = new Object[slist.size()];

        // We have multiple options here:
        // 1. simply map the selected elements to the parameters
        // 2. the parameter is a collection or list type, so we are creating the
        // list
        // (Currently the only supported type is a single list of arbitrary
        // type)

        // Check if the first parameter is a list:
        /*
         * DEACTIVATED: List to List parameter bug if (parameter.length == 1 &&
         * parameter[0].contains("list")) {
         * 
         * 
         * int bStart = listType.indexOf('['); int bEnd = listType.indexOf(']'); if (bStart == -1 ||
         * bEnd == -1) { return false; } listType = listType.substring(bStart + 1, bEnd); // This
         * wont work: // Exception : List is not responsible for java type //
         * de.cau.cs.kieler.synccharts.State BasicEList<EObject> contents = new
         * BasicEList<EObject>();
         * 
         * for (int i = 0; i < slist.size(); ++i) { Object next = slist.get(i); if (next instanceof
         * EditPart) { Object model = ((EditPart) next).getModel(); if (model instanceof View) {
         * EObject selectedEObject = ((View) model).getElement(); if (selectedEObject
         * .eClass().getName().toLowerCase(Locale.getDefault()).equals( listType)) {
         * contents.add(selectedEObject); } } } } modelSelection.append("model0");
         * component.setContextData("model0", contents);
         * 
         * } else {
         */
        // Mapping parameters:
        int paramCount = 0;
        for (String param : parameter) {
            if (modelSelection.length() > 0) {
                modelSelection.append(",");
            }
            for (int i = 0; i < slist.size(); ++i) {
                Object next = slist.get(i);
                if (next instanceof EditPart) {
                    Object model = ((EditPart) next).getModel();
                    if (model instanceof View) {
                        if (((View) model).getElement().eClass().getName().equals(param)) {
                            parameters[paramCount++] = ((View) model).getElement();
                            slist.remove(i);
                            break;
                        }
                    }
                }
            }
        }
        // check if all parameters have been set
        if (paramCount != parameter.length) {
            return false;
        }


        component.initializeTransformation(fileName, command, basePackage, parameters);

        return true;
    }

}
