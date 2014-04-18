/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.cola.graph;

import java.util.List;

import org.adaptagrams.ColaEdge;
import org.adaptagrams.Dim;
import org.adaptagrams.Rectangle;
import org.adaptagrams.SeparationConstraint;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.cola.properties.ColaProperties;
import de.cau.cs.kieler.klay.cola.properties.InternalColaProperties;

/**
 * @author uru
 */
public class CPort extends CShape {

    private static final long serialVersionUID = -2142404691531934943L;

    // CHECKSTYLEOFF VisibilityModifier
    // CHECKSTYLEOFF Javadoc

    /**
     * The index of the edge that connects this dummy port node with the port's parent node.
     */
    public int cEdgeIndex = -1;
    /**
     * The ideal length of the edge connecting this port to its owner. External ports do not have
     * such an edge.
     */
    public double idealDummyEdgeLength;

    public PortSide side = PortSide.UNDEFINED;

    /**
     * The owner of this port, this is the node that contains the port. In case of an external
     * port, this field might be {@code null}.
     */
    public final CNode owner;
    protected List<CEdge> outgoingEdges = Lists.newLinkedList();
    protected List<CEdge> incomingEdges = Lists.newLinkedList();

    private boolean external = false;

    /**
     * 
     */
    public CPort(final CGraph graph, final CNode parentNode) {
        super(graph);
        this.owner = parentNode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        
        double xPos = 0;
        double yPos = 0;

        // should previously assigned positions be considered?
        boolean considerPreviousPositions =
                graph.getProperty(ColaProperties.CONSIDER_PREVIOUS_POSITION);
        if (considerPreviousPositions) {
            xPos = this.getPos().x;
            yPos = this.getPos().y;
        }

        // create the adaptagrams rectangle
        rect = new Rectangle(xPos, 
                Math.max(1 + xPos, xPos + this.getSize().x), 
                yPos, 
                Math.max(1 + yPos, yPos + this.getSize().y));
        // assign it an id
        cIndex = graph.nodeIndex++;
        graph.nodes.add(rect);

        System.out.println("Initialized " + this);
    }

    public CPort asExternalDummy() {

        double borderSpacing = graph.getProperty(LayoutOptions.BORDER_SPACING);

        // FIXME this should really be done only once for all external ports
        double leftMostX = Double.MAX_VALUE;
        CNode leftMost = null;
        double rightMostX = Double.MIN_VALUE;
        CNode rightMost = null;

        // find the leftmost node
        for (CNode n : graph.getChildren()) {
            double maxX = n.getPos().x + n.getSize().x + n.getMargins().right;
            double minX = n.getPos().x - n.getMargins().left;
            if (rightMost == null && leftMost == null) {
                leftMostX = minX;
                leftMost = n;
                rightMostX = maxX;
                rightMost = n;
                continue;
            }

            if (maxX > rightMostX) {
                rightMostX = maxX;
                rightMost = n;
            }

            if (minX < leftMostX) {
                leftMostX = minX;
                leftMost = n;
            }
        }

        switch (side) {
        
        // FIXME ... all cases!
        case EAST:
            // generate a separation constraint to the right most node
            double gRight =
                    rightMost.getSize().x / 2f + this.getSize().x / 2f + borderSpacing
                            + rightMost.getMargins().right;
            SeparationConstraint scRight =
                    new SeparationConstraint(Dim.XDIM, rightMost.cIndex, cIndex, gRight, false);
            graph.constraints.add(scRight);
            System.out.println("Generated External EAST port " + scRight);
            System.out.println("RightMost node " + rightMost);

            // generate a separation constraint on the right side of all nodes
            // for (CNode n : graph.getChildren()) {
            // SeparationConstraint scRight =
            // new SeparationConstraint(Dim.XDIM, n.cIndex, cIndex, n.getSize().x / 2f
            // + this.getSize().x / 2f + borderSpacing, false);
            // graph.constraints.add(scRight);
            // }
            break;

        case WEST:
            // generate a separation constraint on the left side of all nodes
            double gLeft =
                    leftMost.getSize().x / 2f + this.getSize().x / 2f + borderSpacing
                            + leftMost.getMargins().left;
            SeparationConstraint scLeft =
                    new SeparationConstraint(Dim.XDIM, cIndex, leftMost.cIndex, gLeft, false);
            graph.constraints.add(scLeft);

            System.out.println("Generated External WEST port " + scLeft);
            System.out.println("LeftMost node " + leftMost);

            // for (CNode n : graph.getChildren()) {
            // SeparationConstraint scRight =
            // new SeparationConstraint(Dim.XDIM, cIndex, n.cIndex, n.getSize().x / 2f
            // + this.getSize().x / 2f + borderSpacing, false);
            // graph.constraints.add(scRight);
            // }

            break;

        default:
            System.err.println("No port side defined " + this);

        }

        external = true;

        return this;
    }

    public CPort withCenterEdge() {

        // connect by edge
        ColaEdge dummyEdge = new ColaEdge(owner.cIndex, cIndex);
        cEdgeIndex = graph.edgeIndex++;
        graph.edges.add(dummyEdge);

        return this;
    }

    /**
     * Ports are moved to the outside of a node's margin in cola. Here
     * we have to revert this process.
     */
    public KVector getRelativePos() {
        final KVector pos = getRectPos().clone().sub(owner.getRectPos());
        float breathe = graph.getProperty(ColaProperties.PORT_DUMMY_BREATHE);
        switch (side) {

        case NORTH:
            pos.x -= owner.getMargins().left;
            pos.y += owner.getMargins().top + breathe;
            break;

        case SOUTH:
            pos.x -= owner.getMargins().left;
            pos.y -= owner.getMargins().bottom + breathe + getMargins().top;
            break;

        case WEST:
            pos.x += owner.getMargins().left + breathe - getMargins().left;
            pos.y -= getMargins().top;
            break;

        case EAST:
            pos.x -= owner.getMargins().right + breathe + getMargins().left;
            pos.y -= getMargins().top;
            break;

        default:
            break;
        }

        return pos;
    }

    /**
     * @return the external
     */
    public boolean isExternal() {
        return external;
    }

    /**
     * @return incoming and outgoing edges of this node.
     */
    public Iterable<CEdge> getConnectedEdges() {
        return Iterables.concat(outgoingEdges, incomingEdges);
    }

    /**
     * @return the outgoingEdges
     */
    public List<CEdge> getOutgoingEdges() {
        return outgoingEdges;
    }

    /**
     * @return the incomingEdges
     */
    public List<CEdge> getIncomingEdges() {
        return incomingEdges;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CPort { ");
        Object origin = getProperty(InternalColaProperties.ORIGIN);
        if (origin != null) {
            sb.append(origin).append(" ");
        }
        if (rect != null) {
            sb.append("cIndex=").append(cIndex).append(" ");
            sb.append(rect).append(" ");
        }

        sb.append(" }");
        return sb.toString();
    }
}
