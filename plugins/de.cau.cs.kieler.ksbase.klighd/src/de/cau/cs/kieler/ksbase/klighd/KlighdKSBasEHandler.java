package de.cau.cs.kieler.ksbase.klighd;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.xtext.ui.editor.XtextEditor;

import de.cau.cs.kieler.core.kivi.triggers.SelectionTrigger.SelectionState;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
import de.cau.cs.kieler.klighd.views.DiagramViewPart;
import de.cau.cs.kieler.ksbase.ui.kivi.IKSBasEHandler;

public class KlighdKSBasEHandler implements IKSBasEHandler {

    
    DiagramViewPart lastViewPart = null;
    
    public boolean canHandle(IEvaluationContext context) {
        if (context.getVariable("org.eclipse.ui.active_activePart") instanceof DiagramViewPart) {
            return true;
        }
        return false;
    }

    public List<EObject> getSelection(IEvaluationContext context) {
        DiagramViewPart activePart = (DiagramViewPart) context
                .getVariable("org.eclipse.ui.active_activePart");
        lastViewPart = activePart;
        ContextViewer contextViewer = activePart.getContextViewer();
        Object defaultVar = context.getDefaultVariable();
        
        List<EObject> eObjects = new ArrayList<EObject>(/*((List<?>) defaultVar).size()*/);
        try {
            for (Object o : (Iterable<?>) defaultVar) {
                eObjects.add((EObject) contextViewer.getCurrentViewContext().getSourceElement(((EObject) o)));
            }
            return eObjects;
        } catch (ClassCastException e) {
            // ignore exception
        }

        return null;
    }

    public boolean canHandle(IWorkbenchPart workbenchPart, SelectionState selection) {
        if (workbenchPart instanceof XtextEditor) {
            return true;
        }
        return false;
    }

    public List<EObject> getSelection(IWorkbenchPart workbenchPart, SelectionState selection) {
        DiagramViewPart activePart = lastViewPart;
        ContextViewer contextViewer = activePart.getContextViewer();
        Object defaultVar = selection.getSelection();

        List<EObject> eObjects = new ArrayList<EObject>(((List<?>) defaultVar).size());
        try {
            for (Object o : (List<?>) defaultVar) {
                eObjects.add((EObject) contextViewer.getCurrentViewContext().getSourceElement(((EObject) o)));
            }
            return eObjects;
        } catch (ClassCastException e) {
            // ignore exception
        }

        return null;
    }

    public void performPostProcessing() {
        // TODO Auto-generated method stub

    }

    public boolean isPerformLayout() {
        // TODO Auto-generated method stub
        return false;
    }

    public EObject getLayoutRoot() {
        // TODO Auto-generated method stub
        return null;
    }

    public TransactionalEditingDomain getEditingDomain() {
        // TODO Auto-generated method stub
        return null;
    }

    public void transformationExecuted(String transformationName, Object[] parameters,
            Object result, EObject select) {
        // TODO Auto-generated method stub

    }

}
