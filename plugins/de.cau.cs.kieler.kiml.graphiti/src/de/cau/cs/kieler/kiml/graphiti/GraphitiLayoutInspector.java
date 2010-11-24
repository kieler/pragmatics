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
package de.cau.cs.kieler.kiml.graphiti;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementEditPart;

import de.cau.cs.kieler.kiml.ui.layout.ILayoutInspector;

/**
 * @author atr
 * 
 */
public class GraphitiLayoutInspector implements ILayoutInspector {

    IPictogramElementEditPart focusEditPart;

    public GraphitiLayoutInspector(final IPictogramElementEditPart editPart) {
        if (editPart == null) {
            throw new NullPointerException("editPart");
        }
        focusEditPart = editPart;
    }

    /**
     * {@inheritDoc}
     */
    public EditPart getFocusPart() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public EObject getFocusModel() {
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
    public IFigure getDrawingLayer() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public ZoomManager getZoomManager() {
        return null;
    }

}
