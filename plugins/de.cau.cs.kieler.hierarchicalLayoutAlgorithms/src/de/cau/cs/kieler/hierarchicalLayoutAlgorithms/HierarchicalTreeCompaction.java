package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.graph.ElkNode;

/**
 * 
 */
public class HierarchicalTreeCompaction {

	/**
	 * 
	 * @param firstRunList
	 * @param secondRunList
	 * @param seperator
	 * @param nodeHierarchyDepth
	 */
	public void compact(final Map<Integer, List<ElkNode>> firstRunDepthNodeList,
			final Map<Integer, List<ElkNode>> secondRunDepthNodeList, final double seperator,
			final Map<ElkNode, Integer> nodeHierarchyDepth, final int largestHierarchyDepth) {

		Map<Integer, List<ElkNode>> leftTopQuarter = new HashMap<Integer, List<ElkNode>>();
		Map<Integer, List<ElkNode>> rightTopQuarter = new HashMap<Integer, List<ElkNode>>();
		Map<Integer, List<ElkNode>> leftBotQuarter = new HashMap<Integer, List<ElkNode>>();
		Map<Integer, List<ElkNode>> rightBotQuarter = new HashMap<Integer, List<ElkNode>>();

		for (int i = 1; i <= largestHierarchyDepth; i++) {
			List<ElkNode> leftTempList = new ArrayList<ElkNode>();
			List<ElkNode> rightTempList = new ArrayList<ElkNode>();
			if (firstRunDepthNodeList.containsKey(i)) {
				for (ElkNode node : firstRunDepthNodeList.get(i)) {
					if (node.getX() < seperator) {
						leftTempList.add(node);
					} else {
						rightTempList.add(node);
					}
				}
				leftTopQuarter.put(i, leftTempList);
				rightTopQuarter.put(i, rightTempList);
			}
			leftTempList.clear();
			rightTempList.clear();
			if (secondRunDepthNodeList.containsKey(i)) {
				for (ElkNode node : secondRunDepthNodeList.get(i)) {
					if (node.getX() < seperator) {
						leftTempList.add(node);
					} else {
						rightTempList.add(node);
					}
				}
				leftBotQuarter.put(i, leftTempList);
				rightBotQuarter.put(i, rightTempList);
			}
		}

		// TODO calculate space that can be compacted
		// TODO actual compaction
	}

}
