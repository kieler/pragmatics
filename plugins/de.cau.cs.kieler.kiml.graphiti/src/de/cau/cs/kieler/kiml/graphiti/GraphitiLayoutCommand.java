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
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
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
    
    /** node property for the insets caused by invisible shapes. */
    public static final IProperty<KInsets> INVIS_INSETS = new Property<KInsets>(
            "graphiti.layout.invisibleInsets");

    /** list of graph elements and pictogram elements to layout. */
    private List<Pair<KGraphElement, PictogramElement>> elements =
            new LinkedList<Pair<KGraphElement, PictogramElement>>();
    /** the feature provider for layout support. */
    private IFeatureProvider featureProvider;
    /** map of edge layouts to corresponding vector chains. */
    private Map<KEdgeLayout, KVectorChain> bendpointsMap =
            new HashMap<KEdgeLayout, KVectorChain>();

    /**
     * Creates a Graphiti layout command.
     * 
     * @param domain the transactional editing domain
     * @param thefeatureProvider the feature provider
     */
    public GraphitiLayoutCommand(
            final TransactionalEditingDomain domain,
            final IFeatureProvider thefeatureProvider) {
        super(domain, "Automatic Layout");
        this.featureProvider = thefeatureProvider;
    }

    /**
     * Adds the given element to this command.
     * 
     * @param graphElement an element of the layout graph
     * @param pictogramElement the corresponding pictogram element
     */
    public void add(final KGraphElement graphElement,
            final PictogramElement pictogramElement) {
        KGraphData layoutData = graphElement.getData(graphElement instanceof KEdge
                ? KEdgeLayout.class : KShapeLayout.class);
        if (!layoutData.getProperty(LayoutOptions.NO_LAYOUT)) {
            elements.add(new Pair<KGraphElement, PictogramElement>(graphElement,
                    pictogramElement));
        }
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
                    && ((KLabel) element).eContainer() instanceof KEdge) {
                applyEdgeLabelLayout((KLabel) element, entry.getSecond());
            }
        }
        bendpointsMap.clear();
    }

    /**
     * Apply layout for a port.
     * 
     * @param kport a port
     * @param pelem the corresponding pictogram element
     */
    private void applyPortLayout(final KPort kport, final PictogramElement pelem) {
        KShapeLayout shapeLayout = kport.getData(KShapeLayout.class);
        applyPortLayout(shapeLayout.getXpos(), shapeLayout.getYpos(), pelem, kport.getNode());
    }
    
    /**
     * Apply layout for a port.
     * 
     * @param xpos x position of the port
     * @param ypos y position of the port
     * @param pelem the pictogram element
     * @param knode the node to which the port is connected
     */
    private void applyPortLayout(final double xpos, final double ypos,
            final PictogramElement pelem, final KNode knode) {
        int offsetx = 0, offsety = 0;
        if (pelem.getGraphicsAlgorithm() != null) {
            offsetx = pelem.getGraphicsAlgorithm().getX();
            offsety = pelem.getGraphicsAlgorithm().getY();
        }
        if (pelem instanceof BoxRelativeAnchor) {
            BoxRelativeAnchor anchor = (BoxRelativeAnchor) pelem;
            
            KShapeLayout nodeLayout = knode.getData(KShapeLayout.class);
            double relWidth = (xpos - offsetx) / nodeLayout.getWidth();
            if (relWidth < 0) {
                relWidth = 0;
            }
            if (relWidth > 1) {
                relWidth = 1;
            }
            double relHeight = (ypos - offsety) / nodeLayout.getHeight();
            if (relHeight < 0) {
                relHeight = 0;
            }
            if (relHeight > 1) {
                relHeight = 1;
            }

            anchor.setRelativeWidth(relWidth);
            anchor.setRelativeHeight(relHeight);

            featureProvider.layoutIfPossible(new LayoutContext(pelem));
        } else if (pelem instanceof FixPointAnchor) {
            FixPointAnchor anchor = (FixPointAnchor) pelem;

            anchor.getLocation().setX((int) (xpos - offsetx));
            anchor.getLocation().setY((int) (ypos - offsety));
        }
    }

    /**
     * Apply layout for a node.
     * 
     * @param knode a node
     * @param pelem the corresponding pictogram element
     */
    private void applyNodeLayout(final KNode knode, final PictogramElement pelem) {
        KShapeLayout shapeLayout = knode.getData(KShapeLayout.class);
        GraphicsAlgorithm ga = pelem.getGraphicsAlgorithm();
        float xpos = shapeLayout.getXpos();
        float ypos = shapeLayout.getYpos();
        if (knode.getParent() != null) {
            KInsets parentInsets = knode.getParent().getData(KShapeLayout.class)
                    .getProperty(INVIS_INSETS);
            if (parentInsets != null) {
                xpos += parentInsets.getLeft();
                ypos += parentInsets.getRight();
            }
        }
        float width = shapeLayout.getWidth();
        float height = shapeLayout.getHeight();
        KInsets nodeInsets = shapeLayout.getProperty(INVIS_INSETS);
        if (nodeInsets != null) {
            xpos -= nodeInsets.getLeft();
            ypos -= nodeInsets.getTop();
            width += nodeInsets.getLeft() + nodeInsets.getRight();
            height += nodeInsets.getTop() + nodeInsets.getBottom();
        }
        ga.setX(Math.round(xpos));
        ga.setY(Math.round(ypos));
        ga.setWidth(Math.round(width));
        ga.setHeight(Math.round(height));
        featureProvider.layoutIfPossible(new LayoutContext(pelem));
    }

    /**
     * Apply layout for an edge.
     * 
     * @param kedge an edge
     * @param pelem the corresponding pictogram element
     */
    private void applyEdgeLayout(final KEdge kedge, final PictogramElement pelem) {
        // create bend points for the edge
        KVectorChain bendPoints = getBendPoints(kedge);

        if (pelem instanceof FreeFormConnection) {
            FreeFormConnection connection = (FreeFormConnection) pelem;
            List<Point> pointList = connection.getBendpoints();
            // add the bend points to the connection, reusing existing points
            for (int i = 0; i < bendPoints.size(); i++) {
                KVector kpoint = bendPoints.get(i);
                if (i >= pointList.size()) {
                    Point point = Graphiti.getGaService().createPoint((int) Math.round(kpoint.x),
                                    (int) Math.round(kpoint.y));
                    pointList.add(point);
                } else {
                    Point point = pointList.get(i);
                    point.setX((int) Math.round(kpoint.x));
                    point.setY((int) Math.round(kpoint.y));
                }
            }
            while (pointList.size() > bendPoints.size()) {
                pointList.remove(pointList.size() - 1);
            }
            
            // set source anchor position, if not already set via a port
            if (kedge.getSourcePort() == null) {
                KNode source = kedge.getSource();
                KPoint sourcePoint = kedge.getData(KEdgeLayout.class).getSourcePoint();
                float xpos = sourcePoint.getX(), ypos = sourcePoint.getY();
                if (KimlUtil.isDescendant(kedge.getTarget(), source)) {
                    KInsets insets = source.getData(KShapeLayout.class).getInsets();
                    xpos += insets.getLeft();
                    ypos += insets.getTop();
                } else {
                    KShapeLayout sourceLayout = source.getData(KShapeLayout.class);
                    xpos -= sourceLayout.getXpos();
                    ypos -= sourceLayout.getYpos();
                }
                applyPortLayout(xpos, ypos, connection.getStart(), source);
            }
            
            // set target anchor position, if not already set via a port
            if (kedge.getTargetPort() == null) {
                KNode target = kedge.getTarget();
                KVector targetPoint = kedge.getData(KEdgeLayout.class).getTargetPoint().createVector();
                KNode referenceNode = kedge.getSource();
                if (!KimlUtil.isDescendant(target, referenceNode)) {
                    referenceNode = referenceNode.getParent();
                }
                KimlUtil.toAbsolute(targetPoint, referenceNode);
                KimlUtil.toRelative(targetPoint, target);
                applyPortLayout(targetPoint.x, targetPoint.y, connection.getEnd(), target);
            }
        }
    }
    
    /**
     * Get a vector chain holding the bend points for the given edge.
     * 
     * @param edge a layout edge
     * @return the bend points for the edge
     */
    public KVectorChain getBendPoints(final KEdge edge) {
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        KVectorChain bendPoints = bendpointsMap.get(edgeLayout);
        if (bendPoints == null) {
            // determine the offset for all bend points
            KNode parent = edge.getSource();
            if (!KimlUtil.isDescendant(edge.getTarget(), parent)) {
                parent = parent.getParent();
            }
            KVector offset = new KVector();
            KimlUtil.toAbsolute(offset, parent);

            // gather the bend points of the edge
            bendPoints = new KVectorChain();
            KPoint sourcePoint = edgeLayout.getSourcePoint();
            bendPoints.add(sourcePoint.getX() + offset.x, sourcePoint.getY() + offset.y);
            for (KPoint bendPoint : edgeLayout.getBendPoints()) {
                bendPoints.add(bendPoint.getX() + offset.x, bendPoint.getY() + offset.y);
            }
            KPoint targetPoint = edgeLayout.getTargetPoint();
            bendPoints.add(targetPoint.getX() + offset.x, targetPoint.getY() + offset.y);

            // transform spline control points into approximated bend points
            EdgeRouting edgeRouting = edgeLayout.getProperty(LayoutOptions.EDGE_ROUTING);
            if (edgeRouting == EdgeRouting.SPLINES
                    && edgeLayout.getBendPoints().size() >= 1) {
                bendPoints = KielerMath.appoximateSpline(bendPoints);
            }
            
            bendPoints.removeFirst();
            bendPoints.removeLast();
            bendpointsMap.put(edgeLayout, bendPoints);
        }
        return bendPoints;
    }

    /**
     * Apply layout for an edge label.
     * 
     * @param klabel an edge label
     * @param pelem the corresponding pictogram element
     */
    private void applyEdgeLabelLayout(final KLabel klabel,
            final PictogramElement pelem) {
        GraphicsAlgorithm ga = pelem.getGraphicsAlgorithm();
        ConnectionDecorator decorator = (ConnectionDecorator) pelem;
        KEdge kedge = (KEdge) klabel.eContainer();

        // get vector chain for the bend points of the edge
        KVectorChain bendPoints = new KVectorChain(getBendPoints(kedge));
        KVector sourcePoint = calculateAnchorEnds(kedge.getSource(), kedge.getSourcePort());
        bendPoints.addFirst(sourcePoint);
        KVector targetPoint = calculateAnchorEnds(kedge.getTarget(), kedge.getTargetPort());
        bendPoints.addLast(targetPoint);

        // calculate reference point for the label
        KVector referencePoint;
        if (decorator.isLocationRelative()) {
            referencePoint = bendPoints.getPointOnLine(decorator.getLocation()
                            * bendPoints.getLength());
        } else {
            referencePoint = bendPoints.getPointOnLine(decorator.getLocation());
        }
        
        KShapeLayout shapeLayout = klabel.getData(KShapeLayout.class);
        KVector position = shapeLayout.createVector();
        KNode parent = kedge.getSource();
        if (!KimlUtil.isDescendant(kedge.getTarget(), parent)) {
            parent = parent.getParent();
        }
        KimlUtil.toAbsolute(position, parent);
        ga.setX((int) Math.round(position.x - referencePoint.x));
        ga.setY((int) Math.round(position.y - referencePoint.y));
    }

    /**
     * Returns an end point for an anchor.
     * 
     * @param node the node that owns the anchor
     * @param port the port that represents the anchor
     */
    private KVector calculateAnchorEnds(final KNode node, final KPort port) {
        KVector pos = new KVector();
        if (port != null) {
            // the anchor end is represented by a port (box-relative anchor or fix-point anchor)
            KShapeLayout portLayout = port.getData(KShapeLayout.class);
            pos.x = portLayout.getXpos() + portLayout.getWidth() / 2;
            pos.y = portLayout.getYpos() + portLayout.getHeight() / 2;
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            pos.x += nodeLayout.getXpos();
            pos.y += nodeLayout.getYpos();
        } else {
            // the anchor end is calculated by a chopbox anchor
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            pos.x = nodeLayout.getWidth() / 2 + nodeLayout.getXpos();
            pos.y = nodeLayout.getHeight() / 2 + nodeLayout.getYpos();
        }
        KimlUtil.toAbsolute(pos, node.getParent());
        return pos;
    }

}
