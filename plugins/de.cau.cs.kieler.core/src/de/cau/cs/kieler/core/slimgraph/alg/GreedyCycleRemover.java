/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
package de.cau.cs.kieler.core.slimgraph.alg;

import java.util.LinkedList;

import de.cau.cs.kieler.core.slimgraph.*;

/**
 * Cycle remover implementation that uses a greedy algorithm.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class GreedyCycleRemover extends AbstractCycleRemover {

	/** indegree values for the nodes */
	private int indeg[];
	/** outdegree values for the nodes */
	private int outdeg[];
	/** list of source nodes */
	LinkedList<KSlimNode> sources = new LinkedList<KSlimNode>();
	/** list of sink nodes */
	LinkedList<KSlimNode> sinks = new LinkedList<KSlimNode>();
	
	/*
	 * (non-Javadoc)
	 * @see de.cau.cs.kieler.core.graph.alg.AbstractCycleRemover#reset()
	 */
	public void reset() {
		super.reset();
		sources.clear();
		sinks.clear();
	}
	
	/* (non-Javadoc)
	 * @see de.cau.cs.kieler.core.graph.alg.ICycleRemover#removeCycles(de.cau.cs.kieler.core.graph.KGraph)
	 */
	public void removeCycles(KSlimGraph graph) {
		getMonitor().begin("Greedy cycle removal", 1);
		reversedEdges = new LinkedList<KSlimEdge>();

		// initialize values for the algorithm
		int unprocessedNodes = graph.nodes.size();
		indeg = new int[unprocessedNodes];
		outdeg = new int[unprocessedNodes];
		int nextRight = -1, nextLeft = 1;
		for (KSlimNode node : graph.nodes) {
			node.rank = 0;
			for (KSlimNode.IncEntry edgeEntry : node.incidence) {
				if (edgeEntry.type == KSlimNode.IncEntry.Type.OUT)
					outdeg[node.id]++;
				else
					indeg[node.id]++;
			}
			if (outdeg[node.id] == 0)
				sinks.add(node);
			else if (indeg[node.id] == 0)
				sources.add(node);
		}
		
		// assign ranks to all nodes
		while (unprocessedNodes > 0) {
			while (!sinks.isEmpty()) {
				KSlimNode sink = sinks.removeFirst();
				sink.rank = nextRight--;
				updateNeighbors(sink);
				unprocessedNodes--;
			}
			while (!sources.isEmpty()) {
				KSlimNode source = sources.removeFirst();
				source.rank = nextLeft++;
				updateNeighbors(source);
				unprocessedNodes--;
			}
			if (unprocessedNodes != 0) {
				int maxOutflow = Integer.MIN_VALUE;
				KSlimNode maxNode = null;
				for (KSlimNode node : graph.nodes) {
					if (node.rank == 0) {
						int outflow = outdeg[node.id] - indeg[node.id];
						if (outflow > maxOutflow) {
							maxOutflow = outflow;
							maxNode = node;
						}
					}
				}
				maxNode.rank = nextLeft++;
				updateNeighbors(maxNode);
				unprocessedNodes--;
			}
		}
		
		// shift negative ranks
		int shiftBase = graph.nodes.size() + 1;
		for (KSlimNode node : graph.nodes)
			if (node.rank < 0)
				node.rank += shiftBase;
		
		// mark edges that point left
		for (KSlimEdge edge : graph.edges) {
			if (edge.source.rank > edge.target.rank)
				reversedEdges.add(edge);
			else
				edge.rank = 0;
		}
		
		// reverse all marked edges
		reverseEdges();
		
		getMonitor().done();
	}

	/**
	 * Updates indegree and outdegree values of the neighbors of the
	 * given node, simulating its removal from the graph. the sources and
	 * sinks lists are also updated.
	 * 
	 * @param node node for which neighbors are updated
	 */
	private void updateNeighbors(KSlimNode node) {
		for (KSlimNode.IncEntry edgeEntry : node.incidence) {
			KSlimNode endpoint = edgeEntry.endpoint();
			if (endpoint.rank == 0) {
				if (edgeEntry.type == KSlimNode.IncEntry.Type.OUT) {
					indeg[endpoint.id]--;
					if (indeg[endpoint.id] == 0 && outdeg[endpoint.id] != 0)
						sources.add(endpoint);
				}
				else {
					outdeg[endpoint.id]--;
					if (outdeg[endpoint.id] == 0 && indeg[endpoint.id] != 0)
						sinks.add(endpoint);
				}
			}
		}
	}

}
