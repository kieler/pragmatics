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
 * A type info describes the parameters of a type that is used in an {@link IGene}.
 *
 * @author bdu
 * @author msp
 * @param <T> the type of contained values
 */
public class TypeInfo<T extends Comparable<? super T>> {
    
    /**
     * Enumeration of available gene types.
     */
    public static enum GeneType {
        /** integer number genes. */
        INTEGER,
        /** floating point number genes. */
        FLOAT,
        /** enumeration genes. */
        ENUM,
        /** boolean value genes. */
        BOOLEAN,
        /** layout type genes: choose from a list of defined layout types. */
        LAYOUT_TYPE,
        /** layout algorithm genes: choose from a list of available layout algorithms. */
        LAYOUT_ALGO;
    }
    
    /** The id of this gene type. */
    private final String id;
    /** The gene type. */
    private final GeneType geneType;
    /** The lower bound. */
    private final Comparable<T> lowerBound;
    /** The upper bound. */
    private final Comparable<T> upperBound;
    /** Parameter to further specify the type. */
    private final Object typeParameter;
    /** The mutation application probability. */
    private final double probability;
    /** The mutation variance, used for Gaussian distribution. */
    private final double variance;
    
    /**
     * Constructor for a type info.
     *
     * @param theid
     *            the gene identifier
     * @param theGeneType
     *            the gene type 
     * @param theLowerBound
     *            the lower bound
     * @param theUpperBound
     *            the upper bound
     * @param theParam
     *            the type parameter
     * @param prob
     *            the probability that a mutation occurs. Must be within the
     *            interval of 0.0 and 1.0.
     * @param var
     *            the variance (used for Gaussian distribution); must be >= 0.0
     */
    public TypeInfo(final String theid, final GeneType theGeneType, final Comparable<T> theLowerBound,
            final Comparable<T> theUpperBound, final Object theParam, final double prob,
            final double var) {
        if (theid == null || theGeneType == null || theLowerBound == null || theUpperBound == null
                || prob < 0.0 || prob > 1.0 || var < 0.0) {
            throw new IllegalArgumentException();
        }

        this.id = theid;
        this.geneType = theGeneType;
        this.lowerBound = theLowerBound;
        this.upperBound = theUpperBound;
        this.typeParameter = theParam;
        this.probability = prob;
        this.variance = var;
    }

    /**
     * Return the identifier of the gene.
     *
     * @return the identifier
     */
    public String getId() {
        return id;
    }
    
    /**
     * Returns the gene type.
     * 
     * @return the gene type
     */
    public GeneType getGeneType() {
        return geneType;
    }

    /**
     * Returns the lower bound for the gene type.
     * 
     * @return the lower bound
     */
    public Comparable<T> getLowerBound() {
        return lowerBound;
    }

    /**
     * Returns the upper bound for the gene type.
     * 
     * @return the upper bound
     */
    public Comparable<T> getUpperBound() {
        return upperBound;
    }

    /**
     * Returns the type parameter associated with the gene type. For most genes this is the
     * layout option data element from the KIML layout data service. For {@link GeneType#LAYOUT_ALGO}
     * and {@link GeneType#LAYOUT_TYPE} it is the list of available algorithms or types, respectively.
     *
     * @return the type parameter
     */
    public Object getTypeParam() {
        return typeParameter;
    }

    /**
     * Returns the probability for application of mutation.
     *
     * @return the mutation application probability
     */
    public double getProbability() {
        return probability;
    }

    /**
     * Returns the mutation variance, used for Gaussian distribution.
     *
     * @return the mutation variance
     */
    public double getVariance() {
        return variance;
    }
    
}
