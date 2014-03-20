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
package de.cau.cs.kieler.klay.cola.processors;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.cola.graph.CEdge;
import de.cau.cs.kieler.klay.cola.graph.CGraph;
import de.cau.cs.kieler.klay.cola.graph.CNode;
import de.cau.cs.kieler.klay.cola.graph.CPort;
import de.cau.cs.kieler.klay.cola.properties.ColaProperties;

/**
 * @author uru
 * 
 */
public class NonUniformEdgeLengthProcessor implements ILayoutProcessor {

    private double idealEdgeLength = 0;

    /**
     * {@inheritDoc}
     */
    public void process(final CGraph graph, final IKielerProgressMonitor progressMonitor) {

        idealEdgeLength = graph.getProperty(ColaProperties.IDEAL_EDGE_LENGTHS);

        dummyPortEdgeLengths(graph);

//         symmDiffLengths(graph);
        connectivity(graph);
    }

    private void connectivity(final CGraph graph) {

        int[][] connectivity = new int[graph.getLastNodeIndex()][];

        // setup connectivity array
        // for every node we record how many edges are incident to every side of the node
        for (CNode n : graph.getChildren()) {

            connectivity[n.cIndex] = new int[5]; // SUPPRESS CHECKSTYLE MagicNumber

            // WEST
            connectivity[n.cIndex][0] += getIncidentEdgeCount(n, PortSide.UNDEFINED);
            connectivity[n.cIndex][1] += getIncidentEdgeCount(n, PortSide.WEST);
            connectivity[n.cIndex][2] += getIncidentEdgeCount(n, PortSide.NORTH);
            // SUPPRESS CHECKSTYLE NEXT 2 MagicNumber
            connectivity[n.cIndex][3] += getIncidentEdgeCount(n, PortSide.EAST);
            connectivity[n.cIndex][4] += getIncidentEdgeCount(n, PortSide.SOUTH);

        }

        // now use this information to determine the edge lengths of all edges
        for (CNode src : graph.getChildren()) {
            for (CEdge e : src.getOutgoingEdges()) {

                CPort srcPort = e.getSourcePort();
                CNode tgt = e.getTarget();
                CPort tgtPort = e.getTargetPort();

                if (srcPort == null || tgtPort == null) {
                    continue;
                }

                int srcWeight = 0;
                switch (srcPort.getProperty(LayoutOptions.PORT_SIDE)) {
                case UNDEFINED:
                    srcWeight = connectivity[src.cIndex][0];
                    break;
                case WEST:
                    srcWeight = connectivity[src.cIndex][1];
                    break;
                case NORTH:
                    srcWeight = connectivity[src.cIndex][2];
                    break;
                case EAST:
                    srcWeight = connectivity[src.cIndex][3]; // SUPPRESS CHECKSTYLE MagicNumber
                    break;
                case SOUTH:
                    srcWeight = connectivity[src.cIndex][4]; // SUPPRESS CHECKSTYLE MagicNumber
                    break;
                }

                int tgtWeight = 0;
                switch (tgtPort.getProperty(LayoutOptions.PORT_SIDE)) {
                case UNDEFINED:
                    tgtWeight = connectivity[tgt.cIndex][0];
                    break;
                case WEST:
                    tgtWeight = connectivity[tgt.cIndex][1];
                    break;
                case NORTH:
                    tgtWeight = connectivity[tgt.cIndex][2];
                    break;
                case EAST:
                    tgtWeight = connectivity[tgt.cIndex][3]; // SUPPRESS CHECKSTYLE MagicNumber
                    break;
                case SOUTH:
                    tgtWeight = connectivity[tgt.cIndex][4]; // SUPPRESS CHECKSTYLE MagicNumber
                    break;
                }

                double cumWeight = Math.max(1, srcWeight + tgtWeight - 1); // subtract the
                                                                           // connection between the
                                                                           // two nodes itself
                graph.idealEdgeLengths[e.cIndex] = idealEdgeLength * Math.sqrt(cumWeight);
                
                System.out.println(e + " " + graph.idealEdgeLengths[e.cIndex]);

            }
        }
    }

    /**
     * Counts the number of edges incident to a certain side of the passed node. If two edges
     * connect the same pair of nodes, they are counted as one.
     */
    private int getIncidentEdgeCount(final CNode n, final PortSide side) {
        Set<CNode> knownTargets = Sets.newHashSet();
        int incidentEdges = 0;
        for (CPort p : n.getPorts(side)) {
            // we only count edges with different connected node
            for (CEdge e : p.getConnectedEdges()) {

                CNode other = null;
                if (e.getTarget().equals(n)) {
                    other = e.getSource();
                } else {
                    other = e.getTarget();
                }

                if (!knownTargets.contains(other)) {
                    incidentEdges++;
                }

                knownTargets.add(other);
            }
        }
        return incidentEdges;
    }

    /**
     * Writes the ideal edge lengths of dummy port nodes to the graph's array.
     */
    private void dummyPortEdgeLengths(final CGraph graph) {
        // set the ideal edge lengths for the port dummy edges
        for (CNode n : graph.getChildren()) {
            for (CPort p : n.getPorts()) {
                // SUPPRESS CHECKSTYLE NEXT MagicNumber
                graph.idealEdgeLengths[p.cEdgeIndex] = p.idealDummyEdgeLength;
            }
        }
    }

    /**
     * 
     */
    @SuppressWarnings("unused")
    private void symmDiffLengths(final CGraph graph) {

        Map<CNode, Set<CNode>> neighbours = Maps.newHashMap();
        for (CNode node : graph.getChildren()) {
            Set<CNode> neighbourSet = Sets.newHashSet();
            for (CEdge e : node.getOutgoingEdges()) {
                neighbourSet.add(e.getTarget());
            }
            for (CEdge e : node.getIncomingEdges()) {
                neighbourSet.add(e.getSource());
            }
            neighbours.put(node, neighbourSet);
        }

        // calc link sizes for each edge
        for (CNode n : graph.getChildren()) {
            for (CEdge e : n.getOutgoingEdges()) {
                Set<CNode> srcSet = neighbours.get(e.getSource());
                Set<CNode> tgtSet = neighbours.get(e.getTarget());

                // calc link lengths
                int union = Sets.union(srcSet, tgtSet).size();
                int intersection = Sets.intersection(srcSet, tgtSet).size();

                // symmdifflinks
                double sqrt = Math.sqrt((double) (union - intersection));

                // jaccard
                // double jaccard = 1;
                // if (union > 1 && intersection > 1) {
                // jaccard = 1 / (intersection / (double) union);
                // }

                // System.out.println("Edge: " + e.cIndex + " " + (1 + idealEdgeLength * sqrt));
                graph.idealEdgeLengths[e.cIndex] = 1 + idealEdgeLength * sqrt;
                // edgeLengths[index] = 1 + w * jaccard;
            }
        }
    }
}
