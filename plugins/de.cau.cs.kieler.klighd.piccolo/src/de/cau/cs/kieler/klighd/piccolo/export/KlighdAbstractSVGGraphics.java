/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.export;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.IndexColorModel;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.awt.image.renderable.RenderableImage;
import java.io.IOException;
import java.io.OutputStream;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.RGB;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.krendering.KTextUtil;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsEx;
import de.cau.cs.kieler.klighd.piccolo.internal.util.RGBGradient;
import de.cau.cs.kieler.klighd.util.KlighdSemanticDiagramData;

/**
 * Common superclass for SVG generators using a {@link Graphics2D} to produce svg images.
 * 
 * Subclasses are registered via the {@link SVGGeneratorManager#EXTP_ID_SVGGENERATORS} extension
 * point. All implementing classes have to provide a two-argument constructor for {@code bounds} (
 * {@link Rectangle2D}) and the {@code textAsShapes} ({@link Boolean}) flag.
 * 
 * Instances of one of the generators can be retrieved using the
 * {@link SVGGeneratorManager#createGraphics(String, Rectangle2D, boolean)} method.
 * 
 * @author uru
 * @author chsch
 */
public abstract class KlighdAbstractSVGGraphics extends Graphics2D implements KlighdSWTGraphicsEx {

    // The svg generator element
    private Graphics2D graphics;

    // Internal attributes
    private LineAttributes lineAttributes = new LineAttributes(1f);
    private int alpha = KlighdConstants.ALPHA_FULL_OPAQUE;

    private RGB strokeColor = KlighdConstants.BLACK;
    private Pair<RGBGradient, Rectangle2D> strokePattern = null;

    private RGB fillColor = KlighdConstants.WHITE;
    private Pair<RGBGradient, Rectangle2D> fillPattern = null;

    private FontData fontData = KlighdConstants.DEFAULT_FONT;

    private final Map<ImageData, BufferedImage> imageBuffer = Maps.newHashMap();
    private final Rectangle2D imageBoundsRect = new Rectangle2D.Double();
    
    /**
     * true if multiline strings can be handled by exporter.
     */
    private boolean canHandleMultiline = false;

    /**
     * @param graphicsDelegate
     *            the {@link Graphics2D} object to which all drawing requests are delegated. If
     *            <code>null</code> is passed, make sure the
     *            {@link #setGraphicsDelegate(Graphics2D)} method is called prior to any drawing!
     */
    public KlighdAbstractSVGGraphics(final Graphics2D graphicsDelegate) {
        setGraphicsDelegate(graphicsDelegate);
    }

    /**
     * @param graphicsDelegate
     *            the {@link Graphics2D} object to which all drawing requests are delegated.
     */
    protected void setGraphicsDelegate(final Graphics2D graphicsDelegate) {
        this.graphics = graphicsDelegate;
    }

    /**
     * @return the internal graphics delegate.
     */
    protected Graphics2D getGraphicsDelegate() {
        return graphics;
    }

    /**
     * @return String representation of the svg.
     */
    public abstract String getSVG();

    /**
     * Clear the whole drawing area.
     */
    public abstract void clear();

    /**
     * Submits the desired SVG data via the provided {@link OutputStream} <code>output</code>.
     * 
     * @param output
     *            the {@link OutputStream} to dump the SVG data into
     * @throws IOException a
     */
    public abstract void stream(final OutputStream output) throws IOException;

    /**
     * {@inheritDoc}
     */
    public Device getDevice() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setDevice(final Device theDevice) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    public GC getGC() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setGC(final GC theGc) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    public LineAttributes getLineAttributes() {
        return lineAttributes;
    }

    /**
     * {@inheritDoc}
     */
    public void setLineAttributes(final LineAttributes attributes) {
        lineAttributes = attributes;

        final float[] dash = lineAttributes.dash == null
                ? null : lineAttributes.dash.length == 0 ? null : lineAttributes.dash;

        final Stroke s = new BasicStroke(lineAttributes.width, lineAttributes.cap - 1,
                lineAttributes.join - 1, lineAttributes.miterLimit, dash, lineAttributes.dashOffset);

        graphics.setStroke(s);
    }

    /**
     * {@inheritDoc}
     */
    public int getAlpha() {
        return alpha;
    }

    /**
     * {@inheritDoc}
     */
    public void setAlpha(final int alpha) {
        this.alpha = alpha;
    }

    /**
     * {@inheritDoc}
     */
    public RGB getStrokeColor() {
        return this.strokeColor;
    }

    /**
     * {@inheritDoc}
     */
    public void setStrokeColor(final RGB color) {
        this.strokeColor = color;
        this.strokePattern = null;
    }

    /**
     * {@inheritDoc}
     */
    public void setStrokePattern(final RGBGradient gradient, final Rectangle2D bounds) {
        this.strokePattern = Pair.of(gradient, bounds);
        this.strokeColor = null;
    }

    /**
     * {@inheritDoc}
     */
    public RGB getFillColor() {
        return this.fillColor;
    }

    /**
     * {@inheritDoc}
     */
    public void setFillColor(final RGB backgroundColor) {
        this.fillColor = backgroundColor;
        this.fillPattern = null;
    }

    /**
     * {@inheritDoc}
     */
    public void setFillPattern(final RGBGradient backgroundGradient, final Rectangle2D bounds) {
        this.fillPattern = Pair.of(backgroundGradient, bounds);
        this.fillColor = null;
    }

    /**
     * {@inheritDoc}
     */
    public FontData getFontData() {
        return this.fontData;
    }

    /**
     * {@inheritDoc}
     */
    public void setFont(final FontData theFontData) {
        this.fontData = theFontData;
        if (theFontData == null) {
            return;
        }
        graphics.setFont(new Font(theFontData.getName(), KTextUtil.swtFontStyle2Awt(theFontData
                .getStyle()), theFontData.getHeight()));
    }

    /**
     * {@inheritDoc}
     */
    public void setUnderline(final int theUnderlining, final RGB color) {
        // toBeDone: Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     */
    public void setStrikeout(final boolean theStrikeout, final RGB color) {
        // toBeDone: Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AffineTransform getTransform() {
        return graphics.getTransform();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTransform(final AffineTransform transform) {
        graphics.setTransform(transform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final AffineTransform transform) {
        graphics.transform(transform);
    }

    @Override
    public void translate(final int x, final int y) {
        graphics.translate(x, y);
    }

    @Override
    public void translate(final double tx, final double ty) {
        graphics.translate(tx, ty);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Shape getClip() {
        return graphics.getClip();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClip(final Shape clip) {
        graphics.setClip(clip);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clip(final Shape clip) {
        graphics.clip(clip);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(final Shape s) {
        final Paint p =
                this.strokeColor != null ? rgb2Color(this.strokeColor, this.alpha)
                        : this.strokePattern != null ? rgb2Pattern(this.strokePattern) : null;
        if (p != null) {
            graphics.setPaint(p);
            graphics.draw(s);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void draw(final Path p) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final Shape s) {
        final Paint p =
                this.fillColor != null ? rgb2Color(this.fillColor, this.alpha)
                        : this.fillPattern != null ? rgb2Pattern(this.fillPattern) : null;
        if (p != null) {
            graphics.setPaint(p);
            graphics.fill(s);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void fill(final Path p) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    public void drawImage(final Image image, final double width, final double height) {
        this.imageBoundsRect.setRect(0, 0, width, height);
        final Rectangle bounds = imageBoundsRect.getBounds();

        // don't use the buffer in this case
        // as Image#getImageData() returns always a new instance
        final java.awt.Image img = convertToAWT(image.getImageData());
        graphics.drawImage(img, 0, 0, bounds.width, bounds.height, null);
    }

    /**
     * {@inheritDoc}
     */
    public void drawImage(final ImageData imageData, final double width, final double height) {
        this.imageBoundsRect.setRect(0, 0, width, height);
        final Rectangle bounds = imageBoundsRect.getBounds();

        java.awt.Image image = imageBuffer.get(imageData);
        if (image == null) {
            image = convertToAWT(imageData);
        }

        graphics.drawImage(image, 0, 0, bounds.width, bounds.height, null);
    }

    /**
     * {@inheritDoc}
     */
    public void drawText(final String string) {
        // make sure that the color for text drawing is set (defaults to black)
        graphics.setColor(strokeColor != null ? rgb2Color(strokeColor, alpha) : new Color(0, 0, 0,
                alpha));

        // SVG 1.1 does not support automatic line wrapping, thus each line has to be drawn
        // individually.
        // SVG 1.2 supports a textArea with automatic wrapping, however this is not supported by all
        // browsers.
        if (!canHandleMultiline) {
            int y = 0;
            final int fontHeight =
                    graphics.getFontMetrics().getHeight() - graphics.getFontMetrics().getDescent();
    
            // translate by the font height as the reference point for drawing text in svg seems to be
            // at the bottom left corner, SWT has it as the top left corner.
            translate(0, fontHeight);
    
            for (final String line : string.split("\\r?\\n|\\r")) {
                graphics.drawString(line, 0, y);
                y += fontHeight;
            }
            translate(0, -fontHeight);
        } else {
            graphics.drawString(string, 0, 0);
        }
    }

    /*------------------------------------------------ 
     * Internal conversion methods.
     * ------------------------------------------------ */
    private static RGB color2rgb(final Color color) {
        return new RGB(color.getRed(), color.getGreen(), color.getBlue());
    }

    @SuppressWarnings("unused")
    private static Color rgb2Color(final RGB color) {
        return new Color(color.red, color.green, color.blue);
    }

    private static Color rgb2Color(final RGB color, final int alpha) {
        return new Color(color.red, color.green, color.blue, alpha);
    }

    private static GradientPaint rgb2Pattern(final Pair<RGBGradient, Rectangle2D> gradient) {
        return rgb2Pattern(gradient.getFirst(), gradient.getSecond());
    }

    private static GradientPaint rgb2Pattern(final RGBGradient gradient, final Rectangle2D bounds) {
        final GradientPaint gp =
                new GradientPaint((float) bounds.getMinX(), (float) bounds.getMinY(), rgb2Color(
                        gradient.getColor1(), gradient.getAlpha1()), (float) bounds.getMaxX(),
                        (float) bounds.getMaxY(), rgb2Color(gradient.getColor2(),
                                gradient.getAlpha2()));

        return gp;
    }

    private BufferedImage convertToAWT(final ImageData data) {
        final BufferedImage bufferedImage;

        ColorModel colorModel = null;
        final PaletteData palette = data.palette;
        if (palette.isDirect) {
            colorModel =
                    new DirectColorModel(data.depth, palette.redMask, palette.greenMask,
                            palette.blueMask);
            bufferedImage =
                    new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(
                            data.width, data.height), false, null);
            for (int y = 0; y < data.height; y++) {
                for (int x = 0; x < data.width; x++) {
                    final int pixel = data.getPixel(x, y);
                    final RGB rgb = palette.getRGB(pixel);
                    // CHECKSTYLEOFF Magic Numbers
                    bufferedImage.setRGB(x, y, rgb.red << 16 | rgb.green << 8 | rgb.blue);
                    // CHECKSTYLEON Magic Numbers
                }
            }
        } else {
            final RGB[] rgbs = palette.getRGBs();
            final byte[] red = new byte[rgbs.length];
            final byte[] green = new byte[rgbs.length];
            final byte[] blue = new byte[rgbs.length];
            for (int i = 0; i < rgbs.length; i++) {
                final RGB rgb = rgbs[i];
                red[i] = (byte) rgb.red;
                green[i] = (byte) rgb.green;
                blue[i] = (byte) rgb.blue;
            }
            if (data.transparentPixel != -1) {
                colorModel =
                        new IndexColorModel(data.depth, rgbs.length, red, green, blue,
                                data.transparentPixel);
            } else {
                colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue);
            }
            bufferedImage =
                    new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(
                            data.width, data.height), false, null);
            final WritableRaster raster = bufferedImage.getRaster();
            final int[] pixelArray = new int[1];
            for (int y = 0; y < data.height; y++) {
                for (int x = 0; x < data.width; x++) {
                    final int pixel = data.getPixel(x, y);
                    pixelArray[0] = pixel;
                    raster.setPixel(x, y, pixelArray);
                }
            }
        }

        this.imageBuffer.put(data, bufferedImage);
        return bufferedImage;
    }

    /* ------------------------------------------------ */
    /* legacy methods due to inheritance of Graphics2D */
    /* that is required by KlighdCanvas -> PSWTCanvas */
    /* and PPaintContext */
    /* ------------------------------------------------ */

    @Override
    public Composite getComposite() {
        return graphics.getComposite();
    }

    @Override
    public void setComposite(final Composite comp) {
        graphics.setComposite(comp);
    }

    @Override
    public void setColor(final java.awt.Color c) {
        setStrokeColor(color2rgb(c));
    }

    @Override
    public void setBackground(final java.awt.Color c) {
        setFillColor(color2rgb(c));
    }

    @Override
    public void fillRect(final int x, final int y, final int width, final int height) {
        fill(new Rectangle2D.Double(x, y, width, height));
    }

    @Override
    public void setRenderingHint(final Key hintKey, final Object hintValue) {
        // RenderingsHints set this way are likely to contradict the defaults in BatikSVGGraphics
        //  which leads to a blown-up svg file with a huge amount of repeated local style settings

        // graphics.setRenderingHint(hintKey, hintValue);
    }

    /* ------------------------------------------------ */
    /* legacy methods due to inheritance of Graphics2D  */
    /* that are not supported by this implementation    */
    /* ------------------------------------------------ */

    // CHECKSTYLEOFF Parameter|LineLength

    @Override
    public void drawImage(final BufferedImage img, final BufferedImageOp op, final int x, final int y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawRenderedImage(final RenderedImage img, final AffineTransform xform) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawRenderableImage(final RenderableImage img, final AffineTransform xform) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawString(final String str, final int x, final int y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawString(final String str, final float x, final float y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawString(final AttributedCharacterIterator iterator, final int x, final int y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawString(final AttributedCharacterIterator iterator, final float x, final float y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawGlyphVector(final GlyphVector g, final float x, final float y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hit(final java.awt.Rectangle rect, final Shape s, final boolean onStroke) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GraphicsConfiguration getDeviceConfiguration() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPaint(final Paint paint) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setStroke(final Stroke s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getRenderingHint(final Key hintKey) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setRenderingHints(final Map<?, ?> hints) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addRenderingHints(final Map<?, ?> hints) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RenderingHints getRenderingHints() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void rotate(final double theta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void rotate(final double theta, final double x, final double y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void scale(final double sx, final double sy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void shear(final double shx, final double shy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Paint getPaint() {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.awt.Color getBackground() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Stroke getStroke() {
        throw new UnsupportedOperationException();
    }

    @Override
    public FontRenderContext getFontRenderContext() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Graphics create() {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.awt.Color getColor() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPaintMode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setXORMode(final java.awt.Color c1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.awt.Font getFont() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setFont(final java.awt.Font font) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FontMetrics getFontMetrics(final java.awt.Font f) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.awt.Rectangle getClipBounds() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clipRect(final int x, final int y, final int width, final int height) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setClip(final int x, final int y, final int width, final int height) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void copyArea(final int x, final int y, final int width, final int height, final int dx, final int dy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawLine(final int x1, final int y1, final int x2, final int y2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clearRect(final int x, final int y, final int width, final int height) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawRoundRect(final int x, final int y, final int width, final int height, final int arcWidth, final int arcHeight) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fillRoundRect(final int x, final int y, final int width, final int height, final int arcWidth, final int arcHeight) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawOval(final int x, final int y, final int width, final int height) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fillOval(final int x, final int y, final int width, final int height) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawArc(final int x, final int y, final int width, final int height, final int startAngle, final int arcAngle) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fillArc(final int x, final int y, final int width, final int height, final int startAngle, final int arcAngle) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawPolyline(final int[] xPoints, final int[] yPoints, final int nPoints) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawPolygon(final int[] xPoints, final int[] yPoints, final int nPoints) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fillPolygon(final int[] xPoints, final int[] yPoints, final int nPoints) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean drawImage(final java.awt.Image img, final AffineTransform xform, final ImageObserver obs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean drawImage(final java.awt.Image img, final int x, final int y, final ImageObserver observer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean drawImage(final java.awt.Image img, final int x, final int y, final int width, final int height,
            final ImageObserver observer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean drawImage(final java.awt.Image img, final int x, final int y, final Color bgcolor, final ImageObserver observer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean drawImage(final java.awt.Image img, final int x, final int y, final int width, final int height,
            final Color bgcolor, final ImageObserver observer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean drawImage(final java.awt.Image img, final int dx1, final int dy1, final int dx2, final int dy2, final int sx1,
            final int sy1, final int sx2, final int sy2, final ImageObserver observer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean drawImage(final java.awt.Image img, final int dx1, final int dy1, final int dx2, final int dy2, final int sx1,
            final int sy1, final int sx2, final int sy2, final Color bgcolor, final ImageObserver observer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dispose() {
        throw new UnsupportedOperationException();
    }
    
    // CHECKSTYLEOFF Javadoc
    public void addSemanticData(KlighdSemanticDiagramData semanticData) {
    }

    public void startGroup(KlighdSemanticDiagramData semanticData) {
    }

    public void endGroup() {
    }
    
    /**
     * Set if this exporter can handle multiline strings themself or needs fallback
     * @param canHandleMultiline true if multiline strings can be coped with.
     */
    public void setCanHandleMultiline(boolean canHandleMultiline) {
        this.canHandleMultiline = canHandleMultiline;
    }
    
    /**
     * 
     * @return true if multiline strings can be coped with.
     */
    public boolean canHandleMultiline() {
        return this.canHandleMultiline;
    }
}
