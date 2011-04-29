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
import de.cau.cs.kieler.klay.layered.p5edges.OrthogonalRoutingGenerator;

/**
 * TODO: Document.
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
 * @see OrthogonalRoutingGenerator
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
        OrthogonalRoutingGenerator routingGenerator;
        
        getMonitor().done();
    }
    
}
