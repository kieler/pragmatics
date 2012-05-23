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

package de.cau.cs.kieler.kaom.karma.ptolemy.krendering;

import java.util.HashMap;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.krendering.KBackgroundColor;
import de.cau.cs.kieler.core.krendering.KChildArea;
import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KContainerRendering;
import de.cau.cs.kieler.core.krendering.KDirectPlacementData;
import de.cau.cs.kieler.core.krendering.KForegroundColor;
import de.cau.cs.kieler.core.krendering.KGridPlacementData;
import de.cau.cs.kieler.core.krendering.KLineWidth;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KXPosition;
import de.cau.cs.kieler.core.krendering.KYPosition;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Linkable;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.Relation;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.TransformationContext;
import de.cau.cs.kieler.klighd.transformations.AbstractTransformation;

/**
 * 
 * @author ckru
 *
 */
public class KaomKrenderingTransformation extends AbstractTransformation<Entity, KNode> {

    private HashMap<EObject, KGraphElement> map = new HashMap<EObject, KGraphElement>();
    
    /**
     * {@inheritDoc}
     */
    public KNode transform(final Entity model,
            final TransformationContext<Entity, KNode> transformationContext) {
        map.clear();
        KNode parent = KimlUtil.createInitializedNode();
        map.put(model, parent);
        KNode k = transformationhelper(model, parent);
        KShapeLayout layout = k.getData(KShapeLayout.class);
        if (layout != null) {
            layout.setProperty(LayoutOptions.ALGORITHM, "de.cau.cs.kieler.klay.layered");
            layout.setProperty(LayoutOptions.EDGE_ROUTING, EdgeRouting.ORTHOGONAL);
        }
        k = addConnections(model, parent);
        return k;
    }

    private KContainerRendering getKRendering(KGraphElement k) {
        EList<KGraphData> data = k.getData();
        for(KGraphData d: data) {
            if (d instanceof KContainerRendering) {
                return (KContainerRendering) d;
            }
        }
        return null;
    }
    
    private KShapeLayout getKLayout(KGraphElement k) {
        EList<KGraphData> data = k.getData();
        for(KGraphData d: data) {
            if (d instanceof KShapeLayout) {
                return (KShapeLayout) d;
            }
        }
        return null;
    }
    
    private KRendering createTestRendering() {
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        KRectangle rec = factory.createKRectangle();
        /*
        KRectangle rec2 = factory.createKRectangle();
        rec.getChildren().add(rec2);
        KXPosition left = factory.createKLeftPosition();
        left.setAbsolute(10);

        KYPosition top = factory.createKTopPosition();
        top.setAbsolute(10);

        KXPosition right = factory.createKRightPosition();
        right.setAbsolute(30);

        KYPosition bottom = factory.createKBottomPosition();
        bottom.setAbsolute(30);
        KDirectPlacementData placement = factory.createKDirectPlacementData();

        KPosition topleft = factory.createKPosition();
        topleft.setX(left);
        topleft.setY(top);

        KPosition bottomright = factory.createKPosition();
        bottomright.setX(right);
        bottomright.setY(bottom);

        placement.setTopLeft(topleft);
        placement.setBottomRight(bottomright);

        //rec.setChildPlacement(null);
        rec2.setPlacementData(placement);
        KBackgroundColor fill = factory.createKBackgroundColor();
        KBackgroundColor fill2 = factory.createKBackgroundColor();
        rec2.getStyles().add((KBackgroundColor) lookupColor("green", fill));
        
        rec.getStyles().add((KBackgroundColor) lookupColor("red", fill2));
        //rec2.setParent(rec);
         * 
         */
        /*
        KRectangle rec3 = factory.createKRectangle();
        KChildArea ca = factory.createKChildArea();
        KForegroundColor rec3fc = factory.createKForegroundColor();
        KLineWidth lw = factory.createKLineWidth();
        lw.setLineWidth(5);
        KBackgroundColor rec3c = factory.createKBackgroundColor();
        rec3.getStyles().add(lookupColor("black", rec3fc));
        rec3.getStyles().add(lookupColor("white", rec3c));
        rec3.getStyles().add(lw);
        rec3.setPlacementData(EcoreUtil.copy(placement));
        rec3.getChildren().add(ca);
        rec2.getChildren().add(rec3);
        */
        return rec;
    }
    
    private KNode transformationhelper(final Entity element, final KNode parent) {
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        for (Entity e : element.getChildEntities()) {
            KNode n = KimlUtil.createInitializedNode();
            if (e.getName() != null) {
                KLabel l = KimlUtil.createInitializedLabel(n);
                l.setText(e.getName());
            }
            KRendering ren = KRenderingProvider.getKNodeRendering(e);
            KRendering topRen = getKRendering(n);
            KShapeLayout lay = getKLayout(n);
            if (lay != null) {
                lay.setProperty(LayoutOptions.ALGORITHM, "de.cau.cs.kieler.klay.layered");
                lay.setProperty(LayoutOptions.EDGE_ROUTING, EdgeRouting.ORTHOGONAL);
            }
            if (ren != null) {
                if (topRen != null) {
                    getKRendering(n).getChildren().add(ren);
                } else {
                    n.getData().add(ren);
                }
                if (lay != null) {
                    if (ren.getPlacementData() != null && ren.getPlacementData() instanceof KGridPlacementData) {
                        lay.setHeight(((KGridPlacementData) ren.getPlacementData()).getHeightHint());
                        lay.setWidth(((KGridPlacementData) ren.getPlacementData()).getWidthHint());
                    }
                }
            } else {
                lay.setHeight(50);
                lay.setWidth(50);
            }
            map.put(e, n);
            parent.getChildren().add(transformationhelper(e, n));
        }
        for (Relation r : element.getChildRelations()) {
            KNode n = KimlUtil.createInitializedNode();
            map.put(r, n);
            parent.getChildren().add(n);
        }
        for (Port p : element.getChildPorts()) {
            KPort port = KimlUtil.createInitializedPort();
            map.put(p, port);
            parent.getPorts().add(port);
            KRendering topRen = getKRendering(port);
            KRendering ren = KRenderingProvider.getKPortRendering(p);
            KShapeLayout lay = getKLayout(port);
            
            if (p.getName() != null) {
                KLabel l = KimlUtil.createInitializedLabel(port);
                l.setText(p.getName());
            }
            
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
    
    private KNode addConnections(final Entity element, final KNode parent) {
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        for (Link l : element.getChildLinks()) {
            KEdge edge = KimlUtil.createInitializedEdge();
            if (l.getName() != null) {
                KLabel label = KimlUtil.createInitializedLabel(edge);
                label.setText(l.getName());
            }
            KRendering edgeRendering = factory.createKPolyline();
            KEdgeLayout el = null;
            EList<KGraphData> data = edge.getData();
            KContainerRendering topren = null;
            for(KGraphData d: data) {
                if (d instanceof KContainerRendering) {
                    topren = (KContainerRendering) d;
                }
                if (d instanceof KEdgeLayout) {
                    el = (KEdgeLayout) d;
                }
            }
            if (topren == null) {
                edge.getData().add(edgeRendering);
            } else {
                topren.getChildren().add(edgeRendering);
            }
            Linkable source = l.getSource();
            Linkable target = l.getTarget();
            KGraphElement ksource = map.get(source);
            KGraphElement ktarget = map.get(target);
            if (ksource instanceof KNode) {
                edge.setSource((KNode) ksource);
            } else if (ksource instanceof KPort) {
                KNode sourceNode = (KNode)ksource.eContainer();
                edge.setSource(sourceNode);
                edge.setSourcePort((KPort) ksource);
                ((KPort) ksource).getEdges().add(edge);
            }
            
            if (ktarget instanceof KNode) {
                edge.setTarget((KNode) ktarget);
            } else if (ktarget instanceof KPort) {
                KNode targetNode = (KNode)ktarget.eContainer();
                edge.setTarget(targetNode);
                edge.setTargetPort((KPort) ktarget);
                ((KPort) ktarget).getEdges().add(edge);
            }
            int x = 25;
        }
        if (!element.getChildEntities().isEmpty()) {
            for (Entity e : element.getChildEntities()) {
                addConnections(e, (KNode)map.get(e));
            }
        }
        return parent;
    }
    
    private static KColor lookupColor(final String color, final KColor kcolor) {
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
