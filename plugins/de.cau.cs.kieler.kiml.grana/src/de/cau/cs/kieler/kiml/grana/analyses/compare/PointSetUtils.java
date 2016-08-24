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

import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.KVectorChain;
import org.eclipse.elk.graph.KNode;

/**
 * This class holds utility methods to align two point sets according to some metric.
 * 
 * @author uru
 */
public final class PointSetUtils {

    private PointSetUtils() { }
    
    // SUPPRESS CHECKSTYLE NEXT 20 Javadoc 
    /**
     * Executes some metric on the passed point sets.
     */
    @FunctionalInterface
    public interface Evaluator<T> {
        /**
         * Executes some metric on the passed point sets and return a result.
         */
        T evaluate(KVectorChain vc1, KVectorChain vc2);
    }

    /**
     * Compare the results of two runs of the {@link Evaluator} and tell which one is better.
     */
    @FunctionalInterface
    public interface ResultComparator<T> {
        /**
         * @return true if the {@code current} result is better than {@code best}.
         */
        boolean rightIsBetter(T best, T current);
    }

    /**
     * Align the two point sets, such that the average distance between them is minimized. To do so,
     * an offset value is calculated and applied to {@code vc2}. Uses a default step size of 1 pixel. 
     * 
     * @param vc1
     *            first point set
     * @param vc2
     *            second point set
     * @param evaluator
     *            the metric according to which the points should be applied
     * @param resultComparator
     *            a comparator able to tell which of two results produced by {@code evaluator} is
     *            better.
     * @param <T> The type of the result.
     * @return The offset applied {@code vc2}.
     * 
     * @see #alignPointSets(KVectorChain, KVectorChain, double, Evaluator, ResultComparator)
     */
    public static <T> KVector alignPointSets(final KVectorChain vc1, final KVectorChain vc2,
            final Evaluator<T> evaluator, 
            final ResultComparator<T> resultComparator) {
        return alignPointSets(vc1, vc2, 1, evaluator, resultComparator);
    }
    
    /**
     * Align the two point sets, such that the average distance between them is minimized. To do so,
     * an offset value is calculated and applied to {@code vc2}.
     * 
     * @param vc1
     *            first point set
     * @param vc2
     *            second point set
     * @param stepSize
     *            the used step size by which the points are shifted when seeking for the best alignment
     * @param evaluator
     *            the metric according to which the points should be applied
     * @param resultComparator
     *            a comparator able to tell which of two results produced by {@code evaluator} is
     *            better.
     * @param <T> The type of the result.
     * @return The offset applied {@code vc2}.
     */
    public static <T> KVector alignPointSets(final KVectorChain vc1, final KVectorChain vc2,
            final double stepSize,
            final Evaluator<T> evaluator, 
            final ResultComparator<T> resultComparator) {
        
        // normalize such that minimum coordinates are zero
        normalize(vc1);
        normalize(vc2);

        // now find the maximum coordinates of both
        KVector maxCoords1 = maxCoordinates(vc1);
        KVector maxCoords2 = maxCoordinates(vc2);
        
        // we want to find a common x offset and y offset for the points in 'vc2' 
        //  such that the average distance is minimized over all such offsets
        double xRange = maxCoords1.x - maxCoords2.x;
        double yRange = maxCoords1.y - maxCoords2.y;
        
        double stepCntX = (int) Math.abs(xRange / stepSize); // floor this
        double stepCntY = (int) Math.abs(yRange / stepSize);
        
        T bestResult = null;
        KVector bestOffset = null;
        for (int i = 0; i <= stepCntX; ++i) { // <= because of floor
            double xOffset = i * stepSize * (maxCoords1.x > maxCoords2.x ? 1 : -1);
            for (int j = 0; j <= stepCntY; ++j) {
                double yOffset = j * stepSize * (maxCoords1.y > maxCoords2.y ? 1 : -1);
                
                KVector offset = new KVector(xOffset, yOffset);
                vc2.offset(offset);
                
                // analyze!
                // double result = summedDistance(vc1, vc2);
                T result = evaluator.evaluate(vc1, vc2); 
                // no need to normalize with 'n' since all have the same number of points
                if (bestResult == null || resultComparator.rightIsBetter(bestResult, result)) {
                    bestResult = result;
                    bestOffset = offset;
                }
                
                vc2.offset(offset.clone().negate());
            }
        }
        
        // apply the best offset
        vc2.offset(bestOffset);
        
        return bestOffset;
    }
   
    /**
     * @param vc
     */
    private static void normalize(final KVectorChain vc) {
        KVector minCoordinates = minCoordinates(vc);
        for (KVector v : vc) {
            v.x -= minCoordinates.x;
            v.y -= minCoordinates.y;
        }
    }
    
    private static KVector minCoordinates(final KVectorChain vc) {
        double minX = Double.POSITIVE_INFINITY, minY = Double.POSITIVE_INFINITY;
        for (KVector v : vc) {
            minX = Math.min(minX, v.x);
            minY = Math.min(minY, v.y);
        }
        return new KVector(minX, minY);
    }
    
    private static KVector maxCoordinates(final KVectorChain vc) {
        double maxX = Double.NEGATIVE_INFINITY, maxY = Double.NEGATIVE_INFINITY;
        for (KVector v : vc) {
            maxX = Math.max(maxX, v.x);
            maxY = Math.max(maxY, v.y);
        }
        return new KVector(maxX, maxY);
    }

    /**
     * @param sl
     *            a {@link KShapeLayout}
     * @return the center point of the bounding box described by {@code sl}.
     */
    public static KVector center(final KShapeLayout sl) {
        return sl.createVector().add(sl.getWidth() / 2f, sl.getHeight() / 2f);
    }

    /**
     * @param n
     *            a node
     * @return the center of the bounding box of {@code n}.
     */
    public static KVector center(final KNode n) {
        KShapeLayout sl = n.getData(KShapeLayout.class);
        return sl.createVector().add(sl.getWidth() / 2f, sl.getHeight() / 2f);
    }
}
