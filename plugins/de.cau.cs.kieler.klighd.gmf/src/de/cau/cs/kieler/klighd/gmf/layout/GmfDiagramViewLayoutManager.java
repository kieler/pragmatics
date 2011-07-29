/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.gmf.layout;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.kiml.gmf.GmfDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.gmf.GmfDiagramViewer;
import de.cau.cs.kieler.klighd.views.ContextViewer;
import de.cau.cs.kieler.klighd.views.DiagramViewPart;

/**
 * A specialization of the {@code GmfDiagramLayoutManager} for layouting GMF diagrams in a KLighD
 * view.
 * 
 * @author mri
 */
public class GmfDiagramViewLayoutManager extends GmfDiagramLayoutManager {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(final Object object) {
        if (object instanceof DiagramViewPart) {
            DiagramViewPart view = (DiagramViewPart) object;
            return view.getViewer().getActiveViewer() instanceof GmfDiagramViewer;
        } else if (object instanceof ContextViewer) {
            ContextViewer contextViewer = (ContextViewer) object;
            return contextViewer.getActiveViewer() instanceof GmfDiagramViewer;
        } else {
            return object instanceof GmfDiagramViewer || object instanceof IGraphicalEditPart;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LayoutMapping<IGraphicalEditPart> buildLayoutGraph(final IWorkbenchPart workbenchPart,
            final Object diagramPart) {
        // choose the layout root edit part
        IGraphicalEditPart layoutRootPart = null;
        if (diagramPart instanceof ShapeNodeEditPart || diagramPart instanceof DiagramEditPart) {
            layoutRootPart = (IGraphicalEditPart) diagramPart;
        } else if (diagramPart instanceof IGraphicalEditPart) {
            EditPart tgEditPart = ((IGraphicalEditPart) diagramPart).getTopGraphicEditPart();
            if (tgEditPart instanceof ShapeNodeEditPart) {
                layoutRootPart = (IGraphicalEditPart) tgEditPart;
            }
        }
        if (layoutRootPart == null) {
            // find the active viewer
            GmfDiagramViewer viewer = null;
            if (diagramPart instanceof GmfDiagramViewer) {
                viewer = (GmfDiagramViewer) diagramPart;
            } else if (diagramPart instanceof ContextViewer) {
                ContextViewer contextViewer = (ContextViewer) diagramPart;
                IViewer<?> activeViewer = contextViewer.getActiveViewer();
                if (activeViewer instanceof GmfDiagramViewer) {
                    viewer = (GmfDiagramViewer) activeViewer;
                }
            }
            if (viewer == null) {
                if (workbenchPart instanceof DiagramViewPart) {
                    DiagramViewPart view = (DiagramViewPart) workbenchPart;
                    ContextViewer contextViewer = view.getViewer();
                    IViewer<?> activeViewer = contextViewer.getActiveViewer();
                    if (activeViewer instanceof GmfDiagramViewer) {
                        viewer = (GmfDiagramViewer) activeViewer;
                    }
                }
            }
            // retrieve the layout root edit part from the viewer
            if (viewer != null) {
                layoutRootPart = (IGraphicalEditPart) viewer.getDiagramGraphicalViewer()
                        .getContents();
            }
        }
        if (layoutRootPart == null) {
            throw new UnsupportedOperationException(
                    "Not supported by this layout manager: Workbench part " + workbenchPart
                            + ", Edit part or viewer " + diagramPart);
        }
        
        // create the mapping
        LayoutMapping<IGraphicalEditPart> mapping = buildLayoutGraph(layoutRootPart);
        
        // FIXME is creating a new command stack the way to go here?
        mapping.setProperty(COMMAND_STACK, new CommandStack());
        
        return mapping;
    }
    
}
