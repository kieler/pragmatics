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
 * Analysis for the number of edge labels.
 *
 * @author msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class EdgeLabelCountAnalysis implements IAnalysis {
    
    /** identifier of the edge label count analysis. */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.edgeLabelCount";

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Edge label count analysis", 1);
        
        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);
        
        int labelCount = 0;
        List<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.addAll(parentNode.getChildren());
        while (nodeQueue.size() > 0) {
            // pop first element
            KNode node = nodeQueue.remove(0);
            
            for (KEdge edge : node.getOutgoingEdges()) {
                if (hierarchy || edge.getTarget().getParent() == parentNode) {
                    labelCount += edge.getLabels().size();
                }
            }
            
            if (hierarchy) {
                nodeQueue.addAll(node.getChildren());
            }
        }

        progressMonitor.done();
        return labelCount;
    }

}
