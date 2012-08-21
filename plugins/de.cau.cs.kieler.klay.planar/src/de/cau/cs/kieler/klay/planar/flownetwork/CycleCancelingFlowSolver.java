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
package de.cau.cs.kieler.klay.planar.flownetwork;

import java.util.ArrayList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.pathfinding.IPathFinder;

/**
 * 
 * @author pkl
 */
public class CycleCancelingFlowSolver extends AbstractAlgorithm implements IFlowNetworkSolver {

    /**
     * 
     */
    private static final int MAX_CAP = 1000;
    private PGraph graph;

    /**
     * {@inheritDoc}
     */
    public void findFlow(final PGraph network) {
        // erzeuge einen feasible flow x im Netzwerk;
        this.graph = network;

        // set networkflow arcs (edges) properties.
        for (PEdge arc : graph.getEdges()) {
            // capacity is infinite.
            arc.setProperty(CAPACITY, MAX_CAP);
            arc.setProperty(IPathFinder.PATHCOST, 1);
            arc.setProperty(LOWER_BOUND, 1);
        }

        // Add source and sink nodes
        PNode source = graph.addNode();
        PNode sink = graph.addNode();
        // Add some edges as follows
        for (PNode node : graph.getNodes()) {
            int s = node.getProperty(SUPPLY);
            // for each node i with bi > 0, add a source arc (s,i) with
            // capacity bi and cost 0.
            if (s > 0) {
                PEdge edge = graph.addEdge(source, node, true);
                edge.setProperty(CAPACITY, s);
                edge.setProperty(IPathFinder.PATHCOST, 0);
                // for each node i with bi < 0, add a sink arc (i,t) with
                // capacity -bi and cost 0.
            } else if (s < 0) {
                PEdge edge = graph.addEdge(node, sink, true);
                edge.setProperty(CAPACITY, -s);
                edge.setProperty(IPathFinder.PATHCOST, 0);
            }

        }

        // calcFeasibleFlow();
        PNode currentNode = source;
        List<PNode> visitedNodes = new ArrayList<PNode>();
        List<PEdge> visitedEdges = new ArrayList<PEdge>();

        while (true) {

            if (!visitedNodes.contains(currentNode)) {
                // calc edgeflow
                int incomingFlowCount = 0;
                for (PEdge edge : currentNode.incomingEdges()) {
                    incomingFlowCount += edge.getProperty(FLOW);
                }
                for (PEdge edge : currentNode.getEdges()) {
                    if (incomingFlowCount == 0) {
                        edge.setProperty(FLOW, 1);
                    }
                    visitedEdges.add(edge);
                }
                visitedNodes.add(currentNode);
            }
            currentNode = null;
            // determine new node.
            for (PEdge edge : visitedEdges) {
                if (!visitedNodes.contains(edge.getTarget())) {
                    boolean check = true;
                    for (PEdge incomingEdge : edge.getTarget().incomingEdges()) {
                        if (!visitedEdges.contains(incomingEdge)) {
                            check = false;
                        }
                    }
                    if (check) {
                        currentNode = edge.getTarget();
                        break;
                    }
                }
            }
            // if currentNode == null gehe eine edge zurück und such am vorgaenger nach freien
            // leitungen.

        }

        // build up the residual network.

        // while G(x) enthalt einen negativen Kreis do
        // begin
        // finde einen negativen Kreis W in G(x);
        // p = min frij : (i; j) 2 Wg;
        // erhohe den Fluß entlang W um p Einheiten;
        // end;
        // end;

    }

}
