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
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.EditPartService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;

/**
 * A wrapper for a {@code DiagramGraphicalViewer} to fit the {@code Viewer} interface.
 * 
 * @author mri
 */
public class GMFDiagramViewer extends Viewer {

    /** the graphical viewer. */
    private DiagramGraphicalViewer graphicalViewer;
    /** the current input diagram. */
    private Diagram input = null;

    /**
     * Constructs a GMFDiagramViewer wrapping a {@code DiagramGraphicalViewer}.
     * 
     * @param graphicalViewer
     *            the graphical viewer
     */
    public GMFDiagramViewer(final DiagramGraphicalViewer graphicalViewer) {
        this.graphicalViewer = graphicalViewer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Control getControl() {
        return graphicalViewer.getControl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getInput() {
        return input;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISelection getSelection() {
        return graphicalViewer.getSelection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInput(final Object input) {
        if (input instanceof Diagram) {
            this.input = (Diagram) input;
            // configure the viewer
            RootEditPart rootEditPart =
                    EditPartService.getInstance().createRootEditPart(this.input);
            graphicalViewer.setRootEditPart(rootEditPart);
            // set the content
            graphicalViewer.setContents(this.input);
            // disable the edit mode
            ((DiagramEditPart) graphicalViewer.getContents()).disableEditMode();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSelection(final ISelection selection, final boolean reveal) {
        graphicalViewer.setSelection(selection);
    }

}
