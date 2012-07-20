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

package de.cau.cs.kieler.kiml;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * Interface for graph layout engines.
 * 
 * @author swe
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public interface IGraphLayoutEngine {

    /**
     * Performs layout on the given layout graph.
     * 
     * @param layoutGraph instance of a layout graph
     * @param progressMonitor monitor to which progress of the layout algorithms is reported
     */
    void layout(KNode layoutGraph, IKielerProgressMonitor progressMonitor);
    
    /**
     * Determine whether the layout engine is active.
     * 
     * @return true if the engine is active
     */
    boolean isActive();

}
