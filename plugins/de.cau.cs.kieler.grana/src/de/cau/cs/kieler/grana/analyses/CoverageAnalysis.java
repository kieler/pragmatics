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

import org.eclipse.elk.core.klayoutdata.KInsets;
import org.eclipse.elk.core.klayoutdata.KShapeLayout;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.KNode;
import org.eclipse.emf.common.util.EList;

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
 * 
 * @author cds
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class CoverageAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final AnalysisContext context,
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
        float area = ((Float) areaResultArray[AreaAnalysis.INDEX_WIDTH])
            * ((Float) areaResultArray[AreaAnalysis.INDEX_HEIGHT]);
        int nodes = (Integer) nodeSizeResultArray[NodeSizeAnalysis.INDEX_NODES];
        float avgNodeSize = (Float) nodeSizeResultArray[NodeSizeAnalysis.INDEX_AVG];
        
        // The only thing that this analysis don't capture are the area taken up by
        // compound nodes (the actual node graphics and the insets defining the area
        // where child nodes cannot be placed)
        float compoundArea = computeCompoundArea(parentNode);
        
        // Compute the node coverage
        float nodeCoverage = Math.min((avgNodeSize * nodes + compoundArea) / area, 1);
        
        progressMonitor.done();
        
        return nodeCoverage;
    }

    /**
     * Computes the space taken up by compound nodes that cannot be used for child nodes.
     * 
     * @param parentNode the root of the graph.
     * @return the area taken up by insets.
     */
    private float computeCompoundArea(final KNode parentNode) {
        float compoundArea = 0.0f;
        EList<KNode> children = parentNode.getChildren();
        
        if (!children.isEmpty()) {
            // Compound node; get layout data and insets and compute the node's bounding box
            KShapeLayout layoutData = parentNode.getData(KShapeLayout.class);
            KInsets insets = layoutData.getPadding();
            Rectangle2D.Float nodeRect = NodeSizeAnalysis.computeNodeRect(
                    parentNode, true, true, true);
            
            // Now, each side of the node has an inset which must be added to the
            // insets area; afterwards, four rectangles were counted twice and
            // have to be subtracted once
            float insetsArea = 0.0f;
            insetsArea += layoutData.getWidth() * (insets.getTop() + insets.getBottom());
            insetsArea += layoutData.getHeight() * (insets.getLeft() + insets.getRight());
            insetsArea -= insets.getTop() * (insets.getLeft() + insets.getRight())
                        + insets.getBottom() * (insets.getLeft() + insets.getRight());
            
            // The area covered by this node is its bounding box minus the inner area,
            // which is this node's height and width (without ports and labels) minus
            // the insets area
            compoundArea = nodeRect.width * nodeRect.height
                - (layoutData.getWidth() * layoutData.getHeight() - insetsArea);
            
            // Iterate through the children
            for (KNode child : children) {
                compoundArea += computeCompoundArea(child);
            }
        }
        
        return compoundArea;
    }
}
