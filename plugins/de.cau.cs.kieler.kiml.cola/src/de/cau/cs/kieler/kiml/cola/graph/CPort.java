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
package de.cau.cs.kieler.kiml.cola.graph;

import org.adaptagrams.ColaEdge;
import org.adaptagrams.Dim;
import org.adaptagrams.Rectangle;
import org.adaptagrams.SeparationConstraint;

import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;

/**
 * @author uru
 */
public class CPort extends CShape<KPort> {

    // CHECKSTYLEOFF VisibilityModifier
    // CHECKSTYLEOFF Javadoc

    public int cEdgeIndex;
    public final Rectangle rect;
    public double idealDummyEdgeLength;

    public PortSide side;

    private final CNode parentNode;

    /**
     * 
     */
    public CPort(final CGraph graph, final KPort p, final CNode parentNode) {
        super(graph, p);

        this.parentNode = parentNode;

        rect = new Rectangle(0, 0 + this.getSize().x, 0, 0 + this.getSize().y);
        cIndex = graph.nodeIndex++;

        graph.nodes.add(rect);

        side = KimlUtil.calcPortSide(origin, Direction.RIGHT); // TODO dir
    }

    public CPort asExternalDummy() {

        double borderSpacing = graph.getProperty(LayoutOptions.BORDER_SPACING);

        switch (side) {
        case EAST:

            // generate a separation constraint on the right side of all nodes
            for (CNode n : graph.getChildren()) {

                KShapeLayout nodeLayout = n.origin.getData(KShapeLayout.class);
                KShapeLayout portLayout = origin.getData(KShapeLayout.class);

                SeparationConstraint scRight =
                        new SeparationConstraint(Dim.XDIM, n.cIndex, cIndex, nodeLayout.getWidth()
                                / 2f + portLayout.getWidth() / 2f + borderSpacing, false);
                graph.constraints.add(scRight);

            }
            break;

        case WEST:

            // generate a separation constraint on the left side of all nodes
            for (CNode n : graph.getChildren()) {

                KShapeLayout nodeLayout = n.origin.getData(KShapeLayout.class);
                KShapeLayout portLayout = origin.getData(KShapeLayout.class);

                SeparationConstraint scRight =
                        new SeparationConstraint(Dim.XDIM, cIndex, n.cIndex, nodeLayout.getWidth()
                                / 2f + portLayout.getWidth() / 2f + borderSpacing, false);
                graph.constraints.add(scRight);
            }

            break;

        default:

        }

        return this;
    }

    public CPort withCenterEdge() {

        // connect by edge
        ColaEdge dummyEdge = new ColaEdge(parentNode.cIndex, cIndex);
        cEdgeIndex = graph.edgeIndex++;
        graph.edges.add(dummyEdge);

        KShapeLayout portLayout = origin.getData(KShapeLayout.class);
        KShapeLayout layout = parentNode.origin.getData(KShapeLayout.class);

        // System.out.println(portLayout.getXpos() + " " + portLayout.getYpos());

        // get margins
        Margins margin =
                origin.getNode().getData(KShapeLayout.class).getProperty(LayoutOptions.MARGINS);

        KVector origCenter = new KVector(layout.getWidth() / 2f, layout.getHeight() / 2f);
        KVector marginCenter =
                origCenter.clone().translate((margin.left + margin.right) / 2f,
                        (margin.top + margin.bottom) / 2f);

        side = KimlUtil.calcPortSide(origin, Direction.RIGHT);
        KVector diff = KVector.diff(origCenter, marginCenter);

        // shift the port into the right direction
        KVector portOffset = origCenter.clone();
        switch (side) {
        case WEST:
            portOffset.translate(-diff.x, 0);
            break;
        case EAST:
            portOffset.translate(diff.x, 0);
            break;
        case SOUTH:
            portOffset.translate(0, diff.y);
            break;
        case NORTH:
            portOffset.translate(0, -diff.y);
            break;
        }

        // add half the port size
        portOffset.translate(-portLayout.getWidth() / 2f, -portLayout.getHeight() / 2f);

        // generate sep constrs
        SeparationConstraint scX =
                new SeparationConstraint(Dim.XDIM, parentNode.cIndex, cIndex, portLayout.getXpos()
                        - portOffset.x, true);
        graph.constraints.add(scX);
        SeparationConstraint scY =
                new SeparationConstraint(Dim.YDIM, parentNode.cIndex, cIndex, portLayout.getYpos()
                        - portOffset.y, true);
        graph.constraints.add(scY);

        // calculate the fixed distance of the dummy to the center
        KVector portPos =
                new KVector(portLayout.getXpos() - portOffset.x, portLayout.getYpos()
                        - portOffset.y);
        idealDummyEdgeLength =
                KVector.distance(marginCenter, portPos) + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1;

        return this;
    }

}
