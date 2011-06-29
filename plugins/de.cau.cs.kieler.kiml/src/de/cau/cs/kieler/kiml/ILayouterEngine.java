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
 * Interface for layouter engines. Introduces a common api to access any layouter engine
 * for the main layout manager {@link #DiagramLayoutManager}
 * 
 * @kieler.rating 2011-05-03 red
 *
 * @author swe
 */
public interface ILayouterEngine {

    /**
     * Performs layout on the given layout graph.
     * 
     * @param layoutGraph instance of a layout graph
     * @param progressMonitor monitor to which progress of the layout algorithms is reported
     * @param layoutAncestors if true, layout is not only performed for the selected
     *         node, but also for its ancestors
     * @throws Exception 
     */
    void layout(final KNode layoutGraph, final IKielerProgressMonitor progressMonitor) throws Exception;

    /**
     * Returns the last layout provider that was used by the layouter engine.
     * This can be used to check the source of error if an exception is caught
     * during layout.
     * 
     * @return the last used layout provider, or {@code null} if there is none
     */
    AbstractLayoutProvider getLastLayoutProvider();

}
