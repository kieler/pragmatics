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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.PConstants;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * Nodes with a higher degree as 4 are replaced by a expansion cycle of dummy nodes. This processor
 * searches such dummies, replaces them with the original node and adds edge coordinates to the
 * original edges connected with each dummy. These coordinates determine the position of the edge in
 * the grid. The difference to the Giotto approach is, that this one lets the high-degree node sizes
 * unchanged, and adds bends at the positions the high-degree adjacent edges would cross.
 * 
 * @author pkl
 */
public class QuodDummyRemover implements ILayoutProcessor {

    /** The processed graph. */
    private PGraph graph;

    /** The grid representation of that graph. */
    private GridRepresentation grid;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pgraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Remove Quod dummy nodes", 1);
        
        this.graph = pgraph;
        this.grid = pgraph.getProperty(Properties.GRID_REPRESENTATION);

        // stores the found higher 4 degree nodes.
        Set<PNode> highDegreeNodes = filterHighDegreeNodes();

        calcMetrics(highDegreeNodes);

        calcNodePosition(highDegreeNodes);

        // release resources
        graph = null;
        grid = null;
        monitor.done();
    }

    /**
     * Calculates the positions and sizes of high-degree nodes.
     * 
     * @param highDegreeNodes
     *            set of high-degree nodes.
     */
    private void calcMetrics(final Set<PNode> highDegreeNodes) {
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
                List<PEdge> dummies = new LinkedList<PEdge>();
                dummies.addAll((Collection<PEdge>) dummyNode.adjacentEdges());

                for (PEdge edge : dummies) {

                    // search for original edges.
                    if (edge.getProperty(Properties.EXPANSION_CYCLE_ORIGIN) == null) {
                        // add original high-degree node and set the position of the edge endpoint.
                        if (edge.getSource() == dummyNode) {
                            int[] pos = grid.getPosition(edge.getSource());
                            edge.setSource(hDNode);
                            edge.setProperty(Properties.START_POSITION, new Pair<Integer, Integer>(
                                    Integer.valueOf(pos[0]), Integer.valueOf(pos[1])));
                        }
                        if (edge.getTarget() == dummyNode) {
                            int[] pos = grid.getPosition(edge.getTarget());
                            edge.setTarget(hDNode);
                            edge.setProperty(
                                    Properties.TARGET_POSITION,
                                    new Pair<Integer, Integer>(Integer.valueOf(pos[0]), Integer
                                            .valueOf(pos[1])));
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
     * Calculates the high-degree node position.
     */
    private void calcNodePosition(final Set<PNode> highDegreeNodes) {
        for (PNode hDNode : highDegreeNodes) {
            List<Integer> positions = hDNode.getProperty(Properties.HIGH_DEGREE_POSITIONS);
            float smallX = positions.get(PConstants.X_COR).intValue();
            float smallY = positions.get(PConstants.Y_COR).intValue();
            float bigX = positions.get(PConstants.WIDTH_POS).intValue();
            float bigY = positions.get(PConstants.HEIGHT_POS).intValue();

            // add bend point to the end or to the start of the edge.
            for (PEdge edge : hDNode.adjacentEdges()) {

                // order of the bendpoints.
                Pair<Integer, Integer> startPos = edge.getProperty(Properties.START_POSITION);
                if (startPos != null) {
                    edge.getBendPoints().addFirst(startPos.getFirst(), startPos.getSecond());
                }
                Pair<Integer, Integer> targetPos = edge.getProperty(Properties.TARGET_POSITION);
                if (targetPos != null) {
                    edge.getBendPoints().addLast(targetPos.getFirst(), targetPos.getSecond());
                }
            }

            // set the high degree node to only one grid position.
            int newX = (int) (Math.ceil((bigX - smallX) / 2) + smallX);
            positions.set(PConstants.X_COR, newX);
            positions.set(PConstants.WIDTH_POS, newX);

            int newY = (int) (Math.ceil((bigY - smallY) / 2) + smallY);
            positions.set(PConstants.Y_COR, newY);
            positions.set(PConstants.HEIGHT_POS, newY);
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
