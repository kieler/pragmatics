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

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Lifeline representation for SGraphs.
 * 
 * @author grh
 * @kieler.design 2012-11-20 grh, cds, msp
 * @kieler.rating proposed yellow grh
 * 
 */
public class SLifeline extends SGraphElement implements Comparable<SLifeline> {
    private static final long serialVersionUID = 1309361361029991404L;
    private SGraph graph;
    private String name = "Lifeline";
    private int horizontalPosition;
    private KNode destructionEvent;
    private KVector position = new KVector();
    private KVector size = new KVector();
    private List<SMessage> messages = new LinkedList<SMessage>();

    /**
     * Get the SGraph to which the lifeline belongs.
     * 
     * @return the owning SGraph
     */
    public SGraph getGraph() {
        return graph;
    }

    /**
     * Set the SGraph to which the lifeline belongs.
     * 
     * @param graph
     *            the new owning SGraph
     */
    public void setGraph(final SGraph graph) {
        this.graph = graph;
    }

    /**
     * Add a message to the list of messages. The list of messages is sorted by their vertical
     * connection to the lifeline afterwards.
     * 
     * @param message
     *            the message to add
     */
    public void addMessage(final SMessage message) {
        // Get the position of the message at this lifeline
        double messageYPosition;
        if (message.getSource() == this) {
            messageYPosition = message.getSourceYPos();
        } else {
            messageYPosition = message.getTargetYPos();
        }

        // Compare to the position of each message in the list
        for (SMessage mess : messages) {
            // Get the position of the current message in the list
            double pos = 0;
            if (mess.getSource() == this) {
                pos = mess.getSourceYPos();
            } else {
                pos = mess.getTargetYPos();
            }

            // Since messages are already sorted, the place of the first message with greater
            // position is the right place for the message.
            if (messageYPosition < pos) {
                messages.add(messages.indexOf(mess), message);
                return;
            }
        }
        // Add the message at the end if none of the existing messages has greater position
        messages.add(message);
    }

    /**
     * Get the list of outgoing messages of the lifeline.
     * 
     * @return an iterable of outgoing messages
     */
    public Iterable<SMessage> getOutgoingMessages() {
        final SLifeline lifeline = this;
        return Iterables.filter(messages, new Predicate<SMessage>() {
            public boolean apply(final SMessage message) {
                return message.getSource() == lifeline;
            }
        });
    }

    /**
     * Get the number of outgoing messages.
     * 
     * @return the number of outgoing messages
     */
    public int getNumberOfOutgoingMessages() {
        int ret = 0;
        for (SMessage message : messages) {
            if (message.getSource() == this) {
                ret++;
            }
        }
        return ret;
    }

    /**
     * Get the list of incoming messages of the lifeline.
     * 
     * @return an iterable of incoming messages
     */
    public Iterable<SMessage> getIncomingMessages() {
        final SLifeline lifeline = this;
        return Iterables.filter(messages, new Predicate<SMessage>() {
            public boolean apply(final SMessage message) {
                return message.getTarget() == lifeline;
            }
        });
    }

    /**
     * Get the number of incoming messages.
     * 
     * @return the number of incoming messages
     */
    public int getNumberOfIncomingMessages() {
        int ret = 0;
        for (SMessage message : messages) {
            if (message.getTarget() == this) {
                ret++;
            }
        }
        return ret;
    }

    /**
     * Get the list of all messages connected to the lifeline.
     * 
     * @return the list of messages
     */
    public List<SMessage> getMessages() {
        return messages;
    }

    /**
     * Get the name of the lifeline.
     * 
     * @return the name of the lifeline
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name of the lifeline.
     * 
     * @param name
     *            the new name of the lifeline
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get the horizontal position of the lifeline.
     * 
     * @return the position
     */
    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    /**
     * Set the horizontal position of the lifeline.
     * 
     * @param horizontalPosition
     *            the new position
     */
    public void setHorizontalPosition(final int horizontalPosition) {
        this.horizontalPosition = horizontalPosition;
    }

    /**
     * Get the destruction event of the lifeline if existing.
     * 
     * @return the KNode of the destruction event or null if none exists
     */
    public KNode getDestructionEvent() {
        return destructionEvent;
    }

    /**
     * Set the destruction event of the lifeline.
     * 
     * @param destruction
     *            the KNode of the destruction event
     */
    public void setDestructionEvent(final KNode destruction) {
        this.destructionEvent = destruction;
    }

    /**
     * Get the position of the lifeline.
     * 
     * @return the Vector with the position
     */
    public KVector getPosition() {
        return position;
    }

    /**
     * Get the size of the lifeline.
     * 
     * @return the Vector with the size
     */
    public KVector getSize() {
        return size;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public int compareTo(final SLifeline other) {
        Object origin = this.getProperty(Properties.ORIGIN);
        Object otherOrigin = other.getProperty(Properties.ORIGIN);
        if (origin instanceof KNode && otherOrigin instanceof KNode) {
            KNode node = (KNode) origin;
            KNode otherNode = (KNode) otherOrigin;
            KShapeLayout layout = node.getData(KShapeLayout.class);
            KShapeLayout otherLayout = otherNode.getData(KShapeLayout.class);
            if (layout.getXpos() < otherLayout.getXpos()) {
                return -1;
            } else if (layout.getXpos() > otherLayout.getXpos()) {
                return 1;
            }
        }
        return 0;
    }
}

/**
 * Comparator for messages that is necessary to sort the messages of a lifeline.
 * 
 * @author grh
 * 
 */
class MessageComparator implements Comparator<SMessage> {

    private Map<SMessage, Double> base;

    public MessageComparator(final Map<SMessage, Double> base) {
        this.base = base;
    }

    public int compare(final SMessage a, final SMessage b) {

        if (base.get(a) < base.get(b)) {
            return -1;
        } else if ((Double) base.get(a) == (Double) base.get(b)) {
            return 0;
        } else {
            return 1;
        }
    }
}
