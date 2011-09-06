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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.cau.cs.kieler.klighd.piccolo.IChildRepresentedNode;
import de.cau.cs.kieler.klighd.piccolo.PEmptyNode;
import de.cau.cs.kieler.klighd.piccolo.graph.IGraphEdge;
import de.cau.cs.kieler.klighd.piccolo.graph.IGraphNode;
import de.cau.cs.kieler.klighd.piccolo.graph.IGraphPort;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolox.swt.PSWTPath;

/**
 * A Piccolo node wrapping a Pictogram connection.
 * 
 * @author mri
 */
public class ConnectionNode extends PEmptyNode implements IChildRepresentedNode, IPictogramNode,
        IGraphEdge, PropertyChangeListener {

    private static final long serialVersionUID = -8752895610400744167L;

    private Connection connection;
    private AnchorNode sourceAnchor;
    private AnchorNode targetAnchor;

    private PSWTPath polyline = null;

    private float[] xps;
    private float[] yps;

    /** the Piccolo decoration nodes attached to this connection. */
    private List<DecorationNode> decorations = new LinkedList<DecorationNode>();

    /**
     * Constructs a ConnectionNode.
     * 
     * @param connection
     *            the Pictogram connection
     * @param sourceAnchor
     *            the source anchor for this connection
     * @param targetAnchor
     *            the target anchor for this connection
     */
    public ConnectionNode(final Connection connection, final AnchorNode sourceAnchor,
            final AnchorNode targetAnchor) {
        this.connection = connection;
        this.sourceAnchor = sourceAnchor;
        sourceAnchor.addOutgoingConnection(this);
        this.targetAnchor = targetAnchor;
        sourceAnchor.addPropertyChangeListener(AnchorNode.PROPERTY_ANCHOR, this);
        targetAnchor.addPropertyChangeListener(AnchorNode.PROPERTY_ANCHOR, this);
    }

    /**
     * {@inheritDoc}
     */
    public PictogramElement getPictogramElement() {
        return connection;
    }
    
    /**
     * {@inheritDoc}
     */
    public void setRepresentationNode(final PNode representationNode) {
        // set otherwise
    }

    /**
     * {@inheritDoc}
     */
    public PNode getRepresentationNode() {
        return polyline;
    }

    /**
     * Returns the source anchor for this connection.
     * 
     * @return the source anchor
     */
    public AnchorNode getSourceAnchor() {
        return sourceAnchor;
    }

    /**
     * Returns the target anchor for this connection.
     * 
     * @return the target anchor
     */
    public AnchorNode getTargetAnchor() {
        return targetAnchor;
    }

    /**
     * Adds a decoration to the connection.
     * 
     * @param decoration
     *            the Piccolo decoration node
     */
    public void addDecoration(final DecorationNode decoration) {
        addChild(decoration);
        decorations.add(decoration);
    }

    /**
     * Removes a decoration from the connection.
     * 
     * @param decoration
     *            the Piccolo decoration node
     */
    public void removeDecoration(final DecorationNode decoration) {
        decorations.remove(decoration);
        removeChild(decoration);
    }

    /**
     * Sets the polyline used as the main node for this connection.
     * 
     * @param p
     *            the polyline
     */
    public void setPolyline(final PSWTPath p) {
        addChild(p);
        polyline = p;
        // prepare path for polyline
        if (connection instanceof FreeFormConnection) {
            FreeFormConnection ffConnection = (FreeFormConnection) connection;
            EList<Point> bends = ffConnection.getBendpoints();
            xps = new float[bends.size() + 2];
            yps = new float[bends.size() + 2];
            int i = 1;
            for (Point bend : bends) {
                xps[i] = bend.getX();
                yps[i] = bend.getY();
                ++i;
            }
        } else {
            xps = new float[2];
            yps = new float[2];
        }
        // set anchor points
        Point2D source;
        Point2D target;
        if (xps.length > 2) {
            // if bend points are present use them to determine anchor positions
            source = getSourceAnchor().getAnchorPoint(new Point2D.Double(xps[1], yps[1]));
            target =
                    getTargetAnchor().getAnchorPoint(
                            new Point2D.Double(xps[xps.length - 2], yps[yps.length - 2]));
        } else {
            // if no bend points are present use the center points of the connected nodes
            Point2D reference;
            // find reference point for source anchor
            if (targetAnchor.getRepresentationNode() != null) {
                reference = targetAnchor.getRepresentationNode().getGlobalBounds().getCenter2D();
            } else {
                reference = targetAnchor.getReferenceNode().getGlobalBounds().getCenter2D();
            }
            source = getSourceAnchor().getAnchorPoint(reference);
            // find reference point for target anchor
            if (sourceAnchor.getRepresentationNode() != null) {
                reference = sourceAnchor.getRepresentationNode().getGlobalBounds().getCenter2D();
            } else {
                reference = sourceAnchor.getReferenceNode().getGlobalBounds().getCenter2D();
            }
            target = getTargetAnchor().getAnchorPoint(reference);
        }
        // complete the lists for the polyline with the anchor points
        xps[0] = (float) source.getX();
        yps[0] = (float) source.getY();
        xps[xps.length - 1] = (float) target.getX();
        yps[yps.length - 1] = (float) target.getY();
        // update the polyline
        updatePolyline();
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void propertyChange(final PropertyChangeEvent evt) {
        // if (polyline != null) {
        // // update the anchor points
        // if (evt.getSource() == sourceAnchor) {
        // updateSourceAnchor();
        // } else if (evt.getSource() == targetAnchor) {
        // updateTargetAnchor();
        // } else {
        // return;
        // }
        // // update the polyline to reflect the changes to the anchor points
        // updatePolyline();
        // }
    }

    /**
     * Updates the polyline for this connection.
     */
    public void updatePolyline() {
        if (polyline != null) {
            polyline.setPathToPolyline(xps, yps);
        }
        updateDecorations();
    }

    /**
     * Updates the decorations attached to this connection.
     */
    public void updateDecorations() {
        for (DecorationNode decoration : decorations) {
            decoration.updateDecoration(xps, yps);
        }
    }

    private void updateSourceAnchor() {
        Point2D anchorPoint = sourceAnchor.getAnchorPoint(new Point2D.Double(xps[1], yps[1]));
        if (anchorPoint != null) {
            xps[0] = (float) anchorPoint.getX();
            yps[0] = (float) anchorPoint.getY();
        }
    }

    private void updateTargetAnchor() {
        Point2D anchorPoint =
                targetAnchor.getAnchorPoint(new Point2D.Double(xps[xps.length - 2],
                        yps[yps.length - 2]));
        if (anchorPoint != null) {
            xps[xps.length - 1] = (float) anchorPoint.getX();
            yps[yps.length - 1] = (float) anchorPoint.getY();
        }
    }

    // Implementation of the ...kligh.piccolo.graph interfaces

    /**
     * {@inheritDoc}
     */
    public IGraphNode getSource() {
        PNode source = getSourceAnchor().getParent();
        if (source instanceof IGraphNode) {
            return (IGraphNode) source;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public IGraphNode getTarget() {
        PNode target = getTargetAnchor().getParent();
        if (target instanceof IGraphNode) {
            return (IGraphNode) target;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public IGraphPort getSourcePort() {
        AnchorNode anchor = getSourceAnchor();
        if (anchor.isPort()) {
            return anchor;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public IGraphPort getTargetPort() {
        AnchorNode anchor = getTargetAnchor();
        if (anchor.isPort()) {
            return anchor;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void setBends(final List<Point2D> bends) {
        // expand bend point list if necessary
        if (xps.length != bends.size()) {
            int size = bends.size();
            xps = new float[size];
            yps = new float[size];
        }
        for (int i = 0; i < bends.size(); ++i) {
            Point2D bend = bends.get(i);
            xps[i] = (float) bend.getX();
            yps[i] = (float) bend.getY();
        }
        // update anchor points
        updateSourceAnchor();
        updateTargetAnchor();
        // update the polyline to reflect the changes
        updatePolyline();
    }

    /**
     * {@inheritDoc}
     */
    public List<Point2D> getBends() {
        List<Point2D> bends = new ArrayList<Point2D>();
        for (int i = 0; i < xps.length; ++i) {
            bends.add(new Point2D.Double(xps[i], yps[i]));
        }
        return bends;
    }

}
