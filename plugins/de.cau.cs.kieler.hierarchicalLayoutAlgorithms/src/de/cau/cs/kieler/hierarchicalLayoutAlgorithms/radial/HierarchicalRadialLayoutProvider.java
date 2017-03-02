package de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial;

import org.eclipse.elk.alg.radial.RadialLayoutProvider;
import org.eclipse.elk.alg.radial.compaction.RadialCompaction;
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkNode;

public class HierarchicalRadialLayoutProvider extends AbstractLayoutProvider {

	@Override
	public void layout(ElkNode layoutGraph, IElkProgressMonitor progressMonitor) {
		RadialLayoutProvider radialLayouter = new RadialLayoutProvider();
		radialLayouter.setSorter(new PolarCoordinateSorter());
		radialLayouter.setCompactor(new RadialCompaction());
		radialLayouter.setEdgeRouter(new ExplosionLineRouter());
		radialLayouter.layout(layoutGraph, progressMonitor);
	}

}
