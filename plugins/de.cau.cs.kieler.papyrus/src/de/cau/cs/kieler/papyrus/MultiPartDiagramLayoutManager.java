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
import org.eclipse.gef.EditPart;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.IGraphicalFrameworkBridge;
import de.cau.cs.kieler.kiml.gmf.GmfDiagramLayoutManager;

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
    public boolean supports(final IWorkbenchPart workbenchPart) {
        return workbenchPart instanceof IMultiDiagramEditor;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public KNode buildLayoutGraph(final IWorkbenchPart workbenchPart,
            final EditPart editPart, final boolean layoutAncestors) {
        if (workbenchPart instanceof IMultiDiagramEditor) {
            return super.buildLayoutGraph(((IMultiDiagramEditor) workbenchPart).getActiveEditor(),
                    editPart, layoutAncestors);
        } else {
            return super.buildLayoutGraph(workbenchPart, editPart, layoutAncestors);
        }
    }

    /** the framework bridge for this layout manager. */
    private PapyrusFrameworkBridge papyrusBridge = new PapyrusFrameworkBridge();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public IGraphicalFrameworkBridge getBridge() {
        return papyrusBridge;
    }

}
