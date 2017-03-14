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
import org.eclipse.elk.core.options.PortConstraints;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial.ExplosionLineRouter;

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
	// /** Value where the secondHierarchyNodes will be separated. */
	// private int treeSeperator;
	/** Depth of the hierarchy nodes. */
	private int largestHierarchyDepth;
	/** Map of nodes with their corresponding depth. */
	private Map<ElkNode, Integer> nodeHierarchyDepth;
	/** Comparator according to y-coordinates. */
	private static Comparator<ElkNode> compY;
	/** Constant that starts the polar sorting at the bottom of a circle. */
	private static final double BOTTOM_CIRCLE_START = 1.5 * Math.PI;
	/** Constant that starts the polar sorting at the top of a circle. */
	private static final double TOP_CIRCLE_START = 0.5 * Math.PI;
	/**
	 * Offset of the nodes for the new created tree, such that they stay in the
	 * correct order.
	 */
	private static final int OFFSET = 100;
	/** List of nodes that are used in the first run of elk layered. */
	private List<ElkNode> firstRunList;
	/** List of nodes that are used in the second run of elk layered. */
	private List<ElkNode> secondRunList;
	/**
	 * Maps a hierarchy depth to a list of nodes from the first elk layered run
	 * that are in the corresponding hierarchy depth.
	 */
	private Map<Integer, List<ElkNode>> firstRunDepthNodeList;
	/**
	 * Maps a hierarchy depth to a list of nodes from the second elk layered run
	 * that are in the corresponding hierarchy depth.
	 */
	private Map<Integer, List<ElkNode>> secondRunDepthNodeList;

	@Override
	public void layout(final ElkNode layoutGraph, final IElkProgressMonitor progressMonitor) {
		progressMonitor.begin("Hierarchical Tree Layout", 1);
		largestHierarchyDepth = 1;
		nodeHierarchyDepth = new HashMap<ElkNode, Integer>();
		children = layoutGraph.getChildren();
		edges = HierarchicalUtil.getHierarchicalEdges(layoutGraph);
		root = RadialUtil.findRoot(layoutGraph);
		HierarchicalUtil.initializeOriginalNodeMapping(root);
		secondHierarchyNodes = RadialUtil.getSuccessors(root);
		compY = (n1, n2) -> {
			ElkNode orginalNode1 = n1.getProperty(HierarchicalTreeOptions.ORIGINAL_NODE);
			ElkNode orginalNode2 = n2.getProperty(HierarchicalTreeOptions.ORIGINAL_NODE);

			if (orginalNode1.getY() < orginalNode2.getY()) {
				return -1;
			} else if (orginalNode1.getY() == orginalNode2.getY()) {
				return 0;
			} else {
				return 1;
			}
		};

		if (secondHierarchyNodes.size() > 0) {
			layeredTreeLayout(layoutGraph, progressMonitor);

			progressMonitor.begin("Edge Routing", 3);
			ExplosionLineRouter edgeRouter = new ExplosionLineRouter();
			edgeRouter.routeEdges(RadialUtil.findRoot(layoutGraph));

			HierarchicalTreeCompaction compaction = new HierarchicalTreeCompaction();
			compaction.compact(firstRunDepthNodeList, secondRunDepthNodeList, layoutGraph.getWidth() / 2,
					nodeHierarchyDepth, largestHierarchyDepth);
		}
		progressMonitor.done();
	}

	/**
	 * Method for the layout computation that uses two runs of elk layered with
	 * specific properties, such that the result is a tree layout. The two runs
	 * are with different directions and the resulting layout combines the two
	 * directions such that the root node is in the middle of the diagram.
	 * 
	 * @param layoutGraph
	 */
	private void layeredTreeLayout(final ElkNode layoutGraph, final IElkProgressMonitor pm) {
		// Compute the two Lists of nodes for the two runs of Elk-Layered with a
		// Tree representation.
		// treeSeperator = secondHierarchyNodes.size() / 2;
		firstRunList = new ArrayList<ElkNode>();
		secondRunList = new ArrayList<ElkNode>();
		// TODO Better distribution of nodes in the two runs for a similar width
		// in both runs.

		// TODO calculate max width hierarchy
		List<ElkNode> tempTreeList = new ArrayList<ElkNode>();
		Map<ElkNode, Double> propagatingWidth = new HashMap<ElkNode, Double>();
		for (ElkNode node : children) {
			propagatingWidth.put(node, 0.0);
		}
		tempTreeList.addAll(secondHierarchyNodes);
		Map<Integer, List<ElkNode>> depthNodeList = new HashMap<Integer, List<ElkNode>>();
		buildNodeList(tempTreeList, BOTTOM_CIRCLE_START, root.getHeight() / 2, depthNodeList);
		Map<Integer, Double> hierarchyWidth = new HashMap<Integer, Double>();
		calculateHierarchyWidth(hierarchyWidth, tempTreeList);
		int depthWithLargestWidth = calculateDepthOfLargestHierarchyWidth(hierarchyWidth);
		double layerSeperatorWidth = hierarchyWidth.get(1) / 2;

		if (depthWithLargestWidth > 1) {
			while (depthWithLargestWidth > 1) {
				for (ElkNode node : depthNodeList.get(depthWithLargestWidth)) {
					ElkNode parent = RadialUtil.getTreeParent(node);
					double width = propagatingWidth.get(parent) + node.getWidth();
					propagatingWidth.put(parent, width);
				}
				depthWithLargestWidth--;
			}
		} else {
			for (ElkNode node : depthNodeList.get(depthWithLargestWidth)) {
				propagatingWidth.put(node, node.getWidth());
			}
		}

		List<ElkNode> sortedYNodes = new ArrayList<ElkNode>();
		sortedYNodes.addAll(secondHierarchyNodes);
		sortedYNodes.sort(compY);
		double layerWidth = 0.0;
		for (ElkNode node : sortedYNodes) {
			if (layerWidth < layerSeperatorWidth) {
				firstRunList.add(node);
				layerWidth += propagatingWidth.get(node);
			} else {
				secondRunList.add(node);
			}
		}

		// TODO not working as intended.
		firstRunDepthNodeList = new HashMap<Integer, List<ElkNode>>();
		buildNodeList(firstRunList, BOTTOM_CIRCLE_START, root.getHeight() / 2, firstRunDepthNodeList);
		firstRunList = Lists.reverse(firstRunList);
		secondRunDepthNodeList = new HashMap<Integer, List<ElkNode>>();
		buildNodeList(secondRunList, TOP_CIRCLE_START, -root.getHeight() / 2, secondRunDepthNodeList);
		// secondRunList = Lists.reverse(secondRunList);

		// TODO node spacing according to width
		// First run for upward tree
		// Maps the original node to the temporary node
		Map<ElkNode, ElkNode> firstRunMap = new HashMap<ElkNode, ElkNode>();
		// Offset for the Elk Layered Position option. Used for the correct
		// order of nodes when displayed.
		int[] firstOffset = new int[largestHierarchyDepth];
		ElkNode firstRun = createTree(firstRunList, firstRunMap, firstOffset);
		LayeredLayoutProvider layered = new LayeredLayoutProvider();
		configureTreeLayout(firstRun, Direction.UP);
		// TODO sub task numbers
		pm.begin("Layered Layout", 2);
		layered.layout(firstRun, pm.subTask(2));

		// Second run for downward tree
		Map<ElkNode, ElkNode> secondRunMap = new HashMap<ElkNode, ElkNode>();
		int[] secondOffset = new int[largestHierarchyDepth];
		ElkNode secondRun = createTree(secondRunList, secondRunMap, secondOffset);
		configureTreeLayout(secondRun, Direction.DOWN);
		layered.layout(secondRun, pm.subTask(2));

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
	 * Creates a new simple tree that will be used for elk layered and the
	 * computed coordinates will then be applied to the original nodes with
	 * offsets.
	 * 
	 * @param nodes
	 * @param nodeMap
	 * @param firstOffset
	 * @return New layoutGraph
	 */
	private ElkNode createTree(final List<ElkNode> nodes, final Map<ElkNode, ElkNode> nodeMap, final int[] offset) {
		ElkNode layoutGraph = ElkGraphUtil.createGraph();
		ElkNode treeRoot = ElkGraphUtil.createNode(layoutGraph);
		ElkUtil.resizeNode(treeRoot, root.getWidth(), root.getHeight(), false, false);
		nodeMap.put(root, treeRoot);
		for (ElkNode node : nodes) {
			ElkNode tempNode = ElkGraphUtil.createNode(layoutGraph);
			int off = offset[nodeHierarchyDepth.get(node) - 1];
			tempNode.setProperty(LayeredOptions.POSITION, new KVector(off, 0));
			tempNode.setProperty(LayeredOptions.ALIGNMENT, Alignment.CENTER);
			// tempNode.setProperty(LayeredOptions.PORT_CONSTRAINTS,
			// PortConstraints.FIXED_POS);
			offset[nodeHierarchyDepth.get(node) - 1] = (int) (off + node.getWidth() + OFFSET);
			ElkUtil.resizeNode(tempNode, node.getWidth(), node.getHeight(), false, false);
			nodeMap.put(node, tempNode);
		}

		for (ElkEdge edge : edges) {
			ElkNode targetOriginalNode = ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0));
			ElkNode source = nodeMap.get(ElkGraphUtil.connectableShapeToNode(edge.getSources().get(0)));
			ElkNode target = nodeMap.get(targetOriginalNode);
			if ((source != null) && (target != null)) {
				// TODO Use ports for better centering.
				ElkNode n = targetOriginalNode.getProperty(HierarchicalTreeOptions.ORIGINAL_NODE);
				// System.out.println(n.toString());
				// System.out.println("Node: " + n.getX() + n.getWidth() / 2);
				double portPositionX = n.getX() + n.getWidth() / 2;
				ElkPort port = ElkGraphUtil.createPort(n);
				source.getPorts().add(port);
				port.setLocation(portPositionX, source.getY() + source.getHeight());
				// System.out.println("Port: " + port.getX());
				ElkGraphUtil.createSimpleEdge(port, target);
				// ElkGraphUtil.createSimpleEdge(source, target);
			}
		}

		return layoutGraph;
	}

	/**
	 * Starts to put connected nodes of the next hierarchy level into the list
	 * of nodes that are relevant for the corresponding run and uses a recursive
	 * method for this.
	 * 
	 * @param runList
	 * @param comp
	 */
	private void buildNodeList(final List<ElkNode> runList, final double radialOffset, final double nodeOffset,
			final Map<Integer, List<ElkNode>> depthNodeList) {
		nodeHierarchyDepth.put(root, 0);
		Comparator<ElkNode> comp = RadialUtil.createPolarComparator(radialOffset, nodeOffset);
		runList.sort(comp);
		List<ElkNode> tempList = new ArrayList<ElkNode>();
		for (ElkNode node : runList) {
			nodeHierarchyDepth.put(node, 1);
			List<ElkNode> list;
			if (depthNodeList.containsKey(1)) {
				list = depthNodeList.get(1);
			} else {
				list = new ArrayList<ElkNode>();
			}
			list.add(node);
			depthNodeList.put(1, list);
			if (nodeOffset > 0) {
				buildNodeListRecursive(node, tempList, radialOffset, node.getHeight() / 2, 2, depthNodeList);
			} else {
				buildNodeListRecursive(node, tempList, radialOffset, -node.getHeight() / 2, 2, depthNodeList);
			}
		}
		runList.addAll(tempList);
	}

	/**
	 * Puts the connected nodes of the next hierarchy level recursively into the
	 * list of nodes that are relevant for the corresponding run.
	 * 
	 * @param node
	 * @param list
	 * @param comp
	 */
	private void buildNodeListRecursive(final ElkNode node, final List<ElkNode> tempList, final double radialOffset,
			final double nodeOffset, final int depth, final Map<Integer, List<ElkNode>> depthNodeList) {
		List<ElkNode> compList = RadialUtil.getSuccessors(node);
		Comparator<ElkNode> comp = RadialUtil.createPolarComparator(radialOffset, nodeOffset);
		compList.sort(comp);
		for (ElkNode n : compList) {
			tempList.add(n);
			largestHierarchyDepth = Math.max(depth, largestHierarchyDepth);
			nodeHierarchyDepth.put(n, depth);
			List<ElkNode> list;
			if (depthNodeList.containsKey(depth)) {
				list = depthNodeList.get(depth);
			} else {
				list = new ArrayList<ElkNode>();
			}
			list.add(n);
			depthNodeList.put(depth, list);
			if (nodeOffset > 0) {
				buildNodeListRecursive(n, tempList, radialOffset, n.getHeight() / 2, depth + 1, depthNodeList);
			} else {
				buildNodeListRecursive(n, tempList, radialOffset, -n.getHeight() / 2, depth + 1, depthNodeList);
			}
		}
	}

	/**
	 * Accumulates the width of nodes for every hierarchy level for the two
	 * runs.
	 * 
	 * @param hierarchyWidth
	 * @param runList
	 */
	private void calculateHierarchyWidth(final Map<Integer, Double> hierarchyWidth, final List<ElkNode> runList) {
		for (int j = 1; j <= largestHierarchyDepth; j++) {
			hierarchyWidth.put(j, 0.0);
		}
		for (ElkNode node : runList) {
			int depth = nodeHierarchyDepth.get(node);
			hierarchyWidth.put(depth, hierarchyWidth.get(depth) + node.getWidth());
		}
	}

	/**
	 * Calculates the hierarchy depth with the largest width.
	 * 
	 * @param hierarchyWidth
	 * @return Hierarchy depth with largest width.
	 */
	private int calculateDepthOfLargestHierarchyWidth(final Map<Integer, Double> hierarchyWidth) {
		double largestWidth = 0.0;
		int depthWithLargestWidth = 0;
		for (int j = 1; j <= largestHierarchyDepth; j++) {
			if (hierarchyWidth.get(j) > largestWidth) {
				largestWidth = hierarchyWidth.get(j);
				depthWithLargestWidth = j;
			}
		}
		return depthWithLargestWidth;
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
		node.setProperty(LayeredOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_POS);
		node.setProperty(LayeredOptions.DIRECTION, dir);
	}

}
