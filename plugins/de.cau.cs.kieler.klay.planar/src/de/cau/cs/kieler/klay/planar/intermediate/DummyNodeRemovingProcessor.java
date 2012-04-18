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
package de.cau.cs.kieler.klay.planar.intermediate;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode.NodeType;

/**
 * Removes dummy nodes which are inserted at the planarisation step.
 * 
 * <dl>
 * <dt>Precondition:</dt>
 * <dd>a layered graph; nodes are placed; edges are routed.</dd>
 * <dt>Postcondition:</dt>
 * <dd>there are no dummy nodes of type {@link NodeType#DUMMY}.</dd>
 * <dt>Slots:</dt>
 * <dd>After phase 4.</dd>
 * </dl>
 * 
 * @author pkl
 */
public class DummyNodeRemovingProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph graph) {
        getMonitor().begin("Remove dummynodes", 1);

        // TODO to implement.

        getMonitor().done();
    }

}
