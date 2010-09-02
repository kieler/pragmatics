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
package de.cau.cs.kieler.ksbase.kivi;

import org.eclipse.gef.EditPart;
import org.eclipse.ui.part.EditorPart;

import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;
import de.cau.cs.kieler.kivi.core.impl.AbstractEffect;

/**
 * Performs automatic layout on an editor for a given selection.
 * 
 * @author mmu
 *
 */
public class LayoutEffect extends AbstractEffect {
    
    private EditorPart editorPart;
    
    private EditPart editPart;

    /**
     * Create a new layout effect for the given editor and object.
     * 
     * @param editor the editor containing the diagram to layout
     * @param eP the edit part to layout
     */
    public LayoutEffect(final EditorPart editor, final EditPart eP) {
        editorPart = editor;
        editPart = eP;
    }
    
    @Override
    public void execute() {
        EclipseLayoutServices.getInstance().layout(editorPart, editPart, true, false, true);
    }

}
