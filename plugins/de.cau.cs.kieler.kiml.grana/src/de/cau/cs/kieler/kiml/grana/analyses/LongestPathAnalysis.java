/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KNode;

import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.grana.IAnalysis;

/**
 * A drawing analysis that determines the number of edges on the longest path. If the graph contains
 * cycles, the analysis may fail or give unpredictable results.
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2013-06-04 msp
 */
public class LongestPathAnalysis implements IAnalysis {

    /**
     * Identifier of the longest path analysis.
     */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.longestPath";
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Longest path analysis", 1);
        
        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);
        Map<KNode, Integer> incomingCount = new HashMap<KNode, Integer>();
        Map<KNode, Integer> topoNumber = new HashMap<KNode, Integer>();
        LinkedList<KNode> sources = new LinkedList<KNode>();
        
        LinkedList<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.addAll(parentNode.getChildren());
        int nodeCount = 0;
        while (nodeQueue.size() > 0) {
            // pop first element
            KNode node = nodeQueue.removeFirst();
            nodeCount++;
            
            // count the number of incoming edges without self-loops
            int ic = 0;
            for (KEdge edge : node.getIncomingEdges()) {
                if (edge.getSource() != node) {
                    ic++;
                }
            }
            incomingCount.put(node, ic);
            if (ic == 0) {
                sources.add(node);
            }
            
            if (hierarchy) {
                nodeQueue.addAll(node.getChildren());
            }
        }
        
        // find the longest path through topological numbering
        int maxNumber = 0;
        while (topoNumber.size() < nodeCount) {
            while (sources.size() > 0) {
                KNode node = sources.removeFirst();
                // assign a number
                int maxSourceNumber = -1;
                for (KEdge edge : node.getIncomingEdges()) {
                    if (topoNumber.get(edge.getSource()) != null) {
                        maxSourceNumber = Math.max(maxSourceNumber, topoNumber.get(edge.getSource()));
                    }
                }
                topoNumber.put(node, maxSourceNumber + 1);
                maxNumber = Math.max(maxNumber, maxSourceNumber + 1);
                // remove the node and all incident edges
                for (KEdge edge : node.getOutgoingEdges()) {
                    Integer targetIncoming = incomingCount.get(edge.getTarget());
                    if (edge.getTarget() != node && targetIncoming != null) {
                        incomingCount.put(edge.getTarget(), targetIncoming - 1);
                        if (targetIncoming - 1 == 0) {
                            sources.add(edge.getTarget());
                        }
                    }
                }
            }
            
            if (topoNumber.size() < nodeCount) {
                // there are cycles in the graph, break them by finding a node with least incoming
                KNode chosenNode = null;
                int leastic = Integer.MAX_VALUE;
                for (Map.Entry<KNode, Integer> entry : incomingCount.entrySet()) {
                    if (topoNumber.get(entry.getKey()) == null) {
                        if (entry.getValue() < leastic) {
                            chosenNode = entry.getKey();
                            leastic = entry.getValue();
                        }
                    }
                }
                incomingCount.put(chosenNode, 0);
                sources.add(chosenNode);
            }
        }

        progressMonitor.done();
        return maxNumber;
    }
    
}
