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
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klay.layered.properties.Properties;

public class SLifeline extends SGraphElement implements Comparable<SLifeline> {
    private static final long serialVersionUID = 1309361361029991404L;
    private SGraph graph;
    private String name = "Lifeline";
    private List<SMessage> outgoingMessages = new LinkedList<SMessage>();
    private List<SMessage> incomingMessages = new LinkedList<SMessage>();
    private int position;
    private KNode destruction;

    public SGraph getGraph() {
        return graph;
    }

    public void setGraph(SGraph graph) {
        this.graph = graph;
    }

    public List<SMessage> getOutgoingMessages() {
        return outgoingMessages;
    }

    public List<SMessage> getIncomingMessages() {
        return incomingMessages;
    }

    public List<SMessage> getMessages() {
        List<SMessage> messages = new LinkedList<SMessage>();
        messages.addAll(incomingMessages);
        messages.addAll(outgoingMessages);
        return messages;
    }
    
    // TODO sort messages by inlineComparator (array.sort)
    public List<SMessage> getMessagesSorted(){
        List<SMessage> incoming = getIncomingMessages();
        List<SMessage> outgoing = getOutgoingMessages();
        HashMap<SMessage, Double> map = new HashMap<SMessage, Double>();
        for (SMessage message : incoming){
            map.put(message, (double) message.getTargetYPos());
        }
        for (SMessage message : outgoing){
            map.put(message, (double) message.getSourceYPos());
        }
        MessageComparator comp = new MessageComparator(map);
        TreeMap<SMessage, Double> sortedMap = new TreeMap<SMessage, Double>(comp);
        sortedMap.putAll(map);
        Iterator<SMessage> iterator = sortedMap.keySet().iterator();
        List<SMessage> messages = new LinkedList<SMessage>();
        while (iterator.hasNext()){
            messages.add(iterator.next());
        }
        return messages;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public KNode getDestruction() {
        return destruction;
    }

    public void setDestruction(KNode destruction) {
        this.destruction = destruction;
    }

    public int compareTo(SLifeline other) {
        Object origin = this.getProperty(Properties.ORIGIN);
        Object otherOrigin = other.getProperty(Properties.ORIGIN);
        if (origin instanceof KNode && otherOrigin instanceof KNode){
            KNode node = (KNode) origin;
            KNode otherNode = (KNode) otherOrigin;
            KShapeLayout layout = node.getData(KShapeLayout.class);
            KShapeLayout otherLayout = otherNode.getData(KShapeLayout.class);
            if (layout.getXpos() < otherLayout.getXpos()){
                return -1;
            } else if (layout.getXpos() > otherLayout.getXpos()){
                return 1;
            }
        }
        return 0;
    }
}

class MessageComparator implements Comparator {

    Map base;
    public MessageComparator(Map base) {
        this.base = base;
    }

    public int compare(Object a, Object b) {

      if((Double)base.get(a) < (Double)base.get(b)) {
        return -1;
      } else if((Double)base.get(a) == (Double)base.get(b)) {
        return 0;
      } else {
        return 1;
      }
    }
}
