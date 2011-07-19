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
package de.cau.cs.kieler.klay.info.views;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * A canvas that is able to paint KIML layout graphs.
 * 
 * @author msp
 */
public class LayoutGraphCanvas extends Canvas implements PaintListener {

    /** alpha value for nodes. */
    private static final int NODE_ALPHA = 100;
    /** font size for nodes. */
    private static final int NODE_FONT_SIZE = 10;
    /** alpha value for ports. */
    private static final int PORT_ALPHA = 230;
    /** font size for ports. */
    private static final int PORT_FONT_SIZE = 6;
    /** alpha value for edges. */
    private static final int EDGE_ALPHA = 230;
    /** font size for edges. */
    private static final int EDGE_FONT_SIZE = 8;
    /** length of edge arrows. */
    private static final float ARROW_LENGTH = 7.0f;
    /** width of edge arrows. */
    private static final float ARROW_WIDTH = 5.0f;
    
    // CHECKSTYLEOFF MagicNumber

    /**
     * Rectangle class used to mark painted objects.
     */
    public class PaintRectangle {
        private int x, y, width, height;
        private boolean painted = false;

        /**
         * Creates a paint rectangle from the four defining values.
         * 
         * @param thex
         *            x coordinate value of the upper left corner
         * @param they
         *            y coordinate value of the upper left corner
         * @param thewidth
         *            width of the rectangle
         * @param theheight
         *            height of the rectangle
         * @param offset
         *            offset to be added to coordinate values
         */
        PaintRectangle(final float thex, final float they, final float thewidth,
                final float theheight, final KVector offset) {
            this.x = (int) Math.round(thex + offset.x);
            this.y = (int) Math.round(they + offset.y);
            this.width = Math.round(thewidth);
            this.height = Math.round(theheight);
        }

        /**
         * Creates a paint rectangle from a shape layout object.
         * 
         * @param shapeLayout
         *            shape layout from which values shall be taken
         * @param offset
         *            offset to be added to coordinate values
         */
        PaintRectangle(final KShapeLayout shapeLayout, final KVector offset) {
            this.x = (int) Math.round(shapeLayout.getXpos() + offset.x);
            this.y = (int) Math.round(shapeLayout.getYpos() + offset.y);
            this.width = Math.max(Math.round(shapeLayout.getWidth()), 3);
            this.height = Math.max(Math.round(shapeLayout.getHeight()), 3);
        }

        /**
         * Creates a paint rectangle from an edge layout object.
         * 
         * @param edgeLayout
         *            edge layout from which the values shall be determined
         * @param offset
         *            offset to be added to coordinate values
         */
        PaintRectangle(final KEdgeLayout edgeLayout, final KVector offset) {
            float minX = edgeLayout.getSourcePoint().getX(), minY = edgeLayout.getSourcePoint()
                    .getY();
            float maxX = minX, maxY = minY;
            for (KPoint point : edgeLayout.getBendPoints()) {
                minX = Math.min(minX, point.getX());
                minY = Math.min(minY, point.getY());
                maxX = Math.max(maxX, point.getX());
                maxY = Math.max(maxY, point.getY());
            }
            minX = Math.min(minX, edgeLayout.getTargetPoint().getX());
            minY = Math.min(minY, edgeLayout.getTargetPoint().getY());
            maxX = Math.max(maxX, edgeLayout.getTargetPoint().getX());
            maxY = Math.max(maxY, edgeLayout.getTargetPoint().getY());
            this.x = (int) Math.round(minX + offset.x);
            this.y = (int) Math.round(minY + offset.y);
            this.width = Math.round(maxX - minX);
            this.height = Math.round(maxY - minY);
        }

        /**
         * Determines whether the given rectangle intersects with the receiver.
         * 
         * @param other
         *            the rectangle to check for intersection
         * @return true if the other rectangle intersects with this one
         */
        public boolean intersects(final PaintRectangle other) {
            return (other.x < this.x + this.width) && (other.y < this.y + this.height)
                    && (other.x + other.width > this.x) && (other.y + other.height > this.y);
        }
    }

    /**
     * Iterator class used for finding junctures in lists of bend points.
     */
    private class BendsIterator implements Iterator<KPoint> {
        private List<KPoint> bendPoints;
        private boolean forward;
        private ListIterator<KPoint> pointsIter;
        private KPoint startPoint;

        /**
         * Creates a bends iterator from an edge layout.
         * 
         * @param edgeLayout
         *            edge layout with bend points information
         * @param forward
         *            if true, bend points are iterated with the <code>next()</code> method, else
         *            <code>previous()</code> is used
         */
        BendsIterator(final KEdgeLayout edgeLayout, final boolean forward) {
            this.bendPoints = edgeLayout.getBendPoints();
            this.forward = forward;
            this.startPoint = forward ? edgeLayout.getSourcePoint() : edgeLayout.getTargetPoint();
        }

        /**
         * Initializes the iterator for an iteration.
         */
        void init() {
            if (forward) {
                pointsIter = bendPoints.listIterator();
            } else {
                pointsIter = bendPoints.listIterator(bendPoints.size());
            }
        }

        /**
         * @return the start point for this bends iterator
         */
        KPoint getStartPoint() {
            return startPoint;
        }

        /**
         * {@inheritDoc}
         */
        public boolean hasNext() {
            return forward ? pointsIter.hasNext() : pointsIter.hasPrevious();
        }

        /**
         * {@inheritDoc}
         */
        public KPoint next() {
            return forward ? pointsIter.next() : pointsIter.previous();
        }

        /**
         * Throws <code>UnsupportedOperationException</code>.
         */
        public void remove() {
            throw new UnsupportedOperationException(
                    "Removing elements is not supported by BendsIterator.");
        }

        /**
         * Determines whether this bends iterator is compatible with the given one. If they are not
         * compatible, no junctures need to be painted for the two corresponding edges.
         * 
         * @param other
         *            the other bends iterator
         * @return true if both bends iterators are compatible
         */
        public boolean isCompatible(final BendsIterator other) {
            return this.bendPoints != other.bendPoints && this.forward == other.forward;
        }
    }

    /** indicates whether labels are painted. */
    private static boolean paintLabels = true;

    /** the painted layout graph. */
    private KNode layoutGraph;
    /** mapping of each layout graph element to its computed bounds. */
    private Map<Object, PaintRectangle> boundsMap = new LinkedHashMap<Object, PaintRectangle>();
    /** mapping of nodes or ports to bend point iterators of edges on that node or port. */
    private Map<Object, List<BendsIterator>> bendsMap = new HashMap<Object, List<BendsIterator>>();
    /** background color. */
    private Color backgroundColor;
    /** border color for nodes. */
    private Color nodeBorderColor;
    /** fill color for nodes. */
    private Color nodeFillColor;
    /** font used for node labels. */
    private Font nodeFont;
    /** background color for labels. */
    private Color labelBackColor;
    
    /** color used for ports. */
    private Color portColor;
    /** font used for port labels. */
    private Font portFont;
    /** color used for edges. */
    private Color edgeColor;
    /** font used for edge labels. */
    private Font edgeFont;

    /**
     * Creates a layout graph canvas.
     * 
     * @param parent
     *            the parent widget
     */
    public LayoutGraphCanvas(final Composite parent) {
        super(parent, SWT.NONE);
        addPaintListener(this);
        Display display = parent.getDisplay();
        backgroundColor = new Color(display, 255, 255, 255);
        setBackground(backgroundColor);
        nodeBorderColor = new Color(display, 10, 57, 14);
        nodeFillColor = new Color(display, 87, 197, 133);
        nodeFont = new Font(display, "sans", NODE_FONT_SIZE, SWT.NORMAL);
        labelBackColor = new Color(display, 243, 255, 199);
        portColor = new Color(display, 4, 17, 69);
        portFont = new Font(display, "sans", PORT_FONT_SIZE, SWT.NORMAL);
        edgeColor = new Color(display, 49, 77, 114);
        edgeFont = new Font(display, "sans", EDGE_FONT_SIZE, SWT.NORMAL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        backgroundColor.dispose();
        nodeBorderColor.dispose();
        nodeFillColor.dispose();
        nodeFont.dispose();
        labelBackColor.dispose();
        portColor.dispose();
        portFont.dispose();
        edgeColor.dispose();
        edgeFont.dispose();
        super.dispose();
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
        boundsMap.clear();
        bendsMap.clear();
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
     * This method is called when the canvas is requested to paint.
     * 
     * @param event
     *            paint event
     */
    public void paintControl(final PaintEvent event) {
        paintControl(event, 0);
    }

    /**
     * This method is called when the canvas is requested to paint.
     * 
     * @param event
     *            paint event
     * @param thecolor
     *            color index
     */
    public void paintControl(final PaintEvent event, final int thecolor) {
        if (layoutGraph != null) {
            // reset paint information
            for (PaintRectangle rectangle : boundsMap.values()) {
                rectangle.painted = false;
            }

            // paint the top layout nodes with their children
            KVector offset = new KVector();
            PaintRectangle area = new PaintRectangle(event.x - 5, event.y - 5,
                    event.width + 10, event.height + 10, offset);
            paintLayoutNode(layoutGraph, event.gc, area, offset);
        }
    }

    /**
     * Paints the contained layout graph onto the given graphics object.
     * 
     * @param graphics
     *            the graphics context used to paint
     * @param size
     *            of the paintable area
     */
    public void paintLayoutGraph(final GC graphics, final Point size) {
        if (layoutGraph != null) {
            // reset paint information
            for (PaintRectangle rectangle : boundsMap.values()) {
                rectangle.painted = false;
            }

            // paint the top layout node with its children
            KVector offset = new KVector();
            PaintRectangle area = new PaintRectangle(0, 0, size.x, size.y, offset);
            paintLayoutNode(layoutGraph, graphics, area, offset);
        }
    }

    /**
     * Paints a layout node for the given dirty area.
     * 
     * @param layoutNode
     *            layout node to paint
     * @param graphics
     *            the graphics context used to paint
     * @param area
     *            dirty area that needs painting
     * @param offset
     *            offset to be added to relative coordinates
     */
    private void paintLayoutNode(final KNode layoutNode, final GC graphics,
            final PaintRectangle area, final KVector offset) {
        // paint ports of the layout node
        graphics.setFont(portFont);
        graphics.setAlpha(PORT_ALPHA);
        for (KPort port : layoutNode.getPorts()) {
            graphics.setForeground(portColor);
            graphics.setBackground(portColor);
            PaintRectangle rect = boundsMap.get(port);
            if (rect == null) {
                rect = new PaintRectangle(port.getData(KShapeLayout.class), offset);
                boundsMap.put(port, rect);
            }
            if (!rect.painted && rect.intersects(area)) {
                graphics.fillRectangle(rect.x, rect.y, rect.width, rect.height);
                graphics.drawRectangle(rect.x, rect.y, rect.width, rect.height);
                rect.painted = true;
            }
            if (paintLabels && port.getLabel() != null) {
                KVector portOffset = new KVector(rect.x, rect.y);
                rect = boundsMap.get(port.getLabel());
                if (rect == null) {
                    rect = new PaintRectangle(port.getLabel().getData(KShapeLayout.class),
                            portOffset);
                    boundsMap.put(port.getLabel(), rect);
                }
                if (!rect.painted && rect.intersects(area)) {
                    graphics.setBackground(labelBackColor);
                    graphics.fillRectangle(rect.x, rect.y, rect.width, rect.height);
                    String text = port.getLabel().getText();
                    if (text != null) {
                        graphics.drawString(text, rect.x, rect.y, true);
                    }
                    rect.painted = true;
                }
            }
        }

        // add insets to offset value
        KInsets insets = layoutNode.getData(KShapeLayout.class).getInsets();
        KVector subOffset = new KVector(offset);
        if (insets != null) {
            subOffset.translate(insets.getLeft(), insets.getTop());
        }

        // paint sub layout nodes
        for (KNode child : layoutNode.getChildren()) {
            graphics.setForeground(nodeBorderColor);
            graphics.setBackground(nodeFillColor);
            graphics.setAlpha(NODE_ALPHA);
            PaintRectangle rect = boundsMap.get(child);
            if (rect == null) {
                rect = new PaintRectangle(child.getData(KShapeLayout.class), subOffset);
                boundsMap.put(child, rect);
            }
            KVector childOffset = new KVector(rect.x, rect.y);
            if (!rect.painted && rect.intersects(area)) {
                graphics.fillRectangle(rect.x, rect.y, rect.width, rect.height);
                graphics.drawRectangle(rect.x, rect.y, rect.width, rect.height);
                rect.painted = true;
                paintLayoutNode(child, graphics, area, childOffset);
            }
            if (paintLabels && child.getLabel() != null) {
                graphics.setFont(nodeFont);
                rect = boundsMap.get(child.getLabel());
                KShapeLayout labelLayout = child.getLabel().getData(KShapeLayout.class);
                if (rect == null && labelLayout != null) {
                    rect = new PaintRectangle(labelLayout, childOffset);
                    boundsMap.put(child.getLabel(), rect);
                } else if (rect == null && labelLayout == null) {
                    rect = new PaintRectangle(child.getData(KEdgeLayout.class), subOffset);
                }
                if (!rect.painted && rect.intersects(area)) {
                    graphics.setBackground(labelBackColor);
                    graphics.fillRectangle(rect.x, rect.y, rect.width, rect.height);
                    String text = child.getLabel().getText();
                    if (text != null) {
                        graphics.drawString(text, rect.x, rect.y, true);
                    }
                    rect.painted = true;
                }
            }

            // paint edges, deactivate label painting for incoming edges
            graphics.setForeground(edgeColor);
            graphics.setBackground(edgeColor);
            graphics.setAlpha(EDGE_ALPHA);
            graphics.setFont(edgeFont);
            boolean oldPaintLabels = paintLabels;
            paintLabels = false;
            for (KEdge edge : child.getIncomingEdges()) {
                if (edge.getSource().getParent() != child) {
                    paintEdge(edge, graphics, area, subOffset);
                }
            }
            paintLabels = oldPaintLabels;
            for (KEdge edge : child.getOutgoingEdges()) {
                if (edge.getTarget().getParent() != child) {
                    paintEdge(edge, graphics, area, subOffset);
                }
            }
        }
    }

    /**
     * Paints an edge for the given dirty area.
     * 
     * @param edge
     *            edge to paint
     * @param graphics
     *            the graphics context used to paint
     * @param area
     *            dirty area that needs painting
     * @param offset
     *            offset to be added to relative coordinates
     */
    private void paintEdge(final KEdge edge, final GC graphics, final PaintRectangle area,
            final KVector offset) {
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        boolean hasPorts = edge.getSourcePort() != null && edge.getTargetPort() != null;
        BendsIterator sourceIter = null, targetIter = null;
        List<BendsIterator> sourceBends = null, targetBends = null;
        if (hasPorts) {
            sourceIter = new BendsIterator(edgeLayout, true);
            targetIter = new BendsIterator(edgeLayout, false);
            sourceBends = edge.getSourcePort() == null ? bendsMap.get(edge.getSource()) : bendsMap
                    .get(edge.getSourcePort());
            targetBends = edge.getTargetPort() == null ? bendsMap.get(edge.getTarget()) : bendsMap
                    .get(edge.getTargetPort());
        }
        PaintRectangle rect = boundsMap.get(edge);
        if (rect == null) {
            rect = new PaintRectangle(edgeLayout, offset);
            boundsMap.put(edge, rect);
            if (hasPorts) {
                if (sourceBends == null) {
                    sourceBends = new LinkedList<BendsIterator>();
                    if (edge.getSourcePort() == null) {
                        bendsMap.put(edge.getSource(), sourceBends);
                    } else {
                        bendsMap.put(edge.getSourcePort(), sourceBends);
                    }
                }
                sourceBends.add(sourceIter);
                if (targetBends == null) {
                    targetBends = new LinkedList<BendsIterator>();
                    if (edge.getTargetPort() == null) {
                        bendsMap.put(edge.getTarget(), targetBends);
                    } else {
                        bendsMap.put(edge.getTargetPort(), targetBends);
                    }
                }
                targetBends.add(targetIter);
            }
        }
        if (!rect.painted && rect.intersects(area)) {
            KPoint sourcePoint = edgeLayout.getSourcePoint();
            KPoint targetPoint = edgeLayout.getTargetPoint();
            List<KPoint> bendPoints = edgeLayout.getBendPoints();
            KPoint lastPoint = sourcePoint;
            for (KPoint point : bendPoints) {
                graphics.drawLine((int) Math.round(lastPoint.getX() + offset.x),
                        (int) Math.round(lastPoint.getY() + offset.y),
                        (int) Math.round(point.getX() + offset.x),
                        (int) Math.round(point.getY() + offset.y));
                lastPoint = point;
            }
            graphics.drawLine((int) Math.round(lastPoint.getX() + offset.x),
                    (int) Math.round(lastPoint.getY() + offset.y),
                    (int) Math.round(targetPoint.getX() + offset.x),
                    (int) Math.round(targetPoint.getY() + offset.y));
            // draw an arrow at the last segment of the connection
            int[] arrowPoly = makeArrow(lastPoint, targetPoint, offset);
            if (arrowPoly != null) {
                graphics.fillPolygon(arrowPoly);
            }
            rect.painted = true;
            if (hasPorts) {
                // paint junctures of the given edge
                paintJunctures(sourceBends, sourceIter, graphics, offset);
                paintJunctures(targetBends, targetIter, graphics, offset);
            }
        }
        if (paintLabels) {
            for (KLabel edgeLabel : edge.getLabels()) {
                rect = boundsMap.get(edgeLabel);
                if (rect == null) {
                    rect = new PaintRectangle(edgeLabel.getData(KShapeLayout.class), offset);
                    boundsMap.put(edgeLabel, rect);
                }
                if (!rect.painted && rect.intersects(area)) {
                    String text = edgeLabel.getText();
                    graphics.setBackground(labelBackColor);
                    graphics.fillRectangle(rect.x, rect.y, rect.width, rect.height);
                    if (text != null) {
                        graphics.drawString(text, rect.x, rect.y, true);
                    }
                    rect.painted = true;
                }
            }
        }
    }

    /**
     * Constructs a polygon that forms an arrow.
     * 
     * @param point1
     *            source point
     * @param point2
     *            target point
     * @param offset
     *            offset value to be added to coordinates
     * @return array of coordinates for the arrow polygon, or null if the given source and target
     *         points are equal
     */
    private int[] makeArrow(final KPoint point1, final KPoint point2, final KVector offset) {
        if (!(point1.getX() == point2.getX() && point1.getY() == point2.getY())) {
            int[] arrow = new int[6];
            arrow[0] = (int) Math.round(point2.getX() + offset.x);
            arrow[1] = (int) Math.round(point2.getY() + offset.y);

            float vectX = point1.getX() - point2.getX();
            float vectY = point1.getY() - point2.getY();
            float length = (float) Math.sqrt(vectX * vectX + vectY * vectY);
            float normX = vectX / length;
            float normY = vectY / length;
            float neckX = point2.getX() + ARROW_LENGTH * normX;
            float neckY = point2.getY() + ARROW_LENGTH * normY;
            float orthX = normY * ARROW_WIDTH / 2;
            float orthY = -normX * ARROW_WIDTH / 2;

            arrow[2] = (int) Math.round(neckX + orthX + offset.x);
            arrow[3] = (int) Math.round(neckY + orthY + offset.y);
            arrow[4] = (int) Math.round(neckX - orthX + offset.x);
            arrow[5] = (int) Math.round(neckY - orthY + offset.y);
            return arrow;
        } else {
            return null;
        }
    }

    /**
     * Paints the junctures for a given iterator of bend points of an edge.
     * 
     * @param bends
     *            bend points of other edges with the same incidence
     * @param origIter
     *            iterator for bend points of the original edge
     * @param graphics
     *            the graphics context used to paint
     * @param offset
     *            offset to be added to relative coordinates
     */
    private void paintJunctures(final List<BendsIterator> bends, final BendsIterator origIter,
            final GC graphics, final KVector offset) {
        for (BendsIterator compIter : bends) {
            if (origIter.isCompatible(compIter)) {
                origIter.init();
                compIter.init();
                if (origIter.hasNext() && !compIter.hasNext()) {
                    KPoint junctPoint = origIter.next();
                    graphics.drawRectangle((int) Math.round(junctPoint.getX() + offset.x) - 1,
                            (int) Math.round(junctPoint.getY() + offset.y) - 1, 2, 2);
                } else if (compIter.hasNext() && !origIter.hasNext()) {
                    KPoint junctPoint = compIter.next();
                    graphics.drawRectangle((int) Math.round(junctPoint.getX() + offset.x) - 1,
                            (int) Math.round(junctPoint.getY() + offset.y) - 1, 2, 2);
                } else {
                    KPoint lastPoint = origIter.getStartPoint();
                    while (origIter.hasNext() && compIter.hasNext()) {
                        KPoint origPoint = origIter.next();
                        KPoint compPoint = compIter.next();
                        if (origPoint.getX() != compPoint.getX()
                                || origPoint.getY() != compPoint.getY()) {
                            KPoint junctPoint = findMedian(lastPoint, origPoint, compPoint);
                            graphics.drawRectangle(
                                    (int) Math.round(junctPoint.getX() + offset.x) - 1,
                                    (int) Math.round(junctPoint.getY() + offset.y) - 1, 2, 2);
                            break;
                        }
                        lastPoint = origPoint;
                    }
                }
            }
        }
    }

    /**
     * Finds one point out of three points that lies between the other ones.
     * 
     * @param point1
     *            first point
     * @param point2
     *            second point
     * @param point3
     *            third point
     * @return the median point, or <code>point1</code> if the median is ambiguous
     */
    private static KPoint findMedian(final KPoint point1, final KPoint point2, final KPoint point3) {
        if ((point2.getX() >= point1.getX() && point2.getX() <= point3.getX() || point2.getX() >= point3
                .getX() && point2.getX() <= point1.getX())
                && (point2.getY() >= point1.getY() && point2.getY() <= point3.getY() || point2
                        .getY() >= point3.getY() && point2.getY() <= point1.getY())) {
            return point2;
        } else if ((point3.getX() >= point1.getX() && point3.getX() <= point2.getX() || point3
                .getX() >= point2.getX() && point3.getX() <= point1.getX())
                && (point3.getY() >= point1.getY() && point3.getY() <= point2.getY() || point3
                        .getY() >= point2.getY() && point3.getY() <= point1.getY())) {
            return point3;
        } else {
            return point1;
        }
    }

}
