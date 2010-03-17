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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.ContributionItemService;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.providers.DiagramContextMenuProvider;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @generated
 */
public class DiagramEditorContextMenuProvider extends
        DiagramContextMenuProvider {

    /**
     * @generated
     */
    private IWorkbenchPart part;

    /**
     * @generated
     */
    private DeleteElementAction deleteAction;

    /**
     * @generated
     */
    public DiagramEditorContextMenuProvider(IWorkbenchPart part,
            EditPartViewer viewer) {
        super(part, viewer);
        this.part = part;
        deleteAction = new DeleteElementAction(part);
        deleteAction.init();
    }

    /**
     * @generated
     */
    public void dispose() {
        if (deleteAction != null) {
            deleteAction.dispose();
            deleteAction = null;
        }
        super.dispose();
    }

    /**
     * @generated
     */
    public void buildContextMenu(final IMenuManager menu) {
        getViewer().flush();
        try {
            TransactionUtil.getEditingDomain(
                    (EObject) getViewer().getContents().getModel())
                    .runExclusive(new Runnable() {

                        public void run() {
                            ContributionItemService
                                    .getInstance()
                                    .contributeToPopupMenu(
                                            DiagramEditorContextMenuProvider.this,
                                            part);
                            menu.remove(ActionIds.ACTION_DELETE_FROM_MODEL);
                            menu.appendToGroup("editGroup", deleteAction);
                        }
                    });
        } catch (Exception e) {
            DataflowDiagramEditorPlugin.getInstance().logError(
                    "Error building context menu", e);
        }
    }
}
