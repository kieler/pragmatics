package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

public class HierarchicalEdgeRouting {

	public static void routeEdgesCenterToCenter(ElkNode graph) {
		for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(graph)) {

			ElkNode target = ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0));
			if (!graph.getChildren().contains(target)) {

				double sourceX = graph.getX() + graph.getWidth() / 2;
				double sourceY = graph.getY() + graph.getHeight() / 2;

				double targetX = target.getX() + target.getWidth() / 2;
				double targetY = target.getY() + target.getHeight() / 2;

				ElkEdgeSection section = ElkGraphUtil.firstEdgeSection(edge, true, true);
				section.setStartLocation(sourceX, sourceY);
				section.setEndLocation(targetX, targetY);

				routeEdgesCenterToCenter(target);
			}
		}
	}

	public static void routeEdgesCornerToCorner(ElkNode graph) {
		for (ElkEdge edge : graph.getOutgoingEdges()) {
			ElkNode target = ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0));
			if (!graph.getChildren().contains(target)) {
				double sourceX = graph.getX();
				double sourceY = graph.getY();

				double targetX = target.getX();
				double targetY = target.getY();

				ElkEdgeSection section = ElkGraphUtil.firstEdgeSection(edge, true, true);
				section.setStartLocation(sourceX, sourceY);
				section.setEndLocation(targetX, targetY);

				routeEdgesCornerToCorner(target);
			}

		}
	}

	public static void drawExplosionLines(ElkNode root) {
		routeEdgesCornerToCorner(root);
		bendEdgesToExplosionLines(root);
	}

	public static void bendEdgesToExplosionLines(ElkNode root) {
		List<ElkNode> copiedChildren = new ArrayList<>();
		List<ElkNode> children = root.getChildren();
		boolean isBlueBox = children.size() == 1 && !children.get(0).getChildren().isEmpty();
		// blue boxing
		if (!isBlueBox) {
			copiedChildren.addAll(children);
		} else {
			copiedChildren.addAll(children.get(0).getChildren());
		}

		// take a look at all the successors of the node
		for (ElkNode successor : HierarchicalUtil.getSuccessor(root)) {
			Integer id = successor.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_I_D);

			// look at the incoming edges
			for (ElkEdge edge : successor.getIncomingEdges()) {
				// only the edge coming from the original node shall be
				// considered
				if (edge.getSources().get(0) == root || root.getChildren().contains(edge.getSources().get(0))) {
					ElkNode childFound = null;
					for (ElkNode child : copiedChildren) {
						Integer idParent = child.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_PARENT_I_D);

						if (idParent != null && id.equals(idParent)) {
							childFound = child;
							double xPos = root.getX() + child.getX() + child.getWidth() / 2;
							double yPos = root.getY() + child.getY() + child.getHeight() / 2;

							ElkEdgeSection section = ElkGraphUtil.firstEdgeSection(edge, false, false);
							section.setStartLocation(xPos, yPos);

						}
					}
					copiedChildren.remove(childFound);

				}
			}

			bendEdgesToExplosionLines(successor);
		}

	}
}
