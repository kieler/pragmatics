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

import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.EditPartService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Control;

import de.cau.cs.kieler.klighd.viewers.AbstractViewer;

/**
 * A wrapper for a {@code DiagramGraphicalViewer} to fit the {@code IViewer} interface.
 * 
 * @author mri
 */
public class GmfDiagramViewer extends AbstractViewer<Diagram> {

    /** the graphical viewer. */
    private DiagramGraphicalViewer graphicalViewer;

    /**
     * Constructs a GMFDiagramViewer wrapping a {@code DiagramGraphicalViewer}.
     * 
     * @param graphicalViewer
     *            the graphical viewer
     */
    public GmfDiagramViewer(final DiagramGraphicalViewer graphicalViewer) {
        this.graphicalViewer = graphicalViewer;
    }

    /**
     * {@inheritDoc}
     */
    public Control getControl() {
        return graphicalViewer.getControl();
    }

    /**
     * {@inheritDoc}
     */
    public void setModel(final Diagram model, final boolean sync) {
        // configure the viewer
        RootEditPart rootEditPart = EditPartService.getInstance().createRootEditPart(model);
        graphicalViewer.setRootEditPart(rootEditPart);
        // set the content
        graphicalViewer.setContents(model);
        // TODO disable the edit mode in general, only enable for layout
        // ((DiagramEditPart) graphicalViewer.getContents()).disableEditMode();
    }
    
    /**
     * {@inheritDoc}
     */
    public Diagram getModel() {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    public void setRecording(final boolean recording) {
        // TODO Auto-generated method stub
    }

    /**
     * Returns GMF graphical viewer used by this viewer to show the GMF notation model.
     * 
     * @return the GMF graphical viewer
     */
    public DiagramGraphicalViewer getDiagramGraphicalViewer() {
        return graphicalViewer;
    }

}
