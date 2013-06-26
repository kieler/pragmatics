/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.smart.rules;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;

/**
 * An analysis on the density of the biconnected components.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class BiconnectedComponentDensityAnalysis implements IAnalysis {
    
    /** the identifier of the biconnected component density analysis. */
    public static final String ID = "de.cau.cs.kieler.kiml.smart.biconnectedComponentsDensity";
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Biconnected components density analysis", 1);
        int graphSize = parentNode.getChildren().size();
        if (graphSize == 0) {
            progressMonitor.done();
            return 0.0d;
        }
        
        // gather the biconnected components as a list of hash sets
        nextDfsnum = 1;
        lowpt = new int[graphSize + 1];
        parent = new int[graphSize + 1];
        for (KNode node : parentNode.getChildren()) {
            if (dfsMap.get(node) == null) {
                dfsVisit(node, null);
            }
        }
        
        // count the number of edges in each biconnected component
        double densitySum = 0;
        int nodeSum = 0;
        for (Set<KNode> component : components) {
            int componentSize = component.size();
            double value;
            // SUPPRESS CHECKSTYLE NEXT MagicNumber
            if (componentSize <= 3) {
                value = 1.0;
            } else {
                int edgeCount = 0;
                for (KNode node : component) {
                    for (KEdge edge : node.getOutgoingEdges()) {
                        if (component.contains(edge.getTarget())) {
                            edgeCount++;
                        }
                    }
                }
                // SUPPRESS CHECKSTYLE NEXT 3 MagicNumber
                value = Math.min(2 * Math.abs(2.0 * (edgeCount - componentSize)
                        / (componentSize * componentSize - 3 * componentSize)
                        - 0.5), 1.0);
            }
            densitySum += value * componentSize;
            nodeSum += componentSize;
        }
        
        double result = densitySum / nodeSum;
        
        dfsMap.clear();
        components.clear();
        lowpt = null;
        parent = null;
        progressMonitor.done();
        return result;
    }

    /** the biconnected components found by the algorithm. */
    private List<Set<KNode>> components = new LinkedList<Set<KNode>>();
    /** stack of unfinished nodes to be processed. */
    private LinkedList<KNode> unfinishedStack = new LinkedList<KNode>();
    /** next DFS number to assign. */
    private int nextDfsnum = 0;
    /** map of DFS numbers. */
    private Map<KNode, Integer> dfsMap = new HashMap<KNode, Integer>();
    /** lowest point numbers. */
    private int[] lowpt;
    /** parent node numbers. */
    private int[] parent;
    
    /**
     * Performs a DFS starting at the given node.
     * 
     * @param node node to visit
     * @param parentNode the parent node from which the current node is reached
     */
    private void dfsVisit(final KNode node, final KNode parentNode) {
        int dfsNum = nextDfsnum++;
        dfsMap.put(node, dfsNum);
        lowpt[dfsNum] = dfsNum;
        unfinishedStack.push(node);
        
        // follow the outgoing and incoming edges
        for (KEdge edge : Iterables.concat(node.getOutgoingEdges(), node.getIncomingEdges())) {
            KNode endpoint = edge.getSource() == node ? edge.getTarget() : edge.getSource();
            if (endpoint.getParent() == node.getParent()) {
                Integer endpointNum = dfsMap.get(endpoint);
                if (endpointNum == null) {
                    endpointNum = nextDfsnum;
                    parent[endpointNum] = dfsNum;
                    dfsVisit(endpoint, node);
                    lowpt[dfsNum] = Math.min(lowpt[dfsNum], lowpt[endpointNum]);
                } else {
                    lowpt[dfsNum] = Math.min(lowpt[dfsNum], endpointNum);
                }
            }
        }
        
        if (dfsNum >= 2 && lowpt[dfsNum] == parent[dfsNum]) {
            // a biconnected component was found - gather all its nodes into a hash set
            Set<KNode> component = new HashSet<KNode>();
            KNode v;
            do {
                v = unfinishedStack.pop();
                component.add(v);
            } while (v != node);
            component.add(parentNode);
            components.add(component);
        }
    }
    
}
