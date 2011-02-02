/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphiti;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.graphiti.ElementInfo.PortInfo;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * A command for applying the result of automatic layout to a Graphiti diagram.
 * 
 * @author msp
 */
public class GraphitiLayoutCommand extends RecordingCommand {

    /** list of graph elements and pictogram elements to layout. */
    private List<Pair<KGraphElement, PictogramElement>> elements =
            new LinkedList<Pair<KGraphElement, PictogramElement>>();
    /** the feature provider for layout support. */
    private IFeatureProvider featureProvider;
    /** map of edge layouts to corresponding vector chains. */
    private Map<KEdgeLayout, KVectorChain> bendpointsMap =
            new HashMap<KEdgeLayout, KVectorChain>();
    /** Elements map. */
    private Map<KGraphElement, ElementInfo> graphElem2ElemInfoMap;
    /** Elements map. */
    private Map<PictogramElement, ElementInfo> pictElem2ElemInfoMap;

    /**
     * Creates a Graphiti layout command.
     * 
     * @param domain
     *            the transactional editing domain
     * @param thefeatureProvider
     *            the feature provider
     * @param graphElem2PictElemMapParam
     *            elements map
     * @param pictElem2GraphElemMapParam
     *            elements map
     */
    public GraphitiLayoutCommand(
            final TransactionalEditingDomain domain,
            final IFeatureProvider thefeatureProvider,
            final Map<PictogramElement, ElementInfo> pictElem2GraphElemMapParam,
            final Map<KGraphElement, ElementInfo> graphElem2PictElemMapParam) {
        super(domain, "Automatic Layout");
        this.featureProvider = thefeatureProvider;
        this.graphElem2ElemInfoMap = graphElem2PictElemMapParam;
        this.pictElem2ElemInfoMap = pictElem2GraphElemMapParam;
    }

    /**
     * Adds the given element to this command.
     * 
     * @param graphElement
     *            an element of the layout graph
     * @param pictogramElement
     *            the corresponding pictogram element
     */
    public void add(final KGraphElement graphElement,
            final PictogramElement pictogramElement) {
        elements.add(new Pair<KGraphElement, PictogramElement>(graphElement,
                pictogramElement));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        for (Pair<KGraphElement, PictogramElement> entry : elements) {
            KGraphElement element = entry.getFirst();
            if (element instanceof KPort) {
                applyPortLayout((KPort) element, entry.getSecond());
            } else if (element instanceof KNode) {
                applyNodeLayout((KNode) element, entry.getSecond());
            } else if (element instanceof KEdge) {
                applyEdgeLayout((KEdge) element, entry.getSecond());
            } else if (element instanceof KLabel
                    && ((KLabel) element).getParent() instanceof KEdge) {
                applyEdgeLabelLayout((KLabel) element, entry.getSecond());
            }
        }
        bendpointsMap.clear();
    }

    /**
     * Apply layout for a port.
     * 
     * @param kport
     *            a port
     * @param pelem
     *            the corresponding pictogram element
     */
    private void
            applyPortLayout(final KPort kport, final PictogramElement pelem) {
        KShapeLayout shapeLayout = kport.getData(KShapeLayout.class);
        if (pelem instanceof BoxRelativeAnchor) {
            BoxRelativeAnchor anchor = (BoxRelativeAnchor) pelem;
            GraphicsAlgorithm ga = anchor.getReferencedGraphicsAlgorithm();
            PortInfo info = (PortInfo) graphElem2ElemInfoMap.get(kport);
            double parentWidth = ga.getWidth();
            double parentHeight = ga.getHeight();
            double offsetx = anchor.getGraphicsAlgorithm().getX();
            double offsety = anchor.getGraphicsAlgorithm().getY();
            if (info.containerHasInvisibleParent()) {
                offsetx += ga.getX();
                offsety += ga.getY();
            }
            double x = shapeLayout.getXpos();
            double y = shapeLayout.getYpos();
            Pair<Float, Float> ad = info.getOffset();
            if (ad != null) {
                y -= ad.getSecond();
                x -= ad.getFirst();
            }
            double relWidth = (x - offsetx) / parentWidth;
            double relHeight = (y - offsety) / parentHeight;

            anchor.setRelativeWidth(relWidth);
            anchor.setRelativeHeight(relHeight);

            featureProvider.layoutIfPossible(new LayoutContext(pelem));
        }
    }

    /**
     * Apply layout for a node.
     * 
     * @param knode
     *            a node
     * @param pelem
     *            the corresponding pictogram element
     */
    private void
            applyNodeLayout(final KNode knode, final PictogramElement pelem) {
        KShapeLayout shapeLayout = knode.getData(KShapeLayout.class);
        GraphicsAlgorithm ga = pelem.getGraphicsAlgorithm();
        ga.setX(Math.round(shapeLayout.getXpos()));
        ga.setY(Math.round(shapeLayout.getYpos()));
        ga.setHeight(Math.round(shapeLayout.getHeight()));
        ga.setWidth(Math.round(shapeLayout.getWidth()));
        featureProvider.layoutIfPossible(new LayoutContext(pelem));
    }

    /**
     * Apply layout for an edge.
     * 
     * @param kedge
     *            an edge
     * @param pelem
     *            the corresponding pictogram element
     */
    private void
            applyEdgeLayout(final KEdge kedge, final PictogramElement pelem) {
        KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
        FreeFormConnection conn = (FreeFormConnection) pelem;
        List<Point> pointList = conn.getBendpoints();
        pointList.clear();

        // determine the offset for all bend points
        KNode parent = kedge.getSource();
        if (!KimlUtil.isDescendant(kedge.getTarget(), parent)) {
            parent = parent.getParent();
        }
        KVector offset = new KVector();
        KimlUtil.toAbsolute(offset, parent);

        List<ConnectionDecorator> decorators = conn.getConnectionDecorators();
        boolean hasTailDecorator = false;
        boolean hasHeadDecorator = false;
        for (ConnectionDecorator dec : decorators) {
            if (dec.getLocation() == 1.0) {
                hasHeadDecorator = true;
            } else if (dec.getLocation() == 0.0) {
                hasTailDecorator = true;
            }
        }

        // gather the bend points of the edge
        KVectorChain allPoints = new KVectorChain();
        if (conn.getStart() instanceof ChopboxAnchor) {
            KPoint sourcePoint = edgeLayout.getSourcePoint();
            allPoints.add(sourcePoint.getX(), sourcePoint.getY());
            moveBendpointOutofNode(kedge.getSource(), allPoints.get(0), offset);
        } else if (conn.getStart() instanceof BoxRelativeAnchor
                && !hasTailDecorator) {
            KPoint sourcePoint = edgeLayout.getSourcePoint();
            allPoints.add(sourcePoint.getX(), sourcePoint.getY());
            fixFirstBendPoint(allPoints.get(0),
                    (BoxRelativeAnchor) conn.getStart());
        }
        for (KPoint bendPoint : edgeLayout.getBendPoints()) {
            Float x = bendPoint.getX();
            Float y = bendPoint.getY();
            if (x.equals(Float.NaN) || y.equals(Float.NaN)) {
                continue;
            }
            allPoints.add(x, y);
        }
        if (conn.getEnd() instanceof ChopboxAnchor) {
            KPoint targetPoint = edgeLayout.getTargetPoint();
            allPoints.add(targetPoint.getX(), targetPoint.getY());
            moveBendpointOutofNode(kedge.getTarget(),
                    allPoints.get(allPoints.size() - 1), offset);
        } else if (conn.getEnd() instanceof BoxRelativeAnchor) {
            if (allPoints.size() > 1) {
                KVector last = allPoints.get(allPoints.size() - 1);
                if (last.y == edgeLayout.getTargetPoint().getY()) {
                    last.y--;
                }
            }
            if (!hasHeadDecorator) {
                allPoints.add(edgeLayout.getTargetPoint().getX(), edgeLayout
                        .getTargetPoint().getY());
            }
        }

        // transform spline control points into approximated bend points
        EdgeRouting edgeRouting =
                edgeLayout.getProperty(LayoutOptions.EDGE_ROUTING);
        if (edgeRouting == EdgeRouting.SPLINES
                && edgeLayout.getBendPoints().size() >= 1) {
            allPoints = KielerMath.appoximateSpline(allPoints);
        }

        // add the bend points to the connection
        for (KVector kpoint : allPoints) {
            Point point =
                    Graphiti.getGaService().createPoint(
                            (int) Math.round(kpoint.x + offset.x),
                            (int) Math.round(kpoint.y + offset.y));
            pointList.add(point);
        }
    }

    /**
     * Moves the first bend point back onto the edge of the port.
     * 
     * @param sourcePoint
     *            the point to move
     * @param start
     *            the port
     */
    private void fixFirstBendPoint(final KVector sourcePoint,
            final BoxRelativeAnchor start) {
        // undo port center trickery
        ElementInfo.PortInfo info =
                (ElementInfo.PortInfo) pictElem2ElemInfoMap.get(start);
        Pair<Float, Float> ad = info.getOffset();
        sourcePoint.x -= ad.getFirst();
        sourcePoint.y -= ad.getSecond();
        // if (start.getRelativeWidth() == 0) {
        // sourcePoint.x -= start.getGraphicsAlgorithm().getX();
        // } else if (start.getRelativeWidth() == 1) {
        // sourcePoint.x += start.getGraphicsAlgorithm().getX();
        // }
        // if (start.getRelativeHeight() == 0) {
        // sourcePoint.y -= start.getGraphicsAlgorithm().getY();
        // } else if (start.getRelativeHeight() == 1) {
        // sourcePoint.y += start.getGraphicsAlgorithm().getY();
        // }
    }

    /** how much to move bend points out of the source or target node. */
    private static final float ENDPOINT_MOVE = 2.0f;

    /**
     * Move the given bend point out of the node in order to approximate a
     * source or target point for chopbox anchors.
     * 
     * @param node
     *            the node that contains the anchor
     * @param point
     *            the bend point to move
     * @param offset
     *            the vector by which to move the bendpoint
     */
    private void moveBendpointOutofNode(final KNode node, final KVector point,
            final KVector offset) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        KVector relPos = new KVector(point.x + offset.x, point.y + offset.y);
        KimlUtil.toRelative(relPos, node);
        float widthPercent = (float) relPos.x / nodeLayout.getWidth();
        float heightPercent = (float) relPos.y / nodeLayout.getHeight();
        if (widthPercent + heightPercent <= 1
                && widthPercent - heightPercent <= 0) {
            // bend point is put to the left
            point.x -= ENDPOINT_MOVE;
        } else if (widthPercent + heightPercent >= 1
                && widthPercent - heightPercent >= 0) {
            // bend point is put to the right
            point.x += ENDPOINT_MOVE;
        } else if (heightPercent < 1.0f / 2) {
            // bend point is put to the top
            point.y -= ENDPOINT_MOVE;
        } else {
            // bend point is put to the bottom
            point.y += ENDPOINT_MOVE;
        }
    }

    /**
     * Apply layout for an edge label.
     * 
     * @param klabel
     *            an edge label
     * @param pelem
     *            the corresponding pictogram element
     */
    private void applyEdgeLabelLayout(final KLabel klabel,
            final PictogramElement pelem) {
        GraphicsAlgorithm ga = pelem.getGraphicsAlgorithm();
        ConnectionDecorator decorator = (ConnectionDecorator) pelem;
        KEdge kedge = (KEdge) klabel.getParent();

        // get vector chain for the bend points of the edge
        KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
        KVectorChain bendPoints = bendpointsMap.get(edgeLayout);
        if (bendPoints == null) {
            bendPoints = KimlUtil.toVectorChain(edgeLayout);
            bendpointsMap.put(edgeLayout, bendPoints);
        }

        // calculate reference point for the label
        KVector referencePoint;
        if (decorator.isLocationRelative()) {
            referencePoint =
                    bendPoints.getPointOnLine(decorator.getLocation()
                            * bendPoints.getLength());
        } else {
            referencePoint = bendPoints.getPointOnLine(decorator.getLocation());
        }

        KShapeLayout shapeLayout = klabel.getData(KShapeLayout.class);
        ga.setX((int) Math.round(shapeLayout.getXpos() - referencePoint.x));
        ga.setY((int) Math.round(shapeLayout.getYpos() - referencePoint.y));
    }

}
