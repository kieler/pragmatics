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
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * 
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd></dd>
 *   <dt>Postcondition:</dt><dd></dd>
 *   <dt>Slots:</dt><dd>Before phase 4.</dd>
 *   <dt>Same-slot dependencies:</dt><dd></dd>
 * </dl>
 *
 * @author cds
 */
public class LabelAndNodeSizeProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph) {
        getMonitor().begin("Node and Port Label Placement and Node Sizing", 1);
        double spacing = layeredGraph.getProperty(Properties.OBJ_SPACING);

        /* PHASE 1: PLACE PORT LABELS
         * Port labels are placed and port margins are calculated. We currently only support
         * one label per port.
         */
        
        /* PHASE 2: PLACE PORTS
         * Ports are placed and node insets are calculated accordingly.
         */
        
        /* PHASE 3: RESERVE SPACE FOR NODE LABEL
         * If the node has a label (we currently only support one), the node insets might have
         * to be adjusted to reserve space for it, which is what this phase does.
         */
        
        /* PHASE 4: RESIZE NODE
         * The node is resized, taking all node size constraints into account.
         */
        
        /* PHASE 5: PLACE NODE LABEL
         * With space reserved for the node label (we only support one), the label is placed.
         */
        
        getMonitor().done();
    }
    
}
