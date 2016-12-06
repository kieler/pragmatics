/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.kiml.grana.analyses.compare;

import static de.cau.cs.kieler.kiml.grana.analyses.compare.PointSetUtils.center;

import java.util.Map;

import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.KNode;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Function;

import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.grana.IComparingAnalysis;

/**
 * Implements the "Orthogonal Ordering" difference metric as described by Bridgeman and Tamassia.
 * See: 
 * <pre>
 *   S. Bridgeman and R. Tamassia, 
 *   "Difference Metrics for Interactive Orthogonal Graph Drawing Algorithms"
 * <pre>
 * 
 * @author uru
 */
public class OrthogonalOrderingAnalysis implements IComparingAnalysis {

    /** Identifier of this analysis. */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.compare.orthogonalOrdering";
    
    /** Constant weighted order. */
    public static final int INDEX_CONSTANT_WEIGHTED = 0;

    /** Linear weighted order. */
    public static final int INDEX_LINEAR_WEIGHTED = 1;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Object doAnalysis(final KNode first, final KNode second,
            final Map<EObject, EObject> mapping, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Orthogonal Ordering Analysis", 1);
        
        boolean hierarchy = first.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);
        
        // gather results
        Result result = new Result();
        analyzeHierarchy(first, mapping, result, hierarchy);
        
        progressMonitor.done();
        // don't forget to normalize
        return new Double[] { result.constantWeighted / (Math.PI * result.n), 
                              result.linearWeighted / (TWO_PI * result.n) }; 
    }
    
    private void analyzeHierarchy(final KNode parent, final Map<EObject, EObject> mapping,
            final Result result, final boolean hierarchy) {

        int n = parent.getChildren().size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                
                KNode n1 = parent.getChildren().get(i);
                KNode n2 = parent.getChildren().get(j);
        
                KVector p = center(n1);
                KVector pPrime = center((KNode) mapping.get(n1));

                KVector q = center(n2);
                KVector qPrime = center((KNode) mapping.get(n2));
            
                double[] order = getOrthogonalOrder(p, pPrime, q, qPrime);
                result.constantWeighted += order[INDEX_CONSTANT_WEIGHTED];
                result.linearWeighted += order[INDEX_LINEAR_WEIGHTED];
                result.n++;
            }
        }
        
        if (hierarchy) {
            for (KNode child : parent.getChildren()) {
                if (!child.getChildren().isEmpty()) {
                    analyzeHierarchy(child, mapping, result, hierarchy);
                }
            }
        }
    }
    
    /** Container for holding temporary results. */
    private class Result {
        private double constantWeighted = 0d;
        private double linearWeighted = 0d;
        private double n = 0;
    }
    
    private static final KVector HORZ_VECTOR = new KVector(1, 0);
    private static final double PI_HALF = Math.PI / 2d;
    private static final double PI_FOURTH = Math.PI / 4d; // SUPPRESS CHECKSTYLE MagicNumber
    private static final double TWO_PI = 2 * Math.PI;
    
    /**
     * Determines the order between the two vectors (q1-p1) and (q2-p2).
     * 
     * Input: p1 moved to q1 p2 moved to q2
     * 
     * @return the order as determined by the input vectors in the form of
     *         <code>{ constantWeighted, linearWeighted } </code>.
     */
    // SUPPRESS CHECKSTYLE NEXT 2 Javadoc
    public static double[] getOrthogonalOrder(final KVector p1, final KVector p2, final KVector q1,
            final KVector q2) {

        final KVector t1 = q1.clone().sub(p1);
        final KVector t2 = q2.clone().sub(p2);
        
        final double theta1 = getTheta(HORZ_VECTOR, t1);
        final double theta2 = getTheta(HORZ_VECTOR, t2);
        
        double a = Math.min(theta1, theta2);
        double b = Math.max(theta1, theta2);

        // SUPPRESS CHECKSTYLE NEXT 10 MagicNumber
        double area1 = integrateSimpson(d -> 1d, a, b, 100);
        double area2 = integrateSimpson(d -> 1d, b, TWO_PI + a, 1000);
        
        double constantOrder = Math.min(area1, area2);
        
        area1 = integrateSimpson(d -> linearWeighted(d), a, b, 100);
        area2 = integrateSimpson(d -> linearWeighted(d), b, TWO_PI + a, 1000);
        
        double linearOrder = Math.min(area1, area2);
        
        return new double[]{ constantOrder, linearOrder };
    }
    
    private static double linearWeighted(final double theta) {
        if ((theta % PI_HALF) > PI_FOURTH) {
            return (PI_HALF - (theta % PI_HALF)) / PI_FOURTH;
        } else {
            return (theta % PI_HALF) / PI_FOURTH;
        }
    }

    /**
     * Implementation of Simpon's rule for numerical integration. See
     * https://en.wikipedia.org/wiki/Simpson's_rule.
     * 
     * @param fun
     *            function to integrate over the interval [start, end]
     * @param start
     *            start of the integration interval
     * @param end
     *            end of the integration interval
     * @param intervals
     *            number of subintervals to be used for Simpson's rule
     * @return the integral of fun over [start, end].
     */
    public static double integrateSimpson(final Function<Double, Double> fun, final double start,
            final double end, final int intervals) {
        if (intervals % 2 != 0) {
            throw new IllegalArgumentException("Number of intervals must be multiple of 2.");
        }
        double h = (end - start) / intervals;
        double sum = fun.apply(start) + fun.apply(end);

        for (int i = 1; i < intervals; i += 2) {
            sum += 4 * fun.apply(start + i * h); // SUPPRESS CHECKSTYLE MagicNumber
        }
        for (int i = 2; i < intervals - 1; i += 2) {
            sum += 2 * fun.apply(start + i * h);
        }

        return sum * h / 3; // SUPPRESS CHECKSTYLE MagicNumber
    }

    /**
     * @param v
     *            first vector
     * @param w
     *            second vector
     * @return the counter-clockwise angle from v to w in radians.
     */
    public static double getTheta(final KVector v, final KVector w) {
        double theta = Math.atan2(w.y, w.x) - Math.atan2(v.y, v.x);
        // negative sign indicates clock-wise angle 
        if (theta < 0) {
            theta = 2 * Math.PI + theta;
        }
        return theta;
    }
}
