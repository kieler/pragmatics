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
package de.cau.cs.kieler.kiml.formats.svg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;
import java.util.StringTokenizer;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Renderer for KGraph and KRendering models.
 *
 * @author msp
 */
public class KRenderer {

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
    
    /** the default font. */
    private static final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, NODE_FONT_SIZE);
    /** the default stroke. */
    private static final Stroke DEFAULT_STROKE = new BasicStroke(1);
    
    /** the graphics context used for drawing. */
    private Graphics2D graphics;
    
    /**
     * Create a KRenderer.
     * 
     * @param graphics the graphics context
     */
    public KRenderer(final Graphics2D graphics) {
        this.graphics = graphics;
    }
    
    /**
     * Render the whole content of a graph.
     * 
     * @param parentNode the parent node of the graph to render
     */
    public void renderGraph(final KNode parentNode) {
        // render the nodes and ports
        Set<KEdge> edgeSet = new HashSet<KEdge>();
        renderNodeRecursive(parentNode, edgeSet);
        // render the edges
        for (KEdge edge : edgeSet) {
            renderEdge(parentNode, edge);
        }
    }

    /**
     * Render a node and the subgraph contained in it. The graphics must already be transformed
     * to the node's coordinates (except for the top-level node).
     * 
     * @param node the node to paint
     * @param edgeSet set to be filled with edges that are found on the way
     */
    private void renderNodeRecursive(final KNode node, final Set<KEdge> edgeSet) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        KRendering nodeRendering = node.getData(KRendering.class);
        if (nodeRendering == null) {
            // paint the background with white
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, (int) nodeLayout.getWidth(), (int) nodeLayout.getHeight());
            if (node.getParent() != null) {
                // paint a black border around the node
                graphics.setColor(Color.BLACK);
                graphics.setStroke(DEFAULT_STROKE);
                graphics.drawRect(0, 0, (int) nodeLayout.getWidth(), (int) nodeLayout.getHeight());
            }
        } else {
            // paint the given rendering
            render(nodeLayout, nodeRendering);
        }
        
        // render ports
        for (KPort port : node.getPorts()) {
            KShapeLayout portLayout = port.getData(KShapeLayout.class);
            // apply the offset of the port coordinates
            graphics.translate(portLayout.getXpos(), portLayout.getYpos());
            
            KRendering portRendering = port.getData(KRendering.class);
            if (portRendering == null) {
                // paint a dark gray box
                graphics.setColor(Color.DARK_GRAY);
                graphics.fillRect(0, 0, (int) portLayout.getWidth(), (int) portLayout.getHeight());
            } else {
                // paint the given rendering
                render(portLayout, portRendering);
            }
            
            // render port labels
            for (KLabel label : port.getLabels()) {
                renderLabel(label, PORT_FONT_SIZE);
            }
            
            // reset the offset
            graphics.translate(-portLayout.getXpos(), -portLayout.getYpos());
        }
        
        // render node labels
        for (KLabel label : node.getLabels()) {
            renderLabel(label, NODE_FONT_SIZE);
        }
        
        // render contained child nodes
        KInsets insets = nodeLayout.getInsets();
        graphics.translate(insets.getLeft(), insets.getTop());
        for (KNode child : node.getChildren()) {
            // apply the offset of the child coordinates
            KShapeLayout childLayout = child.getData(KShapeLayout.class);
            graphics.translate(childLayout.getXpos(), childLayout.getYpos());
            
            renderNodeRecursive(child, edgeSet);
            
            // reset the offset
            graphics.translate(-childLayout.getXpos(), -childLayout.getYpos());

            // store all incident edges to render them later
            edgeSet.addAll(child.getIncomingEdges());
            edgeSet.addAll(child.getOutgoingEdges());
        }
        graphics.translate(-insets.getLeft(), -insets.getTop());
    }
    
    /**
     * Render a label. The graphics must be transformed to the label's reference point, that is
     * the label's parent for shapes and the edge reference point for edges.
     * 
     * @param label the label to paint
     */
    private void renderLabel(final KLabel label, final int fontSize) {
        KShapeLayout labelLayout = label.getData(KShapeLayout.class);
        // apply the offset of the label coordinates
        graphics.translate(labelLayout.getXpos(), labelLayout.getYpos());
        
        KRendering labelRendering = label.getData(KRendering.class);
        if (labelRendering == null) {
            // paint the text string
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, fontSize));
            renderText(label.getText(), labelLayout.getHeight());
        } else {
            // paint the given rendering
            render(labelLayout, labelRendering, label.getText());
        }
        
        // reset the offset
        graphics.translate(-labelLayout.getXpos(), -labelLayout.getYpos());
    }
    
    /**
     * Render the given text respecting line breaks. The graphics must be transformed to the
     * position of the containing box.
     * 
     * @param text the text to render
     * @param height the height of the box in which the text is contained
     */
    private void renderText(final String text, final float height) {
        StringTokenizer textTokenizer = new StringTokenizer(text, "\n\r");
        int lineCount = textTokenizer.countTokens();
        int fontHeight = graphics.getFontMetrics().getHeight();
        float pos = (height - lineCount * fontHeight) / 2;
        while (textTokenizer.hasMoreTokens()) {
            String line = textTokenizer.nextToken();
            pos += fontHeight;
            graphics.drawString(line, 0, pos);
        }
    }

    /**
     * Render an edge and its labels.
     * 
     * @param graph the top-level node of the graph
     * @param edge the edge to paint
     */
    private void renderEdge(final KNode graph, final KEdge edge) {
        if (!KimlUtil.isDescendant(edge.getSource(), graph)
                || !KimlUtil.isDescendant(edge.getTarget(), graph)) {
            // the edge points to some node outside of the rendered subgraph
            return;
        }
        
        // calculate an offset for edge coordinates
        KNode parent = edge.getSource();
        if (!KimlUtil.isDescendant(edge.getTarget(), parent)) {
            parent = parent.getParent();
        }
        KNode node = parent;
        KInsets graphInsets = graph.getData(KShapeLayout.class).getInsets();
        KVector offset = new KVector(graphInsets.getLeft(), graphInsets.getTop());
        while (node != graph && node != null) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            KInsets insets = nodeLayout.getInsets();
            offset.translate(nodeLayout.getXpos() + insets.getLeft(),
                    nodeLayout.getYpos() + insets.getTop());
            node = node.getParent();
        }
        graphics.translate(offset.x, offset.y);
        
        KRendering edgeRendering = edge.getData(KRendering.class);
        if (edgeRendering == null) {
            // paint a polyline following the edge bend points
            KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
            KVectorChain bendPoints = edgeLayout.createVectorChain();
            if (edgeLayout.getProperty(LayoutOptions.EDGE_ROUTING) == EdgeRouting.SPLINES) {
                bendPoints = KielerMath.approximateSpline(bendPoints);
            }
            int[] xpoints = new int[bendPoints.size()];
            int[] ypoints = new int[bendPoints.size()];
            ListIterator<KVector> pointIter = bendPoints.listIterator();
            while (pointIter.hasNext()) {
                KVector point = pointIter.next();
                xpoints[pointIter.previousIndex()] = (int) Math.round(point.x);
                ypoints[pointIter.previousIndex()] = (int) Math.round(point.y);
            }
            graphics.setColor(Color.BLACK);
            graphics.setStroke(DEFAULT_STROKE);
            graphics.drawPolyline(xpoints, ypoints, bendPoints.size());
            
            // draw an arrow at the last segment of the connection
            makeArrow(bendPoints.get(bendPoints.size() - 2), bendPoints.getLast());
        }
        
        // paint the edge labels
        for (KLabel label : edge.getLabels()) {
            renderLabel(label, EDGE_FONT_SIZE);
        }
        
        // reset the offset
        graphics.translate(-offset.x, -offset.y);
    }

    /**
     * Constructs a polygon that forms an arrow.
     * 
     * @param point1 source point
     * @param point2 target point
     */
    private void makeArrow(final KVector point1, final KVector point2) {
        if (!(point1.x == point2.x && point1.y == point2.y)) {
            // CHECKSTYLEOFF MagicNumber
            int[] arrowx = new int[3];
            int[] arrowy = new int[3];
            arrowx[0] = (int) Math.round(point2.x);
            arrowy[0] = (int) Math.round(point2.y);

            double vectX = point1.x - point2.x;
            double vectY = point1.y - point2.y;
            double length = Math.sqrt(vectX * vectX + vectY * vectY);
            double normX = vectX / length;
            double normY = vectY / length;
            double neckX = point2.x + ARROW_LENGTH * normX;
            double neckY = point2.y + ARROW_LENGTH * normY;
            double orthX = normY * ARROW_WIDTH / 2;
            double orthY = -normX * ARROW_WIDTH / 2;

            arrowx[1] = (int) Math.round(neckX + orthX);
            arrowy[1] = (int) Math.round(neckY + orthY);
            arrowx[2] = (int) Math.round(neckX - orthX);
            arrowy[2] = (int) Math.round(neckY - orthY);
            graphics.fillPolygon(arrowx, arrowy, 3);
            // CHECKSTYLEON MagicNumber
        }
    }
    
    public void render(final KShapeLayout shapeLayout, final KRendering rendering) {
        render(shapeLayout, rendering, null);
    }

    public void render(final KShapeLayout shapeLayout, final KRendering rendering, final String text) {
        
    }

}
