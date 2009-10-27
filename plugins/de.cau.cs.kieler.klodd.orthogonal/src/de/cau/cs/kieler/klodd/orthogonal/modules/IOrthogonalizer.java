/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
package de.cau.cs.kieler.klodd.orthogonal.modules;

import de.cau.cs.kieler.core.alg.IAlgorithm;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;

/**
 * Interface for algorithms that perform orthogonalization of a graph.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public interface IOrthogonalizer extends IAlgorithm {
	
	/**
	 * Performs orthogonalization of a given TSM graph.
	 * 
	 * @param graph TSM graph that is to be filled with shape information
	 */
	public void orthogonalize(KSlimGraph graph);

}
