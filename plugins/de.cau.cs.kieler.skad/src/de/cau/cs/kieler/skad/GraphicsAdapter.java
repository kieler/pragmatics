/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.skad;

import java.awt.BasicStroke;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import edu.umd.cs.piccolox.swt.SWTGraphics2D;

/**
 * A Draw2D graphics object that wraps a Piccolo graphics.
 *
 * @author msp
 */
public class GraphicsAdapter extends Graphics {
    
    /**
     * Transform the given Draw2D rectangle to an AWT rectangle.
     * 
     * @param r a Draw2D rectangle
     * @return an AWT rectangle
     */
    public static Shape toShape(final org.eclipse.draw2d.geometry.Rectangle r) {
        return new java.awt.Rectangle(r.x, r.y, r.width, r.height);
    }
    
    /**
     * Transform the given AWT shape to a Draw2D rectangle.
     * 
     * @param s an AWT shape
     * @return a Draw2D rectangle
     */
    public static org.eclipse.draw2d.geometry.Rectangle toRectangle(final Shape s) {
        java.awt.Rectangle bounds = s.getBounds();
        return new org.eclipse.draw2d.geometry.Rectangle(bounds.x, bounds.y,
                bounds.width, bounds.height);
    }
    
    /**
     * Transform the given Draw2D point list to an AWT polygon.
     * 
     * @param pl a Draw2D point list
     * @return an AWT polygon
     */
    public static Polygon toPolygon(final PointList pl) {
        Polygon result = new Polygon();
        for (int i = 0; i < pl.size(); i++) {
            org.eclipse.draw2d.geometry.Point p = pl.getPoint(i);
            result.addPoint(p.x, p.y);
        }
        return result;
    }
    
    /**
     * Transform the given Draw2D point list into an array of coordinates.
     * 
     * @param pl a Draw2D point list
     * @return a double precision array
     */
    public static double[] toArray(final PointList pl) {
        int[] points = pl.toIntArray();
        double[] result = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            result[i] = points[i];
        }
        return result;
    }
    
    /**
     * Transform the given SWT color into an AWT color.
     * 
     * @param color an SWT color
     * @return an AWT color
     */
    public static java.awt.Color toAWTColor(final org.eclipse.swt.graphics.Color color) {
        return new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Transform the given AWT color into an SWT color.
     * 
     * @param device the device for which to create the color
     * @param color an AWT color
     * @return an SWT color
     */
    public static org.eclipse.swt.graphics.Color toSWTColor(final Device device,
            java.awt.Color color) {
        // FIXME handle reuse and disposal of colors
        return new org.eclipse.swt.graphics.Color(device, color.getRed(), color.getGreen(),
                color.getBlue());
    }

    /** the Piccolo wrapper for SWT graphics. */
    private SWTGraphics2D pg;
    
    /**
     * Creates a Draw2D graphics adapter.
     * 
     * @param graphics the Piccolo wrapper for SWT graphics
     */
    public GraphicsAdapter(final SWTGraphics2D graphics) {
        this.pg = graphics;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void clipRect(final org.eclipse.draw2d.geometry.Rectangle r) {
        pg.setClip(toShape(r));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        // do nothing, since this is just an adapter
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawArc(final int x, final int y, final int w, final int h, final int offset,
            final int length) {
        pg.drawArc(x, y, w, h, offset, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawFocus(final int x, final int y, final int w, final int h) {
        // TODO not yet implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawImage(final Image srcImage, final int x, final int y) {
        pg.drawImage(srcImage, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawImage(final Image srcImage, final int x1, final int y1, final int w1, final int h1,
            final int x2, final int y2, final int w2, final int h2) {
        pg.drawImage(srcImage, x1, y1, w1, h1, x2, y2, w2, h2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawLine(final int x1, final int y1, final int x2, final int y2) {
        pg.drawLine(x1, y1, x2, y2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOval(final int x, final int y, final int w, final int h) {
        pg.drawOval(x, y, w, h);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawPolygon(final PointList points) {
        pg.drawPolygon(toPolygon(points));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawPolyline(final PointList points) {
        pg.drawPolyline(toArray(points));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRectangle(final int x, final int y, final int width, final int height) {
        pg.drawRect(x, y, width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRoundRectangle(final org.eclipse.draw2d.geometry.Rectangle r,
            final int arcWidth, final int arcHeight) {
        pg.drawRoundRect(r.x, r.y, r.width, r.height, arcWidth, arcHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawString(final String s, final int x, final int y) {
        pg.drawString(s, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawText(final String s, final int x, final int y) {
        pg.drawText(s, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillArc(final int x, final int y, final int w, final int h, final int offset,
            final int length) {
        pg.fillArc(x, y, w, h, offset, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillGradient(final int x, final int y, final int w, final int h,
            final boolean vertical) {
        pg.fillGradientRectangle(x, y, w, h, vertical);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillOval(final int x, final int y, final int w, final int h) {
        pg.fillOval(x, y, w, h);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillPolygon(final PointList points) {
        pg.fillPolygon(toPolygon(points));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillRectangle(final int x, final int y, final int width, final int height) {
        pg.fillRect(x, y, width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillRoundRectangle(final org.eclipse.draw2d.geometry.Rectangle r,
            final int arcWidth, final int arcHeight) {
        pg.fillRoundRect(r.x, r.y, r.width, r.height, arcWidth, arcHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillString(final String s, final int x, final int y) {
        org.eclipse.swt.graphics.Point extent = pg.stringExtent(s);
        pg.fillRect(x, y, extent.x, extent.y);
        pg.drawString(s, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillText(final String s, final int x, final int y) {
        org.eclipse.swt.graphics.Point extent = pg.textExtent(s);
        pg.fillRect(x, y, extent.x, extent.y);
        pg.drawText(s, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.swt.graphics.Color getBackgroundColor() {
        return toSWTColor(Display.getDefault(), pg.getBackground());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.draw2d.geometry.Rectangle getClip(
            final org.eclipse.draw2d.geometry.Rectangle rect) {
        org.eclipse.draw2d.geometry.Rectangle clip = toRectangle(pg.getClip());
        return rect.intersect(clip);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Font getFont() {
        return pg.getSWTFont();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FontMetrics getFontMetrics() {
        return pg.getSWTFontMetrics();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.swt.graphics.Color getForegroundColor() {
        return toSWTColor(Display.getDefault(), pg.getColor());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLineStyle() {
        return SWT.LINE_SOLID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLineWidth() {
        Stroke stroke = pg.getStroke();
        if (stroke instanceof BasicStroke) {
            return (int) ((BasicStroke) stroke).getLineWidth();
        }
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getLineWidthFloat() {
        Stroke stroke = pg.getStroke();
        if (stroke instanceof BasicStroke) {
            return ((BasicStroke) stroke).getLineWidth();
        }
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getXORMode() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void popState() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pushState() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restoreState() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void scale(final double amount) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackgroundColor(final org.eclipse.swt.graphics.Color rgb) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClip(final org.eclipse.draw2d.geometry.Rectangle r) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFont(final Font f) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setForegroundColor(final org.eclipse.swt.graphics.Color rgb) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineStyle(final int style) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineWidth(final int width) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineWidthFloat(final float width) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLineMiterLimit(final float miterLimit) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setXORMode(final boolean b) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void translate(final int dx, final int dy) {
        // TODO Auto-generated method stub

    }

}
