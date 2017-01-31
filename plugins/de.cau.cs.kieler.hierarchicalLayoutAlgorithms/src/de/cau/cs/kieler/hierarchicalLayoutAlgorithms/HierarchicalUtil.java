package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KNode;

public class HierarchicalUtil {

	/**
	 * Computes a List of nodes that are the neighbours with outgoing edges.
	 * 
	 * @param node
	 * @return List of neighbours with outgoing edges.
	 */
	public static List<KNode> getSuccessor(KNode node) {
		List<KNode> children = new ArrayList<>();
		for (KEdge outgoingEdge : node.getOutgoingEdges()) {
			KNode target = outgoingEdge.getTarget();
			if (!node.getChildren().contains(target)) {
				children.add(target);
			}
		}
		return children;
	}

	/**
	 * Computes the root node of the graph.
	 * 
	 * @param graph
	 * @return root node of graph.
	 */
	public static KNode findRoot(KNode graph) {
		for (KNode child : graph.getChildren()) {
			if (child.getIncomingEdges().isEmpty()) {
				return child;
			}
		}
		return null;
	}

	/**
	 * Computes the number of Leafs that a node has.
	 * 
	 * @param node
	 * @return number of leafs.
	 */
	public static int getNumberOfLeafs(KNode node) {
		int leafs = 0;
		if (HierarchicalUtil.getSuccessor(node).isEmpty()) {
			return 1;
		} else {
			for (KNode child : HierarchicalUtil.getSuccessor(node)) {
				leafs += getNumberOfLeafs(child);
			}
		}
		return leafs;
	}

	/**
	 * Computes a List of Edges that are the Hierarchical edges of the Graph.
	 * 
	 * @param graph
	 * @return List of Hierarchical Edges.
	 */
	public static List<KEdge> getHierarchicalEdges(KNode graph) {
		List<KEdge> edges = new ArrayList<KEdge>();
		for (KNode node : graph.getChildren()) {
			for (KEdge edge : node.getOutgoingEdges()) {
				if (graph.getChildren().contains(edge.getTarget())) {
					edges.add(edge);
				}
			}
		}
		return edges;
	}

	public static List<KNode> sortSuccesorsByPolarCoordinate(KNode node) {
		List<KNode> successors = HierarchicalUtil.getSuccessor(node);

		if (successors.size() > 1) {
			List<KNode> children = new ArrayList<>();
			List<KNode> rootChildren = node.getChildren();
			boolean isBlueBox = rootChildren.size() == 1 && !rootChildren.get(0).getChildren().isEmpty();
			// blue boxing
			if (!isBlueBox) {
				children.addAll(rootChildren);
			} else {
				children.addAll(rootChildren.get(0).getChildren());
			}
			List<KNode> sortedSuccessors = new ArrayList<>();

			// center of the node, as center of the polar coordinates
			KShapeLayout nodeShape = node.getData(KShapeLayout.class);
			float centerX = nodeShape.getXpos(); // + nodeShape.getWidth()/2;
			float centerY = nodeShape.getYpos(); // + nodeShape.getHeight()/2;

			// sort children
			Comparator<KNode> comparator = new Comparator<KNode>() {

				@Override
				public int compare(KNode child1, KNode child2) {
					KShapeLayout node1Shape = child1.getData(KShapeLayout.class);
					float xPos1 = node1Shape.getXpos() + node1Shape.getWidth() / 2 + centerX;
					float yPos1 = node1Shape.getYpos() + node1Shape.getHeight() / 2 + centerY;
					double arc1 = Math.atan2(yPos1, xPos1);
					if (arc1 < 0) {
						arc1 += 2 * Math.PI;
					}

					KShapeLayout node2Shape = child2.getData(KShapeLayout.class);
					float xPos2 = node2Shape.getXpos() + node2Shape.getWidth() / 2 + centerX;
					float yPos2 = node2Shape.getYpos() + node2Shape.getHeight() / 2 + centerY;
					double arc2 = Math.atan2(yPos2, xPos2);
					if (arc2 < 0) {
						arc2 += 2 * Math.PI;
					}

					if (arc1 > arc2) {
						return -1;
					} else if (arc1 == arc2) {
						return 0;
					} else {
						return 1;
					}
				}
			};
			children.sort(comparator);

			// map child to its successor
			for (KNode child : children) {
				KShapeLayout shapeChild = child.getData(KShapeLayout.class);
				Integer childID = shapeChild.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_PARENT_I_D);
				if (childID != null) {

					KNode removeNode = null;
					for (KNode successor : successors) {
						KShapeLayout shapeSuccessor = successor.getData(KShapeLayout.class);
						Integer successorID = shapeSuccessor.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_I_D);
						if (childID.equals(successorID)) {
							sortedSuccessors.add(successor);
							removeNode = successor;
							break;
						}
					}
					successors.remove(removeNode);
				}
			}

			return sortedSuccessors;
		} else {
			return successors;
		}

	}
}
