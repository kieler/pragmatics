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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * A command for applying the result of automatic layout to a Graphiti diagram.
 *
 * @author msp
 */
@SuppressWarnings("restriction")
public class GraphitiLayoutCommand extends RecordingCommand {
    
    /** list of graph elements and pictogram elements to layout. */
    private List<Pair<KGraphElement, PictogramElement>> elements
            = new LinkedList<Pair<KGraphElement, PictogramElement>>();
    /** the feature provider for layout support. */
    private IFeatureProvider featureProvider;

    /**
     * Creates a Graphiti layout command.
     * 
     * @param domain the transactional editing domain
     * @param thefeatureProvider the feature provider
     */
    public GraphitiLayoutCommand(final TransactionalEditingDomain domain,
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
    public void add(final KGraphElement graphElement, final PictogramElement pictogramElement) {
       elements.add(new Pair<KGraphElement, PictogramElement>(graphElement, pictogramElement)); 
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
            } else if (element instanceof KLabel && ((KLabel) element).getParent() instanceof KEdge) {
                applyEdgeLabelLayout((KLabel) element, entry.getSecond());
            }
        }
    }

    /**
     * Apply layout for a port.
     * 
     * @param kport a port
     * @param pelem the corresponding pictogram element
     */
    private void applyPortLayout(final KPort kport, final PictogramElement pelem) {
        KShapeLayout shapeLayout = kport.getData(KShapeLayout.class);
        if (pelem instanceof BoxRelativeAnchor) {
            BoxRelativeAnchor anchor = (BoxRelativeAnchor) pelem;
            KShapeLayout parentLayout = kport.getNode().getData(KShapeLayout.class);
            double parentWidth = parentLayout.getWidth();
            double parentHeight = parentLayout.getHeight();
            double offetx = anchor.getGraphicsAlgorithm().getX();
            double offsety = anchor.getGraphicsAlgorithm().getY();
            double x = shapeLayout.getXpos();
            double y = shapeLayout.getYpos();
            double relWidth = ((x - offetx) / parentWidth);
            double relHeight = ((y - offsety) / parentHeight);

            anchor.setRelativeWidth(relWidth);
            anchor.setRelativeHeight(relHeight);
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
        ga.setX((int) shapeLayout.getXpos());
        ga.setY((int) shapeLayout.getYpos());
        ga.setHeight((int) shapeLayout.getHeight());
        ga.setWidth((int) shapeLayout.getWidth());

        featureProvider.layoutIfPossible(new LayoutContext(pelem));
    }

    /**
     * Apply layout for an edge.
     * 
     * @param kedge an edge
     * @param pelem the corresponding pictogram element
     */
    private void applyEdgeLayout(final KEdge kedge, final PictogramElement pelem) {
        KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
        FreeFormConnection conn = (FreeFormConnection) pelem;
        List<org.eclipse.graphiti.mm.algorithms.styles.Point> pointList = conn.getBendpoints();
        pointList.clear();
        
        // determine the offset for all bend points
        KNode parent = kedge.getSource();
        if (!KimlUtil.isDescendant(kedge.getTarget(), parent)) {
            parent = parent.getParent();
        }
        KVector offset = new KVector();
        KimlUtil.toAbsolute(offset, parent);

        // gather the bend points of the edge
        List<KPoint> allPoints = new ArrayList<KPoint>(edgeLayout.getBendPoints().size() + 2);
        if (conn.getStart() instanceof ChopboxAnchor) {
            allPoints.add(edgeLayout.getSourcePoint());
            moveBendpointOutofNode(kedge.getSource(), allPoints.get(0), offset);
        }
        allPoints.addAll(edgeLayout.getBendPoints());
        if (conn.getEnd() instanceof ChopboxAnchor) {
            allPoints.add(edgeLayout.getTargetPoint());
            moveBendpointOutofNode(kedge.getTarget(), allPoints.get(allPoints.size() - 1), offset);
        }

        // add the bend points to the connection
        for (KPoint kpoint : allPoints) {
            org.eclipse.graphiti.mm.algorithms.styles.Point point = Graphiti.getGaService()
                    .createPoint((int) (kpoint.getX() + offset.x), (int) (kpoint.getY() + offset.y));
            pointList.add(point);
        }
    }

    /** how much to move bend points out of the source or target node. */
    private static final float ENDPOINT_MOVE = 2.0f;
    
    /**
     * Move the given bend point out of the node in order to approximate a source or target point
     * for chopbox anchors.
     * 
     * @param node the node that contains the anchor
     * @param point the bend point to move
     */
    private void moveBendpointOutofNode(final KNode node, final KPoint point, final KVector offset) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        KVector relPos = new KVector(point.getX() + offset.x, point.getY() + offset.y);
        KimlUtil.toRelative(relPos, node);
        float widthPercent = (float) relPos.x / nodeLayout.getWidth();
        float heightPercent = (float) relPos.y / nodeLayout.getHeight();
        if (widthPercent + heightPercent <= 1 && widthPercent - heightPercent <= 0) {
            // port is put to the left
            point.setX(point.getX() - ENDPOINT_MOVE);
        } else if (widthPercent + heightPercent >= 1 && widthPercent - heightPercent >= 0) {
            // port is put to the right
            point.setX(point.getX() + ENDPOINT_MOVE);
        } else if (heightPercent < 1.0f / 2) {
            // port is put to the top
            point.setY(point.getY() - ENDPOINT_MOVE);
        } else {
            // port is put to the bottom
            point.setY(point.getY() + ENDPOINT_MOVE);
        }
    }
    
    /**
     * Apply layout for an edge label.
     * 
     * @param klabel an edge label
     * @param pelem the corresponding pictogram element
     */
    private void applyEdgeLabelLayout(final KLabel klabel, final PictogramElement pelem) {
        GraphicsAlgorithm ga = pelem.getGraphicsAlgorithm();
        ConnectionDecorator decorator = (ConnectionDecorator) pelem;
        org.eclipse.draw2d.geometry.Point referencePoint;
        if (decorator.isLocationRelative()) {
                referencePoint = GraphitiUiInternal.getGefService().getConnectionPointAt(
                        decorator.getConnection(), decorator.getLocation());
        } else {
                referencePoint = GraphitiUiInternal.getGefService().getAbsolutePointOnConnection(
                        decorator.getConnection(), decorator.getLocation());
        }
        
        // get absolute location of the label
        KEdge kedge = (KEdge) klabel.getParent();
        KNode parent = kedge.getSource();
        if (!KimlUtil.isDescendant(kedge.getTarget(), parent)) {
            parent = parent.getParent();
        }
        KShapeLayout shapeLayout = klabel.getData(KShapeLayout.class);
        KVector location = new KVector(shapeLayout.getXpos(), shapeLayout.getYpos());
        KimlUtil.toAbsolute(location, parent);
        
        ga.setX((int) location.x - referencePoint.x);
        ga.setY((int) location.y - referencePoint.y);        
    }

}
