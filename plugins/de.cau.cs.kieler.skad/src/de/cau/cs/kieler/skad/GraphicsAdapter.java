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

import java.awt.Polygon;
import java.awt.Shape;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.Image;

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
    public static Shape toShape(final Rectangle r) {
        return new java.awt.Rectangle(r.x, r.y, r.width, r.height);
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
    public void clipRect(final Rectangle r) {
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
    public void drawRoundRectangle(final Rectangle r, final int arcWidth, final int arcHeight) {
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
    public void fillRoundRectangle(final Rectangle r, final int arcWidth, final int arcHeight) {
        pg.fillRoundRect(r.x, r.y, r.width, r.height, arcWidth, arcHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillString(final String s, final int x, final int y) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillText(final String s, final int x, final int y) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getBackgroundColor() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getClip(final Rectangle rect) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Font getFont() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FontMetrics getFontMetrics() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getForegroundColor() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLineStyle() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLineWidth() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getLineWidthFloat() {
        // TODO Auto-generated method stub
        return 0;
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
    public void setBackgroundColor(final Color rgb) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClip(final Rectangle r) {
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
    public void setForegroundColor(final Color rgb) {
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
