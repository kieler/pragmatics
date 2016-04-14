/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.LinkedList;

import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.KEdge;
import org.eclipse.elk.graph.KLabel;
import org.eclipse.elk.graph.KNode;

import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.IAnalysis;

/**
 * Analysis for the width of edge labels.
 * 
 * @author ybl
 */
public class EdgeLabelWidthAnalysis implements IAnalysis {

    /**
     * Identifier of the label analysis.
     */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.label.edge.width";

    /**
     * {@inheritDoc}
     */
    @Override
    public Object doAnalysis(final KNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        
        progressMonitor.begin("Label analysis", 1);

        Object[] results = computeLabelSizes(parentNode, context);
        
        progressMonitor.done();
        return results;
    }

    /**
     * Compute average, min, and max label size.
     * 
     * @param parentNode
     *            root node of the graph
     * @param context
     *            context of analysis
     * @return results of the computation.
     */
    private Object[] computeLabelSizes(final KNode parentNode, final AnalysisContext context) {
        LinkedList<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.addAll(parentNode.getChildren());

        int labelCount = 0;
        float minWidth = Float.MAX_VALUE;
        float maxWidth = Float.MIN_VALUE;
        float sumWidth = 0;
        
        while (nodeQueue.size() > 0) {
            // pop first element
            KNode node = nodeQueue.pop();

            // Retrieve width of all edge labels of outgoing edges
            for (KEdge edge : node.getOutgoingEdges()) {
                for (KLabel label : edge.getLabels()) {
                    labelCount++;
                    
                    KShapeLayout labelLayout = label.getData(KShapeLayout.class);
                    minWidth = Math.min(minWidth, labelLayout.getWidth());
                    maxWidth = Math.max(maxWidth, labelLayout.getWidth());
                    sumWidth += labelLayout.getWidth();
                }

            }

        }
        
        if (labelCount != 0) {
            return new Object[] { minWidth, maxWidth, sumWidth / labelCount };
        } else {
            return null;
        }
    }
}
