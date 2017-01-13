package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.alg.force.properties.StressOptions;
import org.eclipse.elk.alg.force.stress.StressLayoutProvider;
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.klayoutdata.KEdgeLayout;
import org.eclipse.elk.core.klayoutdata.KShapeLayout;
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
					KNode source = edge.getSource();
					KNode target = edge.getTarget();
					KShapeLayout sourceLayout = source.getData(KShapeLayout.class);
					float width = sourceLayout.getWidth() / 2;
					float height = sourceLayout.getHeight() / 2;
					float diagonal = (float) Math.sqrt((Math.pow(width, 2) + Math.pow(height, 2)));
					KShapeLayout targetLayout = target.getData(KShapeLayout.class);
					width = targetLayout.getWidth();
					height = targetLayout.getHeight();
					diagonal += Math.sqrt((Math.pow(width, 2) + Math.pow(height, 2)));
					KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
					edgeLayout.setProperty(StressOptions.DESIRED_EDGE_LENGTH, diagonal);
				}
			}
		}
		
		stress.layout(layoutGraph, progressMonitor);

		progressMonitor.done();
	}

}
