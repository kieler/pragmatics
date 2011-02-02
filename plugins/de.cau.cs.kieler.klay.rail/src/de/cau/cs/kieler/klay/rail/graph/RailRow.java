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

import com.google.common.collect.HashBiMap;

import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * @author jjc
 * 
 */
public class RailRow {

    private HashBiMap<LNode, Integer> nodesWithPosition;

    public RailRow() {
        nodesWithPosition = HashBiMap.create();
    }

    public int getPosition(LNode node) {
        return nodesWithPosition.get(node);
    }

    public int getMinimalPosition() {
        int result = Integer.MAX_VALUE;
        for (Integer i : nodesWithPosition.values()) {
            if (i < result) {
                result = i;
            }
        }
        return result;
    }

    public int getMaximalPosition() {
        int result = Integer.MIN_VALUE;
        for (Integer i : nodesWithPosition.values()) {
            if (i > result) {
                result = i;
            }
        }
        return result;
    }

    public boolean isPositionOccupied(int position) {
        return nodesWithPosition.containsValue(position);
    }

    public List<Integer> getOccupiedPositions() {
        return new LinkedList<Integer>(nodesWithPosition.values());
    }

    public void addNodeAtPosition(LNode node, int position) {
        if (nodesWithPosition.containsValue(position)) {
            throw new IllegalArgumentException("Position is already occupied.");
        }
        if (nodesWithPosition.containsKey(node)) {
            throw new IllegalArgumentException("Node is already in grid.");
        }
        nodesWithPosition.put(node, position);
    }

    public List<LNode> getNodesOrderedByPosition() {
        Set<Integer> posSet = nodesWithPosition.values();
        List<Integer> posList = new LinkedList<Integer>(posSet);
        Collections.sort(posList);
        List<LNode> result = new ArrayList<LNode>(nodesWithPosition.size());
        for (int i : posList) {
            result.add(nodesWithPosition.inverse().get(i));
        }
        return result;
    }
}
