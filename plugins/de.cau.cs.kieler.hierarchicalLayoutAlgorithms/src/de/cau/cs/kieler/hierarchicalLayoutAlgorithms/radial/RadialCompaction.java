package de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalUtil;

public class RadialCompaction {


	public void contraction(List<ElkNode> successors) {
		if (!successors.isEmpty()) {
			boolean isOverlapping = overlapping(successors);
			while (!isOverlapping) {
				for (ElkNode successor : successors) {

					double xPos = successor.getX() + successor.getWidth() / 2;
					double yPos = successor.getY() + successor.getHeight() / 2;

					for (ElkEdge edge : successor.getIncomingEdges()) {
						if (!edge.getSources().get(0).equals(successor)) {
							ElkEdgeSection section = ElkGraphUtil.firstEdgeSection(edge, false, false);
							double edgeX = section.getStartX();
							double edgeY = section.getStartY();

							double x = xPos - edgeX;
							double y = yPos - edgeY;
							double length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
							x *= 10 / length;
							y *= 10 / length;

							xPos -= x;
							yPos -= y;
						}
					}

					successor.setX(xPos - successor.getWidth() / 2);
					successor.setY(yPos - successor.getHeight() / 2);
				}
				isOverlapping = overlapping(successors);
			}

			// undo last step
			if (isOverlapping) {
				for (ElkNode successor : successors) {

					double xPos = successor.getX() + successor.getWidth() / 2;
					double yPos = successor.getY() + successor.getHeight() / 2;

					for (ElkEdge edge : successor.getIncomingEdges()) {
						if (!edge.getSources().get(0).equals(successor)) {
							ElkEdgeSection section = ElkGraphUtil.firstEdgeSection(edge, false, false);
							double edgeX = section.getStartX();
							double edgeY = section.getStartY();

							double x = xPos - edgeX;
							double y = yPos - edgeY;
							double length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
							x *= 10 / length;
							y *= 10 / length;

							xPos += x;
							yPos += y;
						}
					}

					successor.setX(xPos - successor.getWidth() / 2);
					successor.setY(yPos - successor.getHeight() / 2);
				}
			}
			contraction(nextLevel(successors));
		}
	}
	
	private boolean overlapping(List<ElkNode> successors) {

		for (int i = 0; i < successors.size(); i++) {

			ElkNode rNode = successors.get(i);
			double rightX = rNode.getX();
			double rightY = rNode.getY();

			ElkNode leftNode;
			if (i == successors.size() - 1) {
				leftNode = successors.get(0);
			} else {
				leftNode = successors.get(i + 1);
			}

			double leftX = leftNode.getX();
			double leftY = leftNode.getY();

			ElkNode predecessor = ElkGraphUtil
					.connectableShapeToNode(successors.get(i).getIncomingEdges().get(0).getSources().get(0));
			double preX = predecessor.getX();
			double preY = predecessor.getY();

			double xPos = rNode.getX() + rNode.getWidth() / 2;
			double yPos = rNode.getY() + rNode.getHeight() / 2;
			double arc = Math.atan2(yPos, xPos);
			if (arc < 0) {
				arc += 2 * Math.PI;
			}

			if (arc < 0.5 * Math.PI) {// 90°
				// lefts lower left corner and rights upper right corner
				if (leftX + leftNode.getWidth() > rightX && leftY < rightY + rNode.getHeight()) {
					return true;
				}
				if (preX + predecessor.getWidth() > rightX && preY + predecessor.getHeight() > rightY) {
					return true;
				}
			} else if (arc < Math.PI) {// 180°
				// lefts upper left corner and rights lower right corner
				if (leftX + leftNode.getWidth() > rightX && leftY+ leftNode.getHeight() > rightY) {
					return true;
				}
				// pres lower left corner and rights upper right corner
				if (preX < rightX + rNode.getWidth() && preY + predecessor.getHeight() > rightY) {
					return true;
				}
			} else if (arc < 1.5 * Math.PI) {// 270°
				// lefts upper right corner and rights lower left corner
				if (leftX < rightX + rNode.getWidth() && leftY + leftNode.getHeight() > rightY) {
					return true;
				}
				if (preX < rightX + rNode.getWidth() && preY < rightY + rNode.getHeight()) {
					return true;
				}
			} else if (arc < 2 * Math.PI) {// 360°

				// lefts lower right corner and rights upper left corner
				if (leftX < rightX + rNode.getWidth() && leftY < rightY + rNode.getHeight()) {
					return true;
				}
				if (preX + predecessor.getWidth() > rightX && preY < rightY + rNode.getHeight()) {
					return true;
				}
			}

		}
		return false;
	}

	private List<ElkNode> nextLevel(List<ElkNode> nodes) {
		List<ElkNode> successors = new ArrayList<ElkNode>();
		for (ElkNode node : nodes) {
			successors.addAll(HierarchicalUtil.sortSuccesorsByPolarCoordinate(node));
		}
		return successors;

	}

}
