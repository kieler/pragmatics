/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.tree.properties;

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
 * TODO this is not used yet 
 * 
 * @author sor
 * @author sgu
 */
public enum GraphProperties {

    /** The graph contains self-loops. */
    SELF_LOOPS;

}
