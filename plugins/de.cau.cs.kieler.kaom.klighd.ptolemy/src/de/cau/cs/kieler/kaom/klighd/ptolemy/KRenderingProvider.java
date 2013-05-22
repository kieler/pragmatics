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
package de.cau.cs.kieler.kaom.klighd.ptolemy;

import java.lang.reflect.Constructor;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.statushandlers.StatusManager;
import org.w3c.dom.Document;

import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.util.NamedObj;
import ptolemy.vergil.icon.EditorIcon;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
import de.cau.cs.kieler.core.krendering.KAction;
import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.Trigger;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.importer.ptolemy.xtend.PtolemyInterface;
import de.cau.cs.kieler.kaom.klighd.ptolemy.util.PtolemyFetcher;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klighd.KlighdConstants;

/**
 * Interface between ptolemy, KRendering creation and transformation.
 * 
 * @author ckru
 * 
 */
public class KRenderingProvider {

    private static FigureProviderKRendering figureProvider = new FigureProviderKRendering();

    /**
     * Finds out what kind of object we have and delegates KRendering creation accordingly.
     * 
     * @param annotatable
     *            the object in need of a rendering
     * @return the rendering for given object
     */
    public static KRendering getKNodeRendering(final Annotatable annotatable) {
        StringAnnotation language = (StringAnnotation) annotatable.getAnnotation("language");
        // check whether we have a ptolemy object else its someone elses job so do nothing
        if (language != null && language.getValue().equals("ptolemy")) {
            // check for nature of object and build rendering
            Annotation director = annotatable.getAnnotation("Director");
            if (director != null) {
                return figureProvider.createDirector();
            } else if ((annotatable instanceof Entity)
                    && (((StringAnnotation) annotatable.getAnnotation("ptolemyClass")).getValue()
                            .equals("ptolemy.actor.lib.Accumulator"))) {
                return figureProvider.createAccumulator();
            } else if ((annotatable instanceof Entity)
                    && (((StringAnnotation) annotatable.getAnnotation("ptolemyClass")).getValue()
                            .equals("ptolemy.domains.modal.kernel.State"))) {
                return figureProvider.createStateRendering((Entity) annotatable);
            } else if ((annotatable instanceof Entity)
                    && ((Entity) annotatable).getChildEntities().isEmpty()) {
                return getPtolemySvgRendering(annotatable);
                // entity has children so make it collapse/expandable
            } else if ((annotatable instanceof Entity)
                    && !((Entity) annotatable).getChildEntities().isEmpty()) {
                KRendering ren = getDefaultRendering();
                ren.setProperty(KlighdConstants.EXPANDED_RENDERING, true);

                KAction a = KRenderingFactory.eINSTANCE.createKAction();
                a.setTrigger(Trigger.DOUBLECLICK);
                a.setId(KlighdConstants.ACTION_COLLAPSE_EXPAND);
                ren.getActions().add(a);

                return ren;
            } else {
                return getDefaultRendering();
            }

        }
        return null;
    }

    /**
     * Create rendering for given port depending on its type.
     * 
     * @param annotatable
     *            the port whose rendering to create
     * @param layout
     *            layout of given port
     * @param parentLayout
     *            layout of the entity he port belongs to
     * @return KRendering of the given port
     */
    public static KRendering getKPortRendering(final Annotatable annotatable,
            final KShapeLayout layout, final KShapeLayout parentLayout) {

        KColor color = KRenderingFactory.eINSTANCE.createKColor();
        String input = "WEST";

        StringAnnotation cardinal = (StringAnnotation) annotatable.getAnnotation("_cardinal");

        KVector portAnchor = new KVector();

        // if the port has an explicit direction use that, else get the port type and deduct it from
        // that
        if (cardinal != null && cardinal.getValue() != null) {
            input = cardinal.getValue();
        } else {
            Annotation inputAnn = annotatable.getAnnotation("input");
            Annotation outputAnn = annotatable.getAnnotation("output");
            Annotation inoutAnn = annotatable.getAnnotation("inputoutput");
            if (inputAnn != null) {
                input = "WEST";
                portAnchor.x = 0;
                portAnchor.y = 3.5;
                layout.setProperty(LayoutOptions.OFFSET, -3f);
                layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.WEST);
            } else if (outputAnn != null) {
                input = "EAST";
                portAnchor.x = 7;
                portAnchor.y = 3.5;
                layout.setProperty(LayoutOptions.OFFSET, 0f);
                layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.EAST);
            } else if (inoutAnn != null) {
                input = "SOUTH";
                layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.SOUTH);
            }

        }

        layout.setProperty(Properties.PORT_ANCHOR, portAnchor);

        EObject parentObject = annotatable.eContainer();
        if (parentObject instanceof Annotatable) {
            Annotatable myAnnotatable = (Annotatable) parentObject;
            Annotation annotation = myAnnotatable.getAnnotation("ptolemyClass");
            if (annotation != null && annotation instanceof StringAnnotation) {
                String ptolemyClassString = ((StringAnnotation) annotation).getValue();
                try {
                    ptolemy.kernel.Entity entity = getPtolemyEntity(ptolemyClassString);
                    if (entity == null) {
                        return figureProvider.getPortKRendering(
                                FigureParserKRendering.lookupColor("black", color), 0, 0, input);
                    }
                    if (annotatable instanceof Port) {
                        // we fetch a ptolemy instance of this port by its name to get some
                        // informations of its former nature.
                        Port port = (Port) annotatable;
                        String name = port.getName();
                        ptolemy.kernel.Port ptolemyPort = entity.getPort(name);
                        // parameterports are gray
                        if (ptolemyPort instanceof ptolemy.actor.parameters.ParameterPort) {
                            if (input.equals("SOUTH")) {
                                layout.setYpos(parentLayout.getHeight() + 1);
                                // layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.SOUTH);
                                return figureProvider.getPortKRendering(
                                        FigureParserKRendering.lookupColor("black", color), 0, -4,
                                        input);
                            } else if (input.equals("NORTH")) {
                                // layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.NORTH);
                                layout.setYpos(-1);
                                return figureProvider.getPortKRendering(
                                        FigureParserKRendering.lookupColor("black", color), 0, 0,
                                        input);
                            } else if (input.equals("EAST")) {
                                layout.setXpos(parentLayout.getWidth() + 1);
                                // layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.EAST);
                                return figureProvider.getPortKRendering(
                                        FigureParserKRendering.lookupColor("gray", color), 0, 0,
                                        input);
                            } else {
                                layout.setXpos(-1);
                                // layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.WEST);
                                return figureProvider.getPortKRendering(
                                        FigureParserKRendering.lookupColor("gray", color),
                                        0 /*-4*/, 0, input);
                            }
                        } else if (ptolemyPort instanceof ptolemy.actor.IOPort) {
                            // io multiports are white
                            if (((ptolemy.actor.IOPort) (ptolemyPort)).isMultiport()) {
                                if (input.equals("SOUTH")) {
                                    layout.setYpos(parentLayout.getHeight() + 1);
                                    // layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.SOUTH);
                                    return figureProvider.getPortKRendering(
                                            FigureParserKRendering.lookupColor("black", color), 0,
                                            -4, input);
                                } else if (input.equals("NORTH")) {
                                    layout.setYpos(-1);
                                    // layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.NORTH);
                                    return figureProvider.getPortKRendering(
                                            FigureParserKRendering.lookupColor("black", color), 0,
                                            0, input);
                                } else if (input.equals("EAST")) {
                                    layout.setXpos(parentLayout.getWidth() + 1);
                                    // layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.EAST);
                                    return figureProvider.getPortKRendering(
                                            FigureParserKRendering.lookupColor("white", color), 0,
                                            0, input);
                                } else {
                                    layout.setXpos(-1);
                                    // layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.WEST);
                                    return figureProvider.getPortKRendering(
                                            FigureParserKRendering.lookupColor("white", color),
                                            0 /*-4*/, 0, input);
                                }
                            } else {
                                // other io ports are black
                                if (input.equals("SOUTH")) {
                                    layout.setYpos(parentLayout.getHeight() + 1);
                                    // layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.SOUTH);
                                    return figureProvider.getPortKRendering(
                                            FigureParserKRendering.lookupColor("black", color), 0,
                                            -4, input);
                                } else if (input.equals("NORTH")) {
                                    layout.setYpos(-1);
                                    // layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.NORTH);
                                    return figureProvider.getPortKRendering(
                                            FigureParserKRendering.lookupColor("black", color), 0,
                                            0, input);
                                } else if (input.equals("EAST")) {
                                    layout.setXpos(parentLayout.getWidth() + 1);
                                    // layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.EAST);
                                    return figureProvider.getPortKRendering(
                                            FigureParserKRendering.lookupColor("black", color), 0,
                                            0, input);
                                } else {
                                    layout.setXpos(-1);
                                    // layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.WEST);
                                    return figureProvider.getPortKRendering(
                                            FigureParserKRendering.lookupColor("black", color),
                                            0 /*-4*/, 0, input);
                                }
                            }
                        } else {
                            // all other ports are painted black
                            if (input.equals("SOUTH")) {
                                layout.setYpos(parentLayout.getHeight() + 1);
                                // layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.SOUTH);
                                return figureProvider.getPortKRendering(
                                        FigureParserKRendering.lookupColor("black", color), 0, 4,
                                        input);
                            } else if (input.equals("NORTH")) {
                                layout.setYpos(-1);
                                // layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.NORTH);
                                return figureProvider.getPortKRendering(
                                        FigureParserKRendering.lookupColor("black", color), 0, 0,
                                        input);
                            } else if (input.equals("EAST")) {
                                layout.setXpos(parentLayout.getWidth() + 1);
                                // layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.EAST);
                                return figureProvider.getPortKRendering(
                                        FigureParserKRendering.lookupColor("black", color), 0, 0,
                                        input);
                            } else {
                                layout.setXpos(-1);
                                // layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.WEST);
                                return figureProvider.getPortKRendering(
                                        FigureParserKRendering.lookupColor("black", color), 0, 0,
                                        input);
                            }
                        }
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return figureProvider.getPortKRendering(FigureParserKRendering.lookupColor("black", color),
                0, 0, input);
    }

    /**
     * Get the svg definition of a ptolemy entity and make a KRendering out of that.
     * 
     * @param annotatable
     *            the entity
     * @return the KRendering according to the svg
     */
    public static KRendering getPtolemySvgRendering(final Annotatable annotatable) {
        NamedObj nObj = PtolemyFetcher.getPtolemyInstance(annotatable);
        if (nObj == null) {
            return null; // figureProvider.getErrorFigure();
        } else {
            // get all icons for this element
            List<EditorIcon> icons = PtolemyFetcher.fetchIcons(nObj);
            // if there is none use svg description
            if (icons.isEmpty()) {
                Document doc = PtolemyFetcher.fetchSvgDoc(nObj);
                if (doc != null) {

                    KRendering figure = figureProvider.createFigureFromSvg(doc);
                    return figure;
                } else {
                    Status myStatus = new Status(IStatus.WARNING,
                            "de.cau.cs.kieler.kaom.karma.ptolemy",
                            "couldn't get svg document from ptolemy");
                    StatusManager.getManager().handle(myStatus, StatusManager.SHOW);
                    return null; // figureProvider.getErrorFigure();
                }

                // else use the first icon (usually there should be only one anyway)
            } else {
                EditorIcon icon = icons.get(0);
                KRendering figure = figureProvider.createFigureFromIcon(icon);
                return figure;
            }

        }
    }

    /**
     * Get some default rendering in case everything fails. White box with black border.
     * 
     * @return default rendering
     */
    public static KRendering getDefaultRendering() {
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        KRectangle rectangle = factory.createKRectangle();
        return rectangle;
    }

    /**
     * Loads the given Ptolemy entity.
     * 
     * @param className
     *            the entity's class name.
     * @return the loaded entity.
     * @throws Exception
     *             various exceptions can occurr.
     */
    private static ptolemy.kernel.Entity getPtolemyEntity(final String className) throws Exception {
        try {
            Class<?> ptolemy = Class.forName(className);
            Constructor<?> constr = ptolemy.getConstructor(CompositeEntity.class, String.class);
            Object obj = constr.newInstance(new CompositeEntity(), "cache");
            ptolemy.kernel.Entity entity = (ptolemy.kernel.Entity) obj;
            return entity;
        } catch (ClassNotFoundException e) {
            // Not a class actor, continue below
        } catch (NoClassDefFoundError er) {
            // er.printStackTrace();
            return null;
        }
        // Use Ptolemy to load the actor
        Injector injector = Guice.createInjector();
        PtolemyInterface ptolemy = injector.getInstance(PtolemyInterface.class);

        NamedObj nObj = null;
        try {
            nObj = ptolemy.instantiatePtolemyActor(className, "actor");
        } catch (Exception e) {
            return null;
        }
        if (nObj instanceof ptolemy.kernel.Entity) {
            return (ptolemy.kernel.Entity) nObj;
        } else {
            throw new Exception("Couldn't load Ptolemy Entity '" + className + "'.");
        }
    }

    /**
     * Get rendering for a ptolemy relation.
     * 
     * @return relation rendering
     */
    public static KRendering getRelationRendering() {
        return figureProvider.createRelation();
    }

}
