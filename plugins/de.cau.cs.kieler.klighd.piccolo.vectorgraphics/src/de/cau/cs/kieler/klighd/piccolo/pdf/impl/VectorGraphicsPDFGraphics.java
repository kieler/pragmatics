/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.pdf.impl;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import de.cau.cs.kieler.klighd.piccolo.export.IViewExporter;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;
import de.cau.cs.kieler.klighd.piccolo.svg.KlighdAbstractSVGGraphics;
import de.erichseifert.vectorgraphics2d.PDFGraphics2D;
import de.erichseifert.vectorgraphics2d.VectorGraphics2D.FontRendering;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * 
 * FIXME generalize the abstract svg graphics ...
 * 
 * @author uru
 */
public class VectorGraphicsPDFGraphics extends KlighdAbstractSVGGraphics implements IViewExporter {

    private Rectangle2D bounds;
    private boolean textAsShapes;

    /**
     * @param bounds
     *            the bounds will be set as viewport values for the resulting root <svg ..> tag.
     * @param textAsShapes
     *            whether text should be rendered as shapes
     */
//    public VectorGraphicsPDFGraphics(final Rectangle2D bounds, final Boolean textAsShapes) {
    public VectorGraphicsPDFGraphics() {
        super(null);
//        this.bounds = bounds;
//        this.textAsShapes = textAsShapes;

        init();

    }

    private void init() {
//
//        PDFGraphics2D g =
//                new PDFGraphics2D(bounds.getX(), bounds.getY(), bounds.getWidth(),
//                        bounds.getHeight());
//
//        // set text handling
//        if (textAsShapes) {
//            g.setFontRendering(FontRendering.VECTORS);
//        } else {
//            g.setFontRendering(FontRendering.TEXT);
//        }
//
//        setGraphicsDelegate(g);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSVG() {
        // dont need here currently
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        init();
    }

    /**
     * {@inheritDoc}
     */
    public void export(OutputStream stream, KlighdCanvas canvas, boolean viewPort, int scale,
            String subFormatId) {

        // FIXME ... dont do this again, improve the KlighdAbstractSVGGraphics ... remove the "svg"
        PCamera camera = canvas.getCamera();
        Rectangle2D bounds = null;
        if (viewPort) {
            bounds = camera.getBounds();
        } else {
            // we want the svg to contain all elements, not just the visible area
            bounds = camera.getRoot().getFullBounds();
        }

        // set up the paint context
//        PDFGraphics2D graphics = (PDFGraphics2D) getGraphicsDelegate();
        
        PDFGraphics2D graphics =
                new PDFGraphics2D(bounds.getX(), bounds.getY(), bounds.getWidth(),
                        bounds.getHeight());

        // set text handling
        if (1 == 1) {
            graphics.setFontRendering(FontRendering.VECTORS);
        } else {
            graphics.setFontRendering(FontRendering.TEXT);
        }

        setGraphicsDelegate(graphics);

        // the following clip set is required in order to get rid of the one set in
        // the constructor call above, which lets Inkscape & browsers go crazy
        if (viewPort) {
            // graphics.setClip(camera.getBounds());
            graphics.setClip(bounds);
        }

        final PPaintContext paintContext = new PPaintContext(this);
        paintContext.setRenderQuality(PPaintContext.HIGH_QUALITY_RENDERING);

        // perform the painting
        if (viewPort) {
            // only render the current viewport
            camera.fullPaint(paintContext);
        } else {
            // render the whole diagram
            @SuppressWarnings("unchecked")
            List<PLayer> layersReference = (List<PLayer>) camera.getLayersReference();
            for (PLayer layer : layersReference) {
                layer.fullPaint(paintContext);
            }
        }

        // write to stream
        try {
            stream.write(graphics.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
