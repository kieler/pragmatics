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

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkLabel;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisOptions;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * A drawing analysis that computes the minimum, maximum and average node size of nodes
 * that are not compound nodes. (that is, that don't contain other nodes) Returns a
 * four-component result {@code (int min, float avg, int max, int nodes)}, where
 * {@code nodes} denotes the number of nodes that were actually included in the
 * analysis.
 * 
 * @author cds
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class NodeSizeAnalysis implements IAnalysis {
    
    /**
     * Utility class that saves the analysis state during the analysis and holds the result
     * after the analysis is finished.
     * 
     * @author cds
     */
    static class NodeSizeAnalysisState {
        // SUPPRESS CHECKSTYLE NEXT 16 Javadoc|VisibilityModifier
        /**
         * The number of nodes analyzed so far.
         */
        int nodes = 0;
        
        double maxWidth = Double.NEGATIVE_INFINITY;
        double minWidth = Double.POSITIVE_INFINITY;
        double sumWidth = 0d;
        
        double maxHeight = Double.NEGATIVE_INFINITY;
        double minHeight = Double.POSITIVE_INFINITY;
        double sumHeight = 0d;
        
        double maxSize = Double.NEGATIVE_INFINITY;
        double minSize = Double.POSITIVE_INFINITY;
        double sumOfSize = 0.0;
    }
    
 
    // CONSTANTS
    /**
     * ID of this analysis.
     */
    public static final String ANALYSIS_ID = "de.cau.cs.kieler.grana.nodeSize";
    
    /**
     * Index of the minimum node size in the result array.
     */
    public static final int INDEX_MIN = 0;

    /**
     * Index of the average node size in the result array.
     */
    public static final int INDEX_AVG = 1;

    /**
     * Index of the maximum node size in the result array.
     */
    public static final int INDEX_MAX = 2;

    /**
     * Index of the number of analyzed nodes in the result array.
     */
    public static final int INDEX_NODES = 3;
    
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Node size analysis", 1);
        
        NodeSizeAnalysisState state = new NodeSizeAnalysisState();
        boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);
        for (ElkNode node : parentNode.getChildren()) {
            computeNodeSizes(node, state, hierarchy);
        }
        
        progressMonitor.done();
        
        // Return result (min, avg, max)
        return returnResult(state);
    }
    
    /**
     * @param state
     *            the state object with the analysis results.
     * @return the result of this analysis
     */
    protected Object returnResult(final NodeSizeAnalysisState state) {
        return new Object[] {
                state.minSize,
                state.sumOfSize / state.nodes,
                state.maxSize,
                state.nodes
        };
    }
    
    /**
     * Does the actual analysis. If the given node contains further nodes, the node's size
     * is not accounted for in the analysis result. Instead, the analysis proceeds with
     * analyzing the child nodes. If the given node doesn't contain further nodes, the node
     * is accounted for in the analysis.
     * 
     * @param node the node to analyze.
     * @param state the analysis state to accumulate the results in.
     * @param hierarchy whether to process hierarchy recursively
     */
    private void computeNodeSizes(final ElkNode node, final NodeSizeAnalysisState state,
            final boolean hierarchy) {
        
        if (!hierarchy || node.getChildren().isEmpty()) {
            analyseNode(node, state);
        }

        if (hierarchy) {
            // Analyze the children
            for (ElkNode child : node.getChildren()) {
                computeNodeSizes(child, state, hierarchy);
            }
        }
    }
    
    private void analyseNode(final ElkNode node, final NodeSizeAnalysisState state) {
        // Compute the node size
        Rectangle2D.Double nodeRect = computeNodeRect(node, true, true, true);
        double nodeSize = nodeRect.width * nodeRect.height;
        
        // Update analysis state
        state.nodes++;
        
        state.minWidth = Math.min(state.minWidth, nodeRect.width);
        state.maxWidth = Math.max(state.maxWidth, nodeRect.width);
        state.sumWidth += nodeRect.width;
        
        state.minHeight = Math.min(state.minHeight, nodeRect.height);
        state.maxHeight = Math.max(state.maxHeight, nodeRect.height);
        state.sumHeight += nodeRect.height;
        
        state.minSize = Math.min(state.minSize, nodeSize);
        state.maxSize = Math.max(state.maxSize, nodeSize);
        state.sumOfSize += nodeSize;
    }
    
    /**
     * Computes the bounding box of the given node. Optionally includes the node's
     * label and ports, if any.
     * 
     * @param node the node whose bounding box to compute.
     * @param includeLabel {@code true} if the node's label should be included in the bounding box.
     * @param includePorts {@code true} if the node's ports should be included in the bounding box.
     * @param includePortLabels {@code true} if the labels of ports should be included in the
     *                          bounding box.
     * @return the bounding box.
     */
    public static Rectangle2D.Double computeNodeRect(final ElkNode node, final boolean includeLabel,
            final boolean includePorts, final boolean includePortLabels) {
        
        double nodeTopLeftX = 0.0;
        double nodeTopLeftY = 0.0;
        double nodeBottomRightX = 0.0;
        double nodeBottomRightY = 0.0;
        
        // Compute the node's minimum top left and maximum bottom right coordinates.
        // At first, this is done relative to the top left point of the node.
        // This effectively computes the top left and bottom right coordinates of a
        // bounding box around the node, taking into account node labels and ports
        // and their labels.
        
        // First, the size of the node itself
        nodeBottomRightX = node.getWidth();
        nodeBottomRightY = node.getHeight();
        
        // Take the label into account, if any
        if (includeLabel) {
            for (ElkLabel label : node.getLabels()) {
                nodeTopLeftX = Math.min(nodeTopLeftX, label.getX());
                nodeTopLeftY = Math.min(nodeTopLeftY, label.getY());
                nodeBottomRightX = Math.max(nodeBottomRightX, label.getX() + label.getWidth());
                nodeBottomRightY = Math.max(nodeBottomRightY, label.getY() + label.getHeight());
            }
        }
        
        // Iterate through the ports
        if (includePorts) {
            for (ElkPort port : node.getPorts()) {
                nodeTopLeftX = Math.min(nodeTopLeftX, port.getX());
                nodeTopLeftY = Math.min(nodeTopLeftY, port.getY());
                nodeBottomRightX = Math.max(nodeBottomRightX, port.getX() + port.getWidth());
                nodeBottomRightY = Math.max(nodeBottomRightY, port.getY() + port.getHeight());
                
                // Take the port label into account, if any
                if (includePortLabels) {
                    for (ElkLabel label : port.getLabels()) {
                        nodeTopLeftX = Math.min(nodeTopLeftX, port.getX() + label.getX());
                        nodeTopLeftY = Math.min(nodeTopLeftY, port.getY() + label.getY());
                        nodeBottomRightX = Math.max(nodeBottomRightX,
                                port.getX() + label.getX() + label.getWidth());
                        nodeBottomRightY = Math.max(nodeBottomRightY,
                                port.getY() + label.getY() + label.getHeight());
                    }
                }
            }
        }
        
        // Offset coordinates by the node's actual position
        return new Rectangle2D.Double(
                nodeTopLeftX + node.getX(),
                nodeTopLeftY + node.getY(),
                nodeBottomRightX - nodeTopLeftX,
                nodeBottomRightY - nodeTopLeftY);
    }
}
