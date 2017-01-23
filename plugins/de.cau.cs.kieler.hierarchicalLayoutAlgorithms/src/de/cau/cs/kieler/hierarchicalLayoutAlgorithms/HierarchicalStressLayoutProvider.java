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
	
	private static List<KNode> children = new ArrayList<KNode>();
	private static final float SPACING = 100;
	
	@Override
	public void layout(KNode layoutGraph, IElkProgressMonitor progressMonitor) {
		progressMonitor.begin("Stress Layouter", 1);
		
		StressLayoutProvider stress = new StressLayoutProvider();

		children.clear();
		children.addAll(layoutGraph.getChildren());
		
		
		for (KNode node : children) {
			for (KEdge edge : node.getOutgoingEdges()) {
				if (children.contains(edge.getTarget())) {
					KNode source = edge.getSource();
					KNode target = edge.getTarget();
					KShapeLayout sourceLayout = source.getData(KShapeLayout.class);
					float width = sourceLayout.getWidth() / 2;
					float height = sourceLayout.getHeight() / 2;
					double sourcediagonal = Math.sqrt(width*width + height*height);
					KShapeLayout targetLayout = target.getData(KShapeLayout.class);
					width = targetLayout.getWidth();
					height = targetLayout.getHeight();
					double targetdiagonal = Math.sqrt(width*width + height*height);
					float edgelength = (float) (sourcediagonal + targetdiagonal + SPACING);
//					System.out.println(edgelength);
					KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
					edgeLayout.setProperty(StressOptions.DESIRED_EDGE_LENGTH, edgelength);
				}
			}
		}
		
		stress.layout(layoutGraph, progressMonitor);

		progressMonitor.done();
	}

}
