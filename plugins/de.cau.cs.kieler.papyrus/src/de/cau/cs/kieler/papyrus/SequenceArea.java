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

import de.cau.cs.kieler.core.math.KVector;

/**
 * Data structure for area-like elements in sequence diagrams.
 * 
 * @author grh
 * @kieler.design proposed grh
 * @kieler.rating proposed yellow grh
 * 
 */
public class SequenceArea {
    /** The originating object of the execution. */
    private Object origin;
    /** The list of messages contained in the area. */
    private List<Object> messages;
    /** The list of affected lifelines. */
    private List<Object> lifelines;
    /** The list of subareas (in case of a combined fragment). */
    private List<SequenceArea> subAreas;
    /** The list of areas that are contained in this area. */
    private List<SequenceArea> containedAreas;
    /** The message, that is nearest to the area if the area is empty. */
    private Object nextMessage;
    /** The size of the area. */
    private KVector size;
    /** The position of the area. */
    private KVector position;

    /**
     * Constructor that initializes the area.
     * 
     * @param origin
     *            the origin this object is created for
     */
    public SequenceArea(final Object origin) {
        this.origin = origin;
        messages = new LinkedList<Object>();
        lifelines = new LinkedList<Object>();
        subAreas = new LinkedList<SequenceArea>();
        containedAreas = new LinkedList<SequenceArea>();
        size = new KVector();
        position = new KVector();
    }

    /**
     * Get the size of the area.
     * 
     * @return the KVector with the size
     */
    public KVector getSize() {
        return size;
    }

    /**
     * Get the position of the area.
     * 
     * @return the KVector with the position
     */
    public KVector getPosition() {
        return position;
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
                + lifelines.size() + " Lifelines: " + lifelines + "\nat (" + getPosition().x + "/"
                + getPosition().y + ") + (" + getSize().x + "/" + getSize().y + ")" + subareas;
    }
}
