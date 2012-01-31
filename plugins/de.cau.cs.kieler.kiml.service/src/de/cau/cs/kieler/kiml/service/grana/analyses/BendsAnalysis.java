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
package de.cau.cs.kieler.kiml.service.grana.analyses;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;

/**
 * A graph analysis that counts the number of bendpoints. Returns a four-component
 * result {@code (int min, float avg, int max, int sum)}.
 * 
 * <p>The analysis collects all bend points and eliminates duplicates. This makes
 * sense for orthogonally routed hyperedges, but may miss a bend point if two completely
 * unrelated edges share a bend point. However, this case is very unlikely and wouldn't
 * make sense in a proper diagram anyway, so we live with not counting it.</p>
 * 
 * @author mri
 * @author cds
 */
public class BendsAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode,
            final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        
        progressMonitor.begin("Number of Bends analysis", 1);
        
        // The set of unique bend points
        Set<KVector> uniqueBendPoints = new HashSet<KVector>();
        
        // Per-edge bend point analyses
        int min = Integer.MAX_VALUE;
        float avg = 0.0f;
        int max = 0;
        int nonUniqueSum = 0;
        int edges = 0;
        int current;
        
        // Iterate through all nodes
        List<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.add(parentNode);
        while (nodeQueue.size() > 0) {
            // Pop first element
            KNode node = nodeQueue.remove(0);
            
            // Iterate through the node's edges
            for (KEdge edge : node.getOutgoingEdges()) {
                KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                List<KPoint> bendPoints = edgeLayout.getBendPoints();
                
                // Add bend points to the set
                for (KPoint bendPoint : bendPoints) {
                    uniqueBendPoints.add(bendPoint.createVector());
                }
                
                // Update per-edge analyses
                current = bendPoints.size();
                min = Math.min(min, current);
                max = Math.max(max, current);
                nonUniqueSum += current;
                edges++;
            }
            
            nodeQueue.addAll(node.getChildren());
        }
        
        // Compute the average number of bend points per edge
        if (edges > 0) {
            avg = (float) nonUniqueSum / (float) edges;
        } else {
            min = 0;
        }

        progressMonitor.done();
        return new Object[] {min, avg, max, uniqueBendPoints.size()};
    }

}
