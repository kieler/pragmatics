/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.grana.analyses;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisOptions;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * A drawing analysis that counts the number of edges going in each of the four directions.
 * (top, left, bottom, right) An edge can go in two directions. Returns a four-component
 * result {@code (int top, int left, int bottom, int right)}.
 * 
 * @author cds
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class EdgeDirectionAnalysis implements IAnalysis {

    /**
     * Identifier of the edge directions analysis.
     */
    public static final String ID = "de.cau.cs.kieler.grana.edgeDirections";
    
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
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Edge direction analysis", 1);
        
        boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);

        int[] edgeDirections = {0, 0, 0, 0};
        List<ElkNode> nodeQueue = new LinkedList<ElkNode>();
        nodeQueue.addAll(parentNode.getChildren());
        while (!nodeQueue.isEmpty()) {
            ElkNode node = nodeQueue.remove(0);
            
            for (ElkEdge edge : node.getOutgoingEdges()) {
                if (!hierarchy && edge.isHierarchical()) {
                    continue;
                }
                
                ElkEdgeSection s = ElkGraphUtil.firstEdgeSection(edge, false, false);
                
                if (s.getStartX() < s.getEndX()) {
                    edgeDirections[INDEX_RIGHT]++;
                }
                
                if (s.getStartX() > s.getEndX()) {
                    edgeDirections[INDEX_LEFT]++;
                }

                if (s.getStartY() < s.getEndY()) {
                    edgeDirections[INDEX_DOWN]++;
                }

                if (s.getStartY() > s.getEndY()) {
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
