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
 * Sets the node margins. Node margins are influenced by both port positions and sizes
 * and label positions and sizes. Furthermore, comment boxes that are put directly
 * above or below a node also increase the margin.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph; nodes have fixed port positions; labels
 *     have fixed positions.</dd>
 *   <dt>Postcondition:</dt><dd>the node margins are properly set to form a bounding box
 *     around the node and its ports and labels.</dd>
 *   <dt>Slots:</dt><dd>Before phase 4.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>{@link PortPositionProcessor}</dd>
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

        
        
        getMonitor().done();
    }
    
}
