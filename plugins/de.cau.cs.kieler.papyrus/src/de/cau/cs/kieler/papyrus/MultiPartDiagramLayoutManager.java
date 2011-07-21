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
package de.cau.cs.kieler.papyrus;

import org.eclipse.papyrus.core.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.diagram.common.editparts.IPapyrusEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.kiml.gmf.GmfDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;

/**
 * Layout manager wrapper for the Papyrus multi diagram editor.
 * 
 * @author msp
 */
public class MultiPartDiagramLayoutManager extends GmfDiagramLayoutManager {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(final Object object) {
        return object instanceof IMultiDiagramEditor || object instanceof IPapyrusEditPart
                || object instanceof IGraphicalEditPart;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public LayoutMapping<IGraphicalEditPart> buildLayoutGraph(final IWorkbenchPart workbenchPart,
            final Object diagramPart) {
        if (workbenchPart instanceof IMultiDiagramEditor) {
            return super.buildLayoutGraph(((IMultiDiagramEditor) workbenchPart).getActiveEditor(),
                    diagramPart);
        } else {
            return super.buildLayoutGraph(workbenchPart, diagramPart);
        }
    }

}
