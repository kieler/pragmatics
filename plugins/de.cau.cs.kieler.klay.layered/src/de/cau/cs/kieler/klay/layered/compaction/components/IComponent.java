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
import java.util.Set;

import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.nodespacing.Rectangle;

/**
 * A component represents, for instance, a set of nodes that are fully connected. In other words,
 * between any pair of nodes of the component exists an undirected path.
 * 
 * @author uru
 * @param <N>
 *            the type of the contained nodes
 * @param <E>
 *            the type of the contained edges
 */
public interface IComponent<N, E> {

    /**
     * @return a hull the nodes of this connected component. This, for instance, can be a
     *         rectilinear convex hull or a polyomino.
     */
    List<Rectangle> getHull();

    /**
     * @return a list with external extensions of this component.
     */
    List<IExternalExtension<E>> getExternalExtensions();
    
    /**
     * @return a set of the port sides of this component's external ports (if there are any,
     *         otherwise an empty set).
     */
    Set<PortSide> getExternalExtensionSides();

}
