/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.codaflow.processors;

import org.adaptagrams.Dim;
import org.adaptagrams.SeparationConstraint;

import de.cau.cs.kieler.adaptagrams.cgraph.CGraph;
import de.cau.cs.kieler.adaptagrams.cgraph.CNode;
import de.cau.cs.kieler.adaptagrams.cgraph.CPort;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;

/**
 * This class generates {@link SeparationConstraint} between ports and their parents to 
 * regard {@link PortConstraints}. Based on the restrictiveness of the {@link PortConstraints} 
 * different separation constraints are created.
 * 
 * <p>
 * <dl>
 *  <dt>{@link PortConstraints#FIXED_POS}</dt>
 *  <dd>
 *      For every port exactly two equality constraints are created (one for each dimension),
 *      fixing the port exactly at the desired position relative to its parent node.
 *  </dd>
 *  <dt>{@link PortConstraints#FIXED_SIDE}</dt>
 *  <dd>
 *      For every port three constraints are generated. One that fixes to port on the specific 
 *      side, e.g. for a WEST port, one equality constraint is created in the XDIM fixing
 *      its x-coordinate and two inequality constraints are created in the YDIM bounding
 *      the ports y-coordinate to the parent node's height. 
 *  </dd> 
 *  <dt>...</dt>
 * </dl>
 * </p>
 * 
 * Precondition: for fixed order we require the port lists to be sorted.
 * 
 * FIXME 
 * Known problems:
 *  - Implement FIXED_RATIO, FIXED_ORDER (they reuse FIXED_SIDE) 
 *  - FREE throws unsupported operation
 * 
 * @author uru
 */
public class PortConstraintProcessor implements ILayoutProcessor {

    /** The current {@link CGraph}. */
    private CGraph graph;

    /**
     * {@inheritDoc}
     */
    public void process(final CGraph theGraph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Generate Port Constraints", 1);

        this.graph = theGraph;

        // handle the ports of every node
        for (CNode n : graph.getChildren()) {

            PortConstraints portConstraints = n.getProperty(LayoutOptions.PORT_CONSTRAINTS);
            
            // handle every port
            for (CPort p : n.getPorts()) {

                // now handle the specified port constraints
                switch (portConstraints) {

                case FREE:
                    throw new UnsupportedOperationException("FREE ports are unsupported");

                // TODO implement the cases 
                case FIXED_RATIO:
                case FIXED_ORDER:
                case FIXED_SIDE:
                    generateFixedSideConstraints(p, n);
                    break;

                case FIXED_POS:
                    generateFixedPosConstraints(p, n);
                    break;

                default:
                    throw new UnsupportedOperationException("Unsupported port constraints "
                            + portConstraints);
                }

            }
        }

        progressMonitor.done();
    }
    
    /**
     * {@link PortConstraints#FIXED_POS}.
     */
    private void generateFixedPosConstraints(final CPort p, final CNode n) {
 
        KVector nodeCenterMove = new KVector().add(n.getRectCenterRaw()).sub(n.getCenter());
        
        // fix X coordinate
        double gx = p.getCenter().x - n.getCenter().x - nodeCenterMove.x;
        SeparationConstraint scX =
                new SeparationConstraint(Dim.XDIM, n.cIndex, p.cIndex, 
                        gx, true);
        graph.constraints.add(scX);
        
        // fix y coordinate
        double gy = p.getCenter().y - n.getCenter().y - nodeCenterMove.y;
        SeparationConstraint scY =
                new SeparationConstraint(Dim.YDIM, n.cIndex, p.cIndex, 
                        gy, true);
        graph.constraints.add(scY);
        
        // calculate the fixed distance of the dummy to the center of the node
        p.idealDummyEdgeLength = new KVector(gx, gy).length();
    }
    
    /**
     * {@link PortConstraints#FIXED_SIDE}.
     */
    private void generateFixedSideConstraints(final CPort p, final CNode n) {

        KVector nodeCenterMove = new KVector().add(n.getRectCenterRaw()).sub(n.getCenter());
        // SUPPRESS CHECKSTYLE NEXT 2 MagicNumber
        // maximally allowed distance between node center and port center 
        KVector portCenterNodeCenter = n.getSize().clone().sub(p.getSize()).scale(0.5f);
        
        // we don't know exactly where the port will end up. 
        // Try to get ports as close to the center as possible, thus we use the distance
        // from center to the perpendicular point on the node's boundary
        double idealEdgeLength = 0;
        
        // CHECKSTYLEOFF AvoidNestedBlocks
        switch (p.side) {
            case WEST:
            case EAST: {
                // fix X coordinate
                double gx = p.getCenter().x - n.getCenter().x - nodeCenterMove.x;
                SeparationConstraint scX =
                        new SeparationConstraint(Dim.XDIM, n.cIndex, p.cIndex, 
                                gx, true);
                graph.constraints.add(scX);
    
                // free y coordinate bound by the node's size
                double g = portCenterNodeCenter.y + nodeCenterMove.y; 
                // Top: n - g <= p
                SeparationConstraint scY1 = 
                        new SeparationConstraint(Dim.YDIM, n.cIndex, p.cIndex, -g, false);
                graph.constraints.add(scY1);
    
                // Bottom: p - g <= n
                g = portCenterNodeCenter.y - nodeCenterMove.y; 
                SeparationConstraint scY2 =
                        new SeparationConstraint(Dim.YDIM, p.cIndex, n.cIndex, -g, false);
                graph.constraints.add(scY2);
                
                idealEdgeLength = n.getSize().x / 2f
                                + (p.side == PortSide.WEST ? +nodeCenterMove.x : -nodeCenterMove.x);
            }
                break;
    
            case NORTH:
            case SOUTH: {
                // fix y coordinate
                double gy = p.getCenter().y - n.getCenter().y - nodeCenterMove.y;
                SeparationConstraint scY =
                        new SeparationConstraint(Dim.YDIM, n.cIndex, p.cIndex, 
                                gy, true);
                graph.constraints.add(scY);
    
                // free x coordinate constrained by the node's bounds
                double g = portCenterNodeCenter.y + nodeCenterMove.y;
                // Left: n - g <= p
                SeparationConstraint scX1 =
                        new SeparationConstraint(Dim.XDIM, n.cIndex, p.cIndex, -g, false);
                graph.constraints.add(scX1);
                
                // Right: p - g <= n
                g = portCenterNodeCenter.y - nodeCenterMove.y;
                SeparationConstraint scX2 =
                        new SeparationConstraint(Dim.XDIM, p.cIndex, n.cIndex, -g, false);
                graph.constraints.add(scX2);
                
                idealEdgeLength = n.getSize().y / 2f
                        + (p.side == PortSide.NORTH ? +nodeCenterMove.y : -nodeCenterMove.y);
            }
            break;
        }
        
        p.idealDummyEdgeLength = Math.abs(idealEdgeLength);
    }

}
