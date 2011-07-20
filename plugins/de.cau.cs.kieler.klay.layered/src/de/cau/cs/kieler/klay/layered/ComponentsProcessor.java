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
package de.cau.cs.kieler.klay.layered;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * A processor that is able to split an input graph into connected components and to pack those
 * components after layout.
 * 
 * <p>Splitting into components
 * <dl>
 *   <dt>Precondition:</dt><dd>an unlayered graph.</dd>
 *   <dt>Postcondition:</dt><dd>a list of graphs that represent the connected components of
 *     the input graph.</dd>
 * </dl>
 * </p>
 * 
 * <p>Packing components
 * <dl>
 *   <dt>Precondition:</dt><dd>a list of graphs with complete layout and layer assignment.</dd>
 *   <dt>Postcondition:</dt><dd>a single unlayered graph.</dd>
 * </dl>
 * </p>
 *
 * @author msp
 */
public class ComponentsProcessor extends AbstractAlgorithm {

    /**
     * Split the given graph into its connected components.
     * 
     * @param graph an input graph with layerless nodes
     * @return a list of components that can be processed one by one
     */
    public List<LayeredGraph> split(final LayeredGraph graph) {
        if (graph.getProperty(Properties.SEPARATE_CC)) {
            // set id of all nodes to 0
            for (LNode node : graph.getLayerlessNodes()) {
                node.id = 0;
            }
            
            // perform DFS starting on each node, collecting connected components
            List<LayeredGraph> components = new LinkedList<LayeredGraph>();
            for (LNode node : graph.getLayerlessNodes()) {
                List<LNode> component = dfs(node, null);
                if (component != null) {
                    LayeredGraph newGraph = new LayeredGraph();
                    newGraph.copyProperties(graph);
                    newGraph.getInsets().copy(graph.getInsets());
                    newGraph.getLayerlessNodes().addAll(component);
                    components.add(newGraph);
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
     * @param node a node
     * @return the connected component, or {@code null} if the node was already visited
     */
    private List<LNode> dfs(final LNode node, final List<LNode> component) {
        if (node.id == 0) {
            node.id = 1;
            List<LNode> c = component;
            if (c == null) {
                c = new LinkedList<LNode>();
            }
            c.add(node);
            for (LPort port1 : node.getPorts()) {
                for (LPort port2 : port1.getConnectedPorts()) {
                    dfs(port2.getNode(), c);
                }
            }
            return c;
        }
        return null;
    }
    
    /**
     * Pack the given components into a single graph.
     * 
     * @param components a list of components
     * @return a single graph that contains all components
     */
    public LayeredGraph pack(final List<LayeredGraph> components) {
        if (components.size() == 1) {
            LayeredGraph graph = components.get(0);
            // move all nodes away from the layers
            for (Layer layer : graph.getLayers()) {
                graph.getLayerlessNodes().addAll(layer.getNodes());
            }
            graph.getLayers().clear();
            return graph;
        } else if (components.size() <= 0) {
            return new LayeredGraph();
        }
        
        // assign priorities
        for (LayeredGraph graph : components) {
            int priority = 0;
            for (Layer layer : graph.getLayers()) {
                for (LNode node : layer.getNodes()) {
                    priority += node.getProperty(Properties.PRIORITY);
                }
            }
            graph.id = priority;
        }

        // sort the components by their priority and size
        Collections.sort(components, new Comparator<LayeredGraph>() {
            public int compare(final LayeredGraph graph1, final LayeredGraph graph2) {
                int prio = graph2.id - graph1.id;
                if (prio == 0) {
                    double size1 = graph1.getSize().x * graph1.getSize().y;
                    double size2 = graph2.getSize().x * graph2.getSize().y;
                    return Double.compare(size1, size2);
                }
                return prio;
            }
        });
        
        LayeredGraph result = new LayeredGraph();
        result.copyProperties(components.get(0));
        result.getInsets().copy(components.get(0).getInsets());
        
        // determine the maximal row width by the maximal box width and the total area
        double maxRowWidth = 0.0f;
        double totalArea = 0.0f;
        for (LayeredGraph graph : components) {
            KVector size = graph.getSize();
            maxRowWidth = Math.max(maxRowWidth, size.x);
            totalArea += size.x * size.y;
        }
        maxRowWidth = Math.max(maxRowWidth, (float) Math.sqrt(totalArea)
                * result.getProperty(Properties.ASPECT_RATIO));
        double spacing = 2 * result.getProperty(Properties.OBJ_SPACING);

        // place nodes iteratively into rows
        double xpos = 0, ypos = 0, highestBox = 0, broadestRow = spacing;
        for (LayeredGraph graph : components) {
            KVector size = graph.getSize();
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
        
        result.getSize().x = broadestRow;
        result.getSize().y = ypos + highestBox;
        return result;
    }
    
    /**
     * Move the source graph into the destination graph using a specified offset.
     * 
     * @param destGraph the destination graph
     * @param sourceGraph the source graph
     * @param offsetx x coordinate offset
     * @param offsety y coordinate offset
     */
    private void moveGraph(final LayeredGraph destGraph, final LayeredGraph sourceGraph,
            final double offsetx, final double offsety) {
        KVector graphOffset = sourceGraph.getOffset().translate(offsetx, offsety);
        for (Layer layer : sourceGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                node.getPosition().add(graphOffset);
                for (LPort port : node.getPorts()) {
                    for (LEdge edge : port.getOutgoingEdges()) {
                        edge.getBendPoints().translate(graphOffset);
                        for (LLabel label : edge.getLabels()) {
                            label.getPosition().add(graphOffset);
                        }
                    }
                }
                destGraph.getLayerlessNodes().add(node);
            }
        }
    }
    
}
