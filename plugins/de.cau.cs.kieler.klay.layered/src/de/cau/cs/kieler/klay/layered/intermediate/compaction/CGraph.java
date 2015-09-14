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

import java.util.EnumSet;
import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.kiml.options.Direction;

/**
 * Internal representation of a constraint graph.
 * The {@link LGraphToCGraphTransformer} returns a {@link CGraph} to be compacted by the
 * {@link OneDimensionalCompactor}.
 * 
 * @author dag
 */
public final class CGraph {
    // Variables are public for convenience reasons since this class is used internally only.
    // SUPPRESS CHECKSTYLE NEXT 4 VisibilityModifier
    /** the list of {@link CNode}s modeling the constraints in this graph. */
    public List<CNode> cNodes = Lists.newArrayList();
    /** groups of elements that are supposed to stay in the configuration they are. */
    public List<CGroup> cGroups = Lists.newArrayList();
    /** the directions that are supported for compaction. */
    private EnumSet<Direction> supportedDirections;
    
    /**
     * Constructor sets the supported directions.
     * 
     * @param supportedDirections
     *          the directions that are supported for compaction
     */
    public CGraph(final EnumSet<Direction> supportedDirections) {
        this.supportedDirections = supportedDirections;
    }
    
    /**
     * If the {@link CGraph} supports compaction in the direction specified by the parameter.
     * @param direction
     *          the direction to check
     * @return if compaction is supported
     */
    public boolean supports(final Direction direction) {
        return supportedDirections.contains(direction);
    }
}
