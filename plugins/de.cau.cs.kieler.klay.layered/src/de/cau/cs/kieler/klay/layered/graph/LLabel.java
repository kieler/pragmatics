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
 */
public class LLabel extends LGraphElement {

    /** the current position of the label. */
    private Coord pos = new Coord();
    /** the size of the label. */
    private Coord size = new Coord();
    /** text of the label. */
    private String text;
    
    /**
     * Creates a label.
     * 
     * @param thetext text of the label
     */
    public LLabel(final String thetext) {
        this.text = thetext;
    }
    
    /**
     * Creates a label with empty text.
     */
    public LLabel() {
        this("");
    }
    
    /**
     * Returns the current position of the label.
     * 
     * @return the position
     */
    public Coord getPos() {
        return pos;
    }

    /**
     * Returns the current size of the label.
     * 
     * @return the size
     */
    public Coord getSize() {
        return size;
    }
    
    /**
     * Returns the text of the label.
     * 
     * @return the text
     */
    public String getText() {
        return text;
    }
    
}
