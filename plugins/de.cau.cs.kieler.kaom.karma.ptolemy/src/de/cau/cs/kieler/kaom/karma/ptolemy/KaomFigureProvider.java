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

import java.io.ByteArrayInputStream;
//import java.io.File;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.draw2d.ui.render.RenderedImage;
import org.eclipse.gmf.runtime.draw2d.ui.render.factory.RenderedImageFactory;
import org.eclipse.gmf.runtime.draw2d.ui.render.figures.ScalableImageFigure;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ptolemy.actor.lib.Ramp;
import ptolemy.data.expr.XMLParser;
import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.Entity;
import ptolemy.kernel.util.ConfigurableAttribute;
import ptolemy.moml.test.TestIconLoader;
import ptolemy.vergil.icon.EditorIcon;
import de.cau.cs.kieler.karma.IRenderingProvider;

/**
 * 
 * @author ckru
 * 
 */
public class KaomFigureProvider implements IRenderingProvider {

    /**
     * running number for unique file names
     */
    int lfdnr = 0;

    /**
     * {@inheritDoc}
     */
    public IFigure getFigureByString(final String input, final IFigure oldFigure,
            final EObject object) {
        // TODO Auto-generated method stub
        if (input.equals("ptolemyObject")) {
            // return createTestSVGFigure1(oldFigure, "");

            return getDefaultFigure();
        } else if(input.equals("ptolemy.actor.lib.MonitorValue")){
            return getDefaultFigure();
        } else {
            try {
                //UpdatedValueIcon val = new UpdatedValueIcon(new CompositeEntity(), "name");
                //String test1 = val.toString();
                //diva.canvas.Figure test2 = val.createBackgroundFigure();
                //diva.canvas.Figure test3 = val.createFigure();
                
                
                Class ptolemy = Class.forName(input);
                Constructor constr = ptolemy.getConstructor(CompositeEntity.class, String.class);
                Object obj = constr.newInstance(new CompositeEntity(), "test");
                if (obj instanceof Entity) {
                    Entity entity = (Entity) obj;
                    TestIconLoader til = new TestIconLoader(); 
                    try {
                        til.loadIconForClass(Ramp.class.getName(), entity);
                    } catch (Exception e) { 
                        // TODO Auto-generated catch block e.printStackTrace(); 
                    }
                    List<EditorIcon> icons = entity.attributeList(EditorIcon.class);
                    int k;
                    ConfigurableAttribute ca = null;
                    String svg = "d";
                    if (icons.isEmpty()) {
                        ca = (ConfigurableAttribute) entity.getAttribute("_iconDescription");

                        svg = ca.getConfigureText();
                    }
                    String file = writeFile(svg, input + String.valueOf(lfdnr));
                    if (file == null){
                        return getDefaultFigure();
                    } else {
                    lfdnr++;
                    return createSvg(file);
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            throw new RuntimeException("initializing svg from ptolemyClass failed");
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
     * method for generating a scalable image figure from a file.
     * 
     * @param file
     *            the file holding the svg image
     * @return a scalable image figure
     */
    private IFigure createSvg(String file) {
        
        RenderedImage img = RenderedImageFactory.getInstance(file.getBytes());
        ScalableImageFigure fig = new ScalableImageFigure(img, false, true, true);
        return fig;
    }

    private String repairString(String input){
        String[] parts = input.split("\"");
        String output = "";
        for (int i = 0; i < parts.length; i+=2) {
            if (i < parts.length -1) {
                output += parts[i] + "\"" + parts[i+1] + "\" ";
            } else {
                output += parts[i];
            }
        }
        return output;
    }
    

    /**
     * 
     * @return
     */

    private IFigure createDirector() {
        RectangleFigure rf = new RectangleFigure();
        // this.setCurrentFigure(rf);
        rf.setFill(false);

        rf.setForegroundColor(ColorConstants.black);
        rf.setBackgroundColor(ColorConstants.green);
        // rf.setBorder(new MarginBorder(getMapMode().DPtoLP(5), getMapMode().DPtoLP(5),
        // getMapMode().DPtoLP(5), getMapMode().DPtoLP(5)));
        return rf;
    }

    /**
     * {@inheritDoc}
     */
    public LayoutManager getLayoutManagerByString(String input, LayoutManager oldLayoutManager,
            EObject object) {
        // TODO Auto-generated method stub
        /*
         * if (input.equals("ramp")) { return new PtolemyEntityLayout(); }
         */
        return oldLayoutManager;
    }

    /**
     * Method for creating and saving an svg file from a string description of an svg and modifying
     * it from ptolemy style to a form usable in eclipse.
     * 
     * @param svg
     *            the string describing an svg in xml
     * @param name
     *            the file name the svg image is saved as
     * @return the file holding the svg image
     */
    private String writeFile(String svg, String name) {
        try {
                XMLParser xmlpars = new XMLParser();
                Document doc;
                try {
                    doc = xmlpars.parser(svg);
                } catch (Exception e) {
                    svg = repairString(svg);
                    doc = xmlpars.parser(svg);
                }
                Element svgElement = (Element) doc.getElementsByTagName("svg").item(0);
                Element rectElement = (Element) doc.getElementsByTagName("rect").item(0);

                svgElement.setAttribute("height",
                        String.valueOf(Integer.parseInt(rectElement.getAttribute("height")) + 1));
                svgElement.setAttribute("width",
                        String.valueOf(Integer.parseInt(rectElement.getAttribute("width")) + 1));

                int xoffset = Math.abs(Integer.parseInt(rectElement.getAttribute("x")));
                int yoffset = Math.abs(Integer.parseInt(rectElement.getAttribute("y")));

                for (int i = 0; i < doc.getElementsByTagName("rect").getLength(); i++) {
                    Element e = (Element) doc.getElementsByTagName("rect").item(i);
                    if (e.hasAttribute("x") && e.hasAttribute("y") && e.hasAttribute("style")) {
                        int x = Integer.parseInt(e.getAttribute("x"));
                        int y = Integer.parseInt(e.getAttribute("y"));
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
                        int x = Integer.parseInt(e.getAttribute("cx"));
                        int y = Integer.parseInt(e.getAttribute("cy"));
                        x += xoffset;
                        y += yoffset;
                        e.setAttribute("cx", String.valueOf(x));
                        e.setAttribute("cy", String.valueOf(y));
                    }
                }

                for (int i = 0; i < doc.getElementsByTagName("polygon").getLength(); i++) {
                    Element e = (Element) doc.getElementsByTagName("polygon").item(i);
                    if (e.hasAttribute("points")) {
                        String newpoints = "";
                        String allpoints = e.getAttribute("points");
                        String[] pointsarray = allpoints.split(" +");
                        for (String coords : pointsarray) {
                            String[] coordsarray = coords.split(",");
                            int x = Integer.parseInt(coordsarray[0]);
                            int y = Integer.parseInt(coordsarray[1]);
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
                            int x = Integer.parseInt(coordsarray[0]);
                            int y = Integer.parseInt(coordsarray[1]);
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
                        int x1 = Integer.parseInt(e.getAttribute("x1"));
                        int y1 = Integer.parseInt(e.getAttribute("y1"));
                        int x2 = Integer.parseInt(e.getAttribute("x2"));
                        int y2 = Integer.parseInt(e.getAttribute("y2"));
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
                        int x = Integer.parseInt(e.getAttribute("x"));
                        int y = Integer.parseInt(e.getAttribute("y"));
                        x += xoffset;
                        y += yoffset;
                        e.setAttribute("x", String.valueOf(x));
                        e.setAttribute("y", String.valueOf(y));
                    }
                }

                for (int i = 0; i < doc.getElementsByTagName("ellipse").getLength(); i++) {
                    Element e = (Element) doc.getElementsByTagName("ellipse").item(i);
                    if (e.hasAttribute("cx") && e.hasAttribute("cy")) {
                        int x = Integer.parseInt(e.getAttribute("cx"));
                        int y = Integer.parseInt(e.getAttribute("cy"));
                        x += xoffset;
                        y += yoffset;
                        e.setAttribute("cx", String.valueOf(x));
                        e.setAttribute("cy", String.valueOf(y));
                    }
                }

                for (int i = 0; i < doc.getElementsByTagName("text").getLength(); i++) {
                    Element e = (Element) doc.getElementsByTagName("text").item(i);
                    if (e.hasAttribute("x") && e.hasAttribute("y")) {
                        int x = Integer.parseInt(e.getAttribute("x"));
                        int y = Integer.parseInt(e.getAttribute("y"));
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
                svg = stringWriter.getBuffer().toString();
            return svg;
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
     * {@inheritDoc}
     */
    public LayoutManager getDefaultLayoutManager() {
        // TODO Auto-generated method stub
        return null;
    }

    public BorderItemLocator getBorderItemLocatorByString(String input) {
        // TODO Auto-generated method stub
        return null;
    }

}
