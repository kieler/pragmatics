package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.intermediate.greedyswitch.PortIterable.PortOrder;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.NodeType;

public class NorthSouthEdgeAllCrossingsCounter {

    private final Map<LNode, Integer> nodePositions;
    private final Map<LPort, Integer> portPositions;
    private final Map<LNode, Integer> northCardinalities;
    private final LNode[] layer;
    private final Map<LNode, Integer> southCardinalities;
    private LNode currentOriginNode;
    private int amountOfNorthSouthEdges;
    private int amountOfLongEdgeDummies;
    private boolean northOfCurrentOriginNode = true;

    public NorthSouthEdgeAllCrossingsCounter(final LNode[] layer) {
        this.layer = layer;
        nodePositions = Maps.newHashMap();
        portPositions = Maps.newHashMap();
        northCardinalities = Maps.newHashMap();
        southCardinalities = Maps.newHashMap();
        initPositionsAndCardinalities();
    }

    private void initPositionsAndCardinalities() {
        int nodeId = 0;
        for (LNode element : layer) {
            LNode node = element;

            if (!isLongEdgeDummy(node)) {
                nodePositions.put(node, nodeId++);
            }

            setPortPositionsAndCardinalitiesFor(node, northCardinalities, PortSide.NORTH);
            setPortPositionsAndCardinalitiesFor(node, southCardinalities, PortSide.SOUTH);
        }
    }

    private void setPortPositionsAndCardinalitiesFor(final LNode node,
            final Map<LNode, Integer> cardinalities, final PortSide side) {
        Iterable<LPort> ports = new PortIterable(node, side, PortOrder.NORTHSOUTH_EASTWEST);
        int portId = 0;
        for (LPort port : ports) {
            portPositions.put(port, portId++);
        }
        cardinalities.put(node, portId);
    }

    public int countCrossings() {
        int crossings = 0;
        for (LNode node : layer) {
            crossings += getLongEdgeDummyCrossings(node);
            if (fixedPortOrderOn(node)) {
                if (hasPortOnSide(node, PortSide.NORTH)) {
                    crossings += getCrossingsOnSide(node, PortSide.NORTH);
                }
                if (hasPortOnSide(node, PortSide.SOUTH)) {
                    crossings += getCrossingsOnSide(node, PortSide.SOUTH);
                }
            }
        }
        return crossings;
    }

    // TODO-alan comment, What is origin node?
    private int getLongEdgeDummyCrossings(final LNode node) {
        int crossings = 0;
        if (isNorthSouth(node)) {
            if (originIsNotCurrentOrigin(node)) {
                resetDummyAmountsAndSetCurrentOriginNodeTo(originPortOf(node).getNode());
                // since we always iterate from north to south in a layer:
                northOfCurrentOriginNode = true;
            }
            if (northOfCurrentOriginNode) {
                amountOfNorthSouthEdges++;
            } else {
                crossings += amountOfLongEdgeDummies;
            }
        } else if (isLongEdgeDummy(node)) {
            if (northOfCurrentOriginNode) {
                crossings += amountOfNorthSouthEdges;
            } else {
                amountOfLongEdgeDummies++;
            }
        } else if (isNormal(node)) {
            resetDummyAmountsAndSetCurrentOriginNodeTo(node);
            // since we always iterate from north to south in a layer:
            northOfCurrentOriginNode = false;
        }
        return crossings;
    }

    private boolean originIsNotCurrentOrigin(final LNode node) {
        return !originPortOf(node).getNode().equals(currentOriginNode);
    }

    private void resetDummyAmountsAndSetCurrentOriginNodeTo(final LNode node) {
        currentOriginNode = node;
        amountOfNorthSouthEdges = 0;
        amountOfLongEdgeDummies = 0;
    }

    private boolean isNormal(final LNode node) {
        return node.getProperty(InternalProperties.NODE_TYPE) == NodeType.NORMAL;
    }

    private boolean isLongEdgeDummy(final LNode node) {
        return node.getProperty(InternalProperties.NODE_TYPE) == NodeType.LONG_EDGE;
    }

    private boolean isNorthSouth(final LNode node) {
        return node.getProperty(InternalProperties.NODE_TYPE) == NodeType.NORTH_SOUTH_PORT;
    }

    private int getCrossingsOnSide(final LNode node, final PortSide side) {
        int crossings = 0;
        Iterable<LPort> southPorts = node.getPorts(side);
        for (LPort port : southPorts) {
            if (hasConnectedEdge(port)) {
                LNode northSouthDummy = getConnectedNorthSouthDummy(port);
                if (hasPortOnSide(northSouthDummy, PortSide.EAST)) {
                    crossings += amountOfEasternCrossings(node, port, northSouthDummy, side);
                }
                if (hasPortOnSide(northSouthDummy, PortSide.WEST)) {
                    crossings += amountOfWesternCrossings(node, port, northSouthDummy, side);
                }
            }
        }
        return crossings;
    }

    private boolean hasConnectedEdge(final LPort port) {
        return !getConnectedNorthSouthDummies(port).isEmpty()
                || port.getConnectedEdges().iterator().hasNext();
    }

    private int amountOfWesternCrossings(final LNode node, final LPort port,
            final LNode northSouthDummy, final PortSide side) {
        return Math.min(positionOf(port), nearnessBetween(node, northSouthDummy));
    }

    private int amountOfEasternCrossings(final LNode node, final LPort port,
            final LNode northSouthDummy, final PortSide side) {
        return Math.min(cardinalityOnSide(node, side) - 1 - positionOf(port),
                nearnessBetween(node, northSouthDummy));
    }

    private boolean fixedPortOrderOn(final LNode node) {
        return node.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed();
    }

    private boolean hasPortOnSide(final LNode node, final PortSide side) {
        return getPortIteratorForSide(node, side).hasNext();
    }

    private LNode getConnectedNorthSouthDummy(final LPort port) {
        List<LPort> connectedNSDummies = getConnectedNorthSouthDummies(port);
        return connectedNSDummies.get(0).getNode();
    }

    private List<LPort> getConnectedNorthSouthDummies(final LPort port) {
        return port.getProperty(InternalProperties.CONNECTED_NORTH_SOUTH_PORT_DUMMIES);
    }

    private int nearnessBetween(final LNode node, final LNode northSouthDummy) {
        PortSide dummySide = getSideOf(northSouthDummy);
        int cardinality = cardinalityOnSide(node, dummySide);
        return cardinality - Math.abs(positionOf(node) - positionOf(northSouthDummy));
    }

    private Integer cardinalityOnSide(final LNode node, final PortSide side) {
        switch (side) {
        case NORTH:
            return northCardinalities.get(node);
        case SOUTH:
            return southCardinalities.get(node);
        default:
        }
        throw new UnsupportedOperationException(
                "The cardinality on this side has not been collected");
    }

    private int positionOf(final LNode node) {
        return nodePositions.get(node);
    }

    private int positionOf(final LPort port) {
        return portPositions.get(port);
    }

    private PortSide getSideOf(final LNode northSouthDummy) {
        return originPortOf(northSouthDummy).getSide();
    }

    private LPort originPortOf(final LNode node) {
        LPort port = node.getPorts().get(0);
        LPort origin = (LPort) port.getProperty(InternalProperties.ORIGIN);
        return origin;
    }

    private Iterator<LPort> getPortIteratorForSide(final LNode node, final PortSide side) {
        return node.getPorts(side).iterator();
    }

    public void notifyNodeSwitch(final LNode nodeOne, final LNode nodeTwo) {
        if (nodePositions.containsKey(nodeOne) && nodePositions.containsKey(nodeTwo)) {
            int formerPositionOfOne = nodePositions.get(nodeOne);
            nodePositions.put(nodeOne, nodePositions.get(nodeTwo));
            nodePositions.put(nodeTwo, formerPositionOfOne);
        }
    }

}
