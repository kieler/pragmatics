/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 */
package de.cau.cs.kieler.graphs.hierarchicalViews.radial;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.alg.radial.RadialUtil;
import org.eclipse.elk.core.math.ElkMath;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.graphs.hierarchicalViews.HierarchicalMetaDataProvider;

/**
 * An edge router implementing the interface of the elk radial layouter for edge
 * routing. It draws the explosion lines of hierarchical edges.
 */
public class ExplosionLineRouter {
	/**
	 * Routes straight edges and transforms them to edges which start from the
	 * original parent of the hierarchical node.
	 * 
	 * @param root
	 */
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

				// Clipping
				KVector vector = new KVector();
				vector.x = targetX - sourceX;
				vector.y = targetY - sourceY;
				KVector sourceClip = new KVector(vector.x, vector.y);
				ElkMath.clipVector(sourceClip, node.getWidth(), node.getHeight());
				vector.x -= sourceClip.x;
				vector.y -= sourceClip.y;

				sourceX = targetX - vector.x;
				sourceY = targetY - vector.y;

				KVector targetClip = new KVector(vector.x, vector.y);
				ElkMath.clipVector(targetClip, target.getWidth(), target.getHeight());
				vector.x -= targetClip.x;
				vector.y -= targetClip.y;

				targetX = sourceX + vector.x;
				targetY = sourceY + vector.y;

				ElkEdgeSection section = ElkGraphUtil.firstEdgeSection(edge, true, true);
				section.setStartLocation(sourceX, sourceY);
				section.setEndLocation(targetX, targetY);
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
		
		// Take care of blue boxes.
		if (!isBlueBox) {
			copiedChildren.addAll(children);
		} else {
			copiedChildren.addAll(children.get(0).getChildren());
		}

		// Take a look at all the successors of the node.
		for (ElkNode successor : RadialUtil.getSuccessors(root)) {
			Integer id = successor.getProperty(HierarchicalMetaDataProvider.PARENT_ID);

			// Look at the incoming edges.
			for (ElkEdge edge : ElkGraphUtil.allIncomingEdges(successor)) {
				// Only the edge coming from the original node shall be
				// considered.
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
				}
			}
			bendEdgesToHierarchicalEdges(successor);
		}
	}
}
