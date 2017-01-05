package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import org.eclipse.elk.core.AbstractLayoutProvider;
import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.KNode;

public class RadialLayoutProvider extends AbstractLayoutProvider {
	/** default value for spacing between nodes. */
	private static final float DEFAULT_SPACING = 15.0f;
	
	@Override
	public void layout(KNode layoutGraph, IElkProgressMonitor progressMonitor) {
		progressMonitor.begin("Simple layouter", 1);
		KShapeLayout parentLayout = layoutGraph.getData(KShapeLayout.class);
		 
		float objectSpacing = parentLayout.getProperty(CoreOptions.SPACING_NODE);
		if (objectSpacing < 0) {
		    objectSpacing = DEFAULT_SPACING;
		}
		 
		float borderSpacing = parentLayout.getProperty(CoreOptions.SPACING_BORDER);
		if (borderSpacing < 0) {
		    borderSpacing = DEFAULT_SPACING;
		}
		 
		progressMonitor.done();
	}

}
