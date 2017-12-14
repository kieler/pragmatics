/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.grana.analyses.compare;

import java.util.List;
import java.util.Map;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.IComparingAnalysis;

/**
 * This measure is used to determine how much the topologies of
 * two layouts differ. For this purpose, the layouts are represented each by a set of
 * points where a point is the center position of an object in a layout. These points
 * are a good approximation for the layout’s topology as they capture the orthogonal
 * ordering as well as changes in proximity relations. The two sets of points are then
 * optimally superimposed by scaling, translating, and rotating. This has the
 * effect that these kinds of transformations do not affect the result of the Procrustes
 * analysis which is desired because such transformations have no influence on the
 * topology of a layout and should be disregarded. The result of this analysis is
 * the Procrustes distance that is defined as the square root of the sum of squared
 * distances between corresponding points from either set.
 * 
 * <pre>
 *   Anand Rangarajan, Haili Chui, and Fred L. Bookstein, 
 *   "The softassign Procrustes matching algorithm"
 * <pre>
 * 
 * @author Kiel University
 */
public class ProcrustesAnalysis implements IComparingAnalysis {
    
    /** Identifier of this analysis. */
    public static final String ID = "de.cau.cs.kieler.grana.compare.procrustes";
    
    /** Procrustes distance. */
    public static final int INDEX_PROCRUSTES = 0;
    
    /** Relative shift between the graphs. */
    public static final int INDEX_REL_SHIFT = 1;
    
    /** Relative scaling between the graphs. */
    public static final int INDEX_REL_SCALE = 2;
    
    /** Relative rotation between the graphs. */
    public static final int INDEX_REL_ROT = 3;
    
    // CHECKSTYLEOFF MagicNumber
    /** Array holding the results. */
    private Double[] results = new Double[4];

    /**
     * {@inheritDoc}
     */
    @Override
    public Object doAnalysis(final ElkNode first, final ElkNode second, final Map<EObject, EObject> mapping,
            final AnalysisContext context, final IElkProgressMonitor progressMonitor) {
        
        progressMonitor.begin("Procrustes Analysis", 1);
        
        // get center positions of nodes
        List<KVector> points1 = Lists.newArrayList();
        List<KVector> points2 = Lists.newArrayList();
        for (ElkNode node1 : first.getChildren()) {
            ElkNode node2 = (ElkNode) mapping.get(node1);
            points1.add(new KVector(node1.getX() + node1.getWidth() / 2,
                    node1.getY() + node1.getHeight() / 2));
            points2.add(new KVector(node2.getX() + node2.getWidth() / 2,
                    node2.getY() + node2.getHeight() / 2));
        }
        
        // normalize translational component
        KVector mean1 = normalizeTranslation(points1);
        KVector mean2 = normalizeTranslation(points2);
        results[INDEX_REL_SHIFT] = mean1.sub(mean2).length();
        
        // normalize scaling component
        double scale1 = normalizeScaling(points1);
        double scale2 = normalizeScaling(points2);
        results[INDEX_REL_SCALE] = scale1 / scale2;
        
        // normalize rotational component
        results[INDEX_REL_ROT] = normalizeRotation(points1, points2);
        
        // calculate the square root of the sum of squared distances
        results[INDEX_PROCRUSTES] = Math.sqrt(ssd(points1, points2));
        
        progressMonitor.done();
        return results;
    }
    
    /**
     * Shifts the points such that their barycenter is (0, 0).
     * 
     * @param points the center points of the nodes
     * @return the applied offset
     */
    private KVector normalizeTranslation(final List<KVector> points) {
        KVector mean = new KVector(0, 0);
        points.forEach(p -> mean.add(p));
        mean.scale(1.0 / points.size());
        points.forEach(p -> p.sub(mean));
        return mean;
    }
    
    /**
     * Normalizes the scaling to the effect that
     * the average distance from the origin is one.
     * 
     * @param points the center points of the nodes
     * @return the applied scaling factor
     */
    private double normalizeScaling(final List<KVector> points) {
        double sumSq = 0;
        for (KVector p : points) {
            sumSq += p.squareLength();
        }
        double scale = 1.0 / Math.sqrt(sumSq / points.size());
        points.forEach(p -> p.scale(scale));
        return scale;
    }
    
    /**
     * Rotates the points from b to optimally superimpose a and b.
     * 
     * @param a the center points of the nodes from the first layout
     * @param b the center points of the nodes from the second layout
     * @return the applied angle of rotation
     */
    private double normalizeRotation(final List<KVector> a, final List<KVector> b) {
        double sum1 = 0, sum2 = 0;
        for (int i = 0; i < a.size(); i++) {
            sum1 += b.get(i).x * a.get(i).y - b.get(i).y * a.get(i).x;
            sum2 += b.get(i).x * a.get(i).x + b.get(i).y * a.get(i).y;
        }
        double angle = Math.atan(sum1 / sum2);
        // rotate
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        b.forEach(p -> p.set(new KVector(p.x * cos - p.y * sin,
                p.x * sin + p.y * cos)));
        return angle;
    }
    
    /**
     * Adds the square distances of the pairs of corresponding points from
     * the two lists.
     * 
     * @param a the center points of the nodes from the first layout
     * @param b the center points of the nodes from the first layout
     * @return the sum of squared distances
     */
    private double ssd(final List<KVector> a, final List<KVector> b) {
        double result = 0;
        for (int i = 0; i < a.size(); i++) {
            result += a.get(i).clone().sub(b.get(i)).squareLength();
        }
        return result;
    }
}
