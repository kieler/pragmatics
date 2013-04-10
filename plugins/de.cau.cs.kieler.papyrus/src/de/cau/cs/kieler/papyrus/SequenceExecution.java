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
import de.cau.cs.kieler.core.math.KVector;

/**
 * Data structure for execution specification elements in sequence diagrams.
 * 
 * @author grh
 * @kieler.design proposed grh
 * @kieler.rating proposed yellow grh
 * 
 */
public class SequenceExecution {
    /** The type of the execution. */
    private String type = "";
    /** The list of connected messages. */
    private List<Object> messages;
    /** The size of the execution. */
    private KVector size;
    /** The position of the execution. */
    private KVector position;
    /** The originating KNode of the execution. */
    private KNode origin;

    /**
     * Constructor that initializes the execution object.
     */
    public SequenceExecution() {
        messages = new LinkedList<Object>();
        size = new KVector(0, -1);
        position = new KVector(0, 0);
    }

    /**
     * Get the size of the execution.
     * 
     * @return the KVector with the size
     */
    public KVector getSize() {
        return size;
    }

    /**
     * Get the position of the execution.
     * 
     * @return the KVector with the position
     */
    public KVector getPosition() {
        return position;
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
     * {@inheritDoc}
     */
    public String toString() {
        return "Origin: " + origin.getClass().getSimpleName() + ", Messages: " + messages
                + ", Pos: (" + getPosition().x + "/" + getPosition().y + "), Size: (" + getSize().x + "/"
                + getSize().y + ")";
    }
}
