/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;


import java.awt.geom.Rectangle2D;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LInsets;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Sets the node margins. Node margins are influenced by both port positions and sizes
 * and label positions and sizes. Furthermore, comment boxes that are put directly
 * above or below a node also increase the margin.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>a layered graph; nodes have fixed port positions; labels
 *     have fixed positions.</dd>
 *   <dt>Postcondition:</dt><dd>the node margins are properly set to form a bounding box
 *     around the node and its ports and labels.</dd>
 *   <dt>Slots:</dt><dd>Before phase 4.</dd>
 *   <dt>Same-slot dependencies:</dt><dd>{@link PortPositionProcessor}</dd>
 * </dl>
 *
 * @see PortPositionProcessor
 * @author cds
 */
public class NodeMarginCalculator extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Node margin calculation", 1);
        double spacing = layeredGraph.getProperty(Properties.OBJ_SPACING);

        // Iterate through the layers
        for (Layer layer : layeredGraph) {
            // Iterate through the layer's nodes
            for (LNode node : layer) {
                // This will be our bounding box. We'll start with one that's the same size
                // as our node, and at the same position.
                Rectangle2D.Double boundingBox = new Rectangle2D.Double(
                        node.getPosition().x,
                        node.getPosition().y,
                        node.getSize().x,
                        node.getSize().y);
                
                // We'll reuse this rectangle as our box for elements to add to the bounding box
                Rectangle2D.Double elementBox = new Rectangle2D.Double();
                
                // Put the node's labels into the bounding box
                for (LLabel label : node.getLabels()) {
                    elementBox.x = label.getPosition().x + node.getPosition().x;
                    elementBox.y = label.getPosition().y + node.getPosition().y;
                    elementBox.width = label.getSize().x;
                    elementBox.height = label.getSize().y;
                    
                    Rectangle2D.union(boundingBox, elementBox, boundingBox);
                }
                
                // Do the same for ports and their labels
                for (LPort port : node.getPorts()) {
                    // Calculate the port's upper left corner's x and y coordinate
                    double portX = port.getPosition().x + node.getPosition().x;
                    double portY = port.getPosition().y + node.getPosition().y;
                    
                    // The port itself
                    elementBox.x = portX;
                    elementBox.y = portY;
                    elementBox.width = port.getSize().x;
                    elementBox.height = port.getSize().y;
                    
                    Rectangle2D.union(boundingBox, elementBox, boundingBox);
                    
                    // The port's labels
                    for (LLabel label : port.getLabels()) {
                        elementBox.x = label.getPosition().x + portX;
                        elementBox.y = label.getPosition().y + portY;
                        elementBox.width = label.getSize().x;
                        elementBox.height = label.getSize().y;
                        
                        Rectangle2D.union(boundingBox, elementBox, boundingBox);
                    }
                }
                
                // Reset the margin
                LInsets.Double margin = node.getMargin();
                margin.top = node.getPosition().y - boundingBox.y;
                margin.bottom = boundingBox.getMaxY() - (node.getPosition().y + node.getSize().y);
                margin.left = node.getPosition().x - boundingBox.x;
                margin.right = boundingBox.getMaxX() - (node.getPosition().x + node.getSize().x);
                
                // Process comments that are placed near the node
                processComments(node, spacing);
            }
        }
        
        getMonitor().done();
    }
    
    /**
     * Make some extra space for comment boxes that are placed near a node.
     * 
     * @param node a node
     * @param spacing the overall spacing value
     */
    private void processComments(final LNode node, final double spacing) {
        LInsets.Double margin = node.getMargin();

        // Consider comment boxes that are put on top of the node
        List<LNode> topBoxes = node.getProperty(Properties.TOP_COMMENTS);
        double topWidth = 0;
        if (topBoxes != null) {
            double maxHeight = 0;
            for (LNode commentBox : topBoxes) {
                maxHeight = Math.max(maxHeight, commentBox.getSize().y);
                topWidth += commentBox.getSize().x;
            }
            topWidth += spacing / 2 * (topBoxes.size() - 1);
            margin.top += maxHeight + spacing;
        }
        
        // Consider comment boxes that are put in the bottom of the node
        List<LNode> bottomBoxes = node.getProperty(Properties.BOTTOM_COMMENTS);
        double bottomWidth = 0;
        if (bottomBoxes != null) {
            double maxHeight = 0;
            for (LNode commentBox : bottomBoxes) {
                maxHeight = Math.max(maxHeight, commentBox.getSize().y);
                bottomWidth += commentBox.getSize().x;
            }
            bottomWidth += spacing / 2 * (bottomBoxes.size() - 1);
            margin.bottom += maxHeight + spacing;
        }
        
        double maxCommentWidth = Math.max(topWidth, bottomWidth);
        double totalWidth = node.getSize().x + margin.left + margin.right;
        if (maxCommentWidth > totalWidth) {
            double extra = (maxCommentWidth - totalWidth) / 2;
            margin.left += extra;
            margin.right += extra;
        }
    }
    
}
