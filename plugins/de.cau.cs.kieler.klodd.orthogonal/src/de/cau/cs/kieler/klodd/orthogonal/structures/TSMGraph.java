/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.orthogonal.structures;

import de.cau.cs.kieler.core.slimgraph.*;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets;


/**
 * The graph structure used internally for the topology-shape-metrics
 * approach.
 * 
 * @author msp
 */
public class TSMGraph extends KSlimGraph {

	/**
	 * Applies all layout information to the contained layout graph objects.
	 */
	public void applyLayout(float offsetX, float offsetY, KInsets insets) {
		float totalXoff = offsetX + insets.getLeft();
		float totalYoff = offsetY + insets.getTop();
		
		// apply node layout
		for (KSlimNode node : nodes)
			((TSMNode)node).applyLayout(totalXoff, totalYoff);
		
		// apply edge layout
		for (KSlimEdge edge : edges)
			edge.rank = 0;
		for (KSlimEdge edge : edges) {
			if (edge.rank == 0)
				((TSMEdge)edge).applyLayout(totalXoff, totalYoff);
		}
	}
	
}
