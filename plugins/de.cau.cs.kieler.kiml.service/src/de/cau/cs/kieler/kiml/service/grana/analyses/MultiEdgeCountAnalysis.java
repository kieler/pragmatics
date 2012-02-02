/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kiml.service.grana.analyses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.service.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;


/**
 * A graph analysis that counts the number of multi edges. (edges with the same
 * source and target) Returns a single-component result {@code (int multiEdgeCount)}.
 * 
 * To be precise, the value returned is computed as follows. Starting with zero, for
 * each pair of ports the result is incremented by the number of edges connecting them
 * if that number is bigger than 1.
 * 
 * @author cds
 */
public class MultiEdgeCountAnalysis implements IAnalysis {
    
    /**
     * A comparator for comparing KEdges. 
     * 
     * @author cds
     */
    private static class KEdgeComparator implements Comparator<KEdge> {
        /**
         * {@inheritDoc}
         */
        public int compare(final KEdge o1, final KEdge o2) {
            KPort sourcePort1 = o1.getSourcePort();
            KPort sourcePort2 = o2.getSourcePort();
            KNode targetNode1 = o1.getTarget();
            KNode targetNode2 = o2.getTarget();
            KPort targetPort1 = o1.getTargetPort();
            KPort targetPort2 = o2.getTargetPort();
            
            // Source Ports
            if (sourcePort1 == null && sourcePort2 != null) {
                return -1;
            } else if (sourcePort1 != null && sourcePort2 == null) {
                return 1;
            } else if (sourcePort1 != null && sourcePort2 != null
                    && !sourcePort1.equals(sourcePort2)) {
                
                return sourcePort1.hashCode() < sourcePort2.hashCode() ? -1 : 1;
            }
            
            // Target nodes
            if (!targetNode1.equals(targetNode2)) {
                return targetNode1.hashCode() < targetNode2.hashCode() ? -1 : 1;
            }
            
            // Target Ports
            if (targetPort1 == null && targetPort2 != null) {
                return -1;
            } else if (targetPort1 != null && targetPort2 == null) {
                return 1;
            } else if (targetPort1 != null && targetPort2 != null
                    && !targetPort1.equals(targetPort2)) {
                
                return targetPort1.hashCode() < targetPort2.hashCode() ? -1 : 1;
            }
            
            // The edges are equal for our intents and purposes
            return 0;
        }
    }
    
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Multi Edge Count Analysis", 1);
        
        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);
        
        int multiEdgeCount = 0;
        for (KNode node : parentNode.getChildren()) {
            multiEdgeCount += countMultiEdges(node, hierarchy);
        }
        
        progressMonitor.done();
        return multiEdgeCount;
    }
    
    /**
     * Counts the number of multi edges in the graph rooted at the given node.
     * 
     * @param node root of the graph to analyze.
     * @param hierarchy whether to process hierarchy recursively
     * @return the number of multi edges.
     */
    public int countMultiEdges(final KNode node, final boolean hierarchy) {
        int multiEdges = 0;
        
        // Get the list of outgoing edges. Sort them by port, then by target.
        List<KEdge> edges = new ArrayList<KEdge>();
        edges.addAll(node.getOutgoingEdges());
        Collections.sort(edges, new KEdgeComparator());
        
        // Go through the list of edges, incrementing the counter when a node has
        // the same source and target port as the last one
        KNode lastTargetNode = null;
        KPort lastSourcePort = null, lastTargetPort = null;
        int edgesConnectingTwoPorts = 0;
        
        for (KEdge edge : edges) {
            if (edge.getTarget() == lastTargetNode
                    && edge.getSourcePort() == lastSourcePort
                    && edge.getTargetPort() == lastTargetPort) {
                
                edgesConnectingTwoPorts++;
            } else {
                // This is another pair of ports. Check if the last pair of ports
                // was connected by more than one edge
                if (edgesConnectingTwoPorts > 1) {
                    multiEdges += edgesConnectingTwoPorts;
                }
                
                edgesConnectingTwoPorts = 1;
            }
            
            lastTargetNode = edge.getTarget();
            lastSourcePort = edge.getSourcePort();
            lastTargetPort = edge.getTargetPort();
        }
        
        // Do a last check for the last pair of ports
        if (edgesConnectingTwoPorts > 1) {
            multiEdges += edgesConnectingTwoPorts;
        }
        
        // Recurse
        if (hierarchy) {
            for (KNode child : node.getChildren()) {
                multiEdges += countMultiEdges(child, true);
            }
        }
        
        return multiEdges;
    }

}
