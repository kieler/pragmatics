/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.ksbase.ui.kivi;

import java.util.Map;

import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.internal.menus.DynamicMenuContributionItem;
import org.eclipse.ui.services.IEvaluationService;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.internal.KiviContributionItem;
import de.cau.cs.kieler.core.model.triggers.SelectionTrigger.DiagramSelectionState;
import de.cau.cs.kieler.core.model.triggers.SelectionTrigger.EObjectSelectionState;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;

/**
 * 
 * This combination while active will update the kieler toolbar every
 * time the selection in the editor is changed.
 * 
 * @author ckru
 * 
 */
public class UpdateVisibilityCombination extends AbstractCombination {

    IWorkbenchPart lastPart = null;

    Diagram lastDiagram = null;

    /**
     * {@inheritDoc}
     */
    public void execute(final EObjectSelectionState selection) {
        
        if (lastPart == selection.getWorkbenchPart()
                && selection.getWorkbenchPart() instanceof IEditorPart) {
            if ((selection.getWorkbenchPart() instanceof DiagramDocumentEditor)
                    && ((DiagramDocumentEditor) selection.getWorkbenchPart()).getDiagram() == lastDiagram) {
                update((IEditorPart) selection.getWorkbenchPart());

            } else {
                // update((IEditorPart)selection.getWorkbenchPart());
                lastPart = selection.getWorkbenchPart();
                lastDiagram = ((DiagramDocumentEditor) selection.getWorkbenchPart()).getDiagram();
            }
        } else {
            lastPart = selection.getWorkbenchPart();
        }
    
    }

    private void update(final IEditorPart editorPart) {
        IEvaluationService evaluationService = (IEvaluationService) editorPart.getEditorSite()
                .getService(IEvaluationService.class);

        evaluationService.requestEvaluation("activeEditorId");
        MonitoredOperation.runInUI(new Runnable() {

            public void run() {
                WorkbenchWindow b = (WorkbenchWindow) editorPart.getSite().getPage()
                        .getWorkbenchWindow();
                ToolBarContributionItem it = (ToolBarContributionItem) b.getCoolBarManager().find(
                        "de.cau.cs.kieler");
                DynamicMenuContributionItem x = (DynamicMenuContributionItem) it
                        .getToolBarManager().getItems()[0];
                // x.update(id)
                KiviContributionItem.setSoftUpdate(true);
                it.getToolBarManager().update(true);
                KiviContributionItem.setSoftUpdate(false);
            }
        }, false);
    }

}
