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

import java.util.LinkedList;
import java.util.List;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.core.util.Pair;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation.OrthogonalAngle;
import de.cau.cs.kieler.klay.planar.p3compact.TidyRectangleCompactor;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * This processor adds for every bend point of every edge a dummy node to the graph. The The phase
 * {@link TidyRectangleCompactor} needs as input graphs that do not contain any bendpoints. Thus
 * this processor adds dummies for each bend.
 * 
 *  <dl>
 *   <dt>Precondition:</dt><dd>none</dd>
 *   <dt>Postcondition:</dt><dd>Edge bends are replaced with dummy nodes.</dd>
 *   <dt>Slots:</dt><dd>Before the orthogonalization phase.</dd>
 * </dl>
 * 
 * @author pkl
 * @kieler.rating yellow 2012-11-01 review KI-30 by ima, cds
 */
public class BendDummyProcessor implements ILayoutProcessor {

    /** The processed graph. */
    private PGraph graph;

    /** The orthogonal representation of that graph. */
    private OrthogonalRepresentation orthogonal;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pgraph, final IElkProgressMonitor monitor) {
        monitor.begin("Add bend dummies", 1);
        this.graph = pgraph;
        this.orthogonal = pgraph.getProperty(Properties.ORTHO_REPRESENTATION);

        // Add a node for every bend in the orthogonal representation
        // Needed otherwise there can be a ConcurrentModificationException,
        // since we iterate over the edges.
        List<PEdge> edges = new LinkedList<PEdge>();
        edges.addAll(pgraph.getEdges());

        for (PEdge edge : edges) {
            addBendDummies(edge);
        }

        // release resources
        graph = null;
        orthogonal = null;
        monitor.done();
    }

    /**
     * Goes through the bend points of the orthogonal representation and adds for each bend a new
     * node on the edge the bend lies on.
     * 
     * @param edge
     *            the edge containing bends
     */
    private void addBendDummies(final PEdge edge) {
        OrthogonalAngle[] bends = this.orthogonal.getBends(edge);
        if (bends == null) {
            return;
        }

        for (int i = bends.length - 1; i >= 0; i--) {
            Pair<PNode, PEdge> pair = this.graph.addNode(edge);
            pair.getFirst().setProperty(Properties.BENDPOINT, bends[i]);
            PEdge newedge = pair.getSecond();
            OrthogonalAngle b1 = bends[i];
            OrthogonalAngle b2 = (bends[i] == OrthogonalAngle.LEFT) ? OrthogonalAngle.RIGHT
                    : OrthogonalAngle.LEFT;
            List<Pair<PEdge, OrthogonalAngle>> list = Lists.newLinkedList();
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
