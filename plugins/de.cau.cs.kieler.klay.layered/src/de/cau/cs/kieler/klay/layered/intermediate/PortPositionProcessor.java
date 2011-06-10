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
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
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
 *   <dt>Precondition:</dt><dd>a layered graph; nodes have fixed port orders.</dd>
 *   <dt>Postcondition:</dt><dd>nodes have fixed port positions.</dd>
 *   <dt>Slots:</dt><dd>Before phase 4.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>{@link PortSideProcessor}</dd>
 * </dl>
 * 
 * @see PortSideProcessor
 * @author msp
 */
public class PortPositionProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Edge joining", 1);
        
        // Iterate through all nodes
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                if (!node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isRatioFixed()) {
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
                        float portOffset = port.getProperty(LayoutOptions.OFFSET);
                        switch (port.getSide()) {
                        case NORTH:
                            port.getPosition().x = northX;
                            port.getPosition().y = -port.getSize().y / 2 - portOffset;
                            northX += northDelta;
                            break;
                        case EAST:
                            port.getPosition().x = nodeSize.x + port.getSize().x / 2 + portOffset;
                            port.getPosition().y = eastY;
                            eastY += eastDelta;
                            break;
                        case SOUTH:
                            port.getPosition().x = southX;
                            port.getPosition().y = nodeSize.y + port.getSize().y / 2 + portOffset;
                            southX -= southDelta;
                            break;
                        case WEST:
                            port.getPosition().x = -port.getSize().x / 2 - portOffset;
                            port.getPosition().y = westY;
                            westY -= westDelta;
                            break;
                        }
                    }
                }
            }
        }
        
        getMonitor().done();
    }

}
