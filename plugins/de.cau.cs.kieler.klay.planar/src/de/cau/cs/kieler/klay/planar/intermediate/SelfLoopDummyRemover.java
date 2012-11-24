/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.intermediate;

import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * Removes the dummy nodes inserted by the {@link SelfLoopDummyProcessor}.
 * 
 * @author pkl
 * @kieler.rating proposed yellow by pkl
 */
public class SelfLoopDummyRemover implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pgraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Remove self-loop dummies", 1);
        
        GridRepresentation grid = pgraph.getProperty(Properties.GRID_REPRESENTATION);

        List<PNode> selfLoopNodes = Lists.newLinkedList();
        for (PNode node : pgraph.getNodes()) {
            SelfLoopDummyContainer container = node.getProperty(Properties.SELFLOOP_DUMMIES);
            if (container != null) {
                selfLoopNodes.add(node);
            }
        }
        for (PNode node : selfLoopNodes) {

            SelfLoopDummyContainer container = node.getProperty(Properties.SELFLOOP_DUMMIES);

            PNode dummyNode1 = container.getDummyNode1();
            PNode dummyNode2 = container.getDummyNode2();

            // filter original edge
            PEdge originalEdge = null;
            for (PEdge edge : node.adjacentEdges()) {
                if (edge.isConnected(dummyNode1) || edge.isConnected(dummyNode2)
                        && edge != container.getDummyEdge1() && edge != container.getDummyEdge2()) {
                    originalEdge = edge;
                    break;
                }
            }

            int[] pos1 = grid.getPosition(dummyNode1);
            int[] pos2 = grid.getPosition(dummyNode2);

            originalEdge.getBendPoints().add(pos1[0], pos1[1]);
            originalEdge.getBendPoints().add(pos2[0], pos2[1]);

            // removal of dummies
            originalEdge.setTarget(node);

            pgraph.removeEdge(container.getDummyEdge1());
            pgraph.removeEdge(container.getDummyEdge2());
            dummyNode1.unlinkAll();
            pgraph.removeNode(dummyNode1);
            pgraph.removeNode(dummyNode2);
            grid.remove(dummyNode1);
            grid.remove(dummyNode2);
        }
        
        monitor.done();
    }

}
