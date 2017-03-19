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
 * Compaction of a tree. Seperates the tree in four quarters which will be
 * compacted according to the lowest available space in that quarter.
 */
public class HierarchicalTreeCompaction {
	
	/**
	 * Constructor that uses a given spacing.
	 * 
	 * @param spacing
	 */
	public HierarchicalTreeCompaction(final double nodeSpacing) {
		this.nodeSpacing = nodeSpacing;
	}
	
	/** Minimum space between nodes. Default: 20. */
	private double nodeSpacing = 20.0;
	/** Epsilon for comparison of double values. */
	private static final double EPSILON = 0.1;
	/**
	 * New separator for the corresponding right side if a node crosses the
	 * separator with it's width.
	 */
	private Map<Integer, Double> newSeparator = new HashMap<Integer, Double>();

	/**
	 * At first nodes will be sorted in a corresponding quarter and after that
	 * the actual compaction will take place with help of a border that shifts
	 * the nodes as much as possible into the corresponding direction.
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

		for (int i = 1; i <= largestHierarchyDepth; i++) {
			List<ElkNode> leftTopList = new ArrayList<ElkNode>();
			List<ElkNode> rightTopList = new ArrayList<ElkNode>();
			if (firstRunDepthNodeList.containsKey(i)) {
				for (ElkNode node : firstRunDepthNodeList.get(i)) {
					if (node.getX() < separator) {
						leftTopList.add(node);
						if (node.getX() + node.getWidth() > separator) {
							newSeparator.put(i, -(node.getX() + node.getWidth()));
						}
					} else {
						rightTopList.add(node);
					}
				}
				
				leftTopQuarter.put(i, leftTopList);
				rightTopList = Lists.reverse(rightTopList);
				rightTopQuarter.put(i, rightTopList);
			}

			List<ElkNode> leftBotList = new ArrayList<ElkNode>();
			List<ElkNode> rightBotList = new ArrayList<ElkNode>();
			if (secondRunDepthNodeList.containsKey(i)) {
				for (ElkNode node : secondRunDepthNodeList.get(i)) {
					if (node.getX() < separator) {
						leftBotList.add(node);
						if (node.getX() + node.getWidth() > separator) {
							newSeparator.put(i, -(node.getX() + node.getWidth()));
						}
					} else {
						rightBotList.add(node);
					}
				}
				
				leftBotList = Lists.reverse(leftBotList);
				leftBotQuarter.put(i, leftBotList);
				rightBotQuarter.put(i, rightBotList);
			}
		}

		doCompaction(leftBotQuarter, largestHierarchyDepth, separator, false);
		doCompaction(rightBotQuarter, largestHierarchyDepth, separator, true);
		doCompaction(leftTopQuarter, largestHierarchyDepth, separator, false);
		doCompaction(rightTopQuarter, largestHierarchyDepth, separator, true);
	}

	/**
	 * Compaction of a quarter. Shifts nodes with the border as much as possible
	 * to the right / left.
	 * 
	 * @param quarter
	 * @param largestHierarchyDepth
	 * @param sep
	 * @param right
	 */
	private void doCompaction(final Map<Integer, List<ElkNode>> quarter, final int largestHierarchyDepth,
			final double sep, final boolean right) {
		double separator = sep;
		if (right) {
			separator = -sep;
		}
		
		// Calculate the border where the compaction starts. The border is the
		// left- / rightmost position of a node in the complete quarter.
		double border = Double.MAX_VALUE;
		for (List<ElkNode> list : quarter.values()) {
			for (ElkNode node : list) {
				double x = node.getX();
				if (right) {
					x = -(node.getX() + node.getWidth());
				}
				border = Math.min(border, x);
			}
		}

		// Calculate space that can be compacted.
		double[] availableSpace = new double[largestHierarchyDepth];
		for (int i = 1; i <= largestHierarchyDepth; i++) {
			double previousX = border;
			if (right) {
				if (newSeparator.containsKey(i)) {
					separator = newSeparator.get(i);
				}
			}
			
			if (quarter.containsKey(i)) {
				for (ElkNode node : quarter.get(i)) {
					double x = node.getX();
					if (right) {
						x = -(node.getX() + node.getWidth());
					}
					
					double space = availableSpace[i - 1] + (x - previousX - nodeSpacing);
					space = Math.max(space, 0);
					availableSpace[i - 1] = space;
					previousX = x + node.getWidth();
				}
				
				boolean bool = previousX < separator;
				if (right) {
					bool = previousX > separator;
				}
				if (bool) {
					double space = availableSpace[i - 1] + (separator - previousX - nodeSpacing);
					space = Math.max(space, 0);
					availableSpace[i - 1] = space;
				}
			}
			separator = sep;
		}

		// Calculate the lowest available space in the quarter.
		double lowestAvailableSpace = Double.MAX_VALUE;
		for (int i = 1; i <= largestHierarchyDepth; i++) {
			if (quarter.containsKey(i)) {
				lowestAvailableSpace = Math.min(lowestAvailableSpace, availableSpace[i - 1]);
			}
		}

		// Actual compaction.
		if (lowestAvailableSpace > nodeSpacing) {
			for (int i = 1; i <= largestHierarchyDepth; i++) {
				if (quarter.containsKey(i)) {
					double spaceUsed = 0;
					double previousX = border;
					List<ElkNode> shiftedNodes = new ArrayList<ElkNode>();
					
					for (ElkNode node : quarter.get(i)) {
						if (lowestAvailableSpace >= spaceUsed - EPSILON) {
							double x = node.getX();
							if (right) {
								x = -(node.getX() + node.getWidth());
							}
							
							double shift = x - previousX - nodeSpacing;
							shift = Math.max(shift, 0);
							if (spaceUsed + shift > lowestAvailableSpace) {
								shift = lowestAvailableSpace - spaceUsed;
								spaceUsed = lowestAvailableSpace;
								
								for (ElkNode n : shiftedNodes) {
									if (right) {
										n.setX(n.getX() - shift);
									} else {
										n.setX(n.getX() + shift);
									}
								}
							} else {
								spaceUsed += shift;
								for (ElkNode n : shiftedNodes) {
									if (right) {
										n.setX(n.getX() - shift);
									} else {
										n.setX(n.getX() + shift);
									}
								}
								
								previousX = x + node.getWidth();
								shiftedNodes.add(node);
							}
						}
					}

					// Add the rest of the available space if it is not
					// completely used yet.
					if (spaceUsed < lowestAvailableSpace) {
						double shift = lowestAvailableSpace - spaceUsed;
						for (ElkNode n : shiftedNodes) {
							if (right) {
								n.setX(n.getX() - shift);
							} else {
								n.setX(n.getX() + shift);
							}
						}
					}
				}
			}
		}
	}
}
