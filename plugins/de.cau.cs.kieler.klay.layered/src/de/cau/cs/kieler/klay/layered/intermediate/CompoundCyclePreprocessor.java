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
package de.cau.cs.kieler.klay.layered.intermediate;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Detects cyclic dependencies between compound nodes and reverts edges to remove them before simple
 * cycle removal and layering. Two compound nodes are regarded to be in cyclic dependency, if there
 * are cyclic adjacencies between the compound nodes themselves or between any two of their descendants
 * or between one of the compound nodes and any descendant of the other.
 * 
 * <dl>
 * <dt>Precondition:</dt>
 * <dd>A layered graph. All nodes have the ORIGIN-Property set to the KNode in the original graph
 * that is represented by them.</dd>
 * <dt>Postcondition:</dt>
 * <dd>The layered graph contains no more cyclic dependencies.</dd>
 * <dt>Slots:</dt>
 * <dd>Before phase 1.</dd>
 * <dt>Same-slot dependencies:</dt>
 * <dd>none.</dd>
 * </dl>
 * 
 * @author ima
 */
public class CompoundCyclePreprocessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Revert edges to remove cyclic dependencies between compound nodes", 1);

        getMonitor().done();
    }

}
