package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.alg.mrtree.TreeLayoutProvider;
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.klayoutdata.KShapeLayout;
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

	@Override
	public void layout(KNode layoutGraph, IElkProgressMonitor progressMonitor) {
		children.clear();
		edges.clear();
		firstRunMap.clear();
		secondRunMap.clear();

		children = layoutGraph.getChildren();
		edges = HierarchicalUtil.getHierarchicalEdges(layoutGraph);
		root = HierarchicalUtil.findRoot(layoutGraph);
		List<KNode> secondHierarchyNodes = HierarchicalUtil.getSuccessor(root);

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
			buildNodeList(node, tempList);
		}
		firstRunList.addAll(tempList);

		tempList.clear();
		for (KNode node : secondRunList) {
			buildNodeList(node, tempList);
		}
		secondRunList.addAll(tempList);

		// First run for upward tree
		KNode firstRun = createTree(firstRunList, firstRunMap);
		TreeLayoutProvider tree = new TreeLayoutProvider();
		BasicProgressMonitor firstTreeRun = new BasicProgressMonitor();
		tree.layout(firstRun, firstTreeRun);

		// TODO Compute correct displacement for x and y axis
		float yDisplacement = 0;
		float firstWidth = 0;
		float smallestXPos = Float.MAX_VALUE;
		for (KNode node : children) {
			if (firstRunMap.containsKey(node)) {
				KShapeLayout nodeLayout = firstRunMap.get(node).getData(KShapeLayout.class);
				if (nodeLayout.getYpos() + nodeLayout.getHeight() > yDisplacement) {
					yDisplacement = nodeLayout.getYpos() + nodeLayout.getHeight();
				}
				if (nodeLayout.getXpos() + nodeLayout.getWidth() > firstWidth) {
					firstWidth = nodeLayout.getXpos() + nodeLayout.getWidth();
				}
				if (nodeLayout.getXpos() < smallestXPos) {
					smallestXPos = nodeLayout.getXpos();
				}
			}
		}

		// Second run for downward tree
		KNode secondRun = createTree(secondRunList, secondRunMap);
		BasicProgressMonitor secondTreeRun = new BasicProgressMonitor();
		tree.layout(secondRun, secondTreeRun);

		float secondWidth = 0;
		for (KNode node : children) {
			if (secondRunMap.containsKey(node)) {
				KShapeLayout nodeLayout = secondRunMap.get(node).getData(KShapeLayout.class);
				if (nodeLayout.getXpos() + nodeLayout.getWidth() > secondWidth) {
					secondWidth = nodeLayout.getXpos() + nodeLayout.getWidth();
				}
				if (nodeLayout.getXpos() < smallestXPos) {
					smallestXPos = nodeLayout.getXpos();
				}
			}
		}
		System.out.println("first: " + firstWidth);
		System.out.println("second: " + secondWidth);

		// Compute positions for first run
		float xDisplacement = 0;
		if (firstWidth < secondWidth) {
			xDisplacement = secondWidth - firstWidth;
		}

		System.out.println("xDisp: " + xDisplacement);
		System.out.println("smallest: " + smallestXPos);
		for (KNode node : children) {
			if (firstRunMap.containsKey(node)) {
				KShapeLayout newLayout = firstRunMap.get(node).getData(KShapeLayout.class);
				KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
				nodeLayout.setXpos(newLayout.getXpos() + xDisplacement - smallestXPos);
				nodeLayout.setYpos(-(newLayout.getYpos() + newLayout.getHeight()) + yDisplacement);
			}
		}

		// Compute positions for second run
		if (firstWidth > secondWidth) {

		}

		KShapeLayout rootLayout = root.getData(KShapeLayout.class);
		yDisplacement -= rootLayout.getHeight();
		for (KNode node : children) {
			if (secondRunMap.containsKey(node)) {
				KShapeLayout newLayout = secondRunMap.get(node).getData(KShapeLayout.class);
				KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
				System.out.println("before: " + newLayout.getXpos());
				nodeLayout.setXpos(newLayout.getXpos() + xDisplacement - smallestXPos);
				System.out.println("after: " + nodeLayout.getXpos());
				nodeLayout.setYpos(newLayout.getYpos() + yDisplacement);
			}
		}

		// TODO set appropriate height and width
		KShapeLayout graphLayout = layoutGraph.getData(KShapeLayout.class);
		graphLayout.setWidth(3000);
		graphLayout.setHeight(3000);
//		rootLayout.setYpos(0);
//		rootLayout.setXpos(0);

		HierarchicalEdgeRouting.drawExplosionLines(root);
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

	private void buildNodeList(KNode node, List<KNode> list) {
		if (!HierarchicalUtil.getSuccessor(node).isEmpty()) {
			for (KNode n : HierarchicalUtil.getSuccessor(node)) {
				list.add(n);
				buildNodeList(n, list);
			}
		}
	}

}
