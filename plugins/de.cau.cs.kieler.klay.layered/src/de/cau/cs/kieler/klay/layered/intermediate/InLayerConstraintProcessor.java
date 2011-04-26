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
 * Makes sure that in-layer constraints are respected. This processor is only necessary
 * if a crossing minimizer doesn't support in-layer constraints anyway. Crossing minimizers
 * that do shouldn't include a dependency on this processor. It would need time without
 * actually doing anything worthwhile.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph; crossing minimization is already finished.</dd>
 *   <dt>Postcondition:</dt><dd>nodes may have been reordered to match in-layer constraints.</dd>
 *   <dt>Slots:</dt><dd>Before phase 4.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>None.</dd>
 * </dl>
 * 
 * @author cds
 */
public class InLayerConstraintProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Layer constraint edge reversal", 1);
        
        // TODO: Implement.
        
        getMonitor().done();
    }

}
