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

import java.util.LinkedList;
import java.util.List;

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
 * This processor adds for every bend point of every edge a dummy node to the graph.
 * 
 * @author pkl
 */
public class BendDummyProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /** The processed graph. */
    private PGraph graph;

    /** The orthogonal representation of that graph. */
    private OrthogonalRepresentation orthogonal;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pgraph) {
        getMonitor().begin("Add bend dummies", 1);
        this.graph = pgraph;
        this.orthogonal = pgraph.getProperty(Properties.ORTHO_REPRESENTATION);

        // Add a node for every bend in the orthogonal representation
        List<PEdge> edges = new LinkedList<PEdge>();
        edges.addAll(pgraph.getEdges());

        for (PEdge edge : edges) {
            addBendDummies(edge);
        }

        getMonitor().done();
    }

    /**
     * Goes through the bend points of the orthogonal representation and adds for each 
     * bend a new node on the edge the bend lies on.
     * @param edge
     */
    private void addBendDummies(final PEdge edge) {
        OrthogonalAngle[] bends = this.orthogonal.getBends(edge);
        if (bends == null) {
            return;
        }
        List<Pair<PEdge, OrthogonalAngle>> list;
        for (int i = bends.length - 1; i >= 0; i--) {
            Pair<PNode, PEdge> pair = this.graph.addNode(edge);
            pair.getFirst().setProperty(Properties.BENDPOINT, bends[i]);
            PEdge newedge = pair.getSecond();
            OrthogonalAngle b1 = bends[i];
            OrthogonalAngle b2 = (bends[i] == OrthogonalAngle.LEFT) ? OrthogonalAngle.RIGHT
                    : OrthogonalAngle.LEFT;
            list = new LinkedList<Pair<PEdge, OrthogonalAngle>>();
            list.add(new Pair<PEdge, OrthogonalAngle>(edge, b1));
            list.add(new Pair<PEdge, OrthogonalAngle>(newedge, b2));
            this.orthogonal.setAngles(pair.getFirst(), list);
            this.orthogonal.setBends(newedge, new OrthogonalAngle[0]);
            for (Pair<PEdge, OrthogonalAngle> entry : this.orthogonal
                    .getAngles(newedge.getTarget())) {
                if (entry.getFirst() == edge) {
                    entry.setFirst(newedge);
                }
            }
        }
        this.orthogonal.setBends(edge, new OrthogonalAngle[0]);
    }

}
