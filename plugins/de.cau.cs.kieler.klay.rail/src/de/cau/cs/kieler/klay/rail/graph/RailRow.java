/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
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
package de.cau.cs.kieler.klay.rail.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * @author jjc
 * 
 */
public class RailRow {

    private HashMap<Integer, LNode> rowNodes = new HashMap<Integer, LNode>();
    private HashMap<LNode, Integer> nodeRows = new HashMap<LNode, Integer>();

    public RailRow() {
    }

    public int getPosition(LNode node) {
        return nodeRows.get(node);
    }
    
    public boolean isPositionOccupied(int position) {
        return rowNodes.containsKey(position);
    }
    
    public void addNodeAtPosition(LNode node, int position) {
        if (rowNodes.containsKey(position)) {
            throw new IllegalArgumentException("Position is already occupied.");
        }
        if (nodeRows.containsKey(node)) {
            throw new IllegalArgumentException("Node is already in grid.");
        }
        rowNodes.put(position, node);
        nodeRows.put(node, position);
    }
    
    public List<LNode> getNodesOrderedByPosition() {
        Set<Integer> keySet = rowNodes.keySet();
        List<Integer> keyList = new LinkedList<Integer>(keySet);
        Collections.sort(keyList);
        List<LNode> result = new ArrayList<LNode>(rowNodes.size());
        for (int i : keyList) {
            result.add(rowNodes.get(i));
        }
        return result;
    }
}

