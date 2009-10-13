/******************************************************************************
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
package de.cau.cs.kieler.kiml.layout.services;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * Interface for listeners to the KIML layout. In Eclipse such listeners must
 * register using the {@code layoutListeners} extension point.
 * <p>
 * Layout listeners are primarily used to analyze and debug the results of a
 * layout algorithm. For example, a layout listener could store the layout
 * graph structure into an XMI file to enable easy browsing of the structure.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public interface ILayoutListener {

	/**
	 * Called when layout is requested for the given layout graph.
	 * The given layout graph must not be modified by this method. As
	 * the graph is modified by the layout algorithm, a copy of it
	 * should be created to reflect its state before layout is applied.
	 * 
	 * @param layoutGraph layout graph that will be processed after
	 *     this method returns
	 */
	public void layoutRequested(KNode layoutGraph);
	
	/**
	 * Called after layout was performed for the given layout graph.
	 * The given layout graph must not be modified by this method.
	 * 
	 * @param layoutGraph layout graph that was layouted
	 * @param monitor progress monitor containing execution time results
	 */
	public void layoutPerformed(KNode layoutGraph,
			IKielerProgressMonitor monitor);
	
}
