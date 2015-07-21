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

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * Nodes with a higher degree as 4 are replaced by a expansion cycle of dummy nodes. This processor
 * searches such dummies, replaces them with the original node and adds edge coordinates to the
 * original edges connected with each dummy. These coordinates determine the position of the edge in
 * the grid.
 * 
 * @author pkl
 */
public class GiottoDummyRemover implements ILayoutProcessor {

    /** The processed graph. */
    private PGraph graph;

    /** The grid representation of that graph. */
    private GridRepresentation grid;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pgraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Giotto Dummy Removing", 1);
        
        this.graph = pgraph;
        this.grid = pgraph.getProperty(Properties.GRID_REPRESENTATION);

        // stores the found higher 4 degree nodes.
        Set<PNode> highDegreeNodes = filterHighDegreeNodes();

        calcMetrics(highDegreeNodes);

        // release resources
        graph = null;
        grid = null;
        monitor.done();
    }

    /**
     * @param highDegreeNodes
     */
    private void calcMetrics(final Set<PNode> highDegreeNodes) {
        for (PNode hDNode : highDegreeNodes) {

            // reinsert the node again
            graph.addNode(hDNode);

            List<int[]> hDNodePositions = Lists.newLinkedList();

            // merge the dummies to the high degree node
            List<PNode> dummyNodes = hDNode.getProperty(Properties.EXPANSION_CYCLE);
            for (PNode dummyNode : dummyNodes) {

                int[] position = grid.getPosition(dummyNode);
                // save position
                hDNodePositions.add(position);

                // link the edges back to the high degree node
                for (PEdge edge : dummyNode.adjacentEdges()) {

                    // set bend-point property to original edge.
                    if (edge.getProperty(Properties.EXPANSION_CYCLE_ORIGIN) == null) {
                        // add old high degree node and set the position of the edge endpoint.
                        if (edge.getSource() == dummyNode) {
                            edge.setSource(hDNode);
                            edge.setProperty(Properties.START_POSITION, new Pair<Integer, Integer>(
                                    Integer.valueOf(position[0]), Integer.valueOf(position[1])));
                        }
                        if (edge.getTarget() == dummyNode) {
                            edge.setTarget(hDNode);
                            edge.setProperty(Properties.TARGET_POSITION,
                                    new Pair<Integer, Integer>(Integer.valueOf(position[0]),
                                            Integer.valueOf(position[1])));
                        }
                        hDNode.linkEdge(edge);
                    } else {
                        // dummy edges are removed
                        graph.removeEdge(edge);
                    }
                }
                dummyNode.unlinkAll();
                graph.removeNode(dummyNode);
                grid.remove(dummyNode);

            }

            // calculate position and size of the highDegreeNode
            int smallestX = Integer.MAX_VALUE;
            int smallestY = Integer.MAX_VALUE;
            int biggestX = -1;
            int biggestY = -1;

            // take the smallest x and y to set the coordinates and
            // take the biggest x and y to set the size.
            for (int[] pos : hDNodePositions) {
                int x = pos[0];
                int y = pos[1];

                if (smallestX > x) {
                    smallestX = x;
                }

                if (smallestY > y) {
                    smallestY = y;
                }

                if (biggestX < x) {
                    biggestX = x;
                }
                if (biggestY < y) {
                    biggestY = y;
                }
            }
            List<Integer> positions = Lists.newLinkedList();
            positions.add(Integer.valueOf(smallestX));
            positions.add(Integer.valueOf(smallestY));
            positions.add(Integer.valueOf(biggestX));
            positions.add(Integer.valueOf(biggestY));
            hDNode.setProperty(Properties.HIGH_DEGREE_POSITIONS, positions);
            grid.set(smallestX, smallestY, hDNode);
        }
    }

    /**
     * filters the nodes with a higher degree of 4 from the dummy nodes.
     * 
     * @return a set found of high degree nodes.
     */
    private Set<PNode> filterHighDegreeNodes() {
        Set<PNode> highDegreeNodes = Sets.newHashSet();
        for (PNode node : graph.getNodes()) {
            PNode highDegreeNode = node.getProperty(Properties.EXPANSION_CYCLE_ORIGIN);
            if (highDegreeNode != null) {
                highDegreeNodes.add(highDegreeNode);
            }
        }
        return highDegreeNodes;
    }

}
