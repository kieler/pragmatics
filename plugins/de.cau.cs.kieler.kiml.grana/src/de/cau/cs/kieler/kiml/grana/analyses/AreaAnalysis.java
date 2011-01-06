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

package de.cau.cs.kieler.kiml.grana.analyses;

import java.awt.geom.Point2D;
import java.util.Map;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;


/**
 * An analysis that computes the size of the area a graph drawing occupies. Returns
 * a two-component result {@code (int width, int height)}.
 * 
 * @author cds
 */
public class AreaAnalysis implements IAnalysis {

    // CONSTANTS
    /**
     * ID of this analysis.
     */
    public static final String ANALYSIS_ID = "de.cau.cs.kieler.kiml.grana.area";
    
    /**
     * Index of the area width in the result array.
     */
    public static final int INDEX_WIDTH = 0;

    /**
     * Index of the area height in the result array.
     */
    public static final int INDEX_HEIGHT = 1;
    
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) throws KielerException {
        
        progressMonitor.begin("Area analysis", 1);
        Point2D.Float area = computeArea(parentNode);
        progressMonitor.done();
        
        return new Object[] {
                (int) area.x,
                (int) area.y
        };
    }
    
    /**
     * Computes the size of the area occupied by the given node's children.
     * 
     * @param parentNode the parent node.
     * @return a point whose x coordinate gives the width and whose y coordinate gives the
     *         height of the drawing area used.
     */
    private Point2D.Float computeArea(final KNode parentNode) {
        // The bounds of the area that is actually used for drawing nodes
        float minX = Float.POSITIVE_INFINITY;
        float maxX = Float.NEGATIVE_INFINITY;
        float minY = Float.POSITIVE_INFINITY;
        float maxY = Float.NEGATIVE_INFINITY;
        
        // Iterate through the parent node's children
        for (KNode child : parentNode.getChildren()) {
            float nodeTopLeftX = 0.0f;
            float nodeTopLeftY = 0.0f;
            float nodeBottomRightX = 0.0f;
            float nodeBottomRightY = 0.0f;
            
            // Get the node's layout data, and the layout data of the node's label, if any
            KShapeLayout layoutData = child.getData(KShapeLayout.class);
            
            // Compute the node's minimum top left and maximum bottom right coordinates.
            // At first, this is done relative to the top left point of the node.
            // This effectively computes the top left and bottom right coordinates of a
            // bounding box around the node, taking into account node labels and ports
            // and their labels.
            
            // First, the size of the node itself
            nodeBottomRightX = layoutData.getWidth();
            nodeBottomRightY = layoutData.getHeight();
            
            // Take the label into account, if any
            if (child.getLabel() != null) {
                KShapeLayout labelLayoutData = child.getLabel().getData(KShapeLayout.class);
                
                if (labelLayoutData != null) {
                    nodeTopLeftX = Math.min(nodeTopLeftX, labelLayoutData.getXpos());
                    nodeTopLeftY = Math.min(nodeTopLeftY, labelLayoutData.getYpos());
                    nodeBottomRightX = Math.max(nodeBottomRightX,
                            labelLayoutData.getXpos() + labelLayoutData.getWidth());
                    nodeBottomRightY = Math.max(nodeBottomRightY,
                            labelLayoutData.getYpos() + labelLayoutData.getHeight());
                }
            }
            
            // Iterate through the ports
            for (KPort port : child.getPorts()) {
                KShapeLayout portLayoutData = port.getData(KShapeLayout.class);
                
                nodeTopLeftX = Math.min(nodeTopLeftX, portLayoutData.getXpos());
                nodeTopLeftY = Math.min(nodeTopLeftY, portLayoutData.getYpos());
                nodeBottomRightX = Math.max(nodeBottomRightX,
                        portLayoutData.getXpos() + portLayoutData.getWidth());
                nodeBottomRightY = Math.max(nodeBottomRightY,
                        portLayoutData.getYpos() + portLayoutData.getHeight());
                
                // Take the port label into account, if any
                if (port.getLabel() != null) {
                    KShapeLayout labelLayoutData = port.getLabel().getData(KShapeLayout.class);
                    
                    if (labelLayoutData != null) {
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
            
            // Offset coordinates by the node's actual position
            nodeTopLeftX += layoutData.getXpos();
            nodeTopLeftY += layoutData.getYpos();
            nodeBottomRightX += layoutData.getXpos();
            nodeBottomRightY += layoutData.getYpos();
            
            // Compute the new bounds of the drawing area
            minX = Math.min(minX, nodeTopLeftX);
            minY = Math.min(minY, nodeTopLeftY);
            maxX = Math.max(maxX, nodeBottomRightX);
            maxY = Math.max(maxY, nodeBottomRightY);
        }
        
        return new Point2D.Float(maxX - minX, maxY - minY);
    }
}
