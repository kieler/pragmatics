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
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.kivi.triggers.SelectionTrigger.SelectionState;

/**
 * @author uru
 * 
 */
public interface IKSBasEHandler {

    boolean canHandle(final IEvaluationContext context);

    List<EObject> getSelection(final IEvaluationContext context);

    boolean canHandle(final IWorkbenchPart workbenchPart, final SelectionState selection);

    List<EObject> getSelection(final IWorkbenchPart workbenchPart, final SelectionState selection);

    void performPostProcessing();

    boolean isPerformLayout();

    EObject getLayoutRoot();

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
