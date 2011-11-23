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
package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * A graph analysis that computes the number of ports on each of the four sides.
 * 
 * @author msp
 */
public class PortSideAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode,
            final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Number of ports analysis", 1);
        int north = 0, east = 0, south = 0, west = 0;
        List<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.add(parentNode);
        while (nodeQueue.size() > 0) {
            KNode node = nodeQueue.remove(0);
            Direction direction = Direction.UNDEFINED;
            if (node.getParent() != null) {
                direction = node.getParent().getData(KShapeLayout.class).getProperty(
                        LayoutOptions.DIRECTION);
            }
            
            for (KPort port : node.getPorts()) {
                KShapeLayout portLayout = port.getData(KShapeLayout.class);
                PortSide portSide = portLayout.getProperty(LayoutOptions.PORT_SIDE);
                if (portSide == PortSide.UNDEFINED) {
                    portSide = KimlUtil.calcPortSide(port, direction);
                }
                switch (portSide) {
                case NORTH:
                    north++;
                    break;
                case EAST:
                    east++;
                    break;
                case SOUTH:
                    south++;
                    break;
                case WEST:
                    west++;
                    break;
                }
            }

            nodeQueue.addAll(node.getChildren());
        }
        progressMonitor.done();
        return new Object[] { north, east, south, west };
    }
    
}
