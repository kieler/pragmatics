/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
package de.cau.cs.kieler.kiml.viewer.views;

import java.util.ArrayList;
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
import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

/**
 * A canvas that is able to paint KIML layout graphs.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class LayoutGraphCanvas extends Canvas implements PaintListener {

    /** the background color */
    private static final Color BACKGROUND_COLOR = new Color(Display
            .getDefault(), 254, 254, 254);
    /** color used for node borders and labels */
    private static final Color NODE_BORDER_COLOR[] = {
        new Color(Display.getDefault(), 9, 22, 80),
        new Color(Display.getDefault(), 22, 80, 9),
        new Color(Display.getDefault(), 80, 22, 9),
        new Color(Display.getDefault(), 254, 0, 0),
    };
    /** color used for node fills */
    private static final Color NODE_FILL_COLOR[] = {
        new Color(Display.getDefault(), 208, 214, 244),
        new Color(Display.getDefault(), 214, 244, 208),
        new Color(Display.getDefault(), 214, 208, 244),
        new Color(Display.getDefault(), 255, 255, 255)
    };
    /** alpha value for nodes */
    private static final int NODE_ALPHA[] ={120,80,80,220};
    /** font size for nodes */
    private static final int NODE_FONT_SIZE = 10;
    /** color used for port borders and labels */
    private static final Color PORT_BORDER_COLOR[] = { 
        new Color(Display.getDefault(), 14, 54, 24),
        new Color(Display.getDefault(), 54, 14, 24),
        new Color(Display.getDefault(), 24, 14, 54),
        new Color(Display.getDefault(), 254, 0, 0)
    };
    /** color used for port fills */
    private static final Color PORT_FILL_COLOR[] = { 
        new Color(Display.getDefault(), 81, 136, 95),
        new Color(Display.getDefault(), 136, 81, 95),
        new Color(Display.getDefault(), 95, 81, 136),
        new Color(Display.getDefault(), 255, 255, 255)
    };
    /** alpha value for ports */
    private static final int PORT_ALPHA = 240;
    /** font size for ports */
    private static final int PORT_FONT_SIZE = 6;
    /** color used for edges and edge labels */
    private static final Color EDGE_COLOR[] = {
        new Color(Display.getDefault(), 39, 9, 16),
        new Color(Display.getDefault(), 16, 39, 9),
        new Color(Display.getDefault(), 9, 16, 39),
        new Color(Display.getDefault(), 254, 0, 0)
    };
      
    /** alpha value for edges */
    private static final int EDGE_ALPHA = 220;
    /** font size for edges */
    private static final int EDGE_FONT_SIZE = 8;

    /** length of edge arrows */
    private static final float ARROW_LENGTH = 7.0f;
    /** width of edge arrows */
    private static final float ARROW_WIDTH = 5.0f;

    /**
     * Rectangle class used to mark painted objects.
     */
    public class PaintRectangle {
        int x, y, width, height;
        boolean painted = false;

        /**
         * Creates a paint rectangle from the four defining values.
         * 
         * @param x
         *            x coordinate value of the upper left corner
         * @param y
         *            y coordinate value of the upper left corner
         * @param width
         *            width of the rectangle
         * @param height
         *            height of the rectangle
         * @param offset
         *            offset to be added to coordinate values
         */
        PaintRectangle(float x, float y, float width, float height,
                KPoint offset) {
            this.x = Math.round(x + offset.getX());
            this.y = Math.round(y + offset.getY());
            this.width = Math.round(width);
            this.height = Math.round(height);
        }

        /**
         * Creates a paint rectangle from a shape layout object.
         * 
         * @param shapeLayout
         *            shape layout from which values shall be taken
         * @param offset
         *            offset to be added to coordinate values
         */
        PaintRectangle(KShapeLayout shapeLayout, KPoint offset) {
            this.x = Math.round(shapeLayout.getXpos()
                    + offset.getX());
            this.y = Math.round(shapeLayout.getYpos()
                    + offset.getY());
            this.width = Math.max(Math.round(shapeLayout.getWidth()),
                    3);
            this.height = Math.max(Math
                    .round(shapeLayout.getHeight()), 3);
        }

        /**
         * Creates a paint rectangle from an edge layout object.
         * 
         * @param edgeLayout
         *            edge layout from which the values shall be determined
         * @param offset
         *            offset to be added to coordinate values
         */
        PaintRectangle(KEdgeLayout edgeLayout, KPoint offset) {
            float minX = edgeLayout.getSourcePoint().getX(), minY = edgeLayout
                    .getSourcePoint().getY();
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
            this.x = Math.round(minX + offset.getX());
            this.y = Math.round(minY + offset.getY());
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
        public boolean intersects(PaintRectangle other) {
            return (other.x < this.x + this.width)
                    && (other.y < this.y + this.height)
                    && (other.x + other.width > this.x)
                    && (other.y + other.height > this.y);
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
         *            if true, bend points are iterated with the
         *            <code>next()</code> method, else <code>previous()</code>
         *            is used
         */
        BendsIterator(KEdgeLayout edgeLayout, boolean forward) {
            this.bendPoints = edgeLayout.getBendPoints();
            this.forward = forward;
            this.startPoint = forward ? edgeLayout.getSourcePoint()
                    : edgeLayout.getTargetPoint();
        }

        /**
         * Initializes the iterator for an iteration.
         */
        void init() {
            if (forward)
                pointsIter = bendPoints.listIterator();
            else
                pointsIter = bendPoints.listIterator(bendPoints.size());
        }

        /**
         * @return the start point for this bends iterator
         */
        KPoint getStartPoint() {
            return startPoint;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.Iterator#hasNext()
         */
        public boolean hasNext() {
            return forward ? pointsIter.hasNext() : pointsIter.hasPrevious();
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.Iterator#next()
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
         * Determines whether this bends iterator is compatible with the given
         * one. If they are not compatible, no junctures need to be painted for
         * the two corresponding edges.
         * 
         * @param other
         *            the other bends iterator
         * @return true if both bends iterators are compatible
         */
        public boolean isCompatible(BendsIterator other) {
            return this.bendPoints != other.bendPoints
                    && this.forward == other.forward;
        }
    }

    /** indicates whether labels are painted */
    private static boolean paintLabels = true;

    /** the layout graph */
    private List<KNode> layoutGraph = null;
    /** mapping of each layout graph element to its computed bounds */
    private Map<Object, PaintRectangle> boundsMap = new LinkedHashMap<Object, PaintRectangle>();
    /**
     * mapping of nodes or ports to bend point iterators of edges on that node
     * or port
     */
    private Map<Object, List<BendsIterator>> bendsMap = new HashMap<Object, List<BendsIterator>>();
    /** font used for node labels */
    private Font nodeFont;
    /** font used for port labels */
    private Font portFont;
    /** font used for edge labels */
    private Font edgeFont;

    /**
     * Creates a layout graph canvas.
     * 
     * @param parent
     *            the parent widget
     */
    public LayoutGraphCanvas(Composite parent) {
        super(parent, SWT.NONE);
        addPaintListener(this);
        setBackground(BACKGROUND_COLOR);
        Display display = this.getDisplay();
        nodeFont = new Font(display, "sans", NODE_FONT_SIZE, SWT.NORMAL);
        portFont = new Font(display, "sans", PORT_FONT_SIZE, SWT.NORMAL);
        edgeFont = new Font(display, "sans", EDGE_FONT_SIZE, SWT.NORMAL);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.widgets.Widget#dispose()
     */
    public void dispose() {
        nodeFont.dispose();
        portFont.dispose();
        edgeFont.dispose();
        super.dispose();
    }

    /**
     * Sets the given layout graph as the painted graph. Assumes that there is
     * only one layout graph to be painted and not multiple ones overlaid over
     * each other.
     * 
     * @param layoutGraph
     *            layout graph to be painted
     */
    public void setLayoutGraph(KNode layoutGraph) {
        if (this.layoutGraph != null)
            this.layoutGraph.clear();
        this.addLayoutGraph(layoutGraph);
    }
    
    /**
     * Returns the currently painted layout graph.
     * 
     * @return the painted layout graph
     */
    public KNode getLayoutGraph() {
        if (layoutGraph != null)
            return layoutGraph.get(0);
        else return null;
    }

    /**
     * Adds a layout graph to be painted. Multiple graphs may be overlaid to get
     * a direct comparison.
     * 
     * @param layoutGraph2
     */
    public void addLayoutGraph(KNode layoutGraph) {
        // set new size values for the canvas
        if (layoutGraph != null) {
            KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(layoutGraph);
            setSize(new Point((int) shapeLayout.getWidth() + 1,
                    (int) shapeLayout.getHeight() + 1));
        }

        if (this.layoutGraph == null)
            this.layoutGraph = new ArrayList<KNode>();
        this.layoutGraph.add(layoutGraph);
        boundsMap.clear();
        bendsMap.clear();
        redraw();
    }

    /**
     * This method is called when the canvas is requested to paint.
     * 
     * @param event
     *            paint event
     */
    public void paintControl(PaintEvent event) {
        paintControl(event,0);
    }

    /**
     * This method is called when the canvas is requested to paint.
     * 
     * @param event
     *            paint event
     */
    public void paintControl(PaintEvent event, int color) {
        if (layoutGraph != null && layoutGraph.size() > 0) {
            // reset paint information
            for (PaintRectangle rectangle : boundsMap.values()) {
                rectangle.painted = false;
            }

            // paint the top layout nodes with their children
            for (KNode graph : layoutGraph) {
                if (graph != null){
                    KPoint offset = KLayoutDataFactory.eINSTANCE.createKPoint();
                    PaintRectangle area = new PaintRectangle(event.x - 5,
                            event.y - 5, event.width + 10, event.height + 10,
                            offset);
                    paintLayoutNode(graph, event.gc, area, offset, color);
                    color++;
                }
            }

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
    public void paintLayoutGraph(GC graphics, Point size) {
        paintLayoutGraph(graphics, size, 0);
    }
    /**
     * Paints the contained layout graph onto the given graphics object.
     * 
     * @param graphics
     *            the graphics context used to paint
     * @param size
     *            of the paintable area
     */
    public void paintLayoutGraph(GC graphics, Point size, int color) {
        if (layoutGraph != null && layoutGraph.size() > 0) {
            // reset paint information
            for (PaintRectangle rectangle : boundsMap.values()) {
                rectangle.painted = false;
            }

            // paint the top layout node with its children
            for (KNode graph : layoutGraph) {
                if (graph != null){
                    KPoint offset = KLayoutDataFactory.eINSTANCE.createKPoint();
                    PaintRectangle area = new PaintRectangle(0, 0, size.x, size.y,
                            offset);
                    paintLayoutNode(graph, graphics, area, offset, color);
                    color++;
                }
            }
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
    private void paintLayoutNode(KNode layoutNode, GC graphics,
            PaintRectangle area, KPoint offset, int color) {
        // paint ports of the layout node
        graphics.setForeground(PORT_BORDER_COLOR[color%PORT_BORDER_COLOR.length]);
        graphics.setBackground(PORT_FILL_COLOR[color%PORT_FILL_COLOR.length]);
        graphics.setAlpha(PORT_ALPHA);
        graphics.setFont(portFont);
        for (KPort port : layoutNode.getPorts()) {
            PaintRectangle rect = boundsMap.get(port);
            if (rect == null) {
                rect = new PaintRectangle(KimlLayoutUtil
                        .getShapeLayout(port), offset);
                boundsMap.put(port, rect);
            }
            if (!rect.painted && rect.intersects(area)) {
                graphics.fillRectangle(rect.x, rect.y, rect.width, rect.height);
                graphics.drawRectangle(rect.x, rect.y, rect.width, rect.height);
                rect.painted = true;
            }
            if (paintLabels && port.getLabel() != null) {
                KPoint portOffset = KLayoutDataFactory.eINSTANCE
                        .createKPoint();
                portOffset.setX(rect.x);
                portOffset.setY(rect.y);
                rect = boundsMap.get(port.getLabel());
                if (rect == null) {
                    rect = new PaintRectangle(KimlLayoutUtil
                            .getShapeLayout(port.getLabel()), portOffset);
                    boundsMap.put(port.getLabel(), rect);
                }
                if (!rect.painted && rect.intersects(area)) {
                    graphics.drawString(port.getLabel().getText(), rect.x,
                            rect.y, true);
                    rect.painted = true;
                }
            }
        }

        // add insets to offset value
        KInsets insets = LayoutOptions.getInsets(KimlLayoutUtil
                .getShapeLayout(layoutNode));
        KPoint subOffset = KLayoutDataFactory.eINSTANCE.createKPoint();
        subOffset.setX(offset.getX() + insets.getLeft());
        subOffset.setY(offset.getY() + insets.getTop());

        // paint sub layout nodes
        for (KNode child : layoutNode.getChildren()) {
            graphics.setForeground(NODE_BORDER_COLOR[color%NODE_BORDER_COLOR.length]);
            graphics.setBackground(NODE_FILL_COLOR[color%NODE_FILL_COLOR.length]);
            graphics.setAlpha(NODE_ALPHA[color%NODE_ALPHA.length]);
            PaintRectangle rect = boundsMap.get(child);
            if (rect == null) {
                rect = new PaintRectangle(KimlLayoutUtil
                        .getShapeLayout(child), subOffset);
                boundsMap.put(child, rect);
            }
            KPoint childOffset = KLayoutDataFactory.eINSTANCE
                    .createKPoint();
            childOffset.setX(rect.x);
            childOffset.setY(rect.y);
            if (!rect.painted && rect.intersects(area)) {
                graphics.fillRectangle(rect.x, rect.y, rect.width, rect.height);
                graphics.drawRectangle(rect.x, rect.y, rect.width, rect.height);
                rect.painted = true;
                paintLayoutNode(child, graphics, area, childOffset, color);
            }
            if (paintLabels && child.getLabel() != null) {
                graphics.setFont(nodeFont);
                rect = boundsMap.get(child.getLabel());
                KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(
                        child.getLabel());
                if (rect == null && labelLayout != null) {
                    rect = new PaintRectangle(labelLayout, childOffset);
                    boundsMap.put(child.getLabel(), rect);
                } else if (rect == null && labelLayout == null) {
                    rect = new PaintRectangle(KimlLayoutUtil
                            .getEdgeLayout(child), subOffset);
                }
                if (!rect.painted && rect.intersects(area)) {
                    graphics.drawString(child.getLabel().getText(), rect.x,
                            rect.y, true);
                    rect.painted = true;
                }
            }

            // paint edges
            graphics.setForeground(EDGE_COLOR[color%EDGE_COLOR.length]);
            graphics.setBackground(EDGE_COLOR[color%EDGE_COLOR.length]);
            graphics.setAlpha(EDGE_ALPHA);
            graphics.setFont(edgeFont);
            for (KEdge edge : child.getIncomingEdges()) {
                if (edge.getSource().getParent() != child)
                    paintEdge(edge, graphics, area, subOffset);
            }
            for (KEdge edge : child.getOutgoingEdges()) {
                if (edge.getTarget().getParent() != child)
                    paintEdge(edge, graphics, area, subOffset);
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
    private void paintEdge(KEdge edge, GC graphics, PaintRectangle area,
            KPoint offset) {
        KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
        BendsIterator sourceIter = new BendsIterator(edgeLayout, true);
        BendsIterator targetIter = new BendsIterator(edgeLayout, false);
        List<BendsIterator> sourceBends = edge.getSourcePort() == null ? bendsMap
                .get(edge.getSource())
                : bendsMap.get(edge.getSourcePort());
        List<BendsIterator> targetBends = edge.getTargetPort() == null ? bendsMap
                .get(edge.getTarget())
                : bendsMap.get(edge.getTargetPort());
        PaintRectangle rect = boundsMap.get(edge);
        if (rect == null) {
            rect = new PaintRectangle(edgeLayout, offset);
            boundsMap.put(edge, rect);
            if (sourceBends == null) {
                sourceBends = new LinkedList<BendsIterator>();
                if (edge.getSourcePort() == null)
                    bendsMap.put(edge.getSource(), sourceBends);
                else
                    bendsMap.put(edge.getSourcePort(), sourceBends);
            }
            sourceBends.add(sourceIter);
            if (targetBends == null) {
                targetBends = new LinkedList<BendsIterator>();
                if (edge.getTargetPort() == null)
                    bendsMap.put(edge.getTarget(), targetBends);
                else
                    bendsMap.put(edge.getTargetPort(), targetBends);
            }
            targetBends.add(targetIter);
        }
        if (!rect.painted && rect.intersects(area)) {
            KPoint sourcePoint = edgeLayout.getSourcePoint();
            KPoint targetPoint = edgeLayout.getTargetPoint();
            List<KPoint> bendPoints = edgeLayout.getBendPoints();
            KPoint lastPoint = sourcePoint;
            for (KPoint point : bendPoints) {
                graphics.drawLine(Math.round(lastPoint.getX() + offset.getX()),
                        Math.round(lastPoint.getY() + offset.getY()), Math
                                .round(point.getX() + offset.getX()), Math
                                .round(point.getY() + offset.getY()));
                lastPoint = point;
            }
            graphics.drawLine(Math.round(lastPoint.getX() + offset.getX()),
                    Math.round(lastPoint.getY() + offset.getY()), Math
                            .round(targetPoint.getX() + offset.getX()), Math
                            .round(targetPoint.getY() + offset.getY()));
            // draw an arrow at the last segment of the connection
            int[] arrowPoly = makeArrow(lastPoint, targetPoint, offset);
            if (arrowPoly != null)
                graphics.fillPolygon(arrowPoly);
            rect.painted = true;
            // paint junctures of the given edge
            paintJunctures(sourceBends, sourceIter, graphics, offset);
            paintJunctures(targetBends, targetIter, graphics, offset);
        }
        if (paintLabels) {
            for (KLabel edgeLabel : edge.getLabels()) {
                rect = boundsMap.get(edgeLabel);
                if (rect == null) {
                    rect = new PaintRectangle(KimlLayoutUtil
                            .getShapeLayout(edgeLabel), offset);
                    boundsMap.put(edgeLabel, rect);
                }
                if (!rect.painted && rect.intersects(area)) {
                    graphics.drawString(edgeLabel.getText(), rect.x, rect.y,
                            true);
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
     * @return array of coordinates for the arrow polygon, or null if the given
     *         source and target points are equal
     */
    private int[] makeArrow(KPoint point1, KPoint point2, KPoint offset) {
        if (!(point1.getX() == point2.getX() && point1.getY() == point2.getY())) {
            int[] arrow = new int[6];
            arrow[0] = Math.round(point2.getX() + offset.getX());
            arrow[1] = Math.round(point2.getY() + offset.getY());

            float vectX = point1.getX() - point2.getX();
            float vectY = point1.getY() - point2.getY();
            float length = (float) Math.sqrt(vectX * vectX + vectY * vectY);
            float normX = vectX / length;
            float normY = vectY / length;
            float neckX = point2.getX() + ARROW_LENGTH * normX;
            float neckY = point2.getY() + ARROW_LENGTH * normY;
            float orthX = normY * ARROW_WIDTH / 2;
            float orthY = -normX * ARROW_WIDTH / 2;

            arrow[2] = Math.round(neckX + orthX + offset.getX());
            arrow[3] = Math.round(neckY + orthY + offset.getY());
            arrow[4] = Math.round(neckX - orthX + offset.getX());
            arrow[5] = Math.round(neckY - orthY + offset.getY());
            return arrow;
        } else
            return null;
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
    private void paintJunctures(List<BendsIterator> bends,
            BendsIterator origIter, GC graphics, KPoint offset) {
        for (BendsIterator compIter : bends) {
            if (origIter.isCompatible(compIter)) {
                origIter.init();
                compIter.init();
                if (origIter.hasNext() && !compIter.hasNext()) {
                    KPoint junctPoint = origIter.next();
                    graphics.drawRectangle(Math.round(junctPoint.getX()
                            + offset.getX()) - 1, Math.round(junctPoint.getY()
                            + offset.getY()) - 1, 2, 2);
                } else if (compIter.hasNext() && !origIter.hasNext()) {
                    KPoint junctPoint = compIter.next();
                    graphics.drawRectangle(Math.round(junctPoint.getX()
                            + offset.getX()) - 1, Math.round(junctPoint.getY()
                            + offset.getY()) - 1, 2, 2);
                } else {
                    KPoint lastPoint = origIter.getStartPoint();
                    while (origIter.hasNext() && compIter.hasNext()) {
                        KPoint origPoint = origIter.next();
                        KPoint compPoint = compIter.next();
                        if (origPoint.getX() != compPoint.getX()
                                || origPoint.getY() != compPoint.getY()) {
                            KPoint junctPoint = findMedian(lastPoint,
                                    origPoint, compPoint);
                            graphics.drawRectangle(Math.round(junctPoint.getX()
                                    + offset.getX()) - 1, Math.round(junctPoint
                                    .getY()
                                    + offset.getY()) - 1, 2, 2);
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
     * @return the median point, or <code>point1</code> if the median is
     *         ambiguous
     */
    private static KPoint findMedian(KPoint point1, KPoint point2, KPoint point3) {
        if ((point2.getX() >= point1.getX() && point2.getX() <= point3.getX() || point2
                .getX() >= point3.getX()
                && point2.getX() <= point1.getX())
                && (point2.getY() >= point1.getY()
                        && point2.getY() <= point3.getY() || point2.getY() >= point3
                        .getY()
                        && point2.getY() <= point1.getY()))
            return point2;
        else if ((point3.getX() >= point1.getX()
                && point3.getX() <= point2.getX() || point3.getX() >= point2
                .getX()
                && point3.getX() <= point1.getX())
                && (point3.getY() >= point1.getY()
                        && point3.getY() <= point2.getY() || point3.getY() >= point2
                        .getY()
                        && point3.getY() <= point1.getY()))
            return point3;
        else
            return point1;
    }

}
