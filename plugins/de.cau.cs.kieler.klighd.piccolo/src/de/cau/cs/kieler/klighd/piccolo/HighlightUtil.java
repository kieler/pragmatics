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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.util.Pair;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolox.swt.PSWTPath;
import edu.umd.cs.piccolox.swt.PSWTText;

/**
 * A utility class for setting highlighting effects on nodes. Supports the interface
 * {@code IChildRepresentedNode} by applying the highlighting effect to the representation node
 * instead.
 * 
 * @author mri
 */
public final class HighlightUtil {

    /** the attribute for the original undo data. */
    private static final Object ATTRIBUTE_ORIGINAL_UNDO = new Object();
    /** the attribute for the current highlight effect key. */
    private static final Object ATTRIBUTE_CURRENT_HIGHLIGHT = new Object();
    /** the attribute for the mapping of keys on the undo data. */
    private static final Object ATTRIBUTE_KEY_TO_UNDO = new Object();

    /**
     * A private constructor to prevent instantiation.
     */
    private HighlightUtil() {
        // do nothing
    }

    /**
     * Sets a highlighting effect with the given key on the node using the specified foreground and
     * background color and factor for line width.
     * 
     * @param key
     *            the key
     * @param node
     *            the node
     * @param foreground
     *            the foreground color or null for no change
     * @param background
     *            the background color or null for no change
     * @param lineWidthFactor
     *            the factor for the line width
     */
    public static void setHighlight(final Object key, final PNode node, final Color foreground,
            final Color background, final float lineWidthFactor) {
        if (key == null) {
            return;
        }
        Object attribute = node.getAttribute(ATTRIBUTE_CURRENT_HIGHLIGHT);
        // remember previous highlighting effects
        if (attribute != null) {
            List<Pair<Object, HighlightUndo>> mapping = getEffectMapping(node);
            removeFromEffectMapping(key, mapping);
            mapping.add(new Pair<Object, HighlightUndo>(attribute, createUndo(node)));
        }
        // apply the highlighting effect
        HighlightUndo original = getOriginalUndo(node);
        applyEffect(node, foreground != null ? foreground : original.foreground,
                background != null ? background : original.background, original.lineWidth
                        * lineWidthFactor);
        node.addAttribute(ATTRIBUTE_CURRENT_HIGHLIGHT, key);
    }

    /**
     * Removes the highlighting effect with the given key from the node if available.
     * 
     * @param key
     *            the key
     * @param node
     *            the node
     */
    public static void removeHighlight(final Object key, final PNode node) {
        if (key == null) {
            return;
        }
        Object attribute = node.getAttribute(ATTRIBUTE_CURRENT_HIGHLIGHT);
        if (attribute != null) {
            List<Pair<Object, HighlightUndo>> mapping = getEffectMapping(node);
            if (attribute.equals(key)) {
                // remove current effect by replacing it with the next recent one
                if (mapping.size() > 0) {
                    Pair<Object, HighlightUndo> keyToUndo = mapping.remove(mapping.size() - 1);
                    applyUndo(node, keyToUndo.getSecond());
                    node.addAttribute(ATTRIBUTE_CURRENT_HIGHLIGHT, keyToUndo.getFirst());
                } else {
                    HighlightUndo original = getOriginalUndo(node);
                    applyUndo(node, original);
                    // remove all traces of the highlighting effects
                    node.addAttribute(ATTRIBUTE_ORIGINAL_UNDO, null);
                    node.addAttribute(ATTRIBUTE_CURRENT_HIGHLIGHT, null);
                    node.addAttribute(ATTRIBUTE_KEY_TO_UNDO, null);
                }
            } else {
                removeFromEffectMapping(key, mapping);
            }
        }
    }

    /**
     * Returns the foreground color of the representation of the given node.
     * 
     * @param node
     *            the node
     * @return the foreground color
     */
    public static Color getForegroundColor(final PNode node) {
        PNode repNode;
        if (node instanceof IChildRepresentedNode) {
            IChildRepresentedNode childRepNode = (IChildRepresentedNode) node;
            repNode = childRepNode.getRepresentationNode();
        } else {
            repNode = node;
        }
        if (repNode instanceof PSWTAdvancedPath) {
            PSWTAdvancedPath path = (PSWTAdvancedPath) repNode;
            return (Color) path.getStrokePaint();
        } else if (repNode instanceof PSWTPath) {
            PSWTPath path = (PSWTPath) repNode;
            return (Color) path.getStrokePaint();
        } else if (repNode instanceof PSWTText) {
            PSWTText text = (PSWTText) repNode;
            return text.getPenColor();
        } else {
            return (Color) repNode.getPaint();
        }
    }

    /**
     * Returns the background color of the representation of the given node.
     * 
     * @param node
     *            the node
     * @return the background color
     */
    public static Color getBackgroundColor(final PNode node) {
        PNode repNode;
        if (node instanceof IChildRepresentedNode) {
            IChildRepresentedNode childRepNode = (IChildRepresentedNode) node;
            repNode = childRepNode.getRepresentationNode();
        } else {
            repNode = node;
        }
        if (repNode instanceof PSWTAdvancedPath) {
            PSWTAdvancedPath path = (PSWTAdvancedPath) repNode;
            return (Color) path.getPaint();
        } else if (repNode instanceof PSWTPath) {
            PSWTPath path = (PSWTPath) repNode;
            return (Color) path.getPaint();
        } else if (repNode instanceof PSWTText) {
            PSWTText text = (PSWTText) repNode;
            return text.getBackgroundColor();
        } else {
            return (Color) repNode.getPaint();
        }
    }

    /**
     * Returns a brighter version of the given color.
     * 
     * @param color
     *            the color
     * @return the brighter color
     */
    public static Color brighter(final Color color) {
        // TODO evaluate the use of the HSB color model here
        return color.brighter();
    }

    /**
     * Returns a darker version of the given color.
     * 
     * @param color
     *            the color
     * @return the brighter color
     */
    public static Color darker(final Color color) {
        // TODO evaluate the use of the HSB color model here
        return color.darker();
    }

    // helper methods

    private static HighlightUndo getOriginalUndo(final PNode node) {
        Object attribute = node.getAttribute(ATTRIBUTE_ORIGINAL_UNDO);
        if (attribute instanceof HighlightUndo) {
            return (HighlightUndo) attribute;
        } else {
            HighlightUndo original = createUndo(node);
            node.addAttribute(ATTRIBUTE_ORIGINAL_UNDO, original);
            return original;
        }
    }

    private static HighlightUndo createUndo(final PNode node) {
        HighlightUndo undo = new HighlightUndo();
        PNode repNode;
        if (node instanceof IChildRepresentedNode) {
            IChildRepresentedNode childRepNode = (IChildRepresentedNode) node;
            repNode = childRepNode.getRepresentationNode();
        } else {
            repNode = node;
        }
        if (repNode instanceof PSWTAdvancedPath) {
            PSWTAdvancedPath path = (PSWTAdvancedPath) repNode;
            undo.foreground = (Color) path.getStrokePaint();
            undo.background = (Color) path.getPaint();
            undo.lineWidth = path.getLineWidth();
        } else if (repNode instanceof PSWTPath) {
            PSWTPath path = (PSWTPath) repNode;
            undo.foreground = (Color) path.getStrokePaint();
            undo.background = (Color) path.getPaint();
        } else if (repNode instanceof PSWTText) {
            PSWTText text = (PSWTText) repNode;
            undo.foreground = text.getPenColor();
            undo.background = text.getBackgroundColor();
        } else {
            undo.foreground = (Color) repNode.getPaint();
        }
        return undo;
    }

    private static void applyUndo(final PNode node, final HighlightUndo undo) {
        applyEffect(node, undo.foreground, undo.background, undo.lineWidth);
    }

    private static void applyEffect(final PNode node, final Color foreground,
            final Color background, final double lineWidth) {
        PNode repNode;
        if (node instanceof IChildRepresentedNode) {
            IChildRepresentedNode childRepNode = (IChildRepresentedNode) node;
            repNode = childRepNode.getRepresentationNode();
        } else {
            repNode = node;
        }
        if (repNode instanceof PSWTAdvancedPath) {
            PSWTAdvancedPath path = (PSWTAdvancedPath) repNode;
            path.setStrokeColor(foreground);
            path.setPaint(background);
            path.setLineWidth(lineWidth);
        } else if (repNode instanceof PSWTPath) {
            PSWTPath path = (PSWTPath) repNode;
            path.setStrokeColor(foreground);
            path.setPaint(background);
        } else if (repNode instanceof PSWTText) {
            PSWTText text = (PSWTText) repNode;
            text.setPenColor(foreground);
            text.setBackgroundColor(background);
        } else {
            repNode.setPaint(foreground);
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Pair<Object, HighlightUndo>> getEffectMapping(final PNode node) {
        Object attribute = node.getAttribute(ATTRIBUTE_KEY_TO_UNDO);
        List<Pair<Object, HighlightUndo>> mapping;
        if (attribute instanceof List<?>) {
            mapping = (LinkedList<Pair<Object, HighlightUndo>>) attribute;
        } else {
            mapping = new LinkedList<Pair<Object, HighlightUndo>>();
            node.addAttribute(ATTRIBUTE_KEY_TO_UNDO, mapping);
        }

        return mapping;
    }

    private static void removeFromEffectMapping(final Object key,
            final List<Pair<Object, HighlightUndo>> mapping) {
        Iterator<Pair<Object, HighlightUndo>> iterator = mapping.iterator();
        while (iterator.hasNext()) {
            Pair<Object, HighlightUndo> keyToUndo = iterator.next();
            if (key.equals(keyToUndo.getFirst())) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * A data holder class for undoing a highlighting effect.
     */
    private static class HighlightUndo {

        /** the original foreground color. */
        private Color foreground = null;
        /** the original background color. */
        private Color background = null;
        /** the original line width. */
        private double lineWidth = 1.0;

    }

}
