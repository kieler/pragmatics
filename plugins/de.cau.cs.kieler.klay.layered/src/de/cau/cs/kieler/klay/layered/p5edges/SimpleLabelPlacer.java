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

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * A simple label placement implementation.
 * Places the labels in the middle between two ports.
 * 
 * @author jjc
 */
public class SimpleLabelPlacer extends AbstractAlgorithm implements ILabelPlacer {

    /**
     * {@inheritDoc}
     */
    public void placeLabels(final LayeredGraph thelayeredGraph) {
        
        //Iterate over all layers
        for (Layer layer : thelayeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                for (LPort port : node.getPorts()) {
                    for (LEdge edge : port.getEdges()) {
                        for (LLabel label : edge.getLabels()) {
                            
                            LongEdge longEdge = null;
                            //Check whether edge is spline or short edge
                            if (edge.getTarget().getNode().getProperty(Properties.NODE_TYPE)
                                    == Properties.NodeType.LONG_EDGE) {
                                longEdge = new LongEdge(edge);
                                longEdge.initialize();
                            }
                            
                            //Get source port position
                            KVector source = new KVector(edge.getSource().getPos().x, edge.getSource()
                                    .getPos().y);
                            
                            //Get target port position, distinguish between short edge or spline target
                            KVector target;
                            if (longEdge == null) {
                                target = new KVector(edge.getTarget().getPos().x, edge.getTarget()
                                        .getPos().y);
                            } else {
                                target = new KVector(longEdge.getTarget().getPos().x, longEdge
                                        .getTarget().getPos().y);
                            }
                            
                            //Get source port absolute position
                            source = source.add(edge.getSource().getNode().getPos());
                            
                            //Get target port absolute position
                            if (longEdge == null) {
                                target = target.add(edge.getTarget().getNode().getPos());
                            } else {
                                target = target.add(longEdge.getTarget().getNode().getPos());
                            }
                            
                            //Compute new horizontal position for label
                            label.getPos().x = Math.abs(source.x - target.x) / 2;
                            
                            //Compute new vertcial position for label
                            if (longEdge == null) {
                                label.getPos().y = (target.y - source.y) / 2;
                            } else {
                                //Or compute label position on a spline by using bend points
                                //Therefore, find bendpoint with max distance to source AND target node
                                KVector portPosition = new KVector(edge.getSource().getPos().x,
                                        edge.getSource().getPos().y);
                                portPosition.add(edge.getSource().getNode().getPos());
                                double minDistanceDifference = Float.POSITIVE_INFINITY;
                                KVector middlePoint = null;
                                for (KVector bPoint : longEdge.getEdge().getBendPoints()) {
                                    double bPointDifference;
                                    KVector vectorBPoint = new KVector(bPoint.x, bPoint.y);
                                    KVector vectorSource = new KVector(source.x, source.y);
                                    KVector vectorTarget = new KVector(target.x, target.y);
                                    
                                    bPointDifference = Math.abs(vectorBPoint.distance(vectorSource)
                                            - vectorBPoint.distance(vectorTarget));
                                    if (bPointDifference < minDistanceDifference) {
                                        minDistanceDifference = bPointDifference;
                                        middlePoint = bPoint;
                                    }
                                }
                                label.getPos().x = middlePoint.x - portPosition.x;
                                label.getPos().y = middlePoint.y - portPosition.y;
                            }
                            
                            //Move label horizontally to put the middle of the label on the computed spot
                            label.getPos().x -= label.getSize().x / 2;
                        }
                    }
                }
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    public LLabel longestLabel(final Layer thelayer) {
        LLabel longest = new LLabel("");
        for (LNode node : thelayer.getNodes()) {
            for (LPort port : node.getPorts()) {
                for (LEdge edge : port.getEdges()) {
                    for (LLabel label : edge.getLabels()) {
                        //Only consider source ports
                        if (label.getSize().x > longest.getSize().x
                                && port.getType() == PortType.OUTPUT) {
                            longest = label;
                        }
                    }
                }
            }
        }
        return longest;
    }

    /**
     * {@inheritDoc}
     */
    public void reset() {
        super.reset();
    }


}
