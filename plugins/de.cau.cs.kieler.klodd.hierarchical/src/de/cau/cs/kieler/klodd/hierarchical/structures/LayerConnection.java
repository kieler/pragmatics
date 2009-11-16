/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.hierarchical.structures;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.options.PortConstraints;
import de.cau.cs.kieler.kiml.layout.options.PortSide;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

/**
 * Connection between two layer elements in a layered graph.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class LayerConnection {

    /** side routing position at the source of this connection. */
    private int sourceSidePos = 0;
    /** side routing position at the target of this connection. */
    private int targetSidePos = 0;
    /** back routing position at the source of this connection. */
    private int sourceBackPos = 0;
    /** front routing position at the target of this connection. */
    private int targetFrontPos = 0;
    /** crosswise position of the connection anchor at source. */
    private float sourceAnchorPos = 0.0f;
    /** crosswise position of the connection anchor at target. */
    private float targetAnchorPos = 0.0f;
    /** list of bend points of this connection. */
    private List<KPoint> bendPoints = new LinkedList<KPoint>();
    /** the contained edge object. */
    private KEdge edge;
    /** the source element. */
    private LayerElement sourceElement;
    /** the source port. */
    private KPort sourcePort;
    /** the target element. */
    private LayerElement targetElement;
    /** the target port. */
    private KPort targetPort;

    /**
     * Creates a layer connection with given source and target.
     * 
     * @param theedge the edge that is to be contained in this layer connection
     * @param sourceElem the source element
     * @param thesourcePort the source port
     * @param targetElem the target element
     * @param thetargetPort the target port
     */
    public LayerConnection(final KEdge theedge, final LayerElement sourceElem,
            final KPort thesourcePort, final LayerElement targetElem, final KPort thetargetPort) {
        this.edge = theedge;
        this.sourceElement = sourceElem;
        this.sourcePort = thesourcePort;
        this.targetElement = targetElem;
        this.targetPort = thetargetPort;
        KimlLayoutUtil.getEdgeLayout(theedge).getBendPoints().clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + sourceElement.toString() + "] > [" + targetElement.toString() + "]";
    }

    /**
     * Applies the layout of this layer connection to the contained edge.
     * 
     * @param offset offset to be added to each bend point
     * @param insets insets of the containing parent layout node
     */
    public void applyLayout(final KPoint offset, final KInsets insets) {
        LayeredGraph layeredGraph = sourceElement.getLayer().getLayeredGraph();
        LayoutDirection layoutDirection = layeredGraph.getLayoutDirection();
        KShapeLayout sourcePortLayout = (sourcePort == null ? null : KimlLayoutUtil
                .getShapeLayout(sourcePort));
        KShapeLayout targetPortLayout = (targetPort == null ? null : KimlLayoutUtil
                .getShapeLayout(targetPort));
        KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);

        // subtract insets values from bend points near fixed external ports
        boolean subSourceXInset = false, subSourceYInset = false,
                subTargetXInset = false, subTargetYInset = false;
        if (layeredGraph.getExternalPortConstraints() == PortConstraints.FIXED_POS) {
            if (sourcePort != null && sourcePort.getNode() == layeredGraph.getParentNode()) {
                PortSide sourceSide = LayoutOptions.getPortSide(sourcePortLayout);
                if (sourceSide == PortSide.NORTH || sourceSide == PortSide.SOUTH) {
                    subSourceXInset = true;
                } else {
                    subSourceYInset = true;
                }
            }
            if (targetPort != null && targetPort.getNode() == layeredGraph.getParentNode()) {
                PortSide targetSide = LayoutOptions.getPortSide(targetPortLayout);
                if (targetSide == PortSide.NORTH || targetSide == PortSide.SOUTH) {
                    subTargetXInset = true;
                } else {
                    subTargetYInset = true;
                }
            }
        }

        // set bend points
        for (KPoint point : bendPoints) {
            point.setX(point.getX() + offset.getX());
            point.setY(point.getY() + offset.getY());
            edgeLayout.getBendPoints().add(point);
        }

        // calculate position of source point
        KPoint sourcePoint = KLayoutDataFactory.eINSTANCE.createKPoint();
        if (sourcePort != null) {
            if (sourcePort.getNode() == layeredGraph.getParentNode()) {
                sourcePoint.setX(sourceElement.getPosition().getX() + sourcePortLayout.getWidth() / 2);
                if (subSourceXInset) {
                    sourcePoint.setX(sourcePoint.getX() - insets.getLeft());
                    if (!bendPoints.isEmpty()) {
                        KPoint bendPoint = bendPoints.get(0);
                        bendPoint.setX(bendPoint.getX() - insets.getLeft());
                    }
                }
                sourcePoint.setY(sourceElement.getPosition().getY() + sourcePortLayout.getHeight() / 2);
                if (subSourceYInset) {
                    sourcePoint.setY(sourcePoint.getY() - insets.getTop());
                    if (!bendPoints.isEmpty()) {
                        KPoint bendPoint = bendPoints.get(0);
                        bendPoint.setY(bendPoint.getY() - insets.getTop());
                    }
                }
                toExternalEndpoint(sourcePoint, LayoutOptions.getPortSide(sourcePortLayout), insets);
            } else {
                sourcePoint.setX(sourcePortLayout.getXpos() + sourcePortLayout.getWidth() / 2
                        + sourceElement.getPosition().getX() + sourceElement.getPosOffset().getX());
                sourcePoint.setY(sourcePortLayout.getYpos() + sourcePortLayout.getHeight() / 2
                        + sourceElement.getPosition().getY() + sourceElement.getPosOffset().getY());
            }
        } else if (sourceElement.getElemObj() instanceof KNode) {
            KNode sourceNode = (KNode) sourceElement.getElemObj();
            KShapeLayout sourceLayout = KimlLayoutUtil.getShapeLayout(sourceNode);
            sourcePoint.setX(sourceElement.getPosition().getX()
                    + sourceElement.getPosOffset().getX()
                    + (layoutDirection == LayoutDirection.DOWN ? sourceLayout.getWidth() / 2
                            : sourceLayout.getWidth()));
            sourcePoint.setY(sourceElement.getPosition().getY()
                    + sourceElement.getPosOffset().getY()
                    + (layoutDirection == LayoutDirection.DOWN ? sourceLayout.getHeight()
                            : sourceLayout.getHeight() / 2));
            edgeLayout.setSourcePoint(sourcePoint);
        }

        // calculate position of target point
        KPoint targetPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
        if (targetPort != null) {
            if (targetPort.getNode() == layeredGraph.getParentNode()) {
                targetPoint.setX(targetElement.getPosition().getX() + targetPortLayout.getWidth() / 2);
                if (subTargetXInset) {
                    targetPoint.setX(targetPoint.getX() - insets.getLeft());
                    if (!bendPoints.isEmpty()) {
                        KPoint bendPoint = bendPoints.get(bendPoints.size() - 1);
                        bendPoint.setX(bendPoint.getX() - insets.getLeft());
                    }
                }
                targetPoint.setY(targetElement.getPosition().getY() + targetPortLayout.getHeight() / 2);
                if (subTargetYInset) {
                    targetPoint.setY(targetPoint.getY() - insets.getTop());
                    if (!bendPoints.isEmpty()) {
                        KPoint bendPoint = bendPoints.get(bendPoints.size() - 1);
                        bendPoint.setY(bendPoint.getY() - insets.getTop());
                    }
                }
                toExternalEndpoint(targetPoint, LayoutOptions.getPortSide(targetPortLayout), insets);
            } else {
                targetPoint.setX(targetPortLayout.getXpos() + targetPortLayout.getWidth() / 2
                        + targetElement.getPosition().getX() + targetElement.getPosOffset().getX());
                targetPoint.setY(targetPortLayout.getYpos() + targetPortLayout.getHeight() / 2
                        + targetElement.getPosition().getY() + targetElement.getPosOffset().getY());
            }
        } else if (targetElement.getElemObj() instanceof KNode) {
            KNode targetNode = (KNode) targetElement.getElemObj();
            KShapeLayout targetLayout = KimlLayoutUtil.getShapeLayout(targetNode);
            targetPoint
                    .setX(targetElement.getPosition().getX()
                            + targetElement.getPosOffset().getX()
                            + (layoutDirection == LayoutDirection.DOWN ? targetLayout.getWidth() / 2
                                    : 0.0f));
            targetPoint
                    .setY(targetElement.getPosition().getY()
                            + targetElement.getPosOffset().getY()
                            + (layoutDirection == LayoutDirection.DOWN ? 0.0f : targetLayout
                                    .getHeight() / 2));
            edgeLayout.setTargetPoint(targetPoint);
        }

        // align the endpoints of the edge if they go to ports
        if (sourcePort != null) {
            alignEndpoint(sourcePoint, bendPoints.isEmpty() ? targetPoint : bendPoints.get(0),
                    sourcePortLayout.getWidth(), sourcePortLayout.getHeight());
            edgeLayout.setSourcePoint(sourcePoint);
        }

        if (targetPort != null) {
            alignEndpoint(targetPoint, bendPoints.isEmpty() ? sourcePoint : bendPoints.get(bendPoints
                    .size() - 1), targetPortLayout.getWidth(), targetPortLayout.getHeight());
            edgeLayout.setTargetPoint(targetPoint);
        }
    }

    /**
     * Gets the source element.
     * 
     * @return the sourceElement
     */
    public LayerElement getSourceElement() {
        return sourceElement;
    }

    /**
     * Gets the source port.
     * 
     * @return the sourcePort
     */
    public KPort getSourcePort() {
        return sourcePort;
    }

    /**
     * Gets the target element.
     * 
     * @return the targetElement
     */
    public LayerElement getTargetElement() {
        return targetElement;
    }

    /**
     * Gets the target port.
     * 
     * @return the targetPort
     */
    public KPort getTargetPort() {
        return targetPort;
    }

    /**
     * Determines the source position of this edge from the current layout
     * position.
     * 
     * @param minDist minimal distance between elements
     * @return position
     */
    public float calcSourcePos(final float minDist) {
        LayeredGraph layeredGraph = sourceElement.getLayer().getLayeredGraph();
        LayoutDirection layoutDirection = layeredGraph.getLayoutDirection();
        sourceAnchorPos = layoutDirection == LayoutDirection.DOWN ? sourceElement.getPosition()
                .getX() : sourceElement.getPosition().getY();
        if (sourceSidePos == 0) {
            if (sourcePort == null) {
                // there is no source port, find appropriate anchor
                sourceAnchorPos += layoutDirection == LayoutDirection.DOWN ? sourceElement
                        .getRealWidth() / 2 : sourceElement.getRealHeight() / 2;
            } else {
                // align the edge to the source port
                KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(sourcePort);
                sourceAnchorPos += layoutDirection == LayoutDirection.DOWN
                        ? portLayout.getWidth() / 2 : portLayout.getHeight() / 2;
                if (sourcePort.getNode() != layeredGraph.getParentNode()) {
                    sourceAnchorPos += layoutDirection == LayoutDirection.DOWN ? portLayout
                            .getXpos()
                            + sourceElement.getPosOffset().getX() : portLayout.getYpos()
                            + sourceElement.getPosOffset().getY();
                }
            }
        } else if (sourceSidePos > 0) {
            sourceAnchorPos += (layoutDirection == LayoutDirection.DOWN ? sourceElement
                    .getRealWidth() : sourceElement.getRealHeight())
                    + sourceSidePos * minDist;
        } else if (sourceSidePos < 0) {
            sourceAnchorPos += sourceSidePos * minDist;
        }
        return sourceAnchorPos;
    }

    /**
     * Determines the target position of this edge from the current layout
     * position.
     * 
     * @param minDist minimal distance between elements
     * @return position
     */
    public float calcTargetPos(final float minDist) {
        LayeredGraph layeredGraph = sourceElement.getLayer().getLayeredGraph();
        LayoutDirection layoutDirection = layeredGraph.getLayoutDirection();
        targetAnchorPos = layoutDirection == LayoutDirection.DOWN ? targetElement.getPosition()
                .getX() : targetElement.getPosition().getY();
        if (getTargetSidePos() == 0) {
            if (targetPort == null) {
                // there is no source port, find appropriate anchor
                targetAnchorPos += layoutDirection == LayoutDirection.DOWN ? targetElement
                        .getRealWidth() / 2 : targetElement.getRealHeight() / 2;
            } else {
                KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(targetPort);
                targetAnchorPos += layoutDirection == LayoutDirection.DOWN
                        ? portLayout.getWidth() / 2 : portLayout.getHeight() / 2;
                if (targetPort.getNode() != layeredGraph.getParentNode()) {
                    targetAnchorPos += layoutDirection == LayoutDirection.DOWN ? portLayout
                            .getXpos()
                            + targetElement.getPosOffset().getX() : portLayout.getYpos()
                            + targetElement.getPosOffset().getY();
                }
            }
        } else if (getTargetSidePos() > 0) {
            targetAnchorPos += (layoutDirection == LayoutDirection.DOWN ? targetElement
                    .getRealWidth() : targetElement.getRealHeight())
                    + getTargetSidePos() * minDist;
        } else if (getTargetSidePos() < 0) {
            targetAnchorPos += getTargetSidePos() * minDist;
        }
        return targetAnchorPos;
    }

    /**
     * Sets the proper position of an endpoint that goes to an external port.
     * 
     * @param endpoint endpoint to align
     * @param portSide side of external port used as endpoint
     * @param insets insets of the parent layout node
     */
    private void toExternalEndpoint(final KPoint endpoint, final PortSide portSide,
            final KInsets insets) {
        switch (portSide) {
        case NORTH:
            endpoint.setY(endpoint.getY() - insets.getTop());
            break;
        case EAST:
            endpoint.setX(endpoint.getX() + insets.getRight());
            break;
        case SOUTH:
            endpoint.setY(endpoint.getY() + insets.getBottom());
            break;
        case WEST:
            endpoint.setX(endpoint.getX() - insets.getLeft());
            break;
        }
    }

    /**
     * Adds or subtracts the width or height of the given endpoint size,
     * depending on the relative position of the next point.
     * 
     * @param endpoint endpoint to align
     * @param next next point on the edge
     * @param width width of the endpoint object
     * @param height height of the endpoint object
     */
    private void alignEndpoint(final KPoint endpoint, final KPoint next, final float width,
            final float height) {
        if (next.getX() > endpoint.getX()) {
            endpoint.setX(endpoint.getX() + width / 2);
        } else if (next.getY() > endpoint.getY()) {
            endpoint.setY(endpoint.getY() + height / 2);
        } else if (next.getX() < endpoint.getX()) {
            endpoint.setX(endpoint.getX() - width / 2);
        } else if (next.getY() < endpoint.getY()) {
            endpoint.setY(endpoint.getY() - height / 2);
        }
    }

    /**
     * Sets the source side position.
     *
     * @param thesourceSidePos the source side position to set
     */
    public void setSourceSidePos(final int thesourceSidePos) {
        this.sourceSidePos = thesourceSidePos;
    }

    /**
     * Returns the source side position.
     *
     * @return the source side position
     */
    public int getSourceSidePos() {
        return sourceSidePos;
    }

    /**
     * Sets the target side position.
     *
     * @param thetargetSidePos the target side position to set
     */
    public void setTargetSidePos(final int thetargetSidePos) {
        this.targetSidePos = thetargetSidePos;
    }

    /**
     * Returns the target side position.
     *
     * @return the target side position
     */
    public int getTargetSidePos() {
        return targetSidePos;
    }

    /**
     * Sets the source back position.
     *
     * @param thesourceBackPos the source back position to set
     */
    public void setSourceBackPos(final int thesourceBackPos) {
        this.sourceBackPos = thesourceBackPos;
    }

    /**
     * Returns the source back position.
     *
     * @return the source back position
     */
    public int getSourceBackPos() {
        return sourceBackPos;
    }

    /**
     * Sets the target front position.
     *
     * @param thetargetFrontPos the target front position to set
     */
    public void setTargetFrontPos(final int thetargetFrontPos) {
        this.targetFrontPos = thetargetFrontPos;
    }

    /**
     * Returns the target front position.
     *
     * @return the target front position
     */
    public int getTargetFrontPos() {
        return targetFrontPos;
    }

    /**
     * Returns the source anchor position.
     *
     * @return the source anchor position
     */
    public float getSourceAnchorPos() {
        return sourceAnchorPos;
    }

    /**
     * Returns the target anchor position.
     *
     * @return the target anchor position
     */
    public float getTargetAnchorPos() {
        return targetAnchorPos;
    }

    /**
     * Returns the bend points.
     *
     * @return the bend points
     */
    public List<KPoint> getBendPoints() {
        return bendPoints;
    }

}
