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

public class SGraph extends SGraphElement {
    private static final long serialVersionUID = -7952451128297135991L;
    private List<SLifeline> lifelines = new LinkedList<SLifeline>();
    private List<SComment> comments = new LinkedList<SComment>();
    private float sizeX = 0.0f;
    private float sizeY = 0.0f;

    public List<SLifeline> getLifelines() {
        return lifelines;
    }
    
    public List<SComment> getComments() {
        return comments;
    }

    public void addLifeline(SLifeline lifeline) {
        this.lifelines.add(lifeline);
        lifeline.setGraph(this);
    }

    public void removeLifeline(SLifeline lifeline) {
        lifelines.remove(lifeline);
    }

    public float getSizeX() {
        return sizeX;
    }

    public void setSizeX(float sizeX) {
        this.sizeX = sizeX;
    }

    public float getSizeY() {
        return sizeY;
    }

    public void setSizeY(float sizeY) {
        this.sizeY = sizeY;
    }
    
    public String toString(){
        String ret = "SGraph: ( ";
        for (SLifeline lifeline : this.lifelines){
            ret += lifeline.getName() + " ";
        }
        ret += ")";
        return ret;
    }
}
