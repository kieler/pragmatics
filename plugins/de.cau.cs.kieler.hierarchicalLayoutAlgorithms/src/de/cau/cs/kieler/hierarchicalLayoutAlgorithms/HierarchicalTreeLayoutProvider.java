/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
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
import java.util.List;
import java.util.Map;

import org.eclipse.elk.alg.layered.LayeredLayoutProvider;
import org.eclipse.elk.alg.layered.options.FixedAlignment;
import org.eclipse.elk.alg.layered.options.GreedySwitchType;
import org.eclipse.elk.alg.layered.options.LayeredOptions;
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
 * Layout Provider for a Hierarchical Graph that uses two runs of Elk Layered
 * for a Tree Layout with two directions.
 * 
 * @author dja
 *
 */
public class HierarchicalTreeLayoutProvider extends AbstractLayoutProvider {
	/** List of all nodes in the graph. */
	private List<ElkNode> children;
	/** List of all edges that are relevant for this Layouter. */
	private static List<ElkEdge> edges;
	/** Root node of the Graph. */
	private static ElkNode root;
	/** Nodes that are in the Hierarchical layer after the root node. */
	private List<ElkNode> secondHierarchyNodes;
	/** Value where the secondHierarchyNodes will be seperated. */
	private int treeseperator;
	/** Depth of the Hierarchy nodes. */
	private int hierarchyDepth = 0;
	/** Map of nodes with their corresponding depth. */
	private Map<ElkNode, Integer> nodeDepth;

	@Override
	public void layout(ElkNode layoutGraph, IElkProgressMonitor progressMonitor) {
		children = new ArrayList<ElkNode>();
		edges = new ArrayList<ElkEdge>();
		nodeDepth = new HashMap<ElkNode, Integer>();

		children = layoutGraph.getChildren();
		edges = HierarchicalUtil.getHierarchicalEdges(layoutGraph);
		root = HierarchicalUtil.findRoot(layoutGraph);
		secondHierarchyNodes = HierarchicalUtil.getSuccessor(root);

		layeredTreeLayout(layoutGraph);

		HierarchicalEdgeRouting.drawHierarchicalEdges(root);
	}

	/**
	 * Method for the layout computation.
	 * 
	 * @param layoutGraph
	 */
	private void layeredTreeLayout(ElkNode layoutGraph) {
		// Compute the two Lists of nodes for the two runs of Elk-Layered with a
		// Tree representation.
		treeseperator = secondHierarchyNodes.size() / 2;
		int i = 0;
		// List of nodes that are used in the particular run
		List<ElkNode> firstRunList = new ArrayList<ElkNode>();
		List<ElkNode> secondRunList = new ArrayList<ElkNode>();
		// TODO Maximale Breite beachten
		Comparator<ElkNode> compY = new Comparator<ElkNode>() {
			@Override
			public int compare(ElkNode n1, ElkNode n2) {
				if (n1.getY() < n2.getY()) {
					return -1;
				} else if (n1.getY() == n2.getY()) {
					return 0;
				} else {
					return 1;
				}
			}
		};

		List<ElkNode> sortedYNodes = sortAxis(root, compY);
		for (ElkNode node : sortedYNodes) {
			if (i < treeseperator) {
				firstRunList.add(node);
			} else {
				secondRunList.add(node);
			}
			i++;
		}

		List<ElkNode> sortedPolarNodes = HierarchicalUtil.sortSuccesorsByPolarCoordinate(root, 1.5 * Math.PI);
		List<ElkNode> tempListF = new ArrayList<ElkNode>();
		List<ElkNode> tempListS = new ArrayList<ElkNode>();
		for (ElkNode node : sortedPolarNodes) {
			if (firstRunList.contains(node)) {
				tempListF.add(node);
			}
		}

		sortedPolarNodes = HierarchicalUtil.sortSuccesorsByPolarCoordinate(root, 0.5 * Math.PI);
		for (ElkNode node : sortedPolarNodes) {
			if (secondRunList.contains(node)) {
				tempListS.add(node);
			}
		}

		List<ElkNode> tempList = new ArrayList<ElkNode>();
		for (ElkNode node : tempListF) {
			tempList.add(node);
			buildNodeList(node, tempList, 1.5 * Math.PI);
		}
		firstRunList.clear();
		firstRunList.addAll(tempList);

		tempList.clear();
		for (ElkNode node : tempListS) {
			tempList.add(node);
			buildNodeList(node, tempList, 0.5 * Math.PI);
		}
		secondRunList.clear();
		tempList = Lists.reverse(tempList);
		secondRunList.addAll(tempList);

		for (ElkNode node : children) {
			int depth = HierarchicalUtil.getDepths(node, root);
			if (hierarchyDepth < depth) {
				hierarchyDepth = depth;
			}
			nodeDepth.put(node, depth);
		}

		// Offsets for the Elk Layered Position option. Used for the correct order of nodes when displayed.
		Map<Integer, Integer> firstOffset = new HashMap<Integer, Integer>();
		for (int x = 1; x <= hierarchyDepth; x++) {
			firstOffset.put(x, 0);
		}
		Map<Integer, Integer> secondOffset = new HashMap<Integer, Integer>();
		for (int x = 1; x <= hierarchyDepth + 1; x++) {
			secondOffset.put(x, 0);
		}

		// First run for upward tree
		// Maps the original node to the temporary node
		// TODO node spacing according to width
		Map<ElkNode, ElkNode> firstRunMap = new HashMap<ElkNode, ElkNode>();
		ElkNode firstRun = createTree(firstRunList, firstRunMap, firstOffset);
		BasicProgressMonitor firstRunMonitor = new BasicProgressMonitor();
		LayeredLayoutProvider layered = new LayeredLayoutProvider();
		firstRun.setProperty(LayeredOptions.CROSSING_MINIMIZATION_SEMI_INTERACTIVE, true);
		firstRun.setProperty(LayeredOptions.NODE_PLACEMENT_BK_FIXED_ALIGNMENT, FixedAlignment.BALANCED);
		firstRun.setProperty(LayeredOptions.EDGE_ROUTING, EdgeRouting.POLYLINE);
		firstRun.setProperty(LayeredOptions.CROSSING_MINIMIZATION_GREEDY_SWITCH_TYPE, GreedySwitchType.OFF);
		firstRun.setProperty(LayeredOptions.DIRECTION, Direction.UP);
		layered.layout(firstRun, firstRunMonitor);

		// Second run for downward tree
		Map<ElkNode, ElkNode> secondRunMap = new HashMap<ElkNode, ElkNode>();
		ElkNode secondRun = createTree(secondRunList, secondRunMap, secondOffset);
		BasicProgressMonitor secondRunMonitor = new BasicProgressMonitor();
		secondRun.setProperty(LayeredOptions.CROSSING_MINIMIZATION_SEMI_INTERACTIVE, true);
		secondRun.setProperty(LayeredOptions.NODE_PLACEMENT_BK_FIXED_ALIGNMENT, FixedAlignment.BALANCED);
		secondRun.setProperty(LayeredOptions.EDGE_ROUTING, EdgeRouting.POLYLINE);
		secondRun.setProperty(LayeredOptions.CROSSING_MINIMIZATION_GREEDY_SWITCH_TYPE, GreedySwitchType.OFF);
		secondRun.setProperty(LayeredOptions.DIRECTION, Direction.DOWN);
		layered.layout(secondRun, secondRunMonitor);

		// Keeps track of actual height and width of the two runs by computing
		// min and max x- and y-values.
		double minFirstY = Double.MAX_VALUE;
		double minFirstX = Double.MAX_VALUE;
		double maxFirstX = Double.MIN_VALUE;
		double minSecondY = Double.MAX_VALUE;
		double minSecondX = Double.MAX_VALUE;
		double maxSecondX = Double.MIN_VALUE;
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
				maxSecondX = Math.min(maxSecondX, node.getX() + node.getWidth());
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
	 * Sorting method.
	 * 
	 * @param node
	 * @param comp
	 * @return
	 */
	private List<ElkNode> sortAxis(ElkNode node, Comparator<ElkNode> comp) {
		List<ElkNode> successors = HierarchicalUtil.getSuccessor(node);

		if (successors.size() > 1) {
			List<ElkNode> sortedNodes = new ArrayList<ElkNode>();
			List<ElkNode> children = new ArrayList<>();
			List<ElkNode> nodeChildren = node.getChildren();
			boolean isBlueBox = nodeChildren.size() == 1 && !nodeChildren.get(0).getChildren().isEmpty();
			// blue boxing
			if (!isBlueBox) {
				children.addAll(nodeChildren);
			} else {
				children.addAll(nodeChildren.get(0).getChildren());
			}
			children.sort(comp);

			// map sorted nodes
			for (ElkNode child : children) {
				Integer childID = child.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_PARENT_I_D);
				if (childID != null) {

					ElkNode removeNode = null;
					for (ElkNode successor : successors) {
						Integer successorID = successor.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_I_D);
						if (childID.equals(successorID)) {
							sortedNodes.add(successor);
							removeNode = successor;
							break;
						}
					}
					successors.remove(removeNode);
				}
			}

			return sortedNodes;
		} else {
			return successors;
		}
	}

	/**
	 * Creates a new simple tree that will be used for Elk Layered and the
	 * computed coordinates will then be applied to the original nodes with
	 * offsets.
	 * 
	 * @param nodes
	 * @param nodeMap
	 * @param offset
	 * @return New layoutGraph
	 */
	private ElkNode createTree(List<ElkNode> nodes, Map<ElkNode, ElkNode> nodeMap, Map<Integer, Integer> offset) {
		ElkNode layoutRoot = ElkGraphUtil.createGraph();
		ElkNode treeRoot = ElkGraphUtil.createNode(layoutRoot);
		ElkUtil.resizeNode(treeRoot, root.getWidth(), root.getHeight(), false, false);
		nodeMap.put(root, treeRoot);
		for (ElkNode node : nodes) {
			ElkNode tempNode = ElkGraphUtil.createNode(layoutRoot);
			int i = offset.get(nodeDepth.get(node));
			tempNode.setProperty(LayeredOptions.POSITION, new KVector(i, 0));
			tempNode.setProperty(LayeredOptions.ALIGNMENT, Alignment.CENTER);
			offset.put(nodeDepth.get(node), (int) (i + node.getWidth() + 100));
			ElkUtil.resizeNode(tempNode, node.getWidth(), node.getHeight(), false, false);
			nodeMap.put(node, tempNode);
		}

		for (ElkNode node : nodes) {
			ElkNode source = null;
			for (ElkEdge edge : ElkGraphUtil.allIncomingEdges(node)) {
				if (edges.contains(edge)) {
					source = nodeMap.get(ElkGraphUtil.connectableShapeToNode(edge.getSources().get(0)));
					break;
				}
			}
			ElkGraphUtil.createSimpleEdge(source, nodeMap.get(node));
		}

		return layoutRoot;
	}

	/**
	 * Puts the connected Nodes of the next hierarchy level recursively into the
	 * List of nodes that are relevant for the corresponding run.
	 * 
	 * @param node
	 * @param list
	 * @param comp
	 */
	private void buildNodeList(ElkNode node, List<ElkNode> list, double offset) {
		// if (!HierarchicalUtil.getSuccessor(node).isEmpty()) {
		List<ElkNode> compList = HierarchicalUtil.sortSuccesorsByPolarCoordinate(node, offset);
		for (ElkNode n : compList) {
			list.add(n);
			buildNodeList(n, list, offset);
		}
	}

}
