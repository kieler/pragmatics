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
package de.cau.cs.kieler.klay.planar.util;

/**
 * Interface for a function that transform an object of one type into an object of another type.
 * 
 * @param <T>
 *            the type of the function parameter
 * @param <E>
 *            the type of the function result
 * @author ocl
 */
public interface IFunction<T, E> {

    /**
     * Perform the function on the given element.
     * 
     * @param element
     *            the parameter
     * @return the resulting element
     */
    E evaluate(T element);
}
