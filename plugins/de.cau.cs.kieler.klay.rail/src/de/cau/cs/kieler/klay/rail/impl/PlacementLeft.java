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

public class PlacementLeft implements IPlacement {

    private HashMap<LNode, Integer> placement = new HashMap<LNode, Integer>();

    private List<Integer> maxNewPos;

    public PlacementLeft(final LNode first, final int layerCount) {
        maxNewPos = new ArrayList<Integer>(layerCount);
        for (int i = 0; i < layerCount; i++) {
            maxNewPos.add(0);
        }
        placement.put(first, 0);
    }

    public void place(LPort targetPort, LPort port, int offset) {
        LNode target = targetPort.getNode();
        int layerNo = target.getLayer().getIndex();
        int newPos = NodePlacerHelper.getPositionForNode(targetPort, port,
                placement.get(port.getNode()), new SimplePatternsImpl());
        newPos += offset;
        placement.put(target, newPos);
        if (maxNewPos.get(layerNo) < newPos) {
            maxNewPos.set(layerNo, newPos);
        }
    }
    
    public List<Integer> getMaxNewPosByLayer() {
        return maxNewPos;
    }

}
