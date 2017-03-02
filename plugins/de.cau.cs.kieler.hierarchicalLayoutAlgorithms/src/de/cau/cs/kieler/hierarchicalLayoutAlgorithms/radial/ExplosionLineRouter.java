package de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial;

import org.eclipse.elk.alg.radial.edgeRouting.IRadialEdgeRouter;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalEdgeRouting;

/**
 * An edge router implementing the interface of the elk radial layouter for edge routing.
 * It draws the explosion lines of hierarchical edges.
 * @author Yella Lasch
 *
 */
public class ExplosionLineRouter implements IRadialEdgeRouter {

	@Override
	public void routeEdges(ElkNode root) {
		HierarchicalEdgeRouting.drawHierarchicalEdges(root);		
	}

}
