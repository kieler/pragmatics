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
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * Nodes with a higher degree as 4 are replaced by a expansion cycle of dummy nodes. This processor
 * searches such dummies, replaces them with the original node and adds edge coordinates to the
 * original edges connected with each dummy. These coordinates determine the position of the edge in
 * the grid.
 * 
 * @author pkl
 */
public class GiottoDummyRemover extends AbstractAlgorithm implements ILayoutProcessor {

    /** The processed graph. */
    private PGraph graph;

    /** The orthogonal representation of that graph. */
    private OrthogonalRepresentation orthogonal;

    /** The grid representation of that graph. */
    private GridRepresentation grid;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pgraph) {
        getMonitor().begin("Giotto Dummy Removing", 1);
        this.graph = pgraph;
        this.orthogonal = pgraph.getProperty(Properties.ORTHO_REPRESENTATION);
        this.grid = pgraph.getProperty(Properties.GRID_REPRESENTATION);

        // stores the found higher 4 degree nodes.
        Set<PNode> highDegreeNodes = filterHighDegreeNodes();

        calcMetrics(highDegreeNodes);

//        calcQuod(highDegreeNodes);

        getMonitor().done();
    }

    /**
     * 
     */
    private void calcQuod(Set<PNode> highDegreeNodes) {
        for (PNode hDNode : highDegreeNodes) {
            List<Integer> positions = hDNode.getProperty(Properties.HIGH_DEGREE_POSITIONS);
            int smallX = positions.get(0).intValue();
            int smallY = positions.get(1).intValue();
            int bigX = positions.get(2).intValue();
            int bigY = positions.get(3).intValue();

            // add bend point to the end or to the start of the edge.
            for (PEdge edge : hDNode.adjacentEdges()) {
                // order of the bendpoints.
                boolean wantsFirst = edge.getSource() == hDNode;
                if (wantsFirst) {
                    edge.getBendPoints().addFirst(smallX, bigX);
                } else {
                    edge.getBendPoints().addLast(smallX, bigY);
                }
            }
            
            // set the high degree node to only one grid position.
            int newX = (int) Math.floor((bigX - smallX)/2);
            positions.set(0, newX);
            positions.set(2, newX);

            int newY = (int) Math.floor((bigY - smallY)/2);
            positions.set(1, newY);
            positions.set(3, newY);
        }

    }

    /**
     * @param highDegreeNodes
     */
    private void calcMetrics(Set<PNode> highDegreeNodes) {
        for (PNode hDNode : highDegreeNodes) {

            // reinsert the node again.
            graph.addNode(hDNode);

            List<int[]> hDNodePositions = Lists.newLinkedList();

            // summarize the dummies to the high degree node.
            List<PNode> dummyNodes = hDNode.getProperty(Properties.EXPANSION_CYCLE);
            for (PNode dummyNode : dummyNodes) {

                int[] position = grid.getPosition(dummyNode);
                // save position
                hDNodePositions.add(position);

                // link the edges back to the high degree node.
                for (PEdge edge : dummyNode.adjacentEdges()) {

                    // search for original edges.
                    if (edge.getProperty(Properties.EXPANSION_CYCLE_ORIGIN) == null) {
                        // add old high degree node and set the position of the edge endpoint.
                        if (edge.getSource() == dummyNode) {
                            edge.setSource(hDNode);
                            edge.setProperty(Properties.START_POSITION, new Pair<Integer, Integer>(
                                    Integer.valueOf(position[0]), Integer.valueOf(position[1])));
                        } else {
                            edge.setTarget(hDNode);
                            edge.setProperty(Properties.TARGET_POSITION,
                                    new Pair<Integer, Integer>(Integer.valueOf(position[0]),
                                            Integer.valueOf(position[1])));
                        }
                        hDNode.linkEdge(edge);
                    } else {
                        // dummy edges are removed.
                        graph.removeEdge(edge);
                    }
                }
                dummyNode.unlinkAll();
                graph.removeNode(dummyNode);
                grid.remove(dummyNode);
                grid.set(position[0], position[1], hDNode);
            }

            // calculate position and size of the highDegreeNode.
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
