/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.analyses;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;

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
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Whitespace Analysis", 1);

        // The bounds of the area that is actually used for drawing nodes
        float minX = Float.POSITIVE_INFINITY;
        float maxX = Float.NEGATIVE_INFINITY;
        float minY = Float.POSITIVE_INFINITY;
        float maxY = Float.NEGATIVE_INFINITY;

        // Iterate through the parent node's children
        for (KNode child : parentNode.getChildren()) {
            Rectangle2D.Float nodeRect = NodeSizeAnalysis.computeNodeRect(child, true, true, true);

            // Compute the new bounds of the drawing area
            minX = Math.min(minX, nodeRect.x);
            minY = Math.min(minY, nodeRect.y);
            maxX = Math.max(maxX, nodeRect.x + nodeRect.width);
            maxY = Math.max(maxY, nodeRect.y + nodeRect.height);
        }

        // Rename for our purpos
        float width = maxX - minX;
        float height = maxY - minY;
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
            for (KNode node : parentNode.getChildren()) {
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
            final List<KNode> nodeQueue = new LinkedList<KNode>();
            nodeQueue.addAll(parentNode.getChildren());
            while (nodeQueue.size() > 0) {
                final KNode node = nodeQueue.remove(0);
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

    private void drawNode(final BufferedImage bi, final KNode node, final KVector offset) {

        Rectangle2D.Float rect = NodeSizeAnalysis.computeNodeRect(node, true, true, true);

        // convert all positions into a global coordinate system
        KVector absoluteOffset = offset.clone();
        if (node.getParent() != null) {
            KVector pos = node.getData(KShapeLayout.class).createVector();
            absoluteOffset.sub(pos);
            absoluteOffset.add(KimlUtil.toAbsolute(pos, node.getParent()));
        }

        // TODO consider some spacing around the node

        // Mark every pixel occupied by the node
        for (int x = (int) (rect.getMinX() + absoluteOffset.x); 
                x < (int) (rect.getMaxX() + absoluteOffset.x); ++x) {
            for (int y = (int) (rect.getMinY() + absoluteOffset.y); 
                    y < (int) (rect.getMaxY() + absoluteOffset.y); ++y) {
                bi.setRGB(x, y, Color.BLACK.getRGB());
            }
        }
    }
}
