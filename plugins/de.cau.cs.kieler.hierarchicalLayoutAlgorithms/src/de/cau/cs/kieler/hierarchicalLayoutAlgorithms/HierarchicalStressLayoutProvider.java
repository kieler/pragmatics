/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.List;

import org.eclipse.elk.alg.force.options.StressOptions;
import org.eclipse.elk.alg.force.stress.StressLayoutProvider;
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

/**
 * Layout Provider for a Hierarchical Graph that uses Elk Stress to be layouted and a minimal desired edge length.
 * 
 * @author dja
 *
 */
public class HierarchicalStressLayoutProvider extends AbstractLayoutProvider {
	
	private static final float SPACING = 100;
	
	@Override
	public void layout(ElkNode layoutGraph, IElkProgressMonitor progressMonitor) {
		progressMonitor.begin("Stress Layouter", 1);
		
		StressLayoutProvider stress = new StressLayoutProvider();

		List<ElkEdge> edges = HierarchicalUtil.getHierarchicalEdges(layoutGraph);
		for (ElkEdge edge : edges) {
			ElkNode source = ElkGraphUtil.connectableShapeToNode(edge.getSources().get(0));
			ElkNode target = ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0));
			double width = source.getWidth() / 2;
			double height = source.getHeight() / 2;
			double sourcediagonal = Math.sqrt(width*width + height*height);
			width = target.getWidth();
			height = target.getHeight();
			double targetdiagonal = Math.sqrt(width*width + height*height);
			double edgelength = (sourcediagonal + targetdiagonal + SPACING);
			edge.setProperty(StressOptions.DESIRED_EDGE_LENGTH, edgelength);
		}
		
		stress.layout(layoutGraph, progressMonitor);
		HierarchicalEdgeRouting.drawHierarchicalEdges(HierarchicalUtil.findRoot(layoutGraph));

		progressMonitor.done();
	}

}
