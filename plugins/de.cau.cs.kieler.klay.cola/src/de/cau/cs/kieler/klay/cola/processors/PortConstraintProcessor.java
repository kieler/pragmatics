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
package de.cau.cs.kieler.klay.cola.processors;

import org.adaptagrams.Dim;
import org.adaptagrams.SeparationConstraint;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.klay.cola.graph.CGraph;
import de.cau.cs.kieler.klay.cola.graph.CNode;
import de.cau.cs.kieler.klay.cola.graph.CPort;

/**
 * 
 * Precondition: for fixed order we require the port lists to be sorted.
 * 
 * TODO ideal edge lengths for non fixed pos?
 * 
 * TODO FREE and FIXED_RATIO
 * 
 * @author uru
 */
public class PortConstraintProcessor implements ILayoutProcessor {

    private CGraph graph;

    /*
     * Following variables are used internally when generating FIXED_ORDER constraints
     */
    private CPort lastWestPort = null;
    private CPort lastNorthPort = null;
    private CPort lastEastPort = null;
    private CPort lastSouthPort = null;

    /**
     * {@inheritDoc}
     */
    public void process(final CGraph theGraph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Generate Port Constraints", 1);

        this.graph = theGraph;

        // handle the ports of every node
        for (CNode n : graph.getChildren()) {

            // Note, ports are in the node's coordinate system
            
            PortConstraints portConstraints = n.getProperty(LayoutOptions.PORT_CONSTRAINTS);

            // determine the center of the node
            KVector origCenter = new KVector(n.getSize().x / 2f, n.getSize().y / 2f);
            // determine the center when considering margins of the node
            KVector marginCenter =
                    origCenter.clone().translate((n.getMargins().left + n.getMargins().right) / 2f,
                            (n.getMargins().top + n.getMargins().bottom) / 2f);
            marginCenter.translate(-n.getMargins().left, -n.getMargins().top);

            KVector diff = KVector.diff(origCenter, marginCenter);

            // reset the internal markers for fixed order constraints
            lastWestPort = null;
            lastNorthPort = null;
            lastEastPort = null;
            lastSouthPort = null;

            // handle every port
            for (CPort p : n.getPorts()) {

                // shift the port's offset into the correct direction
                // Note: make sure the dummy nodes have some space to breathe (overlap removal)
                float breathe = 3;
                KVector portOffset = origCenter.clone();
                switch (p.side) {
                case WEST:
                    portOffset.translate(-(diff.x - breathe - n.getMargins().left), -diff.y);
                    break;
                case EAST:
                    portOffset.translate(diff.x - breathe - n.getMargins().left, -diff.y);
                    break;
                case SOUTH:
                    portOffset.translate(-diff.x, diff.y - breathe - n.getMargins().top);
                    break;
                case NORTH:
                    portOffset.translate(-diff.x, -(diff.y - breathe - n.getMargins().top));
                    break;
                }

                // add half the port size
                portOffset.translate(-p.getSize().x / 2f, -p.getSize().y / 2f);

                // now handle the specified port constraints
                switch (portConstraints) {

                case FREE:
                    // TODO
                    break;

                case FIXED_SIDE:
                    generateFixedSideConstraints(p, n, portOffset);
                    break;

                case FIXED_ORDER:
                    // we require the list of ports to be sorted
                    generateFixedOrderConstraints(p, n, portOffset);
                    break;

                case FIXED_RATIO:
                    // TODO
                    break;

                case FIXED_POS:
                    generateFixedPosConstraints(p, n, portOffset, marginCenter);
                    break;

                default:
                    // TODO undefined?!
                    System.err.println("Oh noooooo!!");
                    // shouldn't happen
                }

            }

            // if fixed order we have to bound the port positions to the "other" side of each node
            if (portConstraints == PortConstraints.FIXED_ORDER) {
                postprocessFixedOrderConstraints(n);
            }

        }

        progressMonitor.done();
    }

    // CHECKSTYLEOFF AvoidNestedBlocks

    private void generateFixedSideConstraints(final CPort p, final CNode n, final KVector portOffset) {

        switch (p.side) {
        case WEST:
        case EAST: {
            // fix x coordinate
            SeparationConstraint scX =
                    new SeparationConstraint(Dim.XDIM, n.cIndex, p.cIndex, p.getPos().x
                            - portOffset.x, true);
            graph.constraints.add(scX);

            // free y coordinate bound by the node's size
            double g = n.getSize().y / 2f - p.getSize().y / 2f;
            SeparationConstraint scY1 =
                    new SeparationConstraint(Dim.YDIM, n.cIndex, p.cIndex, g, false);
            graph.constraints.add(scY1);

            SeparationConstraint scY2 =
                    new SeparationConstraint(Dim.YDIM, p.cIndex, n.cIndex, g, false);
            graph.constraints.add(scY2);

        }
            break;

        case NORTH:
        case SOUTH: {
            // fix y coordinate
            SeparationConstraint scY =
                    new SeparationConstraint(Dim.YDIM, n.cIndex, p.cIndex, p.getPos().y
                            - portOffset.y, true);
            graph.constraints.add(scY);

            // free x coordinate constrained by the node's bounds
            double g = n.getSize().x / 2f - p.getSize().x / 2f;
            SeparationConstraint scX1 =
                    new SeparationConstraint(Dim.XDIM, p.cIndex, n.cIndex, g, false);
            graph.constraints.add(scX1);

            SeparationConstraint scX2 =
                    new SeparationConstraint(Dim.XDIM, n.cIndex, p.cIndex, g, false);
            graph.constraints.add(scX2);
        }
            break;

        }
    }

    private void generateFixedOrderConstraints(final CPort p, final CNode n,
            final KVector portOffset) {
        
        // FIXME restrict to the NONmargin area
        
        switch (p.side) {
        case WEST: {
            // fix the port on the left side of the node
            SeparationConstraint scX =
                    new SeparationConstraint(Dim.XDIM, n.cIndex, p.cIndex, p.getPos().x
                            - portOffset.x, true);
            graph.constraints.add(scX);

            // we are free in y direction
            if (lastWestPort != null) {
                // relative to previous port
                // constraints are generated from bottom to top
                double g = p.getSize().y / 2f + lastWestPort.getSize().y / 2f;
                SeparationConstraint scY =
                        new SeparationConstraint(Dim.YDIM, p.cIndex, lastWestPort.cIndex, g, false);
                graph.constraints.add(scY);
            } else {
                // relative to parent node, restrict the ports pos to the 'bottom'
                double g = n.getSize().y / 2f - p.getSize().y / 2f;
                SeparationConstraint scY =
                        new SeparationConstraint(Dim.YDIM, p.cIndex, n.cIndex, -g, false);
                graph.constraints.add(scY);
            }
            lastWestPort = p;
        }
            break;

        case EAST: {
            // fix the ports on the right side of the node
            SeparationConstraint scX =
                    new SeparationConstraint(Dim.XDIM, n.cIndex, p.cIndex, p.getPos().x
                            - portOffset.x, true);
            graph.constraints.add(scX);

            // we are free in y direction
            if (lastEastPort != null) {
                // relative to previous port
                double g = p.getSize().y / 2f + lastEastPort.getSize().y / 2f;
                SeparationConstraint scY =
                        new SeparationConstraint(Dim.YDIM, lastEastPort.cIndex, p.cIndex, g, false);
                graph.constraints.add(scY);
            } else {
                // relative to parent node
                double g = n.getSize().y / 2f - p.getSize().y / 2f;
                SeparationConstraint scY =
                        new SeparationConstraint(Dim.YDIM, n.cIndex, p.cIndex, -g, false);
                graph.constraints.add(scY);
            }
            lastEastPort = p;
        }
            break;

        case SOUTH: {
            // fix the ports on the bottom side of the node
            SeparationConstraint scY =
                    new SeparationConstraint(Dim.YDIM, n.cIndex, p.cIndex, p.getPos().y
                            - portOffset.y, true);
            graph.constraints.add(scY);

            if (lastSouthPort != null) {
                double g = p.getSize().x / 2f + lastSouthPort.getSize().x / 2f;
                SeparationConstraint scX =
                        new SeparationConstraint(Dim.XDIM, p.cIndex, lastSouthPort.cIndex, g, false);
                graph.constraints.add(scX);
            } else {
                double g = n.getSize().x / 2f - p.getSize().x;
                SeparationConstraint scX =
                        new SeparationConstraint(Dim.XDIM, p.cIndex, n.cIndex, -g, false);
                graph.constraints.add(scX);
            }
            lastSouthPort = p;
        }
            break;

        case NORTH: {
            // fix the ports on the top side of the node
            SeparationConstraint scY =
                    new SeparationConstraint(Dim.YDIM, n.cIndex, p.cIndex, p.getPos().y
                            - portOffset.y, true);
            graph.constraints.add(scY);

            if (lastNorthPort != null) {
                double g = p.getSize().x / 2f + lastNorthPort.getSize().x / 2f;
                SeparationConstraint scX =
                        new SeparationConstraint(Dim.XDIM, lastNorthPort.cIndex, p.cIndex, g, false);
                graph.constraints.add(scX);
            } else {
                double g = n.getSize().x / 2f - p.getSize().x / 2f;
                SeparationConstraint scX =
                        new SeparationConstraint(Dim.XDIM, n.cIndex, p.cIndex, -g, false);
                graph.constraints.add(scX);
            }
            lastNorthPort = p;
        }
            break;

        default:
            // nothing
        }
    }

    private void postprocessFixedOrderConstraints(final CNode n) {
        // WEST
        if (lastWestPort != null) {
            // restrict to the 'top': n.y - (n/2) <= p.y
            double g = n.getSize().y / 2f - lastWestPort.getSize().y / 2f;
            SeparationConstraint scY =
                    new SeparationConstraint(Dim.YDIM, n.cIndex, lastWestPort.cIndex, -g, false);
            graph.constraints.add(scY);
        }

        // EAST
        if (lastEastPort != null) {
            // restrict to the 'bottom'
            double g = n.getSize().y / 2f - lastEastPort.getSize().y / 2f;
            SeparationConstraint scY =
                    new SeparationConstraint(Dim.YDIM, lastEastPort.cIndex, n.cIndex, -g, false);
            graph.constraints.add(scY);
        }

        // SOUTH
        if (lastSouthPort != null) {
            double g = n.getSize().x / 2f - lastSouthPort.getSize().x / 2f;
            SeparationConstraint scX =
                    new SeparationConstraint(Dim.XDIM, n.cIndex, lastSouthPort.cIndex, -g, false);
            graph.constraints.add(scX);
        }

        // NORTH
        if (lastNorthPort != null) {
            double g = n.getSize().x / 2f - lastNorthPort.getSize().x / 2f;
            SeparationConstraint scX =
                    new SeparationConstraint(Dim.XDIM, lastNorthPort.cIndex, n.cIndex, -g, false);
            graph.constraints.add(scX);
        }
    }

    private void generateFixedPosConstraints(final CPort p, final CNode n,
            final KVector portOffset, final KVector marginCenter) {

        double gx = p.getPos().x - portOffset.x;
        SeparationConstraint scX =
                new SeparationConstraint(Dim.XDIM, n.cIndex, p.cIndex, gx,
                        true);
        graph.constraints.add(scX);
        

        double gy = p.getPos().y - portOffset.y;
        SeparationConstraint scY =
                new SeparationConstraint(Dim.YDIM, n.cIndex, p.cIndex, gy,
                        true);
        graph.constraints.add(scY);
        System.out.println(scY);
        
        // calculate the fixed distance of the dummy to the center
        KVector portPos = new KVector(p.getPos().x - portOffset.x, p.getPos().y - portOffset.y);
        p.idealDummyEdgeLength = KVector.distance(marginCenter, portPos);
    }

}
