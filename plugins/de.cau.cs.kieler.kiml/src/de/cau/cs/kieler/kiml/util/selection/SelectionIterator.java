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
package de.cau.cs.kieler.kiml.util.selection;

import java.util.Set;

import org.eclipse.emf.common.util.AbstractTreeIterator;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KPort;

/**
 * This class can be used to implement Iterators for selections. The SelectionIterator is used in
 * {@link de.cau.cs.kieler.kiml.util.KimlUtil} to get the {@link KGraphElement KGraphElements}
 * connected to a selected edge.
 * 
 * @author nbw
 */
public abstract class SelectionIterator extends AbstractTreeIterator<KGraphElement> {

    private static final long serialVersionUID = 478793714459586388L;

    /**
     * The list of already visited ports. Used to break infinite loops.
     */
    protected Set<KPort> visited; // SUPPRESS CHECKSTYLE VisibilityModifier

    /**
     * Creates a {@link SelectionIterator} which needs to be configured afterwards by adding the set
     * of visited nodes and the object to work on. The Iterator won't include the starting object.
     */
    public SelectionIterator() {
        super(null, false);
    }

    /**
     * Creates a {@link SelectionIterator} which needs to be configured afterwards by adding the set
     * of visited nodes. The iterator won't include the starting object.
     * 
     * @param object
     *            The object to iterate from
     */
    public SelectionIterator(final KGraphElement object) {
        super(object, false);
    }

    /**
     * Creates a {@link SelectionIterator} which needs to be configured afterwards by adding the set
     * of visited nodes.
     * 
     * @param object
     *            The object to iterate from
     * @param includeRoot
     *            Flag whether the initial {@link KGraphElement} should be included in the iterator.
     */
    public SelectionIterator(final KGraphElement object, final boolean includeRoot) {
        super(object, includeRoot);
    }

    /**
     * Attach a set of {@link KNodes KNode} to the iterator to be used as a set of visited nodes.
     * Can be used to share a set of nodes across multiple iterators (target and source iterator).
     * 
     * @param visitedSet
     *            The set of nodes to be used as visited set.
     */
    public void attachVisitedSet(final Set<KPort> visitedSet) {
        this.visited = visitedSet;
    }

    /**
     * Sets the root element of the iterator.
     * 
     * @param kgraphelement
     *            the new root element of the iterator
     */
    public void setRoot(final KGraphElement kgraphelement) {
        this.object = kgraphelement;
    }
}
