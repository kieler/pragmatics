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

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Utility class that is able to render a KGraph instance.
 *
 * @author msp
 */
public class KGraphRenderer {
    
    /** default font size for nodes. */
    private static final int NODE_FONT_SIZE = 9;
    /** default font size for ports. */
    private static final int PORT_FONT_SIZE = 6;
    /** default font size for edges. */
    private static final int EDGE_FONT_SIZE = 8;
    /** default length of edge arrows. */
    private static final double ARROW_LENGTH = 8.0f;
    /** default width of edge arrows. */
    private static final double ARROW_WIDTH = 7.0f;
    
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
    
    /** the scale factor for all coordinates. */
    private double scale;
    /** the base offset for all coordinates. */
    private KVector baseOffset;
    
    // CHECKSTYLEOFF MagicNumber
    
    /**
     * Creates a KGraph renderer.
     * 
     * @param display the display for which to create colors and fonts
     */
    public KGraphRenderer(final Display display) {
        this(display, 1.0, new KVector());
    }
    
    /**
     * Creates a KGraph renderer with scaling.
     * 
     * @param display the display for which to create colors and fonts
     * @param thescale the scale factor for all coordinates
     * @param thebaseOffset the base offset for all coordinates
     */
    public KGraphRenderer(final Display display, final double thescale, final KVector thebaseOffset) {
        this.scale = thescale;

        nodeBorderColor = new Color(display, 2, 15, 3);
        nodeFillColor = new Color(display, 87, 197, 133);
        int nodeFontSize = Math.max((int) Math.round(NODE_FONT_SIZE * thescale), 2);
        nodeFont = new Font(display, "sans", nodeFontSize, SWT.NORMAL);
        portColor = new Color(display, 2, 9, 40);
        int portFontSize = Math.max((int) Math.round(PORT_FONT_SIZE * thescale), 2);
        portFont = new Font(display, "sans", portFontSize, SWT.NORMAL);
        edgeColor = new Color(display, 23, 36, 54);
        int edgeFontSize = Math.max((int) Math.round(EDGE_FONT_SIZE * thescale), 2);
        edgeFont = new Font(display, "sans", edgeFontSize, SWT.NORMAL);
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
         * Creates a paint rectangle from a shape layout object.
         * 
         * @param shapeLayout shape layout from which values shall be taken
         * @param offset offset to be added to coordinate values
         */
        PaintRectangle(final KShapeLayout shapeLayout, final KVector offset, final double scale) {
            this.x = (int) Math.round(shapeLayout.getXpos() * scale + offset.x);
            this.y = (int) Math.round(shapeLayout.getYpos() * scale + offset.y);
            this.width = Math.max((int) Math.round(shapeLayout.getWidth() * scale), 1);
            this.height = Math.max((int) Math.round(shapeLayout.getHeight() * scale), 1);
        }

        /**
         * Creates a paint rectangle from an edge layout object.
         * 
         * @param edgeLayout edge layout from which the values shall be determined
         * @param offset offset to be added to coordinate values
         * @param scale the scale to apply to all coordinates
         */
        PaintRectangle(final KEdgeLayout edgeLayout, final KVector offset, final double scale) {
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
            this.x = (int) Math.round(minX * scale + offset.x);
            this.y = (int) Math.round(minY * scale + offset.y);
            this.width = (int) Math.round((maxX - minX) * scale);
            this.height = (int) Math.round((maxY - minY) * scale);
        }
        
        /**
         * Determines whether the given rectangle intersects with the receiver.
         * 
         * @param other the rectangle to check for intersection
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
        // determine an overall alpha value for nodes, depending on the maximal node depth
        int maxDepth = Math.max(maxDepth(parentNode), 1);
        int nodeAlpha = 200 / maxDepth + 55;
        
        // render the nodes and ports
        Set<KEdge> edgeSet = new HashSet<KEdge>();
        renderNode(parentNode, graphics, area, new KVector(baseOffset), edgeSet, nodeAlpha);
        
        // render the edges
        graphics.setForeground(edgeColor);
        graphics.setBackground(edgeColor);
        graphics.setAlpha(255);
        graphics.setFont(edgeFont);
        for (KEdge edge : edgeSet) {
            renderEdge(edge, graphics, area);
        }
    }
    
    /**
     * Determine the maximal depth of the given graph.
     * 
     * @param parent the parent node of the graph
     * @return the maximal depth of contained nodes
     */
    private int maxDepth(final KNode parent) {
        int maxDepth = 0;
        for (KNode child : parent.getChildren()) {
            int depth = maxDepth(child) + 1;
            if (depth > maxDepth) {
                maxDepth = depth;
            }
        }
        return maxDepth;
    }

    /**
     * Paints a node for the given dirty area.
     * 
     * @param node the node to paint
     * @param graphics the graphics context used to paint
     * @param area dirty area that needs painting
     * @param offset offset to be added to relative coordinates
     * @param edgeSet set to be filled with edges that are found on the way
     * @param nodeAlpha alpha value for nodes
     */
    private void renderNode(final KNode node, final GC graphics, final Rectangle area,
            final KVector offset, final Set<KEdge> edgeSet, final int nodeAlpha) {
        for (KNode child : node.getChildren()) {
            PaintRectangle rect = boundsMap.get(child);
            if (rect == null) {
                rect = new PaintRectangle(child.getData(KShapeLayout.class), offset, scale);
                boundsMap.put(child, rect);
            }
            KVector childOffset = new KVector(rect.x, rect.y);
            
            // render the child node and its content
            graphics.setForeground(nodeBorderColor);
            graphics.setBackground(nodeFillColor);
            if (!rect.painted && rect.intersects(area)) {
                graphics.setAlpha(nodeAlpha);
                graphics.fillRectangle(rect.x, rect.y, rect.width, rect.height);
                graphics.drawRectangle(rect.x, rect.y, rect.width, rect.height);
                rect.painted = true;
                KVector contentOffset = new KVector(childOffset);
                KInsets insets = child.getData(KShapeLayout.class).getInsets();
                contentOffset.translate(insets.getLeft() * scale, insets.getTop() * scale);
                renderNode(child, graphics, area, contentOffset, edgeSet, nodeAlpha);
            }

            graphics.setAlpha(255);
            graphics.setFont(nodeFont);
            // render node labels
            for (KLabel label : child.getLabels()) {
                renderLabel(label, graphics, area, childOffset);
            }

            // render ports
            for (KPort port : child.getPorts()) {
                renderPort(port, graphics, area, childOffset);
            }

            // store all incident edges to render them later
            edgeSet.addAll(child.getIncomingEdges());
            edgeSet.addAll(child.getOutgoingEdges());
        }
    }
    
    /**
     * Paints a label for the given dirty area.
     * 
     * @param label the label to paint
     * @param graphics the graphics context used to paint
     * @param area dirty area that needs painting
     * @param offset offset to be added to relative coordinates
     */
    private void renderLabel(final KLabel label, final GC graphics, final Rectangle area,
            final KVector offset) {
        PaintRectangle rect = boundsMap.get(label);
        KShapeLayout labelLayout = label.getData(KShapeLayout.class);
        if (rect == null) {
            rect = new PaintRectangle(labelLayout, offset, scale);
            boundsMap.put(label, rect);
        }
        if (!rect.painted && rect.intersects(area)) {
            String text = label.getText();
            if (text != null && text.length() > 0) {
                graphics.drawString(text, rect.x, rect.y, true);
            }
            rect.painted = true;
        }
    }
    
    /**
     * Paints a port for the given dirty area.
     * 
     * @param port the port to paint
     * @param graphics the graphics context used to paint
     * @param area dirty area that needs painting
     * @param offset offset to be added to relative coordinates
     */
    private void renderPort(final KPort port, final GC graphics, final Rectangle area,
            final KVector offset) {
        graphics.setForeground(portColor);
        graphics.setBackground(portColor);
        graphics.setFont(portFont);
        graphics.setAlpha(255);
        PaintRectangle rect = boundsMap.get(port);
        if (rect == null) {
            rect = new PaintRectangle(port.getData(KShapeLayout.class), offset, scale);
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
            renderLabel(label, graphics, area, portOffset);
        }
    }

    /**
     * Paints an edge for the given dirty area.
     * 
     * @param edge the edge to paint
     * @param graphics the graphics context used to paint
     * @param area dirty area that needs painting
     */
    private void renderEdge(final KEdge edge, final GC graphics, final Rectangle area) {
        // calculate an offset for edge coordinates
        KVector offset = new KVector();
        KNode parent = edge.getSource();
        if (!KimlUtil.isDescendant(edge.getTarget(), parent)) {
            parent = parent.getParent();
        }
        KimlUtil.toAbsolute(offset, parent);
        
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        PaintRectangle rect = boundsMap.get(edge);
        if (rect == null) {
            rect = new PaintRectangle(edgeLayout, offset, scale);
            boundsMap.put(edge, rect);
        }
        if (!rect.painted && rect.intersects(area)) {
            KVectorChain bendPoints = edgeLayout.createVectorChain();
            if (edgeLayout.getProperty(LayoutOptions.EDGE_ROUTING) == EdgeRouting.SPLINES) {
                bendPoints = KielerMath.appoximateSpline(bendPoints);
            }
            bendPoints.scale(scale);
            KVector point1 = bendPoints.getFirst();
            for (KVector point2 : bendPoints) {
                graphics.drawLine((int) Math.round(point1.x + offset.x),
                        (int) Math.round(point1.y + offset.y),
                        (int) Math.round(point2.x + offset.x),
                        (int) Math.round(point2.y + offset.y));
                point1 = point2;
            }
            // draw an arrow at the last segment of the connection
            int[] arrowPoly = makeArrow(bendPoints.get(bendPoints.size() - 2), bendPoints.getLast(),
                    offset);
            if (arrowPoly != null) {
                graphics.fillPolygon(arrowPoly);
            }
            rect.painted = true;
        }
        
        // paint the edge labels
        for (KLabel label : edge.getLabels()) {
            renderLabel(label, graphics, area, offset);
        }
    }

    /**
     * Constructs a polygon that forms an arrow.
     * 
     * @param point1 source point
     * @param point2 target point
     * @param offset offset value to be added to coordinates
     * @return array of coordinates for the arrow polygon, or null if the given source and target
     *         points are equal
     */
    private int[] makeArrow(final KVector point1, final KVector point2, final KVector offset) {
        if (!(point1.x == point2.x && point1.y == point2.y) && ARROW_WIDTH * scale >= 2) {
            int[] arrow = new int[6];
            arrow[0] = (int) Math.round(point2.x + offset.x);
            arrow[1] = (int) Math.round(point2.y + offset.y);

            double vectX = point1.x - point2.x;
            double vectY = point1.y - point2.y;
            double length = Math.sqrt(vectX * vectX + vectY * vectY);
            double normX = vectX / length;
            double normY = vectY / length;
            double neckX = point2.x + ARROW_LENGTH * normX * scale;
            double neckY = point2.y + ARROW_LENGTH * normY * scale;
            double orthX = normY * ARROW_WIDTH / 2;
            double orthY = -normX * ARROW_WIDTH / 2;

            arrow[2] = (int) Math.round(neckX + orthX + offset.x);
            arrow[3] = (int) Math.round(neckY + orthY + offset.y);
            arrow[4] = (int) Math.round(neckX - orthX + offset.x);
            arrow[5] = (int) Math.round(neckY - orthY + offset.y);
            return arrow;
        } else {
            return null;
        }
    }

}
