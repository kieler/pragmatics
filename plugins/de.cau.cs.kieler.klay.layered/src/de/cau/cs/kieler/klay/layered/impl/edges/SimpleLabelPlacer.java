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

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.layout.options.PortType;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.Coord;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.ILabelPlacer;

/**
 * A simple label placement implementation.
 * Places the labels in the middle between two ports.
 * 
 * @author jjc
 *
 */
public class SimpleLabelPlacer extends AbstractAlgorithm implements ILabelPlacer {

    /**
     * {@inheritDoc}
     */
    public void placeLabels(final LayeredGraph thelayeredGraph) {
        
        for (Layer layer : thelayeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                for (LPort port : node.getPorts()) {
                    for (LEdge edge : port.getEdges()) {
                        for (LLabel label : edge.getLabels()) {
                            LongEdge longEdge = null;
                            if (edge.getTarget().getNode().getProperty(Properties.NODE_TYPE)
                                    == Properties.NodeType.LONG_EDGE) {
                                longEdge = new LongEdge(edge);
                                longEdge.initialize();
                            }
                            Coord source = new Coord(edge.getSource().getPos().x, edge.getSource()
                                    .getPos().y);
                            Coord target;
                            if (longEdge == null) {
                                target = new Coord(edge.getTarget().getPos().x, edge.getTarget()
                                        .getPos().y);
                            } else {
                                target = new Coord(longEdge.getTarget().getPos().x, longEdge
                                        .getTarget().getPos().y);
                            }
                            source = source.add(edge.getSource().getNode().getPos());
                            if (longEdge == null) {
                                target = target.add(edge.getTarget().getNode().getPos());
                            } else {
                                target = target.add(longEdge.getTarget().getNode().getPos());
                            }
                            label.getPos().x = Math.abs(source.x - target.x) / 2;
                            if (longEdge == null) {
                                label.getPos().y = (target.y - source.y) / 2;
                            } else {
                                Coord portPosition = new Coord(edge.getSource().getPos().x,
                                        edge.getSource().getPos().y);
                                portPosition.add(edge.getSource().getNode().getPos());
                                int numberOfBendpoints = longEdge.getEdge().getBendPoints().size();
                                label.getPos().x = longEdge.getEdge().
                                    getBendPoints().get(numberOfBendpoints / 2).x - portPosition.x;
                                label.getPos().y = longEdge.getEdge().
                                    getBendPoints().get(numberOfBendpoints / 2).y - portPosition.y;
                                /*for (Coord bendPoint : longEdge.getEdge().getBendPoints()) {
                                }*/
                            }
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
        LLabel longest = new LLabel(null, "");
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
