/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui.util;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
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
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Utility class that is able to render a KGraph instance.
 *
 * @author msp
 */
public class KGraphRenderer {
    
    /** default alpha value for nodes. */
    private static final int NODE_ALPHA = 100;
    /** default font size for nodes. */
    private static final int NODE_FONT_SIZE = 9;
    /** default alpha value for ports. */
    private static final int PORT_ALPHA = 240;
    /** default font size for ports. */
    private static final int PORT_FONT_SIZE = 5;
    /** default alpha value for edges. */
    private static final int EDGE_ALPHA = 240;
    /** default font size for edges. */
    private static final int EDGE_FONT_SIZE = 8;
    /** default length of edge arrows. */
    private static final float ARROW_LENGTH = 7.0f;
    /** default width of edge arrows. */
    private static final float ARROW_WIDTH = 5.0f;
    
    /** mapping of each layout graph element to its computed bounds. */
    private Map<Object, PaintRectangle> boundsMap = new LinkedHashMap<Object, PaintRectangle>();
    /** border color for nodes. */
    private Color nodeBorderColor;
    /** fill color for nodes. */
    private Color nodeFillColor;
    /** font used for node labels. */
    private Font nodeFont;    
    /** color used for ports. */
    private Color portColor;
    /** font used for port labels. */
    private Font portFont;
    /** color used for edges. */
    private Color edgeColor;
    /** font used for edge labels. */
    private Font edgeFont;
    
    // CHECKSTYLEOFF MagicNumber
    
    /**
     * Creates a KGraph renderer.
     * 
     * @param display the display for which to create colors and fonts
     */
    public KGraphRenderer(final Display display) {
        nodeBorderColor = new Color(display, 10, 57, 14);
        nodeFillColor = new Color(display, 87, 197, 133);
        nodeFont = new Font(display, "sans", NODE_FONT_SIZE, SWT.NORMAL);
        portColor = new Color(display, 4, 17, 69);
        portFont = new Font(display, "sans", PORT_FONT_SIZE, SWT.NORMAL);
        edgeColor = new Color(display, 49, 77, 114);
        edgeFont = new Font(display, "sans", EDGE_FONT_SIZE, SWT.NORMAL);
    }

    /**
     * Dispose all created resources such as colors and fonts.
     */
    public void dispose() {
        clear();
        nodeBorderColor.dispose();
        nodeFillColor.dispose();
        nodeFont.dispose();
        portColor.dispose();
        portFont.dispose();
        edgeColor.dispose();
        edgeFont.dispose();
    }
    
    /**
     * Mark all objects in the given area as dirty.
     * 
     * @param area the area to mark as dirty, or {@code null} if all objects shall be marked
     */
    public void markDirty(final Rectangle area) {
        for (PaintRectangle rectangle : boundsMap.values()) {
            if (area == null || rectangle.intersects(area)) {
                rectangle.painted = false;
            }
        }
    }
    
    /**
     * Clear all internally cached data on painted graphs.
     */
    public void clear() {
        boundsMap.clear();
    }

    /**
     * Rectangle class used to mark painted objects.
     */
    private static class PaintRectangle {
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
        
        /**
         * Determines whether the given rectangle intersects with the receiver.
         * 
         * @param other
         *            the rectangle to check for intersection
         * @return true if the other rectangle intersects with this one
         */
        public boolean intersects(final Rectangle other) {
            return (other.x < this.x + this.width) && (other.y < this.y + this.height)
                    && (other.x + other.width > this.x) && (other.y + other.height > this.y);
        }
    }
    
    /**
     * Paints the contained layout graph onto the given graphics object.
     * 
     * @param parentNode the parent node that shall be rendered
     * @param graphics the graphics context used to paint
     * @param area the area to fill
     */
    public void render(final KNode parentNode, final GC graphics, final Rectangle area) {
        renderNode(parentNode, graphics, area, new KVector());
    }

    /**
     * Paints a node for the given dirty area.
     * 
     * @param node layout node to paint
     * @param graphics the graphics context used to paint
     * @param area dirty area that needs painting
     * @param offset offset to be added to relative coordinates
     */
    private void renderNode(final KNode node, final GC graphics, final Rectangle area,
            final KVector offset) {
        // paint ports of the given node
        graphics.setFont(portFont);
        graphics.setAlpha(PORT_ALPHA);
        for (KPort port : node.getPorts()) {
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
            // paint port labels
            KVector portOffset = new KVector(rect.x, rect.y);
            for (KLabel label : port.getLabels()) {
                rect = boundsMap.get(label);
                if (rect == null) {
                    rect = new PaintRectangle(label.getData(KShapeLayout.class), portOffset);
                    boundsMap.put(label, rect);
                }
                if (!rect.painted && rect.intersects(area)) {
                    String text = label.getText();
                    if (text != null) {
                        graphics.drawString(text, rect.x, rect.y, true);
                    }
                    rect.painted = true;
                }
            }
        }

        // add insets to offset value
        KInsets insets = node.getData(KShapeLayout.class).getInsets();
        KVector subOffset = new KVector(offset);
        if (insets != null) {
            subOffset.translate(insets.getLeft(), insets.getTop());
        }

        // paint sub layout nodes
        for (KNode child : node.getChildren()) {
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
                renderNode(child, graphics, area, childOffset);
            }
            // paint node labels
            graphics.setFont(nodeFont);
            for (KLabel label : child.getLabels()) {
                rect = boundsMap.get(label);
                KShapeLayout labelLayout = label.getData(KShapeLayout.class);
                if (rect == null && labelLayout != null) {
                    rect = new PaintRectangle(labelLayout, childOffset);
                    boundsMap.put(label, rect);
                } else if (rect == null && labelLayout == null) {
                    rect = new PaintRectangle(child.getData(KEdgeLayout.class), subOffset);
                }
                if (!rect.painted && rect.intersects(area)) {
                    String text = label.getText();
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
                    paintEdge(edge, graphics, area);
                }
            }
            paintLabels = oldPaintLabels;
            for (KEdge edge : child.getOutgoingEdges()) {
                if (edge.getTarget().getParent() != child) {
                    paintEdge(edge, graphics, area);
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
    private void paintEdge(final KEdge edge, final GC graphics, final PaintRectangle area) {
        // calculate an offset for edge coordinates
        KVector offset = new KVector();
        KNode parent = edge.getSource();
        if (!KimlUtil.isDescendant(edge.getTarget(), parent)) {
            parent = parent.getParent();
        }
        KimlUtil.toAbsolute(offset, parent);
        
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
