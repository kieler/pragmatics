/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.codaflow.util;

import org.adaptagrams.SWIGTYPE_p_std__valarrayT_double_t;
import org.adaptagrams.TestConvergence;

/**
 * Debug helper for Cola-based layouters.
 * 
 * @author uru
 */
public class MinMaxTestConvergence extends TestConvergence {

    public int maxIterations = 20;
    public int minIterations = 1;

    /**
     */
    public MinMaxTestConvergence() {
    }

    // CHECKSTYLEOFF MethodName - I cannot change swig naming ..
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean ColaTestConvergenceOperator(final double arg0,
            final SWIGTYPE_p_std__valarrayT_double_t arg1,
            final SWIGTYPE_p_std__valarrayT_double_t arg2) {

        // we use the default implementation of the convergence test
        if (getIterations() < minIterations) {
            super.ColaTestConvergenceOperator(arg0, arg1, arg2);
            return false;
        } else if (getIterations() > maxIterations) {
            super.ColaTestConvergenceOperator(arg0, arg1, arg2);
            return true;
        } else {
            return super.ColaTestConvergenceOperator(arg0, arg1, arg2);
        }
    }

}
