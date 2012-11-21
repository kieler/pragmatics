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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Lifeline representation for SGraphs.
 * 
 * @author grh
 * @kieler.design proposed grh
 * @kieler.rating proposed yellow grh
 * 
 */
public class SLifeline extends SGraphElement implements Comparable<SLifeline> {
    private static final long serialVersionUID = 1309361361029991404L;
    private SGraph graph;
    private String name = "Lifeline";
    private List<SMessage> outgoingMessages = new LinkedList<SMessage>();
    private List<SMessage> incomingMessages = new LinkedList<SMessage>();
    private int horizontalPosition;
    private KNode destructionEvent;
    private KVector position = new KVector();
    private KVector size = new KVector();

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
     * Get the list of outgoing messages of the lifeline.
     * 
     * @return the list of outgoing messages
     */
    public List<SMessage> getOutgoingMessages() {
        return outgoingMessages;
    }

    /**
     * Get the list of incoming messages of the lifeline.
     * 
     * @return the list of incoming messages
     */
    public List<SMessage> getIncomingMessages() {
        return incomingMessages;
    }

    /**
     * Get the list of all messages connected to the lifeline.
     * 
     * @return the list of messages
     */
    public List<SMessage> getMessages() {
        List<SMessage> messages = new LinkedList<SMessage>();
        messages.addAll(incomingMessages);
        messages.addAll(outgoingMessages);
        return messages;
    }

    // TODO sort messages by inlineComparator (array.sort)
    /**
     * Get a sorted list of messages connected to the lifeline. Messages are sorted according to
     * their vertical position at the lifeline (top-down).
     * 
     * @return the sorted list of messages
     */
    public List<SMessage> getMessagesSorted() {
        List<SMessage> incoming = getIncomingMessages();
        List<SMessage> outgoing = getOutgoingMessages();
        HashMap<SMessage, Double> map = new HashMap<SMessage, Double>();
        for (SMessage message : incoming) {
            map.put(message, (double) message.getTargetYPos());
        }
        for (SMessage message : outgoing) {
            map.put(message, (double) message.getSourceYPos());
        }
        MessageComparator comp = new MessageComparator(map);
        TreeMap<SMessage, Double> sortedMap = new TreeMap<SMessage, Double>(comp);
        sortedMap.putAll(map);
        Iterator<SMessage> iterator = sortedMap.keySet().iterator();
        List<SMessage> messages = new LinkedList<SMessage>();
        while (iterator.hasNext()) {
            messages.add(iterator.next());
        }
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
