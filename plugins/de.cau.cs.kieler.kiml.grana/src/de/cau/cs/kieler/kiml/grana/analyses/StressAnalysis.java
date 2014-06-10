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
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.util.Pair;
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

    private static Map<KNode, Integer> nodeMap = Maps.newHashMap();

    /** Our representation of infinity, allow to add two of them without getting an int overflow. */
    private static final int INFINITY = (Integer.MAX_VALUE / 2) - 1;

    /**
     * Calculates the euclidean distance between the two nodes' center points.
     */
    private static final DistanceStrategy EUCLIDEAN_CENTER_TO_CENTER_DISTANCE =
            new DistanceStrategy() {
                public double dist(final KNode u, final KNode v) {
                    return getNodeCenter(u).distance(getNodeCenter(v));
                }
            };

    /**
     * Calculates the euclidean distance between a line connecting the two nodes' centerpoints
     * clipped at the nodes bounding rectangles.
     */
    private static final DistanceStrategy EUCLIDEAN_BORDER_TO_BORDER_DISTANCE =
            new DistanceStrategy() {
                public double dist(final KNode u, final KNode v) {
                    return getBoundToBound(u, v);
                }
            };

    /**
     * Collection of strategies to be executed. Take care that the order is the same as in the
     * extension's definition (components of an analysis).
     */
    private static final List<DistanceStrategy> STRATEGIES = ImmutableList.of(
            EUCLIDEAN_CENTER_TO_CENTER_DISTANCE, EUCLIDEAN_BORDER_TO_BORDER_DISTANCE);

    private boolean debug = false;

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Stress analysis", 1);

        final boolean hierarchy =
                parentNode.getData(KShapeLayout.class).getProperty(
                        AnalysisOptions.ANALYZE_HIERARCHY);

        // collect all nodes of the graph
        int index = 1;
        List<KNode> nodeQueue = Lists.newArrayList();
        List<KNode> nodes = Lists.newArrayList();
        List<KEdge> edges = Lists.newArrayList();
        nodeQueue.addAll(parentNode.getChildren());

        nodeMap.clear();
        // assign indexes to all child nodes
        while (nodeQueue.size() > 0) {
            // pop first element
            KNode node = nodeQueue.remove(0);

            if (node.getChildren().isEmpty()) {
                nodeMap.put(node, index++);
                nodes.add(node);
                edges.addAll(node.getOutgoingEdges());
            }

            // collect hierarchical nodes
            if (hierarchy) {
                nodeQueue.addAll(node.getChildren());
            }
        }

        // execute all pairs shortest paths
        int[][] shortestPaths = floydWarshall(nodes);

        if (debug) {
            System.out.println(nodes);
            for (int i = 0; i < shortestPaths.length; i++) {
                System.out.print((i > 0 ? nodes.get(i - 1) : "\t\t") + ":\t");
                for (int j = 0; j < shortestPaths.length; j++) {
                    // SUPPRESS CHECKSTYLE NEXT MagicNumber
                    System.out.print((shortestPaths[i][j] < 1000 ? shortestPaths[i][j] : "x")
                            + "\t");
                }
                System.out.print("\n");
            }
        }

        // for every distance strategy, determine the stress
        Object[] stresses = new Object[STRATEGIES.size() + 1];
        for (int i = 0; i < stresses.length - 1; i++) {
            stresses[i] = determineStress(STRATEGIES.get(i), nodes, shortestPaths);
        }
        
        // FIXME
        
        double pStressL0 = computeIdealEdgeLengthForPStress(nodes, edges, shortestPaths);
        stresses[2] = getPStress(nodes, edges, shortestPaths, pStressL0); 
        
        // FIXME
        

        // done
        progressMonitor.done();

        return stresses;
    }

    private double determineStress(final DistanceStrategy strat, final List<KNode> nodes,
            final int[][] shortestPaths) {
        // calculate ideal edge length
        double numerator = 0;
        double denominator = 0;

        for (int i = 0; i < nodes.size(); ++i) {
            KNode src = nodes.get(i);
            for (int j = 0; j < nodes.size(); ++j) {
                if (i == j) {
                    continue;
                }
                KNode tgt = nodes.get(j);

                double theoDist = shortestPaths[nodeMap.get(src)][nodeMap.get(tgt)];

                if (theoDist < INFINITY) {
                    double geomDist = strat.dist(src, tgt);
                    numerator += (geomDist * geomDist) / (theoDist * theoDist);
                    denominator += geomDist / theoDist;
                }
            }
        }

        double idealEdgeLength = numerator / denominator;

        // calculate the stress
        double stress = 0;
        for (int i = 0; i < nodes.size(); ++i) {
            KNode src = nodes.get(i);
            for (int j = 0; j < nodes.size(); ++j) {
                if (i == j) {
                    continue;
                }
                KNode tgt = nodes.get(j);

                double theoDist = shortestPaths[nodeMap.get(src)][nodeMap.get(tgt)];
                if (theoDist < INFINITY) {
                    double geomDist = strat.dist(src, tgt);
                    double current = Math.pow(1 - (geomDist / (idealEdgeLength * theoDist)), 2);

                    if (debug) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(src + " -> " + tgt);
                        // SUPPRESS CHECKSTYLE NEXT MagicNumber
                        while (sb.length() < 60) {
                            sb.append(" ");
                        }
                        sb.append(String.format("%.6f", current));
                        sb.append("\td:" + (int) theoDist);
                        System.out.println(sb.toString());
                    }

                    stress += current;
                }
            }
        }
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
                matrix[i][j] = INFINITY;
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
                Integer u = nodeMap.get(src);
                Integer v = nodeMap.get(tgt);
                // handle hierarchy crossing edges
                if (tgt.getChildren().isEmpty() && src.getParent().equals(tgt.getParent())) {
                    // mark the edge matrix
                    matrix[u][v] = 1;
                    matrix[v][u] = 1;
                } else {
                    // mark all possible hierarchy edges
                    for (KNode hTgt : followHierarchicalEdge(e)) {
                        Integer hV = nodeMap.get(hTgt);
                        matrix[u][hV] = 1;
                        matrix[hV][u] = 1;
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
     * Follows an edge that does not end at an atomic node and eventually returns all target KNodes
     * of a hierarchy crossing edge.
     * 
     * @param edge
     *            for the first call edge must be an outgoing edge of an atomic node.
     */
    private static Iterable<KNode> followHierarchicalEdge(final KEdge edge) {

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

    /*
     * ----------------------- Distance Strategies -----------------------
     */

    /**
     * A specific strategy to calculate the distance between two nodes, eg center to center
     * euclidean distance.
     */
    private interface DistanceStrategy {
        double dist(final KNode u, final KNode v);
    }

    /**
     * @return the center of the passed node.
     */
    private static KVector getNodeCenter(final KNode n) {
        KShapeLayout nLayout = n.getData(KShapeLayout.class);
        KVector pos = nLayout.createVector();
        pos.add(nLayout.getWidth() / 2f, nLayout.getHeight() / 2f);

        return pos;
    }

    private static double getBoundToBound(final KNode n1, final KNode n2) {
        KShapeLayout ul = n1.getData(KShapeLayout.class);
        KShapeLayout vl = n2.getData(KShapeLayout.class);

        KVector u = getNodeCenter(n1);
        KVector v = getNodeCenter(n2);

        double centerToCenterDist = u.distance(v);

        KVector uToBorder = clipVector(v.clone().sub(u), ul.getWidth(), ul.getHeight());
        double uToBorderDist = uToBorder.length();

        KVector vToBorder = clipVector(u.clone().sub(v), vl.getWidth(), vl.getHeight());
        double vToBorderDist = vToBorder.length();

        double borderToBorderDist = centerToCenterDist - uToBorderDist - vToBorderDist;

        return borderToBorderDist;
    }

    /**
     * Clip the given vector to a rectangular box of given size. Copied from
     * {@link de.cau.cs.kieler.klay.force.FEdge}.
     * 
     * @param v
     *            vector relative to the center of the box
     * @param width
     *            width of the rectangular box
     * @param height
     *            height of the rectangular box
     */
    private static KVector clipVector(final KVector v, final double width, final double height) {
        double wh = width / 2, hh = height / 2;
        double absx = Math.abs(v.x), absy = Math.abs(v.y);
        double xscale = 1, yscale = 1;
        if (absx > wh) {
            xscale = wh / absx;
        }
        if (absy > hh) {
            yscale = hh / absy;
        }
        v.scale(Math.min(xscale, yscale));
        return v;
    }

    private static double computeIdealEdgeLengthForPStress(final List<KNode> nodes,
            final List<KEdge> edges, final int[][] shortestPaths) {

        // CHECKSTYLEOFF VariableName
        // 1. S = { b(u,v) for (u,v) in E }
        List<Double> S = Lists.newArrayListWithCapacity(edges.size());
        for (KEdge e : edges) {
            KNode u = e.getSource();
            KNode v = e.getTarget();
            // handle hierarchy crossing edges
            if (v.getChildren().isEmpty() && u.getParent().equals(v.getParent())) {
                // add b(u,v) to S
                double dist = getBoundToBound(u, v);
                S.add(dist);
            } else {
                // follow hierarchical edges and add b(u,v') where v' is the final atomic node
                for (KNode hV : followHierarchicalEdge(e)) {
                    // add b(u,v') to S
                    double dist = getBoundToBound(u, hV);
                    S.add(dist);
                }
            }
        }

        // 2. L0 = CHM(S)
        double L0 = contraharmonicMean(S);

        // 3. P0 = PS(L0)
        double P0 = getPStress(nodes, edges, shortestPaths, L0);

        // 4. C = { b(u,v)/p_{uv} : u != v ^ (u,v) not in E ^ b(u,v)/p_{uv} < L0 }
        Set<Pair<KNode, KNode>> nodePairs = Sets.newHashSet();
        for (KNode u : nodes) {
            for (KNode v : nodes) {
                if (!u.equals(v)) {
                    nodePairs.add(Pair.of(u, v));
                }
            }
        }
        // remove (u,v)s in edges
        for (KEdge e : edges) {
            nodePairs.remove(Pair.of(e.getSource(), e.getTarget()));
        }
        List<Double> C = Lists.newArrayList();
        for (Pair<KNode, KNode> pair : nodePairs) {
            double buv = getBoundToBound(pair.getFirst(), pair.getSecond());
            double theoDist =
                    shortestPaths[nodeMap.get(pair.getFirst())][nodeMap.get(pair.getSecond())];
            double frac = buv / theoDist;
            if (frac < L0) {
                C.add(frac);
            }
        }

        // 5. Sort C into a sequence c0, c1, ..., c_{m-1} in ascending order
        Collections.sort(C); // sorts the specified list into ascending order,

        // 6. cm = L0
        C.add(L0);

        // 7. for ( i = 0; i < m; i++ ) {
        for (int i = 0; i < C.size() - 1; i++) {
            // T = { c0, c1, ..., c_i }
            List<Double> T = C.subList(0, i);
            // U = union of S and T
            Set<Double> U = Sets.newHashSet();
            U.addAll(S);
            U.addAll(T);

            // L = CHM(U)
            double L = contraharmonicMean(U);

            // P = PS(L)
            double P = getPStress(nodes, edges, shortestPaths, L);

            // if ( ci <= L ^ L <= c_{i+1} ^ P < P0 ) {
            if (C.get(i) <= L && L <= C.get(i + 1) && P < P0) {
                L0 = L;
                P0 = P;
            }
        }

        // 8. return L0
        return L0;
    }

    private static double getPStress(final List<KNode> nodes, final List<KEdge> edges,
            final int[][] shortestPaths, final double idealEdgeLength) {

        // for u != v in nodes
        double sum1 = 0;
        for (KNode u : nodes) {
            for (KNode v : nodes) {
                double theoDist = shortestPaths[nodeMap.get(u)][nodeMap.get(v)];
                if (u.equals(v) || theoDist >= INFINITY) {
                    continue;
                }
                
                double wuv = Math.pow(idealEdgeLength * theoDist, -1 * 2);
                double geomDist = getBoundToBound(u, v);
                double base = Math.max(0, idealEdgeLength * theoDist - geomDist);
                double term = wuv * Math.pow(base, 2);

                sum1 += term;
            }
        }
        
        // for (u,v) in edges
        double sum2 = 0;
        for (KEdge e : edges) {
            double geomDist = getBoundToBound(e.getSource(), e.getTarget());
            double base = Math.max(0, geomDist - idealEdgeLength);
            double term = Math.pow(idealEdgeLength, -1 * 2) * Math.pow(base, 2);
            
            sum2 += term;
        }

        return sum1 + sum2;
    }
    
    /**
     * @param numbers
     *            a set of numbers
     * @return the contraharmonic mean of the set of numbers. sum(x^2, x in S) / sum(x, x in S)
     */
    private static double contraharmonicMean(final Iterable<Double> numbers) {
        double numerator = 0;
        double denominator = 0;
        for (Double d : numbers) {
            numerator += d * d;
            denominator += d;
        }

        return numerator / denominator;
    }
    
}
