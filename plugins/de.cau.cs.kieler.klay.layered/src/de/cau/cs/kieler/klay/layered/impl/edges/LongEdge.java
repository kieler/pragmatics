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
package de.cau.cs.kieler.klay.layered.impl.edges;

import java.util.LinkedList;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;

/**
 * @author uru
 * 
 */
public class LongEdge {

    /** underlying edge. */
    private LEdge edge;
    /** start tangent heading to first dummy node. */
    private KVector startTangent;
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
        startTangent = new KVector((endNode.getPos().x + end.getPos().x)
                - (startNode.getPos().x + start.getPos().x), (endNode.getPos().y + end.getPos().y)
                - (startNode.getPos().y + start.getPos().y));
        startTangent.normalize();

        // calc endTangent
        LEdge intermediateEdge = edge;
        LNode currentTarget = edge.getSource().getNode();
        LPort port = edge.getSource();
        points.add(new KVector(currentTarget.getPos().x + port.getPos().x, currentTarget.getPos().y
                + port.getPos().y));
        // run along the edge till end point is found
        do {
            // currently doesnt handle hypergraphs here
            port = intermediateEdge.getTarget();
            currentTarget = port.getNode();
            for (LPort iterPort : currentTarget.getPorts(PortType.OUTPUT)) {
                for (LEdge iterEdge : iterPort.getEdges()) {
                    if (iterEdge.getOrigin() != null) {
                        intermediateEdge = iterEdge;
                        break;
                    }
                }
                break;
            }
            points.add(new KVector(currentTarget.getPos().x + port.getPos().x, currentTarget
                    .getPos().y
                    + port.getPos().y));
        } while (intermediateEdge.getTarget().getNode().getProperty(Properties.NODE_TYPE) 
                == Properties.NodeType.LONG_EDGE);

        start = intermediateEdge.getSource();
        startNode = start.getNode();
        end = intermediateEdge.getTarget();
        endNode = end.getNode();
        endTangent = new KVector((startNode.getPos().x + start.getPos().x)
                - (endNode.getPos().x + end.getPos().x), (startNode.getPos().y + start.getPos().y)
                - (endNode.getPos().y + end.getPos().y));
        endTangent.normalize();
        target = end;

        currentTarget = end.getNode();
        points.add(new KVector(currentTarget.getPos().x + end.getPos().x, currentTarget.getPos().y
                + end.getPos().y));
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
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Node: " + edge + " StartTangent: " + startTangent + " EndTangent: " + endTangent;
    }
}
