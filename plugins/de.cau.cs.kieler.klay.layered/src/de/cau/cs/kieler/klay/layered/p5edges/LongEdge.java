/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p5edges;

import java.util.LinkedList;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Class for convenient use of "long edges", this are edges covering more than one layer. In the
 * base LGraph data structure it is not possible to access the target of such an edge directly. This
 * class also supplies two tangents for the endpoints, each heading to the adjacent dummy node.
 * 
 * It is necessary to call {@link #initialize()} prior usage!
 * 
 * @author uru
 */
public class LongEdge {

    /** underlying edge. */
    private LEdge edge;
    /** starting point of this edge. */
    private KVector startPoint;
    /** start tangent heading to first dummy node. */
    private KVector startTangent;
    /** end point of this edge. */
    private KVector endPoint;
    /** end tangent heading to last dummy node. */
    private KVector endTangent;
    /** the real target of this edge. */
    private LPort target;
    /** points being passed by this edge. (actually the positions of the dummy nodes) */
    private LinkedList<KVector> points = new LinkedList<KVector>();

    /**
     * default constructor. One also needs to call {@link #initialize()} to initialize all values
     * stored for this long edge.
     * 
     * @param newEdge
     *            underlying edge.
     */
    public LongEdge(final LEdge newEdge) {
        this.edge = newEdge;
    }

    /**
     * calculates the target port/node, and the start/end tangents.
     */
    public void initialize() {
        // calculate start tangent
        LPort start = edge.getSource();
        LPort end = edge.getTarget();

        startPoint = start.getAbsoluteAnchor();
        startTangent = end.getAbsoluteAnchor().sub(startPoint);
        startTangent.normalize();

        // calculate end tangent
        LEdge intermediateEdge = edge;
        LPort port = edge.getSource();
        points.add(port.getAbsoluteAnchor());
        // run along the edge till end point is found
        do {
            // currently doesn't handle hypergraphs here
            port = intermediateEdge.getTarget();
            for (LPort iterPort : port.getNode().getPorts()) {
                for (LEdge iterEdge : iterPort.getOutgoingEdges()) {
                    if (iterEdge.getProperty(Properties.ORIGIN) != null) {
                        intermediateEdge = iterEdge;
                        break;
                    }
                }
                break;
            }
            points.add(port.getAbsoluteAnchor());
        } while (intermediateEdge.getTarget().getNode().getProperty(Properties.NODE_TYPE) 
                == NodeType.LONG_EDGE);

        start = intermediateEdge.getSource();
        end = intermediateEdge.getTarget();
        endPoint = end.getAbsoluteAnchor();
        endTangent = start.getAbsoluteAnchor().sub(endPoint);
        endTangent.normalize();
        target = end;

        points.add(end.getAbsoluteAnchor());
    }

    /**
     * @return the edge
     */
    public LEdge getEdge() {
        return edge;
    }

    /**
     * @return the endTangent
     */
    public KVector getEndTangent() {
        return endTangent;
    }

    /**
     * @return the startTangent
     */
    public KVector getStartTangent() {
        return startTangent;
    }

    /**
     * @return the target
     */
    public LPort getTarget() {
        return target;
    }

    /**
     * @return positions being passed by this long edge (actually these are the positions of the
     *         dummy nodes).
     */
    public LinkedList<KVector> getPoints() {
        return points;
    }

    /**
     * @return the endPoint
     */
    public KVector getEndPoint() {
        return endPoint;
    }

    /**
     * @return the startPoint
     */
    public KVector getStartPoint() {
        return startPoint;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Node: " + edge + " StartTangent: " + startTangent + " EndTangent: " + endTangent;
    }
}
