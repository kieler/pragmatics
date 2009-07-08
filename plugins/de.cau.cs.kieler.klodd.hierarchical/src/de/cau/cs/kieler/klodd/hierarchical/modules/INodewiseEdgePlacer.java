/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.hierarchical.modules;

import de.cau.cs.kieler.core.alg.IAlgorithm;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayeredGraph;

/**
 * Interface for algorithms that calculate routing slots for connections
 * starting and ending at regular nodes.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public interface INodewiseEdgePlacer extends IAlgorithm {

	/**
	 * Determine placement for all edges starting or ending at
	 * regular nodes of the given layered graph.
	 * 
	 * @param layeredGraph
	 */
	public void placeEdges(LayeredGraph layeredGraph);
	
}
