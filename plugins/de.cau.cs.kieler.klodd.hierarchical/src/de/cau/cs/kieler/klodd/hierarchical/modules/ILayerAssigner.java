/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayeredGraph;

/**
 * Interface for algorithms that assign layers to each node of a graph.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public interface ILayerAssigner extends IAlgorithm {
	
	/**
	 * Create a layered graph and assign layers to each node. The input
	 * ports must be put into the first layer (rank == 0), the output
	 * ports into the last layer (height == 0).
	 * 
	 * @param slimGraph graph structure that contains no directed cycles
	 * @param parentNode the parent layout node
	 * @param balanceOverSize indicates whether node balancing has priority
	 *     over diagram size
	 * @return a layered graph
	 */
	public LayeredGraph assignLayers(KSlimGraph slimGraph, KNode parentNode,
	        boolean balanceOverSize);

}
