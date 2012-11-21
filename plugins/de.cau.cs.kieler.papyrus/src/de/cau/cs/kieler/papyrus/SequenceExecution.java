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

/**
 * Data structure for execution specification elements in sequence diagrams.
 * 
 * @author grh
 * @kieler.design proposed grh
 * @kieler.rating proposed yellow grh
 * 
 */
public class SequenceExecution {
    private String type = "";
    private List<Object> messages;
    private double xPos, yPos, maxXPos, maxYPos;
    private KNode origin;

    /**
     * Constructor that initializes the execution object.
     */
    public SequenceExecution() {
        messages = new LinkedList<Object>();
        xPos = 0;
        maxXPos = 0;
        yPos = Double.MAX_VALUE;
        maxYPos = 0;
    }

    /**
     * Get the type of the execution.
     * 
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type of the execution.
     * 
     * @param type
     *            the new type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Get the list of messages that are connected to the execution.
     * 
     * @return the list of messages
     */
    public List<Object> getMessages() {
        return messages;
    }

    /**
     * Add a message to the list of connected messages.
     * 
     * @param message
     *            the new message
     */
    public void addMessage(final Object message) {
        this.messages.add(message);
    }

    /**
     * Get the origin KNode of the execution.
     * 
     * @return the origin
     */
    public KNode getOrigin() {
        return origin;
    }

    /**
     * Set the origin KNode of the execution.
     * 
     * @param origin
     *            the new origin
     */
    public void setOrigin(final KNode origin) {
        this.origin = origin;
    }

    /**
     * Get the horizontal position of the execution.
     * 
     * @return the horizontal position
     */
    public double getxPos() {
        return xPos;
    }

    /**
     * Set the horizontal position of the execution.
     * 
     * @param xPos
     *            the new horizontal position
     */
    public void setxPos(final double xPos) {
        this.xPos = xPos;
    }

    /**
     * Get the vertical position of the execution.
     * 
     * @return the vertical position
     */
    public double getyPos() {
        return yPos;
    }

    /**
     * Set the vertical position of the execution.
     * 
     * @param yPos
     *            the new vertical position
     */
    public void setyPos(final double yPos) {
        this.yPos = yPos;
    }

    /**
     * Get the lower border position of the execution.
     * 
     * @return the lower border
     */
    public double getMaxYPos() {
        return maxYPos;
    }

    /**
     * Set the lower border position of the execution.
     * 
     * @param maxYPos
     *            the new lower border
     */
    public void setMaxYPos(final double maxYPos) {
        this.maxYPos = maxYPos;
    }

    /**
     * Get the right border position of the execution.
     * 
     * @return the right border
     */
    public double getMaxXPos() {
        return maxXPos;
    }

    /**
     * Set the right border position of the execution.
     * 
     * @param maxXPos
     *            the new right border
     */
    public void setMaxXPos(final double maxXPos) {
        this.maxXPos = maxXPos;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return "Origin: " + this.origin.getClass().getSimpleName() + ", Messages: " + this.messages
                + ", Pos: (" + this.xPos + "/" + this.yPos + "), MaxPos: (" + this.maxXPos + "/"
                + this.maxYPos + ")";
    }
}
