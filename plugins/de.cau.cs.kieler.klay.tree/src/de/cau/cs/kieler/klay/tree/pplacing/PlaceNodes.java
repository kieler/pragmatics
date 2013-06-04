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
package de.cau.cs.kieler.klay.tree.pplacing;

import java.util.HashMap;
import java.util.Map;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;

/**
 * @author sor
 * @author sgu
 *
 */
public class PlaceNodes {
    
    private final Map<TNode, Double> prelim = new HashMap<TNode, Double>();
    private final Map<TNode, Double> modifier = new HashMap<TNode, Double>();
    private final TGraph tGraph = null;
    private double silbingSeparation = 10.0;
    
    private void setPrelim(TNode tNode, double value) {
        prelim.put(tNode, value);
    }
    
    private double getPrelim(TNode tNode) {
        if (tNode != null)
            return prelim.get(tNode);
        else 
            return 0;
    }
    
    private void setModifier(TNode tNode, double value) {
        modifier.put(tNode, value);
    }
    
    private double getModifier(TNode tNode) {
        if (tNode != null)
            return modifier.get(tNode);
        else 
            return 0;
    }
    
    public void firstWalk(TNode tNode) {
        if (tGraph.isLeaf(tNode)) {
            setPrelim(tNode, 0);
        
            if(tNode.getLeftChild() != null) {
                double calc = prelim.get(tNode.getLeftChild()) + silbingSeparation + 
                        meanNodeSize(tNode.getLeftChild(), tNode).x;
                setPrelim(tNode, calc);
            }
        }
        else {
            TNode prev = null;
            TNode def = tGraph.getNodes().get(0); // is first node in list first child of tNode???
            for (TNode tmp : tGraph.getNodes()) {
                firstWalk(tmp);
                def = apportion(tmp);
                prev = tmp;   
            }
            double midPoint = (getPrelim(tGraph.getFirstChild(tNode)) + 
                    getPrelim(tGraph.getLastChild(tNode)))/2;
                    if (tNode.getLeftChild() != null) {
                        setPrelim(tNode, getPrelim(tNode.getLeftChild()) + 
                                meanNodeSize(tNode, tNode.getLeftChild()).x);
                        setModifier(tNode, getPrelim(tNode) - midPoint);
                    }
                    else {
                        setPrelim(tNode, midPoint);
                    }
        }
        
        
    }

    /**
     * @param tmp
     * @return
     */
    private TNode apportion(TNode tmp) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param leftChild
     * @param tNode
     * @return
     */
    private KVector meanNodeSize(TNode leftNode, TNode rightNode) {
        // TODO Auto-generated method stub
        KVector nodeSize = null;
        double nodeWidth = 0;
        double nodeHeight = 0;
        nodeWidth = (leftNode.getSize().x + rightNode.getSize().x)/2;
        nodeHeight = (leftNode.getSize().y + rightNode.getSize().y)/2;
        nodeSize.x = nodeWidth;
        nodeSize.y = nodeHeight;
        return nodeSize;
    }

}
