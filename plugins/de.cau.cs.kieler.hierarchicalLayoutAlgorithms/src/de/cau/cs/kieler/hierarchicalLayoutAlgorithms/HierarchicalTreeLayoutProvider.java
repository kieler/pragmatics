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
	private static Map<KNode, ArrayList<KEdge>> edgeMap = new HashMap<KNode, ArrayList<KEdge>>();
	private static Map<KNode, KNode> firstRunMap = new HashMap<KNode, KNode>();
	private static Map<KNode, KNode> secondRunMap = new HashMap<KNode, KNode>();
	private static KNode root;

	@Override
	public void layout(KNode layoutGraph, IElkProgressMonitor progressMonitor) {
		edges.clear();
		edgeMap.clear();
		children.clear();
		firstRunMap.clear();
		secondRunMap.clear();
		children = layoutGraph.getChildren();

		for (KNode node : children) {
			for (KEdge edge : node.getOutgoingEdges()) {
				if (children.contains(edge.getTarget())) {
					edges.add(edge);
				}
			}
		}

		// Identify root node
		root = HierarchicalUtil.findRoot(layoutGraph);

		List<KNode> secondHierarchyNodes = new ArrayList<KNode>();
		for (KEdge edge : root.getOutgoingEdges()) {
			if (children.contains(edge.getTarget())) {
				secondHierarchyNodes.add(edge.getTarget());
			}
		}

		int treeseperator = secondHierarchyNodes.size() / 2;
		// TODO seperate secondHierarchy and add all reachable nodes
		KNode firstRun = createTree(children, firstRunMap);
		TreeLayoutProvider tree = new TreeLayoutProvider();
		BasicProgressMonitor firstTreeRun = new BasicProgressMonitor();
		KShapeLayout diagramLayout = firstRun.getData(KShapeLayout.class);
		// diagramLayout.setProperty(org.eclipse.elk.alg.mrtree.properties.MrTreeOptions.DIRECTION, "Top");
		tree.layout(firstRun, firstTreeRun);

		// TODO improve this
		for (KNode node : children) {
			if (firstRunMap.containsKey(node)) {
				KShapeLayout newLayout = firstRunMap.get(node).getData(KShapeLayout.class);
				KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
//				System.out.println("original " + nodeLayout.getYpos());
//				System.out.println("new " + newLayout.getYpos());
				nodeLayout.setXpos(newLayout.getXpos());
				nodeLayout.setYpos(newLayout.getYpos());
			} else {
				KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
				nodeLayout.setXpos(1500);
				nodeLayout.setYpos(1500);
			}
		}

		// TODO configure second Tree run
		KNode secondRun = createTree(children, secondRunMap);
		BasicProgressMonitor secondTreeRun = new BasicProgressMonitor();
//		diagramLayout.setProperty(org.eclipse.elk.alg.mrtree.properties.MrTreeOptions.DIRECTION, "Bottom");
//		tree.layout(secondRun, secondTreeRun);

		// Merge Layout
		// TODO displacement x and y axis
		// TODO set appropriate height and width
		KShapeLayout graphLayout = layoutGraph.getData(KShapeLayout.class);
		graphLayout.setWidth(2000);
		graphLayout.setHeight(2000);

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
			if (!(node == root)) {
				KNode tempNode = ElkUtil.createInitializedNode();
				tempNode.setParent(layoutRoot);
				KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
				ElkUtil.resizeNode(tempNode, nodeLayout.getWidth(), nodeLayout.getHeight(), false, false);
				map.put(node, tempNode);
			}
		}
		
		for (KNode node : nodes) {
			if (!(node == root)) {
				KEdge tempEdge = ElkUtil.createInitializedEdge();
				for (KEdge edge : node.getIncomingEdges()) {
					if (edges.contains(edge)) {
						tempEdge.setSource(map.get(edge.getSource()));
					}
				}
				tempEdge.setTarget(map.get(node));
			}
		}
		
		return layoutRoot;
	}

}
