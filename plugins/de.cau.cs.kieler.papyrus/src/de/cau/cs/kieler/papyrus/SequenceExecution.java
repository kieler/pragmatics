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
package de.cau.cs.kieler.papyrus;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.kgraph.KNode;

public class SequenceExecution {
    private String type = "";
    private List<Object> messages;
    private float xPos, yPos, maxXPos, maxYPos;
    private KNode origin;

    public SequenceExecution() {
        messages = new LinkedList<Object>();
        xPos = 0;
        maxXPos = 0;
        yPos = Float.MAX_VALUE;
        maxYPos = 0;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Object> getMessages() {
        return messages;
    }

    public void addMessage(Object message) {
        this.messages.add(message);
    }

    public KNode getOrigin() {
        return origin;
    }

    public void setOrigin(KNode origin) {
        this.origin = origin;
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public float getMaxYPos() {
        return maxYPos;
    }

    public void setMaxYPos(float maxYPos) {
        this.maxYPos = maxYPos;
    }

    public float getMaxXPos() {
        return maxXPos;
    }

    public void setMaxXPos(float maxXPos) {
        this.maxXPos = maxXPos;
    }

    public String toString() {
        return "Origin: " + this.origin.getClass().getSimpleName() + ", Messages: " + this.messages
                + ", Pos: (" + this.xPos + "/" + this.yPos + "), MaxPos: (" + this.maxXPos + "/"
                + this.maxYPos + ")";
    }
}
