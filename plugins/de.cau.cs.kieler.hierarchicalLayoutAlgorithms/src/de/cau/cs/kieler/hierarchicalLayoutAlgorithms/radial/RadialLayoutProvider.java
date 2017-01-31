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
		radius = findBiggestNodeInGraph(layoutGraph); //averageNodeSize(root) / nodeCounter;

		calcPos(root, 0, 0, 2 * Math.PI);

		postProcess(layoutGraph);
		HierarchicalEdgeRouting.drawExplosionLines(root);
		progressMonitor.done();
	}

	private void calcPos(KNode node, double currentSize, double minAlpha, double maxAlpha) {
		double alphaPoint = (minAlpha + maxAlpha) / 2 + 45;

		KShapeLayout shape = node.getData(KShapeLayout.class);
		// double size = layerSize.get(layer);

		// x=r*sinθ, y=r*cosθ
		float xPos = (float) (currentSize * Math.sin(alphaPoint));
		float yPos = (float) (currentSize * Math.cos(alphaPoint));
		centerNodesOnRadi(shape, xPos, yPos);
		// shiftClosestEdgeToRadi(shape, xPos, yPos);

		double numberOfLeafs = HierarchicalUtil.getNumberOfLeafs(node);
		// double sizeNextLayer = layerSize.get(layer + 1);
		double tau = 2 * Math.acos(currentSize / currentSize + radius);
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
		List<KNode> successors2 = HierarchicalUtil.getSuccessor(node);
		if(successors.size() != successors2.size()){
			System.out.println("Hello there, im a fucker");
		}
		for (KNode child : successors) {
			int numberOfChildLeafs = HierarchicalUtil.getNumberOfLeafs(child);
			calcPos(child, currentSize + radius, alpha, alpha + s * numberOfChildLeafs);
			alpha += s * numberOfChildLeafs;
		}

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

	private float findBiggestNodeInGraph(KNode graph) {
		float biggestChildSize = 0;

		for (KNode child : graph.getChildren()) {
			KShapeLayout shape = child.getData(KShapeLayout.class);

			float width = shape.getWidth();
			float height = shape.getHeight();
			float diameter= (float) Math.sqrt(width * width + height * height);

			if (biggestChildSize < diameter) {
				biggestChildSize = diameter;
			}

			float biggestChild = findBiggestNodeInGraph(child);
			if(biggestChild > biggestChildSize){
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
