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
package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;

/**
 * A graph analysis that counts the number of bendpoints. Returns a four-component
 * result {@code (int min, float avg, int max, int sum)}.
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
        
        int min = Integer.MAX_VALUE;
        float avg = 0.0f;
        int max = 0;
        int sum = 0;
        int edges = 0;
        int current;
        
        List<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.add(parentNode);
        while (nodeQueue.size() > 0) {
            // Pop first element
            KNode node = nodeQueue.remove(0);
            
            // Iterate through the node's edges
            for (KEdge edge : node.getOutgoingEdges()) {
                KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                current = edgeLayout.getBendPoints().size();
                
                min = Math.min(min, current);
                max = Math.max(max, current);
                sum += current;
                edges++;
            }
            
            nodeQueue.addAll(node.getChildren());
        }
        
        // Compute the average number of bend points per edge
        if (edges > 0) {
            avg = (float) sum / (float) edges;
        } else {
            min = 0;
        }

        progressMonitor.done();
        return new Object[] {min, avg, max, sum};
    }

}
