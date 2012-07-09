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

import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.services.IEvaluationService;

import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.core.kivi.internal.KiviContributionItem;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;

/**
 * This effect will request a reevaluation of all Visibility expressions and update the
 * toolbar accordingly.
 * 
 * @author ckru
 *
 */
//Needs some internal eclipse classes since it does internal stuff after all.
@SuppressWarnings("restriction")
public class UpdateVisibilityEffect extends AbstractEffect {

    /**
     * the current editor.
     */
    private IEditorPart editorPart;
    
    /**
     * the constructor.
     * @param editorPart the current editor
     */
    public UpdateVisibilityEffect(final IEditorPart editorPart) {
        this.editorPart = editorPart;
    }
    
    /**
     * {@inheritDoc}
     */
    public void execute() {
        final IEvaluationService evaluationService = (IEvaluationService) editorPart.getEditorSite()
                .getService(IEvaluationService.class);

        
        MonitoredOperation.runInUI(new Runnable() {

            public void run() {
                evaluationService.requestEvaluation("activeEditorId");
                WorkbenchWindow b = (WorkbenchWindow) editorPart.getSite().getPage()
                        .getWorkbenchWindow();
                ToolBarContributionItem it = (ToolBarContributionItem) b.getCoolBarManager().find(
                        "de.cau.cs.kieler");
                KiviContributionItem.setSoftUpdate(true);
                it.getToolBarManager().update(true);
                KiviContributionItem.setSoftUpdate(false);
            }
        }, false);
    }

}
