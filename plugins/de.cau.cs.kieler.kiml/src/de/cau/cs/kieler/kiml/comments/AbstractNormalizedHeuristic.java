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
package de.cau.cs.kieler.kiml.comments;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * Provides a basic and configurable implementation of a normalized heuristic. Clients have to
 * implement {@link #raw(KNode, KGraphElement)}, the rest is done by this implementation. The actual
 * normalization is done through instances of {@link NormalizationFunction}. This class exposes a
 * number of such functions to subclasses.
 * 
 * <p>
 * The way normalization is performed can be controlled through a bunch of configuration methods
 * that are protected to be accessed by subclasses only. Subclasses will probably want to offer
 * specialized configuration methods that call the configuration methods in this class as
 * appropriate.
 * </p>
 * 
 * @author cds
 */
public abstract class AbstractNormalizedHeuristic implements IHeuristic {
    
    /**
     * Enumeration of available normalization functions.
     * 
     * @author cds
     */
    public enum NormalizationFunction {
        
        /** Interpolates linearly between the worst and best value. */
        LINEAR;
        
    }
    
    /** The raw value that marks the point at which the normalized heuristic will become 0. */
    private double worstRawValue = 0;
    /** The raw value that marks the point at which the normalized heuristic will become 1. */
    private double bestRawValue = 1;
    /** The function used to normalize raw values. */
    private NormalizationFunction normalizationFunction = NormalizationFunction.LINEAR;
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Configuration
    
    /**
     * Sets the raw values at which the normalized value will become 0 and 1. There is no
     * restriction on which of the two is the larger value. However, the two must not be equal.
     * 
     * @param worstRawVal
     *            the raw value at which the normalized value will become 0.
     * @param bestRawVal
     *            the raw value at which the normalized value will become 1.
     * @throws IllegalArgumentException
     *             if {@code worstRawValue == bestRawValue}.
     */
    protected final void setBounds(final double worstRawVal, final double bestRawVal) {
        if (worstRawVal == bestRawVal) {
            throw new IllegalArgumentException("Worst and best raw values must not be equal.");
        }
        
        this.worstRawValue = worstRawVal;
        this.bestRawValue = bestRawVal;
    }
    
    /**
     * Sets how exactly raw values between the bounds are normalized.
     * 
     * @param normalizationFunction
     *            the function to use for normalization.
     * @throws IllegalArgumentException if the normalization function is {@code null}.
     */
    protected final void setNormalizationFunction(final NormalizationFunction normalizationFunction) {
        if (normalizationFunction == null) {
            throw new IllegalArgumentException("Normalization function cannot be null.");
        }
        
        this.normalizationFunction = normalizationFunction;
    }

    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // IHeuristic Implementation
    
    /**
     * {@inheritDoc}
     */
    @Override
    public double normalized(final KNode comment, final KGraphElement element) {
        double rawValue = raw(comment, element);
        
        switch (normalizationFunction) {
        case LINEAR:
            return normalizeLinear(rawValue);
        default:
            assert false;
            return 0;
        }
    }

    
    /////////////////////////////////////////////////////////////////////////////////////////////
    // Normalization Functions
    
    /**
     * Normalizes the given raw value by interpolating linearly between the worst and best values.
     * 
     * @param raw
     *            the raw value.
     * @return the normalized value.
     */
    protected final double normalizeLinear(final double raw) {
        if (worstRawValue < bestRawValue) {
            if (raw <= worstRawValue) {
                return 0;
            } else if (raw >= bestRawValue) {
                return 1;
            } else {
                return (raw - worstRawValue) / (bestRawValue - worstRawValue);
            }
        } else if (bestRawValue < worstRawValue) {
            if (raw <= bestRawValue) {
                return 1;
            } else if (raw >= worstRawValue) {
                return 0;
            } else {
                return 1 - (raw - bestRawValue) / (worstRawValue - bestRawValue);
            }
        } else {
            assert false;
            return -1;
        }
    }

}
