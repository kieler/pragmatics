package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.alg.mrtree.MrTree;
import org.eclipse.elk.alg.mrtree.TreeLayoutProvider;
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KNode;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

public class HierarchicalTreeLayoutProvider extends AbstractLayoutProvider {

	private List<KNode> children = new ArrayList<KNode>();
	private static List<KEdge> edges = new ArrayList<KEdge>();
	private static Map<KNode, ArrayList<KEdge>> edgeMap = new HashMap<KNode, ArrayList<KEdge>>();

	@Override
	public void layout(KNode layoutGraph, IElkProgressMonitor progressMonitor) {
		System.out.println("original: " + layoutGraph.getChildren().size());
		edges.clear();
		edgeMap.clear();
		children.clear();
		children = layoutGraph.getChildren();
		// Identify root node
		KNode root = null;
		for (KNode node : children) {
			ArrayList<KEdge> nodeEdges = new ArrayList<KEdge>();
			for (KEdge edge : node.getIncomingEdges()) {
				if (children.contains(edge.getTarget())) {
//					edges.add(edge);
					nodeEdges.add(edge);
				}
			}
			edgeMap.put(node, nodeEdges);
			Boolean isRoot = true;
			for (KEdge edge : node.getIncomingEdges()) {
				if (children.contains(edge.getTarget())) {
					isRoot = false;
				}
			}
			if (isRoot) {
				// System.out.println("Debug");
				root = node;
			}
		}

		List<KNode> secondHierarchyNodes = new ArrayList<KNode>();
		for (KEdge edge : root.getOutgoingEdges()) {
			if (children.contains(edge.getTarget())) {
				secondHierarchyNodes.add(edge.getTarget());
			}
		}

		// System.out.println(secondHierarchyNodes.size());
		int treeseperator = secondHierarchyNodes.size() / 2;
		Copier copier_ = new Copier();
		KNode firstRun = (KNode) copier_.copy(layoutGraph);
//		copier_.copyReferences();
		List<KNode> copiedchildren = new ArrayList<KNode>();
		// copier.copyReferences();
		// for (int i = 0; i < treeseperator; i++) {
		for (KNode node : layoutGraph.getChildren()) {
			// KNode copy = (KNode) copier.copy(secondHierarchyNodes.get(i));
			Copier copier = new Copier();
			KNode copy = (KNode) copier.copy(node);
			copier.copyReferences();
			copiedchildren.add(copy);
//			for (KEdge e : edgeMap.get(node)) {
//				e.setSource(copy);
//				e.setTarget(copiedchildren.get(1));
//			}
			// copy.setParent(firstRun);
			// firstRun.add(secondHierarchyNodes.get(i));
		}
		
		firstRun.getChildren().clear();
		firstRun.getChildren().addAll(copiedchildren);
//		for (KEdge edge : edges) {
//			edge.setSource(firstRun.getChildren().get(1));
//			edge.setTarget(firstRun.getChildren().get(2));
//		}
		// System.out.println(firstRun.getChildren());
		
		System.out.println(firstRun.getChildren().size());
		
		for (KNode node : copiedchildren) {
			for (KEdge edge : node.getIncomingEdges()) {
				System.out.println(edge.getTarget());
				if (firstRun.getChildren().contains(edge.getSource())) {
					edges.add(edge);
				}
			}
		}
		System.out.println(edges);
		
		KNode copiedRoot = null;
		for (KNode node : firstRun.getChildren()) {
			Boolean isRoot = true;
			for (KEdge edge : node.getIncomingEdges()) {
				if (children.contains(edge.getTarget())) {
					isRoot = false;
				}
			}
			if (isRoot) {
				copiedRoot = node;
			}
		}
		System.out.println("root: " + copiedRoot);
		
		for (KNode node : firstRun.getChildren()) {
			for (KEdge edge : node.getIncomingEdges()) {
//				System.out.println(edge);
//				System.out.println(edge.getTarget().getParent());
				if (!copiedchildren.contains(edge.getSource())) {
					System.out.println(edge);
				}
				if (edge.getSource() == copiedRoot) {
					System.out.println("Hallo");
				}
			}
		}

		TreeLayoutProvider tree = new TreeLayoutProvider();
		BasicProgressMonitor firstTreeRun = new BasicProgressMonitor();
		KShapeLayout diagramLayout = firstRun.getData(KShapeLayout.class);
//		diagramLayout.setProperty(MrTreeOptions., );
		tree.layout(firstRun, firstTreeRun);


		BasicProgressMonitor secondTreeRun = new BasicProgressMonitor();
		// tree.layout(layoutGraph, secondTreeRun);

		// Merge Layout
		
//		tree.layout(layoutGraph, firstTreeRun);
	}

}
