/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.debug.views;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.cau.cs.kieler.kiml.debug.actions.ImageExportAction;
import de.cau.cs.kieler.kiml.debug.actions.LoadGraphAction;

/**
 * A viewer for layout graphs.
 * 
 * @author msp
 */
public class LayoutGraphView extends ViewPart {

    /** the view identifier. */
    public static final String VIEW_ID = "de.cau.cs.kieler.kiml.debug.layoutGraph"; //$NON-NLS-1$

    /** the scrolled composite that contains the graph canvas. */
    private ScrolledComposite scrolledComposite;
    /** the canvas used to draw layout graphs. */
    private LayoutGraphCanvas graphCanvas;

    /**
     * Creates a layout graph view.
     */
    public LayoutGraphView() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public void createPartControl(final Composite parent) {
        // create actions in the view toolbar
        IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
        toolBarManager.add(new ImageExportAction(this));
        toolBarManager.add(new LoadGraphAction());

        // create canvas for layout graphs
        scrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
        graphCanvas = new LayoutGraphCanvas(scrolledComposite);
        scrolledComposite.setContent(graphCanvas);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        scrolledComposite.setFocus();
    }

    /**
     * Returns the layout graph canvas.
     * 
     * @return the layout graph canvas
     */
    public LayoutGraphCanvas getCanvas() {
        return graphCanvas;
    }

}
