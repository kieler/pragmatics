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

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation.OrthogonalAngle;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * 
 * @author pkl
 */
public class FullAngleDummyRemover extends AbstractAlgorithm implements ILayoutProcessor {

    /** The current given graph. */
    private PGraph graph = null;

    /** The orthogonal representation of the graph. */
    private OrthogonalRepresentation ortho;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pGraph) {
        getMonitor().begin("full angle dummies remover", 1);
        this.graph = pGraph;
        this.ortho = this.graph.getProperty(Properties.ORTHO_REPRESENTATION);

        List<PNode> processableNodes = Lists.newArrayList();
        for (PNode node : pGraph.getNodes()) {
            if (node.getProperty(Properties.FULL_ANGLE_DUMMY) != null) {
                processableNodes.add(node);
            }
        }

        for (PNode node : processableNodes) {
            FullAngleDummyEntry entry = node.getProperty(Properties.FULL_ANGLE_DUMMY);

            updateFullAngleNode(node, entry.getDummyEdge());

            updateEdge(entry.getDummyNode(), entry.getDummyEdge(), entry.getEdgeSplitDummy());
        }

        // trigger new face calculation
        this.graph.getFaces();

        getMonitor().done();

    }

    /**
     * 
     * @param node
     * @param dummyEdge
     */
    private void updateFullAngleNode(final PNode fullAngleNode, final PEdge dummyEdge) {
        this.graph.removeEdge(dummyEdge);

        List<Pair<PEdge, OrthogonalAngle>> angles = this.ortho.getAngles(fullAngleNode);
        int originalIndex = 0;
        int dummyIndex = 0;
        if (angles.get(0).getFirst() == dummyEdge) {
            dummyIndex = 0;
            originalIndex = 1;
        } else {
            dummyIndex = 1;
            originalIndex = 0;
        }
        angles.get(originalIndex).setSecond(OrthogonalAngle.FULL);
        angles.remove(dummyIndex);
        this.ortho.removeBendEntry(dummyEdge);
        this.ortho.setBends(angles.get(originalIndex).getFirst(), new OrthogonalAngle[0]);

    }

    /**
     * @param dummyNode
     * @param dummyEdge
     * @param edgeSplitDummy
     */
    private void updateEdge(final PNode dummyNode, final PEdge dummyEdge, final PEdge edgeSplitDummy) {

        // filter the original edge connected to the dummy node.
        PEdge originalEdge = null;
        for (PEdge edge : dummyNode.adjacentEdges()) {
            if (edge != edgeSplitDummy && edge != dummyEdge) {
                originalEdge = edge;
                break;
            }
        }

        if (originalEdge == null) {
            throw new IllegalStateException("FullAngleDummyRemover, updateEdge(...)"
                    + "there exists no original edge for the dummyNode!");
        }

        // set original endpoint to the original edge instead of the dummy node.
        if (originalEdge.getSource() == dummyNode) {
            originalEdge.setSource(edgeSplitDummy.getOppositeNode(dummyNode));
        } else {
            originalEdge.setTarget(edgeSplitDummy.getOppositeNode(dummyNode));
        }

        List<OrthogonalAngle> newBends = Lists.newArrayList();

        // FIXME: additionally check for the correct bend direction rll or llr are different bends.
        OrthogonalAngle[] bends = this.ortho.getBends(edgeSplitDummy);
        for (OrthogonalAngle bend : bends) {
            newBends.add(bend);
        }

        List<Pair<PEdge, OrthogonalAngle>> angles = this.ortho.getAngles(dummyNode);
        for (Pair<PEdge, OrthogonalAngle> angle : angles) {
            if (angle.getFirst() == originalEdge) {
                // if straight, no bend needs to be set
                if (angle.getSecond() == OrthogonalAngle.LEFT) {
                    newBends.add(OrthogonalAngle.RIGHT);
                }
                break;
            }
        }

        bends = this.ortho.getBends(originalEdge);
        // FIXME: additionally check for the correct bend direction rll or llr are different bends.
        for (OrthogonalAngle bend : bends) {
            newBends.add(bend);
        }

        OrthogonalAngle[] newBendsArray = new OrthogonalAngle[newBends.size()];
        for (int i = 0; i < newBendsArray.length; i++) {
            newBendsArray[i] = newBends.get(i);
        }
        this.ortho.setBends(originalEdge, newBendsArray);
        this.ortho.setAngles(dummyNode, null);
        dummyNode.unlinkAll();

        update(edgeSplitDummy.getOppositeNode(dummyNode), edgeSplitDummy, originalEdge);

        this.graph.removeEdge(edgeSplitDummy);
        this.graph.removeNode(dummyNode);
        this.ortho.removeAngleEntry(dummyNode);
        this.ortho.removeBendEntry(edgeSplitDummy);
    }

    /**
     * Updates the embedding and the orthogonal representation of the successor node.
     * 
     * @param succNode
     * @param dummyEdge
     * @param originalEdge
     */
    private void update(final PNode succNode, final PEdge dummyEdge, final PEdge originalEdge) {

        // fix embedding
        boolean wantsMove = false;
        List<PEdge> movableEdges = Lists.newLinkedList();
        for (PEdge edge : succNode.adjacentEdges()) {
            if (edge == dummyEdge) {
                wantsMove = true;
            } else {
                if (wantsMove) {
                    movableEdges.add(edge);
                }
            }
        }

        succNode.unlinkEdge(dummyEdge);
        succNode.linkEdge(originalEdge);

        for (PEdge edge : movableEdges) {
            succNode.moveToEnd(edge);
        }

        // fix angles-data
        for (Pair<PEdge, OrthogonalAngle> pair : this.ortho.getAngles(succNode)) {
            if (pair.getFirst() == dummyEdge) {
                pair.setFirst(originalEdge);
            }
        }

    }
}
