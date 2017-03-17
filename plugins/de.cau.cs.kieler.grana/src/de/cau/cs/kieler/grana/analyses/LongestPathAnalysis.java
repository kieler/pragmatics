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

package de.cau.cs.kieler.grana.analyses;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisOptions;
import de.cau.cs.kieler.grana.IAnalysis;

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
    public static final String ID = "de.cau.cs.kieler.grana.longestPath";
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Longest path analysis", 1);
        
        boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);
        Map<ElkNode, Integer> incomingCount = new HashMap<ElkNode, Integer>();
        Map<ElkNode, Integer> topoNumber = new HashMap<ElkNode, Integer>();
        LinkedList<ElkNode> sources = new LinkedList<ElkNode>();
        
        LinkedList<ElkNode> nodeQueue = new LinkedList<ElkNode>();
        nodeQueue.addAll(parentNode.getChildren());
        int nodeCount = 0;
        while (nodeQueue.size() > 0) {
            // pop first element
            ElkNode node = nodeQueue.removeFirst();
            nodeCount++;
            
            // count the number of incoming edges without self-loops
            int ic = 0;
            for (ElkEdge edge : ElkGraphUtil.allIncomingEdges(node)) {
                if (ElkGraphUtil.getSourceNode(edge) != node) {
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
                ElkNode node = sources.removeFirst();
                // assign a number
                int maxSourceNumber = -1;
                for (ElkEdge edge : ElkGraphUtil.allIncomingEdges(node)) {
                    ElkNode source = ElkGraphUtil.getSourceNode(edge);
                    if (topoNumber.get(source) != null) {
                        maxSourceNumber = Math.max(maxSourceNumber, topoNumber.get(source));
                    }
                }
                topoNumber.put(node, maxSourceNumber + 1);
                maxNumber = Math.max(maxNumber, maxSourceNumber + 1);
                // remove the node and all incident edges
                for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
                    ElkNode target = ElkGraphUtil.getTargetNode(edge);
                    Integer targetIncoming = incomingCount.get(target);
                    if (target != node && targetIncoming != null) {
                        incomingCount.put(target, targetIncoming - 1);
                        if (targetIncoming - 1 == 0) {
                            sources.add(target);
                        }
                    }
                }
            }
            
            if (topoNumber.size() < nodeCount) {
                // there are cycles in the graph, break them by finding a node with least incoming
                ElkNode chosenNode = null;
                int leastic = Integer.MAX_VALUE;
                for (Map.Entry<ElkNode, Integer> entry : incomingCount.entrySet()) {
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
