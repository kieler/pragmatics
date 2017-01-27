package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KNode;

public class HierarchicalUtil {

	/**
	 * Computes a List of nodes that are the neighbours with outgoing edges.
	 * @param node
	 * @return List of neighbours with outgoing edges.
	 */
	public static List<KNode> getSuccessor(KNode node) {
		List<KNode> children = new ArrayList<>();
		for (KEdge outgoingEdge : node.getOutgoingEdges()) {
			KNode target = outgoingEdge.getTarget();
			if (!node.getChildren().contains(target)) {
				children.add(target);
			}
		}
		return children;
	}
	
	/**
	 * Computes the root node of the graph.
	 * @param graph
	 * @return root node of graph.
	 */
	public static KNode findRoot(KNode graph) {
		for (KNode child : graph.getChildren()) {
			if (child.getIncomingEdges().isEmpty()) {
				return child;
			}
		}
		return null;
	}
	
	/**
	 * Computes the number of Leafs that a node has.
	 * @param node
	 * @return number of leafs.
	 */
	public static int getNumberOfLeafs(KNode node) {
		int leafs = 0;
		if (HierarchicalUtil.getSuccessor(node).isEmpty()) {
			return 1;
		} else {
			for (KNode child : HierarchicalUtil.getSuccessor(node)) {
				leafs += getNumberOfLeafs(child);
			}
		}
		return leafs;
	}
	
	/**
	 * Computes a List of Edges that are the Hierarchical edges of the Graph.
	 * @param graph
	 * @return List of Hierarchical Edges.
	 */
	public static List<KEdge> getHierarchicalEdges(KNode graph) {
		List<KEdge> edges = new ArrayList<KEdge>();
		for (KNode node : graph.getChildren()) {
			for (KEdge edge : node.getOutgoingEdges()) {
				if (graph.getChildren().contains(edge.getTarget())) {
					edges.add(edge);
				}
			}
		}
		return edges;
	}
}
