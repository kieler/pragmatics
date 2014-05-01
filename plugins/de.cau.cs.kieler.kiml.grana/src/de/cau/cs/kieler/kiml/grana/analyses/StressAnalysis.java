/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * Calculates the stress within the graph.
 * 
 * Stress is calculated as follows, where {@code l} is the ideal edge length, {@code euclidean(u,v)}
 * the euclidean distance between nodes u and v, and {@code ideal(u,v)} the graph-theoretic distance
 * determined by an all-pairs shortest path algorithm, respectively.
 * 
 * <pre>
 *   > stress := Sum( (1 - euclidean(u,v)/(l*ideal(u,v)))^2 );
 *                 -----
 *                  \    /    euclidean(u, v)\2
 *       stress :=   )   |1 - ---------------|
 *                  /    \     l ideal(u, v) /
 *                 -----
 * 
 *   > l := Sum( euclidean(u,v)^2 / ideal(u,v)^2  ) / Sum( euclidean(u,v)/ideal(u,v)  );
 *                  -----                2
 *                   \    euclidean(u, v)
 *                    )   ----------------
 *                   /                 2
 *                  -----   ideal(u, v)
 *             l := ----------------------
 *                  -----
 *                   \    euclidean(u, v)
 *                    )   ---------------
 *                   /      ideal(u, v)
 *                  -----
 * 
 * </pre>
 * 
 * @author uru
 */
public class StressAnalysis implements IAnalysis {

    private Map<KNode, Integer> nodeMap = Maps.newHashMap();

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Stress analysis", 1);
        
        final boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                        AnalysisOptions.ANALYZE_HIERARCHY);

        // collect all nodes of the graph
        int index = 1;
        List<KNode> nodeQueue = new LinkedList<KNode>();
        List<KNode> nodes = new LinkedList<KNode>();
        nodeQueue.addAll(parentNode.getChildren());

        // assign indexes to all child nodes
        while (nodeQueue.size() > 0) {
            // pop first element
            KNode node = nodeQueue.remove(0);

            if (node.getChildren().isEmpty()) {
                nodeMap.put(node, index++);
                nodes.add(node);
            }

            // collect hierarchical nodes
            if (hierarchy) {
                nodeQueue.addAll(node.getChildren());
            }
        }

        int[][] shortestPaths = floydWarshall(nodes);

        // calculate ideal edge length
        double numerator = 0;
        double denominator = 0;
        for (KNode child : nodes) {
            for (KEdge e : child.getOutgoingEdges()) {
                KNode src = e.getSource();
                KNode tgt = e.getTarget();

                if (tgt.getChildren().isEmpty() && src.getParent().equals(tgt.getParent())) {
                    double geomDist = getNodeCenter(src).distance(getNodeCenter(tgt));
                    double theoDist = shortestPaths[nodeMap.get(src)][nodeMap.get(tgt)];

                    numerator += (geomDist * geomDist) / (theoDist * theoDist);
                    denominator += geomDist / theoDist;

                } else if (hierarchy) {
                    // same as above, just for hierarhcy crossing edges
                    for (KNode hTgt : followHierarchicalEdge(e)) {
                        double geomDist = getNodeCenter(src).distance(getNodeCenter(hTgt));
                        double theoDist = shortestPaths[nodeMap.get(src)][nodeMap.get(hTgt)];

                        numerator += (geomDist * geomDist) / (theoDist * theoDist);
                        denominator += geomDist / theoDist;
                    }
                }
            }
        }
        double idealEdgeLength = numerator / denominator;

        // calculate the stress
        double stress = 0;
        for (KNode child : nodes) {
            for (KEdge e : child.getOutgoingEdges()) {
                KNode src = e.getSource();
                KNode tgt = e.getTarget();

                if (tgt.getChildren().isEmpty() && src.getParent().equals(tgt.getParent())) {
                    double geomDist = getNodeCenter(src).distance(getNodeCenter(tgt));
                    double theoDist = shortestPaths[nodeMap.get(src)][nodeMap.get(tgt)];

                    stress += Math.pow(1 - (geomDist / (idealEdgeLength * theoDist)), 2);
                } else if (hierarchy) {
                    // same as above, just for hierarhcy crossing edges
                    for (KNode hTgt : followHierarchicalEdge(e)) {
                        double geomDist = getNodeCenter(src).distance(getNodeCenter(hTgt));
                        double theoDist = shortestPaths[nodeMap.get(src)][nodeMap.get(hTgt)];

                        stress += Math.pow(1 - (geomDist / (idealEdgeLength * theoDist)), 2);
                    }
                }
            }
        }
        
        // done
        progressMonitor.done();
        
        return stress;
    }

    /**
     * Executes the Floyd Warshall algorithm for the all-pairs shortest path problem.
     * 
     * @returns an adjacency matrix where index (u,v) contains the length of the shortest path from
     *          node u to node v.
     */
    private int[][] floydWarshall(final List<KNode> nodes) {

        // populate the adjacency matrix
        int n = nodes.size();
        int[][] matrix = new int[n + 1][n + 1];

        // set weights to infinity
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                // assure that adding two of this does not exceed INT_MAX
                matrix[i][j] = (Integer.MAX_VALUE / 2) - 1;
            }
        }

        // mark the nodes
        for (KNode child : nodes) {
            int i = nodeMap.get(child);
            matrix[i][i] = 0;
        }

        // mark the edges
        for (KNode child : nodes) {
            for (KEdge e : child.getOutgoingEdges()) {
                KNode src = e.getSource();
                KNode tgt = e.getTarget();
                // ignore hierarchy crossing edges
                if (tgt.getChildren().isEmpty() && src.getParent().equals(tgt.getParent())) {
                    // mark the edge matrix
                    matrix[nodeMap.get(src)][nodeMap.get(tgt)] = 1;
                } else {
                    // mark all possible hierarchy edges
                    for (KNode hTgt : followHierarchicalEdge(e)) {
                        matrix[nodeMap.get(src)][nodeMap.get(hTgt)] = 1;
                    }
                }
            }
        }

        // run floyd warshall
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        return matrix;
    }

    /**
     * @return the center of the passed node.
     */
    private KVector getNodeCenter(final KNode n) {
        KShapeLayout nLayout = n.getData(KShapeLayout.class);
        KVector pos = nLayout.createVector();
        pos.add(nLayout.getWidth() / 2f, nLayout.getHeight() / 2f);

        return pos;
    }

    /**
     * Follows an edge that does not end at an atomic node and eventually returns all target KNodes
     * of a hierarchy crossing edge.
     * 
     * @param edge
     *            for the first call edge must be an outgoing edge of an atomic node.
     */
    private Iterable<KNode> followHierarchicalEdge(final KEdge edge) {

        KNode tgt = edge.getTarget();
        // does the edge target an atomic node?
        if (tgt.getChildren().isEmpty()) {
            return ImmutableList.of(tgt);
        } else {
            // follow all edges of the target port
            if (edge.getTargetPort() != null) {

                List<Iterable<KNode>> targets = Lists.newLinkedList();
                // get edges where the current port is source
                for (KEdge hEdge : edge.getTargetPort().getEdges()) {
                    if (hEdge.getSource().equals(tgt)) {
                        targets.add(followHierarchicalEdge(hEdge));
                    }
                }
                return Iterables.concat(targets);
            }
        }

        return Collections.emptyList();
    }

}
