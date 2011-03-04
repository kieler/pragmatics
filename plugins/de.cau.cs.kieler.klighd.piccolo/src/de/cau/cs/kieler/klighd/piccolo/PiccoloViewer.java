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
package de.cau.cs.kieler.klighd.piccolo;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolox.swt.PSWTCanvas;

/**
 * A viewer for light-weight piccolo diagrams.
 *
 * @author msp
 */
public class PiccoloViewer extends Viewer {
    
    /** the canvas used for drawing. */
    private PSWTCanvas canvas;
    
    /**
     * Creates a Skad viewer with default style.
     * 
     * @param parent the parent composite
     */
    public PiccoloViewer(final Composite parent) {
        this(parent, SWT.NONE);
    }
    
    /**
     * Creates a Skad viewer with given style.
     * 
     * @param parent the parent composite
     * @param style the style attributes
     */
    public PiccoloViewer(final Composite parent, final int style) {
        canvas = new PSWTCanvas(parent, style);
        canvas.setDoubleBuffered(false);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Control getControl() {
        return canvas;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getInput() {
        if (canvas.getLayer().getChildrenCount() > 0) {
            return canvas.getLayer().getChild(0);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInput(final Object input) {
        if (input instanceof PNode) {
            canvas.getLayer().removeAllChildren();
            canvas.getLayer().addChild((PNode) input);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISelection getSelection() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSelection(final ISelection selection, final boolean reveal) {
        // TODO Auto-generated method stub

    }

}
