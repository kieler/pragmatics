/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.intermediate;

import java.util.Set;

import org.eclipse.elk.core.util.IElkProgressMonitor;

import com.google.common.collect.Sets;

import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * This processor adds for every self loop edges two dummy nodes that are connected with the self
 * loop edge. This is needed since the phases of the tsm cannot handle plain self loops. Two nodes
 * are added instead of one to avoid multi edge behavior.
 * 
 * @author pkl
 * @kieler.rating proposed yellow by pkl
 */
public class SelfLoopDummyProcessor implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pgraph, final IElkProgressMonitor monitor) {
        monitor.begin("Add self loop dummies", 1);
        
        // filter selfloop edges.
        Set<PEdge> selfLoops = Sets.newHashSet();
        for (PEdge edge : pgraph.getEdges()) {
            if (edge.getSource() == edge.getTarget()) {
                selfLoops.add(edge);
            }
        }

        // insert two dummy nodes to avoid selfloops.
        for (PEdge selfLoop : selfLoops) {
            PNode node = selfLoop.getSource();

            // create dummy nodes
            PNode dummyNode1 = pgraph.addNode();
            PNode dummyNode2 = pgraph.addNode();

            // create edge cycle
            selfLoop.setTarget(dummyNode1);
            dummyNode1.getEdges().add(selfLoop);
            PEdge dummyEdge1 = pgraph.addEdge(dummyNode1, dummyNode2);
            PEdge dummyEdge2 = pgraph.addEdge(dummyNode2, node);

            node.setProperty(Properties.SELFLOOP_DUMMIES, new SelfLoopDummyContainer(dummyNode1,
                    dummyNode2, dummyEdge1, dummyEdge2));
        }

        monitor.done();
    }

}
