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
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.kgraph.KNode;
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

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin(
                "Order subgraphs so that the relative position is the same on all layers", 1);
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
        graphKey.setProperty(Properties.ORIGIN, layeredGraph);

        // Build the subgraphOrderingGraph:
        // Insert nodes and edges representing the relationship "is left of" into the subgraph
        // ordering graph parts.
        for (Layer layer : layeredGraph.getLayers()) {
            List<LNode> layerNodes = layer.getNodes();
            for (int i = 0; i < layerNodes.size(); i++) {
                LNode currentNode = layerNodes.get(i);
                LNode relatedCompoundCurrent = getRelatedCompoundNode(currentNode, layeredGraph);
                LNode nextNode = layerNodes.get(i + 1);
                LNode relatedCompoundNext = getRelatedCompoundNode(nextNode, layeredGraph);
                // There is only something to be done, if nodes that are neighbors in a layer are
                // of different compound nodes.
                if (relatedCompoundCurrent != relatedCompoundNext) {
                    // Find the correct partial graph to insert the "is left of"-relationship by
                    // propagating the dependency up the inclusion tree till two compound nodes with
                    // the same parent are reached.
                    LinkedList<LNode> leftRightList = new LinkedList<LNode>();
                    propagatePair(leftRightList, elemMap);
                    LNode propCompoundCurrent = leftRightList.getFirst();
                    LNode propCompoundNext = leftRightList.getLast();
                    LNode key;
                    LGraphElement parentRep = elemMap.get(propCompoundCurrent
                            .getProperty(Properties.PARENT));
                    if (parentRep == layeredGraph) {
                        key = graphKey;
                    } else {
                        key = (LNode) parentRep;
                    }
                    LayeredGraph partGraph;
                    // Get the corresponding component of the subgraphOrderingGraph or create it.
                    if (subgraphOrderingGraph.containsKey(key)) {
                        partGraph = subgraphOrderingGraph.get(key);
                    } else {
                        partGraph = new LayeredGraph();
                    }
                    // Add representatives for the compound nodes to the ordering graph's component
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
        
        applyOrder(layeredGraph, subgraphOrderingGraph);

        getMonitor().done();
    }

    /**
     * Applies the order given by the subgraphOrderingGraph to the nodes of the layeredGraph.
     * 
     * @param layeredGraph
     *      Graph, to which the node ordering is to be applied.
     * @param subgraphOrderingGraph
     *      The subgraphOrderingGraph. Every LayeredGraph in the HashMap is expected to be acyclic.
     */
    private void applyOrder(final LayeredGraph layeredGraph,
            final HashMap<LNode, LayeredGraph> subgraphOrderingGraph) {
        
        
        // TODO Auto-generated method stub
        
    }

    /**
     * Checks, if a representative for the LNode currentRep is already inserted into the component
     * graph of the subgraph-ordering-graph that is given by the LNode key. Makes the insertion, if
     * not.
     * 
     * @param currentRep
     *            The LNode to be represented.
     * @param layerlessNodes
     *            The list of layerless nodes of the corresponding component graph.
     * @param insertedNodes
     *            The Hashmap, in which the insertion of Nodes into the subGraphOrderingGraph is
     *            documented.
     * @return Returns the representative of the currentNode in the component graph. It may be
     *         freshly created by this method.
     */
    private LNode getNodeCopy(final LNode currentRep, final List<LNode> layerlessNodes,
            final HashMap<LNode, LNode> insertedNodes) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Get the compound node an LNode belongs to.
     * 
     * @param node
     *            The LNode for which the corresponding compound node is to be returned.
     * @param layeredGraph
     *            The layered Graph, which is to be laid out.
     * @return Returns: A. The parent node for a leave node, if it is not a node of the uppermost
     *         hierarchy level- in that case, the node itself will be returned. B. The compound node
     *         of which's representation the node is part of for compound dummies. C. The node
     *         enclosing the represented LGraphElement for dummies of another kind. D. The node
     *         itself in default case.
     */
    private LNode getRelatedCompoundNode(final LNode node, final LayeredGraph layeredGraph) {
        // method is to return the node itself in the default case
        LNode retNode = node;
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
            if (!(elemMap.get(parent) == layeredGraph)) {
                retNode = (LNode) parentRepresentative;
            }
            break;
        case LONG_EDGE:
            // An edge is regarded contained by the compound node which contains both source and
            // target (directly or indirectly). If this is the layeredGraph, return node itself.
            LPort sourcePort = node.getProperty(Properties.LONG_EDGE_SOURCE);
            LPort targetPort = node.getProperty(Properties.LONG_EDGE_TARGET);
            LNode sourceNode = sourcePort.getNode();
            LNode targetNode = targetPort.getNode();
            LinkedList<LNode> sourceTargetList = new LinkedList<LNode>();
            sourceTargetList.add(sourceNode);
            sourceTargetList.add(targetNode);
            propagatePair(sourceTargetList, elemMap);
            LNode newSource = sourceTargetList.getFirst();
            KNode newSourceParent = newSource.getProperty(Properties.PARENT);
            LGraphElement container = elemMap.get(newSourceParent);
            if (!(container == layeredGraph)) {
                retNode = (LNode) container;
            }
        case EXTERNAL_PORT:
            LGraphElement nodeParentRep = elemMap
                    .get(((KPort) (node.getProperty(Properties.ORIGIN))).getNode().getParent());
            if (!(nodeParentRep == layeredGraph)) {
                retNode = (LNode) nodeParentRep;
            }
        case NORTH_SOUTH_PORT:
            LNode portNode = node.getProperty(Properties.IN_LAYER_LAYOUT_UNIT);
            KNode portNodeParent = portNode.getProperty(Properties.PARENT);
            LGraphElement portNodeParentRepresentative = elemMap.get(portNodeParent);
            if (!(elemMap.get(portNodeParent) == layeredGraph)) {
                retNode = (LNode) portNodeParentRepresentative;
            }

        default:
            break;
        }

        if (node.getProperty(Properties.NODE_TYPE) == NodeType.NORMAL) {
            retNode = (LNode) elemMap.get(node.getProperty(Properties.PARENT));
        } else {
            retNode = node.getProperty(Properties.COMPOUND_NODE);
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
    private void propagatePair(final LinkedList<LNode> sourceTargetList,
            final HashMap<KGraphElement, LGraphElement> elemMap) {
        LNode sourceNode = sourceTargetList.getFirst();
        LNode targetNode = sourceTargetList.getLast();
        int depthSource = sourceNode.getProperty(Properties.DEPTH);
        int depthTarget = targetNode.getProperty(Properties.DEPTH);

        KNode currentSource = (KNode) sourceNode.getProperty(Properties.ORIGIN);
        KNode currentTarget = (KNode) targetNode.getProperty(Properties.ORIGIN);

        KNode currentSourceAncestor = currentSource.getParent();
        KNode currentTargetAncestor = currentSource.getParent();

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

        if (currentSource != currentTarget) {
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
}
