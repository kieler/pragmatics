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
package de.cau.cs.kieler.klay.rail.impl;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.IEdgeRouter;

/**
 * 
 * Edge router for routing edges "railway style".
 * 
 * @author jjc
 * 
 */
public class RailwayEdgeRouter extends AbstractAlgorithm implements IEdgeRouter {

    private float spacing;
    /** the bounds of a line's slope in which it is not bent. */
    public static final double SLOPE_TOLERANCE = 0.1f;
    /** the desired bend angle. will be converted to radians later. might be a layout option. */
    public static final double BEND_ANGLE = 30;

    @Override
    /**
     * {@inheritDoc}
     */
    public void routeEdges(final LayeredGraph layeredGraph) {
        spacing = layeredGraph.getProperty(Properties.OBJ_SPACING);

        double xpos = 0.0f;
        for (Layer layer : layeredGraph.getLayers()) {
            layer.placeNodes(xpos);
            for (LNode node : layer.getNodes()) {
                for (LPort port : node.getPorts(PortType.OUTPUT)) {
                    for (LEdge edge : port.getEdges()) {

                        // compute slope of edge
                        KVector p0 = edge.getSource().getPos();
                        KVector p1 = edge.getTarget().getPos();
                        double m = 0;
                        if (p1.x - p0.x != 0) {
                            m = (p1.y - p0.y) / (p1.x - p0.x);
                        } else {
                            m = Double.POSITIVE_INFINITY;
                        }

                        //System.out.println("m is " + m);
                        // can't draw edge straight (or nearly straight)?
                        if (!(-SLOPE_TOLERANCE < m && m < SLOPE_TOLERANCE)) {
                            // this point creates an orthogonal triangle with p0 and p1
                            KVector pOrth = new KVector(p0.x + xpos, edge.getTarget().getNode()
                                    .getPos().y
                                    + p1.y);
                            //System.out.println("otrh " + pOrth.toString());
                            //System.out.println("p0 " + p0.toString());

                            double a = p0.distance(p1);
                            //System.out.println("a is: " + a);
                            double b = p0.distance(pOrth);
                            //System.out.println("b is: " + b);
                            double sineBeta = a / b;
                            //System.out.println("sine beta is: " + sineBeta);
                            double beta = Math.asin(sineBeta);
                            //System.out.println("beta is: " + Math.toDegrees(beta));
                            double alpha = Math.toRadians(BEND_ANGLE);
                            //System.out.println("alpha is: " + Math.toDegrees(alpha));
                            double gamma = (2 * Math.PI) - (alpha + beta);
                            //System.out.println("gamma is: " + Math.toDegrees(gamma));

                            double c = (a * Math.sin(gamma)) / Math.sin(alpha);
                            //System.out.println("c is: " + c);

                            KVector bendPoint = new KVector(p1.x - c + xpos, pOrth.y);
                            edge.getBendPoints().add(bendPoint);
                        }
                    }
                }
            }
            xpos += layer.getSize().x + spacing;
        }
        layeredGraph.getSize().x = xpos - spacing;
    }

}
