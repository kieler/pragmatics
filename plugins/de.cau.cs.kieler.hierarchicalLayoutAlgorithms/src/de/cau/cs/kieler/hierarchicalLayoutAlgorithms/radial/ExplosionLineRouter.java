/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial;

import org.eclipse.elk.alg.radial.edgeRouting.IRadialEdgeRouter;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalEdgeRouting;

/**
 * An edge router implementing the interface of the elk radial layouter for edge routing.
 * It draws the explosion lines of hierarchical edges.
 */
public class ExplosionLineRouter implements IRadialEdgeRouter {

	@Override
	public void routeEdges(ElkNode root) {
		HierarchicalEdgeRouting.drawHierarchicalEdges(root);		
	}

}
