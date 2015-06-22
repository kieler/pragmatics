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
package de.cau.cs.kieler.klay.layered;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.LayoutMetaDataService;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.p4nodes.LinearSegmentsNodePlacer.LinearSegment;
import de.cau.cs.kieler.klay.layered.p5edges.OrthogonalRoutingGenerator.Dependency;
import de.cau.cs.kieler.klay.layered.p5edges.OrthogonalRoutingGenerator.HyperNode;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;

/**
 * A utility class for debugging of KLay Layered.
 * 
 * @author msp
 * @author cds
 * @author csp
 */
public final class JsonDebugUtil {
    
    private static final String INDENT = "  ";
    private static final String DEBUG_ID_PREFIX = "debug.";

    private static final LayoutMetaDataService LAYOUT_DATA_SERVICE = LayoutMetaDataService.getInstance();

    
    /**
     * Hidden constructor to avoid instantiation.
     */
    private JsonDebugUtil() { }


    /**
     * Output a representation of the given graph in JSON format.
     * 
     * @param lgraph
     *            the layered graph
     * @param slotIndex
     *            the slot before whose execution the graph is written.
     * @param name
     *            the name the slot before whose execution the graph is written.
     */
    public static void writeDebugGraph(final LGraph lgraph, final int slotIndex, final String name) {
        try {
            Writer writer = createWriter(lgraph, slotIndex, name);
            
            beginGraph(writer, lgraph);
            
            writeLgraph(lgraph, writer, 1);
            
            // Close the graph and the writer.
            endGraph(writer);
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


    /**
     * Writes a debug graph for the given linear segments and their dependencies.
     * 
     * @param layeredGraph
     *            the layered graph.
     * @param segmentList
     *            the list of linear segments.
     * @param outgoingList
     *            the list of successors for each linear segment.
     */
    public static void writeDebugGraph(final LGraph layeredGraph,
            final List<LinearSegment> segmentList, final List<List<LinearSegment>> outgoingList) {

        try {
            Writer writer = createWriter(layeredGraph);
            
            beginGraph(writer, layeredGraph);
            beginChildNodeList(writer, 1);

            String indent1 = Strings.repeat(INDENT, 2);
            String indent2 = Strings.repeat(INDENT, 3); // SUPPRESS CHECKSTYLE MagicNumber
            int edgeId = 0;
            
            Iterator<LinearSegment> segmentIterator = segmentList.iterator();
            Iterator<List<LinearSegment>> successorsIterator = outgoingList.iterator();
            
            StringBuffer edges = new StringBuffer();
            
            while (segmentIterator.hasNext()) {
                LinearSegment segment = segmentIterator.next();
                writer.write("\n" + indent1 + "{\n"
                        + indent2 + "\"id\": \"n" + segment.hashCode() + "\",\n"
                        + indent2 + "\"labels\": [ { \"text\": \"" + segment + "\" } ],\n"
                        + indent2 + "\"width\": 50,\n"
                        + indent2 + "\"height\": 25\n"
                        + indent1 + "}");
                if (segmentIterator.hasNext()) {
                    writer.write(",");
                }

                Iterator<LinearSegment> succIterator = successorsIterator.next().iterator();
                
                while (succIterator.hasNext()) {
                    LinearSegment successor = succIterator.next();
                    edges.append("\n" + indent1 + "{\n"
                        + indent2 + "\"id\": \"e" + edgeId++ + "\",\n"
                        + indent2 + "\"source\": \"n" + segment.hashCode() + "\",\n"
                        + indent2 + "\"target\": \"n" + successor.hashCode() + "\",\n"
                        + indent1 + "},");
                }
            }
            
            endChildNodeList(writer, 1);
            
            if (edges.length() > 0) {
                edges.deleteCharAt(edges.length() - 1);
            }
            writer.write(INDENT + "\"edges\": [" + edges + "\n" + indent1 + "]");

            endGraph(writer);
            writer.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    /**
     * Writes a debug graph for the given list of hypernodes.
     * 
     * @param layeredGraph
     *            the layered graph
     * @param layerIndex
     *            the currently processed layer's index
     * @param hypernodes
     *            a list of hypernodes
     * @param debugPrefix
     *            prefix of debug output files
     * @param label
     *            a label to append to the output files
     */
    public static void writeDebugGraph(final LGraph layeredGraph, final int layerIndex,
            final List<HyperNode> hypernodes, final String debugPrefix, final String label) {
        
        try {
            Writer writer = createWriter(layeredGraph, layerIndex, debugPrefix, label);
            beginGraph(writer, layeredGraph);
            beginChildNodeList(writer, 1);

            String indent1 = Strings.repeat(INDENT, 2);
            String indent2 = Strings.repeat(INDENT, 3); // SUPPRESS CHECKSTYLE MagicNumber
            int edgeId = 0;
            
            Iterator<HyperNode> hypernodeIterator = hypernodes.iterator();
            
            StringBuffer edges = new StringBuffer();
            
            while (hypernodeIterator.hasNext()) {
                HyperNode hypernode = hypernodeIterator.next();
                writer.write("\n" + indent1 + "{\n"
                        + indent2 + "\"id\": \"n" + System.identityHashCode(hypernode) + "\",\n"
                        + indent2 + "\"labels\": [ { \"text\": \"" + hypernode.toString() + "\" } ],\n"
                        + indent2 + "\"width\": 50,\n"
                        + indent2 + "\"height\": 25\n"
                        + indent1 + "}");
                if (hypernodeIterator.hasNext()) {
                    writer.write(",");
                }

                Iterator<Dependency> dependencyIterator = hypernode.getOutgoing().iterator();
                
                while (dependencyIterator.hasNext()) {
                    Dependency dependency = dependencyIterator.next();
                    edges.append("\n" + indent1 + "{\n"
                        + indent2 + "\"id\": \"e" + edgeId++ + "\",\n"
                        + indent2 + "\"source\": \"n" + System.identityHashCode(hypernode) + "\",\n"
                        + indent2 + "\"target\": \"n" + System.identityHashCode(dependency.getTarget())
                        + "\"\n" + indent1 + "},");
                }
            }
            
            endChildNodeList(writer, 1);
            
            if (edges.length() > 0) {
                edges.deleteCharAt(edges.length() - 1);
            }
            writer.write(INDENT + "\"edges\": [" + edges + "\n" + INDENT + "]");

            endGraph(writer);
            writer.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    /**
     * Calculate the overall size of the given graph. Returns a {@link KVectorChain} with two
     * elements where the first one represents the minimal coordinates used by any node, edge or
     * port contained in the graph and the second one represents the maximal coordinates.
     * 
     * @param lgraph
     *            the graph to calculate the size for.
     * @return a {@link KVectorChain} with two elements, the minimal and the maximal coordinates.
     */
    private static KVectorChain calculateGraphSize(final LGraph lgraph) {
        KVector min = new KVector();
        KVector max = new KVector();
        for (LNode node : lgraph.getLayerlessNodes()) {
            calculateMinMaxPositions(min, max, node);
        }
        for (Layer layer : lgraph) {
            for (LNode node : layer) {
                calculateMinMaxPositions(min, max, node);
            }
        }
        max.x -= min.x;
        max.y -= min.y;
        return new KVectorChain(min, max);
    }


    /**
     * Inspects the given node, its outgoing edges and its ports for minimal and maximal coordinates
     * and adjusts the given vectors {@code min} and {@code max} if necessary.
     * 
     * @param min
     *            the minimal coordinates used by the graph.
     * @param max
     *            the maximal coordinates used by the graph.
     * @param node
     *            the current node to inspect.
     */
    private static void calculateMinMaxPositions(final KVector min, final KVector max,
            final LNode node) {
        min.x = Math.min(min.x, node.getPosition().x - node.getMargin().left);
        max.x = Math.max(max.x, node.getPosition().x + node.getSize().x +  node.getMargin().right);
        min.y = Math.min(min.y, node.getPosition().y - node.getMargin().top);
        max.y = Math.max(max.y, node.getPosition().y + node.getSize().y + node.getMargin().bottom);
        for (LPort port : node.getPorts()) {
            min.x = Math.min(min.x, node.getPosition().x + port.getPosition().x - port.getMargin().left);
            max.x = Math.max(max.x, node.getPosition().x + port.getPosition().x + port.getSize().x
                    + port.getMargin().right);
            min.y = Math.min(min.y, node.getPosition().y + port.getPosition().y - port.getMargin().top);
            max.y = Math.max(max.y, node.getPosition().y + port.getPosition().y + port.getSize().y
                    + port.getMargin().bottom);
        }
        for (LEdge edge : node.getOutgoingEdges()) {
            for (KVector bendpoint : edge.getBendPoints()) {
                min.x = Math.min(min.x, bendpoint.x);
                max.x = Math.max(max.x, bendpoint.x);
                min.y = Math.min(min.y, bendpoint.y);
                max.y = Math.max(max.y, bendpoint.y);
            }
        }
    }


    /**
     * Begins a new graph by writing the root node, the graphs properties and the start of the child
     * node list to the writer.
     * 
     * @param writer
     *            the writer to write to.
     * @param lgraph
     *            the graph to begin
     * @throws IOException
     *             if anything goes wrong with the writer.
     */
    private static void beginGraph(final Writer writer, final LGraph lgraph) throws IOException {
        KVectorChain graphSize = calculateGraphSize(lgraph);
        writer.write("{\n"
                + INDENT + "\"id\": \"root\",\n"
                + INDENT + "\"x\": " + graphSize.getFirst().x + ",\n"
                + INDENT + "\"y\": " + graphSize.getFirst().y + ",\n"
                + INDENT + "\"width\": " + graphSize.getLast().x + ",\n"
                + INDENT + "\"height\": " + graphSize.getLast().y + ",\n");
        writeProperties(writer, lgraph.getAllProperties(), 1);
    }
    
    /**
     * Writes the lgraph to the given writer.
     * 
     * @param lgraph
     *            the graph to begin
     * @param writer
     *            the writer to write to.
     * @param indentation
     *            the indentation level to use.
     * @throws IOException
     *             if anything goes wrong with the writer.
     */
    private static void writeLgraph(final LGraph lgraph, final Writer writer, final int indentation)
            throws IOException {
        
        KVector offset = new KVector(lgraph.getOffset())
                .add(lgraph.getInsets().left, lgraph.getInsets().top);
        
        List<LEdge> edges = Lists.newLinkedList();
        beginChildNodeList(writer, indentation);
        
        // Write layerless nodes and collect edges
        edges.addAll(writeLayer(writer, -1, lgraph.getLayerlessNodes(), indentation + 1, offset));
        
        // Go through the layers
        int layerNumber = -1;
        Iterator<Layer> layersIterator = lgraph.iterator();
        if (!lgraph.getLayerlessNodes().isEmpty() && layersIterator.hasNext()) {
            writer.write(",");
        }
        while (layersIterator.hasNext()) {
            Layer layer = layersIterator.next();
            layerNumber++;
            
            // Write the nodes and collect edges
            edges.addAll(writeLayer(writer, layerNumber, layer.getNodes(), indentation + 1, offset));
            
            if (layersIterator.hasNext()) {
                writer.write(",");
            }
        }
        
        endChildNodeList(writer, indentation);
        
        writeEdges(writer, edges, indentation, offset);
    }


    /**
     * Begins a new list of child nodes with the given indentation.
     * 
     * @param writer
     *            writer to write to.
     * @param indentation
     *            the indentation level to use.
     * @throws IOException
     *             if anything goes wrong with the writer.
     */
    private static void beginChildNodeList(final Writer writer, final int indentation)
            throws IOException {
        
        writer.write(",\n" + Strings.repeat(INDENT, indentation) + "\"children\": [");
    }


    /**
     * Ends the list of child nodes.
     * 
     * @param writer
     *            writer to write to.
     * @param indentation
     *            the indentation level to use.
     * @throws IOException
     *             if anything goes wrong with the writer.
     */
    private static void endChildNodeList(final Writer writer, final int indentation)
            throws IOException {
        
        writer.write("\n" + Strings.repeat(INDENT, indentation) + "],\n");
    }


    /**
     * Ends the graph.
     * 
     * @param writer
     *            the writer to write to.
     * @throws IOException
     *             if anything goes wrong with the writer.
     */
    private static void endGraph(final Writer writer) throws IOException {
        writer.write("\n}\n");
    }


    /**
     * Writes the given list of nodes and collects their edges.
     * 
     * @param writer
     *            writer to write to.
     * @param layerNumber
     *            the layer number. {@code -1} for layerless nodes.
     * @param nodes
     *            the nodes in the layer.
     * @param indentation
     *            the indentation level to use.
     * @param offset
     *            the combined offset of the containing LGraph.
     * @return list of edges that need to be added to the graph.
     * @throws IOException
     *             if anything goes wrong with the writer.
     */
    private static List<LEdge> writeLayer(final Writer writer, final int layerNumber,
            final List<LNode> nodes, final int indentation, final KVector offset)
            throws IOException {
        
        if (nodes.isEmpty()) {
            return Lists.newLinkedList();
        }
        
        writeNodes(writer, nodes, indentation, layerNumber, offset);
        
        List<LEdge> edges = Lists.newLinkedList();
        
        // Collect the edges
        for (LNode node : nodes) {
            // Go through all edges and collect those that have this node as their source
            for (LPort port : node.getPorts()) {
                edges.addAll(port.getOutgoingEdges());
            }
        }
        
        return edges;
    }


    /**
     * Writes the nodes edges as JSON formatted string to the given writer.
     * 
     * @param writer
     *            the writer to write to.
     * @param nodes
     *            the nodes to write.
     * @param indentation
     *            the indentation level to use.
     * @param layerNumber
     *            the layer number. {@code -1} for layerless nodes.
     * @param offset
     *            the combined offset of the containing LGraph.
     * @throws IOException
     *             if anything goes wrong with the writer.
     */
    private static void writeNodes(final Writer writer, final List<LNode> nodes, final int indentation,
            final int layerNumber, final KVector offset) throws IOException {
        
        String indent0 = Strings.repeat(INDENT, indentation);
        String indent1 = Strings.repeat(INDENT, indentation + 1);
        int nodeNumber = -1;
        Iterator<LNode> nodesIterator = nodes.iterator();
        while (nodesIterator.hasNext()) {
            nodeNumber++;
            LNode node = nodesIterator.next();
            final KVector position = new KVector(node.getPosition()).add(offset);
            writer.write("\n" + indent0 + "{\n"
                    + indent1 + "\"id\": \"n" + node.hashCode() + "\",\n"
                    + indent1 + "\"labels\": [ { \"text\": \""
                        + getNodeName(node, layerNumber, nodeNumber) + "\" } ],\n"
                    + indent1 + "\"width\": " + node.getSize().x + ",\n"
                    + indent1 + "\"height\": " + node.getSize().y + ",\n"
                    + indent1 + "\"x\": " + position.x + ",\n"
                    + indent1 + "\"y\": " + position.y + ",\n");
            writeProperties(writer, node.getAllProperties(), indentation + 1);
            writer.write(",\n");
            writePorts(writer, node.getPorts(), indentation + 1);
            final LGraph nestedGraph = node.getProperty(InternalProperties.NESTED_LGRAPH);
            if (nestedGraph != null) {
                writeLgraph(nestedGraph, writer, indentation + 1);
            }
            writer.write("\n" + indent0 + "}");
            if (nodesIterator.hasNext()) {
                writer.write(",");
            }
        }
    }


    /**
     * Writes the given edges as JSON formatted string to the given writer.
     * 
     * @param writer
     *            the writer to write to.
     * @param edges
     *            the edges to write.
     * @param indentation
     *            the indentation level to use.
     * @param offset
     *            the combined offset of the containing LGraph.
     * @throws IOException
     *             if anything goes wrong with the writer.
     */
    private static void writeEdges(final Writer writer, final List<LEdge> edges, final int indentation,
            final KVector offset) throws IOException {
    
        String indent0 = Strings.repeat(INDENT, indentation);
        String indent1 = Strings.repeat(INDENT, indentation + 1);
        String indent2 = Strings.repeat(INDENT, indentation + 2);
        writer.write(indent0 + "\"edges\": [");
        Iterator<LEdge> edgesIterator = edges.iterator();
        while (edgesIterator.hasNext()) {
            LEdge edge = edgesIterator.next();
            final KVector source = new KVector(edge.getSource().getAbsoluteAnchor()).add(offset);
            final KVector target = new KVector(edge.getTarget().getAbsoluteAnchor()).add(offset);
            writer.write("\n" + indent1 + "{\n"
                + indent2 + "\"id\": \"e" + edge.hashCode() + "\",\n"
                + indent2 + "\"source\": \"n" + edge.getSource().getNode().hashCode() + "\",\n"
                + indent2 + "\"target\": \"n" + edge.getTarget().getNode().hashCode() + "\",\n"
                + indent2 + "\"sourcePort\": \"p" + edge.getSource().hashCode() + "\",\n"
                + indent2 + "\"targetPort\": \"p" + edge.getTarget().hashCode() + "\",\n"
                + indent2 + "\"sourcePoint\": { \"x\": " + source.x + ", \"y\": " + source.y + " },\n"
                + indent2 + "\"targetPoint\": { \"x\": " + target.x + ", \"y\": " + target.y + " },\n");
            writeBendPoints(writer, edge.getBendPoints(), indentation + 2, offset);
            writer.write(",\n");
            writeProperties(writer, edge.getAllProperties(), indentation + 2);
            writer.write("\n" + indent1 + "}");
            if (edgesIterator.hasNext()) {
                writer.write(",");
            }
        }
        writer.write("\n" + indent0 + "]");
    }

    /**
     * Writes the given bendpoints as JSON formatted string to the given writer.
     * 
     * @param writer
     *            the writer to write to.
     * @param bendPoints
     *            the bendpoints to write.
     * @param indentation
     *            the indentation level to use.
     * @param offset
     *            the combined offset of the containing LGraph.
     * @throws IOException
     *             if anything goes wrong with the writer.
     */
    private static void writeBendPoints(final Writer writer, final KVectorChain bendPoints,
            final int indentation, final KVector offset) throws IOException {
        
        String indent0 = Strings.repeat(INDENT, indentation);
        String indent1 = Strings.repeat(INDENT, indentation + 1);
        writer.write(indent0 + "\"bendPoints\": [");
        Iterator<KVector> pointsIterator = bendPoints.iterator();
        while (pointsIterator.hasNext()) {
            KVector point = pointsIterator.next();
            point.add(offset);
            writer.write("\n" + indent1 + "{ \"x\": " + point.x + ", \"y\": " + point.y + "}");
            if (pointsIterator.hasNext()) {
                writer.write(",");
            }
        }
        writer.write("\n" + indent0 + "]");
    }

    /**
     * Writes the given ports as JSON formatted string to the given writer.
     * 
     * @param writer
     *            the writer to write to.
     * @param ports
     *            the ports to write.
     * @param indentation
     *            the indentation level to use.
     * @throws IOException
     *             if anything goes wrong with the writer.
     */
    private static void writePorts(final Writer writer, final List<LPort> ports,
            final int indentation) throws IOException {
        
            String indent0 = Strings.repeat(INDENT, indentation);
            String indent1 = Strings.repeat(INDENT, indentation + 1);
            String indent2 = Strings.repeat(INDENT, indentation + 2);
            writer.write(indent0 + "\"ports\": [");
            Iterator<LPort> portsIterator = ports.iterator();
            while (portsIterator.hasNext()) {
                LPort port = portsIterator.next();
                writer.write("\n" + indent1 + "{\n"
                        + indent2 + "\"id\": \"p" + port.hashCode() + "\",\n"
                        + indent2 + "\"width\": " + port.getSize().x + ",\n"
                        + indent2 + "\"height\": " + port.getSize().y + ",\n"
                        + indent2 + "\"x\": " + port.getPosition().x + ",\n"
                        + indent2 + "\"y\": " + port.getPosition().y + ",\n");
                writeProperties(writer, port.getAllProperties(), indentation + 2);
                writer.write("\n" + indent1 + "}");
                if (portsIterator.hasNext()) {
                    writer.write(",");
                }
            }
            writer.write("\n" + indent0 + "]");
        }


    /**
     * Writes the given properties as JSON formatted string to the given writer.
     * 
     * @param writer
     *            the writer to write to.
     * @param properties
     *            the properties to write.
     * @param indentation
     *            the indentation level to use.
     * @throws IOException
     *             if anything goes wrong with the writer.
     */
    private static void writeProperties(final Writer writer,
            final Map<IProperty<?>, Object> properties, final int indentation) throws IOException {
        
        String indent0 = Strings.repeat(INDENT, indentation);
        String indent1 = Strings.repeat(INDENT, indentation + 1);
        writer.write(indent0 + "\"properties\": {");
        Iterator<Entry<IProperty<?>, Object>> propertiesIterator =
                properties.entrySet().iterator();
        while (propertiesIterator.hasNext()) {
            Entry<IProperty<?>, Object> property = propertiesIterator.next();
            String id = property.getKey().getId();
            // Test whether the given id is a registered layout option. If not, prefix the id with
            // DEBUG_ID_PREFIX
            if (LAYOUT_DATA_SERVICE.getOptionData(id) == null) {
                id = DEBUG_ID_PREFIX + id;
            }
            final String val = getValueRepresentation(property.getKey(), property.getValue());
            writer.write("\n" + indent1 + "\"" + id + "\": \"" + val.replace("\"", "\\\"") + "\"");
            if (propertiesIterator.hasNext()) {
                writer.write(",");
            }
        }
        writer.write("\n" + indent0 + "}");
    }

    /**
     * Returns the value of the given property formatted for debug output. For example for layout
     * processors, only the class' simple name instead of the qualified name is returned.
     * 
     * @param property
     *            the property whose value to get.
     * @param value
     *            the value to format.
     * @return the formatted value.
     */
    @SuppressWarnings("unchecked")
    private static String getValueRepresentation(final IProperty<?> property, final Object value) {
        if (property.getId().equals(InternalProperties.PROCESSORS.getId())) {
            Iterator<ILayoutProcessor> processors = ((List<ILayoutProcessor>) value).iterator();
            StringBuffer result = new StringBuffer("[");
            while (processors.hasNext()) {
                ILayoutProcessor processor = processors.next();
                result.append(processor.getClass().getSimpleName());
                if (processors.hasNext()) {
                    result.append(", ");
                }
            }
            result.append("]");
            return result.toString();
        }
        return value.toString();
    }

    /**
     * Returns the name of the node. The layer and index in the layer is added in parentheses. If
     * its a dummy node, it get a suffix {@code DUMMY: <NodeType>}.
     * 
     * @param node
     *            the node whose name to get.
     * @param layer
     *            the layer of the node.
     * @param index
     *            the index inside the layer.
     * @return the nodes name.
     */
    private static String getNodeName(final LNode node, final int layer, final int index) {
        String name = "";
        if (node.getNodeType() == NodeType.NORMAL) {
            // Normal nodes display their name, if any
            if (node.getName() != null) {
                name = node.getName().replace("\"", "\\\"");
            }
            name += " (" + layer + "," + index + ")";
        } else {
            // Dummy nodes show their name (if set), or their node ID
            if (node.getName() != null) {
                name = node.getName().replace("\"", "\\\"");
            } else {
                name = "n_" + node.id;
            }
            if (node.getNodeType() == NodeType.NORTH_SOUTH_PORT) {
                Object origin = node.getProperty(InternalProperties.ORIGIN);
                if (origin instanceof LNode) {
                    name += "(" + ((LNode) origin).toString() + ")";
                }
            }
            name += " (" + layer + "," + index + ")";
            name += "\\n DUMMY: " + node.getNodeType().name();
        }
        return name;
    }

    /**
     * Creates a writer for debug output.
     * 
     * @param layeredGraph
     *            the layered graph.
     * @return a file writer for debug output.
     * @throws IOException
     *             if creating the output file fails.
     */
    private static Writer createWriter(final LGraph layeredGraph) throws IOException {
        String path = getDebugOutputPath();
        new File(path).mkdirs();

        String debugFileName = getDebugOutputFileBaseName(layeredGraph) + "linseg-dep";
        return new FileWriter(new File(path + File.separator + debugFileName + ".dot"));
    }
    
    /**
     * Creates a writer for the given graph. The file name to be written to is assembled from the
     * graph's hash code and the slot index.
     * 
     * @param graph
     *            the graph to be written.
     * @param slotIndex
     *            the slot before whose execution the graph is written.
     * @param name
     *            the name the slot before whose execution the graph is written.
     * @return file writer.
     * @throws IOException
     *             if anything goes wrong.
     */
    private static Writer createWriter(final LGraph graph, final int slotIndex, final String name)
            throws IOException {

        String path = getDebugOutputPath();
        new File(path).mkdirs();

        String debugFileName = getDebugOutputFileBaseName(graph) + "fulldebug-slot"
                        + String.format("%1$02d", slotIndex) + "-" + name;
        return new FileWriter(new File(path + File.separator + debugFileName + ".json"));
    }
    
    /**
     * Create a writer for debug output.
     * 
     * @param layeredGraph
     *            the layered graph
     * @param layerIndex
     *            the currently processed layer's index
     * @param debugPrefix
     *            prefix of debug output files
     * @param label
     *            a label to append to the output files
     * @return a file writer for debug output
     * @throws IOException
     *             if creating the output file fails
     */
    private static Writer createWriter(final LGraph layeredGraph, final int layerIndex,
            final String debugPrefix, final String label) throws IOException {
        String path = getDebugOutputPath();
        new File(path).mkdirs();
        
        String debugFileName = getDebugOutputFileBaseName(layeredGraph)
                + debugPrefix + "-l" + layerIndex + "-" + label;
        return new FileWriter(new File(path + File.separator + debugFileName + ".json"));
    }

    /**
     * Returns the path for debug output graphs.
     * 
     * @return the path for debug output graphs, without trailing separator.
     */
    private static String getDebugOutputPath() {
        String path = System.getProperty("user.home");
        if (path.endsWith(File.separator)) {
            path += "tmp" + File.separator + "klay";
        } else {
            path += File.separator + "tmp" + File.separator + "klay";
        }

        return path;
    }

    /**
     * Returns the beginning of the file name used for debug output graphs while layouting the given
     * layered graph. This will look something like {@code "143293-"}.
     * 
     * @param graph
     *            the graph to return the base debug file name for.
     * @return the base debug file name for the given graph.
     */
    private static String getDebugOutputFileBaseName(final LGraph graph) {
        return Integer.toString(graph.hashCode()
                & ((1 << (Integer.SIZE / 2)) - 1))
                + "-";
    }

}
