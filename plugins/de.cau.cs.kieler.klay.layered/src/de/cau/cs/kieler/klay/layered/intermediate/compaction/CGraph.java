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
 * Internal representation of a constraint graph.
 * The {@link LGraphToCGraphTransformer} returns a {@link CGraph} to be compacted by the
 * {@link OneDimensionalCompactor}.
 * 
 * @author dag
 */
public final class CGraph {
    // Variables are public for convenience reasons since this class is used internally only.
    // SUPPRESS CHECKSTYLE NEXT 10 VisibilityModifier
    /** the instance of the associated CGraphTransformer.
     *  This is used in
     *  {@link OneDimensionalCompactor#changeDirection(de.cau.cs.kieler.kiml.options.Direction)}
     *  to find out if the original graph has edges and in that case prohibit vertical compaction.
     */
    public ICGraphTransformer<LGraph> transformer;
    /** the list of {@link CNode}s modeling the constraints in this graph. */
    public List<CNode> cNodes;
    /** groups of elements that are supposed to stay in the configuration they are. */
    public List<CGroup> cGroups;
}
