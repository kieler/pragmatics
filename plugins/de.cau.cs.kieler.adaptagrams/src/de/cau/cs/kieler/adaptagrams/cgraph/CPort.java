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
package de.cau.cs.kieler.adaptagrams.cgraph;

import java.util.List;

import org.adaptagrams.ColaEdge;
import org.adaptagrams.Rectangle;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.adaptagrams.properties.CGraphProperties;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.PortSide;

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
                graph.getProperty(CGraphProperties.CONSIDER_PREVIOUS_POSITION);
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

        // System.out.println("Initialized " + this);
    }

    public CPort asExternalDummy() {

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
        //float breathe = graph.getProperty(CGraphProperties.PORT_DUMMY_BREATHE);
        float breathe = 2;
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
        Object origin = getProperty(CGraphProperties.ORIGIN);
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
