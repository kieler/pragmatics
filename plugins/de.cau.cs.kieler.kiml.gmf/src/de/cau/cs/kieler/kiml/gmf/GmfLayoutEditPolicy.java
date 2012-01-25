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
package de.cau.cs.kieler.kiml.gmf;

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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.model.gmf.figures.SplineConnection;
import de.cau.cs.kieler.core.model.gmf.util.SplineUtilities;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.diagram.ApplyLayoutRequest;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Edit policy used to apply layout. This edit policy creates a {@link GmfLayoutCommand} to directly
 * manipulate layout data in the GMF notation model.
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean understandsRequest(final Request req) {
        return (ApplyLayoutRequest.REQ_APPLY_LAYOUT.equals(req.getType()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command getCommand(final Request request) {
        if (ApplyLayoutRequest.REQ_APPLY_LAYOUT.equals(request.getType())) {
            if (request instanceof ApplyLayoutRequest) {
                ApplyLayoutRequest layoutRequest = (ApplyLayoutRequest) request;
                IGraphicalEditPart hostEditPart = (IGraphicalEditPart) getHost();
                GmfLayoutCommand command = new GmfLayoutCommand(hostEditPart.getEditingDomain(),
                        Messages.getString("kiml.ui.5"), //$NON-NLS-1$
                        new EObjectAdapter((View) hostEditPart.getModel()));
                float xbound = layoutRequest.getXBound();
                float ybound = layoutRequest.getYBound();

                // retrieve layout data from the request and compute layout data for the command
                for (Pair<KGraphElement, GraphicalEditPart> layoutPair : layoutRequest
                        .getElements()) {
                    if (layoutPair.getFirst() instanceof KNode) {
                        addShapeLayout(command, layoutPair.getFirst(), layoutPair.getSecond());
                    } else if (layoutPair.getFirst() instanceof KPort) {
                        addShapeLayout(command, layoutPair.getFirst(), layoutPair.getSecond());
                    } else if (layoutPair.getFirst() instanceof KEdge) {
                        addEdgeLayout(command, (KEdge) layoutPair.getFirst(),
                                (ConnectionEditPart) layoutPair.getSecond());
                    } else if (layoutPair.getFirst() instanceof KLabel) {
                        addLabelLayout(command, (KLabel) layoutPair.getFirst(),
                                layoutPair.getSecond(), xbound, ybound);
                    }
                }

                // set further options
                IPreferenceStore preferenceStore = KimlUiPlugin.getDefault().getPreferenceStore();
                command.setObliqueRouting(preferenceStore
                        .getBoolean(EclipseLayoutInfoService.PREF_OBLIQUE_ROUTE));

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
     * @param command
     *            command to which a shape layout shall be added
     * @param kgraphElement
     *            graph element with layout data
     * @param editPart
     *            edit part to which layout is applied
     */
    private void addShapeLayout(final GmfLayoutCommand command, final KGraphElement kgraphElement,
            final GraphicalEditPart editPart) {
        KShapeLayout layoutData = kgraphElement.getData(KShapeLayout.class);
        View view = (View) editPart.getModel();
        
        // check whether the location has changed
        Point newLocation = new Point((int) layoutData.getXpos(), (int) layoutData.getYpos());
        Object oldx = ViewUtil.getStructuralFeatureValue(view,
                NotationPackage.eINSTANCE.getLocation_X());
        Object oldy = ViewUtil.getStructuralFeatureValue(view,
                NotationPackage.eINSTANCE.getLocation_Y());
        if (oldx != null && oldy != null && newLocation.x == (Integer) oldx
                && newLocation.y == (Integer) oldy) {
            newLocation = null;
        }
        
        // check whether the size has changed
        Dimension newSize = new Dimension((int) layoutData.getWidth(), (int) layoutData.getHeight());
        Object oldWidth = ViewUtil.getStructuralFeatureValue(view,
                NotationPackage.eINSTANCE.getSize_Width());
        Object oldHeight = ViewUtil.getStructuralFeatureValue(view,
                NotationPackage.eINSTANCE.getSize_Height());
        if (oldWidth != null && oldHeight != null && newSize.width == (Integer) oldWidth
                && newSize.height == (Integer) oldHeight) {
            newSize = null;
        }
        
        if (newLocation != null || newSize != null) {
            command.addShapeLayout(view, newLocation, newSize);
        }
    }

    /**
     * Adds an edge layout to the given command.
     * 
     * @param command
     *            command to which an edge layout shall be added
     * @param kedge
     *            edge with layout data
     * @param connectionEditPart
     *            edit part to which layout is applied
     */
    private void addEdgeLayout(final GmfLayoutCommand command, final KEdge kedge,
            final ConnectionEditPart connectionEditPart) {
        // create source terminal identifier
        KVector sourceRel = getRelativeSourcePoint(kedge);
        INodeEditPart sourceEditPart = (INodeEditPart) connectionEditPart.getSource();
        ConnectionAnchor sourceAnchor = new SlidableAnchor(sourceEditPart.getFigure(),
                new PrecisionPoint(sourceRel.x, sourceRel.y));
        String sourceTerminal = sourceEditPart.mapConnectionAnchorToTerminal(sourceAnchor);
        
        // create target terminal identifier
        KVector targetRel = getRelativeTargetPoint(kedge);
        INodeEditPart targetEditPart = (INodeEditPart) connectionEditPart.getTarget();
        ConnectionAnchor targetAnchor = new SlidableAnchor(targetEditPart.getFigure(),
                new PrecisionPoint(targetRel.x, targetRel.y));
        String targetTerminal = targetEditPart.mapConnectionAnchorToTerminal(targetAnchor);

        PointList bendPoints = getBendPoints(kedge, connectionEditPart.getFigure());
        
        // check whether the connection is a note attachment to an edge, then remove bend points
        if (sourceEditPart instanceof ConnectionEditPart
                || targetEditPart instanceof ConnectionEditPart) {
            while (bendPoints.size() > 2) {
                bendPoints.removePoint(1);
            }
        }
        command.addEdgeLayout((Edge) connectionEditPart.getModel(), bendPoints, sourceTerminal,
                targetTerminal);
    }
    
    /**
     * Create a vector that contains the relative position of the source point to the corresponding
     * source node or port.
     * 
     * @param edge an edge
     * @return the relative source point
     */
    private KVector getRelativeSourcePoint(final KEdge edge) {
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        KNode sourceNode = edge.getSource(), targetNode = edge.getTarget();
        KPoint sourcePoint = edgeLayout.getSourcePoint();
        KVector sourceRel = sourcePoint.createVector();
        KShapeLayout sourceLayout = sourceNode.getData(KShapeLayout.class);
        
        if (KimlUtil.isDescendant(targetNode, sourceNode)) {
            // the target node is contained in the source node
            translateDescendantPoint(sourceRel, sourceLayout);
        } else {
            sourceRel.translate(-sourceLayout.getXpos(), -sourceLayout.getYpos());
        }
        
        if (edge.getSourcePort() != null) {
            // calculate the relative position to the port size
            KShapeLayout portLayout = edge.getSourcePort().getData(KShapeLayout.class);
            if (portLayout.getWidth() <= 0) {
                sourceRel.x = 0;
            } else {
                sourceRel.x = (sourceRel.x - portLayout.getXpos()) / portLayout.getWidth();
            }
            if (portLayout.getHeight() <= 0) {
                sourceRel.y = 0;
            } else {
                sourceRel.y = (sourceRel.y - portLayout.getYpos()) / portLayout.getHeight();
            }
        } else {
            // calculate the relative position to the node size
            if (sourceLayout.getWidth() <= 0) {
                sourceRel.x = 0;
            } else {
                sourceRel.x /= sourceLayout.getWidth();
            }
            if (sourceLayout.getHeight() <= 0) {
                sourceRel.y = 0;
            } else {
                sourceRel.y /= sourceLayout.getHeight();
            }
        }
        
        // check the bound of the relative position
        return sourceRel.applyBounds(0, 0, 1, 1);
    }
    
    /**
     * Create a vector that contains the relative position of the target point to the corresponding
     * target node or port.
     * 
     * @param edge an edge
     * @return the relative target point
     */
    private KVector getRelativeTargetPoint(final KEdge edge) {
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        KNode sourceNode = edge.getSource(), targetNode = edge.getTarget();
        KPoint targetPoint = edgeLayout.getTargetPoint();
        KVector targetRel = targetPoint.createVector();
        KShapeLayout targetLayout = targetNode.getData(KShapeLayout.class);

        if (KimlUtil.isDescendant(targetNode, sourceNode)) {
            // the target node is contained in the source node
            if (sourceNode != targetNode.getParent()) {
                KimlUtil.toAbsolute(targetRel, sourceNode);
                KimlUtil.toRelative(targetRel, targetNode.getParent());
            }
            targetRel.translate(-targetLayout.getXpos(), -targetLayout.getYpos());
        } else if (sourceNode.getParent() != targetNode.getParent()) {
            // the reference point of the target is different from the source
            KimlUtil.toAbsolute(targetRel, sourceNode.getParent());
            KimlUtil.toRelative(targetRel, targetNode.getParent());
            targetRel.translate(-targetLayout.getXpos(), -targetLayout.getYpos());
        } else {
            // source and target have the same reference point
            targetRel.translate(-targetLayout.getXpos(), -targetLayout.getYpos());
        }
        
        if (edge.getTargetPort() != null) {
            // calculate the relative position to the port size
            KShapeLayout portLayout = edge.getTargetPort().getData(KShapeLayout.class);
            if (portLayout.getWidth() <= 0) {
                targetRel.x = 0;
            } else {
                targetRel.x = (targetRel.x - portLayout.getXpos()) / portLayout.getWidth();
            }
            if (portLayout.getHeight() <= 0) {
                targetRel.y = 0;
            } else {
                targetRel.y = (targetRel.y - portLayout.getYpos()) / portLayout.getHeight();
            }
        } else {
            // calculate the relative position to the node size
            if (targetLayout.getWidth() <= 0) {
                targetRel.x = 0;
            } else {
                targetRel.x /= targetLayout.getWidth();
            }
            if (targetLayout.getHeight() <= 0) {
                targetRel.y = 0;
            } else {
                targetRel.y /= targetLayout.getHeight();
            }
        }
        
        // check the bound of the relative position
        return targetRel.applyBounds(0, 0, 1, 1);
    }

    /**
     * Adds the necessary insets of the layout to the given point.
     * 
     * @param point the point to translate.
     * @param layout layout of the node the point is relative to.
     */
    private void translateDescendantPoint(final KVector point, final KShapeLayout layout) {
        // in this case the edge points are given without the source insets, so add them
        KInsets insets = layout.getInsets();
        point.x += insets.getLeft();
        point.y += insets.getTop();
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
     * @param command
     *            command to which the edge label layout shall be added
     * @param klabel
     *            label with layout data
     * @param labelEditPart
     *            edit part to which layout is applied
     */
    private void addLabelLayout(final GmfLayoutCommand command, final KLabel klabel,
            final GraphicalEditPart labelEditPart, final float xbound, final float ybound) {
        KLabeledGraphElement parent = klabel.getParent();
        KShapeLayout labelLayout = klabel.getData(KShapeLayout.class);
        // node and port labels are processed separately
        if (parent instanceof KNode || parent instanceof KPort) {
            View view = (View) labelEditPart.getModel();
            int xpos = (int) labelLayout.getXpos();
            int ypos = (int) labelLayout.getYpos();
            Object oldx = ViewUtil.getStructuralFeatureValue(view,
                    NotationPackage.eINSTANCE.getLocation_X());
            Object oldy = ViewUtil.getStructuralFeatureValue(view,
                    NotationPackage.eINSTANCE.getLocation_Y());
            if (oldx == null || oldy == null || xpos != (Integer) oldx || ypos != (Integer) oldy) {
                command.addShapeLayout(view, new Point(xpos, ypos), null);
            }
            return;
        }
        
        // calculate direct new location of the label
        Rectangle targetBounds = new Rectangle(labelEditPart.getFigure().getBounds());
        targetBounds.x = (int) labelLayout.getXpos();
        targetBounds.y = (int) labelLayout.getYpos();
        if (targetBounds.x < 0 || targetBounds.y < 0 || targetBounds.x > xbound
                || targetBounds.y > ybound || !(parent instanceof KEdge)) {
            // empty labels are just positioned near their reference point
            command.addShapeLayout((View) labelEditPart.getModel(), new Point(), null);
            return;
        }
        
        ConnectionEditPart connectionEditPart = (ConnectionEditPart) labelEditPart.getParent();
        PointList bendPoints = getBendPoints((KEdge) parent, connectionEditPart.getFigure());
        EObject modelElement = connectionEditPart.getNotationView().getElement();
        EdgeLabelPlacement labelPlacement = labelLayout
                .getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT);
        // for labels of the opposite reference of an ecore reference,
        // the list of bend points must be reversed
        if (modelElement instanceof EReference && labelPlacement == EdgeLabelPlacement.TAIL) {
            bendPoints = bendPoints.getCopy();
            bendPoints.reverse();
        }

        // get the referencePoint for the label
        int fromEnd, keyPoint = ConnectionLocator.MIDDLE;
        if (labelEditPart instanceof LabelEditPart) {
            keyPoint = ((LabelEditPart) labelEditPart).getKeyPoint();
        }
        switch (keyPoint) {
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
        Point refPoint = PointListUtilities.calculatePointRelativeToLine(bendPoints, 0, fromEnd, true);

        // get the new relative location
        Point normalPoint = offsetFromRelativeCoordinate(targetBounds, bendPoints, refPoint);
        if (normalPoint != null) {
            command.addShapeLayout((View) labelEditPart.getModel(), normalPoint, null);
        }
    }

    /**
     * Transform the bend points of the given edge layout into a point list, reusing existing ones
     * if possible. The source and target points of the edge layout are included in the point list.
     * 
     * @param edge
     *            the edge for which to fetch bend points
     * @param isSplineEdge
     *            indicates whether the connection supports splines
     * @return point list with the bend points of the edge layout
     */
    private PointList getBendPoints(final KEdge edge, final IFigure edgeFigure) {
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        PointList pointList = pointListMap.get(edgeLayout);
        if (pointList == null) {
            KPoint sourcePoint = edgeLayout.getSourcePoint();
            KPoint targetPoint = edgeLayout.getTargetPoint();
            List<KPoint> bendPoints = edgeLayout.getBendPoints();

            pointList = new PointList(bendPoints.size() + 2);
            pointList.addPoint((int) sourcePoint.getX(), (int) sourcePoint.getY());
            for (KPoint bendPoint : bendPoints) {
                pointList.addPoint((int) bendPoint.getX(), (int) bendPoint.getY());
            }
            pointList.addPoint((int) targetPoint.getX(), (int) targetPoint.getY());
            
            // for connections that support splines the control points are passed without change
            EdgeRouting edgeRouting = edgeLayout.getProperty(LayoutOptions.EDGE_ROUTING);
            if (edgeRouting == EdgeRouting.SPLINES) {
                if (edgeFigure instanceof SplineConnection) {
                    SplineConnection connection = (SplineConnection) edgeFigure;
                    if (connection.getSplineMode() == SplineConnection.SPLINE_OFF) {
                        connection.setSplineMode(SplineConnection.SPLINE_CUBIC);
                    }
                } else if (bendPoints.size() >= 1) {
                    // treat the edge points as control points for splines
                    pointList = SplineUtilities.approximateSpline(pointList);
                }
            } else if (edgeFigure instanceof SplineConnection) {
                SplineConnection connection = (SplineConnection) edgeFigure;
                if (connection.getSplineMode() != SplineConnection.SPLINE_OFF) {
                    connection.setSplineMode(SplineConnection.SPLINE_OFF);
                }
            }

            pointListMap.put(edgeLayout, pointList);
        }
        return pointList;
    }

    /**
     * Calculates the label offset from the reference point given the label bounds and a points
     * list.
     * 
     * @param bounds
     *            the {@code Rectangle} that is the bounding box of the label
     * @param points
     *            the {@code PointList} that the label offset is relative to
     * @param therefPoint
     *            the {@code Point} that is the reference point that the offset is based on, or
     *            {@code null}
     * @return a {@code Point} which represents a value offset from the {@code refPoint} point
     *         oriented based on the nearest line segment, or {@code null} if no such point can be
     *         determined
     */
    public static Point offsetFromRelativeCoordinate(final Rectangle bounds,
            final PointList points, final Point therefPoint) {
        Point refPoint = therefPoint;
        if (refPoint == null) {
            refPoint = points.getFirstPoint();
        }
        // compensate for the fact that we are using the figure center
        bounds.translate(bounds.width / 2, bounds.height / 2);
        Point offset = new Point(bounds.x - refPoint.x, bounds.y - refPoint.y);
        // calculate slope of line
        if (points.size() == 1) {
            // this is a node...
            return offset;
        } else if (points.size() >= 2) {
            // this is an edge...
            int segIndex = PointListUtilities.findNearestLineSegIndexOfPoint(points, refPoint);
            @SuppressWarnings("rawtypes")
            List segmentsList = PointListUtilities.getLineSegments(points);
            if (segIndex <= 0) {
                segIndex = 0;
            } else if (segIndex > segmentsList.size()) {
                segIndex = segmentsList.size() - 1;
            } else {
                segIndex--;
            }
            LineSeg segment = (LineSeg) segmentsList.get(segIndex);
            Point normalOffset = null;
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
                double dx = p1.getDistance(offsetRefPoint) * ((p1.x > offsetRefPoint.x) ? -1 : 1);
                double dy = p1.getDistance(refPoint) * ((p1.y < refPoint.y) ? -1 : 1);
                Point orth = new PrecisionPoint(dx, dy);
                // reflection in the y axis
                if (segment.getOrigin().x > segment.getTerminus().x) {
                    orth = orth.scale(-1, -1);
                }
                return orth;
            }
        }
        return null;
    }

}
