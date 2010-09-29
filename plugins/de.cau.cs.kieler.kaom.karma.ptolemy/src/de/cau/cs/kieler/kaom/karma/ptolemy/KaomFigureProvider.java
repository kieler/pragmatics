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

import java.io.StringWriter;
import java.lang.reflect.Constructor;
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
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.draw2d.ui.render.RenderedImage;
import org.eclipse.gmf.runtime.draw2d.ui.render.factory.RenderedImageFactory;
import org.eclipse.gmf.runtime.draw2d.ui.render.figures.ScalableImageFigure;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ptolemy.actor.lib.Ramp;
import ptolemy.data.expr.XMLParser;
import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.Entity;
import ptolemy.kernel.util.ConfigurableAttribute;
import ptolemy.moml.test.TestIconLoader;
import ptolemy.vergil.icon.EditorIcon;
import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.NamedObject;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
import de.cau.cs.kieler.core.ui.figures.RoundedRectangleFigure;
import de.cau.cs.kieler.karma.IRenderingProvider;
import de.cau.cs.kieler.kvid.KvidUtil;
import de.cau.cs.kieler.kvid.data.DataObject;
import de.cau.cs.kieler.kvid.data.KvidUri;
import de.cau.cs.kieler.kvid.datadistributor.DataDistributor;
import de.cau.cs.kieler.kvid.datadistributor.IDataListener;

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
            final EObject object) {
        if (input.equals("_IconDescription")) {
            if (object instanceof de.cau.cs.kieler.kaom.Entity) {
                de.cau.cs.kieler.kaom.Entity myEntity = (de.cau.cs.kieler.kaom.Entity) object;
                Annotation annotation = myEntity.getAnnotation("ptolemyClass");
                if (annotation != null && annotation instanceof StringAnnotation) {
                    String ptolemyClassString = ((StringAnnotation) annotation).getValue();
                    try {
                        Class ptolemy = Class.forName(ptolemyClassString);
                        Constructor constr = ptolemy.getConstructor(CompositeEntity.class,
                                String.class);
                        Object obj = constr.newInstance(new CompositeEntity(), "test");
                        if (obj instanceof Entity) {
                            Entity entity = (Entity) obj;
                            TestIconLoader til = new TestIconLoader();
                            try {
                                til.loadIconForClass(Ramp.class.getName(), entity);
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            List<EditorIcon> icons = entity.attributeList(EditorIcon.class);
                            ConfigurableAttribute ca = null;
                            String svg = "";
                            if (icons.isEmpty()) {
                                ca = (ConfigurableAttribute) entity
                                        .getAttribute("_iconDescription");

                                svg = ca.getConfigureText();
                            }
                            svg = repairSvg(svg);
                            if (svg == null) {
                                return getDefaultFigure();
                            } else {
                                return createSvg(svg);
                            }
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                } else {
                    return getDefaultFigure();
                }
            }
            throw new RuntimeException("initializing svg from ptolemyClass failed");
        } else if (input.equals("ptolemy.actor.lib.MonitorValue")) {
            return createMonitorValue(object);
        } else if (input.equals("ptolemy.actor.lib.Const")) {
            IFigure constFigure = getDefaultFigure();
            if (object instanceof Annotatable) {
                Annotation valueAnn = ((Annotatable)object).getAnnotation("value");
                if (valueAnn instanceof StringAnnotation) {
                    String value = ((StringAnnotation) valueAnn).getValue();
                    Label valueLabel = new Label();
                    valueLabel.setText(value);
                    valueLabel.setBounds(new Rectangle(10,10,30,10));
                    
                    constFigure.setLayoutManager(new BorderLayout());
                    constFigure.add(valueLabel);
                }
            }
            return constFigure;
        } else if (input.equals("Director")) {
            return createDirector();
        } else {
            return getDefaultFigure();
        }
    }

    /**
     * {@inheritDoc}
     */
    public IFigure getDefaultFigure() {
        // TODO Auto-generated method stub
        // org.ptolemy.
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
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public LayoutManager getDefaultLayoutManager() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public BorderItemLocator getBorderItemLocatorByString(final String input, final IFigure parent, final Object locator, final EObject object) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * method for generating a scalable image figure from a file.
     * 
     * @param file
     *            the file holding the svg image
     * @return a scalable image figure
     */
    private IFigure createSvg(final String file) {

        RenderedImage img = RenderedImageFactory.getInstance(file.getBytes());
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
    private String repairString(final String input) {
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
    private String repairSvg(final String svg) {
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
                //TODO hacked else case, think of something better
                int childPointer = 0;
                Object object = svgElement.getChildNodes().item(childPointer);                
                while (!(object instanceof Element) && (object != svgElement )) {
                    object = svgElement.getElementsByTagName("*").item(childPointer);
                }
                Element firstElement = (Element)object;
                xoffset = Math.abs(Integer.parseInt(firstElement.getAttribute("x")));
                yoffset = Math.abs(Integer.parseInt(firstElement.getAttribute("y")));
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * method holding a figure description used by directors.
     * 
     * @return a figure represention an director
     */
    private IFigure createDirector() {
        String directorsvg = "<svg width=\"102\" height=\"32\"><rect x=\"0\" y=\"0\" width=\"100\" height=\"30\" style=\"fill:#00FF00;stroke:black;stroke-width:1\"/></svg>";
        return createSvg(directorsvg);
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

        /**
         * constructs this figure and adds a label that displays the current value.
         * 
         * @param object
         *            the model element.
         */
        public MonitorValueFigure(final EObject object) {
            value = new Label();
            value.getBounds().setSize(140, 10);
            value.getBounds().setLocation(70, 10);
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

}
