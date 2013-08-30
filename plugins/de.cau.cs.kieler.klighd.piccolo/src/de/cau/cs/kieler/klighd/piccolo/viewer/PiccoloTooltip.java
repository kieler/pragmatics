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
package de.cau.cs.kieler.klighd.piccolo.viewer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.Timer;

import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Strings;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IGraphElement;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPaths;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdStyledText;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolox.swt.SWTTimer;

/**
 * The class realizes a tooltip for the
 * {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdCanvas KlighdCanvas}. Tooltips are
 * either retrieved from a {@link PNode}'s root {@link KRendering} or, if this is not available,
 * from the corresponding {@link KNode}'s {@link KShapeLayout}.
 * 
 * TODO: Evaluate the realization of the tooltip by means of an SWT Widget, too, although that won't
 *  be available for the web-based approach.
 * 
 * @author uru
 * @author chsch
 */
public class PiccoloTooltip {

    private Display display;
    private PCamera camera;

    // graphic elements representing the tooltip
    private KlighdPath root;
    private KlighdStyledText tooltip;

    // configuration constants
    private static final int TOOLTIP_DELAY = 750;
    private static final int ROUNDNESS = 5;
    private static final int OPACITY = 220;
    private static final int INSETS = 5;

    /**
     * @param display
     *            the current display of the {@link de.cau.cs.kieler.klighd.viewers.KlighdViewer
     *            KlighdViewer}.
     * @param camera
     *            the camera of the current {@link de.cau.cs.kieler.klighd.viewers.KlighdViewer
     *            KlighdViewer}'s canvas.
     */
    public PiccoloTooltip(final Display display, final PCamera camera) {
        this.display = display;
        this.camera = camera;

        // create the text element for the tooltip
        tooltip = new KlighdStyledText("");
        tooltip.setPickable(false);
        tooltip.setFont(new FontData(KlighdConstants.DEFAULT_FONT_NAME,
                KlighdConstants.DEFAULT_TOOL_TIP_FONT_SIZE, KlighdConstants.DEFAULT_FONT_STYLE));

        // create a rounded box where the tooltip will be displayed
        root = KlighdPaths.createRoundRectangle(0, 0, 0, 0, ROUNDNESS, ROUNDNESS);
        root.setVisible(false);
        root.setPaint(Color.WHITE);
        root.setPaintAlpha(OPACITY);
        root.addChild(tooltip);

        // add to the camera
        camera.addChild(root);
        camera.addInputEventListener(new TooltipListener());

    }

    
    /**
     * Listens to the {@link PCamera} and reacts to mouse move events in order to display a tooltip
     * where available.
     */
    private class TooltipListener extends PBasicInputEventHandler {
        
        /** The last mouseover's KRendering. */
        private KRendering rendering;
        
        /** The last mouseover's KNode (only used if no rendering is available). */
        private KNode knode;
        
        /** Position at which the tooltip is displayed. */
        private Point2D mousePos;
        
        /** The timer used to realize the delay of the tooltip occurrence. */ 
        private Timer timer = null;
        
        /** Flag indicating whether the tooltip is valid and can be shown. */
        private boolean visible = false;

        /**
         * Constructor.
         */
        public TooltipListener() {
            timer = new SWTTimer(display, TOOLTIP_DELAY, timeOutListener);
            timer.setRepeats(false);
        }

        /**
         * {@inheritDoc}
         */
        public void mouseMoved(final PInputEvent event) {
            updateToolTip(event);
        }

        /**
         * {@inheritDoc}
         */
        public void mouseDragged(final PInputEvent event) {
            updateToolTip(event);
        }

        private void updateToolTip(final PInputEvent event) {

            // first reset the tooltip timer and configuration
            timer.stop();
            visible = false;
            root.setVisible(visible);
            
            // retrieve the mouse we are over
            // PNode n = event.getInputManager().getMouseOver().getPickedNode();
            PNode n = event.getPickedNode();

            if (n instanceof IGraphElement<?>) {
                visible = true;
                IGraphElement<?> graphElement = (IGraphElement<?>) n;
                AbstractKGERenderingController<?, ?> ctr = graphElement.getRenderingController();
                if (ctr == null) {
                    // FIXME the ctr is not supposed to be null, needs to be fixed in KLighD itself
                    return;
                }
                rendering = ctr.getCurrentRendering();

                // fallback to the KNode if no rendering is specified
                if (rendering == null) {
                    Object ge = graphElement.getGraphElement();
                    if (ge instanceof KNode) {
                        knode = (KNode) ge;
                    }
                }

                // only start the timer if we retrieved an element
                if (rendering == null && knode == null) {
                    return;
                }
                // get the mouse position
                mousePos = event.getCanvasPosition();
                event.getPath().canvasToLocal(mousePos, camera);

                // start the timer
                timer.start();
            }
        }

        /** The {@link ActionListener} called once the timer expired, lets the tooltip appear. */
        private ActionListener timeOutListener = new ActionListener() {

            public void actionPerformed(final ActionEvent e) {

                // retrieve the tooltip
                String tooltipText = "";
                if (rendering != null) {
                    tooltipText = rendering.getProperty(KlighdProperties.TOOLTIP);
                } else if (knode != null) {
                    KShapeLayout l = knode.getData(KShapeLayout.class);
                    tooltipText = l.getProperty(KlighdProperties.TOOLTIP);
                } else {
                    return;
                }

                // return if no tooltip was assembled
                if (Strings.isNullOrEmpty(tooltipText)) {
                    return;
                }

                // prepare the tooltip element
                tooltip.setText(tooltipText);
                root.setOffset(mousePos.getX() + 2 * INSETS, mousePos.getY() + 2 * INSETS);

                // adapt bounds to the text
                PBounds tooltipBounds = tooltip.getBounds();
                root.setPathToRoundRectangle((float) tooltipBounds.x - INSETS,
                        (float) tooltipBounds.y - INSETS, (float) tooltipBounds.width + 2 * INSETS,
                        (float) tooltipBounds.height + 2 * INSETS, ROUNDNESS, ROUNDNESS);

                // set visible and repaint
                root.setVisible(visible);
                root.invalidatePaint();
                root.invalidateLayout();
            }
        };
    }
}
