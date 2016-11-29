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

package de.cau.cs.kieler.kiml.grana.analyses;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.IAnalysis;

/**
 * A drawing analysis that computes the size of the area a graph drawing occupies. Returns
 * a two-component result {@code (float width, float height)}.
 * 
 * @author cds
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class AreaAnalysis implements IAnalysis {

    /**
     * Identifier of the area analysis.
     */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.area";
    
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
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Area analysis", 1);
        
        Point2D.Double area = computeArea(parentNode);
        progressMonitor.done();
        
        return new Object[] { area.x, area.y, area.x * area.y };
    }
    
    /**
     * Computes the size of the area occupied by the given node's children.
     * 
     * @param parentNode the parent node.
     * @return a point whose x coordinate gives the width and whose y coordinate gives the
     *         height of the drawing area used.
     */
    private Point2D.Double computeArea(final ElkNode parentNode) {
        // The bounds of the area that is actually used for drawing nodes
        double minX = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        
        // Iterate through the parent node's children
        for (ElkNode child : parentNode.getChildren()) {
            Rectangle2D.Double nodeRect = NodeSizeAnalysis.computeNodeRect(
                    child, true, true, true);
            
            // Compute the new bounds of the drawing area
            minX = Math.min(minX, nodeRect.x);
            minY = Math.min(minY, nodeRect.y);
            maxX = Math.max(maxX, nodeRect.x + nodeRect.width);
            maxY = Math.max(maxY, nodeRect.y + nodeRect.height);
        }
        
        return new Point2D.Double(maxX - minX, maxY - minY);
    }
}
