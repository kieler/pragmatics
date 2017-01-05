package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.KNode;

public class GridSnapLayoutProvider extends AbstractLayoutProvider {

	@Override
	public void layout(KNode layoutGraph, IElkProgressMonitor progressMonitor) {
		progressMonitor.begin("Grid Snap Layouter", 1);
		
		for (KNode node : layoutGraph.getChildren()) {
			KShapeLayout layout = node.getData(KShapeLayout.class);
			
		}
		
		progressMonitor.done();
	}
	
}
