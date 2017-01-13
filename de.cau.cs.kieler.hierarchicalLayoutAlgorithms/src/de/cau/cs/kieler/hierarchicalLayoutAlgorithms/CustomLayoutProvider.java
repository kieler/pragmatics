package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.klayoutdata.KEdgeLayout;
import org.eclipse.elk.core.klayoutdata.KLayoutData;
import org.eclipse.elk.core.klayoutdata.KLayoutDataFactory;
import org.eclipse.elk.core.klayoutdata.KPoint;
import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KNode;

public class CustomLayoutProvider extends AbstractLayoutProvider {

	private List<List<KNode>> layers;

	@Override
	public void layout(KNode layoutGraph, IElkProgressMonitor progressMonitor) {
		KNode root = findRoot(layoutGraph);

		List<KNode> rootLayer = new ArrayList<>();
		rootLayer.add(root);
		layers = orderNodesInLayer(rootLayer);

		for (int i = 0; i < layers.size(); i++) {
			// root node
			if (i == 0) {
				KShapeLayout layout = root.getData(KShapeLayout.class);
				layout.setXpos(0);
				layout.setYpos(0);
				i++;

				for(KNode node: layers.get(i)){
			        KLayoutData nodeData = node.getData(KLayoutData.class);
			      
			      nodeData.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_I_D);
					KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
					if(nodeLayout.getXpos() < layout.getWidth()/2){
						if(nodeLayout.getYpos() < -layout.getHeight()/2){
							//lower left
							nodeLayout.setXpos(0);
						}
					}
				}
			} else {

			}
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

	private KNode findRoot(KNode graph) {
		for (KNode child : graph.getChildren()) {
			if (child.getIncomingEdges().isEmpty()) {
				return child;
			}
		}
		return null;
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

}
