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
package de.cau.cs.kieler.core.slimgraph.alg;

import de.cau.cs.kieler.core.alg.IAlgorithm;
import de.cau.cs.kieler.core.slimgraph.KGraphSection;

/**
 * Interface for algorithms that test planarity of a biconnected graph.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public interface IPlanarityTester extends IAlgorithm {

	/**
	 * Tests planarity of the given biconnected graph section. Any edge
	 * that is found to be incident with a node of the given section,
	 * but not part of the section itself, is removed from the graph.
	 * 
	 * @param biconnectedSection biconnected graph section
	 * @return true if the input graph is planar
	 */
	public boolean isPlanar(KGraphSection biconnectedSection);
	
}
