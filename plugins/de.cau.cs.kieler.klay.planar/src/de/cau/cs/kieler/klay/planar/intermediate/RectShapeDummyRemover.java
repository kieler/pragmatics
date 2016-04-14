/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 - 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.intermediate;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.elk.core.util.IElkProgressMonitor;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * Removes dummy nodes and dummy edges which are inserted while the algorithms. At the state of art
 * planar dummy nodes, rectangular shape dummy nodes and bend points dummies are removed. At the
 * beginning of the algorithm planar dummies are inserted, then bend point are added while the
 * orthogonalization step and then the rectangular shape dummies are added. The dummies have to be
 * removed in reversed order, meaning firstly rectangular shape dummies, secondly bend point dummies
 * and finally the planar dummy nodes.
 * 
 * @author pkl
 */
public class RectShapeDummyRemover implements ILayoutProcessor {

    /** The graph which is changed during the processor. */
    private PGraph graph;

    /** The grid that is changed during the processor. */
    private GridRepresentation grid;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pGraph, final IElkProgressMonitor monitor) {
        monitor.begin("Remove rectangular shape dummy nodes", 1);

        this.graph = pGraph;
        this.grid = graph.getProperty(Properties.GRID_REPRESENTATION);

        removeRectShapeDummies();

        // release resources
        graph = null;
        grid = null;
        monitor.done();
    }

    /**
     * Searches rectangle shape dummy nodes. Remove the dummy edges around the dummy nodes and then
     * the dummy nodes themselves.
     */
    private void removeRectShapeDummies() {
        // Remove rectshape dummies
        List<PNode> dummyNodes = Lists.newLinkedList();
        for (PNode pNode : graph.getNodes()) {
            if (pNode.hasProperties() && pNode.getProperty(Properties.RECT_SHAPE_DUMMY) != null) {
                dummyNodes.add(pNode);
            }
        }

        PEdge removableEdge;
        for (PNode dummy : dummyNodes) {
            removableEdge = null;
            for (PEdge edge : dummy.adjacentEdges()) {
                if (edge.getProperty(Properties.RECT_SHAPE_DUMMY) != null) {
                    // Only the rect shape dummy edge has this property.
                    removableEdge = edge;
                    break;
                }
            }

            if (removableEdge != null) {
                graph.removeEdge(removableEdge);
            }
            Iterator<PEdge> edgeIt;
            switch (dummy.getAdjacentEdgeCount()) {
            case 0:
                // nothing to do
                break;
            case 1:
                edgeIt = dummy.adjacentEdges().iterator();
                graph.removeEdge(edgeIt.next());
                break;
            case 2:
                edgeIt = dummy.adjacentEdges().iterator();
                PEdge first = edgeIt.next();
                PEdge second = edgeIt.next();

                // Check for origin is needed, because the original edge should is kept.
                if (first.getProperty(Properties.ORIGIN) != null) {
                    graph.bridgeOverEdge(first, first.getOppositeNode(dummy),
                            second.getOppositeNode(dummy));
                } else {
                    // Use the second edge otherwise, if it is origin or not.
                    graph.bridgeOverEdge(second, second.getOppositeNode(dummy),
                            first.getOppositeNode(dummy));
                }
                break;
            default:
                throw new InconsistentGraphModelException("This should not happen here! :-)");
            }
            grid.remove(dummy);
            graph.removeNode(dummy);
        }

        // Dummy edges can be exist without dummy nodes.
        List<PEdge> dummyEdges = new LinkedList<PEdge>();
        for (PEdge pEdge : graph.getEdges()) {
            if (pEdge.getProperty(Properties.RECT_SHAPE_DUMMY) != null) {
                dummyEdges.add(pEdge);
            }
        }

        for (PEdge dummy : dummyEdges) {
            graph.removeEdge(dummy);
        }

        // update grid.
        Boolean isExtFaceTransformed = this.graph.getProperty(Properties.RECT_SHAPE_TRANS_EXTERNAL);
        if (isExtFaceTransformed != null && isExtFaceTransformed.booleanValue()) {
            // The old grid is to big, the external nodes were dummies and has been removed in this
            // step. Create a new grid with smaller size.
            GridRepresentation newGrid = new GridRepresentation(this.grid.getWidth() - 2,
                    this.grid.getHeight() - 2);
            for (int x = 1; x < grid.getWidth() - 1; x++) {
                for (int y = 1; y < grid.getHeight() - 1; y++) {
                    newGrid.set(x - 1, y - 1, this.grid.get(x, y));
                }
            }

            this.grid = newGrid;
            this.graph.setProperty(Properties.GRID_REPRESENTATION, newGrid);

        }

    }

}
