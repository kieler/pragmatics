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
 * 
 * @author jjc
 *
 */
public class LLabel extends LGraphElement {

    /** the original object from which the edge was created. */
    private Object origin;
    /** the current position of the label. */
    private Coord pos = new Coord();
    /** the size of the label. */
    private Coord size = new Coord();
    /** text of the label. */
    private String text;
    
    /**
     * Creates a label.
     * 
     * @param theorigin the original object for the label
     * @param thetext text of the label
     */
    public LLabel(final Object theorigin, final String thetext) {
        this.origin = theorigin;
        this.text = thetext;
    }
    
    /**
     * Creates a label.
     */
    public LLabel() {
        this(null, "");
    }
    
    /**
     * Returns the original object from which the label was created.
     * 
     * @return the original object
     */
    public Object getOrigin() {
        return origin;
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
