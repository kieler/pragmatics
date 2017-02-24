package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import com.google.common.math.DoubleMath;

public class HierarchicalUtil {

	/**
	 * Computes a List of nodes that are the neighbours with outgoing edges.
	 * 
	 * @param node
	 * @return List of neighbors with outgoing edges.
	 */
	public static List<ElkNode> getSuccessor(ElkNode node) {
		List<ElkNode> children = new ArrayList<>();
		for (ElkEdge outgoingEdge : ElkGraphUtil.allOutgoingEdges(node)) {
			ElkNode target = ElkGraphUtil.connectableShapeToNode(outgoingEdge.getTargets().get(0));
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
	public static ElkNode findRoot(ElkNode graph) {
		for (ElkNode child : graph.getChildren()) {
			if (!ElkGraphUtil.allIncomingEdges(child).iterator().hasNext()) {
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
	public static int getNumberOfLeafs(ElkNode node) {
		int leafs = 0;
		if (HierarchicalUtil.getSuccessor(node).isEmpty()) {
			return 1;
		} else {
			for (ElkNode child : HierarchicalUtil.getSuccessor(node)) {
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
	public static List<ElkEdge> getHierarchicalEdges(ElkNode graph) {
		List<ElkEdge> edges = new ArrayList<ElkEdge>();
		for (ElkNode node : graph.getChildren()) {
			for (ElkEdge outgoingEdge : ElkGraphUtil.allOutgoingEdges(node)) {
				ElkNode target = ElkGraphUtil.connectableShapeToNode(outgoingEdge.getTargets().get(0));
				if (graph.getChildren().contains(target)) {
					edges.add(outgoingEdge);
				}
			}
		}
		return edges;
	}

	public static List<ElkNode> sortSuccesorsByPolarCoordinate(ElkNode node) {
		List<ElkNode> successors = HierarchicalUtil.getSuccessor(node);

		if (successors.size() > 1) {
			List<ElkNode> children = new ArrayList<>();
			List<ElkNode> rootChildren = node.getChildren();
			boolean isBlueBox = rootChildren.size() == 1 && !rootChildren.get(0).getChildren().isEmpty();
			// blue boxing
			if (!isBlueBox) {
				children.addAll(rootChildren);
			} else {
				children.addAll(rootChildren.get(0).getChildren());
			}
			List<ElkNode> sortedSuccessors = new ArrayList<>();

			// center of the node, as center of the polar coordinates
			double centerX = node.getX(); // + nodeShape.getWidth()/2;
			double centerY = node.getY(); // + nodeShape.getHeight()/2;

			// sort children
			Comparator<ElkNode> comparator = new Comparator<ElkNode>() {

				@Override
				public int compare(ElkNode child1, ElkNode child2) {
					double xPos1 = child1.getX() + child1.getWidth() / 2 + centerX;
					double yPos1 = child1.getY() + child1.getHeight() / 2 + centerY;
					double arc1 = Math.atan2(yPos1, xPos1);
					if (arc1 < 0) {
						arc1 += 2 * Math.PI;
					}

					double xPos2 = child2.getX() + child2.getWidth() / 2 + centerX;
					double yPos2 = child2.getY() + child2.getHeight() / 2 + centerY;
					double arc2 = Math.atan2(yPos2, xPos2);
					if (arc2 < 0) {
						arc2 += 2 * Math.PI;
					}

					return DoubleMath.fuzzyCompare(arc1, arc2, 1e-10);
				}
			};
			children.sort(comparator);

			// map child to its successor
			for (ElkNode child : children) {
				Integer childID = child.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_PARENT_I_D);
				if (childID != null) {

					ElkNode removeNode = null;
					for (ElkNode successor : successors) {
						Integer successorID = successor.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_I_D);
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

	/**
	 * Retrieve the original node of a copy node.
	 * 
	 * @param parent
	 * @param successor
	 * @return
	 */
	public static ElkNode getOriginalNode(ElkNode parent, ElkNode successor) {
		Integer successorID = successor.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_I_D);

		for (ElkNode child : parent.getChildren()) {
			Integer childID = child.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_PARENT_I_D);
			if (successorID.equals(childID)) {
				return child;
			}
		}
		return null;
	}

	/**
	 * Calculates the distance of the given node to the root.
	 * 
	 * @param node
	 * @param root
	 * @return Distance of node to root.
	 */
	public static int getDepths(ElkNode node, ElkNode root) {
		int depth = 0;
		ElkNode parent = null;
		if (node != root) {
			while (parent != root) {
				for (ElkEdge edge : ElkGraphUtil.allIncomingEdges(node)) {
					ElkNode source = ElkGraphUtil.connectableShapeToNode(edge.getSources().get(0));
					if (!node.getChildren().contains(source)) {
						parent = source;
					}
				}
				node = parent;
				depth++;
			}
		}
		return depth;
	}
}
