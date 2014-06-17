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

import de.cau.cs.kieler.adaptagrams.cgraph.CEdge;
import de.cau.cs.kieler.adaptagrams.cgraph.CGraph;
import de.cau.cs.kieler.adaptagrams.cgraph.CNode;
import de.cau.cs.kieler.adaptagrams.cgraph.CPort;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.cola.properties.CodaflowProperties;
import de.cau.cs.kieler.klay.cola.properties.EdgeLengthStrategy;

/**
 * Assigns an ideal edge length to every {@link CEdge} of the {@link CGraph}. The strategy to select
 * the ideal edge length is specified using the {@link CodaflowProperties#EDGE_LENGTH_STRATEGY}
 * property.
 * 
 * <h1>Two Special Cases</h1> 
 * Note that special edge lengths are assigned to 'port dummy edges',
 * edges that connect dummy ports with their parent nodes to maintain the 'force-flow' through the
 * whole graph.
 * 
 * Furthermore, edges to external ports have to be treated differently. We assign a value of 1 to 
 * straighten them as much as possible.
 * 
 * <h1>Known Limitations</h1> 
 * The {@link #getIncidentEdgeCount(CNode, PortSide)} method only works with ports.
 * 
 * @author uru
 */
public class IdealEdgeLengthProcessor implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final CGraph graph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Ideal Edge Length Determination", 1);
        
        final double idealEdgeLength = graph.getProperty(CodaflowProperties.IDEAL_EDGE_LENGTHS);

        // assign the edge lengths for the dummy port edges
        assignDummyPortEdgeLengths(graph);

        // handle external ports
        assignExternalPortEdgeValues(graph);

        // set ideal edge lengths for usual edges based on the specified strategy
        EdgeLengthStrategy ees = graph.getProperty(CodaflowProperties.EDGE_LENGTH_STRATEGY);
        switch (ees) {
        case CONNECTIVITY:
        case CONNECTIVITY_CAPPED:
            // assign values based on the connectivity
            assignConnectivityBasedValues(graph);
            break;

        case SYMMDIFF:
            symmDiffLengths(graph);
            break;

        default: // UNIFORM
            // assign the same value to all edges
            for (CNode n : graph.getChildren()) {
                for (CEdge e : n.getOutgoingEdges()) {
                    graph.idealEdgeLengths[e.cIndex] = idealEdgeLength;
                }
            }
            break;

        }

        //

        if (ees == EdgeLengthStrategy.CONNECTIVITY_CAPPED) {
            limitEdgeLengths(graph);
        }

        progressMonitor.done();
    }

    /**
     * For every node we record how many edges are incident to every side of the node.
     * 
     */
    private void assignConnectivityBasedValues(final CGraph graph) {

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

        final double idealEdgeLength = graph.getProperty(CodaflowProperties.IDEAL_EDGE_LENGTHS);
        // now use this information to determine the edge lengths of all edges
        for (CNode src : graph.getChildren()) {
            // System.out.println("Looking at " + src);
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

                // subtract the connection between the two nodes itself
                double cumWeight = Math.max(1, srcWeight + tgtWeight - 1);
                graph.idealEdgeLengths[e.cIndex] = idealEdgeLength * Math.sqrt(cumWeight);
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
                // TODO ignore external edges from the start
                if (e.getTarget() != null && e.getTarget().equals(n)) {
                    other = e.getSource();
                } else if (e.getSource() != null) {
                    other = e.getTarget();
                }

                if (other != null && !knownTargets.contains(other)) {
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
    private void assignDummyPortEdgeLengths(final CGraph graph) {
        // set the ideal edge lengths for the port dummy edges
        for (CNode n : graph.getChildren()) {
            for (CPort p : n.getPorts()) {
                // SUPPRESS CHECKSTYLE NEXT MagicNumber
                // TODO 
                float dummyPortBreathe = 0;
                graph.idealEdgeLengths[p.cEdgeIndex] = p.idealDummyEdgeLength + dummyPortBreathe;
            }
        }
    }

    private void assignExternalPortEdgeValues(final CGraph graph) {
        for (CNode n : graph.getChildren()) {
            // check for external ports
            for (CEdge e : n.getExternalEdges()) {
                if (e.getSourcePort() != null && e.getSourcePort().isExternal()
                        || e.getTargetPort() != null && e.getTargetPort().isExternal()) {
                    // for external ports try to straighten the edges as much as possible
                    // TODO is there a better strategy?
                    if (graph.getProperty(LayoutOptions.PORT_CONSTRAINTS).isSideFixed()) {
                        // when alignment constraints are used
                        // we set the ideal edge length smaller than the spacing
                        // SUPPRESS CHECKSTYLE NEXT 2 MagicNumber
                        graph.idealEdgeLengths[e.cIndex] =
                                graph.getProperty(LayoutOptions.BORDER_SPACING) * 1f;
                    } else {
                        // with clusters set them as short as possible
                        graph.idealEdgeLengths[e.cIndex] = 1;
                    }
                    continue;
                }
            }
        }
    }

    private void limitEdgeLengths(final CGraph graph) {
        final double spacing = graph.getProperty(LayoutOptions.SPACING);

        // find the maximal edge length amongst the 'usual' edges
        // note, that no edge lengths need to be changed if all of them are smaller than the spacing
        double maxLength = spacing;
        for (CNode n : graph.getChildren()) {
            for (CEdge e : n.getOutgoingEdges()) {
                maxLength = Math.max(maxLength, graph.idealEdgeLengths[e.cIndex]);
            }
        }

        // scale the values according to the new interval
        final double factor = spacing / maxLength;
        for (CNode n : graph.getChildren()) {
            for (CEdge e : n.getOutgoingEdges()) {
                graph.idealEdgeLengths[e.cIndex] *= factor;
            }
        }
    }

    /**
     * 
     */
    private void symmDiffLengths(final CGraph graph) {

        final Map<CNode, Set<CNode>> neighbours = Maps.newHashMap();
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

        final double idealEdgeLength = graph.getProperty(CodaflowProperties.IDEAL_EDGE_LENGTHS);
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

                graph.idealEdgeLengths[e.cIndex] = 1 + idealEdgeLength * sqrt;
            }
        }
    }
}
