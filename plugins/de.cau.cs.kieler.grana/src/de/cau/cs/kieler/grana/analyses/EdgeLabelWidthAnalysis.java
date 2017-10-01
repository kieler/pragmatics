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
package de.cau.cs.kieler.grana.analyses;

import java.util.LinkedList;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkLabel;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisOptions;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * Analysis for the width of edge labels.
 * 
 * @author ybl
 */
public class EdgeLabelWidthAnalysis implements IAnalysis {

    /**
     * Identifier of the label analysis.
     */
    public static final String ID = "de.cau.cs.kieler.grana.label.edge.width";

    /**
     * {@inheritDoc}
     */
    @Override
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
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
    private Object[] computeLabelSizes(final ElkNode parentNode, final AnalysisContext context) {
        LinkedList<ElkNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(parentNode);

        boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);

        int labelCount = 0;
        double minWidth = Double.MAX_VALUE;
        double maxWidth = Double.MIN_VALUE;
        double sumWidth = 0;
        
        while (nodeQueue.size() > 0) {
            // pop first element
            ElkNode node = nodeQueue.pop();

            // Retrieve width of all contained edges
            for (ElkEdge edge : node.getContainedEdges()) {
                for (ElkLabel label : edge.getLabels()) {
                    labelCount++;
                    
                    minWidth = Math.min(minWidth, label.getWidth());
                    maxWidth = Math.max(maxWidth, label.getWidth());
                    sumWidth += label.getWidth();
                }
            }
            
            if (hierarchy) {
                nodeQueue.addAll(node.getChildren());
            }
        }
        
        if (labelCount != 0) {
            return new Object[] { minWidth, maxWidth, sumWidth / labelCount };
        } else {
            return null;
        }
    }
}
