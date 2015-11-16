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
package de.cau.cs.kieler.klay.layered.compaction.components;

import java.util.List;
import java.util.Map;
import java.util.Set;

import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.nodespacing.Rectangle;

/**
 * A component represents a set of nodes that are fully connected. In other words, between any pair
 * of nodes of the component exists an undirected path.
 * 
 * @author uru
 * @param <N>
 *            the type of the contained nodes
 * @param <E>
 *            the type of the contained edges
 */
public interface IComponent<N, E> {

    /**
     * @return the nodes contained in this component.
     */
    List<N> getNodes();
    
    /**
     * @return the external edges contained in this component.
     */
    List<E> getExternalEdges();
    
    /**
     * @return the port sides of this component's external ports (if there are any).
     */
    Set<PortSide> getExternalPortSides();

    /**
     * @return a hull the nodes of this connected component. This, for instance, can be a
     *         rectilinear convex hull or a polyomino.
     */
    List<Rectangle> getHull();

    /**
     * @return for each external edge, return a hull. This, for instance, can be a rectilinear
     *         convex hull or a polyomino.
     */
    Map<IExternalEdge<E>, List<Rectangle>> getExternalEdgeHulls();

}
