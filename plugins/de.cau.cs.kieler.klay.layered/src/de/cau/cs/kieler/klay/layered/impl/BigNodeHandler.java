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

import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.DummyEditPart;
import org.w3c.dom.Node;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
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
     * Note that these attributes do not consider any layers that have to be added after the
     * fixating edges have been inserted.(Determined layering might be wider (more layers) due to
     * adding fixing edges).
     */
    private int[] layer;

    /**
     * The length of the longest path in the graph from a source to sink node, which is defined as
     * the number of nodes in the path minus one.
     */
    private int longestPath;

    private LinkedHashMap<LNode, Integer> nodeIDs;

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
     * 
     */
    public void correctLayering() {

        // restore previous node indices
        Integer id = null;
        if (nodes.size() != nodeIDs.size()) {
            throw new RuntimeException(
                    "The set of nodes have changed illegally since the invokation of 'splitWideNodes()'.");
        }
        for (LNode node : nodes) {
            id = nodeIDs.get(node);
            if (id == null) {
                throw new RuntimeException(
                        "The set of nodes have changed illegally since the invokation of 'splitWideNodes()'.");
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
        System.out.println("------ Bucket sizes ------");
        for (int i = 0; i < buckets.size(); i++)
            System.out.println(i + ": " + buckets.get(i).size());
        // correct layering by shifting violating nodes to the right
        int curIndex = 0, targetIndex = -1, restrIndex = -1;
        LinkedList<LNode> curBucket, targetBucket;
        LNode curNode = null;
        while (curIndex < buckets.size()) {
            curBucket = buckets.get(curIndex);
            // check all wide nodes for layering violations
            if (!curBucket.isEmpty()) {
                // update restriction index
                if (restrIndex < curIndex) {
                    restrIndex = curIndex + width[curBucket.getFirst().id] - 1;
                    System.out.println("curIndex: " + curIndex + ", restriction index: "
                            + restrIndex);
                }
                Iterator<LNode> iterator = curBucket.iterator();
                while (iterator.hasNext()) {
                    curNode = iterator.next();
                    targetIndex = -1;
                    if (curIndex + width[curNode.id] - 1 > restrIndex) {
                        // node violates the layering, put it into a fitting layer
                        targetIndex = restrIndex + 1;
                    } else if (layer[curNode.id] > curIndex) {
                        // node is placed into wrong bucket
                        targetIndex = layer[curNode.id];
                    }
                    System.out.println("curNode " + curNode.toString() + ", target index: "
                            + targetIndex);
                    if (targetIndex != -1) {
                        // put node into a new bucket
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
     * Helper method for the Average Position Big-Node-Handler. It splits all wide nodes in the
     * graph into smaller, equal sized nodes by insertion of dummy nodes. All incoming edges
     * incident to the original wide node will remain on the node, but outgoing back edges will be
     * moved to the rightmost inserted dummy node. A unique and consistent ID will be assigned to
     * each created dummy node. The wide node ID {@code wideNodeID} of each dummy node will be equal
     * to the ID of the wide node, they originate from. Note that after a wide node has been split,
     * its width in layers stored in {@code width} will not be updated.
     * 
     * @see de.cau.cs.kieler.klay.layered.impl.BigNodeHandler#wideNodes wideNodes
     * @see de.cau.cs.kieler.klay.layered.impl.BigNodeHandler#width width
     * 
     * 
     *      TODO
     */
    public void splitWideNodes(final Collection<LNode> theNodes, final LayeredGraph theLayeredGraph) {

        if (theNodes == null) {
            throw new NullPointerException("Input collection of nodes is null.");
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
        for (LNode node : nodes) {
            threshold = (2 * minWidth) + minSpacing;
            while (threshold <= node.getSize().x) {
                width[node.id]++;
                threshold += minWidth + minSpacing;
            }

            // all nodes in the segment get the same width (temporarily)
            double newWidth = (node.getSize().x - ((width[node.id] - 1) * minSpacing))
                    / (width[node.id]);
            node.getSize().x = newWidth;
            // TODO warum muss ne neue Breite zugewiesen werden?

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
        // save current node IDs to restore them, when the layerer finished execution
        nodeIDs = new LinkedHashMap<LNode, Integer>(nodes.size());
        for (LNode node : nodes) {
            nodeIDs.put(node, new Integer(node.id));
        }

        System.out.println("Width-Array: " + Arrays.toString(width));

        for (LNode nodea : this.nodes) {
            System.out.print(nodea.id);
        }
        System.out.println();
    }

    /**
     * Helper method for the Average Position Big-Node-Handler.
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

    private void convertLayering() {

        for (LNode nodea : this.nodes) {
            System.out.print(nodea.id);
        }
        System.out.println();

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
     * Helper method for the LpSolve-layerer. It puts the specified node into its assigned layer in
     * the layered graph.
     * 
     * @param node
     *            the node to put into the layered graph
     */
    private void putNodes() {

        List<Layer> layers = layeredGraph.getLayers();
        // add additional layers to match required amount
        int ID = 0;
        while (longestPath-- >= 0) {
            Layer l = new Layer(layeredGraph);
            layers.add(l);
            l.id = ID++;
        }
        // put nodes into their assigned layers
        for (LNode node : nodes) {
            node.setLayer(layers.get(layer[node.id]));
        }
        // System.out.println("layer assignment:");
        for (LNode node : nodes) {
            // System.out.println(node.toString() + ", " + node.getLayer().id);
        }

        for (LNode nodea : this.nodes) {
            System.out.print(nodea.id);
        }
        System.out.println();
    }

}
