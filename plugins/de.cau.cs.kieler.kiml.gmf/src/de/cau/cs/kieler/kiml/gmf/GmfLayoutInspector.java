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
package de.cau.cs.kieler.kiml.gmf;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.render.editparts.RenderedDiagramRootEditPart;

import de.cau.cs.kieler.kiml.ui.layout.ILayoutInspector;

/**
 * Layout inspector implementation for the GMF.
 *
 * @author msp
 */
public class GmfLayoutInspector implements ILayoutInspector {

    /** the edit part for which options are shown. */
    private IGraphicalEditPart focusEditPart;
    
    /**
     * Finds the diagram edit part of an edit part.
     * 
     * @param editPart an edit part
     * @return the diagram edit part, or {@code null} if there is no containing diagram
     *     edit part
     */
    public static DiagramEditPart getDiagramEditPart(final EditPart editPart) {
        EditPart ep = editPart;
        while (ep != null && !(ep instanceof DiagramEditPart) && !(ep instanceof RootEditPart)) {
            ep = ep.getParent();
        }
        if (ep instanceof RootEditPart) {
            RootEditPart root = (RootEditPart) ep;
            ep = null;
            for (Object child : root.getChildren()) {
                if (child instanceof DiagramEditPart) {
                    ep = (EditPart) child;
                }
            }
        }
        return (DiagramEditPart) ep;
    }
    
    /**
     * Creates a layout inspector for a GMF graphical edit part.
     * 
     * @param editPart a graphical edit part
     */
    public GmfLayoutInspector(final IGraphicalEditPart editPart) {
        if (editPart == null) {
            throw new NullPointerException("editPart");
        }
        focusEditPart = editPart;
    }
    
    /**
     * {@inheritDoc}
     */
    public EditPart getFocusPart() {
        return focusEditPart;
    }
    
    /**
     * {@inheritDoc}
     */
    public EObject getFocusModel() {
        if (focusEditPart == null) {
            return null;
        } else {
            return focusEditPart.getNotationView().getElement();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public TransactionalEditingDomain getEditingDomain() {
        if (focusEditPart == null) {
            return null;
        } else {
            return focusEditPart.getEditingDomain();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public IFigure getDrawingLayer() {
        DiagramEditPart diagramEditPart = getDiagramEditPart(focusEditPart);
        if (diagramEditPart == null) {
            return null;
        } else {
            return diagramEditPart.getLayer(DiagramRootEditPart.PAGE_BREAKS_LAYER);
        }
    }

    /**
     * {@inheritDoc}
     */
    public ZoomManager getZoomManager() {
        DiagramEditPart diagramEditPart = getDiagramEditPart(focusEditPart);
        if (diagramEditPart == null) {
            return null;
        } else {
            return ((RenderedDiagramRootEditPart) diagramEditPart.getRoot()).getZoomManager();
        }
    }
    
}
