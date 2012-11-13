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

/**
 * Data structure for area-like elements in sequence diagrams.
 * 
 * @author grh
 * @kieler.design proposed grh
 * @kieler.rating proposed yellow grh
 * 
 */
public class SequenceArea {
    private Object origin;
    private List<Object> messages;
    private List<Object> lifelines;
    private List<SequenceArea> subAreas;
    private List<SequenceArea> containedAreas;
    private float xPos, yPos, width, height;
    private Object nextMessage;

    /**
     * Constructor that initializes the area.
     * 
     * @param origin
     *            the origin this object is created for
     */
    public SequenceArea(final Object origin) {
        this.origin = origin;
        this.messages = new LinkedList<Object>();
        this.lifelines = new LinkedList<Object>();
        this.subAreas = new LinkedList<SequenceArea>();
        this.containedAreas = new LinkedList<SequenceArea>();
    }

    /**
     * Get the origin object.
     * 
     * @return the origin
     */
    public Object getOrigin() {
        return origin;
    }

    /**
     * Get the list of messages that are covered by the area.
     * 
     * @return the list of messages
     */
    public List<Object> getMessages() {
        return messages;
    }

    /**
     * Get the list of lifelines that are (partly) covered by the area.
     * 
     * @return the list of lifelines
     */
    public List<Object> getLifelines() {
        return lifelines;
    }

    /**
     * Add a lifeline to the list of lifelines covered by the area.
     * 
     * @param lifeline
     *            the new lifeline
     */
    public void addLifeline(final Object lifeline) {
        if (!lifelines.contains(lifeline)) {
            lifelines.add(lifeline);
        }
    }

    /**
     * Get the list of sub-areas (for combined fragments) of the area.
     * 
     * @return the list of sub-areas
     */
    public List<SequenceArea> getSubAreas() {
        return subAreas;
    }

    /**
     * Get the list of areas, that are completely covered by this area.
     * 
     * @return the list of contained areas
     */
    public List<SequenceArea> getContainedAreas() {
        return containedAreas;
    }

    /**
     * Get the horizontal position of the area.
     * 
     * @return the horizontal position
     */
    public float getxPos() {
        return xPos;
    }

    /**
     * Set the horizontal position of the area.
     * 
     * @param xPos
     *            the new horizontal position
     */
    public void setxPos(final float xPos) {
        this.xPos = xPos;
    }

    /**
     * Get the vertical position of the area.
     * 
     * @return the vertical position
     */
    public float getyPos() {
        return yPos;
    }

    /**
     * Set the vertical position of the area.
     * 
     * @param yPos
     *            the new vertical position
     */
    public void setyPos(final float yPos) {
        this.yPos = yPos;
    }

    /**
     * Get the width of the area.
     * 
     * @return the width of the area
     */
    public float getWidth() {
        return width;
    }

    /**
     * Set the width of the area.
     * 
     * @param width
     *            the new width
     */
    public void setWidth(final float width) {
        this.width = width;
    }

    /**
     * Get the height of the area.
     * 
     * @return the height of the area
     */
    public float getHeight() {
        return height;
    }

    /**
     * Set the height of the area.
     * 
     * @param height
     *            the new height
     */
    public void setHeight(final float height) {
        this.height = height;
    }

    /**
     * Get the message that is next to the area if the area does not contain any messages.
     * 
     * @return the message next to the area
     */
    public Object getNextMessage() {
        return nextMessage;
    }

    /**
     * Set the message that is next to the area.
     * 
     * @param nextMessage
     *            the new message that is next to the area
     */
    public void setNextMessage(final Object nextMessage) {
        this.nextMessage = nextMessage;
    }

    /**
     * {@inheritDoc}
     */
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
