package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.alg.force.properties.StressOptions;
import org.eclipse.elk.alg.force.stress.StressLayoutProvider;
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.klayoutdata.KEdgeLayout;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KNode;

public class HierarchicalStressLayoutProvider extends AbstractLayoutProvider {
	
	private static List<KEdge> edges = new ArrayList<KEdge>();
	private static List<KNode> children = new ArrayList<KNode>();
	
	@Override
	public void layout(KNode layoutGraph, IElkProgressMonitor progressMonitor) {
		progressMonitor.begin("Grid Snap Layouter", 1);
		
		StressLayoutProvider stress = new StressLayoutProvider();

		edges.clear();
		children.clear();
		
		children.addAll(layoutGraph.getChildren());
		
		
		for (KNode node : children) {
			for (KEdge edge : node.getOutgoingEdges()) {
				if (children.contains(edge.getTarget())) {
					edges.add(edge);
					KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
					// TODO set edge length
					edgeLayout.setProperty(StressOptions.DESIRED_EDGE_LENGTH, 800.0f);
				}
			}
		}
		
		stress.layout(layoutGraph, progressMonitor);

		progressMonitor.done();
	}

}
