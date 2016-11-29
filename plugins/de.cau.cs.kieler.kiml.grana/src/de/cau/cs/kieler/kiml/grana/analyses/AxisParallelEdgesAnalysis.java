/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.elk.core.UnsupportedGraphException;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkBendPoint;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.grana.IAnalysis;

/**
 * A graph analysis that computes the number of straight edges in a graph.
 * 
 * The analysis reports 5 values. The first values are axis-aligned straight
 * edges in one of four directions, ie TOP, LEFT, DOWN, RIGHT.
 * The fifth value is the sum of the previous values.
 * 
 * Layout direction has no influence on this analysis. Edges with bendpoints
 * are allowed as long as the bendpoints conform to the straightness of the 
 * edge.
 *  
 * @author sor
 * @author uru
 */
public class AxisParallelEdgesAnalysis implements IAnalysis {
    
    /**
     * Identifier of this analysis.
     */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.axisParallelEdges";
    
    /** tolerance for float equality. */
    private static final double TOLERANCE = 0.00001;
    
    /**
     * Index of the number of straight edges going topwards in the result array.
     */
    public static final int INDEX_UP = 0;
    
    /**
     * Index of the number of straight edges going leftwards in the result array.
     */
    public static final int INDEX_LEFT = 1;
   
    /**
     * Index of the number of straight edges going bottomwards in the result array.
     */
    public static final int INDEX_DOWN = 2;
    
    /**
     * Index of the number of straight edges going rightwards in the result array.
     */
    public static final int INDEX_RIGHT = 3;
    
    /**
     * Index of the sum of straight edges of the whole graph in the result array.
     */
    public static final int INDEX_SUM = 4;

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode,
            final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Axis-parallel edges analysis", 1);
        
        boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);
        
        int[] edgeDirections = {0, 0, 0, 0, 0};
        List<ElkNode> nodeQueue = new LinkedList<ElkNode>();
        nodeQueue.add(parentNode);
        while (!nodeQueue.isEmpty()) {
            ElkNode node = nodeQueue.remove(0);
            
            // Prior to the new ELK graph, this for loop iterated over all outgoing edges of the
            // parent node's children that connected them to their siblings. Now we simply iterate
            // over all edges contained in the parent node and, if hierarchy is enabled, its
            // descendants. This may result in slightly different edge sets if people don't respect
            // how edges should be contained in the graph
            for (ElkEdge edge : node.getContainedEdges()) {
                if (!hierarchy && edge.isHierarchical()) {
                    continue;
                }
                
                if (edge.isHyperedge()) {
                    throw new UnsupportedGraphException("Hyperedges are not supported by the "
                            + this.getClass().getSimpleName());
                }
                
                // We only support edges with a single edge section
                if (edge.getSections().size() != 1) {
                    continue;
                }
                
                ElkEdgeSection edgeSection = edge.getSections().get(0);
                
                // get all per edge bendpoints 
                List<ElkBendPoint> bendPoints = edgeSection.getBendPoints();
                
                // if there are no bendpoints, just compare the position of target and source
                // of an edge
                if (bendPoints.isEmpty()) {
                    if (Math.abs(edgeSection.getStartY() - edgeSection.getEndY()) < TOLERANCE 
                            && edgeSection.getStartX() < edgeSection.getEndX()) {
                        // x-straight and from left to right
                        edgeDirections[INDEX_RIGHT]++;
                    } else if (Math.abs(edgeSection.getEndY() - edgeSection.getStartY()) < TOLERANCE
                            && edgeSection.getStartX() > edgeSection.getEndX()) {
                        // x-straight and from right to left
                        edgeDirections[INDEX_LEFT]++;
                    } else if (Math.abs(edgeSection.getEndX() - edgeSection.getStartX()) < TOLERANCE
                            && edgeSection.getStartY() < edgeSection.getEndY()) {
                        // y-straight and from top to bottom
                        edgeDirections[INDEX_DOWN]++;
                    } else if (Math.abs(edgeSection.getStartX() - edgeSection.getEndX()) < TOLERANCE
                            && edgeSection.getStartY() > edgeSection.getEndY()) {
                        // y-straight and from bottom to top
                        edgeDirections[INDEX_UP]++;
                    }
                } else {
                    // if there are bendpoints we consider edges to be straight if 
                    // the edge is 1) either x-straight or y-straight and 2) all bendpoints
                    // conform to this line
                    
                    // reference integer for comparison
                    boolean onlyConformingBendpoints = true;
                    if (Math.abs(edgeSection.getStartY() - edgeSection.getEndY()) < TOLERANCE
                            && edgeSection.getStartX() < edgeSection.getEndX()) {
                        // left-to-right
                        for (ElkBendPoint point : bendPoints) {
                            // if only one bendpoint lies not on a straight line between source 
                            // and target, count one up
                            if (Math.abs(edgeSection.getStartY() - point.getY()) > TOLERANCE) {
                                onlyConformingBendpoints = false;
                                break;
                            }
                        }
                        
                        // count was not incremented, so all bendpoints lie on a straight line
                        if (onlyConformingBendpoints) {
                            edgeDirections[INDEX_RIGHT]++;
                        }
                    } else if (Math.abs(edgeSection.getEndY() - edgeSection.getStartY()) < TOLERANCE
                            && edgeSection.getEndX() < edgeSection.getStartX()) {
                        // right-to-left
                        for (ElkBendPoint point : bendPoints) {
                            if (Math.abs(edgeSection.getStartY() - point.getY()) > TOLERANCE) { 
                                onlyConformingBendpoints = false;
                                break;
                            }
                        }
                        if (onlyConformingBendpoints) {
                            edgeDirections[INDEX_LEFT]++;
                        }
                    } else if (Math.abs(edgeSection.getStartX() - edgeSection.getEndX()) < TOLERANCE
                            && edgeSection.getStartY() > edgeSection.getEndY()) {
                        // bottom-up
                        for (ElkBendPoint point : bendPoints) {
                            if (Math.abs(edgeSection.getStartX() - point.getX()) > TOLERANCE) {
                                onlyConformingBendpoints = false;
                                break;
                            }
                        }
                        if (onlyConformingBendpoints) {
                            edgeDirections[INDEX_UP]++;
                        }
                    } else if (Math.abs(edgeSection.getEndX() - edgeSection.getStartX()) < TOLERANCE
                            && edgeSection.getStartY() < edgeSection.getEndY()) {
                        // top-down
                        for (ElkBendPoint point : bendPoints) {
                            if (Math.abs(edgeSection.getStartX() - point.getX()) > TOLERANCE) {
                                onlyConformingBendpoints = false;
                                break;
                            }
                        }
                        if (onlyConformingBendpoints) {    
                            edgeDirections[INDEX_DOWN]++;    
                        }
                    }   
                }

            }
            
            // sum up all straight edges
            edgeDirections[INDEX_SUM] =
                    edgeDirections[INDEX_DOWN] + edgeDirections[INDEX_UP]
                            + edgeDirections[INDEX_RIGHT] + edgeDirections[INDEX_LEFT];

            // recurse to children
            if (hierarchy) {
                nodeQueue.addAll(node.getChildren());
            }
        }
        progressMonitor.done();
        
        return new Object[] {
                edgeDirections[INDEX_UP],         
                edgeDirections[INDEX_LEFT],
                edgeDirections[INDEX_DOWN],
                edgeDirections[INDEX_RIGHT],
                edgeDirections[INDEX_SUM]
        };
    }
}