/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;


import java.util.Iterator;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;


/**
 * Remove all edges added by the {@link PartitionPreprocessor}.
 *
 * <dl>
 *   <dt>Precondition:</dt>
 *     <dd>a layered graph.</dd>
 *   <dt>Postcondition:</dt>
 *     <dd>layered graph with all partition constraint edges removed.</dd>
 *   <dt>Slots:</dt>
 *     <dd>Before phase 3.</dd>
 *   <dt>Same-slot dependencies:</dt>
 *     <dd>None.</dd>
 * </dl>
 *
 * @author csp
 */
public class PartitionPostprocessor implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(final LGraph lGraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Removing partition constraint edges", 1);
        // Remove all added ports and edges.
        for (Layer layer : lGraph) {
            for (LNode node : layer) {
                final Iterator<LPort> ports = node.getPorts().iterator();
                while (ports.hasNext()) {
                    LPort port = ports.next();
                    if (port.getProperty(InternalProperties.PARTITION_CONSTRAINT)) {
                        // Remove the port explicitly from the iterator to prevent
                        // ConcurrentModificationExceptions.
                        ports.remove();
                        port.setNode(null);
                        if (port.getSide() == PortSide.WEST) {
                            Iterator<LEdge> edges = port.getOutgoingEdges().iterator();
                            while (edges.hasNext()) {
                                LEdge edge = edges.next();
                                // Only partition constraint edges should be connected to this port.
                                assert edge.getProperty(InternalProperties.PARTITION_CONSTRAINT)
                                    : "Non-constraint edge found at constraint port!";
                                // Remove the edge explicitly from the iterator to prevent
                                // ConcurrentModificationExceptions.
                                edges.remove();
                                edge.setSource(null);
                                edge.setTarget(null);
                            }
                        }
                    }
                }
            }
        }
        monitor.done();
    }

}
