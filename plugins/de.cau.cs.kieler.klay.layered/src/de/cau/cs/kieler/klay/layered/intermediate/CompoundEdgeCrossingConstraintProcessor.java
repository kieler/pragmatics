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
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.HashMap;
import java.util.LinkedList;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Sets constraints for the edge crossing minimization phase to prohibit leave-nodes of different
 * compound nodes to get mixed up across one layer.
 * 
 * <dl>
 * <dt>Precondition:</dt>
 * <dd>A layered graph. All nodes have a layer assigned.</dd>
 * <dt>Postcondition:</dt>
 * <dd>Side dummy nodes for each compound node are inserted. Constraints with regards to the order
 * of pairs of nodes are set such, that the leave nodes of two different compound nodes will not be
 * interleaved.</dd>
 * <dt>Slots:</dt>
 * <dd>Before phase 3.</dd>
 * <dt>Same-slot dependencies:</dt>
 * <dd>none.</dd>
 * </dl>
 * 
 * @author ima
 */
public class CompoundEdgeCrossingConstraintProcessor extends AbstractAlgorithm implements
        ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Revert edges to remove cyclic dependencies between compound nodes", 1);
        HashMap<KGraphElement, LGraphElement> elemMap = layeredGraph
                .getProperty(Properties.ELEMENT_MAP);
        for (Layer layer : layeredGraph.getLayers()) {
            HashMap<LNode, LinkedList<LNode>> compoundNodes = new HashMap<LNode, LinkedList<LNode>>();
            for (LNode lnode : layer.getNodes()) {
                KNode parent = lnode.getProperty(Properties.PARENT);
                if (parent != null) {
                    LNode parentRep = (LNode) elemMap.get(parent);
                    if (compoundNodes.containsKey(parentRep)) {
                        compoundNodes.get(parentRep).add(lnode);
                    } else {
                        LinkedList<LNode> newList = new LinkedList<LNode>();
                        newList.add(lnode);
                        compoundNodes.put(parentRep, newList);
                    }
                }
            }
        }

        getMonitor().done();
    }

}
