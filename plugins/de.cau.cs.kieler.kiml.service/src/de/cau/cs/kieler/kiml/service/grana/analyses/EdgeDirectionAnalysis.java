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

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.service.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;


/**
 * A drawing analysis that counts the number of edges going in each of the four directions.
 * (top, left, bottom, right) An edge can go in two directions. Returns a four-component
 * result {@code (int top, int left, int bottom, int right)}.
 * 
 * @author cds
 */
public class EdgeDirectionAnalysis implements IAnalysis {

    /**
     * Identifier of the edge directions analysis.
     */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.edgeDirections";
    
    /**
     * Index of the number of edges going topwards in the result array.
     */
    public static final int INDEX_UP = 0;
    /**
     * Index of the number of edges going leftwards in the result array.
     */
    public static final int INDEX_LEFT = 1;
    /**
     * Index of the number of edges going bottomwards in the result array.
     */
    public static final int INDEX_DOWN = 2;
    /**
     * Index of the number of edges going rightwards in the result array.
     */
    public static final int INDEX_RIGHT = 3;
    
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Edge direction analysis", 1);
        
        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);

        int[] edgeDirections = {0, 0, 0, 0};
        List<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.addAll(parentNode.getChildren());
        while (!nodeQueue.isEmpty()) {
            KNode node = nodeQueue.remove(0);
            
            for (KEdge edge : node.getOutgoingEdges()) {
                if (!hierarchy && edge.getTarget().getParent() != parentNode) {
                    continue;
                }
                
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
                    edgeDirections[INDEX_DOWN]++;
                }
                
                if (sourcePoint.getY() > targetPoint.getY()) {
                    edgeDirections[INDEX_UP]++;
                }
            }
            
            // Recurse to children
            if (hierarchy) {
                nodeQueue.addAll(node.getChildren());
            }
        }
        
        progressMonitor.done();
        
        return new Object[] {
                edgeDirections[INDEX_UP],
                edgeDirections[INDEX_LEFT],
                edgeDirections[INDEX_DOWN],
                edgeDirections[INDEX_RIGHT]
        };
    }
    
}
