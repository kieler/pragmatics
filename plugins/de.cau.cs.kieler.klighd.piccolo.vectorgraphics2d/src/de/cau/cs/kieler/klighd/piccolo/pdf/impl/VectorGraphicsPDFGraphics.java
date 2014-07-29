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

import org.eclipse.swt.widgets.Control;

import de.cau.cs.kieler.klighd.IDiagramExporter;
import de.cau.cs.kieler.klighd.piccolo.export.KlighdAbstractSVGGraphics;
import de.cau.cs.kieler.klighd.piccolo.export.KlighdCanvasExporter;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import de.erichseifert.vectorgraphics2d.PDFGraphics2D;
import de.erichseifert.vectorgraphics2d.VectorGraphics2D.FontRendering;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * 
 * FIXME generalize the abstract svg graphics ...
 * 
 * This is still a proof of concept, major cleanup required!
 * 
 * @author uru
 */
public class VectorGraphicsPDFGraphics extends KlighdAbstractSVGGraphics implements IDiagramExporter {

//    private Rectangle2D bounds;
//    private boolean textAsShapes;
//
//    /**
//     * @param bounds
//     *            the bounds will be set as viewport values for the resulting root <svg ..> tag.
//     * @param textAsShapes
//     *            whether text should be rendered as shapes
//     */
//    public VectorGraphicsPDFGraphics(final Rectangle2D bounds, final Boolean textAsShapes) {
    /**
     * Constructor.
     */
    public VectorGraphicsPDFGraphics() {
        super(null);
//        this.bounds = bounds;
//        this.textAsShapes = textAsShapes;

        init();

    }

    private void init() {

        final PDFGraphics2D g = new PDFGraphics2D(0, 0, 1000, 1000);

//        // set text handling
//        if (textAsShapes) {
//            g.setFontRendering(FontRendering.VECTORS);
//        } else {
//            g.setFontRendering(FontRendering.TEXT);
//        }

        setGraphicsDelegate(g);

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
    @Override
    public void stream(final OutputStream output) throws IOException {
        output.write(((PDFGraphics2D) getGraphicsDelegate()).getBytes());
        output.flush();
    }
    
    /**
     * {@inheritDoc}
     */
    public void export(final OutputStream stream, final Control control,
            final boolean cameraViewport, final int scale, final boolean textAsShapes,
            final boolean embedFonts, final String subFormatId) {
        this.delegate.export(stream, control, cameraViewport, scale, textAsShapes, embedFonts,
                subFormatId);
    }

    private KlighdCanvasExporter delegate = new KlighdCanvasExporter() {
        
        @Override
        public void export(final OutputStream stream, final KlighdCanvas canvas,
                final boolean cameraViewport, final int scale, final boolean textAsShapes,
                final boolean embedFonts, final String subFormatId) {
            VectorGraphicsPDFGraphics.this.export(stream, canvas, cameraViewport, scale,
                    textAsShapes, embedFonts, subFormatId);
        }
    };

    /**
     * {@inheritDoc}
     */
    public void export(final OutputStream stream, final KlighdCanvas canvas,
            final boolean viewPort, final int scale, final boolean textAsShapes,
            final boolean embedFonts, final String subFormatId) {

        // FIXME ... dont do this again, improve the KlighdAbstractSVGGraphics ... remove the "svg"
        final PCamera camera = canvas.getCamera();
        Rectangle2D bounds = null;
        if (viewPort) {
            bounds = camera.getBounds();
        } else {
            // we want the svg to contain all elements, not just the visible area
            bounds = camera.getRoot().getFullBounds();
        }

        // set up the paint context
//        PDFGraphics2D graphics = (PDFGraphics2D) getGraphicsDelegate();
        
        final PDFGraphics2D graphics =
                new PDFGraphics2D(bounds.getX(), bounds.getY(), bounds.getWidth(),
                        bounds.getHeight());

        // set text handling
        if (textAsShapes) {
            graphics.setFontRendering(FontRendering.VECTORS);
        } else {
            graphics.setFontRendering(FontRendering.TEXT);
        }
        
        // TODO: any way to configure the embedding of used fonts???

        setGraphicsDelegate(graphics);

        // the following clip set is required in order to get rid of the one set in
        // the constructor call above, which lets Inkscape & browsers go crazy
        if (viewPort) {
            // graphics.setClip(camera.getBounds());
            graphics.setClip(bounds);
        }

        final KlighdPaintContext paintContext = new KlighdPaintContext(this);
        paintContext.setRenderQuality(PPaintContext.HIGH_QUALITY_RENDERING);

        // perform the painting
        if (viewPort) {
            // only render the current viewport
            camera.fullPaint(paintContext);
        } else {
            // render the whole diagram
            @SuppressWarnings("unchecked")
            final
            List<PLayer> layersReference = camera.getLayersReference();
            for (final PLayer layer : layersReference) {
                layer.fullPaint(paintContext);
            }
        }

        // write to stream
        try {
            stream.write(graphics.getBytes());
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
