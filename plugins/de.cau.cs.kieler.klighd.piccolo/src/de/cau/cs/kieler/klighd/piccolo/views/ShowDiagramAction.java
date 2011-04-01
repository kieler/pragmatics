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
package de.cau.cs.kieler.klighd.piccolo.views;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;

import de.cau.cs.kieler.core.model.GraphicalFrameworkService;
import de.cau.cs.kieler.core.model.IGraphicalFrameworkBridge;
import de.cau.cs.kieler.klighd.piccolo.Draw2DNode;

/**
 * An action that copies the content of the currently open diagram.
 *
 * @author msp
 */
public class ShowDiagramAction extends Action {
    
    /** the view part that created this action. */
    private PiccoloViewPart viewPart;
    /** the current workbench page. */
    private IWorkbenchPage workbenchPage;
    
    /**
     * Creates an action for showing the current diagram.
     * 
     * @param viewPart the view part for displaying diagrams
     * @param page the current workbench page
     */
    public ShowDiagramAction(final PiccoloViewPart viewPart, final IWorkbenchPage page) {
        super("Test");
        this.workbenchPage = page;
        this.viewPart = viewPart;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        IEditorPart editorPart = workbenchPage.getActiveEditor();
        IGraphicalFrameworkBridge bridge = GraphicalFrameworkService.getInstance()
                .getBridge(editorPart);
        EditPart editPart = bridge.getEditPart(editorPart);
        if (editPart instanceof GraphicalEditPart) {
            IFigure diagramFigure = ((GraphicalEditPart) editPart).getFigure();
            Draw2DNode diagramNode = new Draw2DNode(diagramFigure);
            viewPart.showNode(diagramNode);
        }
    }

}
