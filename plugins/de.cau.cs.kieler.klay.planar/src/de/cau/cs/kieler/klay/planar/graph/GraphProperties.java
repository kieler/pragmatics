/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.graph;

/**
 * An enumeration of properties a graph may have. These can be used as part of an {@code EnumSet} to
 * base decisions on graph properties. For example, self-loop processing may be skipped if the graph
 * doesn't contain self-loops in the first place.
 * 
 * <p>
 * An {@code EnumSet} for this enumeration can be attached to a graph via the
 * {@link Properties#GRAPH_PROPERTIES} property.
 * </p>
 * 
 * @author cds
 * @kieler.rating proposed yellow by pkl
 */
public enum GraphProperties {

    /** The graph contains comment boxes. */
    COMMENTS,
    /** The graph contains dummy nodes representing external ports. */
    EXTERNAL_PORTS,
    /** The graph is a flattened hierarchical graph. */
    FLAT_HIERARCHICAL,
    /** The graph contains ports that are not free for positioning. */
    NON_FREE_PORTS,
    /** The graph contains ports on the northern or southern side. */
    NORTH_SOUTH_PORTS,
    /** The graph contains self-loops. */
    SELF_LOOPS;

}
