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
import java.util.LinkedList;
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
    
    public void firstWalk(TNode tNode, TNode leftNeighbour) {
        // initial Prelim value of all leaves should be 0
        if (tGraph.isLeaf(tNode)) {
            setPrelim(tNode, 0);
            setModifier(tNode, 0);
            
            TNode tmp = leftNeighbour;
            // if a node is a leaf and has a leftNeighbour modify Prelim
            if(tmp != null) {
                double calc = getPrelim(tmp) + silbingSeparation + 
                        meanNodeSize(tmp, tNode).x;
                // the value of Prelim is: Prelim of left neighbour + silbingSeparation + mean node size
                setPrelim(tNode, calc);
            }
        }
        // in case tNode is not a leaf
        else {
            // call this method recursively for every child of tNode
            TNode prev = null;
            for (TNode start : tNode.getChildren()) {
                firstWalk(start, prev);
                prev = start;
            }
            // calculate midPoint
            double midPoint = (getPrelim(tGraph.getFirstChild(tNode)) +
                    getPrelim(tGraph.getLastChild(tNode)))/2;
            // check existence of a leftNeighbour
            TNode tmp = leftNeighbour;
            if (tmp != null) {
                // Prelim is Prelim of leftNeighbour + meanNodeSize + silbingSeparation
                setPrelim(tNode, getPrelim(tmp) + meanNodeSize(tmp, tNode).x + silbingSeparation);
                // Modifier is Prelim - midPoint
                setModifier(tNode, getPrelim(tNode) - midPoint);
            }
            // no leftNeighbour, so modifier is 0 and prelim is just midPoint
            else {
                setPrelim(tNode, midPoint);
            }
        }
    }
    
    // recursive method, that adds modifier of ancestors to nodes
    public void secondWalk(TNode tNode, double modifier, LinkedList<TNode> currentLevel) {
        if (!tGraph.isLeaf(tNode)) {
            for (TNode tmp : tNode.getChildren()) {
                secondWalk(tNode, modifier + getModifier(tNode), tNode.getChildren());
            }
        }
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
