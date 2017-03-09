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
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.alg.layered.LayeredLayoutProvider;
import org.eclipse.elk.alg.layered.options.FixedAlignment;
import org.eclipse.elk.alg.layered.options.GreedySwitchType;
import org.eclipse.elk.alg.layered.options.LayeredOptions;
import org.eclipse.elk.alg.radial.RadialUtil;
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.Alignment;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import com.google.common.collect.Lists;

/**
 * Layout Provider for a hierarchical graph that uses two runs of elk layered to
 * compute a layout of a tree with two directions.
 */
public class HierarchicalTreeLayoutProvider extends AbstractLayoutProvider {
	/** List of all nodes in the graph. */
	private List<ElkNode> children;
	/** List of all edges that are relevant for this layout algorithm. */
	private HashSet<ElkEdge> edges;
	/** Root node of the graph. */
	private ElkNode root;
	/** Nodes that are in the hierarchical layer after the root node. */
	private List<ElkNode> secondHierarchyNodes;
	/** Value where the secondHierarchyNodes will be separated. */
	private int treeSeperator;
	/** Depth of the hierarchy nodes. */
	private int hierarchyDepth = 1;
	/** Map of nodes with their corresponding depth. */
	private Map<ElkNode, Integer> nodeHierarchyDepth;
	/** Comparator according to y-coordinates. */
	private static final Comparator<ElkNode> COMPY = (n1, n2) -> {
		if (n1.getY() < n2.getY()) {
			return -1;
		} else if (n1.getY() == n2.getY()) {
			return 0;
		} else {
			return 1;
		}
	};
	/** Constant that starts the polar sorting at the bottom of a circle. */
	private static final double BOTTOM_CIRCLE = 1.5 * Math.PI;
	/** Constant that starts the polar sorting at the top of a circle. */
	private static final double TOP_CIRCLE = 0.5 * Math.PI;
	/**
	 * Offset of the nodes for the new created tree, such that they stay in the
	 * correct order.
	 */
	private static final int OFFSET = 100;

	@Override
	public void layout(final ElkNode layoutGraph, final IElkProgressMonitor progressMonitor) {
		nodeHierarchyDepth = new HashMap<ElkNode, Integer>();
		children = layoutGraph.getChildren();
		edges = HierarchicalUtil.getHierarchicalEdges(layoutGraph);
		root = RadialUtil.findRoot(layoutGraph);
		secondHierarchyNodes = RadialUtil.getSuccessors(root);

		if (secondHierarchyNodes.size() > 0) {
			layeredTreeLayout(layoutGraph);

			HierarchicalEdgeRouting.drawHierarchicalEdges(root);
		}

	}

	/**
	 * Method for the layout computation that uses two runs of elk layered with
	 * specific properties, such that the result is a tree layout. The two runs
	 * are with different directions and the resulting layout combines the two
	 * directions such that the root node is in the middle of the diagram.
	 * 
	 * @param layoutGraph
	 */
	private void layeredTreeLayout(final ElkNode layoutGraph) {
		// Compute the two Lists of nodes for the two runs of Elk-Layered with a
		// Tree representation.
		treeSeperator = secondHierarchyNodes.size() / 2;
		// List of nodes that are used in the particular run
		List<ElkNode> firstRunList = new ArrayList<ElkNode>();
		List<ElkNode> secondRunList = new ArrayList<ElkNode>();
		// TODO Better distribution of nodes in the two runs for a similar width
		// in both runs.

		List<ElkNode> sortedYNodes = RadialUtil.getSortedHierarchicalNodes(root, COMPY);
		int i = 0;
		for (ElkNode node : sortedYNodes) {
			if (i < treeSeperator) {
				firstRunList.add(node);
			} else {
				secondRunList.add(node);
			}
			i++;
		}

		Comparator<ElkNode> polarComp = RadialUtil.createPolarComparator(root, BOTTOM_CIRCLE);
		List<ElkNode> sortedPolarNodes = RadialUtil.getSortedHierarchicalNodes(root, polarComp);
		List<ElkNode> tempListF = new ArrayList<ElkNode>();
		List<ElkNode> tempListS = new ArrayList<ElkNode>();
		for (ElkNode node : sortedPolarNodes) {
			if (firstRunList.contains(node)) {
				tempListF.add(node);
			}
		}

		polarComp = RadialUtil.createPolarComparator(root, TOP_CIRCLE);
		sortedPolarNodes = RadialUtil.getSortedHierarchicalNodes(root, polarComp);
		for (ElkNode node : sortedPolarNodes) {
			if (secondRunList.contains(node)) {
				tempListS.add(node);
			}
		}

		List<ElkNode> tempList = new ArrayList<ElkNode>();
		for (ElkNode node : tempListF) {
			tempList.add(node);
			nodeHierarchyDepth.put(node, 1);
			buildNodeList(node, tempList, BOTTOM_CIRCLE, 2);
		}
		firstRunList.clear();
		firstRunList.addAll(tempList);

		tempList.clear();
		for (ElkNode node : tempListS) {
			tempList.add(node);
			nodeHierarchyDepth.put(node, 1);
			buildNodeList(node, tempList, TOP_CIRCLE, 2);
		}
		secondRunList.clear();
		tempList = Lists.reverse(tempList);
		secondRunList.addAll(tempList);

		// Offsets for the Elk Layered Position option. Used for the correct
		// order of nodes when displayed.
		int[] firstOffset = new int[hierarchyDepth];
		int[] secondOffset = new int[hierarchyDepth];

		// First run for upward tree
		// Maps the original node to the temporary node
		// TODO node spacing according to width
		Map<ElkNode, ElkNode> firstRunMap = new HashMap<ElkNode, ElkNode>();
		ElkNode firstRun = createTree(firstRunList, firstRunMap, firstOffset);
		LayeredLayoutProvider layered = new LayeredLayoutProvider();
		configureTreeLayout(firstRun, Direction.UP);
		BasicProgressMonitor firstRunMonitor = new BasicProgressMonitor();
		layered.layout(firstRun, firstRunMonitor);

		// Second run for downward tree
		Map<ElkNode, ElkNode> secondRunMap = new HashMap<ElkNode, ElkNode>();
		ElkNode secondRun = createTree(secondRunList, secondRunMap, secondOffset);
		configureTreeLayout(secondRun, Direction.DOWN);
		BasicProgressMonitor secondRunMonitor = new BasicProgressMonitor();
		layered.layout(secondRun, secondRunMonitor);

		// Keeps track of actual height and width of the two runs by computing
		// min and max x- and y-values.
		double minFirstY = Double.MAX_VALUE;
		double minFirstX = Double.MAX_VALUE;
		double maxFirstX = Double.NEGATIVE_INFINITY;
		double minSecondY = Double.MAX_VALUE;
		double minSecondX = Double.MAX_VALUE;
		double maxSecondX = Double.NEGATIVE_INFINITY;
		for (ElkNode node : children) {
			if (firstRunMap.containsKey(node)) {
				node = firstRunMap.get(node);
				minFirstY = Math.min(minFirstY, node.getY());
				minFirstX = Math.min(minFirstX, node.getX());
				maxFirstX = Math.max(maxFirstX, node.getX() + node.getWidth());
			} else {
				node = secondRunMap.get(node);
				minSecondY = Math.min(minSecondY, node.getY());
				minSecondX = Math.min(minSecondX, node.getX());
				maxSecondX = Math.max(maxSecondX, node.getX() + node.getWidth());
			}
		}

		double firstWidth = maxFirstX - minFirstX;
		double secondWidth = maxSecondX - minSecondX;

		// Compute positions of original nodes from first run
		double xDisplacement = -minFirstX;
		if (firstWidth < secondWidth) {
			xDisplacement += (secondWidth - firstWidth) / 2;
			layoutGraph.setWidth(secondRun.getWidth());
		}

		for (ElkNode node : children) {
			if (firstRunMap.containsKey(node)) {
				if (node != root) {
					ElkNode newLayout = firstRunMap.get(node);
					node.setX(newLayout.getX() + xDisplacement);
					node.setY(newLayout.getY());
				}
			}
		}

		// Compute positions of original nodes from second run
		double yDisplacement = firstRun.getHeight() - minFirstY - root.getHeight();
		xDisplacement = -minSecondX;
		if (firstWidth > secondWidth) {
			xDisplacement += (firstWidth - secondWidth) / 2;
			layoutGraph.setWidth(firstRun.getWidth());
		}
		root.setY(yDisplacement);
		root.setX(layoutGraph.getWidth() / 2 - root.getWidth() / 2);

		// Computes the smallest relative distance from the root to the other
		// nodes from the first run.
		double firstDistanceToRoot = Double.MAX_VALUE;
		for (ElkNode node : children) {
			if (firstRunMap.containsKey(node)) {
				if (node != root) {
					if (firstRunMap.get(root).getY() - (firstRunMap.get(node).getY()
							+ firstRunMap.get(node).getHeight()) < firstDistanceToRoot) {
						firstDistanceToRoot = firstRunMap.get(root).getY()
								- (firstRunMap.get(node).getY() + firstRunMap.get(node).getHeight());
					}
				}
			}
		}

		// Updates the yDisplacement for the second run such that it has the
		// same smallest relative distance from the root to the nodes than the
		// firstDistanceToRoot.
		yDisplacement = yDisplacement - minSecondY + root.getHeight() + firstDistanceToRoot;

		for (ElkNode node : children) {
			if (secondRunMap.containsKey(node)) {
				if (node != root) {
					ElkNode newLayout = secondRunMap.get(node);
					node.setX(newLayout.getX() + xDisplacement);
					node.setY(newLayout.getY() + yDisplacement);
				}
			}
		}

		layoutGraph.setHeight(yDisplacement + secondRun.getHeight());
	}

	/**
	 * Creates a new simple tree that will be used for Elk Layered and the
	 * computed coordinates will then be applied to the original nodes with
	 * offsets.
	 * 
	 * @param nodes
	 * @param nodeMap
	 * @param firstOffset
	 * @return New layoutGraph
	 */
	private ElkNode createTree(final List<ElkNode> nodes, final Map<ElkNode, ElkNode> nodeMap,
			final int[] firstOffset) {
		ElkNode layoutGraph = ElkGraphUtil.createGraph();
		ElkNode treeRoot = ElkGraphUtil.createNode(layoutGraph);
		ElkUtil.resizeNode(treeRoot, root.getWidth(), root.getHeight(), false, false);
		nodeMap.put(root, treeRoot);
		for (ElkNode node : nodes) {
			ElkNode tempNode = ElkGraphUtil.createNode(layoutGraph);
			int i = firstOffset[nodeHierarchyDepth.get(node) - 1];
			tempNode.setProperty(LayeredOptions.POSITION, new KVector(i, 0));
			tempNode.setProperty(LayeredOptions.ALIGNMENT, Alignment.CENTER);
			firstOffset[nodeHierarchyDepth.get(node) - 1] = (int) (i + node.getWidth() + OFFSET);
			ElkUtil.resizeNode(tempNode, node.getWidth(), node.getHeight(), false, false);
			nodeMap.put(node, tempNode);
		}

		for (ElkNode node : nodes) {
			ElkNode source = null;
			for (ElkEdge edge : ElkGraphUtil.allIncomingEdges(node)) {
				// TODO Use isHierarchical?
				if (edges.contains(edge)) {
					source = nodeMap.get(ElkGraphUtil.connectableShapeToNode(edge.getSources().get(0)));
					break;
				}
			}
			// TODO Use ports for better centering.
			ElkGraphUtil.createSimpleEdge(source, nodeMap.get(node));
		}

		return layoutGraph;
	}

	/**
	 * Puts the connected Nodes of the next hierarchy level recursively into the
	 * List of nodes that are relevant for the corresponding run.
	 * 
	 * @param node
	 * @param list
	 * @param comp
	 */
	private void buildNodeList(final ElkNode node, final List<ElkNode> list, final double offset, final int depth) {
		Comparator<ElkNode> polarComp = RadialUtil.createPolarComparator(node, offset);
		List<ElkNode> compList = RadialUtil.getSortedHierarchicalNodes(node, polarComp);
		for (ElkNode n : compList) {
			list.add(n);
			hierarchyDepth = Math.max(depth, hierarchyDepth);
			nodeHierarchyDepth.put(n, depth);
			buildNodeList(n, list, offset, depth + 1);
		}
	}

	/**
	 * Configures the correct options for elk layered to be executed with.
	 * 
	 * @param node
	 * @param dir
	 */
	private void configureTreeLayout(final ElkNode node, final Direction dir) {
		node.setProperty(LayeredOptions.CROSSING_MINIMIZATION_SEMI_INTERACTIVE, true);
		node.setProperty(LayeredOptions.NODE_PLACEMENT_BK_FIXED_ALIGNMENT, FixedAlignment.BALANCED);
		node.setProperty(LayeredOptions.EDGE_ROUTING, EdgeRouting.POLYLINE);
		node.setProperty(LayeredOptions.CROSSING_MINIMIZATION_GREEDY_SWITCH_TYPE, GreedySwitchType.OFF);
		node.setProperty(LayeredOptions.DIRECTION, dir);
	}

}
