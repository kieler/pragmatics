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

				float sourceX = sourceLayout.getXpos();
				float sourceY = sourceLayout.getYpos();

				float targetX = targetLayout.getXpos();
				float targetY = targetLayout.getYpos();

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

	public static void drawExplosionLines(KNode root) {
		routeEdgesCornerToCorner(root);
		bendEdgesToExplosionLines(root);
	}

	public static void bendEdgesToExplosionLines(KNode root) {
		List<KNode> copiedChildren = new ArrayList<>();
		copiedChildren.addAll(root.getChildren());

		// take a look at all the successors of the node
		for (KNode successor : HierarchicalUtil.getSuccessor(root)) {
			KShapeLayout graphShape = root.getData(KShapeLayout.class);
			KShapeLayout shapeSuccessor = successor.getData(KShapeLayout.class);
			Integer id = shapeSuccessor.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_I_D);

			// look at the incoming edges
			for (KEdge edge : successor.getIncomingEdges()) {
				// only the edge coming from the original node shall be
				// considered
				if (edge.getSource() == root) {
					KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
					KNode childFound = null;
					for (KNode child : copiedChildren) {
						KShapeLayout shapeChild = child.getData(KShapeLayout.class);
						Integer idParent = shapeChild.getProperty(HierarchicalMetaDataProvider.HIERARCHICAL_PARENT_I_D);

						if (idParent != null && id.equals(idParent)) {
							childFound = child;
							float xPos = graphShape.getXpos() + shapeChild.getXpos() + shapeChild.getWidth() / 2;
							float yPos = graphShape.getYpos() + shapeChild.getYpos() + shapeChild.getHeight() / 2;
							KPoint point = KLayoutDataFactory.eINSTANCE.createKPoint();
							point.setX(xPos);
							point.setY(yPos);
							edgeLayout.setSourcePoint(point);

						}
					}
					copiedChildren.remove(childFound);
				}
			}

			bendEdgesToExplosionLines(successor);
		}

	}
}
