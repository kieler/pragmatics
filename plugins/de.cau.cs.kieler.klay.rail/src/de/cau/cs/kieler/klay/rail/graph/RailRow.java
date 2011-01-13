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

import java.util.HashMap;

import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * @author jjc
 * 
 */
public class RailRow {

    HashMap<Integer, LNode> rowNodes = new HashMap<Integer, LNode>();

    public RailRow() {
    }

    public boolean addNodeRelativeTo(final LNode otherNode, final int offset, final LNode node) {
        //rowNodes.
        return true;
    }
}
