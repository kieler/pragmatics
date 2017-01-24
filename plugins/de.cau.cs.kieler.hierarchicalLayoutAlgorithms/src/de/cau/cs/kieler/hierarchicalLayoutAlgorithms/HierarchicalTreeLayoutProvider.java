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
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

public class HierarchicalTreeLayoutProvider extends AbstractLayoutProvider {

	private List<KNode> children = new ArrayList<KNode>();
	private static List<KEdge> edges = new ArrayList<KEdge>();
	private static Map<KNode, ArrayList<KEdge>> edgeMap = new HashMap<KNode, ArrayList<KEdge>>();
	private static Map<KNode, KNode> firstRunMap = new HashMap<KNode, KNode>();

	@Override
	public void layout(KNode layoutGraph, IElkProgressMonitor progressMonitor) {
		System.out.println("original: " + layoutGraph.getChildren().size());
		edges.clear();
		edgeMap.clear();
		children.clear();
		firstRunMap.clear();
		children = layoutGraph.getChildren();

		for (KNode node : children) {
			for (KEdge edge : node.getOutgoingEdges()) {
				if (children.contains(edge.getTarget())) {
					edges.add(edge);
				}
			}
		}

		// Identify root node
		KNode root = HierarchicalUtil.findRoot(layoutGraph);

		List<KNode> secondHierarchyNodes = new ArrayList<KNode>();
		for (KEdge edge : root.getOutgoingEdges()) {
			if (children.contains(edge.getTarget())) {
				secondHierarchyNodes.add(edge.getTarget());
			}
		}

		// System.out.println(secondHierarchyNodes.size());
		int treeseperator = secondHierarchyNodes.size() / 2;
		KNode firstRun = createTree(root);
		// Copier copier_ = new Copier();
		// KNode firstRun = (KNode) copier_.copy(layoutGraph);
		// List<KNode> copiedchildren = new ArrayList<KNode>();
		// copier_.copyReferences();
		// for (int i = 0; i < treeseperator; i++) {
		// for (KNode node : layoutGraph.getChildren()) {
		// KNode copy = (KNode) copier.copy(secondHierarchyNodes.get(i));
		// Copier copier = new Copier();
		// KNode copy = (KNode) copier.copy(node);
		// copier.copyReferences();
		// copiedchildren.add(copy);
		// for (KEdge e : edgeMap.get(node)) {
		// e.setSource(copy);
		// e.setTarget(copiedchildren.get(1));
		// }
		// copy.setParent(firstRun);
		// firstRun.add(secondHierarchyNodes.get(i));
		// }

		// firstRun.getChildren().clear();
		// firstRun.getChildren().addAll(copiedchildren);
		// for (KEdge edge : edges) {
		// edge.setSource(firstRun.getChildren().get(1));
		// edge.setTarget(firstRun.getChildren().get(2));
		// }
		// System.out.println(firstRun.getChildren());

		// System.out.println(firstRun.getChildren().size());

		// System.out.println("Successors");

		// for (KNode node : copiedchildren) {
		// for (KEdge edge : node.getIncomingEdges()) {
		// if (firstRun.getChildren().contains(edge.getTarget())) {
		// edges.add(edge);
		// }
		// }
		// System.out.println(HierarchicalUtil.getSuccessor(node));
		// }
		// System.out.println(edges);
		//
		// KNode copiedRoot = null;
		// for (KNode node : firstRun.getChildren()) {
		// Boolean isRoot = true;
		// for (KEdge edge : node.getIncomingEdges()) {
		// if (firstRun.getChildren().contains(edge.getTarget())) {
		// System.out.println("Hallo");
		// isRoot = false;
		// }
		// }
		// if (isRoot) {
		// copiedRoot = node;
		// }
		// }
		// System.out.println("root: " + copiedRoot);
		//
		// for (KNode node : firstRun.getChildren()) {
		// for (KEdge edge : node.getIncomingEdges()) {
		// System.out.println(edge);
		// System.out.println(edge.getTarget().getParent());
		// if (!copiedchildren.contains(edge.getSource())) {
		// System.out.println(edge);
		// }
		// if (edge.getSource() == copiedRoot) {
		// System.out.println("Hallo");
		// }
		// }
		// }

		System.out.println(firstRun);
		TreeLayoutProvider tree = new TreeLayoutProvider();
		BasicProgressMonitor firstTreeRun = new BasicProgressMonitor();
		KShapeLayout diagramLayout = firstRun.getData(KShapeLayout.class);
//		 diagramLayout.setProperty(MrTreeOptions., );
		tree.layout(firstRun, firstTreeRun);
		
		for (KNode node : children) {
			if (firstRunMap.containsKey(node)) {
				KShapeLayout newLayout = firstRunMap.get(node).getData(KShapeLayout.class);
				KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
				System.out.println("original " + nodeLayout.getYpos());
				System.out.println("new " + newLayout.getYpos());
				nodeLayout.setXpos(newLayout.getXpos());
				nodeLayout.setYpos(newLayout.getYpos());
			} else {
				KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
				nodeLayout.setXpos(1500);
				nodeLayout.setYpos(1500);
			}
		}
		

		BasicProgressMonitor secondTreeRun = new BasicProgressMonitor();
		// tree.layout(layoutGraph, secondTreeRun);

		// Merge Layout

//		 tree.layout(layoutGraph, secondTreeRun);
		KShapeLayout graphLayout = layoutGraph.getData(KShapeLayout.class);
		graphLayout.setWidth(2000);
		graphLayout.setHeight(2000);
		
		HierarchicalEdgeRouting.drawExplosionLines(root);
	}

	private KNode createTree(KNode root) {
		KNode layoutRoot = ElkUtil.createInitializedNode();
//		KShapeLayout diagramLayout = layoutRoot.getData(KShapeLayout.class);
//		diagramLayout.setWidth(1500);
//		diagramLayout.setHeight(1500);
		KNode treeRoot = ElkUtil.createInitializedNode();
		treeRoot.setParent(layoutRoot);
//		KShapeLayout treeLayout = treeRoot.getData(KShapeLayout.class);
		KShapeLayout rootLayout = root.getData(KShapeLayout.class);
//		treeLayout.setWidth(rootLayout.getWidth());
//		treeLayout.setHeight(rootLayout.getHeight());
		ElkUtil.resizeNode(treeRoot, rootLayout.getWidth(), rootLayout.getHeight(), false, false);
		List<KNode> secondHierarchy = HierarchicalUtil.getSuccessor(root);
		firstRunMap.put(root, treeRoot);
		for (KNode node : secondHierarchy) {
			KNode tempNode = ElkUtil.createInitializedNode();
			tempNode.setParent(layoutRoot);
			KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
//			tempLayout.setWidth(nodeLayout.getWidth());
//			tempLayout.setHeight(nodeLayout.getHeight());
			ElkUtil.resizeNode(tempNode, nodeLayout.getWidth(), nodeLayout.getHeight(), false, false);
//			KShapeLayout tempLayout = tempNode.getData(KShapeLayout.class);
//			System.out.println("tempwidth " + tempLayout.getWidth());
//			System.out.println("originalwidth " + nodeLayout.getWidth());
			KEdge tempEdge = ElkUtil.createInitializedEdge();
			tempEdge.setSource(root);
			tempEdge.setTarget(tempNode);
			firstRunMap.put(node, tempNode);
		}
		return layoutRoot;
	}

}
