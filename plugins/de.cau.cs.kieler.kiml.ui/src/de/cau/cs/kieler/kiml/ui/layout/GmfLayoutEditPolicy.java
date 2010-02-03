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
package de.cau.cs.kieler.kiml.ui.layout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.util.KielerMath;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.EdgeRouting;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.kiml.ui.Messages;

/**
 * Edit policy used to apply layout. This edit policy creates a
 * {@link GmfLayoutCommand} to directly manipulate layout data in the
 * GMF notation model.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy
 * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConnectionBendpointEditPolicy
 * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.GraphicalNodeEditPolicy
 */
public class GmfLayoutEditPolicy extends AbstractEditPolicy {

    /** map of edge layouts to existing point lists. */
    private Map<KEdgeLayout, PointList> pointListMap = new HashMap<KEdgeLayout, PointList>();
    
    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#understandsRequest(org.eclipse.gef.Request)
     */
    @Override
    public boolean understandsRequest(final Request req) {
        return (ApplyLayoutRequest.REQ_APPLY_LAYOUT.equals(req.getType()));
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(final Request request) {
        if (ApplyLayoutRequest.REQ_APPLY_LAYOUT.equals(request.getType())) {
            if (request instanceof ApplyLayoutRequest) {
                ApplyLayoutRequest layoutRequest = (ApplyLayoutRequest) request;
                IGraphicalEditPart hostEditPart = (IGraphicalEditPart) getHost();
                GmfLayoutCommand command = new GmfLayoutCommand(
                        hostEditPart.getEditingDomain(), Messages.getString("kiml.ui.5"), //$NON-NLS-1$
                        new EObjectAdapter((View) hostEditPart.getModel()));
                
                // retrieve layout data from the request and compute layout data for the command
                for (Pair<KGraphElement, GraphicalEditPart> layoutPair : layoutRequest.getElements()) {
                    if (layoutPair.getFirst() instanceof KNode) {
                        addShapeLayout(command, layoutPair.getFirst(), layoutPair.getSecond(), null);
                    } else if (layoutPair.getFirst() instanceof KPort) {
                        addShapeLayout(command, layoutPair.getFirst(), layoutPair.getSecond(),
                                KimlLayoutUtil.getShapeLayout(
                                ((KPort) layoutPair.getFirst()).getNode()));
                    } else if (layoutPair.getFirst() instanceof KEdge) {
                        addEdgeLayout(command, (KEdge) layoutPair.getFirst(),
                                (ConnectionEditPart) layoutPair.getSecond());
                    } else if (layoutPair.getFirst() instanceof KLabel) {
                        addEdgeLabelLayout(command, (KLabel) layoutPair.getFirst(),
                                (LabelEditPart) layoutPair.getSecond());
                    }
                }
                
                pointListMap.clear();
                return new ICommandProxy(command);
            } else {
                return null;
            }
        } else {
            return super.getCommand(request);
        }
    }

    /**
     * Adds a shape layout to the given command.
     * 
     * @param command command to which a shape layout shall be added
     * @param kgraphElement graph element with layout data
     * @param editPart edit part to which layout is applied
     * @param offsetLayout layout data of the graph element whose position is added
     *     as offset to the current shape, or {@code null} if no offset shall be added
     */
    private void addShapeLayout(final GmfLayoutCommand command, final KGraphElement kgraphElement,
            final GraphicalEditPart editPart, final KShapeLayout offsetLayout) {
        KShapeLayout layoutData = KimlLayoutUtil.getShapeLayout(kgraphElement);
        Rectangle oldBounds = editPart.getFigure().getBounds();
        Point newLocation = new Point((int) layoutData.getXpos(),
                (int) layoutData.getYpos());
        int offsetx = offsetLayout == null ? 0 : (int) offsetLayout.getXpos();
        int offsety = offsetLayout == null ? 0 : (int) offsetLayout.getYpos();
        if (newLocation.x + offsetx == oldBounds.x && newLocation.y + offsety == oldBounds.y) {
            newLocation = null;
        }
        Dimension newSize = new Dimension((int) layoutData.getWidth(),
                (int) layoutData.getHeight());
        if (newSize.width == oldBounds.width && newSize.height == oldBounds.height) {
            newSize = null;
        }
        if (newLocation != null || newSize != null) {
            View view = (View) editPart.getModel();
            command.addShapeLayout(view, newLocation, newSize);
        }
    }
    
    /**
     * Adds an edge layout to the given command.
     * 
     * @param command command to which an edge layout shall be added
     * @param kedge edge with layout data
     * @param connectionEditPart edit part to which layout is applied
     */
    private void addEdgeLayout(final GmfLayoutCommand command, final KEdge kedge,
            final ConnectionEditPart connectionEditPart) {
        KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(kedge);
        PointList bendPoints = getBendPoints(edgeLayout);
        Rectangle sourceExt, targetExt;

        KShapeLayout sourceLayout = KimlLayoutUtil.getShapeLayout(kedge.getSource());
        KPort sourcePort = kedge.getSourcePort();
        ShapeNodeEditPart sourceEditPart = (ShapeNodeEditPart) connectionEditPart.getSource();
        if (sourcePort != null) {
            KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(sourcePort);
            sourceExt = new Rectangle((int) (portLayout.getXpos()
                    + sourceLayout.getXpos()), (int) (portLayout.getYpos()
                    + sourceLayout.getYpos()), (int) portLayout.getWidth(),
                    (int) portLayout.getHeight());
        } else {
            sourceExt = new Rectangle((int) sourceLayout.getXpos(),
                    (int) sourceLayout.getYpos(),
                    (int) sourceLayout.getWidth(),
                    (int) sourceLayout.getHeight());
        }
        PrecisionPoint sourceRatio = new PrecisionPoint((edgeLayout.getSourcePoint().getX()
                - sourceExt.preciseX()) / sourceExt.preciseWidth(),
                (edgeLayout.getSourcePoint().getY() - sourceExt.preciseY())
                / sourceExt.preciseHeight());
        NodeFigure sourceFigure = (NodeFigure) sourceEditPart.getFigure();
        Rectangle sourceBounds = sourceFigure.getBounds();
        Point sourceAnchorReference = new PrecisionPoint(sourceBounds.preciseX()
                + sourceRatio.preciseX() * sourceBounds.preciseWidth(),
                sourceBounds.preciseY() + sourceRatio.preciseY() * sourceBounds.preciseHeight());
        sourceFigure.translateToAbsolute(sourceAnchorReference);
        ConnectionAnchor sourceAnchor = sourceFigure.getSourceConnectionAnchorAt(sourceAnchorReference);
        String sourceTerminal = sourceEditPart.mapConnectionAnchorToTerminal(sourceAnchor);

        KShapeLayout targetLayout = KimlLayoutUtil.getShapeLayout(kedge.getTarget());
        KPort targetPort = kedge.getTargetPort();
        ShapeNodeEditPart targetEditPart = (ShapeNodeEditPart) connectionEditPart.getTarget();
        if (targetPort != null) {
            KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(targetPort);
            targetExt = new Rectangle((int) (portLayout.getXpos()
                    + targetLayout.getXpos()), (int) (portLayout.getYpos()
                    + targetLayout.getYpos()), (int) portLayout.getWidth(),
                    (int) portLayout.getHeight());
        } else {
            targetExt = new Rectangle((int) targetLayout.getXpos(),
                    (int) targetLayout.getYpos(),
                    (int) targetLayout.getWidth(),
                    (int) targetLayout.getHeight());
        }
        PrecisionPoint targetRatio = new PrecisionPoint((edgeLayout.getTargetPoint().getX()
                - targetExt.preciseX()) / targetExt.preciseWidth(),
                (edgeLayout.getTargetPoint().getY() - targetExt.preciseY())
                / targetExt.preciseHeight());
        NodeFigure targetFigure = (NodeFigure) targetEditPart.getFigure();
        Rectangle targetBounds = targetFigure.getBounds();
        Point targetAnchorReference = new PrecisionPoint(targetBounds.preciseX()
                + targetRatio.preciseX() * targetBounds.preciseWidth(),
                targetBounds.preciseY() + targetRatio.preciseY() * targetBounds.preciseHeight());
        targetFigure.translateToAbsolute(targetAnchorReference);
        ConnectionAnchor targetAnchor = targetFigure.getTargetConnectionAnchorAt(targetAnchorReference);
        String targetTerminal = targetEditPart.mapConnectionAnchorToTerminal(targetAnchor);
        
        command.addEdgeLayout((Edge) connectionEditPart.getModel(),
                bendPoints, sourceTerminal, targetTerminal);
    }
    
    /** see LabelViewConstants.TARGET_LOCATION. */
    private static final int SOURCE_LOCATION = 85;
    /** see LabelViewConstants.MIDDLE_LOCATION. */
    private static final int MIDDLE_LOCATION = 50;
    /** see LabelViewConstants.SOURCE_LOCATION. */
    private static final int TARGET_LOCATION = 15;
    
    /**
     * Adds an edge label layout to the given command.
     * 
     * @param command command to which the edge label layout shall be added
     * @param klabel label with layout data
     * @param labelEditPart edit part to which layout is applied
     */
    private void addEdgeLabelLayout(final GmfLayoutCommand command, final KLabel klabel,
            final LabelEditPart labelEditPart) {
        // get zoom level for offset compensation
        double zoomLevel = 1.0;
        if (labelEditPart.getRoot() instanceof DiagramRootEditPart) {
            zoomLevel = ((DiagramRootEditPart) labelEditPart.getRoot())
                    .getZoomManager().getZoom();
        }

        // calculate direct new location of the label
        KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(klabel);
        ConnectionEditPart connectionEditPart = (ConnectionEditPart) labelEditPart.getParent();
        IFigure sourceFigure = ((GraphicalEditPart) connectionEditPart.getSource())
                .getFigure();
        Point newLocation = new Point(labelLayout.getXpos(),
                labelLayout.getYpos());
        sourceFigure.translateToAbsolute(newLocation);
        newLocation.scale(1 / zoomLevel);
        Rectangle targetBounds = labelEditPart.getFigure().getBounds();
        targetBounds.x = newLocation.x;
        targetBounds.y = newLocation.y;

        // get new bend points for the parent edge
        KEdge kedge = (KEdge) klabel.getParent();
        KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(kedge);
        PointList bendPoints = getBendPoints(edgeLayout);
        PointList absoluteBendPoints = new PointList();
        for (int i = 0; i < bendPoints.size(); i++) {
            Point point = bendPoints.getPoint(i);
            sourceFigure.translateToAbsolute(point);
            point.scale(1 / zoomLevel);
            absoluteBendPoints.addPoint(point);
        }
        
        // get the referencePoint for the label
        int fromEnd;
        switch (labelEditPart.getKeyPoint()) {
        case ConnectionLocator.SOURCE:
            fromEnd = SOURCE_LOCATION;
            break;
        case ConnectionLocator.TARGET:
            fromEnd = TARGET_LOCATION;
            break;
        default:
            fromEnd = MIDDLE_LOCATION;
            break;
        }
        Point refPoint = PointListUtilities.calculatePointRelativeToLine(
                absoluteBendPoints, 0, fromEnd, true);
        
        // get the new relative location
        Point normalPoint = offsetFromRelativeCoordinate(targetBounds,
                absoluteBendPoints, refPoint);
        command.addShapeLayout((View) labelEditPart.getModel(), normalPoint, null);
        
        // invalidate the figure to be sure that it is redrawn
        labelEditPart.getFigure().invalidate();
    }
    
    /**
     * Transform the bend points of the given edge layout into a
     * point list, reusing existing ones if possible. The source and
     * target points of the edge layout are included in the point
     * list.
     * 
     * @param edgeLayout the edge layout
     * @return point list with the bend points of the edge layout
     */
    private PointList getBendPoints(final KEdgeLayout edgeLayout) {
        PointList pointList = pointListMap.get(edgeLayout);
        if (pointList == null) {
            pointList = new PointList();
            KPoint sourcePoint = edgeLayout.getSourcePoint();
            KPoint targetPoint = edgeLayout.getTargetPoint();
            List<KPoint> bendPoints = edgeLayout.getBendPoints();
            pointList.addPoint((int) sourcePoint.getX(), (int) sourcePoint.getY());
            
            EdgeRouting edgeRouting = LayoutOptions.getEnum(edgeLayout, EdgeRouting.class);
            if (edgeRouting == EdgeRouting.SPLINES && bendPoints.size() >= 1) {
                // treat the edge points as control points for splines
                List<KielerMath.Point> controlPoints = new ArrayList<KielerMath.Point>(
                        bendPoints.size() + 2);
                controlPoints.add(new KielerMath.Point(sourcePoint.getX(), sourcePoint.getY()));
                for (KPoint bendPoint : bendPoints) {
                    controlPoints.add(new KielerMath.Point(bendPoint.getX(), bendPoint.getY()));
                }
                controlPoints.add(new KielerMath.Point(targetPoint.getX(), targetPoint.getY()));
                KielerMath.Point[] approxPoints = KielerMath.calcBezierPoints(controlPoints,
                        bendPoints.size() + 2);
                for (KielerMath.Point approxPoint : approxPoints) {
                    pointList.addPoint((int) approxPoint.x, (int) approxPoint.y);
                }
            } else {
                // treat the edge points as normal bend points
                for (KPoint bendPoint : bendPoints) {
                    pointList.addPoint((int) bendPoint.getX(), (int) bendPoint.getY());
                }
                pointList.addPoint((int) targetPoint.getX(), (int) targetPoint.getY());
            }
            
            pointListMap.put(edgeLayout, pointList);
        }
        return pointList;
    }
    
    /**
     * Calculates the label offset from the reference point given the label bounds
     * and a points list.  
     * 
     * @param bounds the {@code Rectangle} that is the bounding box of the label
     * @param points the {@code PointList} that contains that the label offset is relative to
     * @param refPoint the {@code Point} that is the reference point that the offset
     *     is based on
     * @return a {@code Point} which represents a value offset from the {@code refPoint}
     *     point oriented based on the nearest line segment
     */
    public static Point offsetFromRelativeCoordinate(final Rectangle bounds,
            final PointList points, final Point refPoint) {
        Rectangle rect = new Rectangle(bounds);
        // compensate for the fact that we are using the figure center
        rect.translate(rect.width / 2, rect.height / 2);
        Point offset = new Point(rect.x - refPoint.x, rect.y - refPoint.y);
        // calculate slope of line
        if (points.size() == 1) {
            // this is a node...
            return offset;
        } else if (points.size() >= 2) {
            // this is an edge...
            int index = PointListUtilities.findNearestLineSegIndexOfPoint(points, refPoint);
            LineSeg segment = (LineSeg) PointListUtilities.getLineSegments(points).get(index - 1);
            Point normalOffset = null;
            if (segment != null) {
                if (segment.isHorizontal()) {
                    if (segment.getOrigin().x > segment.getTerminus().x) {
                        normalOffset = offset.getNegated();
                        return normalOffset;
                    } else {
                        normalOffset = offset;
                        return normalOffset;
                    }
                } else if (segment.isVertical()) {
                    if (segment.getOrigin().y < segment.getTerminus().y) {
                        normalOffset = offset.scale(-1, 1).transpose();
                        return normalOffset;
                    } else {
                        normalOffset = offset.scale(1, -1).transpose();
                        return normalOffset;
                    }
                } else {
                    Point offsetRefPoint = refPoint.getTranslated(offset);
                    LineSeg parallelSeg = segment.getParallelLineSegThroughPoint(offsetRefPoint);
                    Point p1 = parallelSeg.perpIntersect(refPoint.x, refPoint.y);
                    double dx = p1.getDistance(offsetRefPoint)
                            * ((p1.x > offsetRefPoint.x) ? -1 : 1);
                    double dy = p1.getDistance(refPoint) * ((p1.y < refPoint.y) ? -1 : 1);
                    Point orth = new Point(dx, dy);
                    // reflection in the y axis
                    if (segment.getOrigin().x > segment.getTerminus().x) {
                        orth = orth.scale(-1, -1);
                    }
                    return orth;
                }
            }
        }
        return null;
    }

}
