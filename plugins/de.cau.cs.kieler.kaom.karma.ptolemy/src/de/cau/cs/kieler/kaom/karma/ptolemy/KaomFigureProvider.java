/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kaom.karma.ptolemy;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.render.RenderedImage;
import org.eclipse.gmf.runtime.draw2d.ui.render.factory.RenderedImageFactory;
import org.eclipse.gmf.runtime.draw2d.ui.render.figures.ScalableImageFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.swt.widgets.Display;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ptolemy.data.expr.XMLParser;
import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.util.ConfigurableAttribute;
import ptolemy.kernel.util.NamedObj;
import ptolemy.moml.test.TestIconLoader;
import ptolemy.vergil.icon.EditorIcon;
import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.NamedObject;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
import de.cau.cs.kieler.core.ui.util.CoreUiUtil;
import de.cau.cs.kieler.karma.IRenderingProvider;
import de.cau.cs.kieler.kvid.KvidUtil;
import de.cau.cs.kieler.kvid.data.DataObject;
import de.cau.cs.kieler.kvid.data.KvidUri;
import de.cau.cs.kieler.kvid.datadistributor.DataDistributor;
import de.cau.cs.kieler.kvid.datadistributor.IDataListener;
import diva.canvas.CanvasUtilities;
import diva.canvas.Figure;
import diva.canvas.toolbox.ImageFigure;

/**
 * Karma rendering provider for rendering ptolemy diagrams in kaom.
 * 
 * @author ckru
 * 
 */
public class KaomFigureProvider implements IRenderingProvider {

    /**
     * {@inheritDoc}
     */
    public IFigure getFigureByString(final String input, final IFigure oldFigure,
            final EObject object, final EditPart part) {
        if (input.equals("_IconDescription")) {
            return createFigureFromIconDescription(object);
        } else if (input.equals("MonitorValue")) {
            return createMonitorValue(object);
        } else if (input.startsWith("valueDisplay")) {
            String[] parts = input.split("//");
            return createValueFigure(object, parts[1], part);
        } else if (input.equals("Director")) {
            return createDirector();
        } else if (input.equals("accumulator")) {
            return createAccumulator();
        } else if (input.equals("connection")) {
            if (oldFigure instanceof PolylineConnectionEx) {
                PolylineConnectionEx connection = ((PolylineConnectionEx) oldFigure);
                connection.setTargetDecoration(null);
                connection.setLineWidthFloat(1.5f);
                return oldFigure;
            } else {
                return null;
            }
        } else {
            return getDefaultFigure();
        }
    }

    /**
     * {@inheritDoc}
     */
    public static IFigure getDefaultFigure() {
        RectangleFigure defaultFigure = new RectangleFigure();
        defaultFigure.setLineWidth(1);
        defaultFigure.setForegroundColor(ColorConstants.black);
        defaultFigure.setBackgroundColor(ColorConstants.white);
        return defaultFigure;
    }

    /**
     * {@inheritDoc}
     */
    public LayoutManager getLayoutManagerByString(final String input,
            final LayoutManager oldLayoutManager, final EObject object) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public LayoutManager getDefaultLayoutManager() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public BorderItemLocator getBorderItemLocatorByString(final String input, final IFigure parent,
            final Object locator, final EObject object) {
        return null;
    }

    /**
     * method for generating a scalable image figure from a file.
     * 
     * @param svgString
     *            the string representing the svg image
     * @return a scalable image figure
     */
    private static IFigure createSvg(final String svgString) {
        RenderedImage img = RenderedImageFactory.getInstance(svgString.getBytes());
        ScalableImageFigure fig = new ScalableImageFigure(img, false, true, true);
        return fig;
    }

    /**
     * Helper method that adds necessary blanks to an svg description because ptolemy is bugged.
     * 
     * @param input
     *            the broken svg description
     * @return a repaired version of the input that an xml parser can understand.
     */
    private static String repairString(final String input) {
        String[] parts = input.split("\"");
        String output = "";
        for (int i = 0; i < parts.length; i += 2) {
            if (i < parts.length - 1) {
                output += parts[i] + "\"" + parts[i + 1] + "\" ";
            } else {
                output += parts[i];
            }
        }
        return output;
    }

    /**
     * Helper method for converting a ptolemy svg description to an svg description thats compatible
     * with the common svg standard.
     * 
     * @param svg
     *            the string describing an svg in xml
     * @return an svg description compatible with the svg standard
     */
    private static String repairSvg(final String svg) {
        try {
            String svgDescription = svg;
            XMLParser xmlpars = new XMLParser();
            Document doc;
            try {
                doc = xmlpars.parser(svgDescription);
            } catch (Exception e) {
                svgDescription = repairString(svgDescription);
                doc = xmlpars.parser(svgDescription);
            }
            Element svgElement = (Element) doc.getElementsByTagName("svg").item(0);
            NodeList nodeList = doc.getElementsByTagName("rect");
            int xoffset = 0;
            int yoffset = 0;
            if (nodeList.getLength() != 0) {
                Element rectElement = (Element) doc.getElementsByTagName("rect").item(0);

                svgElement.setAttribute("height",
                        String.valueOf(Integer.parseInt(rectElement.getAttribute("height")) + 1));
                svgElement.setAttribute("width",
                        String.valueOf(Integer.parseInt(rectElement.getAttribute("width")) + 1));

                xoffset = Math.abs(Integer.parseInt(rectElement.getAttribute("x")));
                yoffset = Math.abs(Integer.parseInt(rectElement.getAttribute("y")));
            } else {
                // TODO hacked else case, think of something better
                int childPointer = 0;
                Object object = svgElement.getChildNodes().item(childPointer);
                while (!(object instanceof Element) && (object != svgElement)) {
                    object = svgElement.getElementsByTagName("*").item(childPointer);
                }
                Element firstElement = (Element) object;
                if (firstElement.hasAttribute("points")) {
                    String points = firstElement.getAttribute("points");
                    String[] splittedPoints = points.split(" +");
                    String firstPoint = splittedPoints[0];
                    String[] firstPointCoords = firstPoint.split(",");
                    xoffset = Math.abs(Integer.parseInt(firstPointCoords[0]));
                    yoffset = Math.abs(Integer.parseInt(firstPointCoords[1]));
                    List<Integer> pointsX = new LinkedList<Integer>();
                    List<Integer> pointsY = new LinkedList<Integer>();
                    for (String singlePoint : splittedPoints) {
                        String[] pointCoord = singlePoint.split(",");
                        pointsX.add(Integer.parseInt(pointCoord[0]));
                        pointsY.add(Integer.parseInt(pointCoord[1]));
                    }
                    int maxX = Collections.max(pointsX);
                    int maxY = Collections.max(pointsY);
                    svgElement.setAttribute("height", String.valueOf(maxX + xoffset + 1));
                    svgElement.setAttribute("width", String.valueOf(maxY + yoffset + 1));
                }

            }
            for (int i = 0; i < doc.getElementsByTagName("rect").getLength(); i++) {
                Element e = (Element) doc.getElementsByTagName("rect").item(i);
                if (e.hasAttribute("x") && e.hasAttribute("y") && e.hasAttribute("style")) {
                    float x = Float.parseFloat(e.getAttribute("x"));
                    float y = Float.parseFloat(e.getAttribute("y"));
                    x += xoffset;
                    y += yoffset;
                    e.setAttribute("x", String.valueOf(x));
                    e.setAttribute("y", String.valueOf(y));
                    e.setAttribute("style",
                            e.getAttribute("style").concat(";stroke:black;stroke-width:1"));
                }
            }

            for (int i = 0; i < doc.getElementsByTagName("circle").getLength(); i++) {
                Element e = (Element) doc.getElementsByTagName("circle").item(i);
                if (e.hasAttribute("cx") && e.hasAttribute("cy")) {
                    float x = Float.parseFloat(e.getAttribute("cx"));
                    float y = Float.parseFloat(e.getAttribute("cy"));
                    x += xoffset;
                    y += yoffset;
                    e.setAttribute("cx", String.valueOf(x));
                    e.setAttribute("cy", String.valueOf(y));
                }
                e.setAttribute("style",
                        e.getAttribute("style").concat(";stroke:black;stroke-width:1"));
            }

            for (int i = 0; i < doc.getElementsByTagName("polygon").getLength(); i++) {
                Element e = (Element) doc.getElementsByTagName("polygon").item(i);
                if (e.hasAttribute("points")) {
                    String newpoints = "";
                    String allpoints = e.getAttribute("points");
                    String[] pointsarray = allpoints.split(" +");
                    for (String coords : pointsarray) {
                        String[] coordsarray = coords.split(",");
                        float x = Float.parseFloat(coordsarray[0]);
                        float y = Float.parseFloat(coordsarray[1]);
                        x += xoffset;
                        y += yoffset;
                        newpoints += String.valueOf(x) + "," + String.valueOf(y) + " ";

                    }
                    e.setAttribute("points", newpoints);
                    e.setAttribute("style",
                            e.getAttribute("style").concat(";stroke:black;stroke-width:1"));
                }
            }

            for (int i = 0; i < doc.getElementsByTagName("polyline").getLength(); i++) {
                Element e = (Element) doc.getElementsByTagName("polyline").item(i);
                if (e.hasAttribute("points")) {
                    String newpoints = "";
                    String allpoints = e.getAttribute("points");
                    String[] pointsarray = allpoints.split(" +");
                    for (String coords : pointsarray) {
                        String[] coordsarray = coords.split(",");
                        float x = Float.parseFloat(coordsarray[0]);
                        float y = Float.parseFloat(coordsarray[1]);
                        x += xoffset;
                        y += yoffset;
                        newpoints += String.valueOf(x) + "," + String.valueOf(y) + " ";

                    }
                    e.setAttribute("points", newpoints);
                }
            }

            for (int i = 0; i < doc.getElementsByTagName("line").getLength(); i++) {
                Element e = (Element) doc.getElementsByTagName("line").item(i);
                if (e.hasAttribute("x1") && e.hasAttribute("y1") && e.hasAttribute("x2")
                        && e.hasAttribute("y2")) {
                    float x1 = Float.parseFloat(e.getAttribute("x1"));
                    float y1 = Float.parseFloat(e.getAttribute("y1"));
                    float x2 = Float.parseFloat(e.getAttribute("x2"));
                    float y2 = Float.parseFloat(e.getAttribute("y2"));
                    x1 += xoffset;
                    y1 += yoffset;
                    x2 += xoffset;
                    y2 += yoffset;
                    e.setAttribute("x1", String.valueOf(x1));
                    e.setAttribute("y1", String.valueOf(y1));
                    e.setAttribute("x2", String.valueOf(x2));
                    e.setAttribute("y2", String.valueOf(y2));
                }
                e.setAttribute("style",
                        e.getAttribute("style").concat(";stroke:black;stroke-width:1"));
            }

            for (int i = 0; i < doc.getElementsByTagName("image").getLength(); i++) {
                Element e = (Element) doc.getElementsByTagName("image").item(i);
                if (e.hasAttribute("x") && e.hasAttribute("y")) {
                    float x = Float.parseFloat(e.getAttribute("x"));
                    float y = Float.parseFloat(e.getAttribute("y"));
                    x += xoffset;
                    y += yoffset;
                    e.setAttribute("x", String.valueOf(x));
                    e.setAttribute("y", String.valueOf(y));
                }
            }

            for (int i = 0; i < doc.getElementsByTagName("ellipse").getLength(); i++) {
                Element e = (Element) doc.getElementsByTagName("ellipse").item(i);
                if (e.hasAttribute("cx") && e.hasAttribute("cy")) {
                    float x = Float.parseFloat(e.getAttribute("cx"));
                    float y = Float.parseFloat(e.getAttribute("cy"));
                    x += xoffset;
                    y += yoffset;
                    e.setAttribute("cx", String.valueOf(x));
                    e.setAttribute("cy", String.valueOf(y));
                }
            }

            for (int i = 0; i < doc.getElementsByTagName("text").getLength(); i++) {
                Element e = (Element) doc.getElementsByTagName("text").item(i);
                if (e.hasAttribute("x") && e.hasAttribute("y")) {
                    float x = Float.parseFloat(e.getAttribute("x"));
                    float y = Float.parseFloat(e.getAttribute("y"));
                    x += xoffset;
                    y += yoffset;
                    e.setAttribute("x", String.valueOf(x));
                    e.setAttribute("y", String.valueOf(y));
                }
            }
            Source source = new DOMSource(doc);
            StringWriter stringWriter = new StringWriter();
            Result result = new StreamResult(stringWriter);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.transform(source, result);
            svgDescription = stringWriter.getBuffer().toString();
            return svgDescription;
        } catch (CoreException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * method holding a figure description used by directors.
     * 
     * @return a figure represention an director
     */
    private static IFigure createDirector() {
        String directorsvg = "<svg width=\"102\" height=\"32\"><rect x=\"0\" y=\"0\" width=\"100\" "
                + " height=\"30\" style=\"fill:#00FF00;stroke:black;stroke-width:1\"/></svg>";
        return createSvg(directorsvg);
    }

    private static IFigure createAccumulator() {
        String accsvg = "<svg height=\"41\" width=\"41\" >"
                + "<rect height=\"40\" style=\"fill:white;stroke:black;stroke-width:1\" " 
                +       "width=\"40\" x=\"0.0\" y=\"0.0\" />"
                + "<path d=\"m 29.126953,6.2304687 0,3.0410157 -13.833984,0 8.789062,9.3515626 " 
                +       "-8.789062,10.335937 14.554687,0 0,3.041016 -18.246093,0 " 
                +       "0,-3.550781 8.419921,-9.826172 -8.419921,-8.9648439 0,-3.4277344 z\" />"
                + "</svg>";
        return createSvg(accsvg);
    }

    /**
     * method for creating a custom monitorvalue figure.
     * 
     * @param object
     *            the modelelement
     * @return the monitorvalue figure
     */
    private IFigure createMonitorValue(final EObject object) {
        MonitorValueFigure monitor = new MonitorValueFigure(object);
        monitor.setLineWidth(1);
        monitor.setForegroundColor(ColorConstants.black);
        monitor.setBackgroundColor(ColorConstants.white);
        return monitor;
    }

    /**
     * a monitor figure using the kvid mechanism of displaying its value.
     * 
     * @author ckru
     * 
     */
    private class MonitorValueFigure extends RectangleFigure implements IDataListener {

        private Label value;

        private String referredDataUri;

        private static final int LABELSIZE_WIDTH = 140;
        private static final int LABELSIZE_HEIGHT = 10;
        private static final int LABELLOCATION_X = 70;
        private static final int LABELLOCATION_Y = 10;

        /**
         * constructs this figure and adds a label that displays the current value.
         * 
         * @param object
         *            the model element.
         */
        public MonitorValueFigure(final EObject object) {
            value = new Label();
            value.getBounds().setSize(LABELSIZE_WIDTH, LABELSIZE_HEIGHT);
            value.getBounds().setLocation(LABELLOCATION_X, LABELLOCATION_Y);
            this.setLayoutManager(new BorderLayout());
            this.add(value);
            String uri = object.eResource().getURIFragment(object);
            uri = KvidUtil.fragmentUri2PtolemyUri(uri, object.eResource());
            if (object instanceof de.cau.cs.kieler.kaom.Entity) {
                de.cau.cs.kieler.kaom.Entity entity = (de.cau.cs.kieler.kaom.Entity) object;
                NamedObject port = entity.getChildPorts().get(0);
                uri += ":" + port.getName();
                referredDataUri = uri;
                DataDistributor.getInstance().registerDataListener(this);
            }

        }

        /**
         * {@inheritDoc}
         */
        public void triggerDataChanged(final boolean isHistoryValue) {
            DataObject data = DataDistributor.getInstance().getDataObjectByURI(
                    new KvidUri(referredDataUri));
            if (data != null) {
                value.setText(data.getData().toString());
            }
        }

        /**
         * {@inheritDoc}
         */
        public void triggerWrapup() {
            value.setText("");
        }

    }

    private static final int DEFAULT_WIDTH = 90;
    private static final int LABELSIZE_HEIGHT = 12;
    private static final int LABELLOCATION_X = 5;
    private static final int LABELLOCATION_Y = 8;

    private IFigure createValueFigure(final EObject object, final String valueAttribute, final EditPart part) {
        RectangleFigure constFigure = (RectangleFigure) getDefaultFigure();
        if (object instanceof Annotatable) {
            Annotation iconAnn = ((Annotatable) object).getAnnotation("_icon");
            int width;
            if (iconAnn != null) {
                StringAnnotation sizeAnn = (StringAnnotation) iconAnn.getAnnotation("displayWidth");                
                
            if (sizeAnn != null) {
                width = Integer.parseInt(sizeAnn.getValue());
            } else {
                width = DEFAULT_WIDTH;
            }
            } else {
                width = DEFAULT_WIDTH;
            }
            Dimension dim = new Dimension(width, 30);
            constFigure.getBounds().setSize(dim);
            constFigure.setMaximumSize(dim.getCopy());
            constFigure.setMinimumSize(dim.getCopy());
            constFigure.setPreferredSize(dim.getCopy());
            
            if (!valueAttribute.equals("null")) {
                Annotation valueAnn = ((Annotatable) object).getAnnotation(valueAttribute);
                String value = ((StringAnnotation) valueAnn).getValue();
                Label valueLabel = new Label();
                valueLabel.setText(value);
                valueLabel.setBounds(new Rectangle(LABELLOCATION_X, LABELLOCATION_Y,
                    /*width -5*/80, LABELSIZE_HEIGHT));
                constFigure.setLayoutManager(new BorderLayout());
                constFigure.add(valueLabel);
            }
            
        }
        return constFigure;
        
    }
    
    
    /**
     * creates a figure representing a constant.
     * 
     * @param object
     *            the model element
     * @return the constant figure
     */
    /*
    private IFigure createConstFigure(final EObject object) {
        IFigure constFigure = getDefaultFigure();
        if (object instanceof Annotatable) {
            Annotation valueAnn = ((Annotatable) object).getAnnotation("value");
            if (valueAnn instanceof StringAnnotation) {
                String value = ((StringAnnotation) valueAnn).getValue();
                Label valueLabel = new Label();
                valueLabel.setText(value);
                valueLabel.setBounds(new Rectangle(LABELLOCATION_X, LABELLOCATION_Y,
                        LABELSIZE_WIDTH, LABELSIZE_HEIGHT));
                constFigure.setLayoutManager(new BorderLayout());
                constFigure.add(valueLabel);
            }
        }
        return constFigure;
    }
    
    private IFigure createValuesFigure(final EObject object) {
        IFigure constFigure = getDefaultFigure();
        if (object instanceof Annotatable) {
            Annotation valueAnn = ((Annotatable) object).getAnnotation("values");
            if (valueAnn instanceof StringAnnotation) {
                String value = ((StringAnnotation) valueAnn).getValue();
                Label valueLabel = new Label();
                valueLabel.setText(value);
                valueLabel.setBounds(new Rectangle(LABELLOCATION_X, LABELLOCATION_Y,
                        LABELSIZE_WIDTH, LABELSIZE_HEIGHT));
                constFigure.setLayoutManager(new BorderLayout());
                constFigure.add(valueLabel);
            }
        }
        return constFigure;
    }
*/
    
    /**
     * creates an appropriate figure according to the _IconDescription attribute of a ptolemy actor.
     * 
     * @param object
     *            the model element
     * @return the figure
     */
    private static IFigure createFigureFromIconDescription(final EObject object) {
        if (object instanceof Annotatable) {
            Annotatable myAnnotatable =  (Annotatable) object;
            Annotation annotation = myAnnotatable.getAnnotation("ptolemyClass");
            if (annotation != null && annotation instanceof StringAnnotation) {
                String ptolemyClassString = ((StringAnnotation) annotation).getValue();
                try {
                    Object obj;
                    Class<?> ptolemy = Class.forName(ptolemyClassString);
                    Constructor<?> constr = ptolemy.getConstructor(CompositeEntity.class,
                            String.class);
                    obj = constr.newInstance(new CompositeEntity(), "cache");
                    if (obj instanceof NamedObj) {
                        NamedObj entity = (NamedObj) obj;
                        TestIconLoader til = new TestIconLoader();
                        try {
                            til.loadIconForClass(ptolemyClassString, entity);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        List<EditorIcon> icons = entity.attributeList(EditorIcon.class);
                        ConfigurableAttribute ca = null;
                        String svg = "";
                        if (icons.isEmpty()) {
                            ca = (ConfigurableAttribute) entity.getAttribute("_iconDescription");
                            svg = ca.getConfigureText();
                        } else {
                            EditorIcon icon = icons.get(0);
                            Figure shape = icon.createBackgroundFigure();
                            Image img;
                            img = getImageFromFigure(shape);
                            BufferedImage resizedImage = new BufferedImage(img.getWidth(null),
                                    img.getHeight(null), BufferedImage.TYPE_INT_RGB);
                            Graphics2D g = resizedImage.createGraphics();
                            g.drawImage(img, 0, 0, null);
                            g.dispose();
                            g.setComposite(AlphaComposite.Src);
                            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                            g.setRenderingHint(RenderingHints.KEY_RENDERING,
                                    RenderingHints.VALUE_RENDER_QUALITY);
                            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                            ScalableImageFigure fig = new ScalableImageFigure(
                                    new org.eclipse.swt.graphics.Image(Display.getCurrent(),
                                            CoreUiUtil.convertAWTImageToSWT(resizedImage)));
                            return fig;
                        }
                        svg = repairSvg(svg);
                        if (svg == null) {
                            return getDefaultFigure();
                        } else {
                            return createSvg(svg);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                return getDefaultFigure();
            }
        }
        throw new RuntimeException("initializing svg from ptolemyClass failed");

    }

    private static Image getImageFromFigure(final Figure figure) {
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

            graphics.setBackground(new Color(255, 255, 255, 255));
            graphics.clearRect(0, 0, (int) figure.getBounds().getWidth(), (int) figure.getBounds()
                    .getHeight());
            figure.paint(graphics);
            return image;
        }
    }

    public NodeFigure getNodePlateByString(String input, EObject object) {
        //return new DefaultSizeNodeFigure(50,70);
        return null;
    }

}
