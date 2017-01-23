package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.core.klayoutdata.KEdgeLayout;
import org.eclipse.elk.core.klayoutdata.KLayoutDataFactory;
import org.eclipse.elk.core.klayoutdata.KPoint;
import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.klayoutdata.impl.KEdgeLayoutImpl;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KGraphFactory;
import org.eclipse.elk.graph.KNode;
import org.eclipse.elk.graph.impl.KGraphFactoryImpl;

public class HierarchicalEdgeRouting {

	public static void routeEdgesCenterToCenter(KNode graph) {
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

				routeEdgesCenterToCenter(target);
			}

		}
	}

	public static void routeEdgesCornerToCorner(KNode graph) {
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

				routeEdgesCornerToCorner(target);
			}

		}
	}

	public static void routeEdgesParentToChild(KNode graph) {
		List<KNode> copiedSuccessors = new ArrayList<KNode>();
		copiedSuccessors.addAll(HierarchicalUtil.getSuccessor(graph));

		for (KNode child : graph.getChildren()) {
			KShapeLayout shapeChild = child.getData(KShapeLayout.class);
			Integer id = shapeChild.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_I_D);

			if (id != null) {
				KNode copyOfChild = null;
				for (KNode successor : copiedSuccessors) {
					KShapeLayout shapeParent = successor.getData(KShapeLayout.class);
					int parent_id = shapeParent.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_PARENT_I_D);
					if (id == parent_id) {
						copyOfChild = successor;
						KEdge edge = ElkUtil.createInitializedEdge();
						edge.setSource(child);
						edge.setTarget(successor);

						KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
						KPoint sourcePoint = KLayoutDataFactory.eINSTANCE.createKPoint();
						sourcePoint.setX(shapeChild.getXpos() + shapeChild.getWidth() / 2);
						sourcePoint.setY(shapeChild.getYpos() + shapeChild.getHeight() / 2);
						edgeLayout.setSourcePoint(sourcePoint);

						KPoint targetPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
						targetPoint.setX(shapeParent.getXpos() + shapeParent.getWidth() / 2);
						targetPoint.setY(shapeParent.getYpos() + shapeParent.getHeight() / 2);
						edgeLayout.setTargetPoint(targetPoint);

						child.getOutgoingEdges().add(edge);
						routeEdgesParentToChild(successor);
						break;
					}
				}
				copiedSuccessors.remove(copyOfChild);
			}
		}

	}
}
