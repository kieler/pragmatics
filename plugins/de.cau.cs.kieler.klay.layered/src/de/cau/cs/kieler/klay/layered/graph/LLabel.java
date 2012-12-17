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
package de.cau.cs.kieler.klay.layered.graph;

/**
 * A label in the layered graph structure.
 * 
 * @author jjc
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public final class LLabel extends LShape {
    
    /** Enum for the definition of a side of the edge to place the (edge) label to. 
     *  Currently supported in orthogonal edge routing.
     */
    public enum LSide {
        /** The label is placed above the edge. */
        UP,
        /** The label is placed below the edge. */
        DOWN;
    }

    /** the serial version UID. */
    private static final long serialVersionUID = -264988654527750053L;
    
    /** text of the label. */
    private String text;
    
    private LSide side;
    
    /**
     * Creates a label.
     * 
     * @param graph the graph for which the label is created
     * @param thetext text of the label
     */
    public LLabel(final LGraph graph, final String thetext) {
        super(graph);
        this.text = thetext;
    }
    
    /**
     * Creates a label with empty text.
     * 
     * @param graph the graph for which the label is created
     */
    public LLabel(final LGraph graph) {
        this(graph, "");
    }
    
    /**
     * {@inheritDoc}
     */
    public String toString() {
        if (text == null) {
            return "l_" + id;
        } else {
            return "l_" + text;
        }
    }
    
    /**
     * Returns the text of the label.
     * 
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @return the side
     */
    public LSide getSide() {
        return side;
    }

    /**
     * @param side the side to set
     */
    public void setSide(final LSide side) {
        this.side = side;
    }
    
}
