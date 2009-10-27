/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.orthogonal.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.slimgraph.KSlimEdge;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;
import de.cau.cs.kieler.klodd.orthogonal.structures.*;

/**
 * Algorithm that finds the connected components for a layout graph
 * and outputs them as separate TSM graphs.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class ConnectedComponents extends AbstractAlgorithm {

	/** stack of layout edges for BFS */
	private LinkedList<KEdge> edgeStack = new LinkedList<KEdge>();
	/** mapping of layout nodes to TSM nodes */
	private Map<KNode, KSlimNode> nodeMap = new HashMap<KNode, KSlimNode>();
	/** mapping of layout edges to TSM edges */
	private Map<KEdge, KSlimEdge> edgeMap = new HashMap<KEdge, KSlimEdge>();
	
	/*
	 * (non-Javadoc)
	 * @see de.cau.cs.kieler.klodd.core.algorithms.AbstractAlgorithm#reset()
	 */
	public void reset() {
		super.reset();
		edgeStack.clear();
		nodeMap.clear();
		edgeMap.clear();
	}
	
	/**
	 * Finds the connected components of a layout graph and returns
	 * them as separate TSM graphs.
	 * 
	 * @param parentNode parent layout node
	 * @return list of connected components
	 */
	public List<TSMGraph> findComponents(KNode parentNode) {
		List<TSMGraph> components = new LinkedList<TSMGraph>();
		
		for (KNode child : parentNode.getChildren()) {
			if (!nodeMap.containsKey(child))
				components.add(findComponent(child));
		}
		return components;
	}
	
	/**
	 * Finds the connected component that contains the given node.
	 * 
	 * @param startLayoutNode node to use as start for the new component
	 * @return a graph representing the connected component
	 */
	private TSMGraph findComponent(KNode startLayoutNode) {
		TSMGraph graph = new TSMGraph();
		KSlimNode newTsmNode = new TSMNode(graph, TSMNode.Type.LAYOUT, startLayoutNode);
		nodeMap.put(startLayoutNode, newTsmNode);
		edgeStack.addAll(startLayoutNode.getOutgoingEdges());
		edgeStack.addAll(startLayoutNode.getIncomingEdges());
		
		while (!edgeStack.isEmpty()) {
			KEdge layoutEdge = edgeStack.removeFirst();
			// TODO add support for external ports
			if (layoutEdge.getSource().getParent()
			        == layoutEdge.getTarget().getParent()) {
				if (!edgeMap.containsKey(layoutEdge)) {
					KNode currentLayoutNode = layoutEdge.getSource();
					KSlimNode sourceNode = nodeMap.get(currentLayoutNode);
					if (sourceNode == null) {
						sourceNode = new TSMNode(graph, TSMNode.Type.LAYOUT,
								currentLayoutNode);
						nodeMap.put(currentLayoutNode, sourceNode);
						edgeStack.addAll(currentLayoutNode.getOutgoingEdges());
						edgeStack.addAll(currentLayoutNode.getIncomingEdges());
					}
					currentLayoutNode = layoutEdge.getTarget();
					KSlimNode targetNode = nodeMap.get(currentLayoutNode);
					if (targetNode == null) {
						targetNode = new TSMNode(graph, TSMNode.Type.LAYOUT,
								currentLayoutNode);
						nodeMap.put(currentLayoutNode, targetNode);
						edgeStack.addAll(currentLayoutNode.getOutgoingEdges());
						edgeStack.addAll(currentLayoutNode.getIncomingEdges());
					}
					KSlimEdge tsmEdge = new TSMEdge(graph, sourceNode, targetNode,
							layoutEdge);
					tsmEdge.connectNodes();
					edgeMap.put(layoutEdge, tsmEdge);
				}
			}
		}
		return graph;
	}
	
}
