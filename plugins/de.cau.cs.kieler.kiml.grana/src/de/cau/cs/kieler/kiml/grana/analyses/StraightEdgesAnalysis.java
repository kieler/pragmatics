/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

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
public class StraightEdgesAnalysis implements IAnalysis {
    
    /**
     * Identifier of the edge crossings analysis.
     */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.straightEdges";
    
    /** tolerance for float equality. */
    private static final float TOLERANCE = 0.00001f;
    
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
    public Object doAnalysis(final KNode parentNode,
            final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Straight edges analysis", 1);
        
        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);
        
        int[] edgeDirections = {0, 0, 0, 0, 0};
        List<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.addAll(parentNode.getChildren());
        while (!nodeQueue.isEmpty()) {
            KNode node = nodeQueue.remove(0);
            for (KEdge edge : node.getOutgoingEdges()) {
                if (!hierarchy && edge.getTarget().getParent() != parentNode) {
                    continue;
                }
                KEdgeLayout layoutData = edge.getData(KEdgeLayout.class);
                
                // get all per edge bendpoints 
                List<KPoint> bendPoints = layoutData.getBendPoints();
                
                KPoint sourcePoint = layoutData.getSourcePoint();
                KPoint targetPoint = layoutData.getTargetPoint();
                
                // if there are no bendpoints, just compare the position of target and source of an edge
                if (bendPoints.isEmpty()) {
                    if (Math.abs(sourcePoint.getY() - targetPoint.getY()) < TOLERANCE 
                            && sourcePoint.getX() < targetPoint.getX()) {
                        // x-straight and from left to right
                        edgeDirections[INDEX_RIGHT]++;
                    } else if (Math.abs(targetPoint.getY() - sourcePoint.getY()) < TOLERANCE
                            && sourcePoint.getX() > targetPoint.getX()) {
                        // x-straight and from right to left
                        edgeDirections[INDEX_LEFT]++;
                    } else if (Math.abs(targetPoint.getX() - sourcePoint.getX()) < TOLERANCE
                            && sourcePoint.getY() < targetPoint.getY()) {
                        // y-straight and from top to bottom
                        edgeDirections[INDEX_DOWN]++;
                    } else if (Math.abs(sourcePoint.getX() - targetPoint.getX()) < TOLERANCE
                            && sourcePoint.getY() > targetPoint.getY()) {
                        // y-straight and from bottom to top
                        edgeDirections[INDEX_UP]++;
                    }
                } else {
                    // if there are bendpoints we consider edges to be straight if 
                    // the edge is 1) either x-straight or y-straight and 2) all bendpoints
                    // conform to this line
                    
                    // reference integer for comparison
                    boolean onlyConformingBendpoints = true;
                    if (Math.abs(sourcePoint.getY() - targetPoint.getY()) < TOLERANCE
                            && sourcePoint.getX() < targetPoint.getX()) {
                        float tmp = Math.abs(sourcePoint.getX() - targetPoint.getX());
                        
                        // check for bendpoints that are not straight
                        for (KPoint point : bendPoints) {
                            // if only one bendpoint lies not on a straight line between source 
                            // and target, count one up
                            if (Math.abs(tmp - point.getY()) > TOLERANCE) {
                                onlyConformingBendpoints = false;
                                break;
                            }
                        }
                        
                        // count was not incremented, so all bendpoints lie on a straight line
                        if (onlyConformingBendpoints) {
                            edgeDirections[INDEX_RIGHT]++;
                        }
                        break;
                    } 
                    
                    // all other cases are analog to the one above
                    if (Math.abs(targetPoint.getY() - sourcePoint.getY()) < TOLERANCE
                            && targetPoint.getX() < sourcePoint.getX()) {
                        float tmp = Math.abs(targetPoint.getX() - sourcePoint.getX());
                        for (KPoint point : bendPoints) {
                            if (Math.abs(tmp - point.getY()) > TOLERANCE) { 
                                onlyConformingBendpoints = false;
                                break;
                            }
                        }
                        if (onlyConformingBendpoints) {
                            edgeDirections[INDEX_LEFT]++;
                        }
                        break;
                    }             
                    if (Math.abs(sourcePoint.getX() - targetPoint.getX()) < TOLERANCE
                            && sourcePoint.getY() > targetPoint.getY()) {
                        float tmp = Math.abs(sourcePoint.getY() - targetPoint.getY());
                        for (KPoint point : bendPoints) {
                            if (Math.abs(tmp - point.getX()) > TOLERANCE) {
                                onlyConformingBendpoints = false;
                                break;
                            }
                        }
                        if (onlyConformingBendpoints) {
                            edgeDirections[INDEX_UP]++;
                        }
                        break;
                    }                   
                    if (Math.abs(targetPoint.getX() - sourcePoint.getX()) < TOLERANCE
                            && sourcePoint.getY() < targetPoint.getY()) {
                        float tmp = Math.abs(sourcePoint.getY() - targetPoint.getY());
                        for (KPoint point : bendPoints) {
                            if (Math.abs(tmp - point.getX()) > TOLERANCE) {
                                onlyConformingBendpoints = false;
                                break;
                            }
                        }
                        if (onlyConformingBendpoints) {    
                            edgeDirections[INDEX_DOWN]++;    
                        }
                        break;
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