/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.intermediate;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * This processor adds dummies to handle nodes with a higher degree than 4.
 * 
 * @author pkl
 */
public class ExpansionCycleProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /** Nodes with a higher degree than four are called high-degree nodes. */
    private static final int HIGH_DEGREE_BORDER = 4;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pgraph) {
        getMonitor().begin("Perform Giotto Process", 1);

        // filter nodes with degree higher than 4.
        List<PNode> hiDeNodes = Lists.newLinkedList();
        for (PNode node : pgraph.getNodes()) {
            if (node.getAdjacentEdgeCount() > HIGH_DEGREE_BORDER) {
                hiDeNodes.add(node);
            }
        }

        // split nodes with degree greater than 4.
        for (PNode higherDegreeNode : hiDeNodes) {
            List<PNode> dummies = Lists.newLinkedList();
            int currentDegree = higherDegreeNode.getAdjacentEdgeCount();
            int edgeCount = 0;
            List<PEdge> edges = (LinkedList<PEdge>) higherDegreeNode.adjacentEdges();

            // add first dummy of the expansion cycle.
            PNode dummyNode = pgraph.addNode();
            dummyNode.setProperty(Properties.EXPANSION_CYCLE_ORIGIN, higherDegreeNode);
            dummies.add(dummyNode);
            PEdge changableEdge = edges.get(0);

            if (higherDegreeNode == changableEdge.getSource()) {
                // let source be the new dummy.
                switchEdge(pgraph, changableEdge, dummyNode, higherDegreeNode);
            } else {
                // let target be the new dummy.
                switchEdge(pgraph, changableEdge, dummyNode, higherDegreeNode);
            }
            PNode prevNode = dummyNode;
            edgeCount++;
            currentDegree--;
            // add remaining elements of the expansion cycle.
            do {

                dummyNode = pgraph.addNode();
                dummyNode.setProperty(Properties.EXPANSION_CYCLE_ORIGIN, higherDegreeNode);
                dummies.add(dummyNode);

                // add a dummy to connect the dummy nodes (first of the embedding).
                PEdge dummyEdge = pgraph.addEdge(prevNode, dummyNode);
                dummyEdge.setProperty(Properties.EXPANSION_CYCLE_ORIGIN, higherDegreeNode);
                // change end of the edge of the higher degree node to the new dummy
                changableEdge = edges.get(edgeCount);
                edgeCount++;

                // add real edge (second of the embedding).
                if (higherDegreeNode == changableEdge.getSource()) {
                    // let source be the new dummy.
                    switchEdge(pgraph, changableEdge, dummyNode, higherDegreeNode);
                } else {
                    // let target be the new dummy.
                    switchEdge(pgraph, changableEdge, dummyNode, higherDegreeNode);
                }

                prevNode = dummyNode;
                currentDegree--;
                if (currentDegree == 0) {
                    // the last dummy has to be connected to the start degree.
                    dummyEdge = pgraph.addEdge(dummyNode, dummies.get(0));
                    dummyEdge.setProperty(Properties.EXPANSION_CYCLE_ORIGIN, higherDegreeNode);
                    break;
                }

            } while (true);

            higherDegreeNode.unlinkAll();
            pgraph.removeNode(higherDegreeNode);
            higherDegreeNode.setProperty(Properties.EXPANSION_CYCLE, dummies);
        }

        // computes the faces of the processor.
        pgraph.getFaces();

        // adds a property to the expansion cycle faces to identify them.
        markExpCycleFaces(pgraph);

        getMonitor().done();
    }

    /**
     * Changes the source or/and target of an edge.
     * 
     * @param graph
     *            pgraph environment
     * @param edge
     *            the changable edge
     * @param newNode
     *            new node
     * @param oldEndPoint
     *            old node which should be removed
     */
    public void switchEdge(final PGraph graph, final PEdge edge, final PNode newNode,
            final PNode oldEndPoint) {

        if (edge.getSource() == oldEndPoint) {
            edge.setSource(newNode);
        } else {
            edge.setTarget(newNode);
        }

        newNode.linkEdge(edge);

        graph.setChangedFaces();

    }

    /**
     * Sets a property to all expansion cycle faces, to identify them at the flow network creation.
     * 
     * @param graph
     *            , the graph for which the expansion cycle faces should be marked
     * 
     */
    private void markExpCycleFaces(final PGraph graph) {
        for (PFace face : graph.getFaces()) {
            boolean wantsMark = true;
            for (PNode node : face.adjacentNodes()) {
                PNode expCycleOrigin = node.getProperty(Properties.EXPANSION_CYCLE_ORIGIN);
                if (expCycleOrigin == null) {
                    wantsMark = false;
                    break;
                }
            }
            if (wantsMark) {
                face.setProperty(Properties.EXPANSION_CYCLE_FACE, Boolean.TRUE);
            }
        }
    }

}
