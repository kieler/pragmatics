/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.PortSide;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;

import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.grana.IAnalysis;

/**
 * A graph analysis that computes the number of ports on each of the four sides.
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class PortSideAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Port side analysis", 1);
        
        boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);
        
        int north = 0, east = 0, south = 0, west = 0;
        List<ElkNode> nodeQueue = new LinkedList<>();
        nodeQueue.addAll(parentNode.getChildren());
        while (nodeQueue.size() > 0) {
            ElkNode node = nodeQueue.remove(0);
            Direction direction = Direction.UNDEFINED;
            if (node.getParent() != null) {
                direction = node.getParent().getProperty(CoreOptions.DIRECTION);
            }
            
            for (ElkPort port : node.getPorts()) {
                PortSide portSide = port.getProperty(CoreOptions.PORT_SIDE);
                if (portSide == PortSide.UNDEFINED) {
                    portSide = ElkUtil.calcPortSide(port, direction);
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

            if (hierarchy) {
                nodeQueue.addAll(node.getChildren());
            }
        }
        
        progressMonitor.done();
        return new Object[] { north, east, south, west };
    }
    
}
