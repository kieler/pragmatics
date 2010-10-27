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
package de.cau.cs.kieler.kiml.ui.layout;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.ZoomManager;

/**
 * Interface for edit part inspectors. A layout inspector is always associated with a specific
 * edit part instance.
 * 
 * @author msp
 */
public interface ILayoutInspector {

    /**
     * Returns the edit part that is associated with this layout inspector.
     * 
     * @return the associated edit part
     */
    EditPart getFocusPart();

    /**
     * Returns the domain model element that is associated with this inspector.
     * 
     * @return the associated domain model element
     */
    EObject getFocusModel();

    /**
     * Returns a transactional editing domain in which to perform model changes.
     * 
     * @return an editing domain for model changes
     */
    TransactionalEditingDomain getEditingDomain();

    /**
     * Returns a layer figure for the associated diagram that can be used to draw
     * additional information.
     * 
     * @return a drawing layer
     */
    IFigure getDrawingLayer();
    
    /**
     * Returns a zoom manager for the associated diagram.
     * 
     * @return a zoom manager
     */
    ZoomManager getZoomManager();

}
