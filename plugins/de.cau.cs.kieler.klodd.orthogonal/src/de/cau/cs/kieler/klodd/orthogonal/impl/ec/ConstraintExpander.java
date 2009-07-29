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
package de.cau.cs.kieler.klodd.orthogonal.impl.ec;

import java.util.HashMap;
import java.util.Map;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.slimgraph.KSlimEdge;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;
import de.cau.cs.kieler.klodd.orthogonal.structures.*;


/**
 * Algorithm for the expansion of embedding constraints.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class ConstraintExpander extends AbstractAlgorithm {
	
	/** the new graph created during EC expansion */
	private TSMGraph expandedGraph;
	private Map<KSlimEdge, TSMNode> incoming2NodeMap = new HashMap<KSlimEdge, TSMNode>();
	private Map<KSlimEdge, TSMNode> outgoing2NodeMap = new HashMap<KSlimEdge, TSMNode>();
	
	/**
	 * Creates a TSM graph from a given parent layout node and
	 * expands all embedding constraints.
	 * 
	 * @param parentNode parent layout node
	 * @param constraintsMap constraints for the child nodes
	 * @return expanded TSM graph
	 */
	public TSMGraph expand(KSlimGraph graph) {
		expandedGraph = new TSMGraph();
		
		for (KSlimNode node : graph.nodes) {
			TSMNode tsmNode = (TSMNode)node;
			if (tsmNode.embeddingConstraint == null) {
				expandNode(node);
			}
			else {
				expandConstraint(tsmNode.embeddingConstraint, null);
			}
		}
		
		return expandedGraph;
	}
	
	/**
	 * Creates a new node for the given node of the input graph and
	 * registers all incident edges.
	 * 
	 * @param node node of the input graph
	 */
	private void expandNode(KSlimNode node) {
		TSMNode newNode = new TSMNode(expandedGraph, TSMNode.Type.ECEXPANSION, node);
		for (KSlimNode.IncEntry edgeEntry : node.incidence) {
			registerEdge(newNode, (TSMEdge)edgeEntry.edge,
					edgeEntry.type == KSlimNode.IncEntry.Type.OUT);
		}
	}
	
	/**
	 * Expands a given embedding constraint and connects the expansion with
	 * the given node.
	 * 
	 * @param constraint embedding constraint to expand
	 * @param parentNode node to connect, or null if there is none
	 */
	private void expandConstraint(EmbeddingConstraint constraint,
			TSMNode parentNode) {
		switch (constraint.type) {
		case OUT_EDGE:
		case IN_EDGE:
			// connect an edge with the parent constraint
			assert parentNode != null;
			TSMEdge edge = (TSMEdge)constraint.object;
			registerEdge(parentNode, edge, constraint.type
					== EmbeddingConstraint.Type.OUT_EDGE);
			break;
		case GROUPING:
			// create a single node and connect children with it
			TSMNode groupingNode = new TSMNode(expandedGraph, TSMNode.Type.ECEXPANSION,
					constraint);
			if (parentNode != null) {
				KSlimEdge newEdge = new KSlimEdge(expandedGraph, parentNode, groupingNode);
				newEdge.connectNodes();
			}
			for (EmbeddingConstraint childConstraint : constraint.children) {
				expandConstraint(childConstraint, groupingNode);
			}
			break;
		case ORIENTED:
		case MIRROR:
			// for oriented and mirrored constraints a wheel gadget is created
			KSlimNode hubNode = new TSMNode(expandedGraph, TSMNode.Type.ECEXPANSION,
					constraint);
			KSlimNode firstNode = null, lastNode = null;
			if (parentNode != null) {
				firstNode = new TSMNode(expandedGraph, TSMNode.Type.ECEXPANSION);
				KSlimEdge newEdge = new KSlimEdge(expandedGraph, firstNode, hubNode);
				newEdge.connectNodes();
				lastNode = new TSMNode(expandedGraph, TSMNode.Type.ECEXPANSION);
				newEdge = new KSlimEdge(expandedGraph, hubNode, lastNode);
				newEdge.connectNodes();
				newEdge = new KSlimEdge(expandedGraph, firstNode, lastNode);
				newEdge.connectNodes();
				newEdge = new KSlimEdge(expandedGraph, parentNode, firstNode);
				newEdge.connectNodes();
			}
			for (EmbeddingConstraint childConstraint : constraint.children) {
				TSMNode xNode = new TSMNode(expandedGraph, TSMNode.Type.ECEXPANSION);
				KSlimEdge newEdge = new KSlimEdge(expandedGraph, xNode, hubNode);
				newEdge.connectNodes();
				KSlimNode yNode = new TSMNode(expandedGraph, TSMNode.Type.ECEXPANSION);
				newEdge = new KSlimEdge(expandedGraph, hubNode, yNode);
				newEdge.connectNodes();
				newEdge = new KSlimEdge(expandedGraph, xNode, yNode);
				newEdge.connectNodes();
				if (lastNode == null)
					firstNode = xNode;
				else {
					newEdge = new KSlimEdge(expandedGraph, lastNode, xNode);
					newEdge.connectNodes();
				}
				expandConstraint(childConstraint, xNode);
				lastNode = yNode;
			}
			if (lastNode != null) {
				KSlimEdge newEdge = new KSlimEdge(expandedGraph, lastNode, firstNode);
				newEdge.connectNodes();
			}
			break;
		}
	}
	
	/**
	 * Registers a given edge of the input graph. If the other endpoint of the
	 * edge is already registered, a new edge connecting the corresponding
	 * expanded nodes is created.
	 * 
	 * @param node node incident to the given edge in the expanded graph
	 * @param edge edge of the input graph
	 * @param forward if true, the edge goes out of the given node,
	 *     else it comes into the given node
	 */
	private void registerEdge(TSMNode node, TSMEdge edge, boolean forward) {
		if (forward) {
			TSMNode endPoint = incoming2NodeMap.get(edge);
			if (endPoint == null) {
				outgoing2NodeMap.put(edge, node);
			}
			else {
				KSlimEdge newEdge = new TSMEdge(expandedGraph, node, endPoint,
				        (KEdge)edge.object);
				newEdge.connectNodes();
			}
		}
		else {
			KSlimNode endPoint = outgoing2NodeMap.get(edge);
			if (endPoint == null) {
				incoming2NodeMap.put(edge, node);
			}
			else {
				KSlimEdge newEdge = new TSMEdge(expandedGraph, endPoint, node,
				        (KEdge)edge.object);
				newEdge.connectNodes();
			}
		}
	}
	
}
