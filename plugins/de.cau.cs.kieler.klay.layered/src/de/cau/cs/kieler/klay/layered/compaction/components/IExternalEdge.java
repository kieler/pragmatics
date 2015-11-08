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

/**
 * Container class for edges that represent external edges, i.e. edges that connect to ports that
 * span hierarchy boundaries.
 * 
 * @author uru
 *
 * @param <E>
 *            the type of the contained edge.
 */
public interface IExternalEdge<E> {

    /**
     * @return the underlying edge this container represents
     */
    E getEdge();

    /**
     * @return whether this is a vertical external edge. An external edge is <em>vertical</em> if it
     *         either connects to an external port with port side
     *         {@link de.cau.cs.kieler.kiml.options.PortSide PortSide#NORTH}, or port side
     *         {@link de.cau.cs.kieler.kiml.options.PortSide PortSide#SOUTH}. Otherwise it is
     *         <em>horizontal</em>.
     */
    boolean isVertical();

}
