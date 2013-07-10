/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.tree;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.tree.graph.TGraph;

/**
 * A layout processor processes a {@link de.cau.cs.kieler.klay.tree.graph.TGraph}, performing layout
 * related tasks on it.
 * 
 * @author sor
 * @author sgu
 * @author cds
 */
public interface ILayoutProcessor {

    /**
     * Performs the phase's work on the given graph.
     * 
     * @param tGraph
     *            a tree graph
     * @param progressMonitor
     *            a progress monitor to track algorithm execution
     */
    void process(TGraph tGraph, IKielerProgressMonitor progressMonitor);

}
