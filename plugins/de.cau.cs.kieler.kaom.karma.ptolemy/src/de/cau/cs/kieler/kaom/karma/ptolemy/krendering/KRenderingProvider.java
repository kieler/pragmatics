package de.cau.cs.kieler.kaom.karma.ptolemy.krendering;

import java.lang.reflect.Constructor;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.ui.statushandlers.StatusManager;
import org.w3c.dom.Document;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ptolemy.kernel.CompositeEntity;
import ptolemy.kernel.util.NamedObj;
import ptolemy.vergil.icon.EditorIcon;
import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.krendering.KBackgroundColor;
import de.cau.cs.kieler.core.krendering.KForegroundColor;
import de.cau.cs.kieler.core.krendering.KGridPlacementData;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.model.gmf.IAdvancedRenderingEditPart;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.importer.ptolemy.xtend.PtolemyInterface;
import de.cau.cs.kieler.kaom.karma.ptolemy.figurecreation.PtolemyFetcher;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;

public class KRenderingProvider {

    private static FigureProviderKRendering figureProvider = new FigureProviderKRendering();
    
    public static KRendering getKNodeRendering(Annotatable annotatable) {
        StringAnnotation language = (StringAnnotation) annotatable.getAnnotation("language");
        if (language != null && language.getValue().equals("ptolemy") ) {
            Annotation director = annotatable.getAnnotation("Director");
            if (director != null) {
                return getDirectorRendering();
            } else if ((annotatable instanceof Entity) && (((StringAnnotation) annotatable.getAnnotation("ptolemyClass")).getValue().equals("ptolemy.actor.lib.Accumulator"))) {
                return figureProvider.createAccumulator();
            } else if ((annotatable instanceof Entity) && ((Entity) annotatable).getChildEntities().isEmpty()) {
                return getPtolemySvgRendering(annotatable);
            } else {
                return getDefaultRendering();
            }
        }
        return null;
    }
    
    public static KRendering getKPortRendering(final Annotatable annotatable, final KShapeLayout layout, KShapeLayout parentLayout) {
        
        KBackgroundColor color = KRenderingFactory.eINSTANCE.createKBackgroundColor();
        String input = "WEST";
        
        StringAnnotation cardinal = (StringAnnotation)annotatable.getAnnotation("_cardinal");

        if(cardinal != null && cardinal.getValue() != null) {
            input = cardinal.getValue();
        } else {
            Annotation inputAnn = annotatable.getAnnotation("input");
            Annotation outputAnn = annotatable.getAnnotation("output");
            Annotation inoutAnn = annotatable.getAnnotation("inputoutput");
            if (inputAnn != null) {
                input = "WEST";
            } else if (outputAnn != null) {
                input = "EAST";
            } else if (inoutAnn != null) {
                input = "SOUTH";
            }
            
        }
        
        
        EObject parentObject = annotatable.eContainer();
        if (parentObject instanceof Annotatable) {
            Annotatable myAnnotatable = (Annotatable) parentObject;
            Annotation annotation = myAnnotatable.getAnnotation("ptolemyClass");
            if (annotation != null && annotation instanceof StringAnnotation) {
                String ptolemyClassString = ((StringAnnotation) annotation).getValue();
                try {
                    ptolemy.kernel.Entity entity = getPtolemyEntity(ptolemyClassString);
                    if (entity == null) {
                        return figureProvider.getPortKRendering((KBackgroundColor) FigureParserKRendering.lookupColor("black", color), 0, 0, input);
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
                                //layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.SOUTH);
                                return figureProvider.getPortKRendering((KBackgroundColor) FigureParserKRendering.lookupColor("black", color), 0, -4, input);
                            } else if (input.equals("NORTH")) {
                                //layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.NORTH);
                                layout.setYpos(-1);
                                return figureProvider.getPortKRendering((KBackgroundColor) FigureParserKRendering.lookupColor("black", color), 0, 0, input);
                            } else if (input.equals("EAST")){
                                layout.setXpos(parentLayout.getWidth() +1);
                                //layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.EAST);
                                return figureProvider.getPortKRendering((KBackgroundColor) FigureParserKRendering.lookupColor("gray", color), 0, 0, input);
                            } else {
                                layout.setXpos(-1);
                                //layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.WEST);
                                return figureProvider.getPortKRendering((KBackgroundColor) FigureParserKRendering.lookupColor("gray", color), -4, 0, input);
                            }
                        } else if (ptolemyPort instanceof ptolemy.actor.IOPort) {
                            // io multiports are white
                            if (((ptolemy.actor.IOPort) (ptolemyPort)).isMultiport()) {
                                if (input.equals("SOUTH")) {
                                    layout.setYpos(parentLayout.getHeight() + 1);
                                    //layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.SOUTH);
                                    return figureProvider.getPortKRendering((KBackgroundColor) FigureParserKRendering.lookupColor("black", color), 0, -4, input);
                                } else if (input.equals("NORTH")) {
                                    layout.setYpos(-1);
                                    //layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.NORTH);
                                    return figureProvider.getPortKRendering((KBackgroundColor) FigureParserKRendering.lookupColor("black", color), 0, 0, input);
                                } else if (input.equals("EAST")){
                                    layout.setXpos(parentLayout.getWidth() +1);
                                    //layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.EAST);
                                    return figureProvider.getPortKRendering((KBackgroundColor) FigureParserKRendering.lookupColor("white", color), 0, 0, input);
                                } else {
                                    layout.setXpos(-1);
                                    //layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.WEST);
                                    return figureProvider.getPortKRendering((KBackgroundColor) FigureParserKRendering.lookupColor("white", color), -4, 0, input);
                                }
                            } else {
                                // other io ports are black
                                if (input.equals("SOUTH")) {
                                    layout.setYpos(parentLayout.getHeight() + 1);
                                    //layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.SOUTH);
                                    return figureProvider.getPortKRendering((KBackgroundColor) FigureParserKRendering.lookupColor("black", color), 0, -4, input);
                                } else if (input.equals("NORTH")) {
                                    layout.setYpos(-1);
                                    //layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.NORTH);
                                    return figureProvider.getPortKRendering((KBackgroundColor) FigureParserKRendering.lookupColor("black", color), 0, 0, input);
                                } else if (input.equals("EAST")){
                                    layout.setXpos(parentLayout.getWidth() + 1);
                                    //layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.EAST);
                                    return figureProvider.getPortKRendering((KBackgroundColor) FigureParserKRendering.lookupColor("black", color), 0, 0, input);
                                } else {
                                    layout.setXpos(-1);
                                    //layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.WEST);
                                    return figureProvider.getPortKRendering((KBackgroundColor) FigureParserKRendering.lookupColor("black", color), -4, 0, input);
                                }
                            }
                        } else {
                            // all other ports are painted black
                            if (input.equals("SOUTH")) {
                                layout.setYpos(parentLayout.getHeight() + 1);
                                //layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.SOUTH);
                                return figureProvider.getPortKRendering((KBackgroundColor) FigureParserKRendering.lookupColor("black", color), 0, 4, input);
                            } else if (input.equals("NORTH")) {
                                layout.setYpos(-1);
                                //layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.NORTH);
                                return figureProvider.getPortKRendering((KBackgroundColor) FigureParserKRendering.lookupColor("black", color), 0, 0, input);
                            } else if (input.equals("EAST")){
                                layout.setXpos(parentLayout.getWidth() + 1);
                                //layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.EAST);
                                return figureProvider.getPortKRendering((KBackgroundColor) FigureParserKRendering.lookupColor("black", color), 0, 0, input);
                            } else {
                                layout.setXpos(-1);
                                //layout.setProperty(LayoutOptions.PORT_SIDE, PortSide.WEST);
                                return figureProvider.getPortKRendering((KBackgroundColor) FigureParserKRendering.lookupColor("black", color), 0, 0, input);
                            }
                        }
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
        return figureProvider.getPortKRendering((KBackgroundColor) FigureParserKRendering.lookupColor("black", color), 0, 0, input);
    }
    
    
    public static KRendering getPtolemySvgRendering(Annotatable annotatable) {
        NamedObj nObj = PtolemyFetcher.getPtolemyInstance(annotatable);
        if (nObj == null) {
            return null;//figureProvider.getErrorFigure();
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
                    Status myStatus = new Status(IStatus.WARNING, "de.cau.cs.kieler.kaom.karma.ptolemy",
                            "couldn't get svg document from ptolemy");
                    StatusManager.getManager().handle(myStatus, StatusManager.SHOW);
                    return null;//figureProvider.getErrorFigure();
                }

                // else use the first icon (usually there should be only one anyway)
            } else {
                EditorIcon icon = icons.get(0);
                KRendering figure = figureProvider.createFigureFromIcon(icon);
                return figure;
            }

        }
    }
    
    public static KRendering getDefaultRendering() {
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        KRectangle rectangle = factory.createKRectangle();
        return rectangle;
    }
    
    public static KRendering getDirectorRendering() {
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        KRectangle rectangle = factory.createKRectangle();
        
        KGridPlacementData placement = factory.createKGridPlacementData();
        placement.setHeightHint(30);
        placement.setWidthHint(100);
        rectangle.setPlacementData(placement);
        
        KBackgroundColor fill = factory.createKBackgroundColor();
        rectangle.getStyles().add((KBackgroundColor) FigureParserKRendering.lookupColor("green", fill));
        
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
            ;
            return null;
        }
        if (nObj instanceof ptolemy.kernel.Entity) {
            return (ptolemy.kernel.Entity) nObj;
        } else {
            throw new Exception("Couldn't load Ptolemy Entity '" + className + "'.");
        }
    }

    public static KRendering getRelationRendering() {
        return figureProvider.createRelation();
    }
    
}
