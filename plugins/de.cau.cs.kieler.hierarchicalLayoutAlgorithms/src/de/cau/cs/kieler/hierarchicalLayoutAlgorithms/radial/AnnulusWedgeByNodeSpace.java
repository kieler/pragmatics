package de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial;

import java.util.List;

import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalUtil;

public class AnnulusWedgeByNodeSpace implements IAnnulusWedgeCriteria{

	@Override
	public double calculateWedgeSpace(ElkNode node) {
		List<ElkNode> successors = HierarchicalUtil.getSuccessor(node);
		double nodeSize = Math.sqrt(Math.pow(node.getHeight(), 2) + Math.pow(node.getWidth(), 2));
		double childSpace = 0;
		for (ElkNode child : successors) {
			childSpace += calculateWedgeSpace(child);
		}
		if (successors.isEmpty()) {
		}
		return Math.max(childSpace, nodeSize);
	}

}
