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
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.p1cycles.GreedyCycleBreaker;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Postprocesses the node ordering phase to ensure that subgraphs are not intertwined across the
 * layers. The approach is inspired by Georg Sander, "Layout of Compound Graphs", Technical Report
 * A/03/96, Universität des Saarlandes, 1996.
 * 
 * <dl>
 * <dt>Precondition:</dt>
 * <dd>A layered graph. The node ordering has taken place. The nodes on a layer that belong to the
 * same compound node are placed in an unbroken sequence in the layer.</dd>
 * <dt>Postcondition:</dt>
 * <dd>The nodes are ordered such that the subgraphs have the same relative position on all layers.
 * The nodes of one subgraph on one layer are still placed next to each other without other nodes
 * between them.</dd>
 * <dt>Slots:</dt>
 * <dd>After phase 3.</dd>
 * <dt>Same-slot dependencies:</dt>
 * <dd>none.</dd>
 * </dl>
 * 
 * @author ima
 */
public class SubgraphOrderingProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    // Document the layers, that are resorted.
    private HashMap<Layer, HashMap<LNode, LinkedList<LNode>>> reorderedLayers;

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin(
                "Order subgraphs so that the relative position is the same on all layers", 1);

        reorderedLayers = new HashMap<Layer, HashMap<LNode, LinkedList<LNode>>>();

        // A subgraph ordering graph is used to find the correct subgraph ordering. The subgraph
        // ordering graph is disconnected - it consists of connected components for each compound
        // node. Represent it as a HashMap of layered Graphs. The compound nodes serve as keys.
        HashMap<LNode, LayeredGraph> subgraphOrderingGraph = new HashMap<LNode, LayeredGraph>();

        // Document the insertion of nodes for reference.
        HashMap<LNode, LNode> insertedNodes = new HashMap<LNode, LNode>();

        // Get the layeredGraph's element map.
        HashMap<KGraphElement, LGraphElement> elemMap = layeredGraph
                .getProperty(Properties.ELEMENT_MAP);

        // Make up an LNode that is to represent the layeredGraph as a key in the
        // subgraphOrderingGraph
        LNode graphKey = new LNode();
        graphKey.copyProperties(layeredGraph);

        // Build the subgraphOrderingGraph:
        // Insert nodes and edges representing the relationship "is left of" into the subgraph
        // ordering graph parts.
        for (Layer layer : layeredGraph.getLayers()) {

            // Keep a list of associated Nodes for every compound node relevant for this layer for
            // later order application.
            HashMap<LNode, LinkedList<LNode>> layerCompoundContents 
                    = new HashMap<LNode, LinkedList<LNode>>();

            List<LNode> layerNodes = layer.getNodes();
            boolean reordered = false;
            for (int i = 0; i < (layerNodes.size() - 1); i++) {

                LNode currentNode = layerNodes.get(i);
                LNode relatedCompoundCurrent = getRelatedCompoundNode(currentNode, layeredGraph);
                // Store the currentNode in layerCompoundContents under the key of
                // relatedCompoundCurrent.
                insertRelatedCompound(layerCompoundContents, currentNode, relatedCompoundCurrent,
                        graphKey);

                LNode nextNode = layerNodes.get(i + 1);
                // Store the currentNode in layerCompoundContents under the key of
                // relatedCompoundCurrent.
                LNode relatedCompoundNext = getRelatedCompoundNode(nextNode, layeredGraph);
                insertRelatedCompound(layerCompoundContents, nextNode, relatedCompoundNext,
                        graphKey);

                // The subgraphOrderingGraph has to be updated, if nodes that are neighbors in a
                // layer are of different compound nodes and no leave nodes of highest level.
                if ((relatedCompoundCurrent != null) && (relatedCompoundNext != null)
                        && (relatedCompoundCurrent != relatedCompoundNext)) {
                    reordered = true;
                    // Find the correct partial graph to insert the "is left of"-relationship by
                    // propagating the dependency up the inclusion tree till two compound nodes with
                    // the same parent are reached.
                    LinkedList<LNode> leftRightList = new LinkedList<LNode>();
                    leftRightList.add(currentNode);
                    leftRightList.add(nextNode);
                    propagatePair(leftRightList, elemMap);
                    LNode propCompoundCurrent = leftRightList.getFirst();
                    LNode propCompoundNext = leftRightList.getLast();
                    // Do not insert self-loops into the subgraph-ordering-graph.
                    if (propCompoundCurrent != propCompoundNext) {
                        LNode key;
                        LGraphElement parentRep = elemMap.get(propCompoundCurrent
                                .getProperty(Properties.PARENT));
                        if (parentRep instanceof LayeredGraph) {
                            key = graphKey;
                        } else {
                            key = (LNode) parentRep;
                        }
                        LayeredGraph partGraph;
                        // Get the corresponding component of the subgraphOrderingGraph or create
                        // it.
                        if (subgraphOrderingGraph.containsKey(key)) {
                            partGraph = subgraphOrderingGraph.get(key);
                        } else {
                            partGraph = new LayeredGraph();
                            partGraph.setProperty(Properties.RANDOM,
                                    layeredGraph.getProperty(Properties.RANDOM));
                            subgraphOrderingGraph.put(key, partGraph);
                        }
                        // Add representatives for the compound nodes to the ordering graph's
                        // component
                        // if not already present. Add an "is-left-of" edge between them.
                        List<LNode> nodeList = partGraph.getLayerlessNodes();
                        LNode currentRep = getNodeCopy(propCompoundCurrent, nodeList, insertedNodes);
                        LNode nextRep = getNodeCopy(propCompoundNext, nodeList, insertedNodes);
                        LEdge leftOfEdge = new LEdge();
                        LPort sourcePort = new LPort();
                        LPort targetPort = new LPort();
                        leftOfEdge.setSource(sourcePort);
                        leftOfEdge.setTarget(targetPort);
                        sourcePort.setNode(currentRep);
                        targetPort.setNode(nextRep);
                    }
                }
            }
            if (reordered) {
                reorderedLayers.put(layer, layerCompoundContents);
            }
        }
        // Break the cycles in the subgraphOrderingGraph. Any cycle-breaking heuristic can be used
        // here - but with different results with respect to the number of introduced edge
        // crossings.
        // Sander's approach is to break a cycle at the node with the smallest complete
        // average position. At the time, we use the GreedyCycleBreaker here. This may be changed
        // for a heuristic using Sander's approach in the future.
        GreedyCycleBreaker cycleBreaker = new GreedyCycleBreaker();
        for (LNode key : subgraphOrderingGraph.keySet()) {
            LayeredGraph graphComponent = subgraphOrderingGraph.get(key);
            cycleBreaker.process(graphComponent);
        }

        applyOrder(layeredGraph, subgraphOrderingGraph, graphKey, elemMap);

        getMonitor().done();
    }

    /**
     * This method stores the given node in the layerCompoundContents in the list given by the
     * relatedCompound as a key. If the relatedCompound is null, the key is the graphKey
     * representing the layeredGraph itself. Nodes will appear in the list in the order preserving
     * the result of the crossing-minimization phase.
     * 
     * @param layerCompoundContents
     *            HashMap mapping compound nodes to their direct children leave nodes and any dummy
     *            nodes assigned to them in the sense of the method getRelatedCompoundNode().
     * @param node
     *            The node to be stored.
     * @param relatedCompound
     *            The relatedCompound serving as key to the layerCompoundContents.
     * @param graphKey
     *            The key representing the layeredGraph itself.
     */
    private void insertRelatedCompound(
            final HashMap<LNode, LinkedList<LNode>> layerCompoundContents, final LNode node,
            final LNode relatedCompound, final LNode graphKey) {
        LNode key = relatedCompound;
        if (key == null) {
            key = graphKey;
        }
        LinkedList<LNode> nodeList;
        if (layerCompoundContents.containsKey(key)) {
            nodeList = layerCompoundContents.get(key);
        } else {
            nodeList = new LinkedList<LNode>();
            layerCompoundContents.put(key, nodeList);
        }
        if (!(nodeList.contains(node))) {
            nodeList.addLast(node);
        }
    }

    /**
     * Applies the order given by the acyclic subgraphOrderingGraph to the nodes of the
     * layeredGraph.
     * 
     * @param layeredGraph
     *            Graph, to which the node ordering is to be applied.
     * @param subgraphOrderingGraph
     *            The subgraphOrderingGraph. Every LayeredGraph in the HashMap must be acyclic.
     * @param graphKey
     *            The LNode serving as key representing the layeredGraph in the
     *            subgraphOrderingGraph.
     * @param elemMap
     *            The element-map mapping the original KGraph elements to LGraph elements.
     */
    private void applyOrder(final LayeredGraph layeredGraph,
            final HashMap<LNode, LayeredGraph> subgraphOrderingGraph, final LNode graphKey,
            final HashMap<KGraphElement, LGraphElement> elemMap) {

        HashMap<Layer, LinkedList<LNode>> layerOrders = new HashMap<Layer, LinkedList<LNode>>();
        for (Layer layer : reorderedLayers.keySet()) {
            LinkedList<LNode> layerOrder = new LinkedList<LNode>();
            recursiveApplyLayerOrder(layer, graphKey, layeredGraph, subgraphOrderingGraph,
                    layerOrder, elemMap);
            layerOrders.put(layer, layerOrder);
        }
        // Resort the layers.
        for (Layer layer : layerOrders.keySet()) {
            List<LNode> nodes = layer.getNodes();
            int sizeNodes = nodes.size();
            LinkedList<LNode> orderNodes = layerOrders.get(layer);
            int sizeOrderNodes = orderNodes.size();
            assert (sizeNodes == sizeOrderNodes);
            for (int i = 0; i < sizeNodes; i++) {
                nodes.remove(0);
            }
            assert (nodes.isEmpty());
            for (LNode node : orderNodes) {
                nodes.add(node);
            }
            assert (nodes.size() == sizeOrderNodes);
        }
    }

    /**
     * Applies the node order given by the subgraphOrderingGraph to one given layer. The
     * subgraphOrderingGraph passed to this method has to be acyclic and non-layered.
     * 
     * @param layer
     *            The layer to be ordered.
     * @param key
     *            The key-LNode designating the actual orderingGraph-component processed.
     * @param layeredGraph
     *            The layeredGraph to be laid out.
     * @param subgraphOrderingGraph
     *            A subgraph ordering graph without cycles.
     * @param layerOrder
     *            The list, in which the node order for the layer is stored.
     * @param elemMap
     *            The element map mapping original KGraph elements to LGraph elements.
     */
    private void recursiveApplyLayerOrder(final Layer layer, final LNode key,
            final LayeredGraph layeredGraph,
            final HashMap<LNode, LayeredGraph> subgraphOrderingGraph,
            final LinkedList<LNode> layerOrder, final HashMap<KGraphElement, LGraphElement> elemMap) {

        LayeredGraph keyGraphComponent = subgraphOrderingGraph.get(key);

        // There may be no component for the key in the subgraphOrderingGraph. A child compound node
        // of this node has to be handled nevertheless.
        if (keyGraphComponent == null) {
            KNode kKey = (KNode) key.getProperty(Properties.ORIGIN);
            for (KNode child : kKey.getChildren()) {
                LNode childRep = (LNode) elemMap.get(child);
                if (reorderedLayers.get(layer).containsKey(childRep)) {
                    assert (childRep != key);
                    recursiveApplyLayerOrder(layer, childRep, layeredGraph, subgraphOrderingGraph,
                            layerOrder, elemMap);
                }
            }
        } else {
            // If there is a component in the subgraphOrderingGraph for this key, stick to the
            // topological order of the children in the component in handling them.
            LinkedList<LNode> componentOrder = graphToList(keyGraphComponent);
            Iterator<LNode> orderIterator = componentOrder.iterator();
            while (orderIterator.hasNext()) {
                LNode currentNode = (LNode) orderIterator.next().getProperty(Properties.ORIGIN);
                assert (currentNode != key);
                recursiveApplyLayerOrder(layer, currentNode, layeredGraph, subgraphOrderingGraph,
                        layerOrder, elemMap);

            }
        }

        // Add assigned nodes for the current key to the order.
        LinkedList<LNode> assignedNodes = reorderedLayers.get(layer).get(key);
        if (assignedNodes != null) {
            for (LNode assignedNode : assignedNodes) {
                // assert (!layerOrder.contains(assignedNode));
                layerOrder.add(assignedNode);
            }
        }
    }

    /**
     * Creates a node list representing an topological sorting of the nodes for a layered graph. The
     * graph passed to this method has to be acyclic and non-layered.
     * 
     * @param graph
     *            Acyclic layeredGraph - all nodes in the layerlessNodes-list.
     * @return List containing all nodes of the layered graph in an topological order.
     */

    private LinkedList<LNode> graphToList(final LayeredGraph graph) {
        boolean acyclic = true;
        LinkedList<LNode> retList = new LinkedList<LNode>();
        List<LNode> nodes = graph.getLayerlessNodes();
        LinkedList<LNode> sources = new LinkedList<LNode>();
        // Collect the graph's sources.
        for (LNode node : nodes) {
            // if node has no incoming edges
            if (!node.getIncomingEdges().iterator().hasNext()) {
                sources.add(node);
            }
        }
        // Iterate over the sources.
        while (!sources.isEmpty()) {
            LNode currentSource = sources.getFirst();
            sources.removeFirst();
            // Add source to sorting.
            retList.add(currentSource);
            Iterator<LEdge> outEdgesIterator = currentSource.getOutgoingEdges().iterator();
            // Collect the source's targets.
            HashSet<LNode> targets = new HashSet<LNode>();
            while (outEdgesIterator.hasNext()) {
                LEdge edge = outEdgesIterator.next();
                LNode edgeTarget = edge.getTarget().getNode();
                if (!(targets.contains(edgeTarget))) {
                    targets.add(edgeTarget);
                }
            }
            // Iterate the targets.
            for (LNode target : targets) {
                boolean isNewSource = true;
                Iterator<LEdge> inEdgesIterator = target.getIncomingEdges().iterator();
                LinkedList<LEdge> removableEdges = new LinkedList<LEdge>();
                // If they have no further incoming edges, add them to the source list.
                while (inEdgesIterator.hasNext()) {
                    LEdge edge = inEdgesIterator.next();
                    LNode source = edge.getSource().getNode();
                    if (source == currentSource) {
                        removableEdges.add(edge);
                    } else {
                        isNewSource = false;
                    }
                }
                // Remove edges already visited.
                int edgesSize = removableEdges.size();
                for (int i = 0; i < edgesSize; i++) {
                    LEdge edge = removableEdges.removeFirst();
                    edge.getSource().getNode().getPorts().remove(edge.getSource());
                    edge.getTarget().getNode().getPorts().remove(edge.getTarget());
                }
                if (isNewSource) {
                    sources.add(target);
                }
            }
        }
        // There should be no more edges left. If otherwise, there is at least one cycle in the
        // graph passed as parameter.
        for (LNode node : nodes) {
            for (LPort port : node.getPorts()) {
                if (port.getConnectedEdges().iterator().hasNext()) {
                    acyclic = false;
                }
            }
        }
        // This method will not work, if the graph is cyclic.
        assert acyclic;
        return retList;
    }

    /**
     * Checks, if a representative for the LNode currentRep is already inserted into the
     * corresponding component graph of the subgraph-ordering-graph. Makes the insertion, if not.
     * 
     * @param node
     *            The LNode to be represented.
     * @param layerlessNodes
     *            The list of layerless nodes of the corresponding component graph.
     * @param insertedNodes
     *            The Hashmap, in which the insertion of Nodes into the subGraphOrderingGraph is
     *            documented.
     * @return Returns the representative of the currentNode in the component graph. It may be
     *         freshly created by this method.
     */
    private LNode getNodeCopy(final LNode node, final List<LNode> layerlessNodes,
            final HashMap<LNode, LNode> insertedNodes) {
        LNode retNode;
        if (insertedNodes.containsKey(node)) {
            // Node already has a representative.
            retNode = insertedNodes.get(node);
        } else {
            // A representative has to be created.
            retNode = new LNode();
            retNode.setProperty(Properties.ORIGIN, node);
            // retNode.copyProperties(node);
            insertedNodes.put(node, retNode);
            layerlessNodes.add(retNode);
        }
        return retNode;
    }

    /**
     * Get the compound node an LNode belongs to.
     * 
     * @param node
     *            The LNode for which the corresponding compound node is to be returned.
     * @param layeredGraph
     *            The layered Graph, which is to be laid out.
     * @return Returns: A. The parent node for a leave node, if it is not a node of the uppermost
     *         hierarchy level- in that case, null will be returned. B. The compound node of which's
     *         representation the node is part of for compound dummies. C. The node enclosing the
     *         represented LGraphElement for dummies of another kind. Null, if represented
     *         LGraphElement is of depth 1. D. null in default case.
     */
    public static LNode getRelatedCompoundNode(final LNode node, final LayeredGraph layeredGraph) {
        // method is to return the node itself in the default case
        LNode retNode = null;
        HashMap<KGraphElement, LGraphElement> elemMap = layeredGraph
                .getProperty(Properties.ELEMENT_MAP);
        NodeType nodeType = node.getProperty(Properties.NODE_TYPE);
        LGraphElement parentRepresentative;
        switch (nodeType) {
        case LOWER_COMPOUND_BORDER:
        case LOWER_COMPOUND_PORT:
        case UPPER_COMPOUND_BORDER:
        case UPPER_COMPOUND_PORT:
            retNode = node.getProperty(Properties.COMPOUND_NODE);
            break;
        case NORMAL:
            KNode parent = node.getProperty(Properties.PARENT);
            parentRepresentative = elemMap.get(parent);
            if (!(parentRepresentative instanceof LayeredGraph)) {
                retNode = (LNode) parentRepresentative;
            }
            break;
        case LONG_EDGE:
            // LPort sourcePort = node.getProperty(Properties.LONG_EDGE_SOURCE);
            // LPort targetPort = node.getProperty(Properties.LONG_EDGE_TARGET);
            // LNode sourceNode = sourcePort.getNode();
            // LNode targetNode = targetPort.getNode();
            // LNode relatedCompoundSource = getRelatedCompoundNode(sourceNode, layeredGraph);
            // LNode relatedCompoundTarget = getRelatedCompoundNode(targetNode, layeredGraph);
            // if (relatedCompoundSource == relatedCompoundTarget) {
            // retNode = relatedCompoundSource;
            // } else {
            // LinkedList<LNode> sourceTargetList = new LinkedList<LNode>();
            // sourceTargetList.add(relatedCompoundSource);
            // sourceTargetList.add(relatedCompoundTarget);
            // propagatePair(sourceTargetList, elemMap);
            // LNode newSource = sourceTargetList.getFirst();
            // KNode newSourceParent = newSource.getProperty(Properties.PARENT);
            // LGraphElement container = elemMap.get(newSourceParent);
            // if (!(container instanceof LayeredGraph)) {
            // retNode = (LNode) container;
            // }
            //
            // // int depthSource = relatedCompoundSource.getProperty(Properties.DEPTH);
            // // int depthTarget = relatedCompoundTarget.getProperty(Properties.DEPTH);
            // // if (depthSource != depthTarget) {
            // // for (int i = depthSource; i > depthTarget; i--) {
            // // relatedCompoundSource = getRelatedCompoundNode(relatedCompoundSource,
            // // layeredGraph);
            // // }
            // // for (int j = depthTarget; j > depthSource; j--) {
            // // relatedCompoundTarget = getRelatedCompoundNode(relatedCompoundTarget,
            // // layeredGraph);
            // // }
            // // }
            // // depthSource = relatedCompoundSource.getProperty(Properties.DEPTH);
            // // depthTarget = relatedCompoundTarget.getProperty(Properties.DEPTH);
            // // assert (depthSource == depthTarget);
            // //
            // // while (relatedCompoundSource != relatedCompoundTarget) {
            // // relatedCompoundSource = getRelatedCompoundNode(relatedCompoundSource,
            // // layeredGraph);
            // // relatedCompoundTarget = getRelatedCompoundNode(relatedCompoundTarget,
            // // layeredGraph);
            // // }
            // // retNode = relatedCompoundSource;
            // }

            // // An edge is regarded contained by the compound node of it's target.
            // LPort targetPort = node.getProperty(Properties.LONG_EDGE_TARGET);
            // LNode targetNode = targetPort.getNode();
            // retNode = getRelatedCompoundNode(targetNode, layeredGraph);

            // An edge is regarded contained by the compound node which contains both source and
            // target (directly or indirectly). If this is the layeredGraph, return null.
            LPort sourcePort = node.getProperty(Properties.LONG_EDGE_SOURCE);
            LPort targetPort = node.getProperty(Properties.LONG_EDGE_TARGET);
            LNode sourceNode = sourcePort.getNode();
            LNode targetNode = targetPort.getNode();
            KNode sourceNodeOrigin = (KNode) sourceNode.getProperty(Properties.ORIGIN);
            KNode targetNodeOrigin = (KNode) targetNode.getProperty(Properties.ORIGIN);
            if (KimlUtil.isDescendant(sourceNodeOrigin, targetNodeOrigin)
                    || KimlUtil.isDescendant(targetNodeOrigin, sourceNodeOrigin)) {
                LinkedList<LNode> sourceTargetList = new LinkedList<LNode>();
                sourceTargetList.add(sourceNode);
                sourceTargetList.add(targetNode);
                propagatePair(sourceTargetList, elemMap);
                LNode newSource = sourceTargetList.getFirst();
                KNode newSourceParent = newSource.getProperty(Properties.PARENT);
                LGraphElement container = elemMap.get(newSourceParent);
                if (!(container instanceof LayeredGraph)) {
                    retNode = (LNode) container;
                }
            } else {
                LNode sourceNodeCompound = getRelatedCompoundNode(sourceNode, layeredGraph);
                LNode targetNodeCompound = getRelatedCompoundNode(targetNode, layeredGraph);
                if (sourceNodeCompound == targetNodeCompound) {
                    retNode = sourceNodeCompound;
                } else {
                    retNode = targetNodeCompound;
                }
            }
            break;
        case EXTERNAL_PORT:
            LGraphElement nodeParentRep = elemMap
                    .get(((KPort) (node.getProperty(Properties.ORIGIN))).getNode().getParent());
            if (!(nodeParentRep == layeredGraph)) {
                retNode = (LNode) nodeParentRep;
            }
            break;
        case NORTH_SOUTH_PORT:
            LNode portNode = node.getProperty(Properties.IN_LAYER_LAYOUT_UNIT);
            KNode portNodeParent = portNode.getProperty(Properties.PARENT);
            LGraphElement portNodeParentRepresentative = elemMap.get(portNodeParent);
            if (!(elemMap.get(portNodeParent) instanceof LayeredGraph)) {
                retNode = (LNode) portNodeParentRepresentative;
            }
            break;
        default:
            break;
        }

        return retNode;
    }

    /**
     * Finds for a pair of LNodes the pair of ancestors with a common parent that is highest in
     * depth in the inclusion tree. Each of the ancestors may be the given node itself.
     * 
     * @param sourceTargetList
     *            The pair of nodes is handed over as a List. The pair of ancestors will be stored
     *            in the same list.
     * @param elemMap
     *            The element map that maps the original KGraphElements to the LGraphElements.
     */
    private static void propagatePair(final LinkedList<LNode> sourceTargetList,
            final HashMap<KGraphElement, LGraphElement> elemMap) {
        LNode sourceNode = sourceTargetList.getFirst();
        LNode targetNode = sourceTargetList.getLast();

        KNode currentSource = getRelatedKNode(sourceNode);
        KNode currentTarget = getRelatedKNode(targetNode);

        int depthSource = elemMap.get(currentSource).getProperty(Properties.DEPTH);
        int depthTarget = elemMap.get(currentTarget).getProperty(Properties.DEPTH);
        assert (depthSource > 0);
        assert (depthTarget > 0);

        KNode currentSourceAncestor = currentSource.getParent();
        KNode currentTargetAncestor = currentTarget.getParent();

        // If source and target differ in depth in the nesting tree, crawl up the
        // nesting tree on the deep side to reach even depth level
        if (depthSource != depthTarget) {
            for (int i = depthSource; i > depthTarget; i--) {
                currentSource = currentSource.getParent();
            }
            for (int j = depthTarget; j > depthSource; j--) {
                currentTarget = currentTarget.getParent();
            }
        }

        if (currentSourceAncestor != currentTargetAncestor) {
            // Walk up the nesting tree from both sides, until nodes have the same
            // parent.
            currentSourceAncestor = currentSource.getParent();
            currentTargetAncestor = currentTarget.getParent();
            while (currentSourceAncestor != currentTargetAncestor) {
                currentSource = currentSource.getParent();
                currentTarget = currentTarget.getParent();
                currentSourceAncestor = currentSource.getParent();
                currentTargetAncestor = currentTarget.getParent();
            }
        }
        LNode newSource = (LNode) elemMap.get(currentSource);
        LNode newTarget = (LNode) elemMap.get(currentTarget);
        sourceTargetList.addFirst(newSource);
        sourceTargetList.addLast(newTarget);
    }

    /**
     * Returns the KNode the given node is representing (in case of normal or compound dummy nodes)
     * or directly related to - port node in case of Port dummies, target node origin in case of
     * long edge dummies.
     * 
     * @param node
     *            The node for which to find the related KNode.
     * @return The KNode represented by the given node or directly related to it.
     */
    private static KNode getRelatedKNode(final LNode node) {
        KNode retNode;
        Object origin = node.getProperty(Properties.ORIGIN);
        NodeType nodeType = node.getProperty(Properties.NODE_TYPE);
        switch (nodeType) {
        case EXTERNAL_PORT:
            KNode portNode = ((KPort) (node.getProperty(Properties.ORIGIN))).getNode();
            retNode = portNode;
            break;
        case LONG_EDGE:
            LNode edgeTarget = node.getProperty(Properties.LONG_EDGE_TARGET).getNode();
            Object edgeTargetOrigin = edgeTarget.getProperty(Properties.ORIGIN);
            assert (edgeTargetOrigin instanceof KNode);
            retNode = (KNode) edgeTargetOrigin;
            break;
        case NORTH_SOUTH_PORT:
            LNode lnode = node.getProperty(Properties.IN_LAYER_LAYOUT_UNIT);
            Object nodeOrigin = lnode.getProperty(Properties.ORIGIN);
            assert (nodeOrigin instanceof KNode);
            retNode = (KNode) nodeOrigin;
            break;
        default:
            assert (origin instanceof KNode);
            retNode = (KNode) origin;
            break;
        }
        return retNode;
    }
}
