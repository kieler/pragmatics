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

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolox.swt.PSWTPath;

/**
 * A Piccolo node wrapping a Pictogram connection.
 * 
 * @author mri
 */
public class ConnectionNode extends PNode implements PropertyChangeListener {

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
        this.targetAnchor = targetAnchor;
        sourceAnchor.addPropertyChangeListener(AnchorNode.PROPERTY_ANCHOR, this);
        targetAnchor.addPropertyChangeListener(AnchorNode.PROPERTY_ANCHOR, this);
    }

    /**
     * Returns the Pictogram connection represented by this node.
     * 
     * @return the Pictogram connection
     */
    public Connection getPictogramConnection() {
        return connection;
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
        Point2D source = getSourceAnchor().getAnchorPoint(new Point2D.Double(xps[1], yps[1]));
        Point2D target =
                getTargetAnchor().getAnchorPoint(
                        new Point2D.Double(xps[xps.length - 2], yps[yps.length - 2]));
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
    public void propertyChange(final PropertyChangeEvent evt) {
        // FIXME under rare circumstances this can throw NP exceptions
        if (polyline != null) {
            // update the anchor points
            if (evt.getSource() == sourceAnchor) {
                Point2D anchorPoint =
                        sourceAnchor.getAnchorPoint(new Point2D.Double(xps[1], yps[1]));
                if (anchorPoint != null) {
                    xps[0] = (float) anchorPoint.getX();
                    yps[0] = (float) anchorPoint.getY();
                }
            } else if (evt.getSource() == targetAnchor) {
                Point2D anchorPoint =
                        targetAnchor.getAnchorPoint(new Point2D.Double(xps[xps.length - 2],
                                yps[yps.length - 2]));
                if (anchorPoint != null) {
                    xps[xps.length - 1] = (float) anchorPoint.getX();
                    yps[yps.length - 1] = (float) anchorPoint.getY();
                }
            } else {
                return;
            }
            // update the polyline to reflect the changes to the anchor points
            updatePolyline();
        }
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
}
