package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.alg.layered.LayeredLayoutProvider;
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.klayoutdata.KEdgeLayout;
import org.eclipse.elk.core.klayoutdata.KLayoutDataFactory;
import org.eclipse.elk.core.klayoutdata.KPoint;
import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KNode;

public class RadialLayoutProvider extends AbstractLayoutProvider {
	/** default value for spacing between nodes. */
	private static final float DEFAULT_SPACING = 15.0f;
	private List<List<KNode>> layers;
	private ArrayList<Float> layerSize;
	private LayeredLayoutProvider provider;

	@Override
	public void layout(KNode layoutGraph, IElkProgressMonitor progressMonitor) {
		provider = new LayeredLayoutProvider();
		
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
		layerSize = new ArrayList<>();
		float size = 0;
		for (List<KNode> layer : layers) {
			layerSize.add(size);
			size += findBiggestNode(layer);
			size += objectSpacing;
		}
		layerSize.add(size);
		layerSize.set(0, layerSize.get(0) / 2);

		calcPos(root, 0, 0, 4 * Math.PI);
        
        postProcess(layoutGraph);
		routeEdges(root);
		progressMonitor.done();
	}

	private void calcPos(KNode node, int layer, double minAlpha, double maxAlpha) {
		double alphaPoint = (minAlpha + maxAlpha) / 2;

		KShapeLayout shape = node.getData(KShapeLayout.class);
		double size = layerSize.get(layer);

		// x=r*sinθ, y=r*cosθ
		float xPos = (float) (size * Math.sin(alphaPoint));
		float yPos = (float) (size * Math.cos(alphaPoint));
//		shape.setXpos(xPos);
//		shape.setYpos(yPos);

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

		double sizeNextLayer = layerSize.get(layer + 1);
		double tau = Math.acos(size / sizeNextLayer);
		double s;
		double alpha;
		double numberOfSucessors = (calcSizes(node) - 1);
		if (tau < minAlpha - maxAlpha) {
			s = tau / numberOfSucessors;
			alpha = (minAlpha + maxAlpha - tau) / 2;

		} else {
			s = (minAlpha - maxAlpha) / numberOfSucessors;
			alpha = minAlpha;
		}

		for (KNode child : getSuccessor(node)) {
			IElkProgressMonitor monitor = new BasicProgressMonitor();
			provider.layout(child, monitor);
			calcPos(child, layer + 1, alpha, alpha + s * (calcSizes(child)-1));
			alpha += s * (calcSizes(child) - 1);
		}
	}

	private List<List<KNode>> orderNodesInLayer(List<KNode> nodes) {
		List<List<KNode>> layers = new ArrayList<List<KNode>>();
		layers.add(nodes);

		List<KNode> nextLayer = new ArrayList<>();
		for (KNode node : nodes) {
			nextLayer.addAll(getSuccessor(node));
		}
		if (!nextLayer.isEmpty()) {
			layers.addAll(orderNodesInLayer(nextLayer));
		}
		return layers;
	}

	private float findBiggestNode(List<KNode> node) {
		float biggestChildSize = 0;

		for (KNode child : node) {
			KShapeLayout shape = child.getData(KShapeLayout.class);
			shape.getProperty(CoreOptions.EDGE_LABELS_PLACEMENT);
			
			float width = shape.getWidth();
			float height = shape.getHeight();
			biggestChildSize += (float) Math.sqrt(width * width + height * height);

//			if (biggestChildSize < diameter) {
//				biggestChildSize = diameter;
//			}

		}
		return (biggestChildSize/ node.size());
	}

	private KNode findRoot(KNode graph) {
		for (KNode child : graph.getChildren()) {
			if (child.getIncomingEdges().isEmpty()) {
				return child;
			}
		}
		return null;
	}

	private int calcSizes(KNode node) {
		int n = 1;

		for (KNode child : getSuccessor(node)) {
			n += calcSizes(child);
		}
		return n;
	}

	private List<KNode> getSuccessor(KNode node) {
		List<KNode> children = new ArrayList<>();
		for (KEdge outgoingEdge : node.getOutgoingEdges()) {
			KNode target = outgoingEdge.getTarget();
			if (!node.getChildren().contains(target)) {
				children.add(target);
			}
		}
		return children;
	}

	private void routeEdges(KNode graph) {

		for (KEdge edge : graph.getOutgoingEdges()) {
			KNode target = edge.getTarget();
			if (!graph.getChildren().contains(target)) {
				KShapeLayout sourceLayout = graph.getData(KShapeLayout.class);
				KShapeLayout targetLayout = target.getData(KShapeLayout.class);

				float sourceX = sourceLayout.getXpos() + sourceLayout.getWidth() / 2;
				float sourceY = sourceLayout.getYpos() + sourceLayout.getHeight() / 2;

				float targetX = targetLayout.getXpos() + targetLayout.getWidth() / 2;
				float targetY = targetLayout.getYpos() + targetLayout.getHeight() / 2;

				KEdgeLayout layout = edge.getData(KEdgeLayout.class);

				KPoint sourcePoint = KLayoutDataFactory.eINSTANCE.createKPoint();
				sourcePoint.setPos(sourceX, sourceY);
				layout.setSourcePoint(sourcePoint);

				KPoint targetPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
				targetPoint.setPos(targetX, targetY);
				layout.setTargetPoint(targetPoint);

				routeEdges(target);
			}

		}
	}
	
	
	private void postProcess(KNode layoutGraph){
	    // calculate min size
        KShapeLayout graphData = layoutGraph.getData(KShapeLayout.class);
        float maxHeight = 0;
        float minXPos = 0;
        for (KNode node : layoutGraph.getChildren()) {
            KShapeLayout layoutData = node.getData(KShapeLayout.class);
            maxHeight = Math.min(maxHeight, layoutData.getYpos());
            minXPos = Math.min(minXPos, layoutData.getXpos());
        }
        
        //shift graph
        for (KNode node : layoutGraph.getChildren()) {
            KShapeLayout layoutData = node.getData(KShapeLayout.class);
           layoutData.setXpos(layoutData.getXpos()-minXPos);
           layoutData.setYpos(layoutData.getYpos()-maxHeight);
        }
		
		
	    // calculate graph size
        float height = 0;
        float width = 0;
        for (KNode node : layoutGraph.getChildren()) {
            KShapeLayout layoutData = node.getData(KShapeLayout.class);
            height = Math.max(height, layoutData.getYpos()+layoutData.getHeight());
            width = Math.max(width, layoutData.getXpos()+layoutData.getWidth());
        }
        graphData.setHeight(height+100);
        graphData.setWidth(width+100);
	}

}
