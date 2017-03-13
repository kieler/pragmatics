package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.alg.layered.p2layers.LongestPathLayerer;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

public class HierarchicalTreeCompaction {

	public void compact(List<ElkNode> firstRunList, List<ElkNode> secondRunList, double seperator,
			Map<ElkNode, Integer> nodeHierarchyDepth) {

		List<ElkNode> leftTopQuarter = new ArrayList<ElkNode>();
		List<ElkNode> rightTopQuarter = new ArrayList<ElkNode>();
		List<ElkNode> leftBotQuarter = new ArrayList<ElkNode>();
		List<ElkNode> rightBotQuarter = new ArrayList<ElkNode>();

		for (ElkNode node : firstRunList) {
			if (node.getX() < seperator) {
				leftTopQuarter.add(node);
			} else {
				rightTopQuarter.add(node);
			}
		}
		for (ElkNode node : secondRunList) {
			if (node.getX() < seperator) {
				leftBotQuarter.add(node);
			} else {
				rightBotQuarter.add(node);
			}
		}
		// TODO Put correct nodes in blocks / add edges between nodes?
		for (ElkNode node : leftTopQuarter) {
			nodeHierarchyDepth.get(node);
//			ElkGraphUtil.createSimpleEdge(source, target);
		}

		// TODO Self implementation maybe
		LongestPathLayerer longestPath = new LongestPathLayerer();
	}

}
