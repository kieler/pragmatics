/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.rail.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.rail.IPlacement;

/**
 * Implementation of placement class for simulating lookahead on the right side of the trunk.
 * 
 * @author jjc
 * 
 */
public class PlacementRight implements IPlacement {

    /** The simulated placement. */
    private HashMap<LNode, Integer> placement = new HashMap<LNode, Integer>();
    /** The minimal new position in all simulated layers. */
    private List<Integer> minNewPos;

    /**
     * Initializing constructor.
     * 
     * @param first
     *            The node to start with.
     * @param layerCount
     *            The number of layers.
     */
    public PlacementRight(final LNode first, final int layerCount) {
        minNewPos = new ArrayList<Integer>(layerCount);
        for (int i = 0; i < layerCount; i++) {
            minNewPos.add(0);
        }
        placement.put(first, 0);
    }

    /**
     * {@inheritDoc}
     */
    public void place(final LPort targetPort, final LPort port, final int offset) {
        LNode target = targetPort.getNode();
        int layerNo = target.getLayer().getIndex();
        int newPos = NodePlacerHelper.getPositionForNode(targetPort, port,
                placement.get(port.getNode()), new SimplePatternsImpl());
        newPos += offset;
        placement.put(target, newPos);
        if (minNewPos.get(layerNo) > newPos) {
            minNewPos.set(layerNo, newPos);
        }
    }

    /**
     * Gives the list of simulated minimal positions.
     * 
     * @return A list of simulated minimal positions.
     */
    public List<Integer> getMinNewPosByLayer() {
        return minNewPos;
    }

}
