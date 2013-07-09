/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.ksbase.ui.kivi;

import java.util.List;

import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.kivi.triggers.SelectionTrigger.SelectionState;
import de.cau.cs.kieler.ksbase.ui.KSBasEUIPlugin;

/**
 * An interface that is used by KSBasE to retrieve selections and perform post transformation
 * actions. The {@link KSBasECombination} uses the {@link KSBasEUIPlugin} to find the best fitting
 * handler for the current context (via the
 * {@link KSBasEUIPlugin#getFittingKSBasEHandler(IWorkbenchPart, SelectionState)} method).
 * 
 * All handlers have to be registered via the de.cau.cs.kieler.ksbase.ui.handlers extension point.
 * 
 * @author uru
 */
public interface IKSBasEHandler {

    /**
     * @see #canHandle(IWorkbenchPart, SelectionState)
     */
    boolean canHandle(final IEvaluationContext context);

    /**
     * @see #getSelection(IWorkbenchPart, SelectionState)
     */
    List<EObject> getSelection(final IEvaluationContext context);

    /**
     * @param workbenchPart
     *            the workbench part to be tested, eg, a {@link IEditorPart}
     * @param selection
     *            the selection within the passed workbenchPart
     * @return true if this handler is able to handle the editor and selection
     */
    boolean canHandle(final IWorkbenchPart workbenchPart, final SelectionState selection);

    /**
     * 
     * @param workbenchPart
     *            the workbench part for which to retrieve the selection, eg, a {@link IEditorPart}
     * @param selection
     *            the selection within the passed workbenchPart
     * @return
     */
    List<EObject> getSelection(final IWorkbenchPart workbenchPart, final SelectionState selection);

    /**
     * Perform any post processing _after_ the model transformation was applied. For instance, GMF
     * editors might require their update policies to be refreshed.
     */
    void performPostProcessing();

    /**
     * @return true if a automatic layout should be performed after the ksbase action.
     */
    boolean isPerformLayout();

    /**
     * @return the root element for which layout should be applied if the {@link #isPerformLayout()}
     *         method returns true.
     */
    EObject getLayoutRoot();

    /**
     * @return the {@link TransactionalEditingDomain} of a currently used editor, or null if no
     *         editing domain is desired.
     */
    TransactionalEditingDomain getEditingDomain();

    /**
     * This method will be called after a transformation was executed.
     * 
     * @param transformationName
     *            the name of the executed transformation
     * @param parameters
     *            the parameters with which the transformation was executed
     * @param result
     *            the result of the transformation
     * @param select
     *            this element should be selected within the active editor
     */
    void transformationExecuted(String transformationName, Object[] parameters, Object result,
            EObject select);
}
