/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana;

import java.util.Collection;
import java.util.List;

/**
 * The interface for graph structures that express and resolve object dependencies.
 * 
 * @author mri
 * @param <S>
 *            the identifier type
 * @param <T>
 *            the object type
 */
public interface IDependencyGraph<S extends Comparable<S>, T extends IDepending<S>> {

    /**
     * Adds an object to the graph if all dependencies can be resolved.
     * 
     * @param object
     *            the object
     * @return true if the object was added
     */
    boolean add(final T object);
    
    /**
     * Removes an object from the graph and all objects depending on it.
     * 
     * @param object
     *            the object to remove
     * @return the removed objects
     */
    List<T> remove(final T object);
    
    /**
     * Adds a collection of objects to the graph and tries to resolve
     * dependencies.<br>
     * Returns a list of objects that could not be added cause they had missing
     * dependencies or were part of a cycle.
     * 
     * @param objects
     *            the objects to add
     * @return the list of objects that could not be added
     */
    List<T> addAll(final Collection<T> objects);
    
    /**
     * Returns an object by it's identifier.
     * 
     * @param id
     *            the identifier
     * @return the object
     */
    T get(final S id);
    
    /**
     * Returns a sorted list of the objects so that an object that depends on
     * another object precedes it in the list. Removes objects that are not
     * represented in this graph.
     * 
     * @param objects
     *            the objects
     * @return a sorted list respecting dependencies between the objects
     */
    List<T> dependencySort(final List<T> objects);
}
