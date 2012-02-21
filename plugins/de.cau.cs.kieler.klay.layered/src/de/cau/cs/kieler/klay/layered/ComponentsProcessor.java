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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.GraphProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * A processor that is able to split an input graph into connected components and to pack those
 * components after layout.
 * 
 * <p>If the graph has no external ports, splitting it into components is straightforward and always
 * works. If on the other hand it does have external ports, splitting the graph into connected
 * components is problematic because the port positions might introduce constraints on the placement
 * of the different components. More or less simple solutions have only been implemented for the cases
 * of port constraints set to {@link de.cau.cs.kieler.kiml.options.PortConstraints#FREE FREE} or
 * {@link de.cau.cs.kieler.kiml.options.PortConstraints#FIXED_SIDE FIXED_SIDE}. If the graph contains
 * external ports with port constraints other than these, connected components processing is disabled
 * even if requested by the user.</p>
 * 
 * <p>Splitting into components
 * <dl>
 *   <dt>Precondition:</dt>
 *     <dd>an unlayered graph.</dd>
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
 * @author cds
 */
public class ComponentsProcessor extends AbstractAlgorithm {

    /**
     * Split the given graph into its connected components.
     * 
     * @param graph an input graph with layerless nodes
     * @return a list of components that can be processed one by one
     */
    public List<LayeredGraph> split(final LayeredGraph graph) {
        List<LayeredGraph> result;
        
        // Whether separate components processing is requested
        Boolean separateProperty = graph.getProperty(LayoutOptions.SEPARATE_CC);
        boolean separate = separateProperty == null || separateProperty.booleanValue();
        
        // Whether the graph contains external ports
        boolean extPorts =
                graph.getProperty(Properties.GRAPH_PROPERTIES).contains(GraphProperties.EXTERNAL_PORTS);
        
        // The graph's external port constraints
        PortConstraints extPortConstraints = graph.getProperty(LayoutOptions.PORT_CONSTRAINTS);
        boolean compatiblePortConstraints = extPortConstraints == PortConstraints.FREE
                || extPortConstraints == PortConstraints.FIXED_SIDE;
        
        // The graph may only be separated 
        //  1. Separation was requested.
        //  2. If the graph contains external ports, port constraints are set to either
        //     FREE or FIXED_SIDES.
        if (separate && (compatiblePortConstraints || !extPorts)) {
            // Set id of all nodes to 0
            for (LNode node : graph.getLayerlessNodes()) {
                node.id = 0;
            }
            
            // Perform DFS starting on each node, collecting connected components
            result = new LinkedList<LayeredGraph>();
            for (LNode node : graph.getLayerlessNodes()) {
                Pair<List<LNode>, Set<PortSide>> componentData = dfs(node, null);
                if (componentData != null) {
                    LayeredGraph newGraph = new LayeredGraph();
                    newGraph.copyProperties(graph);
                    newGraph.setProperty(Properties.EXT_PORT_CONNECTIONS, componentData.getSecond());
                    newGraph.getInsets().copy(graph.getInsets());
                    newGraph.getLayerlessNodes().addAll(componentData.getFirst());
                    result.add(newGraph);
                }
            }
        } else {
            result = new ArrayList<LayeredGraph>(1);
            result.add(graph);
        }
        
        return result;
    }
    
    /**
     * Perform a DFS starting on the given node, collect all nodes that are found in the corresponding
     * connected component and return the set of external port sides the component connects to.
     * 
     * @param node a node.
     * @param data pair of nodes in the component and external port sides used to produce the result
     *             during recursive calls. Should be {@code null} when this method is called.
     * @return a pairing of the connected component and the set of port sides of external ports it
     *         connects to, or {@code null} if the node was already visited
     */
    private Pair<List<LNode>, Set<PortSide>> dfs(final LNode node,
            final Pair<List<LNode>, Set<PortSide>> data) {
        
        if (node.id == 0) {
            // Mark the node as visited
            node.id = 1;
            
            // Check if we already have a list of nodes for the connected component
            Pair<List<LNode>, Set<PortSide>> mutableData = data;
            if (mutableData == null) {
                List<LNode> component = new LinkedList<LNode>();
                Set<PortSide> extPortSides = EnumSet.noneOf(PortSide.class);
                
                mutableData = new Pair<List<LNode>, Set<PortSide>>(component, extPortSides);
            }
            
            // Add this node to the component
            mutableData.getFirst().add(node);
            
            // Check if this node is an external port dummy and, if so, add its side
            if (node.getProperty(Properties.NODE_TYPE) == NodeType.EXTERNAL_PORT) {
                mutableData.getSecond().add(node.getProperty(Properties.EXT_PORT_SIDE));
            }
            
            // DFS
            for (LPort port1 : node.getPorts()) {
                for (LPort port2 : port1.getConnectedPorts()) {
                    dfs(port2.getNode(), mutableData);
                }
            }
            
            return mutableData;
        }
        
        // The node was already visited
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
            for (Layer layer : graph) {
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
            for (Layer layer : graph) {
                for (LNode node : layer) {
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
        for (Layer layer : sourceGraph) {
            for (LNode node : layer) {
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
