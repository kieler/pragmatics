/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.grana.analyses;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * Analyses the whitespace of a diagram, i.e. the area of the drawing that is not occupied by nodes.
 * 
 * We report two different values, the first refers to the top level of the graph only, drawing
 * compound nodes as overall black boxes. The second value digs into the hierarchy and only draws
 * atomic nodes, neglecting any area occupied by the 'background' of compound nodes.
 * 
 * Both values are percentages, the number of white pixels per total pixels.
 * 
 * {@code (float topLevel, float hierarchy)}.
 * 
 * @author uru
 */
public class WhitespaceAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {

        progressMonitor.begin("Whitespace Analysis", 1);

        // The bounds of the area that is actually used for drawing nodes
        double minX = Float.POSITIVE_INFINITY;
        double maxX = Float.NEGATIVE_INFINITY;
        double minY = Float.POSITIVE_INFINITY;
        double maxY = Float.NEGATIVE_INFINITY;

        // Iterate through the parent node's children
        for (ElkNode child : parentNode.getChildren()) {
            Rectangle2D.Double nodeRect = NodeSizeAnalysis.computeNodeRect(child, true, true, true);

            // Compute the new bounds of the drawing area
            minX = Math.min(minX, nodeRect.x);
            minY = Math.min(minY, nodeRect.y);
            maxX = Math.max(maxX, nodeRect.x + nodeRect.width);
            maxY = Math.max(maxY, nodeRect.y + nodeRect.height);
        }

        // Rename for our purpos
        double width = maxX - minX;
        double height = maxY - minY;
        final KVector offset = new KVector(-minX, -minY);

        // Prepare the container for the result
        Object[] result = new Object[2];

        // Initialize image to draw to
        BufferedImage bi =
                new BufferedImage((int) Math.ceil(width), (int) Math.ceil(height),
                        BufferedImage.TYPE_BYTE_BINARY);

        clearImage(bi);
        try {

            // 1. only top level

            // Set every pixel of a node black
            for (ElkNode node : parentNode.getChildren()) {
                drawNode(bi, node, offset);
            }

            // Count white pixels
            long totalPixels = bi.getWidth() * bi.getHeight();
            long whitePixels = countWhitePixels(bi);
            double percWhitePixels = whitePixels / (double) totalPixels;

            // CHECKSTYLEOFF MagicNumber
            result[0] = percWhitePixels * 100;
            // ImageIO.write(bi, "PNG", new File("flat.png"));

            // 2. full hierarchy
            clearImage(bi);
            final List<ElkNode> nodeQueue = new LinkedList<ElkNode>();
            nodeQueue.addAll(parentNode.getChildren());
            while (nodeQueue.size() > 0) {
                final ElkNode node = nodeQueue.remove(0);
                if (node.getChildren().isEmpty()) {
                    drawNode(bi, node, offset);
                } else {
                    nodeQueue.addAll(node.getChildren());
                }
            }

            whitePixels = countWhitePixels(bi);
            percWhitePixels = whitePixels / (double) totalPixels;

            result[1] = percWhitePixels * 100;
            // ImageIO.write(bi, "PNG", new File("hierarchy.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Finish off..
        progressMonitor.done();

        return result;
    }

    private void clearImage(final BufferedImage bi) {
        // Set all pixels to white initially
        for (int x = 0; x < bi.getWidth(); ++x) {
            for (int y = 0; y < bi.getHeight(); ++y) {
                bi.setRGB(x, y, Color.WHITE.getRGB());
            }
        }
    }

    private long countWhitePixels(final BufferedImage bi) {
        long whitePixels = 0;
        for (int x = 0; x < bi.getWidth(); ++x) {
            for (int y = 0; y < bi.getHeight(); ++y) {
                if (bi.getRGB(x, y) == Color.WHITE.getRGB()) {
                    whitePixels++;
                }
            }
        }
        return whitePixels;
    }

    private void drawNode(final BufferedImage bi, final ElkNode node, final KVector offset) {

        Rectangle2D.Double rect = NodeSizeAnalysis.computeNodeRect(node, true, true, true);

        // convert all positions into a global coordinate system
        KVector absoluteOffset = offset.clone();
        if (node.getParent() != null) {
            KVector pos = new KVector(node.getX(), node.getY());
            absoluteOffset.sub(pos);
            absoluteOffset.add(ElkUtil.toAbsolute(pos, node.getParent()));
        }

        // TODO consider some spacing around the node
        double spacing = Math.max(0, node.getParent().getProperty(CoreOptions.SPACING_NODE_NODE));

        // assure that we stay within the image's boundaries (the spacing might reach out further)
        int minX = (int) Math.max(0, rect.getMinX() - spacing + absoluteOffset.x);
        int minY = (int) Math.max(0, rect.getMinY() - spacing + absoluteOffset.y);
        int maxX = (int) Math.min(bi.getWidth(), rect.getMaxX() + spacing + absoluteOffset.x);
        int maxY = (int) Math.min(bi.getHeight(), rect.getMaxY() + spacing + absoluteOffset.y);
        
        // Mark every pixel occupied by the node
        for (int x = minX; x < maxX; ++x) {
            for (int y = minY; y < maxY; ++y) {
                bi.setRGB(x, y, Color.BLACK.getRGB());
            }
        }
    }
}
