/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 - 2012 by
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

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KEdge;
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
 * planar dummy nodes, rectangular shape dummy nodes and bend points dummies are removed. At the
 * beginning of the algorithm planar dummies are inserted, then bend point are added while the
 * orthogonalization step and then the rectangular shape dummies are added. The dummies have to be
 * removed in reversed order, meaning firstly rectangular shape dummies, secondly bend point dummies
 * and finally the planar dummy nodes.
 * 
 * @author pkl
 */
public class DummyRemovingProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /** The graph which is changed during the processor. */
    private PGraph graph;

    /** The grid that is changed during the processor. */
    private GridRepresentation grid;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pGraph) {
        getMonitor().begin("Remove dummynodes", 1);

        this.graph = pGraph;
        this.grid = graph.getProperty(Properties.GRID_REPRESENTATION);

        removeRectShapeDummies();

        removeBendDummies();

        removePlanarDummies();

        getMonitor().done();
    }

    /**
     * Goes through the grid and constructs bend point for every dummy node.
     */
    private void removeBendDummies() {
        // construct bendpoints for each node for which there is no original node.
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                if (grid.get(x, y) != null && grid.get(x, y).hasProperties()
                        && (grid.get(x, y).getProperty(Properties.BENDPOINT) != null)) {
                    constructBendPointEdge(x, y);
                }
            }
        }
    }

    /**
     * Removes a bend point dummy from the grid and adds it as bend point the edge, that is original
     * meaning contains a {@link KEdge} as property, otherwise choose a arbitrary one.
     * 
     * @param x
     *            index of the grid of the bend point dummy.
     * @param y
     *            index of the grid of the bend point dummy.
     */
    private void constructBendPointEdge(final int x, final int y) {
        PNode currentNode = grid.get(x, y);
        Iterator<PEdge> nodeIterator = currentNode.getEdges().iterator();

        PEdge first = nodeIterator.next();
        PNode firstNode = first.getOppositeNode(currentNode);
        PEdge second = nodeIterator.next();
        PNode secondNode = second.getOppositeNode(currentNode);

        if (first.hasProperties() && first.getProperty(Properties.ORIGIN) != null) {
            first.getBendPoints().add(x, y);
            first.getBendPoints().addAll(second.getBendPoints());
            this.graph.bridgeOverEdge(first, firstNode, secondNode);
        } else {
            second.getBendPoints().add(x, y);
            second.getBendPoints().addAll(first.getBendPoints());
            this.graph.bridgeOverEdge(second, secondNode, firstNode);
        }
        graph.removeNode(currentNode);
        grid.remove(currentNode);
    }

    /**
     * Searches rectangle shape dummy nodes. Remove the dummy edges around the dummy nodes and then
     * the dummy nodes themselves.
     */
    private void removeRectShapeDummies() {
        // Remove rectshape dummies
        List<PNode> dummyNodes = Lists.newLinkedList();
        for (PNode pNode : graph.getNodes()) {
            if (pNode.hasProperties() && pNode.getProperty(Properties.RECT_SHAPE_DUMMY) != null) {
                dummyNodes.add(pNode);
            }
        }

        PEdge removableEdge = null;
        for (PNode dummy : dummyNodes) {
            for (PEdge edge : dummy.adjacentEdges()) {
                if (edge.hasProperties() && edge.getProperty(Properties.RECT_SHAPE_DUMMY) != null) {
                    // Only the rect shape dummy edge has this property.
                    removableEdge = edge;
                    break;
                }
            }
            graph.removeEdge(removableEdge);

            Iterator<PEdge> it = dummy.adjacentEdges().iterator();
            PEdge first = it.next();
            PEdge second = it.next();

            // Check for origin is needed, because the original edge should is kept.
            if (first.hasProperties() && first.getProperty(Properties.ORIGIN) != null) {
                graph.bridgeOverEdge(first, first.getOppositeNode(dummy),
                        second.getOppositeNode(dummy));
            } else {
                // Use the second edge otherwise, if it is origin or not.
                graph.bridgeOverEdge(second, second.getOppositeNode(dummy),
                        first.getOppositeNode(dummy));
            }
            grid.remove(dummy);
            graph.removeNode(dummy);
        }

        // Dummy edges can be exist without dummy nodes.
        List<PEdge> dummyEdges = new LinkedList<PEdge>();
        for (PEdge pEdge : graph.getEdges()) {
            if (pEdge.hasProperties() && pEdge.getProperty(Properties.RECT_SHAPE_DUMMY) != null) {
                dummyEdges.add(pEdge);
            }
        }

        for (PEdge dummy : dummyEdges) {
            graph.removeEdge(dummy);
        }
    }

    /**
     * Searches for planar dummy nodes. Bridges the edges around that node and removes the node from
     * graph and grid.
     */
    private void removePlanarDummies() {
        OrthogonalRepresentation ortho = graph.getProperty(Properties.ORTHO_REPRESENTATION);

        List<PNode> planarDummynodes = new LinkedList<PNode>();
        for (PNode node : graph.getNodes()) {
            if (node.hasProperties() && node.getProperty(Properties.PlANAR_DUMMY_NODE) != null) {
                planarDummynodes.add(node);
            }
        }

        for (PNode dummyNode : planarDummynodes) {
            List<Pair<PEdge, OrthogonalAngle>> angles = ortho.getAngles(dummyNode);

            // move the original edge over the dummy node from source to new target or vice versa
            // according to the edge direction. Edge 0 and 2 are straight to each other so that
            // we can move the edge from source 0 to target 2 or vice versa.
            doPlanarRemoveStep(dummyNode, angles.get(0).getFirst(), angles.get(2).getFirst());

            // do the same to the edges 1 and 3.
            doPlanarRemoveStep(dummyNode, angles.get(1).getFirst(), angles.get(3).getFirst());

            graph.removeNode(dummyNode);
            grid.remove(dummyNode);
        }
    }

    /**
     * Bridge the edges of a dummy node. Thereby one of the edges is deleted and the other edge is
     * moved around the dummy node. The both parameter edges have to be straight to each other.
     * 
     * @param dummyNode
     *            {@link PNode}
     * @param first
     *            {@link PEdge} adjacent to dummy node
     * @param second
     *            {@link PEdge} adjacent to dummy node
     */
    private void doPlanarRemoveStep(final PNode dummyNode, final PEdge first, final PEdge second) {
        if (first.hasProperties() && first.getProperty(Properties.ORIGIN) != null) {
            first.getBendPoints().addAll(second.getBendPoints());
            graph.bridgeOverEdge(first, first.getOppositeNode(dummyNode),
                    second.getOppositeNode(dummyNode));
        } else if (second.hasProperties() && second.getProperty(Properties.ORIGIN) != null) {
            second.getBendPoints().addAll(first.getBendPoints());
            graph.bridgeOverEdge(second, second.getOppositeNode(dummyNode),
                    first.getOppositeNode(dummyNode));
        } else {
            first.getBendPoints().addAll(second.getBendPoints());
            graph.bridgeOverEdge(first, first.getOppositeNode(dummyNode),
                    second.getOppositeNode(dummyNode));

            List<PEdge> insertableEdges = graph.getProperty(Properties.INSERTABLE_EDGES);
            for (PEdge pEdge : insertableEdges) {
                if (first.getSource() == pEdge.getSource()
                        && first.getTarget() == pEdge.getTarget()) {
                    first.setProperty(Properties.ORIGIN, pEdge.getProperty(Properties.ORIGIN));
                    break;
                }
            }
        }
    }
}
