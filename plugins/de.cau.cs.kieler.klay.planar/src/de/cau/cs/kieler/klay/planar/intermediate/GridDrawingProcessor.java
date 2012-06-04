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

import java.util.ArrayList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.graph.PNode.NodeType;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * 
 * <dl>
 * <dt>Precondition:</dt>
 * <dd>a layered graph; nodes are placed; edges are routed.</dd>
 * <dt>Postcondition:</dt>
 * <dd>there are no dummy nodes of type {@link NodeType#DUMMY}.</dd>
 * <dt>Slots:</dt>
 * <dd>After phase 4.</dd>
 * </dl>
 * 
 * @author pkl
 */
public class GridDrawingProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    private PNode[][] grid;

    private PGraph graph;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pgraph) {
        getMonitor().begin("Draw grid", 1);
        this.graph = pgraph;
        PFace externalFace = this.graph.getExternalFace(false);
        List<PEdge>[] sides = externalFace.getProperty(Properties.FACE_SIDES);

        // set size of the grid.
        // it is enough to count one vertical and horizontal direction.

        int gridHeight = 0;
        for (PEdge edge : sides[0]) {
            gridHeight += edge.getProperty(Properties.RELATIVE_LENGTH);
        }

        int gridWidth = 0;
        for (PEdge edge : sides[1]) {
            gridWidth = edge.getProperty(Properties.RELATIVE_LENGTH);
        }

        grid = new PNode[gridWidth][gridHeight];
        fillGrid();

        getMonitor().done();
    }

    private void fillGrid() {
        PFace externalFace = this.graph.getExternalFace(false);
        List<PEdge>[] sides = externalFace.getProperty(Properties.FACE_SIDES);

        PNode startNode = null;
        PEdge startEdge = null;
        int sideIndex = -1;
        // filter startNode, the node that lies on the left side and on the bottom side,
        // meaning the leftmost and lower most node!
        boolean found;
        out: for (PEdge leftSideEdge : sides[0]) {
            for (PEdge bottomSideEdge : sides[3]) {
                found = leftSideEdge.getSource() == bottomSideEdge.getSource()
                        || leftSideEdge.getSource() == bottomSideEdge.getTarget();
                if (found) {
                    startNode = leftSideEdge.getSource();
                    startEdge = leftSideEdge;
                    break out;
                }
                found = leftSideEdge.getTarget() == bottomSideEdge.getSource()
                        || leftSideEdge.getTarget() == bottomSideEdge.getTarget();
                if (found) {
                    startNode = leftSideEdge.getTarget();
                    startEdge = leftSideEdge;
                    break out;
                }
            }
        }

        grid[0][0] = startNode;

        // start filling the grid
        // beginning with the external face sides.

        List<PNode> visitedNodes = new ArrayList<PNode>();
        visitedNodes.add(startNode);
        List<PNode> completedNodes = new ArrayList<PNode>();

        List<PEdge> completedEdges = new ArrayList<PEdge>();

        // left
        List<PEdge> side = sides[0];
        int startIndex = side.indexOf(startEdge);
        // work all sides
        // do{
        // side.
        //
        // }while(true)

        // for (int i = 0; i < sides.length; i++) {
        // while(true)
        // {
        // C if(side.indexOf(startEdge))
        // }

        // }

        // first implement the external face, then go all neighboredge durch and place it in the
        // grid.

        // now it should be possible to use the sides so as | e1 | e2 | e3 |
    }
}
