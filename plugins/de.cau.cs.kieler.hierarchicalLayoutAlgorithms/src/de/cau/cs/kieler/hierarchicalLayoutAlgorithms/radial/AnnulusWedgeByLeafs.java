package de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial;

import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalUtil;

public class AnnulusWedgeByLeafs implements IAnnulusWedgeCriteria{

	@Override
	public double calculateWedgeSpace(ElkNode node) {
		return HierarchicalUtil.getNumberOfLeafs(node);
	}
	
	

}
