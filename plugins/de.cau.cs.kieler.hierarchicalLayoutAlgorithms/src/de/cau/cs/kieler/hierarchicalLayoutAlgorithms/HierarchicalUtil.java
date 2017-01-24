package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KNode;

public class HierarchicalUtil {

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
	
	public static KNode findRoot(KNode graph) {
		for (KNode child : graph.getChildren()) {
			if (child.getIncomingEdges().isEmpty()) {
				return child;
			}
		}
		return null;
	}
	
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
}
