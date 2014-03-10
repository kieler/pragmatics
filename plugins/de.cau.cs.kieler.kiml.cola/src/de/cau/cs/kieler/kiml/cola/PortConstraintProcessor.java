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
package de.cau.cs.kieler.kiml.cola;

import org.adaptagrams.Dim;
import org.adaptagrams.SeparationConstraint;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.cola.graph.CGraph;
import de.cau.cs.kieler.kiml.cola.graph.CNode;
import de.cau.cs.kieler.kiml.cola.graph.CPort;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;

/**
 * @author uru
 * 
 */
public class PortConstraintProcessor implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final CGraph graph, final IKielerProgressMonitor progressMonitor) {

        for (CNode n : graph.getChildren()) {

            PortConstraints portConstraints = n.getProperty(LayoutOptions.PORT_CONSTRAINTS);

            // get margins of the parent node
            Margins margins = n.getProperty(LayoutOptions.MARGINS);

            // determine the center of the node
            KVector origCenter = new KVector(n.getSize().x / 2f, n.getSize().y / 2f);
            // determine the center when considering margins of the node
            KVector marginCenter =
                    origCenter.clone().translate((margins.left + margins.right) / 2f,
                            (margins.top + margins.bottom) / 2f);

            KVector diff = KVector.diff(origCenter, marginCenter);

            // variables used for non-fixed separation constraint generation
            double lastYWestPos = n.getPos().x - margins.left;
            double lastYEastPos = n.getPos().x + n.getSize().x + margins.right;
            double lastXNorthPos = n.getPos().y - margins.top;
            double lastXSouthPos = n.getPos().y + n.getSize().y + margins.bottom;

            CPort lastWestPort = null;
            CPort lastNorthPort = null;
            CPort lastEastPort = null;
            CPort lastSouthPort = null;

            for (CPort p : n.getPorts()) {

                // shift the port's offset into the correct direction
                KVector portOffset = origCenter.clone();
                switch (p.side) {
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
                portOffset.translate(-p.getSize().x / 2f, -p.getSize().y / 2f);

                // now handle the specified port constraints
                switch (portConstraints) {

                //
                // FIXED_ORDER
                //
                case FIXED_ORDER:

                    // TODO restrict the last port on each side

                    // PRECONDITION:
                    // we require the list of ports to be sorted!

                    switch (p.side) {
                    case WEST:

                    {// fix the port on the left side of the node
                        SeparationConstraint scX =
                                new SeparationConstraint(Dim.XDIM, n.cIndex, p.cIndex, p.getPos().x
                                        - portOffset.x, true);
                        graph.constraints.add(scX);

                        // we are free in y direction
                        if (lastWestPort != null) {
                            // relative to previous port
                            // constraints are generated from bottom to top
                            SeparationConstraint scY =
                                    new SeparationConstraint(Dim.YDIM, p.cIndex,
                                            lastWestPort.cIndex, p.getSize().y / 2f, false);
                            graph.constraints.add(scY);
                        } else {
                            // relative to parent node
                            SeparationConstraint scY =
                                    new SeparationConstraint(Dim.YDIM, n.cIndex, p.cIndex,
                                            -(n.getSize().y / 2f + margins.top), false);
                            graph.constraints.add(scY);
                        }
                        lastWestPort = p;
                    }
                        break;
                    case EAST:

                    { // fix the ports on the right side of the node
                        SeparationConstraint scX =
                                new SeparationConstraint(Dim.XDIM, n.cIndex, p.cIndex, p.getPos().x
                                        - portOffset.x, true);
                        graph.constraints.add(scX);

                        // we are free in y direction
                        if (lastEastPort != null) {
                            // relative to previous port
                            SeparationConstraint scY =
                                    new SeparationConstraint(Dim.YDIM, lastEastPort.cIndex,
                                            p.cIndex, p.getSize().y / 2f, false);
                            graph.constraints.add(scY);
                        } else {
                            // relative to parent node
                            SeparationConstraint scY =
                                    new SeparationConstraint(Dim.YDIM, n.cIndex, p.cIndex,
                                            -(n.getSize().y / 2f), false);
                            graph.constraints.add(scY);
                        }
                        lastEastPort = p;
                    }
                        break;
                    case SOUTH:

                    { // fix the ports on the bottom side of the node
                        SeparationConstraint scY =
                                new SeparationConstraint(Dim.YDIM, n.cIndex, p.cIndex, p.getPos().y
                                        - portOffset.y, true);
                        graph.constraints.add(scY);

                        if (lastSouthPort != null) {
                            SeparationConstraint scX =
                                    new SeparationConstraint(Dim.XDIM, p.cIndex,
                                            lastSouthPort.cIndex, p.getSize().x / 2f, false);
                            graph.constraints.add(scX);
                        } else {
                            SeparationConstraint scX =
                                    new SeparationConstraint(Dim.XDIM, n.cIndex, p.cIndex,
                                            -(n.getSize().x / 2f), false);
                            graph.constraints.add(scX);
                        }
                        lastSouthPort = p;
                    }

                        break;
                    case NORTH:

                    { // fix the ports on the top side of the node
                        SeparationConstraint scY =
                                new SeparationConstraint(Dim.YDIM, n.cIndex, p.cIndex, p.getPos().y
                                        - portOffset.y, true);
                        graph.constraints.add(scY);

                        if (lastNorthPort != null) {
                            SeparationConstraint scX =
                                    new SeparationConstraint(Dim.XDIM, lastNorthPort.cIndex,
                                            p.cIndex, p.getSize().x / 2f, false);
                            graph.constraints.add(scX);
                        } else {
                            SeparationConstraint scX =
                                    new SeparationConstraint(Dim.XDIM, n.cIndex, p.cIndex,
                                            -(n.getSize().x / 2f), false);
                            graph.constraints.add(scX);
                        }
                        lastNorthPort = p;
                    }
                        break;
                    }

                    break;
                //
                // FIXED_POS
                //
                case FIXED_POS:

                    // generate sep constrs
                    SeparationConstraint scX =
                            new SeparationConstraint(Dim.XDIM, n.cIndex, p.cIndex, p.getPos().x
                                    - portOffset.x, true);
                    graph.constraints.add(scX);
                    SeparationConstraint scY =
                            new SeparationConstraint(Dim.YDIM, n.cIndex, p.cIndex, p.getPos().y
                                    - portOffset.y, true);
                    graph.constraints.add(scY);

                    // calculate the fixed distance of the dummy to the center
                    KVector portPos =
                            new KVector(p.getPos().x - portOffset.x, p.getPos().y - portOffset.y);
                    p.idealDummyEdgeLength =
                            KVector.distance(marginCenter, portPos) + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1;

                    break;
                default:
                    // nothing to do
                }

            }

        }

    }

}
