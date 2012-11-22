/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.papyrus.sequence.graph;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.math.KVector;

/**
 * The graph representation for sequence diagrams.
 * 
 * @author grh
 * @kieler.design proposed grh
 * @kieler.rating proposed yellow grh
 * 
 */
public class SGraph extends SGraphElement {
    private static final long serialVersionUID = -7952451128297135991L;
    private List<SLifeline> lifelines = new LinkedList<SLifeline>();
    private List<SComment> comments = new LinkedList<SComment>();
//    private double width = 0.0f;
//    private double height = 0.0f;
    private KVector size = new KVector();

    /**
     * Get the size of the graph.
     * 
     * @return the KVector with the size
     */
    public KVector getSize() {
        return size;
    }

    /**
     * Get the list of lifelines in the SGraph.
     * 
     * @return the list of lifelines
     */
    public List<SLifeline> getLifelines() {
        return lifelines;
    }

    /**
     * Get the list of comments in the SGraph.
     * 
     * @return the list of comments
     */
    public List<SComment> getComments() {
        return comments;
    }

    /**
     * Add a lifeline to the SGraph.
     * 
     * @param lifeline
     *            the new lifeline
     */
    public void addLifeline(final SLifeline lifeline) {
        this.lifelines.add(lifeline);
        lifeline.setGraph(this);
    }

    /**
     * Remove a lifeline from the SGraph.
     * 
     * @param lifeline
     *            the lifeline to be removed
     */
    public void removeLifeline(final SLifeline lifeline) {
        lifelines.remove(lifeline);
    }

//    /**
//     * Get the width of the graph.
//     * 
//     * @return the width
//     */
//    public double getWidth() {
//        return width;
//    }
//
//    /**
//     * Set the width of the graph.
//     * 
//     * @param width
//     *            the new width
//     */
//    public void setWidth(final double width) {
//        this.width = width;
//    }
//
//    /**
//     * Get the height of the graph.
//     * 
//     * @return the height
//     */
//    public double getHeight() {
//        return height;
//    }
//
//    /**
//     * Set the height of the graph.
//     * 
//     * @param height
//     *            the new height
//     */
//    public void setHeight(final double height) {
//        this.height = height;
//    }

    /**
     * 
     * {@inheritDoc}
     */
    public String toString() {
        String ret = "SGraph: ( ";
        for (SLifeline lifeline : this.lifelines) {
            ret += lifeline.getName() + " ";
        }
        ret += ")";
        return ret;
    }
}
