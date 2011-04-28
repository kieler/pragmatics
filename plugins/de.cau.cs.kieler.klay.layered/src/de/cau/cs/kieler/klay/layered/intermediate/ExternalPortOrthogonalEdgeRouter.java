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

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * TODO: Document.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>A layered graph, with edge routing finished for edges not incident
 *     to external ports; long edge dummies are not yet joined.</dd>
 *   <dt>Postcondition:</dt><dd>All external port dummy nodes left map onto an actual external port;
 *    the coordinates of external port dummy nodes specify the coordinates of their respective
 *    external port; all external port dummy nodes have a size of (0, 0); edges connected to
 *    external ports have their bend points set.</dd>
 *   <dt>Slots:</dt><dd>After phase 5.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>None.</dd>
 * </dl>
 *
 * @author cds
 */
public class ExternalPortOrthogonalEdgeRouter extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Orthogonally routing external port edges", 1);
        
        // TODO: Implement.
        
        /* This processor works in four steps.
         * 
         * Step 1
         * If port constraints are FIXED_ORDER or more, northern and southern external ports may
         * be represented by multiple dummies each. These dummies are converted to long edge
         * dummies, and the original external port dummy nodes are reinserted into the graph.
         * They are just appended to the fifth layer.
         * 
         * Step 2
         * The hyperedges for all external port dummies are calculated. The hyperedges to not yet
         * include the external port dummies themselves. For western and eastern dummy nodes, this
         * will just be a single line. 
         * 
         * Step 3
         * The exact positions of the dummies is calculated, if necessary.
         * 
         * Step 4
         * Bend points for all edges are set.
         */
        
        
        
        
        getMonitor().done();
    }

}
