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
package de.cau.cs.kieler.skad;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PText;
import edu.umd.cs.piccolox.swt.PSWTCanvas;

/**
 * A viewer for scalable diagrams.
 *
 * @author msp
 */
public class SkadViewer extends Viewer {

    /** the canvas used for drawing. */
    private PSWTCanvas canvas;
    
    /**
     * Creates a Skad viewer with default style.
     * 
     * @param parent the parent composite
     */
    public SkadViewer(final Composite parent) {
        this(parent, SWT.NONE);
    }
    
    /**
     * Creates a Skad viewer with given style.
     * 
     * @param parent the parent composite
     * @param style the style attributes
     */
    public SkadViewer(final Composite parent, final int style) {
        canvas = new PSWTCanvas(parent, style);
        PNode child = new PText("Test");
        canvas.getLayer().addChild(child);
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
        // TODO Auto-generated method stub
        return null;
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
    public void refresh() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInput(final Object input) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSelection(final ISelection selection, final boolean reveal) {
        // TODO Auto-generated method stub

    }

}
