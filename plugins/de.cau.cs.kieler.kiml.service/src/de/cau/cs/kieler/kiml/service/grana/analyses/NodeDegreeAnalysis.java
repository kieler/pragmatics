/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
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
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.service.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;

/**
 * A graph analysis that computes the min/avg/max node degree of the given
 * graph.
 * 
 * @author mri
 */
public class NodeDegreeAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode,
            final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Node degree analysis", 1);
        
        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);
        
        int numberOfNodes = 0;
        int overallNodeDegree = 0;
        int minNodeDegree = Integer.MAX_VALUE;
        int maxNodeDegree = 0;
        List<KNode> nodeQueue = new LinkedList<KNode>();
        numberOfNodes += parentNode.getChildren().size();
        nodeQueue.addAll(parentNode.getChildren());
        while (nodeQueue.size() > 0) {
            // pop first element
            KNode node = nodeQueue.remove(0);
            int nodeDegree = 0;
            // node degree outgoing
            for (KEdge edge : node.getOutgoingEdges()) {
                if (edge.getTarget() != node
                        && (hierarchy || edge.getTarget().getParent() == parentNode)) {
                    nodeDegree++;
                }
            }
            // node degree incoming
            for (KEdge edge : node.getIncomingEdges()) {
                if (edge.getSource() != node
                        && (hierarchy || edge.getSource().getParent() == parentNode)) {
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
                    (float) overallNodeDegree / (float) numberOfNodes,
                    maxNodeDegree };
        } else {
            return new Object[] { 0, 0.0f, 0 };
        }
    }
}
