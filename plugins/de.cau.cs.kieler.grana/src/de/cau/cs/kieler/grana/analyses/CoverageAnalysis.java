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

import java.awt.geom.Rectangle2D;
import java.util.List;

import org.eclipse.elk.core.math.ElkPadding;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisFailed;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * A drawing analysis that computes the percentage of the drawing area that is covered by
 * nodes and insets. The closer this value is to 1.0, the more effectively packed the layout
 * is. This analysis depends on {@link AreaAnalysis} and {@link NodeSizeAnalysis}. Returns a
 * single-component result {@code (float coverage)}.
 * 
 * The result of this analysis is currently only correct if there is no overlapping going
 * on between nodes.
 */
public class CoverageAnalysis implements IAnalysis {

    @Override
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        
        progressMonitor.begin("Node Coverage Analysis", 1);

        // Fetch the results of the area and node size analysis
        Object areaResult = context.getResult(AreaAnalysis.ID);
        Object nodeSizeResult = context.getResult(NodeSizeAnalysis.ANALYSIS_ID);
        if (areaResult == null || nodeSizeResult == null) {
            progressMonitor.done();
            
            return new AnalysisFailed(AnalysisFailed.Type.Dependency);
        }
        
        Object[] areaResultArray = (Object[]) areaResult;
        Object[] nodeSizeResultArray = (Object[]) nodeSizeResult;
        
        // Extract the values we need
        double area = ((Float) areaResultArray[AreaAnalysis.INDEX_WIDTH])
            * ((Float) areaResultArray[AreaAnalysis.INDEX_HEIGHT]);
        int nodes = (Integer) nodeSizeResultArray[NodeSizeAnalysis.INDEX_NODES];
        double avgNodeSize = (Float) nodeSizeResultArray[NodeSizeAnalysis.INDEX_AVG];
        
        // The only thing that this analysis don't capture are the area taken up by
        // compound nodes (the actual node graphics and the insets defining the area
        // where child nodes cannot be placed)
        double compoundArea = computeCompoundArea(parentNode);
        
        // Compute the node coverage
        double nodeCoverage = Math.min((avgNodeSize * nodes + compoundArea) / area, 1);
        
        progressMonitor.done();
        
        return nodeCoverage;
    }

    /**
     * Computes the space taken up by compound nodes that cannot be used for child nodes.
     * 
     * @param parentNode the root of the graph.
     * @return the area taken up by insets.
     */
    private double computeCompoundArea(final ElkNode parentNode) {
        double compoundArea = 0.0f;
        List<ElkNode> children = parentNode.getChildren();
        
        if (!children.isEmpty()) {
            // Compound node; get layout data and insets and compute the node's bounding box
            ElkPadding insets = parentNode.getProperty(CoreOptions.PADDING);
            Rectangle2D.Double nodeRect = NodeSizeAnalysis.computeNodeRect(parentNode, true, true, true);
            
            // Now, each side of the node has an inset which must be added to the
            // insets area; afterwards, four rectangles were counted twice and
            // have to be subtracted once
            double insetsArea = 0.0f;
            insetsArea += parentNode.getWidth() * (insets.getTop() + insets.getBottom());
            insetsArea += parentNode.getHeight() * (insets.getLeft() + insets.getRight());
            insetsArea -= insets.getTop() * (insets.getLeft() + insets.getRight())
                        + insets.getBottom() * (insets.getLeft() + insets.getRight());
            
            // The area covered by this node is its bounding box minus the inner area,
            // which is this node's height and width (without ports and labels) minus
            // the insets area
            compoundArea = nodeRect.width * nodeRect.height
                - (parentNode.getWidth() * parentNode.getHeight() - insetsArea);
            
            // Iterate through the children
            for (ElkNode child : children) {
                compoundArea += computeCompoundArea(child);
            }
        }
        
        return compoundArea;
    }
}
