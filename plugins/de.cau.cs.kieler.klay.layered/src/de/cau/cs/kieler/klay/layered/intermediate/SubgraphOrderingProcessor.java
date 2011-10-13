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
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
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

        // // A subgraph ordering graph is used to find the correct subgraph ordering. The subgraph
        // // ordering graph is disconnected - it consists of connected partial graphs for each
        // depth
        // // level of the nesting tree. Represent them as a list of layered graphs.
        // LinkedList<LayeredGraph> levelOrderingGraphs = new LinkedList<LayeredGraph>();
        // for (int j = 1; j <= layeredGraph.getProperty(Properties.MAX_DEPTH); j++) {
        // LayeredGraph partialSubgraphOrderingGraph = new LayeredGraph();
        // partialSubgraphOrderingGraph.setProperty(Properties.ORIGIN, j);
        // levelOrderingGraphs.add(partialSubgraphOrderingGraph);
        // }

        // Insert nodes and edges representing the relationship "is left of" into the subgraph
        // ordering graph parts.
        // for (Layer layer : layeredGraph.getLayers()) {
        // List<LNode> layerNodes = layer.getNodes();
        // for (int i = 0; i < layerNodes.size(); i++) {
        // LNode currentNode = layerNodes.get(i);
        // LNode relatedCompoundCurrent = getRelatedCompoundNode(currentNode, layeredGraph);
        // LNode nextNode = layerNodes.get(i + 1);
        // LNode relatedCompoundNext = getRelatedCompoundNode(nextNode, layeredGraph);
        // // There is only something to be done, if nodes that are neighbors in a layer are
        // // of different compound nodes.
        // if (relatedCompoundCurrent != relatedCompoundNext) {
        // int depthCurrent = currentNode.getProperty(Properties.DEPTH);
        // int depthNext = nextNode.getProperty(Properties.DEPTH);
        // int maxDepth = Math.max(depthCurrent, depthNext);
        // HashMap<KGraphElement, LGraphElement> elemMap = layeredGraph
        // .getProperty(Properties.ELEMENT_MAP);
        // // Insert nodes resp. their ancestors of the relevant level into the
        // // level-ordering-Graph, if not already done.
        // LayeredGraph orderingSubgraph = levelOrderingGraphs.get(maxDepth);
        // while (depthCurrent != maxDepth) {
        // currentNode = (LNode) elemMap.get(currentNode
        // .getProperty(Properties.PARENT));
        // depthCurrent = currentNode.getProperty(Properties.DEPTH);
        // }
        // LNode currentCopy = getNodeCopy(orderingSubgraph.getLayerlessNodes());
        // if (currentCopy == null) {
        // currentCopy = new LNode();
        // currentCopy.setProperty(Properties.ORIGIN, currentNode);
        // orderingSubgraph.getLayerlessNodes().add(currentCopy);
        // }
        //
        // // write method for insertion of a node into a Subgraph ordering graph.
        //
        // }
        // }
        // }

        getMonitor().done();
    }

    private LNode getNodeCopy(List<LNode> layerlessNodes) {
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
            KNode currentSource = (KNode) sourceNode.getProperty(Properties.ORIGIN);
            KNode currentTarget = (KNode) targetNode.getProperty(Properties.ORIGIN);
            int depthSource = sourceNode.getProperty(Properties.DEPTH);
            int depthTarget = targetNode.getProperty(Properties.DEPTH);
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

            LGraphElement container = elemMap.get(currentSourceAncestor);
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
}
