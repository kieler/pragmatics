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

public class SequenceArea {
    private Object origin;
    private List<Object> messages;
    private List<Object> lifelines;
    private List<SequenceArea> subAreas;
    private List<SequenceArea> containedAreas;
    private float xPos, yPos, width, height;
    private Object nextMessage;

    public SequenceArea(Object origin) {
        this.origin = origin;
        this.messages = new LinkedList<Object>();
        this.lifelines = new LinkedList<Object>();
        this.subAreas = new LinkedList<SequenceArea>();
        this.containedAreas = new LinkedList<SequenceArea>();
    }

    public Object getOrigin() {
        return origin;
    }

    public List<Object> getMessages() {
        return messages;
    }

    public List<Object> getLifelines() {
        return lifelines;
    }

    public void addLifeline(Object lifeline) {
        if (!lifelines.contains(lifeline)) {
            lifelines.add(lifeline);
        }
    }

    public List<SequenceArea> getSubAreas() {
        return subAreas;
    }

    public List<SequenceArea> getContainedAreas() {
        return containedAreas;
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

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Object getNextMessage() {
        return nextMessage;
    }

    public void setNextMessage(Object nextMessage) {
        this.nextMessage = nextMessage;
    }

    public String toString() {
        String subareas = "\nwith " + subAreas.size() + " Subareas: " + subAreas;
        if (subAreas.size() == 0) {
            subareas = "";
        }
        return "Area " + origin + "\nwith " + messages.size() + " Messages: " + messages + "\nand "
                + lifelines.size() + " Lifelines: " + lifelines + "\nat (" + xPos + "/" + yPos
                + ") + (" + width + "/" + height + ")" + subareas;
    }
}
