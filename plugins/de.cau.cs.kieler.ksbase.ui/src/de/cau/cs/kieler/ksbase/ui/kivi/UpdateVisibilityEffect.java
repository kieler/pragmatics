package de.cau.cs.kieler.ksbase.ui.kivi;

import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.internal.menus.DynamicMenuContributionItem;
import org.eclipse.ui.services.IEvaluationService;

import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.core.kivi.internal.KiviContributionItem;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;

public class UpdateVisibilityEffect extends AbstractEffect {

    private IEditorPart editorPart;
    
    public UpdateVisibilityEffect(IEditorPart editorPart) {
        this.editorPart = editorPart;
    }
    
    public void execute() {
        // TODO Auto-generated method stub
        final IEvaluationService evaluationService = (IEvaluationService) editorPart.getEditorSite()
                .getService(IEvaluationService.class);

        
        MonitoredOperation.runInUI(new Runnable() {

            public void run() {
                evaluationService.requestEvaluation("activeEditorId");
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
