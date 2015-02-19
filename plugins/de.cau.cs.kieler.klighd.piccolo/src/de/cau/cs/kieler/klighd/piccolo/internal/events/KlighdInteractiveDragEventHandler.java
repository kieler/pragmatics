/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.events;

import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;

import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 *
 * @author chsch
 */
public class KlighdInteractiveDragEventHandler extends PDragSequenceEventHandler {

    private static final int HALF_OPACITY = KlighdConstants.ALPHA_FULL_OPAQUE / 2;

    private final PiccoloViewer pViewer;

    private PNode node;

    /**
     * Constructor.
     *
     * @param pViewer
     *            the {@link PiccoloViewer} this handler is attached to
     */
    public KlighdInteractiveDragEventHandler(final PiccoloViewer pViewer) {
        this.pViewer = pViewer;
    }

    @Override
    protected boolean shouldStartDragInteraction(final PInputEvent event) {
        return (event.getPickedNode() instanceof KNodeNode) && event.isShiftDown()
                && super.shouldStartDragInteraction(event);
    }

    @Override
    protected void startDrag(final PInputEvent event) {

        super.startDrag(event);

        final KNodeNode nodeNode = (KNodeNode) event.getPickedNode();
        final Rectangle2D bounds = nodeNode.getFullBounds();

        final PAffineTransform invertedNodeNodeTransform =
                new PAffineTransform(NodeUtil.invert(nodeNode.getTransform()));
        invertedNodeNodeTransform.transform(bounds, bounds);

        node = new PNode() {

            private static final long serialVersionUID = -3737436221817065320L;

            @Override
            public void fullPaint(final PPaintContext paintContext) {
                paintContext.pushTransform(getTransformReference(false));

                final KlighdPaintContext pc = (KlighdPaintContext) paintContext;
                final KlighdSWTGraphics graphics = pc.getKlighdGraphics();
                final int alpha = graphics.getAlpha();

                graphics.setAlpha(HALF_OPACITY);

                paintContext.pushTransform(invertedNodeNodeTransform);
                nodeNode.fullPaint(paintContext);

                paintContext.popTransform(invertedNodeNodeTransform);

                graphics.setAlpha(alpha);
                paintContext.popTransform(getTransformReference(false));
            };
        };
        node.setBounds(bounds);
        node.setTransform(event.getPath().getPathTransformTo(nodeNode));

        event.getTopCamera().addChild(node);
        event.setHandled(true);
    }

    @Override
    protected void drag(final PInputEvent event) {
        super.drag(event);

        final Dimension2D d = event.getDelta();
        node.translate(d.getWidth(), d.getHeight());

        event.setHandled(true);
    }

    @Override
    protected void endDrag(final PInputEvent event) {
        super.endDrag(event);
        node.removeFromParent();

//        pViewer.getControl().getDisplay().asyncExec(new Runnable() {
//            public void run() {
//                LightDiagramServices.layoutDiagram(pViewer.getViewContext());
//            }
//        });
        event.setHandled(true);
    }
}
