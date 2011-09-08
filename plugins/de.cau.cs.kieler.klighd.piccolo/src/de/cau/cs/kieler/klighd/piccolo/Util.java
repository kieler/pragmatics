/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
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
package de.cau.cs.kieler.klighd.piccolo;

import java.awt.Color;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolox.swt.PSWTPath;
import edu.umd.cs.piccolox.swt.PSWTText;

/**
 * A utility class to work with Piccolo nodes.
 * 
 * @author mri
 */
public final class Util {

    /** the attribute for the highlight. */
    private static final Object ATTRIBUTE_HIGHLIGHT = new Object();

    /**
     * A private constructor to prevent instantiation.
     */
    private Util() {
        // do nothing
    }

    /**
     * Sets a highlighting effect on the given node using the specified foreground and background
     * color and factor for line width.
     * 
     * @param node
     *            the node
     * @param foreground
     *            the foreground color or null for no change
     * @param background
     *            the background color or null for no change
     * @param lineWidthFactor
     *            the factor for the line width
     */
    public static void setHighlight(final PNode node, final Color foreground,
            final Color background, final float lineWidthFactor) {
        // remove current highlighting effect
        removeHighlight(node);
        // find the node to remove the highlighting effect from
        PNode repNode;
        if (node instanceof IChildRepresentedNode) {
            IChildRepresentedNode childRepNode = (IChildRepresentedNode) node;
            repNode = childRepNode.getRepresentationNode();
        } else {
            repNode = node;
        }
        // apply new highlighting effect
        HighlightUndo highlightUndo = new HighlightUndo();
        if (repNode instanceof PSWTAdvancedPath) {
            PSWTAdvancedPath path = (PSWTAdvancedPath) repNode;
            highlightUndo.foreground = (Color) path.getStrokePaint();
            highlightUndo.background = (Color) path.getPaint();
            highlightUndo.lineWidth = path.getLineWidth();
            if (foreground != null) {
                path.setStrokeColor(foreground);   
            }
            if (background != null) {
                path.setPaint(background);
            }
            path.setLineWidth(highlightUndo.lineWidth * lineWidthFactor);
        } else if (repNode instanceof PSWTPath) {
            PSWTPath path = (PSWTPath) repNode;
            highlightUndo.foreground = (Color) path.getStrokePaint();
            highlightUndo.background = (Color) path.getPaint();
            if (foreground != null) {
                path.setStrokeColor(foreground);
            }
            if (background != null) {
                path.setPaint(background);    
            }
        } else if (repNode instanceof PSWTText) {
            PSWTText text = (PSWTText) repNode;
            highlightUndo.foreground = text.getPenColor();
            highlightUndo.background = text.getBackgroundColor();
            if (foreground != null) {
                text.setPenColor(foreground);
            }
            if (background != null) {
                text.setBackgroundColor(background);
            }
        } else {
            highlightUndo.foreground = (Color) node.getPaint();
            if (foreground != null) {
                node.setPaint(highlightUndo.foreground);
            }
        }
        node.addAttribute(ATTRIBUTE_HIGHLIGHT, highlightUndo);
    }

    /**
     * Removes the highlighting effect from the given node if any.
     * 
     * @param node
     *            the node
     */
    public static void removeHighlight(final PNode node) {
        Object attribute = node.getAttribute(ATTRIBUTE_HIGHLIGHT);
        if (attribute instanceof HighlightUndo) {
            node.addAttribute(ATTRIBUTE_HIGHLIGHT, null);
            HighlightUndo highlightUndo = (HighlightUndo) attribute;
            // find the node to remove the highlighting effect from
            PNode repNode;
            if (node instanceof IChildRepresentedNode) {
                IChildRepresentedNode childRepNode = (IChildRepresentedNode) node;
                repNode = childRepNode.getRepresentationNode();
            } else {
                repNode = node;
            }
            // undo the highlighting effect
            if (repNode instanceof PSWTAdvancedPath) {
                PSWTAdvancedPath path = (PSWTAdvancedPath) repNode;
                path.setStrokeColor(highlightUndo.foreground);
                path.setPaint(highlightUndo.background);
                path.setLineWidth(highlightUndo.lineWidth);
            } else if (repNode instanceof PSWTPath) {
                PSWTPath path = (PSWTPath) repNode;
                path.setStrokeColor(highlightUndo.foreground);
                path.setPaint(highlightUndo.background);
            } else if (repNode instanceof PSWTText) {
                PSWTText text = (PSWTText) repNode;
                text.setPenColor(highlightUndo.foreground);
                text.setBackgroundColor(highlightUndo.background);
            } else {
                node.setPaint(highlightUndo.foreground);
            }
        }
    }

    /**
     * A data holder class for undoing a highlighting effect.
     */
    private static class HighlightUndo {

        /** the original foreground color. */
        private Color foreground;
        /** the original background color. */
        private Color background;
        /** the original line width. */
        private double lineWidth;

    }

}
