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
package de.cau.cs.kieler.klighd.graphiti.piccolo;

import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.util.PictogramsSwitch;

import de.cau.cs.kieler.klighd.piccolo.graph.IGraphEdge;
import de.cau.cs.kieler.klighd.piccolo.graph.IGraphPort;
import de.cau.cs.kieler.klighd.piccolo.nodes.PChildRepresentedNode;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * A Piccolo node wrapping a Pictogram anchor.
 * 
 * @author mri
 */
public class AnchorNode extends PChildRepresentedNode implements IPictogramNode, IGraphPort,
        PropertyChangeListener {

    private static final long serialVersionUID = 1406179264277421131L;

    /** the property name for the change of the anchor point. */
    public static final String PROPERTY_ANCHOR = "anchor";

    /** the Pictogram anchor represented by this node. */
    private Anchor anchor;
    /** the Piccolo node this anchor references. */
    private PNode reference;

    /** the anchors outgoing connections. */
    private List<IGraphEdge> outgoingConnections = new LinkedList<IGraphEdge>();

    /**
     * Constructs an AnchorNode.
     * 
     * @param anchor
     *            the Pictogram anchor
     * @param reference
     *            the Piccolo node this anchor references
     */
    public AnchorNode(final Anchor anchor, final PNode reference) {
        this.anchor = anchor;
        this.reference = reference;
        // register listener on all parent nodes
        PNode node = reference;
        while (node != null) {
            node.addPropertyChangeListener(PNode.PROPERTY_BOUNDS, this);
            node = node.getParent();
        }
    }

    /**
     * Returns the node that is referenced by this anchor.
     * 
     * @return the node referenced by this anchor
     */
    public PNode getReferenceNode() {
        return reference;
    }

    /**
     * Returns whether this anchor is a port, i.e. has a graphical representation and can be moved.
     * 
     * @return true if this anchor is a port; false else
     */
    public boolean isPort() {
        return getRepresentationNode() != null && getPickable();
    }

    /**
     * {@inheritDoc}
     */
    public PictogramElement getPictogramElement() {
        return anchor;
    }

    /**
     * Returns the absolute coordinates of the anchor point for this anchor. Also updates the
     * position of this anchor accordingly.
     * 
     * @param referencePoint
     *            the reference point for computing the anchor point or null if no such point is
     *            available
     * @return the anchor point or null if no anchor point could be determined
     */
    public Point2D getAnchorPoint(final Point2D referencePoint) {
        if (getRepresentationNode() != null) {
            updateAnchorPosition(referencePoint);
            // chopbox anchor point from representing child node
            PBounds bounds = getRepresentationNode().getGlobalBounds();
            Point2D intersection =
                    getIntersectionWithBounds(bounds.getX() + bounds.getWidth() / 2, bounds.getY()
                            + bounds.getHeight() / 2, referencePoint.getX(), referencePoint.getY(),
                            bounds);
            return intersection;
        } else {
            return getAnchorPosition(referencePoint);
        }
    }

    /**
     * Adds an outgoing connection. Only call from {@code ConnectionNode}.
     * 
     * @param connection
     *            the connection
     */
    void addOutgoingConnection(final ConnectionNode connection) {
        outgoingConnections.add(connection);
    }

    /**
     * Tries to update the position of the anchor.
     * 
     * @param referencePoint
     *            the reference point for computing the anchor position or null if no such point is
     *            available
     */
    public void updateAnchorPosition(final Point2D referencePoint) {
        getAnchorPosition(referencePoint);
    }

    private Point2D getAnchorPosition(final Point2D referencePoint) {
        // get the anchor position depending on the type of this anchor
        Point2D point = new PictogramsSwitch<Point2D>() {
            public Point2D caseBoxRelativeAnchor(final BoxRelativeAnchor object) {
                return getBoxRelativeAnchorPoint(object);
            }

            public Point2D caseChopboxAnchor(final ChopboxAnchor object) {
                return getChopboxAnchorPoint(referencePoint);
            }

            public Point2D caseFixPointAnchor(final FixPointAnchor object) {
                return getFixPointAnchorPoint(object);
            }
        } .doSwitch(anchor);
        // set the position of this anchor to the anchor point
        if (point != null) {
            Point2D position = (Point2D) point.clone();
            setGlobalTranslation(position);
        }
        return point;
    }

    private Point2D getBoxRelativeAnchorPoint(final BoxRelativeAnchor bra) {
        PBounds bounds = reference.getGlobalBounds();
        return new Point2D.Double(bounds.getX() + bounds.getWidth() * bra.getRelativeWidth(),
                bounds.getY() + bounds.getHeight() * bra.getRelativeHeight());
    }

    private Point2D getChopboxAnchorPoint(final Point2D referencePoint) {
        if (referencePoint == null) {
            return null;
        }
        PBounds bounds = reference.getGlobalBounds();
        return getIntersectionWithBounds(bounds.getX() + bounds.getWidth() / 2, bounds.getY()
                + bounds.getHeight() / 2, referencePoint.getX(), referencePoint.getY(), bounds);
    }

    private Point2D getFixPointAnchorPoint(final FixPointAnchor fpa) {
        Point location = fpa.getLocation();
        PBounds bounds = reference.getGlobalBounds();
        return new Point2D.Double(bounds.getX() + location.getX(), bounds.getY() + location.getY());
    }

    private Point2D getIntersectionWithBounds(final double xIn, final double yIn,
            final double xOut, final double yOut, final PBounds bounds) {
        if (xOut <= bounds.getX()) {
            if (yOut <= bounds.getY()) {
                // intersection with top or left line
                Point2D s =
                        computeIntersection(xIn, yIn, xOut, yOut, bounds.getX(), bounds.getY(),
                                bounds.getMaxX(), bounds.getY());
                if (s == null) {
                    s =
                            computeIntersection(xIn, yIn, xOut, yOut, bounds.getX(), bounds.getY(),
                                    bounds.getX(), bounds.getMaxY());
                }
                return s;
            } else if (yOut >= bounds.getMaxY()) {
                // intersection with bottom or left line
                Point2D s =
                        computeIntersection(xIn, yIn, xOut, yOut, bounds.getX(), bounds.getMaxY(),
                                bounds.getMaxX(), bounds.getMaxY());
                if (s == null) {
                    s =
                            computeIntersection(xIn, yIn, xOut, yOut, bounds.getX(), bounds.getY(),
                                    bounds.getX(), bounds.getMaxY());
                }
                return s;
            } else {
                // intersection with left line
                Point2D s =
                        computeIntersection(xIn, yIn, xOut, yOut, bounds.getX(), bounds.getY(),
                                bounds.getX(), bounds.getMaxY());
                return s;
            }
        } else if (xOut >= bounds.getMaxX()) {
            if (yOut <= bounds.getY()) {
                // intersection with top or right line
                Point2D s =
                        computeIntersection(xIn, yIn, xOut, yOut, bounds.getX(), bounds.getY(),
                                bounds.getMaxX(), bounds.getY());
                if (s == null) {
                    s =
                            computeIntersection(xIn, yIn, xOut, yOut, bounds.getMaxX(),
                                    bounds.getY(), bounds.getMaxX(), bounds.getMaxY());
                }
                return s;
            } else if (yOut >= bounds.getMaxY()) {
                // intersection with bottom or right line
                Point2D s =
                        computeIntersection(xIn, yIn, xOut, yOut, bounds.getX(), bounds.getMaxY(),
                                bounds.getMaxX(), bounds.getMaxY());
                if (s == null) {
                    s =
                            computeIntersection(xIn, yIn, xOut, yOut, bounds.getMaxX(),
                                    bounds.getY(), bounds.getMaxX(), bounds.getMaxY());
                }
                return s;
            } else {
                // intersection with right line
                Point2D s =
                        computeIntersection(xIn, yIn, xOut, yOut, bounds.getMaxX(), bounds.getY(),
                                bounds.getMaxX(), bounds.getMaxY());
                return s;
            }
        } else {
            if (yOut <= bounds.getY()) {
                // intersection with top line
                Point2D s =
                        computeIntersection(xIn, yIn, xOut, yOut, bounds.getX(), bounds.getY(),
                                bounds.getMaxX(), bounds.getY());
                return s;
            } else if (yOut >= bounds.getMaxY()) {
                // intersection with bottom line
                Point2D s =
                        computeIntersection(xIn, yIn, xOut, yOut, bounds.getX(), bounds.getMaxY(),
                                bounds.getMaxX(), bounds.getMaxY());
                return s;
            } else {
                // TODO find a better point here
                return new Point2D.Double(xIn, yIn);
            }
        }
    }

    private Point2D computeIntersection(final double xP1, final double yP1, final double xP2,
            final double yP2, final double xP3, final double yP3, final double xP4, final double yP4) {
        double s = (yP4 - yP3) * (xP2 - xP1) - (xP4 - xP3) * (yP2 - yP1);
        // are the line segments parallel?
        if (s == 0) {
            return null;
        }
        double a1 = (xP4 - xP3) * (yP1 - yP3) - (yP4 - yP3) * (xP1 - xP3);
        double a2 = (xP2 - xP1) * (yP1 - yP3) - (yP2 - yP1) * (xP1 - xP3);
        double t1 = a1 / s;
        double t2 = a2 / s;
        // the line segments intersect when t1 and t2 lie in the interval (0,1)
        if (0.0f <= t1 && t1 <= 1 && 0 <= t2 && t2 <= 1) {
            double xD = xP2 - xP1;
            double yD = yP2 - yP1;
            return new Point2D.Double(xP1 + t1 * xD, yP1 + t1 * yD);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void propertyChange(final PropertyChangeEvent evt) {
        firePropertyChange(0, PROPERTY_ANCHOR, null, null);
    }

    // Implementation of the ...kligh.piccolo.graph interfaces

    /**
     * {@inheritDoc}
     */
    public void setRelativeBounds(final PBounds bounds) {
        PAffineTransform transform = getTransformReference(true);
        transform.translate(bounds.getX() - transform.getTranslateX(),
                bounds.getY() - transform.getTranslateY());
        // TODO resize the shape
    }

    /**
     * {@inheritDoc}
     */
    public PBounds getRelativeBounds() {
        PAffineTransform transform = getTransformReference(true);
        PBounds bounds =
                getRepresentationNode() != null ? getRepresentationNode().getBounds() : getBounds();
        PBounds relativeBounds = new PBounds();
        relativeBounds.setRect(transform.getTranslateX(), transform.getTranslateY(),
                bounds.getWidth(), bounds.getHeight());
        return relativeBounds;
    }

    /**
     * {@inheritDoc}
     */
    public List<IGraphEdge> getOutgoingEdges() {
        return outgoingConnections;
    }

}
