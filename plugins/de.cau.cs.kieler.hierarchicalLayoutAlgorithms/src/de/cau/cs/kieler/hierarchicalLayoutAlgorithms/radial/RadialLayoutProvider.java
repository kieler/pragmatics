package de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial;

import java.util.List;

import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
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
		hillClimber(root);

		postProcess(layoutGraph);
		HierarchicalEdgeRouting.drawExplosionLines(root);
		progressMonitor.done();
	}

	double offset = 0;

	/**
	 * Place a node in the center of an wedge an calculate the wedge for the
	 * next child.
	 * 
	 * @param node
	 * @param currentRadius
	 * @param minAlpha
	 * @param maxAlpha
	 */
	private void positionNodes(ElkNode node, double currentRadius, double minAlpha, double maxAlpha) {

		double alphaPoint = (minAlpha + maxAlpha) / 2 + offset;

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

		// node.setX(xPos);
		// node.setY(yPos);

		// shift the nodes, such that the center of each node is on the circle
		centerNodesOnRadi(node, xPos, yPos);

		calculateChildrenWedgeBySize(node, currentRadius + radius, minAlpha, maxAlpha);
	}

	private void calculateChildrenWedgeBySize(ElkNode node, double currentRadius, double minAlpha, double maxAlpha) {
		double numberOfLeafs = necessarySpace(node);
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
		for (ElkNode succesor : successors) {
			double numberOfChildLeafs = necessarySpace(succesor);
			positionNodes(succesor, currentRadius, alpha, alpha + s * numberOfChildLeafs);
			alpha += s * numberOfChildLeafs;
		}
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
			positionNodes(child, currentRadius, alpha, alpha + s * numberOfChildLeafs);
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
		List<ElkNode> successors = HierarchicalUtil.sortSuccesorsByPolarCoordinate(node);
		for (int i = 0; i < successors.size(); i++) {
			ElkNode successor = successors.get(i);
			double diameter = Math.sqrt(Math.pow(successor.getHeight(), 2) + Math.pow(successor.getWidth(), 2));
			double alpha = Math.atan(diameter / currentRadius);
			positionNodes(successor, currentRadius, minAlpha, minAlpha + alpha);
			minAlpha += alpha;
		}
	}

	/**
	 * Determine the size a node needs
	 * 
	 * @param node
	 * @param previousSize
	 * @return
	 */
	private double necessarySpace(ElkNode node) {
		List<ElkNode> successors = HierarchicalUtil.getSuccessor(node);
		double nodeSize = Math.sqrt(Math.pow(node.getHeight(), 2) + Math.pow(node.getWidth(), 2));
		double childSpace = 0;
		for (ElkNode child : successors) {
			childSpace += necessarySpace(child);
		}
		if (successors.isEmpty()) {
		}
		return Math.max(childSpace, nodeSize);
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

	private void translate(ElkNode node, double currentRadius, double degree) {
		double xPos = node.getX();
		double yPos = node.getY();
		double arc = Math.atan2(yPos, xPos);
		if (arc < 0) {
			arc += 2 * Math.PI;
		}

		arc += degree;

		xPos = currentRadius * Math.cos(arc);
		yPos = currentRadius * Math.sin(arc);

		node.setX(xPos);
		node.setY(yPos);

		List<ElkNode> successors = HierarchicalUtil.getSuccessor(node);
		for (ElkNode successor : successors) {
			translate(successor, currentRadius + radius, degree);
		}
	}

	private void hillClimber(ElkNode root) {
		positionNodes(root, 0, 0, 2 * Math.PI);
		HierarchicalEdgeRouting.drawExplosionLines(root);
		double optimalOffset = 0;
		double optimalValue = evaluate(root);
		
		for (int i = 1; i < 360; i++) {
			offset = i;
			positionNodes(root, 0, 0, 2 * Math.PI);
			HierarchicalEdgeRouting.drawExplosionLines(root);
			double translatedValue = evaluate(root);
			if (translatedValue < optimalValue) {
				optimalOffset = offset;
				optimalValue = translatedValue;
			}
		}
		offset = optimalOffset;
		positionNodes(root, 0, 0, 2 * Math.PI);
		HierarchicalEdgeRouting.drawExplosionLines(root);
	}

	private double evaluate(ElkNode root) {
		double edgeLength = 0;
		for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(root)) {
			if (!root.getChildren().contains(edge.getTargets().get(0))) {
				ElkEdgeSection section = ElkGraphUtil.firstEdgeSection(edge, false, false);
				double startX = section.getStartX();
				double startY = section.getStartY();
				double endX = section.getEndX();
				double endY = section.getEndY();

				double x = endX - startX;
				double y = endY - startY;

				edgeLength += Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
			}
		}
		return edgeLength;
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
