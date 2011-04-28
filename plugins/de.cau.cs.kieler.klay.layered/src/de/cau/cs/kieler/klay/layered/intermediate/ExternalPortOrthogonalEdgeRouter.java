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
 * <p><i>Note:</i> Some of this code may seem similar to the code seen in
 * {@link de.cau.cs.kieler.klay.layered.p5edges.OrthogonalEdgeRouter}. That's because it
 * is indeed similar. The concept of hyper nodes is used there as well, for instance. If
 * that is the case, why not integrate external port handling into the edge router? Well,
 * for one, the edge router is complex enough as it is. Furthermore, by factoring external
 * port edge routing out of the edge router, edge routers are freed from having to implement
 * this functionality and can simply reuse one of the appropriate processors to do the work
 * for them.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>A layered graph, with edge routing finished for edges not incident
 *     to external ports; long edge dummies are not yet joined.</dd>
 *   <dt>Postcondition:</dt><dd>All external port dummy nodes left map onto an actual external port;
 *     the coordinates of external port dummy nodes specify the coordinates of their respective
 *     external port; all external port dummy nodes have a size of (0, 0); edges connected to
 *     external ports have their bend points set.</dd>
 *   <dt>Slots:</dt><dd>After phase 5.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>None.</dd>
 * </dl>
 * 
 * @see ExternalPortConstraintProcessor
 * @see ExternalPortDummySizeProcessor
 * @author cds
 */
public class ExternalPortOrthogonalEdgeRouter extends AbstractAlgorithm implements ILayoutProcessor {
    
    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Orthogonally routing external port edges", 1);
        
        /* This processor splits its work into multiple steps:
         * 
         * Step 1
         * Restore any north / south port dummies removed by the ExternalPortConstraintProcessor
         * and connect them to the dummies created in their stead. Assign them an x coordinate
         * (which may be temporary if the port constraints are < FIXED_RATIO). Remove the dummies
         * created in their stead and reroute their incident edges.
         * 
         * Step 2
         * Calculate hyper edges for northern and southern external ports. Connect the external
         * port dummies last, assigning them an x coordinate beforehand if necessary.
         * 
         * Step 3
         * Set bend points.
         */
        
        // TODO: Implement.
        
        getMonitor().done();
    }
    
}
