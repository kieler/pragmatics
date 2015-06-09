/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.util.nodespacing.Rectangle;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * @author dag
 *
 */
public class OneDimensionalCompactor implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("one dimensional compacting", 1);

        double objSpacing = layeredGraph.getProperty(InternalProperties.SPACING);
        double edgeSpacing =
                layeredGraph.getProperty(InternalProperties.SPACING)
                        * layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);

        List<CNode> nodes = new ArrayList<CNode>();

        // collecting positions of graph elements
        for (Layer layer : layeredGraph) {
            for (LNode node : layer) {
                // add all nodes of type NORMAL because dummy nodes shouldn't have any size but
                // sometimes are 1 high.
                if (node.getNodeType().equals(NodeType.NORMAL)) {
                    // hitbox including object spacing
                	// FIXME half obj + half edgespacing != edgespacing between node and edge
                    Rectangle r =
                            new Rectangle(node.getPosition().x - objSpacing / 2,
                                    node.getPosition().y - objSpacing / 2, node.getSize().x
                                            + objSpacing, node.getSize().y + objSpacing);
                    nodes.add(new CNode(node, r));
                }

                // add vertical edge segments
                for (LEdge edge : node.getOutgoingEdges()) {

                    Iterator<KVector> bends = edge.getBendPoints().iterator();
                    while (bends.hasNext()) {

                        KVector bend1 = bends.next();
                        KVector bend2 = bends.next();
                        double x, y, h;
                        if (bend1.y < bend2.y) {
                            x = bend1.x;
                            y = bend1.y;
                            h = bend2.y - y;
                        } else {
                            x = bend2.x;
                            y = bend2.y;
                            h = bend1.y - y;
                        }
                        // TODO wie fisch ich die später wieder raus, und wie kommen mehrere
                        // segmente von gleicher kante in die map?
                        Rectangle rEdge =
                                new Rectangle(x - edgeSpacing / 2, y - edgeSpacing / 2,
                                        0 + edgeSpacing, h + edgeSpacing);

                        nodes.add(new CNode(edge, rEdge));
                    }
                }
            }
        }

        // infer constraints from node intersections
        // TODO consider a list for the constraint graph
        for (CNode node1 : nodes) {
            for (CNode node2 : nodes) {
                // add constraint if node2 is to the right of node1 and could collide in x direction
                // consider some wiggleroom between adjacent nodes .. weird
                if (node2.hitbox.x + 5.01 >= node1.hitbox.x + node1.hitbox.width
                        && node2.hitbox.y + node2.hitbox.height >= node1.hitbox.y
                        && node2.hitbox.y <= node1.hitbox.y + node1.hitbox.height) {
                    node1.incoming.add(node2);
                    node2.outDegree++;
                }
            }
        }

        // calculate node positions
        // starting with nodes with outDegree == 0
        Queue<CNode> startNodes = new LinkedList<CNode>();
        for (CNode node : nodes) {
            if (node.outDegree == 0) {
                startNodes.add(node);
            }
        }

        // TODO longest-path-like stuff
        while (!startNodes.isEmpty()) {

            CNode node = startNodes.poll();
            for (CNode inc : node.incoming) {

                inc.startX = Math.max(inc.startX, node.startX + node.hitbox.width);
                inc.outDegree--;

                if (inc.outDegree == 0) {
                    startNodes.add(inc);
                }
            }
        }

        // position nodes
        for (CNode node : nodes) {

            if (node.elem.getClass() == LNode.class) {
                LNode n = (LNode) node.elem;
                n.getPosition().x = node.startX - objSpacing / 2;
                
            } else if (node.elem.getClass() == LEdge.class) { // TODO can't be anything else
                LEdge e = (LEdge) node.elem;
                // TODO which bendpoint?
                Iterator<KVector> bends = e.getBendPoints().iterator();
                KVector bend1 = bends.next();
                KVector bend2 = bends.next();
                double x = node.startX - edgeSpacing / 2;
                if (bend1.x > x) {
                	bend1.x = bend2.x = x;
                } else if (bends.hasNext()) {
                	bend1 = bends.next();
                    bend2 = bends.next();
                    bend1.x = bend2.x = x;
                }
            }
        }

        progressMonitor.done();
    }

    /**
     * 
     * @author dag
     *
     */
    private class CNode {

        private LGraphElement elem;
        private Rectangle hitbox;
        private List<CNode> incoming = new ArrayList<CNode>();
        private int outDegree = 0;
        private double startX = 0;

        /**
         * @param elem
         * @param hitbox
         */
        private CNode(final LGraphElement elem, final Rectangle hitbox) {
            this.elem = elem;
            this.hitbox = hitbox;
        }

    }

}
