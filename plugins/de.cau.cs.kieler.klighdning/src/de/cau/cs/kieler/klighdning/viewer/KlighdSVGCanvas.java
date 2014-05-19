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
package de.cau.cs.kieler.klighdning.viewer;

import java.awt.Cursor;
import java.awt.geom.Rectangle2D;

import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import de.cau.cs.kieler.klighd.piccolo.svg.KlighdAbstractSVGGraphics;
import de.cau.cs.kieler.klighd.piccolo.svg.SVGGeneratorManager;
import edu.umd.cs.piccolo.PComponent;
import edu.umd.cs.piccolo.PRoot;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * This class can be used to render a {@link de.cau.cs.kieler.core.kgraph.KNode KNode} model to an
 * SVG. The piccolo infrastructure is used to create piccolo elements for the respective KGraph
 * model.
 * 
 * Using an svg generator implementation (e.g., batik's
 * {@link org.apache.batik.svggen.SVGGraphics2D SVGGraphics2D} generator), an SVG is created.
 * 
 * The canvas can either be instantiated and the {@link #render()} method used to retrieve the svg,
 * or one of the static render methods can be used.
 * 
 * Basic usage: <code>
 *  TODO
 * </code>
 * 
 * Inspired by the {@link edu.umd.cs.piccolo.POffscreenCanvas POffscreenCanvas} class.
 * 
 * @author uru
 * @author chsch (moved class more less completely from outdated .klighd.piccolo.svg)
 * 
 */
public class KlighdSVGCanvas implements PComponent {

    private KlighdMainCamera camera;

    private static final PBounds INITIAL_BOUNDS = new PBounds(0, 0, 800, 600);

    private boolean textAsShapes = false;
    private boolean embedFonts = false;

    /**
     * 
     */
    public KlighdSVGCanvas() {
        this(INITIAL_BOUNDS, false, false);
    }

    /**
     * @param textAsShapes
     *            whether text should be rendered as a shape.
     */
    public KlighdSVGCanvas(final boolean textAsShapes) {
        this(INITIAL_BOUNDS, textAsShapes, false);
    }

    /**
     * @param textAsShapes
     *            whether text should be rendered as a shape.
     * @param embedFonts
     *            whether the texts' fonts shall be embedded in the output
     */
    public KlighdSVGCanvas(final boolean textAsShapes, final boolean embedFonts) {
        this(INITIAL_BOUNDS, textAsShapes, embedFonts);
    }

    /**
     * @param bounds
     *            the initial bounds of this canvas
     * @param textAsShapes
     *            whether text should be rendered as a shape.
     * @param embedFonts
     *            whether the texts' fonts shall be embedded in the output
     */
    public KlighdSVGCanvas(final Rectangle2D bounds, final boolean textAsShapes,
            final boolean embedFonts) {
        this.textAsShapes = textAsShapes;
        this.embedFonts = embedFonts;

        // create a new main camera
        camera = new KlighdMainCamera();
        
        // the basic PRoot is sufficient as this canvas doesn't rely on any SWT stuff
        final PRoot root = new PRoot();
        root.addChild(camera);

        // set the bounds of the camera,
        //  this is the actual size of the camera, not of what it is viewing
        camera.setBounds(bounds);
        // camera.setComponent(this);
    }

    private static KlighdAbstractSVGGraphics createGraphics(final boolean textAsShapes,
            final boolean embedFonts, final Rectangle2D bounds) {
        return createGraphics(textAsShapes, embedFonts, bounds, DEFAULT_GENERATOR);
    }

    private static KlighdAbstractSVGGraphics createGraphics(final boolean textAsShapes,
            final boolean embedFonts, final Rectangle2D bounds, final String svgGen) {

        return SVGGeneratorManager.createGraphics(svgGen, bounds, textAsShapes, embedFonts);
    }
    
    /**
     * @return the rendered SVG.
     */
    public String render() {

        // TODO
        // client side should send its available viewarea!
        // use this to set the camera's bounds.
        // don't touch the cameras bound here (in the canvas context)

        // TODO
        // is this ok in case we do not zoomToFit every time?
        // basically the svg's viewport might have to be bigger
        // but that should be fine? make it the size of the top level node
        // and translate it by the camera bounds?

        // remeber old bounds
        final Rectangle2D bounds = camera.getBounds();
        // camera.setBounds(bounds);

        // create a new graphics object
        final KlighdAbstractSVGGraphics graphics = createGraphics(textAsShapes, embedFonts, bounds);
        // initially clear the graphics object
        graphics.clear();

        // set up the paint context
        final PPaintContext paintContext = new PPaintContext(graphics);

        // the following clip set is required in order to get rid of the one set in
        // the constructor call above, which lets Inkscape & browsers go crazy
        graphics.setClip(bounds);
        paintContext.setRenderQuality(PPaintContext.HIGH_QUALITY_RENDERING);

        camera.validateFullPaint();

        // perform the painting
        camera.fullPaint(paintContext);

        // return the created svg
        final String svg = graphics.getSVG();
        return svg;
    }

    /**
     * @return the camera
     */
    public KlighdMainCamera getCamera() {
        return camera;
    }

    /*---------------------------------------------------------------------
     * Render from a PCamera.
     */

    /** The default svg generator to use. */
    public static final String DEFAULT_GENERATOR = "de.cau.cs.kieler.klighd.piccolo.svg.batik";

    /**
     * {@inheritDoc}
     * 
     * @deprecated not implemented for this offscreen canvas.
     */
    public void repaint(final PBounds bounds) {
    }

    /**
     * {@inheritDoc}
     * 
     * @deprecated not implemented for this offscreen canvas.
     */
    public void paintImmediately() {
    }

    /**
     * {@inheritDoc}
     * 
     * @deprecated not implemented for this offscreen canvas.
     */
    public void pushCursor(final Cursor cursor) {
    }

    /**
     * {@inheritDoc}
     * 
     * @deprecated not implemented for this offscreen canvas.
     */
    public void popCursor() {
    }

    /**
     * {@inheritDoc}
     * 
     * @deprecated not implemented for this offscreen canvas.
     */
    public void setInteracting(final boolean interacting) {
    }
}
