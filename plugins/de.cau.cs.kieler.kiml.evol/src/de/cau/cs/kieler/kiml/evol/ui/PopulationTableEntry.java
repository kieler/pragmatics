package de.cau.cs.kieler.kiml.evol.ui;

import de.cau.cs.kieler.kiml.evol.Individual;

/**
 * A population table entry contains an individual.
 *
 * @author bdu
 *
 */
public class PopulationTableEntry {
    private int index = 0;
    private Individual individual;

    public String getId() {
        if (this.individual != null) {
            return this.individual.getGeneration() + "." + this.individual.getId();
        }
        return null;
    }

    public Individual getIndividual() {
        return this.individual;
    }

    public void setIndividual(final Individual theIndividual) {
        this.individual = theIndividual;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(final int i) {
        this.index = i;
    }
}
