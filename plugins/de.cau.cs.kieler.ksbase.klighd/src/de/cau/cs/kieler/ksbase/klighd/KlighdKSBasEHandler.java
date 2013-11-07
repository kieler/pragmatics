package de.cau.cs.kieler.ksbase.klighd;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.ui.editor.XtextEditor;

import de.cau.cs.kieler.core.kivi.triggers.SelectionTrigger.SelectionState;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
import de.cau.cs.kieler.klighd.views.DiagramEditorPart;
import de.cau.cs.kieler.klighd.views.DiagramViewPart;
import de.cau.cs.kieler.ksbase.ui.kivi.IKSBasEHandler;

public class KlighdKSBasEHandler implements IKSBasEHandler {

    
    private ContextViewer lastContextViewer = null;
    
    private IWorkbenchPart partCache = null;
    
    public boolean canHandle(IEvaluationContext context) {
        ContextViewer activeContextViewer = this.getDiagramViewPart(context);
        if (activeContextViewer != null) {
            return true;
        }
        return false;
    }

    public List<EObject> getSelection(IEvaluationContext context) {
        ContextViewer activeContextViewer = this.getDiagramViewPart(context);
        if (activeContextViewer != null) {
            lastContextViewer = activeContextViewer;
            Object defaultVar = context.getDefaultVariable();
            
            List<EObject> eObjects = new ArrayList<EObject>(/*((List<?>) defaultVar).size()*/);
            try {
                for (Object o : (Iterable<?>) defaultVar) {
                    eObjects.add((EObject) activeContextViewer.getCurrentViewContext().getSourceElement(((EObject) o)));
                }
                return eObjects;
            } catch (ClassCastException e) {
                // ignore exception
            }
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
        ContextViewer activeContextViewer = lastContextViewer;
        Object defaultVar = selection.getSelection();

        List<EObject> eObjects = new ArrayList<EObject>(((List<?>) defaultVar).size());
        try {
            for (Object o : (List<?>) defaultVar) {
                eObjects.add((EObject) activeContextViewer.getCurrentViewContext().getSourceElement(((EObject) o)));
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
    
    private ContextViewer getDiagramViewPart(IEvaluationContext context) {
        
        Object activePart = context.getVariable("org.eclipse.ui.active_activePart");
        if (activePart instanceof DiagramViewPart) {
            return ((DiagramViewPart) activePart).getContextViewer();
        }
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                IWorkbench wb = PlatformUI.getWorkbench();
                IWorkbenchWindow wbw = wb.getActiveWorkbenchWindow();
                IWorkbenchPage ap = wbw.getActivePage();
                partCache = ap.getActivePart();
                        
                                
            }
        });
        
        if (partCache != null && partCache instanceof DiagramViewPart) {
            return ((DiagramViewPart) partCache).getContextViewer();
        }
        
        if (partCache != null && partCache instanceof DiagramEditorPart) {
            return ((DiagramEditorPart) partCache).getContextViewer();
        }
        return null;
    }

}
