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
import java.awt.Image;
import java.awt.Stroke;
import java.awt.geom.Path2D;
import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import org.eclipse.core.runtime.Platform;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.krendering.HorizontalAlignment;
import de.cau.cs.kieler.core.krendering.KArc;
import de.cau.cs.kieler.core.krendering.KAreaPlacementData;
import de.cau.cs.kieler.core.krendering.KBackground;
import de.cau.cs.kieler.core.krendering.KBottomPosition;
import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KContainerRendering;
import de.cau.cs.kieler.core.krendering.KDecoratorPlacementData;
import de.cau.cs.kieler.core.krendering.KFontBold;
import de.cau.cs.kieler.core.krendering.KFontItalic;
import de.cau.cs.kieler.core.krendering.KFontName;
import de.cau.cs.kieler.core.krendering.KFontSize;
import de.cau.cs.kieler.core.krendering.KForeground;
import de.cau.cs.kieler.core.krendering.KGridPlacement;
import de.cau.cs.kieler.core.krendering.KGridPlacementData;
import de.cau.cs.kieler.core.krendering.KHorizontalAlignment;
import de.cau.cs.kieler.core.krendering.KImage;
import de.cau.cs.kieler.core.krendering.KInvisibility;
import de.cau.cs.kieler.core.krendering.KLeftPosition;
import de.cau.cs.kieler.core.krendering.KLineCap;
import de.cau.cs.kieler.core.krendering.KLineStyle;
import de.cau.cs.kieler.core.krendering.KLineWidth;
import de.cau.cs.kieler.core.krendering.KPlacementData;
import de.cau.cs.kieler.core.krendering.KPointPlacementData;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KRenderingRef;
import de.cau.cs.kieler.core.krendering.KRightPosition;
import de.cau.cs.kieler.core.krendering.KRoundedRectangle;
import de.cau.cs.kieler.core.krendering.KSpline;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.KTopPosition;
import de.cau.cs.kieler.core.krendering.KVerticalAlignment;
import de.cau.cs.kieler.core.krendering.KXPosition;
import de.cau.cs.kieler.core.krendering.KYPosition;
import de.cau.cs.kieler.core.krendering.LineCap;
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
import de.cau.cs.kieler.kiml.service.formats.TransformationData;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Renderer for KGraph and KRendering models on AWT graphics.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class KAwtRenderer {

    /** property for output scaling, to be put in parent node's shape layout. */
    public static final IProperty<Float> SCALE = new Property<Float>(
            "de.cau.cs.kieler.svg.scale", 1.0f);

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
    /** the default stroke. */
    private static final Stroke DEFAULT_STROKE = new BasicStroke(1);

    // the dash constants in the following definitions are copied
    // from the related definitions in the GC class (OSX Cocoa fragment):
    private static final float[] LINE_DOT = new float[] { 1, 1 };
    private static final float[] LINE_DASH = new float[] { 3, 1 };
    private static final float[] LINE_DASHDOT = new float[] { 3, 1, 1, 1 };
    private static final float[] LINE_DASHDOTDOT = new float[] { 3, 1, 1, 1, 1, 1 };

    /** the graphics context used for drawing. */
    private Graphics2D graphics;
    /** the scale factor for all coordinates. */
    private float scale = 1.0f;
    /** the transformation data object used for logging. */
    private TransformationData<?, ?> transData;
    
    /**
     * Create a KRenderer.
     * 
     * @param graphics the graphics context
     * @param transData transformation data used for logging, or {@code null} if no logging is required
     */
    public KAwtRenderer(final Graphics2D graphics, final TransformationData<?, ?> transData) {
        this.graphics = graphics;
        this.transData = transData;
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
     * Render something inside the given size. The graphics must be transformed to the
     * rendering's position.
     * 
     * @param rendering a rendering
     * @param size the size to apply for the rendering
     */
    public void render(final KRendering rendering, final KVector size) {
        handleDirectPlacement(rendering, size);
    }

    /**
     * Render something inside the given size. The graphics must be transformed to the
     * rendering's position. The text is applied to the first encountered {@link KText}.
     * 
     * @param rendering a rendering
     * @param size the size to apply for the rendering
     * @param text the text to display in the first encountered {@link KText}
     */
    public void render(final KRendering rendering, final KVector size, final String text) {
        propagatedText = text;
        handleDirectPlacement(rendering, size);
        propagatedText = null;
    }
    
    /**
     * Render something onto the given points. The graphics must be transformed to the
     * rendering's position.
     * 
     * @param rendering a rendering
     * @param points points to apply to the top-level {@link KPolyline}
     * @param isSpline whether the points are control points for a spline
     */
    public void render(final KRendering rendering, final KVectorChain points, final boolean isSpline) {
        doRender(rendering, null, points, isSpline);
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
        bendPoints.scale(scale);

        boolean isSpline = edgeLayout.getProperty(LayoutOptions.EDGE_ROUTING) == EdgeRouting.SPLINES;
        KRendering edgeRendering = edge.getData(KRendering.class);
        if (edgeRendering == null) {
            // paint a polyline following the edge bend points
            Path2D path = createPath(bendPoints, isSpline);
            graphics.setColor(Color.BLACK);
            graphics.setStroke(DEFAULT_STROKE);
            graphics.draw(path);
            
            // draw an arrow at the last segment of the connection
            makeArrow(bendPoints.get(bendPoints.size() - 2), bendPoints.getLast());
        } else {
            // paint the edge using the given rendering
            render(edgeRendering, bendPoints, isSpline);
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
    
    /** the propagated styles of parent renderings. */
    private final LinkedList<KStyle> propagatedStyles = new LinkedList<KStyle>();
    /** the stack of renderings that are drawn (used to detect cycles). */
    private final LinkedList<KRendering> renderingStack = new LinkedList<KRendering>();
    /** the propagated text of KLabel elements. */
    private String propagatedText;
    
    /**
     * Apply styles, render the shape, render the content respecting placement information.
     * 
     * @param rendering a rendering
     * @param size the size in which to render the shape, or {@code null} to use points
     * @param points the points onto which to render the polyline, or {@code null} to use size
     * @param isSpline true if the points are interpreted as spline control points in any case
     */
    private void doRender(final KRendering rendering, final KVector size, final KVectorChain points,
            final boolean isSpline) {
        if (renderingStack.contains(rendering)) {
            // the rendering references form a cycle, so ignore the last reference
            return;
        }
        renderingStack.push(rendering);
        
        // apply the propagated and contained styles
        StyleData styleData = applyStyles(rendering);
        
        if (rendering instanceof KRenderingRef) {
            // draw the referenced rendering instance
            doRender(((KRenderingRef) rendering).getRendering(), size, points, isSpline);
        } else {
            // draw the given rendering instance
            handleRendering(rendering, styleData, size, points, isSpline);
        }
        
        // draw the contained child renderings
        if (rendering instanceof KContainerRendering) {
            handleContent((KContainerRendering) rendering, size, points, isSpline);
        }
        
        // remove the contained propagated styles
        for (KStyle style : rendering.getStyles()) {
            if (style.isPropagateToChildren()) {
                propagatedStyles.removeLastOccurrence(style);
            }
        }
        renderingStack.pop();
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
    
    /**
     * Apply the styles contained in the given rendering. The propagated styles are applied first
     * and updated according to the contained propagated styles.
     * 
     * @param rendering a rendering
     * @return style data to be used for actually drawing the rendering
     */
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
        
        // transfer basic styles to the graphics context
        float[] scaledLineStyle = null;
        if (styleData.lineStyle != null) {
            scaledLineStyle = new float[styleData.lineStyle.length];
            for (int i = 0; i < styleData.lineStyle.length; i++) {
                scaledLineStyle[i] = scale * styleData.lineWidth * styleData.lineStyle[i];
            }
        }
        graphics.setStroke(new BasicStroke(scale * styleData.lineWidth, styleData.lineCap,
                BasicStroke.JOIN_MITER, scale, scaledLineStyle, 0.0f));
        graphics.setFont(new Font(styleData.fontName, styleData.fontStyle,
                Math.round(scale * styleData.fontSize)));
        return styleData;
    }
    
    /** meta data class holding style attributes. */
    private static class StyleData {
        private Color foregColor = Color.BLACK;
        private Color backgColor = Color.WHITE;
        private boolean foregVisible = true;
        private boolean backgVisible = false;
        private float lineWidth = 1.0f;
        private float[] lineStyle = null;
        private int lineCap = BasicStroke.CAP_BUTT;
        private HorizontalAlignment horzAlignment = HorizontalAlignment.LEFT;
        private VerticalAlignment vertAlignment = VerticalAlignment.CENTER;
        private boolean invisible = false;
        private int fontStyle = Font.PLAIN;
        private String fontName = Font.SANS_SERIF;
        private int fontSize = NODE_FONT_SIZE;
    }
    
    /**
     * Handle a single style attribute.
     * 
     * @param style a style
     * @param styleData the style data where gathered information is stored
     */
    private void handleStyle(final KStyle style, final StyleData styleData) {
        switch (style.eClass().getClassifierID()) {
        case KRenderingPackage.KFOREGROUND:
            KColor color = ((KForeground) style).getColor();
            styleData.foregColor = new Color(color.getRed(), color.getGreen(), color.getBlue());
            styleData.foregVisible = true;
            break;
        case KRenderingPackage.KBACKGROUND:
            KColor backgroundColor = ((KBackground) style).getColor();
            styleData.backgColor = new Color(backgroundColor.getRed(), backgroundColor.getGreen(),
                    backgroundColor.getBlue());
            styleData.backgVisible = true;
            break;
        case KRenderingPackage.KLINE_WIDTH:
            styleData.lineWidth = ((KLineWidth) style).getLineWidth();
            break;
        case KRenderingPackage.KLINE_STYLE:
            LineStyle lineStyle = ((KLineStyle) style).getLineStyle();            
            switch (lineStyle) {
            case DASH:
                styleData.lineStyle = LINE_DASH;
                break;
            case DOT:
                styleData.lineStyle = LINE_DOT;
                break;
            case DASHDOT:
                styleData.lineStyle = LINE_DASHDOT;
                break;
            case DASHDOTDOT:
                styleData.lineStyle = LINE_DASHDOTDOT;
                break;
            default:
                styleData.lineStyle = null;
            }
            break;
        case KRenderingPackage.KLINE_CAP:
            LineCap lineCap = ((KLineCap) style).getLineCap();
            switch (lineCap) {
            case CAP_FLAT:
                styleData.lineCap = BasicStroke.CAP_BUTT;
                break;
            case CAP_ROUND:
                styleData.lineCap = BasicStroke.CAP_ROUND;
                break;
            case CAP_SQUARE:
                styleData.lineCap = BasicStroke.CAP_SQUARE;
                break;
            }
            break;
        case KRenderingPackage.KHORIZONTAL_ALIGNMENT:
            styleData.horzAlignment = ((KHorizontalAlignment) style).getHorizontalAlignment();
            break;
        case KRenderingPackage.KVERTICAL_ALIGNMENT:
            styleData.vertAlignment = ((KVerticalAlignment) style).getVerticalAlignment();
            break;
        case KRenderingPackage.KINVISIBILITY:
            styleData.invisible = ((KInvisibility) style).isInvisible();
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
        default:
            if (transData != null) {
                transData.log("Style class not supported: " + style.eClass().getName());
            }
        }
    }
    
    /**
     * Draw the given rendering.
     * 
     * @param rendering a rendering
     * @param styleData style to apply for drawing
     * @param size the size in which the shape is drawn, or {@code null}
     * @param points the points onto which to draw lines, or {@code null}
     * @param isSpline whether the points are control points for a spline
     */
    private void handleRendering(final KRendering rendering, final StyleData styleData,
            final KVector size, final KVectorChain points, final boolean isSpline) {
        if (!styleData.invisible) {
            boolean unknownShape = false, unknownLine = false;
            if (size != null) {
                
                // check shapes that need size information to be drawn
                int width = (int) Math.round(size.x);
                int height = (int) Math.round(size.y);
                switch (rendering.eClass().getClassifierID()) {
                case KRenderingPackage.KRECTANGLE:
                    if (styleData.backgVisible) {
                        graphics.setColor(styleData.backgColor);
                        graphics.fillRect(0, 0, width, height);
                    }
                    if (styleData.foregVisible) {
                        graphics.setColor(styleData.foregColor);
                        graphics.drawRect(0, 0, width, height);
                    }
                    break;
                case KRenderingPackage.KELLIPSE:
                    if (styleData.backgVisible) {
                        graphics.setColor(styleData.backgColor);
                        graphics.fillOval(0, 0, width, height);
                    }
                    if (styleData.foregVisible) {
                        graphics.setColor(styleData.foregColor);
                        graphics.drawOval(0, 0, width, height);
                    }
                    break;
                case KRenderingPackage.KROUNDED_RECTANGLE:
                    KRoundedRectangle roundedRectangle = (KRoundedRectangle) rendering;
                    int arcWidth = Math.round(scale * roundedRectangle.getCornerWidth());
                    int arcHeight = Math.round(scale * roundedRectangle.getCornerHeight());
                    if (styleData.backgVisible) {
                        graphics.setColor(styleData.backgColor);
                        graphics.fillRoundRect(0, 0, width, height, arcWidth, arcHeight);
                    }
                    if (styleData.foregVisible) {
                        graphics.setColor(styleData.foregColor);
                        graphics.drawRoundRect(0, 0, width, height, arcWidth, arcHeight);
                    }
                    break;
                case KRenderingPackage.KIMAGE:
                    if (styleData.backgVisible) {
                        KImage kimage = (KImage) rendering;
                        Image image = createImage(kimage);
                        if (image != null) {
                            graphics.drawImage(image, 0, 0, width, height, null);
                        } else if (transData != null) {
                            transData.log("Could not create image for bundle " + kimage.getBundleName()
                                    + " and path " + kimage.getImagePath());
                        }
                    }
                    break;
                case KRenderingPackage.KARC:
                    if (styleData.foregVisible) {
                        KArc arc = (KArc) rendering;
                        int startAngle = Math.round(arc.getStartAngle());
                        int arcAngle = Math.round(arc.getArcAngle());
                        graphics.setColor(styleData.foregColor);
                        graphics.drawArc(0, 0, width, height, startAngle, arcAngle);
                    }
                    break;
                case KRenderingPackage.KTEXT:
                    if (styleData.foregVisible) {
                        String text = propagatedText;
                        if (text == null) {
                            // use the text contained in the rendering, if present
                            text = ((KText) rendering).getText();
                        } else {
                            // consume the propagated text so contained renderings don't use it anymore
                            propagatedText = null;
                        }
                        if (text != null) {
                            graphics.setColor(styleData.foregColor);
                            renderText(text, size, styleData.horzAlignment, styleData.vertAlignment);
                        }
                    }
                    break;
                default:
                    unknownShape = true;
                }
            }
                
            if (points != null) {
                // check shapes that need points information to be drawn
                switch (rendering.eClass().getClassifierID()) {
                case KRenderingPackage.KSPLINE:
                    if (styleData.foregVisible) {
                        graphics.setColor(styleData.foregColor);
                        graphics.draw(createPath(points, true));
                    }
                    break;
                case KRenderingPackage.KPOLYGON:
                    Path2D path = createPath(points, isSpline);
                    path.closePath();
                    if (styleData.foregVisible) {
                        graphics.setColor(styleData.foregColor);
                        graphics.draw(path);
                    }
                    if (styleData.backgVisible) {
                        graphics.setColor(styleData.backgColor);
                        graphics.fill(path);
                    }
                    break;
                case KRenderingPackage.KPOLYLINE:
                    if (styleData.foregVisible) {
                        graphics.setColor(styleData.foregColor);
                        graphics.draw(createPath(points, isSpline));
                    }
                    break;
                default:
                    unknownLine = true;
                }
            }
            
            if (unknownShape && unknownLine && transData != null) {
                transData.log("Rendering class not supported: " + rendering.eClass().getName());
            }
        }
    }
    
    /**
     * Create an AWT image for the given KImage.
     * 
     * @param kimage an image
     * @return an according AWT image
     */
    private Image createImage(final KImage kimage) {
        String bundleName = kimage.getBundleName();
        String imagePath = kimage.getImagePath();
        if (imagePath == null) {
            // TODO support direct image references
            return null;
        } else {
            try {
                if (bundleName == null) {
                    return ImageIO.read(new File(imagePath));
                } else {
                    URL url = Platform.getBundle(bundleName).getResource(imagePath);
                    return ImageIO.read(new File(url.toURI()));
                }
            } catch (Exception e) {
                return null;
            }
        }
    }
    
    /**
     * Create a vector chain for a polyline placement data.
     * 
     * @param polyline the polyline with points to added to the vector chain
     * @param parentSize the parent size
     * @return a vector chain with the points of the placement data
     */
    private KVectorChain createVectorChain(final KPolyline polyline,
            final KVector parentSize) {
        KVectorChain points = new KVectorChain();
        for (KPosition position : polyline.getPoints()) {
            KVector point = new KVector();
            KXPosition xpos = position.getX();
            if (xpos instanceof KLeftPosition) {
                point.x = xpos.getRelative() * parentSize.x + scale * xpos.getAbsolute();
            } else if (xpos instanceof KRightPosition) {
                point.x = parentSize.x
                        - (xpos.getRelative() * parentSize.x + scale * xpos.getAbsolute());
            }
            KYPosition ypos = position.getY();
            if (ypos instanceof KTopPosition) {
                point.y = ypos.getRelative() * parentSize.y + scale * ypos.getAbsolute();
            } else if (ypos instanceof KBottomPosition) {
                point.y = parentSize.y
                        - (ypos.getRelative() * parentSize.y + scale * ypos.getAbsolute());
            }
            points.add(point);
        }
        return points;
    }
    
    /**
     * Create an AWT path for the given vector chain.
     * 
     * @param points points of the path
     * @param curve whether points shall be interpreted as spline control points
     * @return an according AWT path
     */
    private Path2D createPath(final KVectorChain points, final boolean curve) {
        Path2D path = new Path2D.Double(Path2D.WIND_NON_ZERO, points.size() + 1);
        Iterator<KVector> pointIter = points.iterator();
        if (pointIter.hasNext()) {
            KVector point1 = pointIter.next();
            path.moveTo(point1.x, point1.y);
            while (pointIter.hasNext()) {
                point1 = pointIter.next();
                if (curve && pointIter.hasNext()) {
                    KVector point2 = pointIter.next();
                    if (pointIter.hasNext()) {
                        KVector point3 = pointIter.next();
                        path.curveTo(point1.x, point1.y, point2.x, point2.y, point3.x, point3.y);
                    } else {
                        path.quadTo(point1.x, point1.y, point2.x, point2.y);
                    }
                } else {
                    path.lineTo(point1.x, point1.y);
                }
            }
        }
        return path;
    }
    
    /**
     * Handle the content of the given rendering.
     * 
     * @param rendering a container rendering
     * @param size the size of the shape rendering, or {@code null}
     * @param points the points of the polyline rendering, or {@code null}
     * @param isSpline whether the points are control points for splines
     */
    private void handleContent(final KContainerRendering rendering, final KVector size,
            final KVectorChain points, final boolean isSpline) {
        if (rendering instanceof KPolyline && points != null) {
            // approximate the spline for more accurate decorator placement
            KVectorChain referencePoints = points;
            if (isSpline || rendering instanceof KSpline) {
                referencePoints = KielerMath.approximateSpline(points);
            }
            
            // handle the decorator placement of the contained children
            for (KRendering child : rendering.getChildren()) {
                handleDecoratorPlacement(child, referencePoints);
            }
            
        } else if (size != null) {
            if (rendering.getChildPlacement() instanceof KGridPlacement) {
                // handle the grid placement of the contained children
                int colCount = ((KGridPlacement) rendering.getChildPlacement()).getNumColumns();
                int childCount = rendering.getChildren().size();
                if (colCount < 0) {
                    // special meaning for negative value: make as many columns as there are children
                    colCount = childCount;
                } else {
                    colCount = Math.min(colCount, childCount);
                }
                if (colCount > 0) {
                    int rowCount = childCount / colCount;
                    if (childCount % colCount > 0) {
                        rowCount++;
                    }
                    KRendering[] children = rendering.getChildren().toArray(
                            new KRendering[rendering.getChildren().size()]);
                    handleGridPlacement(children, size, colCount, rowCount);
                    return;
                }
            }
            
            // handle the direct placement of the contained children
            for (KRendering child : rendering.getChildren()) {
                handleDirectPlacement(child, size);
            }
        }
    }
    
    /**
     * Handle the area placement or point placement of a rendering and render it.
     * 
     * @param rendering a rendering
     * @param parentSize the parent size
     */
    private void handleDirectPlacement(final KRendering rendering, final KVector parentSize) {
        KPlacementData placeData = rendering.getPlacementData();
        
        double x = 0, y = 0;
        KVector childSize = new KVector(parentSize);
        if (placeData instanceof KAreaPlacementData) {
            KAreaPlacementData directPlaceData = (KAreaPlacementData) placeData;
            
            // determine top left corner
            if (directPlaceData.getTopLeft() != null) {
                KPosition topLeft = directPlaceData.getTopLeft();
                if (topLeft.getX() != null) {
                    KXPosition xpos = topLeft.getX();
                    x = xpos.getRelative() * parentSize.x + scale * xpos.getAbsolute();
                }
                if (topLeft.getY() != null) {
                    KYPosition ypos = topLeft.getY();
                    y = ypos.getRelative() * parentSize.y + scale * ypos.getAbsolute();
                }
            }
            
            // determine bottom right corner
            childSize.translate(-x, -y);
            if (directPlaceData.getBottomRight() != null) {
                KPosition bottomRight = directPlaceData.getBottomRight();
                KXPosition xpos = bottomRight.getX();
                if (xpos instanceof KLeftPosition) {
                    childSize.x = xpos.getRelative() * parentSize.x + scale * xpos.getAbsolute() - x;
                } else if (xpos instanceof KRightPosition) {
                    childSize.x = (1 - xpos.getRelative()) * parentSize.x - scale
                            * xpos.getAbsolute() - x;
                }
                KYPosition ypos = bottomRight.getY();
                if (ypos instanceof KTopPosition) {
                    childSize.y = ypos.getRelative() * parentSize.y + scale * ypos.getAbsolute() - y;
                }
                if (ypos instanceof KBottomPosition) {
                    childSize.y = (1 - ypos.getRelative()) * parentSize.y - scale
                            * ypos.getAbsolute() - y;
                }
            }
            
        } else if (placeData instanceof KPointPlacementData) {
            KPointPlacementData pointPlaceData = (KPointPlacementData) placeData;

            //use the properties attached to the Rendering to get the positioning data of this element
            
            /**
             * Property to save xPosition of placed element.
             */
            IProperty<Double> pointPlacedObjectXPos = new Property<Double>("PointPlacedObjectXPos");
            /**
             * Property to save yPosition of placed element.
             */
            IProperty<Double> pointPlacedObjectYPos = new Property<Double>("PointPlacedObjectYPos");
            /**
             * Property to save width of element.
             */
            IProperty<Float> pointPlacedObjectWidth = new Property<Float>("PointPlacedObjectWidth");
            /**
             * Property to save height of element.
             */
            IProperty<Float> pointPlacedObjectHeight = new Property<Float>("PointPlacedObjectHeight");

            KRendering containerRendering = ((KRendering) pointPlaceData.eContainer());
            
            Double xposcoord = containerRendering.getProperty(pointPlacedObjectXPos);
            Double yposcoord = containerRendering.getProperty(pointPlacedObjectYPos);
            Float width = containerRendering.getProperty(pointPlacedObjectWidth);
            Float height = containerRendering.getProperty(pointPlacedObjectHeight);
            if (xposcoord != null) {
                x = xposcoord;
            }

            if (yposcoord != null) {
                y = yposcoord;
            }

            if (width != null) {
                childSize.x = width;
            } else {
                childSize.x = 0;
            }

            if (height != null) {
                childSize.y = height;
            } else {
                childSize.y = 0;
            }
        } else if (placeData != null && transData != null) {
            transData.log("Placement data not supported in the context of direct placement: "
                    + placeData.eClass().getName());
        }
        
        if (childSize.x < 0) {
            childSize.x = 0;
        }
        if (childSize.y < 0) {
            childSize.y = 0;
        }
        
        // create points for polyline
        KVectorChain points = null;
        if (rendering instanceof KPolyline) {
            points = createVectorChain((KPolyline) rendering, parentSize);
        }

        // render the child with translated graphics
        graphics.translate(x, y);
        doRender(rendering, childSize, points, false);
        graphics.translate(-x, -y);
    }
    
    /**
     * Handle the grid placement of the given renderings and render them.
     * 
     * @param children child renderings that shall be rendered
     * @param parentSize the parent size
     * @param colCount the number of columns
     * @param rowCount the number of rows
     */
    private void handleGridPlacement(final KRendering[] children, final KVector parentSize,
            final int colCount, final int rowCount) {
        // gather the preferred width / height of each column / row
        double[] colWidth = new double[colCount];
        double[] rowHeight = new double[rowCount];
        for (int i = 0; i < children.length; i++) {
            KPlacementData placeData = children[i].getPlacementData();
            if (placeData instanceof KGridPlacementData) {
                KGridPlacementData gridPlaceData = (KGridPlacementData) placeData;
                // chsch: TODO hot fix is most likely not correct
                colWidth[i % colCount] = Math.min(Math.max(colWidth[i % colCount],
                        scale * gridPlaceData.getMinCellWidth()),
                        scale * gridPlaceData.getMaxCellWidth());
                rowHeight[i / colCount] = Math.min(Math.max(rowHeight[i / colCount],
                        scale * gridPlaceData.getMinCellHeight()),
                        scale * gridPlaceData.getMaxCellHeight());
            }
        }
        
        // check the column widths
        double widthSum = 0;
        int nullColCount = 0;
        for (int c = 0; c < colCount; c++) {
            widthSum += colWidth[c];
            if (colWidth[c] == 0) {
                nullColCount++;
            }
        }
        if (widthSum > parentSize.x) {
            assert nullColCount < colCount;
            // the columns are too wide, diminish their size
            double widthDim = (widthSum - parentSize.x) / (colCount - nullColCount);
            for (int c = 0; c < colCount; c++) {
                if (colWidth[c] >= widthDim) {
                    colWidth[c] -= widthDim;
                } else if (colWidth[c] > 0) {
                    if (c < colCount - 1) {
                        widthDim += (widthDim - colWidth[c]) / (colCount - c - 1);
                    }
                    colWidth[c] = 0;
                }
            }
        } else if (widthSum < parentSize.x) {
            // distribute the excess width to columns without width hint
            if (nullColCount > 0) {
                double widthAugm = (parentSize.x - widthSum) / nullColCount;
                for (int c = 0; c < colCount; c++) {
                    if (colWidth[c] == 0) {
                        colWidth[c] = widthAugm;
                    }
                }
            }
        }
        
        // check the column heights
        double heightSum = 0;
        int nullRowCount = 0;
        for (int r = 0; r < rowCount; r++) {
            heightSum += rowHeight[r];
            if (rowHeight[r] == 0) {
                nullRowCount++;
            }
        }
        if (heightSum > parentSize.y) {
            assert nullRowCount < rowCount;
            // the rows are too high, diminish their size
            double heightDim = (heightSum - parentSize.y) / (rowCount - nullRowCount);
            for (int r = 0; r < rowCount; r++) {
                if (rowHeight[r] >= heightDim) {
                    rowHeight[r] -= heightDim;
                } else if (rowHeight[r] > 0) {
                    if (r < colCount - 1) {
                        heightDim += (heightDim - rowHeight[r]) / (colCount - r - 1);
                    }
                    rowHeight[r] = 0;
                }
            }
        } else if (heightSum < parentSize.y) {
            // distribute the excess height to rows without height hint
            if (nullRowCount > 0) {
                double heightAugm = (parentSize.y - heightSum) / nullRowCount;
                for (int r = 0; r < rowCount; r++) {
                    if (rowHeight[r] == 0) {
                        rowHeight[r] = heightAugm;
                    }
                }
            }
        }
        
        // determine placement of each cell of the grid
        double xpos = 0, ypos = 0;
        for (int i = 0; i < children.length; i++) {
            int c = i % colCount;
            int r = i / colCount;
            if (c == 0) {
                xpos = 0;
            }
            
            double x = xpos, y = ypos;
            KVector childSize = new KVector(colWidth[c], rowHeight[r]);
            KPlacementData placeData = children[i].getPlacementData();
            if (placeData instanceof KGridPlacementData) {
                
                // chsch TODO: fix this
//                KGridPlacementData gridPlaceData = (KGridPlacementData) placeData;
//                x += scale * gridPlaceData.getInsetLeft();
//                y += scale * gridPlaceData.getInsetTop();
//                childSize.translate(-scale * (gridPlaceData.getInsetLeft()
//                                + gridPlaceData.getInsetRight()),
//                        -scale * (gridPlaceData.getInsetTop()
//                                + gridPlaceData.getInsetBottom()));
            } else if (placeData != null && transData != null) {
                transData.log("Placement data not supported in the context of grid placement: "
                        + placeData.eClass().getName());
            }

            if (childSize.x < 0) {
                childSize.x = 0;
            }
            if (childSize.y < 0) {
                childSize.y = 0;
            }
            
            // create points for polyline
            KVectorChain points = null;
            if (children[i] instanceof KPolyline) {
                points = createVectorChain((KPolyline) children[i], childSize);
            }
            
            // render the child with translated graphics
            graphics.translate(x, y);
            doRender(children[i], childSize, points, false);
            graphics.translate(-x, -y);
            
            xpos += colWidth[c];
            if (c == colCount - 1) {
                ypos += rowHeight[r];
            }
        }
    }
    
    /**
     * Handle the decorator placement of the given rendering and render it.
     * 
     * @param rendering a rendering
     * @param linePoints points of the reference polyline
     */
    private void handleDecoratorPlacement(final KRendering rendering, final KVectorChain linePoints) {
        KPlacementData placeData = rendering.getPlacementData();
        if (placeData instanceof KDecoratorPlacementData && linePoints.size() >= 2) {
            KDecoratorPlacementData decoPlaceData = (KDecoratorPlacementData) placeData;
            
            // calculate the reference point
            double absLocation = decoPlaceData.getRelative() * linePoints.getLength();
            KVector referencePoint = linePoints.getPointOnLine(absLocation);
            
            KVector size = new KVector(decoPlaceData.getWidth(), decoPlaceData.getHeight());
            if (size.x < 0) {
                size.x = 0;
            }
            if (size.y < 0) {
                size.y = 0;
            }
            
            // create points for polyline
            KVectorChain points = null;
            if (rendering.getPlacementData() instanceof KPolyline) {
                points = createVectorChain((KPolyline) rendering, size);
            }
            
            // rotate the decorator if requested
            if (decoPlaceData.isRotateWithLine()) {
                double angle = linePoints.getAngleOnLine(absLocation);
                
                // render the decorator with translated and rotated graphics
                graphics.translate(referencePoint.x, referencePoint.y);
                graphics.rotate(angle);
                graphics.translate(decoPlaceData.getXOffset(), decoPlaceData.getYOffset());
                doRender(rendering, size, points, false);
                graphics.translate(-decoPlaceData.getXOffset(), -decoPlaceData.getYOffset());
                graphics.rotate(-angle);
                graphics.translate(-referencePoint.x, -referencePoint.y);
            } else {
                // render the decorator with translated graphics
                double x = referencePoint.x + decoPlaceData.getXOffset();
                double y = referencePoint.y + decoPlaceData.getYOffset();

                graphics.translate(x, y);
                doRender(rendering, size, points, false);
                graphics.translate(-x, -y);
            }
            
        } else if (transData != null) {
            if (linePoints.size() < 2) {
                transData.log("Need at least 2 line points for placement of decorator "
                        + rendering.eClass().getName());
            } else {
                transData.log("Placement data not supported in the context of decorator placement: "
                        + placeData.eClass().getName());
            }
        }
    }

}
