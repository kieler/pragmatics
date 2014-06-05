/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.batik;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGeneratorContext.GraphicContextDefaults;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.util.SVGConstants;
import org.apache.fop.svg.PDFTranscoder;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.widgets.Control;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import de.cau.cs.kieler.klighd.IDiagramExporter;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.export.KlighdAbstractSVGGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * 
 * Save klighd diagram as pdf using batik.
 * 
 * @author ckru
 * 
 */
public class BatikPDFGraphics extends KlighdAbstractSVGGraphics implements IDiagramExporter {

    private SVGGraphics2D graphicsDelegate;

    /**
     * {@inheritDoc}
     */
    public BatikPDFGraphics() {
        super(null);
    }

    @Override
    public String getSVG() {
        final StringWriter sw = new StringWriter();
        try {
            graphicsDelegate.stream(sw, true);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    @Override
    public void clear() {
        getSVG();
    }

    @Override
    public void stream(OutputStream output) throws IOException {
        PNGTranscoder t = new PNGTranscoder();
        Reader r = new StringReader(getSVG());
        TranscoderInput in = new TranscoderInput(r);
        TranscoderOutput out = new TranscoderOutput(output);
        try {
            t.transcode(in, out);
        } catch (TranscoderException e) {
            e.printStackTrace();
        }

    }

    /**
     * {@inheritDoc}
     */
    public void export(OutputStream stream, Control control, boolean cameraViewport, int scale,
            boolean textAsShapes, boolean embedFonts, String subFormatId) {

        final PCamera camera = ((KlighdCanvas) control).getCamera();

        Rectangle2D bounds = null;
        if (cameraViewport) {
            bounds = camera.getBounds();
        } else {
            // we want the svg to contain all elements, not just the visible area
            bounds = camera.getRoot().getFullBounds();
        }

        // Get a DOMImplementation.
        final DOMImplementation domImpl = SVGDOMImplementation.getDOMImplementation();

        // Create an instance of org.w3c.dom.Document.
        final Document document = domImpl.createDocument(SVGConstants.SVG_NAMESPACE_URI,
                "svg", null); //$NON-NLS-1$

        // assemble context
        final SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(document);
        ctx.setEmbeddedFontsOn(embedFonts);

        final GraphicContextDefaults defaults = new GraphicContextDefaults();
        ctx.setGraphicContextDefaults(defaults);

        defaults.setBackground(Color.WHITE);

        // this setting influences the default stroke color as well as the default paint (fill)
        // color!!
        defaults.setPaint(Color.BLACK);

        final LineAttributes lineAttributes = KlighdConstants.DEFAULT_LINE_ATTRIBUTES;
        defaults.setStroke(new BasicStroke(lineAttributes.width, lineAttributes.cap - 1,
                lineAttributes.join - 1, lineAttributes.miterLimit, lineAttributes.dash,
                lineAttributes.dashOffset));

        defaults.setFont(new Font(KlighdConstants.DEFAULT_FONT_NAME,
                KlighdConstants.DEFAULT_FONT_STYLE, KlighdConstants.DEFAULT_FONT_SIZE));

        final RenderingHints hints = new RenderingHints(null);
        defaults.setRenderingHints(hints);

        // be careful while modifying these definitions
        // they're evaluated in org.apache.batik.svggen.SVGRenderingHints.toSVG(...)

        // + RENDERING -> sets all other hints to initial value.
        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        // + FRACTIONAL_METRICS -> sets initial values for text-rendering and shape-rendering.
        hints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        // + ANTIALIASING -> shape-rendering and text-rendering
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);
        // + COLOR_RENDERING -> color-rendering
        hints.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
        // + INTERPOLATION -> image-rendering
        hints.put(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        // + TEXT_ANTIALIASING -> text-rendering
        hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // create and configure the graphics object
        graphicsDelegate = new SVGGraphics2D(ctx, textAsShapes);
        graphicsDelegate.setSVGCanvasSize(new Dimension((int) Math.ceil(bounds.getWidth()),
                (int) Math.ceil(bounds.getHeight())));

        // IMPORTANT
        super.setGraphicsDelegate(graphicsDelegate);

        final PPaintContext paintContext = new PPaintContext(this);
        paintContext.setRenderQuality(PPaintContext.HIGH_QUALITY_RENDERING);

        // perform the painting
        if (cameraViewport) {
            // only render the current viewport
            camera.fullPaint(paintContext);
        } else {
            // render the whole diagram
            @SuppressWarnings("unchecked")
            final List<PLayer> layersReference = camera.getLayersReference();
            for (final PLayer layer : layersReference) {
                layer.fullPaint(paintContext);
            }
        }

        // Transcode the image to pdf
        PDFTranscoder t = new PDFTranscoder();
        String svg = getSVG();
        Reader r = new StringReader(svg);
        TranscoderInput in = new TranscoderInput(r);
        TranscoderOutput out = new TranscoderOutput(stream);
        try {
            t.transcode(in, out);
            stream.flush();
        } catch (TranscoderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
