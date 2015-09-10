/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.tree.graph;

/**
 * A label in the T graph.
 * 
 * @author sor
 * @author sgu
 */
public class TLabel extends TShape {

    /** the serial version UID. */
    private static final long serialVersionUID = 1L;

    /** the edge this label is associated to. */
    private TEdge edge;
    /** label text. */
    private String text;

    /**
     * Create a new label. The label is also put into the edge's list of labels.
     * 
     * @param text
     *            the text of the new label
     * @param tedge
     *            edge corresponding to this label
     */
    public TLabel(final TEdge tedge, final String text) {
        this.edge = tedge;
        this.text = text;
        edge.getLabels().add(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (text == null || text.length() == 0) {
            return "l[" + edge.toString() + "]";
        } else {
            return "l_" + text;
        }
    }

    /**
     * Returns the text of this label.
     * 
     * @return text of this label
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the associated edge.
     * 
     * @return edge this label is associated to.
     */
    public TEdge getEdge() {
        return edge;
    }

}
