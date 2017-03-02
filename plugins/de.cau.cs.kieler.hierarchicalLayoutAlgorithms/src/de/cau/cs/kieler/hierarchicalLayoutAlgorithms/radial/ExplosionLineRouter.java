package de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial;

import org.eclipse.elk.alg.radial.edgeRouting.IRadialEdgeRouter;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalEdgeRouting;

public class ExplosionLineRouter implements IRadialEdgeRouter {

	@Override
	public void routeEdges(ElkNode root) {
		HierarchicalEdgeRouting.drawHierarchicalEdges(root);		
	}

}
