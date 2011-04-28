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
 *   <dt>Precondition:</dt><dd>A layered graph; long edge dummies have not yet been inserted;
 *     layer constraints have not yet been applied.</dd>
 *   <dt>Postcondition:</dt><dd>External port dummies for northern and southern ports are
 *     replaced by multiple dummies if the port constraints are at least {@code FIXED_ORDER}.</dd>
 *   <dt>Slots:</dt><dd>Before phase 5.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>None.</dd>
 * </dl>
 * 
 * @see ExternalPortConstraintProcessor
 * @see ExternalPortOrthogonalEdgeRouter
 * @author cds
 */
public class ExternalPortDummySizeProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("External port dummy size processing", 1);
        
        // TODO: Implement.
        
        getMonitor().done();
    }
    
}
