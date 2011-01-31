/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.rail.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.p4nodes.INodePlacer;
import de.cau.cs.kieler.klay.rail.IPatterns;
import de.cau.cs.kieler.klay.rail.Properties;
import de.cau.cs.kieler.klay.rail.graph.RailLayer;
import de.cau.cs.kieler.klay.rail.graph.RailRow;
import de.cau.cs.kieler.klay.rail.options.NodeType;
import de.cau.cs.kieler.klay.rail.options.PortType;

/**
 * Node placer for railway layout.
 * 
 * @author jjc
 */
public class RailwayNodePlacer extends AbstractAlgorithm implements INodePlacer {

    private IPatterns patterns = new SimplePatternsImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        super.reset();
    }

    /**
     * {@inheritDoc}
     */
    public void placeNodes(final LayeredGraph layeredGraph) {
        getMonitor().begin("Linear segments node placement", 1);
        int layerCount = layeredGraph.getLayers().size();

        // determine starting position for straight-first DFS
        LNode walker = null;
        List<LNode> known = new LinkedList<LNode>();
        Stack<LNode> nextNodes = new Stack<LNode>();
        findStart: for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                if (node.getProperty(Properties.ENTRY_POINT)) {
                    walker = node;
                    break findStart;
                }
            }
        }
        if (walker == null) {
            throw new IllegalArgumentException("No entry point found!");
        }
        if (walker.getLayer() instanceof RailLayer) {
            ((RailLayer) walker.getLayer()).getRowList().addNodeAtPosition(walker, 0);
        } else {
            throw new IllegalArgumentException("Only works with RailLayers!");
        }

        // start straight first DFS
        nextNodes.push(walker);
        known.add(walker);
        List<LNode> trunk = new LinkedList<LNode>();
        boolean stillTrunk = true;

        while (!nextNodes.isEmpty()) {
            walker = nextNodes.pop();
            boolean found = false;
            if (allNeighborsKnown(walker, known)) {
                continue;
            }
            for (LPort port : walker.getPorts()) {
                LNode target = port.getEdges().get(0).getTarget().getNode();
                LPort targetPort = port.getEdges().get(0).getTarget();
                if ((port.getProperty(Properties.PORT_TYPE).equals(PortType.STRAIGHT) || port
                        .getProperty(Properties.PORT_TYPE).equals(PortType.STUMP))
                        && !known.contains(target)) {
                    found = true;
                    known.add(target);
                    if (!allNeighborsKnown(walker, known)) {
                        nextNodes.push(walker);
                    }
                    if (stillTrunk) {
                        trunk.add(walker);
                    }
                    nextNodes.push(target);
                    putTargetInGrid(target, getPositionForNode(targetPort, port));
                    break;
                }
            }
            if (!found) {
                for (LPort port : walker.getPorts()) {
                    LNode target = port.getEdges().get(0).getTarget().getNode();
                    LPort targetPort = port.getEdges().get(0).getTarget();
                    if (port.getProperty(Properties.PORT_TYPE).equals(PortType.BRANCH)
                            && !known.contains(target)) {
                        stillTrunk = false;
                        if (!known.contains(target)) {
                            known.add(target);
                            if (!allNeighborsKnown(walker, known)) {
                                nextNodes.push(walker);
                            }
                            nextNodes.push(target);
                            int offset = 0;
                            if (port.getNode().getProperty(Properties.NODE_TYPE)
                                    .equals(NodeType.SWITCH_LEFT)) {
                                offset -= getConflictDistanceLeft(
                                        getMaxNewPosByLayer(target, layerCount),
                                        getMinPosByLayer(layeredGraph));
                            } else if (port.getNode().getProperty(Properties.NODE_TYPE)
                                    .equals(NodeType.SWITCH_RIGHT)) {
                                offset += getConflictDistanceRight(
                                        getMinNewPosByLayer(target, layerCount),
                                        getMaxPosByLayer(layeredGraph));
                            }
                            putTargetInGrid(target, offset + getPositionForNode(targetPort, port));
                        }
                    }
                }
            }

        }

        // apply grid to graph
        for (Layer layer : layeredGraph.getLayers()) {
            if (layer instanceof RailLayer) {
                gridToAbsolutePosition((RailLayer) layer, getMinimalPosition(layeredGraph));
            } else {
                throw new IllegalArgumentException("Only works with RailLayers!");
            }
        }
        getMonitor().done();
    }

    private void putTargetInGrid(final LNode target, final int position) {
        RailRow targetRow;
        if (target.getLayer() instanceof RailLayer) {
            targetRow = ((RailLayer) target.getLayer()).getRowList();
        } else {
            throw new IllegalArgumentException("Only works with RailLayers!");
        }
        targetRow.addNodeAtPosition(target, position);
    }

    private int getMinimalPosition(final LayeredGraph graph) {
        int result = Integer.MAX_VALUE;
        for (Layer layer : graph.getLayers()) {
            if (layer instanceof RailLayer) {
                int i = ((RailLayer) layer).getRowList().getMinimalPosition();
                if (i < result) {
                    result = i;
                }
            } else {
                // TODO: maybe validate once, gets annoying
            }
        }
        return result;
    }

    private void gridToAbsolutePosition(final RailLayer layer, final int minPos) {
        float spacing = layer.getGraph().getProperty(Properties.OBJ_SPACING);
        float borspacing = layer.getGraph().getProperty(Properties.BOR_SPACING);
        double value = borspacing;
        for (LNode node : layer.getRowList().getNodesOrderedByPosition()) {
            int position = layer.getRowList().getPosition(node);
            int offset = (-1) * minPos;
            value = offset * (node.getSize().y + spacing) + position * (node.getSize().y + spacing);
            node.getPos().y = value;
            System.out.println("Setting y of " + node.toString() + " to " + value);
            System.out.println("Position was " + position);
        }
    }

    private boolean allNeighborsKnown(final LNode node, final List<LNode> known) {
        boolean result = true;
        for (LPort port : node.getPorts()) {
            result &= known.contains(port.getEdges().get(0).getTarget().getNode());
        }
        return result;
    }

    private int getConflictDistanceLeft(final List<Integer> maxNewPosByLayer,
            final List<Integer> minPosByLayer) {
        int result = 0;
        for (int i = 0; i < maxNewPosByLayer.size(); i++) {
            if (maxNewPosByLayer.get(i) >= minPosByLayer.get(i)) {
                int conflict = 1 + maxNewPosByLayer.get(i) - minPosByLayer.get(i);
                if (conflict > result) {
                    result = conflict;
                }
            }
        }
        return result;
    }

    private int getConflictDistanceRight(final List<Integer> minNewPosByLayer,
            final List<Integer> maxPosByLayer) {
        int result = 0;
        for (int i = 0; i < minNewPosByLayer.size(); i++) {
            if (minNewPosByLayer.get(i) <= maxPosByLayer.get(i)) {
                int conflict = 1 + maxPosByLayer.get(i) - minNewPosByLayer.get(i);
                if (conflict > result) {
                    result = conflict;
                }
            }
        }
        return result;
    }

    private List<Integer> getMinPosByLayer(final LayeredGraph layeredGraph) {
        List<Integer> result = new ArrayList<Integer>(layeredGraph.getLayers().size());
        for (Layer layer : layeredGraph.getLayers()) {
            if (layer instanceof RailLayer) {
                result.add(((RailLayer) layer).getRowList().getMinimalPosition());
            }
        }
        return result;
    }

    private List<Integer> getMaxPosByLayer(final LayeredGraph layeredGraph) {
        List<Integer> result = new ArrayList<Integer>(layeredGraph.getLayers().size());
        for (Layer layer : layeredGraph.getLayers()) {
            if (layer instanceof RailLayer) {
                result.add(((RailLayer) layer).getRowList().getMaximalPosition());
            }
        }
        return result;
    }

    private List<Integer> getMinNewPosByLayer(final LNode first, final int layerCount) {
        List<Integer> result = new LinkedList<Integer>();
        List<LNode> knownNodes = new LinkedList<LNode>();
        Stack<LNode> next = new Stack<LNode>();
        HashMap<LNode, Integer> placement = new HashMap<LNode, Integer>();
        for (int i = 0; i < layerCount; i++) {
            result.add(Integer.MAX_VALUE);
        }
        LNode walker = first;

        next.push(walker);
        knownNodes.add(walker);

        while (!next.isEmpty()) {
            walker = next.pop();
            boolean found = false;
            if (allNeighborsKnown(walker, knownNodes)) {
                continue;
            }
            for (LPort port : walker.getPorts()) {
                LNode target = port.getEdges().get(0).getTarget().getNode();
                LPort targetPort = port.getEdges().get(0).getTarget();
                if ((port.getProperty(Properties.PORT_TYPE).equals(PortType.STRAIGHT) || port
                        .getProperty(Properties.PORT_TYPE).equals(PortType.STUMP))
                        && !knownNodes.contains(target)) {
                    found = true;
                    knownNodes.add(target);
                    if (!allNeighborsKnown(walker, knownNodes)) {
                        next.push(walker);
                    }
                    next.push(target);
                    int layerNo = target.getLayer().getIndex();
                    int newPos = getPositionForNode(targetPort, port);
                    placement.put(target, newPos);
                    if (result.get(layerNo) < newPos) {
                        result.set(layerNo, newPos);
                    }
                    break;
                }
            }

            if (!found) {
                for (LPort port : walker.getPorts()) {
                    LNode target = port.getEdges().get(0).getTarget().getNode();
                    LPort targetPort = port.getEdges().get(0).getTarget();
                    if (port.getProperty(Properties.PORT_TYPE).equals(PortType.BRANCH)
                            && !knownNodes.contains(target)) {
                        if (!knownNodes.contains(target)) {
                            knownNodes.add(target);
                            if (!allNeighborsKnown(walker, knownNodes)) {
                                next.push(walker);
                            }
                            next.push(target);
                            int layerNo = target.getLayer().getIndex();
                            int newPos = getPositionForNode(targetPort, port, placement.get(walker));
                            if (result.get(layerNo) < newPos) {
                                result.set(layerNo, newPos);
                            }
                        }
                    }
                }
            }

        }

        return result;
    }

    private List<Integer> getMaxNewPosByLayer(final LNode first, final int layerCount) {
        List<Integer> result = new LinkedList<Integer>();
        List<LNode> knownNodes = new LinkedList<LNode>();
        Stack<LNode> next = new Stack<LNode>();
        HashMap<LNode, Integer> placement = new HashMap<LNode, Integer>();
        for (int i = 0; i < layerCount; i++) {
            result.add(0);
        }
        LNode walker = first;

        next.push(walker);
        knownNodes.add(walker);

        while (!next.isEmpty()) {
            walker = next.pop();
            boolean found = false;
            if (allNeighborsKnown(walker, knownNodes)) {
                continue;
            }
            for (LPort port : walker.getPorts()) {
                LNode target = port.getEdges().get(0).getTarget().getNode();
                LPort targetPort = port.getEdges().get(0).getTarget();
                if ((port.getProperty(Properties.PORT_TYPE).equals(PortType.STRAIGHT) || port
                        .getProperty(Properties.PORT_TYPE).equals(PortType.STUMP))
                        && !knownNodes.contains(target)) {
                    found = true;
                    knownNodes.add(target);
                    if (!allNeighborsKnown(walker, knownNodes)) {
                        next.push(walker);
                    }
                    next.push(target);
                    int layerNo = target.getLayer().getIndex();
                    int newPos = getPositionForNode(targetPort, port, placement.get(walker));
                    placement.put(target, newPos);
                    if (result.get(layerNo) > newPos) {
                        result.set(layerNo, newPos);
                    }
                    break;
                }
            }

            if (!found) {
                for (LPort port : walker.getPorts()) {
                    LNode target = port.getEdges().get(0).getTarget().getNode();
                    LPort targetPort = port.getEdges().get(0).getTarget();
                    if (port.getProperty(Properties.PORT_TYPE).equals(PortType.BRANCH)
                            && !knownNodes.contains(target)) {
                        if (!knownNodes.contains(target)) {
                            knownNodes.add(target);
                            if (!allNeighborsKnown(walker, knownNodes)) {
                                next.push(walker);
                            }
                            next.push(target);
                            int layerNo = target.getLayer().getIndex();
                            int newPos = getPositionForNode(targetPort, port);
                            if (result.get(layerNo) > newPos) {
                                result.set(layerNo, newPos);
                            }
                        }
                    }
                }
            }

        }
        return result;
    }

    private int getPositionForNode(final LPort targetPort, final LPort sourcePort) {
        return getPositionForNode(targetPort, sourcePort, Integer.MIN_VALUE);
    }

    private int getPositionForNode(final LPort targetPort, final LPort sourcePort,
            final int sourcePosition) {
        RailRow targetRow;
        RailRow sourceRow;
        LNode source = sourcePort.getNode();
        LNode target = targetPort.getNode();
        if (target.getLayer() instanceof RailLayer && source.getLayer() instanceof RailLayer) {
            targetRow = ((RailLayer) target.getLayer()).getRowList();
            sourceRow = ((RailLayer) source.getLayer()).getRowList();
        } else {
            throw new IllegalArgumentException("Only works with RailLayers!");
        }
        int sourcePos = sourcePosition;
        if (sourcePos != Integer.MIN_VALUE) {
            sourcePos = sourceRow.getPosition(source);
        }
        switch (source.getProperty(Properties.NODE_TYPE)) {
        case BREACH_OR_CLOSE:
            switch (target.getProperty(Properties.NODE_TYPE)) {
            case BREACH_OR_CLOSE:
                return patterns.breachBreach(targetRow.getOccupiedPositions(), sourcePos);
            case SWITCH_LEFT:
                switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                case BRANCH:
                    return patterns.breachSwitchLeftBranch(targetRow.getOccupiedPositions(),
                            sourcePos);
                case STRAIGHT:
                    return patterns.breachSwitchLeftStraight(targetRow.getOccupiedPositions(),
                            sourcePos);
                case STUMP:
                    return patterns.breachSwitchLeftStump(targetRow.getOccupiedPositions(),
                            sourcePos);
                }
                break;
            case SWITCH_RIGHT:
                switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                case BRANCH:
                    return patterns.breachSwitchRightBranch(targetRow.getOccupiedPositions(),
                            sourcePos);
                case STRAIGHT:
                    return patterns.breachSwitchRightStraight(targetRow.getOccupiedPositions(),
                            sourcePos);
                case STUMP:
                    return patterns.breachSwitchRightStump(targetRow.getOccupiedPositions(),
                            sourcePos);
                }
                break;
            }
            break;
        case SWITCH_LEFT:
            switch (sourcePort.getProperty(Properties.PORT_TYPE)) {
            case BRANCH:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchLeftBranchBreach(targetRow.getOccupiedPositions(),
                            sourcePos);
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftBranchSwitchLeftBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchLeftBranchSwitchLeftStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchLeftBranchSwitchLeftStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftBranchSwitchRightBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchLeftBranchSwitchRightStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchLeftBranchSwitchRightStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                }
                break;
            case STRAIGHT:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchLeftStraightBreach(targetRow.getOccupiedPositions(),
                            sourcePos);
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftStraightSwitchLeftBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchLeftStraightSwitchLeftStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchLeftStraightSwitchLeftStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftStraightSwitchRightBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchLeftStraightSwitchRightStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchLeftStraightSwitchRightStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                }
                break;
            case STUMP:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchLeftStumpBreach(targetRow.getOccupiedPositions(),
                            sourcePos);
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftStumpSwitchLeftBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchLeftStumpSwitchLeftStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchLeftStumpSwitchLeftStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftStumpSwitchRightBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchLeftStumpSwitchRightStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchLeftStumpSwitchRightStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                }
                break;
            }
            break;
        case SWITCH_RIGHT:
            switch (sourcePort.getProperty(Properties.PORT_TYPE)) {
            case BRANCH:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchRightBranchBreach(targetRow.getOccupiedPositions(),
                            sourcePos);
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightBranchSwitchLeftBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchRightBranchSwitchLeftStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchRightBranchSwitchLeftStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightBranchSwitchRightBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchRightBranchSwitchRightStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchRightBranchSwitchRightStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                }
                break;
            case STRAIGHT:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchRightStraightBreach(targetRow.getOccupiedPositions(),
                            sourcePos);
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightStraightSwitchLeftBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchRightBranchSwitchLeftStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchRightBranchSwitchLeftStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightStraightSwitchRightBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchRightBranchSwitchRightStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchRightBranchSwitchRightStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                }
                break;
            case STUMP:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchRightStumpBreach(targetRow.getOccupiedPositions(),
                            sourcePos);
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightStumpSwitchLeftBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchRightStumpSwitchLeftStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchRightStumpSwitchLeftStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightStumpSwitchRightBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchRightStumpSwitchRightStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchRightStumpSwitchRightStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                }
                break;
            }
            break;
        }
        return 0;
    }

}
