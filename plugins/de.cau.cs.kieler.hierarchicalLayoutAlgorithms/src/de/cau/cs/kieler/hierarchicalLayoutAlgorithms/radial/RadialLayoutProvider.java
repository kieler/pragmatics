package de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial;

import java.util.List;

import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalEdgeRouting;
import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalUtil;
import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.RadialOptions;

public class RadialLayoutProvider extends AbstractLayoutProvider {
	private double radius;

	@Override
	public void layout(ElkNode layoutGraph, IElkProgressMonitor progressMonitor) {
		progressMonitor.begin("Radial layouter", 1);

		// Calculate the radius or take the one given by the user.
		double layoutRadius = layoutGraph.getProperty(RadialOptions.RADIUS);
		if (layoutRadius == 0) {
			radius = findBiggestNodeInGraph(layoutGraph); // averageNodeSize(root)
															// / nodeCounter;
		} else {
			radius = layoutRadius;
		}

		// calculate the positions, starting with the root node
		ElkNode root = HierarchicalUtil.findRoot(layoutGraph);
		calcPos(root, 0, 0, 2 * Math.PI);

		postProcess(layoutGraph);
		HierarchicalEdgeRouting.drawExplosionLines(root);
		progressMonitor.done();
	}

	// double offset = 0;

	private void calcPos(ElkNode node, double currentRadius, double minAlpha, double maxAlpha) {

		double alphaPoint = (minAlpha + maxAlpha) / 2;// + offset;

		// double size = layerSize.get(layer);

		// x=r*sinθ, y=r*cosθ
		double xPos = currentRadius * Math.cos(alphaPoint);
		double yPos = currentRadius * Math.sin(alphaPoint);

		// calculate offset according to first surrounding node
		// if (minAlpha == 0.0 && maxAlpha != 2 * Math.PI && offset == 0) {
		// offset = calculateOffset(node, currentRadius, xPos, yPos);
		// alphaPoint = (minAlpha + maxAlpha) / 2 + offset;
		// xPos = currentRadius * Math.sin(alphaPoint);
		// yPos = currentRadius * Math.cos(alphaPoint);
		// }

		node.setX(xPos);
		node.setY(yPos);

		// shift the nodes, such that the center of each node is on the circle
		 centerNodesOnRadi(node, xPos, yPos);
		// shiftClosestEdgeToRadi(shape, xPos, yPos);

		 calculateChildrenWedgeByLeafs(node, currentRadius + radius, minAlpha, maxAlpha);
	}

	/**
	 * Calculate the annulus wedge of each child according to the number of
	 * leafs, each child has.
	 * 
	 * @param node
	 * @param currentRadius
	 * @param minAlpha
	 * @param maxAlpha
	 */
	private void calculateChildrenWedgeByLeafs(ElkNode node, double currentRadius, double minAlpha, double maxAlpha) {
		double numberOfLeafs = HierarchicalUtil.getNumberOfLeafs(node);
		double tau = 2 * Math.acos(currentRadius / currentRadius + radius);
		double s;
		double alpha;
		if (tau < maxAlpha - minAlpha) {
			s = tau / numberOfLeafs;
			alpha = (minAlpha + maxAlpha - tau) / 2;

		} else {
			s = (maxAlpha - minAlpha) / numberOfLeafs;
			alpha = minAlpha;
		}
		List<ElkNode> successors = HierarchicalUtil.sortSuccesorsByPolarCoordinate(node);
		for (ElkNode child : successors) {
			int numberOfChildLeafs = HierarchicalUtil.getNumberOfLeafs(child);
			calcPos(child, currentRadius, alpha, alpha + s * numberOfChildLeafs);
			alpha += s * numberOfChildLeafs;
		}
	}

	/**
	 * Determine the size of the annulus wedge for each child by the size of the
	 * nodes.
	 * 
	 * @param node
	 * @param currentRadius
	 * @param minAlpha
	 * @param maxAlpha
	 */
	private void calculateChildrenWedgeBySizeSimple(ElkNode node, double currentRadius, double minAlpha,
			double maxAlpha) {
		double numberOfLeafs = necessarySpace(node, 0);
		double tau = 2 * Math.acos(currentRadius / currentRadius + radius);
		double s;
		double alpha;
		if (tau < maxAlpha - minAlpha) {
			s = tau / numberOfLeafs;
			alpha = (minAlpha + maxAlpha - tau) / 2;

		} else {
			s = (maxAlpha - minAlpha) / numberOfLeafs;
			alpha = minAlpha;
		}
		List<ElkNode> successors = HierarchicalUtil.sortSuccesorsByPolarCoordinate(node);
		for (ElkNode child : successors) {
			double numberOfChildLeafs = necessarySpace(child, 0);
			calcPos(child, currentRadius, alpha, alpha + s * numberOfChildLeafs);
			alpha += s * numberOfChildLeafs;
		}
	}

	/**
	 * Determine the size a node needs
	 * 
	 * @param node
	 * @param previousSize
	 * @return
	 */
	private double necessarySpace(ElkNode node, double previousSize) {
		List<ElkNode> successors = HierarchicalUtil.getSuccessor(node);
		double childSpace = 0;
		for (ElkNode child : successors) {
			childSpace += necessarySpace(child, previousSize);
		}
		if (successors.isEmpty()) {
			previousSize = node.getWidth();
		}
		return Math.max(childSpace, previousSize);
	}

	private void calculateChildrenWedgeBySize(ElkNode node, double currentRadius, double minAlpha, double maxAlpha) {
		List<ElkNode> successors = HierarchicalUtil.sortSuccesorsByPolarCoordinate(node);

		for (ElkNode child : successors) {

			double minxPos = currentRadius * Math.cos(minAlpha);
			double minyPos = currentRadius * Math.sin(minAlpha);

			// Differentiate the different spaces: up, right,down,left
			double alpha = 0;
			if (minAlpha < 0.7854) {// 45°
				double width = child.getHeight();
				minyPos += width;
				double distanceToOrigin = Math.sqrt(minxPos * minxPos + minyPos * minyPos);
				alpha = Math.acos((Math.pow(distanceToOrigin, 2) + Math.pow(currentRadius, 2) - Math.pow(width, 2))
						/ (2 * distanceToOrigin * currentRadius));

			} else if (minAlpha < 2.3562) {// 135°
				double height = child.getWidth();
				minxPos += height;
				double distanceToOrigin = Math.sqrt(minxPos * minxPos + minyPos * minyPos);
				alpha = Math.acos((Math.pow(distanceToOrigin, 2) + Math.pow(currentRadius, 2) - Math.pow(height, 2))
						/ (2 * distanceToOrigin * currentRadius));

			} else if (minAlpha < 3.927) {// 225°
				double width = child.getHeight();
				minyPos += width;
				double distanceToOrigin = Math.sqrt(minxPos * minxPos + minyPos * minyPos);
				alpha = Math.acos((Math.pow(distanceToOrigin, 2) + Math.pow(currentRadius, 2) - Math.pow(width, 2))
						/ (2 * distanceToOrigin * currentRadius));

			} else if (minAlpha < 5.4978) {// 315°
				double height = child.getWidth();
				minxPos += height;
				double distanceToOrigin = Math.sqrt(minxPos * minxPos + minyPos * minyPos);
				alpha = Math.acos((Math.pow(distanceToOrigin, 2) + Math.pow(currentRadius, 2) - Math.pow(height, 2))
						/ (2 * distanceToOrigin * currentRadius));
			}

			calcPos(child, currentRadius, minAlpha, minAlpha + alpha);
			minAlpha += alpha;
		}
	}

	private double calculateOffset(ElkNode node, double currentRadius, double xPos, double yPos) {
		List<ElkEdge> edges = node.getIncomingEdges();
		ElkNode parent = null;
		for (ElkEdge edge : edges) {
			if (!node.getChildren().contains(edge.getSources().get(0))) {
				parent = ElkGraphUtil.connectableShapeToNode(edge.getSources().get(0));
			}
		}
		ElkNode innerNode = HierarchicalUtil.getOriginalNode(parent, node);

		// center of the node, as center of the polar coordinates
		double centerX = parent.getX(); // + nodeShape.getWidth()/2;
		double centerY = parent.getY(); // + nodeShape.getHeight()/2;

		double innerX = innerNode.getX() + centerX;
		double innerY = innerNode.getY() + centerY;

		double distanceOriginToInnerNode = Math.sqrt(innerX * innerX + innerY * innerY);

		double distanceX = xPos - innerX;
		double distanceY = yPos - innerY;
		double distanceNodeToInnerNode = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

		double alpha = Math.acos((Math.pow(distanceOriginToInnerNode, 2) + Math.pow(currentRadius, 2)
				- Math.pow(distanceNodeToInnerNode, 2)) / (2 * distanceOriginToInnerNode * currentRadius));

		return alpha;
	}

	private void centerNodesOnRadi(ElkNode node, double xPos, double yPos) {
		xPos = xPos - node.getWidth() / 2;
		yPos = yPos - node.getHeight() / 2;

		node.setX(xPos);
		node.setY(yPos);
	}

	private void shiftClosestEdgeToRadi(ElkNode node, double xPos, double yPos) {

		// center root
		if (xPos == 0 && yPos == 0) {
			node.setX(xPos - node.getWidth() / 2);
			node.setY(yPos - node.getHeight() / 2);
		} else {

			if (xPos < 0) {
				if (yPos < 0) {
					// lower left rectangle

					node.setX(xPos - node.getWidth());
					node.setY(yPos);
				} else {
					// upper left rectangle
					node.setX(xPos - node.getWidth());
					node.setY(yPos + node.getHeight());
				}
			} else {

				if (yPos < 0) {
					// lower right rectangle

					node.setX(xPos);
					node.setY(yPos);
				} else {
					// upper right rectangle

					node.setX(xPos);
					node.setY(yPos + node.getHeight());
				}
			}
		}
	}

	private double findBiggestNodeInGraph(ElkNode graph) {
		double biggestChildSize = 0;

		for (ElkNode child : graph.getChildren()) {

			double width = child.getWidth();
			double height = child.getHeight();
			double diameter = Math.sqrt(width * width + height * height);

			if (biggestChildSize < diameter) {
				biggestChildSize = diameter;
			}

			double biggestChild = findBiggestNodeInGraph(child);
			if (biggestChild > biggestChildSize) {
				biggestChildSize = biggestChild;
			}
		}
		return biggestChildSize;
	}

	private int nodeCounter = 0;

	private double averageNodeSize(ElkNode graph) {
		double size = 0;
		nodeCounter++;
		double width = graph.getWidth();
		double height = graph.getHeight();
		size += Math.sqrt(width * width + height * height);
		for (ElkNode node : HierarchicalUtil.getSuccessor(graph)) {
			size += averageNodeSize(node);
		}

		return size;
	}

	/**
	 * Calculate the size of the graph, such that it will be drawn in the
	 * properly on the display
	 * 
	 * @param layoutGraph
	 */
	private void postProcess(ElkNode layoutGraph) {
		// calculate min size
		double minHeight = 0;
		double minXPos = 0;
		for (ElkNode node : layoutGraph.getChildren()) {
			minHeight = Math.min(minHeight, node.getY());
			minXPos = Math.min(minXPos, node.getX());
		}

		// shift graph
		for (ElkNode node : layoutGraph.getChildren()) {
			node.setX(node.getX() - minXPos);
			node.setY(node.getY() - minHeight);
		}

		// calculate graph size
		double height = 0;
		double width = 0;
		for (ElkNode node : layoutGraph.getChildren()) {
			height = Math.max(height, node.getY() + node.getHeight());
			width = Math.max(width, node.getX() + node.getWidth());
		}
		layoutGraph.setHeight(height + 100);
		layoutGraph.setWidth(width + 100);
	}

}
