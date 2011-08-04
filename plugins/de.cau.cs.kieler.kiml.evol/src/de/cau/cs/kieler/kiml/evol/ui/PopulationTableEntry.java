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
package de.cau.cs.kieler.kiml.evol.ui;

import de.cau.cs.kieler.kiml.evol.genetic.Genome;

/**
 * A population table entry contains an individual.
 *
 * @author bdu
 *
 */
public class PopulationTableEntry {
    private int index = 0;
    private Genome individual;

    /**
     *
     * @return the id of the individual.
     */
    public String getId() {
        if (this.individual != null) {
            return this.individual.getGenerationNumber() + "." + this.individual.getId();
        }
        return null;
    }

    /**
     *
     * @return the individual
     */
    public Genome getIndividual() {
        return this.individual;
    }

    /**
     * Sets the given individual to be contained in the entry.
     *
     * @param theIndividual
     *            the individual
     */
    public void setIndividual(final Genome theIndividual) {
        this.individual = theIndividual;
    }

    /**
     *
     * @return the index of the entry.
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Sets the the given value as index.
     *
     * @param i
     *            an {@code int} value.
     *
     */
    public void setIndex(final int i) {
        this.index = i;
    }
}
