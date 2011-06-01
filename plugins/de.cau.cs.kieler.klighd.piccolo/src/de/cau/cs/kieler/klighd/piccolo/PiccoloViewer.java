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

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.PRoot;
import edu.umd.cs.piccolox.swt.PSWTCanvas;

/**
 * A viewer for Piccolo nodes or lists of Piccolo nodes, which will be used as root nodes for a
 * fitting number of layers in the Piccolo canvas. The first node in the list will be contained in
 * the bottom most layer and the last node in the top most layer.
 * 
 * @author mri
 */
public class PiccoloViewer extends Viewer {

    /** the canvas used for drawing. */
    private PSWTCanvas canvas;

    /**
     * Creates a PiccoloViewer with default style.
     * 
     * @param parent
     *            the parent composite
     */
    public PiccoloViewer(final Composite parent) {
        this(parent, SWT.NONE);
    }

    /**
     * Creates a PiccoloViewer with given style.
     * 
     * @param parent
     *            the parent composite
     * @param style
     *            the style attributes
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
        // a single node is added as root node to the first layer
        if (input instanceof PNode) {
            resizeAndResetLayers(1);
            canvas.getLayer().addChild((PNode) input);
        }
        // for a list of nodes, every node is added as root node for a single layer
        if (input instanceof List) {
            PCamera camera = canvas.getCamera();
            List<?> list = (List<?>) input;
            resizeAndResetLayers(list.size());
            int index = 0;
            for (Object obj : list) {
                // only consider nodes in the list, ignore everything else
                if (obj instanceof PNode) {
                    camera.getLayer(index++).addChild((PNode) obj);
                }
            }
        }
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
     * {@inheritDoc}
     */
    @Override
    public void refresh() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISelection getSelection() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSelection(final ISelection selection, final boolean reveal) {
    }

}
