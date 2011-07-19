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
package de.cau.cs.kieler.kiml.evol.genetic;

/**
 * Interface for gene.
 *
 * @param <T>
 *            type of data.
 * @author bdu
 *
 */
public interface IGene<T extends Comparable<? super T>> {
    /**
     * Return the value.
     *
     * @return value
     */
    T getValue();

    /**
     * Returns a copy of the given template instance, or {@code null}, if the
     * types are incompatible.
     *
     * @param template
     *            an instance. Must be of the same type as this instance,
     *            otherwise {@code null} is returned.
     * @return copy of the template, or {@code null}.
     */
    IGene<T> newInstance(final AbstractGene<T> template);

    /**
     * Return a new IGene that has a changed value with a certain probability.
     *
     * @return new IGene with possibly mutated value.
     */
    IGene<T> newMutation();

    /**
     * Return a new IGene that is a recombination of the the given genes.
     *
     * @param otherGenes
     *            Some genes to be recombined with this instance.
     * @return a new instance
     */
    IGene<T> recombineWith(final IGene<T>... otherGenes);

    /**
     * Return the id of the object.
     *
     * @return the id
     */
    String getId();

}
