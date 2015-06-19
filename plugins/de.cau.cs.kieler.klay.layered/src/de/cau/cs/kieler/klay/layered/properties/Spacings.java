/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.properties;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;

/**
 * Container class for a variety of spacing values that are either specified in the general
 * {@link LayoutOptions} class or KLay Layered's dedicated {@link Properties} class.
 * 
 * This class allows to either select the recorded spacing values directly or to query for spacing
 * values using one of the convenience methods. The methods do not provide results for every
 * combination of graph elements yet. In case it does not know the answer a
 * {@link UnspecifiedSpacingException} is thrown. In such a case the developer should add the
 * required functionality to this class.
 * 
 * @author uru
 */
public final class Spacings {

    // SUPPRESS CHECKSTYLE NEXT 40 VisibilityModifier
    /**
     * The horizontal spacing to be preserved between adjacent nodes. Multiply this value by
     * {@link #inLayerSpacingFactor} to get the vertical spacing value.
     */
    public final float nodeSpacing;
    /**
     * The horizontal spacing to be preserved between adjacent edge segments. Multiply this value by
     * {@link #inLayerSpacingFactor} to get the vertical spacing value.
     */
    public final float edgeEdgeSpacing;
    /**
     * The horizontal spacing to be preserved between a node and an adjacent edge segment. Multiply
     * this value by {@link #inLayerSpacingFactor} to get the vertical spacing value.
     */
    public final float edgeNodeSpacing;
    /**
     * The minimal spacing to be preserved between any pair of ports.
     */
    public final float portSpacing;
    /**
     * The minimal horizontal spacing to be preserved between external port dummies marked by the
     * {@link NodeType#EXTERNAL_PORT} flag. Multiply by {@link #inLayerSpacingFactor} to get the 
     * vertical spacing.
     */
    public final float externalPortSpacing;
    /**
     * The minimal spacing to be preserved between labels.
     */
    public final float labelSpacing;

    /**
     * A factor influencing all vertical spacing values. 
     */
    public final float inLayerSpacingFactor;

    /**
     * @param graph
     *            the {@link LGraph} for which to record the spacing values.
     */
    public Spacings(final LGraph graph) {

        nodeSpacing = graph.getProperty(InternalProperties.SPACING);
        inLayerSpacingFactor = graph.getProperty(Properties.OBJ_SPACING_IN_LAYER_FACTOR);
        edgeEdgeSpacing = nodeSpacing * graph.getProperty(Properties.EDGE_SPACING_FACTOR);
        edgeNodeSpacing = nodeSpacing * graph.getProperty(Properties.EDGE_NODE_SPACING_FACTOR);
        portSpacing = graph.getProperty(InternalProperties.PORT_SPACING);
        externalPortSpacing = graph.getProperty(InternalProperties.PORT_SPACING);
        labelSpacing = graph.getProperty(LayoutOptions.LABEL_SPACING);
    }

    // ----------------------------------------------------------------------------------
    // Regular, a.k.a. horizontal spacings
    // ----------------------------------------------------------------------------------

    /**
     * @param e1
     *            a graph element
     * @param e2
     *            another graph element
     * @return the horizontal spacing to be preserved between {@code e1} and {@code e2}
     */
    public float getHorizontalSpacing(final LGraphElement e1, final LGraphElement e2) {
        if (e1 instanceof LNode && e2 instanceof LNode) {
            return getHorizontalSpacing((LNode) e1, (LNode) e2);
        }

        throw new UnspecifiedSpacingException();
    }

    /**
     * @param n1
     *            a node
     * @param n2
     *            another node
     * @return the spacing to be preserved between {@code n1} and {@code n2}
     */
    public float getHorizontalSpacing(final LNode n1, final LNode n2) {
        return getHorizontalSpacing(n1.getNodeType(), n2.getNodeType());
    }

    /**
     * @param t1
     *            the type of one node
     * @param t2
     *            the type of the other node
     * @return the horizontal spacing value according to the two passed node types.
     */
    public float getHorizontalSpacing(final NodeType t1, final NodeType t2) {

        // 6 node types --> (6 ncr 2) = 16 2-subsets + 6 1-subsets = 22
        // sort them based on expected frequency
        
        // frequent ones
        if (matches(t1, t2, NodeType.NORMAL)) {
            return nodeSpacing;
        }
        if (matches(t1, t2, NodeType.NORMAL, NodeType.LONG_EDGE)) {
            return edgeNodeSpacing;
        }
        if (matches(t1, t2, NodeType.LONG_EDGE)) {
            return edgeEdgeSpacing;
        }
        if (matches(t1, t2, NodeType.NORMAL, NodeType.NORTH_SOUTH_PORT)) {
            return edgeNodeSpacing;
        }
        if (matches(t1, t2, NodeType.EXTERNAL_PORT)) {
            return externalPortSpacing;
        }
        if (matches(t1, t2, NodeType.NORTH_SOUTH_PORT)) {
            return edgeEdgeSpacing;
        }
        
        // normal
        if (matches(t1, t2, NodeType.NORMAL, NodeType.EXTERNAL_PORT)) {
            return externalPortSpacing; // shouldnt happen though
        }
        if (matches(t1, t2, NodeType.NORMAL, NodeType.LABEL)) {
            return edgeNodeSpacing;
        }
        if (matches(t1, t2, NodeType.NORMAL, NodeType.BIG_NODE)) {
            return edgeNodeSpacing;
        }
        
        // longedge
        if (matches(t1, t2, NodeType.LONG_EDGE, NodeType.NORTH_SOUTH_PORT)) {
            return edgeEdgeSpacing;
        }
        if (matches(t1, t2, NodeType.LONG_EDGE, NodeType.EXTERNAL_PORT)) {
            return externalPortSpacing; // shouldnt happen
        }
        if (matches(t1, t2, NodeType.LONG_EDGE, NodeType.LABEL)) {
            return labelSpacing;
        }
        if (matches(t1, t2, NodeType.LONG_EDGE, NodeType.BIG_NODE)) {
            return edgeNodeSpacing;
        }
        
        // northsouth
        if (matches(t1, t2, NodeType.NORTH_SOUTH_PORT, NodeType.EXTERNAL_PORT)) {
            return externalPortSpacing;
        }
        if (matches(t1, t2, NodeType.NORTH_SOUTH_PORT, NodeType.LABEL)) {
            return labelSpacing;
        }
        if (matches(t1, t2, NodeType.NORTH_SOUTH_PORT, NodeType.BIG_NODE)) {
            return edgeNodeSpacing;
        }

        // external
        if (matches(t1, t2, NodeType.EXTERNAL_PORT, NodeType.LABEL)) {
            return externalPortSpacing;
        }
        if (matches(t1, t2, NodeType.EXTERNAL_PORT, NodeType.BIG_NODE)) {
            return externalPortSpacing;
        }
        
        // label
        if (matches(t1, t2, NodeType.LABEL)) {
            return labelSpacing;
        }
        if (matches(t1, t2, NodeType.LABEL, NodeType.BIG_NODE)) {
            return labelSpacing;
        }
        
        // bignode
        if (matches(t1, t2, NodeType.BIG_NODE)) {
            return nodeSpacing;
        }
        
        throw new UnspecifiedSpacingException("Node types: " + t1 + " " + t2);
    }
    
    private static boolean matches(final NodeType n1, final NodeType n2, final NodeType desired) {
        return n1 == desired && n2 == desired;
    }

    private static boolean matches(final NodeType n1, final NodeType n2, final NodeType desired1,
            final NodeType desired2) {
        return (n1 == desired1 && n2 == desired2) || (n2 == desired1 && n1 == desired2);
    }

    // ----------------------------------------------------------------------------------
    // Vertical spacings
    // ----------------------------------------------------------------------------------

    /**
     * @param n1
     *            a node
     * @param n2
     *            another node
     * @return the vertical spacing to be preserved between {@code n1} and {@code n2}
     */
    public float getVerticalSpacing(final LNode n1, final LNode n2) {
        return getHorizontalSpacing(n1, n2) * inLayerSpacingFactor;
    }

    /**
     * @param t1
     *            a node type
     * @param t2
     *            another node type
     * @return the vertical spacing to be preserved between node type {@code t1} and {@code t2}
     */
    public float getVerticalSpacing(final NodeType t1, final NodeType t2) {
        return getHorizontalSpacing(t1, t2) * inLayerSpacingFactor;
    }

    /**
     * Dedicated exception indicating that no spacing value could be determined for a certain set of
     * graph elements. This is probably due to a programming error.
     */
    public static class UnspecifiedSpacingException extends RuntimeException {

        private static final long serialVersionUID = 1609767701465615319L;

        // SUPPRESS CHECKSTYLE NEXT 4 Javadoc
        public UnspecifiedSpacingException() {
        }

        public UnspecifiedSpacingException(final String msg) {
            super(msg);
        }
    }
}
