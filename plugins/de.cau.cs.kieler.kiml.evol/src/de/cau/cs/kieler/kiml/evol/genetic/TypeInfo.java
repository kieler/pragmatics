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
        /** list item genes. */
        LIST_ITEM;
    }
    
    /** The gene type. */
    private GeneType geneType;
    /** The default value. */
    private final T defaultValue;
    /** The lower bound. */
    private final Comparable<T> lowerBound;
    /** The upper bound. */
    private final Comparable<T> upperBound;
    /** Parameter to further specify the type. */
    private final Object typeParameter;
    /** The mutation application probability. */
    private final double probability;
    /** The probability distribution for mutation. */
    private final Distribution distribution;
    /** The mutation variance, used for Gaussian distribution. */
    private final double variance;
    
   /**
    * Constructor for a type info without variance.
    *
    * @param theGeneType
    *            the gene type 
    * @param theDefaultValue
    *            the default value
    * @param theLowerBound
    *            the lower bound
    * @param theUpperBound
    *            the upper bound
    * @param theClass
    *            the class of the value
    * @param prob
    *            the probability that a mutation occurs. Must be within the
    *            interval of {@code 0.0} and {@code 1.0}.
    * @param distr
    *            the probability distribution.
    */
   public TypeInfo(final GeneType theGeneType, final T theDefaultValue,
           final Comparable<T> theLowerBound, final Comparable<T> theUpperBound,
           final Class<?> theClass, final double prob, final Distribution distr) {
       this(theGeneType, theDefaultValue, theLowerBound, theUpperBound, theClass, prob, distr, 1.0);
   }
    
    /**
     * Constructor for a type info.
     *
     * @param theGeneType
     *            the gene type 
     * @param theDefaultValue
     *            the default value
     * @param theLowerBound
     *            the lower bound
     * @param theUpperBound
     *            the upper bound
     * @param theParam
     *            the type parameter
     * @param prob
     *            the probability that a mutation occurs. Must be within the
     *            interval of {@code 0.0} and {@code 1.0}.
     * @param var
     *            the variance (used for Gaussian distribution); must be >= 0.0
     * @param distr
     *            the probability distribution.
     */
    public TypeInfo(final GeneType theGeneType, final T theDefaultValue,
            final Comparable<T> theLowerBound, final Comparable<T> theUpperBound,
            final Object theParam, final double prob, final Distribution distr, final double var) {
        if (theGeneType == null || theDefaultValue == null || theLowerBound == null
                || theUpperBound == null || distr == null || prob < 0.0 || prob > 1.0 || var < 0.0) {
            throw new IllegalArgumentException();
        }

        if (theLowerBound.compareTo(theDefaultValue) > 0) {
            throw new IllegalArgumentException("Default value < lower bound");
        } else if (theUpperBound.compareTo(theDefaultValue) <= 0) {
            throw new IllegalArgumentException("Default value > upper bound");
        }

        this.geneType = theGeneType;
        this.defaultValue = theDefaultValue;
        this.lowerBound = theLowerBound;
        this.upperBound = theUpperBound;
        this.typeParameter = theParam;
        this.probability = prob;
        this.variance = var;
        this.distribution = distr;
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
     * Returns the default value for the gene type.
     * 
     * @return the default value
     */
    public T getDefault() {
        return defaultValue;
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
     * class object of the corresponding values.
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
     * Returns the probability distribution for mutation.
     *
     * @return the distribution
     */
    public Distribution getDistr() {
        return distribution;
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
