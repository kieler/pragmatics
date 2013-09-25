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
package de.cau.cs.kieler.kiml.libavoid;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * @author uru
 */
public class LibavoidServerCommunicator {

    private BiMap<Integer, KNode> nodeIdMap = HashBiMap.create();
    private BiMap<Integer, KEdge> edgeIdMap = HashBiMap.create();

    private int nodeIdCounter = 0;
    private int edgeIdCounter = 0;
    private StringBuffer sb = new StringBuffer();

    public void reset() {
        nodeIdCounter = 0;
        nodeIdMap.clear();
        edgeIdCounter = 0;
        edgeIdMap.clear();
        sb = new StringBuffer();
    }
    
    
    public void layout(KNode root) {
        
        reset();
        
        transformGraph(root);
        
        System.out.println(sb.toString());
    }
    

    private void transformGraph(KNode root) {

        sb.append("GRAPH");
        sb.append("\n");
        
        // nodes
        for (KNode node : root.getChildren()) {
            transformNode(node);
        }

        // edges
        for (KNode node : root.getChildren()) {
            for (KEdge edge : node.getOutgoingEdges()) {
                transformEdge(edge);
            }
        }
        
        sb.append("GRAPHEND");
        sb.append("\n");
    }

    private void transformNode(KNode node) {
        // assign an id
        nodeIdMap.put(nodeIdCounter, node);

        // convert the bounds
        KShapeLayout shape = node.getData(KShapeLayout.class);
        // format: id topleft bottomright
        sb.append("NODE " + nodeIdCounter + " " + shape.getXpos() + " " + shape.getYpos() + " "
                + (shape.getXpos() + shape.getWidth()) + " "
                + (shape.getYpos() + shape.getHeight()));
        sb.append("\n");

        nodeIdCounter++;
    }

    private void transformEdge(KEdge edge) {
        // assign an id
        edgeIdMap.put(edgeIdCounter, edge);
        
        // convert the edge
        int srcId = nodeIdMap.inverse().get(edge.getSource());
        int tgtId = nodeIdMap.inverse().get(edge.getTarget());
        
        // format: edgeId srcId tgtId
        sb.append("EDGE " + edgeIdCounter + " " + srcId + " " 
                 + tgtId);
        sb.append("\n");
        
        edgeIdCounter++;
    }
}
