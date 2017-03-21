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
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import com.google.common.collect.Lists;
import com.google.common.math.DoubleMath;

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
	/** Heuristic for a better complete width of the graph before a layout is computed. */
	private boolean widthheuristic;
	/**
	 * Magic divisor for a better distance between the root and nearest other
	 * nodes in dependence to the complete graph height.
	 */
	private static final int DIVISOR = 20;
	/** Epsilon for comparison of double values. */
	private static final double EPSILON = 1e-10;
	/** Spacing between nodes. */
	private double nodeSpacing;

	@Override
	public void layout(final ElkNode layoutGraph, final IElkProgressMonitor progressMonitor) {
		progressMonitor.begin("Hierarchical tree layout", 1);
		widthheuristic = layoutGraph.getProperty(HierarchicalTreeOptions.WIDTH_HEURSTIC);
		nodeSpacing = layoutGraph.getProperty(HierarchicalTreeOptions.NODE_SPACING);
		children = layoutGraph.getChildren();
		edges = HierarchicalUtil.getHierarchicalEdges(layoutGraph);
		root = RadialUtil.findRoot(layoutGraph);
		secondHierarchyNodes = RadialUtil.getSuccessors(root);
		largestHierarchyDepth = 1;
		nodeHierarchyDepth = new HashMap<ElkNode, Integer>();

		compY = (n1, n2) -> {
			KVector originalNode1 = n1.getProperty(CoreOptions.POSITION);
			KVector originalNode2 = n2.getProperty(CoreOptions.POSITION);
			
			return DoubleMath.fuzzyCompare(originalNode1.y, originalNode2.y, EPSILON);
		};

		// Compute the two Lists of nodes for the two runs of Elk-Layered with a
		// Tree representation.
		firstRunList = new ArrayList<ElkNode>();
		secondRunList = new ArrayList<ElkNode>();
		firstRunDepthNodeList = new HashMap<Integer, List<ElkNode>>();
		secondRunDepthNodeList = new HashMap<Integer, List<ElkNode>>();
		int size = secondHierarchyNodes.size();

		if (size > 0) {
			progressMonitor.begin("Layout computation", 2);
			if (size > 1) {
				layeredTwoDimensionalTreeLayout(layoutGraph, progressMonitor);
			} else {
				layeredOneDimensionalTreeLayout(layoutGraph, progressMonitor);
			}

			progressMonitor.begin("Compaction", 3);
			HierarchicalTreeCompaction compaction = new HierarchicalTreeCompaction(nodeSpacing);
			compaction.compact(firstRunDepthNodeList, secondRunDepthNodeList, layoutGraph.getWidth() / 2,
					nodeHierarchyDepth, largestHierarchyDepth);

			progressMonitor.begin("Edge routing", 4);
			ExplosionLineRouter edgeRouter = new ExplosionLineRouter();
			edgeRouter.routeExplsionLines(RadialUtil.findRoot(layoutGraph));
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
	private void layeredTwoDimensionalTreeLayout(final ElkNode layoutGraph, final IElkProgressMonitor pm) {
		if (widthheuristic) {
			List<ElkNode> tempTreeList = new ArrayList<ElkNode>();
			Map<ElkNode, Double> propagatingWidth = new HashMap<ElkNode, Double>();

			for (ElkNode node : children) {
				propagatingWidth.put(node, 0.0);
			}

			Map<Integer, List<ElkNode>> depthNodeList = new HashMap<Integer, List<ElkNode>>();
			tempTreeList.addAll(secondHierarchyNodes);
			buildNodeList(tempTreeList, 0, 0, depthNodeList, false);
			Map<Integer, Double> hierarchyWidth = new HashMap<Integer, Double>();
			calculateHierarchyWidth(hierarchyWidth, tempTreeList);
			int depthWithLargestWidth = calculateDepthOfLargestHierarchyWidth(hierarchyWidth);
			double layerSeperatorWidth = 0;

			if (depthWithLargestWidth > 1) {
				while (depthWithLargestWidth > 1) {
					for (ElkNode node : depthNodeList.get(depthWithLargestWidth)) {
						ElkNode parent = RadialUtil.getTreeParent(node);
						double width = propagatingWidth.get(parent) + node.getWidth();
						propagatingWidth.put(parent, width);
					}
					depthWithLargestWidth--;
				}

				for (ElkNode node : secondHierarchyNodes) {
					double width = propagatingWidth.get(node);
					if (width < 1) {
						width = node.getWidth();
					}
					layerSeperatorWidth += width;
				}
			} else {
				for (ElkNode node : depthNodeList.get(depthWithLargestWidth)) {
					propagatingWidth.put(node, node.getWidth());
					layerSeperatorWidth += node.getWidth();
				}
			}

			layerSeperatorWidth = layerSeperatorWidth / 2;
			List<ElkNode> sortedYNodes = new ArrayList<ElkNode>();
			sortedYNodes.addAll(secondHierarchyNodes);
			sortedYNodes.sort(compY);
			double layerWidth = 0.0;

			for (ElkNode node : sortedYNodes) {
				double nextWidth = layerWidth + propagatingWidth.get(node);
				if (layerSeperatorWidth - layerWidth > Math.abs(layerSeperatorWidth - nextWidth)) {
					firstRunList.add(node);
					layerWidth = nextWidth;
				} else {
					secondRunList.add(node);
				}
			}
		} else {
			List<ElkNode> sortedYNodes = new ArrayList<ElkNode>();
			sortedYNodes.addAll(secondHierarchyNodes);
			sortedYNodes.sort(compY);
			int i = 0;

			for (ElkNode node : sortedYNodes) {
				if (i < secondHierarchyNodes.size() / 2) {
					firstRunList.add(node);
				} else {
					secondRunList.add(node);
				}
				i++;
			}

			// If something went wrong
			if (firstRunList.isEmpty()) {
				firstRunList.add(secondRunList.get(0));
				secondRunList.remove(0);
			} else if (secondRunList.isEmpty()) {
				int size = firstRunList.size();
				secondRunList.add(firstRunList.get(size));
				firstRunList.remove(size);
			}
		}

		buildNodeList(firstRunList, BOTTOM_CIRCLE_START, -root.getHeight() / 2, firstRunDepthNodeList, true);
		// firstRunList = Lists.reverse(firstRunList);
		buildNodeList(secondRunList, TOP_CIRCLE_START, root.getHeight() / 2, secondRunDepthNodeList, true);
		secondRunList = Lists.reverse(secondRunList);

		// First run for upward tree
		// Maps the original node to the temporary node
		Map<ElkNode, ElkNode> firstRunMap = new HashMap<ElkNode, ElkNode>();
		// Offset for the Elk Layered Position option. Used for the correct
		// order of nodes when displayed.
		int[] firstOffset = new int[largestHierarchyDepth];
		ElkNode firstRun = createTree(firstRunList, firstRunMap, firstOffset);
		LayeredLayoutProvider layered = new LayeredLayoutProvider();
		configureTreeLayout(firstRun, Direction.UP);
		layered.layout(firstRun, pm.subTask(1));

		// Second run for downward tree
		Map<ElkNode, ElkNode> secondRunMap = new HashMap<ElkNode, ElkNode>();
		int[] secondOffset = new int[largestHierarchyDepth];
		ElkNode secondRun = createTree(secondRunList, secondRunMap, secondOffset);
		configureTreeLayout(secondRun, Direction.DOWN);
		layered.layout(secondRun, pm.subTask(1));

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

		root.setX(layoutGraph.getWidth() / 2 - root.getWidth() / 2);
		root.setY(yDisplacement);

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
		// firstDistanceToRoot and increases the distance to the root if it is
		// too short.
		yDisplacement = yDisplacement - minSecondY + root.getHeight() + firstDistanceToRoot;
		double height = yDisplacement + secondRun.getHeight();
		if (firstDistanceToRoot < height / DIVISOR) {
			firstDistanceToRoot = height / DIVISOR;
			height += firstDistanceToRoot * 2;
			yDisplacement += firstDistanceToRoot * 2;
			root.setY(root.getY() + firstDistanceToRoot);
		}

		for (ElkNode node : children) {
			if (secondRunMap.containsKey(node)) {
				if (node != root) {
					ElkNode newLayout = secondRunMap.get(node);
					node.setX(newLayout.getX() + xDisplacement);
					node.setY(newLayout.getY() + yDisplacement);
				}
			}
		}
		layoutGraph.setHeight(height);
	}

	/**
	 * Method for the layout computation if the tree root has only one child.
	 * Then there need to be a layout that only has one direction.
	 * 
	 * @param layoutGraph
	 * @param pm
	 */
	private void layeredOneDimensionalTreeLayout(final ElkNode layoutGraph, final IElkProgressMonitor pm) {
		children = layoutGraph.getChildren();
		root = RadialUtil.findRoot(layoutGraph);
		ElkNode child = RadialUtil.getSuccessors(root).get(0);
		List<ElkNode> runList = new ArrayList<ElkNode>();
		runList.add(child);
		buildNodeList(runList, TOP_CIRCLE_START, root.getHeight() / 2, secondRunDepthNodeList, true);
		runList = Lists.reverse(runList);
		Map<ElkNode, ElkNode> nodeMap = new HashMap<ElkNode, ElkNode>();
		int[] offset = new int[largestHierarchyDepth];
		ElkNode run = createTree(runList, nodeMap, offset);
		configureTreeLayout(run, Direction.DOWN);
		LayeredLayoutProvider layered = new LayeredLayoutProvider();
		layered.layout(run, pm.subTask(1));

		for (ElkNode node : children) {
			ElkNode newLayout = nodeMap.get(node);
			node.setX(newLayout.getX());
			node.setY(newLayout.getY());
		}

		double middle = run.getWidth() / 2;
		root.setX(middle - root.getWidth() / 2);
		child.setX(middle - child.getWidth() / 2);
		layoutGraph.setHeight(run.getHeight());
		layoutGraph.setWidth(run.getWidth());
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
			offset[nodeHierarchyDepth.get(node) - 1] = (int) (off + node.getWidth() + OFFSET);
			ElkUtil.resizeNode(tempNode, node.getWidth(), node.getHeight(), false, false);
			nodeMap.put(node, tempNode);
		}

		for (ElkEdge edge : edges) {
			ElkNode targetOriginalNode = ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0));
			ElkNode source = nodeMap.get(ElkGraphUtil.connectableShapeToNode(edge.getSources().get(0)));
			ElkNode target = nodeMap.get(targetOriginalNode);
			if ((source != null) && (target != null)) {
				ElkGraphUtil.createSimpleEdge(source, target);
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
			final Map<Integer, List<ElkNode>> depthNodeList, final boolean sort) {
		nodeHierarchyDepth.put(root, 0);
		if (sort) {
			Comparator<ElkNode> comp = RadialUtil.createPolarComparator(radialOffset, nodeOffset);
			runList.sort(comp);
		}

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
				buildNodeListRecursive(node, tempList, radialOffset, node.getHeight() / 2, 2, depthNodeList, sort);
			} else {
				buildNodeListRecursive(node, tempList, radialOffset, -node.getHeight() / 2, 2, depthNodeList, sort);
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
			final double nodeOffset, final int depth, final Map<Integer, List<ElkNode>> depthNodeList, boolean sort) {
		List<ElkNode> compList = RadialUtil.getSuccessors(node);
		if (sort) {
			Comparator<ElkNode> comp = RadialUtil.createPolarComparator(radialOffset, nodeOffset);
			compList.sort(comp);
		}

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
				buildNodeListRecursive(n, tempList, radialOffset, n.getHeight() / 2, depth + 1, depthNodeList, sort);
			} else {
				buildNodeListRecursive(n, tempList, radialOffset, -n.getHeight() / 2, depth + 1, depthNodeList, sort);
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
	private void configureTreeLayout(final ElkNode layoutGraph, final Direction dir) {
		layoutGraph.setProperty(LayeredOptions.CROSSING_MINIMIZATION_SEMI_INTERACTIVE, true);
		layoutGraph.setProperty(LayeredOptions.NODE_PLACEMENT_BK_FIXED_ALIGNMENT, FixedAlignment.BALANCED);
		layoutGraph.setProperty(LayeredOptions.EDGE_ROUTING, EdgeRouting.POLYLINE);
		layoutGraph.setProperty(LayeredOptions.CROSSING_MINIMIZATION_GREEDY_SWITCH_TYPE, GreedySwitchType.OFF);
		layoutGraph.setProperty(LayeredOptions.DIRECTION, dir);
		layoutGraph.setProperty(LayeredOptions.SPACING_NODE_NODE, nodeSpacing);
	}

}
