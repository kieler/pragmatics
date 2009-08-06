/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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

import java.util.HashMap;
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
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.kiml.ui.Messages;

/**
 * Edit policy used to apply layout. This edit policy creates a
 * {@link GmfLayoutCommand} to directly manipulate layout data in the
 * GMF notation model.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy
 * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConnectionBendpointEditPolicy
 * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.GraphicalNodeEditPolicy
 */
public class GmfLayoutEditPolicy extends AbstractEditPolicy {

    /** map of edge layouts to existing point lists */
    private Map<KEdgeLayout, PointList> pointListMap = new HashMap<KEdgeLayout, PointList>();
    
    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#understandsRequest(org.eclipse.gef.Request)
     */
    @Override
    public boolean understandsRequest(Request req) {
        return (ApplyLayoutRequest.REQ_APPLY_LAYOUT.equals(req.getType()));
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(Request request) {
        if (ApplyLayoutRequest.REQ_APPLY_LAYOUT.equals(request.getType())) {
            if (request instanceof ApplyLayoutRequest) {
                ApplyLayoutRequest layoutRequest = (ApplyLayoutRequest)request;
                IGraphicalEditPart hostEditPart = (IGraphicalEditPart)getHost();
                GmfLayoutCommand command = new GmfLayoutCommand(
                        hostEditPart.getEditingDomain(), Messages.getString("kiml.ui.5"),
                        new EObjectAdapter((View)hostEditPart.getModel()));
                
                // retrieve layout data from the request and compute layout data for the command
                for (Pair<KGraphElement, GraphicalEditPart> layoutPair : layoutRequest.getElements()) {
                    if (layoutPair.first instanceof KNode || layoutPair.first instanceof KPort)
                        addShapeLayout(command, layoutPair.first, layoutPair.second);
                    else if (layoutPair.first instanceof KEdge)
                        addEdgeLayout(command, (KEdge)layoutPair.first,
                                (ConnectionEditPart)layoutPair.second);
                    else if (layoutPair.first instanceof KLabel)
                        addEdgeLabelLayout(command, (KLabel)layoutPair.first,
                                (LabelEditPart)layoutPair.second);
                }
                
                pointListMap.clear();
                return new ICommandProxy(command);
            }
            else return null;
        }
        else
            return super.getCommand(request);
    }

    /**
     * Adds a shape layout to the given command.
     * 
     * @param command command to which a shape layout shall be added
     * @param kgraphElement graph element with layout data
     * @param editPart edit part to which layout is applied
     */
    private void addShapeLayout(GmfLayoutCommand command, KGraphElement kgraphElement,
            GraphicalEditPart editPart) {
        KShapeLayout layoutData = KimlLayoutUtil.getShapeLayout(kgraphElement);
        Rectangle oldBounds = editPart.getFigure().getBounds();
        Point newLocation = new Point(Math.round(layoutData.getXpos()),
                Math.round(layoutData.getYpos()));
        if (newLocation.x == oldBounds.x && newLocation.y == oldBounds.y)
            newLocation = null;
        Dimension newSize = new Dimension(Math.round(layoutData.getWidth()),
                Math.round(layoutData.getHeight()));
        if (newSize.width == oldBounds.width && newSize.height == oldBounds.height)
            newSize = null;
        if (newLocation != null || newSize != null) {
            View view = (View)editPart.getModel();
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
    private void addEdgeLayout(GmfLayoutCommand command, KEdge kedge,
            ConnectionEditPart connectionEditPart) {
        KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(kedge);
        PointList bendPoints = getBendPoints(edgeLayout);
        Rectangle sourceExt, targetExt;

        KShapeLayout sourceLayout = KimlLayoutUtil.getShapeLayout(kedge.getSource());
        KPort sourcePort = kedge.getSourcePort();
        ShapeNodeEditPart sourceEditPart = (ShapeNodeEditPart)connectionEditPart.getSource();
        if (sourcePort != null) {
            KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(sourcePort);
            sourceExt = new Rectangle(Math.round(portLayout.getXpos()
                    + sourceLayout.getXpos()), Math.round(portLayout.getYpos()
                    + sourceLayout.getYpos()), Math.round(portLayout.getWidth()),
                    Math.round(portLayout.getHeight()));
        }
        else {
            sourceExt = new Rectangle(Math.round(sourceLayout.getXpos()),
                    Math.round(sourceLayout.getYpos()),
                    Math.round(sourceLayout.getWidth()),
                    Math.round(sourceLayout.getHeight()));
        }
        PrecisionPoint sourceRatio = new PrecisionPoint((edgeLayout.getSourcePoint().getX()
                - sourceExt.preciseX()) / sourceExt.preciseWidth(),
                (edgeLayout.getSourcePoint().getY() - sourceExt.preciseY())
                / sourceExt.preciseHeight());
        NodeFigure sourceFigure = (NodeFigure)sourceEditPart.getFigure();
        Rectangle sourceBounds = sourceFigure.getBounds();
        Point sourceAnchorReference = new PrecisionPoint(sourceBounds.preciseX()
                + sourceRatio.preciseX() * sourceBounds.preciseWidth(),
                sourceBounds.preciseY() + sourceRatio.preciseY() * sourceBounds.preciseHeight());
        sourceFigure.translateToAbsolute(sourceAnchorReference);
        ConnectionAnchor sourceAnchor = sourceFigure.getSourceConnectionAnchorAt(sourceAnchorReference);
        String sourceTerminal = sourceEditPart.mapConnectionAnchorToTerminal(sourceAnchor);

        KShapeLayout targetLayout = KimlLayoutUtil.getShapeLayout(kedge.getTarget());
        KPort targetPort = kedge.getTargetPort();
        ShapeNodeEditPart targetEditPart = (ShapeNodeEditPart)connectionEditPart.getTarget();
        if (targetPort != null) {
            KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(targetPort);
            targetExt = new Rectangle(Math.round(portLayout.getXpos()
                    + targetLayout.getXpos()), Math.round(portLayout.getYpos()
                    + targetLayout.getYpos()), Math.round(portLayout.getWidth()),
                    Math.round(portLayout.getHeight()));
        }
        else {
            targetExt = new Rectangle(Math.round(targetLayout.getXpos()),
                    Math.round(targetLayout.getYpos()),
                    Math.round(targetLayout.getWidth()),
                    Math.round(targetLayout.getHeight()));
        }
        PrecisionPoint targetRatio = new PrecisionPoint((edgeLayout.getTargetPoint().getX()
                - targetExt.preciseX()) / targetExt.preciseWidth(),
                (edgeLayout.getTargetPoint().getY() - targetExt.preciseY())
                / targetExt.preciseHeight());
        NodeFigure targetFigure = (NodeFigure)targetEditPart.getFigure();
        Rectangle targetBounds = targetFigure.getBounds();
        Point targetAnchorReference = new PrecisionPoint(targetBounds.preciseX()
                + targetRatio.preciseX() * targetBounds.preciseWidth(),
                targetBounds.preciseY() + targetRatio.preciseY() * targetBounds.preciseHeight());
        targetFigure.translateToAbsolute(targetAnchorReference);
        ConnectionAnchor targetAnchor = targetFigure.getTargetConnectionAnchorAt(targetAnchorReference);
        String targetTerminal = targetEditPart.mapConnectionAnchorToTerminal(targetAnchor);
        
        command.addEdgeLayout((Edge)connectionEditPart.getModel(),
                bendPoints, sourceTerminal, targetTerminal);
    }
    
    /**
     * Adds an edge label layout to the given command.
     * 
     * @param command command to which the edge label layout shall be added
     * @param klabel label with layout data
     * @param labelEditPart edit part to which layout is applied
     */
    private void addEdgeLabelLayout(GmfLayoutCommand command, KLabel klabel,
            LabelEditPart labelEditPart) {
        KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(klabel);
        ConnectionEditPart connectionEditPart = (ConnectionEditPart)labelEditPart.getParent();
        IFigure sourceFigure = ((GraphicalEditPart)connectionEditPart.getSource())
                .getFigure();
        Point newLocation = new Point(labelLayout.getXpos(),
                labelLayout.getYpos());
        sourceFigure.translateToAbsolute(newLocation);

        // get new bend points for the parent edge
        KEdge kedge = (KEdge)klabel.getParent();
        KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(kedge);
        PointList bendPoints = getBendPoints(edgeLayout);
        PointList absoluteBendPoints = new PointList();
        for (int i = 0; i < bendPoints.size(); i++){
            Point point = bendPoints.getPoint(i);
            sourceFigure.translateToAbsolute(point);
            absoluteBendPoints.addPoint(point);
        }
        // get the referencePoint for the label in the future
        int fromEnd;
        switch (labelEditPart.getKeyPoint()) {
        case ConnectionLocator.SOURCE:
            fromEnd = 85; // see LabelViewConstants.TARGET_LOCATION
        case ConnectionLocator.TARGET:
            fromEnd = 15; // see LabelViewConstants.SOURCE_LOCATION
        default:
            fromEnd = 50; // see LabelViewConstants.MIDDLE_LOCATION
        }
        Point refPoint = PointListUtilities.calculatePointRelativeToLine(
                absoluteBendPoints, 0, fromEnd, true);
        IFigure labelFigure = labelEditPart.getFigure();
        // get the future bounds of the label
        Rectangle targetBounds = labelFigure.getBounds();
        targetBounds.x = newLocation.x;
        targetBounds.y = newLocation.y;
        // get the new relative location
        Point normalPoint = offsetFromRelativeCoordinate(labelFigure,
                        targetBounds, absoluteBendPoints, refPoint);
        command.addShapeLayout((View)labelEditPart.getModel(),
                normalPoint, null);
        labelFigure.invalidate();
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
    private PointList getBendPoints(KEdgeLayout edgeLayout) {
        PointList pointList = pointListMap.get(edgeLayout);
        if (pointList == null) {
            pointList = new PointList();
            KPoint sourcePoint = edgeLayout.getSourcePoint();
            pointList.addPoint(Math.round(sourcePoint.getX()), Math.round(sourcePoint.getY()));
            for (KPoint bendPoint : edgeLayout.getBendPoints()) {
                pointList.addPoint(Math.round(bendPoint.getX()), Math.round(bendPoint.getY()));
            }
            KPoint targetPoint = edgeLayout.getTargetPoint();
            pointList.addPoint(Math.round(targetPoint.getX()), Math.round(targetPoint.getY()));
            pointListMap.put(edgeLayout, pointList);
        }
        return pointList;
    }
    
    /**
     * Calculates the label offset from the reference point given the label bounds
     * and a points list.  
     * 
     * @param label the {@code IFigure} to calculate the offset for
     * @param bounds the {@code Rectangle} that is the bounding box of the label
     * @param points the {@code PointList} that contains that the label offset is relative to
     * @param refPoint the {@code Point} that is the reference point that the offset
     *     is based on
     * @return a {@code Point} which represents a value offset from the {@code refPoint}
     *     point oriented based on the nearest line segment
     */
    public static Point offsetFromRelativeCoordinate(IFigure label, Rectangle bounds,
            PointList points, Point refPoint) {
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
                    if (segment.getOrigin().x > segment.getTerminus().x)
                        orth = orth.scale(-1, -1);
                    return orth;
                }
            }
        }
        return null;
    }

}
