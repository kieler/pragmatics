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
package de.cau.cs.kieler.klay.planar.intermediate;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation.OrthogonalAngle;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * Removes dummy nodes and dummy edges which are inserted while the algorithms. At the state of art
 * planar dummynodes rectshape dummynodes and bendpoints are removed.
 * 
 * @author pkl
 */
public class DummyRemovingProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph graph) {
        getMonitor().begin("Remove dummynodes", 1);

        OrthogonalRepresentation or = graph.getProperty(Properties.ORTHO_REPRESENTATION);
        GridRepresentation grid = graph.getProperty(Properties.GRID_REPRESENTATION);

        List<PNode> planarDummynodes = new LinkedList<PNode>();
        for (PNode node : graph.getNodes()) {
            if (node.hasProperties() && node.getProperty(Properties.PlANAR_DUMMY_NODE) != null) {
                planarDummynodes.add(node);
            }
        }
        // Remove planar dummy nodes.

        for (PNode dummyNode : planarDummynodes) {
            List<Pair<PEdge, OrthogonalAngle>> angles = or.getAngles(dummyNode);
            // TODO proof: should have exact 4 adjacent edges
            PEdge first = angles.get(0).getFirst();
            PEdge second = angles.get(1).getFirst();
            PEdge third = angles.get(2).getFirst();
            PEdge fourth = angles.get(3).getFirst();

            // move the original edge over the dummy node from source to new target or vice versa
            // according to the edge direction. Edge 1 and 3 are straight to eachother so that
            // we can move the edge from source 1 to target 3 or vice versa (according to the edge
            // direction).
            // The same goes to the edges 2 and 4.
            if (first.hasProperties() && first.getProperty(Properties.ORIGIN) != null) {
                if (first.getTarget() == dummyNode) {
                    first.move(first.getSource(), third.getOppositeNode(dummyNode));
                } else {
                    first.move(third.getOppositeNode(dummyNode), first.getTarget());
                }
            } else {
                if (third.getTarget() == dummyNode) {
                    third.move(third.getSource(), first.getOppositeNode(dummyNode));
                } else {
                    third.move(first.getOppositeNode(dummyNode), third.getTarget());
                }
            }

            if (second.hasProperties() && second.getProperty(Properties.ORIGIN) != null) {
                if (second.getTarget() == dummyNode) {
                    second.move(second.getSource(), fourth.getOppositeNode(dummyNode));
                } else {
                    second.move(fourth.getOppositeNode(dummyNode), second.getTarget());
                }
            } else {
                if (fourth.getTarget() == dummyNode) {
                    fourth.move(fourth.getSource(), second.getOppositeNode(dummyNode));
                } else {
                    fourth.move(second.getOppositeNode(dummyNode), fourth.getTarget());
                }
            }

            grid.remove(dummyNode);

        }

        // Remove rectshape dummies
        List<PNode> dummyNodes = new LinkedList<PNode>();
        for (PNode pNode : graph.getNodes()) {
            if (pNode.hasProperties() && pNode.getProperty(Properties.RECT_SHAPE_DUMMY) != null) {
                dummyNodes.add(pNode);
            }
        }

        PEdge toRemove = null;
        for (PNode dummy : dummyNodes) {
            for (PEdge edge : dummy.adjacentEdges()) {
                if (edge.hasProperties() && edge.getProperty(Properties.RECT_SHAPE_DUMMY) != null) {
                    toRemove = edge;
                    break;
                }
            }
            graph.removeEdge(toRemove);
            Iterator<PEdge> it = dummy.adjacentEdges().iterator();
            PEdge first = it.next();
            PEdge second = it.next();

            if (first.hasProperties() && first.getProperty(Properties.ORIGIN) != null) {
                if (first.getTarget() == dummy) {
                    first.move(first.getSource(), second.getOppositeNode(dummy));
                } else {
                    first.move(second.getOppositeNode(dummy), first.getTarget());
                }
            } else if (second.hasProperties() && second.getProperty(Properties.ORIGIN) != null) {
                if (second.getTarget() == dummy) {
                    second.move(second.getSource(), first.getOppositeNode(dummy));
                } else {
                    second.move(first.getOppositeNode(dummy), second.getTarget());
                }
            } else {
                graph.addEdge(first.getOppositeNode(dummy), second.getOppositeNode(dummy));
            }
            graph.removeNode(dummy);
            grid.remove(dummy);
        }

        // This can happen, because dummy edges can be exist without dummy nodes.
        List<PEdge> dummyEdges = new LinkedList<PEdge>();
        for (PEdge pEdge : graph.getEdges()) {
            if (pEdge.hasProperties() && pEdge.getProperty(Properties.RECT_SHAPE_DUMMY) != null) {
                dummyEdges.add(pEdge);
            }
        }
        // that should reach
        for (PEdge dummy : dummyEdges) {
            graph.removeEdge(dummy);
        }

        // TODO Attention: the ortho repre is broken, angles arent ccw.

        getMonitor().done();
    }
}
