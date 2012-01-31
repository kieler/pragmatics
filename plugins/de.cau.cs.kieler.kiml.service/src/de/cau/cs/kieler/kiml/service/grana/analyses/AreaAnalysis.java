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

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;


/**
 * A drawing analysis that computes the size of the area a graph drawing occupies. Returns
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
            final IKielerProgressMonitor progressMonitor) {
        
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
            Rectangle2D.Float nodeRect = NodeSizeAnalysis.computeNodeRect(
                    child, true, true, true);
            
            // Compute the new bounds of the drawing area
            minX = Math.min(minX, nodeRect.x);
            minY = Math.min(minY, nodeRect.y);
            maxX = Math.max(maxX, nodeRect.x + nodeRect.width);
            maxY = Math.max(maxY, nodeRect.y + nodeRect.height);
        }
        
        return new Point2D.Float(maxX - minX, maxY - minY);
    }
}
