/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.cola.processors;

import de.cau.cs.kieler.adaptagrams.cgraph.CGraph;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;

/**
 * A layout processor processes a {@link CGraph}, performing layout related tasks on it.
 * 
 * @author cds
 * @author uru
 * 
 */
public interface ILayoutProcessor {

    /**
     * Performs the phase's work on the given graph.
     * 
     * @param graph
     *            a cola graph
     * @param progressMonitor
     *            a progress monitor to track algorithm execution
     */
    void process(CGraph graph, IKielerProgressMonitor progressMonitor);

}
