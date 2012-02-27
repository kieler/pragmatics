/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kiml.service.grana.analyses;

import java.awt.geom.Rectangle2D;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.service.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;


/**
 * A drawing analysis that computes the minimum, maximum and average node size of nodes
 * that are not compound nodes. (that is, that don't contain other nodes) Returns a
 * four-component result {@code (int min, float avg, int max, int nodes)}, where
 * {@code nodes} denotes the number of nodes that were actually included in the
 * analysis.
 * 
 * @author cds
 */
public class NodeSizeAnalysis implements IAnalysis {
    
    /**
     * Utility class that saves the analysis state during the analysis and holds the result
     * after the analysis is finished.
     * 
     * @author cds
     */
    private static class NodeSizeAnalysisState {
        /**
         * The number of nodes analyzed so far.
         */
        private int nodes = 0;
        
        /**
         * The maximum node size analyzed so far.
         */
        private float maxSize = Float.NEGATIVE_INFINITY;
        
        /**
         * The minimum node size analyzed so far.
         */
        private float minSize = Float.POSITIVE_INFINITY;
        
        /**
         * The sum of the size of all nodes analyzed so far.
         */
        private float sumOfSize = 0.0f;
    }
    

    // CONSTANTS
    /**
     * ID of this analysis.
     */
    public static final String ANALYSIS_ID = "de.cau.cs.kieler.kiml.grana.nodeSize";
    
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
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Node size analysis", 1);
        
        NodeSizeAnalysisState state = new NodeSizeAnalysisState();
        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);
        for (KNode node : parentNode.getChildren()) {
            computeNodeSizes(node, state, hierarchy);
        }
        
        progressMonitor.done();
        
        // Return result (min,avg,max)
        return new Object[] {
                (int) state.minSize,
                state.sumOfSize / state.nodes,
                (int) state.maxSize,
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
    private void computeNodeSizes(final KNode node, final NodeSizeAnalysisState state,
            final boolean hierarchy) {
        if (!hierarchy || node.getChildren().isEmpty()) {
            // Compute the node size
            Rectangle2D.Float nodeRect = computeNodeRect(node, true, true, true);
            float nodeSize = nodeRect.width * nodeRect.height;
            
            // Update analysis state
            state.nodes++;
            state.minSize = Math.min(state.minSize, nodeSize);
            state.maxSize = Math.max(state.maxSize, nodeSize);
            state.sumOfSize += nodeSize;
        } else {
            // Analyze the children
            for (KNode child : node.getChildren()) {
                computeNodeSizes(child, state, hierarchy);
            }
        }
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
    public static Rectangle2D.Float computeNodeRect(final KNode node, final boolean includeLabel,
            final boolean includePorts, final boolean includePortLabels) {
        
        float nodeTopLeftX = 0.0f;
        float nodeTopLeftY = 0.0f;
        float nodeBottomRightX = 0.0f;
        float nodeBottomRightY = 0.0f;
        
        // Get the node's layout data, and the layout data of the node's label, if any
        KShapeLayout layoutData = node.getData(KShapeLayout.class);
        
        // Compute the node's minimum top left and maximum bottom right coordinates.
        // At first, this is done relative to the top left point of the node.
        // This effectively computes the top left and bottom right coordinates of a
        // bounding box around the node, taking into account node labels and ports
        // and their labels.
        
        // First, the size of the node itself
        nodeBottomRightX = layoutData.getWidth();
        nodeBottomRightY = layoutData.getHeight();
        
        // Take the label into account, if any
        if (includeLabel) {
            for (KLabel label : node.getLabels()) {
                KShapeLayout labelLayoutData = label.getData(KShapeLayout.class);
                nodeTopLeftX = Math.min(nodeTopLeftX, labelLayoutData.getXpos());
                nodeTopLeftY = Math.min(nodeTopLeftY, labelLayoutData.getYpos());
                nodeBottomRightX = Math.max(nodeBottomRightX,
                        labelLayoutData.getXpos() + labelLayoutData.getWidth());
                nodeBottomRightY = Math.max(nodeBottomRightY,
                        labelLayoutData.getYpos() + labelLayoutData.getHeight());
            }
        }
        
        // Iterate through the ports
        if (includePorts) {
            for (KPort port : node.getPorts()) {
                KShapeLayout portLayoutData = port.getData(KShapeLayout.class);
                
                nodeTopLeftX = Math.min(nodeTopLeftX, portLayoutData.getXpos());
                nodeTopLeftY = Math.min(nodeTopLeftY, portLayoutData.getYpos());
                nodeBottomRightX = Math.max(nodeBottomRightX,
                        portLayoutData.getXpos() + portLayoutData.getWidth());
                nodeBottomRightY = Math.max(nodeBottomRightY,
                        portLayoutData.getYpos() + portLayoutData.getHeight());
                
                // Take the port label into account, if any
                if (includePortLabels) {
                    for (KLabel label : port.getLabels()) {
                        KShapeLayout labelLayoutData = label.getData(KShapeLayout.class);
                        nodeTopLeftX = Math.min(nodeTopLeftX,
                                portLayoutData.getXpos() + labelLayoutData.getXpos());
                        nodeTopLeftY = Math.min(nodeTopLeftY,
                                portLayoutData.getYpos() + labelLayoutData.getYpos());
                        nodeBottomRightX = Math.max(nodeBottomRightX,
                                portLayoutData.getXpos() + labelLayoutData.getXpos()
                                    + labelLayoutData.getWidth());
                        nodeBottomRightY = Math.max(nodeBottomRightY,
                                portLayoutData.getYpos() + labelLayoutData.getYpos()
                                    + labelLayoutData.getHeight());
                    }
                }
            }
        }
        
        // Offset coordinates by the node's actual position
        return new Rectangle2D.Float(
                nodeTopLeftX + layoutData.getXpos(),
                nodeTopLeftY + layoutData.getYpos(),
                nodeBottomRightX - nodeTopLeftX,
                nodeBottomRightY - nodeTopLeftY);
    }
}
