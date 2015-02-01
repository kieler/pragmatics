package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;

import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;

/**
 * Counts crossings with in-layer inLayerEdges considering two neighboring nodes. Should the order
 * of the nodes change, the counter must be notified using {link
 * {@link #notifyNodeSwitch(LNode, LNode)}. This counter initializes the node.id's and port.id's
 * when initialized. This means that should they be changed after initialization, it won't
 * necessarily work anymore.
 * 
 * @author alan
 *
 */
public class InLayerEdgeNeighboringNodeCrossingCounter extends InLayerEdgeCrossingCounter {
    /** We store port.ids in mutlisets, as nodes without fixed order have the same port.id. */
    private final SortedMultiset<Integer> betweenLayerEdgePorts;
    private final SortedMultiset<Integer> upwardUpperNodeEdgePorts;
    private final SortedMultiset<Integer> downwardLowerNodeEdgePorts;
    private final SortedMultiset<Integer> downwardUpperNodeEdgePorts;
    private int upperLowerCrossings;
    private int lowerUpperCrossings;
    private LNode lowerNode;
    private LNode upperNode;

    public InLayerEdgeNeighboringNodeCrossingCounter(final LNode[] nodeOrder) {
        super(nodeOrder);
        betweenLayerEdgePorts = TreeMultiset.create();
        upwardUpperNodeEdgePorts = TreeMultiset.create();
        downwardLowerNodeEdgePorts = TreeMultiset.create();
        downwardUpperNodeEdgePorts = TreeMultiset.create();
    }

    /**
     * Counts crossings with in-layer inLayerEdges considering two neighboring nodes. The main
     * algorithm can be found in {@link #processNode(PortSide, LNode)}.
     * 
     * @param upper
     *            The upper node assuming a left-right layout.
     * @param lower
     *            The lower node assuming a left-right layout.
     */
    public void countCrossingsBetweenNeighbouringNodes(final LNode upper, final LNode lower) {
        upperNode = upper;
        lowerNode = lower;
        upperLowerCrossings = countUpperLowerNeighbouringCrossings();
        lowerUpperCrossings = countLowerUpperNeighbouringCrossings();
    }

    private int countUpperLowerNeighbouringCrossings() {
        int crossings = countNeighbouringCrossingsOnSide(PortSide.WEST);
        crossings += countNeighbouringCrossingsOnSide(PortSide.EAST);
        return crossings;
    }

    private int countLowerUpperNeighbouringCrossings() {
        switchUpperLower();

        int crossings = countUpperLowerNeighbouringCrossings();

        switchUpperLower();

        return crossings;
    }

    private void switchUpperLower() {
        notifyNodeSwitch(upperNode, lowerNode);
        LNode temp = upperNode;
        upperNode = lowerNode;
        lowerNode = temp;
    }

    private int countNeighbouringCrossingsOnSide(final PortSide portSide) {
        betweenLayerEdgePorts.clear();
        upwardUpperNodeEdgePorts.clear();
        downwardUpperNodeEdgePorts.clear();
        downwardLowerNodeEdgePorts.clear();
        addEdgesFromUpperNode(portSide);
        int crossings = countCrossingsToLowerNode(portSide);
        return crossings;
    }

    private void addEdgesFromUpperNode(final PortSide portSide) {
        for (LPort port : portsOrderedTopToBottom(upperNode, portSide)) {
            for (LEdge edge : port.getConnectedEdges()) {
                if (isInBetweenLayerEdge(edge)) {
                    betweenLayerEdgePorts.add(port.id);
                } else if (isNotSelfLoop(edge)) {
                    if (isUpward(edge, port)) {
                        upwardUpperNodeEdgePorts.add(endOfEdgePort(edge, port).id);
                    } else {
                        downwardUpperNodeEdgePorts.add(endOfEdgePort(edge, port).id);
                    }
                }
            }
        }
    }

    // TODO-alan Comment with ASCII-Drawing. This is super confusing.
    private int countCrossingsToLowerNode(final PortSide portSide) {
        int crossings = 0;
        for (LPort port : portsOrderedTopToBottom(lowerNode, portSide)) {
            for (LEdge edge : port.getConnectedEdges()) {

                if (isInBetweenLayerEdge(edge)) {
                    crossings += amountOfPortsAfter(port.id, downwardUpperNodeEdgePorts);
                } else if (isNotSelfLoop(edge)) {
                    if (downwardUpperNodeEdgePorts.contains(port.id)) {
                        crossings += countVisitedEdgeCrossings(port, edge);
                        downwardUpperNodeEdgePorts.remove(port.id);
                    } else {
                        LPort endOfEdgePort = endOfEdgePort(edge, port);
                        if (isUpward(edge, port)) {
                            crossings += countUpwardEdgeCrossings(endOfEdgePort);
                        } else {
                            downwardLowerNodeEdgePorts.add(endOfEdgePort.id);
                        }
                    }
                }

            }
        }
        // count crossings below lower Node
        for (Integer id : downwardLowerNodeEdgePorts) {
            crossings += amountOfPortsBefore(id, downwardUpperNodeEdgePorts);
        }
        return crossings;
    }

    private int countUpwardEdgeCrossings(final LPort endOfEdgePort) {
        int crossings = amountOfPortsBefore(endOfEdgePort.id, upwardUpperNodeEdgePorts);
        crossings += amountOfPortsAfter(endOfEdgePort.id, betweenLayerEdgePorts);
        crossings += downwardUpperNodeEdgePorts.size();
        return crossings;
    }

    private int countVisitedEdgeCrossings(final LPort port, final LEdge edge) {
        // Edges which are removed from the set go from the upperNode to the lowerNode.
        // Between-layer edges have only been added for the upper node, so this edge crosses
        // all between layer edges between its end ports.
        int crossings = amountOfPortsInbetweenEndsOf(edge, betweenLayerEdgePorts);

        // if the port order is fixed, this edge will also have crossed any edges which originate
        // from the lower node and go downward.
        if (portOrderIsFixedFor(lowerNode)) {
            crossings += downwardLowerNodeEdgePorts.size();
        }
        return crossings;
    }

    /**
     * Returns the amount of in-layer crossings assuming the order upperNode-lowerNode in a
     * left-right layout.
     * 
     * @return The amount of in-layer crossings in current order.
     */
    public int getUpperLowerCrossings() {
        return upperLowerCrossings;
    }

    /**
     * Returns the amount of in-layer crossings assuming the order upperNode-lowerNode in a
     * left-right layout.
     * 
     * @return The amount of in-layer crossings in switched order.
     */
    public int getLowerUpperCrossings() {
        return lowerUpperCrossings;
    }
}
