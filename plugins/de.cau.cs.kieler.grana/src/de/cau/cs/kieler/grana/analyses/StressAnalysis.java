/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.grana.analyses;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.elk.core.math.ElkMath;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.core.util.Pair;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisOptions;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * Calculates the stress within the graph.
 * 
 * Stress is calculated as follows, where {@code l} is the ideal edge length, {@code euclidean(u,v)}
 * the euclidean distance between nodes u and v, and {@code ideal(u,v)} the graph-theoretic distance
 * determined by an all-pairs shortest path algorithm, respectively.
 * 
 * <h3>Usual Stress</h3>
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
 * <h3>P-stress</h3>
 * P-stress is an extension of stress.
 * 
 * @author uru
 */
public class StressAnalysis implements IAnalysis {

    private Map<ElkNode, Integer> nodeMap = Maps.newHashMap();

    /** Our representation of infinity, allow to add two of them without getting an int overflow. */
    private static final int INFINITY = (Integer.MAX_VALUE / 2) - 1;

    private boolean debug = false;

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Stress analysis", 1);

        final boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);

        // collect all nodes of the graph
        int index = 1;
        List<ElkNode> nodeQueue = Lists.newArrayList();
        List<ElkNode> nodes = Lists.newArrayList();
        List<ElkEdge> edges = Lists.newArrayList();
        nodeQueue.addAll(parentNode.getChildren());

        nodeMap = Maps.newHashMap();
        nodeMap.clear();
        // assign indexes to all child nodes
        while (nodeQueue.size() > 0) {
            // pop first element
            ElkNode node = nodeQueue.remove(0);

            if (node.getChildren().isEmpty()) {
                nodeMap.put(node, index++);
                nodes.add(node);
                Iterables.addAll(edges, ElkGraphUtil.allOutgoingEdges(node));
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

        // SUPPRESS CHECKSTYLE NEXT 2 MagicNumber
        // for every distance strategy, determine the stress
        Object[] stresses = new Object[3];

        // usual stress with center to center
        double idealEdgeLengthCtc =
                computeStressIdealEdgeLength(nodes, shortestPaths,
                        euclideanCenterToCenterDistance);
        stresses[0] =
                computeStress(nodes, shortestPaths, idealEdgeLengthCtc,
                        euclideanCenterToCenterDistance);

        // usual stress with border to border
        double idealEdgeLengthBtb =
                computeStressIdealEdgeLength(nodes, shortestPaths,
                        euclideanBorderToBorderDistance);
        stresses[1] =
                computeStress(nodes, shortestPaths, idealEdgeLengthBtb,
                        euclideanBorderToBorderDistance);

        // pstress
        double pStressL0 = computePstressIdealEdgeLength(nodes, edges, shortestPaths);
        stresses[2] = getPStress(nodes, edges, shortestPaths, pStressL0);

        // done
        progressMonitor.done();

        return stresses;
    }

    /*
     * ----------------------- Floyd Warshall -----------------------
     */

    /**
     * Executes the Floyd Warshall algorithm for the all-pairs shortest path problem.
     * 
     * @returns an adjacency matrix where index (u,v) contains the length of the shortest path from
     *          node u to node v.
     */
    private int[][] floydWarshall(final List<ElkNode> nodes) {

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
        for (ElkNode child : nodes) {
            int i = nodeMap.get(child);
            matrix[i][i] = 0;
        }

        // mark the edges
        for (ElkNode child : nodes) {
            for (ElkEdge e : ElkGraphUtil.allOutgoingEdges(child)) {
                ElkNode src = ElkGraphUtil.getSourceNode(e);
                ElkNode tgt = ElkGraphUtil.getTargetNode(e);
                Integer u = nodeMap.get(src);
                Integer v = nodeMap.get(tgt);
                // handle hierarchy crossing edges
                if (tgt.getChildren().isEmpty() && src.getParent().equals(tgt.getParent())) {
                    // mark the edge matrix
                    matrix[u][v] = 1;
                    matrix[v][u] = 1;
                } else {
                    // mark all possible hierarchy edges
                    for (ElkNode hTgt : followHierarchicalEdge(e)) {
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
     * Follows an edge that does not end at an atomic node and eventually returns all target ElkNodes
     * of a hierarchy crossing edge.
     * 
     * @param edge
     *            for the first call edge must be an outgoing edge of an atomic node.
     */
    private Iterable<ElkNode> followHierarchicalEdge(final ElkEdge edge) {

        ElkNode tgt = ElkGraphUtil.getTargetNode(edge);
        // does the edge target an atomic node?
        if (tgt.getChildren().isEmpty()) {
            return ImmutableList.of(tgt);
        } else {
            // follow all edges of the target port
            if (edge.getTargets().get(0) instanceof ElkPort) {

                ElkPort targetPort = ElkGraphUtil.connectableShapeToPort(edge.getTargets().get(0));
                List<Iterable<ElkNode>> targets = Lists.newLinkedList();
                // get edges where the current port is source
                for (ElkEdge hEdge : ElkGraphUtil.allIncidentEdges(targetPort)) {
                    ElkNode src = ElkGraphUtil.getSourceNode(hEdge);
                    if (src.equals(tgt)) {
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
     * Calculates the euclidean distance between the two nodes' center points.
     */
    private final DistanceStrategy euclideanCenterToCenterDistance = new DistanceStrategy() {
        public double dist(final ElkNode u, final ElkNode v) {
            return getNodeCenter(u).distance(getNodeCenter(v));
        }
    };

    /**
     * Calculates the euclidean distance between a line connecting the two nodes' centerpoints
     * clipped at the nodes bounding rectangles.
     */
    private final DistanceStrategy euclideanBorderToBorderDistance = new DistanceStrategy() {
        public double dist(final ElkNode u, final ElkNode v) {
            return getBoundToBound(u, v);
        }
    };

    /**
     * A specific strategy to calculate the distance between two nodes, eg center to center
     * euclidean distance.
     */
    private interface DistanceStrategy {
        double dist(ElkNode u, ElkNode v);
    }

    /**
     * @return the center of the passed node.
     */
    private KVector getNodeCenter(final ElkNode n) {
        KVector pos = new KVector(n.getX(), n.getY());
        pos.add(n.getWidth() / 2d, n.getHeight() / 2d);

        return pos;
    }

    private double getBoundToBound(final ElkNode n1, final ElkNode n2) {
        KVector u = getNodeCenter(n1);
        KVector v = getNodeCenter(n2);

        double centerToCenterDist = u.distance(v);

        KVector uToBorder = ElkMath.clipVector(v.clone().sub(u), n1.getWidth(), n1.getHeight());
        double uToBorderDist = uToBorder.length();

        KVector vToBorder = ElkMath.clipVector(u.clone().sub(v), n2.getWidth(), n2.getHeight());
        double vToBorderDist = vToBorder.length();

        double borderToBorderDist = centerToCenterDist - uToBorderDist - vToBorderDist;

        return borderToBorderDist;
    }

    /*
     * ----------------------- Usual Stress -----------------------
     */
    
    private double computeStressIdealEdgeLength(final List<ElkNode> nodes,
            final int[][] shortestPaths, final DistanceStrategy strat) {
        // calculate ideal edge length
        double numerator = 0;
        double denominator = 0;

        for (int i = 0; i < nodes.size(); ++i) {
            ElkNode src = nodes.get(i);
            for (int j = 0; j < nodes.size(); ++j) {
                if (i == j) {
                    continue;
                }
                ElkNode tgt = nodes.get(j);

                double theoDist = shortestPaths[nodeMap.get(src)][nodeMap.get(tgt)];

                if (theoDist < INFINITY) {
                    double geomDist = strat.dist(src, tgt);
                    numerator += (geomDist * geomDist) / (theoDist * theoDist);
                    denominator += geomDist / theoDist;
                }
            }
        }

        double idealEdgeLength = numerator / denominator;
        return idealEdgeLength;
    }

    private double computeStress(final List<ElkNode> nodes, final int[][] shortestPaths,
            final double idealEdgeLength, final DistanceStrategy strat) {

        // calculate the stress
        double stress = 0;
        for (int i = 0; i < nodes.size(); ++i) {
            ElkNode src = nodes.get(i);
            for (int j = 0; j < nodes.size(); ++j) {
                if (i == j) {
                    continue;
                }
                ElkNode tgt = nodes.get(j);

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

    /*
     * ----------------------- P-stress -----------------------
     */

    private double computePstressIdealEdgeLength(final List<ElkNode> nodes,
            final List<ElkEdge> edges, final int[][] shortestPaths) {

        // CHECKSTYLEOFF VariableName
        // 1. S = { b(u,v) for (u,v) in E }
        List<Double> S = Lists.newArrayListWithCapacity(edges.size());
        for (ElkEdge e : edges) {
            ElkNode u = ElkGraphUtil.getSourceNode(e);
            ElkNode v = ElkGraphUtil.getTargetNode(e);
            // handle hierarchy crossing edges
            if (v.getChildren().isEmpty() && u.getParent().equals(v.getParent())) {
                // add b(u,v) to S
                double dist = getBoundToBound(u, v);
                S.add(dist);
            } else {
                // follow hierarchical edges and add b(u,v') where v' is the final atomic node
                for (ElkNode hV : followHierarchicalEdge(e)) {
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

        // 4. C = { b(u,v)/p_{uv} : u < v ^ (u,v) not in E ^ b(u,v)/p_{uv} < L0 }
        Set<Pair<ElkNode, ElkNode>> nodePairs = Sets.newHashSet();
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                ElkNode u = nodes.get(i);
                ElkNode v = nodes.get(j);
                nodePairs.add(Pair.of(u, v));
            }
        }

        // remove (u,v)s in edges
        for (ElkEdge e : edges) {
            ElkNode u = ElkGraphUtil.getSourceNode(e);
            ElkNode v = ElkGraphUtil.getTargetNode(e);
            nodePairs.remove(Pair.of(u, v));
            // to achieve u < v, remove the opposite as well
            // FIXME is this enough?
            nodePairs.remove(Pair.of(v, u));
        }
        List<Double> C = Lists.newArrayList();
        for (Pair<ElkNode, ElkNode> pair : nodePairs) {
            double buv = getBoundToBound(pair.getFirst(), pair.getSecond());
            double theoDist =
                    shortestPaths[nodeMap.get(pair.getFirst())][nodeMap.get(pair.getSecond())];
            double frac = buv / theoDist;
            if (frac < L0) {
                C.add(frac);
            }
        }

        // 5. Sort C into a sequence c0, c1, ..., c_{m-1} in ascending order
        Collections.sort(C); // sorts the specified list into ascending order

        // 6. cm = L0
        C.add(L0);

        // 7. for ( i = 0; i < m; i++ ) {
        for (int i = 0; i < C.size() - 1; i++) {
            // T = { c0, c1, ..., c_i }
            List<Double> T = C.subList(0, i + 1);
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

    private double getPStress(final List<ElkNode> nodes, final List<ElkEdge> edges,
            final int[][] shortestPaths, final double idealEdgeLength) {

        // for u < v in nodes
        double sum1 = 0;
        for (ElkNode u : nodes) {
            for (ElkNode v : nodes) {
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
        // divide by two as we added every stress term twice before
        sum1 /= 2;

        // for (u,v) in edges
        double sum2 = 0;
        for (ElkEdge e : edges) {
            ElkNode u = ElkGraphUtil.getSourceNode(e);
            ElkNode v = ElkGraphUtil.getTargetNode(e);
            double geomDist = getBoundToBound(u, v);
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
    private double contraharmonicMean(final Iterable<Double> numbers) {
        double numerator = 0;
        double denominator = 0;
        for (Double d : numbers) {
            numerator += d * d;
            denominator += d;
        }

        return numerator / denominator;
    }

}
