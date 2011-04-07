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
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;

/**
 * Class for convenient use of "long edges", this are edges covering more than one layer. In the
 * base LGraph data structure it is not possible to access the target of such an edge directly. This
 * class also supplies two tangents for the endpoints, each heading to the adjacent dummynode.
 * 
 * It is necessary to call {@code initialize()} prior usage!
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
     * default constructor. One also need to call {@code initalize()} to initialize all values
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
        // calc startTangent
        LPort start = edge.getSource();
        LNode startNode = start.getNode();
        LPort end = edge.getTarget();
        LNode endNode = end.getNode();

        startPoint = new KVector((startNode.getPosition().x + start.getPosition().x),
                (startNode.getPosition().y + start.getPosition().y));
        startTangent = new KVector(
                (endNode.getPosition().x + end.getPosition().x)
                - (startNode.getPosition().x + start.getPosition().x),
                (endNode.getPosition().y + end.getPosition().y)
                - (startNode.getPosition().y + start.getPosition().y));
        startTangent.normalize();

        // calc endTangent
        LEdge intermediateEdge = edge;
        LNode currentTarget = edge.getSource().getNode();
        LPort port = edge.getSource();
        points.add(new KVector(currentTarget.getPosition().x + port.getPosition().x,
                currentTarget.getPosition().y + port.getPosition().y));
        // run along the edge till end point is found
        do {
            // currently doesnt handle hypergraphs here
            port = intermediateEdge.getTarget();
            currentTarget = port.getNode();
            for (LPort iterPort : currentTarget.getPorts(PortType.OUTPUT)) {
                for (LEdge iterEdge : iterPort.getEdges()) {
                    if (iterEdge.getProperty(Properties.ORIGIN) != null) {
                        intermediateEdge = iterEdge;
                        break;
                    }
                }
                break;
            }
            points.add(new KVector(currentTarget.getPosition().x + port.getPosition().x, currentTarget
                    .getPosition().y
                    + port.getPosition().y));
        } while (intermediateEdge.getTarget().getNode().getProperty(Properties.NODE_TYPE) 
                == Properties.NodeType.LONG_EDGE);

        start = intermediateEdge.getSource();
        startNode = start.getNode();
        end = intermediateEdge.getTarget();
        endNode = end.getNode();
        endPoint = new KVector(
                (endNode.getPosition().x + end.getPosition().x),
                (endNode.getPosition().y + end.getPosition().y));
        endTangent = new KVector(
                (startNode.getPosition().x + start.getPosition().x)
                - (endNode.getPosition().x + end.getPosition().x),
                (startNode.getPosition().y + start.getPosition().y)
                - (endNode.getPosition().y + end.getPosition().y));
        endTangent.normalize();
        target = end;

        currentTarget = end.getNode();
        points.add(new KVector(
                currentTarget.getPosition().x + end.getPosition().x,
                currentTarget.getPosition().y + end.getPosition().y));
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
