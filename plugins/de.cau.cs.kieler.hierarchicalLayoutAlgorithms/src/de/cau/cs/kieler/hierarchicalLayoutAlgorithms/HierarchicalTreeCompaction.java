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

	/**  */
	private static final double SPACING = 20.0;
	private static final double EPSILON = 0.1;

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

		// TODO add node that crosses the seperation border to the rightQuarters
		// as well.
		for (int i = 1; i <= largestHierarchyDepth; i++) {
			List<ElkNode> leftTopList = new ArrayList<ElkNode>();
			List<ElkNode> rightTopList = new ArrayList<ElkNode>();
			if (firstRunDepthNodeList.containsKey(i)) {
				for (ElkNode node : firstRunDepthNodeList.get(i)) {
					if (node.getX() < seperator) {
						leftTopList.add(node);
					} else {
						rightTopList.add(node);
					}
				}
				leftTopQuarter.put(i, leftTopList);
				rightTopQuarter.put(i, rightTopList);
			}
			
			List<ElkNode> leftBotList = new ArrayList<ElkNode>();
			List<ElkNode> rightBotList = new ArrayList<ElkNode>();
			if (secondRunDepthNodeList.containsKey(i)) {
				for (ElkNode node : secondRunDepthNodeList.get(i)) {
					if (node.getX() < seperator) {
						leftBotList.add(node);
					} else {
						rightBotList.add(node);
					}
				}
				leftBotQuarter.put(i, leftBotList);
				rightBotQuarter.put(i, rightBotList);
			}
		}
		
		doCompaction(leftBotQuarter, largestHierarchyDepth, false);
//		 doCompaction(rightBotQuarter, largestHierarchyDepth, true);
		doCompaction(leftTopQuarter, largestHierarchyDepth, false);
//		 doCompaction(rightTopQuarter, largestHierarchyDepth, true);
	}

	private void doCompaction(final Map<Integer, List<ElkNode>> quarter, final int largestHierarchyDepth,
			final boolean right) {
		// TODO right side compaction
		// Calculate space that can be compacted.
		double border = Double.MAX_VALUE;
		for (List<ElkNode> list : quarter.values()) {
			for (ElkNode node : list) {
				double x = node.getX();
//				System.out.println("X: " + x);
//				System.out.println(node);
				if (right) {
					x = -node.getX() + node.getWidth();
				}
				border = Math.min(border, x);
			}
		}
//		System.out.println(border);

		// System.out.println("border: " + border);
		double[] availableSpace = new double[largestHierarchyDepth];
		for (int i = 1; i <= largestHierarchyDepth; i++) {
			double previousX = border;
			// System.out.println("i: " + i);
			if (quarter.containsKey(i)) {
				for (ElkNode node : quarter.get(i)) {
					double x = node.getX();
					if (right) {
						x = -node.getX();
					}
					// System.out.println(node.getX());
					double space = availableSpace[i - 1] + (x - previousX - SPACING);
					space = Math.max(space, 0);
					// System.out.println("width: " + width);
					availableSpace[i - 1] = space;
					previousX = x + node.getWidth();
				}
			}
		}

		double lowestAvailableSpace = Double.MAX_VALUE;
		for (int i = 1; i <= largestHierarchyDepth; i++) {
			if (quarter.containsKey(i)) {
				lowestAvailableSpace = Math.min(lowestAvailableSpace, availableSpace[i - 1]);
			}
		}
		// System.out.println(lowestAvailableWidth);

		// Actual compaction.
		double spaceUsed = 0;
		for (int i = 1; i <= largestHierarchyDepth; i++) {
			double previousX = border;
			List<ElkNode> shiftedNodes = new ArrayList<ElkNode>();
			if (quarter.containsKey(i)) {
				for (ElkNode node : quarter.get(i)) {
					// System.out.println(node.getX());
					if (lowestAvailableSpace >= spaceUsed - EPSILON) {
						double x = node.getX();
						if (right) {
							x = -node.getX();
						}
						double shift = x - previousX - SPACING;
						shift = Math.max(shift, 0);
						if (spaceUsed + shift > lowestAvailableSpace) {
							shift = lowestAvailableSpace - spaceUsed;
							spaceUsed = lowestAvailableSpace;
							for (ElkNode n : shiftedNodes) {
								n.setX(n.getX() + shift);
							}
						} else {
							spaceUsed += shift;
							for (ElkNode n : shiftedNodes) {
								n.setX(n.getX() + shift);
							}
							// System.out.println("width: " + width);
							availableSpace[i - 1] = shift;
							previousX = node.getX() + node.getWidth();
							shiftedNodes.add(node);
						}
					}
				}
			}
		}
	}

}
