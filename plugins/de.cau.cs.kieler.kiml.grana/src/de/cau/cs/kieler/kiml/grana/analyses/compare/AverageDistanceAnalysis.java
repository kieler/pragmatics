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

import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.KVectorChain;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.KNode;
import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.grana.IComparingAnalysis;

/**
 * Determines the average distance between the center points of nodes of two different layouts.
 * The point sets are aligned before the final result is calculated. The alignment is 
 * chosen such that the average distance is minimized over all possible alignments.
 * Reports and absolute value and an relative value. The absolute value transforms all points into a
 * common global coordinate system before measuring distances. This does not take into account that
 * if compound nodes move, their children have to move inevitably by the same amount. The relative
 * value addresses the last point.
 * 
 * @author uru
 */
public class AverageDistanceAnalysis implements IComparingAnalysis {

    /** Identifier of this analysis. */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.compare.averageDistance";
    
    /** Distances between absolute coordinates. */
    public static final int INDEX_ABSOLUTE = 0;

    /** Distances between relative coordinates as in the KGraph's coordinate system. */
    public static final int INDEX_RELATIVE = 1;
    
    /** Internal container for temporary result. */
    private class Result {
        private double absolute = 0;
        private int cntAbsolute = 0;
        private double relative = 0;
        private int cntRelative = 0;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Object doAnalysis(final KNode first, final KNode second, final Map<EObject, EObject> mapping,
            final AnalysisContext context, final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Average Distance Analysis", 1);
        
        boolean hierarchy = first.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);

        Result result = new Result();
        // --------------------------
        // # 1 analyze every level separately, accumulate the results
        analyzeRelative(first, mapping, result, hierarchy);
        
        // --------------------------
        // # 2 analyze full hierarchy by translating every
        //     node's coordinate into an absolute coordinate
        analyzeAbsolute(first, mapping, result, hierarchy);
        
        progressMonitor.done();
        return new Double[] { 
                result.absolute / result.cntAbsolute, 
                result.relative / result.cntRelative };
    }
    
    private Result analyzeAbsolute(final KNode root, final Map<EObject, EObject> mapping,
            final Result result, final boolean hierarchy) {

        KVectorChain ap1 = new KVectorChain();
        KVectorChain ap2 = new KVectorChain();
        Queue<KNode> nodes = Lists.newLinkedList();
        nodes.addAll(root.getChildren());

        while (!nodes.isEmpty()) {
            KNode node = nodes.poll();

            // only use atomic nodes
            if (node.getChildren().isEmpty()) {
                KShapeLayout sl1 = node.getData(KShapeLayout.class);
                KShapeLayout sl2 = (KShapeLayout) mapping.get(sl1);

                KVector n1Center = center(sl1);
                KVector n2Center = center(sl2);
                n1Center = ElkUtil.toAbsolute(n1Center, node.getParent());
                // CAREFUL! Take the correct parent here
                KNode secondParent = (KNode) mapping.get(node.getParent());
                n2Center = ElkUtil.toAbsolute(n2Center, secondParent);

                ap1.add(n1Center.clone());
                ap2.add(n2Center.clone());
            }

            if (hierarchy) {
                nodes.addAll(node.getChildren());
            }
        }

        PointSetUtils.alignPointSets(ap1, ap2, 
                (vc1, vc2) -> summedDistance(vc1, vc2), 
                (best, current) -> best > current);
        result.cntAbsolute = ap1.size();
        result.absolute = summedDistance(ap1, ap2);
        
        return result;
    }
    
    private Result analyzeRelative(final KNode parent, final Map<EObject, EObject> mapping,
            final Result result, final boolean hierarchy) {
   
        KVectorChain rp1 = new KVectorChain();
        KVectorChain rp2 = new KVectorChain();
        
        // collect the points
        for (KNode n1 : parent.getChildren()) {
            KShapeLayout sl1 = n1.getData(KShapeLayout.class);
            KShapeLayout sl2 = (KShapeLayout) mapping.get(sl1);

            KVector n1Center = center(sl1);
            KVector n2Center = center(sl2);
            
            rp1.add(n1Center.clone());
            rp2.add(n2Center.clone());
        }
        
        PointSetUtils.alignPointSets(rp1, rp2, 
                (vc1, vc2) -> summedDistance(vc1, vc2), 
                (best, current) -> best > current);
        result.cntRelative += rp1.size();
        result.relative += summedDistance(rp1, rp2);
        
        // hierarchy
        if (hierarchy) {
            for (KNode n1 : parent.getChildren()) {
                if (!n1.getChildren().isEmpty()) {
                    analyzeRelative(n1, mapping, result, hierarchy);
                }
            }
        }
        
        return result;
    }
    
    private double summedDistance(final KVectorChain vc1, final KVectorChain vc2) {
        if (vc1.size() != vc2.size()) {
            throw new IllegalArgumentException("The two vectors must have the same length.");
        }
        
        double sum = 0;
        final Iterator<KVector> it1 = vc1.iterator();
        final Iterator<KVector> it2 = vc2.iterator();
        while (it1.hasNext()) {
            sum += it1.next().distance(it2.next());
        }
        
        return sum;
    }

}
