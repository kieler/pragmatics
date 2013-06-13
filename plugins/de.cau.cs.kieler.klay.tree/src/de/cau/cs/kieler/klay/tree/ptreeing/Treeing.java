/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.tree.ptreeing;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.tree.ILayoutPhase;
import de.cau.cs.kieler.klay.tree.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.tree.graph.TEdge;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.intermediate.LayoutProcessorStrategy;

/**
 * @author sor
 * @author sgu
 *
 */
public class Treeing implements ILayoutPhase {
    
    /** intermediate processing configuration. */
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION = 
            
            new IntermediateProcessingConfiguration(
            IntermediateProcessingConfiguration.BEFORE_PHASE_2,
            EnumSet.of(LayoutProcessorStrategy.TEST_PROCESSOR));
          
    
    /**
     * {@inheritDoc}
     */
    public void process(TGraph tGraph, IKielerProgressMonitor progressMonitor) {
        
        // TODO Auto-generated method stub  
    }

    
    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(TGraph tGraph) {
        
        return INTERMEDIATE_PROCESSING_CONFIGURATION;
    }
    
    
    private boolean[] seen;
    
    List<TEdge>[] eliminated;
    
    public void init(TGraph tGraph) {
        int size = tGraph.getNodes().size();
        eliminated = new LinkedList[size];
        seen = new boolean[size];
    }
    
    
    /**
     * Create a list with the edges to remove. These edges should be all multiple edges
     * except one.
     * 
     * @param TGraph where to collect the edges
     * @return the list with all edges to remove
     */
    public List<TEdge> collectMultipleEdges(TGraph tGraph) {
        init(tGraph);
        List<TEdge>[] edges = new LinkedList[tGraph.getEdges().size()];
        List<TEdge> edgesToRemove = new LinkedList<TEdge>();
        for (TNode tNode : tGraph.getNodes()) {
            edges = searchEdges(tNode, null);
            if (edges[tNode.id] != null) {
                int start = 0;
                for (int i = 1; i < edges[tNode.id].size(); i++) {
                    edgesToRemove.set(start, edges[tNode.id].get(i));
                    start++;
                }
            }
        }
        // TODO do some POST-PROCESSING
        return edgesToRemove;
    }
    
    
    /**
     * This method performs a DFS on a given graph. If a node has been visited it must have
     * multiple outgoing edges. These edges are written in a list.
     * 
     * @param node to start DFS
     * @param graph to start DFS
     * @return the list including multiple edges
     */
    public List<TEdge>[] searchEdges(TNode tNode, TGraph tGraph) {
        if (seen[tNode.id] == false) {
            seen[tNode.id] = true;
            
            for (TEdge edge : tGraph.getEdges()) {
                if (edge.getSource() != tNode) {
                    searchEdges(edge.getSource(), tGraph);
                }
                if (edge.getTarget() != tNode) {
                    searchEdges(edge.getTarget(), tGraph);
                }
            }
            return null;
        }
        else {
            eliminated[tNode.id] = tNode.getOutgoingEdges();
            return eliminated;
        }
    }
    
    

    
    
    

}
