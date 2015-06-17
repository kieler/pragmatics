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
 * 
 * @author uru
 */
public final class Spacings {
    
    // SUPPRESS CHECKSTYLE NEXT 7 VisibilityModifier
    // SUPPRESS CHECKSTYLE NEXT 6 Javadoc
    public final float spacing;
    public final float edgeEdgeSpacing;
    public final float edgeNodeSpacing;
    public final float portSpacing;
    public final float externalPortSpacing;
    public final float labelSpacing;
    
    public final float inLayerSpacingFactor;
    
    /**
     * 
     */
    public Spacings(final LGraph graph) {

        // TODO in-layer spacing factor!!
        spacing = graph.getProperty(InternalProperties.SPACING);
         
        inLayerSpacingFactor = graph.getProperty(Properties.OBJ_SPACING_IN_LAYER_FACTOR);
        
        edgeEdgeSpacing = spacing
                * graph.getProperty(Properties.EDGE_SPACING_FACTOR);

        edgeNodeSpacing = spacing
                * graph.getProperty(Properties.EDGE_NODE_SPACING_FACTOR);
        
        portSpacing = graph.getProperty(InternalProperties.PORT_SPACING);
        externalPortSpacing = graph.getProperty(InternalProperties.PORT_SPACING);
        
        labelSpacing = graph.getProperty(LayoutOptions.LABEL_SPACING);
        
    }
    
    // ----------------------------------------------------------------------------------
    //                     Regular, a.k.a. horizontal spacings
    // ----------------------------------------------------------------------------------
    
    public float getHorizontalSpacing(final LGraphElement e1, final LGraphElement e2) {
        if(e1 instanceof LNode && e2 instanceof LNode) {
            return getHorizontalSpacing((LNode) e1, (LNode) e2);
        }
        
        throw new UnspecifiedSpacingException();
    }
    
    public float getHorizontalSpacing(final LNode n1, final LNode n2) {
        return getHorizontalSpacing(n1.getNodeType(), n2.getNodeType());
    }
    
    /**
     * @param t1
     *            the type of one node
     * @param t2
     *            the type of the other node
     * @return a spacing value according to the two passed node types.
     */
    public float getHorizontalSpacing(final NodeType t1, final NodeType t2) {

        // two regular nodes, normal spacing
        if (t1 == NodeType.NORMAL && t2 == NodeType.NORMAL) {
            return spacing;
        }
        
        // if at least one regular node is involved, use node edge spacing
        if (t1 == NodeType.NORMAL && t2 == NodeType.LONG_EDGE
                || t2 == NodeType.NORMAL && t1 == NodeType.LONG_EDGE) {
            return edgeNodeSpacing;
        }
        
        if (t1 == NodeType.LONG_EDGE && t2 == NodeType.LONG_EDGE) {
            return edgeEdgeSpacing;
        }

        if (t1 == NodeType.EXTERNAL_PORT && t2 == NodeType.EXTERNAL_PORT) {
            return externalPortSpacing;
        }

        throw new UnspecifiedSpacingException("Node types: " + t1 + " " + t2);
    }
    
    // ----------------------------------------------------------------------------------
    //                     Vertical spacings
    // ----------------------------------------------------------------------------------
    
    public float getVerticalSpacing(final LNode n1, final LNode n2) {
        return getHorizontalSpacing(n1, n2) * inLayerSpacingFactor;
    }
    
    public float getVerticalSpacing(final NodeType t1, final NodeType t2) {
        return getHorizontalSpacing(t1, t2) * inLayerSpacingFactor;
    }
    
    
    /**
     * Dedicated exception indicating that no spacing value could be determined for a certain set of
     * graph elements. This is probably due to a programming error.
     */
    public static class UnspecifiedSpacingException extends RuntimeException {

        public UnspecifiedSpacingException() {
        }

        public UnspecifiedSpacingException(String msg) {
            super(msg);
        }
    }
}
