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
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * Tamassia's orthogonalization does not work correctly for full anlges at the internal faces. Thus,
 * all full angle nodes are replaced with dummy cages that do not form a full angle.
 * 
 * 
 * @author pkl
 */
public class FullAngleDummyProcessor implements ILayoutProcessor {

    /** The current given graph. */
    private PGraph graph = null;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pGraph, final IKielerProgressMonitor monitor) {
        monitor.begin("full angle dummies", 1);
        this.graph = pGraph;

        // for testing only.
        // processFullAngles(pGraph);

        // processCutEdges(pGraph);

        monitor.done();
    }

    /**
     * 
     * @param pGraph
     *            the graph for which the full anlge node dummies should be computed.
     */
    @SuppressWarnings("unused")
    private void processFullAngles(final PGraph pGraph) {
        List<PNode> fullAngleNodes = Lists.newLinkedList();
        Iterable<PFace> faces = this.graph.getFaces();
        for (PFace face : faces) {

            // the external face needs not to be processed.
            if (pGraph.getExternalFace() == face) {
                continue;
            }

            // create full angle structure
            for (PNode node : face.adjacentNodes()) {
                if (node.getAdjacentEdgeCount() == 1) {
                    fullAngleNodes.add(node);
                }
            }
        }
        // add dummies to avoid full angle nodes at internal faces
        for (PNode node : fullAngleNodes) {

            PNode currentNode = node;
            PEdge cutEdge = node.adjacentEdges().iterator().next();
            PEdge currentEdge = cutEdge;

            // find the next edge with more than two adjacent edges
            while (true) {
                currentNode = currentEdge.getOppositeNode(currentNode);
                if (currentNode.getAdjacentEdgeCount() == 2) {
                    for (PEdge oppEdge : currentNode.adjacentEdges()) {
                        if (oppEdge != currentEdge) {
                            currentEdge = oppEdge;
                            break;
                        }
                    }
                } else {
                    break;
                }
            }

            // the first edge in ccw direction around the currentnode
            // (adjacent to at least 3 edges) behind the currentEdge.
            PEdge targetEdge = null;
            boolean wantsNextEdge = false;
            for (PEdge edge : currentNode.getEdges()) {
                if (wantsNextEdge) {
                    targetEdge = edge;
                    break;
                }
                if (edge == currentEdge) {
                    wantsNextEdge = true;
                }
            }

            if (targetEdge == null) {
                // then it is the first edge
                targetEdge = currentNode.getEdges().iterator().next();
            }

            // adds dummies
            addDummies(node, currentNode, targetEdge);
        }
        // trigger face calulation
        this.graph.getFaces();

        for (PNode node : fullAngleNodes) {
            node.getProperty(Properties.FULL_ANGLE_DUMMY);
        }
    }

    /**
     * Subdivides the targetEdge with a new dummy node and connects this new dummy with the
     * fullAngleNode in order to avoid fullangles. All dummy elements are equipped with a full angle
     * dummy property.
     * 
     * @param fullAngleNode
     *            the node for which a dummy edge needs to be inserted
     * @param triConnectedNode
     *            connected to the targetEdge
     * @param targetEdge
     *            the edge with that should be split with a new dummy node
     */
    private void addDummies(final PNode fullAngleNode, final PNode triConnectedNode,
            final PEdge targetEdge) {
        Pair<PNode, PEdge> addedPair = graph.addNode(targetEdge, triConnectedNode);
        PNode dummyNode = addedPair.getFirst();
        PEdge dummyEdge = addedPair.getSecond();
        PEdge addedDummyEdge = graph.addEdge(fullAngleNode, dummyNode);

        dummyNode.moveToStart(targetEdge);
        dummyNode.moveToStart(addedDummyEdge);
        dummyNode.moveToStart(dummyEdge);

        fullAngleNode.setProperty(Properties.FULL_ANGLE_DUMMY, new FullAngleDummyEntry(dummyNode,
                addedDummyEdge, dummyEdge));
    }
}
