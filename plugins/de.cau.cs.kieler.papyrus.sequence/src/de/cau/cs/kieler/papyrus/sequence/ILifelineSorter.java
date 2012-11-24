/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.papyrus.sequence;

import java.util.List;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.papyrus.sequence.graph.SGraph;
import de.cau.cs.kieler.papyrus.sequence.graph.SLifeline;

/**
 * Interface for lifeline sorting algorithms.
 * 
 * @author grh
 * @kieler.design proposed grh
 * @kieler.rating proposed yellow grh
 * 
 */
public interface ILifelineSorter {
    /**
     * Sort the lifelines in the given SGraph. Some implementations may need the calculated layered
     * graph for their computation too.
     * 
     * @param graph the Sequence Graph
     * @param lgraph the layered graph
     * @param progressMonitor the progress monitor
     * @return a list of the lifelines in the calculated order
     */
    List<SLifeline> sortLifelines(SGraph graph, LGraph lgraph, IKielerProgressMonitor progressMonitor);
}
