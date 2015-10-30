/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * A graph analysis that counts the number of bendpoints. Returns a four-component
 * result {@code (int min, float avg, int max, int sum)}.
 * 
 * <p>The analysis collects all bend points and eliminates duplicates. This makes
 * sense for orthogonally routed hyperedges, but may miss a bend point if two completely
 * unrelated edges share a bend point. However, this case is very unlikely and wouldn't
 * make sense in a proper diagram anyway, so we live with not counting it.</p>
 * 
 * <p>Care has to be taken for bendpoints within different compound nodes. Their 
 * coordinates are converted to absolute values.</p>
 * 
 * @author mri
 * @author cds
 * @author uru
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class BendsAnalysis implements IAnalysis {
    
    /**
     * Identifier of the bend points analysis.
     */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.bendpointCount";

    // SUPPRESS CHECKSTYLE NEXT 5 Javadoc
    public static final int INDEX_MIN = 0;
    public static final int INDEX_AVG = 1;
    public static final int INDEX_MAX = 2;
    public static final int INDEX_SUM = 3;
    public static final int INDEX_SUM_NON_UNIQUE = 4;
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode,
            final AnalysisContext context,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Number of Bends analysis", 1);
        
        // The set of unique bend points
        Set<KVector> uniqueBendPoints = new HashSet<KVector>();
        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);
        
        // Per-edge bend point analyses
        int min = Integer.MAX_VALUE;
        float avg = 0.0f;
        int max = 0;
        int nonUniqueSum = 0;
        int edges = 0;
        int current;
        
        // Iterate through all nodes
        List<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.addAll(parentNode.getChildren());
        while (nodeQueue.size() > 0) {
            // Pop first element
            KNode node = nodeQueue.remove(0);
            
            // Iterate through the node's edges
            for (KEdge edge : node.getOutgoingEdges()) {
                if (!hierarchy && edge.getTarget().getParent() != parentNode) {
                    continue;
                }
                KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                List<KPoint> bendPoints = edgeLayout.getBendPoints();
                
                // Add bend points to the set
                for (KPoint bendPoint : bendPoints) {
                    // convert bendpoint to a common, global coordinate system
                    KVector local = bendPoint.createVector();
                    KVector global;
                    if (KimlUtil.isDescendant(edge.getTarget(), edge.getSource())) {
                        global = KimlUtil.toAbsolute(local, edge.getSource());
                    } else {
                        global = KimlUtil.toAbsolute(local, edge.getSource().getParent());
                    }
                    
                    uniqueBendPoints.add(global);
                }
                
                // Update per-edge analyses
                current = bendPoints.size();
                min = Math.min(min, current);
                max = Math.max(max, current);
                nonUniqueSum += current;
                edges++;
            }
            
            if (hierarchy) {
                nodeQueue.addAll(node.getChildren());
            }
        }
        
        // Compute the average number of bend points per edge
        if (edges > 0) {
            avg = (float) nonUniqueSum / (float) edges;
        } else {
            min = 0;
        }

        progressMonitor.done();
        
        final Object[] result = new Object[5]; // SUPPRESS CHECKSTYLE MagicNumber
        result[INDEX_MIN] = min;
        result[INDEX_AVG] = avg;
        result[INDEX_MAX] = max;
        result[INDEX_SUM] = uniqueBendPoints.size();
        result[INDEX_SUM_NON_UNIQUE] = nonUniqueSum;
        
        return result;
    }

}
