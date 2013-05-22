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
package de.cau.cs.kieler.kaom.klighd.ptolemy;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

import org.eclipse.draw2d.ColorConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.cau.cs.kieler.core.krendering.KAreaPlacementData;
import de.cau.cs.kieler.core.krendering.KBackground;
import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KContainerRendering;
import de.cau.cs.kieler.core.krendering.KEllipse;
import de.cau.cs.kieler.core.krendering.KFontSize;
import de.cau.cs.kieler.core.krendering.KForeground;
import de.cau.cs.kieler.core.krendering.KInvisibility;
import de.cau.cs.kieler.core.krendering.KLineWidth;
import de.cau.cs.kieler.core.krendering.KPointPlacementData;
import de.cau.cs.kieler.core.krendering.KPolygon;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.KXPosition;
import de.cau.cs.kieler.core.krendering.KYPosition;
import de.cau.cs.kieler.core.krendering.impl.KRenderingFactoryImpl;

/**
 * Class for creating krenderings out of ptolemy svg figures.
 * 
 * @author ckru
 * 
 */
public class FigureParserKRendering {
    /**
     * This is a utility class and thus the constructor is hidden.
     */
    private FigureParserKRendering() {

    }

    /**
     * Create a krendering figure out of an svg document.
     * 
     * @param doc
     *            the svg document
     * @return the krendering equivalent to the svg
     */
    public static KRendering createFigure(final Document doc) {
        Element svgElement = (Element) doc.getElementsByTagName("svg").item(0);
        KRenderingFactory factory = KRenderingFactoryImpl.eINSTANCE;
        // Make an invisible container to hold the visible figures because we don't know the
        // structure of the svg.
        KContainerRendering rootFigure = factory.createKRectangle();
        
        KInvisibility invisibility = factory.createKInvisibility();//.createKBackgroundVisibility();
        invisibility.setInvisible(false);//.setVisible(false);
        //KVisibility foregroundVisibility = factory.createKForegroundVisibility();
        //foregroundVisibility.setVisible(false);
        
        //rootFigure.getStyles().add(invisibility);
        
        //rootFigure.getStyles().add(foregroundVisibility);
        
        // IFigure rootFigure = new Panel();
        KAreaPlacementData placement = factory.createKAreaPlacementData();
        KPosition topleft = factory.createKPosition();
        KXPosition left = factory.createKLeftPosition();
        left.setAbsolute(0);
        topleft.setX(left);
        KYPosition top = factory.createKTopPosition();
        top.setAbsolute(0);
        topleft.setY(top);
        KPosition bottomright = factory.createKPosition();
        KXPosition right = factory.createKLeftPosition();
        right.setAbsolute(Float.parseFloat(svgElement.getAttribute("width")));
        bottomright.setX(right);
        KYPosition bottom = factory.createKTopPosition();
        bottom.setAbsolute(Float.parseFloat(svgElement.getAttribute("height")));
        bottomright.setY(bottom);
        placement.setTopLeft(topleft);
        placement.setBottomRight(bottomright);
        //placement.setHeightHint(Float.parseFloat(svgElement.getAttribute("height")));
        //placement.setWidthHint(Float.parseFloat(svgElement.getAttribute("width")));
        rootFigure.setPlacementData(placement);
        //rootFigure.setChildPlacement(factory.createKStackPlacement());
        rootFigure = buildFigure(svgElement, rootFigure);
        return rootFigure;
    }

    /**
     * Parsing the svg and creating krenderings accordingly. Builds a hirachical structure.
     * 
     * @param root
     *            the top level svg element.
     * @param parentFigure
     *            an invisible krendering as container for the actual figures
     * @return a hirachical krendering representing the svg
     */
    private static KContainerRendering buildFigure(final Element root,
            final KContainerRendering parentFigure) {
        NodeList childList = root.getChildNodes();
        KRenderingFactory factory = KRenderingFactoryImpl.eINSTANCE;
        for (int i = 0; i < childList.getLength(); i++) {
            Node child = childList.item(i);
            if (child instanceof Element) {
                Element childElement = (Element) child;
                String tag = childElement.getTagName();
                // make a RectangleFigure from a rectangle element
                if (tag.equals("rect")) {
                    KRectangle figure = factory.createKRectangle();
                    KXPosition left = factory.createKLeftPosition();
                    left.setAbsolute(Float.parseFloat(childElement.getAttribute("x")));

                    KYPosition top = factory.createKTopPosition();
                    top.setAbsolute(Float.parseFloat(childElement.getAttribute("y")));

                    KXPosition right = factory.createKLeftPosition();
                    right.setAbsolute(left.getAbsolute()
                            + Float.parseFloat(childElement.getAttribute("width")));

                    KYPosition bottom = factory.createKTopPosition();
                    bottom.setAbsolute(top.getAbsolute()
                            + Float.parseFloat(childElement.getAttribute("height")));

                    String style = (String) childElement.getAttribute("style");

                    KAreaPlacementData placement = factory.createKAreaPlacementData();

                    KPosition topleft = factory.createKPosition();
                    topleft.setX(left);
                    topleft.setY(top);

                    KPosition bottomright = factory.createKPosition();
                    bottomright.setX(right);
                    bottomright.setY(bottom);

                    placement.setTopLeft(topleft);
                    placement.setBottomRight(bottomright);

                    figure.setPlacementData(placement);
                    applyStyle(figure, style);
                    parentFigure.getChildren().add(buildFigure(childElement, figure));
                    // make a CircleFigure from a circle element.
                    // structure is different between draw2d and svg so positions are a bit hacked
                } else if (tag.equals("circle")) {

                    KEllipse figure = factory.createKEllipse();

                    float radius = Float.parseFloat(childElement.getAttribute("r"));
                    
                    KXPosition left = factory.createKLeftPosition();
                    left.setAbsolute((Float.parseFloat(childElement.getAttribute("cx")) - radius));

                    KYPosition top = factory.createKTopPosition();
                    top.setAbsolute((Float.parseFloat(childElement.getAttribute("cy")) - radius));

                    KXPosition right = factory.createKLeftPosition();
                    right.setAbsolute(left.getAbsolute()
                            + (radius * 2));

                    KYPosition bottom = factory.createKTopPosition();
                    bottom.setAbsolute(top.getAbsolute()
                            + (radius * 2));

                    
                    String style = (String) childElement.getAttribute("style");

                    KAreaPlacementData placement = factory.createKAreaPlacementData();

                    KPosition topleft = factory.createKPosition();
                    topleft.setX(left);
                    topleft.setY(top);

                    KPosition bottomright = factory.createKPosition();
                    bottomright.setX(right);
                    bottomright.setY(bottom);

                    placement.setTopLeft(topleft);
                    placement.setBottomRight(bottomright);

                    figure.setPlacementData(placement);
                    applyStyle(figure, style);
                    parentFigure.getChildren().add(buildFigure(childElement, figure));
                    // make a CircleFigure from a ellipse element.
                    // structure is different between draw2d and svg so positions are a bit hacked
                } else if (tag.equals("ellipse")) {
                    KEllipse figure = factory.createKEllipse();

                    float xradius = Float.parseFloat(childElement.getAttribute("rx"));
                    float yradius = Float.parseFloat(childElement.getAttribute("ry"));
                    
                    KXPosition left = factory.createKLeftPosition();
                    left.setAbsolute(Float.parseFloat(childElement.getAttribute("cx")) - xradius);

                    KYPosition top = factory.createKTopPosition();
                    top.setAbsolute(Float.parseFloat(childElement.getAttribute("cy")) - yradius);

                    KXPosition right = factory.createKLeftPosition();
                    right.setAbsolute(left.getAbsolute()
                            + (xradius * 2));

                    KYPosition bottom = factory.createKTopPosition();
                    bottom.setAbsolute(top.getAbsolute()
                            + (yradius * 2));

                    String style = (String) childElement.getAttribute("style");

                    KAreaPlacementData placement = factory.createKAreaPlacementData();

                    KPosition topleft = factory.createKPosition();
                    topleft.setX(left);
                    topleft.setY(top);

                    KPosition bottomright = factory.createKPosition();
                    bottomright.setX(right);
                    bottomright.setY(bottom);

                    placement.setTopLeft(topleft);
                    placement.setBottomRight(bottomright);
                    figure.setPlacementData(placement);
                    applyStyle(figure, style);
                    parentFigure.getChildren().add(buildFigure(childElement, figure));
                    // make a PolyLineShape from a line element
                } else if (tag.equals("line")) {

                    KPolyline figure = factory.createKPolyline();

                    KXPosition x1 = factory.createKLeftPosition();
                    x1.setAbsolute(Float.parseFloat(childElement.getAttribute("x1")));

                    KYPosition y1 = factory.createKTopPosition();
                    y1.setAbsolute(Float.parseFloat(childElement.getAttribute("y1")));

                    KXPosition x2 = factory.createKLeftPosition();
                    x2.setAbsolute(Float.parseFloat(childElement.getAttribute("x2")));

                    KYPosition y2 = factory.createKTopPosition();
                    y2.setAbsolute(Float.parseFloat(childElement.getAttribute("y2")));

                    String style = (String) childElement.getAttribute("style");

                    
                    //KPointPlacementData placement = factory.createKPointPlacementData();//createKPolylinePlacementData();

                    KPosition p1 = factory.createKPosition();
                    p1.setX(x1);
                    p1.setY(y1);

                    KPosition p2 = factory.createKPosition();
                    p2.setX(x2);
                    p2.setY(y2);
                    
                    //placement.getPoints().add(p1);
                    //placement.getPoints().add(p2);
                    figure.getPoints().add(p1);
                    figure.getPoints().add(p2);
                    //figure.setPlacementData(placement);
                    applyStyle(figure, style);
                    
                    
                    parentFigure.getChildren().add(buildFigure(childElement, figure));
                    // make a PolylineShape from a polyline element.
                } else if (tag.equals("polyline")) {
                    String allpoints = childElement.getAttribute("points");
                    String style = (String) childElement.getAttribute("style");
                    String[] pointsarray = allpoints.split(" +");
                    //KPolylinePlacementData placement = factory.createKPolylinePlacementData();
                    KPolyline figure = factory.createKPolyline();
                    for (String coords : pointsarray) {
                        String[] coordsarray = coords.split(",");

                        KXPosition x = factory.createKLeftPosition();
                        x.setAbsolute(Float.parseFloat(coordsarray[0]));

                        KYPosition y = factory.createKTopPosition();
                        y.setAbsolute(Float.parseFloat(coordsarray[1]));

                        KPosition p = factory.createKPosition();
                        p.setX(x);
                        p.setY(y);

                        figure.getPoints().add(p);
                        //placement.getPoints().add(p);
                    }
                    
                    //figure.setPlacementData(placement);
                    applyStyle(figure, style);
                    parentFigure.getChildren().add(buildFigure(childElement, figure));

                    // make a PolygonShape from a polygon element
                } else if (tag.equals("polygon")) {
                    String allpoints = childElement.getAttribute("points");
                    String style = (String) childElement.getAttribute("style");
                    String[] pointsarray = allpoints.split(" +");
                    //KPolylinePlacementData placement = factory.createKPolylinePlacementData();

                    KPolygon figure = factory.createKPolygon();
                    
                    for (String coords : pointsarray) {
                        String[] coordsarray = coords.split(",");

                        KXPosition x = factory.createKLeftPosition();
                        x.setAbsolute(Float.parseFloat(coordsarray[0]));

                        KYPosition y = factory.createKTopPosition();
                        y.setAbsolute(Float.parseFloat(coordsarray[1]));

                        KPosition p = factory.createKPosition();
                        p.setX(x);
                        p.setY(y);

                        figure.getPoints().add(p);
                        //placement.getPoints().add(p);
                    }
                    

                    //figure.setPlacementData(placement);
                    applyStyle(figure, style);
                    parentFigure.getChildren().add(buildFigure(childElement, figure));

                    // make a Label from a text element
                    // TODO weird behavior of y value
                } else if (tag.equals("text")) {
                    KXPosition x = factory.createKLeftPosition();
                    x.setAbsolute(Float.parseFloat(childElement.getAttribute("x")));

                    KYPosition y = factory.createKTopPosition();
                    y.setAbsolute(Float.parseFloat(childElement.getAttribute("y")));

                    String style = (String) childElement.getAttribute("style");
                    String text = childElement.getTextContent();
                    text = text.replaceAll("\n", "");
                    text = text.trim();

                    KText figure = factory.createKText();
                    figure.setText(text);
                    
                    KPointPlacementData placement = factory.createKPointPlacementData();

                    KPosition topleft = factory.createKPosition();
                    topleft.setX(x);
                    topleft.setY(y);
                    //System.out.println("???????????" + text);
                    placement.setReferencePoint(topleft);

                    
                    applyTextStyle(figure, style, placement);

                                        // KPosition bottomright = factory.createKPosition();
                    // bottomright.setX(right);
                    // bottomright.setY(bottom);
                    figure.setPlacementData(placement);
                    /*
                     * figure.getBounds() .setLocation( new PrecisionPoint(x, y -
                     * (figure.getTextBounds().getSize().height - 2)));
                     * figure.getBounds().setSize(figure.getTextBounds().getSize());
                     */
                    // figure.setLayoutManager(new BorderLayout());
                    parentFigure.getChildren().add(figure);
                    // make an ImageFigureEx out of an image element
                } else if (tag.equals("image")) {
                    KXPosition left = factory.createKLeftPosition();
                    left.setAbsolute(Float.parseFloat(childElement.getAttribute("x")));

                    KYPosition top = factory.createKTopPosition();
                    top.setAbsolute(Float.parseFloat(childElement.getAttribute("y")));

                    KXPosition right = factory.createKLeftPosition();
                    right.setAbsolute(left.getAbsolute()
                            + Float.parseFloat(childElement.getAttribute("width")));

                    KYPosition bottom = factory.createKTopPosition();
                    bottom.setAbsolute(top.getAbsolute()
                            + Float.parseFloat(childElement.getAttribute("height")));

                    String link = (String) childElement.getAttribute("xlink:href");
                    //String style = (String) childElement.getAttribute("style");
                    URL url = ClassLoader.getSystemResource(link);
                    if (url == null) {
                        try {
                            url = new URL(link);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                    // Image img = null;
                    KRectangle figure = null;
                    // try {
                    // img = new Image(null, url.openStream());
                    figure = factory.createKRectangle();//factory.createKImage();
                    //figure.setImagePath(url.getPath());
                    // } catch (IOException e) {
                    
                    // e.printStackTrace();
                    // }

                    KAreaPlacementData placement = factory.createKAreaPlacementData();

                    KPosition topleft = factory.createKPosition();
                    topleft.setX(left);
                    topleft.setY(top);

                    KPosition bottomright = factory.createKPosition();
                    bottomright.setX(right);
                    bottomright.setY(bottom);
                    
                    figure.setPlacementData(placement);
                    
                    /*
                    applyStyle(figure, style);
                    */
                    parentFigure.getChildren().add(buildFigure(childElement, figure));

                }

            }
        }
        return parentFigure;
    }

    /**
     * Applys the style attribute of the svg element to the krendering.
     * 
     * @param figure
     *            the krendering whose style to set
     * @param style
     *            the style as a string
     */
    private static KContainerRendering applyStyle(final KContainerRendering figure,
            final String style) {
        KRenderingFactory factory = KRenderingFactoryImpl.eINSTANCE;
        if (style != null) {
            StringTokenizer t = new StringTokenizer(style, ";");

            while (t.hasMoreTokens()) {
                String string = t.nextToken().trim();
                int index = string.indexOf(":");
                String name = string.substring(0, index);
                String value = string.substring(index + 1);
                // fill might be background, stroke foreground. Works fine so far.
                if (name.equals("fill")) {
                    KBackground fill = factory.createKBackground();
                    KColor color = factory.createKColor();
                    color = lookupColor(value, color);
                    fill.setColor(color);
                    figure.getStyles().add(fill);
                } else if (name.equals("stroke")) {
                    KForeground stroke = factory.createKForeground();
                    KColor color = factory.createKColor();
                    color = lookupColor(value, color);
                    stroke.setColor(color);
                    figure.getStyles().add(stroke);
                } else if (name.equals("stroke-width")) {
                    Float width = Float.parseFloat(value);
                    KLineWidth strokeWidth = factory.createKLineWidth();
                    strokeWidth.setLineWidth(width.intValue());
                    figure.getStyles().add(strokeWidth);
                }
            }
        }
        return figure;
    }

    /**
     * Applys the style attribute of the svg element to the krendering.
     * 
     * @param figure
     *            the krendering whoose style to set
     * @param style
     *            the style as a string
     */
    private static KContainerRendering applyTextStyle(final KText figure, final String style, KPointPlacementData placement) {
        KRenderingFactory factory = KRenderingFactoryImpl.eINSTANCE;
        if (style != null) {
            StringTokenizer t = new StringTokenizer(style, ";");

            while (t.hasMoreTokens()) {
                String string = t.nextToken().trim();
                int index = string.indexOf(":");
                String name = string.substring(0, index);
                String value = string.substring(index + 1);
                // foreground color determines the text color
                if (name.equals("fill")) {
                   // KForegroundColor fill = factory.createKForegroundColor();
                    //figure.getStyles().add(lookupColor(value, fill));
                    // some hacked size stuff without having a fitting font.
                } else if (name.equals("font-size")) {
                    
                    int size = Integer.parseInt(value);
                    KFontSize fontSize = factory.createKFontSize();
                    fontSize.setSize(size);
                    figure.getStyles().add(fontSize);
                    
                    KPosition position = placement.getReferencePoint();
                    position.getY().setAbsolute(position.getY().getAbsolute() - size);
                    
                    /*
                     * if (figure instanceof Label) { FontData[] fonts =
                     * PlatformUI.getWorkbench().getDisplay() .getFontList("arial", true); FontData
                     * fd = fonts[0]; fd.setHeight(size - 2); Font font = new
                     * Font(PlatformUI.getWorkbench().getDisplay(), fd); ((Label)
                     * figure).setFont(font); }
                     */
                }
                // TODO set a font.Problem: Svg has an attribute that loosely describes the font
                // family.
                // This has to be mapped to existing fonts on a specific system.
            }
        }
        return null;//figure;
    }

    /**
     * Make a KRendering KColor object out of a color name.
     * 
     * @param color
     *            string representation of a color
     * @return the color described by the string. black if not found.
     */
    public static KColor lookupColor(final String color, final KColor kcolor) {
        String s = color.toLowerCase();
        if (s.equals("black")) {
            kcolor.setBlue(ColorConstants.black.getBlue());
            kcolor.setRed(ColorConstants.black.getRed());
            kcolor.setGreen(ColorConstants.black.getGreen());
            return kcolor;
        } else if (s.equals("blue")) {
            kcolor.setBlue(ColorConstants.blue.getBlue());
            kcolor.setRed(ColorConstants.blue.getRed());
            kcolor.setGreen(ColorConstants.blue.getGreen());
            return kcolor;
        } else if (s.equals("cyan")) {
            kcolor.setBlue(ColorConstants.cyan.getBlue());
            kcolor.setRed(ColorConstants.cyan.getRed());
            kcolor.setGreen(ColorConstants.cyan.getGreen());
            return kcolor;
        } else if (s.equals("darkgray") || s.equals("darkgrey")) {
            kcolor.setBlue(ColorConstants.darkGray.getBlue());
            kcolor.setRed(ColorConstants.darkGray.getRed());
            kcolor.setGreen(ColorConstants.darkGray.getGreen());
            return kcolor;
        } else if (s.equals("lightgray") || s.equals("lightgrey")) {
            kcolor.setBlue(ColorConstants.lightGray.getBlue());
            kcolor.setRed(ColorConstants.lightGray.getRed());
            kcolor.setGreen(ColorConstants.lightGray.getGreen());
            return kcolor;
        } else if (s.equals("gray") || s.equals("grey")) {
            kcolor.setBlue(ColorConstants.gray.getBlue());
            kcolor.setRed(ColorConstants.gray.getRed());
            kcolor.setGreen(ColorConstants.gray.getGreen());
            return kcolor;
        } else if (s.equals("green")) {
            kcolor.setBlue(ColorConstants.green.getBlue());
            kcolor.setRed(ColorConstants.green.getRed());
            kcolor.setGreen(ColorConstants.green.getGreen());
            return kcolor;
        } else if (s.equals("orange")) {
            kcolor.setBlue(ColorConstants.orange.getBlue());
            kcolor.setRed(ColorConstants.orange.getRed());
            kcolor.setGreen(ColorConstants.orange.getGreen());
            return kcolor;
        } else if (s.equals("red")) {
            kcolor.setBlue(ColorConstants.red.getBlue());
            kcolor.setRed(ColorConstants.red.getRed());
            kcolor.setGreen(ColorConstants.red.getGreen());
            return kcolor;
        } else if (s.equals("white")) {
            kcolor.setBlue(ColorConstants.white.getBlue());
            kcolor.setRed(ColorConstants.white.getRed());
            kcolor.setGreen(ColorConstants.white.getGreen());
            return kcolor;
        } else if (s.equals("yellow")) {
            kcolor.setBlue(ColorConstants.yellow.getBlue());
            kcolor.setRed(ColorConstants.yellow.getRed());
            kcolor.setGreen(ColorConstants.yellow.getGreen());
            return kcolor;
        } else {
            kcolor.setBlue(ColorConstants.black.getBlue());
            kcolor.setRed(ColorConstants.black.getRed());
            kcolor.setGreen(ColorConstants.black.getGreen());
            return kcolor;
        }
    }

}
