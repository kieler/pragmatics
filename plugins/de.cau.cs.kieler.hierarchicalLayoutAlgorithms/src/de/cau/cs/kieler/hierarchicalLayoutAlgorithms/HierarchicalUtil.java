package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KNode;

public class HierarchicalUtil {

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
	
	public static KNode findRoot(KNode graph) {
		for (KNode child : graph.getChildren()) {
			if (child.getIncomingEdges().isEmpty()) {
				return child;
			}
		}
		return null;
	}
	
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
	
	public static List<KNode> sortSuccesorsByPolarCoordinate(KNode node) {
		List<KNode> successors = HierarchicalUtil.getSuccessor(node);

		if (successors.size() > 1) {
			List<KNode> children = new ArrayList<>();
			children.addAll(node.getChildren());
			List<KNode> sortedSuccessors = new ArrayList<>();

			float medianX = 0;
			float medianY = 0;
			int nodeCounter = 0;
			for (KNode successor : successors) {
				if (!successor.getChildren().isEmpty()) {
					KShapeLayout sucShape = successor.getData(KShapeLayout.class);
					medianX += sucShape.getXpos() + sucShape.getWidth() / 2;
					medianY += sucShape.getYpos() + sucShape.getHeight() / 2;
					nodeCounter++;
				}
			}
			medianX = medianX / nodeCounter; //TODO
			medianY = medianY / nodeCounter;

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
