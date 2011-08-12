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

import java.awt.event.InputEvent;
import java.util.Collection;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import de.cau.cs.kieler.klighd.AbstractViewer;
import de.cau.cs.kieler.klighd.events.SelectionEvent;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.PRoot;
import edu.umd.cs.piccolo.event.PInputEventFilter;
import edu.umd.cs.piccolox.swt.PSWTCanvas;

/**
 * A viewer for Piccolo diagram contexts.
 * 
 * @author mri
 */
public class PiccoloViewer extends AbstractViewer<PiccoloDiagramContext> implements
        INodeSelectionListener {

    /** the canvas used for drawing. */
    private PSWTCanvas canvas;
    /** the current selection event handler. */
    private PSWTSimpleSelectionEventHandler selectionHandler = null;
    /** the current diagram context. */
    private PiccoloDiagramContext diagramContext = null;

    /**
     * Creates a Piccolo viewer with default style.
     * 
     * @param parent
     *            the parent composite
     */
    public PiccoloViewer(final Composite parent) {
        this(parent, SWT.NONE);
    }

    /**
     * Creates a Piccolo viewer with given style.
     * 
     * @param parent
     *            the parent composite
     * @param style
     *            the style attributes
     */
    public PiccoloViewer(final Composite parent, final int style) {
        canvas = new PSWTCanvas(parent, style);
        canvas.setDoubleBuffered(false);
        // canvas.removeInputEventListener(canvas.getPanEventHandler());
        // prevent conflicts with selection handler
        canvas.getPanEventHandler().setEventFilter(
                new PInputEventFilter(InputEvent.BUTTON1_MASK, InputEvent.CTRL_MASK));
    }

    /**
     * {@inheritDoc}
     */
    public Control getControl() {
        return canvas;
    }

    /**
     * {@inheritDoc}
     */
    public void setModel(final PiccoloDiagramContext model) {
        diagramContext = model;
        // remove the old selection handler
        if (selectionHandler != null) {
            canvas.removeInputEventListener(selectionHandler);
            selectionHandler = null;
        }
        // fill the layers
        int index = 0;
        PCamera camera = canvas.getCamera();
        resizeAndResetLayers(model.getLayerRoots().size() + 1);
        for (PNode rootNode : model.getLayerRoots()) {
            camera.getLayer(index++).addChild(rootNode);
        }
        // add a node for the marquee
        PNode marqueeParent = new PNode();
        camera.getLayer(index).addChild(marqueeParent);
        // add a selection handler
        selectionHandler = new PSWTSimpleSelectionEventHandler(camera, marqueeParent);
        canvas.addInputEventListener(selectionHandler);
        // forward the selection events
        selectionHandler.addSelectionListener(this);
    }

    private void resizeAndResetLayers(final int count) {
        PRoot root = canvas.getRoot();
        PCamera camera = canvas.getCamera();
        // resize down
        while (camera.getLayerCount() > count) {
            PLayer layer = camera.getLayer(camera.getLayerCount() - 1);
            camera.removeLayer(layer);
            root.removeChild(layer);
        }
        // resize up
        while (camera.getLayerCount() < count) {
            PLayer layer = new PLayer();
            root.addChild(layer);
            camera.addLayer(layer);
        }
        // reset
        @SuppressWarnings("unchecked")
        List<PLayer> layers = camera.getLayersReference();
        for (PLayer layer : layers) {
            layer.removeAllChildren();
        }
    }
    
    /**
     * Returns the currently active diagram context.
     * 
     * @return the diagram context or null if no diagram context is active
     */
    public PiccoloDiagramContext getDiagramContext() {
        return diagramContext;
    }

    /**
     * {@inheritDoc}
     */
    public void nodesSelected(final PSWTSimpleSelectionEventHandler handler,
            final Collection<PNode> nodes) {
        notifyListeners(new SelectionEvent(this, nodes, true));
    }

    /**
     * {@inheritDoc}
     */
    public void nodesUnselected(final PSWTSimpleSelectionEventHandler handler,
            final Collection<PNode> nodes) {
        notifyListeners(new SelectionEvent(this, nodes, false));
    }

}
