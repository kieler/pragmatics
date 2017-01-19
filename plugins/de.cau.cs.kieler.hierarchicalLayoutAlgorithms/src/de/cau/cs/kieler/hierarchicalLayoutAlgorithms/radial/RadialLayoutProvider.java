package de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.KNode;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalEdgeRouting;
import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalUtil;

public class RadialLayoutProvider extends AbstractLayoutProvider {
	/** default value for spacing between nodes. */
	private static final float DEFAULT_SPACING = 15.0f;
	private List<List<KNode>> layers;
	private ArrayList<Float> layerSize;
	private double size;

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
		KNode root = findRoot(layoutGraph);

		List<KNode> rootLayer = new ArrayList<>();
		rootLayer.add(root);
		layers = orderNodesInLayer(rootLayer);

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
		size = averageNodeSize(layoutGraph) / nodeCounter;

		calcPos(root, 0, 0, 2 * Math.PI);

		postProcess(layoutGraph);
		HierarchicalEdgeRouting.routeEdgesCenterToCenter(root);
		progressMonitor.done();
	}

	private void calcPos(KNode node, double currentSize, double minAlpha, double maxAlpha) {
		double alphaPoint = (minAlpha + maxAlpha) / 2;

		KShapeLayout shape = node.getData(KShapeLayout.class);
		// double size = layerSize.get(layer);

		// x=r*sinθ, y=r*cosθ
		float xPos = (float) (currentSize * Math.sin(alphaPoint));
		float yPos = (float) (currentSize * Math.cos(alphaPoint));
		centerNodesOnRadi(shape, xPos, yPos);
		// shiftClosestEdgeToRadi(shape, xPos, yPos);

		double numberOfLeafs = numberOfLeafs(node);
		// double sizeNextLayer = layerSize.get(layer + 1);
		double tau = 2 * Math.acos(currentSize / currentSize + size);
		double s;
		double alpha;
		if (tau < maxAlpha - minAlpha) {
			s = tau / numberOfLeafs;
			alpha = (minAlpha + maxAlpha - tau) / 2;

		} else {
			s = (maxAlpha - minAlpha) / numberOfLeafs;
			alpha = minAlpha;
		}

		for (KNode child : HierarchicalUtil.getSuccessor(node)) {
			int numberOfChildLeafs = numberOfLeafs(child);
			calcPos(child, currentSize + size, alpha, alpha + s * numberOfChildLeafs);
			alpha += s * numberOfChildLeafs;
		}

	}

	private List<List<KNode>> orderNodesInLayer(List<KNode> nodes) {
		List<List<KNode>> layers = new ArrayList<List<KNode>>();
		layers.add(nodes);

		List<KNode> nextLayer = new ArrayList<>();
		for (KNode node : nodes) {
			nextLayer.addAll(HierarchicalUtil.getSuccessor(node));
		}
		if (!nextLayer.isEmpty()) {
			layers.addAll(orderNodesInLayer(nextLayer));
		}
		return layers;
	}

	private void centerNodesOnRadi(KShapeLayout shape, float xPos, float yPos) {
		if (xPos == 0 && yPos == 0) {
			xPos = xPos - shape.getWidth() / 2;
			yPos = yPos - shape.getHeight() / 2;
		} else {
			xPos = xPos - shape.getWidth() / 2;
			yPos = yPos - shape.getHeight() / 2;
		}
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

	private float findBiggestNode(List<KNode> node) {
		float biggestChildSize = 0;

		for (KNode child : node) {
			KShapeLayout shape = child.getData(KShapeLayout.class);
			shape.getProperty(CoreOptions.EDGE_LABELS_PLACEMENT);

			float width = shape.getWidth();
			float height = shape.getHeight();
			biggestChildSize += (float) Math.sqrt(width * width + height * height);

			// if (biggestChildSize < diameter) {
			// biggestChildSize = diameter;
			// }

		}
		return (biggestChildSize / node.size());
	}

	private KNode findRoot(KNode graph) {
		for (KNode child : graph.getChildren()) {
			if (child.getIncomingEdges().isEmpty()) {
				return child;
			}
		}
		return null;
	}

	private int numberOfLeafs(KNode node) {
		int leafs = 0;
		if (HierarchicalUtil.getSuccessor(node).isEmpty()) {
			return 1;
		} else {
			for (KNode child : HierarchicalUtil.getSuccessor(node)) {
				leafs += numberOfLeafs(child);
			}
		}
		return leafs;
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
		float maxHeight = 0;
		float minXPos = 0;
		for (KNode node : layoutGraph.getChildren()) {
			KShapeLayout layoutData = node.getData(KShapeLayout.class);
			maxHeight = Math.min(maxHeight, layoutData.getYpos());
			minXPos = Math.min(minXPos, layoutData.getXpos());
		}

		// shift graph
		for (KNode node : layoutGraph.getChildren()) {
			KShapeLayout layoutData = node.getData(KShapeLayout.class);
			layoutData.setXpos(layoutData.getXpos() - minXPos);
			layoutData.setYpos(layoutData.getYpos() - maxHeight);
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
