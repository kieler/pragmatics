/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * A processor that is able to split an input graph into connected components and to pack those
 * components after layout.
 * 
 * <p>
 * Splitting into components
 * <dl>
 * <dt>Precondition:</dt>
 * <dd>a graph.</dd>
 * <dt>Postcondition:</dt>
 * <dd>a list of graphs that represent the connected components of the input graph.</dd>
 * </dl>
 * </p>
 * 
 * <p>
 * Packing components
 * <dl>
 * <dt>Precondition:</dt>
 * <dd>a list of graphs with complete layout and layer assignment.</dd>
 * <dt>Postcondition:</dt>
 * <dd>a single graph.</dd>
 * </dl>
 * </p>
 * 
 * @author msp
 */
public class ComponentsProcessor extends AbstractAlgorithm {

    /** the incidence lists of the graph. */
    private List<PEdge>[] incidence;
    /** the visited array of the graph. */
    private boolean[] visited;

    /**
     * Initialize adjacency lists.
     * 
     * @param graph
     *            a force graph
     */
    @SuppressWarnings("unchecked")
    private void initialize(final PGraph graph) {
        int n = graph.getNodes().size();
        incidence = new List[n];
        visited = new boolean[n];

        // create incidence lists
        for (PNode node : graph.getNodes()) {
            incidence[node.id] = new LinkedList<PEdge>();
        }
        // add edges to incidence lists
        for (PEdge edge : graph.getEdges()) {
            incidence[edge.getSource().id].add(edge);
            incidence[edge.getTarget().id].add(edge);
        }
    }

    /**
     * Split the given graph into its connected components.
     * 
     * @param graph
     *            an input graph
     * @return a list of components that can be processed one by one
     */
    public List<PGraph> split(final PGraph graph) {
        Boolean separate = graph.getProperty(LayoutOptions.SEPARATE_CC);
        if (separate == null || separate.booleanValue()) {
            initialize(graph);

            // perform DFS starting on each node, collecting connected components
            List<PGraph> components = new LinkedList<PGraph>();
            for (PNode node : graph.getNodes()) {
                PGraph comp = dfs(node, null);
                if (comp != null) {
                    comp.copyProperties(graph);
                    components.add(comp);
                }
            }
            incidence = null;
            visited = null;

            // redistribute identifier numbers to each component
            // TODO Problem: Id of nodes can be n1,n2,n3,n5,n4, then n5 gets a smaller id than n4.
            // that does not work for the boyer myrvold algorithm.
            if (components.size() > 1) {
                for (PGraph comp : components) {
                    int id = 0;
                    // sort the components by their priority and size
                    for (PNode node : comp.getNodes()) {
                        node.id += id++;
                    }
                }
            }
            return components;
        }
        return Lists.newArrayList(graph);
    }

    /**
     * Perform a DFS starting on the given node and collect all nodes that are found in the
     * corresponding connected component.
     * 
     * @param node
     *            a node
     * @param graph
     *            a graph representing a connected component, or {@code null}
     * @return the connected component, or {@code null} if the node was already visited
     */
    private PGraph dfs(final PNode node, final PGraph graph) {
        if (!visited[node.id]) {
            visited[node.id] = true;
            PGraph component = graph;
            if (component == null) {
                component = new PGraph();
            }
            component.getNodes().add(node);
            for (PEdge edge : incidence[node.id]) {
                if (edge.getSource() != node) {
                    dfs(edge.getSource(), component);
                }
                if (edge.getTarget() != node) {
                    dfs(edge.getTarget(), component);
                }
                component.getEdges().add(edge);
                // TODO labels? component.getLabels().addAll(edge.getLabels());
            }
            return component;
        }
        return null;
    }

    /**
     * Pack the given components into a single graph.
     * 
     * @param components
     *            a list of components
     * @return a single graph that contains all components
     */
    public PGraph pack(final List<PGraph> components) {
        if (components.size() == 1) {
            return components.get(0);
        } else if (components.size() <= 0) {
            return new PGraph();
        }

        // assign priorities and sizes
        for (PGraph graph : components) {
            int priority = 0;
            double minx = Integer.MAX_VALUE;
            double miny = Integer.MAX_VALUE;
            double maxx = Integer.MIN_VALUE;
            double maxy = Integer.MIN_VALUE;
            for (PNode node : graph.getNodes()) {
                Integer p = node.getProperty(LayoutOptions.PRIORITY);
                if (p != null) {
                    priority += p;
                }
                minx = Math.min(minx, node.getPosition().x);
                miny = Math.min(miny, node.getPosition().y);
                maxx = Math.max(maxx, node.getPosition().x + node.getSize().x);
                maxy = Math.max(maxy, node.getPosition().y + node.getSize().y);
            }
            graph.setProperty(Properties.PRIORITY, priority);
            graph.setProperty(Properties.BB_UPLEFT, new KVector(minx, miny));
            graph.setProperty(Properties.BB_LOWRIGHT, new KVector(maxx, maxy));
        }

        // sort the components by their priority and size
        Collections.sort(components, new Comparator<PGraph>() {
            public int compare(final PGraph graph1, final PGraph graph2) {
                int prio = graph2.getProperty(Properties.PRIORITY)
                        - graph1.getProperty(Properties.PRIORITY);
                if (prio == 0) {
                    KVector size1 = KVector.diff(graph1.getProperty(Properties.BB_LOWRIGHT),
                            graph1.getProperty(Properties.BB_UPLEFT));
                    KVector size2 = KVector.diff(graph2.getProperty(Properties.BB_LOWRIGHT),
                            graph2.getProperty(Properties.BB_UPLEFT));
                    return Double.compare(size1.x * size1.y, size2.x * size2.y);
                }
                return prio;
            }
        });

        PGraph result = new PGraph();
        result.copyProperties(components.get(0));

        // determine the maximal row width by the maximal box width and the total area
        double maxRowWidth = 0.0f;
        double totalArea = 0.0f;
        for (PGraph graph : components) {
            KVector size = KVector.diff(graph.getProperty(Properties.BB_LOWRIGHT),
                    graph.getProperty(Properties.BB_UPLEFT));
            maxRowWidth = Math.max(maxRowWidth, size.x);
            totalArea += size.x * size.y;
        }
        maxRowWidth = Math.max(maxRowWidth,
                (float) Math.sqrt(totalArea) * result.getProperty(Properties.ASPECT_RATIO));
        double spacing = result.getProperty(Properties.SPACING);

        // place nodes iteratively into rows
        double xpos = 0, ypos = 0, highestBox = 0, broadestRow = spacing;
        for (PGraph graph : components) {
            KVector size = KVector.diff(graph.getProperty(Properties.BB_LOWRIGHT),
                    graph.getProperty(Properties.BB_UPLEFT));
            if (xpos + size.x > maxRowWidth) {
                // place the graph into the next row
                xpos = 0;
                ypos += highestBox + spacing;
                highestBox = 0;
            }
            moveGraph(result, graph, xpos, ypos);
            broadestRow = Math.max(broadestRow, xpos + size.x);
            highestBox = Math.max(highestBox, size.y);
            xpos += size.x + spacing;
        }

        return result;
    }

    /**
     * Move the source graph into the destination graph using a specified offset.
     * 
     * @param destGraph
     *            the destination graph
     * @param sourceGraph
     *            the source graph
     * @param offsetx
     *            x coordinate offset
     * @param offsety
     *            y coordinate offset
     */
    private void moveGraph(final PGraph destGraph, final PGraph sourceGraph, final double offsetx,
            final double offsety) {
        KVector graphOffset = new KVector(offsetx, offsety);
        graphOffset.sub(sourceGraph.getProperty(Properties.BB_UPLEFT));
        for (PNode node : sourceGraph.getNodes()) {
            node.getPosition().add(graphOffset);
            destGraph.getNodes().add(node);
        }
        // for (PEdge edge : sourceGraph.getEdges()) {
        // for (FBendpoint bendpoint : edge.getBendpoints()) {
        // bendpoint.getPosition().add(graphOffset);
        // }
        // destGraph.getEdges().add(edge);
        // }
        // for (PLabel label : sourceGraph.getLabels()) {
        // label.getPosition().add(graphOffset);
        // destGraph.getLabels().add(label);
        // }
    }

}
