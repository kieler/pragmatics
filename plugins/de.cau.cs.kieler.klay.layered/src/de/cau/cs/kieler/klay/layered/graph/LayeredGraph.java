/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.graph;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.Properties.NodeType;

/**
 * A layered graph has a set of layers that contain the nodes, as well as a
 * list of nodes that are not yet assigned to a layer. Layout algorithms are
 * required to layout the graph from left to right. If another layout direction
 * is desired, it can be obtained by pre-processing and post-processing the graph.
 * 
 * <p>TODO add methods to rotate / mirror the graph for alternative layout
 * directions</p>
 *
 * @author msp
 */
public class LayeredGraph extends LGraphElement {

    /** the total size of the drawing, without offset. */
    private KVector size = new KVector();
    /** the offset to be added to all positions. */
    private KVector offset = new KVector();
    /** nodes that are not currently part of a layer. */
    private List<LNode> layerlessNodes = new LinkedList<LNode>();
    /** the layers of the layered graph. */
    private List<Layer> layers = new LinkedList<Layer>();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "G[" + layerlessNodes.toString() + ", " + layers.toString() + "]";
    }

    /**
     * Returns the size of the graph, that is the bounding box that covers the
     * whole drawing.
     * 
     * @return the size of the layered graph
     */
    public KVector getSize() {
        return size;
    }

    /**
     * Returns the offset for the graph, that is a coordinate vector that has
     * to be added to all position values of nodes and edges.
     * 
     * @return the offset of the layered graph
     */
    public KVector getOffset() {
        return offset;
    }
    
    /**
     * Returns the list of nodes that are not currently assigned to a layer.
     * 
     * @return the layerless nodes.
     */
    public List<LNode> getLayerlessNodes() {
        return layerlessNodes;
    }

    /**
     * Returns the list of layers of the graph.
     * 
     * @return the layers
     */
    public List<Layer> getLayers() {
        return layers;
    }
    
    /**
     * Outputs a representation of this graph in dot format to the given writer. The
     * following conventions are used:
     * <ul>
     *   <li>Standard nodes are drawn as rectangles.</li>
     *   <li>Dummy nodes are drawn as ellipses.</li>
     *   <li>Nodes have a color that depends on their node type.
     *     (yellow for {@code LONG_EDGE}, turquoise for {@code ODD_PORT_SIDE},
     *     dark blue for {@code NORTH_SOUTH_PORT})</li>
     * </ul>
     * 
     * @param writer the writer to output the graph to. An attempt is made to close the
     *               writer when finished.
     * @throws IOException if anything goes wrong with the writer.
     */
    public void writeDotGraph(final Writer writer) throws IOException {
        // Begin the digraph
        writer.write("digraph {\n");
        
        // Digraph options
        writer.write("    rankdir=LR;\n");
        
        // Write layerless nodes and edges
        writeLayer(writer, -1, layerlessNodes);
        
        // Go through the layers
        int layerNumber = -1;
        for (Layer layer : layers) {
            layerNumber++;
            
            // Write the nodes and edges
            writeLayer(writer, layerNumber, layer.getNodes());
        }
        
        // Close the digraph. And the writer.
        writer.write("}\n");
        writer.close();
    }
    
    /**
     * Writes the given list of nodes and their edges.
     * 
     * @param writer writer to write to.
     * @param layerNumber the layer number. {@code -1} for layerless nodes.
     * @param nodes the nodes in the layer.
     * @throws IOException if anything goes wrong with the writer.
     */
    private void writeLayer(final Writer writer, final int layerNumber, final List<LNode> nodes)
            throws IOException {
        
        if (nodes.isEmpty()) {
            return;
        }
        
        // Start a subgraph for the layer
        writer.write("    {\n");
        
        if (layerNumber != -1) {
            writer.write("        rank=same;\n");
        }
        
        // Go through the layer's nodes
        int nodeNumber = -1;
        for (LNode node : nodes) {
            nodeNumber++;
            
            // Output the node name
            writer.write("        " + node.hashCode());
            
            // Options time!
            StringBuffer options = new StringBuffer();
            
            // Label
            options.append("label=\"");
            if (node.getName() != null) {
                options.append(node.getName().replace("\"", "\\\"") + " ");
            }
            options.append("(" + layerNumber + "," + nodeNumber + ")\",");
            
            // Node type
            if (node.getProperty(Properties.NODE_TYPE).equals(NodeType.NORMAL)) {
                options.append("shape=box,");
            } else {
                options.append("shape=circle,style=filled,");
                
                // Add colouring
                switch (node.getProperty(Properties.NODE_TYPE)) {
                case LONG_EDGE:
                    options.append("color=\"#eaed00\",");
                    break;
                    
                case ODD_PORT_SIDE:
                    options.append("color=\"#00cbbf\",");
                    break;
                
                case NORTH_SOUTH_PORT:
                    options.append("color=\"#0034de\",");
                    break;
                }
            }
            
            // Print options, if any
            options.deleteCharAt(options.length() - 1);
            if (options.length() > 0) {
                writer.write("[" + options + "]");
            }
            
            // End the node line
            writer.write(";\n");
        }
        
        if (layerNumber != -1) {
            // End the layer's subgraph
            writer.write("    }\n");
        }
        
        // Write the edges
        for (LNode node : nodes) {
            // Go through all ports and edges and output those that have this
            // node as their source
            for (LPort port : node.getPorts()) {
                for (LEdge edge : port.getEdges()) {
                    if (edge.getSource() == port) {
                        writer.write("    " + node.hashCode() + " -> "
                                + edge.getTarget().getNode().hashCode() + ";\n");
                    }
                }
            }
        }
        
        if (layerNumber == -1) {
            // End the layer's subgraph
            writer.write("    }\n");
        }
    }
}
