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
package de.cau.cs.kieler.klay.layered.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.w3c.dom.Node;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.IBigNodeHandler;

/**
 * The main class of the big node handler component.
 * 
 * @author pdo
 */
public class BigNodeHandler extends AbstractAlgorithm implements IBigNodeHandler {

    // ================================== Attributes ==============================================

    /**
     * The layered graph, all methods in this class operate on.
     * 
     * @see de.cau.cs.kieler.klay.layered.graph.LayeredGraph LayeredGraph
     */
    private LayeredGraph layeredGraph;

    /**
     * A {@code Collection} containing all nodes in the graph to layer.
     * 
     * @see de.cau.cs.kieler.klay.layered.graph.LNode LNode
     */
    private Collection<LNode> nodes;

    /**
     * The width of each node in layers before wide nodes have been split. Note that all wide nodes
     * in the graph can be easily identified by this attribute, since their {@code width} is always
     * greater than {@code 1}, whereas the {@code width} of all other nodes remains {@code 1}.
     */
    private int[] width;

    /**
     * The index of the layer, each node is currently assigned to. The lesser the index, the more to
     * the left will a node be located in the final drawing of the graph.
     */
    private int[] layer;

    /**
     * The length of the longest path in the graph from a source to sink node, which is defined as
     * the number of nodes in the path minus one.
     */
    private int longestPath;

    /**
     * A map, in which for every node in the graph its ID will be saved. Since the layerer invoked
     * right after
     */
    private LinkedHashMap<LNode, Integer> nodeIDs;
    
    private static final double GRANULARITY = 3;

    // ================================== Constructor =============================================

    /**
     * Default constructor for {@link BigNodeHandler}. It creates a new instance of this class.
     * 
     * @see de.cau.cs.kieler.klay.layered.modules.IBigNodeHandler IBigNodeSplitter
     */
    public BigNodeHandler() {
    }

    // ============================== Big-Node-Handling Algorithm ================================

    /**
     * Main method for the Big-Node-Handler. It splits all wide nodes in the graph into smaller,
     * equal sized nodes by insertion of dummy nodes. All incoming edges incident to the original
     * wide node will remain on the node, but outgoing back edges will be moved to the rightmost
     * inserted dummy node. Note that after a wide node has been split, its width in layers stored
     * in {@code width} will not be updated, i.e. it keeps its value of 1 plus the number of
     * inserted dummy nodes to split the specific wide node.
     * 
     * @param theNodes
     *            a {@code Collection} containing all nodes of the graph
     * @param theLayeredGraph
     *            the (empty) layered graph to put all nodes into
     * 
     * @see de.cau.cs.kieler.klay.layered.impl.BigNodeHandler#width width
     * 
     * 
     *      TODO
     */
    public void splitWideNodes(final Collection<LNode> theNodes, final LayeredGraph theLayeredGraph) {

        if (theNodes == null) {
            throw new NullPointerException("Input set of nodes is null.");
        }
        if (theLayeredGraph == null) {
            throw new NullPointerException("Input graph is null.");
        }

        // initialize attributes
        nodes = theNodes;
        layeredGraph = theLayeredGraph;

        // re-index nodes
        int counter = 0;
        for (LNode node : theNodes) {
            node.id = counter++;
        }
        // the list of dummy nodes to be inserted into the graph
        LinkedList<LNode> dummyNodes = new LinkedList<LNode>();
        // the object spacing in the drawn graph
        double minSpacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        // the ID for the most recently created dummy node
        int dummyID = nodes.size();
        // the list of ports to reassign to the wide node's rightmost dummy node
        LinkedList<LPort> ports = null;

        // Compute width of narrowest node
        double minWidth = Float.MAX_VALUE;
        for (LNode node : nodes) {
            if (node.getSize().x < minWidth) {
                minWidth = node.getSize().x;
            }
        }
        // initialize width array
        if (width == null || width.length < nodes.size()) {
            width = new int[nodes.size()];
        }
        Arrays.fill(width, 1);
        // determine width in layers of each node
        double threshold;
        double graMinWidth = minWidth / GRANULARITY;
        double graMinSpacing = minSpacing / GRANULARITY;
        for (LNode node : nodes) {
            threshold = ((2 * graMinWidth) + graMinSpacing);
            while (threshold <= node.getSize().x) {
                width[node.id]++;
                threshold += (graMinWidth + graMinSpacing);
            }

            // all nodes in the segment get the same width (temporarily)
            double newWidth = (node.getSize().x - ((width[node.id] - 1) * minSpacing))
                    / (width[node.id]);
            node.getSize().x = newWidth;
            if (width[node.id] > 1) {
                // save outgoing ports of wide node to reassign them later
                ports = new LinkedList<LPort>();
                for (LPort port : node.getPorts(PortType.OUTPUT)) {
                    ports.add(port);
                }
                // expand node by one dummy node per iteration
                for (int d = 1; d < width[node.id]; d++) {
                    // create new dummy node
                    LNode dummy = new LNode();
                    dummy.id = dummyID++;
                    // set size
                    dummy.getSize().y = node.getSize().y;
                    dummy.getSize().x = newWidth;
                    // add ports to connect it with the previous node
                    LPort outPort = new LPort(PortType.OUTPUT);
                    LPort inPort = new LPort(PortType.INPUT);
                    outPort.setNode((d == 1) ? node : dummyNodes.getLast());
                    inPort.setNode(dummy);
                    // add edge to connect it with the previous node
                    LEdge edge = new LEdge();
                    edge.setSource(outPort);
                    edge.setTarget(inPort);
                    // add new dummy node to list of all dummy nodes
                    dummyNodes.add(dummy);
                }
                // reassign saved outgoing ports to rightmost dummy node
                for (LPort port : ports) {
                    port.setNode(dummyNodes.getLast());
                }
            }
        }
        // add all dummy nodes to the nodes of the graph
        nodes.addAll(dummyNodes);
        // save current node IDs to restore them in 'correctLayering()'
        nodeIDs = new LinkedHashMap<LNode, Integer>(nodes.size());
        for (LNode node : nodes) {
            nodeIDs.put(node, new Integer(node.id));
        }
    }

    /**
     * Main method of the Big-Node-Splitter. It corrects the layering after layerer execution
     * concerning a correct layer assignment of wide nodes. Two wide nodes (i.e. nodes with a
     * {@code width} > 1) violate a layering, if they are not disjunct regarding the layers, they
     * are assigned to, and the narrower of the two nodes is not assigned to all layers, the wider
     * nodes is also placed in. 
     */
    public void correctLayering() {

        if (nodeIDs == null) {
            throw new RuntimeException("Illegal invokation of correctLayering(). "
                    + "splitWideNodes() has not been invoked yet.");
        }
        // restore previous node indices
        Integer id = null;
        if (nodes.size() != nodeIDs.size()) {
            throw new RuntimeException("correctLayering(): The set of nodes has changed illegally "
                    + "since the invokation of splitWideNodes().");
        }
        for (LNode node : nodes) {
            id = nodeIDs.get(node);
            if (id == null) {
                throw new RuntimeException(
                        "correctLayering(): The set of nodes has changed illegally "
                                + "since the invokation of splitWideNodes().");
            }
            node.id = id;
        }
        // get current layer assignment
        convertLayering();
        // create buckets for wide nodes
        ArrayList<LinkedList<LNode>> buckets = new ArrayList<LinkedList<LNode>>(longestPath << 1);
        for (int i = 0; i < (longestPath << 1); i++) {
            buckets.add(new LinkedList<LNode>());
        }
        // put all wide nodes into their bucket
        LinkedList<LNode> bucket = null;
        for (LNode node : nodes) {
            if (node.id < width.length && width[node.id] > 1) {
                // node is a wide node
                bucket = buckets.get(layer[node.id]);
                // put widest node at the top
                if (bucket.isEmpty() || width[bucket.getFirst().id] < width[node.id]) {
                    bucket.addFirst(node);
                } else {
                    bucket.addLast(node);
                }
            }
        }
        // correct layering by shifting violating nodes to the right
        int curIndex = 0, targetIndex = -1, restrIndex = -1;
        LinkedList<LNode> curBucket, targetBucket;
        LNode curNode = null;
        Iterator<LNode> iterator = null;
        while (curIndex < buckets.size()) {
            curBucket = buckets.get(curIndex);
            // check all wide nodes for layering violations
            if (!curBucket.isEmpty()) {
                // update restriction index
                if (restrIndex < curIndex) {
                    restrIndex = curIndex + width[curBucket.getFirst().id] - 1;
                }
                iterator = curBucket.iterator();
                while (iterator.hasNext()) {
                    curNode = iterator.next();
                    targetIndex = -1;
                    if (curIndex + width[curNode.id] - 1 > restrIndex) {
                        // node violates the layering, put it into a fitting layer
                        targetIndex = restrIndex + 1;
                    } else if (layer[curNode.id] > curIndex) {
                        // node is placed in wrong bucket
                        targetIndex = layer[curNode.id];
                    }
                    if (targetIndex != -1) {
                        // add more buckets, if necessary
                        while (targetIndex >= buckets.size()) {
                            buckets.add(new LinkedList<LNode>());
                        }
                        // put node into the new bucket
                        targetBucket = buckets.get(targetIndex);
                        if (targetBucket.isEmpty()
                                || width[targetBucket.getFirst().id] < width[curNode.id]) {
                            targetBucket.addFirst(curNode);
                        } else {
                            targetBucket.addLast(curNode);
                        }
                        // remove node from current bucket
                        iterator.remove();
                        // update leftmost assignable layer of each connected node
                        minimalLayer(curNode, targetIndex);
                    }
                }
            }
            curIndex++;
        }
        // put all nodes into their new layers
        putNodes();
    }

    /**
     * Helper method for the Big-Node-Handler.
     * 
     * @param node
     *            the root of the DFS-subtree
     * @param start
     *            TODO
     * 
     * @see de.cau.cs.kieler.klay.layered.impl.BigNodeHandler.Direction Direction
     */
    private void minimalLayer(final LNode node, final int start) {

        // traverse edges from source to target
        layer[node.id] = Math.max(layer[node.id], start);
        for (LPort port : node.getPorts(PortType.OUTPUT)) {
            for (LEdge edge : port.getEdges()) {
                minimalLayer(edge.getTarget().getNode(), start + 1);
            }
        }
        // update longest path
        longestPath = Math.max(longestPath, layer[node.id]);
    }

    /**
     * Helper method for the Big-Node-Handler. It re-indexes all layers contained in
     * {@code layeredGraph}, determines the current longest path in the graph and retrieves the
     * index of the layer, each node of the graph is assigned to and stores this value at the
     * position equal to the node's {@code id} in {@code layer}. If this array does not exist or its
     * size to small to store the value of each node, a (new) instance of this attribute will be
     * created. Otherwise, the old instance will be reused. After this step is done, all layers will
     * be removed from {@code layeredGraph}.
     * 
     * TODO
     */
    private void convertLayering() {

        // initialize layer attribute
        if (layer == null || layer.length < nodes.size()) {
            layer = new int[nodes.size()];
        }
        // re-index layers
        int counter = 0;
        List<Layer> layers = layeredGraph.getLayers();
        for (Layer theLayer : layers) {
            theLayer.id = counter++;
        }
        // get layer assignment of each node
        for (LNode node : nodes) {
            layer[node.id] = node.getLayer().id;
        }
        // save length of longest path
        longestPath = layers.size() - 1;
        // clear layers
        layers.clear();
    }

    /**
     * Helper method for the Big-Node-Handler. It puts all nodes of the graph into their assigned
     * layers in the layered graph ({@code layeredGraph}) as stated in {@code layer}. Since at this
     * stage, the graph does not contain any layers, not even empty ones, all necessary layers will
     * be added to it by this method. After execution, each layer is assigned at least one
     * node.
     * 
     * @param node
     *            the node to put into the layered graph
     */
    private void putNodes() {

        List<Layer> layers = layeredGraph.getLayers();
        // add additional layers to match required amount
        while (longestPath-- >= 0) {
            layers.add(new Layer(layeredGraph));
        }
        // put nodes into their assigned layers
        for (LNode node : nodes) {
            node.setLayer(layers.get(layer[node.id]));
        }

        System.out.println("The number of nodes in each layer from left to right: ");
        for (Layer l : layers) {
            System.out.print(l.getNodes().size() + ",");
        }
        System.out.print("\n");
    }

}
