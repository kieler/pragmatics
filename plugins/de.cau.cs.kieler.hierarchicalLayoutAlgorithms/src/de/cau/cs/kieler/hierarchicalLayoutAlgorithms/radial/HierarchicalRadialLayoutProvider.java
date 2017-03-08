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

import org.eclipse.elk.alg.radial.RadialLayoutProvider;
import org.eclipse.elk.alg.radial.compaction.RadialCompaction;
import org.eclipse.elk.alg.radial.options.RadialOptions;
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkNode;

/**
 * The hierarchical radial layout provider uses the elk radial layout provider
 * and arguments it with a polar coordinate sorter and explosion line edge
 * routing.
 */
public class HierarchicalRadialLayoutProvider extends AbstractLayoutProvider {

	@Override
	public void layout(ElkNode layoutGraph, IElkProgressMonitor progressMonitor) {
		RadialLayoutProvider radialLayouter = new RadialLayoutProvider();
		layoutGraph.setProperty(RadialOptions.COMPACTOR, new RadialCompaction());
		layoutGraph.setProperty(RadialOptions.SORTER, new PolarCoordinateSorter());
		layoutGraph.setProperty(RadialOptions.EDGE_ROUTER, new ExplosionLineRouter());
//		radialLayouter.setEdgeRouter(new ExplosionLineRouter());
		radialLayouter.layout(layoutGraph, progressMonitor);
	}

}
