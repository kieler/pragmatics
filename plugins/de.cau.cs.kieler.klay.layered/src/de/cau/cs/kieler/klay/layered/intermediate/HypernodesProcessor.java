/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * TODO comment.
 *
 * @author msp
 */
public class HypernodesProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Hypernodes processing", 1);
        
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                if (node.getProperty(LayoutOptions.HYPERNODE) && node.getPorts().size() <= 2) {
                    int outEdges = 0, inEdges = 0;
                    for (LPort port : node.getPorts()) {
                        outEdges += port.getOutgoingEdges().size();
                        inEdges = port.getIncomingEdges().size();
                    }
                    moveHypernode(layeredGraph, node, inEdges <= outEdges);
                }
            }
        }
        
        getMonitor().done();
    }
    
    private void moveHypernode(final LayeredGraph layeredGraph, final LNode hypernode,
            final boolean right) {
        // find edges that constitute the first join point of the hyperedge
        List<LEdge> bendEdges = new LinkedList<LEdge>();
        double bendx = layeredGraph.getSize().x;
        if (right) {
            for (LPort port : hypernode.getPorts(PortType.OUTPUT)) {
                for (LEdge edge : port.getOutgoingEdges()) {
                    if (!edge.getBendPoints().isEmpty()) {
                        KVector firstPoint = edge.getBendPoints().getFirst();
                        if (firstPoint.x < bendx) {
                            bendEdges.clear();
                            bendEdges.add(edge);
                            bendx = firstPoint.x;
                        } else if (firstPoint.x == bendx) {
                            bendEdges.add(edge);
                        }
                    }
                }
            }
        } else {
            for (LPort port : hypernode.getPorts(PortType.INPUT)) {
                for (LEdge edge : port.getIncomingEdges()) {
                    if (!edge.getBendPoints().isEmpty()) {
                        KVector lastPoint = edge.getBendPoints().getLast();
                        if (lastPoint.x > bendx) {
                            bendEdges.clear();
                            bendEdges.add(edge);
                            bendx = lastPoint.x;
                        } else if (lastPoint.x == bendx) {
                            bendEdges.add(edge);
                        }
                    }
                }
            }
        }
        
        if (!bendEdges.isEmpty()) {
            // create new ports for the edges
            LPort northPort = new LPort();
            northPort.setNode(hypernode);
            northPort.setSide(PortSide.NORTH);
            northPort.getPosition().x = hypernode.getSize().x / 2;
            LPort southPort = new LPort();
            southPort.setNode(hypernode);
            southPort.setSide(PortSide.SOUTH);
            southPort.getPosition().x = hypernode.getSize().x / 2;
            southPort.getPosition().y = hypernode.getSize().y;
            // replace the first bend point by the new ports
            for (LEdge edge : bendEdges) {
                KVector first, second;
                if (right) {
                    first = edge.getBendPoints().removeFirst();
                    second = edge.getBendPoints().isEmpty() ? edge.getTargetPoint()
                            : edge.getBendPoints().getFirst();
                } else {
                    first = edge.getBendPoints().removeLast();
                    second = edge.getBendPoints().isEmpty() ? edge.getSourcePoint()
                            : edge.getBendPoints().getLast();
                }
                if (second.y >= first.y) {
                    edge.setSource(southPort);
                } else {
                    edge.setSource(northPort);
                }
            }
            // move the node to new position
            hypernode.getPosition().x = bendx - hypernode.getSize().x / 2;
        }
    }

}
