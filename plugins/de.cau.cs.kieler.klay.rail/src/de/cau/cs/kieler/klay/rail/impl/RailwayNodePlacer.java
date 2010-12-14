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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.INodePlacer;
import de.cau.cs.kieler.klay.rail.Properties;
import de.cau.cs.kieler.klay.rail.options.NodeType;

/**
 * Node placement implementation that aligns long edges using linear segments. Inspired by Section 4
 * of
 * <ul>
 * <li>Georg Sander. A fast heuristic for hierarchical manhattan layout. In <i>Proceedings of the
 * Symposium on Graph Drawing (GD '95)</i>, pp. 447-458, Springer, 1996.</li>
 * </ul>
 * 
 * @author msp
 */
public class RailwayNodePlacer extends AbstractAlgorithm implements INodePlacer {
    /**
     * A linear segment contains a single regular node or all dummy nodes of a long edge.
     */
    public static class LinearSegment implements Comparable<LinearSegment> {

        /** nodes of the linear segment. */
        private List<LNode> nodes = new LinkedList<LNode>();

        /**
         * @return the nodes
         */
        public List<LNode> getNodes() {
            return nodes;
        }

        // CHECKSTYLEOFF VisibilityModifier
        /** Identifier value, may be arbitrarily used by algorithms. */
        public int id;

        // CHECKSTYLEON VisibilityModifier

        /**
         * {@inheritDoc}
         */
        public String toString() {
            return "ls" + nodes.toString();
        }

        /**
         * {@inheritDoc}
         */
        public int compareTo(final LinearSegment other) {
            return this.id - other.id;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        super.reset();
    }

    /**
     * {@inheritDoc}
     */
    public void placeNodes(final LayeredGraph layeredGraph) {
        getMonitor().begin("Linear segments node placement", 1);

        float spacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        float borspacing = layeredGraph.getProperty(Properties.BOR_SPACING);
        int layerCount = layeredGraph.getLayers().size();

        layeredGraph.getLayers().get(0).getNodes().get(0).getPos().y = borspacing;
        for (int i = 0; i < layerCount - 1; i++) {
            for (int j = 0; j < layeredGraph.getLayers().get(i).getNodes().size(); j++) {
                LNode currentNode = layeredGraph.getLayers().get(i).getNodes().get(j);
                if (currentNode.getProperty(Properties.NODE_TYPE).equals(NodeType.SWITCH_LEFT)) {
                    //TODO: move twig up and push rest down
                    Iterator<LPort> theTwoPorts = currentNode.getPorts(PortType.OUTPUT).iterator();
                    LPort port = theTwoPorts.next();
                    LPort port2 = theTwoPorts.next();
                    double pushDown = 0;
                    if (port.getPos().y < port2.getPos().y) {
                        port.getEdges().get(0).getTarget().getNode().getPos().y = currentNode
                                .getPos().y;
                        pushDown = currentNode.getPos().y
                                + port.getEdges().get(0).getTarget().getNode().getSize().y
                                + spacing;
                        port2.getEdges().get(0).getTarget().getNode().getPos().y = pushDown;
                    } else {
                        port2.getEdges().get(0).getTarget().getNode().getPos().y = currentNode
                                .getPos().y;
                        pushDown = currentNode.getPos().y
                                + port2.getEdges().get(0).getTarget().getNode().getSize().y
                                + spacing;
                        port.getEdges().get(0).getTarget().getNode().getPos().y = pushDown;
                    }
                    fitAncestors(currentNode, currentNode.getPos().y);
                } else if (currentNode.getProperty(Properties.NODE_TYPE).equals(
                        NodeType.SWITCH_RIGHT)) {
                    // TODO: case for switch right and cases for turned switches
                } else {
                    if (currentNode.getPorts().get(0).getType().equals(PortType.OUTPUT)) {
                        currentNode.getPorts().get(0).getEdges().get(0).getTarget().getNode()
                                .getPos().y = currentNode.getPos().y;
                    }
                }
                layeredGraph.getLayers().get(i).getSize().x = Math.max(layeredGraph.getLayers()
                        .get(i).getSize().x, currentNode.getSize().x);
            }
        }
        /*
         * // arrange port positions layeredGraph.arrangePorts(); // sort the linear segments of the
         * layered graph sortLinearSegments(layeredGraph); // create an unbalanced placement from
         * the sorted segments createUnbalancedPlacement(layeredGraph); // balance the placement
         * IKielerProgressMonitor monitor = getMonitor().subTask(1);
         * //monitor.begin("Balance Placement", 1); //balancePlacement(layeredGraph);
         * //monitor.done();
         * 
         * // release the created resources this.linearSegments = null;
         */
        // set the proper height for the whole graph
        KVector graphSize = layeredGraph.getSize();
        for (Layer layer : layeredGraph.getLayers()) {
            graphSize.y = Math.max(graphSize.y, layer.getSize().y);
        }
        getMonitor().done();
    }

    /*
     * for (LNode node : segment.getNodes()) { double offset = 0.0f; if (straightEdges) { // add
     * node offset - minimal offset offset = node.getProperty(Properties.LINSEG_OFFSET) - minOffset;
     * } else { offset = maxSize / 2 - node.getSize().y / 2; } Layer layer = node.getLayer();
     * node.getPos().y = newPos + offset; layer.getSize().y = newPos + offset + node.getSize().y;
     * layer.getSize().x = Math.max(layer.getSize().x, node.getSize().x); }
     */

    private void fitAncestors(final LNode source, final double newY) {
        for (LPort port : source.getPorts(PortType.INPUT)) {
            port.getEdges().get(0).getSource().getNode().getPos().y = newY;
            fitAncestors(port.getEdges().get(0).getSource().getNode(), newY);
        }
    }

}
