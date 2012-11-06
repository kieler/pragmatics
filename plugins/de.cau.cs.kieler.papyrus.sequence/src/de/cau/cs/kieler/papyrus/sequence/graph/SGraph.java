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

/**
 * The graph representation for sequence diagrams.
 * @author grh
 *
 */
public class SGraph extends SGraphElement {
    private static final long serialVersionUID = -7952451128297135991L;
    private List<SLifeline> lifelines = new LinkedList<SLifeline>();
    private List<SComment> comments = new LinkedList<SComment>();
    private float sizeX = 0.0f;
    private float sizeY = 0.0f;

    /**
     * Get the list of lifelines in the SGraph.
     * @return the list of lifelines
     */
    public List<SLifeline> getLifelines() {
        return lifelines;
    }
    
    /**
     * Get the list of comments in the SGraph.
     * @return the list of comments
     */
    public List<SComment> getComments() {
        return comments;
    }

    /**
     * Add a lifeline to the SGraph.
     * @param lifeline the new lifeline
     */
    public void addLifeline(final SLifeline lifeline) {
        this.lifelines.add(lifeline);
        lifeline.setGraph(this);
    }

    /**
     * Remove a lifeline from the SGraph.
     * @param lifeline the lifeline to be removed
     */
    public void removeLifeline(final SLifeline lifeline) {
        lifelines.remove(lifeline);
    }

    /**
     * Get the horizontal size of the graph.
     * @return the horizontal size
     */
    public float getSizeX() {
        return sizeX;
    }

    /**
     * Set the horizontal size of the graph.
     * @param sizeX the new horizontal size
     */
    public void setSizeX(final float sizeX) {
        this.sizeX = sizeX;
    }

    /**
     * Get the vertical size of the graph.
     * @return the vertical size
     */
    public float getSizeY() {
        return sizeY;
    }

    /**
     * Set the vertical size of the graph.
     * @param sizeY the new vertical size
     */
    public void setSizeY(final float sizeY) {
        this.sizeY = sizeY;
    }
    
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
