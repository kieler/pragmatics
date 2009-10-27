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

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;
import de.cau.cs.kieler.core.slimgraph.KGraphSection;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;
import de.cau.cs.kieler.klodd.orthogonal.structures.*;


/**
 * Algorithm that determines the biconnected components of a graph
 * with a DFS.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class BiconnectedComponents extends AbstractAlgorithm {

	/** list of biconnected components calculated by the algorithm */
	private List<KGraphSection> components = new LinkedList<KGraphSection>();
	/** next DFS number to assign */
	private int nextDfsnum = 0;
	/** list of lowest point numbers */
	private int[] lowpt;
	/** list of parent nodes */
	private KSlimNode[] parent;
	/** stack with unfinished nodes */
	private Stack<KSlimNode> unfinished = new Stack<KSlimNode>();
	
	/*
	 * (non-Javadoc)
	 * @see de.cau.cs.kieler.klodd.core.algorithms.AbstractAlgorithm#reset()
	 */
	public void reset() {
		super.reset();
		components.clear();
		unfinished.clear();
		nextDfsnum = 0;
	}
	
	/**
	 * Calculates the biconnected components of the given graph.
	 * The input graph is considered as undirected.
	 * 
	 * @param graph graph to be processed
	 * @return list of biconnected components
	 */
	public List<KGraphSection> findComponents(KSlimGraph graph) {
		// initialize DFS variables
		int graphSize = graph.nodes.size();
		lowpt = new int[graphSize];
		parent = new KSlimNode[graphSize];
		for (KSlimNode node : graph.nodes) {
			node.rank = -1;
		}
		
		// perform DFS on all nodes of the graph
		for (KSlimNode node : graph.nodes) {
			if (node.rank < 0)
				dfsVisit(node);
		}
		
		return components;
	}
	
	/**
	 * Performs a DFS starting at the given node.
	 * 
	 * @param node node to visit
	 */
	private void dfsVisit(KSlimNode node) {
		node.rank = nextDfsnum++;
		lowpt[node.id] = node.rank;
		unfinished.push(node);
		for (KSlimNode.IncEntry edgeEntry : node.incidence) {
			KSlimNode endpoint = edgeEntry.endpoint();
			if (endpoint.rank < 0) {
				parent[endpoint.id] = node;
				dfsVisit(endpoint);
				lowpt[node.id] = Math.min(lowpt[node.id], lowpt[endpoint.id]);
			}
			else {
				lowpt[node.id] = Math.min(lowpt[node.id], endpoint.rank);
			}
		}
		if (node.rank >= 2 && lowpt[node.id] == parent[node.id].rank) {
			KGraphSection graphSection = new KGraphSection();
			KSlimNode sectionNode;
			do {
				sectionNode = unfinished.pop();
				graphSection.nodes.add(sectionNode);
			} while (sectionNode != node);
			graphSection.nodes.add(parent[node.id]);
			graphSection.sortNodes();
			components.add(graphSection);
		}
	}
	
}
