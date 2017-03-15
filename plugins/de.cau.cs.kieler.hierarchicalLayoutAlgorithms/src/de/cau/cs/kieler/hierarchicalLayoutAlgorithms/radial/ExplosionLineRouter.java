/*******************************************************************************
 * Copyright (c) 2017 Kiel University and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Kiel University - initial API and implementation
 *******************************************************************************/
package de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.alg.radial.RadialUtil;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalMetaDataProvider;

/**
 * An edge router implementing the interface of the elk radial layouter for edge
 * routing. It draws the explosion lines of hierarchical edges.
 */
public class ExplosionLineRouter {
	public void routeExplsionLines(final ElkNode root) {
		routeStraightEdges(root);
		bendEdgesToHierarchicalEdges(root);
	}

	/**
	 * Route edges from node center to node center.. Then clip it, to not cross
	 * the node.
	 */
	public void routeStraightEdges(final ElkNode node) {
		for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {

			ElkNode target = ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0));
			if (!edge.isHierarchical()) {
				double sourceX = node.getX() + node.getWidth() / 2;
				double sourceY = node.getY() + node.getHeight() / 2;

				double targetX = target.getX() + target.getWidth() / 2;
				double targetY = target.getY() + target.getHeight() / 2;

				ElkEdgeSection section = ElkGraphUtil.firstEdgeSection(edge, true, true);
				section.setStartLocation(sourceX, sourceY);
				section.setEndLocation(targetX, targetY);
				clipTargetOfEdge(edge);
				clipSourceOfEdge(edge);
				routeStraightEdges(target);
			}
		}
	}


	/**
	 * Sets the source for the edge at the corresponding child in the source
	 * node.
	 * 
	 * @param root
	 */
	public void bendEdgesToHierarchicalEdges(final ElkNode root) {
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
		for (ElkNode successor : RadialUtil.getSuccessors(root)) {
			Integer id = successor.getProperty(HierarchicalMetaDataProvider.PARENT_ID);

			// look at the incoming edges
			for (ElkEdge edge : ElkGraphUtil.allIncomingEdges(successor)) {
				// only the edge coming from the original node shall be
				// considered
				if (edge.getSources().get(0) == root || root.getChildren().contains(edge.getSources().get(0))) {
					ElkNode childFound = null;
					for (ElkNode child : copiedChildren) {
						Integer idParent = child.getProperty(HierarchicalMetaDataProvider.NODE_ID);

						if (idParent != null && id.equals(idParent)) {
							childFound = child;
							double xPos = root.getX() + child.getX() + child.getWidth() / 2;
							double yPos = root.getY() + child.getY() + child.getHeight() / 2;

							ElkEdgeSection section = ElkGraphUtil.firstEdgeSection(edge, false, false);
							section.setStartLocation(xPos, yPos);

						}
					}
					copiedChildren.remove(childFound);
					// clippTargetOfEdge(edge);

				}
			}

			bendEdgesToHierarchicalEdges(successor);
		}

	}
	

	/**
	 * Reset the edge end point to the point where the edge enters a node.
	 * 
	 * @param edge
	 */
	private void clipTargetOfEdge(final ElkEdge edge) {
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

	/**
	 * Reset the edge Start point to the point where the edge leaves a node.
	 * 
	 * @param edge
	 */
	private void clipSourceOfEdge(final ElkEdge edge) {
		ElkEdgeSection section = ElkGraphUtil.firstEdgeSection(edge, false, false);
		double startX = section.getStartX();
		double startY = section.getStartY();
		double targetX = section.getEndX();
		double targetY = section.getEndY();

		double m = (targetY - startY) / (targetX - startX);
		double b = startY - m * startX;

		double sourceWidth = edge.getSources().get(0).getWidth();
		double sourceHeight = edge.getSources().get(0).getHeight();

		double yPoint;
		double xPoint;
		if (targetX <= startX) {

			// lower left
			if (targetY <= startY) {
				yPoint = startY - sourceHeight / 2;
				xPoint = startX - sourceWidth / 2;

			} else {
				// upper left
				yPoint = startY + sourceHeight / 2;
				xPoint = startX - sourceWidth / 2;

			}
		} else {
			// lower right
			if (targetY <= startY) {
				yPoint = startY - sourceHeight / 2;
				xPoint = startX + sourceWidth / 2;

			} else {
				// upper right
				yPoint = startY + sourceHeight / 2;
				xPoint = startX + sourceWidth / 2;
			}
		}

		double y = yPoint;
		double x = (y - b) / m;
		if (x < startX + sourceWidth / 2 && startX - sourceWidth / 2 < x) {
			section.setStartY(y);
			section.setStartX(x);
		} else {
			y = xPoint * m + b;
			section.setStartY(y);
			section.setStartX(xPoint);
		}
	}

}
