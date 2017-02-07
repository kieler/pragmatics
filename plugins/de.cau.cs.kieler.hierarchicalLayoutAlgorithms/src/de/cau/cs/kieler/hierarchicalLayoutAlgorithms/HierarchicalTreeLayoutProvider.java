package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.alg.layered.LayeredLayoutProvider;
import org.eclipse.elk.alg.layered.properties.FixedAlignment;
import org.eclipse.elk.alg.layered.properties.LayeredOptions;
import org.eclipse.elk.alg.mrtree.TreeLayoutProvider;
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KNode;

public class HierarchicalTreeLayoutProvider extends AbstractLayoutProvider {

	private List<KNode> children = new ArrayList<KNode>();
	private static List<KEdge> edges = new ArrayList<KEdge>();
	private static Map<KNode, KNode> firstRunMap = new HashMap<KNode, KNode>();
	private static Map<KNode, KNode> secondRunMap = new HashMap<KNode, KNode>();
	private static KNode root;
	private List<KNode> secondHierarchyNodes;

	@Override
	public void layout(KNode layoutGraph, IElkProgressMonitor progressMonitor) {
		children.clear();
		edges.clear();
		firstRunMap.clear();
		secondRunMap.clear();

		children = layoutGraph.getChildren();
		edges = HierarchicalUtil.getHierarchicalEdges(layoutGraph);
		root = HierarchicalUtil.findRoot(layoutGraph);
		secondHierarchyNodes = HierarchicalUtil.getSuccessor(root);

		// treeLayout(layoutGraph);
		layeredLayout(layoutGraph);

		HierarchicalEdgeRouting.drawExplosionLines(root);
	}

	private void treeLayout(KNode layoutGraph) {
		// Compute the two Lists of nodes for the two runs of MrTree.
		int treeseperator = secondHierarchyNodes.size() / 2;
		int i = 0;
		List<KNode> firstRunList = new ArrayList<KNode>();
		List<KNode> secondRunList = new ArrayList<KNode>();
		for (KNode node : secondHierarchyNodes) {
			if (i < treeseperator) {
				firstRunList.add(node);
			} else {
				secondRunList.add(node);
			}
			i++;
		}

		List<KNode> tempList = new ArrayList<KNode>();
		for (KNode node : firstRunList) {
//			buildNodeList(node, tempList);
		}
		firstRunList.addAll(tempList);

		tempList.clear();
		for (KNode node : secondRunList) {
//			buildNodeList(node, tempList);
		}
		secondRunList.addAll(tempList);

		// First run for upward tree
		KNode firstRun = createTree(firstRunList, firstRunMap);
		TreeLayoutProvider tree = new TreeLayoutProvider();
		BasicProgressMonitor firstTreeRun = new BasicProgressMonitor();
		tree.layout(firstRun, firstTreeRun);

		// TODO Compute correct displacement for x
		KShapeLayout firstRunLayout = firstRun.getData(KShapeLayout.class);
		float firstWidth = firstRunLayout.getWidth();
		float yDisplacement = firstRunLayout.getHeight();
		float smallestXPos = Float.MAX_VALUE;

		// Second run for downward tree
		KNode secondRun = createTree(secondRunList, secondRunMap);
		BasicProgressMonitor secondTreeRun = new BasicProgressMonitor();
		tree.layout(secondRun, secondTreeRun);

		KShapeLayout secondRunLayout = secondRun.getData(KShapeLayout.class);
		float secondWidth = secondRunLayout.getWidth();

		System.out.println("first: " + firstWidth);
		System.out.println("second: " + secondWidth);

		// Compute positions for first run
		float xDisplacement = 0;
		if (firstWidth < secondWidth) {
			xDisplacement = (secondWidth - firstWidth) / 2;
		}

		System.out.println("xDisp: " + xDisplacement);
		System.out.println("smallest: " + smallestXPos);
		for (KNode node : children) {
			if (firstRunMap.containsKey(node)) {
				KShapeLayout newLayout = firstRunMap.get(node).getData(KShapeLayout.class);
				KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
				// nodeLayout.setXpos(newLayout.getXpos() + xDisplacement -
				// smallestXPos);
				nodeLayout.setXpos(newLayout.getXpos() + xDisplacement);
				nodeLayout.setYpos(-(newLayout.getYpos() + newLayout.getHeight()) + yDisplacement);
			}
		}

		// Compute positions for second run
		xDisplacement = 0;
		if (firstWidth > secondWidth) {
			xDisplacement = (firstWidth - secondWidth) / 2;
		}

		KShapeLayout rootLayout = root.getData(KShapeLayout.class);
		yDisplacement -= rootLayout.getHeight() + 40;
		for (KNode node : children) {
			if (secondRunMap.containsKey(node)) {
				KShapeLayout newLayout = secondRunMap.get(node).getData(KShapeLayout.class);
				KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
				// System.out.println("before: " + newLayout.getXpos());
				// nodeLayout.setXpos(newLayout.getXpos() + xDisplacement -
				// smallestXPos);
				nodeLayout.setXpos(newLayout.getXpos() + xDisplacement);
				// System.out.println("after: " + nodeLayout.getXpos());
				nodeLayout.setYpos(newLayout.getYpos() + yDisplacement);
			}
		}

		// TODO set appropriate height and width
		KShapeLayout graphLayout = layoutGraph.getData(KShapeLayout.class);
		graphLayout.setWidth(3000);
		graphLayout.setHeight(3000);
		// rootLayout.setYpos(0);
		// rootLayout.setXpos(0);
	}

	private void layeredLayout(KNode layoutGraph) {
		// Compute the two Lists of nodes for the two runs of MrTree.
		int treeseperator = secondHierarchyNodes.size() / 2;
		int i = 0;
		List<KNode> firstRunList = new ArrayList<KNode>();
		List<KNode> secondRunList = new ArrayList<KNode>();
		// TODO Secondary sort method according to width
		Comparator<KNode> compY = new Comparator<KNode>() {
			@Override
			public int compare(KNode n1, KNode n2) {
				KShapeLayout n1Layout = n1.getData(KShapeLayout.class);
				KShapeLayout n2Layout = n2.getData(KShapeLayout.class);
				if (n1Layout.getYpos() < n2Layout.getYpos()) {
					return -1;
				} else if (n1Layout.getYpos() == n2Layout.getYpos()) {
					return 0;
				} else {
					return 1;
				}
			}
		};
		List<KNode> sortedYNodes = sortAxis(root, compY);
		for (KNode node : sortedYNodes) {
			if (i < treeseperator) {
				firstRunList.add(node);
			} else {
				secondRunList.add(node);
			}
			i++;
		}
		
		Comparator<KNode> compX = new Comparator<KNode>() {
			@Override
			public int compare(KNode n1, KNode n2) {
				KShapeLayout n1Layout = n1.getData(KShapeLayout.class);
				KShapeLayout n2Layout = n2.getData(KShapeLayout.class);
				if (n1Layout.getXpos() < n2Layout.getXpos()) {
					return -1;
				} else if (n1Layout.getXpos() == n2Layout.getXpos()) {
					return 0;
				} else {
					return 1;
				}
			}
		};
//		List<KNode> sortedXNodes = sortAxis(root, compX);
		List<KNode> sortedXNodes = HierarchicalUtil.sortSuccesorsByPolarCoordinate(root);
		List<KNode> tempListF = new ArrayList<KNode>();
		List<KNode> tempListS = new ArrayList<KNode>();
		for (KNode node : sortedXNodes) {
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

		List<KNode> tempList = new ArrayList<KNode>();
		Boolean first = true;
		for (KNode node : firstRunList) {
			buildNodeList(node, tempList, compX, first);
		}
		firstRunList.addAll(tempList);

		tempList.clear();
		first = false;
		for (KNode node : secondRunList) {
			buildNodeList(node, tempList, compX, first);
		}
		secondRunList.addAll(tempList);

		// First run for upward tree
		KNode firstRun = createTree(firstRunList, firstRunMap);
		BasicProgressMonitor firstLayeredRun = new BasicProgressMonitor();
		LayeredLayoutProvider layered = new LayeredLayoutProvider();
		KShapeLayout firstLayout = firstRun.getData(KShapeLayout.class);
		firstLayout.setProperty(LayeredOptions.CROSSING_MINIMIZATION_SEMI_INTERACTIVE, true);
		firstLayout.setProperty(LayeredOptions.NODE_PLACEMENT_BK_FIXED_ALIGNMENT, FixedAlignment.BALANCED);
		firstLayout.setProperty(LayeredOptions.EDGE_ROUTING, EdgeRouting.POLYLINE);
		firstLayout.setProperty(LayeredOptions.DIRECTION, Direction.UP);
		layered.layout(firstRun, firstLayeredRun);

		// TODO Compute correct displacement for x (maybe fixed)
		KShapeLayout firstRunLayout = firstRun.getData(KShapeLayout.class);
		float firstWidth = firstRunLayout.getWidth();
		float smallestXPos = Float.MAX_VALUE;

		// Second run for downward tree
		KNode secondRun = createTree(secondRunList, secondRunMap);
		BasicProgressMonitor secondLayeredRun = new BasicProgressMonitor();
		KShapeLayout secondLayout = secondRun.getData(KShapeLayout.class);
		secondLayout.setProperty(LayeredOptions.CROSSING_MINIMIZATION_SEMI_INTERACTIVE, true);
		secondLayout.setProperty(LayeredOptions.NODE_PLACEMENT_BK_FIXED_ALIGNMENT, FixedAlignment.BALANCED);
		secondLayout.setProperty(LayeredOptions.EDGE_ROUTING, EdgeRouting.POLYLINE);
		secondLayout.setProperty(LayeredOptions.DIRECTION, Direction.DOWN);
		layered.layout(secondRun, secondLayeredRun);

		KShapeLayout secondRunLayout = secondRun.getData(KShapeLayout.class);
		float secondWidth = secondRunLayout.getWidth();

		System.out.println("first: " + firstWidth);
		System.out.println("second: " + secondWidth);

		// Compute positions for first run
		KShapeLayout graphLayout = layoutGraph.getData(KShapeLayout.class);
		float xDisplacement = 0;
		if (firstWidth < secondWidth) {
			xDisplacement = (secondWidth - firstWidth) / 2;
			graphLayout.setWidth(secondWidth);
		}

		System.out.println("xDisp: " + xDisplacement);
		System.out.println("smallest: " + smallestXPos);
		for (KNode node : children) {
			if (firstRunMap.containsKey(node)) {
				KShapeLayout newLayout = firstRunMap.get(node).getData(KShapeLayout.class);
				KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
				nodeLayout.setXpos(newLayout.getXpos() + xDisplacement);
				nodeLayout.setYpos(newLayout.getYpos());
			}
		}

		// Compute positions for second run
		xDisplacement = 0;
		if (firstWidth > secondWidth) {
			xDisplacement = (firstWidth - secondWidth) / 2;
			graphLayout.setWidth(firstWidth);
		}

		KShapeLayout rootLayout = root.getData(KShapeLayout.class);
		float yDisplacement = firstRunLayout.getHeight() - rootLayout.getHeight();
		for (KNode node : children) {
			if (secondRunMap.containsKey(node)) {
				KShapeLayout newLayout = secondRunMap.get(node).getData(KShapeLayout.class);
				KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
				nodeLayout.setXpos(newLayout.getXpos() + xDisplacement);
				nodeLayout.setYpos(newLayout.getYpos() + yDisplacement);
			}
		}

		graphLayout.setHeight(yDisplacement + secondRunLayout.getHeight());
	}

	private List<KNode> sortAxis(KNode node, Comparator<KNode> comp) {
		List<KNode> successors = HierarchicalUtil.getSuccessor(node);
		
		if (successors.size() > 1) {
			List<KNode> sortedNodes = new ArrayList<KNode>();
			List<KNode> children = new ArrayList<>();
			List<KNode> nodeChildren = node.getChildren();
			boolean isBlueBox = nodeChildren.size() == 1 && !nodeChildren.get(0).getChildren().isEmpty();
			// blue boxing
			if (!isBlueBox) {
				children.addAll(nodeChildren);
			} else {
				children.addAll(nodeChildren.get(0).getChildren());
			}
			children.sort(comp);

			// map sorted nodes
			for (KNode child : children) {
				KShapeLayout shapeChild = child.getData(KShapeLayout.class);
				Integer childID = shapeChild.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_PARENT_I_D);
				if (childID != null) {

					KNode removeNode = null;
					for (KNode successor : successors) {
						KShapeLayout shapeSuccessor = successor.getData(KShapeLayout.class);
						Integer successorID = shapeSuccessor.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_I_D);
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
	
	private KNode createTree(List<KNode> nodes, Map<KNode, KNode> map) {
		KNode layoutRoot = ElkUtil.createInitializedNode();
		KNode treeRoot = ElkUtil.createInitializedNode();
		treeRoot.setParent(layoutRoot);
		KShapeLayout rootLayout = root.getData(KShapeLayout.class);
		ElkUtil.resizeNode(treeRoot, rootLayout.getWidth(), rootLayout.getHeight(), false, false);
		map.put(root, treeRoot);
		for (KNode node : nodes) {
			KNode tempNode = ElkUtil.createInitializedNode();
			tempNode.setParent(layoutRoot);
			KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
			ElkUtil.resizeNode(tempNode, nodeLayout.getWidth(), nodeLayout.getHeight(), false, false);
			map.put(node, tempNode);
		}

		for (KNode node : nodes) {
			KEdge tempEdge = ElkUtil.createInitializedEdge();
			for (KEdge edge : node.getIncomingEdges()) {
				if (edges.contains(edge)) {
					tempEdge.setSource(map.get(edge.getSource()));
				}
			}
			tempEdge.setTarget(map.get(node));
		}

		return layoutRoot;
	}

	private void buildNodeList(KNode node, List<KNode> list, Comparator<KNode> comp, Boolean first) {
//		if (!HierarchicalUtil.getSuccessor(node).isEmpty()) {
//			List<KNode> compList = sortAxis(node, comp);
			List<KNode> compList = HierarchicalUtil.sortSuccesorsByPolarCoordinate(node);
			for (KNode n : compList) {
				if (first) {
					list.add(0, n);
				} else {
					list.add(n);
				}
				buildNodeList(n, list, comp, first);
			}
//			for (KNode n : HierarchicalUtil.getSuccessor(node)) {
//				list.add(n);
//				buildNodeList(n, list);
//			}
//		}
	}

}
