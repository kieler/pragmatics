/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.graph.ElkNode;

import com.google.common.collect.Lists;

/**
 * 
 */
public class HierarchicalTreeCompaction {
	/** Minimum space between nodes. */
	private static final double SPACING = 20.0;
	/** Epsilon for comparison of double values. */
	private static final double EPSILON = 0.1;

	/**
	 * 
	 * @param firstRunList
	 * @param secondRunList
	 * @param seperator
	 * @param nodeHierarchyDepth
	 */
	public void compact(final Map<Integer, List<ElkNode>> firstRunDepthNodeList,
			final Map<Integer, List<ElkNode>> secondRunDepthNodeList, final double separator,
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
					if (node.getX() < separator) {
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
					if (node.getX() < separator) {
						leftBotList.add(node);
					} else {
						rightBotList.add(node);
					}
				}
				leftBotList = Lists.reverse(leftBotList);
				rightBotList = Lists.reverse(rightBotList);
				leftBotQuarter.put(i, leftBotList);
				rightBotQuarter.put(i, rightBotList);
			}
		}
		System.out.println(leftBotQuarter);
		System.out.println(rightBotQuarter);

		doCompaction(leftBotQuarter, largestHierarchyDepth, separator, false);
		doCompaction(rightBotQuarter, largestHierarchyDepth, separator, true);
		doCompaction(leftTopQuarter, largestHierarchyDepth, separator, false);
		doCompaction(rightTopQuarter, largestHierarchyDepth, separator, true);
	}

	// private void doCompaction(final Map<Integer, List<ElkNode>> quarter,
	// final int largestHierarchyDepth,
	// final boolean right) {
	// // TODO right side compaction
	// // Calculate space that can be compacted.
	// double border = Double.MAX_VALUE;
	// for (List<ElkNode> list : quarter.values()) {
	// for (ElkNode node : list) {
	// double x = node.getX();
	// // System.out.println("X: " + x);
	// // System.out.println(node);
	// if (right) {
	// x = -node.getX() + node.getWidth();
	// }
	// border = Math.min(border, x);
	// }
	// }
	// // System.out.println(border);
	//
	// // System.out.println("border: " + border);
	// double[] availableSpace = new double[largestHierarchyDepth];
	// for (int i = 1; i <= largestHierarchyDepth; i++) {
	// double previousX = border;
	// // System.out.println("i: " + i);
	// if (quarter.containsKey(i)) {
	// for (ElkNode node : quarter.get(i)) {
	// double x = node.getX();
	// if (right) {
	// x = -node.getX();
	// }
	// // System.out.println(node.getX());
	// double space = availableSpace[i - 1] + (x - previousX - SPACING);
	// space = Math.max(space, 0);
	// // System.out.println("width: " + width);
	// availableSpace[i - 1] = space;
	// previousX = x + node.getWidth();
	// }
	// }
	// }
	//
	// double lowestAvailableSpace = Double.MAX_VALUE;
	// for (int i = 1; i <= largestHierarchyDepth; i++) {
	// if (quarter.containsKey(i)) {
	// lowestAvailableSpace = Math.min(lowestAvailableSpace, availableSpace[i -
	// 1]);
	// }
	// }
	// // System.out.println(lowestAvailableWidth);
	//
	// // Actual compaction.
	// double spaceUsed = 0;
	// for (int i = 1; i <= largestHierarchyDepth; i++) {
	// double previousX = border;
	// List<ElkNode> shiftedNodes = new ArrayList<ElkNode>();
	// if (quarter.containsKey(i)) {
	// for (ElkNode node : quarter.get(i)) {
	// // System.out.println(node.getX());
	// if (lowestAvailableSpace >= spaceUsed - EPSILON) {
	// double x = node.getX();
	// if (right) {
	// x = -node.getX();
	// }
	// double shift = x - previousX - SPACING;
	// shift = Math.max(shift, 0);
	// if (spaceUsed + shift > lowestAvailableSpace) {
	// shift = lowestAvailableSpace - spaceUsed;
	// spaceUsed = lowestAvailableSpace;
	// for (ElkNode n : shiftedNodes) {
	// n.setX(n.getX() + shift);
	// }
	// } else {
	// spaceUsed += shift;
	// for (ElkNode n : shiftedNodes) {
	// n.setX(n.getX() + shift);
	// }
	// // System.out.println("width: " + width);
	// availableSpace[i - 1] = shift;
	// previousX = node.getX() + node.getWidth();
	// shiftedNodes.add(node);
	// }
	// }
	// }
	// }
	// }
	// }

	private void doCompaction(final Map<Integer, List<ElkNode>> quarter, final int largestHierarchyDepth,
			final double separator, final boolean right) {
		// TODO right side compaction
		// Calculate space that can be compacted.
		double border = Double.MAX_VALUE;
		for (List<ElkNode> list : quarter.values()) {
			for (ElkNode node : list) {
				double x = node.getX();
				// System.out.println("X: " + x);
				// System.out.println(node);
				if (right) {
					x = -(node.getX() + node.getWidth());
				}
				border = Math.min(border, x);
			}
		}
		System.out.println("Border: " + border);

		// System.out.println("border: " + border);
		double[] availableSpace = new double[largestHierarchyDepth];
		for (int i = 1; i <= largestHierarchyDepth; i++) {
			double previousX = border;
			// if (right) {
			// previousX = 0;
			// }
			// System.out.println("i: " + i);
			if (quarter.containsKey(i)) {
				for (ElkNode node : quarter.get(i)) {
					double x = node.getX();
					if (right) {
						x = -(node.getX() + node.getWidth());
					}
					// System.out.println(node.getX());
					double space = availableSpace[i - 1] + (x - previousX - SPACING);
					space = Math.max(space, 0);
					// System.out.println("width: " + width);
					availableSpace[i - 1] = space;
					previousX = x + node.getWidth();
					// if (right) {
					// previousX = x - node.getWidth();
					// }
				}
				if (previousX < separator) {
					double space = availableSpace[i - 1] + (separator - previousX - SPACING);
					space = Math.max(space, 0);
					availableSpace[i - 1] = availableSpace[i - 1] + space;
				}
			}
		}

		double lowestAvailableSpace = Double.MAX_VALUE;
		for (int i = 1; i <= largestHierarchyDepth; i++) {
			if (quarter.containsKey(i)) {
				System.out.println(availableSpace[i - 1]);
				lowestAvailableSpace = Math.min(lowestAvailableSpace, availableSpace[i - 1]);
			}
		}

		// Actual compaction.
		if (lowestAvailableSpace > SPACING) {
			for (int i = 1; i <= largestHierarchyDepth; i++) {
				System.out.println("Available: " + lowestAvailableSpace);
				double spaceUsed = 0;
				double previousX = border;
				List<ElkNode> shiftedNodes = new ArrayList<ElkNode>();
				if (quarter.containsKey(i)) {
					for (ElkNode node : quarter.get(i)) {
						// System.out.println(node.getX());
						System.out.println("Used: " + spaceUsed);
						if (lowestAvailableSpace >= spaceUsed - EPSILON) {
							double x = node.getX();
							if (right) {
								x = -(node.getX() + node.getWidth());
							}
							double shift = x - previousX - SPACING;
							shift = Math.max(shift, 0);
							if (spaceUsed + shift > lowestAvailableSpace) {
								shift = lowestAvailableSpace - spaceUsed;
								spaceUsed = lowestAvailableSpace;
								for (ElkNode n : shiftedNodes) {
									if (right) {
										n.setX(n.getX() - shift);
									} else {
										// System.out.println("Before: " +
										// n.getX());
										n.setX(n.getX() + shift);
										// System.out.println("After: " +
										// n.getX());
									}
								}
							} else {
								spaceUsed += shift;
								for (ElkNode n : shiftedNodes) {
									if (right) {
										n.setX(n.getX() - shift);
									} else {
										// System.out.println("Before: " +
										// n.getX());
										n.setX(n.getX() + shift);
										// System.out.println("After: " +
										// n.getX());
									}
								}
								// System.out.println("width: " + width);
								availableSpace[i - 1] = shift;
								previousX = node.getX() + node.getWidth();
								// if (right) {
								// previousX = node.getX();
								// }
								shiftedNodes.add(node);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 */
	class Node {
		private double newX;
		private double width;
		private double oldX;
		private ElkNode originalNode;

		/**
		 * 
		 * 
		 * @param newX
		 * @param width
		 * @param oldX
		 * @param originalNode
		 */
		Node(final double newX, final double width, final double oldX, final ElkNode originalNode) {
			this.newX = newX;
			this.width = width;
			this.oldX = oldX;
			this.originalNode = originalNode;
		}

		/**
		 * 
		 * @return
		 */
		public double getNewX() {
			return newX;
		}

		/**
		 * 
		 * @param newX
		 */
		public void setNewX(final double newX) {
			this.newX = newX;
		}

		/**
		 * 
		 * @return
		 */
		public double getWidth() {
			return width;
		}

		/**
		 * 
		 * @param width
		 */
		public void setWidth(final double width) {
			this.width = width;
		}

		/**
		 * 
		 * @return
		 */
		public double getOldX() {
			return oldX;
		}

		/**
		 * 
		 * @param oldX
		 */
		public void setOldX(final double oldX) {
			this.oldX = oldX;
		}

		/**
		 * 
		 * @return
		 */
		public ElkNode getOriginalNode() {
			return originalNode;
		}

		/**
		 * 
		 * @param originalNode
		 */
		public void setOriginalNode(final ElkNode originalNode) {
			this.originalNode = originalNode;
		}
	}

}
