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
package de.cau.cs.kieler.papyrus;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.papyrus.core.editor.IMultiDiagramEditor;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.model.GmfFrameworkBridge;

/**
 * An extension of the GMF editing bridge for Papyrus multi-page editors.
 *
 * @author msp
 */
public class PapyrusFrameworkBridge extends GmfFrameworkBridge {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(final Object object) {
        return object instanceof IMultiDiagramEditor;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public EditPart getEditPart(final Object object) {
        if (object instanceof IMultiDiagramEditor) {
            return super.getEditPart(((IMultiDiagramEditor) object).getActiveEditor());
        }
        return super.getEditPart(object);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ISelection getSelection(final IWorkbenchPart workbenchPart) {
        if (workbenchPart instanceof IMultiDiagramEditor) {
            return super.getSelection(((IMultiDiagramEditor) workbenchPart).getActiveEditor());
        }
        return super.getSelection(workbenchPart);
    }

}
