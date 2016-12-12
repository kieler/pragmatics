/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
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

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.grana.IAnalysis;

/**
 * A graph analysis that computes the min/avg/max node degree of the given
 * graph.
 * 
 * @author mri
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class NodeDegreeAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Node degree analysis", 1);
        
        boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);
        
        int numberOfNodes = 0;
        int overallNodeDegree = 0;
        int minNodeDegree = Integer.MAX_VALUE;
        int maxNodeDegree = 0;
        List<ElkNode> nodeQueue = new LinkedList<ElkNode>();
        numberOfNodes += parentNode.getChildren().size();
        nodeQueue.addAll(parentNode.getChildren());
        while (nodeQueue.size() > 0) {
            // pop first element
            ElkNode node = nodeQueue.remove(0);
            int nodeDegree = 0;
            
            // node degree outgoing
            for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
                if (!edge.isSelfloop() && (hierarchy || !edge.isHierarchical())) {
                    nodeDegree++;
                }
            }
            
            // node degree incoming
            for (ElkEdge edge : ElkGraphUtil.allIncomingEdges(node)) {
                if (!edge.isSelfloop() && (hierarchy || !edge.isHierarchical())) {
                    nodeDegree++;
                }
            }
            
            // min node degree
            if (nodeDegree < minNodeDegree) {
                minNodeDegree = nodeDegree;
            }
            
            // max node degree
            if (nodeDegree > maxNodeDegree) {
                maxNodeDegree = nodeDegree;
            }
            overallNodeDegree += nodeDegree;
            
            if (hierarchy) {
                numberOfNodes += node.getChildren().size();
                nodeQueue.addAll(node.getChildren());
            }
        }

        progressMonitor.done();

        if (numberOfNodes > 0) {
            return new Object[] { minNodeDegree,
                    overallNodeDegree / numberOfNodes,
                    maxNodeDegree };
        } else {
            return new Object[] { 0, 0.0, 0 };
        }
    }
}
