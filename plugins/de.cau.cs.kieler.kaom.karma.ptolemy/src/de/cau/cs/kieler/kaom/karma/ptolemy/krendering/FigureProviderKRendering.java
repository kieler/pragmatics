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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.kaom.karma.ptolemy.krendering;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import org.eclipse.draw2d.Label;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.w3c.dom.Document;

import ptolemy.data.expr.XMLParser;
import ptolemy.vergil.icon.EditorIcon;
import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
import de.cau.cs.kieler.core.krendering.KAreaPlacementData;
import de.cau.cs.kieler.core.krendering.KBackground;
import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KForeground;
import de.cau.cs.kieler.core.krendering.KImage;
import de.cau.cs.kieler.core.krendering.KLineWidth;
import de.cau.cs.kieler.core.krendering.KPlacementData;
import de.cau.cs.kieler.core.krendering.KPolygon;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KRoundedRectangle;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.KXPosition;
import de.cau.cs.kieler.core.krendering.KYPosition;
import de.cau.cs.kieler.core.krendering.impl.KRenderingFactoryImpl;
import de.cau.cs.kieler.core.ui.util.CoreUiUtil;
import de.cau.cs.kieler.kaom.Entity;
import diva.canvas.CanvasUtilities;
import diva.canvas.Figure;
import diva.canvas.toolbox.ImageFigure;

public class FigureProviderKRendering {

    private KRenderingFactory factory = KRenderingFactoryImpl.eINSTANCE;
    
    /**
     * Creates a draw2d ImageFigure out of an ptolemy EditorIcon.
     * 
     * @param icon
     *            the EditorIcon to display in draw2d
     * @return draw2d Figure representing the EditorIcon
     */
    public KRendering createFigureFromIcon(final EditorIcon icon) {
        Figure shape = icon.createBackgroundFigure();
        Image img;
        img = getImageFromFigure(shape);
        BufferedImage resizedImage = new BufferedImage(img.getWidth(null), img.getHeight(null),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        org.eclipse.swt.graphics.Image image = new org.eclipse.swt.graphics.Image(
                Display.getCurrent(), CoreUiUtil.convertAWTImageToSWT(resizedImage));
        /*
        ImageFigureEx fig = new ImageFigureEx(image);
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        fig.setMinimumSize(size.getCopy());
        fig.setPreferredSize(size.getCopy());
        fig.getBounds().setSize(size.getCopy());
        fig.setSize(size.getCopy());
        */
        KImage figure = factory.createKImage();
        figure.setImageObject(image);
        Rectangle rec = image.getBounds();
        
        KAreaPlacementData placement = factory.createKAreaPlacementData();

        KPosition topleft = makeTopLeftKPosition(0, 0);
       
        ImageData data = image.getImageData();
        KPosition bottomright = makeBottomRightKPosition(rec.width, rec.height);

        placement.setTopLeft(topleft);
        placement.setBottomRight(bottomright);
        /*
        KGridPlacementData placement = factory.createKGridPlacementData();
        placement.setHeightHint(rec.height);
        placement.setWidthHint(rec.width);
        */
        figure.setPlacementData(placement);
        
        
        return figure;
    }

    /**
     * Create a figure for an entity representing an ptolemy input port.
     * 
     * @return the input port figure
     */
    public KRendering createInputPort() {
        KPolygon figure = factory.createKPolygon();
        
        //KPolylinePlacementData placement = factory.createKPolylinePlacementData();
        //thats how ptolemy ports look like. no use for constants
        // CHECKSTYLEOFF MagicNumber
        figure.getPoints().add(makeTopLeftKPosition(0, 4));
        figure.getPoints().add(makeTopLeftKPosition(0, 14));
        figure.getPoints().add(makeTopLeftKPosition(10, 14));
        figure.getPoints().add(makeTopLeftKPosition(10, 18));
        figure.getPoints().add(makeTopLeftKPosition(18, 9));
        figure.getPoints().add(makeTopLeftKPosition(10, 0));
        figure.getPoints().add(makeTopLeftKPosition(10, 4));
        figure.getPoints().add(makeTopLeftKPosition(0, 4));
       // figure.setPlacementData(placement);
        // CHECKSTYLEON MagicNumber
        
       
        KBackground background = factory.createKBackground();
        KColor backgroundColor = factory.createKColor();
        backgroundColor = FigureParserKRendering.lookupColor("black", backgroundColor);
        background.setColor(backgroundColor);
        figure.getStyles().add(background);
        return figure;
        // return this.getDefaultFigure();
    }

    /**
     * Create a figure for an entity representing an ptolemy output port.
     * 
     * @return the output port figure
     */
    public KRendering createOutputPort() {
        KPolygon figure = factory.createKPolygon();

//        KPolylinePlacementData placement = factory.createKPolylinePlacementData();

        //thats how ptolemy ports look like. no use for constants
        // CHECKSTYLEOFF MagicNumber
        figure.getPoints().add(makeTopLeftKPosition(0, 0));
        figure.getPoints().add(makeTopLeftKPosition(0, 18));
        figure.getPoints().add(makeTopLeftKPosition(4, 14));
        figure.getPoints().add(makeTopLeftKPosition(18, 14));
        figure.getPoints().add(makeTopLeftKPosition(18, 4));
        figure.getPoints().add(makeTopLeftKPosition(4, 4));
        figure.getPoints().add(makeTopLeftKPosition(0, 0));
        // CHECKSTYLEON MagicNumber
       // figure.setPlacementData(placement);
        
        KBackground background = factory.createKBackground();
        KColor backgroundColor = factory.createKColor();
        backgroundColor = FigureParserKRendering.lookupColor("black", backgroundColor);
        background.setColor(backgroundColor);
        figure.getStyles().add(background);
        
        return figure;
    }

    private KPosition makeTopLeftKPosition(final float x, final float y) {
        KRenderingFactory factory = KRenderingFactoryImpl.eINSTANCE;
        KPosition p = factory.createKPosition();
        KXPosition xp = factory.createKLeftPosition();
        xp.setAbsolute(x);
        KYPosition yp = factory.createKTopPosition();
        yp.setAbsolute(y);
        p.setX(xp);
        p.setY(yp);
        return p;
    }

    private KPosition makeBottomRightKPosition(final float x, final float y) {
        KPosition p = factory.createKPosition();
        KXPosition xp = factory.createKLeftPosition();
        xp.setAbsolute(x);
        KYPosition yp = factory.createKTopPosition();
        yp.setAbsolute(y);
        p.setX(xp);
        p.setY(yp);
        return p;
    }
    
    /**
     * Create a draw2d figure out of an svg Document. FigureParser.createFigure does the actual
     * work.
     * 
     * @param doc
     *            the Document holding the svg description
     * @return the figure representing the svg
     */
    public KRendering createFigureFromSvg(final Document doc) {
        return FigureParserKRendering.createFigure(doc);
    }

    /**
     * builds a default figure for this diagram.
     * 
     * @return the default figure
     */
    public KRendering getDefaultFigure() {
        KRectangle figure = factory.createKRectangle();
        
        KBackground fill = factory.createKBackground();
        KColor fillColor = factory.createKColor();
        fillColor = FigureParserKRendering.lookupColor("white", fillColor);
        fill.setColor(fillColor);
        KForeground stroke = factory.createKForeground();
        KColor strokeColor = factory.createKColor();
        strokeColor = FigureParserKRendering.lookupColor("white", strokeColor);
        fill.setColor(strokeColor);
        figure.getStyles().add(fill);
        figure.getStyles().add(stroke);
        
        KLineWidth strokeWidth = factory.createKLineWidth();
        strokeWidth.setLineWidth(1);
        figure.getStyles().add(strokeWidth);
        return figure;
    }

    /**
     * method for generating a scalable image figure from a file.
     * 
     * @param svgString
     *            the string representing the svg image
     * @return a scalable image figure
     */
    private KRendering createSvg(final String svgString) {
        try {
            XMLParser xmlpars = new XMLParser();
            Document doc;
            doc = xmlpars.parser(svgString);
            return createFigureFromSvg(doc);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Gets an awt image out of a diva figure.
     * 
     * @param figure
     *            diva figure holding an image
     * @return the awt image of the diva figure
     */
    private Image getImageFromFigure(final Figure figure) {
        // if its an ImageFigure use that image.
        if (figure instanceof ImageFigure) {
            ImageFigure imageFigure = (ImageFigure) figure;
            Image image = imageFigure.getImage();
            if (image != null) {
                image = image.getScaledInstance(image.getWidth(null), image.getHeight(null),
                        Image.SCALE_DEFAULT);
                return image;
            } else {
                throw new NullPointerException("Failed to get an image from " + imageFigure);
            }
            // if its something else try to get some swt graphics stuff and make an image out of
            // that.
        } else {
            Rectangle2D bounds = figure.getBounds();
            Rectangle2D size = new Rectangle2D.Double(0, 0, figure.getBounds().getWidth(), figure
                    .getBounds().getHeight());
            AffineTransform transform = CanvasUtilities.computeFitTransform(bounds, size);
            figure.transform(transform);

            BufferedImage image = new BufferedImage((int) figure.getBounds().getWidth(),
                    (int) figure.getBounds().getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics = image.createGraphics();

            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            // White background since draw2 can't handle transparency well
            graphics.setBackground(new Color(255, 255, 255, 255));
            graphics.clearRect(0, 0, (int) figure.getBounds().getWidth(), (int) figure.getBounds()
                    .getHeight());
            figure.paint(graphics);
            return image;
        }
    }

    /**
     * Method holding a figure description used by directors.
     * 
     * @return a figure represention an director
     */
    public KRendering createDirector() {
        KRectangle figure = factory.createKRectangle();
        
        KBackground fill = factory.createKBackground();
        KColor fillColor = factory.createKColor();
        fillColor = FigureParserKRendering.lookupColor("green", fillColor);
        fill.setColor(fillColor);
        KForeground stroke = factory.createKForeground();
        KColor strokeColor = factory.createKColor();
        strokeColor = FigureParserKRendering.lookupColor("black", strokeColor);
        stroke.setColor(strokeColor);
        
        figure.getStyles().add(fill);
        figure.getStyles().add(stroke);
        KAreaPlacementData placement = factory.createKAreaPlacementData();

        KPosition topleft = makeTopLeftKPosition(0, 0);

        KPosition bottomright = makeBottomRightKPosition(100, 30);

        placement.setTopLeft(topleft);
        placement.setBottomRight(bottomright);

        figure.setPlacementData(placement);

        return figure;
    }

    /**
     * A custom svg of an Accumulator since the ptolemy one is bugged.
     * 
     * @return a KRendering representing an Accumulator
     */
    public KRendering createAccumulator() {
        String accsvg = "<svg height=\"41\" width=\"41\" >"
                + "<rect height=\"40\" style=\"fill:white;stroke:black;stroke-width:1\" "
                + "width=\"40\" x=\"0.0\" y=\"0.0\" />"
                + "<path d=\"m 29.126953,6.2304687 0,3.0410157 -13.833984,0 8.789062,9.3515626 "
                + "-8.789062,10.335937 14.554687,0 0,3.041016 -18.246093,0 "
                + "0,-3.550781 8.419921,-9.826172 -8.419921,-8.9648439 0,-3.4277344 z\" />"
                + "</svg>";
        return createSvg(accsvg);
    }

    /**
     * Method for creating a custom monitorvalue figure.
     * 
     * @param object
     *            the modelelement
     * @return the monitorvalue figure
     */
    public KRendering createMonitorValue(final EObject object) {
        KRendering monitor = this.getDefaultFigure();
        KLineWidth strokeWidth = factory.createKLineWidth();
        strokeWidth.setLineWidth(1);
        monitor.getStyles().add(strokeWidth);
        
        KBackground fill = factory.createKBackground();
        KColor fillColor = factory.createKColor();
        fillColor = FigureParserKRendering.lookupColor("white", fillColor);
        fill.setColor(fillColor);
        KForeground stroke = factory.createKForeground();
        KColor strokeColor = factory.createKColor();
        strokeColor = FigureParserKRendering.lookupColor("white", strokeColor);
        fill.setColor(strokeColor);
        monitor.getStyles().add(fill);
        monitor.getStyles().add(stroke);
        
        return monitor;
    }

    // Position of the label inside the outer rectangle to make it centered somehow.
    private static final int LABELLOCATION_X = 5;
    private static final int LABELLOCATION_Y = 8;

    /**
     * A figure that displays a value.
     * 
     * @param object
     *            the model element
     * @param valueAttribute
     *            name of the annotation holding the value
     * @param part
     *            the editpart of the model element
     * @return a figure that display the given value
     */
    public KRendering createValueFigure(final EObject object, final String valueAttribute,
            final EditPart part) {
        KRectangle constFigure = (KRectangle) getDefaultFigure();
        if (object instanceof Annotatable) {
            KText valueLabel = factory.createKText();
            Label labelcache = new Label();
            if (!valueAttribute.equals("null")) {
                Annotation valueAnn = ((Annotatable) object).getAnnotation(valueAttribute);
                String value = ((StringAnnotation) valueAnn).getValue();
                valueLabel.setText(value);

                labelcache.setText(value);
                // Make a font. Without giving the label a font we can't calculate it size.
                FontData fd = new FontData();
                fd.setStyle(SWT.NORMAL);
                fd.setHeight(10);
                Font font = new Font(PlatformUI.getWorkbench().getDisplay(), fd);
                labelcache.setFont(font);

                KAreaPlacementData labelplacement = factory.createKAreaPlacementData();

                KPosition topleft = makeTopLeftKPosition(LABELLOCATION_X, LABELLOCATION_Y);

                KPosition bottomright = makeBottomRightKPosition(LABELLOCATION_X
                        + labelcache.getTextBounds().getSize().width, LABELLOCATION_Y
                        + labelcache.getTextBounds().getSize().height);

                labelplacement.setTopLeft(topleft);
                labelplacement.setBottomRight(bottomright);

                valueLabel.setPlacementData(labelplacement);

                constFigure.getChildren().add(valueLabel);
            }
            // make the size of the outer rectangle a bit bigger than the label to have a nice
            // border

            KAreaPlacementData placement = factory.createKAreaPlacementData();

            KPosition topleft = makeTopLeftKPosition(0, 0);

            KPosition bottomright = makeBottomRightKPosition(LABELLOCATION_X
                    + labelcache.getTextBounds().getSize().width + 10, LABELLOCATION_Y
                    + labelcache.getTextBounds().getSize().height + 10);

            placement.setTopLeft(topleft);
            placement.setBottomRight(bottomright);

            constFigure.setPlacementData(placement);

        }
        return constFigure;

    }

    /**
     * A figure to display if things go haywire.
     * 
     * @return a red box with a questionmark in it
     */
    public KRendering getErrorFigure() {
        
        KRectangle errorFigure = factory.createKRectangle();
        
        KBackground fill = factory.createKBackground();
        KForeground stroke = factory.createKForeground();
        KColor strokeColor = factory.createKColor();
        KColor fillColor = factory.createKColor();
        strokeColor = FigureParserKRendering.lookupColor("black", strokeColor);
        stroke.setColor(strokeColor);
        fillColor = FigureParserKRendering.lookupColor("red", fillColor);
        fill.setColor(fillColor);
        errorFigure.getStyles().add(fill);
        errorFigure.getStyles().add(stroke);

        KAreaPlacementData placement = factory.createKAreaPlacementData();

        KPosition topleft = makeTopLeftKPosition(0, 0);

        KPosition bottomright = makeBottomRightKPosition(50, 50);

        placement.setTopLeft(topleft);
        placement.setBottomRight(bottomright);

        errorFigure.setPlacementData(placement);

        KText errorLabel = factory.createKText();
        errorLabel.setText("?");
        KAreaPlacementData labelPlacement = factory.createKAreaPlacementData();

        KPosition labeltopleft = makeTopLeftKPosition(20, 15);

        KPosition labelbottomright = makeBottomRightKPosition(30, 25);

        placement.setTopLeft(labeltopleft);
        placement.setBottomRight(labelbottomright);

        errorFigure.setPlacementData(labelPlacement);
        errorFigure.getChildren().add(errorLabel);
        return errorFigure;
    }

    
    
    
    private static final float PORT_SIZE_LONG_SIDE = 7;
    private static final float PORT_SIZE_SHORT_SIDE = 3.5f;
    //private static final int PORT_SIZE_BOUNDS = 8;
    
    public KRendering getPortKRendering(final KColor color, float xoffset, float yoffset, String input) {
        KPolygon figure = factory.createKPolygon();
        
        //KPolylinePlacementData placement = factory.createKPolylinePlacementData();
        
        if (input.equals("SOUTH")) {
            figure.getPoints().add(createKPosition(0 + xoffset, PORT_SIZE_LONG_SIDE + yoffset));
            figure.getPoints().add(createKPosition(PORT_SIZE_LONG_SIDE + xoffset, PORT_SIZE_LONG_SIDE + yoffset));
            figure.getPoints().add(createKPosition(PORT_SIZE_SHORT_SIDE + xoffset, 0 + yoffset));
            figure.getPoints().add(createKPosition(0 + xoffset, PORT_SIZE_LONG_SIDE + yoffset));
        } else if (input.equals("NORTH")) {
            figure.getPoints().add(createKPosition(0 + xoffset, 0 + yoffset));
            figure.getPoints().add(createKPosition(PORT_SIZE_LONG_SIDE + xoffset, 0 + yoffset));
            figure.getPoints().add(createKPosition(PORT_SIZE_SHORT_SIDE + xoffset, PORT_SIZE_LONG_SIDE + yoffset));
            figure.getPoints().add(createKPosition(0 + xoffset, 0 + yoffset));
        } else {
            figure.getPoints().add(createKPosition(0 + xoffset, PORT_SIZE_LONG_SIDE + yoffset));
            figure.getPoints().add(createKPosition(0 + xoffset, 0 + yoffset));
            figure.getPoints().add(createKPosition(PORT_SIZE_LONG_SIDE + xoffset, PORT_SIZE_SHORT_SIDE + yoffset));
            figure.getPoints().add(createKPosition(0 + xoffset, PORT_SIZE_LONG_SIDE + yoffset));
        }
        
        

        //figure.setPlacementData(placement);
        KBackground fill = factory.createKBackground();
        KForeground stroke = factory.createKForeground();
        KColor strokeColor = factory.createKColor();
        strokeColor = FigureParserKRendering.lookupColor("black", strokeColor);
        stroke.setColor(strokeColor);
        fill.setColor(color);
        figure.getStyles().add(fill);
        figure.getStyles().add(stroke);
        
        //figure.setLineWidth(1);
        //figure.getBounds().setSize(PORT_SIZE_BOUNDS, PORT_SIZE_BOUNDS);
        return figure;
    }

    private KPosition createKPosition(final float x,final float y) {
        KPosition position = factory.createKPosition();
        KXPosition xp = factory.createKLeftPosition();
        xp.setAbsolute(x);
        KYPosition yp = factory.createKTopPosition();
        yp.setAbsolute(y);
        position.setX(xp);
        position.setY(yp);
        return position;
    }
    
    public KRendering createRelation() {
        //KPlacementData d;
        KPolygon relation = factory.createKPolygon();
        
        //KPolylinePlacementData placement = factory.createKPolylinePlacementData();
        relation.getPoints().add(createKPosition(5, 0));
        relation.getPoints().add(createKPosition(10, 5));
        relation.getPoints().add(createKPosition(5, 10));
        relation.getPoints().add(createKPosition(0, 5));
        relation.getPoints().add(createKPosition(5, 0));
        
        
        //relation.setPlacementData(placement);
        
        KBackground fill = factory.createKBackground();
        KColor fillColor = factory.createKColor();
        fillColor = FigureParserKRendering.lookupColor("black", fillColor);
        fill.setColor(fillColor);
        relation.getStyles().add(fill);
        
        return relation;
        
    }
    
    public KRendering createStateRendering(final Entity entity) {
        Annotation isfinal = entity.getAnnotation("isFinalState");
        Annotation isinitial = entity.getAnnotation("isInitialState");
        KRoundedRectangle figure = factory.createKRoundedRectangle();
        
        figure.setCornerHeight(30);
        figure.setCornerWidth(30);
        KAreaPlacementData placement = factory.createKAreaPlacementData();

        KPosition topleft = makeTopLeftKPosition(0, 0);

        KPosition bottomright = makeBottomRightKPosition(30, 30);

        placement.setTopLeft(topleft);
        placement.setBottomRight(bottomright);

        figure.setPlacementData(placement);

        if (isfinal != null && isinitial != null) {
            KRoundedRectangle innerfigure = factory.createKRoundedRectangle();
            
            innerfigure.setCornerHeight(22);
            innerfigure.setCornerWidth(22);
            KAreaPlacementData innerplacement = factory.createKAreaPlacementData();

            KPosition innertopleft = makeTopLeftKPosition(3, 3);

            KPosition innerbottomright = makeBottomRightKPosition(27, 27);

            innerplacement.setTopLeft(innertopleft);
            innerplacement.setBottomRight(innerbottomright);

            innerfigure.setPlacementData(innerplacement);
            
            figure.getChildren().add(innerfigure);
            
            KLineWidth linewidth = factory.createKLineWidth();
            linewidth.setLineWidth(4);
            figure.getStyles().add(linewidth);
            KLineWidth innerlinewidth = factory.createKLineWidth();
            innerlinewidth.setLineWidth(4);
            innerfigure.getStyles().add(innerlinewidth);
        } else if (isfinal != null) {
            
            KRoundedRectangle innerfigure = factory.createKRoundedRectangle();
            
            innerfigure.setCornerHeight(22);
            innerfigure.setCornerWidth(22);
            KAreaPlacementData innerplacement = factory.createKAreaPlacementData();

            KPosition innertopleft = makeTopLeftKPosition(3, 3);

            KPosition innerbottomright = makeBottomRightKPosition(27, 27);

            innerplacement.setTopLeft(innertopleft);
            innerplacement.setBottomRight(innerbottomright);

            innerfigure.setPlacementData(innerplacement);
            
            figure.getChildren().add(innerfigure);
            
            KLineWidth linewidth = factory.createKLineWidth();
            linewidth.setLineWidth(1);
            figure.getStyles().add(linewidth);
            KLineWidth innerlinewidth = factory.createKLineWidth();
            innerlinewidth.setLineWidth(1);
            innerfigure.getStyles().add(innerlinewidth);
        } else if (isinitial != null) {
            KLineWidth linewidth = factory.createKLineWidth();
            linewidth.setLineWidth(4);
            figure.getStyles().add(linewidth);
        } else {
            KLineWidth linewidth = factory.createKLineWidth();
            linewidth.setLineWidth(1);
            figure.getStyles().add(linewidth);
        }
        
        return figure;
    }
    
}
