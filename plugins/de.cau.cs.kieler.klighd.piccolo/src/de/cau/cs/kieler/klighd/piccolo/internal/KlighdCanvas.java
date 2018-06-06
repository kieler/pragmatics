/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.InputEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdFocusEventListener;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdInputManager;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdKeyEventListener;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.NodeDisposeListener;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PInputManager;
import edu.umd.cs.piccolo.event.PPanEventHandler;
import edu.umd.cs.piccolo.event.PZoomEventHandler;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPickPath;
import edu.umd.cs.piccolox.swt.PSWTCanvas;
import edu.umd.cs.piccolox.swt.PSWTRoot;

/**
 * A specialized version of {@link PSWTCanvas} with lots of KLighD-specific customizations.
 *
 * @author chsch
 */
public class KlighdCanvas extends PSWTCanvas {

    /**
     * The {@link de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics KlighdSWTGraphics} abstractor to
     * be incorporated while drawing the diagram.
     */
    private KlighdSWTGraphicsEx graphics;

    /**
     * The {@link PInputManager} employed in this diagram canvas, is cached here for the sake of
     * squeezing out some more performance, which is most effective while dispatching mouse move
     * events.
     */
    private PInputManager inputManager;

    
    /**
     * Construct a canvas with the basic scene graph consisting of a root node, a camera, and a
     * layer (via <code>super(...)</code> and {@link #createBasicSceneGraph()}). The original event
     * handlers for zooming and panning coming via <code>super(...)</code> are removed, appropriate
     * ones are installed later on by users of this class.
     *
     * @param parent
     *            component onto which the canvas is installed
     * @param style
     *            {@link Composite} style of <code>this</code> {@link KlighdCanvas}
     */
    public KlighdCanvas(final Composite parent, final int style) {
        this(parent, style, KlighdProperties.CANVAS_COLOR.getDefault());
    }
    
    /**
     * Construct a canvas with the basic scene graph consisting of a root node, a camera, and a
     * layer (via <code>super(...)</code> and {@link #createBasicSceneGraph()}). The original event
     * handlers for zooming and panning coming via <code>super(...)</code> are removed, appropriate
     * ones are installed later on by users of this class.
     *
     * @param parent
     *            component onto which the canvas is installed
     * @param style
     *            {@link Composite} style of <code>this</code> {@link KlighdCanvas}
     * @param backgroundColor
     *            The {@link Color} of the canvas background
     */
    public KlighdCanvas(final Composite parent, final int style, final Color backgroundColor) {
        super(parent, style);
        
        // Set the background color of the canvas
        this.backgroundColor = backgroundColor;
        
        // make sure this canvas is not stored in this horrible static reference!
        PSWTCanvas.CURRENT_CANVAS = null;

        // remove the original event handlers as they require AWT event type codes
        //  instances of this class are augmented with SWT-based event handlers
        //  e.g. in PiccoloViewer or PiccoloOutlinePage
        this.removeInputEventListener(super.getZoomEventHandler());
        this.removeInputEventListener(super.getPanEventHandler());

        this.graphics = new KlighdSWTGraphicsImpl(parent.getDisplay());

        // this reduces flickering drastically
        this.setDoubleBuffered(true);

        this.addDisposeListener(new DisposeListener() {

            public void widgetDisposed(final DisposeEvent e) {
                final KlighdCanvas thisCanvas = KlighdCanvas.this;

                NodeDisposeListener.disposePNode(thisCanvas.getCamera().getRoot());

                // this horrible static reference may preserve a pick path containing
                //  a reference to the camera containing a reference to ...,
                //  thus. preventing the gc from disposing almost every diagram data
                PPickPath.CURRENT_PICK_PATH = null;

                // this way the back buffer image will be disposed!
                thisCanvas.setDoubleBuffered(false);

                if (thisCanvas.graphics != null) {
                    thisCanvas.graphics.dispose();
                    thisCanvas.graphics = null;
                }
                if (thisCanvas.inputManager != null) {
                    thisCanvas.inputManager = null;
                }
            }
        });
    }

    /**
     * Named specialized {@link PSWTRoot} contributing the customized {@link KlighdInputManager}.
     *
     * @author chsch
     */
    protected class KlighdRoot extends PSWTRoot {

        private static final long serialVersionUID = 5244208939770217529L;

        private PInputManager inputManager = null;

        /**
         * Standard constructor.
         */
        public KlighdRoot() {
            super(KlighdCanvas.this);
        }

        @Override
        public PInputManager getDefaultInputManager() {
            if (inputManager == null) {
                inputManager = new KlighdInputManager();
                addInputSource(inputManager);

                if (!KlighdCanvas.this.isDisposed()) {
                    // TODO: Make sure this check doesn't lead to a memory leak 
                    KlighdCanvas.this.addListener(SWT.Dispose, new Listener() {
                        public void handleEvent(final Event event) {
                            removeInputSource(inputManager);
                            inputManager = null;
                        }
                    });
                }
            }
            return inputManager;
        }
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This customization is required for injecting the specialized root node providing the
     * KLighD-specific {@link KlighdInputManager}. This input manager replaces the event evaluation
     * based on AWT's event type codes by one based on SWT's event type codes.<br>
     * <br>
     * Besides, no initial instance of {@link edu.umd.cs.piccolo.PLayer PLayer} is added - our
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeTopNode KNodeTopNode} takes that
     * part; see
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController#DiagramController(
     * de.cau.cs.kieler.klighd.kgraph.KNode, KlighdMainCamera, boolean, boolean)
     * DiagramController.DiagramController(KNode, KlighdMainCamera, boolean, boolean)}.
     */
    @Override // SUPPRESS CHECKSTYLE Javadoc, see http://sourceforge.net/p/checkstyle/bugs/592/
    public PCamera createBasicSceneGraph() {
        return new KlighdMainCamera(new KlighdRoot());
    }

    @Override
    protected Graphics2D getGraphics2D(final GC gc, final Device device) {
        graphics.setDevice(device);
        graphics.setGC(gc);
        return (Graphics2D) graphics;
    }

    @Override
    protected KlighdPaintContext getPaintContext(final Graphics2D g2) {
        return KlighdPaintContext.createDiagramPaintContext(graphics);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KlighdMainCamera getCamera() {
        return (KlighdMainCamera) super.getCamera();
    }

    /**
     * With this specialized implementation I register customized event listeners that do not
     * translate SWT events into AWT ones. The original event listeners are omitted.
     */
    @Override
    protected void installInputSources() {

        this.addFocusListener(new KlighdFocusEventListener(this));
        this.addKeyListener(new KlighdKeyEventListener(this));

        final KlighdMouseEventListener mouseListener = new KlighdMouseEventListener(this);
        this.addMouseListener(mouseListener);
        this.addMouseMoveListener(mouseListener);
        this.addMouseTrackListener(mouseListener);
        this.addMouseWheelListener(mouseListener);
        this.addDragDetectListener(mouseListener);
        this.addGestureListener(mouseListener);
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This specialization simply changes visibility to 'public' in order to be used in
     * {@link KlighdMouseEventListener}, for example.
     */
    @Override
    public void sendInputEventToInputManager(final InputEvent awtEvent, final int type) {
        // instead of calling the super implementation
        //  save some performance and keep and call the input manager directly

        if (this.inputManager == null) {
            this.inputManager = getRoot().getDefaultInputManager();
        }
        this.inputManager.processEventFromCamera(awtEvent, type, getCamera());
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This specialization simply deactivates the inherited behavior. Since we do not (have
     * opportunities to?) change the rendering quality, there is no need repaint the whole visible
     * diagram area after user interaction.
     */
    @Override
    public void setInteracting(final boolean isInteracting) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PPanEventHandler getPanEventHandler() {
        final String msg = "KLighD Piccolo viewer: "
                + "Method is not supported as a different pan event handler is deployed.";
        throw new UnsupportedOperationException(msg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PZoomEventHandler getZoomEventHandler() {
        final String msg = "KLighD Piccolo viewer: "
                + "Method is not supported as a different zoom event handler is deployed.";
        throw new UnsupportedOperationException(msg);
    }


    /**
     * {@inheritDoc}<br>
     * <br>
     * This specialized method checks the validity of the canvas
     * before something is painted in order to avoid the 'Widget is disposed' errors.
     */
    @Override
    public void repaint(final PBounds bounds) {
        if (!this.isDisposed()) {
            super.repaint(bounds);
        }
    }

    private boolean resize = false;

    @Override
    public void setBounds(final int x, final int y, final int newWidth, final int newHeight) {
        // extracted the following check from the super implementation
        //  in order to allow to roll back most of the customizations in the Piccolo2D code some day
        if (newWidth == 0 || newHeight == 0) {
            // chsch: introduced this check as the workbench sometimes determines width
            //  and/or height of zero that results in an exception later on.
            return;
        } else {
            final PBounds bounds = getCamera().getBoundsReference();
            resize = bounds.width != newWidth || bounds.height != newHeight;

            super.setBounds(x, y, newWidth, newHeight);
        }
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * In contrast to the super method this specialized method forces a back buffer recreation even
     * if the canvas size has been decreased, e.g., after a KLighD diagram view as been maximized
     * and "re-normalized". Otherwise the oversized back buffer image will be kept until the diagram
     * is closed.
     */
    @Override
    protected boolean backBufferNeedsResizing(final int newWidth, final int newHeight) {
        return resize && getDoubleBuffered();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
