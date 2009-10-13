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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @generated
 */
public class LoadResourceAction extends AbstractHandler {
    /**
     * @generated
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IEditorPart diagramEditor = HandlerUtil.getActiveEditorChecked(event);
        Shell shell = diagramEditor.getEditorSite().getShell();
        assert diagramEditor instanceof DiagramEditor;
        TransactionalEditingDomain editingDomain = ((DiagramEditor) diagramEditor)
                .getEditingDomain();
        org.eclipse.emf.edit.ui.action.LoadResourceAction.LoadResourceDialog loadResourceDialog = new org.eclipse.emf.edit.ui.action.LoadResourceAction.LoadResourceDialog(
                shell, editingDomain);
        loadResourceDialog.open();
        return null;
    }

}
