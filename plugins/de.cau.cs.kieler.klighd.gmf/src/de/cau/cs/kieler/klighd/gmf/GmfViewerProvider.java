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
package de.cau.cs.kieler.klighd.gmf;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.EditPartService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Composite;

import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.IViewerProvider;

/**
 * A viewer provider for GMF diagrams.
 * 
 * @author mri
 */
public class GmfViewerProvider implements IViewerProvider {

    /**
     * {@inheritDoc}
     */
    public IViewer<?> createViewer(final Composite parent) {
        DefaultEditDomain editDomain = new DefaultEditDomain(null);
        DiagramGraphicalViewer graphicalViewer = new DiagramGraphicalViewer();
        graphicalViewer.createControl(parent);
        editDomain.addViewer(graphicalViewer);
        GmfDiagramViewer viewer = new GmfDiagramViewer(graphicalViewer);
        // from GraphicalEditor
        graphicalViewer.getControl().setBackground(ColorConstants.listBackground);
        // from DiagramEditor
        graphicalViewer.setEditPartFactory(EditPartService.getInstance());
        if (graphicalViewer.getControl() instanceof FigureCanvas) {
            ((FigureCanvas) graphicalViewer.getControl())
                    .setScrollBarVisibility(FigureCanvas.ALWAYS);
        }
        return viewer;
    }

    /**
     * {@inheritDoc}
     */
    public boolean supports(final Object model) {
        return model instanceof Diagram;
    }

}
