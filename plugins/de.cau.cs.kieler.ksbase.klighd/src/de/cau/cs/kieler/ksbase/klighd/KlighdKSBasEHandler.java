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

/**
 * 
 * Checks whether the current active part is part of the klighd framework and can be
 * handled by this class. Also provides a method to get the current selection.
 * 
 * @author ckru
 *
 */
public class KlighdKSBasEHandler implements IKSBasEHandler {

    /**
     * Saves the last found context viewer.
     */
    private ContextViewer lastContextViewer = null;
    
    /**
     * Cache to get workbench part from different thread.
     */
    private IWorkbenchPart partCache = null;
    
    /**
     * {@inheritDoc}
     */
    public boolean canHandle(IEvaluationContext context) {
        ContextViewer activeContextViewer = this.getDiagramViewPart(context);
        if (activeContextViewer != null) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public List<EObject> getSelection(IEvaluationContext context) {
        ContextViewer activeContextViewer = this.getDiagramViewPart(context);
        if (activeContextViewer != null) {
            lastContextViewer = activeContextViewer;
            Object defaultVar = context.getDefaultVariable();
            
            List<EObject> eObjects = new ArrayList<EObject>(/*((List<?>) defaultVar).size()*/);
            try {
                for (Object o : (Iterable<?>) defaultVar) {
                    eObjects.add((EObject) activeContextViewer.getViewContext().getSourceElement(((EObject) o)));
                }
                return eObjects;
            } catch (ClassCastException e) {
                // ignore exception
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canHandle(IWorkbenchPart workbenchPart, SelectionState selection) {
        if (workbenchPart instanceof XtextEditor) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public List<EObject> getSelection(IWorkbenchPart workbenchPart, SelectionState selection) {
        ContextViewer activeContextViewer = lastContextViewer;
        Object defaultVar = selection.getSelection();

        List<EObject> eObjects = new ArrayList<EObject>(((List<?>) defaultVar).size());
        try {
            for (Object o : (List<?>) defaultVar) {
                eObjects.add((EObject) activeContextViewer.getViewContext().getSourceElement(((EObject) o)));
            }
            return eObjects;
        } catch (ClassCastException e) {
            // ignore exception
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void performPostProcessing() {

    }

    /**
     * {@inheritDoc}
     */
    public boolean isPerformLayout() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public EObject getLayoutRoot() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public TransactionalEditingDomain getEditingDomain() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void transformationExecuted(String transformationName, Object[] parameters,
            Object result, EObject select) {

    }
    
    /**
     * Gets a ContextViewer from currently active WorkbenchPart if possible. The ContextViewer is later used
     * to get the current selection.
     * @param context the evaluation context given by eclipse.
     * @return the ContextViewer linked to the active WorkbenchPart. Null if active part has no
     *          ContextViewer
     */
    private ContextViewer getDiagramViewPart(IEvaluationContext context) {
        
        Object activePart = context.getVariable("org.eclipse.ui.active_activePart");
        if (activePart instanceof DiagramViewPart) {
            return ((DiagramViewPart) activePart).getContextViewer();
        }
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                IWorkbench wb = PlatformUI.getWorkbench();
                IWorkbenchWindow wbw = wb.getActiveWorkbenchWindow();
                if (wbw != null) {
                    IWorkbenchPage ap = wbw.getActivePage();
                    partCache = ap.getActivePart();
                }
                
                        
                                
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
