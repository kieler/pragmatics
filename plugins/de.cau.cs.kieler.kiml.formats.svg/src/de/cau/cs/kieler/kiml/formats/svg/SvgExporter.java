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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.List;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Exporter for SVG format.
 *
 * @author msp
 */
public class SvgExporter implements IGraphTransformer<KNode, SVGGraphics2D> {

    /** namespace of the SVG format. */
    private static final String SVG_NAMESPACE = "http://www.w3.org/2000/svg";
    
    /** length of edge arrows. */
    private static final float ARROW_LENGTH = 7.0f;
    /** width of edge arrows. */
    private static final float ARROW_WIDTH = 5.0f;    

    /** background color. */
    private static final Color BACKGROUND_COLOR = new Color(255, 255, 255);
    /** border color for nodes. */
    private static final Color NODE_BORDER_COLOR = new Color(10, 57, 14, 150);
    /** fill color for nodes. */
    private static final Color NODE_FILL_COLOR = new Color(87, 197, 133, 80);
    /** font used for node labels. */
    private static final Font NODE_FONT = new Font("SansSerif", Font.BOLD, 10);
    /** background color for labels. */
    private static final Color LABEL_BACK_COLOR = new Color(243, 255, 199, 180);
    /** color used for ports. */
    private static final Color PORT_COLOR = new Color(4, 17, 69, 230);
    /** font used for port labels. */
    private static final Font PORT_FONT = new Font("SansSerif", Font.PLAIN, 6);
    /** color used for edges. */
    private static final Color EDGE_COLOR = new Color(49, 77, 114, 230);
    /** font used for edge labels. */
    private static final Font EDGE_FONT  = new Font("SansSerif", Font.PLAIN, 8);
    
    /**
     * {@inheritDoc}
     */
    public void transform(TransformationData<KNode, SVGGraphics2D> data) {
        // create a DOM document for XML output
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        Document document = domImpl.createDocument(SVG_NAMESPACE, "svg", null);
        
        // create an instance of the SVG generator
        SVGGraphics2D graphics = new SVGGraphics2D(document);
        data.getTargetGraphs().add(graphics);
        
        KNode parentKNode = data.getSourceGraph();
        
        Rectangle rect = getRect(parentKNode.getData(KShapeLayout.class), new KVector());
        
        graphics.setBackground(BACKGROUND_COLOR);
        
        // paint the top layout node with its children
        KVector offset = new KVector(rect.x, rect.y);
        paintLayoutNode(parentKNode, graphics, offset);
       
    }

    /**
     * Create a rectangle for the given shape layout.
     * 
     * @param shapeLayout a shape layout
     * @param offset offset to add to shape coordinates
     * @return a new rectangle
     */
    private Rectangle getRect(final KShapeLayout shapeLayout, final KVector offset) {
        if (shapeLayout != null) {
            return new Rectangle(Math.round(shapeLayout.getXpos() + (float) offset.x),
                    Math.round(shapeLayout.getYpos() + (float) offset.y),
                    Math.round(shapeLayout.getWidth()),
                    Math.round(shapeLayout.getHeight()));
        }
        return null;
    }

    /**
     * Draw the given text centered in given rectangle bounds.
     * 
     * TODO fix graphical glitches with drawString
     * 
     * @param text a text string
     * @param graphics graphics object to draw on
     * @param bounds the bounds for placement of the string
     */
    private void drawString(final String text, final Graphics2D graphics, final Rectangle bounds) {
        int width = graphics.getFontMetrics().stringWidth(text);
        int height = graphics.getFontMetrics().getHeight();
//        graphics.drawString(text, bounds.x + (bounds.width - width) / 2,
//                bounds.y + (bounds.height + height) / 2);
          graphics.drawString(text, bounds.x + (bounds.width - width) / 2,
              bounds.y + (bounds.height + height) / 2);	
    }

    /**
     * Paints a layout node.
     * 
     * @param layoutNode layout node to paint
     * @param graphics the graphics context used to paint
     * @param offset offset to be added to relative coordinates
     */
    private void paintLayoutNode(final KNode layoutNode, final Graphics2D graphics,
            final KVector offset) {
        // paint ports of the layout node
        graphics.setFont(PORT_FONT);
        graphics.setColor(PORT_COLOR);
        for (KPort port : layoutNode.getPorts()) {
            Rectangle rect = getRect(port.getData(KShapeLayout.class), offset);
            graphics.fill(rect);
            for (KLabel label : port.getLabels()) {
                if (label.getText() != null) {
                    KVector portOffset = new KVector(rect.x, rect.y);
                    rect = getRect(label.getData(KShapeLayout.class), portOffset);
                    graphics.setColor(LABEL_BACK_COLOR);
                    graphics.fill(rect);
                    graphics.setColor(PORT_COLOR);
                    drawString(label.getText(), graphics, rect);
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
            Rectangle rect = getRect(child.getData(KShapeLayout.class), subOffset);
            graphics.setColor(NODE_FILL_COLOR);
            graphics.fill(rect);
            graphics.setColor(NODE_BORDER_COLOR);
            graphics.draw(rect);
            KVector childOffset = new KVector(rect.x, rect.y);
            paintLayoutNode(child, graphics, childOffset);
            for (KLabel label : child.getLabels()) {
                if (label.getText() != null) {
                    graphics.setFont(NODE_FONT);
                    rect = getRect(label.getData(KShapeLayout.class), childOffset);
                    graphics.setColor(LABEL_BACK_COLOR);
                    graphics.fill(rect);
                    graphics.setColor(NODE_BORDER_COLOR);
                    drawString(label.getText(), graphics, rect);
                }
            }

            // paint edges, deactivate label painting for incoming edges
            graphics.setColor(EDGE_COLOR);
            graphics.setFont(EDGE_FONT);
            for (KEdge edge : child.getOutgoingEdges()) {
                paintEdge(edge, graphics);
            }
        }
    }

    /**
     * Paints an edge.
     * 
     * TODO use polylines if they fix the issue with two lines with alpha-channel overlaying each other
     * LINK: http://www.w3schools.com/svg/svg_polyline.asp
     * 
     * @param edge edge to paint
     * @param graphics the graphics context used to paint
     */
    private void paintEdge(final KEdge edge, final Graphics2D graphics) {
        // calculate an offset for edge coordinates
        KVector offset = new KVector();
        KNode parent = edge.getSource();
        if (!KimlUtil.isDescendant(edge.getTarget(), parent)) {
            parent = parent.getParent(); //???
        }
        KimlUtil.toAbsolute(offset, parent);
        
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        KPoint sourcePoint = edgeLayout.getSourcePoint();
        KPoint targetPoint = edgeLayout.getTargetPoint();
        List<KPoint> bendPoints = edgeLayout.getBendPoints();
        
        int[] x_coords = new int[bendPoints.size()+2];
        int[] y_coords = new int[x_coords.length];
        
        x_coords[0] = (int) Math.round(sourcePoint.getX() + offset.x);
        y_coords[0] = (int) Math.round(sourcePoint.getY() + offset.y);
        x_coords[x_coords.length-1] = (int) Math.round(targetPoint.getX() + offset.x);
        y_coords[y_coords.length-1] = (int) Math.round(targetPoint.getY() + offset.y);
        
        for(int i=0;i<bendPoints.size();i++){
        	x_coords[i+1] = (int) Math.round(bendPoints.get(i).getX() + offset.x);
            y_coords[i+1] = (int) Math.round(bendPoints.get(i).getY() + offset.y);
        }
        
        //draw a polyline with the given points to prevent issues when color contains alpha-channel
        graphics.drawPolyline(x_coords, y_coords, x_coords.length);
        
        // draw an arrow at the last segment of the connection
        Polygon arrowPoly = makeArrow(bendPoints.get(bendPoints.size()-1), targetPoint, offset);
        if (arrowPoly != null) {
            graphics.fillPolygon(arrowPoly);
        }

        for (KLabel edgeLabel : edge.getLabels()) {
            if (edgeLabel.getText() != null) {
                Rectangle rect = getRect(edgeLabel.getData(KShapeLayout.class), offset);
                graphics.setColor(LABEL_BACK_COLOR);
                graphics.fill(rect);
                graphics.setColor(EDGE_COLOR);
                drawString(edgeLabel.getText(), graphics, rect);
            }
        }
    }
    
    // CHECKSTYLEOFF MagicNumber

    /**
     * Constructs a polygon that forms an arrow.
     * 
     * TODO make arrowhead look nicer (moved sideways somehow)
     * 
     * @param point1 source point
     * @param point2 target point
     * @param offset offset value to be added to coordinates
     * @return the arrow polygon, or null if the given source and target points are equal
     */
    private Polygon makeArrow(final KPoint point1, final KPoint point2, final KVector offset) {
        if (!(point1.getX() == point2.getX() && point1.getY() == point2.getY())) {
            int[] arrowx = new int[3];
            int[] arrowy = new int[3];
            arrowx[0] = (int) Math.round(point2.getX() + offset.x);
            arrowy[0] = (int) Math.round(point2.getY() + offset.y);

            float vectX = point1.getX() - point2.getX();
            float vectY = point1.getY() - point2.getY();
            float length = (float) Math.sqrt(vectX * vectX + vectY * vectY);
            float normX = vectX / length;
            float normY = vectY / length;
            float neckX = point2.getX() + ARROW_LENGTH * normX;
            float neckY = point2.getY() + ARROW_LENGTH * normY;
            float orthX = normY * ARROW_WIDTH / 2;
            float orthY = -normX * ARROW_WIDTH / 2;

            arrowx[1] = (int) Math.round(neckX + orthX + offset.x);
            arrowy[1] = (int) Math.round(neckY + orthY + offset.y);
            arrowx[2] = (int) Math.round(neckX - orthX + offset.x);
            arrowy[2] = (int) Math.round(neckY - orthY + offset.y);
            return new Polygon(arrowx, arrowy, 3);
        } else {
            return null;
        }
    }
	
	/**
     * {@inheritDoc}
     */
    public void transferLayout(TransformationData<KNode, SVGGraphics2D> data) {
        throw new UnsupportedOperationException("SVG layout transfer is not supported.");
    }

}
