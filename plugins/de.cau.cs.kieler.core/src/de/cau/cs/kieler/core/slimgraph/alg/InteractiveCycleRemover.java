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
package de.cau.cs.kieler.core.slimgraph.alg;

import java.util.LinkedList;

import de.cau.cs.kieler.core.slimgraph.KSlimEdge;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;

/**
 * A cycle remover that allows interactive layout by considering the node
 * positions given by the user.
 *
 * @author msp
 */
public class InteractiveCycleRemover extends AbstractCycleRemover {

    /** whether vertical layout is active. */
    private boolean vertical = false;
    
    /**
     * {@inheritDoc}
     */
    public void removeCycles(final KSlimGraph graph) {
        getMonitor().begin("Interactive cycle removal", 1);
        setReversedEdges(new LinkedList<KSlimEdge>());

        // mark edges that point left / up
        if (vertical) {
            for (KSlimEdge edge : graph.getEdges()) {
                if (edge.getSource().getYpos() > edge.getTarget().getYpos()) {
                    getReversedEdges().add(edge);
                } else {
                    edge.setRank(0);
                }
            }
        } else {
            for (KSlimEdge edge : graph.getEdges()) {
                if (edge.getSource().getXpos() > edge.getTarget().getXpos()) {
                    getReversedEdges().add(edge);
                } else {
                    edge.setRank(0);
                }
            }
        }
        
        // reverse all marked edges
        reverseEdges();

        getMonitor().done();
    }
    
    /**
     * Activate vertical layout.
     * 
     * @param thevertical if true, vertical layout is done, else
     *     horizontal layout is done
     */
    public void setVertical(final boolean thevertical) {
        this.vertical = thevertical;
    }

}
