package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.alg.force.properties.StressOptions;
import org.eclipse.elk.alg.force.stress.StressLayoutProvider;
import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

public class HierarchicalStressLayoutProvider extends AbstractLayoutProvider {
	
	private static List<ElkNode> children = new ArrayList<ElkNode>();
	private static final float SPACING = 100;
	
	@Override
	public void layout(ElkNode layoutGraph, IElkProgressMonitor progressMonitor) {
		progressMonitor.begin("Stress Layouter", 1);
		
		StressLayoutProvider stress = new StressLayoutProvider();

		children.clear();
		children.addAll(layoutGraph.getChildren());
		
		for (ElkNode node : children) {
			for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
				ElkNode target = ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0));
				if (children.contains(target)) {
					ElkNode source = ElkGraphUtil.connectableShapeToNode(edge.getSources().get(0));
					double width = source.getWidth() / 2;
					double height = source.getHeight() / 2;
					double sourcediagonal = Math.sqrt(width*width + height*height);
					width = target.getWidth();
					height = target.getHeight();
					double targetdiagonal = Math.sqrt(width*width + height*height);
					double edgelength = (sourcediagonal + targetdiagonal + SPACING);
//					System.out.println(edgelength);
					edge.setProperty(StressOptions.DESIRED_EDGE_LENGTH, edgelength);
				}
			}
		}
		
		stress.layout(layoutGraph, progressMonitor);
		HierarchicalEdgeRouting.drawExplosionLines(HierarchicalUtil.findRoot(layoutGraph));

		progressMonitor.done();
	}

}
