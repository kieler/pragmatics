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

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;

/**
 * @author uru
 */
public class CPort extends CShape {

    private static final long serialVersionUID = -2142404691531934943L;

    // CHECKSTYLEOFF VisibilityModifier
    // CHECKSTYLEOFF Javadoc

    public int cEdgeIndex = -1;
    public Rectangle rect;
    public double idealDummyEdgeLength;

    public PortSide side = PortSide.UNDEFINED;

    public final CNode parentNode;
    protected List<CEdge> outgoingEdges = Lists.newLinkedList();
    protected List<CEdge> incomingEdges = Lists.newLinkedList();

    /**
     * 
     */
    public CPort(final CGraph graph, final CNode parentNode) {
        super(graph);
        this.parentNode = parentNode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        rect = new Rectangle(0, 0 + this.getSize().x, 0, 0 + this.getSize().y);
        cIndex = graph.nodeIndex++;
        graph.nodes.add(rect);
    }

    public CPort asExternalDummy() {

        double borderSpacing = graph.getProperty(LayoutOptions.BORDER_SPACING);

        switch (side) {
        case EAST:

            // generate a separation constraint on the right side of all nodes
            for (CNode n : graph.getChildren()) {

                SeparationConstraint scRight =
                        new SeparationConstraint(Dim.XDIM, n.cIndex, cIndex, n.getSize().x / 2f
                                + this.getSize().x / 2f + borderSpacing, false);
                graph.constraints.add(scRight);

            }
            break;

        case WEST:

            // generate a separation constraint on the left side of all nodes
            for (CNode n : graph.getChildren()) {

                SeparationConstraint scRight =
                        new SeparationConstraint(Dim.XDIM, cIndex, n.cIndex, n.getSize().x / 2f
                                + this.getSize().x / 2f + borderSpacing, false);
                graph.constraints.add(scRight);
            }

            break;

        default:
            System.err.println("No port side defined " + this);

        }

        return this;
    }

    public CPort withCenterEdge() {

        // connect by edge
        ColaEdge dummyEdge = new ColaEdge(parentNode.cIndex, cIndex);
        cEdgeIndex = graph.edgeIndex++;
        graph.edges.add(dummyEdge);

        return this;
    }

    public double getActualXPos() {

        double x = rect.getMinX();

        // offset relative to parent
        x -= parentNode.rect.getMinX();

        // subtract some stuff depending on position
        Margins margins = parentNode.getProperty(LayoutOptions.MARGINS);

        switch (side) {

        case NORTH:
        case SOUTH:
            x -= margins.right - getSize().x;
            break;

        case WEST:
            x -= margins.left;
            x += getSize().x;
            break;

        case EAST:
            x -= margins.right + margins.left;
            break;

        default:
            break;
        }

        return x;
    }

    public double getActualYPos() {

        double y = rect.getMinY();

        // offset relative to parent
        y -= parentNode.rect.getMinY();

        // subtract some stuff depending on position
        Margins margins = parentNode.getProperty(LayoutOptions.MARGINS);

        switch (side) {

        case NORTH:
            y += margins.top;
            break;

        case SOUTH:
            y -= margins.bottom + margins.top;
            break;

        case WEST:
        case EAST:
            y -= margins.bottom;
            y -= getSize().y;
            break;

        default:
            break;
        }

        return y;

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

    // /**
    // * Returns the name of the port. The name is derived from the text of the first label, if any.
    // *
    // * @return the name, or {@code null}
    // */
    // public String getName() {
    // if (!origin.getLabels().isEmpty()) {
    // return origin.getLabels().get(0).getText();
    // }
    // return null;
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public String toString() {
    // String text = getName();
    // if (text == null) {
    // return "p_" + cIndex + (cEdgeIndex != -1 ? "_" + cEdgeIndex : "");
    // } else {
    // return "p_" + text;
    // }
    // }
}
