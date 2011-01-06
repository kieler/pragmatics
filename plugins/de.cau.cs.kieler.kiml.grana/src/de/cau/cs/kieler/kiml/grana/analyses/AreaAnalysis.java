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
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;


/**
 * An analysis that computes the size of the area a graph drawing occupies. Returns
 * a two-component result {@code (width, height)}.
 * 
 * @author cds
 */
public class AreaAnalysis implements IAnalysis {
    
    /**
     * ID of this analysis.
     */
    public static final String ANALYSIS_ID = "de.cau.cs.kieler.kiml.grana.area";
    
    
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
            float nodeX = 0.0f;
            float nodeY = 0.0f;
            float nodeWidth = 0.0f;
            float nodeHeight = 0.0f;
            
            // Get the node's layout data, and the layout data of the node's label, if any
            KShapeLayout layoutData = child.getData(KShapeLayout.class);
            KShapeLayout labelLayoutData = child.getLabel() == null
                ? null
                : child.getLabel().getData(KShapeLayout.class);
            
            // Compute the node's size and position
            if (layoutData != null) {
                if (labelLayoutData != null) {
                    nodeX = Math.min(layoutData.getXpos(), labelLayoutData.getXpos());
                    nodeY = Math.min(layoutData.getYpos(), labelLayoutData.getYpos());
                    
                    nodeWidth = Math.max(layoutData.getWidth(),
                            labelLayoutData.getXpos() + labelLayoutData.getWidth());
                    nodeHeight = Math.max(layoutData.getHeight(),
                            labelLayoutData.getYpos() + labelLayoutData.getHeight());
                } else {
                    nodeX = layoutData.getXpos();
                    nodeY = layoutData.getYpos();
                    
                    nodeWidth = layoutData.getWidth();
                    nodeHeight = layoutData.getHeight();
                }
            }
            
            // Compute the new bounds of the drawing area
            minX = Math.min(minX, nodeX);
            minY = Math.min(minY, nodeY);
            
            maxX = Math.max(maxX, nodeX + nodeWidth);
            maxY = Math.max(maxY, nodeY + nodeHeight);
        }
        
        return new Point2D.Float(maxX - minX, maxY - minY);
    }
}
