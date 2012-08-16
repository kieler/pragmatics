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
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;
import java.util.StringTokenizer;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.krendering.HorizontalAlignment;
import de.cau.cs.kieler.core.krendering.KBackgroundColor;
import de.cau.cs.kieler.core.krendering.KBackgroundVisibility;
import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KFontBold;
import de.cau.cs.kieler.core.krendering.KFontItalic;
import de.cau.cs.kieler.core.krendering.KFontName;
import de.cau.cs.kieler.core.krendering.KFontSize;
import de.cau.cs.kieler.core.krendering.KHorizontalAlignment;
import de.cau.cs.kieler.core.krendering.KLineStyle;
import de.cau.cs.kieler.core.krendering.KLineWidth;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.core.krendering.KVerticalAlignment;
import de.cau.cs.kieler.core.krendering.KVisibility;
import de.cau.cs.kieler.core.krendering.LineStyle;
import de.cau.cs.kieler.core.krendering.VerticalAlignment;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
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

    /** property for output scaling, to be put in parent node's shape layout. */
    public static final IProperty<Float> SCALE = new Property<Float>(
            "de.cau.cs.kieler.awt.scale", 1.0f);

    /** default font size for nodes. */
    private static final int NODE_FONT_SIZE = 9;
    /** default font size for ports. */
    private static final int PORT_FONT_SIZE = 6;
    /** default font size for edges. */
    private static final int EDGE_FONT_SIZE = 8;
    /** default length of edge arrows. */
    private static final double ARROW_LENGTH = 8.0;
    /** default width of edge arrows. */
    private static final double ARROW_WIDTH = 7.0;
    /** the length of dashes used in line style. */
    private static final float DASH_LENGTH = 5.0f;
    /** the length of blanks used in line style. */
    private static final float BLANK_LENGTH = 3.0f;
    /** the default stroke. */
    private static final Stroke DEFAULT_STROKE = new BasicStroke(1);
    
    /** the graphics context used for drawing. */
    private Graphics2D graphics;
    /** the scale factor for all coordinates. */
    private float scale = 1.0f;
    
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
     * @return the size of the rendered graph
     */
    public KVector renderGraph(final KNode parentNode) {
        KShapeLayout parentLayout = parentNode.getData(KShapeLayout.class);
        // set the scale factor
        scale = parentLayout.getProperty(SCALE);
        if (scale <= 0) {
            scale = 1;
        }
        
        // render the nodes and ports
        Set<KEdge> edgeSet = new HashSet<KEdge>();
        renderNodeRecursive(parentNode, edgeSet);
        
        // render the edges
        for (KEdge edge : edgeSet) {
            renderEdge(parentNode, edge);
        }
        
        return new KVector(parentLayout.getWidth(), parentLayout.getHeight()).scale(scale);
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
            int width = Math.round(scale * nodeLayout.getWidth());
            int height = Math.round(scale * nodeLayout.getHeight());
            // paint the background with white
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            if (node.getParent() != null) {
                // paint a black border around the node
                graphics.setColor(Color.BLACK);
                graphics.setStroke(DEFAULT_STROKE);
                graphics.drawRect(0, 0, width, height);
            }
        } else {
            KVector size = new KVector(nodeLayout.getWidth(), nodeLayout.getHeight()).scale(scale);
            // paint the given rendering
            render(nodeRendering, size);
        }
        
        // render ports
        for (KPort port : node.getPorts()) {
            KShapeLayout portLayout = port.getData(KShapeLayout.class);
            // apply the offset of the port coordinates
            graphics.translate(scale * portLayout.getXpos(), scale * portLayout.getYpos());
            
            KRendering portRendering = port.getData(KRendering.class);
            if (portRendering == null) {
                int width = Math.round(scale * portLayout.getWidth());
                int height = Math.round(scale * portLayout.getHeight());
                // paint a dark gray box
                graphics.setColor(Color.DARK_GRAY);
                graphics.fillRect(0, 0, width, height);
            } else {
                KVector size = new KVector(portLayout.getWidth(), portLayout.getHeight()).scale(scale);
                // paint the given rendering
                render(portRendering, size);
            }
            
            // render port labels
            for (KLabel label : port.getLabels()) {
                renderLabel(label, PORT_FONT_SIZE);
            }
            
            // reset the offset
            graphics.translate(-scale * portLayout.getXpos(), -scale * portLayout.getYpos());
        }
        
        // render node labels
        for (KLabel label : node.getLabels()) {
            renderLabel(label, NODE_FONT_SIZE);
        }
        
        // render contained child nodes
        KInsets insets = nodeLayout.getInsets();
        graphics.translate(scale * insets.getLeft(), scale * insets.getTop());
        for (KNode child : node.getChildren()) {
            // apply the offset of the child coordinates
            KShapeLayout childLayout = child.getData(KShapeLayout.class);
            graphics.translate(scale * childLayout.getXpos(), scale * childLayout.getYpos());
            
            renderNodeRecursive(child, edgeSet);
            
            // reset the offset
            graphics.translate(-scale * childLayout.getXpos(), -scale * childLayout.getYpos());

            // store all incident edges to render them later
            edgeSet.addAll(child.getIncomingEdges());
            edgeSet.addAll(child.getOutgoingEdges());
        }
        graphics.translate(-scale * insets.getLeft(), -scale * insets.getTop());
    }
    
    /**
     * Render a label. The graphics must be transformed to the label's reference point, that is
     * the label's parent for shapes and the edge reference point for edges.
     * 
     * @param label the label to paint
     * @param fontSize the font size in points (unscaled)
     */
    private void renderLabel(final KLabel label, final int fontSize) {
        KShapeLayout labelLayout = label.getData(KShapeLayout.class);
        // apply the offset of the label coordinates
        graphics.translate(scale * labelLayout.getXpos(), scale * labelLayout.getYpos());
        
        KVector size = new KVector(labelLayout.getWidth(), labelLayout.getHeight()).scale(scale);
        KRendering labelRendering = label.getData(KRendering.class);
        if (labelRendering == null) {
            // paint the text string
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, Math.round(scale * fontSize)));
            renderText(label.getText(), size, HorizontalAlignment.LEFT, VerticalAlignment.CENTER);
        } else {
            // paint the given rendering
            render(labelRendering, size, label.getText());
        }
        
        // reset the offset
        graphics.translate(-scale * labelLayout.getXpos(), -scale * labelLayout.getYpos());
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
        offset.scale(scale);
        graphics.translate(offset.x, offset.y);

        // get the bend points of the edge
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        KVectorChain bendPoints = edgeLayout.createVectorChain();
        if (edgeLayout.getProperty(LayoutOptions.EDGE_ROUTING) == EdgeRouting.SPLINES) {
            bendPoints = KielerMath.approximateSpline(bendPoints);
        }
        bendPoints.scale(scale);

        KRendering edgeRendering = edge.getData(KRendering.class);
        if (edgeRendering == null) {
            // paint a polyline following the edge bend points
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
        } else {
            // paint the edge using the given rendering
            render(edgeRendering, bendPoints);
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
        if (!(point1.x == point2.x && point1.y == point2.y) && scale * ARROW_WIDTH >= 2) {
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
            double neckX = point2.x + scale * ARROW_LENGTH * normX;
            double neckY = point2.y + scale * ARROW_LENGTH * normY;
            double orthX = normY * scale * ARROW_WIDTH / 2;
            double orthY = -normX * scale * ARROW_WIDTH / 2;

            arrowx[1] = (int) Math.round(neckX + orthX);
            arrowy[1] = (int) Math.round(neckY + orthY);
            arrowx[2] = (int) Math.round(neckX - orthX);
            arrowy[2] = (int) Math.round(neckY - orthY);
            graphics.fillPolygon(arrowx, arrowy, 3);
            // CHECKSTYLEON MagicNumber
        }
    }
    
    private final LinkedList<KStyle> propagatedStyles = new LinkedList<KStyle>();
    
    public void render(final KRendering rendering, final KVector size) {
        render(rendering, size, null);
    }

    public void render(final KRendering rendering, final KVector size, final String text) {
        // apply the propagated and contained styles
        StyleData styleData = applyStyles(rendering);
        
        // 
        
        // remove the contained propagated styles
        for (KStyle style : rendering.getStyles()) {
            if (style.isPropagateToChildren()) {
                propagatedStyles.removeLastOccurrence(style);
            }
        }
    }
    
    public void render(final KRendering rendering, final KVectorChain points) {
        
    }
    
    /**
     * Render the given text respecting line breaks. The graphics must be transformed to the
     * position of the containing box.
     * 
     * @param text the text to render
     * @param containerSize the size of the box in which the text is contained
     * @param horzAlignment the horizontal alignment
     * @param vertAlignment the vertical alignment
     */
    private void renderText(final String text, final KVector containerSize,
            final HorizontalAlignment horzAlignment, final VerticalAlignment vertAlignment) {
        StringTokenizer textTokenizer = new StringTokenizer(text, "\n\r");
        int lineCount = textTokenizer.countTokens();
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int fontHeight = fontMetrics.getHeight();
        float ypos;
        switch (vertAlignment) {
        case TOP:
            ypos = 0;
            break;
        case BOTTOM:
            ypos = (float) containerSize.y - lineCount * fontHeight;
            break;
        default:
            ypos = (float) (containerSize.y - lineCount * fontHeight) / 2;
        }
        while (textTokenizer.hasMoreTokens()) {
            String line = textTokenizer.nextToken();
            ypos += fontHeight;
            float xpos;
            switch (horzAlignment) {
            case LEFT:
                xpos = 0;
                break;
            case RIGHT:
                xpos = (float) containerSize.x - fontMetrics.stringWidth(line);
                break;
            default:
                xpos = (float) (containerSize.x - fontMetrics.stringWidth(line)) / 2;
            }
            graphics.drawString(line, xpos, ypos);
        }
    }
    
    private StyleData applyStyles(final KRendering rendering) {
        // apply the propagated styles
        StyleData styleData = new StyleData();
        for (KStyle style : propagatedStyles) {
            handleStyle(style, styleData);
        }
        
        // apply the contained styles
        for (KStyle style : rendering.getStyles()) {
            handleStyle(style, styleData);
            if (style.isPropagateToChildren()) {
                propagatedStyles.addLast(style);
            }
        }
        
        // transfer styles to the graphics context
        graphics.setColor(styleData.color);
        graphics.setBackground(styleData.backgroundColor);
        graphics.setStroke(new BasicStroke(styleData.lineWidth, BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_MITER, 1.0f, styleData.lineStyle, 0.0f));
        graphics.setFont(new Font(styleData.fontName, styleData.fontStyle, styleData.fontSize));
        return styleData;
    }
    
    /** meta data class holding style attributes. */
    private static class StyleData {
        private Color color = Color.BLACK;
        private Color backgroundColor = Color.WHITE;
        private boolean backgroundVisible = false;
        private float lineWidth = 1.0f;
        private float[] lineStyle = null;
        private HorizontalAlignment horzAlignment = HorizontalAlignment.LEFT;
        private VerticalAlignment vertAlignment = VerticalAlignment.CENTER;
        private boolean visible = true;
        private int fontStyle = Font.PLAIN;
        private String fontName = Font.SANS_SERIF;
        private int fontSize = NODE_FONT_SIZE;
    }
    
    private void handleStyle(final KStyle style, final StyleData styleData) {
        switch (style.eClass().getClassifierID()) {
        case KRenderingPackage.KCOLOR:
            KColor color = (KColor) style;
            styleData.color = new Color(color.getRed(), color.getGreen(), color.getBlue());
            break;
        case KRenderingPackage.KBACKGROUND_COLOR:
            KBackgroundColor backgroundColor = (KBackgroundColor) style;
            styleData.backgroundColor = new Color(backgroundColor.getRed(), backgroundColor.getGreen(),
                    backgroundColor.getBlue());
            break;
        case KRenderingPackage.KLINE_WIDTH:
            styleData.lineWidth = ((KLineWidth) style).getLineWidth();
            break;
        case KRenderingPackage.KLINE_STYLE:
            LineStyle lineStyle = ((KLineStyle) style).getLineStyle();
            switch (lineStyle) {
            case DASH:
                styleData.lineStyle = new float[] { DASH_LENGTH, BLANK_LENGTH };
                break;
            case DOT:
                styleData.lineStyle = new float[] { 1, BLANK_LENGTH };
                break;
            case DASHDOT:
                styleData.lineStyle = new float[] { DASH_LENGTH, BLANK_LENGTH, 1, BLANK_LENGTH };
                break;
            case DASHDOTDOT:
                styleData.lineStyle = new float[] { DASH_LENGTH, BLANK_LENGTH, 1, BLANK_LENGTH,
                        1, BLANK_LENGTH };
                break;
            default:
                styleData.lineStyle = null;
            }
            break;
        case KRenderingPackage.KHORIZONTAL_ALIGNMENT:
            styleData.horzAlignment = ((KHorizontalAlignment) style).getHorizontalAlignment();
            break;
        case KRenderingPackage.KVERTICAL_ALIGNMENT:
            styleData.vertAlignment = ((KVerticalAlignment) style).getVerticalAlignment();
            break;
        case KRenderingPackage.KVISIBILITY:
            styleData.visible = ((KVisibility) style).isVisible();
            break;
        case KRenderingPackage.KBACKGROUND_VISIBILITY:
            styleData.backgroundVisible = ((KBackgroundVisibility) style).isVisible();
            break;
        case KRenderingPackage.KFONT_BOLD:
            if (((KFontBold) style).isBold()) {
                styleData.fontStyle |= Font.BOLD;
            } else {
                styleData.fontStyle &= ~Font.BOLD;
            }
            break;
        case KRenderingPackage.KFONT_ITALIC:
            if (((KFontItalic) style).isItalic()) {
                styleData.fontStyle |= Font.ITALIC;
            } else {
                styleData.fontStyle &= ~Font.ITALIC;
            }
            break;
        case KRenderingPackage.KFONT_NAME:
            styleData.fontName = ((KFontName) style).getName();
            break;
        case KRenderingPackage.KFONT_SIZE:
            styleData.fontSize = ((KFontSize) style).getSize();
            break;
        }
    }
    
    private void handleRendering(final KRendering rendering, final StyleData styleData,
            final KVector size) {
    }

}
