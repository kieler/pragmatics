package de.cau.cs.kieler.kiml.evol.genetic;

/**
     * Probability distribution to specify how mutated values are generated.
     * 
     * @author bdu
     * 
     */
public enum Distribution {
        /** Normal distribution (Gaussian). **/
        GAUSSIAN,
        /** uniform distribution (does not regard previous values). **/
        UNIFORM
    }