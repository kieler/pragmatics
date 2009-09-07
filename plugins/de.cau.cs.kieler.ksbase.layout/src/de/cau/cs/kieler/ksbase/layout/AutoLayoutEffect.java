/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.layout;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.viewmanagement.AEffect;

/**
 * The effect that is used to perform auto layout 
 * @author Michael Matzen
 *
 */
public class AutoLayoutEffect extends AEffect {

    
    EditPart editPart; //the edit part for the auto layout method
    IEditorPart editorPart; //the editor part for the auto layout method
    
    /**
     * Performs auto layout by simply calling the layout method
     * from {@link DiagramLayoutManager}
     */
    @Override
    public void execute() {
        DiagramLayoutManager.layout(editorPart, editPart , true, false);
    }

    /**
     * Sets the effect parameters.
     * In this special case the parameter is an IEditorPart
     */
    @Override
    public void setParameters(Object parameters) {
        if (parameters instanceof IEditorPart)
            this.editorPart = (IEditorPart)parameters;
    }

    /**
     * Sets the target ShapeEditPart
     */
    @Override
    public void setTarget(ShapeEditPart target) {
        this.editPart = (EditPart)target;
    }
    

}
