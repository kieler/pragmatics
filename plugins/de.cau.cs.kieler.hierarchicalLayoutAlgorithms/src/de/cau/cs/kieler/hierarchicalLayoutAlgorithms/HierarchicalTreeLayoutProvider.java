package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.alg.layered.LayeredLayoutProvider;
import org.eclipse.elk.alg.layered.properties.FixedAlignment;
import org.eclipse.elk.alg.layered.properties.GreedySwitchType;
import org.eclipse.elk.alg.layered.properties.LayeredOptions;
import org.eclipse.elk.alg.mrtree.TreeLayoutProvider;
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

public class HierarchicalTreeLayoutProvider extends AbstractLayoutProvider {

	private List<ElkNode> children = new ArrayList<ElkNode>();
	private static List<ElkEdge> edges = new ArrayList<ElkEdge>();
	private static Map<ElkNode, ElkNode> firstRunMap = new HashMap<ElkNode, ElkNode>();
	private static Map<ElkNode, ElkNode> secondRunMap = new HashMap<ElkNode, ElkNode>();
	private static ElkNode root;
	private List<ElkNode> secondHierarchyNodes;
	private int treeseperator;
	private int maxDepth = 0;
	private Map<Integer, Integer> firstOffset = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> secondOffset = new HashMap<Integer, Integer>();

	@Override
	public void layout(ElkNode layoutGraph, IElkProgressMonitor progressMonitor) {
		children.clear();
		edges.clear();
		firstRunMap.clear();
		secondRunMap.clear();

		children = layoutGraph.getChildren();
		edges = HierarchicalUtil.getHierarchicalEdges(layoutGraph);
		root = HierarchicalUtil.findRoot(layoutGraph);
		secondHierarchyNodes = HierarchicalUtil.getSuccessor(root);

		layeredLayout(layoutGraph);

		HierarchicalEdgeRouting.drawExplosionLines(root);
	}

	private void layeredLayout(ElkNode layoutGraph) {
		// Compute the two Lists of nodes for the two runs of MrTree.
		treeseperator = secondHierarchyNodes.size() / 2;
		int i = 0;
		List<ElkNode> firstRunList = new ArrayList<ElkNode>();
		List<ElkNode> secondRunList = new ArrayList<ElkNode>();
		// TODO Secondary sort method according to width
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

		Comparator<ElkNode> compX = new Comparator<ElkNode>() {
			@Override
			public int compare(ElkNode n1, ElkNode n2) {
				if (n1.getX() < n2.getX()) {
					return -1;
				} else if (n1.getX() == n2.getX()) {
					return 0;
				} else {
					return 1;
				}
			}
		};
		// List<ElkNode> sortedXNodes = sortAxis(root, compX);
		List<ElkNode> sortedXNodes = HierarchicalUtil.sortSuccesorsByPolarCoordinate(root);
		List<ElkNode> tempListF = new ArrayList<ElkNode>();
		List<ElkNode> tempListS = new ArrayList<ElkNode>();
		for (ElkNode node : sortedXNodes) {
			if (firstRunList.contains(node)) {
				tempListF.add(0, node);
			} else if (secondRunList.contains(node)) {
				tempListS.add(node);
			}
		}
		firstRunList.clear();
		firstRunList.addAll(tempListF);
		secondRunList.clear();
		secondRunList.addAll(tempListS);

		List<ElkNode> tempList = new ArrayList<ElkNode>();
		Boolean first = true;
		for (ElkNode node : firstRunList) {
			tempList.add(node);
			buildNodeList(node, tempList, compX, first);
		}
		firstRunList.clear();
		firstRunList.addAll(tempList);
		for (int x = 0; i < maxDepth; i++) {
			firstOffset.put(x, 0);
		}

		maxDepth = 0;
		tempList.clear();
		first = false;
		for (ElkNode node : secondRunList) {
			tempList.add(node);
			buildNodeList(node, tempList, compX, first);
		}
		secondRunList.clear();
		secondRunList.addAll(tempList);
		for (int x = 0; i < maxDepth; i++) {
			secondOffset.put(x, 0);
		}
		
		// First run for upward tree
		ElkNode firstRun = createTree(firstRunList, firstRunMap);
		BasicProgressMonitor firstLayeredRun = new BasicProgressMonitor();
		LayeredLayoutProvider layered = new LayeredLayoutProvider();
		firstRun.setProperty(LayeredOptions.CROSSING_MINIMIZATION_SEMI_INTERACTIVE, true);
		firstRun.setProperty(LayeredOptions.NODE_PLACEMENT_BK_FIXED_ALIGNMENT, FixedAlignment.BALANCED);
		firstRun.setProperty(LayeredOptions.EDGE_ROUTING, EdgeRouting.POLYLINE);
//		firstRun.setProperty(LayeredOptions.CROSSING_MINIMIZATION_GREEDY_SWITCH, GreedySwitchType.OFF);
		firstRun.setProperty(LayeredOptions.DIRECTION, Direction.UP);
		layered.layout(firstRun, firstLayeredRun);

		// TODO Compute correct displacement for x (maybe fixed)
		float firstWidth = (float) firstRun.getWidth();
		float smallestXPos = Float.MAX_VALUE;

		// Second run for downward tree
		ElkNode secondRun = createTree(secondRunList, secondRunMap);
		BasicProgressMonitor secondLayeredRun = new BasicProgressMonitor();
		secondRun.setProperty(LayeredOptions.CROSSING_MINIMIZATION_SEMI_INTERACTIVE, true);
		secondRun.setProperty(LayeredOptions.NODE_PLACEMENT_BK_FIXED_ALIGNMENT, FixedAlignment.BALANCED);
		secondRun.setProperty(LayeredOptions.EDGE_ROUTING, EdgeRouting.POLYLINE);
//		secondRun.setProperty(LayeredOptions.CROSSING_MINIMIZATION_GREEDY_SWITCH, GreedySwitchType.OFF);
		secondRun.setProperty(LayeredOptions.DIRECTION, Direction.DOWN);
		layered.layout(secondRun, secondLayeredRun);

		float secondWidth = (float) secondRun.getWidth();

		System.out.println("first: " + firstWidth);
		System.out.println("second: " + secondWidth);

		// Compute positions for first run
		float xDisplacement = 0;
		if (firstWidth < secondWidth) {
			xDisplacement = (secondWidth - firstWidth) / 2;
			layoutGraph.setWidth(secondWidth);
		}

		System.out.println("xDisp: " + xDisplacement);
		System.out.println("smallest: " + smallestXPos);
		for (ElkNode node : children) {
			if (firstRunMap.containsKey(node)) {
				ElkNode newLayout = firstRunMap.get(node);
				node.setX(newLayout.getX() + xDisplacement);
				node.setY(newLayout.getY());
			}
		}

		// Compute positions for second run
		xDisplacement = 0;
		if (firstWidth > secondWidth) {
			xDisplacement = (firstWidth - secondWidth) / 2;
			layoutGraph.setWidth(firstWidth);
		}

		float yDisplacement = (float) (firstRun.getHeight() - root.getHeight());
		for (ElkNode node : children) {
			if (secondRunMap.containsKey(node)) {
				ElkNode newLayout = secondRunMap.get(node);
				node.setX(newLayout.getX() + xDisplacement);
				node.setY(newLayout.getY() + yDisplacement);
			}
		}

		layoutGraph.setHeight(yDisplacement + secondRun.getHeight());
	}

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

	private ElkNode createTree(List<ElkNode> nodes, Map<ElkNode, ElkNode> map) {
		ElkNode layoutRoot = ElkGraphUtil.createGraph();
		ElkNode treeRoot = ElkGraphUtil.createNode(layoutRoot);
		ElkUtil.resizeNode(treeRoot, root.getWidth(), root.getHeight(), false, false);
		map.put(root, treeRoot);
		int j = 0;
		int i = 0;
		for (ElkNode node : nodes) {
			// TODO mit getDepth arbeiten
			if (j <= 0) {
				i++;
				j = HierarchicalUtil.getSuccessor(node).size();
			}
			ElkNode tempNode = ElkGraphUtil.createNode(layoutRoot);
			tempNode.setProperty(LayeredOptions.POSITION, new KVector(i, i));
			ElkUtil.resizeNode(tempNode, node.getWidth(), node.getHeight(), false, false);
			map.put(node, tempNode);
			j--;
		}

		for (ElkNode node : nodes) {
			ElkNode source = null;
			for (ElkEdge edge : node.getIncomingEdges()) {
				if (edges.contains(edge)) {
					source = map.get(edge.getSources());
					break;
				}
			}
			ElkGraphUtil.createSimpleEdge(source, map.get(node));
		}

		return layoutRoot;
	}

	private void buildNodeList(ElkNode node, List<ElkNode> list, Comparator<ElkNode> comp, Boolean first) {
		// if (!HierarchicalUtil.getSuccessor(node).isEmpty()) {
		// List<ElkNode> compList = sortAxis(node, comp);
		List<ElkNode> compList = HierarchicalUtil.sortSuccesorsByPolarCoordinate(node);
		for (ElkNode n : compList) {
			if (first) {
				list.add(0, n);
			} else {
				list.add(n);
			}
			// TODO depth
			buildNodeList(n, list, comp, first);
		}
		// for (ElkNode n : HierarchicalUtil.getSuccessor(node)) {
		// list.add(n);
		// buildNodeList(n, list);
		// }
		// }
	}

}
