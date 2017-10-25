/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2017 by
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

import org.eclipse.elk.core.UnsupportedGraphException;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeLabelPlacement;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkLabel;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisOptions;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * Analyzes how centered center edge labels are along their edge. A value of 0.5 is dead center.
 * Lower values mean that labels are progressively more left from the center, higher values mean
 * that they are right from the center. -1 is returned if no center edge label is found or if the
 * layout direction is not horizontal. Note that we don't care about feedback edges or right-to-left
 * layouts; we handle everything the same.
 * 
 * <p>This may not work for many more special cases, but was good enough for when I needed it...</p>
 * 
 * @author cds
 */
public class EdgeLabelCenterednessAnalysis implements IAnalysis {

    /** Analysis ID. */
    public static final String ID = "de.cau.cs.kieler.grana.edgeLabelCenteredness";
    
    @Override
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        
        progressMonitor.begin("Edge Label Centeredness analysis", 1);
        
        boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);

        double ratioSum = 0;
        int centerLabelCount = 0;

        // Iterate through all nodes
        List<ElkNode> nodeQueue = new LinkedList<ElkNode>();
        nodeQueue.add(parentNode);
        
        while (nodeQueue.size() > 0) {
            // Pop first element
            ElkNode node = nodeQueue.remove(0);
            
            if (hierarchy) {
                nodeQueue.addAll(node.getChildren());
            }
            
            // Check if this node has a supported layout direction
            if (!isLayoutDirectionSupported(node)) {
                continue;
            }

            // Iterate through the node's edges
            for (ElkEdge edge : node.getContainedEdges()) {
                if (edge.isHyperedge()) {
                    throw new UnsupportedGraphException("Hyperedges are not supported by the "
                            + this.getClass().getSimpleName());
                }

                if (edge.isHierarchical()) {
                    continue;
                }

                if (edge.getSections().size() != 1) {
                    continue;
                }
                
                // We only care about edges with a center label
                ElkLabel centerLabel = findCenterLabel(edge);
                if (centerLabel == null) {
                    continue;
                }
                
                // The edge's horizontal span
                ElkEdgeSection edgeSection = edge.getSections().get(0);
                double leftX = edgeSection.getStartX();
                double rightX = edgeSection.getEndX();
                
                if (leftX > rightX) {
                    double tmp = leftX;
                    leftX = rightX;
                    rightX = tmp;
                }
                
                double edgeSpan = rightX - leftX;
                
                // Find the first label's horizontal center (we only care about the first label
                // because the others would give us the same result anyway, so we're counting
                // everything as one)
                double labelCenter = centerLabel.getX() + centerLabel.getWidth() * 0.5;
                
                // Find the ratio
                ratioSum += (labelCenter - leftX) / edgeSpan;
                
                centerLabelCount++;
            }
        }
        
        progressMonitor.done();

        if (centerLabelCount == 0) {
            return null;
        } else {
            return ratioSum / centerLabelCount;
        }
    }
    
    /**
     * We allow horizontal layout directions.
     */
    private boolean isLayoutDirectionSupported(final ElkNode graph) {
        Direction dir = graph.getProperty(CoreOptions.DIRECTION);
        return dir.isHorizontal() || dir == Direction.UNDEFINED;
    }
    
    /**
     * Return the first center label we find, or {@code null} if we don't find any.
     */
    private ElkLabel findCenterLabel(final ElkEdge edge) {
        for (ElkLabel label : edge.getLabels()) {
            EdgeLabelPlacement placement = label.getProperty(CoreOptions.EDGE_LABELS_PLACEMENT);
            if (placement == EdgeLabelPlacement.UNDEFINED
                    || placement == EdgeLabelPlacement.CENTER) {
                
                return label;
            }
        }
        
        return null;
    }

}
