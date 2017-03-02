/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

/**
 * Edge routing for Hierarchical diagrams.
 * 
 * @author ybl
 *
 */
public class HierarchicalEdgeRouting {

	/**
	 * Routes the edges from center to center.
	 * 
	 * @param graph
	 */
	public static void routeEdgesCenterToCenter(ElkNode root) {
		for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(root)) {

			ElkNode target = ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0));
			if (!root.getChildren().contains(target)) {

				double sourceX = root.getX() + root.getWidth() / 2;
				double sourceY = root.getY() + root.getHeight() / 2;

				double targetX = target.getX() + target.getWidth() / 2;
				double targetY = target.getY() + target.getHeight() / 2;

				ElkEdgeSection section = ElkGraphUtil.firstEdgeSection(edge, true, true);
				section.setStartLocation(sourceX, sourceY);
				section.setEndLocation(targetX, targetY);

				routeEdgesCenterToCenter(target);
			}
		}
	}

	/**
	 * Routes the edges from corner to corner.
	 * 
	 * @param graph
	 */
	public static void routeEdgesCornerToCorner(ElkNode root) {
		for (ElkEdge edge : root.getOutgoingEdges()) {
			ElkNode target = ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0));
			if (!root.getChildren().contains(target)) {
				double sourceX = root.getX();
				double sourceY = root.getY();

				double targetX = target.getX();
				double targetY = target.getY();

				ElkEdgeSection section = ElkGraphUtil.firstEdgeSection(edge, true, true);
				section.setStartLocation(sourceX, sourceY);
				section.setEndLocation(targetX, targetY);

				routeEdgesCornerToCorner(target);
			}

		}
	}

	/**
	 * Mthod that combines the two steps that are needed to draw hierarchical edges.
	 * 
	 * @param root
	 */
	public static void drawHierarchicalEdges(ElkNode root) {
		routeEdgesCenterToCenter(root);
		bendEdgesToHierarchicalEdges(root);
	}

	/**
	 * Sets the source for the edge at the corresponding child in the source node.
	 * 
	 * @param root
	 */
	public static void bendEdgesToHierarchicalEdges(ElkNode root) {
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
					clippTargetOfEdge(edge);

				}
			}

			bendEdgesToHierarchicalEdges(successor);
		}

	}

	/**
	 * Clips the edge when it reaches the border of the target node.
	 * 
	 * @param edge
	 */
	private static void clippTargetOfEdge(ElkEdge edge) {
		ElkEdgeSection section = ElkGraphUtil.firstEdgeSection(edge, false, false);
		double startX = section.getStartX();
		double startY = section.getStartY();
		double targetX = section.getEndX();
		double targetY = section.getEndY();

		double m = (targetY - startY) / (targetX - startX);
		double b = startY - m * startX;

		double targetWidth = edge.getTargets().get(0).getWidth();
		double targetHeight = edge.getTargets().get(0).getHeight();

		double yPoint;
		double xPoint;
		if (startX <= targetX) {

			// lower left
			if (startY <= targetY) {
				yPoint = targetY - targetHeight / 2;
				xPoint = targetX - targetWidth / 2;

			} else {
				// upper left
				yPoint = targetY + targetHeight / 2;
				xPoint = targetX - targetWidth / 2;

			}
		} else {
			// lower right
			if (startY <= targetY) {
				yPoint = targetY - targetHeight / 2;
				xPoint = targetX + targetWidth / 2;

			} else {
				// upper right
				yPoint = targetY + targetHeight / 2;
				xPoint = targetX + targetWidth / 2;
			}
		}

		double y = yPoint;
		double x = (y - b) / m;
		if (x < targetX + targetWidth / 2 && targetX - targetWidth / 2 < x) {
			section.setEndY(y);
			section.setEndX(x);
		} else {
			y = xPoint * m + b;
			section.setEndY(y);
			section.setEndX(xPoint);
		}
	}
}
