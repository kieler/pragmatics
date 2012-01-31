/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kiml.service.grana.analyses;

import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;


/**
 * A drawing analysis that counts the number of edges going in each of the four directions.
 * (top, left, bottom, right) An edge can go in two directions. Returns a four-component
 * result {@code (int top, int left, int bottom, int right)}.
 * 
 * @author cds
 */
public class EdgeDirectionAnalysis implements IAnalysis {
    
    // CONSTANTS
    /**
     * Index of the number of edges going topwards in the result array.
     */
    public static final int INDEX_TOP = 0;
    
    /**
     * Index of the number of edges going leftwards in the result array.
     */
    public static final int INDEX_LEFT = 1;
    
    /**
     * Index of the number of edges going bottomwards in the result array.
     */
    public static final int INDEX_BOTTOM = 2;
    
    /**
     * Index of the number of edges going rightwards in the result array.
     */
    public static final int INDEX_RIGHT = 3;
    
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        
        progressMonitor.begin("Edge Direction Analysis", 1);
        
        int[] edgeDirections = {0, 0, 0, 0};
        computeEdgeDirections(parentNode, edgeDirections);
        
        progressMonitor.done();
        
        return new Object[] {
                edgeDirections[INDEX_TOP],
                edgeDirections[INDEX_LEFT],
                edgeDirections[INDEX_BOTTOM],
                edgeDirections[INDEX_RIGHT]
        };
    }
    
    /**
     * Computes the number of edges going in the four directions.
     * 
     * @param parentNode the root of the graph whose edges to count.
     * @param edgeDirections array to accumulate the values in.
     */
    private void computeEdgeDirections(final KNode parentNode, final int[] edgeDirections) {
        // Iterate through the outgoing edges; this also ensures that each edge only
        // gets analyzed once
        for (KEdge edge : parentNode.getOutgoingEdges()) {
            KEdgeLayout layoutData = edge.getData(KEdgeLayout.class);
            KPoint sourcePoint = layoutData.getSourcePoint();
            KPoint targetPoint = layoutData.getTargetPoint();
            
            if (sourcePoint.getX() < targetPoint.getX()) {
                edgeDirections[INDEX_RIGHT]++;
            }
            
            if (sourcePoint.getX() > targetPoint.getX()) {
                edgeDirections[INDEX_LEFT]++;
            }
            
            if (sourcePoint.getY() < targetPoint.getY()) {
                edgeDirections[INDEX_BOTTOM]++;
            }
            
            if (sourcePoint.getY() > targetPoint.getY()) {
                edgeDirections[INDEX_TOP]++;
            }
        }
        
        // Recurse to children
        for (KNode child : parentNode.getChildren()) {
            computeEdgeDirections(child, edgeDirections);
        }
    }
    
}
