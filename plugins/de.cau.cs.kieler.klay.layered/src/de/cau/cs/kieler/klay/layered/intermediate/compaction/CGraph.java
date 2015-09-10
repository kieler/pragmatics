/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klay.layered.intermediate.compaction;

import java.util.List;

import de.cau.cs.kieler.klay.layered.graph.LGraph;

/**
 * @author dag
 */
public final class CGraph {
    // TODO not generic
    /** the layered graph. */
    public LGraph layeredGraph;
    /** the list of {@link CNode}s modeling the constraints in this graph. */
    public List<CNode> cNodes;
    /** groups of elements that are supposed to stay in the configuration they are. */
    public List<CGroup> cGroups;
}
