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

import de.cau.cs.kieler.kiml.ui.util.KGraphRenderer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * A canvas that is able to paint KIML layout graphs.
 * 
 * @author msp
 */
public class LayoutGraphCanvas extends Canvas implements PaintListener {

    /** the painted layout graph. */
    private KNode layoutGraph;
    /** the graph renderer used for painting. */
    private KGraphRenderer graphRenderer;
    
    /** background color. */
    private Color backgroundColor;

    /**
     * Creates a layout graph canvas.
     * 
     * @param parent
     *            the parent widget
     */
    public LayoutGraphCanvas(final Composite parent) {
        super(parent, SWT.NONE);
        addPaintListener(this);
        graphRenderer = new KGraphRenderer(parent.getDisplay());
        // SUPPRESS CHECKSTYLE NEXT MagicNumber
        backgroundColor = new Color(parent.getDisplay(), 255, 255, 255);
        setBackground(backgroundColor);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        graphRenderer.dispose();
        backgroundColor.dispose();
    }

    /**
     * Sets the given layout graph as the painted graph.
     * 
     * @param thelayoutGraph layout graph to be painted
     */
    public void setLayoutGraph(final KNode thelayoutGraph) {
        // set new size values for the canvas
        if (thelayoutGraph != null) {
            KShapeLayout shapeLayout = thelayoutGraph.getData(KShapeLayout.class);
            setSize(new Point((int) shapeLayout.getWidth() + 1, (int) shapeLayout.getHeight() + 1));
        }

        this.layoutGraph = thelayoutGraph;
        graphRenderer.clear();
        redraw();
    }

    /**
     * Returns the currently painted layout graph.
     * 
     * @return the painted layout graph
     */
    public KNode getLayoutGraph() {
        return layoutGraph;
    }
    
    /**
     * Returns the KGraph renderer used for painting.
     * 
     * @return the KGraph renderer
     */
    public KGraphRenderer getRenderer() {
        return graphRenderer;
    }

    /**
     * This method is called when the canvas is requested to paint.
     * 
     * @param event
     *            paint event
     */
    public void paintControl(final PaintEvent event) {
        if (layoutGraph != null) {
            Rectangle area = new Rectangle(event.x, event.y, event.width, event.height);
            // reset paint information
            graphRenderer.markDirty(area);
            // paint the top layout nodes with their children
            graphRenderer.render(layoutGraph, event.gc, area);
        }
    }

}
