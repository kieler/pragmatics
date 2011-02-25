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
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Determines the actual positions of ports for nodes whose port positions are
 * left to be determined. (whose port contsraints are not
 * {@link de.cau.cs.kieler.kiml.options.PortConstraints#FIXED_POS})
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph.</dd>
 *   <dt>Postcondition:</dt><dd>the ports in the layered graph have had their
 *     exact positions set.</dd>
 * </dl>
 *
 * @author msp
 */
public class PortArranger extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Edge joining", 1);
        
        // Iterate through all nodes
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                if (!node.getProperty(Properties.PORT_CONS).isPosFixed()) {
                    // The ports are not fixed to their positions, so arrange them
                    
                    // Count the ports on different sides
                    int northCount = 1, eastCount = 1, southCount = 1, westCount = 1;
                    for (LPort port : node.getPorts()) {
                        switch (port.getSide()) {
                        case NORTH:
                            northCount++;
                            break;
                        case EAST:
                            eastCount++;
                            break;
                        case SOUTH:
                            southCount++;
                            break;
                        default:
                            westCount++;
                        }
                    }
                    
                    // Compute the space to be left between the ports
                    KVector nodeSize = node.getSize();
                    double northDelta = nodeSize.x / northCount;
                    double northX = northDelta;
                    double eastDelta = nodeSize.y / eastCount;
                    double eastY = eastDelta;
                    double southDelta = nodeSize.x / southCount;
                    double southX = nodeSize.x - southDelta;
                    double westDelta = nodeSize.y / westCount;
                    double westY = nodeSize.y - westDelta;
                    
                    // Arrange the ports
                    for (LPort port : node.getPorts()) {
                        switch (port.getSide()) {
                        case NORTH:
                            port.getPos().x = northX;
                            port.getPos().y = 0;
                            northX += northDelta;
                            break;
                        case EAST:
                            port.getPos().x = nodeSize.x;
                            port.getPos().y = eastY;
                            eastY += eastDelta;
                            break;
                        case SOUTH:
                            port.getPos().x = southX;
                            port.getPos().y = nodeSize.y;
                            southX -= southDelta;
                            break;
                        default:
                            port.getPos().x = 0;
                            port.getPos().y = westY;
                            westY -= westDelta;
                        }
                    }
                }
            }
        }
        
        getMonitor().done();
    }

}
