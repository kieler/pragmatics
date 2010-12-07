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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.kaom.karma.renderingprovider;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.draw2d.ui.render.RenderedImage;
import org.eclipse.gmf.runtime.draw2d.ui.render.factory.RenderedImageFactory;
import org.eclipse.gmf.runtime.draw2d.ui.render.figures.ScalableImageFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;

import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.util.NamedObj;

import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.importer.ptolemy.PtolemyHelper;
import de.cau.cs.kieler.kaom.karma.ptolemy.PtolemyPortBorderItemLocator;
import de.cau.cs.kieler.karma.IAdvancedRenderingEditPart;
import de.cau.cs.kieler.karma.IRenderingProvider;
import de.cau.cs.kieler.karma.util.CustomPortLocator;

/**
 * @author ckru
 */
public class KaomPortProvider implements IRenderingProvider {

    /**
     * {@inheritDoc}
     */
    public IFigure getFigureByString(final String input, final IFigure oldFigure,
            final EObject object, final EditPart part) {
        EditPart parentPart = part.getParent();

        // /// make port name invisible. not working yet.
        /*
         * Object partChild = part.getChildren().get(0); if (partChild instanceof PortNameEditPart)
         * { PortNameEditPart portNameEditPart = (PortNameEditPart) partChild;
         * portNameEditPart.getFigure().setVisible(false); portNameEditPart.getFigure().setSize(0,
         * 0);
         * 
         * }
         */
        // ///
        if (parentPart instanceof IAdvancedRenderingEditPart) {
            EObject parentObject = ((IAdvancedRenderingEditPart) parentPart).getModelElement();
            if (parentObject instanceof Annotatable) {
                Annotatable myAnnotatable = (Annotatable) parentObject;
                Annotation annotation = myAnnotatable.getAnnotation("ptolemyClass");
                if (annotation != null && annotation instanceof StringAnnotation) {
                    String ptolemyClassString = ((StringAnnotation) annotation).getValue();
                    try {
                        ptolemy.kernel.Entity entity = getPtolemyEntity(ptolemyClassString);
                        
                        if (object instanceof Port) {
                            Port port = (Port) object;
                            String name = port.getName();
                            ptolemy.kernel.Port ptolemyPort = entity.getPort(name);
                            if (ptolemyPort instanceof ptolemy.actor.parameters.ParameterPort) {
                                if (input.equals("UP")) {
                                    return createSvg(getUpwardsPortSvgString("gray"));
                                } else if (input.equals("DOWN")) {
                                    return createSvg(getDownwardsPortSvgString("gray"));
                                } else {
                                    return createSvg(getPortSvgString("gray"));
                                }
                            } else if (ptolemyPort instanceof ptolemy.actor.IOPort) {
                                if (((ptolemy.actor.IOPort) (ptolemyPort)).isMultiport()) {
                                    if (input.equals("UP")) {
                                        return createSvg(getUpwardsPortSvgString("white"));
                                    } else if (input.equals("DOWN")) {
                                        return createSvg(getDownwardsPortSvgString("white"));
                                    } else {
                                        return createSvg(getPortSvgString("white"));
                                    }
                                } else {
                                    if (input.equals("UP")) {
                                        return createSvg(getUpwardsPortSvgString("black"));
                                    } else if (input.equals("DOWN")) {
                                        return createSvg(getDownwardsPortSvgString("black"));
                                    } else {
                                        return createSvg(getPortSvgString("black"));
                                    }
                                }
                            } else {
                                if (input.equals("UP")) {
                                    return createSvg(getUpwardsPortSvgString("black"));
                                } else if (input.equals("DOWN")) {
                                    return createSvg(getDownwardsPortSvgString("black"));
                                } else {
                                    return createSvg(getPortSvgString("black"));
                                }
                            }
                        }

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        /*
         * if (input.equals("UP")) { return createSvg(getUpwardsPortSvgString("black")); } else if
         * (input.equals("DOWN")) { return createSvg(getDownwardsPortSvgString("white")); } else if
         * (input.equals("white")) { return createSvg(getPortSvgString("white")); } else if
         * (input.equals("gray")) { return createSvg(getPortSvgString("gray")); } else { return
         * createSvg(getPortSvgString("black")); }
         */
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public IFigure getDefaultFigure() {
        RectangleFigure defaultFigure = new RectangleFigure();
        defaultFigure.setLineWidth(1);
        defaultFigure.setForegroundColor(ColorConstants.black);
        defaultFigure.setBackgroundColor(ColorConstants.black);
        return defaultFigure;
    }

    /**
     * Holds an svg image description of a triangle pointing east.
     * 
     * @param color
     *            a css compatible color name
     * @return the svg description
     */
    private String getPortSvgString(final String color) {
        return "<svg width=\"7.5\" height=\"7.5\">"
                + "<polygon points=\"0,7 0,0 7,3.5 0,7\" style=\"fill:" + color
                + ";stroke:black;stroke-width:1\" />" + "</svg>";
    }

    /**
     * Holds an svg image description of a triangle pointing south.
     * 
     * @param color
     *            a css compatible color name
     * @return the svg description
     */
    private String getDownwardsPortSvgString(final String color) {
        return "<svg width=\"7.5\" height=\"7.5\">"
                + "<polygon points=\"0,0 7,0 3.5,7 0,0\" style=\"fill:" + color
                + ";stroke:black;stroke-width:1\" />" + "</svg>";
    }

    /**
     * Holds an svg image description of a triangle pointing north.
     * 
     * @param color
     *            a css compatible color name
     * @return the svg description
     */
    private String getUpwardsPortSvgString(final String color) {
        return "<svg width=\"7.5\" height=\"7.5\">"
                + "<polygon points=\"0,7 7,7 3.5,0 0,7\" style=\"fill:" + color
                + ";stroke:black;stroke-width:1\" />" + "</svg>";
    }

    /**
     * method for generating a scalable image figure from a file.
     * 
     * @param svgString
     *            the string representing the svg image
     * @return a scalable image figure
     */
    private IFigure createSvg(final String svgString) {
        RenderedImage img = RenderedImageFactory.getInstance(svgString.getBytes());
        ScalableImageFigure fig = new ScalableImageFigure(img, false, true, true);
        return fig;
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
    public BorderItemLocator getBorderItemLocatorByString(final String input,
            final IFigure parentFigure, final Object locator, final EObject object) {
        if (locator instanceof CustomPortLocator) {
            CustomPortLocator borderItemLocator = (CustomPortLocator) locator;
            if (borderItemLocator.getPublicConstraint().x == 0) {
                EObject container = object.eContainer();
                if (container instanceof Entity) {
                    Entity parent = (Entity) container;
                    EList<Port> ports = parent.getChildPorts();
                    if (input.equals("NORTH")) {
                        List<Port> portsOfSide = new LinkedList<Port>();
                        for (Port port : ports) {
                            StringAnnotation cardinal = (StringAnnotation) port
                                    .getAnnotation("_cardinal");
                            if (cardinal != null && cardinal.getValue().equals("NORTH")) {
                                portsOfSide.add(port);
                            }
                        }
                        BorderItemLocator newlocator = new PtolemyPortBorderItemLocator(
                                parentFigure, PositionConstants.NORTH, portsOfSide, (Port) object);
                        return newlocator;
                    } else if (input.equals("EAST")) {
                        List<Port> portsOfSide = new LinkedList<Port>();
                        for (Port port : ports) {
                            StringAnnotation cardinal = (StringAnnotation) port
                                    .getAnnotation("_cardinal");
                            Annotation output = port.getAnnotation("output");
                            if ((cardinal != null && cardinal.getValue().equals("EAST"))
                                    || output != null) {
                                portsOfSide.add(port);
                            }
                        }
                        BorderItemLocator newlocator = new PtolemyPortBorderItemLocator(
                                parentFigure, PositionConstants.EAST, portsOfSide, (Port) object);
                        return newlocator;
                    } else if (input.equals("SOUTH")) {
                        List<Port> portsOfSide = new LinkedList<Port>();
                        for (Port port : ports) {
                            StringAnnotation cardinal = (StringAnnotation) port
                                    .getAnnotation("_cardinal");
                            Annotation output = port.getAnnotation("inputoutput");
                            if ((cardinal != null && cardinal.getValue().equals("SOUTH"))
                                    || output != null) {
                                portsOfSide.add(port);
                            }
                        }
                        BorderItemLocator newlocator = new PtolemyPortBorderItemLocator(
                                parentFigure, PositionConstants.SOUTH, portsOfSide, (Port) object);
                        return newlocator;
                    } else if (input.equals("WEST")) {
                        List<Port> portsOfSide = new LinkedList<Port>();
                        for (Port port : ports) {
                            StringAnnotation cardinal = (StringAnnotation) port
                                    .getAnnotation("_cardinal");
                            Annotation output = port.getAnnotation("input");
                            if ((cardinal != null && cardinal.getValue().equals("WEST"))
                                    || output != null) {
                                portsOfSide.add(port);
                            }
                        }
                        BorderItemLocator newlocator = new PtolemyPortBorderItemLocator(
                                parentFigure, PositionConstants.WEST, portsOfSide, (Port) object);
                        return newlocator;
                    }
                }

            }
        }
        return null;
    }

    public NodeFigure getNodePlateByString(String input, EObject object) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * Loads the given Ptolemy entity.
     * 
     * @param className the entity's class name.
     * @return the loaded entity.
     * @throws Exception various exceptions can occurr.
     */
    private ptolemy.kernel.Entity getPtolemyEntity(final String className) throws Exception {
        try {
            Class<?> ptolemy = Class.forName(className);
            Constructor<?> constr = ptolemy.getConstructor(CompositeEntity.class,
                    String.class);
            Object obj = constr.newInstance(new CompositeEntity(), "cache");
            ptolemy.kernel.Entity entity = (ptolemy.kernel.Entity) obj;
            return entity;
        } catch (ClassNotFoundException e) {
            // Not a class actor, continue below
        }

        // Use Ptolemy to load the actor
        PtolemyHelper ptolemyHelper = new PtolemyHelper();
        NamedObj nObj = ptolemyHelper.instantiatePtolemyEntity(className);
        
        if (nObj instanceof ptolemy.kernel.Entity) {
            return (ptolemy.kernel.Entity) nObj;
        } else {
            throw new Exception("Couldn't load Ptolemy Entity '" + className + "'.");
        }
    }
}
