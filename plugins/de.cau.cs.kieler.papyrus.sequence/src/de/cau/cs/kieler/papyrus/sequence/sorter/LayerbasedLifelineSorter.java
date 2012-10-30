package de.cau.cs.kieler.papyrus.sequence.sorter;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.papyrus.sequence.ILifelineSorter;
import de.cau.cs.kieler.papyrus.sequence.SeqProperties;
import de.cau.cs.kieler.papyrus.sequence.graph.SGraph;
import de.cau.cs.kieler.papyrus.sequence.graph.SLifeline;
import de.cau.cs.kieler.papyrus.sequence.graph.SMessage;

public class LayerbasedLifelineSorter implements ILifelineSorter {
    private int position;
    List<SLifeline> lifelines = new LinkedList<SLifeline>();
    List<SLifeline> sortedLifelines;

    public List<SLifeline> sortLifelines(SGraph graph, LGraph lgraph) {
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
            SMessage m0 = find_uppermost_message(lgraph);
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
                m0 = find_uppermost_outgoing_message(lgraph, x);
            } while (m0 != null);
        }

        return sortedLifelines;
    }

    private void assignToNextPosition(SLifeline lifeline) {
        if (!sortedLifelines.contains(lifeline)) {
            sortedLifelines.add(lifeline);
            lifeline.setPosition(position);
            position++;
            lifelines.remove(lifeline);
        }
    }

    private void addLayerToMessages(LGraph lgraph) {
        for (Layer layer : lgraph.getLayers()) {
            int layerIndex = layer.getIndex();
            for (LNode node : layer.getNodes()) {
                SMessage message = (SMessage) node.getProperty(Properties.ORIGIN);
                if (message != null) {
                    message.setProperty(SeqProperties.MESSAGE_LAYER, layerIndex);
                }
            }
        }
    }

    // Select lifeline x with outgoing message m_0 in the uppermost layer
    // If there are different messages in that layer, select lifeline with best incoming/outgoing
    // relation
    private SMessage find_uppermost_message(LGraph lgraph) {
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
                    int relation = candidate.getSource().getOutgoingMessages().size()
                            - candidate.getSource().getIncomingMessages().size();
                    if (relation > bestRelation) {
                        bestOne = candidate;
                    }
                }
                return bestOne;
            }
        }
        // The left lifelines are not connected by any message
        return null;
    }

    private SMessage find_uppermost_outgoing_message(LGraph lgraph, SLifeline x) {
        SMessage uppermostMessage = null;
        int bestLayer = lgraph.getLayers().size();
        for (SMessage outgoingMessage : x.getOutgoingMessages()) {
            if (((int) outgoingMessage.getProperty(SeqProperties.MESSAGE_LAYER)) < bestLayer) {
                // check if target lifeline was already set
                if (lifelines.contains(outgoingMessage.getTarget())) {
                    uppermostMessage = outgoingMessage;
                    bestLayer = (int) outgoingMessage.getProperty(SeqProperties.MESSAGE_LAYER);
                }
            }
        }
        return uppermostMessage;
    }
}
