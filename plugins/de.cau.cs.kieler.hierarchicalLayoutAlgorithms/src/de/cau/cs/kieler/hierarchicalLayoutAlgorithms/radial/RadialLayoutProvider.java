package de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KNode;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalEdgeRouting;
import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalMetaDataProvider;
import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalUtil;

public class RadialLayoutProvider extends AbstractLayoutProvider {
	/** default value for spacing between nodes. */
	private static final float DEFAULT_SPACING = 15.0f;
	// private ArrayList<Float> layerSize;
	private double radius;

	@Override
	public void layout(KNode layoutGraph, IElkProgressMonitor progressMonitor) {
		progressMonitor.begin("Simple layouter", 1);
		KShapeLayout parentLayout = layoutGraph.getData(KShapeLayout.class);

		float objectSpacing = parentLayout.getProperty(CoreOptions.SPACING_NODE);
		if (objectSpacing < 0) {
			objectSpacing = DEFAULT_SPACING;
		}

		float borderSpacing = parentLayout.getProperty(CoreOptions.SPACING_BORDER);
		if (borderSpacing < 0) {
			borderSpacing = DEFAULT_SPACING;
		}
		KNode root = HierarchicalUtil.findRoot(layoutGraph);

		List<KNode> rootLayer = new ArrayList<>();
		rootLayer.add(root);

		// pre-calculate the size of each layer
		// layerSize = new ArrayList<>();
		// float size = 0;
		// for (List<KNode> layer : layers) {
		// layerSize.add(size);
		// size += findBiggestNode(layer);
		// size += objectSpacing;
		// }
		// layerSize.add(size);
		// layerSize.set(0, layerSize.get(0) / 2);

		nodeCounter = 0;
		radius = findBiggestNodeInGraph(layoutGraph); // averageNodeSize(root) /
														// nodeCounter;

		calcPos(root, 0, 0, 2 * Math.PI);

		postProcess(layoutGraph);
		HierarchicalEdgeRouting.drawExplosionLines(root);
		progressMonitor.done();
	}

	double offset = 0;

	private void calcPos(KNode node, double currentRadius, double minAlpha, double maxAlpha) {

		double alphaPoint = (minAlpha + maxAlpha) / 2 + offset;

		KShapeLayout shape = node.getData(KShapeLayout.class);
		// double size = layerSize.get(layer);

		// x=r*sinθ, y=r*cosθ
		float xPos = (float) (currentRadius * Math.sin(alphaPoint));
		float yPos = (float) (currentRadius * Math.cos(alphaPoint));

		// calculate offset according to first surrounding node
		if (minAlpha == 0.0 && maxAlpha != 2 * Math.PI && offset == 0) {
			offset = calculateOffset(node, currentRadius, xPos, yPos);
			alphaPoint = (minAlpha + maxAlpha) / 2 + offset;
			xPos = (float) (currentRadius * Math.sin(alphaPoint));
			yPos = (float) (currentRadius * Math.cos(alphaPoint));
		}

		shape.setXpos(xPos);
		shape.setYpos(yPos);

		centerNodesOnRadi(shape, xPos, yPos);
		// shiftClosestEdgeToRadi(shape, xPos, yPos);

		calculateChildrenWedgeByLeafs(node, currentRadius+radius, minAlpha, maxAlpha);
	}

	private void calculateChildrenWedgeByLeafs(KNode node, double currentRadius, double minAlpha, double maxAlpha) {
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
		List<KNode> successors = HierarchicalUtil.sortSuccesorsByPolarCoordinate(node);
		for (KNode child : successors) {
			int numberOfChildLeafs = HierarchicalUtil.getNumberOfLeafs(child);
			calcPos(child, currentRadius, alpha, alpha + s * numberOfChildLeafs);
			alpha += s * numberOfChildLeafs;
		}
	}

	private void calculateChildrenWedgeBySize(KNode node, double currentRadius, double minAlpha, double maxAlpha) {

		List<KNode> successors = HierarchicalUtil.sortSuccesorsByPolarCoordinate(node);
		for (KNode child : successors) {
			KShapeLayout shapeNode = child.getData(KShapeLayout.class);

			double minxPos = currentRadius * Math.sin(minAlpha);
			if (minxPos == 0) {
				minxPos = currentRadius;
			}
			float minyPos = (float) (currentRadius * Math.cos(minAlpha));

			double alpha = 0;
			if (minAlpha + offset < 0.7854) {// 45°
				float height = shapeNode.getHeight();
				minyPos += height;
				double distanceToOrigin = Math.sqrt(minxPos * minxPos + minyPos * minyPos);
				alpha = Math.acos((Math.pow(distanceToOrigin, 2) + Math.pow(currentRadius, 2) - Math.pow(height, 2))
						/ (2 * distanceToOrigin * currentRadius));

			} else if (minAlpha + offset < 2.3562) {// 135°

				float height = shapeNode.getWidth();
				minyPos += height;
				double distanceToOrigin = Math.sqrt(minxPos * minxPos + minyPos * minyPos);
				alpha = Math.acos((Math.pow(distanceToOrigin, 2) + Math.pow(currentRadius, 2) - Math.pow(height, 2))
						/ (2 * distanceToOrigin * currentRadius));
			} else if (minAlpha + offset < 3.927) {// 225°
				float height = shapeNode.getHeight();
				minyPos += height;
				double distanceToOrigin = Math.sqrt(minxPos * minxPos + minyPos * minyPos);
				alpha = Math.acos((Math.pow(distanceToOrigin, 2) + Math.pow(currentRadius, 2) - Math.pow(height, 2))
						/ (2 * distanceToOrigin * currentRadius));

			} else if (minAlpha + offset < 5.4978) {// 315°
				float height = shapeNode.getWidth();
				minyPos += height;
				double distanceToOrigin = Math.sqrt(minxPos * minxPos + minyPos * minyPos);
				alpha = Math.acos((Math.pow(distanceToOrigin, 2) + Math.pow(currentRadius, 2) - Math.pow(height, 2))
						/ (2 * distanceToOrigin * currentRadius));
			}

			calcPos(child, currentRadius, minAlpha, minAlpha + alpha);
			minAlpha += alpha;
		}
	}

	private double calculateOffset(KNode node, double currentRadius, float xPos, float yPos) {
		List<KEdge> edges = node.getIncomingEdges();
		KNode parent = null;
		for (KEdge edge : edges) {
			if (!node.getChildren().contains(edge.getSource())) {
				parent = edge.getSource();
			}
		}
		KNode innerNode = HierarchicalUtil.getOriginalNode(parent, node);

		// center of the node, as center of the polar coordinates
		KShapeLayout parentShape = parent.getData(KShapeLayout.class);
		float centerX = parentShape.getXpos(); // + nodeShape.getWidth()/2;
		float centerY = parentShape.getYpos(); // + nodeShape.getHeight()/2;

		KShapeLayout shapeInnerNode = innerNode.getData(KShapeLayout.class);
		float innerX = shapeInnerNode.getXpos() + centerX;
		float innerY = shapeInnerNode.getYpos() + centerY;

		double distanceOriginToInnerNode = Math.sqrt(innerX * innerX + innerY * innerY);

		float distanceX = xPos - innerX;
		float distanceY = yPos - innerY;
		double distanceNodeToInnerNode = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

		double alpha = Math.acos((Math.pow(distanceOriginToInnerNode, 2) + Math.pow(currentRadius, 2)
				- Math.pow(distanceNodeToInnerNode, 2)) / (2 * distanceOriginToInnerNode * currentRadius));

		return alpha;
	}

	private void centerNodesOnRadi(KShapeLayout shape, float xPos, float yPos) {
		xPos = xPos - shape.getWidth() / 2;
		yPos = yPos - shape.getHeight() / 2;

		shape.setXpos(xPos);
		shape.setYpos(yPos);
	}

	private void shiftClosestEdgeToRadi(KShapeLayout shape, float xPos, float yPos) {

		// center root
		if (xPos == 0 && yPos == 0) {
			shape.setXpos(xPos - shape.getWidth() / 2);
			shape.setYpos(yPos - shape.getHeight() / 2);
		} else {

			if (xPos < 0) {
				if (yPos < 0) {
					// lower left rectangle

					shape.setXpos(xPos - shape.getWidth());
					shape.setYpos(yPos);
				} else {
					// upper left rectangle
					shape.setXpos(xPos - shape.getWidth());
					shape.setYpos(yPos + shape.getHeight());
				}
			} else {

				if (yPos < 0) {
					// lower right rectangle

					shape.setXpos(xPos);
					shape.setYpos(yPos);
				} else {
					// upper right rectangle

					shape.setXpos(xPos);
					shape.setYpos(yPos + shape.getHeight());
				}
			}
		}
	}

	private float findBiggestNodeInGraph(KNode graph) {
		float biggestChildSize = 0;

		for (KNode child : graph.getChildren()) {
			KShapeLayout shape = child.getData(KShapeLayout.class);

			float width = shape.getWidth();
			float height = shape.getHeight();
			float diameter = (float) Math.sqrt(width * width + height * height);

			if (biggestChildSize < diameter) {
				biggestChildSize = diameter;
			}

			float biggestChild = findBiggestNodeInGraph(child);
			if (biggestChild > biggestChildSize) {
				biggestChildSize = biggestChild;
			}
		}
		return biggestChildSize;
	}

	private int nodeCounter = 0;

	private double averageNodeSize(KNode graph) {
		double size = 0;
		nodeCounter++;
		KShapeLayout shape = graph.getData(KShapeLayout.class);
		double width = shape.getWidth();
		double height = shape.getHeight();
		size += (float) Math.sqrt(width * width + height * height);
		for (KNode node : HierarchicalUtil.getSuccessor(graph)) {
			size += averageNodeSize(node);
		}

		return size;
	}

	private void postProcess(KNode layoutGraph) {
		// calculate min size
		KShapeLayout graphData = layoutGraph.getData(KShapeLayout.class);
		float minHeight = 0;
		float minXPos = 0;
		for (KNode node : layoutGraph.getChildren()) {
			KShapeLayout layoutData = node.getData(KShapeLayout.class);
			minHeight = Math.min(minHeight, layoutData.getYpos());
			minXPos = Math.min(minXPos, layoutData.getXpos());
		}

		// shift graph
		for (KNode node : layoutGraph.getChildren()) {
			KShapeLayout layoutData = node.getData(KShapeLayout.class);
			layoutData.setXpos(layoutData.getXpos() - minXPos);
			layoutData.setYpos(layoutData.getYpos() - minHeight);
		}

		// calculate graph size
		float height = 0;
		float width = 0;
		for (KNode node : layoutGraph.getChildren()) {
			KShapeLayout layoutData = node.getData(KShapeLayout.class);
			height = Math.max(height, layoutData.getYpos() + layoutData.getHeight());
			width = Math.max(width, layoutData.getXpos() + layoutData.getWidth());
		}
		graphData.setHeight(height + 100);
		graphData.setWidth(width + 100);
	}

}
