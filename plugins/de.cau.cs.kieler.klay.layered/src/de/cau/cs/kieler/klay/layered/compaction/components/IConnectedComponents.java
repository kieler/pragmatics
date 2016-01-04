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

/**
 * A set of {@link IComponent}s. 
 * 
 * @author uru
 * @param <N>
 *            the type of the contained nodes
 * @param <E>
 *            the type of the contained edges
 */
public interface IConnectedComponents<N, E> extends Iterable<IComponent<N, E>> {

    /**
     * @return the components
     */
    List<IComponent<N, E>> getComponents();

    /**
     * @return whether any of the components contains external ports 
     */
    boolean isContainsExternalPorts();

}
