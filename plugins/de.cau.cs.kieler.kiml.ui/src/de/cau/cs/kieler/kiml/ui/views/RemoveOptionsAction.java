/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui.views;

import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;

import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.layout.KimlUiUtil;

/**
 * An action that removes all layout options from the current diagram.
 *
 * @author msp
 */
public class RemoveOptionsAction extends Action {
    
    /** the layout view that created this action. */
    private LayoutViewPart layoutView;

    /**
     * Creates an apply option action.
     * 
     * @param thelayoutView the layout view that created this action
     * @param text user friendly text
     */
    public RemoveOptionsAction(final LayoutViewPart thelayoutView, final String text) {
        super(text);
        this.layoutView = thelayoutView;
    }
    
    /**
     * {@inheritDoc}
     */
    public void run() {
        final DiagramEditor editor = layoutView.getCurrentEditor();
        if (editor != null) {
            // show a dialog to confirm the removal of all layout options
            String diagramName = editor.getTitle();
            boolean userResponse = MessageDialog.openQuestion(layoutView.getSite().getShell(),
                    Messages.getString("kiml.ui.31"), Messages.getString("kiml.ui.32")
                    + " " + diagramName + "?");
            if (userResponse) {
                Runnable runnable = new Runnable() {
                    public void run() {
                        removeOptions(editor.getDiagram());
                    }
                };
                KimlUiUtil.runModelChange(runnable, editor.getEditingDomain(),
                        Messages.getString("kiml.ui.30"));
            }
        }
    }
    
    /**
     * Removes all layout options from the given notation diagram.
     * 
     * @param diagram a diagram from the notation model
     */
    private void removeOptions(final Diagram diagram) {
        for (Object edge : diagram.getPersistedEdges()) {
            KimlUiUtil.removeOptionStyle((View) edge);
        }
        removeChildOptions(diagram);
    }
    
    /**
     * Removes all layout options from the given node and its children.
     * 
     * @param node a node from the notation model
     */
    private void removeChildOptions(final View node) {
        KimlUiUtil.removeOptionStyle(node);
        for (Object child : node.getPersistedChildren()) {
            removeChildOptions((View) child);
        }
    }
    
}
