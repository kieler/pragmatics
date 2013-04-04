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
package de.cau.cs.kieler.papyrus.sequence.sorter;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.papyrus.sequence.ILifelineSorter;
import de.cau.cs.kieler.papyrus.sequence.graph.SGraph;
import de.cau.cs.kieler.papyrus.sequence.graph.SLifeline;
import de.cau.cs.kieler.papyrus.sequence.graph.SMessage;

/**
 * Lifelinesorting algorithm that sorts the lifelines according to their uppermost outgoing
 * messages. The "source" lifeline is placed leftmost, the successor lifelines following.
 * 
 * @author grh
 * @kieler.design proposed grh
 * @kieler.rating proposed yellow grh
 * 
 */
public class LayerbasedLifelineSorter implements ILifelineSorter {
    private int position;
    private List<SLifeline> lifelines = new LinkedList<SLifeline>();
    private List<SLifeline> sortedLifelines;

    /**
     * Sorts the lifelines in a stairway-like fashion. {@inheritDoc}
     */
    public List<SLifeline> sortLifelines(final SGraph graph, final LGraph lgraph,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Layer based lifeline sorting", 1);

        lifelines.addAll(graph.getLifelines());
        sortedLifelines = new LinkedList<SLifeline>();

        if (lgraph.getLayers().size() == 0) {
            // Abort, if no layers are set (e.g. outer node)
            return lifelines;
        }

        // Add the layerIndex Property to messages
        addLayerToMessages(lgraph);

        position = 0;

        while (!lifelines.isEmpty()) {
            // Find the message with the uppermost position whose source has not been set
            SMessage m0 = findUppermostMessage(lgraph);
            if (m0 == null) {
                // Left lifelines are not connected by any message => assign positions arbitrarily
                assignToNextPosition(lifelines.get(0));
                continue;
            }
            SLifeline x = m0.getSource();

            // Append Lifeline to the ordered lifelines list
            assignToNextPosition(x);

            do {
                // The target of this lifeline is set to next position
                x = m0.getTarget();

                // Append Lifeline to the ordered lifelines list
                assignToNextPosition(x);

                // Find the uppermost outgoing message of the next lifeline
                m0 = findUppermostOutgoingMessage(lgraph, x);
            } while (m0 != null);
        }

        progressMonitor.done();

        return sortedLifelines;
    }

    /**
     * Place the given lifeline to the next position.
     * 
     * @param lifeline
     *            the next lifeline to be placed
     */
    private void assignToNextPosition(final SLifeline lifeline) {
        if (!sortedLifelines.contains(lifeline)) {
            sortedLifelines.add(lifeline);
            lifeline.setHorizontalSlot(position);
            position++;
            lifelines.remove(lifeline);
        }
    }

    /**
     * Annotate the messages with a layer number.
     * 
     * @param lgraph
     *            the layered graph
     */
    private void addLayerToMessages(final LGraph lgraph) {
        for (Layer layer : lgraph.getLayers()) {
            int layerIndex = layer.getIndex();
            for (LNode node : layer.getNodes()) {
                SMessage message = (SMessage) node.getProperty(Properties.ORIGIN);
                if (message != null) {
                    message.setMessageLayer(layerIndex);
                }
            }
        }
    }

    /**
     * Select lifeline x with outgoing message m_0 in the uppermost layer. If there are different
     * messages in that layer, select lifeline with best incoming/outgoing relation (more outgoing
     * messages are desirable).
     * 
     * @param lgraph
     *            the layered graph
     * @return the uppermost message
     */
    private SMessage findUppermostMessage(final LGraph lgraph) {
        List<LNode> candidates = new LinkedList<LNode>();
        for (Layer layer : lgraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                SMessage message = (SMessage) node.getProperty(Properties.ORIGIN);
                if (message != null && lifelines.contains(message.getSource())) {
                    candidates.add(node);
                }
            }
            if (candidates.size() == 1) {
                // If there is only one candidate, return it
                return (SMessage) candidates.get(0).getProperty(Properties.ORIGIN);
            } else if (!candidates.isEmpty()) {
                // If there are more candidates, check, which ones source has the best in/out
                // relation
                SMessage bestOne = (SMessage) candidates.get(0).getProperty(Properties.ORIGIN);
                int bestRelation = Integer.MIN_VALUE;
                for (LNode node : candidates) {
                    SMessage candidate = (SMessage) node.getProperty(Properties.ORIGIN);
                    int relation = candidate.getSource().getNumberOfOutgoingMessages()
                            - candidate.getSource().getNumberOfIncomingMessages();
                    if (relation > bestRelation) {
                        bestRelation = relation;
                        bestOne = candidate;
                    }
                }
                return bestOne;
            }
        }
        // The left lifelines are not connected by any message
        return null;
    }

    /**
     * Find the uppermost message of the given lifeline that is pointing at a lifeline that was not
     * already set.
     * 
     * @param lgraph
     *            the layered graph
     * @param lifeline
     *            the current lifeline
     * @return the uppermost outgoing message
     */
    private SMessage findUppermostOutgoingMessage(final LGraph lgraph, final SLifeline lifeline) {
        SMessage uppermostMessage = null;
        int bestLayer = lgraph.getLayers().size();
        for (SMessage outgoingMessage : lifeline.getOutgoingMessages()) {
            if (outgoingMessage.getMessageLayer() < bestLayer) {
                // check if target lifeline was already set
                if (lifelines.contains(outgoingMessage.getTarget())) {
                    uppermostMessage = outgoingMessage;
                    bestLayer = outgoingMessage.getMessageLayer();
                }
            }
        }
        return uppermostMessage;
    }
}
