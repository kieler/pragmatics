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
 * A factory for genes.
 *
 * @author bdu
 *
 */
public interface IGeneFactory {
    /**
     * Creates a gene.
     *
     * @param theId
     *            id
     * @param theValue
     *            value
     * @param theTypeInfo
     *            type info
     * @param theMutationInfo
     *            mutation info
     * @return the constructed gene
     */
    IGene<?> newGene(
            final String theId, final Object theValue, final TypeInfo<?> theTypeInfo,
            final MutationInfo theMutationInfo);

    /**
     * Creates a gene.
     *
     * @param theId
     *            id
     * @param theValue
     *            value
     * @param theMutationProb
     *            mutation prob.
     * @return the constructed gene
     */
    IGene<?> newGene(final String theId, final Object theValue, final double theMutationProb);
}
