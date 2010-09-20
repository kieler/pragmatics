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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.ILayoutInspector;
import de.cau.cs.kieler.kiml.ui.util.KimlUiUtil;

/**
 * An action that removes all layout options from the current diagram.
 *
 * @kieler.rating 2010-01-26 proposed yellow msp
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
        IEditorPart editorPart = layoutView.getCurrentEditor();
        if (editorPart instanceof GraphicalEditor) {
            GraphicalEditor graphEditor = (GraphicalEditor) editorPart;
            EditPart diagram = (EditPart) graphEditor.getAdapter(EditPart.class);
            DiagramLayoutManager manager = layoutView.getCurrentManager();
            if (diagram != null && manager != null) {
                // show a dialog to confirm the removal of all layout options
                String diagramName = graphEditor.getTitle();
                boolean userResponse = MessageDialog.openQuestion(layoutView.getSite().getShell(),
                        Messages.getString("kiml.ui.31"), Messages.getString("kiml.ui.32")
                        + " " + diagramName + "?");
                if (userResponse) {
                    final ILayoutInspector inspector = manager.getInspector(diagram);
                    Runnable runnable = new Runnable() {
                        public void run() {
                            inspector.removeAllOptions();
                            LayoutViewPart.findView().refresh();
                        }
                    };
                    KimlUiUtil.runModelChange(runnable, inspector.getEditingDomain(),
                            Messages.getString("kiml.ui.30"));
                }
            }
        }
    }
    
}
