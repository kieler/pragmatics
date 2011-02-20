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
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.HashBiMap;

import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * A class introducing a vertical order additionally to the horizontal oder given by layers. Uses
 * the zero position for the straight center and allows positive positions for node below and
 * negative positions for nodes above.
 * 
 * @author jjc
 * 
 */
public class RailRow {

    /** A bidirectional order of vertical positions and nodes. */
    private HashBiMap<LNode, Integer> nodesWithPosition;

    /**
     * Default constructor.
     */
    public RailRow() {
        nodesWithPosition = HashBiMap.create();
    }

    /**
     * Gives the position associated with a node.
     * 
     * @param node The node from which will be searched.
     * @return The position of the node.
     */
    public int getPosition(final LNode node) {
        return nodesWithPosition.get(node);
    }

    /**
     * Gives the lowest vertical position in this layer.
     * 
     * @return The lowest vertical position.
     */
    public int getMinimalPosition() {
        int result = Integer.MAX_VALUE;
        for (Integer i : nodesWithPosition.values()) {
            if (i < result) {
                result = i;
            }
        }
        return result;
    }
    
    /**
     * Gives the highest vertical position in this layer.
     * 
     * @return The highest vertical position.
     */
    public int getMaximalPosition() {
        int result = Integer.MIN_VALUE;
        for (Integer i : nodesWithPosition.values()) {
            if (i > result) {
                result = i;
            }
        }
        return result;
    }

    /**
     * Allows a check whether a given position is already in use.
     * 
     * @param position The position to check for.
     * @return True if the position is already in use, false else
     */
    public boolean isPositionOccupied(final int position) {
        return nodesWithPosition.containsValue(position);
    }

    /**
     * Gives a list of all occupied positions in this layer.
     * 
     * @return A list of all occupied positions.
     */
    public List<Integer> getOccupiedPositions() {
        return new LinkedList<Integer>(nodesWithPosition.values());
    }

    /**
     * Adds a node at a given position.
     * Will throw an exception if node already has a position or the position was occupied.
     * 
     * @param node The node to place.
     * @param position The position to place at.
     */
    public void addNodeAtPosition(final LNode node, final int position) {
        if (nodesWithPosition.containsValue(position)) {
            throw new IllegalArgumentException("Position is already occupied.");
        }
        if (nodesWithPosition.containsKey(node)) {
            throw new IllegalArgumentException("Node is already in grid.");
        }
        nodesWithPosition.put(node, position);
    }

    /**
     * Gives a list of nodes arranged in this layer ordered ascending by their position.
     *  
     * @return A list of nodes arranged in this layer ordered ascending by their position.
     */
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
