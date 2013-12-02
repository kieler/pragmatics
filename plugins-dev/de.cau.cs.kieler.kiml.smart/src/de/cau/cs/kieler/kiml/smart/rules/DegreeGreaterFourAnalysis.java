/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.smart.rules;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * Analysis that counts the nodes with degree greater four.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class DegreeGreaterFourAnalysis implements IAnalysis {
    
    /** the identifier for the tree analysis. */
    public static final String ID = "de.cau.cs.kieler.kiml.smart.degreeGreaterFour";
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Degree greater four analysis", 1);
        
        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);
        
        int result = 0;
        List<KNode> nodeQueue = new LinkedList<KNode>();
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
            
            // SUPPRESS CHECKSTYLE NEXT MagicNumber
            if (nodeDegree > 4) {
                result++;
            }
            
            if (hierarchy) {
                nodeQueue.addAll(node.getChildren());
            }
        }
        
        progressMonitor.done();
        return result;
    }

}
