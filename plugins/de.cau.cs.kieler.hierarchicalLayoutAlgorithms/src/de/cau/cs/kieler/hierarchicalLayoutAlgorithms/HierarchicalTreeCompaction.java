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

import org.eclipse.elk.alg.radial.RadialUtil;
import org.eclipse.elk.graph.ElkNode;

import com.google.common.collect.Lists;

/**
 * Compaction of a tree. Seperates the tree in four quarters which will be
 * compacted according to the lowest available space in that quarter. This
 * compaction only works for connected graphs.
 */
public class HierarchicalTreeCompaction {
	/** List of nodes of the upward tree. */
	private List<ElkNode> firstRunList = new ArrayList<ElkNode>();
	/** List of nodes of the downward tree. */
	private List<ElkNode> secondRunList = new ArrayList<ElkNode>();
	/** Boolean if values need to be calculated or not. */
	private boolean calculateValues = true;
	/** Minimum space between nodes. */
	private double nodeSpacing;
	/** Epsilon for comparison of double values. */
	private static final double EPSILON = 0.1;
	/**
	 * New separator for the top right side if a node crosses the separator with
	 * it's width.
	 */
	private Map<Integer, Double> newSeparatorTop = new HashMap<Integer, Double>();
	/**
	 * New separator for the bottom right side if a node crosses the separator
	 * with it's width.
	 */
	private Map<Integer, Double> newSeparatorBot = new HashMap<Integer, Double>();
	/**
	 * Maps the hierarchy depth of the upward tree as integer to a list of
	 * elknodes in the corresponding depth, starting with depth of one.
	 */
	private Map<Integer, List<ElkNode>> firstRunDepthNodeList = new HashMap<Integer, List<ElkNode>>();
	/**
	 * Maps the hierarchy depth of the downward tree as integer to a list of
	 * elknodes in the corresponding depth, starting with depth of one.
	 */
	private Map<Integer, List<ElkNode>> secondRunDepthNodeList = new HashMap<Integer, List<ElkNode>>();
	/** The separator is the half of the graph width. */
	private double separator;
	/** Integer value with the largest hierarchy depth. */
	private int largestHierarchyDepth;
	/**
	 * Map of a hierarchy depth with a list of all nodes in that depth of the
	 * left top quarter.
	 */
	private Map<Integer, List<ElkNode>> leftTopQuarter = new HashMap<Integer, List<ElkNode>>();
	/**
	 * Map of a hierarchy depth with a list of all nodes in that depth of the
	 * right top quarter.
	 */
	private Map<Integer, List<ElkNode>> rightTopQuarter = new HashMap<Integer, List<ElkNode>>();
	/**
	 * Map of a hierarchy depth with a list of all nodes in that depth of the
	 * left bottom quarter.
	 */
	private Map<Integer, List<ElkNode>> leftBotQuarter = new HashMap<Integer, List<ElkNode>>();
	/**
	 * Map of a hierarchy depth with a list of all nodes in that depth of the
	 * right bottom quarter.
	 */
	private Map<Integer, List<ElkNode>> rightBotQuarter = new HashMap<Integer, List<ElkNode>>();
	/** Root of the tree. */
	private ElkNode root;

	/**
	 * Constructor for one dimensional trees.
	 */
	public HierarchicalTreeCompaction() {
		// Nothing to do in this case.
	}

	/**
	 * Constructor for two dimensional trees.
	 * 
	 * @param upwardRunList
	 * @param downwardRunList
	 */
	public HierarchicalTreeCompaction(final List<ElkNode> upwardRunList, final List<ElkNode> downwardRunList) {
		firstRunList = upwardRunList;
		secondRunList = downwardRunList;
	}

	/**
	 * Constructor for one or two dimensional trees that holds all the values
	 * needed for the compaction.
	 * 
	 * @param firstRunDepthNodeList
	 *            Maps the hierarchy depth of the upward tree as integer to a
	 *            list of elknodes in the corresponding depth, starting with
	 *            depth of one.
	 * @param secondRunDepthNodeList
	 *            Maps the hierarchy depth of the downward tree as integer to a
	 *            list of elknodes in the corresponding depth, starting with
	 *            depth of one.
	 * @param separator
	 *            The separator is the half of the graph width.
	 * @param largestHierarchyDepth
	 *            Integer value with the largest hierarchy depth.
	 */
	public HierarchicalTreeCompaction(final Map<Integer, List<ElkNode>> firstRunDepthNodeList,
			final Map<Integer, List<ElkNode>> secondRunDepthNodeList, final double separator,
			final int largestHierarchyDepth) {
		this.firstRunDepthNodeList = firstRunDepthNodeList;
		this.secondRunDepthNodeList = secondRunDepthNodeList;
		this.separator = separator;
		this.largestHierarchyDepth = largestHierarchyDepth;
		calculateValues = false;
	}

	/**
	 * At first nodes will be put in a corresponding quarter and after that the
	 * actual compaction will take place with help of a border that shifts the
	 * nodes as much as possible into the corresponding direction.
	 * 
	 * @param graph
	 */
	public void compact(final ElkNode graph) {
		nodeSpacing = graph.getProperty(HierarchicalTreeOptions.NODE_SPACING);
		root = RadialUtil.findRoot(graph);
		if (calculateValues) {
			if (secondRunList.isEmpty()) {
				secondRunList = graph.getChildren();
			}
			calculateValues(graph);
		}

		calculateQuarters();
		doCompaction(leftBotQuarter, false, newSeparatorBot);
		doCompaction(rightBotQuarter, true, newSeparatorBot);
		doCompaction(leftTopQuarter, false, newSeparatorTop);
		doCompaction(rightTopQuarter, true, newSeparatorTop);
	}

	/**
	 * First step of calculating missing values.
	 */
	private void calculateValues(final ElkNode graph) {
		calculateValues(firstRunList, firstRunDepthNodeList);
		calculateValues(secondRunList, secondRunDepthNodeList);
		separator = graph.getWidth() / 2;
	}

	/**
	 * Actual calculation of missing values.
	 * 
	 * @param runList
	 * @param depthNodeList
	 */
	private void calculateValues(final List<ElkNode> runList, final Map<Integer, List<ElkNode>> depthNodeList) {
		// Set<ElkNode> graphNodes = new HashSet<ElkNode>(runList);
		for (ElkNode node : runList) {
			int depth = HierarchicalUtil.getDepths(node, root);
			largestHierarchyDepth = Math.max(depth, largestHierarchyDepth);
			List<ElkNode> list;
			if (depthNodeList.containsKey(depth)) {
				list = depthNodeList.get(depth);
			} else {
				list = new ArrayList<ElkNode>();
			}
			list.add(node);
			depthNodeList.put(depth, list);
		}
	}

	/**
	 * Puts nodes in the corresponding quarter.
	 */
	private void calculateQuarters() {
		for (int i = 1; i <= largestHierarchyDepth; i++) {
			List<ElkNode> leftTopList = new ArrayList<ElkNode>();
			List<ElkNode> rightTopList = new ArrayList<ElkNode>();
			if (firstRunDepthNodeList.containsKey(i)) {
				for (ElkNode node : firstRunDepthNodeList.get(i)) {
					if (node.getX() < separator) {
						leftTopList.add(node);
						if (node.getX() + node.getWidth() > separator) {
							newSeparatorTop.put(i, -(node.getX() + node.getWidth()));
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
							newSeparatorBot.put(i, -(node.getX() + node.getWidth()));
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
	private void doCompaction(final Map<Integer, List<ElkNode>> quarter, final boolean right,
			final Map<Integer, Double> newSeparator) {
		double sep = separator;
		if (right) {
			sep = -separator;
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
					sep = newSeparator.get(i);
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

				boolean bool = previousX < sep;
				if (bool) {
					double space = availableSpace[i - 1] + (sep - previousX - nodeSpacing);
					space = Math.max(space, 0);
					availableSpace[i - 1] = space;
				}
			}
			if (right) {
				sep = -separator;
			} else {
				sep = separator;
			}
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
