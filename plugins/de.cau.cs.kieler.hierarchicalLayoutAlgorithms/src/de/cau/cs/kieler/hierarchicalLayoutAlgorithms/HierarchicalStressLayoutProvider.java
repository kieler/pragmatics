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
package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.HashSet;

import org.eclipse.elk.alg.force.options.StressOptions;
import org.eclipse.elk.alg.force.stress.StressLayoutProvider;
import org.eclipse.elk.alg.radial.RadialUtil;
import org.eclipse.elk.alg.radial.edgeRouting.ExplosionLineRouter;
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

/**
 * Layout provider for a hierarchical graph that uses elk stress to compute a
 * layout for the given graph and uses a minimal desired edge length.
 */
public class HierarchicalStressLayoutProvider extends AbstractLayoutProvider {

	@Override
	public void layout(final ElkNode layoutGraph, final IElkProgressMonitor progressMonitor) {
		progressMonitor.begin("Stress Layouter", 1);

		StressLayoutProvider stress = new StressLayoutProvider();

		HashSet<ElkEdge> edges = HierarchicalUtil.getHierarchicalEdges(layoutGraph);
		for (ElkEdge edge : edges) {
			ElkNode source = ElkGraphUtil.connectableShapeToNode(edge.getSources().get(0));
			double sourceDiagonal = getDiagonalLength(source);

			ElkNode target = ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0));
			double targetDiagonal = getDiagonalLength(target);

			double edgeLength = (sourceDiagonal + targetDiagonal
					+ layoutGraph.getProperty(HierarchicalStressOptions.DESIRED_EDGE_LENGTH));
			edge.setProperty(StressOptions.DESIRED_EDGE_LENGTH, edgeLength);
		}

		stress.layout(layoutGraph, progressMonitor);
		ExplosionLineRouter edgeRouter = new ExplosionLineRouter();
		edgeRouter.routeEdges(RadialUtil.findRoot(layoutGraph));

		progressMonitor.done();
	}

	/**
	 * Computes the diagonal length from the center to a corner of a given node.
	 * 
	 * @param n
	 * @return Length from center to a corner of a given node.
	 */
	private double getDiagonalLength(final ElkNode n) {
		double width = n.getWidth() / 2;
		double height = n.getHeight() / 2;
		double diagonal = Math.sqrt(width * width + height * height);

		return diagonal;
	}

}
