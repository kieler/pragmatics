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

import java.util.EnumSet;
import java.util.HashMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.RGB;

import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.krendering.KAction;
import de.cau.cs.kieler.core.krendering.KAreaPlacementData;
import de.cau.cs.kieler.core.krendering.KBackground;
import de.cau.cs.kieler.core.krendering.KBottomPosition;
import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KContainerRendering;
import de.cau.cs.kieler.core.krendering.KDecoratorPlacementData;
import de.cau.cs.kieler.core.krendering.KForeground;
import de.cau.cs.kieler.core.krendering.KLeftPosition;
import de.cau.cs.kieler.core.krendering.KLineWidth;
import de.cau.cs.kieler.core.krendering.KPolygon;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KRightPosition;
import de.cau.cs.kieler.core.krendering.KRoundedBendsPolyline;
import de.cau.cs.kieler.core.krendering.KShadow;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.KTopPosition;
import de.cau.cs.kieler.core.krendering.Trigger;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Linkable;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.Relation;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.NodeLabelPlacement;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.SizeConstraint;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.transformations.AbstractDiagramSynthesis;

/**
 * 
 * KRendering transformation to transform a kaom model into a klight one.
 * 
 * @author ckru
 * 
 */
public class KaomKrenderingTransformation extends AbstractDiagramSynthesis<Entity> {

    /**
     * id of this transformation.
     */
    public static final String ID =
            "de.cau.cs.kieler.kaom.klighd.ptolemy.KaomKrenderingTransformation";

    private HashMap<EObject, KGraphElement> map = new HashMap<EObject, KGraphElement>();

    private static final RGB COMPOSITE_BACKGROUND_COLOR = new RGB(16, 78, 139);
    private static final int COMPOSITE_BACKGROUND_ALPHA = 10;
    private static final float EDGE_LINE_WIDTH = 2;

    /**
     * {@inheritDoc}
     */
    public KNode transform(final Entity model) {
        map.clear();
        KNode parent = KimlUtil.createInitializedNode();
        map.put(model, parent);
        KNode k = transformationhelper(model, parent);
        KShapeLayout layout = k.getData(KShapeLayout.class);
        if (layout != null) {
            layout.setProperty(LayoutOptions.ALGORITHM, "de.cau.cs.kieler.klay.layered");
            layout.setProperty(LayoutOptions.EDGE_ROUTING, EdgeRouting.ORTHOGONAL);
            if (model.getChildEntities() == null || model.getChildEntities().isEmpty()) {
                layout.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE);

            }
        }
        k = addConnections(model, parent);

        for (KNode child : parent.getChildren()) {
            child.getData(KShapeLayout.class).setProperty(
                    KlighdConstants.KLIGHD_SELECTION_UNPICKABLE, true);
            child.getData(KShapeLayout.class).setProperty(KlighdConstants.EXPAND, true);
        }

        return k;
    }

    /**
     * Try to get the KRendering of an KGraphElement. Return null if none was found.
     * 
     * @param k
     *            the KGraphElement element so search in
     * @return the found KRendering or null
     */
    private static KContainerRendering getKRendering(final KGraphElement k) {
        EList<KGraphData> data = k.getData();
        for (KGraphData d : data) {
            if (d instanceof KContainerRendering) {
                return (KContainerRendering) d;
            }
        }
        return null;
    }

    /**
     * Try to get the KShapeLayout of an KGraphElement. Return null if none was found.
     * 
     * @param k
     *            the KGraphElement element so search in
     * @return the found KShapeLayout or null
     */
    private static KShapeLayout getKLayout(final KGraphElement k) {
        EList<KGraphData> data = k.getData();
        for (KGraphData d : data) {
            if (d instanceof KShapeLayout) {
                return (KShapeLayout) d;
            }
        }
        return null;
    }

    /**
     * Recursive method to do the actual transformation.
     * 
     * @param element
     *            the parent entity
     * @param parent
     *            the parent knode
     * @return the root knode of the diagram segment transformed
     */
    private KNode transformationhelper(final Entity element, final KNode parent) {
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        // transform all child entities
        for (Entity e : element.getChildEntities()) {
            KNode n = KimlUtil.createInitializedNode();
            KLabel l;

            // create a label in case the entity has a name
            if (e.getName() != null) {
                l = KimlUtil.createInitializedLabel(n);
                l.setText(e.getName());
                KText t = factory.createKText();
                t.setProperty(KlighdConstants.KLIGHD_SELECTION_UNPICKABLE, true);
                l.getData().add(t);
                KShapeLayout labelLayout = getKLayout(l);
                labelLayout.setProperty(LayoutOptions.FONT_NAME, KlighdConstants.DEFAULT_FONT_NAME);
                labelLayout.setProperty(LayoutOptions.FONT_SIZE, KlighdConstants.DEFAULT_FONT_SIZE);
                labelLayout.setProperty(KlighdConstants.KLIGHD_SELECTION_UNPICKABLE, true);

            }

            // get/create a rendering representation of the entity
            KRendering ren = KRenderingProvider.getKNodeRendering(e);
            KRendering collapsedRen = null;

            // if entity has children make it expandable
            if (!e.getChildEntities().isEmpty()) {

                // get/create the collapsed rendering of the entity
                collapsedRen = KRenderingProvider.getPtolemySvgRendering(e);
                collapsedRen.setProperty(KlighdConstants.COLLAPSED_RENDERING, true);

                KAction a = KRenderingFactory.eINSTANCE.createKAction();
                a.setTrigger(Trigger.DOUBLECLICK);
                a.setId(KlighdConstants.ACTION_COLLAPSE_EXPAND);
                collapsedRen.getActions().add(a);

            }

            KRendering topRen = getKRendering(n);
            KShapeLayout lay = getKLayout(n);

            // configure the layout
            if (lay != null) {
                // statemachines use the dot layouter
                if (e.getAnnotation("initialStateName") != null) {
                    lay.setProperty(LayoutOptions.ALGORITHM, "de.cau.cs.kieler.graphviz.dot");
                    lay.setProperty(LayoutOptions.DIRECTION, Direction.RIGHT);
                    // everything else uses klight
                } else {
                    lay.setProperty(LayoutOptions.ALGORITHM, "de.cau.cs.kieler.klay.layered");
                    lay.setProperty(LayoutOptions.EDGE_ROUTING, EdgeRouting.ORTHOGONAL);
                    lay.setProperty(LayoutOptions.NODE_LABEL_PLACEMENT,
                            NodeLabelPlacement.outsideTopCenter());
                    // if (e.getChildEntities() == null || e.getChildEntities().isEmpty()) {
                    lay.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE);

                    // }
                }
            }

            // if a rendering could be created apply it to the knode
            if (ren != null) {
                if (topRen != null) {
                    getKRendering(n).getChildren().add(ren);
                } else {
                    if (collapsedRen != null) {
                        n.getData().add(collapsedRen);

                        // add a shadow
                        KShadow shadow = KRenderingFactory.eINSTANCE.createKShadow();
                        KColor color = KRenderingFactory.eINSTANCE.createKColor();
                        shadow.setColor(color);
                        ren.getStyles().add(shadow);

                        // add a cummulating background
                        KBackground bg = KRenderingFactory.eINSTANCE.createKBackground();
                        KColor bgColor = KRenderingFactory.eINSTANCE.createKColor();
                        bgColor.setRed(COMPOSITE_BACKGROUND_COLOR.red);
                        bgColor.setGreen(COMPOSITE_BACKGROUND_COLOR.green);
                        bgColor.setBlue(COMPOSITE_BACKGROUND_COLOR.blue);
                        bg.setAlpha(COMPOSITE_BACKGROUND_ALPHA);
                        bg.setColor(bgColor);
                        ren.getStyles().add(bg);

                    }
                    n.getData().add(ren);
                }

                // set the size of the knode according to the rendering
                if (lay != null) {
                    if (ren.getPlacementData() != null
                            && ren.getPlacementData() instanceof KAreaPlacementData) {
                        lay.setHeight(((KAreaPlacementData) ren.getPlacementData())
                                .getBottomRight().getY().getAbsolute());
                        lay.setWidth(((KAreaPlacementData) ren.getPlacementData()).getBottomRight()
                                .getX().getAbsolute());

                    } else {
                        KVector minSize = new KVector(60, 40);
                        lay.setProperty(KlighdConstants.MINIMAL_NODE_SIZE, minSize);
                        lay.setProperty(LayoutOptions.SIZE_CONSTRAINT,
                                EnumSet.of(SizeConstraint.MINIMUM_SIZE));
                    }
                }
            } else {
                lay.setHeight(50);
                lay.setWidth(50);
            }
            map.put(e, n);
            KNode child = transformationhelper(e, n);
            getKLayout(child).setProperty(KlighdConstants.EXPAND, false);
            parent.getChildren().add(child);
        }

        // transform the child relations
        for (Relation r : element.getChildRelations()) {
            KNode n = KimlUtil.createInitializedNode();
            KRendering topRen = getKRendering(n);
            // get rendering of relations
            KRendering ren = KRenderingProvider.getRelationRendering();
            KShapeLayout lay = getKLayout(n);
            // apply the renderign
            if (topRen != null) {
                getKRendering(n).getChildren().add(ren);
            } else {
                n.getData().add(ren);
            }

            // set size and layout options
            if (lay != null) {
                lay.setHeight(10);
                lay.setWidth(10);
                lay.setProperty(LayoutOptions.HYPERNODE, true);
            }

            map.put(r, n);
            parent.getChildren().add(n);
        }
        // transform the child ports
        for (Port p : element.getChildPorts()) {
            KPort port = KimlUtil.createInitializedPort();
            map.put(p, port);
            parent.getPorts().add(port);
            KRendering topRen = getKRendering(port);
            KShapeLayout lay = getKLayout(port);
            lay.setProperty(KlighdConstants.KLIGHD_SELECTION_UNPICKABLE, true);
            // chsch: the following call performs side effects on 'lay'
            KRendering ren = KRenderingProvider.getKPortRendering(p, lay, getKLayout(parent));

            if (topRen != null) {
                getKRendering(port).getChildren().add(ren);
            } else {
                port.getData().add(ren);
            }

            if (lay != null) {
                lay.setHeight(8);
                lay.setWidth(8);
            }

        }

        return parent;
    }

    /**
     * Add connections to the KNode.
     * 
     * @param element
     *            the Entity whose child connections to add
     * @param parent
     *            the KNode to add connections to
     * @return the KNode with added connections
     */
    private KNode addConnections(final Entity element, final KNode parent) {
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        for (Link l : element.getChildLinks()) {
            // create edge labels
            KEdge edge = KimlUtil.createInitializedEdge();
            if (l.getName() != null) {
                KLabel label = KimlUtil.createInitializedLabel(edge);
                label.setText(l.getName());
            }
            KPolyline edgeRendering = null;
            Annotation ptolemyClass = ((Annotatable) l.getSource()).getAnnotation("ptolemyClass");

            // state transitions look a little different
            if ((ptolemyClass != null)
                    && ((StringAnnotation) ptolemyClass).getValue().equals(
                            "ptolemy.domains.modal.kernel.State")) {
                edgeRendering = factory.createKSpline();
                KLineWidth linewidth = factory.createKLineWidth();
                linewidth.setLineWidth(EDGE_LINE_WIDTH);
                edgeRendering.getStyles().add(linewidth);
                this.addArrowToEdge(edgeRendering);

                // edgeRendering
            } else {
                edgeRendering = factory.createKRoundedBendsPolyline();
                ((KRoundedBendsPolyline) edgeRendering).setBendRadius(5);
                KLineWidth linewidth = factory.createKLineWidth();
                linewidth.setLineWidth(EDGE_LINE_WIDTH);
                edgeRendering.getStyles().add(linewidth);
            }

            EList<KGraphData> data = edge.getData();
            KContainerRendering topren = null;
            for (KGraphData d : data) {
                if (d instanceof KContainerRendering) {
                    topren = (KContainerRendering) d;
                }
            }
            if (topren == null) {
                edge.getData().add(edgeRendering);
            } else {
                topren.getChildren().add(edgeRendering);
            }

            // set source and target
            Linkable source = l.getSource();
            Linkable target = l.getTarget();
            KGraphElement ksource = map.get(source);
            KGraphElement ktarget = map.get(target);
            if (ksource instanceof KNode) {
                edge.setSource((KNode) ksource);
            } else if (ksource instanceof KPort) {
                KNode sourceNode = (KNode) ksource.eContainer();
                edge.setSource(sourceNode);
                edge.setSourcePort((KPort) ksource);
                ((KPort) ksource).getEdges().add(edge);
            }

            if (ktarget instanceof KNode) {
                edge.setTarget((KNode) ktarget);
            } else if (ktarget instanceof KPort) {
                KNode targetNode = (KNode) ktarget.eContainer();
                edge.setTarget(targetNode);
                edge.setTargetPort((KPort) ktarget);
                ((KPort) ktarget).getEdges().add(edge);
            }
        }
        // just to be save. unlikely to actually happen
        if (!element.getChildEntities().isEmpty()) {
            for (Entity e : element.getChildEntities()) {
                addConnections(e, (KNode) map.get(e));
            }
        }
        return parent;
    }

    /**
     * Add an arrow to the edge rendering. Used for state transitions, normal ptolemy connection
     * don't have arrows.
     * 
     * @param edgeRendering
     *            the connection to add the arrow to.
     */
    private void addArrowToEdge(final KPolyline edgeRendering) {
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        KPolygon arrow = factory.createKPolygon();

        // create the KPoints used to define the polygon
        KLeftPosition px1 = factory.createKLeftPosition();
        px1.setRelative(0);
        KTopPosition py1 = factory.createKTopPosition();
        py1.setRelative(0);
        KPosition p1 = factory.createKPosition();
        p1.setX(px1);
        p1.setY(py1);
        arrow.getPoints().add(p1);

        KLeftPosition px2 = factory.createKLeftPosition();
        px2.setRelative(0.4f);
        KTopPosition py2 = factory.createKTopPosition();
        py2.setRelative(0.5f);
        KPosition p2 = factory.createKPosition();
        p2.setX(px2);
        p2.setY(py2);
        arrow.getPoints().add(p2);

        KLeftPosition px3 = factory.createKLeftPosition();
        px3.setRelative(0);
        KBottomPosition py3 = factory.createKBottomPosition();
        py3.setRelative(0);
        KPosition p3 = factory.createKPosition();
        p3.setX(px3);
        p3.setY(py3);
        arrow.getPoints().add(p3);

        KRightPosition px4 = factory.createKRightPosition();
        px4.setRelative(0);
        KTopPosition py4 = factory.createKTopPosition();
        py4.setRelative(0.5f);
        KPosition p4 = factory.createKPosition();
        p4.setX(px4);
        p4.setY(py4);
        arrow.getPoints().add(p4);

        // set the colors
        KForeground fg = factory.createKForeground();
        KColor color = factory.createKColor();
        color.setBlue(0);
        color.setGreen(0);
        color.setRed(0);
        fg.setColor(color);

        arrow.getStyles().add(fg);

        KBackground bg = factory.createKBackground();
        KColor colorbg = factory.createKColor();
        colorbg.setBlue(0);
        colorbg.setGreen(0);
        colorbg.setRed(0);
        bg.setColor(colorbg);

        arrow.getStyles().add(bg);

        // place the decorator on the connection
        KDecoratorPlacementData decoratorPlacement = factory.createKDecoratorPlacementData();
        decoratorPlacement.setRotateWithLine(true);
        decoratorPlacement.setRelative(1f);
        decoratorPlacement.setAbsolute(-3f);
        decoratorPlacement.setWidth(7);
        decoratorPlacement.setXOffset(-5f);
        decoratorPlacement.setHeight(5);
        decoratorPlacement.setYOffset(-2.5f);

        arrow.setPlacementData(decoratorPlacement);
        edgeRendering.getChildren().add(arrow);

    }

}
