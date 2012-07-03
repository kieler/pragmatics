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
package de.cau.cs.kieler.klay.planar.graph;

/**
 * A label in the layered graph structure.
 * 
 * @author jjc
 */
public class PLabel extends PShape {

    /** the serial version UID. */
    private static final long serialVersionUID = -1870955938798249029L;
    
    /** text of the label. */
    private String text;

    /**
     * Creates a label.
     * 
     * @param thetext
     *            text of the label
     */
    public PLabel(final String thetext) {
        super(-1, null);
        this.text = thetext;
    }

    /**
     * Creates a label with empty text.
     */
    public PLabel() {
        this("");
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

}
