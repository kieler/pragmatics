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
package de.cau.cs.kieler.grana.analyses;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.elk.core.UnsupportedGraphException;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkBendPoint;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisOptions;
import de.cau.cs.kieler.grana.IAnalysis;

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
    public static final String ID = "de.cau.cs.kieler.grana.bendpointCount";

    // SUPPRESS CHECKSTYLE NEXT 5 Javadoc
    public static final int INDEX_MIN = 0;
    public static final int INDEX_AVG = 1;
    public static final int INDEX_MAX = 2;
    public static final int INDEX_SUM = 3;
    public static final int INDEX_SUM_NON_UNIQUE = 4;
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Number of Bends analysis", 1);
        
        // The set of unique bend points
        Set<KVector> uniqueBendPoints = new HashSet<KVector>();
        boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);
        
        // Per-edge bend point analyses
        int min = Integer.MAX_VALUE;
        double avg = 0.0;
        int max = 0;
        int nonUniqueSum = 0;
        int edges = 0;
        int current;
        
        // Iterate through all nodes
        List<ElkNode> nodeQueue = new LinkedList<ElkNode>();
        nodeQueue.add(parentNode);
        while (nodeQueue.size() > 0) {
            // Pop first element
            ElkNode node = nodeQueue.remove(0);
            
            // Iterate through the node's edges
            for (ElkEdge edge : node.getContainedEdges()) {
                if (!hierarchy && edge.isHierarchical()) {
                    continue;
                }
                
                if (edge.isHyperedge()) {
                    throw new UnsupportedGraphException("Hyperedges are not supported by the "
                            + this.getClass().getSimpleName());
                }
                
                // We only support edges with a single edge section
                if (edge.getSections().size() != 1) {
                    continue;
                }
                
                ElkEdgeSection edgeSection = edge.getSections().get(0);
                
                // get all per edge bendpoints 
                List<ElkBendPoint> bendPoints = edgeSection.getBendPoints();
                
                // Add bend points to the set
                for (ElkBendPoint bendPoint : bendPoints) {
                    // convert bendpoint to a common, global coordinate system
                    KVector kpoint = new KVector(bendPoint.getX(), bendPoint.getY());
                    ElkUtil.toAbsolute(kpoint, node);
                    uniqueBendPoints.add(kpoint);
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
            avg = nonUniqueSum / edges;
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
